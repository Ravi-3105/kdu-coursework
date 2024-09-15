import { AddHeader } from "./AddHeader";
import {ItemInput} from "./ItemInput";
import {IState} from './StateInter'

interface IAddItemProp{
    item:IState
}
export function AddItem({item} :IAddItemProp) {
    return (
        <>
            <AddHeader />
            <ItemInput input={item}/>
        </>
    )
}
