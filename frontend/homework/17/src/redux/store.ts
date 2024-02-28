import { configureStore } from "@reduxjs/toolkit";
import productReducer  from "./ProductSlice";
import snackbarReducer from  "./SnackbarSlice"


export const store = configureStore({
    reducer: {  //state collection or state combination/aggregator
        product: productReducer,
        snackbar: snackbarReducer
    }
})

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;