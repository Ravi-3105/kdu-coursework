function outputKeysAndValues(obj) {
    
    const keys = [];
    function getAllKeys(obj) {
        for (let key in obj) {
            keys.push(key);
            if (typeof obj[key] === 'object' && obj[key] !== null) {
                getAllKeys(obj[key]);
            }
        }
    }
    getAllKeys(obj);
    console.log("All the keys:", keys);

    const values = [];
    function getAllValues(obj) {
        for (let key in obj) {
            if (typeof obj[key] !== 'object' || obj[key] === null) {
                values.push(obj[key]);
            } else {
                getAllValues(obj[key]);
            }
        }
    }
    getAllValues(obj);
    console.log("All the values:", values);
}

const player = {
    firstName: "Leo",
    lastName: "Messi",
    address: {
        country: "Spain",
        city: "Barcelona",
    },
    careerInfo: {
        fcBarcelona: {
            appearances: 780,
            goals: {
                premierLeagueGoals: 590,
                championsLeagueGoals: 50,
            },
        },
    },
};

outputKeysAndValues(player);
