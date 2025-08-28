import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Pepero {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/pepero.txt");
        TaskList tasks = storage.load();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy HHmm");

        System.out.println("Loaded tasks:");
        for (Task task : tasks.getTasks()) {
            System.out.println(task);
        }

        ui.printWelcome();

        label:
        while (true) {
            try {
                String input = ui.readInput();
                String[] parts = input.split(" ", 2);
                String command = parts[0];
                System.out.println("____________________________________________________________");

                switch (command) {
                    case "bye":
                        storage.save(tasks);
                        ui.printExit();
                        break label;

                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 1; i <= tasks.getTasks().size(); i++) {
                            System.out.println(i + "." + tasks.getTasks().get(i - 1).toString());
                        }
                        System.out.println("____________________________________________________________");
                        break;
                    case "mark": {
                        int number = Integer.parseInt(parts[1]);
                        Task task = tasks.getTasks().get(number - 1);
                        task.markDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task.getStatusIcon() + " " + task.getDescription());
                        System.out.println("____________________________________________________________");
                        break;
                    }
                    case "unmark": {
                        int number = Integer.parseInt(parts[1]);
                        Task task = tasks.getTasks().get(number - 1);
                        task.unmarkDone();
                        System.out.println("Okay, I've marked this task as not done yet:");
                        System.out.println(task.getStatusIcon() + " " + task.getDescription());
                        System.out.println("____________________________________________________________");
                        break;
                    }
                    case "todo": {
                        if (parts.length < 2) {
                            throw new PeperoException("description of todo empty :(");
                        }
                        ToDo task = new ToDo(parts[1]);
                        tasks.addTask(task);
                        break;
                    }
                    case "deadline": {
                        if (parts.length < 2) {
                            throw new PeperoException("description of deadline empty :(");
                        }
                        String deadlineParts = parts[1];
                        String[] otherPart = deadlineParts.split("/by ", 2);
                        String description = otherPart[0];
                        String deadline = otherPart[1];
                        Deadline task = new Deadline(description, LocalDateTime.parse(deadline, formatter));
                        tasks.addTask(task);
                        break;
                    }
                    case "event": {
                        if (parts.length < 2) {
                            throw new PeperoException("description of event empty :(");
                        }
                        String eventParts = parts[1];
                        String[] otherPart = eventParts.split("/", 3);
                        String description = otherPart[0];
                        String from = otherPart[1].split(" ", 2)[1];
                        String to = otherPart[2].split(" ", 2)[1];
                        Event task = new Event(description, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to,formatter));
                        tasks.addTask(task);
                        break;
                    }
                    case "delete":
                        int deletedTaskIndex = Integer.parseInt(parts[1]);
                        tasks.deleteTask(deletedTaskIndex);
                        break;
                    default:
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ui.exit();
    }
}
