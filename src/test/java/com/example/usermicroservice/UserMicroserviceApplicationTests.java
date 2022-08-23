package com.example.usermicroservice;

import com.example.usermicroservice.Model.Items;
import com.example.usermicroservice.Model.Users;
import com.example.usermicroservice.Respository.ItemsRepository;
import com.example.usermicroservice.Respository.UsersRepository;
import com.example.usermicroservice.Service.ItemsService;
import com.example.usermicroservice.Service.UsersService;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.AssertionErrors;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserMicroserviceApplicationTests {

    @MockBean
    private UsersRepository usersRepository;
    @MockBean
    private ItemsRepository itemsRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ItemsService itemsService;

    Users user = new Users(1L, "login123456", "password12345", new ArrayList<Items>());
    Items item = new Items(1L, 1L, "first");

/*    public void addUserTest(){
        when(usersRepository.save(user)).thenReturn(user);
        assertEquals(user, usersService.registerUser(user));

    }

    public void addItemTest(){
        when(itemsRepository.save(item)).thenReturn(item);
        assertEquals(item, itemsService.addItem(item));
    }*/








}
