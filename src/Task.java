/**
 * Represents a single task in the to-do list application.
 * <p>
 * Each Task has a description, an owner, and a completion status.
 * Tasks are designed to be mutable, allowing updates to description,
 * owner, and completion status during the application lifecycle.
 * <p>
 * This class is used by the App and FileIO classes for in-memory
 * management and persistence of tasks.
 */
public class Task {

    /**
     * A textual description of the task.
     * Represents the main content or action that needs to be done.
     */
    private String description;

    /**
     * Indicates whether the task has been completed.
     * True if completed, false otherwise.
     */
    private boolean isCompleted;

    /**
     * The owner or assignee of the task.
     * Can be used to differentiate responsibilities in multi-user setups.
     */
    private String owner;

    /**
     * Constructs a new Task with the given description, owner, and completion status.
     *
     * @param description the textual description of the task
     * @param owner the person responsible for the task
     * @param isCompleted initial completion status of the task
     */
    public Task(String description, String owner, boolean isCompleted) {
        this.description = description;
        this.owner = owner;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets or updates the description of the task.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the owner of the task.
     *
     * @return the task owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets or updates the owner of the task.
     *
     * @param owner the new owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Returns whether the task has been completed.
     *
     * @return true if completed, false otherwise
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Sets or updates the completion status of the task.
     *
     * @param completed true if the task is completed, false otherwise
     */
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    /**
     * Returns a string representation of the task for display purposes.
     * <p>
     * The format includes description, owner, and completion status.
     * Example: "Finish report (Owner: Alice, Completed: false)"
     *
     * @return formatted string representing the task
     */
    @Override
    public String toString() {
        return description + " (Owner: " + owner + ", Completed: " + isCompleted + ")";
    }
}