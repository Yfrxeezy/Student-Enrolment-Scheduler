package scheduler;

public class StudentEnrol extends Thread {

    //Stores the process ID for every student enrolment
    private final String processID;

    //Amount of execution time needed for every process
    private int burstTime;

    //Priority level of every student enrolment (shown in priority scheduler)
    private final int priority;

    //Creates a new student enrolment process
    public StudentEnrol(String processID, int burstTime, int priority) {
        this.processID = processID;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    //Returns the processID for every process
    public String getProcessID() {
        return processID;
    }

    //Returns the burst time for every process
    public int getBurstTime() {
        return burstTime;
    }

    //Updates the burst time after execution for every process
    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    //Returns the priority level of every process
    public int getPriorityLevel() {
        return priority;
    }
}
