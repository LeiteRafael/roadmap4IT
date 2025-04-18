import { Course } from '../../infra/database/model/CourseModel.js';
import { Discipline } from '../../infra/database/model/DisciplineModel.js';

export const getAllCoursesService = async () => {
    const courses = await Course.find().populate('disciplines', 'code');

    return courses.map(course => ({
        ...course.toObject(),
        disciplines: course.disciplines.map(d => d.code)
    }));
};

export const getCourseByCodeService = async (code) => {
    const course = await Course.findOne({ code }).populate('disciplines', 'code');

    if (!course) return null;

    const courseObj = course.toObject();
    courseObj.disciplines = course.disciplines.map(d => d.code);

    return courseObj;
};

export const upsertCourseService = async (courseData) => {
    // Buscar disciplinas pelo code
    const disciplines = await Discipline.find({ code: { $in: courseData.disciplines } });

    if (disciplines.length !== courseData.disciplines.length) {
        throw new Error('Uma ou mais disciplinas não foram encontradas.');
    }

    const disciplineIds = disciplines.map(d => d._id);

    const updatedCourse = await Course.findOneAndUpdate(
        { code: courseData.code },
        {
            university: courseData.university,
            name: courseData.name,
            duration: courseData.duration,
            area: courseData.area,
            disciplines: disciplineIds
        },
        {
            new: true,
            upsert: true
        }
    ).populate('disciplines', 'code');

    const courseObj = updatedCourse.toObject();
    courseObj.disciplines = updatedCourse.disciplines.map(d => d.code);
    delete courseObj.__v;

    return courseObj;
};

export const updateCourseByCodeService = async (code, courseData) => {
    // Buscar disciplinas pelo code
    const disciplines = await Discipline.find({ code: { $in: courseData.disciplines } });

    if (disciplines.length !== courseData.disciplines.length) {
        throw new Error('Uma ou mais disciplinas não foram encontradas.');
    }

    const disciplineIds = disciplines.map(d => d._id);

    const updatedCourse = await Course.findOneAndUpdate(
        { code },
        {
            university: courseData.university,
            name: courseData.name,
            duration: courseData.duration,
            area: courseData.area,
            disciplines: disciplineIds
        },
        {
            new: true
        }
    ).populate('disciplines', 'code');

    if (!updatedCourse) return null;

    const courseObj = updatedCourse.toObject();
    courseObj.disciplines = updatedCourse.disciplines.map(d => d.code);
    delete courseObj.__v;

    return courseObj;
};

export const getDisciplinesByCourseCodeService = async (code) => {
    const course = await Course.findOne({ code }).populate('disciplines');

    if (!course) return null;

    return course.disciplines.map(d => {
        const discipline = d.toObject();
        delete discipline.__v;
        return discipline;
    });
};

export const deleteCourseByCodeService = async (code) => {
    const course = await Course.findOneAndDelete({ code });
    return course;
};
