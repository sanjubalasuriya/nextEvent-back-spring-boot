package com.nextevent.service.serviceImpl;

import com.nextevent.dto.requestDto.RequestUpdateCartDTO;
import com.nextevent.dto.requestDto.RequestCartDTO;
import com.nextevent.dto.responsetDto.ResponseCartDTO;
import com.nextevent.entity.*;
import com.nextevent.repository.CartRepo;
import com.nextevent.repository.CustomerRepo;
import com.nextevent.repository.TicketCategoryRepo;
import com.nextevent.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final CustomerRepo customerRepo;
    private final TicketCategoryRepo ticketCategoryRepo;

    @Override
    public String saveCart(RequestCartDTO requestCartDTO) {

        if (customerRepo.existsById(requestCartDTO.getCustomerId()) && ticketCategoryRepo.existsById(requestCartDTO.getTicketCategoryId())) {
            Customer customer = customerRepo.getReferenceById(requestCartDTO.getCustomerId());
            TicketCategory ticketCategory = ticketCategoryRepo.getReferenceById(requestCartDTO.getTicketCategoryId());

            Cart cart = new Cart(
                    requestCartDTO.getQty(),
                    ticketCategory,
                    customer
            );

            cartRepo.save(cart);
            return cart.getCartId() + " Saved";
        }
        else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public ResponseCartDTO findByCartId(int cartId) {
        if (cartRepo.existsById(cartId)){
            Cart cart = cartRepo.getReferenceById(cartId);

            ResponseCartDTO responseCartDTO = new ResponseCartDTO();

            responseCartDTO.setCartId(cart.getCartId());
            responseCartDTO.setQty(cart.getQty());
            responseCartDTO.setCustomer(cart.getCustomer().getCustomerId());
            responseCartDTO.setTicketCategory(cart.getTicketCategory().getTicketCategoryId());

            return responseCartDTO;
        }else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public String updateCart(RequestUpdateCartDTO requestUpdateCartDTO) {
        if (cartRepo.existsById(requestUpdateCartDTO.getCartId())) {
            Cart cart = cartRepo.getReferenceById(requestUpdateCartDTO.getCartId());

            cart.setQty(requestUpdateCartDTO.getQty());
            cart.setCustomer(customerRepo.getReferenceById(requestUpdateCartDTO.getCustomerId()));
            cart.setTicketCategory(ticketCategoryRepo.getReferenceById(requestUpdateCartDTO.getTicketCategoryId()));

            cartRepo.save(cart);
            return cart.getCartId() + " Updated";
        }
        else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public String deleteCart(int cartId) {
        if (cartRepo.existsById(cartId)){
            Cart cart = cartRepo.getReferenceById(cartId);
            cartRepo.deleteById(cart.getCartId());

            return cart.getCartId()+ " Deleted";
        }
        return "Cart not found";
    }

    @Override
    public List<ResponseCartDTO> getAllCart() {
        List<Cart> cartList = cartRepo.findAll();

        List<ResponseCartDTO> responseEventDTOS = new ArrayList<>();

        for(Cart cart : cartList){
            ResponseCartDTO responseCartDTO = new ResponseCartDTO(
                    cart.getCartId(),
                    cart.getQty(),
                    cart.getTicketCategory().getTicketCategoryId(),
                    cart.getCustomer().getCustomerId()
            );
            responseEventDTOS.add(responseCartDTO);
        }
        return responseEventDTOS;
    }

    @Override
    public List<ResponseCartDTO> findByCustomerId(int customerId) {


        List<Cart> cartList = cartRepo.findAllByCustomer_CustomerId(customerId);

        List<ResponseCartDTO> responseEventDTOS = new ArrayList<>();

        for(Cart cart : cartList){
            ResponseCartDTO responseCartDTO = new ResponseCartDTO(
                    cart.getCartId(),
                    cart.getQty(),
                    cart.getTicketCategory().getTicketCategoryId(),
                    cart.getCustomer().getCustomerId()
            );
            responseEventDTOS.add(responseCartDTO);
        }
        return responseEventDTOS;
    }
}
