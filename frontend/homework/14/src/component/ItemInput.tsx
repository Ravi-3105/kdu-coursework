import { useDispatch, useSelector } from 'react-redux';
import { addItem, setInputItem } from '../redux/ListSlice';
import { RootState } from '../redux/store';
import { itemBtn, itemInput } from '../styles/ItemInput';

export function ItemInput() {

    const inputItem = useSelector((state:RootState) => state.items.inputItem);
    const reduxDispatch = useDispatch();
    function generateUniqueId(): number {
        // Generate a unique ID using timestamp and random number
        return Date.now() + Math.floor(Math.random() * 1000);
    }
    function add() {        
        const newItem = {
            id: generateUniqueId(),
            text: inputItem
        }
        reduxDispatch(addItem(newItem));
    }

    return (
        <div style={itemInput}>
            <input
                className="additem"
                value={inputItem}
                onChange={(e) => reduxDispatch(setInputItem(e.target.value))}
            />
            <button
                style={itemBtn}
                onClick={add} // Pass reference to the add function without ()
            >
                Submit
            </button>
        </div>
    );
}
