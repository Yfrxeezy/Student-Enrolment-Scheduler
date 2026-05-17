package scheduler;

import java.util.List;

public class MainPriority {

    public static void main(String[] args) {

        //Creates the RoundRobin scheduler
        PriorityScheduler scheduler =
                new PriorityScheduler();

        //Creates a new enrolment processes
        StudentEnrol p1 =
                new StudentEnrol("P1", 100, 1);

        StudentEnrol p2 =
                new StudentEnrol("P2", 80, 2);

        StudentEnrol p3 =
                new StudentEnrol("P3", 60, 3);

        //Adds the processes into the queue
        scheduler.enqueue(p1);
        scheduler.enqueue(p2);
        scheduler.enqueue(p3);

        //Starts the scheduling
        List<StudentEnrol> completed =
                scheduler.startEnrolment();

        //Print completed processes
        System.out.println("\nCompleted Processes:");

        for (StudentEnrol process : completed) {

            System.out.println(
                    process.getProcessID()
                            + " | COMPLETE"
            );
        }
    }
}
