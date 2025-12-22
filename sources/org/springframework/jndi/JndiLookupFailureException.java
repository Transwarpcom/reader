package org.springframework.jndi;

import javax.naming.NamingException;
import org.springframework.core.NestedRuntimeException;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/jndi/JndiLookupFailureException.class */
public class JndiLookupFailureException extends NestedRuntimeException {
    public JndiLookupFailureException(String msg, NamingException cause) {
        super(msg, cause);
    }
}
