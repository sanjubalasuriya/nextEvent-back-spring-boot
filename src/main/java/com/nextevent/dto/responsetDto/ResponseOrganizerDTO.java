package com.nextevent.dto.responsetDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseOrganizerDTO {

    private int organizerId;
    private String mobile;
    private String address;

}
