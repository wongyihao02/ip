import java.util.Scanner;

public class Allmind {

    private Ui ui;
    private Storage fileStorage;
    private TaskList taskList;

    public Allmind(String filePath) {
        ui = new Ui();
        fileStorage = new Storage(filePath);
        taskList = new TaskList(filePath);

    }

    public void run() {

        ui.printWelcomeMessage();
        String input = ui.getUserInput();

        while (!input.equals("bye")) {
            taskList.runTask(input);
            input = ui.getUserInput();
        }
        ui.printExitMessage();
    }

    public static void main(String[] args) {

        //echo part
//        String message;
//        Scanner in = new Scanner(System.in);
//        taskList tasks = new taskList();
//
//        while (true) {
//
//            message = in.nextLine();
//
//            if (message.equals("bye")) {
//                break;
//            }
//            tasks.runTask(message);
//        }
//
//        System.out.println("That concludes the briefing,good luck.");
        new Allmind("data/savedTaskList.txt").run();
    }
}
