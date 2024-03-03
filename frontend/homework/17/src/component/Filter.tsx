import './styles/Filter.scss'
import { allFilter } from "../type/product.type";
import { useDispatch } from "react-redux";
import { setCategory } from "../redux/ProductSlice";

export function Filter() {

    const reduxDispatch = useDispatch();

    const handleDropdownChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        reduxDispatch(setCategory(e.target.value));
    };

    return (
        <div className="filter">
            <label className="select" htmlFor="myDropdown">Filter: </label>
            <select className="myDropdown" onChange={handleDropdownChange}>
                <option value="Category">Category</option>
                {
                    allFilter.map((filter) => (
                        <option key={filter} value={filter}>{filter}</option>
                    ))}
            </select>
        </div>
    );
}

