package io.netty.channel;

import java.net.ConnectException;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/ConnectTimeoutException.class */
public class ConnectTimeoutException extends ConnectException {
    private static final long serialVersionUID = 2317065249988317463L;

    public ConnectTimeoutException(String msg) {
        super(msg);
    }

    public ConnectTimeoutException() {
    }
}
