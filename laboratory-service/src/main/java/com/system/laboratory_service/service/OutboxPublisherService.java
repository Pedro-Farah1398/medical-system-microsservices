package com.system.laboratory_service.service;

import com.system.laboratory_service.domain.postgres.OutboxEvent;
import com.system.laboratory_service.repository.postgres.OutboxEventRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class OutboxPublisherService {

    private final OutboxEventRepository repository;
    private final ExamEventPublisher  publisher;

    public OutboxPublisherService(OutboxEventRepository repository,
                                  ExamEventPublisher  publisher
    ) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @Scheduled(fixedDelay = 5000)
    public void publishPendingEvents() {
        List<OutboxEvent> pending = repository.findByStatus("NEW");

        for (OutboxEvent event : pending) {
            try{
                publisher.publishExamRequested(event);
                event.setStatus("SENT");
                repository.save(event);
            } catch (Exception e) {
                //TODO -> RETRY ATTEMPT
            }
        }
    }

}
