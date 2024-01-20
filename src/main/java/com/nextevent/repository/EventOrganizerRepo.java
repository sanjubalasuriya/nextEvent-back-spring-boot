package com.nextevent.repository;

import com.nextevent.entity.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventOrganizerRepo extends JpaRepository<EventOrganizer, Integer> {
}
