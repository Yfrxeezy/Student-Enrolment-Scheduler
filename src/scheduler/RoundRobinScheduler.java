package scheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobinScheduler {

    //The set time given to each process during execution
    private static final int TIME_SLICE = 20;

    //RoundRobin queue to store student enrolment processes
    private final Queue<StudentEnrol> queue = new LinkedList<>();

    //Adds a new student process into the queue
    public void addStudent(StudentEnrol student) {
        queue.offer(student);
    }
    //Executes the RoundRobin scheduling algorithm
    public List<StudentEnrol> runEnrolment() {

        //Creates a storage for completed student processes
        List<StudentEnrol> completedStudents = new ArrayList<>();

        //Continues while there are processes in the queue
        while (!queue.isEmpty()) {

            //Gets the next process into the queue
            StudentEnrol current = queue.poll();
            System.out.println("Running -> " + current.getProcessID());

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

            //Confirmation if the process has finished execution
            if (current.getBurstTime() <= 0) {
                completedStudents.add(current);
                System.out.println(current.getProcessID() + " Finished.");
            } else {
                //Puts the unfinished process back into the queue
                queue.offer(current);
                System.out.println(current.getProcessID() + " Remaining: " + current.getBurstTime() + "ms");
            }
        }
        //Returns the completed processes
        return completedStudents;
    }
}

