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

            if (input.toLowerCase().equals("Q")) {
                System.out.println("Scheduler terminated.");
                break;
            }

            //code to process command follows
        }

        scanner.close();
    }

}
