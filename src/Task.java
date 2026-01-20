public class Task {
    private String description;
    private boolean isCompleted;
    private String owner;

    public Task(String description, String owner, boolean isCompleted) {
        this.description = description;
        this.owner = owner;
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return description + " (Owner: " + owner + ", Completed: " + isCompleted + ")";
    }
}