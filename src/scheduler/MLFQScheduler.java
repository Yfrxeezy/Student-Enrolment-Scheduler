package scheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MLFQScheduler {

    //The set time given to each process during execution
    private static final int TIME_SLICE = 20;

    //MLFQ Queue stores new and recently active processes
    private final Queue<StudentEnrol> youngQueue = new LinkedList<>();

    //MLFQ Queue stores older processes
    private final Queue<StudentEnrol> oldQueue = new LinkedList<>();

    //Adds a new student process into the younger queue
    public void addStudent(StudentEnrol student) {
        youngQueue.offer(student);
    }

    //Executes the MLFQ scheduling algorithm
    public List<StudentEnrol> runEnrolment() {

        //Stores the completed student processes
        List<StudentEnrol> completedStudents = new ArrayList<>();

        //Continues the execution process while either queue still contains processes
        while(!youngQueue.isEmpty() || !oldQueue.isEmpty()) {
            StudentEnrol current;

            //Tracks which queue the process came from
            boolean fromYoung;

            //Young queue gets the priority first
            if (!youngQueue.isEmpty()) {
                current = youngQueue.poll();
                fromYoung = true;
                System.out.println("\nYoung Queue -> " + current.getProcessID());
            } else {
                current = oldQueue.poll();
                fromYoung = false;
                System.out.println("\nOld Queue -> " + current.getProcessID());
            }
            //Stimulates the process running for one time slice
            try {
                Thread.sleep(TIME_SLICE);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            //Reduces the remaining burst time by subtracting TIME_SLICE
            int updatedTime = current.getBurstTime() - TIME_SLICE;

            //Prevents any burst time to be less than 0
            current.setBurstTime(Math.max(updatedTime, 0));

            System.out.println(current.getProcessID() + " Remaining: " + current.getBurstTime() + "ms");

            //Confirmation if the process has finished execution
            if (current.getBurstTime() == 0) {
                completedStudents.add(current);
                System.out.println(current.getProcessID() + " Finished.");
            } else {
                //Moves the unfinished process to the opposite side of the queue
                if (fromYoung) {
                    oldQueue.offer(current);
                } else {
                    youngQueue.offer(current);
                }
            }
        }
        //Returns the completed processes
        return completedStudents;
    }
}
