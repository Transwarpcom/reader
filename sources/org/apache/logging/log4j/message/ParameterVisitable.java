package org.apache.logging.log4j.message;

import org.apache.logging.log4j.util.PerformanceSensitive;

@PerformanceSensitive({"allocation"})
/* loaded from: reader.jar:BOOT-INF/lib/log4j-api-2.11.2.jar:org/apache/logging/log4j/message/ParameterVisitable.class */
public interface ParameterVisitable {
    <S> void forEachParameter(ParameterConsumer<S> parameterConsumer, S s);
}
