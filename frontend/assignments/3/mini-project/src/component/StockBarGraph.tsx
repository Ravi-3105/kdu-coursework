import React, { useEffect, useRef, useState } from 'react';
import Chart from 'chart.js/auto';

interface StockBarGraphProps {
  data: { date: string; prices: number[] }[];
}

const StockBarGraph: React.FC<StockBarGraphProps> = ({ data }) => {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const chartRef = useRef<Chart>();

  useEffect(() => {
    if (canvasRef.current) {
      const ctx = canvasRef.current.getContext('2d');
      if (ctx) {
        chartRef.current = new Chart(ctx, {
          type: 'bar',
          data: {
            labels: data.map(entry => entry.date),
            datasets: data.map(entry => ({
              label: entry.date,
              data: entry.prices,
              backgroundColor: 'rgba(75, 192, 192, 0.2)',
              borderColor: 'rgba(75, 192, 192, 1)',
              borderWidth: 1
            }))
          },
          options: {
            scales: {
              x: {
                stacked: true
              },
              y: {
                stacked: true
              }
            }
          }
        });
      }
    }

    return () => {
      if (chartRef.current) {
        chartRef.current.destroy();
      }
    };
  }, [data]);

  return <canvas ref={canvasRef} />;
};

export default StockBarGraph;
