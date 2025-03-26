package com.roadmap4it.adapters.rest.user.controllers;

import com.roadmap4it.domain.entity.User;
import com.roadmap4it.infra.database.model.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .name(user.getName())
                .courseId(user.getCourseId())
                .completedDisciplines(user.getCompletedDisciplines())
                .build();
    }

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getName(),
                entity.getCourseId(),
                entity.getCompletedDisciplines());
    }
}
