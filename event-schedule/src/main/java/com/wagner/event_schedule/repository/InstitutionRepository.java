package com.wagner.event_schedule.repository;

import com.wagner.event_schedule.model.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
