import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
    
    String dataPath = "/Users/v/Desktop/Github Rep/To-do-Manager/Data";
    String dataName = "/toDoList.txt";
    String dataFolder= "/toDoList";
    String fullPath = dataPath+dataName;
    

public void importList(){
     try {
      File myObj = new File(dataPath+dataName);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        App.toDoList.add(data);
      }
      myReader.close();
    } catch (Exception e) {
      if (e instanceof IOException) {
        CreateFile();
        System.out.println("File not found. A new file has been created.");
      } else {  
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
}

public void exportList(){ 
  CreateFile();
  try {
      FileWriter myWriter = new FileWriter(fullPath);

      for (int i = 0; i < App.toDoList.size(); i++) {
        myWriter.write(App.toDoList.get(i) + System.lineSeparator());
      }
      myWriter.close();
      //System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}

public void CreateFile() {
  try {
    File directory = new File(dataPath);
    if (directory.mkdir()) {
      System.out.println("Directory created: " + directory.getName());
    } else {
      //System.out.println("Directory already exists or could not be created.");
    }
  } catch (Exception e) {
    System.out.println("An error occurred while creating the directory.");
    e.printStackTrace();
  }
  try {
    File myObj = new File(fullPath);
    if (myObj.createNewFile()) {
      System.out.println("File created: " + myObj.getName());
    } else {
      //System.out.println("File already exists.");
      }
    } catch (Exception e) {
      System.out.println("An error occurred while creating the file.");
      e.printStackTrace();
    }
  }

}