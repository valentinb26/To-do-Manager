import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileIO {
    
    String path = "/Users/v/Desktop/repo/toDoManger/To-do-Manager/toDoManager/Data/toDoList.txt";

public void importList(){
     try {
      File myObj = new File(path);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    System.out.println("imported");
}

public void exportList(){ 
    System.out.println("exported");
}

}
