package com.nextevent.controller;

import com.nextevent.dto.paginatedDto.ResponseEventPaginatedDTO;
import com.nextevent.dto.requestDto.RequestEventDTO;
import com.nextevent.dto.requestDto.RequestUpdateEventDTO;
import com.nextevent.dto.responsetDto.ResponseEventDTO;
import com.nextevent.service.EventService;
import com.nextevent.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/event")
@CrossOrigin
public class EventController {

    private final EventService eventService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveEvent(@RequestBody RequestEventDTO requestEventDTO) {

        String message = eventService.saveEvent(requestEventDTO);

        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/get-by-id",
            params = "eventId"
    )
    public ResponseEntity<StandardResponse> getEventById(int eventId) {

        ResponseEventDTO responseEventDTO = eventService.findByEventId(eventId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", responseEventDTO)
                , HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateEvent(@RequestBody RequestUpdateEventDTO requestUpdateEventDTO) {
        String message = eventService.updateEvent(requestUpdateEventDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse> deleteEvent(@PathVariable(value = "id") int eventId) {
        String message = eventService.deleteEvent(eventId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<StandardResponse> getAllEvent() {
        List<ResponseEventDTO> allEvent = eventService.getAllEvent();
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", allEvent)
                , HttpStatus.OK);
    }

    @GetMapping(
            path = "/paginate-all",
            params = {"page", "size"}
    )
    public ResponseEntity<StandardResponse> getAllEventPaginate(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        ResponseEventPaginatedDTO responseEventPaginatedDTO = eventService.getAllEventPaginate(page, size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", responseEventPaginatedDTO)
                , HttpStatus.OK);
    }
}
