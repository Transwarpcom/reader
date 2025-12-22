package org.springframework.boot.web.embedded.tomcat;

import org.apache.catalina.Context;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/embedded/tomcat/TomcatContextCustomizer.class */
public interface TomcatContextCustomizer {
    void customize(Context context);
}
