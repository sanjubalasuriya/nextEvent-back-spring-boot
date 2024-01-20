package com.nextevent.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUpdateOrganizerDTO {

    private int organizerId;
    private String mobile;
    private String address;
}
