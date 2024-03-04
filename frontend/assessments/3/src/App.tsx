import React, { useEffect } from 'react'
import './App.css'
import { useDispatch, useSelector } from 'react-redux';
import { AppDispatch, RootState } from './redux/store';
import { getRooms } from './thunk/getRooms';
import Room from './component/Room';
import Cost from './component/Cost';
import AddOnList from './component/AddOn';
import DateSet from './component/DateSet';

const head: React.CSSProperties = {
  backgroundColor: "#f08a5d",
  color: "white",
  padding: "0.3rem",
}
const marg: React.CSSProperties = {
  margin: "1rem"
}
const h1: React.CSSProperties = {
  textAlign: "center"
}

function App() {

  const roomDetail = useSelector((state: RootState) => state.hotel.roomDetail);

  const dispatch: AppDispatch = useDispatch();

  useEffect(() => {
    dispatch(getRooms());
  }, []);
  console.log(roomDetail);
  return (
    <div>
      <h1 style={h1}>Hotel Booking</h1>
      <div style={marg}>
        <div style={head}>Select Room Type</div>
        <Room />
      </div>
      <div style={marg}>
        <div style={head}>Select Date </div>
        <DateSet />
      </div>
      <div style={marg}>
        <div style={head}>Select additional add ons/preferences</div>
        <AddOnList />
      </div>
      <div style={marg}>
        <Cost />
        <button style={head}>Submit</button>
      </div>
    </div>
  )
}

export default App
