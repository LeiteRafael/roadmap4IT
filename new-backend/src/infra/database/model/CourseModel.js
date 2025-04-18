import mongoose from "mongoose";

const CourseSchema = new mongoose.Schema({
  university: String,
  code: String,
  name: String,
  duration: Number,
  area: String,
  disciplines: [{ type: mongoose.Schema.Types.ObjectId, ref: 'Discipline' }]
});

export const Course = mongoose.model('Course', CourseSchema);
