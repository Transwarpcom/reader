package com.mongodb.internal.management.jmx;

import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.management.MBeanServer;
import java.lang.management.ManagementFactory;
import javax.management.ObjectName;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/management/jmx/JMXMBeanServer.class */
public class JMXMBeanServer implements MBeanServer {
    private static final Logger LOGGER = Loggers.getLogger("management");
    private final javax.management.MBeanServer server = ManagementFactory.getPlatformMBeanServer();

    @Override // com.mongodb.management.MBeanServer
    public void registerMBean(Object mBean, String mBeanName) {
        try {
            this.server.registerMBean(mBean, new ObjectName(mBeanName));
        } catch (Exception e) {
            LOGGER.warn("Unable to register MBean " + mBeanName, e);
        }
    }

    @Override // com.mongodb.management.MBeanServer
    public void unregisterMBean(String mBeanName) {
        try {
            ObjectName objectName = new ObjectName(mBeanName);
            if (this.server.isRegistered(objectName)) {
                this.server.unregisterMBean(objectName);
            }
        } catch (Exception e) {
            LOGGER.warn("Unable to unregister MBean " + mBeanName, e);
        }
    }
}
