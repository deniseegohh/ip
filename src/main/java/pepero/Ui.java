package pepero;

import pepero.task.Task;
import pepero.task.TaskList;

import java.util.Scanner;

/**
 * Handles all interactions with the user, including reading input from
 * the console and printing messages to the console.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a new Ui object and initialises scanner for reading user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message when the program first starts.
     */
    public void printWelcome() {
        printLine();
        System.out.println("Hello! I'm Pepero.Pepero! \nWhat can I do for you?");
        printLine();
    }

    /**
     * Prints line in between program messages and user inputs.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints exit message when the program ends.
     */
    public void printExit() {
        System.out.println("Bye bye!~ Hope to see you again soon~");
        printLine();
    }

    /**
     * Prints all tasks in the list.
     * @param tasks the TaskList object where tasks are stored and modified
     */
    public void printTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.getTasks().size(); i++) {
            System.out.println(i + "." + tasks.getTasks().get(i - 1).toString());
        }
        printLine();
    }

    /**
     * Prints a message indicating that the given task has been marked as done.
     * @param task the task object that has been marked as completed
     */
    public void printMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.getStatusIcon() + " " + task.getDescription());
        printLine();
    }

    /**
     * Prints a message indicating that the given task has been marked as undone.
     * @param task the task object that has been marked as incomplete
     */
    public void printUnmark(Task task) {
        System.out.println("Okay, I've marked this task as not done yet:");
        System.out.println(task.getStatusIcon() + " " + task.getDescription());
        printLine();
    }

    /**
     * Prints a message indicating that the given task has been added to the tasklist.
     * @param task the task object that has been added
     */
    public void printAddTask(Task task) {
        System.out.println("added: ");
        System.out.println(task);
        printLine();
    }

    /**
     * Prints a message indicating that the given task has been deleted from the tasklist.
     * @param task the task object that has been deleted
     */
    public void printDeleteTask(Task task) {
        System.out.println("I will remove this task:");
        System.out.println(task);
        printLine();
    }

    /**
     * Prints a message indicating the number of tasks in the tasklist.
     * @param tasks the TaskList object where tasks are stored and modified
     */
    public void printTaskCount(TaskList tasks) {
        System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
        printLine();
    }

    /**
     * Reads a line of input from the user.
     * @return the next line of input entered by the user
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Closes the scanner and releases the associated system resources.
     * Should be called when the program no longer needs to read user input.
     */
    public void exit() {
        sc.close();
    }
}
