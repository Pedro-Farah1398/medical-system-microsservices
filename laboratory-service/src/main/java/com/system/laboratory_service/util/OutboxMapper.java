package com.system.laboratory_service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.events.laboratory.ExamRequestedEvent;
import com.system.laboratory_service.domain.postgres.Exam;
import com.system.laboratory_service.domain.postgres.OutboxEvent;

public class OutboxMapper {

    private static ObjectMapper mapper = new ObjectMapper();

    public static OutboxEvent createOutboxEvent(Exam exam, ExamRequestedEvent examRequestedEvent) throws JsonProcessingException {
        OutboxEvent outboxEvent = new OutboxEvent();

        outboxEvent.setAggregateType("exam");
        outboxEvent.setAggregateId(exam.getId());
        outboxEvent.setEventType(examRequestedEvent.getClass().getName());
        outboxEvent.setPayload(mapper.writeValueAsString(examRequestedEvent));

        return outboxEvent;
    }

}
