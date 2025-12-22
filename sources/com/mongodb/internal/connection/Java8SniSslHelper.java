package com.mongodb.internal.connection;

import com.mongodb.ServerAddress;
import java.util.Collections;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLParameters;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/Java8SniSslHelper.class */
final class Java8SniSslHelper implements SniSslHelper {
    static {
        try {
            Class.forName("javax.net.ssl.SNIHostName");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @Override // com.mongodb.internal.connection.SniSslHelper
    public void enableSni(ServerAddress address, SSLParameters sslParameters) {
        try {
            SNIServerName sniHostName = new SNIHostName(address.getHost());
            sslParameters.setServerNames(Collections.singletonList(sniHostName));
        } catch (IllegalArgumentException e) {
        }
    }

    Java8SniSslHelper() {
    }
}
