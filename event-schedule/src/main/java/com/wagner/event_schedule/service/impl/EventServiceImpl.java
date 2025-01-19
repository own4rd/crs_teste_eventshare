package com.wagner.event_schedule.service.impl;

import com.wagner.event_schedule.dtos.v1.requests.CreateEventRequestDto;
import com.wagner.event_schedule.dtos.v1.responses.EventResponseDto;
import com.wagner.event_schedule.exceptions.ResourceNotFoundException;
import com.wagner.event_schedule.model.entity.Event;
import com.wagner.event_schedule.model.entity.Institution;
import com.wagner.event_schedule.model.mappers.EventMapper;
import com.wagner.event_schedule.repository.EventRepository;
import com.wagner.event_schedule.repository.InstitutionRepository;
import com.wagner.event_schedule.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final InstitutionRepository institutionRepository;
    private final EventMapper eventMapper;

    @Override
    public void createEvent(CreateEventRequestDto createEventRequestDto) {
        if (createEventRequestDto.startDate().isAfter(createEventRequestDto.endDate())) {
            throw new IllegalArgumentException("Data de início não pode ser após a data de término");
        }
        Institution institution = institutionRepository.findById(createEventRequestDto.institutionId())
                .orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada"));

        LocalDate today = LocalDate.now();
        boolean isActive = !today.isBefore(createEventRequestDto.startDate()) && !today.isAfter(createEventRequestDto.endDate());
        Event event = Event.builder()
                .name(createEventRequestDto.name())
                .startDate(createEventRequestDto.startDate())
                .endDate(createEventRequestDto.endDate())
                .institution(institution)
                .active(isActive)
                .build();

        eventRepository.save(event);
    }

    @Override
    public List<EventResponseDto> findAll(Boolean activeFilter) {
        List<Event> events;
        if (activeFilter == null) {
            events = eventRepository.findAll();
        } else {
            events = eventRepository.findByActive(activeFilter);
        }

        return events.stream().map(eventMapper::toEventResponseDto).toList();
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));

        eventRepository.delete(event);
    }
}
