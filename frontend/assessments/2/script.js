const messageInput = document.getElementById("inp");
const sellButton = document.getElementById("sell");
const buyButton = document.getElementById("buy");
const stock = document.getElementById("stock");
const price = document.getElementById('pnum');

const socket = io("http://localhost:3000");

function addMessage(from, message) {

    const element = document.createElement('div');
    element.className = 'element';
    element.innerText = from+":" + message;

    stock.appendChild(element);
}

buyButton.addEventListener("click", () => {
    const message = messageInput.value;

    socket.emit("message", message);
    addMessage("Buy", message);
});

socket.on('1',(data)=>{
    console.log('00');
    price.innerText = data;
});

sellButton.addEventListener("click", () => {
    const message = messageInput.value;

    socket.emit("message", message);
    addMessage("Sell", message);
});
socket.on("new-message", (message) => {
    addMessage("Sold", message)
})