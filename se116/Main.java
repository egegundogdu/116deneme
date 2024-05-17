import java.io.*;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;

public class Main {

    static List<TaskType> taskTypes;
    static List<TaskClass> taskClasses;
    static List<String> jobTypes;
    static List<String> stations;
    static List<String> jobs;
    static String workflowFile;
    static String jobFile;

    public static void main(String[] args) {

        getFileNames();
        parseWorkflowFile(workflowFile);
        parseJobFile(jobFile);
        WorkflowSimulation simulation = new WorkflowSimulation();

    }

    static void getFileNames() {
        System.out.println("Please enter workflow file name and job file name using space between them");
        try (Scanner sc = new Scanner(System.in)) {
            workflowFile = sc.next();
            jobFile = sc.next();
            if (!Files.exists(Path.of(workflowFile)) && !Files.exists(Path.of(jobFile))) {
                throw new NoSuchFileException("There is no files with that names");
            }
            else if (!Files.exists(Path.of(workflowFile))) {
                throw new NoSuchFileException("There is no workflow file with that name");
            }
            else if (!Files.exists(Path.of(jobFile))) {
                throw new NoSuchFileException("There is no job file with that name");
            }
            if (!Files.isReadable(Path.of(workflowFile)) && !Files.isReadable(Path.of(jobFile))) {
                throw new AccessDeniedException("Files are not accessible");
            }
            else if (!Files.isReadable(Path.of(workflowFile))) {
                throw new AccessDeniedException("Workflow file is not accessible");
            }
            else if (!Files.isReadable(Path.of(jobFile))) {
                throw new AccessDeniedException("Job file is not accessible");
            }
        }
        catch (NoSuchFileException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (AccessDeniedException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.printf("%nSomething went wrong about file");
            System.exit(1);
        }
    }

    static void parseWorkflowFile(String workflowFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(workflowFile))) {
            String line;
            taskTypes = new ArrayList<>();
            taskClasses = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                int linenumber = 0;
                if (line.startsWith("(TASKTYPES")) {
                    do {
                        ++linenumber;
                        List<String> temp = new ArrayList<>();
                        temp.addAll(Arrays.asList(line.split("\\s+")));
                        String lastString = temp.get(temp.size() - 1);
                        if (lastString.matches(".*\\)")) {
                            lastString = lastString.substring(0, lastString.length() - 1);
                            temp.set(temp.size() - 1, lastString);
                        }
                        for (int i = 0; i < temp.size(); i++) {
                            if (Validation.checkTaskType(temp.get(i), linenumber)) {
                                taskTypes.add(new TaskType(temp.get(i), linenumber));
                                if (i + 1 < temp.size() && Validation.checkTaskSize(temp.get(i + 1), linenumber)) {
                                    taskClasses.add(new TaskClass(taskTypes.get(taskTypes.size() - 1), temp.get(i + 1)));
                                }
                            }
                        }
                        line = reader.readLine();
                    } while (!line.startsWith("(JOBTYPES"));
                    Validation.checkIfSingle(taskTypes);
                } else if (line.startsWith("(JOBTYPES")) {
                    do {
                        //generalParser(line, jobTypes);
                        line = reader.readLine();
                    } while (line.startsWith("(STATIONS"));
                } else if (line.startsWith("(STATIONS")) {
                    do {
                        //generalParser(line, stations);
                        line = reader.readLine();
                    } while (!(line == null));
                }
            }
            if (!Validation.getIsAllOk()) {
                throw new Exception();
            }
        }
        catch (IOException e) {
            System.err.println("Error reading workflow file: " + e.getMessage());
            System.exit(1);
        }
        catch (Exception e) {
            System.out.println("Closing program");
            System.exit(1);
        }
    }

    static void parseJobFile(String jobFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(jobFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                do {
                    parseJobs(line);
                    line = reader.readLine();
                } while (!(line == null));
                /*String jobID = parts[0];
                String jobTypeID = parts[1];
                int startTime = Integer.parseInt(parts[2]);
                int duration = Integer.parseInt(parts[3]);*/

            }
        } catch (IOException e) {
            System.err.println("Error reading job file: " + e.getMessage());
            System.exit(1);
        }
    }

    static void parseJobs(String line) {
        jobs = new ArrayList<>();
        jobs.addAll(Arrays.asList(line.split("\\s+")));
    }

}