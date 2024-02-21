import { useState } from "react";
import { IAll } from "../blueprint/example";
import { Api } from "../types/quotes.types";
import './NewQuote.scss'
import ClipLoader from "react-spinners/ClipLoader";

interface INewQuoteProp {
    head: IAll
}

export function NewQuote({ head }: INewQuoteProp) {
    const [loading, setLoading] = useState(false);
    const fetchQuote = () => {
        setLoading(!loading)
        fetch("https://api.quotable.io/quotes/random").
            then((response) => response.json()).
            then((data: Api[]) => {
                setLoading(loading)
                head.allState([data[0], ...head.all]);
            })
    };
    return (
        <div>
            <button className="new" onClick={() => fetchQuote()}>
                <b>NEW QUOTE</b>
                <ClipLoader className="clip" loading={loading}
                />
            </button>
        </div>
    )
}
