package hw2;
import java.util.concurrent.Semaphore;


public class Lab {
    
    private String name;
    private int capacity;

    int currentStudentNumber; //variable to hold the number of students inside lab
    String currentGroup; // variable to hold the current group inside the lab
    Semaphore capacityHunter; // Semaphore for the capacity 
    Semaphore groupHunter; // Semaphore to ensure one group at a time

    public Lab(String n, int c) {
        this.name = n;
        this.capacity = c;
        this.currentStudentNumber = 0;
        this.currentGroup = null; 
        this.capacityHunter = new Semaphore(c); //initialize with capacity
        this.groupHunter = new Semaphore(1); //initialize with 1
    }

    public String getName() {
        return  this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getCurrentStudentNumber(){ return  this.currentStudentNumber; }

    public Semaphore getCapacityHunter() {
        return capacityHunter;
    }

    public Semaphore getGroupHunter() {
        return groupHunter;
    }

    public void setCurrentStudentNumber(int currentStudentNumber) {
        this.currentStudentNumber = currentStudentNumber;
    }

    public String getCurrentGroup() {
        return this.currentGroup;
    }

    public void setCurrentGroup(String currentGroup) {
        this.currentGroup = currentGroup;
    }
}
