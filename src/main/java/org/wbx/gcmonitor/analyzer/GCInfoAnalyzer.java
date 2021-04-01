package org.wbx.gcmonitor.analyzer;

import com.sun.management.GarbageCollectionNotificationInfo;
import org.wbx.gcmonitor.GCInfo;

public interface GCInfoAnalyzer {

    GCInfo analyze(long index, GarbageCollectionNotificationInfo info);
}
