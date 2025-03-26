package com.roadmap4it.infra.database.repository;

import com.roadmap4it.adapters.rest.user.controllers.UserMapper;
import com.roadmap4it.domain.entity.User;
import com.roadmap4it.domain.repository.UserRepository;
import com.roadmap4it.infra.database.model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long>, UserRepository {

    @Override
    default List<User> findAllUsers() {
        return findAll().stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    default Optional<User> findUserById(Long id) {
        return findById(id).map(UserMapper::toDomain);
    }

    @Override
    default User saveUser(User user) {
        return UserMapper.toDomain(save(UserMapper.toEntity(user)));
    }

    @Override
    default void deleteUserById(Long id) {
        deleteById(id);
    }

    boolean existsById(Long id);

    boolean existsByName(String name);
}
