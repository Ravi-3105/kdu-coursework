import { useDispatch } from 'react-redux';
import './styles/Filter.scss'
import { setSortList } from '../redux/ProductSlice';

export function Sort() {

    const reduxDispatch = useDispatch();

    const handleDropdownChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        reduxDispatch(setSortList(e.target.value));
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

