package com.nextevent.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestTicketDTO {

    private int price;
    private int ticketCategory;
    private int customer;
    private int qty;
}
