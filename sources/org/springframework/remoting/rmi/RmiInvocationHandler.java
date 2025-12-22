package org.springframework.remoting.rmi;

import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import org.springframework.lang.Nullable;
import org.springframework.remoting.support.RemoteInvocation;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/remoting/rmi/RmiInvocationHandler.class */
public interface RmiInvocationHandler extends Remote {
    @Nullable
    String getTargetInterfaceName() throws RemoteException;

    @Nullable
    Object invoke(RemoteInvocation remoteInvocation) throws IllegalAccessException, NoSuchMethodException, RemoteException, InvocationTargetException;
}
