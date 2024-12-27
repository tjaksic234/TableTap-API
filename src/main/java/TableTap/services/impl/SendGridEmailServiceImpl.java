package TableTap.services.impl;

import TableTap.services.SendGridEmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.util.Base64;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class SendGridEmailServiceImpl implements SendGridEmailService {

    private final SendGrid sendGrid;

    @Override
    public Response sendEmail(String from, String to, String subject, Content content) {
        Mail mail = new Mail(new Email(from), subject, new Email(to), content);
        try {
            // Read image from resources
            Resource resource = new ClassPathResource("static/menu.png");
            byte[] imageBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String base64Content = Base64.getEncoder().encodeToString(imageBytes);

            Attachments attachment = new Attachments();
            attachment.setContent(base64Content);
            attachment.setType("image/png");
            attachment.setFilename("menu.png");
            attachment.setDisposition("inline");
            attachment.setContentId("menu-logo");

            mail.addAttachments(attachment);
        } catch (IOException e) {
            log.error("Failed to attach image: {}", e.getMessage());
        }

        Request request = new Request();
        Response response = null;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = sendGrid.api(request);
        } catch (IOException e) {
            log.error("Failed to send email: {}", e.getMessage());
        }
        return response;
    }

    @Override
    public void sendHtml(String from, String to, String subject, String body) {
        Response response = sendEmail(from, to, subject, new Content("text/html", body));
        logResponse(response);
    }

    private static void logResponse(Response response) {
        if (response != null) {
            log.info("Status Code: {}, Body: {}, Headers: {}", response.getStatusCode(), response.getBody(), response.getHeaders());
        }
    }
}
