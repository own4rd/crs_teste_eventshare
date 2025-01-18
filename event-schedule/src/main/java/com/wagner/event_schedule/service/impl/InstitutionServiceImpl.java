package com.wagner.event_schedule.service.impl;

import com.wagner.event_schedule.dtos.v1.requests.CreateInstitutionRequestDto;
import com.wagner.event_schedule.dtos.v1.responses.InstitutionResponseDto;
import com.wagner.event_schedule.model.entity.Institution;
import com.wagner.event_schedule.model.enums.InstitutionType;
import com.wagner.event_schedule.model.mappers.InstitutionMapper;
import com.wagner.event_schedule.repository.InstitutionRepository;
import com.wagner.event_schedule.service.InstitutionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final InstitutionMapper institutionMapper;

    @Override
    public void createInstitution(CreateInstitutionRequestDto createInstitutionRequestDto) {
        log.info("Criando instituição: {}", createInstitutionRequestDto);
        Institution institution = Institution.builder().name(
            createInstitutionRequestDto.name()
        ).type(
                InstitutionType.valueOf(createInstitutionRequestDto.type().toUpperCase())
        ).build();
        institutionRepository.save(institution);
        log.info("Instituição criada com sucesso: {}", institution);
    }

    @Override
    public List<InstitutionResponseDto> findAllInstitutions() {
        log.info("Buscando todas as instituições");
        List<Institution> institutions = institutionRepository.findAll();

        return institutions.stream()
                .map(institutionMapper::toDto)
                .collect(Collectors.toList());
    }

    private InstitutionResponseDto convertToDto(Institution institution) {
        return new InstitutionResponseDto(
                institution.getId(),
                institution.getName(),
                institution.getType().name()
        );
    }
}
