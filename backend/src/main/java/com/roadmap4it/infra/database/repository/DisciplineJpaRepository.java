package com.roadmap4it.infra.database.repository;

import com.roadmap4it.adapters.rest.discipline.controllers.DisciplineMapper;
import com.roadmap4it.domain.entity.Discipline;
import com.roadmap4it.domain.repository.DisciplineRepository;
import com.roadmap4it.infra.database.model.DisciplineEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface DisciplineJpaRepository extends JpaRepository<DisciplineEntity, Long>, DisciplineRepository {

    default List<Discipline> findAllDisciplines() {
        return findAll().stream()
                .map(DisciplineMapper::toDomain)
                .collect(Collectors.toList());
    }

    default Optional<Discipline> findDisciplineById(Long id) {
        return findById(id).map(DisciplineMapper::toDomain);
    }


    default Discipline saveDiscipline(Discipline discipline) {
        return DisciplineMapper.toDomain(save(DisciplineMapper.toEntity(discipline)));
    }

    default void deleteDisciplineById(Long id) {
        deleteById(id);
    }

    default boolean existsByCode(String code) {
        System.out.println(code);
        return existsByCodeIgnoreCase(code);
    }

    boolean existsByCodeIgnoreCase(String code);
}
