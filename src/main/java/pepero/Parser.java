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
            int markIndex = Integer.parseInt(parts[1]);
            assert(markIndex >= tasks.getSize());
            Task task = tasks.getTask(markIndex - 1);

            task.markDone();
            response = ui.printMarkedTask(task);
            break;
        }

        case "unmark": {
            int unmarkIndex = Integer.parseInt(parts[1]);
            assert(unmarkIndex >= tasks.getSize());
            Task task = tasks.getTask(unmarkIndex - 1);

            task.unmarkDone();

            response = ui.printUnmarkedTask(task);
            break;
        }

        case "todo": {
            if (parts.length < 2) {
                throw new PeperoException("description of todo empty :(");
            }

            assert(parts[1] != null);

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
            int deleteIndex = Integer.parseInt(parts[1]);
            if (deleteIndex > tasks.getSize()) {
                throw new PeperoException("task " + deleteIndex + " does not exist :(");
            }
            assert(deleteIndex >= tasks.getSize());

            Task deletedTask = tasks.getTask(deleteIndex - 1);
            tasks.deleteTask(deleteIndex);

            response = ui.printDeletedTask(deletedTask) + "\n\n" + ui.printTaskCount(tasks);
            break;
        }

        case "find": {
            if (parts.length < 2) {
                throw new PeperoException("description of find empty :(");
            }

            String keyword = parts[1];
            assert(keyword != null);

            ArrayList<Task> foundTasks = tasks.findTasks(keyword);

            response = ui.printFindResults(foundTasks);
            break;
        }

        case "update": {
            if (parts.length < 2) {
                throw new PeperoException("description of update empty :(");
            }
            String[] updateParts = parts[1].split(" ", 2);
            int updateIndex = Integer.parseInt(updateParts[0]);
            String updateDetails = updateParts[1];
            Task task = tasks.getTask(updateIndex - 1);
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
        if (parts.length < 2) {
            throw new PeperoException("description of deadline empty :(");
        }

        String[] deadlineParts = parts[1].split("/by ", 2);
        assert(deadlineParts.length == 2);
        String description = deadlineParts[0];
        String deadline = deadlineParts[1];

        return new Deadline(description, LocalDateTime.parse(deadline, formatter));
    }

    private static Event getEvent(String[] parts) throws PeperoException {
        if (parts.length < 2) {
            throw new PeperoException("description of event empty :(");
        }
        String pattern = " /from | /to ";
        String[] eventParts = parts[1].split(pattern);
        assert(eventParts.length == 3);
        String description = eventParts[0].trim();
        String from = eventParts[1].trim();
        String to = eventParts[2].trim();

        return new Event(description, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter));
    }

}
