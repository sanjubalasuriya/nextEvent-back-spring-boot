package com.nextevent.controller;

import com.nextevent.dto.paginatedDto.ResponseCustomerPaginatedDTO;
import com.nextevent.dto.requestDto.RequestUpdateCustomerDTO;
import com.nextevent.dto.responsetDto.ResponseCustomerDTO;
import com.nextevent.service.CustomerService;
import com.nextevent.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(
            path = "/get-by-id",
            params = "customerId"
    )
    public ResponseEntity<StandardResponse> getCustomerById(int customerId) {

        ResponseCustomerDTO message = customerService.findByCustomerId(customerId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody RequestUpdateCustomerDTO requestUpdateCustomerDTO) {
        String message = customerService.updateCustomer(requestUpdateCustomerDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse> updateCustomer(@PathVariable(value = "id") int customerId) {
        String message = customerService.deleteCustomer(customerId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<StandardResponse> getAllCustomers() {
        List<ResponseCustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", allCustomers)
                , HttpStatus.OK);
    }

    @GetMapping(
            path = "/paginate-all",
            params = {"page", "size"}
    )
    public ResponseEntity<StandardResponse> getAllCustomersPaginate(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        ResponseCustomerPaginatedDTO responseCustomerPaginatedDTO = customerService.getAllCustomersPaginate(page, size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", responseCustomerPaginatedDTO)
                , HttpStatus.OK);
    }
}
