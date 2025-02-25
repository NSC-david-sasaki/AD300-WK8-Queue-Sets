package org.example;

import java.util.Scanner;

public class ScannerInteractiveSession implements InteractiveSession {
    private WaitingListManager wlm;
    private Scanner scanner;

    public ScannerInteractiveSession(WaitingListManager wlm) {
        this.wlm = wlm;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void startSession() {
        System.out.println("Welcome to the Waiting List Manager!");
        showAvailableCommands();

        while (true) {
            System.out.print("\nEnter a command (or 'exit' to quit): ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            if (!isValidCommand(command)) {
                System.out.println("Invalid command. Please try again.");
                continue;
            }

            String param = "";
            if (command.equals("addPerson") || command.equals("isPersonInList")) {
                System.out.print("Enter the person's name: ");
                param = scanner.nextLine().trim();
            }

            try {
                executeCommand(command, param);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    @Override
    public void executeCommand(String command, String param) {
        Object result = wlm.setCommand(command, param);
        if (result != null){
            System.out.println(result);
        }
    }

    @Override
    public boolean isValidCommand(String command) {
        return wlm.getCommands().contains(command);
    }

    @Override
    public void showAvailableCommands() {
        System.out.println("Available commands: " + wlm.getCommands());
    }
}

