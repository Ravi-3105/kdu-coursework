import { configureStore } from "@reduxjs/toolkit";
import stockReducer from './StockSlice'
import portfolioReducer from './PortfolioSlice'

export const store = configureStore({
    reducer: {  //state collection or state combination/aggregator
        dashboard: stockReducer,
        portfolio: portfolioReducer     
    }
})

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;