
import { useDispatch, useSelector } from 'react-redux';
import { AddItem } from './component/AddItem'
import { Header } from './component/Header'
import { List } from './component/List'
import { RootState } from './redux/store';
import { setList } from './redux/ListSlice';
import { mainBody, todolist } from './styles/AppStyle';

function App() {
  const oldList = useSelector((state:RootState)=> state.items.oldlist);
  const searchQuery = useSelector((state:RootState)=> state.items.searchQuery);
  const reduxDispatch = useDispatch();

  const filteredList = oldList.filter(x =>
    x.text.includes(searchQuery));

  reduxDispatch(setList(searchQuery === '' ? oldList : filteredList));


  if (searchQuery !== '' && filteredList.length === 0) {
    alert('No Such Item found')
  }
  return (
    <div className="todo-list" style={todolist}>
      <Header />

      <div className='mainBody' style={mainBody}>
        <AddItem />
        <List />

      </div>
    </div>
  )
}

export default App
