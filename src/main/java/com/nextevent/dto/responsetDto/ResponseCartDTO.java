package com.nextevent.dto.responsetDto;

import com.nextevent.entity.Customer;
import com.nextevent.entity.TicketCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCartDTO {

    private int cartId;
    private int qty;
    private TicketCategory ticketCategory;
    private Customer customer;
}
