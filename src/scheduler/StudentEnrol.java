package scheduler;

public class StudentEnrol extends Thread {

    private String processID;
    private int burstTime;
    private int priority;

    public StudentEnrol(String processID,
                        int burstTime,
                        int priority) {
        this.processID = processID;
        this.burstTime = burstTime;
        this.priority = priority;

    }

    public String getProcessID() {
        return processID;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getPriorityLevel() {
        return priority;
    }
}

//Old methods used
//    @Override
//     public void run() {
//        try {
//
//            //Stimulates one quantum of work
//            Thread.sleep(20);
//
//            //Reduces the burst time
//            burstTime -= 20;
//
//            //Prevents the negative burst Time
//            if(burstTime < 0) {
//                burstTime = 0;
//            }
//
//            //Prints out the burstTime status
//            System.out.println(processID + " running... Remaining:" + burstTime + "ms");
//
//            //If process is completed
//            if (burstTime == 0) {
//
//                System.out.println(
//                        processID + " COMPLETE"
//                );
//            }
//            //Spots out for interruption within the process run
//        } catch (InterruptedException e) {
//
//            System.out.println(processID +" INTERRUPTED");
//        }
//    }
//}
