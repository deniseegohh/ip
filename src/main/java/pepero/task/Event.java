package pepero.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs over a period of time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new task with the given description, start time and end time.
     *
     * @param description the description of the event
     * @param from the start date and time of the event
     * @param to the end date and time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        taskCount++;
    }

    /**
     * Constructs a new task with the given description, start time, end time and completion status.
     *
     * @param description the description of the event
     * @param from the start date and time of the event
     * @param to the end date and time of the event
     * @param isDone the initial completion status of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon for the event, showing "[E]" for event
     * and "X" if done, " " if not done.
     *
     * @return a string representing the status of the task
     */
    @Override
    public String getStatusIcon() {
        if (isDone) {
            return("[E][X]");
        } else {
            return("[E][ ]");
        }
    }

    /**
     * Returns a string representation of the event for displaying to the user,
     * including the status icon, description, start time and end time.
     *
     * @return a string representing the task
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription() + " (from: " + from.format(DateTimeFormatter.ofPattern("ddMMyy HHmm")) + " to: " + to.format(DateTimeFormatter.ofPattern("ddMMyy HHmm")) + ")";
    }

    /**
     * Returns a string representation of the event formatted for saving to a file,
     * including type, completion status, description, start time and end time.
     *
     * @return a string in the file storage format
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(DateTimeFormatter.ofPattern("ddMMyy HHmm")) + "-" + to.format(DateTimeFormatter.ofPattern("ddMMyy HHmm"));
    }
}

