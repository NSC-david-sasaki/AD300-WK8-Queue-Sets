package org.example;

public class Main {
    public static void main(String[] args) {
        WaitingListManager wlm = new WaitingListManager();
        InteractiveSession session = new ScannerInteractiveSession(wlm);
        session.startSession();
    }
}
