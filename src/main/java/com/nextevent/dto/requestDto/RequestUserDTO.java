package com.nextevent.dto.requestDto;

import com.nextevent.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUserDTO {
    private int userId;
    private String email;
    private String password;
    private String role;
    private Set<Customer> customers;
}
