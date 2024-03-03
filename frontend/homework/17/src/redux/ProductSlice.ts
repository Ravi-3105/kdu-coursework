import { PayloadAction, createSlice } from "@reduxjs/toolkit/react";
import { IProduct } from "../type/product.type"
import { getProducts } from "../thunk/getProducts";


interface ProductState {
    product: IProduct[],
    filterProduct: IProduct[],
    searchQuery: string,
    category: string,
    sortList: string,
    state: "fulfilled" | "pending" | "error"
}
const initialState: ProductState = {
    product: [],
    filterProduct: [],
    searchQuery: '',
    category: '',
    sortList: '',
    state: "pending"
}
const check = (state: ProductState, category: string, searchQuery: string, sortList: string) => {
    let filteredProducts = state.product;

    if (state.category !== "Category") {
        filteredProducts = filteredProducts.filter(x => x.category === category);
    }
    console.log(...filteredProducts);
    if (state.searchQuery !== "") {
        filteredProducts = filteredProducts.filter(x => x.title.includes(searchQuery));
    }

    if (sortList === "ASC") {
        filteredProducts = filteredProducts.slice().sort((a, b) => a.price - b.price);
    } else if (sortList === "DES") {
        filteredProducts = filteredProducts.slice().sort((a, b) => b.price - a.price);
    }
    state.filterProduct = filteredProducts;
}
const productSlice = createSlice({
    name: 'product',
    initialState,
    reducers: {
        setSearch: (state, action: PayloadAction<string>) => {
            state.searchQuery = action.payload;
            check(state, state.category, state.searchQuery, state.sortList);
        },
        setCategory: (state, action: PayloadAction<string>) => {
            state.category = action.payload;
            check(state, state.category, state.searchQuery, state.sortList);
        },
        setSortList: (state, action: PayloadAction<string>) => {
            state.sortList = action.payload;
            check(state, state.category, state.searchQuery, state.sortList);
        },
    },
    extraReducers(builder) {
        builder.addCase(getProducts.pending, (state) => {
            state.state = "pending";
        }).addCase(getProducts.fulfilled, (state, action: PayloadAction<IProduct[]>) => {
            state.product = action.payload;
            state.filterProduct = action.payload;
            state.state = "fulfilled";
        }).addCase(getProducts.rejected, (state) =>{
            state.state = "error";
        })
    }
});
export const { setSearch, setCategory, setSortList } = productSlice.actions;
export default productSlice.reducer;