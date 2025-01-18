package com.wagner.event_schedule.service;

import com.wagner.event_schedule.dtos.v1.requests.CreateInstitutionRequestDto;
import com.wagner.event_schedule.dtos.v1.responses.InstitutionResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InstitutionService {
    void createInstitution(CreateInstitutionRequestDto institution);
    List<InstitutionResponseDto> findAllInstitutions();
}
