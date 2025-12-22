package io.netty.handler.codec.serialization;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-4.1.42.Final.jar:io/netty/handler/codec/serialization/ClassResolver.class */
public interface ClassResolver {
    Class<?> resolve(String str) throws ClassNotFoundException;
}
