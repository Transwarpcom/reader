package org.springframework.context.i18n;

import java.util.TimeZone;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/i18n/TimeZoneAwareLocaleContext.class */
public interface TimeZoneAwareLocaleContext extends LocaleContext {
    @Nullable
    TimeZone getTimeZone();
}
