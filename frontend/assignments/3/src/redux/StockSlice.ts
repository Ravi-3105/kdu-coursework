import { PayloadAction, createSlice, current } from "@reduxjs/toolkit";
import { IStock } from "../types/Stock.type";
import { getStocks } from "../thunk/getStocks";

interface StockState {
    stocks: IStock[],
    watchlist: IStock[],
    state: "fulfilled" | "pending" | "error"
}

const initialState: StockState = {
    stocks: [],
    watchlist: [],
    state: 'pending'
}

const stockSlice = createSlice({
    name: 'stock',
    initialState,
    reducers: {
        setWatchlist: (state, action: PayloadAction<IStock>) => {
            const existingIndex = state.watchlist.findIndex(stock => stock.stock_name === action.payload.stock_name);

            if (existingIndex !== -1) {
                console.log("del");
                state.watchlist.splice(existingIndex, 1); // Remove the item from the array
            } else {
                console.log("add");
                state.watchlist.push(action.payload); // Add the item to the array
            }
        },
    },
    extraReducers(builder) {
        builder.addCase(getStocks.pending, (state) => {
            state.state = "pending";
            console.log("pending")

        }).addCase(getStocks.fulfilled, (state, action: PayloadAction<IStock[]>) => {
            state.state = "fulfilled";
            state.stocks = [...action.payload].sort((a, b) =>
                a.stock_name.localeCompare(b.stock_name));

            console.log("Stocks pricesrecieved")

        }).addCase(getStocks.rejected, (state) => {
            state.state = "error";
            console.log("Error receiving stock prices")
        })
    }
})
export const { setWatchlist } = stockSlice.actions;
export default stockSlice.reducer;