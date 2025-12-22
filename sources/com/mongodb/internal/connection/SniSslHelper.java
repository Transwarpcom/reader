package com.mongodb.internal.connection;

import com.mongodb.ServerAddress;
import javax.net.ssl.SSLParameters;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/SniSslHelper.class */
interface SniSslHelper {
    void enableSni(ServerAddress serverAddress, SSLParameters sSLParameters);
}
