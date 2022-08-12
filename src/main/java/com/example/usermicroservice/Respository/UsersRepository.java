package com.example.usermicroservice.Respository;

import com.example.usermicroservice.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
