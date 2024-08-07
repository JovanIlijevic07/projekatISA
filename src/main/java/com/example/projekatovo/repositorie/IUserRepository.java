package com.example.projekatovo.repositorie;

import com.example.projekatovo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
     Optional<User> findByEmail(String email);
    //Optional<User> findByUsername(String username);
}
