package uk.org.lidalia.sysoutslf4j.common;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/common/LoggerAppender.class */
public interface LoggerAppender {
    void append(String str);

    void appendAndLog(String str, String str2, boolean z);
}
