
const users = [
  { username: 'user1', password: 'password1' },
  { username: 'user2', password: 'password2' }
];
const username = document.getElementById('username').value;
const password = document.getElementById('password').value;

// Check if the provided credentials match any user
const user = users.find(u => u.username === username && u.password === password);

if (user) {
  // Redirect to the next page if credentials are correct
  window.location.href = 'main.html';
} else {
  // Display an error message if credentials are incorrect
  alert('Invalid username or password. Please try again.');
}
