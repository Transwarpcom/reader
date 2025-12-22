package org.springframework.remoting.support;

import org.aopalliance.intercept.MethodInvocation;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/remoting/support/RemoteInvocationFactory.class */
public interface RemoteInvocationFactory {
    RemoteInvocation createRemoteInvocation(MethodInvocation methodInvocation);
}
