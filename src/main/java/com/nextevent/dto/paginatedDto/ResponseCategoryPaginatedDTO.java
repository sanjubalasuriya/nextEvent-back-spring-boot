package com.nextevent.dto.paginatedDto;

import com.nextevent.dto.responsetDto.ResponseCustomerDTO;
import com.nextevent.dto.responsetDto.ResponseTicketCategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCategoryPaginatedDTO {

    List<ResponseTicketCategoryDTO> list;
    private long dataCount;
}
