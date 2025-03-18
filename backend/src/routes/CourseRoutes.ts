import { Router } from "express";
import CourseController from "../controllers/CourseController";

const router = Router();

router.get("/", CourseController.getCourses);
router.post("/", CourseController.createCourse);

export default router;
