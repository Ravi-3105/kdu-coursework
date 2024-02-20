import './ListItem.scss'
import { DeleteItem } from './DeleteItem'
import { IState } from './StateInter'
import { IListItem } from './List'

declare function alert(message: string): void;

interface IListPair {
    item: IListItem;
    change: IState;
}
interface IListItemProps {
    getPair: IListPair
}
export function ListItem({ getPair }: IListItemProps) {
    return (
        <li className='list-item'>
            <p>{getPair.item.text}</p>
            <DeleteItem getPair={getPair} />
        </li>
    )
}