import { useNavigate, useParams } from "react-router-dom";
import { Search } from "./Search";
import { useContext } from "react";
import { ProductContext } from "../context/Context";
import './styles/Detail.scss'

export function Detail() {
    const navigate = useNavigate();
    const display = () => {
        navigate(`/home`)
    }
    const { id } = useParams();
    const products = useContext(ProductContext);
    const item = products.filterProduct.find((x) => x.id === parseInt(id!));
    return (
        <div className="tnew">
            <div className="thead">
                <div className="tblank1"></div>
                <Search />
            </div>
            <div className="tback">
                <div className="ttitle">{item?.title}</div>
                <div className='titems'>
                    <div className="tcontrol">
                        <img className="timg" src={item?.image} alt="product" />
                    </div>
                    <div className="tdetail">
                        <div className="tmain">Title: {item?.title}</div>
                        <div className="tprice">Price $ {item?.price}</div>
                        <div className="tdes">Product Descritpion:</div>
                        <div className="tdes">{item?.description}</div>
                        <button className="tbtn" onClick={display}>Go Back To Products</button>
                    </div>
                </div>
            </div>
        </div>
    )
}
