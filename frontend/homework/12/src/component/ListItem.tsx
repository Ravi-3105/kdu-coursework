import './ListItem.scss'
import { DeleteItem } from './DeleteItem'
import { useContext } from 'react'
import { DeleteContext } from './List';

export function ListItem() {
    const value = useContext(DeleteContext);
    return (
        <li className='list-item'>
            <p>{value.item.text}</p>
            <DeleteItem />
        </li>
    )
}