package hw2;

import java.util.concurrent.Semaphore;

public class StudyGroup {
    private String groupName;
    private Lab lab;

    public StudyGroup(String name, Lab _lab) {
        this.lab = _lab;
        this.groupName = name;
    }

    public String getName() {
        return  this.groupName;
    }

    public Lab getLab() {
        return this.lab;
    }

    public void startStudyingWith(){
        if (this.lab.getCurrentStudentNumber() == 0 || !this.getName().equals(this.lab.getCurrentGroup())) { 
            // if it is the first student to the lab
            // or it is not in the same group with the group in the lab

            try { //acquire groupHunter semaphore, so no other group will go in or wait for group to empty the lab
                this.lab.getGroupHunter().acquire();
                this.lab.setCurrentGroup(this.groupName);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try { // decrease the capacitySemaphore so that the lab will not become overcrowded
            this.lab.getCapacityHunter().acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // adjust the current student number for the lab
        this.lab.setCurrentStudentNumber(this.lab.getCurrentStudentNumber()+1);


    }

    public void stopStudyingWith() {
        //if current student number is 1, lab becomes empty after it stops studying
        // so release the groupHunter semaphore
        if(this.lab.getCurrentStudentNumber() == 1){
            this.lab.setCurrentGroup(null);
            this.lab.getGroupHunter().release();
        }

        // adjust the current student number for the lab
        this.lab.getCapacityHunter().release();
        this.lab.setCurrentStudentNumber(this.lab.getCurrentStudentNumber() -1);
    }
}
