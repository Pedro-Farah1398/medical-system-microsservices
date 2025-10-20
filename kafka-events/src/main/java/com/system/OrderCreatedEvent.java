package com.system.events;

import java.time.Instant;

public record OrderCreatedEvent(
        String orderId,
        Long medicineId,
        String priority,
        Instant createdAt
) {
}
