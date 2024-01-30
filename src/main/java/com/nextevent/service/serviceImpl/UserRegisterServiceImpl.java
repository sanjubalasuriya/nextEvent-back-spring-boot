package com.nextevent.service.serviceImpl;

import com.nextevent.dto.requestDto.RequestCustomerRegisterDTO;
import com.nextevent.dto.requestDto.RequestTicketOrganizerRegisterDTO;
import com.nextevent.entity.Customer;
import com.nextevent.entity.EventOrganizer;
import com.nextevent.entity.Role;
import com.nextevent.entity.User;
import com.nextevent.repository.CustomerRepo;
import com.nextevent.repository.EventOrganizerRepo;
import com.nextevent.repository.UserRepo;
import com.nextevent.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class UserRegisterServiceImpl implements UserRegisterService {

    private final UserRepo userRepo;
    private final CustomerRepo customerRepo;
    private final EventOrganizerRepo eventOrganizerRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public String saveCustomer(RequestCustomerRegisterDTO requestCustomerRegisterDTO) {

        User user = new User();

        user.setEmail(requestCustomerRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(requestCustomerRegisterDTO.getPassword()));
        user.setRole(Role.USER);

        userRepo.save(user);

        if(userRepo.existsById(user.getUserId())){
            Customer customer = new Customer(
                    requestCustomerRegisterDTO.getGender(),
                    requestCustomerRegisterDTO.getNicOrPassportPrefix(),
                    requestCustomerRegisterDTO.getNicOrPassport(),
                    requestCustomerRegisterDTO.getMobile(),
                    user
            );

            customerRepo.save(customer);
        }

        return "User Registered";
    }

    @Override
    public String saveOrganizer(RequestTicketOrganizerRegisterDTO requestTicketOrganizerRegisterDTO) {
        User user = new User();
        user.setEmail(requestTicketOrganizerRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(requestTicketOrganizerRegisterDTO.getPassword()));
        user.setRole(Role.ADMIN);

        userRepo.save(user);

        if(userRepo.existsById(user.getUserId())){
            EventOrganizer eventOrganizer = new EventOrganizer(
                    requestTicketOrganizerRegisterDTO.getMobile(),
                    requestTicketOrganizerRegisterDTO.getAddress(),
                    user
            );

            eventOrganizerRepo.save(eventOrganizer);
        }

        return "User Registered";
    }

}
