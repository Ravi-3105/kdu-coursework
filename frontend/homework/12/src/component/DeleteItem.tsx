import { useContext } from 'react';
import './DeleteItem.scss'
import { AllContext } from '../context/Context';
import { DeleteContext } from './List';

export function DeleteItem() {

    const listArray = useContext(AllContext);
    const listItem = useContext(DeleteContext);
    function del() {
        const list = listArray.listItems.filter(x => x.id !== listItem.item.id);
        listArray.setState([...list]);
    }
    return (
        <button className='del-btn' onClick={del}>X</button>
    )
}
