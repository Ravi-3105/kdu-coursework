import React, { createContext, useEffect, useState } from "react";
import { IProduct } from "../type/product.type";
import { unstable_HistoryRouter } from "react-router-dom";

interface IAll {
    product: IProduct[],
    setProduct: React.Dispatch<React.SetStateAction<IProduct[]>>
    filterProduct: IProduct[],
    searchQuery: string,
    setSearch: React.Dispatch<React.SetStateAction<string>>
    category: string,
    setCategory: React.Dispatch<React.SetStateAction<string>>
    sortList: string,
    setSort: React.Dispatch<React.SetStateAction<string>>

}
export const ProductContext = createContext<IAll>({
    product: [],
    setProduct: () => { },
    filterProduct: [],
    searchQuery: '',
    setSearch: () => { },
    category: '',
    setCategory: () => { },
    sortList: '',
    setSort: () => { }
})
interface IListProviderProp {
    children: React.ReactNode
}


export const ListProvider = ({ children }: IListProviderProp) => {
    const [product, setProduct] = useState<IProduct[]>([]);
    const [searchQuery, setSearch] = useState<string>('');
    const [category, setCategory] = useState<string>('All');
    const [sortList, setSort] = useState<string>('Category');
    const [filterProduct, setFilter] = useState<IProduct[]>([]);

    const fetchData = () => {
        fetch("https://fakestoreapi.com/products").
            then((response) => response.json()).
            then((data: IProduct[]) => {
                setProduct(data);
                setFilter(data);
            });
    }
    useEffect(() => {
        fetchData();
    }, []);
    useEffect(() => {
        let filteredProducts = product;
        if (category !== "Category") {
            filteredProducts = filteredProducts.filter(x => x.category === category);
        }

        if (searchQuery !== '') {
            filteredProducts = filteredProducts.filter(x => x.title.includes(searchQuery));
        }

        if (sortList === "ASC") {
            filteredProducts = filteredProducts.slice().sort((a, b) => a.price - b.price);
        } else if (sortList === "DES") {
            filteredProducts = filteredProducts.slice().sort((a, b) => b.price - a.price);
        }

        setFilter(filteredProducts);
    }, [searchQuery, category, sortList]);
    return (
        <ProductContext.Provider value={{ product, setProduct, filterProduct, searchQuery, setSearch, category, setCategory, sortList, setSort }}>
            {children}
        </ProductContext.Provider>

    )
}