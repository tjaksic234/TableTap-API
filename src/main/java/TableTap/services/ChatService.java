package TableTap.services;

import TableTap.models.dao.Message;

public interface ChatService {
    Message sendMessage(String fromUser, String toUser, String content);
}
