import React, { useEffect, useState } from 'react';
import io from 'socket.io-client';
import StockBarGraph from './StockBarGraph';

interface Stock {
  company: string;
  symbol: string;
  data: { date: string; prices: number[] }[];
}

const StockMarketComponent: React.FC = () => {
  const [socket, setSocket] = useState<any>(null);
  const [stockData, setStockData] = useState<Stock[]>([]);

  useEffect(() => {
    const socket = io('http://localhost:3001');
    setSocket(socket);

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
