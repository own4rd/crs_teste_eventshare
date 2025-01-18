package com.wagner.event_schedule.dtos.v1.requests;

import com.wagner.event_schedule.dtos.v1.validators.ValueOfTypeInstitutionEnum;
import com.wagner.event_schedule.model.enums.InstitutionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateInstitutionRequestDto(
        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotNull(message = "Tipo é obrigatório")
        @ValueOfTypeInstitutionEnum(enumClass = InstitutionType.class)
        String type
) {
}
