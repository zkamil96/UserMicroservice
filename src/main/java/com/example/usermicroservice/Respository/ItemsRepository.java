package com.example.usermicroservice.Respository;

import com.example.usermicroservice.Model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Long> {
}
