
import { useContext, useState, createContext } from 'react';
import { AddItem } from './AddItem';
import { Header } from './Header';
import { List } from './List';
import './Todolist.scss'
import { AllContext } from '../context/Context';



export function Todolist() {

const all = useContext(AllContext);

  const filteredList = all.listItems.filter(x =>
    x.text.includes(all.searchQuery));

  all.listItems = all.searchQuery === '' ? all.oldlist : filteredList;


  if (all.searchQuery !== '' && filteredList.length === 0) {
    alert('No Such Item found')
  }
  return (
    <div className="todo-list">
      <Header />

      <div className='mainBody'>
        <AddItem />
        <List />

      </div>
    </div>
  )
}