import {
    createDisciplineService,
    getAllDisciplinesService,
    getDisciplineByCodeService,
    updateDisciplineByCodeService,
    deleteDisciplineByCodeService
} from '../../app/services/DisiciplineServices.js';

export const createDiscipline = async (req, res) => {
    try {
        const discipline = await createDisciplineService(req.body);
        res.status(201).json(discipline);
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

export const getAllDisciplines = async (req, res) => {
    try {
        const disciplines = await getAllDisciplinesService();
        res.status(200).json(disciplines);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};

export const getDisciplineByCode = async (req, res) => {
    try {
        const discipline = await getDisciplineByCodeService(req.params.code);
        if (!discipline) {
            return res.status(404).json({ error: "Disciplina não encontrada" });
        }
        res.status(200).json(discipline);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};

export const updateDisciplineByCode = async (req, res) => {
    try {
        const updated = await updateDisciplineByCodeService(req.params.code, req.body);
        if (!updated) {
            return res.status(404).json({ error: "Disciplina não encontrada para atualizar" });
        }
        res.status(200).json(updated);
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
};

export const deleteDisciplineByCode = async (req, res) => {
    try {
        const deleted = await deleteDisciplineByCodeService(req.params.code);
        if (!deleted) {
            return res.status(404).json({ error: "Disciplina não encontrada para deletar" });
        }
        res.status(204).send();
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};
