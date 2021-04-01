package org.wbx.gcmonitor;

import org.wbx.gcmonitor.exception.DuplicateRegistrationException;

import javax.management.NotificationEmitter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GCListenerRegister {

    private volatile boolean isRegistered;

    public synchronized boolean registerGCListener() {

        if (isRegistered) {
            throw new DuplicateRegistrationException("function 'registerGCListener' is not allow called duplicate");
        }

        NotificationDispatcher notificationDispatcher = new NotificationDispatcher();
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeans) {
            if (! (garbageCollectorMXBean instanceof NotificationEmitter)) {
                // 不支持监听
                continue;
            }
            final NotificationEmitter emitter = (NotificationEmitter) garbageCollectorMXBean;
            emitter.addNotificationListener(notificationDispatcher, null, null);
        }

        return true;
    }
}
