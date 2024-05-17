public class TaskType {
    private String taskID;
    private int linenumber;

    public TaskType(String taskID, int linenumber) {
        this.taskID = taskID;
        this.linenumber = linenumber;
    }

    public String getTaskID() {
        return taskID;
    }

    public int getLinenumber() {
        return linenumber;
    }
}