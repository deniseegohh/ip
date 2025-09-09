package pepero;

import pepero.task.Task;
import pepero.task.TaskList;
import pepero.task.ToDo;
import pepero.task.Deadline;
import pepero.task.Event;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy HHmm");

    /**
     * Parses a user input string and executes the corresponding command on the task list.
     * @param input user input string to parse
     * @param tasks the TaskList object where tasks are stored and modified
     * @param ui the Ui object used that handles interactions with the user
     * @param storage the Storage object used to save the tasks to persistent storage
     * @throws PeperoException if the input is invalid or a required description is missing
     * @throws IOException if saving tasks to storage fails
     */
    public static void parse(String input, TaskList tasks, Ui ui, Storage storage) throws PeperoException, IOException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        assert(command != null);


        switch (command) {
        case "bye": {
            storage.save(tasks);
            ui.printExit();
            break;
        }

        case "list": {
            ui.printTaskList(tasks);
            break;
        }

        case "mark": {
            int markIndex = Integer.parseInt(parts[1]);
            assert(markIndex >= tasks.getTasks().size());
            Task task = tasks.getTasks().get(markIndex - 1);
            task.markDone();
            ui.printMark(task);
            break;
        }

        case "unmark": {
            int unmarkIndex = Integer.parseInt(parts[1]);
            assert(unmarkIndex >= tasks.getTasks().size());
            Task task = tasks.getTasks().get(unmarkIndex - 1);
            task.unmarkDone();
            ui.printUnmark(task);
            break;
        }

        case "todo": {
            if (parts.length < 2) {
                throw new PeperoException("description of todo empty :(");
            }
            assert(parts[1] != null);
            ToDo task = new ToDo(parts[1]);
            tasks.addTask(task);
            ui.printAddTask(task);
            break;
        }

        case "deadline": {
            if (parts.length < 2) {
                throw new PeperoException("description of deadline empty :(");
            }
            String deadlineParts = parts[1];
            String[] otherPart = deadlineParts.split("/by ", 2);
            assert(otherPart.length == 2);
            String description = otherPart[0];
            String deadline = otherPart[1];
            Deadline task = new Deadline(description, LocalDateTime.parse(deadline, formatter));
            tasks.addTask(task);
            ui.printAddTask(task);
            break;
        }

        case "event": {
            if (parts.length < 2) {
                throw new PeperoException("description of event empty :(");
            }
            String eventParts = parts[1];
            String[] otherPart = eventParts.split("/", 3);
            assert(otherPart.length == 3);
            String description = otherPart[0];
            String from = otherPart[1].split(" ", 2)[1];
            String to = otherPart[2].split(" ", 2)[1];
            Event task = new Event(description, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter));
            tasks.addTask(task);
            ui.printAddTask(task);
            break;
        }

        case "delete": {
            int deleteIndex = Integer.parseInt(parts[1]);
            assert(deleteIndex >= tasks.getTasks().size());
            Task deletedTask = tasks.getTasks().get(deleteIndex - 1);
            ui.printDeleteTask(deletedTask);
            tasks.deleteTask(deleteIndex);
            ui.printTaskCount(tasks);
            break;
        }

            case "find": {
                if (parts.length < 2) {
                    throw new PeperoException("description of find empty :(");
                }
                String keyword = parts[1];
                assert(keyword != null);
                ui.printFindResults(tasks.findTasks(keyword));
                break;
            }

            default: {
                throw new PeperoException("I'm sorry I don't quite understand :(");
            }
        }
    }

    public static String parseAndReturn(String input, TaskList tasks, Storage storage) throws PeperoException, IOException {
        StringBuilder response = new StringBuilder();
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "bye":
            storage.save(tasks);
            response.append("Bye bye!~ Hope to see you again soon~");
            break;

        case "list":
            response.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                response.append((i + 1) + ". " + tasks.getTasks().get(i)).append("\n");
            }
            break;

        case "mark": {
            int markIndex = Integer.parseInt(parts[1]);
            assert(markIndex >= tasks.getTasks().size());
            Task task = tasks.getTasks().get(markIndex - 1);
            task.markDone();
            response.append("Nice! I've marked this task as done:\n").append(task);
            break;
        }

        case "unmark": {
            int unmarkIndex = Integer.parseInt(parts[1]);
            assert(unmarkIndex >= tasks.getTasks().size());
            Task task = tasks.getTasks().get(unmarkIndex - 1);
            task.unmarkDone();
            response.append("OK, I've marked this task as not done yet:\n").append(task);
            break;
        }

        case "todo": {
            if (parts.length < 2) {
                throw new PeperoException("description of todo empty :(");
            }
            assert(parts[1] != null);
            ToDo task = new ToDo(parts[1]);
            tasks.addTask(task);
            response.append("Added task:\n").append(task).append("\n")
                    .append("Now you have ").append(tasks.getTasks().size()).append(" tasks in the list.");
            break;
        }

        case "deadline": {
            if (parts.length < 2) {
                throw new PeperoException("description of deadline empty :(");
            }
            String[] otherPart = parts[1].split("/by ", 2);
            assert(otherPart.length == 2);
            String description = otherPart[0];
            String deadline = otherPart[1];
            Deadline task = new Deadline(description, LocalDateTime.parse(deadline, formatter));
            tasks.addTask(task);
            response.append("Added task:\n").append(task).append("\n")
                    .append("Now you have ").append(tasks.getTasks().size()).append(" tasks in the list.");
            break;
        }

        case "event": {
            if (parts.length < 2) {
                throw new PeperoException("description of event empty :(");
            }
            String[] otherPart = parts[1].split("/", 3);
            assert(otherPart.length == 3);
            String description = otherPart[0];
            String from = otherPart[1].split(" ", 2)[1];
            String to = otherPart[2].split(" ", 2)[1];
            Event task = new Event(description, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter));
            tasks.addTask(task);
            response.append("Added task:\n").append(task).append("\n")
                    .append("Now you have ").append(tasks.getTasks().size()).append(" tasks in the list.");
            break;
        }

        case "delete": {
            int deleteIndex = Integer.parseInt(parts[1]);
            if (deleteIndex > tasks.getTasks().size()) {
                throw new PeperoException("task " + deleteIndex + " does not exist :(");
            }
            assert(deleteIndex >= tasks.getTasks().size());
            Task deletedTask = tasks.getTasks().get(deleteIndex - 1);
            tasks.deleteTask(deleteIndex);
            response.append("Noted. I've removed this task:\n").append(deletedTask).append("\n")
                    .append("Now you have ").append(tasks.getTasks().size()).append(" tasks in the list.");
            break;
        }

        case "find": {
            if (parts.length < 2) {
                throw new PeperoException("description of find empty :(");
            }
            String keyword = parts[1];
            assert(keyword != null);
            ArrayList<Task> found = tasks.findTasks(keyword);
            response.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < found.size(); i++) {
                response.append((i + 1) + ". " + found.get(i)).append("\n");
            }
            break;
        }

        default:
            throw new PeperoException("I'm sorry I don't quite understand :(");
        }

        return response.toString().trim();
    }

}
