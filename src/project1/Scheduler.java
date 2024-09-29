package project1;
import java.util.Scanner;

public class Scheduler {
    private Scanner scanner;

    public Scheduler(){
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Scheduler is running.");

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) System.out.println("Please provide an input.");

            if (input.charAt(0) == 'Q') {
                System.out.println("Scheduler terminated.");
                break;
            }

            processCommand(input);
        }

        scanner.close();
    }

    // Direct commands to respective functions
    private void processCommand(String input) {
        String command = getCommand(input);
        switch (command) {
            case "S":
                System.out.println("Schedule");
                break;
            case "C":
                System.out.println("C");
                break;
            case "R":
                System.out.println("R");
                break;
            case "PA":
                System.out.println("PA");
                break;
            case "PP":
                System.out.println("PP");
                break;
            case "PL":
                System.out.println("PL");
                break;
            case "PS":
                System.out.println("PS");
                break;
            default:
                System.out.println("Invalid command.");
                break;

            }

    }

    private String getCommand(String input) {
        int commaIndex = input.indexOf(',');
        return input.substring(0, commaIndex);
    }

}
