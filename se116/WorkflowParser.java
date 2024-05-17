import java.util.*;

interface WorkflowParser {
    List<Task> parseTaskTypes(String line);
    List<Job> parseJobTypes(String line);
    List<Station> parseStations(String line);
}
