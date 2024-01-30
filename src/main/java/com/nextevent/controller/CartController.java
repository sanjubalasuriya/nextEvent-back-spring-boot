package com.nextevent.controller;

import com.nextevent.dto.requestDto.RequestUpdateCartDTO;
import com.nextevent.dto.requestDto.RequestCartDTO;
import com.nextevent.dto.responsetDto.ResponseCartDTO;
import com.nextevent.service.CartService;
import com.nextevent.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/cart")
@CrossOrigin
public class CartController {

    private final CartService cartService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveCart(@RequestBody RequestCartDTO requestCartDTO) {

        String message = cartService.saveCart(requestCartDTO);

        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/get-by-id",
            params = "cartId"
    )
    public ResponseEntity<StandardResponse> getCartById(int cartId) {

        ResponseCartDTO message = cartService.findByCartId(cartId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateCart(@RequestBody RequestUpdateCartDTO requestUpdateCartDTO) {
        String message = cartService.updateCart(requestUpdateCartDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse> deleteCart(@PathVariable(value = "id") int cartId) {
        String message = cartService.deleteCart(cartId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<StandardResponse> getAllCarts() {
        List<ResponseCartDTO> allCarts = cartService.getAllCart();
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", allCarts)
                , HttpStatus.OK);
    }


}
