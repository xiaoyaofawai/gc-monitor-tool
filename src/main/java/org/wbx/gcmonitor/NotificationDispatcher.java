package org.wbx.gcmonitor;

import com.alibaba.fastjson.JSONObject;
import com.sun.management.GarbageCollectionNotificationInfo;
import org.wbx.gcmonitor.listener.GCNotificationHandler;
import org.wbx.gcmonitor.listener.GCNotificationHandlerFactory;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationDispatcher implements NotificationListener, Closeable {

    private ExecutorService threadPool = Executors.newFixedThreadPool(1);

    private List<GCNotificationHandler> handlerList = new ArrayList<>();

    public NotificationDispatcher() {
        loadHandler();
    }

    @Override
    public void handleNotification(final Notification notification, final Object handback) {

        final String notificationType = notification.getType();
        if (notificationType.equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
            // retrieve the garbage collection notification information
            final CompositeData cd = (CompositeData) notification.getUserData();
            final GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from(cd);
            final long sequence = notification.getSequenceNumber();
            System.out.println(JSONObject.toJSONString(info));

            threadPool.execute(() -> {
                for (GCNotificationHandler gcNotificationHandler : handlerList) {
                    gcNotificationHandler.handleNotification(sequence, info);
                }
            });
        }
    }


    @Override
    public void close() throws IOException {
        threadPool.shutdown();
    }

    private void loadHandler() {
        ServiceLoader<GCNotificationHandlerFactory> serviceLoader
                = ServiceLoader.load(GCNotificationHandlerFactory.class);
        Iterator<GCNotificationHandlerFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            handlerList.add(iterator.next().create());
        }
    }
}
