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

export function Search ({ find }: IFindProp) {

  return (
    <input className='search' placeholder='Search Items' value={find.squery}
      onChange={(e) => find.tstate(e.target.value)}></input>
  )
}
