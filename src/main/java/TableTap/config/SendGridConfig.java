package TableTap.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfig {

    @Value("${spring.sendgrid.api-key}")
    private String API_KEY;

    @Value("${spring.sendgrid.email-from}")
    private String EMAIL_FROM;

    @Bean
    public SendGrid sendGrid() {
        return new SendGrid(API_KEY);
    }

    @Bean(name = "emailFromAddress")
    public String emailFrom() {
        return EMAIL_FROM;
    }
}
