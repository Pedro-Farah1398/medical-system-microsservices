package com.system.events.laboratory;

import java.util.Date;
import java.util.UUID;

public record ExamSampleCollectedEvent(
        UUID examId,
        UUID sampleId,
        UUID collectorId,
        Date collectedAt,
        String storageLocation
) {
}
