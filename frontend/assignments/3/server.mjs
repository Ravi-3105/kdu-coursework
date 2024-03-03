import express from 'express';
import { createServer } from 'http';
import { Server as SocketServer } from 'socket.io';
import fetch from 'node-fetch';
import cors from 'cors'; // Import the cors middleware

const app = express();
const server = createServer(app);

// Use the cors middleware to allow requests from any origin
app.use(cors());

const io = new SocketServer(server, {
  cors: {
    origin: "*", // Allow requests from this origin
    methods: ["GET", "POST"] // Allow specified HTTP methods
  }
});

// Fetch stock data from an API
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

  socket.on('selectedCompany', async (selectedCompany) => {
    console.log('Selected company:', selectedCompany);
    // Handle the selected company here
  });

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

