import { ItemHeader } from './ItemHeader';
import './List.scss'
import { ListItem } from './ListItem';
import { IState } from './StateInter'

//For Api prop
export interface IListItem {
    id: number;
    text: string;
}

interface IListProps {
    listIt: IState;
}

export function List({ listIt }: IListProps) {

    return (
        <div className='itemList'>
            <ItemHeader />
            <ul id="list">
                {
                    listIt.listItems.map((item) => {
                        const pair = {
                            item: item,
                            change: listIt
                        }
                        return (
                            <ListItem key={item.id} getPair={pair} />
                        )
                    })
                }
            </ul>
        </div>
    )
}