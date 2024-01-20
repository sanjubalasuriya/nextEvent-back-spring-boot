package com.nextevent.service;

import com.nextevent.dto.paginatedDto.ResponseCategoryPaginatedDTO;
import com.nextevent.dto.requestDto.RequestTicketCategoryDTO;
import com.nextevent.dto.requestDto.RequestUpdateCategoryDTO;
import com.nextevent.dto.requestDto.RequestUpdateCustomerDTO;
import com.nextevent.dto.responsetDto.ResponseTicketCategoryDTO;

import java.util.List;

public interface TicketCategoryService {
    String saveCategory(RequestTicketCategoryDTO requestTicketCategoryDTO);

    ResponseTicketCategoryDTO findByCategoryId(int categoryId);

    String updateCategory(RequestUpdateCategoryDTO requestUpdateCategoryDTO);

    String deleteCategory(int categoryId);

    List<ResponseTicketCategoryDTO> getAllCategory();

    ResponseCategoryPaginatedDTO getAllCategoryPaginate(int page, int size);

    List<ResponseTicketCategoryDTO> findCategoryByEventId(int eventId);
}
