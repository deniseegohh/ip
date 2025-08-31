package pepero.task;

import java.util.ArrayList;

/**
 * Represents a collection of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param t the task to add
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes the task at the given 1-based index from the task list.
     *
     * @param index the 1-based index of the task to delete
     */
    public void deleteTask(int index) {;
        tasks.remove(index - 1);
    }

    /**
     * Returns the list of tasks.
     *
     * @return an ArrayList containing all tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
