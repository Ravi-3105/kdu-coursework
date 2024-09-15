import { useContext } from "react";
import { ProductContext } from "../context/Context";
import './styles/Product.scss'
import { Detail } from "./Detail";
import { useNavigate } from "react-router-dom";


export function Products() {
    const products = useContext(ProductContext);


    return (
        <div className="product">
            {
                products.filterProduct.map((item) => {
                    const navigate = useNavigate();
                    const display = () => {
                        navigate(`/home/${item.id}`)
                    }
                    return (
                        <div className='items' key={item.id} onClick={display}>
                            <div className="top"></div>
                            <div className="control">
                                <img className="img" src={item.image} alt="product" />
                            </div>
                            <div className="detail">
                                <div>{item.title}</div>
                                <div>$ {item.price}</div>
                            </div>
                        </div>
                    )
                }
                )
            }
        </div>
    );
}
