package pepero.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        taskCount++;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }


    @Override
    public String getStatusIcon() {
        if (isDone) {
            return("[D][X]");
        } else {
            return("[D][ ]");
        }
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription() + " (by: " + by.format(DateTimeFormatter.ofPattern("ddMMyy HHmm")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("ddMMyy HHmm"));
    }
}
