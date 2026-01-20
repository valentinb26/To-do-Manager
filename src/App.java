import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class App {
    public List<Task> toDoList = new ArrayList<>();
    static Scanner inputScanner = new Scanner(System.in);

    public static String[] validCMD = {"HELP","USER","CREATE","DELETE","SHOW","EXIT"};
    public enum Command {
        HELP,
        USER,
        CREATE,
        DELETE,
        SHOW,
        EXIT
    }
    static String SEPERATOR = "#################################################################";

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }
       
    public void run() throws Exception {
        FileIO fileIO = new FileIO();
        toDoList = fileIO.importList();
        
        boolean running = true;

        while (running) {

            System.out.println(SEPERATOR);
            System.out.println("Hello! What would you like to do? Type help if you need help");
            String userInput = inputScanner.nextLine().toUpperCase(); 

            Command command;

            try {
                command = Command.valueOf(userInput);
            } catch (IllegalArgumentException e) {
                command = null;
            }

            switch (command) {
                case HELP -> helpMenu();
                case USER -> System.out.println("WIP");
                case CREATE -> createTask();
                case DELETE -> deleteTask();
                case SHOW -> showList();
                case EXIT -> running = false;
                default -> {
                    System.out.println("Sorry I can't help you with that.");
                    running = continueCheck();
                }
            }
        }
        //cleanup before exit
        inputScanner.close();
        fileIO.exportList(toDoList);
    }

    // method to show the to-do list using an iterator
    // order depends on when tasks were added (1. is oldest)
    public void showList(){
        System.out.println("Sure! Let me show you your to-do's:");
        Iterator<Task> iterator = toDoList.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            System.out.println(i + ". " + iterator.next());
            i++;
        }
    }

    // method to ask the user if they want to continue
    // user will start over if they type Y, otherwise the program will exit
    public boolean continueCheck() {
        System.out.println("Would you like to continue? Y/N");
        String answer = inputScanner.nextLine().trim().toUpperCase();

        return answer.equals("Y");
    }

    public void helpMenu() {
        System.out.println("Sure! Let me show you your possible commands: ");
        for (int j = 0; j < validCMD.length; j++) {
            System.out.println(validCMD[j]);
        }
    }

    public void createTask() {
        System.out.println("Let's create a new task!");
        System.out.println("Please enter the task description:");
        String description = inputScanner.nextLine();

        System.out.println("Please enter the task owner:");
        String owner = inputScanner.nextLine();

        Task newTask = new Task(description, owner, false);
        toDoList.add(newTask);
        System.out.println("Task created successfully: " + newTask);
    }

    public void deleteTask() {
        showList();
        System.out.println("Please enter the description of the task you want to delete:");
        String description = inputScanner.nextLine();
        boolean removed = toDoList.removeIf(task -> task.getDescription().equals(description));
        if (removed) {
            System.out.println("Deleted  " + description + "  successfully");
        } else {
            System.out.println("Task not found: " + description);
        }
    }

}