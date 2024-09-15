import { useState } from 'react';
import { IState } from './StateInter';
import './ItemInput.scss';
interface IInput {
    input: IState
}

export function ItemInput({ input }: IInput) {
    const [inputValue, setInputValue] = useState('');

    function add() {
        const items = input.listItems;
        const newItem = {
            id: input.listItems.length + 1,
            text: inputValue
        }
        input.state([...items,newItem])
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
