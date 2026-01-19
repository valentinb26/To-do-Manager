import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static String[] validCMD = {"HELP","USER","CREATE","DELETE","SHOW","EXIT"};
    //auf jedenfall String durch ganze richtige Objekte ersetzen
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
                        Task newTask = new Task("","",false); //placeholder
                        System.out.println("Sure! Please enter the Name of your task.");
                        String userInputTaskDescription = inputScanner.nextLine();
                        newTask.setDescription(userInputTaskDescription);
                        System.out.println("Please enter the Owner of the task.");
                        String userInputTaskOwner = inputScanner.nextLine();
                        newTask.setOwner(userInputTaskOwner);
                        toDoList.add(newTask);

                        //todo: add check if sucessfull added task

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
                        System.out.println("Sure! Which following task would you like to delete?");
                        showList();
                        String userInputDelete = inputScanner.nextLine();
                        deleteFromList(userInputDelete);
                        fileIO.exportList();
                        //frag ob man noch mehr löschen will
                        running = continueCheck();
                        break;  

                    case "SHOW":
                        System.out.println("Sure! Let me show you your to-do's:");
                        
                        showList();
                        running = continueCheck();
                        break;   
                    case "EXIT":
                        System.out.println("Goodbye");
                        running = false;
                        break; 
                    //einen sort case oder eine sort option in show einbauen    
                    default:
                        break;
                }
                break;
            }else if (i == validCMD.length-1) {
                System.out.println("Sorry I can't help you with that.");
                running = continueCheck();
            } 
        }
        }

       
     inputScanner.close();
     fileIO.exportList();
    }

    public static void showList(){
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println(i+1 + ". " + toDoList.get(i));
        }
    }

    public static void deleteFromList(String removeable){
        boolean removed = toDoList.removeIf(task -> task.getDescription().equals(removeable));
        if (removed) {
            System.out.println("Deleted  " + removeable + "  successfully");
        } else {
            System.out.println("Task not found: " + removeable);
        }
    }

    public static boolean continueCheck() {
        System.out.println("Would you like to continue? Y/N");
        String answer = inputScanner.nextLine().trim().toUpperCase();

        return answer.equals("Y");
    }

}