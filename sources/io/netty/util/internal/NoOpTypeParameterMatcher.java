package io.netty.util.internal;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/NoOpTypeParameterMatcher.class */
public final class NoOpTypeParameterMatcher extends TypeParameterMatcher {
    @Override // io.netty.util.internal.TypeParameterMatcher
    public boolean match(Object msg) {
        return true;
    }
}
