package com.nextevent.controller;

import com.nextevent.dto.requestDto.RequestCustomerRegisterDTO;
import com.nextevent.dto.requestDto.RequestTicketOrganizerRegisterDTO;
import com.nextevent.service.UserRegisterService;
import com.nextevent.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/organizer-register")
public class EventOrganizerRegisterController {

    private final UserRegisterService userRegisterService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveOrganizer(@RequestBody RequestTicketOrganizerRegisterDTO requestTicketOrganizerRegisterDTO) {

        String message = userRegisterService.saveOrganizer(requestTicketOrganizerRegisterDTO);

        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.CREATED);
    }


}
