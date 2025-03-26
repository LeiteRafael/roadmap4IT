import React from 'react';
import disciplines from '../data/disciplines.json';

const DataConverter = (semesterRange = { start: 2, end: 3 }) => {
    // Agrupar as disciplinas por semestre
    const groupedBySemester = disciplines.reduce((acc, discipline) => {
        if (!acc[discipline.semester]) {
            acc[discipline.semester] = [];
        }
        acc[discipline.semester].push(discipline);
        return acc;
    }, {});

    // Filtrar as disciplinas dentro do intervalo de semestres
    const filteredDisciplines = Object.values(groupedBySemester).flat().filter(discipline => {
        return discipline.semester >= semesterRange.start && discipline.semester <= semesterRange.end;
    });

    // Criar nós e arestas
    const initialNodes = filteredDisciplines.map(discipline => ({
        id: discipline.code,
        data: {
            label: discipline.name,
            description: discipline.description
        },
        semester: discipline.semester // Adicionar semestre aos nós, se necessário
    }));

    const initialEdges = [];
    filteredDisciplines.forEach(discipline => {
        // Criar arestas para pré-requisitos
        discipline.prerequisites.forEach(prerequisite => {
            initialEdges.push({
                id: `${prerequisite}-${discipline.code}`,
                source: prerequisite,
                target: discipline.code,
                animated: true,
                style: { strokeDasharray: "5,5", stroke: "#0345fc" }
            });
        });

        // Criar arestas para disciplinas que são desbloqueadas
        discipline.unlocks.forEach(unlock => {
            initialEdges.push({
                id: `${discipline.code}-${unlock}`,
                source: discipline.code,
                target: unlock,
                animated: true,
                style: { strokeDasharray: "5,5", stroke: "#0345fc" }
            });
        });
    });

    return { initialNodes, initialEdges };
};

export default DataConverter;
