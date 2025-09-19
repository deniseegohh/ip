package pepero;

import org.junit.jupiter.api.Test;
import pepero.task.Task;
import pepero.task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    void parseAndReturn_todoCommand_taskAddedCorrectly() throws Exception {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");

        String response = Parser.parseAndReturn("todo read book", tasks, storage, ui);

        assertEquals(1, tasks.getSize());
        assertEquals("read book", tasks.getTask(0).getDescription());
    }

    @Test
    void parseAndReturn_markCommand_marksTaskAsDone() throws Exception {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");

        Parser.parseAndReturn("todo read book", tasks, storage, ui);
        String response = Parser.parseAndReturn("mark 1", tasks, storage, ui);

        Task task = tasks.getTask(0);
        assertTrue(task.getIsDone());
    }

    @Test
    void parseAndReturn_invalidCommand_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        try {
            Parser.parseAndReturn("nonsense", tasks, storage, ui);
        } catch (PeperoException e) {
            assertEquals("I'm sorry I don't quite understand :(", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e);
        }
    }
}
