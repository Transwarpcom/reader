package org.springframework.beans.factory.support;

import java.security.AccessControlContext;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/support/SecurityContextProvider.class */
public interface SecurityContextProvider {
    AccessControlContext getAccessControlContext();
}
