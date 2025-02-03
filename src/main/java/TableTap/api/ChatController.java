package TableTap.api;

import TableTap.models.dto.MessageDTO;
import TableTap.services.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static TableTap.security.utils.Constants.API_ROUTE;

@RestController
@RequestMapping(API_ROUTE + "/chat")
@Slf4j
@AllArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/send")
    public ResponseEntity<MessageDTO> sendMessage(@RequestParam String fromUser,
                                                  @RequestParam String toUser,
                                                  @RequestParam String content)
    {
        log.info("Attempting to send a message");
        return ResponseEntity.ok(chatService.sendMessage(fromUser, toUser, content));
    }
}
