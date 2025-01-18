package com.wagner.event_schedule.repository;

import com.wagner.event_schedule.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.startDate <= :today AND e.endDate >= :today AND e.active = false")
    List<Event> findEventsToActivate(LocalDate today);

    @Query("SELECT e FROM Event e WHERE e.endDate < :today AND e.active = true")
    List<Event> findEventsToDeactivate(LocalDate today);

    List<Event> findByActive(Boolean active);

}
