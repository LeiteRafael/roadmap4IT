package com.roadmap4it.adapters.rest.discipline.controllers;


import com.roadmap4it.app.services.DisciplineService;
import com.roadmap4it.domain.entity.Discipline;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService disciplineService;

    @GetMapping
    public List<Discipline> getAllDisciplines() {
        return disciplineService.getAllDisciplines();
    }

    // @GetMapping("/{id}")
    // public Optional<Discipline> getDisciplineById(@PathVariable Long id) {
    //     return disciplineService.getDisciplineById(id);
    // }

    @PostMapping
    public Discipline createDiscipline(@RequestBody Discipline Discipline) {
        return disciplineService.createDiscipline(Discipline);
    }

    @DeleteMapping("/{id}")
    public void deleteDiscipline(@PathVariable Long id) {
        disciplineService.deleteDiscipline(id);
    }
}
