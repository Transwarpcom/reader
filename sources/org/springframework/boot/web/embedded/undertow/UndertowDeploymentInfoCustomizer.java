package org.springframework.boot.web.embedded.undertow;

import io.undertow.servlet.api.DeploymentInfo;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/embedded/undertow/UndertowDeploymentInfoCustomizer.class */
public interface UndertowDeploymentInfoCustomizer {
    void customize(DeploymentInfo deploymentInfo);
}
