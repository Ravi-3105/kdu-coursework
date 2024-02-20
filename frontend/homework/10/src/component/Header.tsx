import './Header.scss'
import { Search } from './Search'
import { IState } from './StateInter'

interface ITop {
    top: IState,
    squery: string,
    tstate: React.Dispatch<React.SetStateAction<string>>
}

interface ITopProp {
    head: ITop
}

export function Header({ head }: ITopProp) {
    return (
        <h1><div className='header-div'><p>Item Lister</p><Search find={head} /></div></h1>
    )
}