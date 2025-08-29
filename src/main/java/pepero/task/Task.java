package pepero.task;

public class Task {

    protected final String description;
    protected boolean isDone;
    protected static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public int getTaskCount() {return taskCount;}

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

    public String toString() {
        return "";
    }

    public String toFileFormat() {return "";}
}
