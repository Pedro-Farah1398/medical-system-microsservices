package com.system.events.laboratory;

import java.util.Date;
import java.util.UUID;

public record ExamResultReadyEvent(
        UUID examId,
        UUID resultId,
        Date measuredAt,
        Boolean critical,
        String summary
) {
}
