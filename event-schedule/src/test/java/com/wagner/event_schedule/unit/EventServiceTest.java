package com.wagner.event_schedule.unit;


import com.wagner.event_schedule.dtos.v1.requests.CreateEventRequestDto;
import com.wagner.event_schedule.exceptions.ResourceNotFoundException;
import com.wagner.event_schedule.model.entity.Event;
import com.wagner.event_schedule.model.entity.Institution;
import com.wagner.event_schedule.repository.EventRepository;
import com.wagner.event_schedule.repository.InstitutionRepository;
import com.wagner.event_schedule.service.impl.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private InstitutionRepository institutionRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    private Institution institution;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        institution = new Institution();
        institution.setId(1L);
        institution.setName("Instituição Teste");
    }

    @Test
    public void testCreateEvent_WhenInstitutionFound_ThenEventCreated() {
        CreateEventRequestDto createEventRequestDto = new CreateEventRequestDto(
                1L,
                "Evento Teste",
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2025, 1, 20)
        );

        when(institutionRepository.findById(1L)).thenReturn(Optional.of(institution));

        eventService.createEvent(createEventRequestDto);

        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    public void testCreateEvent_WhenInstitutionNotFound_ThenThrowResourceNotFoundException() {
        CreateEventRequestDto createEventRequestDto = new CreateEventRequestDto(
                1L,                "Evento Teste",
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2025, 1, 20)
        );

        when(institutionRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            eventService.createEvent(createEventRequestDto);
        });

        assertEquals("Instituição não encontrada", exception.getMessage());
    }

    @Test
    public void testCreateEvent_WhenStartDateAfterEndDate_ThenThrowIllegalArgumentException() {
        CreateEventRequestDto createEventRequestDto = new CreateEventRequestDto(
                1L,
                "Evento Teste",
                LocalDate.of(2025, 1, 21),
                LocalDate.of(2025, 1, 20)
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            eventService.createEvent(createEventRequestDto);
        });

        assertEquals("Data de início não pode ser após a data de término", exception.getMessage());
    }
}
