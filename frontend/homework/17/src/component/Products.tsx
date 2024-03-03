import { useSelector } from 'react-redux';
import './styles/Product.scss'
import { useNavigate } from "react-router-dom";
import { RootState } from '../redux/store';


export function Products() {
    
    const filterProduct = useSelector((state: RootState) => state.product.filterProduct);
    const navigate = useNavigate();

    return (
        <div className="product">
            {
                filterProduct.map((item) => {
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
