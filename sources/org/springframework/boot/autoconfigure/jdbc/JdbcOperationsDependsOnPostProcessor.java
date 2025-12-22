package org.springframework.boot.autoconfigure.jdbc;

import org.springframework.boot.autoconfigure.AbstractDependsOnBeanFactoryPostProcessor;
import org.springframework.jdbc.core.JdbcOperations;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/jdbc/JdbcOperationsDependsOnPostProcessor.class */
public class JdbcOperationsDependsOnPostProcessor extends AbstractDependsOnBeanFactoryPostProcessor {
    public JdbcOperationsDependsOnPostProcessor(String... dependsOn) {
        super(JdbcOperations.class, dependsOn);
    }
}
