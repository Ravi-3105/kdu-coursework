import { useContext } from "react";
import { ProductContext } from "../context/Context";
import './styles/Filter.scss'

export function Sort() {

    const product = useContext(ProductContext);
    const handleDropdownChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        product.setSort(e.target.value);
    };

    return (
        <div className="sort">
            <label className="select" htmlFor="myDropdown">Sort: </label>
            <select className="myDropdown" onChange={handleDropdownChange}>
                <option value="Price">Price</option>
                <option value="ASC">ASC</option>
                <option value="DES">DES</option>
            </select>
        </div>
    );
}

