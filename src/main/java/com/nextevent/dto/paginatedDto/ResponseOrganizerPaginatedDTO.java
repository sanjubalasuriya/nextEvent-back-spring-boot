package com.nextevent.dto.paginatedDto;

import com.nextevent.dto.responsetDto.ResponseOrganizerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseOrganizerPaginatedDTO {

    List<ResponseOrganizerDTO> list;
    private long dataCount;
}
