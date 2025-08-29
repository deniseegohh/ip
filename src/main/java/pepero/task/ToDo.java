package pepero.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        taskCount++;
    }

    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String getStatusIcon() {
        if (isDone) {
            return("[T][X]");
        } else {
            return("[T][ ]");
        }
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
