package com.mongodb.management;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/management/ConnectionPoolStatisticsMBean.class */
public interface ConnectionPoolStatisticsMBean {
    String getHost();

    int getPort();

    int getMinSize();

    int getMaxSize();

    int getSize();

    int getCheckedOutCount();

    int getWaitQueueSize();
}
