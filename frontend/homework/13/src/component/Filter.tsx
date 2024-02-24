import { useContext } from "react";
import { ProductContext } from "../context/Context";
import './styles/Filter.scss'
import { allFilter } from "../type/product.type";

export function Filter() {

    const product = useContext(ProductContext);
    const handleDropdownChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        product.setCategory(e.target.value);
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

