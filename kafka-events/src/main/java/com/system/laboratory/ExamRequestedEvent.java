package com.system.events.laboratory;

import java.util.Date;
import java.util.UUID;

public record ExamRequestedEvent(
        UUID examId,
        UUID patientId,
        UUID physicianId,
        String testType,
        String priority,
        Date requestedAt
) {
}
