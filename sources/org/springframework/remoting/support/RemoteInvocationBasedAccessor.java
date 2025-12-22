package org.springframework.remoting.support;

import org.aopalliance.intercept.MethodInvocation;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/remoting/support/RemoteInvocationBasedAccessor.class */
public abstract class RemoteInvocationBasedAccessor extends UrlBasedRemoteAccessor {
    private RemoteInvocationFactory remoteInvocationFactory = new DefaultRemoteInvocationFactory();

    public void setRemoteInvocationFactory(RemoteInvocationFactory remoteInvocationFactory) {
        this.remoteInvocationFactory = remoteInvocationFactory != null ? remoteInvocationFactory : new DefaultRemoteInvocationFactory();
    }

    public RemoteInvocationFactory getRemoteInvocationFactory() {
        return this.remoteInvocationFactory;
    }

    protected RemoteInvocation createRemoteInvocation(MethodInvocation methodInvocation) {
        return getRemoteInvocationFactory().createRemoteInvocation(methodInvocation);
    }

    protected Object recreateRemoteInvocationResult(RemoteInvocationResult result) throws Throwable {
        return result.recreate();
    }
}
