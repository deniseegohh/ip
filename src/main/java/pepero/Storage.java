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

public class Storage {
    private final String filePath;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getTasks()) {
            fw.write(task.toFileFormat() + System.lineSeparator());
        }
        fw.close();
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            System.err.println("Invalid line format: " + line);
            return null;
        }
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                return new ToDo(description, isDone);

            case "D":
                return new Deadline(description, LocalDateTime.parse(parts[3], formatter), isDone);

            case "E":
                String from = parts[3].split("-")[0];
                String to = parts[3].split("-")[1];
                return new Event(description, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter), isDone);

            default:
                return null;

        }
    }
}
