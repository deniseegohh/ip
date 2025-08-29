package pepero;

import pepero.task.Task;
import pepero.task.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void printWelcome() {
        printLine();
        System.out.println("Hello! I'm Pepero.Pepero! \nWhat can I do for you?");
        printLine();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printExit() {
        System.out.println("Bye bye!~ Hope to see you again soon~");
        printLine();
    }

    public void printTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.getTasks().size(); i++) {
            System.out.println(i + "." + tasks.getTasks().get(i - 1).toString());
        }
        printLine();
    }

    public void printMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.getStatusIcon() + " " + task.getDescription());
        printLine();
    }

    public void printUnmark(Task task) {
        System.out.println("Okay, I've marked this task as not done yet:");
        System.out.println(task.getStatusIcon() + " " + task.getDescription());
        printLine();
    }

    public void printAddTask(Task task) {
        System.out.println("added: ");
        System.out.println(task);
        printLine();
    }

    public void printDeleteTask(Task task) {
        System.out.println("I will remove this task:");
        System.out.println(task);
        printLine();
    }

    public void printTaskCount(TaskList tasks) {
        System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
        printLine();
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void exit() {
        sc.close();
    }
}
