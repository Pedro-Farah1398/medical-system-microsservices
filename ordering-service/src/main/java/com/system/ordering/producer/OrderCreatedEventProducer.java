package com.system.ordering.producer;

import com.system.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderCreatedEventProducer {

    private final KafkaTemplate<String, OrderCreatedEvent>  kafkaTemplate;
    private static final String TOPIC = "order.created";

    public OrderCreatedEventProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent event){
        // Sending Id, will all go to same partition.
        kafkaTemplate.send(TOPIC, event.medicineId().toString(), event);
        log.info("Event sent to kafka: {}", event);
    }
}
