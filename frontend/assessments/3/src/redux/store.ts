import { configureStore } from "@reduxjs/toolkit";
import hotelReducer from './HotelSlice'

export const store = configureStore({
    reducer: {  //state collection or state combination/aggregator
        hotel: hotelReducer
        
    }
})

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;