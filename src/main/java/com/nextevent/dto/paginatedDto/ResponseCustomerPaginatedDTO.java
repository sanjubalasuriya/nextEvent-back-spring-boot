package com.nextevent.dto.paginatedDto;

import com.nextevent.dto.responsetDto.ResponseCustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCustomerPaginatedDTO {

    List<ResponseCustomerDTO> list;
    private long dataCount;
}
