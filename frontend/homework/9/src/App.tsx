import React from 'react';
import logo from './logo.svg';
import './App.css';
import { IHome, Home } from './component/Home'

function App() {
  const profile: IHome = {
    "name": "Ravi",
    "fullName": "Ravi Ranjan Maurya",
    "qualification": "Software Developer intern",
    "skills": [
      {
        "id": 1,
        "skill": "Java"
      },
      {
        "id": 2,
        "skill": "React"
      }
    ],
    "hobbies": [
      {
        "id": 1,
        "hobby": "Cricket"
      }
    ]
  }
  return (
    <Home homeList={profile}/>
  );
}

export default App;
