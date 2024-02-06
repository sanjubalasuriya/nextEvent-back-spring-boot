package com.nextevent.service.serviceImpl;

import com.nextevent.dto.paginatedDto.ResponseTicketPaginatedDTO;
import com.nextevent.dto.requestDto.RequestTicketDTO;
import com.nextevent.dto.requestDto.RequestTicketDTOList;
import com.nextevent.dto.requestDto.RequestUpdateTicketDTO;
import com.nextevent.dto.responsetDto.ResponseTicketDTO;
import com.nextevent.entity.Event;
import com.nextevent.entity.Ticket;
import com.nextevent.entity.TicketCategory;
import com.nextevent.repository.CustomerRepo;
import com.nextevent.repository.EventRepo;
import com.nextevent.repository.TicketCategoryRepo;
import com.nextevent.repository.TicketRepo;
import com.nextevent.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;
    private final TicketCategoryRepo ticketCategoryRepo;
    private final CustomerRepo customerRepo;
    private final EventRepo eventRepo;

    @Override
    public String saveTicket(RequestTicketDTOList requestTicketDTOList) {
        List<RequestTicketDTO> requestTicketDTOSaveList = requestTicketDTOList.getRequestTicketDTOList();

        List<Ticket> ticketList = new ArrayList<>();

        for (RequestTicketDTO requestTicketDTO : requestTicketDTOSaveList) {

            TicketCategory ticketCategory = ticketCategoryRepo.getReferenceById(requestTicketDTO.getTicketCategory());
            Event event = eventRepo.getReferenceById(ticketCategory.getEvent().getEventId());

            for (int i = 0; i < requestTicketDTO.getQty(); i++){

                int random = new Random().nextInt(900000) + 100000;
                String ticketNumber = "TKT"+random;


                Ticket ticket = new Ticket(

                        ticketNumber,
                        ticketCategory.getTicketPrice(),
                        event.getEventDate(),
                        true,
                        customerRepo.getReferenceById(requestTicketDTO.getCustomer())


                );
                ticketList.add(ticket);
            }

        }

        if (!ticketList.isEmpty()){
            ticketRepo.saveAll(ticketList);
        }

        return "saved";
    }

    @Override
    public ResponseTicketDTO findByCategoryId(int ticketId) {
        if (ticketRepo.existsById(ticketId)) {
            Ticket ticket = ticketRepo.getReferenceById(ticketId);

            return new ResponseTicketDTO(
                    ticket.getTicketId(),
                    ticket.getTicketNumber(),
                    ticket.getTicketPrice(),
                    ticket.getTicketValidDate()
            );
        } else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public String updateTicket(RequestUpdateTicketDTO requestUpdateTicketDTO) {
        return null;
    }

    @Override
    public String deleteTicket(int categoryId) {
//        if (eventOrganizerRepo.existsById(organizerId)){
//            EventOrganizer eventOrganizer = eventOrganizerRepo.getReferenceById(organizerId);
//            eventOrganizerRepo.deleteById(eventOrganizer.getOrganizerId());
//
//            return eventOrganizer.getOrganizerId()+ " Deleted";
//        }
//        return "Organizer not found";
        return null;
    }

    @Override
    public List<ResponseTicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepo.findAll();

        List<ResponseTicketDTO> responseTicketDTOS = new ArrayList<>();

        for (Ticket ticket : tickets) {
            ResponseTicketDTO responseTicketDTO = new ResponseTicketDTO(
                    ticket.getTicketId(),
                    ticket.getTicketNumber(),
                    ticket.getTicketPrice(),
                    ticket.getTicketValidDate()
            );
            responseTicketDTOS.add(responseTicketDTO);
        }
        return responseTicketDTOS;
    }

    @Override
    public ResponseTicketPaginatedDTO getAllTicketPaginate(int page, int size) {
        Page<Ticket> tickets = ticketRepo.findAll(PageRequest.of(page, size));

        List<ResponseTicketDTO> responseTicketDTOS = new ArrayList<>();

        for (Ticket ticket : tickets) {
            ResponseTicketDTO responseTicketDTO = new ResponseTicketDTO(
                    ticket.getTicketId(),
                    ticket.getTicketNumber(),
                    ticket.getTicketPrice(),
                    ticket.getTicketValidDate()
            );
            responseTicketDTOS.add(responseTicketDTO);
        }

        return new ResponseTicketPaginatedDTO(
                responseTicketDTOS,
                ticketRepo.count()
        );
    }
}
