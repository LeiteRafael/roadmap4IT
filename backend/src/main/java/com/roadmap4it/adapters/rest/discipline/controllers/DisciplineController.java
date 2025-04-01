package com.roadmap4it.adapters.rest.discipline.controllers;

import com.roadmap4it.app.services.DisciplineService;
import com.roadmap4it.domain.entity.Discipline;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService disciplineService;

    @GetMapping
    public List<Discipline> listDisciplines(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) Integer semester,
            @RequestParam(required = false) String categories,
            @RequestParam(required = false) String prerequisites,
            @RequestParam(required = false) String unlocks) {
        return disciplineService.listDisciplines(code, semester, categories, prerequisites, unlocks);
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
    public void deleteDisciplineByCode(@PathVariable String code) {
        disciplineService.deleteDisciplineByCode(code);
    }
}
