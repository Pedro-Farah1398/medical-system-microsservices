package com.system.events.laboratory;

import java.util.Date;
import java.util.UUID;

public record ExamResultCriticalEvent(
        UUID examId,
        UUID resultId,
        UUID patientId,
        String analyte,
        Double value,
        String unit,
        String threshold,
        Date measuredAt
) {
}
