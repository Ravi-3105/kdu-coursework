import React, { useEffect, useState } from 'react';
import io, { Socket } from 'socket.io-client';
import StockBarGraph from './StockBarGraph';

interface Stock {
  company: string;
  symbol: string;
  data: { date: string; prices: number[] }[];
}

const StockMarketComponent: React.FC = () => {
  const [socket, setSocket] = useState<Socket | null>(null);
  const [stockData, setStockData] = useState<Stock[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const socket = io('http://localhost:3001');
    setSocket(socket);

    socket.on('connect_error', (err) => {
      setError('Socket connection error: ' + err.message);
    });

    return () => {
      if (socket) {
        socket.disconnect();
      }
    };
  }, []);

  useEffect(() => {
    if (!socket) return;

    socket.on('initialStockData', (data: Stock[]) => {
      setStockData(data);
    });

    socket.on('updatedStockData', (data: Stock[]) => {
      setStockData(data);
    });

    return () => {
      socket.off('initialStockData');
      socket.off('updatedStockData');
    };
  }, [socket]);

  return (
    <div>
      {error && <div>Error: {error}</div>}
      {stockData.map(stock => (
        <div key={stock.symbol}>
          <h2>{stock.company}</h2>
          <StockBarGraph data={stock.data} />
        </div>
      ))}
    </div>
  );
};

export default StockMarketComponent;
