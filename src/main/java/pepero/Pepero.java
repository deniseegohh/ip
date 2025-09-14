package pepero;

import pepero.task.TaskList;

import java.io.IOException;

/**
 * The main class for the Pepero application.
 */
public class Pepero {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The entry point of the Pepero application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/pepero.txt");
        TaskList tasks = storage.load();
        ui.printWelcome();
        ui.printTaskList(tasks);

        label:
        while (true) {
            try {
                String input = ui.readInput();
                ui.printLine();

                if (input.equals("bye")) {
                    Parser.parseAndReturn(input, tasks, storage);
                    break;
                }

                Parser.parseAndReturn(input, tasks, storage);

            } catch (PeperoException e) {
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println(" OOPS!!! Pepero.Task number must be an integer.");
                System.out.println("____________________________________________________________");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(" OOPS!!! Pepero.Task number out of range.");
                System.out.println("____________________________________________________________");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



        ui.exit();
    }

    public Pepero(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load().getTasks());
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseAndReturn(input, tasks, storage);
        } catch (PeperoException | IOException e) {
            return e.getMessage();
        }
    }
}
