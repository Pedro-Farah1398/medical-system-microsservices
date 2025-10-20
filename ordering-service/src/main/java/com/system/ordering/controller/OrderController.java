package com.system.ordering.controller;

import com.system.ordering.entity.Order;
import com.system.ordering.entity.OrderStatus;
import com.system.ordering.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PatchMapping("/{id}/status")
    public Order updateStatus(@PathVariable String id, @RequestParam OrderStatus orderStatus) {
        return orderService.updateStatus(id, orderStatus).orElseThrow(() -> new RuntimeException("Order not found."));
    }
}
