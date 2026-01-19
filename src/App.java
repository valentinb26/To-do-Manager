import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class App {

    public static String[] validCMD = {"HELP","USER","CREATE","DELETE","SHOW","EXIT"};
    public static ArrayList<Task> toDoList = new ArrayList<>();
    static String SEPERATOR = "#################################################################";
    static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        FileIO fileIO = new FileIO();
        fileIO.importList();
        
        boolean running = true;

        while (running) {

        System.out.println(SEPERATOR);
        System.out.println("Hello! What would you like to do? Type help if you need help");
        String userTask = inputScanner.nextLine().toUpperCase(); 
        
        for (int i = 0; i < validCMD.length ; i++) {
            
            if (validCMD[i].equals(userTask)) {

                switch (userTask) {
                    case "HELP":
                        System.out.println("Sure! Let me show you your possible commands: ");
                        for (int j = 0; j < validCMD.length; j++) {
                            System.out.println(validCMD[j]);
                        }
                        running = continueCheck();
                        break;
                    case "USER":
                    /* 
                    die idee ist dass man sich einloggen kann (möglichkeit mit hash zu arbeiten o.ä)
                    um mehrere listen und datein für mehrer nutzer hat (gast user als standard)
                    */
                        System.out.println("WIP");
                        break;
                    case "CREATE":
                        //create blank task, ask for description and owner, add to list
                        Task newTask = new Task("","",false); //placeholder
                        System.out.println("Sure! Please enter the Name of your task.");
                        String userInputTaskDescription = inputScanner.nextLine();
                        newTask.setDescription(userInputTaskDescription);
                        System.out.println("Please enter the Owner of the task.");
                        String userInputTaskOwner = inputScanner.nextLine();
                        newTask.setOwner(userInputTaskOwner);
                        toDoList.add(newTask);

                        //todo: add check if sucessfull added task
                        //todo: unique IDs for tasks

                        /* 
                        for (int j = 0; j < toDoList.size(); j++) {
                            if (toDoList.get(j).equals(userInputTaskDescription) ) {
                                System.out.println("Succesfully added: " + toDoList.get(j));
                                fileIO.exportList();
                            }else if (j == toDoList.size()-1) {
                                System.out.println("Ups seems like there was a problem...");
                            }
                        }
                            */
                        //frag ob man noch mehr erstellen will
                        running = continueCheck();
                        break;

                    case "DELETE":
                        // show list, ask which one to delete, delete from list
                        System.out.println("Sure! Which following task would you like to delete?");
                        showList();
                        String userInputDelete = inputScanner.nextLine();
                        deleteFromList(userInputDelete);
                        fileIO.exportList();
                        //inspiration: ask if wanting to delete more
                        running = continueCheck();
                        break;  

                    case "SHOW":
                        //display all tasks
                        System.out.println("Sure! Let me show you your to-do's:");
                        
                        showList();
                        running = continueCheck();
                        break;   
                        //inspiration: sort options
                    case "EXIT":
                        //exit program
                        System.out.println("Goodbye");
                        running = false;
                        break;     
                    default:
                        break;
                }
                break;
            }else if (i == validCMD.length-1) {
                //if command not recognized and run again
                System.out.println("Sorry I can't help you with that.");
                running = continueCheck();
            } 
        }
        }

    //cleanup before exit
     inputScanner.close();
     fileIO.exportList();
    }

    // method to show the to-do list using an iterator
    public static void showList(){
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
    public static void deleteFromList(String removeable){
        boolean removed = toDoList.removeIf(task -> task.getDescription().equals(removeable));
        if (removed) {
            System.out.println("Deleted  " + removeable + "  successfully");
        } else {
            System.out.println("Task not found: " + removeable);
        }
    }

    // method to ask the user if they want to continue
    // user will start over if they type Y, otherwise the program will exit
    public static boolean continueCheck() {
        System.out.println("Would you like to continue? Y/N");
        String answer = inputScanner.nextLine().trim().toUpperCase();

        return answer.equals("Y");
    }

}