import { Filter } from "./Filter";
import { Search } from "./Search";
import { Sort } from "./Sort";
import './styles/Header.scss'


export function Header() {
    return (
        <div className="head">
            <div className="blank1"></div>
            <Search />
            <div className="blank2"></div>
            <Filter />
            <div className="blank3"></div>
            <Sort />
        </div>
    )
}
