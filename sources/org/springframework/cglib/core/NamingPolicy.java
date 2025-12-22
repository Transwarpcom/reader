package org.springframework.cglib.core;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/cglib/core/NamingPolicy.class */
public interface NamingPolicy {
    String getClassName(String str, String str2, Object obj, Predicate predicate);

    boolean equals(Object obj);
}
