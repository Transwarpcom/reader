package org.springframework.beans.factory.xml;

import org.springframework.lang.Nullable;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/xml/NamespaceHandlerResolver.class */
public interface NamespaceHandlerResolver {
    @Nullable
    NamespaceHandler resolve(String str);
}
