package org.springframework.format;

import java.util.Locale;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/format/Printer.class */
public interface Printer<T> {
    String print(T t, Locale locale);
}
