public class Task {

    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) {
            return("[X]");
        } else {
            return("[ ]");
        }
    }
}
