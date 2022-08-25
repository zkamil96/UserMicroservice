package com.example.usermicroservice.Service;

import com.example.usermicroservice.Model.Items;
import com.example.usermicroservice.Model.Users;
import com.example.usermicroservice.Respository.ItemsRepository;
import com.example.usermicroservice.Respository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class ItemsService {

    private ItemsRepository itemsRepository;
    private UsersRepository usersRepository;
    public ResponseEntity<?> addItem(Items item) {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = usersRepository.findByLogin(user.getLogin())
                .orElseThrow(() -> new UsernameNotFoundException("User " + user.getLogin() + "not found"));
        log.info("Current logged user: " + currentUser.getId());
        item.setOwner(currentUser.getId());
        itemsRepository.save(item);
        return ResponseEntity.ok(item);
    }

    public ResponseEntity<?> getItems() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = usersRepository.findByLogin(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User " + user.getUsername() + "not found"));
        List<Items> items = itemsRepository.findByOwner(currentUser.getId()).orElseThrow(() -> new UsernameNotFoundException("User " + user.getUsername() + "not found"));
        return ResponseEntity.ok(items);
    }
}
