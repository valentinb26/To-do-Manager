import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * FileIO handles persistent storage for the to-do list application.
 * <p>
 * This class manages importing and exporting Task objects to a flat text file.
 * The data is stored in the user's home directory under "To-do-manager/Data/todoList.txt".
 * Tasks are serialized in a simple semicolon-separated format:
 * <pre>
 * description;owner;isCompleted
 * </pre>
 * <p>
 * Responsibilities:
 * - Ensure that the data directory and file exist.
 * - Read tasks from a file and convert them into Task objects.
 * - Write a list of Task objects back to the file in a consistent format.
 * - Handle basic I/O exceptions gracefully.
 * <p>
 * Note:
 * - This class is designed for single-threaded access. Concurrent modification
 *   of the file from multiple processes is not handled.
 * - The exportList method overwrites the file each time it is called.
 */
public class FileIO {

    /**
     * Path to the directory where the to-do list data file is stored.
     */
    private final Path dataDirectory;

    /**
     * Path to the actual data file storing the serialized to-do list.
     */
    private final Path dataFile;

    /**
     * Constructor.
     * Initializes the dataDirectory and dataFile paths.
     * <p>
     * The data will be stored in:
     * <pre>
     * {user.home}/To-do-manager/Data/todoList.txt
     * </pre>
     */
    public FileIO() {
        this.dataDirectory = Paths.get(
            System.getProperty("user.home"),
            "To-do-manager",
            "Data"
        );
        this.dataFile = dataDirectory.resolve("todoList.txt");
    }

    /**
     * Imports the to-do list from the persistent file.
     * <p>
     * Each line in the file represents a task in the format:
     * <pre>
     * description;owner;isCompleted
     * </pre>
     * If the file or directory does not exist, they will be created automatically.
     *
     * @return a List of Task objects read from the file; may be empty if file does not exist
     */
    public List<Task> importList() {
        List<Task> importedList = new ArrayList<>();

        try {
            Scanner myReader = new Scanner(dataFile);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(";");
                String description = parts[0];
                String owner = parts[1];
                boolean isCompleted = Boolean.parseBoolean(parts[2]);
                Task task = new Task(description, owner, isCompleted);
                importedList.add(task);
            }

            myReader.close();

        } catch (Exception e) {
            ensureDataFileExists();
            System.out.println("File not found. A new file has been created.");
            e.printStackTrace();
        }

        return importedList;
    }

    /**
     * Exports a list of Task objects to the persistent data file.
     * <p>
     * Each task is written in a single line with fields separated by semicolons:
     * <pre>
     * description;owner;isCompleted
     * </pre>
     * The file is overwritten each time this method is called.
     *
     * @param exportedList the list of Task objects to persist
     */
    public void exportList(List<Task> exportedList) {
        ensureDataFileExists();

        try {
            FileWriter myWriter = new FileWriter(dataFile.toFile());

            for (Task task : exportedList) {
                myWriter.write(
                    task.getDescription() + ";" +
                    task.getOwner() + ";" +
                    task.isCompleted() +
                    System.lineSeparator()
                );
            }

            myWriter.close();
            System.out.println("## Successfully wrote to the file. ##");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    /**
     * Ensures that the data directory and data file exist.
     * <p>
     * This method creates the directory and file if they do not already exist.
     * Called automatically before exporting or if importing fails due to a missing file.
     */
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