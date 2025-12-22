package io.vertx.core.impl;

import io.netty.resolver.AddressResolverGroup;
import io.netty.util.concurrent.Future;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.impl.launcher.commands.ExecUtils;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.resolver.ResolverProvider;
import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/AddressResolver.class */
public class AddressResolver {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) AddressResolver.class);
    private static final Pattern NDOTS_OPTIONS_PATTERN = resolvOption("ndots:[ \\t\\f]*(\\d)+");
    private static final Pattern ROTATE_OPTIONS_PATTERN = resolvOption("rotate");
    public static final int DEFAULT_NDOTS_RESOLV_OPTION;
    public static final boolean DEFAULT_ROTATE_RESOLV_OPTION;
    private final Vertx vertx;
    private final AddressResolverGroup<InetSocketAddress> resolverGroup;
    private final ResolverProvider provider;

    static {
        int ndots = 1;
        boolean rotate = false;
        if (ExecUtils.isLinux()) {
            File f = new File("/etc/resolv.conf");
            try {
                if (f.exists() && f.isFile()) {
                    String conf = new String(Files.readAllBytes(f.toPath()));
                    int ndotsOption = parseNdotsOptionFromResolvConf(conf);
                    if (ndotsOption != -1) {
                        ndots = ndotsOption;
                    }
                    rotate = parseRotateOptionFromResolvConf(conf);
                }
            } catch (Throwable t) {
                log.debug("Failed to load options from /etc/resolv/.conf", t);
            }
        }
        DEFAULT_NDOTS_RESOLV_OPTION = ndots;
        DEFAULT_ROTATE_RESOLV_OPTION = rotate;
    }

    private static Pattern resolvOption(String regex) {
        return Pattern.compile("^[ \\t\\f]*options[^\n]+" + regex + "(?=$|\\s)", 8);
    }

    public AddressResolver(Vertx vertx, AddressResolverOptions options) {
        this.provider = ResolverProvider.factory(vertx, options);
        this.resolverGroup = this.provider.resolver(options);
        this.vertx = vertx;
    }

    public void resolveHostname(String hostname, Handler<AsyncResult<InetAddress>> resultHandler) {
        ContextInternal callback = (ContextInternal) this.vertx.getOrCreateContext();
        Future<InetSocketAddress> fut = this.resolverGroup.getResolver(callback.nettyEventLoop()).resolve(InetSocketAddress.createUnresolved(hostname, 0));
        fut.addListener2(a -> {
            callback.runOnContext(v -> {
                if (a.isSuccess()) {
                    InetSocketAddress address = (InetSocketAddress) fut.getNow();
                    resultHandler.handle(io.vertx.core.Future.succeededFuture(address.getAddress()));
                } else {
                    resultHandler.handle(io.vertx.core.Future.failedFuture(a.cause()));
                }
            });
        });
    }

    AddressResolverGroup<InetSocketAddress> nettyAddressResolverGroup() {
        return this.resolverGroup;
    }

    public void close(Handler<Void> doneHandler) {
        this.provider.close(doneHandler);
    }

    public static int parseNdotsOptionFromResolvConf(String s) throws NumberFormatException {
        int ndots = -1;
        Matcher matcher = NDOTS_OPTIONS_PATTERN.matcher(s);
        while (matcher.find()) {
            ndots = Integer.parseInt(matcher.group(1));
        }
        return ndots;
    }

    public static boolean parseRotateOptionFromResolvConf(String s) {
        Matcher matcher = ROTATE_OPTIONS_PATTERN.matcher(s);
        return matcher.find();
    }
}
