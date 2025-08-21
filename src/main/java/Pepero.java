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
            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                System.out.println("Bye bye!~ Hope to see you again soon~");
                System.out.println("____________________________________________________________");
                break;

            } else if (input.equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i - 1).getDescription());
                }
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
