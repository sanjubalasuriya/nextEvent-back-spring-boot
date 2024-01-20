package com.nextevent.service;

import com.nextevent.dto.paginatedDto.ResponseEventPaginatedDTO;
import com.nextevent.dto.requestDto.RequestEventDTO;
import com.nextevent.dto.requestDto.RequestUpdateEventDTO;
import com.nextevent.dto.responsetDto.ResponseEventDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EventService {
    String saveEvent(RequestEventDTO requestEventDTO);

    ResponseEventDTO findByEventId(int eventId);

    String updateEvent(RequestUpdateEventDTO requestUpdateEventDTO);

    String deleteEvent(int eventId);

    List<ResponseEventDTO> getAllEvent();

    ResponseEventPaginatedDTO getAllEventPaginate(int page, int size);
}
