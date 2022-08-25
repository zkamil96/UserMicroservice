package com.example.usermicroservice.Controller;

import com.example.usermicroservice.Model.Items;
import com.example.usermicroservice.Model.Users;
import com.example.usermicroservice.Service.ItemsService;
import com.example.usermicroservice.Service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final ItemsService itemsService;
    private final UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid  Users user){
        return usersService.loginUser(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid Users user){
        return usersService.registerUser(user);
    }

    @PostMapping("/items")
    public ResponseEntity<?> addItem(@RequestBody @Valid Items item){
        return itemsService.addItem(item);
    }

    @GetMapping("/items")
    public ResponseEntity<?> getItems(){
        return itemsService.getItems();
    }

}
