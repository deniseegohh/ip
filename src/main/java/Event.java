public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        taskCount++;
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
}

