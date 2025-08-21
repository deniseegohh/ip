public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        taskCount++;
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
}
