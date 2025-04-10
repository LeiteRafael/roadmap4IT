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

    default Optional<Discipline> findByCode(String code) {
        return findAll().stream()
                .filter(disciplineEntity -> disciplineEntity.getCode().equalsIgnoreCase(code))
                .map(DisciplineMapper::toDomain)
                .findFirst();
    }

    default List<Discipline> findBySemester(int semester) {
        return findAll().stream()
                .filter(disciplineEntity -> disciplineEntity.getSemester() == semester)
                .map(DisciplineMapper::toDomain)
                .collect(Collectors.toList());
    }

    default List<Discipline> findByCategory(String category) {
        return findAll().stream()
                .filter(disciplineEntity -> disciplineEntity.getCategories().contains(category))
                .map(DisciplineMapper::toDomain)
                .collect(Collectors.toList());
    }

    default Discipline saveDiscipline(Discipline discipline) {
        return DisciplineMapper.toDomain(save(DisciplineMapper.toEntity(discipline)));
    }

    default void deleteDisciplineByCode(String code) {
        deleteByCodeIgnoreCase(code);
    }

    default boolean existsByCode(String code) {
        return existsByCodeIgnoreCase(code);
    }

    default Discipline updateDiscipline(Discipline discipline) {
        DisciplineEntity entity = DisciplineMapper.toEntity(discipline);
        return DisciplineMapper.toDomain(save(entity));
    }



    void deleteByCodeIgnoreCase(String code);

    boolean existsByCodeIgnoreCase(String code);
}
