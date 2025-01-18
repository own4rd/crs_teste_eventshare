package com.wagner.event_schedule.model.mappers;

import com.wagner.event_schedule.dtos.v1.responses.InstitutionResponseDto;
import com.wagner.event_schedule.model.entity.Institution;
import org.springframework.stereotype.Component;

@Component
public class InstitutionMapper {

    public InstitutionResponseDto toDto(Institution institution) {
        return new InstitutionResponseDto(
                institution.getId(),
                institution.getName(),
                institution.getType().name()
        );
    }
}

