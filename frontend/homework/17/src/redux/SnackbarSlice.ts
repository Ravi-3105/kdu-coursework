import { createSlice } from "@reduxjs/toolkit"
import { getProducts } from "../thunk/getProducts"

interface SanackbarState {
    message:string
}
const initialState:SanackbarState = {
    message: ''
}
const snackbarSlice = createSlice({
    name: 'snackbar',
    initialState,
    reducers:{       
    },
    extraReducers(builder) {
        builder.addCase(getProducts.pending, (state) => {
            state.message = 'Loading...';
        }).addCase(getProducts.fulfilled, (state) => {
            state.message = 'Products are Loaded!'
        }).addCase(getProducts.rejected,(state) => {
            state.message = 'Failed to load Products'
        })
    }
})
export default snackbarSlice.reducer;