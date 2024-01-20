package com.nextevent.service.serviceImpl;

import com.nextevent.dto.paginatedDto.ResponseEventPaginatedDTO;
import com.nextevent.dto.paginatedDto.ResponseOrganizerPaginatedDTO;
import com.nextevent.dto.requestDto.RequestEventDTO;
import com.nextevent.dto.requestDto.RequestUpdateEventDTO;
import com.nextevent.dto.responsetDto.ResponseEventDTO;
import com.nextevent.dto.responsetDto.ResponseOrganizerDTO;
import com.nextevent.entity.Event;
import com.nextevent.entity.EventOrganizer;
import com.nextevent.repository.EventOrganizerRepo;
import com.nextevent.repository.EventRepo;
import com.nextevent.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;
    private final EventOrganizerRepo eventOrganizerRepo;
    @Override
    public String saveEvent(RequestEventDTO requestEventDTO) {
        if (eventOrganizerRepo.existsById(requestEventDTO.getEventOrganizerId())) {
            EventOrganizer eventOrganizer = eventOrganizerRepo.getReferenceById(requestEventDTO.getEventOrganizerId());

            Event event = new Event(
                    requestEventDTO.getEventName(),
                    requestEventDTO.getEventLocation(),
                    requestEventDTO.getEventDate(),
                    requestEventDTO.getTime(),
                    requestEventDTO.getEventBanner(),
                    eventOrganizer
            );

            eventRepo.save(event);
            return event.getEventName() + " Saved";
        }
        else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public ResponseEventDTO findByEventId(int eventId) {
        if (eventRepo.existsById(eventId)){
            Event event = eventRepo.getReferenceById(eventId);

            return new ResponseEventDTO(
                    event.getEventId(),
                    event.getEventName(),
                    event.getEventLocation(),
                    event.getEventDate(),
                    event.getTime(),
                    event.getEventBanner()

            );
        }else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public String updateEvent(RequestUpdateEventDTO requestUpdateEventDTO) {
        if (eventRepo.existsById(requestUpdateEventDTO.getEventId())) {
            Event event = eventRepo.getReferenceById(requestUpdateEventDTO.getEventId());

            event.setEventId(requestUpdateEventDTO.getEventId());
            event.setEventName(requestUpdateEventDTO.getEventName());
            event.setEventLocation(requestUpdateEventDTO.getEventLocation());
            event.setEventDate(requestUpdateEventDTO.getEventDate());
            event.setEventDate(requestUpdateEventDTO.getTime());
            event.setEventBanner(requestUpdateEventDTO.getEventBanner());

            eventRepo.save(event);
            return event.getEventName() + " Updated";
        }
        else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public String deleteEvent(int eventId) {
        if (eventRepo.existsById(eventId)){
            Event event = eventRepo.getReferenceById(eventId);
            eventOrganizerRepo.deleteById(event.getEventId());

            return event.getEventName()+ " Deleted";
        }
        return "Organizer not found";
    }

    @Override
    public List<ResponseEventDTO> getAllEvent() {
        List<Event> eventList = eventRepo.findAll();

        List<ResponseEventDTO> responseEventDTOS = new ArrayList<>();

        for(Event event : eventList){
            ResponseEventDTO responseEventDTO = new ResponseEventDTO(
                    event.getEventId(),
                    event.getEventName(),
                    event.getEventLocation(),
                    event.getEventDate(),
                    event.getTime(),
                    event.getEventBanner()
            );
            responseEventDTOS.add(responseEventDTO);
        }
        return responseEventDTOS;
    }

    @Override
    public ResponseEventPaginatedDTO getAllEventPaginate(int page, int size) {
        Page<Event> eventList = eventRepo.findAll(PageRequest.of(page, size));

        List<ResponseEventDTO> responseEventDTOS = new ArrayList<>();

        for(Event event : eventList){
            ResponseEventDTO responseEventDTO = new ResponseEventDTO(
                    event.getEventId(),
                    event.getEventName(),
                    event.getEventLocation(),
                    event.getEventDate(),
                    event.getTime(),
                    event.getEventBanner()
            );
            responseEventDTOS.add(responseEventDTO);
        }

        return new ResponseEventPaginatedDTO(
                responseEventDTOS,
                eventRepo.count()
        );
    }
}
