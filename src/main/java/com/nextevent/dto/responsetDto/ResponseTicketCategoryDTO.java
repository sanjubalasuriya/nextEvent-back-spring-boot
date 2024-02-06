package com.nextevent.dto.responsetDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTicketCategoryDTO {

    private int categoryId;
    private String category;
    private int ticketPrice;
    private int ticketCount;
    private String eventName;

}
