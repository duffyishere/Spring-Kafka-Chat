package org.duffy.spring_kafka_chat.domain.chat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChatServiceTests {
    @Autowired
    private ChatService chatService;

    @Test
    void sendMessage() {
        chatService.sendMessageToTopic("This is test message.");
    }
}