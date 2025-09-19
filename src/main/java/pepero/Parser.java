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

    public static String parseAndReturn(String input, TaskList tasks, Storage storage, Ui ui) throws PeperoException, IOException {
        String response;
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "bye":
            storage.save(tasks);
            response = ui.printExit();
            break;

        case "list":
            response = ui.printTaskList(tasks);
            break;

        case "mark": {
            ensureHasArgument(parts, "Please specify a task number to mark :(");

            int markIndex = parseAndValidateIndex(parts[1], tasks.getSize(), "mark");
            Task task = tasks.getTask(markIndex);

            task.markDone();
            response = ui.printMarkedTask(task);
            break;
        }

        case "unmark": {
            ensureHasArgument(parts, "Please specify a task number to unmark :(");

            int unmarkIndex = parseAndValidateIndex(parts[1], tasks.getSize(), "unmark");
            Task task = tasks.getTask(unmarkIndex);

            task.unmarkDone();

            response = ui.printUnmarkedTask(task);
            break;
        }

        case "todo": {
            ensureHasArgument(parts, "description of todo empty :(");

            ToDo task = new ToDo(parts[1]);

            tasks.addTask(task);

            response = ui.printAddedTask(task) + "\n\n" + ui.printTaskCount(tasks);
            break;
        }

        case "deadline": {
            Deadline task = getDeadline(parts);
            tasks.addTask(task);

            response = ui.printAddedTask(task) + "\n\n" + ui.printTaskCount(tasks);
            break;
        }

        case "event": {
            Event task = getEvent(parts);
            tasks.addTask(task);

            response = ui.printAddedTask(task) + "\n\n" + ui.printTaskCount(tasks);
            break;
        }

        case "delete": {
            ensureHasArgument(parts, "Please specify a task number to delete :(");

            int deleteIndex = parseAndValidateIndex(parts[1], tasks.getSize(), "delete");

            Task deletedTask = tasks.getTask(deleteIndex);
            tasks.deleteTask(deleteIndex);

            response = ui.printDeletedTask(deletedTask) + "\n\n" + ui.printTaskCount(tasks);
            break;
        }

        case "find": {
            ensureHasArgument(parts, "description of find empty :(");

            String keyword = parts[1];
            assert(keyword != null);

            ArrayList<Task> foundTasks = tasks.findTasks(keyword);

            response = ui.printFindResults(foundTasks);
            break;
        }

        case "update": {
            ensureHasArgument(parts, "description of update empty :(");

            String[] updateParts = parts[1].split(" ", 2);
            ensureHasArgument(updateParts, "Please specify the new details to update :(");

            int updateIndex = parseAndValidateIndex(updateParts[0], tasks.getSize(), "update");
            String updateDetails = updateParts[1].trim();

            Task task = tasks.getTask(updateIndex);
            task.updateTask(updateDetails);
            response = ui.printUpdatedTask(task);
            break;
        }

        default:
            throw new PeperoException("I'm sorry I don't quite understand :(");
        }

        return response;
    }

    private static Deadline getDeadline(String[] parts) throws PeperoException {
        ensureHasArgument(parts, "description of deadline empty :(");
        ensureContains(parts[1], "/by");

        String[] deadlineParts = parts[1].split("/by ", 2);
        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
            throw new PeperoException("☹ Deadline must have a valid /by date.");
        }

        String description = deadlineParts[0];
        String deadline = deadlineParts[1];

        try {
            return new Deadline(description, LocalDateTime.parse(deadline, formatter));
        } catch (Exception e) {
            throw new PeperoException("☹ Please enter valid date/time in format ddMMyy HHmm.");
        }
    }

    private static Event getEvent(String[] parts) throws PeperoException {
        ensureHasArgument(parts, "description of event empty :(");
        ensureContains(parts[1], "/from", "/to");

        String pattern = " /from | /to ";
        String[] eventParts = parts[1].split(pattern);
        if (eventParts.length < 3 || eventParts[1].trim().isEmpty() || eventParts[2].trim().isEmpty()) {
            throw new PeperoException("☹ Event must include both /from and /to times.");
        }

        String description = eventParts[0].trim();
        String from = eventParts[1].trim();
        String to = eventParts[2].trim();

        try {
            return new Event(description, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter));
        } catch (Exception e) {
            throw new PeperoException("☹ Please enter valid date/time in format ddMMyy HHmm.");
        }
    }

    private static int parseAndValidateIndex(String part, int size, String action) throws PeperoException {
        if (part == null || part.isEmpty()) {
            throw new PeperoException("Please specify a task number to " + action + " :(");
        }
        int index;
        try {
            index = Integer.parseInt(part);
        } catch (NumberFormatException e) {
            throw new PeperoException("Invalid task number format :(");
        }
        if (index < 1 || index > size) {
            throw new PeperoException("task " + index + " does not exist :(");
        }
        return index - 1;
    }

    private static void ensureHasArgument(String[] parts, String errorMsg) throws PeperoException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new PeperoException(errorMsg);
        }
    }

    private static void ensureContains(String input, String... keywords) throws PeperoException {
        for (String keyword : keywords) {
            if (!input.contains(keyword)) {
                throw new PeperoException("☹ OOPS!!! This command must include " + String.join(" and ", keywords) + ".");
            }
        }
    }


}
