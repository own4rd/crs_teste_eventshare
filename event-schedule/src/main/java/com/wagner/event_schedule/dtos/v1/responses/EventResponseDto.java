package com.wagner.event_schedule.dtos.v1.responses;

import java.time.LocalDate;

public record EventResponseDto(
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String institutionName,
        Boolean status
) {
}
