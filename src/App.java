import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static String[] validCMD = {"HELP","USER","CREATE","DELETE","SHOW","EXIT"};
    //auf jedenfall String durch ganze richtige Objekte ersetzen
    public static ArrayList<String> toDoList = new ArrayList<>();
    static boolean var = true;
    static String SEPERATOR = "#################################################################";
    static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        FileIO fileIO = new FileIO();
        fileIO.importList();
        
        while (var) {

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
                        continueCheck();
                        break;
                    case "USER":
                    /* 
                    die idee ist dass man sich einloggen kann (möglichkeit mit hash zu arbeiten o.ä)
                    um mehrere listen und datein für mehrer nutzer hat (gast user als standard)
                    */
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
                        //frag ob man noch mehr erstellen will
                        continueCheck();
                        break;

                    case "DELETE":
                        System.out.println("Sure! Which following task would you like to delete?");
                        showList();
                        String userInputDelete = inputScanner.nextLine();
                        deleteFromList(userInputDelete);
                        fileIO.exportList();
                        //frag ob man noch mehr löschen will
                        continueCheck();
                        break;  

                    case "SHOW":
                        System.out.println("Sure! Let me show you your to-do's:");
                        showList();
                        continueCheck();
                        break;   
                    case "EXIT":
                        System.out.println("Goodbye");
                        var=false;
                        break; 
                    //einen sort case oder eine sort option in show einbauen    
                    default:
                        break;
                }
                break;
            }else if (i == validCMD.length-1) {
                System.out.println("Sorry I can't help you with that.");
                continueCheck();
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
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i).equals(removeable)) {
                toDoList.remove(i);
                if (!toDoList.contains(removeable)) {
                    System.out.println("Deleted  " + removeable + "  successfully");
                }else {
                    System.out.println("Ups seems like something went wrong while deleting...");
                } 
            }else if (i == toDoList.size()-1) {
                System.out.println("Hmm can't delete what I can't find...");
            }
        }
    }

    public static void continueCheck(){
        System.out.println("Would you like to continue ? Y/N");
        String answer = inputScanner.nextLine().toUpperCase();
        if (answer.equals("N")) {
            System.out.println("Ok Goodbye.");
            var = false;
        }else if (answer.equals("Y")) {
            var = true;
        } else {
            var = false;
        }
    }

}