package com.mongodb.management;

import com.mongodb.internal.management.jmx.JMXMBeanServer;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/management/MBeanServerFactory.class */
public final class MBeanServerFactory {
    private static final MBeanServer M_BEAN_SERVER;

    private MBeanServerFactory() {
    }

    static {
        MBeanServer tmp;
        try {
            tmp = new JMXMBeanServer();
        } catch (Throwable th) {
            tmp = new NullMBeanServer();
        }
        M_BEAN_SERVER = tmp;
    }

    public static MBeanServer getMBeanServer() {
        return M_BEAN_SERVER;
    }
}
