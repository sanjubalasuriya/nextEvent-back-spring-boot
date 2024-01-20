package com.nextevent.repository;

import com.nextevent.entity.Event;
import com.nextevent.entity.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketCategoryRepo extends JpaRepository<TicketCategory, Integer> {


    List<TicketCategory> findAllByEvent(Event event);
}
