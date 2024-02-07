package com.nextevent.repository;

import com.nextevent.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByCustomer_CustomerId(int customerId);
}
