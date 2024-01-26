package com.nextevent.repository;

import com.nextevent.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Integer> {
}
