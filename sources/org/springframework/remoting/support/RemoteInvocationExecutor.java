package org.springframework.remoting.support;

import java.lang.reflect.InvocationTargetException;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/remoting/support/RemoteInvocationExecutor.class */
public interface RemoteInvocationExecutor {
    Object invoke(RemoteInvocation remoteInvocation, Object obj) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
