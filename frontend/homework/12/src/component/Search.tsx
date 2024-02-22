import { useContext, useState } from 'react';
import './Search.scss'
import { AllContext } from '../context/Context';


export function Search() {

  const value = useContext(AllContext);
  
  function handleInputChange(e:React.ChangeEvent<HTMLInputElement>) {
    value.setSearchQuery(e.target.value);
  }
  return (
    <input className='search' placeholder='Search Items' value={value.searchQuery}
      onChange={handleInputChange} ></ input>
      )
}
