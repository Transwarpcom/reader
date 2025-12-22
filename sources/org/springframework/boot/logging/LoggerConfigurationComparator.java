package org.springframework.boot.logging;

import java.util.Comparator;
import org.springframework.util.Assert;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/logging/LoggerConfigurationComparator.class */
class LoggerConfigurationComparator implements Comparator<LoggerConfiguration> {
    private final String rootLoggerName;

    LoggerConfigurationComparator(String rootLoggerName) {
        Assert.notNull(rootLoggerName, "RootLoggerName must not be null");
        this.rootLoggerName = rootLoggerName;
    }

    @Override // java.util.Comparator
    public int compare(LoggerConfiguration o1, LoggerConfiguration o2) {
        if (this.rootLoggerName.equals(o1.getName())) {
            return -1;
        }
        if (this.rootLoggerName.equals(o2.getName())) {
            return 1;
        }
        return o1.getName().compareTo(o2.getName());
    }
}
