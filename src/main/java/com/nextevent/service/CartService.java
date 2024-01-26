package com.nextevent.service;

import com.nextevent.dto.requestDto.RequestUpdateCartDTO;
import com.nextevent.dto.requestDto.RequestCartDTO;
import com.nextevent.dto.responsetDto.ResponseCartDTO;

import java.util.List;

public interface CartService {
    String saveCart(RequestCartDTO requestCartDTO);

    ResponseCartDTO findByCartId(int cartId);

    String updateCart(RequestUpdateCartDTO requestUpdateCartDTO);

    String deleteCart(int cartId);

    List<ResponseCartDTO> getAllCart();
}
