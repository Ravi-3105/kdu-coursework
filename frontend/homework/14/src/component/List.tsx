import { ItemHeader } from './ItemHeader';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/store';
import { delItem } from '../redux/ListSlice';
import { delBtn, itemList, listStyles } from '../styles/List';

export function List() {

    const list = useSelector((state: RootState) => state.items.listItems);
    return (
        <div style={itemList}>
            <ItemHeader />
            <ul id="list" >
                {
                    list.map((item) => {
                        const reduxDispatch = useDispatch();
                        function del() {
                            reduxDispatch(delItem(item))
                        }
                        return (
                            <li style={listStyles}>
                                <p>{item.text}</p>
                                <button style={delBtn} onClick={del}>X</button>
                            </li>
                        )
                    })
                }
            </ul>
        </div >
    )
}