import { PayloadAction, createSlice} from "@reduxjs/toolkit";
import { ITransaction } from "../types/Portfolio.type";
import { getTransaction } from "../thunk/getTransaction";

interface PortfolioState {
    transaction: ITransaction[],
    state: "fulfilled" | "pending" | "error"
}

const initialState: PortfolioState = {
    transaction:[],
    state: 'pending'
}

const portfolioSlice = createSlice({
    name: 'transaction',
    initialState,
    reducers: {
    },
    extraReducers(builder) {
        builder.addCase(getTransaction.pending, (state) => {
            state.state = "pending";
            console.log("pending")

        }).addCase(getTransaction.fulfilled, (state, action: PayloadAction<ITransaction[]>) => {
            state.state = "fulfilled";
            state.transaction = action.payload;

            console.log("Stocks pricesrecieved")

        }).addCase(getTransaction.rejected, (state) => {
            state.state = "error";
            console.log("Error receiving stock prices")
        })
    }
})
export default portfolioSlice.reducer;