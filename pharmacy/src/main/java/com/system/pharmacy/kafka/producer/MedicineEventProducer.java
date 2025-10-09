package com.system.pharmacy.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MedicineEventProducer {

    private final KafkaTemplate<String, MedicineOutOfStockEvent>  kafkaTemplate;
    private static final String TOPIC = "medicine-events";

    public MedicineEventProducer(KafkaTemplate<String, MedicineOutOfStockEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMedicineOutOfStockEvent(MedicineOutOfStockEvent event){
        // Sending Id, will all go to same partition.
        kafkaTemplate.send(TOPIC, event.medicineId().toString(), event);
        log.info("Event sent to kafka: {}", event);
    }
}
