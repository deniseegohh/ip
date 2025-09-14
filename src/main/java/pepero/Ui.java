package pepero;

import pepero.task.Task;
import pepero.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

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
     * Prints welcome message when the program first starts.
     *
     * @return a string representing welcome message
     */
    public String printWelcome() {
        return "Hello! I'm Pepero.Pepero! \n What can I do for you?";
    }

    /**
     * Prints exit message when the program ends.
     *
     * @return a string representing exit message
     */
    public String printExit() {
        return "Bye bye!~ Hope to see you again soon~";
    }

    /**
     * Prints all tasks in the list.
     *
     * @param tasks the TaskList object where tasks are stored and modified
     * @return a string representing list of tasks
     */
    public String printTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 1; i <= tasks.getTasks().size(); i++) {
            sb.append(i).append(".").append(tasks.getTasks().get(i - 1)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints a message indicating that the given task has been marked as done.
     *
     * @param task the task object that has been marked as completed
     * @return a string representing message indicating given task has been marked as done
     */
    public String printMark(Task task) {
        return "Nice! I've marked this task as done: \n"
                + task.getStatusIcon() + " " + task.getDescription() + "\n";
    }

    /**
     * Prints a message indicating that the given task has been marked as undone.
     *
     * @param task the task object that has been marked as incomplete
     * @return a string representing message indicating that the given task has been marked as undone
     */
    public String printUnmark(Task task) {
        return "Okay, I've marked this task as not done yet: \n"
                + task.getStatusIcon() + " " + task.getDescription() + "\n";
    }

    /**
     * Prints a message indicating that the given task has been added to the tasklist.
     *
     * @param task the task object that has been added
     * @return a string representing message indicating that the given task has been added to the tasklist
     */
    public String printAddTask(Task task) {
        return "added: " + task;
    }

    /**
     * Prints a message indicating that the given task has been deleted from the tasklist.
     *
     * @param task the task object that has been deleted
     * @return a string representing message that given task has been deleted
     */
    public String printDeleteTask(Task task) {
        return "I will remove this task: " + task;
    }

    /**
     * Prints a message indicating the number of tasks in the tasklist.
     *
     * @param tasks the TaskList object where tasks are stored and modified
     * @return a string representing message indicating number of tasks in the tasklist
     */
    public String printTaskCount(TaskList tasks) {
        return "Now you have " + tasks.getTasks().size() + " tasks in the list.";
    }

    /**
     * Prints a message indicating the tasks found containing the keyword.
     *
     * @param tasks the TaskList object where tasks are stored and modified
     * @return a string representing message indicating the tasks found containing the keyword
     */
    public String printFindResults(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(i).append(".").append(tasks.get(i - 1)).append("\n");
        }
        return sb.toString();
    }

}
