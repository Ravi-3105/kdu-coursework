import { createAsyncThunk } from "@reduxjs/toolkit/react";
import { ITransaction } from "../types/Portfolio.type";

export const getTransaction = createAsyncThunk<ITransaction[]>(
    "getStocks", async () => {
        try {
            const response = await fetch("https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/portfolio-transactions.json");
            const data = await response.json();
            return data;
        } catch {
            throw Error("Error while making api call");
        }
    }
); 