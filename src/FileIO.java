import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileIO {
    private final Path dataDirectory;
    private final Path dataFile;
    
public FileIO() {
  this.dataDirectory = Paths.get(
      System.getProperty("user.home"),
      "to-do-manager",
      "data"
  );
  this.dataFile = dataDirectory.resolve("todoList.txt");
    }

public void importList(){
     try {
      File myObj = new File(dataFile.toString());
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] parts = data.split(";");
        String description = parts[0];
        String owner = parts[1];
        boolean isCompleted = Boolean.parseBoolean(parts[2]);
        App.toDoList.add(new Task(description, owner, isCompleted));
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
      FileWriter myWriter = new FileWriter(dataFile.toString());

      for (int i = 0; i < App.toDoList.size(); i++) {
        myWriter.write(
          App.toDoList.get(i).getDescription() + ";" + 
          App.toDoList.get(i).getOwner() + ";" + 
          App.toDoList.get(i).isCompleted() +
          System.lineSeparator());
      }
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}

public void CreateFile() {
  try {
    File directory = new File(dataDirectory.toString());
    if (directory.mkdir()) {
      System.out.println("Directory created: " + directory.getName());
    } else {
      System.out.println("Directory exists or could not be created.");
    }
  } catch (Exception e) {
    System.out.println("An error occurred while creating the directory.");
    e.printStackTrace();
  }
  try {
    File myObj = new File(dataFile.toString());
    if (myObj.createNewFile()) {
      System.out.println("File created: " + myObj.getName());
    } else {
      System.out.println("File exists.");
      }
    } catch (Exception e) {
      System.out.println("An error occurred while creating the file.");
      e.printStackTrace();
    }
  }

}