import java.util.*;

class StationClass implements Station {
    private String stationID;
    private int maxCapacity;
    private boolean multiflag;
    private boolean fifoFlag;
    private Map<String, Double> taskSpeeds;

    public StationClass(String stationID, int maxCapacity, boolean multiflag, boolean fifoFlag, Map<String, Double> taskSpeeds) {
        this.stationID = stationID;
        this.maxCapacity = maxCapacity;
        this.multiflag = multiflag;
        this.fifoFlag = fifoFlag;
        this.taskSpeeds = taskSpeeds;
    }

    @Override
    public String getStationID() {
        return stationID;
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public boolean isMultiflag() {
        return multiflag;
    }

    @Override
    public boolean isFifoFlag() {
        return fifoFlag;
    }

    @Override
    public Map<String, Double> getTaskSpeeds() {
        return taskSpeeds;
    }
}