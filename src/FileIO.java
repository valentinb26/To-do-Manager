import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileIO {
    private final Path dataDirectory;
    private final Path dataFile;
    
public FileIO() {
  this.dataDirectory = Paths.get(
      System.getProperty("user.home"),
      "To-do-manager",
      "Data"
  );
  this.dataFile = dataDirectory.resolve("todoList.txt");
}

// import the to-do list from the data file
// format: description;owner;isCompleted
// split by semicolon and parse accordingly
public void importList(){
     try {
      Scanner myReader = new Scanner(dataFile);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] parts = data.split(";");
        String description = parts[0];
        String owner = parts[1];
        boolean isCompleted = Boolean.parseBoolean(parts[2]);

        // todo: disconnect FileIO from App class
        App.toDoList.add(new Task(description, owner, isCompleted));
      }
      myReader.close();
    } catch (Exception e) {
        ensureDataFileExists();
        System.out.println("File not found. A new file has been created.");
      }
}

// export the to-do list to the data file
// format: description;owner;isCompleted
// separated by semicolon and parsed accordingly
public void exportList(){ 
  ensureDataFileExists();
  try {
      FileWriter myWriter = new FileWriter(dataFile.toFile());

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
      System.out.println("An error occurred while writing to the file.");
      e.printStackTrace();
    }
}

// ensure that the data file and its parent directories exist
// if not, create them 
// called before export operation and if import operation fails
private void ensureDataFileExists() {
  try {
    Files.createDirectories(dataDirectory);
    if (Files.notExists(dataFile)) {
        Files.createFile(dataFile);
    }
  } catch (Exception e) {
    System.out.println("An error occurred while creating the data file.");
    e.printStackTrace();
    }
  }

}