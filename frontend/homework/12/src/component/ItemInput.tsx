import { useContext, useState } from 'react';
import './ItemInput.scss';
import { AllContext } from '../context/Context';

export function ItemInput() {
    const [inputValue, setInputValue] = useState('');
    const value = useContext(AllContext);

    function add() {
        const items = value.listItems;
        const newItem = {
            id: value.listItems.length + 3,
            text: inputValue
        }
        value.setState([...items, newItem])
    }

    return (
        <div className="itemInput">
            <input
                className="additem"
                value={inputValue}
                onChange={(e) => setInputValue(e.target.value)}
            />
            <button
                className="item-btn"
                onClick={add} // Pass reference to the add function without ()
            >
                Submit
            </button>
        </div>
    );
}
