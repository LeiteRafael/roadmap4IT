package com.roadmap4it.domain.repository;

import com.roadmap4it.domain.entity.Discipline;
import java.util.List;
import java.util.Optional;

public interface DisciplineRepository {

    Optional<Discipline> findByCode(String code);

    List<Discipline> findBySemester(int semester);

    List<Discipline> findAllDisciplines();

    Discipline saveDiscipline(Discipline discipline);

    // void delete(Discipline discipline);

    void deleteDisciplineById(Long id);

    boolean existsByCode(String code);
}
