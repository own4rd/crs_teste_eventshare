package com.wagner.event_schedule.service;

import com.wagner.event_schedule.dtos.v1.requests.CreateEventRequestDto;
import com.wagner.event_schedule.dtos.v1.requests.UpdateEventRequestDto;
import com.wagner.event_schedule.dtos.v1.responses.EventResponseDto;

import java.util.List;

public interface EventService {
    void createEvent(CreateEventRequestDto createEventRequestDto);
    List<EventResponseDto> findAll(Boolean activeFilter);
    void deleteEvent(Long id);
    EventResponseDto updateEvent(UpdateEventRequestDto updateEventRequestDto);
    EventResponseDto findOneById(Long id);
}
