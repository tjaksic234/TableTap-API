package TableTap.services.impl;

import TableTap.models.dao.Message;
import TableTap.services.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static TableTap.security.utils.Constants.CHAT_CHANNEL_PREFIX;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ChatServiceImpl implements ChatService {

    private final RedissonClient redissonClient;

    @Override
    public Message sendMessage(String fromUser, String toUser, String content) {

        Message message = new Message();
        message.setFromUser(fromUser);
        message.setToUser(toUser);
        message.setContent(content);

        RTopic topic = redissonClient.getTopic(CHAT_CHANNEL_PREFIX + toUser);

        topic.publish(message);

        log.info("Message sent from {} to {}: {}", fromUser, toUser, content);
        return message;
    }
}
