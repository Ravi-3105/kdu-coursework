import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Header } from "./Header";
import { Products } from "./Products";
import './styles/Home.scss';
import { AppDispatch, RootState } from "../redux/store";
import { getProducts } from "../thunk/getProducts";
import ClipLoader from "react-spinners/ClipLoader";


const override: React.CSSProperties = {
    display: 'flex',
    margin: '0 auto',
    borderWidth: '1rem',
    borderColor: 'blue',
}

export function Home() {
    const message = useSelector((state: RootState) => state.snackbar.message);
    const state = useSelector((state: RootState) => state.product.state);
    const [showSnackbar, setShowSnackbar] = useState(false);
    const [type, setColor] = useState('yellow');
    const [loading, setLoading] = useState(true);

    const dispatch: AppDispatch = useDispatch();


    useEffect(() => {
        setLoading(true);
        dispatch(getProducts());
    }, []);

    useEffect(() => {
        if (message === 'Loading...') {
            setColor("yellow");
            setLoading(true);
        }
        else if (message === 'Products are Loaded!') {
            setLoading(false);
            setColor("green");
        }
        else {
            setLoading(false);
            setColor("red");
        }
        if (message) {
            setShowSnackbar(true);
            const timer = setTimeout(() => {
                setShowSnackbar(false);
            }, 2000);

            return () => clearTimeout(timer);
        }

    }, [message]);

    return (
        <div className="home">
            <Header />
            <div className="title">KDU MARKETPLACE</div>
            {
                state !== "error" &&
                <Products />
            }
            <ClipLoader size={100} loading={loading} cssOverride={override} />
            {
                showSnackbar && (
                    < span className="snack" ><p style={{ backgroundColor: type }}>{message}</p></span>
                )
            }
        </div >
    );
}
