package scheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobinScheduler {

    //Queue holding processes
    private Queue<StudentEnrol> processQueue;

    //Quantum time
    private final int QUANTUM = 20;

    //Constructor
    public RoundRobinScheduler() {
        processQueue = new LinkedList<>();
    }

    //Add process to queue
    public void enqueue(StudentEnrol process) {
        processQueue.add(process);
    }

    //Remove process from queue
    public StudentEnrol dequeue() {
        return processQueue.poll();
    }

    //Start enrolment scheduling
    public List<StudentEnrol> startEnrolment() {

        //Completed process list
        List<StudentEnrol> completedProcesses = new ArrayList<>();

        //Continue while queue not empty
        while (!processQueue.isEmpty()) {

            //Get next process
            StudentEnrol process = dequeue();

            System.out.println("Running " + process.getProcessID());
            try {
                Thread.sleep(QUANTUM);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

            process.setBurstTime(
                    process.getBurstTime() - QUANTUM
            );

            //Prevents negative values
            if (process.getBurstTime() < 0) {

                process.setBurstTime(0);
            }

            System.out.println(
                    process.getProcessID() + " Remaining: " + process.getBurstTime() + "ms"
            );

            //If process is completed
            if (process.getBurstTime() == 0) {

                completedProcesses.add(process);

                System.out.println(process.getProcessID() + " COMPLETE");
            } else {

                //Puts the unfinished process back into the queue
                enqueue(process);
            }
        }
        return completedProcesses;
    }
}

