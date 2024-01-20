package com.nextevent.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestTicketOrganizerRegisterDTO {

    private int userId;
    private String email;
    private String password;
    private String role;
    private String mobile;
    private String address;
}
