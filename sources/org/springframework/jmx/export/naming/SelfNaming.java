package org.springframework.jmx.export.naming;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/jmx/export/naming/SelfNaming.class */
public interface SelfNaming {
    ObjectName getObjectName() throws MalformedObjectNameException;
}
