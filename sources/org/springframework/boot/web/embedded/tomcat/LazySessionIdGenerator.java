package org.springframework.boot.web.embedded.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.util.StandardSessionIdGenerator;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/embedded/tomcat/LazySessionIdGenerator.class */
class LazySessionIdGenerator extends StandardSessionIdGenerator {
    LazySessionIdGenerator() {
    }

    protected void startInternal() throws LifecycleException {
        setState(LifecycleState.STARTING);
    }
}
