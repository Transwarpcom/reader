package org.springframework.scripting.groovy;

import groovy.lang.GroovyObject;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/scripting/groovy/GroovyObjectCustomizer.class */
public interface GroovyObjectCustomizer {
    void customize(GroovyObject groovyObject);
}
