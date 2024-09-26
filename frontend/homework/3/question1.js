function tipCalculator(bills) {
    const tips = [];
    const finalAmounts = [];

    for (let bill of bills) {
        let tipPercentage;

        if (bill < 50) {
            tipPercentage = 0.2;
        } else if (bill >= 50 && bill <= 200) {
            tipPercentage = 0.15;
        } else {
            tipPercentage = 0.1;
        }

        const tip = bill * tipPercentage;
        const finalAmount = bill + tip;

        tips.push(tip);
        finalAmounts.push(finalAmount);
    }

    return [tips, finalAmounts];
}

const bills = [140, 45, 280];
const [tips, finalAmounts] = tipCalculator(bills);

console.log("Tips:", tips);
console.log("Final Amounts:", finalAmounts);
