package io.vertx.core.dns.impl;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.MaxMessagesRecvByteBufAllocator;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.handler.codec.dns.DatagramDnsQuery;
import io.netty.handler.codec.dns.DatagramDnsQueryEncoder;
import io.netty.handler.codec.dns.DatagramDnsResponseDecoder;
import io.netty.handler.codec.dns.DefaultDnsQuestion;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsRecordType;
import io.netty.handler.codec.dns.DnsResponse;
import io.netty.handler.codec.dns.DnsSection;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import io.netty.util.concurrent.GenericFutureListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxException;
import io.vertx.core.dns.DnsClient;
import io.vertx.core.dns.DnsClientOptions;
import io.vertx.core.dns.DnsException;
import io.vertx.core.dns.DnsResponseCode;
import io.vertx.core.dns.MxRecord;
import io.vertx.core.dns.SrvRecord;
import io.vertx.core.dns.impl.decoder.RecordDecoder;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.impl.PartialPooledByteBufAllocator;
import io.vertx.core.net.impl.transport.Transport;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/dns/impl/DnsClientImpl.class */
public final class DnsClientImpl implements DnsClient {
    private static final char[] HEX_TABLE = "0123456789abcdef".toCharArray();
    private final Vertx vertx;
    private final IntObjectMap<Query> inProgressMap = new IntObjectHashMap();
    private final InetSocketAddress dnsServer;
    private final ContextInternal actualCtx;
    private final DatagramChannel channel;
    private final DnsClientOptions options;

    public DnsClientImpl(VertxInternal vertx, DnsClientOptions options) {
        Objects.requireNonNull(options, "no null options accepted");
        Objects.requireNonNull(options.getHost(), "no null host accepted");
        this.options = new DnsClientOptions(options);
        ContextInternal creatingContext = vertx.getContext();
        if (creatingContext != null && creatingContext.isMultiThreadedWorkerContext()) {
            throw new IllegalStateException("Cannot use DnsClient in a multi-threaded worker verticle");
        }
        this.dnsServer = new InetSocketAddress(options.getHost(), options.getPort());
        if (this.dnsServer.isUnresolved()) {
            throw new IllegalArgumentException("Cannot resolve the host to a valid ip address");
        }
        this.vertx = vertx;
        Transport transport = vertx.transport();
        this.actualCtx = vertx.getOrCreateContext();
        this.channel = transport.datagramChannel(this.dnsServer.getAddress() instanceof Inet4Address ? InternetProtocolFamily.IPv4 : InternetProtocolFamily.IPv6);
        this.channel.config().setOption(ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION, true);
        MaxMessagesRecvByteBufAllocator bufAllocator = (MaxMessagesRecvByteBufAllocator) this.channel.config().getRecvByteBufAllocator();
        bufAllocator.maxMessagesPerRead(1);
        this.channel.config().setAllocator((ByteBufAllocator) PartialPooledByteBufAllocator.INSTANCE);
        this.actualCtx.nettyEventLoop().register(this.channel);
        if (options.getLogActivity()) {
            this.channel.pipeline().addLast("logging", new LoggingHandler());
        }
        this.channel.pipeline().addLast(new DatagramDnsQueryEncoder());
        this.channel.pipeline().addLast(new DatagramDnsResponseDecoder());
        this.channel.pipeline().addLast(new SimpleChannelInboundHandler<DnsResponse>() { // from class: io.vertx.core.dns.impl.DnsClientImpl.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.netty.channel.SimpleChannelInboundHandler
            public void channelRead0(ChannelHandlerContext ctx, DnsResponse msg) throws Exception {
                int id = msg.id();
                Query query = (Query) DnsClientImpl.this.inProgressMap.get(id);
                if (query != null) {
                    query.handle(msg);
                }
            }
        });
    }

    @Override // io.vertx.core.dns.DnsClient
    public DnsClient lookup4(String name, Handler<AsyncResult<String>> handler) {
        lookupSingle(name, handler, DnsRecordType.A);
        return this;
    }

    @Override // io.vertx.core.dns.DnsClient
    public DnsClient lookup6(String name, Handler<AsyncResult<String>> handler) {
        lookupSingle(name, handler, DnsRecordType.AAAA);
        return this;
    }

