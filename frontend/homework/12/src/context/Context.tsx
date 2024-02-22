import { createContext, useState } from "react";

export interface IListItem {
    id: number;
    text: string;
}
interface IAll {
    oldlist:IListItem[],
    listItems: IListItem[],
    setState: React.Dispatch<React.SetStateAction<{
        id: number;
        text: string;
    }[]>>,
    searchQuery: string;
    setSearchQuery: React.Dispatch<React.SetStateAction<string>>;
}

export const AllContext = createContext<IAll>({
    oldlist:[],
    listItems: [],
    setState: () => {},
    searchQuery: '',
    setSearchQuery: () => { }
})
interface ThemeProviderProp {
    children: React.ReactNode
}
export const ThemeProvider = ({ children }: ThemeProviderProp) => {
    const [listItems, setState] = useState([
        {
            id: 1,
            text: "Item 1",
        },
        {
            id: 2,
            text: "Item 2",
        },
        {
            id: 3,
            text: "Item 3",
        },
    ]);
    const oldlist = listItems;
    const [searchQuery, setSearchQuery] = useState('');

    return (
        <AllContext.Provider value={{oldlist,listItems, setState,searchQuery,setSearchQuery}}>
            {children}
        </AllContext.Provider>
    );
};