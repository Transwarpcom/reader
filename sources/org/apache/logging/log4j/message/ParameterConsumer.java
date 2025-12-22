package org.apache.logging.log4j.message;

/* loaded from: reader.jar:BOOT-INF/lib/log4j-api-2.11.2.jar:org/apache/logging/log4j/message/ParameterConsumer.class */
public interface ParameterConsumer<S> {
    void accept(Object obj, int i, S s);
}
