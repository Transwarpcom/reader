package org.springframework.aop;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/ClassFilter.class */
public interface ClassFilter {
    public static final ClassFilter TRUE = TrueClassFilter.INSTANCE;

    boolean matches(Class<?> cls);
}
