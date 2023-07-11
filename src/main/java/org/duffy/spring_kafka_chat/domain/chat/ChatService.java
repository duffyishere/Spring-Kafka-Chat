package org.duffy.spring_kafka_chat.domain.chat;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final String CHAT_TOPIC = "chatting";
    public void sendMessageToTopic(String message) {
        final ProducerRecord<String, String> record = new ProducerRecord<>(CHAT_TOPIC, message);
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(record);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                // TODO: handle failure
            }
        });
    }
}
