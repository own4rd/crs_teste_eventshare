package com.wagner.event_schedule.model.entity;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "institution_id", nullable = false)
    private Institution institution;
}
