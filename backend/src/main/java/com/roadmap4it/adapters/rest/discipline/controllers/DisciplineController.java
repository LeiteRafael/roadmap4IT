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

    @GetMapping("/{code}")
    public Optional<Discipline> getDisciplineByCode(@PathVariable String code) {
        return disciplineService.getDisciplineByCode(code);
    }

    @PostMapping
    public Discipline createDiscipline(@RequestBody Discipline Discipline) {
        return disciplineService.createDiscipline(Discipline);
    }

    @PutMapping("/{code}")
    public Discipline updateDiscipline(@PathVariable String code, @RequestBody Discipline discipline) {
        return disciplineService.updateDiscipline(code, discipline);
    }

    @DeleteMapping("/{code}")
    public void deleteDiscipline(@PathVariable String code) {
        disciplineService.deleteDiscipline(code);
    }
}
