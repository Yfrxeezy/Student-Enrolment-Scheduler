package scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityScheduler {

    //The set time given to each process during execution
    private static final int TIME_SLICE = 20;

    //Priority queue stores each processes based on priority level
    private final PriorityQueue<StudentEnrol> queue =
            new PriorityQueue<>(Comparator.comparingInt(StudentEnrol::getPriorityLevel));

    //Adds a new student process into the queue
    public void addStudent(StudentEnrol student) {
        queue.offer(student);
    }

    //Executes the priority scheduling algorithm
    public List<StudentEnrol> runEnrolment() {

        //Creates a storage for completed student processes
        List<StudentEnrol> completedStudents = new ArrayList<>();

        //Continues while there are processes in the queue
        while (!queue.isEmpty()) {
            //Gets the highest priority level
            StudentEnrol current = queue.poll();

            System.out.println("Executing -> " + current.getProcessID());

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
                System.out.println(current.getProcessID() +" Finished.");
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
