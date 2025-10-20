package com.system.events.laboratory;

import java.util.Date;
import java.util.UUID;

public record ExamAnalysisStartedEvent(
        UUID examId,
        Date startedAt,
        Date DueDate
) {

}
