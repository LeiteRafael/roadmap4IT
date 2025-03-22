package com.roadmap4it.adapters.rest.discipline.controllers;

import com.roadmap4it.domain.entity.Discipline;
import com.roadmap4it.infra.database.model.DisciplineEntity;

public class DisciplineMapper {

    public static DisciplineEntity toEntity(Discipline discipline) {
        return DisciplineEntity.builder()
                .code(discipline.getCode())
                .name(discipline.getName())
                .description(discipline.getDescription())
                .semester(discipline.getSemester())
                .prerequisites(discipline.getPrerequisites())
                .unlocks(discipline.getUnlocks())
                .build();
    }

    public static Discipline toDomain(DisciplineEntity entity) {
        return new Discipline(
                entity.getCode(),
                entity.getName(),
                entity.getDescription(),
                entity.getSemester(),
                entity.getPrerequisites(),
                entity.getUnlocks()
        );
    }
}
