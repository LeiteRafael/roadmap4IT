package com.roadmap4it.domain.repository;

import com.roadmap4it.domain.entity.Discipline;
import java.util.List;
import java.util.Optional;

public interface DisciplineRepository {

    List<Discipline> findAllDisciplines();

    Optional<Discipline> findByCode(String code);

    List<Discipline> findBySemester(int semester);

    List<Discipline> findByCategory(String category);

    Discipline saveDiscipline(Discipline discipline);

    void deleteDisciplineByCode(String code);

    boolean existsByCode(String code);

    Discipline updateDiscipline(Discipline discipline);

}