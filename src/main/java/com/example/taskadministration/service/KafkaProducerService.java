package com.example.taskadministration.service;

import com.example.taskadministration.dto.request.TaskRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, TaskRequest> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, TaskRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(TaskRequest taskRequest) {
        kafkaTemplate.send("task_topic", taskRequest);
    }
}