    @Override // io.vertx.core.dns.DnsClient
    public DnsClient lookup(String name, Handler<AsyncResult<String>> handler) {
        lookupSingle(name, handler, DnsRecordType.A, DnsRecordType.AAAA);
        return this;
    }

    @Override // io.vertx.core.dns.DnsClient
    public DnsClient resolveA(String name, Handler<AsyncResult<List<String>>> handler) {
        lookupList(name, handler, DnsRecordType.A);
        return this;
    }

    @Override // io.vertx.core.dns.DnsClient
    public DnsClient resolveCNAME(String name, Handler<AsyncResult<List<String>>> handler) {
        lookupList(name, handler, DnsRecordType.CNAME);
        return this;
    }

    @Override // io.vertx.core.dns.DnsClient
    public DnsClient resolveMX(String name, Handler<AsyncResult<List<MxRecord>>> handler) {
        lookupList(name, handler, DnsRecordType.MX);
        return this;
    }

    @Override // io.vertx.core.dns.DnsClient
    public DnsClient resolveTXT(String name, final Handler<AsyncResult<List<String>>> handler) {
        lookupList(name, new Handler<AsyncResult<List<String>>>() { // from class: io.vertx.core.dns.impl.DnsClientImpl.2
            @Override // io.vertx.core.Handler
            public /* bridge */ /* synthetic */ void handle(AsyncResult<List<String>> asyncResult) {
                handle2((AsyncResult) asyncResult);
            }

            /* renamed from: handle, reason: avoid collision after fix types in other method */
            public void handle2(AsyncResult event) {
                if (event.failed()) {
                    handler.handle(event);
                    return;
                }
                List<String> txts = new ArrayList<>();
                List<List<String>> records = (List) event.result();
                for (List<String> txt : records) {
                    txts.addAll(txt);
                }
                handler.handle(Future.succeededFuture(txts));
            }
        }, DnsRecordType.TXT);
        return this;
    }

    @Override // io.vertx.core.dns.DnsClient
    public DnsClient resolvePTR(String name, Handler<AsyncResult<String>> handler) {
        lookupSingle(name, handler, DnsRecordType.PTR);
        return this;
    }

    @Override // io.vertx.core.dns.DnsClient
    public DnsClient resolveAAAA(String name, Handler<AsyncResult<List<String>>> handler) {
        lookupList(name, handler, DnsRecordType.AAAA);
        return this;
    }

    @Override // io.vertx.core.dns.DnsClient
    public DnsClient resolveNS(String name, Handler<AsyncResult<List<String>>> handler) {
        lookupList(name, handler, DnsRecordType.NS);
        return this;
    }

    @Override // io.vertx.core.dns.DnsClient
    public DnsClient resolveSRV(String name, Handler<AsyncResult<List<SrvRecord>>> handler) {
        lookupList(name, handler, DnsRecordType.SRV);
        return this;
    }

    @Override // io.vertx.core.dns.DnsClient
    public DnsClient reverseLookup(String address, Handler<AsyncResult<String>> handler) throws UnknownHostException {
        try {
            InetAddress inetAddress = InetAddress.getByName(address);
            byte[] addr = inetAddress.getAddress();
            StringBuilder reverseName = new StringBuilder(64);
            if (inetAddress instanceof Inet4Address) {
                reverseName.append(addr[3] & 255).append(".").append(addr[2] & 255).append(".").append(addr[1] & 255).append(".").append(addr[0] & 255);
            } else {
                for (int i = 0; i < 16; i++) {
                    reverseName.append(HEX_TABLE[addr[15 - i] & 15]);
                    reverseName.append(".");
                    reverseName.append(HEX_TABLE[(addr[15 - i] >> 4) & 15]);
                    if (i != 15) {
                        reverseName.append(".");
                    }
                }
            }
            reverseName.append(".in-addr.arpa");
            return resolvePTR(reverseName.toString(), handler);
        } catch (UnknownHostException e) {
            this.actualCtx.runOnContext(v -> {
                handler.handle(Future.failedFuture(e));
            });
            return this;
        }
    }

