package com.roadmap4it.app.services;

import com.roadmap4it.app.exceptions.BusinessException;
import com.roadmap4it.domain.entity.Discipline;
import com.roadmap4it.domain.repository.DisciplineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAllDisciplines();
    }

    // public Optional<Discipline> getDisciplineById(Long id) {
    // return disciplineRepository.findById(id);
    // }

    public Discipline createDiscipline(Discipline discipline) {
        // Verificar se a disciplina já existe (com base no código, por exemplo)
        boolean exists = disciplineRepository.existsByCode(discipline.getCode());

        if (exists) {
            throw new BusinessException("Já existe uma disciplina com o código '" + discipline.getCode() + "'.");
        }
        
        // Salvar a nova disciplina
        return disciplineRepository.saveDiscipline(discipline);
    }

    // Deletar uma disciplina
    public void deleteDiscipline(Long id) {
        disciplineRepository.deleteDisciplineById(id);
    }
}
