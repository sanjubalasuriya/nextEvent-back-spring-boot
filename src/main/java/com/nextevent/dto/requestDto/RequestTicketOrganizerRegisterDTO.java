package com.nextevent.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestTicketOrganizerRegisterDTO {


    private String email;
    private String password;
    private String role;
    private String mobile;
    private String address;
}
