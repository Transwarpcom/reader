package org.apache.logging.log4j.spi;

/* loaded from: reader.jar:BOOT-INF/lib/log4j-api-2.11.2.jar:org/apache/logging/log4j/spi/CleanableThreadContextMap.class */
public interface CleanableThreadContextMap extends ThreadContextMap2 {
    void removeAll(Iterable<String> iterable);
}
