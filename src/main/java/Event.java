import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        taskCount++;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    @Override
    public String getStatusIcon() {
        if (isDone) {
            return("[E][X]");
        } else {
            return("[E][ ]");
        }
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription() + " (from: " + from.format(DateTimeFormatter.ofPattern("ddMMyy HHmm")) + " to: " + to.format(DateTimeFormatter.ofPattern("ddMMyy HHmm")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(DateTimeFormatter.ofPattern("ddMMyy HHmm")) + "-" + to.format(DateTimeFormatter.ofPattern("ddMMyy HHmm"));
    }
}

