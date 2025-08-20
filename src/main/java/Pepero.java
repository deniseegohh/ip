import java.util.Scanner;

public class Pepero {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Pepero! \nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye bye!~ Hope to see you again soon~");
                System.out.println("____________________________________________________________");
                break;
            } else {
                System.out.println(input);
                System.out.println("____________________________________________________________");
            }
        }
        sc.close();
    }
}
