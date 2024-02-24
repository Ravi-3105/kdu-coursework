import { useContext } from "react";
import { ProductContext } from "../context/Context";
import './styles/Search.scss'


export function Search() {

    const value = useContext(ProductContext);

    function handleInputChange(e: React.ChangeEvent<HTMLInputElement>) {
        value.setSearch(e.target.value);
    }
    return (
        <input className='search' placeholder='Search' value={value.searchQuery}
            onChange={handleInputChange} ></ input>
    )
}