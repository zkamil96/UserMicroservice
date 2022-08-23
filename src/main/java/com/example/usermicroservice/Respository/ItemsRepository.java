package com.example.usermicroservice.Respository;

import com.example.usermicroservice.Model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemsRepository extends JpaRepository<Items, Long> {
    Optional<List<Items>> findByOwner( long id);
}
