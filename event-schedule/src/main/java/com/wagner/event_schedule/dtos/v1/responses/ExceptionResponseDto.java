package com.wagner.event_schedule.dtos.v1.responses;

import java.util.Date;

public record ExceptionResponseDto(
        Date timestamp,
        String message,
        String details
) {
}
