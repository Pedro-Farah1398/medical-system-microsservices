package com.system.ordering.consumer;

import com.system.events.MedicineOutOfStockEvent;
import com.system.ordering.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MedicineOutOfStockConsumer {

    private final OrderService orderService;

    public MedicineOutOfStockConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "medicine-events", groupId = "ordering-service-group")
    public void consumeOutOfStockEvent(@Payload MedicineOutOfStockEvent event) {
        log.info("Received out-of-stock event: {}", event);

        orderService.generatePurchaseOrder(event);
    }
}
