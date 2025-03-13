import java.util.Scanner;

public class Ui {
    public Ui() {}

    public void printWelcomeMessage (){
        System.out.println("Hello! I'm AllMind, the support system for all mercenaries");
        System.out.println("What task would you like me to help you with today?");
        System.out.println(" ");
    }

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return getUserInput();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
    public void printExitMessage() {
        System.out.println("Briefing over.");
    }
}
