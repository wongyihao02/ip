import java.util.Scanner;

public class Ui {
    public Ui() {}

    /**
     * prints the standard message on start
     */
    public void printWelcomeMessage (){
        System.out.println("Hello! I'm AllMind, the support system for all mercenaries");
        System.out.println("What task would you like me to help you with today?");
        System.out.println(" ");
    }

    /**
     * gets the users input as a String
     * @return the users input
     */
    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * gets the users input,prints a message for the user to see first
     * @param prompt message for user to see before giving input
     * @return the users input
     */
    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return getUserInput();
    }

    /**
     * prints the given string
     * @param message the string to be printed
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * prints the standard end message
     */
    public void printExitMessage() {
        System.out.println("Briefing over.");
    }
}
