package com.system.events;

import java.time.Instant;

public record MedicineOutOfStockEvent(
        Long medicineId,
        String medicineName,
        Instant timestamp,
        String priority
) {
}
