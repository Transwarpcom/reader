package org.springframework.core.env;

import java.util.function.Predicate;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/env/Profiles.class */
public interface Profiles {
    boolean matches(Predicate<String> predicate);

    static Profiles of(String... profiles) {
        return ProfilesParser.parse(profiles);
    }
}
