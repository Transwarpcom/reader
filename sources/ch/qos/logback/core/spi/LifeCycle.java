package ch.qos.logback.core.spi;

/* loaded from: reader.jar:BOOT-INF/lib/logback-core-1.2.3.jar:ch/qos/logback/core/spi/LifeCycle.class */
public interface LifeCycle {
    void start();

    void stop();

    boolean isStarted();
}
