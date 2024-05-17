import java.util.ArrayList;
import java.util.List;

class WorkflowParserClass implements WorkflowParser {

    private List<Task> tasks;
    private List<Job> jobs;
    private List<Station> station;

    @Override
    public List<Task> parseTaskTypes(String line) {
        	
    List<Task> tasks = new ArrayList<>();
    
    String[] taskInfo = line.split("\\s+");
    if (taskInfo.length >= 2) {
   
        String taskID = taskInfo[0];
        String taskName = taskInfo[1];
        tasks.add(new Task(taskID, taskName));
    } else {
        System.err.println("Invalid task type: " + line);
    }
    return tasks;

    }

    @Override
    public List<Job> parseJobTypes(String line) {
        return jobs;
    }

    @Override
    public List<Station> parseStations(String line) {
        return station;
    }
}
