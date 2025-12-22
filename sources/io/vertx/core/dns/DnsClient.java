package io.vertx.core.dns;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import java.util.List;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/dns/DnsClient.class */
public interface DnsClient {
    @Fluent
    DnsClient lookup(String str, Handler<AsyncResult<String>> handler);

    @Fluent
    DnsClient lookup4(String str, Handler<AsyncResult<String>> handler);

    @Fluent
    DnsClient lookup6(String str, Handler<AsyncResult<String>> handler);

    @Fluent
    DnsClient resolveA(String str, Handler<AsyncResult<List<String>>> handler);

    @Fluent
    DnsClient resolveAAAA(String str, Handler<AsyncResult<List<String>>> handler);

    @Fluent
    DnsClient resolveCNAME(String str, Handler<AsyncResult<List<String>>> handler);

    @Fluent
    DnsClient resolveMX(String str, Handler<AsyncResult<List<MxRecord>>> handler);

    @Fluent
    DnsClient resolveTXT(String str, Handler<AsyncResult<List<String>>> handler);

    @Fluent
    DnsClient resolvePTR(String str, Handler<AsyncResult<String>> handler);

    @Fluent
    DnsClient resolveNS(String str, Handler<AsyncResult<List<String>>> handler);

    @Fluent
    DnsClient resolveSRV(String str, Handler<AsyncResult<List<SrvRecord>>> handler);

    @Fluent
    DnsClient reverseLookup(String str, Handler<AsyncResult<String>> handler);
}
