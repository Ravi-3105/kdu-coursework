document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('post-btn').addEventListener('click', function(event) {
        event.preventDefault();
        var postText = document.getElementById('post-input').value;
        if (postText.trim() !== '') {
            var postElement = document.createElement('div');
            postElement.className = 'post';
            postElement.innerHTML = `
                <img src="img/nit-icon.png" alt="Profile" width="50px" height="auto">
                <p>${postText}</p>
            `;
            console.log(postElement);
            document.getElementById('post').appendChild(postElement);
            document.getElementById('post-input').value = ''; 
        }
    });
});

function openChat() {
    // Create a chat box element
    const chatBox = document.createElement('div');
    chatBox.className = 'chat-box';
    chatBox.innerHTML = '<h3>Chat Box</h3><p>This is the chat box content.</p>';

    // Clear any existing content in the right pane
    const rightPane = document.getElementById('rightpane');
    rightPane.innerHTML = '';

    // Append the chat box to the right pane
    rightPane.appendChild(chatBox);
}
const myDiv = document.getElementById('msg');

// Add a click event listener to the div
myDiv.addEventListener('click', function() {
  alert('You clicked the div!');
});

// Add click event listener to the "Messages" div
const openChatButton = document.getElementById('msg');
openChatButton.addEventListener('click', openChat);