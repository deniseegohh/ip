import java.util.ArrayList;
import java.util.Scanner;

public class Pepero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Pepero! \nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            try {
                String input = sc.nextLine();
                String[] parts = input.split(" ", 2);
                String command = parts[0];
                System.out.println("____________________________________________________________");

                if (command.equals("bye")) {
                    System.out.println("Bye bye!~ Hope to see you again soon~");
                    System.out.println("____________________________________________________________");
                    break;

                } else if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println(i + "." + tasks.get(i - 1).toString());
                    }
                    System.out.println("____________________________________________________________");
                } else if (command.equals("mark")) {
                    int number = Integer.parseInt(parts[1]);
                    Task task = tasks.get(number - 1);
                    task.markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task.getStatusIcon() + " " + task.getDescription());
                    System.out.println("____________________________________________________________");
                } else if (command.equals("unmark")) {
                    int number = Integer.parseInt(parts[1]);
                    Task task = tasks.get(number - 1);
                    task.unmarkDone();
                    System.out.println("Okay, I've marked this task as not done yet:");
                    System.out.println(task.getStatusIcon() + " " + task.getDescription());
                    System.out.println("____________________________________________________________");
                } else if (command.equals("todo")) {
                    if (parts.length < 2) {
                        throw new PeperoException("description of todo empty :(");
                    }
                    ToDo task = new ToDo(parts[1]);
                    tasks.add(task);
                    System.out.println("added: ");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + task.getTaskCount() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (command.equals("deadline")) {
                    if (parts.length < 2) {
                        throw new PeperoException("description of deadline empty :(");
                    }
                    String deadlineParts = parts[1];
                    String[] otherPart = deadlineParts.split("/by", 2);
                    String description = otherPart[0];
                    String deadline = otherPart[1];
                    Deadline task = new Deadline(description, deadline);
                    tasks.add(task);
                    System.out.println("added:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + task.getTaskCount() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (command.equals("event")) {
                    if (parts.length < 2) {
                        throw new PeperoException("description of event empty :(");
                    }
                    String eventParts = parts[1];
                    String[] otherPart = eventParts.split("/", 3);
                    String description = otherPart[0];
                    String from = otherPart[1].split(" ", 2)[1];
                    String to = otherPart[2].split(" ", 2)[1];
                    Event task = new Event(description, from, to);
                    tasks.add(task);
                    System.out.println("added: ");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + task.getTaskCount() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (command.equals("delete")) {
                    int deletedTaskIndex = Integer.parseInt(parts[1]);
                    System.out.println("I will remove this task");
                    System.out.println(tasks.get(deletedTaskIndex - 1).toString());
                    tasks.remove(deletedTaskIndex - 1);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new PeperoException("I'm sorry I don't quite understand :(");
                }
            } catch (PeperoException e) {
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println(" OOPS!!! Task number must be an integer.");
                System.out.println("____________________________________________________________");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(" OOPS!!! Task number out of range.");
                System.out.println("____________________________________________________________");
            }
        }

        sc.close();
    }
}
