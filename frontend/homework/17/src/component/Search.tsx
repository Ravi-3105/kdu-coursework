import './styles/Search.scss'
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { setSearch } from '../redux/ProductSlice';


export function Search() {

    const searchQuery = useSelector((state: RootState) => state.product.searchQuery);
    const reduxDispatch = useDispatch();

    function handleInputChange(e: React.ChangeEvent<HTMLInputElement>) {
        reduxDispatch(setSearch(e.target.value));
    }
    return (
        <input className='search' placeholder='Search' value={searchQuery}
            onChange={handleInputChange} ></ input>
    )
}