package com.nextevent.repository;

import com.nextevent.entity.Role;
import com.nextevent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);


    User findByRole(Role role);
}
