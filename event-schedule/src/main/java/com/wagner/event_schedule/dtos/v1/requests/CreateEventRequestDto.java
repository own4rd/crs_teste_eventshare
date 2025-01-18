package com.wagner.event_schedule.dtos.v1.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateEventRequestDto(
        @NotNull
        Long institutionId,
        @NotNull
        @NotBlank
        String name,
        @NotNull
        @FutureOrPresent
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,
        @NotNull
        @Future
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate

) {
}
