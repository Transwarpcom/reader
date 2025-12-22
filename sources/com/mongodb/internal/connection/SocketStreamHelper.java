package com.mongodb.internal.connection;

import com.mongodb.MongoInternalException;
import com.mongodb.ServerAddress;
import com.mongodb.connection.SocketSettings;
import com.mongodb.connection.SslSettings;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/SocketStreamHelper.class */
final class SocketStreamHelper {
    static void initialize(Socket socket, ServerAddress address, SocketSettings settings, SslSettings sslSettings) throws IOException {
        socket.setTcpNoDelay(true);
        socket.setSoTimeout(settings.getReadTimeout(TimeUnit.MILLISECONDS));
        socket.setKeepAlive(settings.isKeepAlive());
        if (settings.getReceiveBufferSize() > 0) {
            socket.setReceiveBufferSize(settings.getReceiveBufferSize());
        }
        if (settings.getSendBufferSize() > 0) {
            socket.setSendBufferSize(settings.getSendBufferSize());
        }
        if (sslSettings.isEnabled() || (socket instanceof SSLSocket)) {
            if (!(socket instanceof SSLSocket)) {
                throw new MongoInternalException("SSL is enabled but the socket is not an instance of javax.net.ssl.SSLSocket");
            }
            SSLSocket sslSocket = (SSLSocket) socket;
            SSLParameters sslParameters = sslSocket.getSSLParameters();
            if (sslParameters == null) {
                sslParameters = new SSLParameters();
            }
            SslHelper.enableSni(address, sslParameters);
            if (!sslSettings.isInvalidHostNameAllowed()) {
                SslHelper.enableHostNameVerification(sslParameters);
            }
            sslSocket.setSSLParameters(sslParameters);
        }
        socket.connect(address.getSocketAddress(), settings.getConnectTimeout(TimeUnit.MILLISECONDS));
    }

    private SocketStreamHelper() {
    }
}
