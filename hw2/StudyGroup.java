
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
        if (this.lab.getCurrentStudentNumber() == 0 || this.groupName.equals(this.lab.currentGroup) == false) { // first student to the lab
            try {
                this.lab.getGroupHunter().acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            this.lab.getCapacityHunter().acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.lab.setCurrentStudentNumber(this.lab.getCurrentStudentNumber()+1);


    }

    public void stopStudyingWith() {
        if(this.lab.getCurrentStudentNumber() == 1){
            this.lab.getGroupHunter().release();
        }
        this.lab.getCapacityHunter().release();
        this.lab.setCurrentStudentNumber(this.lab.getCurrentStudentNumber() -1);
    }
}
