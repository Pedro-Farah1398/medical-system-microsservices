package com.system.laboratory_service.service;

import com.system.events.laboratory.ExamRequestedEvent;
import com.system.events.laboratory.ExamResultCriticalEvent;
import com.system.events.laboratory.ExamResultReadyEvent;
import com.system.events.laboratory.ExamSampleCollectedEvent;
import com.system.laboratory_service.domain.postgres.OutboxEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExamEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ExamEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishExamRequested(OutboxEvent event) {
        kafkaTemplate.send("lab.exam.requested", event.getAggregateId().toString(), event.getPayload());
    }

    public void publishExamSampleCollected(ExamSampleCollectedEvent event) {
        kafkaTemplate.send("lab.exam.sample_collected", event.examId().toString(), event);
    }

    public void publishResultReady(ExamResultReadyEvent event) {
        kafkaTemplate.send("lab.exam.result_ready", event.examId().toString(), event);
    }

    public void publishResultCritical(ExamResultCriticalEvent event) {
        kafkaTemplate.send("lab.exam.result_critical", event.examId().toString(), event);
    }
}
