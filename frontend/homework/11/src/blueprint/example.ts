import { Api } from "../types/quotes.types";


export interface IAll {
    all: Api[]
    allState: React.Dispatch<React.SetStateAction<Api[]>>
}

export interface IFilt {
    filt:string[]
    filtState:React.Dispatch<React.SetStateAction<string[]>>
}