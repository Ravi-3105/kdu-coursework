// server.js

import express from 'express';
import http from 'http';
import { Server as SocketServer } from 'socket.io';
import fetch from 'node-fetch';
import cors from 'cors';

const app = express();
const server = http.createServer(app);
const io = new SocketServer(server, {
  cors: {
    origin: '*',
    methods: ['GET', 'POST']
  }
});

app.use(cors());

async function fetchStockData() {
  try {
    const response = await fetch('https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/all-stocks-transactions.json');
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error fetching stock data:', error);
    return [];
  }
}

io.on('connection', async (socket) => {
  console.log('A user connected');

  const initialStockData = await fetchStockData();
  socket.emit('initialStockData', initialStockData);

  setInterval(async () => {
    const updatedStockData = await fetchStockData();
    socket.emit('updatedStockData', updatedStockData);
  }, 5000);

  socket.on('disconnect', () => {
    console.log('User disconnected');
  });
});

const PORT = process.env.PORT || 3001;
server.listen(PORT, () => {
  console.log(`Server listening on port ${PORT}`);
});
