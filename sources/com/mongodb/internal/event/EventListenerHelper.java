package com.mongodb.internal.event;

import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.connection.ServerSettings;
import com.mongodb.event.ClusterListener;
import com.mongodb.event.ClusterListenerAdapter;
import com.mongodb.event.CommandListener;
import com.mongodb.event.ConnectionPoolListener;
import com.mongodb.event.ConnectionPoolListenerAdapter;
import com.mongodb.event.ServerListener;
import com.mongodb.event.ServerListenerAdapter;
import com.mongodb.event.ServerMonitorListener;
import com.mongodb.event.ServerMonitorListenerAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/event/EventListenerHelper.class */
public final class EventListenerHelper {
    public static final ServerListener NO_OP_SERVER_LISTENER = new ServerListenerAdapter() { // from class: com.mongodb.internal.event.EventListenerHelper.1
    };
    public static final ServerMonitorListener NO_OP_SERVER_MONITOR_LISTENER = new ServerMonitorListenerAdapter() { // from class: com.mongodb.internal.event.EventListenerHelper.2
    };
    public static final ClusterListener NO_OP_CLUSTER_LISTENER = new ClusterListenerAdapter() { // from class: com.mongodb.internal.event.EventListenerHelper.3
    };
    public static final ConnectionPoolListener NO_OP_CONNECTION_POOL_LISTENER = new ConnectionPoolListenerAdapter() { // from class: com.mongodb.internal.event.EventListenerHelper.4
    };

    public static ClusterListener getClusterListener(ClusterSettings clusterSettings) {
        switch (clusterSettings.getClusterListeners().size()) {
            case 0:
                return NO_OP_CLUSTER_LISTENER;
            case 1:
                return clusterSettings.getClusterListeners().get(0);
            default:
                return new ClusterListenerMulticaster(clusterSettings.getClusterListeners());
        }
    }

    public static CommandListener getCommandListener(List<CommandListener> commandListeners) {
        switch (commandListeners.size()) {
            case 0:
                return null;
            case 1:
                return commandListeners.get(0);
            default:
                return new CommandListenerMulticaster(commandListeners);
        }
    }

    public static ConnectionPoolListener getConnectionPoolListener(ConnectionPoolSettings connectionPoolSettings) {
        switch (connectionPoolSettings.getConnectionPoolListeners().size()) {
            case 0:
                return NO_OP_CONNECTION_POOL_LISTENER;
            case 1:
                return connectionPoolSettings.getConnectionPoolListeners().get(0);
            default:
                return new ConnectionPoolListenerMulticaster(connectionPoolSettings.getConnectionPoolListeners());
        }
    }

    public static ServerMonitorListener getServerMonitorListener(ServerSettings serverSettings) {
        switch (serverSettings.getServerMonitorListeners().size()) {
            case 0:
                return NO_OP_SERVER_MONITOR_LISTENER;
            case 1:
                return serverSettings.getServerMonitorListeners().get(0);
            default:
                return new ServerMonitorListenerMulticaster(serverSettings.getServerMonitorListeners());
        }
    }

    public static ServerListener createServerListener(ServerSettings serverSettings, ServerListener additionalServerListener) {
        List<ServerListener> mergedServerListeners = new ArrayList<>();
        if (additionalServerListener != null) {
            mergedServerListeners.add(additionalServerListener);
        }
        mergedServerListeners.addAll(serverSettings.getServerListeners());
        switch (mergedServerListeners.size()) {
            case 0:
                return NO_OP_SERVER_LISTENER;
            case 1:
                return mergedServerListeners.get(0);
            default:
                return new ServerListenerMulticaster(mergedServerListeners);
        }
    }

    private EventListenerHelper() {
    }
}
