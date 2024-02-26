import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/store';
import { setSearchQuery } from '../redux/ListSlice';
import { search } from '../styles/SearchStyle';


export function Search() {

  const searchQuery = useSelector((state:RootState) => state.items.searchQuery);
  const reduxDispatch = useDispatch();
  function handleInputChange(e:React.ChangeEvent<HTMLInputElement>) {
     reduxDispatch(setSearchQuery(e.target.value));
  }
  return (
    <input style={search} placeholder='Search Items' value={searchQuery}
      onChange={handleInputChange} ></ input>
      )
}
