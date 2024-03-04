import { PayloadAction, createSlice, current } from "@reduxjs/toolkit";
import { AddOn, IHotel, RoomType } from "../types/Hotel.types";
import { getRooms } from "../thunk/getRooms";

interface HotelState {
    roomDetail: RoomType[],
    roomId: number,
    dateStart: Date | null,
    dateEnd: Date | null,
    addOn: AddOn[],
    addOnSelected: AddOn[],
    cost: number,
    state: "fulfilled" | "pending" | "error"
}
const initialState: HotelState = {
    roomDetail: [],
    roomId: -1,
    dateStart: new Date(),
    dateEnd: new Date(),
    addOn: [],
    addOnSelected: [],
    cost: 0.0,
    state: 'pending'
}
const check = (state: HotelState) => {
    if (state.dateEnd !== undefined && state.dateStart !== undefined) {
        const msDiff = state.dateEnd!.getTime() - state.dateStart!.getTime();    //Future date - current date
        const days = Math.floor(msDiff / (1000 * 60 * 60 * 24));
        let money = parseFloat(state.roomDetail.find(x => x.id === state.roomId)!.costPerNight)
        state.addOnSelected.forEach((add) => {
            console.log(money);
            money = money + parseFloat(add.cost);
        })
        state.cost = (money * days) * 1.18;
        console.log(state.cost);
    }
}
const hotelSlice = createSlice({
    name: 'hotel',
    initialState,
    reducers: {
        setRoom: (state, action: PayloadAction<number>) => {
            state.roomId = action.payload;
            //console.log(state.roomId);
            state.addOn = state.roomDetail.find(x => x.id === state.roomId)!.addOns;
            //console.log(current(state.addOn));
            check(state);
        },
        setAddOn: (state, action: PayloadAction<AddOn>) => {
            if (state.addOnSelected.filter(x => x === action.payload).length === 0) {
                state.addOnSelected = [...state.addOnSelected, action.payload];
            }
            else {
                state.addOnSelected = state.addOnSelected.filter(x => x.name !== action.payload.name);
            }
            check(state);
        },
        setStart: (state, action: PayloadAction<Date | null>) => {
            state.dateStart = action.payload;
            check(state);
        },
        setEnd: (state, action: PayloadAction<Date | null>) => {
            state.dateEnd = action.payload;
            check(state);
        },
    },
    extraReducers(builder) {
        builder.addCase(getRooms.pending, (state) => {
            state.state = "pending";
        }).addCase(getRooms.fulfilled, (state, action: PayloadAction<IHotel>) => {
            state.state = "fulfilled";
            state.roomDetail = action.payload.roomTypes
            //console.log(state.roomDetail);
        }).addCase(getRooms.rejected, (state) => {
            state.state = "error";
        })
    }
})
export const { setRoom, setAddOn, setStart, setEnd } = hotelSlice.actions;
export default hotelSlice.reducer;