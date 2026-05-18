package scheduler;

import java.util.List;

public class MainPriority {

    public static void main(String[] args) {

        //Creates the Priority scheduler
        PriorityScheduler scheduler = new PriorityScheduler();

        //Creates a new enrolment processes
        StudentEnrol p1 = new StudentEnrol("P1", 100, 1);

        StudentEnrol p2 = new StudentEnrol("P2", 80, 2);

        StudentEnrol p3 = new StudentEnrol("P3", 60, 3);

        //Adds the students into the queue
        scheduler.addStudent(p1);
        scheduler.addStudent(p2);
        scheduler.addStudent(p3);

        //Starts the scheduling
        List<StudentEnrol> completed = scheduler.runEnrolment();
        System.out.println("\nExecution process results:");

        //Confirmation message of the successful execution
        for (StudentEnrol process : completed) {
            System.out.println(process.getProcessID() + " COMPLETE");
        }
    }
}
