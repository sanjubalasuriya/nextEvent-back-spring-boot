package com.nextevent.service;

import com.nextevent.dto.paginatedDto.ResponseOrganizerPaginatedDTO;
import com.nextevent.dto.requestDto.RequestUpdateOrganizerDTO;
import com.nextevent.dto.responsetDto.ResponseOrganizerDTO;

import java.util.List;

public interface EventOrganizerService {
    ResponseOrganizerDTO findByOrganizerId(int organizerId);

    String updateOrganizer(RequestUpdateOrganizerDTO requestUpdateOrganizerDTO);

    String deleteCustomer(int organizerId);

    List<ResponseOrganizerDTO> getAllOrganizers();

    ResponseOrganizerPaginatedDTO getAllOrganizersPaginate(int page, int size);
}
