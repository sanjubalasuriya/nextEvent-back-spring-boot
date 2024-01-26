package com.nextevent.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUpdateCartDTO {

    private int cartId;
    private int qty;
    private int ticketCategoryId;
    private int customerId;
}
