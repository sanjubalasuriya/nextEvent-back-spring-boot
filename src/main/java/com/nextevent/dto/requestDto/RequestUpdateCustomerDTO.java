package com.nextevent.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUpdateCustomerDTO {

    private int customerId;
    private String gender;
    private String nicOrPassportPrefix;
    private String nicOrPassport;
    private String mobile;
}
