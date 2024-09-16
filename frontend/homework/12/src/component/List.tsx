import { createContext, useContext } from 'react';
import { ItemHeader } from './ItemHeader';
import './List.scss'
import { ListItem } from './ListItem';
import { AllContext, IListItem } from '../context/Context';

interface IDelete {
    item: IListItem
}

export const DeleteContext = createContext<IDelete>({ item: { id: 0, text: '' } });

export function List() {

    const list = useContext(AllContext);
    return (
        <div className='itemList'>
            <ItemHeader />
            <ul id="list">
                {
                    list.listItems.map((item) => {
                        const delItem = item;
                        return (
                            <DeleteContext.Provider value={{ item: delItem }}>
                                <ListItem key={item.id} />
                            </DeleteContext.Provider>
                        )
                    })
                }
            </ul>
        </div >
    )
}