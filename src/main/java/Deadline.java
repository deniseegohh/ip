public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        taskCount++;
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
        return this.getStatusIcon() + " " + this.getDescription() + " (by: " + this.by + ")";
    }
}
