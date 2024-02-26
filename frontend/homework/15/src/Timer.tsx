import { useRef, useState, useEffect } from "react";

export const Timer = () => {
  const intervalRef = useRef<number | undefined>();
  const [time, setTime] = useState(0);
  const [isRunning, setIsRunning] = useState(false);

  const startTimer = () => {
    setIsRunning(true);
    intervalRef.current = setInterval(() => {
      setTime(prevTime => prevTime + 1);
    }, 1000);
  };

  const stopTimer = () => {
    setIsRunning(false);
    if (intervalRef.current) {
      clearInterval(intervalRef.current);
      intervalRef.current = undefined; // Reset intervalRef.current to undefined
    }
  };

  const resetTimer = () => {
    stopTimer();
    setTime(0);
  };

  useEffect(() => {
    return () => {
      clearInterval(intervalRef.current);
    };
  }, []);

  return (
    <div style={{ alignItems: 'center' }}>
      <div style={{ textAlign: "center", backgroundColor: 'lightgrey', margin: '2rem' }}>{time}</div>

      <button onClick={startTimer} style={{ margin: '2rem', padding: '1rem' }}>Start</button>
      <button onClick={stopTimer} style={{ margin: '2rem', padding: '1rem' }}>Stop</button>
      <button onClick={resetTimer} style={{ margin: '2rem', padding: '1rem' }}>Reset</button>
    </div>
  );
};