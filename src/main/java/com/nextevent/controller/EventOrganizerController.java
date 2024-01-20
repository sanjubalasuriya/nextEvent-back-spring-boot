package com.nextevent.controller;

import com.nextevent.dto.paginatedDto.ResponseOrganizerPaginatedDTO;
import com.nextevent.dto.requestDto.RequestUpdateOrganizerDTO;
import com.nextevent.dto.responsetDto.ResponseOrganizerDTO;
import com.nextevent.service.EventOrganizerService;
import com.nextevent.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/organizer")
public class EventOrganizerController {

    private final EventOrganizerService eventOrganizerService;

    @GetMapping(
            path = "/get-by-id",
            params = "organizerId"
    )
    public ResponseEntity<StandardResponse> getOrganizerById(int organizerId) {

        ResponseOrganizerDTO message = eventOrganizerService.findByOrganizerId(organizerId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateOrganizer(@RequestBody RequestUpdateOrganizerDTO requestUpdateOrganizerDTO) {
        String message = eventOrganizerService.updateOrganizer(requestUpdateOrganizerDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse> deleteOrganizer(@PathVariable(value = "id") int organizerId) {
        String message = eventOrganizerService.deleteCustomer(organizerId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<StandardResponse> getAllOrganizers() {
        List<ResponseOrganizerDTO> allOrganizers = eventOrganizerService.getAllOrganizers();
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", allOrganizers)
                , HttpStatus.OK);
    }

    @GetMapping(
            path = "/paginate-all",
            params = {"page", "size"}
    )
    public ResponseEntity<StandardResponse> getAllOrganizersPaginate(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        ResponseOrganizerPaginatedDTO responseOrganizerPaginatedDTO = eventOrganizerService.getAllOrganizersPaginate(page, size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", responseOrganizerPaginatedDTO)
                , HttpStatus.OK);
    }
}
