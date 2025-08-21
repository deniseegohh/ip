import java.util.ArrayList;
import java.util.Scanner;

public class Pepero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Pepero! \nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            System.out.println("____________________________________________________________");

            if (command.equals("bye")) {
                System.out.println("Bye bye!~ Hope to see you again soon~");
                System.out.println("____________________________________________________________");
                break;

            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + "." + tasks.get(i - 1).getStatusIcon() + " " + tasks.get(i - 1).getDescription());
                }
                System.out.println("____________________________________________________________");
            } else if (command.equals("mark")) {
                int number = Integer.parseInt(parts[1]);
                Task task = tasks.get(number - 1);
                task.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.getStatusIcon() + " " + task.getDescription());
                System.out.println("____________________________________________________________");
            } else if (command.equals("unmark")) {
                int number = Integer.parseInt(parts[1]);
                Task task = tasks.get(number - 1);
                task.unmarkDone();
                System.out.println("Okay, I've marked this task as not done yet:");
                System.out.println(task.getStatusIcon() + " " + task.getDescription());
                System.out.println("____________________________________________________________");
            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        }

        sc.close();
    }
}
