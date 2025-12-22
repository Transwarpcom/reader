package org.springframework.boot.autoconfigure.jdbc;

import org.springframework.boot.autoconfigure.AbstractDependsOnBeanFactoryPostProcessor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/jdbc/NamedParameterJdbcOperationsDependsOnPostProcessor.class */
public class NamedParameterJdbcOperationsDependsOnPostProcessor extends AbstractDependsOnBeanFactoryPostProcessor {
    public NamedParameterJdbcOperationsDependsOnPostProcessor(String... dependsOn) {
        super(NamedParameterJdbcOperations.class, dependsOn);
    }
}
