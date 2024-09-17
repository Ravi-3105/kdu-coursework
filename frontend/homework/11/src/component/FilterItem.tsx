import { IFilt } from "../blueprint/example"
import './FilterItem.scss'

interface IComb {
    tag: string,
    check: IFilt
}

interface IFilterItemProp {
    head: IComb
}

export function FilterItem({ head }: IFilterItemProp) {
    const deleteFilter = () => {
        head.check.filtState(
            head.check.filt.filter(x => x !== head.tag)
        );
    }
    return (
        <div className="filter-item">
            <p className="head"><b>{head.tag}</b>
                <button className="fil-del"
                    onClick={() => deleteFilter()}>
                    x</button>
            </p>

        </div>
    )
}
