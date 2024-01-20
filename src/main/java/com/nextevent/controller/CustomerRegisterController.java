package com.nextevent.controller;

import com.nextevent.dto.requestDto.RequestCustomerRegisterDTO;
import com.nextevent.service.CustomerService;
import com.nextevent.service.UserRegisterService;
import com.nextevent.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/customer-register")
public class CustomerRegisterController {


    private final UserRegisterService userRegisterService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveCustomer(@RequestBody RequestCustomerRegisterDTO requestCustomerRegisterDTO) {

        String message = userRegisterService.saveCustomer(requestCustomerRegisterDTO);

        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.CREATED);
    }
}
