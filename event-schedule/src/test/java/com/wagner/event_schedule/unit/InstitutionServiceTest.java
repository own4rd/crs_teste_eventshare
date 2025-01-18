package com.wagner.event_schedule.unit;

import com.wagner.event_schedule.dtos.v1.requests.CreateInstitutionRequestDto;
import com.wagner.event_schedule.dtos.v1.responses.InstitutionResponseDto;
import com.wagner.event_schedule.model.entity.Institution;
import com.wagner.event_schedule.model.enums.InstitutionType;
import com.wagner.event_schedule.model.mappers.InstitutionMapper;
import com.wagner.event_schedule.repository.InstitutionRepository;
import com.wagner.event_schedule.service.impl.InstitutionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class InstitutionServiceTest {

    @Mock
    private InstitutionRepository institutionRepository;

    @Mock
    private InstitutionMapper institutionMapper;

    @InjectMocks
    private InstitutionServiceImpl institutionService;

    private Institution institution;
    private CreateInstitutionRequestDto createInstitutionRequestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        institution = new Institution();
        institution.setId(1L);
        institution.setName("Instituição A");
        institution.setType(InstitutionType.CONFEDERATION);

        createInstitutionRequestDto = new CreateInstitutionRequestDto(
                "Instituição A",
                InstitutionType.CONFEDERATION.name()
        );
    }

    @Test
    void testCreateInstitution() {
        when(institutionRepository.save(any(Institution.class))).thenReturn(institution);

        institutionService.createInstitution(createInstitutionRequestDto);

        verify(institutionRepository, times(1)).save(any(Institution.class));
    }

    @Test
    void testFindAllInstitutions() {
        InstitutionResponseDto institutionResponseDto = new InstitutionResponseDto(institution.getId(), institution.getName(), institution.getType().name());
        when(institutionRepository.findAll()).thenReturn(Collections.singletonList(institution));
        when(institutionMapper.toDto(institution)).thenReturn(institutionResponseDto);

        List<InstitutionResponseDto> response = institutionService.findAllInstitutions();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Instituição A", response.getFirst().name());
        assertEquals("CONFEDERATION", response.getFirst().type());
    }

    @Test
    void testCreateInstitutionWithInvalidType() {
        createInstitutionRequestDto = new CreateInstitutionRequestDto("Instituição B", "InvalidType");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            institutionService.createInstitution(createInstitutionRequestDto);
        });
    }

    @Test
    void testFindInstitutionById() {
        when(institutionRepository.findById(1L)).thenReturn(Optional.of(institution));

        Optional<Institution> foundInstitution = institutionRepository.findById(1L);

        assertTrue(foundInstitution.isPresent());
        assertEquals(1L, foundInstitution.get().getId());
        assertEquals("Instituição A", foundInstitution.get().getName());
    }

    @Test
    void testFindInstitutionByIdNotFound() {
        when(institutionRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Institution> foundInstitution = institutionRepository.findById(1L);

        assertFalse(foundInstitution.isPresent());
    }
}

