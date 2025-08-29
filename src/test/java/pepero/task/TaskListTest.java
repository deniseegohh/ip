package pepero.task;

import org.junit.jupiter.api.Test;
import pepero.PeperoException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void deleteTask_invalidIndex_throwsIndexOutOfBoundsException() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("read book"));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            tasks.deleteTask(2);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            tasks.deleteTask(0);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            tasks.deleteTask(-1);
        });


    }
}
