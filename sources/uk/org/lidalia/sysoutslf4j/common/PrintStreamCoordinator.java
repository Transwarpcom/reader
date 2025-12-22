package uk.org.lidalia.sysoutslf4j.common;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/common/PrintStreamCoordinator.class */
public interface PrintStreamCoordinator {
    void replaceSystemOutputsWithSLF4JPrintStreams();

    void restoreOriginalSystemOutputs();
}
