package pepero;

import pepero.task.Task;
import pepero.task.TaskList;
import pepero.task.ToDo;
import pepero.task.Deadline;
import pepero.task.Event;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy HHmm");

    public static void parse(String input, TaskList tasks, Ui ui, Storage storage) throws PeperoException, IOException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];

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
                Task task = tasks.getTasks().get(markIndex - 1);
                task.markDone();
                ui.printMark(task);
                break;
            }

            case "unmark": {
                int unmarkIndex = Integer.parseInt(parts[1]);
                Task task = tasks.getTasks().get(unmarkIndex - 1);
                task.unmarkDone();
                ui.printUnmark(task);
                break;
            }

            case "todo": {
                if (parts.length < 2) {
                    throw new PeperoException("description of todo empty :(");
                }
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
                String description = otherPart[0];
                String from = otherPart[1].split(" ", 2)[1];
                String to = otherPart[2].split(" ", 2)[1];
                Event task = new Event(description, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to,formatter));
                tasks.addTask(task);
                ui.printAddTask(task);
                break;
            }

            case "delete": {
                int deleteIndex = Integer.parseInt(parts[1]);
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
                ui.printFindResults(tasks.findTasks(keyword));
                break;
            }

            default: {
                throw new PeperoException("I'm sorry I don't quite understand :(");
            }
        }
    }
}
