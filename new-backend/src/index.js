import express from "express";
import cors from "cors";
import bodyParser from "body-parser";
import mongoose from "mongoose";
import * as Config from "./config.js";

import courseRoutes from "./adapters/course/CourseRoutes.js";
import disciplineRoutes from "./adapters/discipline/DisciplineRoutes.js";

// import { httpStatusCode } from './enums/httpStatusCode.js';

const app = express();
app.use(bodyParser.json());
app.use(cors());

app.use('/courses', courseRoutes);
app.use("/disciplines", disciplineRoutes);

app.listen(Config.PORT, () => {
    console.log("Servidor iniciado com sucesso! Ouvindo na porta:", Config.PORT);
});

mongoose.connect(Config.mongoDBURL_local, {})
    .then(() => {
        console.log("Conexao com mongoDB realizada com sucesso!");
    })
    .catch((error) => {
        console.error("Erro ao conectar ao mongoDB:", error);
    });

// // Middleware de tratamento de erros
// app.use((err, req, res, next) => {
//     console.error(err.stack);
//     res.status(httpStatusCode.INTERNAL_SERVER_ERROR).json({ error: 'internal server error' });
// });
