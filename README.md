# To-Do Manager

A console-based To-Do List Manager written in Java.  
Allows users to create, view, and manage tasks. Tasks are saved to a file so they persist across sessions. The application is lightweight, modular, and easy to extend.

---

## Features

- Add new tasks with description and owner
- View tasks in the order they were added
- Delete tasks by description
- Persist tasks to a file (`todoList.txt`) in the user's home directory
- Simple command-line interface
- Modular design with `App`, `Task`, and `FileIO` classes
- Prepared for future extensions (e.g., multi-user support)

---

## Installation

1. Clone the repository:

git clone https://github.com/your-username/todo-manager.git


2. Navigate into the project directory:

cd todo-manager


3. Compile the Java files:

javac App.java Task.java FileIO.java


---

## Usage

Run the application:

java App


When prompted, enter one of the available commands:

- **HELP**: Display all available commands  
- **CREATE**: Add a new task (enter description and owner)  
- **DELETE**: Remove a task by description  
- **SHOW**: Display all tasks  
- **USER**: Work in progress (future multi-user support)  
- **EXIT**: Save tasks and exit the application  

Example workflow:

CREATE
Enter task description: Finish project report
Enter task owner: Alice
Task created successfully: Finish project report (Owner: Alice, Completed: false)

SHOW

Finish project report (Owner: Alice, Completed: false)

DELETE
Enter task description: Finish project report
Deleted Finish project report successfully


---

## File Storage

- Data file path: `{user.home}/To-do-manager/Data/todoList.txt`
- Task format: `description;owner;isCompleted`
- Automatically creates directories and file if missing

---

## Classes

- **App**: Main class, handles user input, command parsing, and task management  
- **Task**: Represents a single task with description, owner, and completion status  
- **FileIO**: Manages reading/writing tasks to the persistent file  

---

## Future Enhancements

- Multi-user task management  
- Task deadlines and priorities  
- Search and filter tasks  
- GUI version (JavaFX or Swing)  
- Unit testing with JUnit  


---

## License

MIT License. See the `LICENSE` file for details.

---

## Contact

- Author: valentin  
- GitHub: https://github.com/valentinb26