package org.springframework.context;

import org.springframework.beans.factory.Aware;
import org.springframework.core.env.Environment;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/EnvironmentAware.class */
public interface EnvironmentAware extends Aware {
    void setEnvironment(Environment environment);
}
