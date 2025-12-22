package com.htmake.reader.verticle;

import com.htmake.reader.utils.VertExtKt;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.kotlin.coroutines.CoroutineVerticle;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.slf4j.MDCContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RestVerticle.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\b&\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H&J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nH¦@ø\u0001��¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001c\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010\u0015\u001a\u00060\u001aj\u0002`\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0012H\u0016J\u0011\u0010\u001d\u001a\u00020\u0012H\u0094@ø\u0001��¢\u0006\u0002\u0010\u001eJ\b\u0010\u001f\u001a\u00020\u0012H\u0016J6\u0010 \u001a\u00020\u0012*\u00020!2\"\u0010\"\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$\u0012\u0006\u0012\u0004\u0018\u00010%0#ø\u0001��¢\u0006\u0002\u0010&J6\u0010'\u001a\u00020\u0012*\u00020!2\"\u0010\"\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$\u0012\u0006\u0012\u0004\u0018\u00010%0#ø\u0001��¢\u0006\u0002\u0010&R\u001a\u0010\u0003\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0084.¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006("}, d2 = {"Lcom/htmake/reader/verticle/RestVerticle;", "Lio/vertx/kotlin/coroutines/CoroutineVerticle;", "()V", RtspHeaders.Values.PORT, "", "getPort", "()I", "setPort", "(I)V", "router", "Lio/vertx/ext/web/Router;", "getRouter", "()Lio/vertx/ext/web/Router;", "setRouter", "(Lio/vertx/ext/web/Router;)V", "getContextPath", "", "initRouter", "", "(Lio/vertx/ext/web/Router;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onException", "error", "", "onHandlerError", "ctx", "Lio/vertx/ext/web/RoutingContext;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onStartError", "start", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "started", "coroutineHandler", "Lio/vertx/ext/web/Route;", "fn", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lio/vertx/ext/web/Route;Lkotlin/jvm/functions/Function2;)V", "coroutineHandlerWithoutRes", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/verticle/RestVerticle.class */
public abstract class RestVerticle extends CoroutineVerticle {
    protected Router router;
    private int port = 8080;

    /* compiled from: RestVerticle.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "RestVerticle.kt", l = {40, 96}, i = {0, 1}, s = {"L$0", "L$0"}, n = {"this", "this"}, m = "start$suspendImpl", c = "com.htmake.reader.verticle.RestVerticle")
    /* renamed from: com.htmake.reader.verticle.RestVerticle$start$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/verticle/RestVerticle$start$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return RestVerticle.start$suspendImpl(RestVerticle.this, (Continuation) this);
        }
    }

    @Override // io.vertx.kotlin.coroutines.CoroutineVerticle
    @Nullable
    protected Object start(@NotNull Continuation<? super Unit> $completion) {
        return start$suspendImpl(this, (Continuation) $completion);
    }

    @Nullable
    public abstract Object initRouter(@NotNull Router router, @NotNull Continuation<? super Unit> $completion);

    @NotNull
    public abstract String getContextPath();

    @NotNull
    protected final Router getRouter() {
        Router router = this.router;
        if (router != null) {
            return router;
        }
        Intrinsics.throwUninitializedPropertyAccessException("router");
        throw null;
    }

    protected final void setRouter(@NotNull Router router) {
        Intrinsics.checkNotNullParameter(router, "<set-?>");
        this.router = router;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object start$suspendImpl(com.htmake.reader.verticle.RestVerticle r6, kotlin.coroutines.Continuation r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 638
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.verticle.RestVerticle.start$suspendImpl(com.htmake.reader.verticle.RestVerticle, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: start$lambda-1, reason: not valid java name */
    private static final void m893start$lambda1(String cookieName, RoutingContext it) {
        Intrinsics.checkNotNullParameter(cookieName, "$cookieName");
        it.addHeadersEndHandler((v2) -> {
            m892start$lambda1$lambda0(r1, r2, v2);
        });
        it.next();
    }

    /* renamed from: start$lambda-1$lambda-0, reason: not valid java name */
    private static final void m892start$lambda1$lambda0(RoutingContext $it, String cookieName, Void $noName_0) {
        Intrinsics.checkNotNullParameter(cookieName, "$cookieName");
        Cookie cookie = $it.getCookie(cookieName);
        if (cookie != null) {
            cookie.setMaxAge(172800000L);
            cookie.setPath("/");
        }
    }

    /* renamed from: start$lambda-3, reason: not valid java name */
    private static final void m895start$lambda3(String cookieName, RoutingContext it) {
        Intrinsics.checkNotNullParameter(cookieName, "$cookieName");
        it.addHeadersEndHandler((v1) -> {
            m894start$lambda3$lambda2(r1, v1);
        });
        String origin = it.request().getHeader("Origin");
        if (origin != null) {
            if ((origin.length() > 0) && it.request().method() == HttpMethod.OPTIONS) {
                it.removeCookie(cookieName);
                Intrinsics.checkNotNullExpressionValue(it, "it");
                VertExtKt.success(it, "");
                return;
            }
        }
        it.next();
    }

    /* renamed from: start$lambda-3$lambda-2, reason: not valid java name */
    private static final void m894start$lambda3$lambda2(RoutingContext $it, Void $noName_0) {
        String origin = $it.request().getHeader("Origin");
        if (origin != null) {
            if (origin.length() > 0) {
                HttpServerResponse res = $it.response();
                res.putHeader("Access-Control-Allow-Origin", origin);
                res.putHeader("Access-Control-Allow-Credentials", "true");
                res.putHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE");
                res.putHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, If-Match, If-Modified-Since, If-None-Match, If-Unmodified-Since, X-Requested-With");
            }
        }
    }

    /* renamed from: start$lambda-4, reason: not valid java name */
    private static final void m896start$lambda4(RoutingContext it) {
        String rawMethod = it.request().rawMethod();
        RestVerticleKt.logger.info("{} {}", rawMethod, URLDecoder.decode(it.request().absoluteURI(), "UTF-8"));
        if (!rawMethod.equals("PUT") && ((it.fileUploads() == null || it.fileUploads().isEmpty()) && it.getBodyAsString() != null && it.getBodyAsString().length() > 0 && it.getBodyAsString().length() < 1000)) {
            RestVerticleKt.logger.info("Request body: {}", it.getBodyAsString());
        }
        it.next();
    }

    /* renamed from: start$lambda-5, reason: not valid java name */
    private static final void m897start$lambda5(RoutingContext it) {
        Intrinsics.checkNotNullExpressionValue(it, "it");
        VertExtKt.success(it, "ok!");
    }

    /* renamed from: start$lambda-6, reason: not valid java name */
    private static final void m898start$lambda6(RoutingContext ctx) throws UnsupportedEncodingException {
        Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
        Throwable thFailure = ctx.failure();
        Intrinsics.checkNotNullExpressionValue(thFailure, "ctx.failure()");
        VertExtKt.error(ctx, thFailure);
    }

    /* renamed from: start$lambda-7, reason: not valid java name */
    private static final void m899start$lambda7(RestVerticle this$0, Throwable error) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(error, "error");
        this$0.onException(error);
    }

    /* renamed from: start$lambda-8, reason: not valid java name */
    private static final void m900start$lambda8(RestVerticle this$0, AsyncResult res) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (res.succeeded()) {
            RestVerticleKt.logger.info("Server running at: http://localhost:{}", Integer.valueOf(this$0.getPort()));
            RestVerticleKt.logger.info("Web reader running at: http://localhost:{}", Integer.valueOf(this$0.getPort()));
            System.out.println((Object) "ReaderApplication Started");
            this$0.started();
            return;
        }
        this$0.onStartError();
    }

    public void onException(@NotNull Throwable error) {
        Intrinsics.checkNotNullParameter(error, "error");
        RestVerticleKt.logger.error("vertx exception: {}", error);
    }

    public void onStartError() {
    }

    public void started() {
    }

    public void onHandlerError(@NotNull RoutingContext ctx, @NotNull Exception error) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(error, "error");
        RestVerticleKt.logger.error("Error: {}", (Throwable) error);
        VertExtKt.error(ctx, error);
    }

    public final void coroutineHandler(@NotNull Route $this$coroutineHandler, @NotNull Function2<? super RoutingContext, ? super Continuation<Object>, ? extends Object> fn) {
        Intrinsics.checkNotNullParameter($this$coroutineHandler, "<this>");
        Intrinsics.checkNotNullParameter(fn, "fn");
        VertExtKt.globalHandler($this$coroutineHandler, (v2) -> {
            m902coroutineHandler$lambda10(r1, r2, v2);
        });
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [T, kotlinx.coroutines.Job] */
    /* renamed from: coroutineHandler$lambda-10, reason: not valid java name */
    private static final void m902coroutineHandler$lambda10(RestVerticle this$0, Function2 fn, RoutingContext ctx) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fn, "$fn");
        Ref.ObjectRef job = new Ref.ObjectRef();
        ctx.request().connection().closeHandler((v1) -> {
            m901coroutineHandler$lambda10$lambda9(r1, v1);
        });
        job.element = BuildersKt__Builders_commonKt.launch$default(this$0, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new RestVerticle$coroutineHandler$1$2(ctx, fn, this$0, null), 2, null);
    }

    /* renamed from: coroutineHandler$lambda-10$lambda-9, reason: not valid java name */
    private static final void m901coroutineHandler$lambda10$lambda9(Ref.ObjectRef job, Void it) {
        Intrinsics.checkNotNullParameter(job, "$job");
        RestVerticleKt.logger.info("客户端已断开链接，终止运行");
        Job job2 = (Job) job.element;
        if (job2 == null) {
            return;
        }
        Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
    }

    public final void coroutineHandlerWithoutRes(@NotNull Route $this$coroutineHandlerWithoutRes, @NotNull Function2<? super RoutingContext, ? super Continuation<Object>, ? extends Object> fn) {
        Intrinsics.checkNotNullParameter($this$coroutineHandlerWithoutRes, "<this>");
        Intrinsics.checkNotNullParameter(fn, "fn");
        VertExtKt.globalHandler($this$coroutineHandlerWithoutRes, (v2) -> {
            m904coroutineHandlerWithoutRes$lambda12(r1, r2, v2);
        });
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [T, kotlinx.coroutines.Job] */
    /* renamed from: coroutineHandlerWithoutRes$lambda-12, reason: not valid java name */
    private static final void m904coroutineHandlerWithoutRes$lambda12(RestVerticle this$0, Function2 fn, RoutingContext ctx) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fn, "$fn");
        Ref.ObjectRef job = new Ref.ObjectRef();
        ctx.request().connection().closeHandler((v1) -> {
            m903coroutineHandlerWithoutRes$lambda12$lambda11(r1, v1);
        });
        job.element = BuildersKt__Builders_commonKt.launch$default(this$0, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new RestVerticle$coroutineHandlerWithoutRes$1$2(fn, ctx, this$0, null), 2, null);
    }

    /* renamed from: coroutineHandlerWithoutRes$lambda-12$lambda-11, reason: not valid java name */
    private static final void m903coroutineHandlerWithoutRes$lambda12$lambda11(Ref.ObjectRef job, Void it) {
        Intrinsics.checkNotNullParameter(job, "$job");
        RestVerticleKt.logger.info("客户端已断开链接，终止运行");
        Job job2 = (Job) job.element;
        if (job2 == null) {
            return;
        }
        Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
    }
}
