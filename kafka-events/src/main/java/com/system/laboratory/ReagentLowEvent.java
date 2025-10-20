package com.system.events.laboratory;

public record ReagentLowEvent(
        String reagentId,
        String reagentName,
        Integer stockLevel,
        Integer threshold
) {
}
