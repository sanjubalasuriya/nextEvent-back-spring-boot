package com.nextevent.dto.responsetDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTicketDTO {

    private int ticketId;
    private String ticketNumber;
    private int ticketPrice;
    private Date ticketValidDate;

}
