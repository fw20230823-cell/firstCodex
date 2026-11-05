package com.example.usermanagement.repository;

import com.example.usermanagement.model.User;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    public User save(String name, String email) {
        User user = new User(sequence.incrementAndGet(), name, email);
        users.put(user.id(), user);
        return user;
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public List<User> findAll() {
        return users.values().stream().sorted(Comparator.comparing(User::id)).toList();
    }

    public Optional<User> update(Long id, String name, String email) {
        return users.computeIfPresent(id, (key, old) -> new User(id, name, email)) == null ? Optional.empty()
                : findById(id);
    }

    public boolean delete(Long id) {
        return users.remove(id) != null;
    }
}
