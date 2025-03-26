package com.roadmap4it.domain.repository;

import com.roadmap4it.domain.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAllUsers();

    Optional<User> findUserById(Long id);

    User saveUser(User user);

    void deleteUserById(Long id);

    boolean existsById(Long id);

    boolean existsByName(String name);
}
