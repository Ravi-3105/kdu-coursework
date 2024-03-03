import { createAsyncThunk } from "@reduxjs/toolkit";
import { IStock } from "../types/Stock.type";

export const getStocks = createAsyncThunk<IStock[]>(
    "getStocks", async () => {
        try {
            const response = await fetch("https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json");
            const data = await response.json();
            return data;
        } catch {
            throw Error("Error while making api call");
        }
    }
); 
