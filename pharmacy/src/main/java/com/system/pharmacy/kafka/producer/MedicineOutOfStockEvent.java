package com.system.pharmacy.kafka.producer;

import java.time.Instant;

public record MedicineOutOfStockEvent(
        Long medicineId,
        String medicineName,
        Instant timestamp
) {}
