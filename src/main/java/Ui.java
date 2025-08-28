import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void printWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Pepero! \nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printExit() {
        System.out.println("Bye bye!~ Hope to see you again soon~");
        System.out.println("____________________________________________________________");
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void exit() {
        sc.close();
    }
}
