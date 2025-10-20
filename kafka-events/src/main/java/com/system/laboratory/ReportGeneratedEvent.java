package com.system.events.laboratory;

import java.util.UUID;

public record ReportGeneratedEvent(
        UUID examId,
        UUID reportId,
        String reportUrl,
        String format
) {

}
