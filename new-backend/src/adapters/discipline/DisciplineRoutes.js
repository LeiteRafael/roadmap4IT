import express from "express";
import {
  createDiscipline,
  getAllDisciplines,
  getDisciplineByCode,
  updateDisciplineByCode,
  deleteDisciplineByCode
} from "./DisciplineController.js";

const router = express.Router();

router.post("/", createDiscipline);
router.get("/", getAllDisciplines);
router.get("/:code", getDisciplineByCode);
router.put("/:code", updateDisciplineByCode);
router.delete("/:code", deleteDisciplineByCode);

export default router;
