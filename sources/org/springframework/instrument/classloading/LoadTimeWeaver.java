package org.springframework.instrument.classloading;

import java.lang.instrument.ClassFileTransformer;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/instrument/classloading/LoadTimeWeaver.class */
public interface LoadTimeWeaver {
    void addTransformer(ClassFileTransformer classFileTransformer);

    ClassLoader getInstrumentableClassLoader();

    ClassLoader getThrowawayClassLoader();
}
