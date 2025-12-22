package com.mongodb.management;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/management/NullMBeanServer.class */
public class NullMBeanServer implements MBeanServer {
    @Override // com.mongodb.management.MBeanServer
    public void unregisterMBean(String mBeanName) {
    }

    @Override // com.mongodb.management.MBeanServer
    public void registerMBean(Object mBean, String mBeanName) {
    }
}
