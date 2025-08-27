public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        taskCount++;
    }

    public Event(String description, String from, String to, boolean isDone) {
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
        return this.getStatusIcon() + " " + this.getDescription() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + "-" + to;
    }
}

