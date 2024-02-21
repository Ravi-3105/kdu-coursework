import { useState } from "react"
import { IFilt } from "../blueprint/example"
import { Api } from "../types/quotes.types"
import './Quote.scss'

interface IQuote {
    head: IFilt
    foot: Api
}

interface IQuoteProps {
    quote: IQuote
}

export function Quote({ quote }: IQuoteProps) {

    const addFilter = (q:string) => {
        quote.head.filtState(
            [q, ...quote.head.filt]
        );
    }
    return (
        <div className="quote">
            <h1>{quote.foot.content}</h1>
            <p className="author"><b>~{quote.foot.author}</b></p>
            <p className="date">{quote.foot.dateAdded}</p>

            <div>
                {quote.foot.tags.map((q) => {
                    return (
                        <button className="tags" key={q} onClick={() => addFilter(q)}><b>{q}</b></button>
                    )
                })}
            </div>
        </div>
    )
}