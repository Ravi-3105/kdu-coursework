import './Header.scss'
import { Search } from './Search'



export function Header() {
    return (
        <h1><div className='header-div'><p>Item Lister</p><Search/></div></h1>
    )
}