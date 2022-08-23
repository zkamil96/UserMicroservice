package com.example.usermicroservice.Service;

import com.example.usermicroservice.Model.Items;
import com.example.usermicroservice.Model.Users;
import com.example.usermicroservice.Respository.ItemsRepository;
import com.example.usermicroservice.Respository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
@AllArgsConstructor
@Slf4j
public class ItemsService {

    private ItemsRepository itemsRepository;
    private UsersRepository usersRepository;
    public void addItem(Items item) {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = usersRepository.findByLogin(user.getLogin())
                .orElseThrow(() -> new UsernameNotFoundException("User " + user.getLogin() + "not found"));
        log.info("Current logged user: " + currentUser.getId());
        item.setOwner(currentUser.getId());
        log.info("Actual item: " + item);
        itemsRepository.save(item);
    }

    public List<Items> getItems() {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = usersRepository.findByLogin(user.getLogin())
                .orElseThrow(() -> new UsernameNotFoundException("User " + user.getLogin() + "not found"));
        List<Items> items = itemsRepository.findByOwner(currentUser.getId()).orElseThrow(() -> new UsernameNotFoundException("User " + user.getLogin() + "not found"));
        return items;
    }
}
