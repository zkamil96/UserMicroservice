package com.example.usermicroservice.Controller;

import com.example.usermicroservice.Model.Items;
import com.example.usermicroservice.Model.Users;
import com.example.usermicroservice.Service.ItemsService;
import com.example.usermicroservice.Service.UsersService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.util.List;

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
    public void registerUser(@Valid @RequestBody Users user){
        usersService.registerUser(user);
    }

    @PostMapping("/items")
    public void addItem(@RequestBody @Valid Items item){
        itemsService.addItem(item);
    }

    @GetMapping("/items")
    public List<Items> getItems(){
        return itemsService.getItems();
    }

}
