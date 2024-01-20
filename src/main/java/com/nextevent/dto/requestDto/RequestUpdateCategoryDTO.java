package com.nextevent.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUpdateCategoryDTO {

    private int categoryId;
    private String category;
    private int ticketPrice;
    private int ticketCount;

}
