package org.springframework.context;

import org.springframework.beans.factory.Aware;
import org.springframework.util.StringValueResolver;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/EmbeddedValueResolverAware.class */
public interface EmbeddedValueResolverAware extends Aware {
    void setEmbeddedValueResolver(StringValueResolver stringValueResolver);
}
