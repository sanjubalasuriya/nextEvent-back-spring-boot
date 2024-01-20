package com.nextevent.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestTicketDTO {

    private int ticketPrice;
    private int ticketCategoryId;
    private int customerId;
    private int ticketCount;
}
