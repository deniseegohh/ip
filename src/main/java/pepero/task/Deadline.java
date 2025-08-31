package pepero.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a new task with the given description and deadline.
     *
     * @param description the description of the task
     * @param by the date and time by which the task should be completed
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        taskCount++;
    }

    /**
     * Constructs a new task with the given description, deadline and completion status.
     *
     * @param description the description of the task
     * @param by the date and time by which the task should be completed
     * @param isDone the initial completion status of the task
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon for the task, showing "[D]" for deadline
     * and "X" if done, " " if not done.
     *
     * @return a string representing the status of the task
     */
    @Override
    public String getStatusIcon() {
        if (isDone) {
            return("[D][X]");
        } else {
            return("[D][ ]");
        }
    }

    /**
     * Returns a string representation of the task for displaying to the user,
     * including the status icon, description and deadline.
     *
     * @return a string representing the task
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription() + " (by: " + by.format(DateTimeFormatter.ofPattern("ddMMyy HHmm")) + ")";
    }

    /**
     * Returns a string representation of the task formatted for saving to a file,
     * including type, completion status, description and deadline.
     *
     * @return a string in the file storage format
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("ddMMyy HHmm"));
    }
}
