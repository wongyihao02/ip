import java.util.Scanner;

public class Allmind {

    private final Ui ui;
    private final Storage fileStorage;
    private TaskList taskList;

    public Allmind(String filePath) {
        ui = new Ui();
        fileStorage = new Storage(filePath);
        taskList = new TaskList(fileStorage);

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

        new Allmind("data/savedTaskList.txt").run();
    }
}
