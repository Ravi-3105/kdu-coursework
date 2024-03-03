import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../redux/store";
import PlusSvg from "../assets/plus.svg";
import './Tabs.scss'
import { IStock } from "../types/Stock.type";
import { setWatchlist } from "../redux/StockSlice";
import { getStocks } from "../thunk/getStocks";

const tabStyle: React.CSSProperties = {
    display: "flex",
    margin: "1rem",
};

const dataBody: React.CSSProperties = {
    display: "flex",
    flexDirection: "column", // Display children in a column layout
    alignItems: "center", // Center align children horizontally
};

const tableStyle: React.CSSProperties = {
    border: "2px solid black",
    borderRadius: "20px",
    width: "80%",
    margin: "auto",
    height: "70vh",
    padding: "1rem"
};

const bottomCellStyle: React.CSSProperties = {
    borderBottom: "1px solid black",
    width: "100%",
};
const headCellStyle: React.CSSProperties = {
    borderBottom: "2px solid black",
    width: "100%",
};
const pageStyle: React.CSSProperties = {
    background: "white",
    border: "0px",
    fontSize: "1.5rem",
    color: "#1971c2",
};
const stockImgage: React.CSSProperties = {
    height: "1.5rem",
    marginLeft: "1rem"
}
export function Tabs() {
    const explore = useSelector((state: RootState) => state.dashboard.stocks);
    const watchlist = useSelector((state: RootState) => state.dashboard.watchlist);
    const dispatch: AppDispatch = useDispatch();
    useEffect(() => {
        dispatch(getStocks());
        setStocks(explore);
    }, []);
    useEffect(() => {
        if (tab === 0) {
            setStocks(explore);
        }
        else setStocks(watchlist);
    }, [watchlist, explore]);

    const [stocks, setStocks] = useState(explore);
    const reduxDispatch = useDispatch();
    const [currentPage, setCurrentPage] = useState(1);
    const [tab, setTabs] = useState(0);
    const itemsPerPage = 7;

    const totalPages = Math.ceil(stocks.length / itemsPerPage);

    // Function to handle page number click
    const goToPage = (page: number) => {
        setCurrentPage(page);
    };

    const generatePageNumbers = () => {
        const pageCount = 5; // Number of page numbers to display
        const pageNumbers = [];
        let startPage = Math.max(currentPage - Math.floor(pageCount / 2), 1);
        let endPage = Math.min(startPage + pageCount - 1, totalPages);

        if (endPage - startPage < pageCount - 1) {
            startPage = Math.max(endPage - pageCount + 1, 1);
        }

        if (startPage > 1) {
            pageNumbers.push(1);
        }

        if (startPage > 2) {
            pageNumbers.push("...");
        }

        for (let i = startPage; i <= endPage; i++) {
            pageNumbers.push(i);
        }

        if (endPage < totalPages - 1) {
            pageNumbers.push("...");
        }

        if (endPage < totalPages) {
            pageNumbers.push(totalPages);
        }

        return pageNumbers;
    };

    const tabChange = () => {
        if (tab === 0) {
            setTabs(1);
            setStocks(watchlist)
        }
        else {
            setTabs(0);
            setStocks(explore)
        }
    };
    const addWatch = (item: IStock) => {
        reduxDispatch(setWatchlist(item));
    }

    return (
        <div>
            <div className="tabs" style={tabStyle}>
                <div onClick={() => tabChange()} style={{ borderBottom: (tab === 0) ? "2px solid #1971c2" : "0px" }}>Explore</div>
                <div onClick={() => tabChange()} style={{ borderBottom: (tab === 1) ? "2px solid #1971c2" : "0px", marginLeft: "1rem" }}>
                    My Watchlist
                </div>
            </div>
            <div className="dataBody" style={dataBody}>
                <table style={tableStyle}>
                    <thead>
                        <tr>
                            <td style={headCellStyle}>Company</td>
                            <td style={headCellStyle}>Base Price</td>
                            <td style={headCellStyle}>Watchlist</td>
                        </tr>
                    </thead>
                    <tbody>
                        {stocks
                            .slice((currentPage - 1) * itemsPerPage, currentPage * itemsPerPage)
                            .map((item, index) => {
                                const x = watchlist.includes(item);

                                return (
                                    <tr key={index}>
                                        <td style={bottomCellStyle}>{item.stock_name}</td>
                                        <td style={bottomCellStyle}>{item.base_price}</td>
                                        <td style={bottomCellStyle}><button style={{ background: "white", border: "none" }} onClick={() => addWatch(item)}>{
                                            (x) ? (
                                                <div className="image-container">
                                                    <div className="image"></div>
                                                </div>
                                            )
                                                :
                                                (
                                                    <img
                                                        src={PlusSvg}
                                                        alt="add" style={stockImgage}
                                                    />
                                                )
                                        }</button></td>
                                    </tr>
                                );

                            })}
                    </tbody>
                </table>
                <div className="pagination" style={{ width: "50%", margin: "auto" }}>
                    {generatePageNumbers().map((pageNumber, index) => (
                        <button
                            key={index}
                            onClick={() =>
                                typeof pageNumber === "number" && goToPage(pageNumber)
                            }
                            className={pageNumber === currentPage ? "active" : ""}
                            style={pageStyle}
                        >
                            {pageNumber}
                        </button>
                    ))}
                </div>
            </div>
        </div>
    );
}
