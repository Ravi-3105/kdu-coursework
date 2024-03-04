import { configureStore } from "@reduxjs/toolkit";
import itemReducer from "./ListSlice";
import storage from 'redux-persist/lib/storage'
import { persistReducer } from 'redux-persist'


const persistConfig = {
    key: 'root',
    storage
};

const persistedReducer = persistReducer(persistConfig, itemReducer);

export const store = configureStore({
    reducer: persistedReducer,
})

export type RootState = ReturnType<typeof store.getState>