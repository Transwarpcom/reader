package io.netty.handler.codec.marshalling;

import io.netty.channel.ChannelHandlerContext;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-4.1.42.Final.jar:io/netty/handler/codec/marshalling/DefaultUnmarshallerProvider.class */
public class DefaultUnmarshallerProvider implements UnmarshallerProvider {
    private final MarshallerFactory factory;
    private final MarshallingConfiguration config;

    public DefaultUnmarshallerProvider(MarshallerFactory factory, MarshallingConfiguration config) {
        this.factory = factory;
        this.config = config;
    }

    @Override // io.netty.handler.codec.marshalling.UnmarshallerProvider
    public Unmarshaller getUnmarshaller(ChannelHandlerContext ctx) throws Exception {
        return this.factory.createUnmarshaller(this.config);
    }
}
