import { useDispatch, useSelector } from "react-redux"
import { setEnd, setStart } from "../redux/HotelSlice";

export default function Date() {
  const reduxDispatch = useDispatch();
  return(
    <div>
      <input type="date" onChange={(e) =>reduxDispatch(setStart(e.target.valueAsDate))} />
      <input type="date" onChange={(e) =>reduxDispatch(setEnd(e.target.valueAsDate))} />
    </div>
  )
}
