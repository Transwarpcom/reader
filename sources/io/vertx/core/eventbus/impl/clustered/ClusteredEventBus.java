package io.vertx.core.eventbus.impl.clustered;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.impl.CodecManager;
import io.vertx.core.eventbus.impl.EventBusImpl;
import io.vertx.core.eventbus.impl.HandlerHolder;
import io.vertx.core.eventbus.impl.MessageImpl;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.core.impl.HAManager;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.core.net.TCPSSLOptions;
import io.vertx.core.net.TrustOptions;
import io.vertx.core.net.impl.ServerID;
import io.vertx.core.parsetools.RecordParser;
import io.vertx.core.spi.cluster.AsyncMultiMap;
import io.vertx.core.spi.cluster.ChoosableIterable;
import io.vertx.core.spi.cluster.ClusterManager;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/clustered/ClusteredEventBus.class */
public class ClusteredEventBus extends EventBusImpl {
    public static final String CLUSTER_PUBLIC_HOST_PROP_NAME = "vertx.cluster.public.host";
    public static final String CLUSTER_PUBLIC_PORT_PROP_NAME = "vertx.cluster.public.port";
    private static final String SERVER_ID_HA_KEY = "server_id";
    private static final String SUBS_MAP_NAME = "__vertx.subs";
    private final ClusterManager clusterManager;
    private final ConcurrentMap<ServerID, ConnectionHolder> connections;
    private final Context sendNoContext;
    private EventBusOptions options;
    private AsyncMultiMap<String, ClusterNodeInfo> subs;
    private Set<String> ownSubs;
    private ServerID serverID;
    private ClusterNodeInfo nodeInfo;
    private NetServer server;
    private static final Logger log = LoggerFactory.getLogger((Class<?>) ClusteredEventBus.class);
    private static final Buffer PONG = Buffer.buffer(new byte[]{1});

