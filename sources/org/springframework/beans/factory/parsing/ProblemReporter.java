package org.springframework.beans.factory.parsing;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/parsing/ProblemReporter.class */
public interface ProblemReporter {
    void fatal(Problem problem);

    void error(Problem problem);

    void warning(Problem problem);
}
