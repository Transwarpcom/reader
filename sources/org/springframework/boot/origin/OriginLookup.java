package org.springframework.boot.origin;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/origin/OriginLookup.class */
public interface OriginLookup<K> {
    Origin getOrigin(K key);

    static <K> Origin getOrigin(Object source, K key) {
        if (!(source instanceof OriginLookup)) {
            return null;
        }
        try {
            return ((OriginLookup) source).getOrigin(key);
        } catch (Throwable th) {
            return null;
        }
    }
}
