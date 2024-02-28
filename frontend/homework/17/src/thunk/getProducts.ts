import { createAsyncThunk } from "@reduxjs/toolkit";

export const getProducts = createAsyncThunk(
    "getProducts", async () => {
            const response = await fetch("https://fakestoreapi.com/products");
            const data = await response.json();
            return data;
    }
); 