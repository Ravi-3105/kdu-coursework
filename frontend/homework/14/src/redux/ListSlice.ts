import { PayloadAction, createSlice } from "@reduxjs/toolkit";

export interface IListItem {
    id: number;
    text: string;
}

interface ListState {
    oldlist: IListItem[],
    listItems: IListItem[],
    searchQuery: string,
    inputItem: string,
}
const list = [
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
];
const initialState: ListState = {
    oldlist: list,
    listItems: list,
    searchQuery: '',
    inputItem: ''
}

const listSlice = createSlice({
    name: 'list',
    initialState,
    reducers: {
        setSearchQuery: (state, action: PayloadAction<string>) => {
            state.searchQuery = action.payload
        },
        setInputItem: (state, action: PayloadAction<string>) => {
            state.inputItem = action.payload
        },
        addItem: (state, action: PayloadAction<IListItem>) =>{
            state.oldlist = [...state.oldlist,action.payload]
        },
        setList: (state,action:PayloadAction<IListItem[]>) =>{
            state.listItems = [...action.payload]
        },
        delItem: (state, action: PayloadAction<IListItem>) =>{
            const list = state.oldlist.filter(x => x.id !== action.payload.id);
            state.oldlist = [...list]
        },
    }
});
export const {setList, setSearchQuery, setInputItem , addItem, delItem} = listSlice.actions;
export default listSlice.reducer