package com.example.usermanagement.controller;

import com.example.usermanagement.dto.*;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<User> create(@Valid @RequestBody UserRequest request) {
        return ApiResponse.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<User> get(@PathVariable Long id) {
        return ApiResponse.ok(service.get(id));
    }

    @GetMapping
    public ApiResponse<List<User>> getAll() {
        return ApiResponse.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ApiResponse<User> update(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok(null);
    }

    @ExceptionHandler(UserService.UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Void> notFound(RuntimeException exception) {
        return new ApiResponse<>(false, exception.getMessage(), null);
    }
}
