class TaskClass {
    
    private TaskType taskType;
    private double size;

    public TaskClass(TaskType taskType, String size) {
        this.taskType = taskType;
        if (size.matches("-?\\d+")) {
            this.size = Integer.parseInt(size);
        }
        else if (size.matches("-?\\d+[.]\\d+")) {
            this.size = Float.parseFloat(size);
        }
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public double getSize() {
        return size;
    }

}