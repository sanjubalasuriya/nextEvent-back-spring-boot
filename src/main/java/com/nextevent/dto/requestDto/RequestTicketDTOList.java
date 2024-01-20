package com.nextevent.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestTicketDTOList {

    List<RequestTicketDTO> requestTicketDTOList;
}
