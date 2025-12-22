package io.netty.handler.proxy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.base64.Base64;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import io.vertx.ext.web.handler.FormLoginHandler;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-proxy-4.1.42.Final.jar:io/netty/handler/proxy/HttpProxyHandler.class */
public final class HttpProxyHandler extends ProxyHandler {
    private static final String PROTOCOL = "http";
    private static final String AUTH_BASIC = "basic";
    private final HttpClientCodec codec;
    private final String username;
    private final String password;
    private final CharSequence authorization;
    private final HttpHeaders outboundHeaders;
    private final boolean ignoreDefaultPortsInConnectHostHeader;
    private HttpResponseStatus status;
    private HttpHeaders inboundHeaders;

    public HttpProxyHandler(SocketAddress proxyAddress) {
        this(proxyAddress, null);
    }

    public HttpProxyHandler(SocketAddress proxyAddress, HttpHeaders headers) {
        this(proxyAddress, headers, false);
    }

    public HttpProxyHandler(SocketAddress proxyAddress, HttpHeaders headers, boolean ignoreDefaultPortsInConnectHostHeader) {
        super(proxyAddress);
        this.codec = new HttpClientCodec();
        this.username = null;
        this.password = null;
        this.authorization = null;
        this.outboundHeaders = headers;
        this.ignoreDefaultPortsInConnectHostHeader = ignoreDefaultPortsInConnectHostHeader;
    }

    public HttpProxyHandler(SocketAddress proxyAddress, String username, String password) {
        this(proxyAddress, username, password, null);
    }

    public HttpProxyHandler(SocketAddress proxyAddress, String username, String password, HttpHeaders headers) {
        this(proxyAddress, username, password, headers, false);
    }

    public HttpProxyHandler(SocketAddress proxyAddress, String username, String password, HttpHeaders headers, boolean ignoreDefaultPortsInConnectHostHeader) {
        super(proxyAddress);
        this.codec = new HttpClientCodec();
        if (username == null) {
            throw new NullPointerException(FormLoginHandler.DEFAULT_USERNAME_PARAM);
        }
        if (password == null) {
            throw new NullPointerException(FormLoginHandler.DEFAULT_PASSWORD_PARAM);
        }
        this.username = username;
        this.password = password;
        ByteBuf authz = Unpooled.copiedBuffer(username + ':' + password, CharsetUtil.UTF_8);
        ByteBuf authzBase64 = Base64.encode(authz, false);
        this.authorization = new AsciiString("Basic " + authzBase64.toString(CharsetUtil.US_ASCII));
        authz.release();
        authzBase64.release();
        this.outboundHeaders = headers;
        this.ignoreDefaultPortsInConnectHostHeader = ignoreDefaultPortsInConnectHostHeader;
    }

    @Override // io.netty.handler.proxy.ProxyHandler
    public String protocol() {
        return PROTOCOL;
    }

    @Override // io.netty.handler.proxy.ProxyHandler
    public String authScheme() {
        return this.authorization != null ? AUTH_BASIC : "none";
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    @Override // io.netty.handler.proxy.ProxyHandler
    protected void addCodec(ChannelHandlerContext ctx) throws Exception {
        ChannelPipeline p = ctx.pipeline();
        String name = ctx.name();
        p.addBefore(name, null, this.codec);
    }

    @Override // io.netty.handler.proxy.ProxyHandler
    protected void removeEncoder(ChannelHandlerContext ctx) throws Exception {
        this.codec.removeOutboundHandler();
    }

    @Override // io.netty.handler.proxy.ProxyHandler
    protected void removeDecoder(ChannelHandlerContext ctx) throws Exception {
        this.codec.removeInboundHandler();
    }

    @Override // io.netty.handler.proxy.ProxyHandler
    protected Object newInitialMessage(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress raddr = (InetSocketAddress) destinationAddress();
        String hostString = HttpUtil.formatHostnameForHttp(raddr);
        int port = raddr.getPort();
        String url = hostString + ":" + port;
        String hostHeader = (this.ignoreDefaultPortsInConnectHostHeader && (port == 80 || port == 443)) ? hostString : url;
        FullHttpRequest req = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.CONNECT, url, Unpooled.EMPTY_BUFFER, false);
        req.headers().set(HttpHeaderNames.HOST, hostHeader);
        if (this.authorization != null) {
            req.headers().set(HttpHeaderNames.PROXY_AUTHORIZATION, this.authorization);
        }
        if (this.outboundHeaders != null) {
            req.headers().add(this.outboundHeaders);
        }
        return req;
    }

    @Override // io.netty.handler.proxy.ProxyHandler
    protected boolean handleResponse(ChannelHandlerContext ctx, Object response) throws Exception {
        if (response instanceof HttpResponse) {
            if (this.status != null) {
                throw new HttpProxyConnectException(exceptionMessage("too many responses"), null);
            }
            HttpResponse res = (HttpResponse) response;
            this.status = res.status();
            this.inboundHeaders = res.headers();
        }
        boolean finished = response instanceof LastHttpContent;
        if (finished) {
            if (this.status == null) {
                throw new HttpProxyConnectException(exceptionMessage("missing response"), this.inboundHeaders);
            }
            if (this.status.code() != 200) {
                throw new HttpProxyConnectException(exceptionMessage("status: " + this.status), this.inboundHeaders);
            }
        }
        return finished;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/netty-handler-proxy-4.1.42.Final.jar:io/netty/handler/proxy/HttpProxyHandler$HttpProxyConnectException.class */
    public static final class HttpProxyConnectException extends ProxyConnectException {
        private static final long serialVersionUID = -8824334609292146066L;
        private final HttpHeaders headers;

        public HttpProxyConnectException(String message, HttpHeaders headers) {
            super(message);
            this.headers = headers;
        }

        public HttpHeaders headers() {
            return this.headers;
        }
    }
}
