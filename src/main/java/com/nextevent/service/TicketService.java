package com.nextevent.service;

import com.nextevent.dto.paginatedDto.ResponseTicketPaginatedDTO;
import com.nextevent.dto.requestDto.RequestTicketDTO;
import com.nextevent.dto.requestDto.RequestTicketDTOList;
import com.nextevent.dto.requestDto.RequestUpdateTicketDTO;
import com.nextevent.dto.responsetDto.ResponseTicketDTO;

import java.util.List;

public interface TicketService {
    String saveTicket(RequestTicketDTOList requestTicketDTOList);

    ResponseTicketDTO findByCategoryId(int ticketId);

    String updateTicket(RequestUpdateTicketDTO requestUpdateTicketDTO);

    String deleteTicket(int categoryId);

    List<ResponseTicketDTO> getAllTickets();

    ResponseTicketPaginatedDTO getAllTicketPaginate(int page, int size);
}
