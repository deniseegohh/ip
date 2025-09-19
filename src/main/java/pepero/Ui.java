package pepero;

import pepero.task.Task;
import pepero.task.TaskList;

import java.util.ArrayList;

/**
 * Handles printing messages to the console.
 */
public class Ui {

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
    }

    /**
     * Prints exit message when the program ends.
     *
     * @return a string representing exit message
     */
    public String printExit() {
        return "\uD83C\uDF6B Bye bye! Hope your day stays as sweet as Pepero! \uD83C\uDF6A";
    }

    /**
     * Prints all tasks in the list.
     *
     * @param tasks the TaskList object where tasks are stored and modified
     * @return a string representing list of tasks
     */
    public String printTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Here's a look at your tasks: \n");
        for (int i = 1; i <= tasks.getTasks().size(); i++) {
            sb.append(i).append(".").append(tasks.getTasks().get(i - 1)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints a message indicating that the given task has been marked as done.
     * dialogContainer.getChildren().add(
     * @param task the task object that has been marked as completed
     * @return a string representing message indicating given task has been marked as done
     */
    public String printMarkedTask(Task task) {
        return "✅ Sweet! This task is all done: \n"
                + task.getStatusIcon() + " " + task.getDescription() + "\n";
    }

    /**
     * Prints a message indicating that the given task has been marked as undone.
     *
     * @param task the task object that has been marked as incomplete
     * @return a string representing message indicating that the given task has been marked as undone
     */
    public String printUnmarkedTask(Task task) {
        return "\uD83C\uDF6A No worries! This task is still pending: \n"
                + task.getStatusIcon() + " " + task.getDescription() + "\n";
    }

    /**
     * Prints a message indicating that the given task has been added to the tasklist.
     *
     * @param task the task object that has been added
     * @return a string representing message indicating that the given task has been added to the tasklist
     */
    public String printAddedTask(Task task) {
        return "✨ Got it! Added this task: " + task;
    }

    /**
     * Prints a message indicating that the given task has been deleted from the tasklist.
     *
     * @param task the task object that has been deleted
     * @return a string representing message that given task has been deleted
     */
    public String printDeletedTask(Task task) {
        return "\uD83D\uDC94 Task removed: " + task;
    }

    /**
     * Prints a message indicating the number of tasks in the tasklist.
     *
     * @param tasks the TaskList object where tasks are stored and modified
     * @return a string representing message indicating number of tasks in the tasklist
     */
    public String printTaskCount(TaskList tasks) {
        return "Now you have " + tasks.getTasks().size() + " tasks to handle \uD83C\uDF6B";
    }

    /**
     * Prints a message indicating the tasks found containing the keyword.
     *
     * @param tasks the TaskList object where tasks are stored and modified
     * @return a string representing message indicating the tasks found containing the keyword
     */
    public String printFindResults(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks I found:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(i).append(".").append(tasks.get(i - 1)).append("\n");
        }
        return sb.toString();
    }

    public String printUpdatedTask(Task task) {
        return "✨ Nice! I’ve updated this task:\n" + task;
    }

}
