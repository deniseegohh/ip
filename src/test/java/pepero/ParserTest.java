package pepero;

import org.junit.jupiter.api.Test;
import pepero.task.TaskList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parse_invalidCommand_throwsPeperoExpection() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/pepero.txt");

        assertThrows(PeperoException.class, () -> Parser.parse("abracadabra", tasks, ui, storage));
    }
}
