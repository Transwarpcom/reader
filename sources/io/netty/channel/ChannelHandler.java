package io.netty.channel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/ChannelHandler.class */
public interface ChannelHandler {

    @Target({ElementType.TYPE})
    @Inherited
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/ChannelHandler$Sharable.class */
    public @interface Sharable {
    }

    void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception;

    void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception;

    @Deprecated
    void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception;
}
