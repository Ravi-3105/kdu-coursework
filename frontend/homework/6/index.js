const express = require("express");
const app = express();
const routeApi = require("./routes/api");

app.use(express.json());

const PORT = process.env.PORT || 3000;

app.listen(PORT, () => {
  console.log("Server started on port 3000");
});

app.use("/api", routeApi);
