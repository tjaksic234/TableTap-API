package TableTap.services.impl;

import TableTap.converters.ConverterService;
import TableTap.models.dao.Message;
import TableTap.models.dto.MessageDTO;
import TableTap.repository.MessageRepository;
import TableTap.services.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ChatServiceImpl implements ChatService {

    private final SimpMessagingTemplate messagingTemplate;

    private final MessageRepository messageRepository;

    private final ConverterService converterService;

    @Override
    public MessageDTO sendMessage(String fromUser, String toUser, String content) {

        Message message = new Message();

        return converterService.convertMessageToMessageDTO(message);
    }
}
