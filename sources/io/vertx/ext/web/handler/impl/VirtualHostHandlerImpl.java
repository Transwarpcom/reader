package io.vertx.ext.web.handler.impl;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.VirtualHostHandler;
import java.util.regex.Pattern;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/VirtualHostHandlerImpl.class */
public class VirtualHostHandlerImpl implements VirtualHostHandler {
    private final Pattern regex;
    private final Handler<RoutingContext> handler;

    public VirtualHostHandlerImpl(String hostname, Handler<RoutingContext> handler) {
        this.handler = handler;
        this.regex = Pattern.compile("^" + hostname.replaceAll("\\.", "\\\\.").replaceAll("[*]", "(.*?)") + PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX, 2);
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext ctx) {
        String host = ctx.request().host();
        if (host == null) {
            ctx.next();
            return;
        }
        boolean match = false;
        String[] strArrSplit = host.split(":");
        int length = strArrSplit.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String h = strArrSplit[i];
            if (!this.regex.matcher(h).matches()) {
                i++;
            } else {
                match = true;
                break;
            }
        }
        if (match) {
            this.handler.handle(ctx);
        } else {
            ctx.next();
        }
    }
}
