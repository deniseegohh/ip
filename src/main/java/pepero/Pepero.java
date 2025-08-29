package pepero;

import pepero.task.TaskList;

import java.io.IOException;

public class Pepero {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
                    Parser.parse(input, tasks, ui, storage);
                    break;
                }

                Parser.parse(input, tasks, ui, storage);

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
}
