import mongoose, { Schema, Document } from 'mongoose';

interface Discipline {
  name: string;
  code: string;
  prerequisites: string[]; 
  unlocks: string[]; 
}

interface Course extends Document {
  name: string;
  disciplines: Discipline[];
}

const disciplineSchema = new Schema<Discipline>({
  name: { type: String, required: true },
  code: { type: String, required: true },
  prerequisites: { type: [String], default: [] },
  unlocks: { type: [String], default: [] },
});

const courseSchema = new Schema<Course>({
  name: { type: String, required: true },
  disciplines: { type: [disciplineSchema], required: true },
});

const CourseModel = mongoose.model<Course>('Course', courseSchema);

export default CourseModel;