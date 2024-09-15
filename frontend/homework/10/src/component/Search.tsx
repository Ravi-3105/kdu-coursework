import { useState } from 'react';
import { IState } from './StateInter';
import './Search.scss'

interface IFind {
  top: IState,
  squery: string,
  tstate: React.Dispatch<React.SetStateAction<string>>
}

interface IFindProp {
  find: IFind
}

export function Search({ find }: IFindProp) {
  function handleInputChange(e:React.ChangeEvent<HTMLInputElement>) {
    find.tstate(e.target.value);
    const filteredList = find.top.listItems.filter(x =>
      x.text === find.squery);
      if(filteredList.length === 0){
        alert("No Such Item Present");
      }
  }
  return (
    <input className='search' placeholder='Search Items' value={find.squery}
      onChange={handleInputChange} ></ input>
      )
}
