import java.util.*;

class JobClass implements Job {
    private String jobID;
    private List<Task> tasks;

    public JobClass(String jobID, List<Task> tasks) {
        this.jobID = jobID;
        this.tasks = tasks;
    }

    @Override
    public String getJobID() {
        return jobID;
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }
}