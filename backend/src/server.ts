import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import connectDB from "./config/database";
import courseRoutes from "./routes/CourseRoutes";

dotenv.config();
const app = express();

app.use(cors());
app.use(express.json());

app.use("/courses", courseRoutes);

const PORT = process.env.PORT || 5000;
connectDB().then(() => {
  app.listen(PORT, () => console.log(`Running at ${PORT}`));
});
