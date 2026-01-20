import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for a console-based To-Do list manager.
 * <p>
 * This class provides a command-line interface (CLI) for users to manage tasks.
 * Tasks can be created, displayed, and deleted. The application supports
 * persisting tasks to a file through the FileIO class.
 * <p>
 * The application is designed to be simple yet extendable for future functionality,
 * e.g., user management (USER command is currently WIP).
 * 
 * Key features:
 * - Command-based interface with HELP, CREATE, DELETE, SHOW, USER, EXIT commands.
 * - Tasks are stored in memory as a List of Task objects.
 * - Tasks are persisted between sessions via file import/export.
 * - Provides graceful handling of invalid user commands.
 * - Iterates through tasks using an Iterator to ensure ordered display.
 * 
 * Thread safety: This class is not thread-safe and should only be used in a single-threaded context.
 */
public class App {

    /**
     * In-memory list storing all tasks.
     * Uses ArrayList for efficient sequential access and iteration.
     */
    private List<Task> toDoList = new ArrayList<>();

    /**
     * Scanner instance used for reading user input from the console.
     * Initialized once to avoid resource leaks.
     */
    private final Scanner inputScanner = new Scanner(System.in);

    /**
     * Instance of FileIO for persisting tasks to and from a file.
     */
    private final FileIO fileIO = new FileIO();

    /**
     * Enumeration defining the set of valid commands the user can execute.
     */
    public enum Command {
        HELP,   // Show available commands
        USER,   // Placeholder for user management (WIP)
        CREATE, // Create a new task
        DELETE, // Delete an existing task
        SHOW,   // Display all tasks
        EXIT    // Exit the application
    }

    /**
     * Visual separator used for enhancing console readability.
     */
    private static final String SEPARATOR = "#################################################################";

    /**
     * Entry point for the application.
     *
     * @param args command-line arguments (not used)
     * @throws Exception if file import/export fails
     */
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    /**
     * Main application loop.
     * <p>
     * This method initializes the to-do list from file storage,
     * prompts the user for commands, executes the corresponding actions,
     * and persists any changes before exiting.
     *
     * @throws Exception if file operations fail
     */
    public void run() throws Exception {

        // Load previously saved tasks from file
        toDoList = fileIO.importList();

        boolean running = true;

        // Main command loop
        while (running) {
            System.out.println(SEPARATOR);
            System.out.println("Hello! What would you like to do? Type help if you need help");
            String userInput = inputScanner.nextLine().toUpperCase();

            Command command;

            // Attempt to parse the user input into a Command enum
            try {
                command = Command.valueOf(userInput);
            } catch (IllegalArgumentException e) {
                command = null; // Invalid command
            }

            // Execute action based on the parsed command
            switch (command) {
                case HELP -> helpMenu();
                case USER -> System.out.println("WIP"); // Placeholder for future user management
                case CREATE -> createTask();
                case DELETE -> deleteTask();
                case SHOW -> showList();
                case EXIT -> running = false;
                default -> {
                    // Handle invalid or unrecognized commands
                    System.out.println("Sorry I can't help you with that.");
                    running = continueCheck();
                }
            }
        }

        // Cleanup resources and save tasks to file before exiting
        inputScanner.close();
        fileIO.exportList(toDoList);
    }

    /**
     * Displays the current to-do list in the order tasks were added.
     * Uses an iterator for traversal to demonstrate standard Java iteration patterns.
     */
    public void showList() {
        System.out.println("Sure! Let me show you your to-do's:");
        Iterator<Task> iterator = toDoList.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            System.out.println(i + ". " + iterator.next());
            i++;
        }
    }

    /**
     * Prompts the user to decide whether to continue after an unrecognized command.
     *
     * @return true if the user wants to continue, false to exit
     */
    public boolean continueCheck() {
        System.out.println("Would you like to continue? Y/N");
        String answer = inputScanner.nextLine().trim().toUpperCase();
        return answer.equals("Y");
    }

    /**
     * Displays all available commands to the user.
     * Enumerates the Command enum to ensure automatic updates if commands are added or removed.
     */
    public void helpMenu() {
        System.out.println("Sure! Let me show you your possible commands: ");
        for (Command cmd : Command.values()) {
            System.out.println(cmd);
        }
    }

    /**
     * Creates a new Task object based on user input and adds it to the to-do list.
     * Prompts the user for a task description and owner.
     * Persists the updated list to file immediately.
     */
    public void createTask() {
        System.out.println("Let's create a new task!");
        System.out.println("Please enter the task description:");
        String description = inputScanner.nextLine();

        System.out.println("Please enter the task owner:");
        String owner = inputScanner.nextLine();

        Task newTask = new Task(description, owner, false);
        toDoList.add(newTask);
        fileIO.exportList(toDoList);
        System.out.println("Task created successfully: " + newTask);
    }

    /**
     * Deletes a task from the to-do list by matching the task description.
     * Shows the current list, asks for the task description to delete,
     * and updates file storage upon successful deletion.
     */
    public void deleteTask() {
        showList();
        System.out.println("Please enter the description of the task you want to delete:");
        String description = inputScanner.nextLine();

        // Remove task(s) matching the description
        boolean removed = toDoList.removeIf(task -> task.getDescription().equals(description));

        if (removed) {
            fileIO.exportList(toDoList);
            System.out.println("Deleted  " + description + "  successfully");
        } else {
            System.out.println("Task not found: " + description);
        }
    }
}