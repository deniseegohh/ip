package pepero;

import pepero.task.Task;
import pepero.task.TaskList;
import pepero.task.ToDo;
import pepero.task.Deadline;
import pepero.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Handles loading from and saving tasks to a file.
 */
public class Storage {
    private final String filePath;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy HHmm");

    /**
     * Constructs a new Storage object for the given file path.
     *
     * @param filePath the path of the file to load from and save to
     */
    public Storage(String filePath) {
        assert(filePath != null);
        this.filePath = filePath;
    }

    /**
     * Load tasks from the file and returns them as a TaskList.
     * If the file does not exist, it will be created and an empty TaskList is returned.
     *
     * @return a TaskList containing tasks loaded from the file
     */
    public TaskList load() {
        TaskList tasks = new TaskList();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.addTask(task);
                }
            }
            sc.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        return tasks;
    }

    /**
     * Saves the task in the given TaskList to the file.
     *
     * @param tasks the TaskList to save
     * @throws IOException if an error occurs while writing to the file
     */
    public void save(TaskList tasks) throws IOException {
        assert(tasks != null);
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getTasks()) {
            assert(task != null);
            fw.write(task.toFileFormat() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Parses a single line from the file and returns the corresponding Task object.
     *
     * @param line the line from the file representing a task
     * @return the Task object corresponding to the line, or null if the line is not a Task.
     */
    private Task parseTask(String line) {
        assert(line != null);

        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            System.err.println("Invalid line format: " + line);
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        assert(description != null);

        switch (type) {
        case "T":
            return new ToDo(description, isDone);

        case "D":
            assert (parts.length == 4);
            return new Deadline(description, LocalDateTime.parse(parts[3], formatter), isDone);

        case "E":
            assert (parts.length == 4);
            String[] eventParts = parts[3].split("-");
            assert (eventParts.length == 2);
            String from = eventParts[0];
            String to = eventParts[1];
            return new Event(description, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter), isDone);

        default:
            return null;

        }
    }
}
