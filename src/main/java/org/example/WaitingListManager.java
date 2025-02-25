package org.example;

import java.lang.reflect.Method;
import java.util.*;

public class WaitingListManager {
    private Queue<String> waitingList = new LinkedList<>();
    private Set<String> waitingSet = new HashSet<>();

    public void addPerson(String name){
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        else if (isPersonInList(name)){
            System.out.println(name + " is already in the list");
        }
        else{
            waitingList.offer(name);
            waitingSet.add(name);
        }
    }
    public String servePerson(){
        if (waitingList.isEmpty()){
            return("Waiting list is empty");
        }
       String buff = waitingList.poll();
       if (!buff.equals(null)) {
           waitingSet.remove(buff);
           return buff;
       }
       else{
           throw new RuntimeException("waitingList's String buffer was null");
       }
    }

    public boolean isPersonInList(String name){
        return waitingSet.contains(name);
    }

    public int waitingListSize(){
        return waitingList.size();
    }

    public void displayWaitingList(){
        int positionNum = 1;
        for (String name : waitingList){
            System.out.println("Position: "+positionNum+" - "+name);
            positionNum++;
        }
    }

    public List<String> getCommands(){
        Method[] methods = WaitingListManager.class.getDeclaredMethods();
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods) {
            methodNames.add(method.getName());
        }
        return methodNames;
    }

    public Object setCommand(String command, String param) {
        command = command.toLowerCase();
        Object result = null;

        switch (command) {
            case "addperson":
                this.addPerson(param);
                break;
            case "serveperson":
                this.servePerson();
                break;
            case "ispersoninlist":
                result = this.isPersonInList(param);
                break;
            case "waitinglistsize":
                result = this.waitingListSize();
                break;
            case "displaywaitinglist":
                this.displayWaitingList();
                break;
            default:
                throw new IllegalArgumentException("Invalid command: " + command);
        }

        return result;
    }
}
