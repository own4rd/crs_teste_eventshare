package com.wagner.event_schedule.model.mappers;

import com.wagner.event_schedule.dtos.v1.requests.CreateEventRequestDto;
import com.wagner.event_schedule.dtos.v1.responses.EventResponseDto;
import com.wagner.event_schedule.model.entity.Event;
import com.wagner.event_schedule.model.entity.Institution;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public Event toEntity(CreateEventRequestDto createEventRequestDto) {
        return Event.builder()
                .name(createEventRequestDto.name())
                .startDate(createEventRequestDto.startDate())
                .endDate(createEventRequestDto.endDate())
                .institution(Institution.builder().id(createEventRequestDto.institutionId()).build())
                .build();
    }

    public EventResponseDto toEventResponseDto(Event event) {
        return new EventResponseDto(
                event.getId(),
                event.getName(),
                event.getStartDate(),
                event.getEndDate(),
                event.getInstitution().getName(),
                event.isActive()
        );
    }
}
