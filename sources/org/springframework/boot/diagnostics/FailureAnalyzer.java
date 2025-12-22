package org.springframework.boot.diagnostics;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/diagnostics/FailureAnalyzer.class */
public interface FailureAnalyzer {
    FailureAnalysis analyze(Throwable failure);
}
