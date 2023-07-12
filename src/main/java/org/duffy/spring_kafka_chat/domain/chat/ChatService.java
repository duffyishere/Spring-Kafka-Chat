package org.duffy.spring_kafka_chat.domain.chat;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ChatService {
    public static Logger logger = LoggerFactory.getLogger(ChatService.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final String CHAT_TOPIC = "chatting1";
    private final String GROUP_ID = "chatGroup";

    public void sendMessageToTopic(String message) {
        final ProducerRecord<String, String> record = new ProducerRecord<>(CHAT_TOPIC, GROUP_ID, message);
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(record);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                // TODO: handle failure
            }
            else {
                kafkaTemplate.flush();
                logger.info("Send message successful.");
            }
        });
    }

    @KafkaListener(groupId = GROUP_ID, topics = CHAT_TOPIC)
    public void receiveMessageToTopic(String message) {
        logger.info("Received Message: " + message);
    }
}
