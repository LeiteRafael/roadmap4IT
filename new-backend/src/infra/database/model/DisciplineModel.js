import mongoose from "mongoose";

const DisciplineSchema = new mongoose.Schema({
  code: String,
  name: String,
  description: String,
  semester: Number,
  prerequisites: [String],
  unlocks: [String],
  categories: [String]
});

export const Discipline = mongoose.model('Discipline', DisciplineSchema);
