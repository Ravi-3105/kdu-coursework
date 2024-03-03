import React, { useEffect } from "react";
import StockSVG from '../assets/stock.svg';
import { useDispatch } from "react-redux";
import { AppDispatch } from "../redux/store";
import Transaction from "./Transaction";
import { getTransaction } from "../thunk/getTransaction";

const dashStyle: React.CSSProperties = {
    // Your CSS styles for the dashboard container
};
const headStyle: React.CSSProperties = {
    backgroundColor: "#1971c2",
    color: "white",
    display: "flex",
    justifyContent: "space-between",
}
const stockImgage: React.CSSProperties = {
    height: "2rem",
    marginRight: "0.5rem"
}
const display: React.CSSProperties = {
    display: "flex",
    margin: "0.5rem",
    alignItems: "center",
}
export default function Portfolio() {

    const dispatch: AppDispatch = useDispatch();
    useEffect(() => {
        dispatch(getTransaction());
    }, []);

    return (
        <div style={dashStyle}>

            <div className="head" style={headStyle}>

                <div style={display}>
                    <img src={StockSVG} alt="stock market" style={stockImgage} />
                    <p >KDU Stock Market</p>

                </div>

                <div style={display}>
                    <div style={{ marginRight: "0.5rem" }}>Summarizer</div>
                    <div>My Portfolio</div>
                </div>

            </div>
            <Transaction />
        </div>
    );
}
