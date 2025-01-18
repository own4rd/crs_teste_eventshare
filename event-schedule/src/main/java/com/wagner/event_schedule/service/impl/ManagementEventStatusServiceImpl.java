package com.wagner.event_schedule.service.impl;

import com.wagner.event_schedule.model.entity.Event;
import com.wagner.event_schedule.repository.EventRepository;
import com.wagner.event_schedule.service.ManagementEventStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ManagementEventStatusServiceImpl implements ManagementEventStatusService {

    private final EventRepository eventRepository;

    @Value("${scheduler.cron}")
    private String cronExpression;

    @Scheduled(cron = "${scheduler.cron}", zone = "America/Sao_Paulo")
    public void activateEvents() {
        LocalDate today = LocalDate.now();
        List<Event> eventsToActivate = eventRepository.findEventsToActivate(today);
        eventsToActivate.parallelStream().forEach(event -> {
            event.setActive(true);
            eventRepository.save(event);
        });
    }

    @Scheduled(cron = "${scheduler.cron}", zone = "America/Sao_Paulo")
    public void deactivateEvents() {
        LocalDate today = LocalDate.now();
        List<Event> eventsToDeactivate = eventRepository.findEventsToDeactivate(today);
        eventsToDeactivate.parallelStream().forEach(event -> {
            event.setActive(false);
            eventRepository.save(event);
        });
    }
}

