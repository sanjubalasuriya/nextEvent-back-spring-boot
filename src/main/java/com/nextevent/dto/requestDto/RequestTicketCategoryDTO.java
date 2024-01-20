package com.nextevent.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestTicketCategoryDTO {

    private String category;
    private int ticketPrice;
    private int ticketCount;
    private int event;
}
