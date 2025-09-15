package pepero.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs over a period of time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy HHmm");

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
            return "[E][X]";
        } else {
            return "[E][ ]";
        }
    }

    @Override
    public void updateTask(String details) {
        String[] parts = details.split(" (?=/)");

        for (String part : parts) {
            if (part.startsWith("/from")) {
                this.from = LocalDateTime.parse(part.substring(5).trim(), formatter);
            } else if (part.startsWith("/to")) {
                this.to = LocalDateTime.parse(part.substring(3).trim(), formatter);
            } else if (!part.isBlank()) {
                this.description = part.trim();
            }
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