    private static /* synthetic */ Object $deserializeLambda$(SerializedLambda lambda) {
        switch (lambda.getImplMethodName()) {
            case "lambda$null$51267305$1":
                if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("java/util/function/Predicate") && lambda.getFunctionalInterfaceMethodName().equals("test") && lambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Z") && lambda.getImplClass().equals("io/vertx/core/eventbus/impl/clustered/ClusteredEventBus") && lambda.getImplMethodSignature().equals("(Ljava/util/Set;Lio/vertx/core/eventbus/impl/clustered/ClusterNodeInfo;)Z")) {
                    Set set = (Set) lambda.getCapturedArg(0);
                    return ci -> {
                        return !set.contains(ci.nodeId);
                    };
                }
                break;
        }
        throw new IllegalArgumentException("Invalid lambda deserialization");
    }

    public ClusteredEventBus(VertxInternal vertx, VertxOptions options, ClusterManager clusterManager) {
        super(vertx);
        this.connections = new ConcurrentHashMap();
        this.ownSubs = new ConcurrentHashSet();
        this.options = options.getEventBusOptions();
        this.clusterManager = clusterManager;
        this.sendNoContext = vertx.getOrCreateContext();
    }

    private NetServerOptions getServerOptions() {
        NetServerOptions serverOptions = new NetServerOptions(this.options.toJson());
        setCertOptions(serverOptions, this.options.getKeyCertOptions());
        setTrustOptions(serverOptions, this.options.getTrustOptions());
        return serverOptions;
    }

    static void setCertOptions(TCPSSLOptions options, KeyCertOptions keyCertOptions) {
        if (keyCertOptions == null) {
            return;
        }
        if (keyCertOptions instanceof JksOptions) {
            options.setKeyStoreOptions((JksOptions) keyCertOptions);
        } else if (keyCertOptions instanceof PfxOptions) {
            options.setPfxKeyCertOptions((PfxOptions) keyCertOptions);
        } else {
            options.setPemKeyCertOptions((PemKeyCertOptions) keyCertOptions);
        }
    }

    static void setTrustOptions(TCPSSLOptions sslOptions, TrustOptions options) {
        if (options == null) {
            return;
        }
        if (options instanceof JksOptions) {
            sslOptions.setTrustStoreOptions((JksOptions) options);
        } else if (options instanceof PfxOptions) {
            sslOptions.setPfxTrustOptions((PfxOptions) options);
        } else {
            sslOptions.setPemTrustOptions((PemTrustOptions) options);
        }
    }

    @Override // io.vertx.core.eventbus.impl.EventBusImpl, io.vertx.core.eventbus.EventBus
    public void start(Handler<AsyncResult<Void>> resultHandler) {
        HAManager haManager = this.vertx.haManager();
        setClusterViewChangedHandler(haManager);
        this.clusterManager.getAsyncMultiMap(SUBS_MAP_NAME, ar1 -> {
            if (ar1.succeeded()) {
                this.subs = (AsyncMultiMap) ar1.result();
                this.server = this.vertx.createNetServer(getServerOptions());
                this.server.connectHandler(getServerHandler());
                this.server.listen(asyncResult -> {
                    if (asyncResult.succeeded()) {
                        int serverPort = getClusterPublicPort(this.options, this.server.actualPort());
                        String serverHost = getClusterPublicHost(this.options);
                        this.serverID = new ServerID(serverPort, serverHost);
                        this.nodeInfo = new ClusterNodeInfo(this.clusterManager.getNodeID(), this.serverID);
                        this.vertx.executeBlocking(fut -> {
                            haManager.addDataToAHAInfo(SERVER_ID_HA_KEY, new JsonObject().put("host", this.serverID.host).put(RtspHeaders.Values.PORT, Integer.valueOf(this.serverID.port)));
                            fut.complete();
                        }, false, ar2 -> {
                            if (ar2.succeeded()) {
                                this.started = true;
                                resultHandler.handle(Future.succeededFuture());
                            } else {
                                resultHandler.handle(Future.failedFuture(ar2.cause()));
                            }
                        });
                        return;
                    }
                    resultHandler.handle(Future.failedFuture(asyncResult.cause()));
                });
                return;
            }
            resultHandler.handle(Future.failedFuture(ar1.cause()));
        });
    }

    @Override // io.vertx.core.eventbus.impl.EventBusImpl, io.vertx.core.eventbus.EventBus
    public void close(Handler<AsyncResult<Void>> completionHandler) {
        super.close(ar1 -> {
            if (this.server != null) {
                this.server.close(ar -> {
                    if (ar.failed()) {
                        log.error("Failed to close server", ar.cause());
                    }
                    for (ConnectionHolder holder : this.connections.values()) {
                        holder.close();
                    }
                    if (completionHandler != null) {
                        completionHandler.handle(ar);
                    }
                });
            } else if (completionHandler != null) {
                completionHandler.handle(ar1);
            }
        });
    }

    @Override // io.vertx.core.eventbus.impl.EventBusImpl
    public MessageImpl createMessage(boolean send, String address, MultiMap headers, Object body, String codecName, Handler<AsyncResult<Void>> writeHandler) {
        Objects.requireNonNull(address, "no null address accepted");
        MessageCodec codec = this.codecManager.lookupCodec(body, codecName);
        ClusteredMessage msg = new ClusteredMessage(this.serverID, address, null, headers, body, codec, send, this, writeHandler);
        return msg;
    }

    @Override // io.vertx.core.eventbus.impl.EventBusImpl
    protected <T> void addRegistration(boolean newAddress, String address, boolean replyHandler, boolean localOnly, Handler<AsyncResult<Void>> completionHandler) {
        if (newAddress && this.subs != null && !replyHandler && !localOnly) {
            this.subs.add(address, this.nodeInfo, completionHandler);
            this.ownSubs.add(address);
        } else {
            completionHandler.handle(Future.succeededFuture());
        }
    }

    @Override // io.vertx.core.eventbus.impl.EventBusImpl
    protected <T> void removeRegistration(HandlerHolder<T> lastHolder, String address, Handler<AsyncResult<Void>> completionHandler) {
        if (lastHolder != null && this.subs != null && !lastHolder.isLocalOnly()) {
            this.ownSubs.remove(address);
            removeSub(address, this.nodeInfo, completionHandler);
        } else {
            callCompletionHandlerAsync(completionHandler);
        }
    }

    @Override // io.vertx.core.eventbus.impl.EventBusImpl
    protected <T> void sendReply(EventBusImpl.OutboundDeliveryContext<T> sendContext, MessageImpl replierMessage) {
        clusteredSendReply(((ClusteredMessage) replierMessage).getSender(), sendContext);
    }

    @Override // io.vertx.core.eventbus.impl.EventBusImpl
    protected <T> void sendOrPub(EventBusImpl.OutboundDeliveryContext<T> sendContext) {
        if (sendContext.options.isLocalOnly()) {
            if (this.metrics != null) {
                this.metrics.messageSent(sendContext.message.address(), !sendContext.message.isSend(), true, false);
            }
            deliverMessageLocally(sendContext);
        } else if (Vertx.currentContext() == null) {
            this.sendNoContext.runOnContext(v -> {
                this.subs.get(sendContext.message.address(), ar -> {
                    onSubsReceived(ar, sendContext);
                });
            });
        } else {
            this.subs.get(sendContext.message.address(), ar -> {
                onSubsReceived(ar, sendContext);
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void onSubsReceived(AsyncResult<ChoosableIterable<ClusterNodeInfo>> asyncResult, EventBusImpl.OutboundDeliveryContext<T> sendContext) {
        if (asyncResult.succeeded()) {
            ChoosableIterable<ClusterNodeInfo> serverIDs = asyncResult.result();
            if (serverIDs != null && !serverIDs.isEmpty()) {
                sendToSubs(serverIDs, sendContext);
                return;
            }
            if (this.metrics != null) {
                this.metrics.messageSent(sendContext.message.address(), !sendContext.message.isSend(), true, false);
            }
            deliverMessageLocally(sendContext);
            return;
        }
        log.error("Failed to send message", asyncResult.cause());
        Handler<AsyncResult<Void>> handlerWriteHandler = sendContext.message.writeHandler();
        if (handlerWriteHandler != 0) {
            handlerWriteHandler.handle(asyncResult.mapEmpty());
        }
    }

    @Override // io.vertx.core.eventbus.impl.EventBusImpl
    protected String generateReplyAddress() {
        return "__vertx.reply." + UUID.randomUUID().toString();
    }

    @Override // io.vertx.core.eventbus.impl.EventBusImpl
    protected boolean isMessageLocal(MessageImpl msg) {
        ClusteredMessage clusteredMessage = (ClusteredMessage) msg;
        return !clusteredMessage.isFromWire();
    }

    private void setClusterViewChangedHandler(HAManager haManager) {
        haManager.setClusterViewChangedHandler(members -> {
            this.ownSubs.forEach(address -> {
                this.subs.add(address, this.nodeInfo, addResult -> {
                    if (addResult.failed()) {
                        log.warn("Failed to update subs map with self", addResult.cause());
                    }
                });
            });
            this.subs.removeAllMatching((Serializable) ci -> {
                return !members.contains(ci.nodeId);
            }, removeResult -> {
                if (removeResult.failed()) {
                    log.warn("Error removing subs", removeResult.cause());
                }
            });
        });
    }

    private int getClusterPublicPort(EventBusOptions options, int actualPort) {
        int publicPort = Integer.getInteger(CLUSTER_PUBLIC_PORT_PROP_NAME, options.getClusterPublicPort()).intValue();
        if (publicPort == -1) {
            publicPort = actualPort;
        }
        return publicPort;
    }

    private String getClusterPublicHost(EventBusOptions options) {
        String publicHost = System.getProperty(CLUSTER_PUBLIC_HOST_PROP_NAME, options.getClusterPublicHost());
        if (publicHost == null) {
            publicHost = options.getHost();
        }
        return publicHost;
    }

    private Handler<NetSocket> getServerHandler() {
        return socket -> {
            final RecordParser parser = RecordParser.newFixed(4);
            Handler<Buffer> handler = new Handler<Buffer>() { // from class: io.vertx.core.eventbus.impl.clustered.ClusteredEventBus.1
                int size = -1;

                @Override // io.vertx.core.Handler
                public void handle(Buffer buff) {
                    if (this.size == -1) {
                        this.size = buff.getInt(0);
                        parser.fixedSizeMode(this.size);
                        return;
                    }
                    ClusteredMessage received = new ClusteredMessage();
                    received.readFromWire(buff, ClusteredEventBus.this.codecManager);
                    if (ClusteredEventBus.this.metrics != null) {
                        ClusteredEventBus.this.metrics.messageRead(received.address(), buff.length());
                    }
                    parser.fixedSizeMode(4);
                    this.size = -1;
                    if (received.codec() == CodecManager.PING_MESSAGE_CODEC) {
                        socket.write(ClusteredEventBus.PONG);
                    } else {
                        ClusteredEventBus.this.deliverMessageLocally(received);
                    }
                }
            };
            parser.setOutput(handler);
            socket.handler2((Handler<Buffer>) parser);
        };
    }

    private <T> void sendToSubs(ChoosableIterable<ClusterNodeInfo> subs, EventBusImpl.OutboundDeliveryContext<T> sendContext) {
        String address = sendContext.message.address();
        if (sendContext.message.isSend()) {
            ClusterNodeInfo ci = subs.choose();
            ServerID sid = ci == null ? null : ci.serverID;
            if (sid != null && !sid.equals(this.serverID)) {
                if (this.metrics != null) {
                    this.metrics.messageSent(address, false, false, true);
                }
                sendRemote(sid, sendContext.message);
                return;
            } else {
                if (this.metrics != null) {
                    this.metrics.messageSent(address, false, true, false);
                }
                deliverMessageLocally(sendContext);
                return;
            }
        }
        boolean local = false;
        boolean remote = false;
        for (ClusterNodeInfo ci2 : subs) {
            if (!ci2.serverID.equals(this.serverID)) {
                remote = true;
                sendRemote(ci2.serverID, sendContext.message);
            } else {
                local = true;
            }
        }
        if (this.metrics != null) {
            this.metrics.messageSent(address, true, local, remote);
        }
        if (local) {
            deliverMessageLocally(sendContext);
        }
    }

    private <T> void clusteredSendReply(ServerID replyDest, EventBusImpl.OutboundDeliveryContext<T> sendContext) {
        MessageImpl message = sendContext.message;
        String address = message.address();
        if (!replyDest.equals(this.serverID)) {
            if (this.metrics != null) {
                this.metrics.messageSent(address, false, false, true);
            }
            sendRemote(replyDest, message);
        } else {
            if (this.metrics != null) {
                this.metrics.messageSent(address, false, true, false);
            }
            deliverMessageLocally(sendContext);
        }
    }

    private void sendRemote(ServerID theServerID, MessageImpl message) {
        ConnectionHolder holder = this.connections.get(theServerID);
        if (holder == null) {
            holder = new ConnectionHolder(this, theServerID, this.options);
            ConnectionHolder prevHolder = this.connections.putIfAbsent(theServerID, holder);
            if (prevHolder != null) {
                holder = prevHolder;
            } else {
                holder.connect();
            }
        }
        holder.writeMessage((ClusteredMessage) message);
    }

    private void removeSub(String subName, ClusterNodeInfo node, Handler<AsyncResult<Void>> completionHandler) {
        this.subs.remove(subName, node, ar -> {
            if (!ar.succeeded()) {
                log.error("Failed to remove sub", ar.cause());
                return;
            }
            if (((Boolean) ar.result()).booleanValue()) {
                if (completionHandler != null) {
                    completionHandler.handle(Future.succeededFuture());
                }
            } else if (completionHandler != null) {
                completionHandler.handle(Future.failedFuture("sub not found"));
            }
        });
    }

    ConcurrentMap<ServerID, ConnectionHolder> connections() {
        return this.connections;
    }

    VertxInternal vertx() {
        return this.vertx;
    }

    EventBusOptions options() {
        return this.options;
    }
}
