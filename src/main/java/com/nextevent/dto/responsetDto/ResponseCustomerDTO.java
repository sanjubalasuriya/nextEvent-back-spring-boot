package com.nextevent.dto.responsetDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCustomerDTO {

    private int customerId;
    private String gender;
    private String nicOrPassportPrefix;
    private String nicOrPassport;
    private String mobile;
}
