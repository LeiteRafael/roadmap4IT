import { Discipline } from '../../infra/database/model/DisciplineModel.js';

export const createDisciplineService = async (disciplineData) => {
    const discipline = await Discipline.create(disciplineData);
    return discipline;
};

export const getAllDisciplinesService = async () => {
    return await Discipline.find();
};

export const getDisciplineByCodeService = async (code) => {
    return await Discipline.findOne({ code });
};

export const updateDisciplineByCodeService = async (code, updateData) => {
    return await Discipline.findOneAndUpdate({ code }, updateData, { new: true });
};

export const deleteDisciplineByCodeService = async (code) => {
    return await Discipline.findOneAndDelete({ code });
};
