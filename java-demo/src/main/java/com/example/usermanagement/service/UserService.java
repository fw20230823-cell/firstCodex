package com.example.usermanagement.service;

import com.example.usermanagement.dto.UserRequest;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(UserRequest request) {
        return repository.save(request.name(), request.email());
    }

    public User get(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User update(Long id, UserRequest request) {
        return repository.update(id, request.name(), request.email()).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void delete(Long id) {
        if (!repository.delete(id))
            throw new UserNotFoundException(id);
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(Long id) {
            super("User not found: " + id);
        }
    }
}
