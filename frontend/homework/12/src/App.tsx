import { createContext, useState } from 'react';
import './App.scss';
import { Todolist } from './component/Todolist';
import { ThemeProvider } from './context/Context';



function App() {
  // API Call
  

  return (
    <ThemeProvider>
      <Todolist />
    </ThemeProvider>
  );
}

export default App;
