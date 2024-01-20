package com.nextevent.dto.paginatedDto;

import com.nextevent.dto.responsetDto.ResponseTicketDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTicketPaginatedDTO {

    List<ResponseTicketDTO> list;
    private long dataCount;
}
