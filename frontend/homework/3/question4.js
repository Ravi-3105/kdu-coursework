const inputString = '{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}';

// Step 1: Convert input string to JSON object
const jsonObject = JSON.parse(inputString);

// Step 2: Convert values to uppercase (except for email)
for (let key in jsonObject) {
    if (key !== 'email' && typeof jsonObject[key] === 'string') {
        jsonObject[key] = jsonObject[key].toUpperCase();
    }
}
console.log(jsonObject);
// Step 3: Convert JSON object back to string without the "email" key-value pair
delete jsonObject.email; // Remove email property
const outputString = JSON.stringify(jsonObject);

console.log(outputString);
