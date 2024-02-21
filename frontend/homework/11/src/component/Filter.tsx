import { IFilt } from "../blueprint/example";
import { FilterItem } from "./FilterItem";
import './Filter.scss'

interface IFilterProp {
    head: IFilt
}

export function Filter({ head }: IFilterProp) {
    return (
        <div className="filter">
            <h1>Filters</h1>
            <div className="filter-list">{
                head.filt.map((item) => {
                    const comb ={
                        tag:item,
                        check:head
                    }
                    return(
                        <FilterItem key={item} head={comb}/>
                    )
                })
        }
            </div>
        </div>
    )
}
