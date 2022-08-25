package com.example.usermicroservice.Respository;

import com.example.usermicroservice.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
     Optional<Users> findByLogin(String login);
}
