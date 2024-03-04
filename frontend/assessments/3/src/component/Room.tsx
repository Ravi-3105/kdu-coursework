import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { setRoom } from "../redux/HotelSlice";
import React from "react";

const roomStyle: React.CSSProperties = {
    display: "flex",
}
const btn: React.CSSProperties = {
    marginRight: "0.5rem",
    backgroundColor: "white"
}

export default function Room() {

    const roomDetail = useSelector((state: RootState) => state.hotel.roomDetail);
    const roomId = useSelector((state: RootState) => state.hotel.roomId);
    const reduxDispatch = useDispatch();
    const roomSelect = (id: number) => {
        reduxDispatch(setRoom(id));
    }
    const sel: React.CSSProperties = {
        color: "#f08a5d",
        backgroundColor: "white",
        marginRight: "0.5rem",
        border: "1px solid #f08a5d",
    }
    return (
        <div style={roomStyle}>
            {
                roomDetail.map((room) => {
                    return (
                        <div key={room.id}>
                            <button style={roomId === room.id ? sel : btn} onClick={() => roomSelect(room.id)}>{room.name}</button>
                        </div>
                    )
                })
            }
        </div>
    )
}
