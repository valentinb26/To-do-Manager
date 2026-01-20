import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class App {

    public static String[] validCMD = {"HELP","USER","CREATE","DELETE","SHOW","EXIT"};
    public List<Task> toDoList = new ArrayList<>();
    static String SEPERATOR = "#################################################################";
    static Scanner inputScanner = new Scanner(System.in);

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
        String userTask = inputScanner.nextLine().toUpperCase(); 
        
        for (int i = 0; i < validCMD.length ; i++) {
            
            if (validCMD[i].equals(userTask)) {

                switch (userTask) {
                    case "HELP":
                        helpMenu();
                        break;
                    case "USER":
                    /* 
                    die idee ist dass man sich einloggen kann (möglichkeit mit hash zu arbeiten o.ä)
                    um mehrere listen und datein für mehrer nutzer hat (gast user als standard)
                    */
                        System.out.println("WIP");
                        break;
                    case "CREATE":
                        createTask();
                        fileIO.exportList(toDoList);
                        break;
                    case "DELETE":
                        deleteTask();
                        fileIO.exportList(toDoList);
                        running = continueCheck();
                        break;  
                    case "SHOW":
                        showList();
                        running = continueCheck();
                        break;   
                    case "EXIT":
                        // exit program
                        // cleanup not needed since cleanup happens after 
                        // switch but before while loop ends
                        System.out.println("Goodbye");
                        running = false;
                        break;     
                    default:
                        break;
                }
                break;
            }else if (i == validCMD.length-1) {
                //if command not recognized will run again
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

    // method to delete all tasks with the given description from the to-do list
    // might delete multiple tasks if they share the same description
    // todo: give each task a unique ID to delete specific tasks
    public void deleteFromList(String removeable){
        boolean removed = toDoList.removeIf(task -> task.getDescription().equals(removeable));
        if (removed) {
            System.out.println("Deleted  " + removeable + "  successfully");
        } else {
            System.out.println("Task not found: " + removeable);
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
        System.out.println("Please enter the description of the task you want to delete:");
        String description = inputScanner.nextLine();
        deleteFromList(description);
    }

}