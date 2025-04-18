import express from 'express';
import courseController from './CourseController.js';

const router = express.Router();

router.use('/', courseController);

export default router;
