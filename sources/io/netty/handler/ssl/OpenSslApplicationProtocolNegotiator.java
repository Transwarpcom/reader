package io.netty.handler.ssl;

import io.netty.handler.ssl.ApplicationProtocolConfig;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/ssl/OpenSslApplicationProtocolNegotiator.class */
public interface OpenSslApplicationProtocolNegotiator extends ApplicationProtocolNegotiator {
    ApplicationProtocolConfig.Protocol protocol();

    ApplicationProtocolConfig.SelectorFailureBehavior selectorFailureBehavior();

    ApplicationProtocolConfig.SelectedListenerFailureBehavior selectedListenerFailureBehavior();
}
