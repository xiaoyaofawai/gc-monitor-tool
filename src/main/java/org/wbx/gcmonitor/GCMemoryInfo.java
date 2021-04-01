package org.wbx.gcmonitor;

public class GCMemoryInfo {

    private String memoryRegionName;

    /**
     * 区域总量
     */
    private long total;

    /**
     * 回收前使用量
     */
    private long original;

    /**
     * 回收后剩余量
     */
    private long rest;


    // 回收的大小
    private long committed;

    public String getMemoryRegionName() {
        return memoryRegionName;
    }

    public GCMemoryInfo setMemoryRegionName(String memoryRegionName) {
        this.memoryRegionName = memoryRegionName;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public GCMemoryInfo setTotal(long total) {
        this.total = total;
        return this;
    }

    public long getOriginal() {
        return original;
    }

    public GCMemoryInfo setOriginal(long original) {
        this.original = original;
        return this;
    }

    public long getRest() {
        return rest;
    }

    public GCMemoryInfo setRest(long rest) {
        this.rest = rest;
        return this;
    }

    public long getCommitted() {
        return committed;
    }

    public GCMemoryInfo setCommitted(long committed) {
        this.committed = committed;
        return this;
    }

    public GCMemoryInfo() {
    }

    public GCMemoryInfo(String memoryRegionName, long total, long original, long rest, long committed) {
        this.memoryRegionName = memoryRegionName;
        this.total = total;
        this.original = original;
        this.rest = rest;
        this.committed = committed;
    }
}
