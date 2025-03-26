package com.roadmap4it.app.services;

import com.roadmap4it.app.exceptions.BusinessException;
import com.roadmap4it.domain.entity.User;
import com.roadmap4it.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public User createUser(User user) {
        boolean exists = userRepository.existsByName(user.getName());

        if (exists) {
            throw new BusinessException("Já existe um usuário com o nome '" + user.getName() + "'.");
        }
        return userRepository.saveUser(user);
    }

    public User updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new BusinessException("Usuário com ID " + id + " não encontrado.");
        }
        user.setId(id);
        return userRepository.saveUser(user);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new BusinessException("Usuário com ID " + id + " não encontrado.");
        }
        userRepository.deleteUserById(id);
    }
}
