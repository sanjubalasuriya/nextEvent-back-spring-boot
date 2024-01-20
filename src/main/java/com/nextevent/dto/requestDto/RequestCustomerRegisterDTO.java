package com.nextevent.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestCustomerRegisterDTO {

    private String email;
    private String password;
    private String role;
    private String gender;
    private String nicOrPassportPrefix;
    private String nicOrPassport;
    private String mobile;
}
