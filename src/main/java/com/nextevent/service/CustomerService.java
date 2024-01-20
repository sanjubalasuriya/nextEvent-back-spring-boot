package com.nextevent.service;

import com.nextevent.dto.paginatedDto.ResponseCustomerPaginatedDTO;
import com.nextevent.dto.requestDto.RequestUpdateCustomerDTO;
import com.nextevent.dto.responsetDto.ResponseCustomerDTO;

import java.util.List;

public interface CustomerService {
    ResponseCustomerDTO findByCustomerId(int customerId);

    String updateCustomer(RequestUpdateCustomerDTO requestUpdateCustomerDTO);

    String deleteCustomer(int customerId);

    List<ResponseCustomerDTO> getAllCustomers();

    ResponseCustomerPaginatedDTO getAllCustomersPaginate(int page, int size);
}
