package pepero.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    void findTasks_matchExists_returnsMatchingTasks() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("do homework"));

        var results = taskList.findTasks("book");

        assertEquals(1, results.size());
        assertEquals("read book", results.get(0).getDescription());
    }

    @Test
    void findTasks_noMatch_returnsEmptyList() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new ToDo("buy chocolate"));

        var results = taskList.findTasks("papaya");

        assertEquals(0, results.size());
    }
}
