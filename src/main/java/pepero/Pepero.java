package pepero;

import pepero.task.TaskList;

import java.io.IOException;

/**
 * The main class for the Pepero application.
 */
public class Pepero {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Pepero(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load().getTasks());
        ui = new Ui();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseAndReturn(input, tasks, storage, ui);
        } catch (PeperoException | IOException e) {
            return e.getMessage();
        }
    }
}
