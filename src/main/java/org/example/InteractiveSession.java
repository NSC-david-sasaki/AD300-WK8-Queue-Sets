package org.example;

public interface InteractiveSession {
    void startSession();
    void executeCommand(String command, String param);
    boolean isValidCommand(String command);
    void showAvailableCommands();
}
