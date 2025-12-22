package org.springframework.jmx.export;

import javax.management.ObjectName;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/jmx/export/MBeanExporterListener.class */
public interface MBeanExporterListener {
    void mbeanRegistered(ObjectName objectName);

    void mbeanUnregistered(ObjectName objectName);
}
