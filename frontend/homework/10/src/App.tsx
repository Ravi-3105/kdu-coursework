import { useState } from 'react';
import './App.scss';
import { Todolist } from './component/Todolist';

function App() {
  // API Call
  const [list, setState] = useState([
    {
      id: 1,
      text: "Item 1",
    },
    {
      id: 2,
      text: "Item 2",
    },
    {
      id: 3,
      text: "Item 3",
    },
  ]);
  const flow ={
    listItems: list,
    state: setState
  }
  return <Todolist todo = {flow} />;
}

export default App;
