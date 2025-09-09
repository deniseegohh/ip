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

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
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
        assert(index >= 0 && index < tasks.size());
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

    public ArrayList<Task> findTasks(String keyword) {
        assert(keyword != null);
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                results.add(task);
            }
        }

        return results;
    }
}
