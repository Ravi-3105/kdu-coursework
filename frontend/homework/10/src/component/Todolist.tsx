
import { useState } from 'react';
import { AddItem } from './AddItem';
import { Header } from './Header';
import { List } from './List';
import { IState } from './StateInter'
import './Todolist.scss'


interface ITodoListProps {
  todo: IState
}


export function Todolist({ todo }: ITodoListProps) {


  const [searchQuery, setSearchQuery] = useState('');
  const comb = {
    top: todo,
    squery: searchQuery,
    tstate: setSearchQuery
  }

  const filteredList = todo.listItems.filter(x =>
    x.text === searchQuery);
  const pass = {
    listItems: searchQuery === '' ? todo.listItems : filteredList,
    state: todo.state
  }

  return (
    <div className="todo-list">
      <Header head={comb} />
      <div className='mainBody'>
        <AddItem item={pass} />
        <List listIt={pass} />

      </div>
    </div>
  )
}