package PrinterQueues;

public class PrintJob {
    private int startTimeOffset;
    private JobType jobType;
    private int processTime;
    private int priority;

    public PrintJob(int statTimeOffset, JobType jobType, int processTime, int priority) {
        this.startTimeOffset = statTimeOffset;
        this.jobType = jobType;
        this.processTime = processTime;
        this.priority = priority;
    }


    public int getStartTimeOffset() {
        return startTimeOffset;
    }

    public void setStartTimeOffset(int startTimeOffset) {
        this.startTimeOffset = startTimeOffset;
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
