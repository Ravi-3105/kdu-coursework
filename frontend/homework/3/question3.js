
const shoes = [
    { type: 'shoe', color: 'blue', size: 9, price: 50 },
    { type: 'shoe', color: 'black', size: 8, price: 60 }
];

const shirts = [
    { type: 'shirt', color: 'blue', size: 'M', price: 25 },
    { type: 'shirt', color: 'white', size: 'L', price: 30 },
    { type: 'shirt', color: 'red', size: 'S', price: 20 }
];
const warehouse = [...shoes, ...shirts];

const totalWorth = warehouse.reduce((total, product) => total + product.price, 0);

warehouse.sort((a, b) => b.price - a.price);

const blueProducts = warehouse.filter(product => product.color === 'blue');

console.log("Total worth of products stored in the warehouse:", totalWorth);
console.log("Warehouse products sorted in descending order of prices:", warehouse);
console.log("Warehouse products which are blue in color:", blueProducts);
