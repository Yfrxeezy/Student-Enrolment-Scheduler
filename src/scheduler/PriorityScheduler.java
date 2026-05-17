package scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityScheduler {

    //Priority queue
    private PriorityQueue<StudentEnrol> processQueue;

    //Quantum time
    private final int QUANTUM = 20;

    //Constructor
    public PriorityScheduler() {

        Comparator<StudentEnrol> c = (s1, s2) -> {
            if (s1.getPriorityLevel() < s2.getPriorityLevel()) {
                return -1;
            } else {
                return 1;
            }
        };

        processQueue = new PriorityQueue<>(c);
    }

    //Adds new processes
    public void enqueue(StudentEnrol process) {
        processQueue.add(process);
    }

    //Removes the highest priority process
    public StudentEnrol dequeue() {
        return processQueue.poll();
    }

    //Starts the scheduling
    public List<StudentEnrol> startEnrolment() {
        List<StudentEnrol> complete = new ArrayList<>();

        while (!processQueue.isEmpty()) {
            StudentEnrol process = dequeue();

            System.out.println("Running " + process.getProcessID() + " | Priority: " + process.getPriorityLevel());

            try {
                Thread.sleep(QUANTUM);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Reduces burst time
            process.setBurstTime(process.getBurstTime() - QUANTUM);

            //Prevents negative values
            if (process.getBurstTime() < 0) {
                process.setBurstTime(0);
            }

            System.out.println(process.getProcessID() + " Remaining: " + process.getBurstTime());

            //Completed process burstTime
            if (process.getBurstTime() == 0) {

                complete.add(process);

                System.out.println(process.getProcessID() + " COMPLETE");
            } else {
                enqueue(process);
            }
        }

        return complete;

    }
}
