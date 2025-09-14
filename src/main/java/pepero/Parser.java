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
            for (int i = 0; i < tasks.getSize(); i++) {
                response.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
            }
            break;

        case "mark": {
            int markIndex = Integer.parseInt(parts[1]);
            assert(markIndex >= tasks.getSize());
            Task task = tasks.getTask(markIndex - 1);

            task.markDone();
            response.append("Nice! I've marked this task as done:\n").append(task);
            break;
        }

        case "unmark": {
            int unmarkIndex = Integer.parseInt(parts[1]);
            assert(unmarkIndex >= tasks.getSize());
            Task task = tasks.getTask(unmarkIndex - 1);

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

            response = printAddedTask(response, tasks, task);
            response = printNumberOfTasks(response, tasks);
            break;
        }

        case "deadline": {
            if (parts.length < 2) {
                throw new PeperoException("description of deadline empty :(");
            }

            String[] deadlineParts = parts[1].split("/by ", 2);
            assert(deadlineParts.length == 2);
            String description = deadlineParts[0];
            String deadline = deadlineParts[1];

            Deadline task = new Deadline(description, LocalDateTime.parse(deadline, formatter));
            tasks.addTask(task);

            response = printAddedTask(response, tasks, task);
            response = printNumberOfTasks(response, tasks);
            break;
        }

        case "event": {
            if (parts.length < 2) {
                throw new PeperoException("description of event empty :(");
            }

            String[] eventParts = parts[1].split("/", 3);
            assert(eventParts.length == 3);
            String description = eventParts[0];
            String from = eventParts[1].split(" ", 2)[1];
            String to = eventParts[2].split(" ", 2)[1];

            Event task = new Event(description, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter));
            tasks.addTask(task);

            response = printAddedTask(response, tasks, task);
            response = printNumberOfTasks(response, tasks);
            break;
        }

        case "delete": {
            int deleteIndex = Integer.parseInt(parts[1]);
            if (deleteIndex > tasks.getSize()) {
                throw new PeperoException("task " + deleteIndex + " does not exist :(");
            }
            assert(deleteIndex >= tasks.getSize());

            Task deletedTask = tasks.getTask(deleteIndex - 1);
            tasks.deleteTask(deleteIndex);

            response.append("Noted. I've removed this task:\n").append(deletedTask).append("\n");
            response = printNumberOfTasks(response, tasks);
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
                response.append((i + 1)).append(". ").append(found.get(i)).append("\n");
            }
            break;
        }

        default:
            throw new PeperoException("I'm sorry I don't quite understand :(");
        }

        return response.toString().trim();
    }

    /**
     * Prints a message indicating the number of tasks in the list.
     *
     * @param response the StringBuilder to append the message to
     * @param tasks the TaskList which size is displayed
     * @return the updated StringBuilder containing the message
     */
    private static StringBuilder printNumberOfTasks(StringBuilder response, TaskList tasks) {
        return response.append("Now you have ").append(tasks.getSize()).append(" tasks in the list.");
    }

    /**
     * Prints a message indicating that a task has been added to the list
     *
     * @param response the StringBuilder to append the message to
     * @param tasks the TaskList to which the task was added
     * @param task the Task that was added
     * @return the updated StringBuilder containing the message
     */
    private static StringBuilder printAddedTask(StringBuilder response, TaskList tasks, Task task) {
        return response.append("Added task:\n").append(task).append("\n");
    }

}
