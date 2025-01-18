package com.wagner.event_schedule.dtos.v1.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ValidationErrorResponseDto {
    private Date timestamp;
    private String message;
    private List<String> errors;

}
