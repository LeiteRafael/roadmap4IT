import React, { useState, useEffect } from 'react';
import disciplines from '../data/disciplines.json';

const DisciplineList = () => {
  const [semester, setSemester] = useState(1);
  const [filteredDisciplines, setFilteredDisciplines] = useState([]);

  useEffect(() => {
    setFilteredDisciplines(filterBySemester(disciplines, semester));
  }, [semester]);

  return (
    <div>
      <select onChange={(e) => setSemester(Number(e.target.value))}>
        <option value={1}>Semestre 1</option>
        <option value={2}>Semestre 2</option>
      </select>
      <ul>
        {filteredDisciplines.map(discipline => (
          <li key={discipline.code}>{discipline.name}</li>
        ))}
      </ul>
    </div>
  );
};

const filterBySemester = (data, semester) => {
    return data.filter(item => item.semester === semester);
  };

export default DisciplineList;