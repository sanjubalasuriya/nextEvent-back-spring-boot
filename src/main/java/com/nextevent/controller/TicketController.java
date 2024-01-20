package com.nextevent.controller;

import com.nextevent.dto.paginatedDto.ResponseTicketPaginatedDTO;
import com.nextevent.dto.requestDto.RequestTicketDTO;
import com.nextevent.dto.requestDto.RequestTicketDTOList;
import com.nextevent.dto.requestDto.RequestUpdateTicketDTO;
import com.nextevent.dto.responsetDto.ResponseTicketDTO;
import com.nextevent.service.TicketService;
import com.nextevent.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveTicket(@RequestBody RequestTicketDTOList requestTicketDTOList) {

        String message = ticketService.saveTicket(requestTicketDTOList);

        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/get-by-id",
            params = "ticketId"
    )
    public ResponseEntity<StandardResponse> getTicketById(int ticketId) {

        ResponseTicketDTO responseTicketDTO = ticketService.findByCategoryId(ticketId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", responseTicketDTO)
                , HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateTicket(@RequestBody RequestUpdateTicketDTO requestUpdateTicketDTO) {
        String message = ticketService.updateTicket(requestUpdateTicketDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse> deleteTicket(@PathVariable(value = "id") int categoryId) {
        String message = ticketService.deleteTicket(categoryId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<StandardResponse> getTickets() {
        List<ResponseTicketDTO> allTickets = ticketService.getAllTickets();
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", allTickets)
                , HttpStatus.OK);
    }

    @GetMapping(
            path = "/paginate-all",
            params = {"page", "size"}
    )
    public ResponseEntity<StandardResponse> getAllTicketPaginate(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        ResponseTicketPaginatedDTO responseTicketPaginatedDTO = ticketService.getAllTicketPaginate(page, size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", responseTicketPaginatedDTO)
                , HttpStatus.OK);
    }
}
