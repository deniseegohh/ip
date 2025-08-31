package pepero.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void deleteTask(int index) {;
        tasks.remove(index - 1);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                results.add(task);
            }
        }

        return results;
    }
}
