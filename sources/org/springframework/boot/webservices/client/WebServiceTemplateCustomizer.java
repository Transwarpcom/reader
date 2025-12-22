package org.springframework.boot.webservices.client;

import org.springframework.ws.client.core.WebServiceTemplate;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/webservices/client/WebServiceTemplateCustomizer.class */
public interface WebServiceTemplateCustomizer {
    void customize(WebServiceTemplate webServiceTemplate);
}
