package org.springframework.jmx.export.assembler;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/jmx/export/assembler/AutodetectCapableMBeanInfoAssembler.class */
public interface AutodetectCapableMBeanInfoAssembler extends MBeanInfoAssembler {
    boolean includeBean(Class<?> cls, String str);
}
