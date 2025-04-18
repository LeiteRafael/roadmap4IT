import express from 'express';
import {
    getAllCoursesService,
    getCourseByCodeService,
    upsertCourseService,
    updateCourseByCodeService,
    deleteCourseByCodeService,
    getDisciplinesByCourseCodeService
} from '../../app/services/CourseService.js';

const router = express.Router();


router.get('/', async (req, res) => {
    try {
        const courses = await getAllCoursesService();
        res.json(courses);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

router.get('/:code', async (req, res) => {
    try {
        const course = await getCourseByCodeService(req.params.code);

        if (!course) {
            return res.status(404).json({ error: 'Curso não encontrado' });
        }

        res.json(course);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

router.get('/:code/disciplines', async (req, res) => {
    try {
        const { code } = req.params;
        const disciplines = await getDisciplinesByCourseCodeService(code);

        if (!disciplines) {
            return res.status(404).json({ error: 'Curso não encontrado.' });
        }

        res.status(200).json({ disciplines });
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

router.post('/', async (req, res) => {
    try {
        const upsertedCourse = await upsertCourseService(req.body);
        res.status(200).json(upsertedCourse);
    } catch (err) {
        res.status(400).json({ error: err.message });
    }
});

router.put('/:code', async (req, res) => {
    try {
        const updatedCourse = await updateCourseByCodeService(req.params.code, req.body);

        if (!updatedCourse) {
            return res.status(404).json({ error: 'Curso não encontrado para atualização' });
        }

        res.json(updatedCourse);
    } catch (err) {
        res.status(400).json({ error: err.message });
    }
});

router.delete('/:code', async (req, res) => {
    try {
        const result = await deleteCourseByCodeService(req.params.code);

        if (!result) {
            return res.status(404).json({ error: 'Curso não encontrado para deletar' });
        }

        res.status(204).send();
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

export default router;
