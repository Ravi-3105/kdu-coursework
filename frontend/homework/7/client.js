const messageInput = document.getElementById("msgInput");
const sendButton = document.getElementById("sendMessage");
const messageOutput = document.getElementById("messages");

const socket = io("http://localhost:3000");

function addMessage(from, message) {
    const fix = document.createElement('div');
    fix.className = 'fix';

    const circle = document.createElement('div');
    circle.className = 'circle';

    if (from === 'You') {
        circle.textContent = 'Y';
        circle.classList.add('you-circle');
    } else if (from === 'User') {
        circle.textContent = 'U';
        circle.classList.add('user-circle');
    }

    const element = document.createElement('div');
    element.className = 'element';
    element.innerText = ":" + message;

    fix.appendChild(circle);
    fix.appendChild(element);
    messageOutput.appendChild(fix);
}



sendButton.addEventListener("click", () => {
    const message = messageInput.value;

    socket.emit("message", message);
    addMessage("You", message);
});
socket.on("new-message", (message) => {
    addMessage("User", message)
})