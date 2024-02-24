import { Header } from "./Header";
import { Products } from "./Products";
import './styles/Home.scss'


export function Home() {
   
    return (
        <div className="home">
           <Header/>
           <div className="title">KDU MARKETPLACE</div>
           <Products/>
        </div>
    )
}
