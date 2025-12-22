package org.springframework.jmx.export.assembler;

import javax.management.JMException;
import javax.management.modelmbean.ModelMBeanInfo;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/jmx/export/assembler/MBeanInfoAssembler.class */
public interface MBeanInfoAssembler {
    ModelMBeanInfo getMBeanInfo(Object obj, String str) throws JMException;
}
