package org.wbx.gcmonitor.listener;

import com.sun.management.GarbageCollectionNotificationInfo;

public interface GCNotificationHandler {

    void handleNotification(long index, GarbageCollectionNotificationInfo info);

}
