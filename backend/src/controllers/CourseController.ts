import { Request, Response } from "express";
import CourseService from "../services/CourseService";

class CourseController {
  async getCourses(req: Request, res: Response) {
    try {
      const courses = await CourseService.getAllCourses();
      res.json(courses);
    } catch (error) {
      res.status(500).json({ message: "Generic error" });
    }
  }

  async createCourse(req: Request, res: Response) {
    try {
      const { name, disciplines } = req.body;
      if (!name) return res.status(400).json({ message: "Name is required" });

      const newCourse = await CourseService.createCourse(name, disciplines);
      res.status(201).json(newCourse);
    } catch (error) {
      console.log(error);
      res.status(500).json({ message: "Generic error" });
    }
  }
}

export default new CourseController();
