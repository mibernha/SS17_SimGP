package PrinterQueues;

public class PrintJob {
    private JobType jobType;
    private int processTime;
    private int priority;

    public PrintJob(JobType jobType, int processTime, int priority) {
        this.jobType = jobType;
        this.processTime = processTime;
        this.priority = priority;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public int getProcessTime() {
        return processTime;
    }

    public void setProcessTime(int numberOfPages) {
        this.processTime = numberOfPages;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
