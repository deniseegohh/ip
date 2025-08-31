package pepero.task;

/**
 * Represents a task without a specific duration or deadline.
 */
public class ToDo extends Task {

    /**
     * Constructs a new task with the given description.
     *
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
        taskCount++;
    }

    /**
     * Constructs a new task with the given description and completion status.
     *
     * @param description the description of the task
     * @param isDone the initial completion status of the task
     */
    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns the status icon for the task, showing "[T]" for ToDo
     * and "X" if done, " " if not done.
     *
     * @return a string representing the status of the task
     */
    @Override
    public String getStatusIcon() {
        if (isDone) {
            return("[T][X]");
        } else {
            return("[T][ ]");
        }
    }

    /**
     * Returns a string representation of the task for displaying to the user,
     * including the status icon and description.
     *
     * @return a string representing the task
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    /**
     * Returns a string representation of the task formatted for saving to a file,
     * including type, completion status and description.
     *
     * @return a string in the file storage format
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
