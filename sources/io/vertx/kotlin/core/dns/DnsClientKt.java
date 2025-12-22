package io.vertx.kotlin.core.dns;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.dns.DnsClient;
import io.vertx.core.dns.MxRecord;
import io.vertx.core.dns.SrvRecord;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DnsClient.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��&\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001f\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u001f\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u001f\u0010\u0006\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a#\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a#\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a#\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a#\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a#\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u001f\u0010\u000e\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a#\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a#\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u001f\u0010\u0012\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"lookup4Await", "", "Lio/vertx/core/dns/DnsClient;", "name", "(Lio/vertx/core/dns/DnsClient;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lookup6Await", "lookupAwait", "resolveAAAAAwait", "", "resolveAAwait", "resolveCNAMEAwait", "resolveMXAwait", "Lio/vertx/core/dns/MxRecord;", "resolveNSAwait", "resolvePTRAwait", "resolveSRVAwait", "Lio/vertx/core/dns/SrvRecord;", "resolveTXTAwait", "reverseLookupAwait", "ipaddress", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/dns/DnsClientKt.class */
public final class DnsClientKt {
    @Nullable
    public static final Object lookupAwait(@NotNull final DnsClient $this$lookupAwait, @NotNull final String name, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.dns.DnsClientKt.lookupAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$lookupAwait.lookup(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object lookup4Await(@NotNull final DnsClient $this$lookup4Await, @NotNull final String name, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.dns.DnsClientKt.lookup4Await.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$lookup4Await.lookup4(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object lookup6Await(@NotNull final DnsClient $this$lookup6Await, @NotNull final String name, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.dns.DnsClientKt.lookup6Await.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$lookup6Await.lookup6(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object resolveAAwait(@NotNull final DnsClient $this$resolveAAwait, @NotNull final String name, @NotNull Continuation<? super List<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.core.dns.DnsClientKt.resolveAAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends String>>> handler) {
                invoke2((Handler<AsyncResult<List<String>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<String>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$resolveAAwait.resolveA(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object resolveAAAAAwait(@NotNull final DnsClient $this$resolveAAAAAwait, @NotNull final String name, @NotNull Continuation<? super List<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.core.dns.DnsClientKt.resolveAAAAAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends String>>> handler) {
                invoke2((Handler<AsyncResult<List<String>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<String>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$resolveAAAAAwait.resolveAAAA(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object resolveCNAMEAwait(@NotNull final DnsClient $this$resolveCNAMEAwait, @NotNull final String name, @NotNull Continuation<? super List<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.core.dns.DnsClientKt.resolveCNAMEAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends String>>> handler) {
                invoke2((Handler<AsyncResult<List<String>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<String>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$resolveCNAMEAwait.resolveCNAME(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object resolveMXAwait(@NotNull final DnsClient $this$resolveMXAwait, @NotNull final String name, @NotNull Continuation<? super List<? extends MxRecord>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends MxRecord>>>, Unit>() { // from class: io.vertx.kotlin.core.dns.DnsClientKt.resolveMXAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends MxRecord>>> handler) {
                invoke2((Handler<AsyncResult<List<MxRecord>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<MxRecord>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$resolveMXAwait.resolveMX(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object resolveTXTAwait(@NotNull final DnsClient $this$resolveTXTAwait, @NotNull final String name, @NotNull Continuation<? super List<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.core.dns.DnsClientKt.resolveTXTAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends String>>> handler) {
                invoke2((Handler<AsyncResult<List<String>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<String>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$resolveTXTAwait.resolveTXT(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object resolvePTRAwait(@NotNull final DnsClient $this$resolvePTRAwait, @NotNull final String name, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.dns.DnsClientKt.resolvePTRAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$resolvePTRAwait.resolvePTR(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object resolveNSAwait(@NotNull final DnsClient $this$resolveNSAwait, @NotNull final String name, @NotNull Continuation<? super List<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.core.dns.DnsClientKt.resolveNSAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends String>>> handler) {
                invoke2((Handler<AsyncResult<List<String>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<String>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$resolveNSAwait.resolveNS(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object resolveSRVAwait(@NotNull final DnsClient $this$resolveSRVAwait, @NotNull final String name, @NotNull Continuation<? super List<? extends SrvRecord>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends SrvRecord>>>, Unit>() { // from class: io.vertx.kotlin.core.dns.DnsClientKt.resolveSRVAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends SrvRecord>>> handler) {
                invoke2((Handler<AsyncResult<List<SrvRecord>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<SrvRecord>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$resolveSRVAwait.resolveSRV(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object reverseLookupAwait(@NotNull final DnsClient $this$reverseLookupAwait, @NotNull final String ipaddress, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.dns.DnsClientKt.reverseLookupAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$reverseLookupAwait.reverseLookup(ipaddress, it);
            }
        }, continuation);
    }
}
