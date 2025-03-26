package com.roadmap4it.app.services;

import com.roadmap4it.app.exceptions.BusinessException;
import com.roadmap4it.domain.entity.Discipline;
import com.roadmap4it.domain.repository.DisciplineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAllDisciplines();
    }

    public Optional<Discipline> getDisciplineByCode(String code) {
        return disciplineRepository.findByCode(code);
    }

    public List<Discipline> getDisciplinesBySemester(int semester) {
        return disciplineRepository.findBySemester(semester);
    }

    public List<Discipline> getDisciplinesByCategory(String category) {
        return disciplineRepository.findByCategory(category);
    }

    public Discipline createDiscipline(Discipline discipline) {
        boolean exists = disciplineRepository.existsByCode(discipline.getCode());

        if (exists) {
            throw new BusinessException("Já existe uma disciplina com o código '" + discipline.getCode() + "'.");
        }

        return disciplineRepository.saveDiscipline(discipline);
    }

    public Discipline updateDiscipline(String code, Discipline updatedDiscipline) {
        Discipline existingDiscipline = disciplineRepository.findByCode(code)
                .orElseThrow(() -> new BusinessException("Disciplina com código " + code + " não encontrada."));

        existingDiscipline.setName(updatedDiscipline.getName());
        existingDiscipline.setPrerequisites(updatedDiscipline.getPrerequisites());
        existingDiscipline.setSemester(updatedDiscipline.getSemester());
        existingDiscipline.setCategories(new ArrayList<>(updatedDiscipline.getCategories()));
        existingDiscipline.setUnlocks(new ArrayList<>(updatedDiscipline.getUnlocks())); 

        return disciplineRepository.updateDiscipline(existingDiscipline);
    }

    @Transactional
    public void deleteDisciplineByCode(String code) {
        disciplineRepository.findByCode(code)
                .orElseThrow(() -> new BusinessException("Disciplina com código " + code + " não encontrada."));

        disciplineRepository.deleteDisciplineByCode(code);
    }
}
