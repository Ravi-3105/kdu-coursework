import './DeleteItem.scss'
import { IState } from './StateInter'
import { IListItem } from './List';

interface IDelete {
    item: IListItem;
    change: IState;
}

interface IDeleteProp {
    getPair: IDelete
}

export function DeleteItem({ getPair }: IDeleteProp) {
    function del() {
        const list = getPair.change.listItems.filter(x => x.id !== getPair.item.id);
        getPair.change.state([...list]);
    }
    return (
        <button className='del-btn' onClick={del}>X</button>
    )
}
