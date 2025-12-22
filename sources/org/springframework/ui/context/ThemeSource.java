package org.springframework.ui.context;

import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/ui/context/ThemeSource.class */
public interface ThemeSource {
    @Nullable
    Theme getTheme(String str);
}
