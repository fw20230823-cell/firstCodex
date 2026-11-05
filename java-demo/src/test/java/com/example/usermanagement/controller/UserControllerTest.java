package com.example.usermanagement.controller;

import com.example.usermanagement.dto.UserRequest;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
    @MockitoBean
    UserService service;
    User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Ada", "ada@example.com");
    }

    @Test
    void createsUser() throws Exception {
        when(service.create(any(UserRequest.class))).thenReturn(user);
        mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new UserRequest("Ada", "ada@example.com"))))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    void getsUserById() throws Exception {
        when(service.get(1L)).thenReturn(user);
        mvc.perform(get("/api/users/1")).andExpect(status().isOk()).andExpect(jsonPath("$.data.name").value("Ada"));
    }

    @Test
    void getsAllUsers() throws Exception {
        when(service.getAll()).thenReturn(List.of(user));
        mvc.perform(get("/api/users")).andExpect(status().isOk()).andExpect(jsonPath("$.data.length()").value(1));
    }

    @Test
    void updatesUser() throws Exception {
        when(service.update(eq(1L), any(UserRequest.class))).thenReturn(user);
        mvc.perform(put("/api/users/1").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new UserRequest("Ada", "ada@example.com"))))
                .andExpect(status().isOk()).andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    void deletesUser() throws Exception {
        doNothing().when(service).delete(1L);
        mvc.perform(delete("/api/users/1")).andExpect(status().isOk()).andExpect(jsonPath("$.success").value(true));
    }
}
