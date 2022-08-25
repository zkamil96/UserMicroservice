package com.example.usermicroservice;

import com.example.usermicroservice.Service.ItemsService;
import com.example.usermicroservice.Service.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class UserMicroserviceApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    private UsersService usersService;
    @MockBean
    private ItemsService itemsService;

    @Test
    public void loginUserTest() throws Exception {
        String body = "{\"login\":\"login12345\",\"password\":\"password12345\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void registerUserTest() throws Exception {
        String body = "{\"login\":\"login12345\",\"password\":\"password12345\"}";

         mockMvc.perform(MockMvcRequestBuilders
                .post("/register")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "login12345", password = "password12345")
    public void addItemTest() throws Exception {
        String body = "{\"name\":\"first\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/items")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "login12345", password = "password12345")
    public void getItemsTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/items"))
                .andExpect(status().isOk());
    }
}
