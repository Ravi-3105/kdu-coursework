const express = require("express");
const cors = require("cors");
const http = require("http");
const socketIo = require("socket.io");

const app = express();
const server = http.createServer(app);
const io = new socketIo.Server(server, {
  cors: {
    origin: '*'
  },
});
app.use(cors());

app.use(express.json());

app.get("/", (req, res) => {
  res.json({
    msg: "Hello World",
  });
});

io.on("connection", (socket) => {
  console.log("connected");

  socket.on("message",(payload) =>{
    console.log("Payload",payload);
    io.except(socket.id).emit('new-message',payload);
  })
});

server.listen(3001, () => {
  console.log("Server started");
});
