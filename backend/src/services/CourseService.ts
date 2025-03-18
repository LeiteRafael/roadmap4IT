import CourseModel from "../models/Course";

class CourseService {
  async getAllCourses() {
    return await CourseModel.find();
  }

  async getCourseById(id: string) {
    return await CourseModel.findById(id);
  }

  async createCourse(name: string, disciplines: any[]) {
    const newCourse = new CourseModel({ name, disciplines });
    return await newCourse.save();
  }
}

export default new CourseService();
