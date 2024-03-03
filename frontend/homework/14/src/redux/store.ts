import { configureStore } from "@reduxjs/toolkit";
import itemReducer  from "./ListSlice";

export const store = configureStore({
    reducer: {  //state collection or state combination/aggregator
        items: itemReducer
    }
})

export  type RootState = ReturnType<typeof store.getState>