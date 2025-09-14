package pepero.task;

/**
 * Represents a generic task with a description and a completion status.
 */
public class Task {

    protected final String description;
    protected boolean isDone;
    protected static int taskCount = 0;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the total number of active tasks.
     *
     * @return the total task count
     */
    public int getTaskCount() {
        return taskCount;
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
     * Marks the task as completed
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Returns a string representing the completion status of the task.
     * "[X]" if done, "[ ]" if not done,
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Returns a string representation of the task for displaying ot the user.
     *
     * @return a string representing the task
     */
    public String toString() {
        return "";
    }

    /**
     * Returns a string representation of the task formatted for saving to a file.
     *
     * @return a string in the file storage format
     */
    public String toFileFormat() {
        return "";
    }
}
