package com.nextevent.dto.paginatedDto;

import com.nextevent.dto.responsetDto.ResponseEventDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseEventPaginatedDTO {

    List<ResponseEventDTO> list;
    private long dataCount;
}
