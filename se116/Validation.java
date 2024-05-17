import java.util.List;

public class Validation {
    private static boolean isAllOk;
    public static boolean checkTaskType(String taskType, int line) {
        boolean isOk = false;
        if (taskType.matches("T[1-9]([0-9]*)|T_[1-9]([0-9]*)")) {
            isOk = true;
        }
        if (taskType.matches("([0-9]*)T")) {
            System.out.println(taskType + " is an invalid tasktypeID at line: " + line);
            isAllOk = false;
        }
        return isOk;
    }
    public static boolean checkTaskSize(String size, int line) {
        boolean isOk = false;
        int ivalue = 1;
        double fvalue = 1;
        if (size.matches("-?\\d+")) {
            ivalue = Integer.parseInt(size);
            isOk = true;
        }
        else if (size.matches("-?\\d+[.]\\d+")) {
            fvalue = Float.parseFloat(size);
            isOk = true;
        }
        if (ivalue <= 0) {
            System.out.println(ivalue + " has a negative task size at line: " + line);
            isOk = false;
            isAllOk = false;
        }
        else if (fvalue <= 0) {
            System.out.println(fvalue + " has a negative task size at line: " + line);
            isOk = false;
            isAllOk = false;
        }
        return isOk;
    }

    public static void checkIfSingle(List<TaskType> taskTypes) {
        int j = 0;
        for (int i = 0; i < taskTypes.size() - 1; i++) {
            for (j = i + 1; j < taskTypes.size(); j++) {
                if (taskTypes.get(i).getTaskID().equals(taskTypes.get(j).getTaskID())) {
                    System.out.println(taskTypes.get(i).getTaskID() + " has listed twice at line: " + taskTypes.get(i).getLinenumber());
                    isAllOk = false;
                }
            }
        }
    }

    public static boolean getIsAllOk() {
        return isAllOk;
    }

}