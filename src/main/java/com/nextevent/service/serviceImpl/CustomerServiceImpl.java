package com.nextevent.service.serviceImpl;

import com.nextevent.dto.paginatedDto.ResponseCustomerPaginatedDTO;
import com.nextevent.dto.requestDto.RequestUpdateCustomerDTO;
import com.nextevent.dto.responsetDto.ResponseCustomerDTO;
import com.nextevent.entity.Customer;
import com.nextevent.repository.CustomerRepo;
import com.nextevent.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    @Override
    public ResponseCustomerDTO findByCustomerId(int customerId) {
        if (customerRepo.existsById(customerId)){
            Customer customer = customerRepo.getReferenceById(customerId);

            return new ResponseCustomerDTO(
                    customer.getCustomerId(),
                    customer.getGender(),
                    customer.getNicOrPassportPrefix(),
                    customer.getNicOrPassport(),
                    customer.getMobile()
            );
        }else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public String updateCustomer(RequestUpdateCustomerDTO requestUpdateCustomerDTO) {
        if (customerRepo.existsById(requestUpdateCustomerDTO.getCustomerId())){

            Customer customer = customerRepo.getReferenceById(requestUpdateCustomerDTO.getCustomerId());
            customer.setGender(requestUpdateCustomerDTO.getGender());
            customer.setMobile(requestUpdateCustomerDTO.getMobile());
            customer.setNicOrPassportPrefix(requestUpdateCustomerDTO.getNicOrPassportPrefix());
            customer.setNicOrPassport(requestUpdateCustomerDTO.getNicOrPassport());


            customerRepo.save(customer);
            return customer.getCustomerId()+ " updated";
        }else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public String deleteCustomer(int customerId) {
        if (customerRepo.existsById(customerId)){
            Customer customer = customerRepo.getReferenceById(customerId);
            customerRepo.deleteById(customer.getCustomerId());

            return customer.getCustomerId()+ " Deleted";
        }
        return "Customer not found";
    }

    @Override
    public List<ResponseCustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();

        List<ResponseCustomerDTO> responseCustomerDTOS = new ArrayList<>();

        for(Customer customer : customers){
            ResponseCustomerDTO responseCustomerDTO = new ResponseCustomerDTO(
                    customer.getCustomerId(),
                    customer.getGender(),
                    customer.getNicOrPassport(),
                    customer.getNicOrPassport(),
                    customer.getMobile()
            );
            responseCustomerDTOS.add(responseCustomerDTO);
        }
        return responseCustomerDTOS;
    }

    @Override
    public ResponseCustomerPaginatedDTO getAllCustomersPaginate(int page, int size) {
        Page<Customer> customers = customerRepo.findAll(PageRequest.of(page, size));

        List<ResponseCustomerDTO> responseCustomerDTOS = new ArrayList<>();



        for(Customer customer : customers){
            ResponseCustomerDTO responseCustomerDTO = new ResponseCustomerDTO(
                    customer.getCustomerId(),
                    customer.getGender(),
                    customer.getNicOrPassport(),
                    customer.getNicOrPassport(),
                    customer.getMobile()
            );
            responseCustomerDTOS.add(responseCustomerDTO);
        }

        return new ResponseCustomerPaginatedDTO(
                responseCustomerDTOS,
                customerRepo.count()
        );
    }
}
