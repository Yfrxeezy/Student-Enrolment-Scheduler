package scheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MLFQScheduler {
    //Young Queue
    private Queue<StudentEnrol> youngQueue;

    //Old Queue
    private Queue<StudentEnrol> oldQueue;

    //Quantum time
    private final int QUANTUM = 20;

    //Constructor
    public MLFQScheduler() {
        youngQueue = new LinkedList<>();
        oldQueue = new LinkedList<>();
    }

    //Adds a new process into the young queue
    public void enqueue(StudentEnrol process) {
        youngQueue.add(process);
    }

    //Starts scheduling
    public List<StudentEnrol> startEnrolment() {
        List<StudentEnrol> complete = new ArrayList<>();

        while(!youngQueue.isEmpty() || !oldQueue.isEmpty()) {
            StudentEnrol process;
            boolean cameFromYoung;

            //Young Queue first
            if (!youngQueue.isEmpty()) {
                process = youngQueue.poll();

                cameFromYoung = true;

                System.out.println("Running Young process: " + process.getProcessID());

            } else {
                process = oldQueue.poll();

                cameFromYoung = false;

                System.out.println("Running Old process: " + process.getProcessID());
            }
            try {
                Thread.sleep(QUANTUM);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Reduces the burst time
            process.setBurstTime(process.getBurstTime() - QUANTUM);

            //Prevents negative values
            if (process.getBurstTime() < 0) {
                process.setBurstTime(0);
            }
            System.out.println(process.getProcessID() + " Remaining: " + process.getBurstTime());

            //Complete Process
            if (process.getBurstTime() == 0) {

                complete.add(process);

                System.out.println(process.getProcessID() + " COMPLETE");
            } else {
                //moves process to the opposite queue
                if (cameFromYoung) {
                    oldQueue.add(process);
                } else {
                    youngQueue.add(process);
                }
            }
        }
        return complete;
    }
}
