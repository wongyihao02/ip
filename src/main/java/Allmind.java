import java.util.Scanner;

public class Allmind {
    public static void main(String[] args) {
       /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        System.out.println("Hello! I'm AllMind, the support system for all mercenaries");

        System.out.println("What task would you like me to help you with today?");

        System.out.println(" ");

        //echo part
        String message;
        Scanner in = new Scanner(System.in);
        taskList tasks = new taskList();

        while (true) {

            message = in.nextLine();

            if (message.equals("bye")) {
                break;
            }
            tasks.runTask(message);
        }

        System.out.println("That concludes the briefing,good luck.");
    }
}
