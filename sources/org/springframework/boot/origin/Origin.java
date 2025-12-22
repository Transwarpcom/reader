package org.springframework.boot.origin;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/origin/Origin.class */
public interface Origin {
    static Origin from(Object source) {
        if (source instanceof Origin) {
            return (Origin) source;
        }
        Origin origin = null;
        if (source != null && (source instanceof OriginProvider)) {
            origin = ((OriginProvider) source).getOrigin();
        }
        if (origin == null && source != null && (source instanceof Throwable)) {
            return from(((Throwable) source).getCause());
        }
        return origin;
    }
}
