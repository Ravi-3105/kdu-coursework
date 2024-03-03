import {useState } from "react";
import './Transaction.scss'
import { ITransaction } from "../types/Portfolio.type";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";

interface CheckedItems {
    [key: string]: boolean;
}

export default function Transaction() {
    const [selectedOption, setSelectedOption] = useState('');
    const data = useSelector((state: RootState) => state.portfolio.transaction);
    const [checkedItems, setCheckedItems] = useState<CheckedItems>({});

    const groupedData: { [key: string]: Array<ITransaction> } = {};

    data.forEach(item => {
        const date = new Date(item.timestamp).toDateString();
        if (!groupedData[date]) {
            groupedData[date] = [];
        }
        groupedData[date].push(item);
    });

    type GroupedData = { [key: string]: ITransaction[] };

    const filteredData = Object.keys(groupedData).reduce((acc: GroupedData, key) => {
        const filteredItems = groupedData[key].filter(item => {
            if (selectedOption && item.status !== selectedOption) {
                return false;
            }
            if (Object.keys(checkedItems).length > 0 && !checkedItems[item.stock_name]) {
                return false;
            }
            return true;
        });
        if (filteredItems.length > 0) {
            acc[key] = filteredItems;
        }
        return acc;
    }, {} as GroupedData);

    const handleOptionChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSelectedOption(event.target.value);
    };

    const handleCheckboxChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, checked } = event.target;
        setCheckedItems({ ...checkedItems, [name]: checked });
    };


    return (
        <div className="body">
            <div className="filters">
                <div className="head">
                    <div>Filters</div>
                    <button className="clr">Clear All</button>
                </div>
                <div className="search">
                    <input type="text" placeholder="Search for a stock" className="txt" />
                </div>
                <div className="date">
                    <div>Start</div>
                    <input type="date" className="start" placeholder="Start date" />
                    <div style={{ marginLeft: "0.6rem" }}>End</div>
                    <input type="date" className="end" placeholder="End Date" />
                </div>
                <div className="status">
                    <label>
                        <input
                            type="radio"
                            value="Passed"
                            checked={selectedOption === 'Passed'}
                            onChange={handleOptionChange}
                        />
                        Passed
                    </label>
                    <label>
                        <input
                            type="radio"
                            value="Failed"
                            checked={selectedOption === 'Failed'}
                            onChange={handleOptionChange}
                        />
                        Failed
                    </label>
                </div>
                <div className="company">
                    <label>
                        <input
                            type="checkbox"
                            name="Zomato"
                            checked={checkedItems['Zomato'] || false}
                            onChange={handleCheckboxChange}
                        />
                        Zomato
                    </label>
                    <br />
                    <label>
                        <input
                            type="checkbox"
                            name="Apple"
                            checked={checkedItems['Apple'] || false}
                            onChange={handleCheckboxChange}
                        />
                        Apple
                    </label>
                    <br />
                    <label>
                        <input
                            type="checkbox"
                            name="PayTM"
                            checked={checkedItems['PayTM'] || false}
                            onChange={handleCheckboxChange}
                        />
                        PayTM
                    </label>
                    <br />
                    <label>
                        <input
                            type="checkbox"
                            name="ITC"
                            checked={checkedItems['ITC'] || false}
                            onChange={handleCheckboxChange}
                        />
                        ITC
                    </label>
                    <br />
                    <label>
                        <input
                            type="checkbox"
                            name="divC"
                            checked={checkedItems['divC'] || false}
                            onChange={handleCheckboxChange}
                        />
                        divC
                    </label>
                    <br />
                    <label>
                        <input
                            type="checkbox"
                            name="Hindustan Unilever"
                            checked={checkedItems['Hindustan Unilever'] || false}
                            onChange={handleCheckboxChange}
                        />
                        Hindustan Unilever
                    </label>
                    <br />
                    <label>
                        <input
                            type="checkbox"
                            name="HDFC Bank"
                            checked={checkedItems['HDFC Bank'] || false}
                            onChange={handleCheckboxChange}
                        />
                        HDFC Bank
                    </label>
                </div>
            </div>
            <div className="transaction">
                {Object.entries(filteredData).map(([date, items]) => (
                    <div key={date}>
                        <h2>{date}</h2>
                        <table className="table">
                            {items.map((item, index) => (
                                <tr key={index} className="row">
                                    <td>{item.stock_name}</td>
                                    <td>{item.stock_symbol}</td>
                                    <td>{item.transaction_price}</td>
                                    <td>
                                    {item.status === 'Failed' && <span className="dot red"></span>}
                                    {item.status === 'Passed' && <span className="dot green"></span>}
                                    </td>
                                </tr>
                            ))}
                        </table>
                    </div>
                ))}
            </div>
        </div>
    )
}
