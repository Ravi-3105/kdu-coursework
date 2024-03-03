import React, { useEffect, useRef, useState } from 'react';
import Chart from 'chart.js/auto';
import io, { Socket } from 'socket.io-client';

const StockBarGraph = ({ data }) => {
  const [selectedCompany, setSelectedCompany] = useState('');
  const [labels, setLabels] = useState<string[]>([]);
  const [prices, setPrices] = useState<[number, number][]>([]);

  const ctx = useRef<HTMLCanvasElement>(null);
  const chartInstance = useRef<Chart | null>(null);
  const socketRef = useRef<Socket | null>(null);

  useEffect(() => {
    if (data && data.length > 0) {
      setSelectedCompany(data[0].company);
      setLabels(data[0].data.map(entry => entry.date));
      setPrices(data[0].data.map((entry, index) => [index, entry.prices]));
    }
  }, [data]);

  useEffect(() => {
    if (ctx.current && chartInstance.current) {
      chartInstance.current.data.datasets[0].label = selectedCompany;
      chartInstance.current.data.labels = labels;
      chartInstance.current.data.datasets[0].data = prices;
      chartInstance.current.update();
    }
  }, [selectedCompany, labels, prices]);

  useEffect(() => {
    socketRef.current = io('http://localhost:3001');

    return () => {
      if (socketRef.current) {
        socketRef.current.disconnect();
      }
    };
  }, []);

  useEffect(() => {
    if (socketRef.current) {
      socketRef.current.emit('selectedCompany', selectedCompany);
    }
  }, [selectedCompany]);

  const handleCompanyChange = (event) => {
    setSelectedCompany(event.target.value);
  };

  return (
    <div>
      <select value={selectedCompany} onChange={handleCompanyChange}>
        {data && data.map((companyData, index) => (
          <option key={index} value={companyData.company}>{companyData.company}</option>
        ))}
      </select>
      <canvas ref={ctx} />
    </div>
  );
};

export default StockBarGraph;
