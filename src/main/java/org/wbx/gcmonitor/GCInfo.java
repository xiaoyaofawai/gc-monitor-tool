package org.wbx.gcmonitor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GCInfo {

    private long startTime;
    private long endTime;
    private long duration;

    private Map<String, GCMemoryInfo> gcMemoryInfoMap;

    public GCInfo(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = endTime - startTime;

        gcMemoryInfoMap = new HashMap<>();
    }

    public synchronized GCMemoryInfo addMemoryInfo(String memoryRegionName, GCMemoryInfo gcMemoryInfo) {
        return gcMemoryInfoMap.put(memoryRegionName, gcMemoryInfo);
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getDuration() {
        return duration;
    }

    public synchronized Map<String, GCMemoryInfo> getGcMemoryInfoMap() {
        return Collections.synchronizedMap(gcMemoryInfoMap);
    }
}
