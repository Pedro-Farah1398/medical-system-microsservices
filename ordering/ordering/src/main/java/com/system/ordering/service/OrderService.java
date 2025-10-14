package com.system.ordering.service;

import com.system.events.MedicineOutOfStockEvent;
import com.system.events.OrderCreatedEvent;
import com.system.ordering.entity.Order;
import com.system.ordering.entity.OrderStatus;
import com.system.ordering.producer.OrderCreatedEventProducer;
import com.system.ordering.repository.OrderRepository;
import com.system.ordering.util.PurchaseOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderCreatedEventProducer orderCreatedEventProducer;

    public OrderService(OrderRepository orderRepository, OrderCreatedEventProducer orderCreatedEventProducer) {
        this.orderRepository = orderRepository;
        this.orderCreatedEventProducer = orderCreatedEventProducer;
    }

    public void generatePurchaseOrder(MedicineOutOfStockEvent event) {
        Order order = PurchaseOrderMapper.from(event);
        Order savedOrder = orderRepository.save(order);
        orderCreatedEventProducer.sendOrderCreatedEvent(new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getMedicineId(),
                savedOrder.getPriority(),
                savedOrder.getCreatedAt().toInstant()
        ));

    }

    private String getSupplierBasedOnMedicineName() {
        return "";
    }

    public Optional<Order> updateStatus(String medicineId, OrderStatus orderStatus) {
        return orderRepository.findById(medicineId).map(order -> {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        });
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
