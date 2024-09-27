const input = document.getElementById("todo-input");
const addButton = document.getElementById("add-button");
const todoList = document.getElementById("todo-list");

addButton.addEventListener("click", function () {
    const inputValue = input.value.trim();
    if (inputValue !== "") {
        addItem(inputValue);
        input.value = "";
    }
});

function addItem(text) {
    const listItem = document.createElement("li");
    listItem.textContent = text;

    const deleteButton = document.createElement("button");
    deleteButton.textContent = "DELETE";
    deleteButton.addEventListener("click", function () {
        todoList.removeChild(listItem);
    });

    listItem.appendChild(deleteButton);
    todoList.appendChild(listItem);
}