    private <T> void lookupSingle(String name, Handler<AsyncResult<T>> handler, DnsRecordType... types) {
        lookupList(name, ar -> {
            handler.handle(ar.map(result -> {
                if (result.isEmpty()) {
                    return null;
                }
                return result.get(0);
            }));
        }, types);
    }

    private <T> void lookupList(String name, Handler<AsyncResult<List<T>>> handler, DnsRecordType... types) {
        Objects.requireNonNull(name, "no null name accepted");
        EventLoop el = this.actualCtx.nettyEventLoop();
        if (el.inEventLoop()) {
            new Query(name, types, handler).run();
        } else {
            el.execute(() -> {
                new Query(name, types, handler).run();
            });
        }
    }

    public void inProgressQueries(Handler<Integer> handler) {
        this.actualCtx.runOnContext(v -> {
            handler.handle(Integer.valueOf(this.inProgressMap.size()));
        });
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/dns/impl/DnsClientImpl$Query.class */
    private class Query<T> {
        final DatagramDnsQuery msg;
        final Promise<List<T>> promise;
        final String name;
        final DnsRecordType[] types;
        long timerID;

        public Query(String name, DnsRecordType[] types, Handler<AsyncResult<List<T>>> handler) {
            Promise<List<T>> promise = Promise.promise();
            promise.future().setHandler2(handler);
            this.msg = new DatagramDnsQuery(null, DnsClientImpl.this.dnsServer, ThreadLocalRandom.current().nextInt()).setRecursionDesired(DnsClientImpl.this.options.isRecursionDesired());
            for (DnsRecordType type : types) {
                this.msg.addRecord(DnsSection.QUESTION, (DnsRecord) new DefaultDnsQuestion(name, type, 1));
            }
            this.promise = promise;
            this.types = types;
            this.name = name;
        }

        private void fail(Throwable cause) {
            DnsClientImpl.this.inProgressMap.remove(this.msg.id());
            if (this.timerID >= 0) {
                DnsClientImpl.this.vertx.cancelTimer(this.timerID);
            }
            this.promise.tryFail(cause);
        }

        void handle(DnsResponse msg) {
            DnsResponseCode code = DnsResponseCode.valueOf(msg.code().intValue());
            if (code == DnsResponseCode.NOERROR) {
                DnsClientImpl.this.inProgressMap.remove(msg.id());
                if (this.timerID >= 0) {
                    DnsClientImpl.this.vertx.cancelTimer(this.timerID);
                }
                int count = msg.count(DnsSection.ANSWER);
                ArrayList arrayList = new ArrayList(count);
                for (int idx = 0; idx < count; idx++) {
                    DnsRecord a = msg.recordAt(DnsSection.ANSWER, idx);
                    Object objDecode = RecordDecoder.decode(a);
                    if (isRequestedType(a.type(), this.types)) {
                        arrayList.add(objDecode);
                    }
                }
                if (arrayList.size() > 0 && ((arrayList.get(0) instanceof MxRecordImpl) || (arrayList.get(0) instanceof SrvRecordImpl))) {
                    Collections.sort(arrayList);
                }
                DnsClientImpl.this.actualCtx.executeFromIO(v -> {
                    this.promise.tryComplete(arrayList);
                });
                return;
            }
            DnsClientImpl.this.actualCtx.executeFromIO(new DnsException(code), (v1) -> {
                fail(v1);
            });
        }

        void run() {
            DnsClientImpl.this.inProgressMap.put(this.msg.id(), (int) this);
            this.timerID = DnsClientImpl.this.vertx.setTimer(DnsClientImpl.this.options.getQueryTimeout(), id -> {
                this.timerID = -1L;
                DnsClientImpl.this.actualCtx.runOnContext(v -> {
                    fail(new VertxException("DNS query timeout for " + this.name));
                });
            });
            DnsClientImpl.this.channel.writeAndFlush(this.msg).addListener2((GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>>) future -> {
                if (!future.isSuccess()) {
                    DnsClientImpl.this.actualCtx.executeFromIO(future.cause(), this::fail);
                }
            });
        }

        private boolean isRequestedType(DnsRecordType dnsRecordType, DnsRecordType[] types) {
            for (DnsRecordType t : types) {
                if (t.equals(dnsRecordType)) {
                    return true;
                }
            }
            return false;
        }
    }
}
