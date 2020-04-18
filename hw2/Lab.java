
import java.util.concurrent.Semaphore;


public class Lab {
    private String name;
    private int capacity;
    int currentStudentNumber;
    String currentGroup;
    Semaphore capacityHunter;
    Semaphore groupHunter;

    public Lab(String n, int c) {
        this.name = n;
        this.capacity = c;
        this.currentStudentNumber = 0;
        this.currentGroup = null;
        this.capacityHunter = new Semaphore(c);
        this.groupHunter = new Semaphore(1);
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
