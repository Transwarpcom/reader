package org.springframework.boot.web.server;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/server/ErrorPageRegistry.class */
public interface ErrorPageRegistry {
    void addErrorPages(ErrorPage... errorPages);
}
