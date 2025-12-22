package com.mongodb.management;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/management/MBeanServer.class */
public interface MBeanServer {
    void unregisterMBean(String str);

    void registerMBean(Object obj, String str);
}
