package com.mongodb.internal.operation;

import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.ServerVersion;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/operation/ServerVersionHelper.class */
public final class ServerVersionHelper {
    public static boolean serverIsAtLeastVersionThreeDotZero(ConnectionDescription description) {
        return serverIsAtLeastVersion(description, new ServerVersion(3, 0));
    }

    public static boolean serverIsAtLeastVersionThreeDotTwo(ConnectionDescription description) {
        return serverIsAtLeastVersion(description, new ServerVersion(3, 2));
    }

    public static boolean serverIsAtLeastVersionThreeDotFour(ConnectionDescription description) {
        return serverIsAtLeastVersion(description, new ServerVersion(3, 4));
    }

    public static boolean serverIsAtLeastVersionThreeDotSix(ConnectionDescription description) {
        return serverIsAtLeastVersion(description, new ServerVersion(3, 6));
    }

    public static boolean serverIsAtLeastVersionFourDotZero(ConnectionDescription description) {
        return serverIsAtLeastVersion(description, new ServerVersion(4, 0));
    }

    private static boolean serverIsAtLeastVersion(ConnectionDescription description, ServerVersion serverVersion) {
        return description.getServerVersion().compareTo(serverVersion) >= 0;
    }

    private ServerVersionHelper() {
    }
}
