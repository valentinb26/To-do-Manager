import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static String[] validCMD = {"HELP","USER","CREATE","DELETE","SHOW","EXIT"};

    public static ArrayList<String> toDoList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        FileIO fileIO = new FileIO();
        Scanner inputScanner = new Scanner(System.in);
        boolean var = true;
        String SEPERATOR = "#################################################################";

        while (var) {
        fileIO.importList();
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
                        break;
                    case "USER":
                        System.out.println("WIP");
                        break;
                    case "CREATE":
                        System.out.println("Sure! Please enter the Name of your task.");
                        String userInputTodoName = inputScanner.nextLine();
                        toDoList.add(userInputTodoName);
                        for (int j = 0; j < toDoList.size(); j++) {
                            if (toDoList.get(j).equals(userInputTodoName) ) {
                                System.out.println("Succesfully added: " + toDoList.get(j));
                                fileIO.exportList();
                            }else if (j == toDoList.size()-1) {
                                System.out.println("Ups seems like there was a problem...");
                            }
                        }

                        break;
                    case "DELETE":
                        System.out.println("WIP");
                        break;  
                    case "SHOW":
                        System.out.println("Sure! Let me show you your to-do's:");
                        for (int j = 0; j < toDoList.size(); j++) {
                            System.out.println(j+1 + ". " + toDoList.get(j));
                        }
                        break;   
                    case "EXIT":
                        System.out.println("Goodbye");
                        var=false;
                    break; 
                    default:
                        break;
                }
                break;
            }else if (i == validCMD.length-1) {
                System.out.println("Sorry I can't help you with that.");
                var = false;
            } 
        }
        }

       
     inputScanner.close();
    }
}