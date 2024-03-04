import { useSelector, useDispatch } from "react-redux";
import { setAddOn } from "../redux/HotelSlice";
import { RootState } from "../redux/store";
import { AddOn } from "../types/Hotel.types";


const roomStyle: React.CSSProperties = {
    display: "flex",
}
const btn: React.CSSProperties = {
    marginRight: "0.5rem",
    backgroundColor:"white",
}
export default function AddOnList() {

    const addOn = useSelector((state: RootState) => state.hotel.addOn);
    const addOnSelected = useSelector((state: RootState) => state.hotel.addOnSelected);
       const reduxDispatch = useDispatch();
    const roomSel = (add: AddOn) => {
        reduxDispatch(setAddOn(add));
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
                addOn.map((add) => {
                    return (
                        <div key={add.name} style={btn}>
                            <button style={addOnSelected.includes(add) ? sel : btn} onClick={() => roomSel(add)}>{add.name}</button>
                        </div>
                    )
                })
            }
        </div>
    )
}
