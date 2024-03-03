import { useNavigate, useParams } from "react-router-dom";
import { Search } from "./Search";
import './styles/Detail.scss'
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";

export function Detail() {
    const navigate = useNavigate();
    const display = () => {
        navigate(`/home`)
    }
    const { id } = useParams();
    const filterProduct = useSelector((state: RootState) => state.product.filterProduct);
    const item = filterProduct.find((x) => x.id === parseInt(id!));
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
