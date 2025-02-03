package TableTap.services;

import TableTap.models.dto.MessageDTO;

public interface ChatService {
    MessageDTO sendMessage(String fromUser, String toUser, String content);
}
