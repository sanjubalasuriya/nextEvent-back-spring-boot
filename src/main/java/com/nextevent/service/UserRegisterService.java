package com.nextevent.service;

import com.nextevent.dto.requestDto.RequestCustomerRegisterDTO;
import com.nextevent.dto.requestDto.RequestTicketOrganizerRegisterDTO;

public interface UserRegisterService {
    String saveCustomer(RequestCustomerRegisterDTO requestCustomerRegisterDTO);

    String saveOrganizer(RequestTicketOrganizerRegisterDTO requestTicketOrganizerRegisterDTO);
}
