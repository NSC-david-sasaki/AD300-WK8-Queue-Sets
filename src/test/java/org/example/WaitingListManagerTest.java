package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class WaitingListManagerTest {
    private WaitingListManager waitingListManager;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;

    @BeforeEach
    void setUp() {
        waitingListManager = new WaitingListManager();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        waitingListManager = null;
        System.gc();
        System.setOut(originalSystemOut);
    }


    @Test
    void addPersonTest() {
        waitingListManager.addPerson("Bob");
        assertEquals("Bob",waitingListManager.servePerson());
    }

    @Test
    void addPersonDuplicateTest() {
        waitingListManager.addPerson("Bob");
        waitingListManager.addPerson("Bob");
        assertEquals(1, waitingListManager.waitingListSize());

    }
    @Test
    void addPersonEmptyNameTest() {
        assertThrows(IllegalArgumentException.class, () -> waitingListManager.addPerson(""));
    }

    @Test
    void addPersonNullNameTest() {
        assertThrows(IllegalArgumentException.class, () -> waitingListManager.addPerson(null));
    }

    @Test
    void servePersonTest() {
        waitingListManager.addPerson("Bob");
        assertEquals("Bob",waitingListManager.servePerson());
    }

    @Test
    void servePersonEmptyListTest() {
        assertEquals("Waiting list is empty",waitingListManager.servePerson());
        assertEquals("Waiting list is empty",waitingListManager.servePerson()); // checking repeated calls
    }

    @Test
    void isPersonInList() {
        waitingListManager.addPerson("Bob");
        assertTrue(waitingListManager.isPersonInList("Bob"));
    }

    @Test
    void isPersonInListFalseTest() {
        waitingListManager.addPerson("Alice");
        assertFalse(waitingListManager.isPersonInList("Bob"));
    }

    @Test
    void waitingListSize() {
        waitingListManager.addPerson("Bob");
        assertEquals(1, waitingListManager.waitingListSize());
        waitingListManager.servePerson();
        waitingListManager.servePerson(); // should not decrement below 0
        assertEquals(0, waitingListManager.waitingListSize());
    }

    @Test
    void displayWaitingList() {
        waitingListManager.addPerson("Bob");
        waitingListManager.displayWaitingList();
        String output = outputStream.toString();
        assertEquals("Position: 1 - Bob\n",output);
    }

}