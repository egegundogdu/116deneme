import java.util.*;

interface Station {
    
    String getStationID();
    int getMaxCapacity();
    boolean isMultiflag();
    boolean isFifoFlag();
    Map<String, Double> getTaskSpeeds();
}