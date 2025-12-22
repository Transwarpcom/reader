package com.htmake.reader.api;

import ch.qos.logback.classic.ClassicConstants;
import ch.qos.logback.core.CoreConstants;
import com.htmake.reader.SpringEvent;
import com.htmake.reader.api.controller.BookController;
import com.htmake.reader.api.controller.BookGroupController;
import com.htmake.reader.api.controller.BookSourceController;
import com.htmake.reader.api.controller.BookmarkController;
import com.htmake.reader.api.controller.FileController;
import com.htmake.reader.api.controller.HttpTTSController;
import com.htmake.reader.api.controller.LicenseController;
import com.htmake.reader.api.controller.ReplaceRuleController;
import com.htmake.reader.api.controller.RssSourceController;
import com.htmake.reader.api.controller.UserController;
import com.htmake.reader.api.controller.WebdavController;
import com.htmake.reader.config.AppConfig;
import com.htmake.reader.config.BookConfig;
import com.htmake.reader.entity.License;
import com.htmake.reader.entity.User;
import com.htmake.reader.utils.ExtKt;
import com.htmake.reader.utils.SpringContextUtils;
import com.htmake.reader.utils.VertExtKt;
import com.htmake.reader.verticle.RestVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.net.impl.URIDecoder;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.slf4j.MDCContext;
import mu.KLogger;
import okhttp3.internal.http.StatusLine;
import org.apache.fontbox.ttf.WGL4Names;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mozilla.classfile.ByteCode;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/* compiled from: YueduApi.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0017\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0017J\b\u0010\t\u001a\u00020\bH\u0017J\b\u0010\n\u001a\u00020\bH\u0017J\b\u0010\u000b\u001a\u00020\bH\u0017J\b\u0010\f\u001a\u00020\rH\u0016J\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0082@ø\u0001��¢\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@ø\u0001��¢\u0006\u0002\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\bH\u0096@ø\u0001��¢\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00112\n\u0010\u001b\u001a\u00060\u001cj\u0002`\u001dH\u0016J\b\u0010\u001e\u001a\u00020\bH\u0016J\b\u0010\u001f\u001a\u00020\bH\u0017J\u0011\u0010 \u001a\u00020\bH\u0096@ø\u0001��¢\u0006\u0002\u0010\u0018J\b\u0010!\u001a\u00020\bH\u0017J\b\u0010\"\u001a\u00020\bH\u0016R\u0012\u0010\u0003\u001a\u00020\u00048\u0002@\u0002X\u0083.¢\u0006\u0002\n��R\u0012\u0010\u0005\u001a\u00020\u00068\u0002@\u0002X\u0083.¢\u0006\u0002\n��\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lcom/htmake/reader/api/YueduApi;", "Lcom/htmake/reader/verticle/RestVerticle;", "()V", "appConfig", "Lcom/htmake/reader/config/AppConfig;", "env", "Lorg/springframework/core/env/Environment;", "autoBackup", "", "autoGC", "checkLicense", "clearUser", "getContextPath", "", "getSystemInfo", "Lcom/htmake/reader/api/ReturnData;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initRouter", "router", "Lio/vertx/ext/web/Router;", "(Lio/vertx/ext/web/Router;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "migration", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onHandlerError", "ctx", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onStartError", "remoteBookSourceSubUpdateJob", "setupPort", "shelfUpdateJob", "started", "reader-pro"})
@Component
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi.class */
public class YueduApi extends RestVerticle {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private Environment env;

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "YueduApi.kt", l = {504}, i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11"}, n = {"returnData", "systemFont", "freeMemory", "totalMemory", "maxMemory", "dayLoginUser", "sevenDayLoginUser", "monthLoginUser", "keepUser", "dayRegisterUser", "sevenDayRegisterUser", "monthRegisterUser"}, m = "getSystemInfo", c = "com.htmake.reader.api.YueduApi")
    /* renamed from: com.htmake.reader.api.YueduApi$getSystemInfo$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$getSystemInfo$1.class */
    static final class C00561 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        Object L$10;
        Object L$11;
        /* synthetic */ Object result;
        int label;

        C00561(Continuation<? super C00561> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return YueduApi.this.getSystemInfo(null, this);
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "YueduApi.kt", l = {48, 64}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"this", "router"}, m = "initRouter$suspendImpl", c = "com.htmake.reader.api.YueduApi")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$1.class */
    static final class C00571 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C00571(Continuation<? super C00571> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return YueduApi.initRouter$suspendImpl(YueduApi.this, null, this);
        }
    }

    @Override // com.htmake.reader.verticle.RestVerticle
    @Nullable
    public Object initRouter(@NotNull Router router, @NotNull Continuation<? super Unit> $completion) {
        return initRouter$suspendImpl(this, router, $completion);
    }

    @Nullable
    public Object setupPort(@NotNull Continuation<? super Unit> $completion) {
        return setupPort$suspendImpl(this, $completion);
    }

    @Nullable
    public Object migration(@NotNull Continuation<? super Unit> $completion) {
        return migration$suspendImpl(this, $completion);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Type inference failed for: r1v26, types: [T, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object initRouter$suspendImpl(com.htmake.reader.api.YueduApi r8, io.vertx.ext.web.Router r9, kotlin.coroutines.Continuation r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 6003
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.YueduApi.initRouter$suspendImpl(com.htmake.reader.api.YueduApi, io.vertx.ext.web.Router, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: initRouter$lambda-0, reason: not valid java name */
    private static final void m822initRouter$lambda0(Ref.ObjectRef dataDir, RoutingContext it) {
        Intrinsics.checkNotNullParameter(dataDir, "$dataDir");
        String strPath = it.request().path();
        Intrinsics.checkNotNullExpressionValue(strPath, "it.request().path()");
        String path = URIDecoder.decodeURIComponent(StringsKt.replace(strPath, "/book-assets/", "/", true), false);
        Intrinsics.checkNotNullExpressionValue(path, "decodeURIComponent(path, false)");
        if (StringsKt.endsWith(path, "html", true) || StringsKt.endsWith(path, "htm", true)) {
            File filePath = new File(Intrinsics.stringPlus((String) dataDir.element, path));
            if (filePath.exists()) {
                BookConfig bookConfig = BookConfig.INSTANCE;
                String string = filePath.toString();
                Intrinsics.checkNotNullExpressionValue(string, "filePath.toString()");
                bookConfig.injectJavascriptToEpubChapter(string);
            }
        }
        it.next();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: initRouter$lambda-1, reason: not valid java name */
    private static final void m823initRouter$lambda1(Ref.ObjectRef dataDir, RoutingContext it) throws UnsupportedEncodingException {
        Intrinsics.checkNotNullParameter(dataDir, "$dataDir");
        String strPath = it.request().path();
        Intrinsics.checkNotNullExpressionValue(strPath, "it.request().path()");
        String path = URLDecoder.decode(StringsKt.replace(strPath, "/epub/", "/", true), "UTF-8");
        Intrinsics.checkNotNullExpressionValue(path, "decode(path, \"UTF-8\")");
        if (StringsKt.endsWith(path, "html", true)) {
            File filePath = new File(Intrinsics.stringPlus((String) dataDir.element, path));
            if (filePath.exists()) {
                BookConfig bookConfig = BookConfig.INSTANCE;
                String string = filePath.toString();
                Intrinsics.checkNotNullExpressionValue(string, "filePath.toString()");
                bookConfig.injectJavascriptToEpubChapter(string);
            }
        }
        it.next();
    }

    /* renamed from: initRouter$lambda-2, reason: not valid java name */
    private static final void m824initRouter$lambda2(RoutingContext it) throws UnsupportedEncodingException {
        String strPath = it.request().path();
        Intrinsics.checkNotNullExpressionValue(strPath, "it.request().path()");
        if (StringsKt.endsWith$default(strPath, "/simple-web", false, 2, (Object) null)) {
            HttpServerResponse httpServerResponseResponse = it.response();
            String strDecode = URLDecoder.decode(it.request().absoluteURI(), "UTF-8");
            Intrinsics.checkNotNullExpressionValue(strDecode, "decode(it.request().absoluteURI(), \"UTF-8\")");
            httpServerResponseResponse.putHeader("Location", StringsKt.replace$default(strDecode, "/simple-web", "/simple-web/", false, 4, (Object) null)).setStatusCode(302).end();
            return;
        }
        it.next();
    }

    /* renamed from: initRouter$lambda-3, reason: not valid java name */
    private static final void m825initRouter$lambda3(RoutingContext it) {
        License license = ExtKt.getInstalledLicense$default(false, 1, null);
        long simpleWebExpiredAt = 0;
        String strHost = it.request().host();
        Intrinsics.checkNotNullExpressionValue(strHost, "it.request().host()");
        if (license.validHost(strHost)) {
            simpleWebExpiredAt = license.getSimpleWebExpiredAt();
        }
        if (simpleWebExpiredAt != 0 && simpleWebExpiredAt < System.currentTimeMillis()) {
            it.response().putHeader("content-type", "text/html; charset=UTF-8").setStatusCode(403).end("<html><head><title>未激活该功能</title></head><body><div style='text-align: center;padding: 30px 0;'>未激活该功能，请加<a href='https://t.me/+pQ8HDlANPZ84ZWNl'>TG群</a>激活</div></body></html>");
        } else {
            it.next();
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {143}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$6")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$6, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$6.class */
    static final class AnonymousClass6 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;

        AnonymousClass6(Continuation<? super AnonymousClass6> $completion) {
            super(2, $completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass6 anonymousClass6 = YueduApi.this.new AnonymousClass6($completion);
            anonymousClass6.L$0 = value;
            return anonymousClass6;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass6) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object systemInfo = YueduApi.this.getSystemInfo(it, this);
                    return systemInfo == coroutine_suspended ? coroutine_suspended : systemInfo;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {163}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$7")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$7, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$7.class */
    static final class AnonymousClass7 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass7(BookSourceController $bookSourceController, Continuation<? super AnonymousClass7> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass7 anonymousClass7 = new AnonymousClass7(this.$bookSourceController, $completion);
            anonymousClass7.L$0 = value;
            return anonymousClass7;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass7) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveBookSource = this.$bookSourceController.saveBookSource(it, this);
                    return objSaveBookSource == coroutine_suspended ? coroutine_suspended : objSaveBookSource;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {164}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$8")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$8, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$8.class */
    static final class AnonymousClass8 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass8(BookSourceController $bookSourceController, Continuation<? super AnonymousClass8> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass8 anonymousClass8 = new AnonymousClass8(this.$bookSourceController, $completion);
            anonymousClass8.L$0 = value;
            return anonymousClass8;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass8) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveBookSources = this.$bookSourceController.saveBookSources(it, this);
                    return objSaveBookSources == coroutine_suspended ? coroutine_suspended : objSaveBookSources;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {166}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$9")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$9, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$9.class */
    static final class AnonymousClass9 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass9(BookSourceController $bookSourceController, Continuation<? super AnonymousClass9> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass9 anonymousClass9 = new AnonymousClass9(this.$bookSourceController, $completion);
            anonymousClass9.L$0 = value;
            return anonymousClass9;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass9) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object bookSource = this.$bookSourceController.getBookSource(it, this);
                    return bookSource == coroutine_suspended ? coroutine_suspended : bookSource;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {167}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$10")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$10, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$10.class */
    static final class AnonymousClass10 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass10(BookSourceController $bookSourceController, Continuation<? super AnonymousClass10> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass10 anonymousClass10 = new AnonymousClass10(this.$bookSourceController, $completion);
            anonymousClass10.L$0 = value;
            return anonymousClass10;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass10) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object bookSource = this.$bookSourceController.getBookSource(it, this);
                    return bookSource == coroutine_suspended ? coroutine_suspended : bookSource;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {168}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$11")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$11, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$11.class */
    static final class AnonymousClass11 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass11(BookSourceController $bookSourceController, Continuation<? super AnonymousClass11> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass11 anonymousClass11 = new AnonymousClass11(this.$bookSourceController, $completion);
            anonymousClass11.L$0 = value;
            return anonymousClass11;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass11) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object bookSources = this.$bookSourceController.getBookSources(it, this);
                    return bookSources == coroutine_suspended ? coroutine_suspended : bookSources;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {169}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$12")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$12, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$12.class */
    static final class AnonymousClass12 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass12(BookSourceController $bookSourceController, Continuation<? super AnonymousClass12> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass12 anonymousClass12 = new AnonymousClass12(this.$bookSourceController, $completion);
            anonymousClass12.L$0 = value;
            return anonymousClass12;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass12) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object bookSources = this.$bookSourceController.getBookSources(it, this);
                    return bookSources == coroutine_suspended ? coroutine_suspended : bookSources;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {171}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$13")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$13, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$13.class */
    static final class AnonymousClass13 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass13(BookSourceController $bookSourceController, Continuation<? super AnonymousClass13> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass13 anonymousClass13 = new AnonymousClass13(this.$bookSourceController, $completion);
            anonymousClass13.L$0 = value;
            return anonymousClass13;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass13) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteAllBookSources = this.$bookSourceController.deleteAllBookSources(it, this);
                    return objDeleteAllBookSources == coroutine_suspended ? coroutine_suspended : objDeleteAllBookSources;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {172}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$14")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$14, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$14.class */
    static final class AnonymousClass14 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass14(BookSourceController $bookSourceController, Continuation<? super AnonymousClass14> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass14 anonymousClass14 = new AnonymousClass14(this.$bookSourceController, $completion);
            anonymousClass14.L$0 = value;
            return anonymousClass14;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass14) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteBookSource = this.$bookSourceController.deleteBookSource(it, this);
                    return objDeleteBookSource == coroutine_suspended ? coroutine_suspended : objDeleteBookSource;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {173}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$15")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$15, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$15.class */
    static final class AnonymousClass15 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass15(BookSourceController $bookSourceController, Continuation<? super AnonymousClass15> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass15 anonymousClass15 = new AnonymousClass15(this.$bookSourceController, $completion);
            anonymousClass15.L$0 = value;
            return anonymousClass15;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass15) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteBookSources = this.$bookSourceController.deleteBookSources(it, this);
                    return objDeleteBookSources == coroutine_suspended ? coroutine_suspended : objDeleteBookSources;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {176}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$16")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$16, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$16.class */
    static final class AnonymousClass16 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass16(BookSourceController $bookSourceController, Continuation<? super AnonymousClass16> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass16 anonymousClass16 = new AnonymousClass16(this.$bookSourceController, $completion);
            anonymousClass16.L$0 = value;
            return anonymousClass16;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass16) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object sourceFile = this.$bookSourceController.readSourceFile(it, this);
                    return sourceFile == coroutine_suspended ? coroutine_suspended : sourceFile;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {179}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$17")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$17, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$17.class */
    static final class AnonymousClass17 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Unit>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass17(BookSourceController $bookSourceController, Continuation<? super AnonymousClass17> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass17 anonymousClass17 = new AnonymousClass17(this.$bookSourceController, $completion);
            anonymousClass17.L$0 = value;
            return anonymousClass17;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull RoutingContext p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass17) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    if (this.$bookSourceController.saveFromRemoteSource(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {182}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$18")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$18, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$18.class */
    static final class AnonymousClass18 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass18(BookSourceController $bookSourceController, Continuation<? super AnonymousClass18> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass18 anonymousClass18 = new AnonymousClass18(this.$bookSourceController, $completion);
            anonymousClass18.L$0 = value;
            return anonymousClass18;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass18) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object asDefaultBookSources = this.$bookSourceController.setAsDefaultBookSources(it, this);
                    return asDefaultBookSources == coroutine_suspended ? coroutine_suspended : asDefaultBookSources;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {183}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$19")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$19, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$19.class */
    static final class AnonymousClass19 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass19(BookSourceController $bookSourceController, Continuation<? super AnonymousClass19> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass19 anonymousClass19 = new AnonymousClass19(this.$bookSourceController, $completion);
            anonymousClass19.L$0 = value;
            return anonymousClass19;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass19) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteUserBookSource = this.$bookSourceController.deleteUserBookSource(it, this);
                    return objDeleteUserBookSource == coroutine_suspended ? coroutine_suspended : objDeleteUserBookSource;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {184}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$20")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$20, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$20.class */
    static final class AnonymousClass20 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookSourceController $bookSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass20(BookSourceController $bookSourceController, Continuation<? super AnonymousClass20> $completion) {
            super(2, $completion);
            this.$bookSourceController = $bookSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass20 anonymousClass20 = new AnonymousClass20(this.$bookSourceController, $completion);
            anonymousClass20.L$0 = value;
            return anonymousClass20;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass20) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteBookSourcesFile = this.$bookSourceController.deleteBookSourcesFile(it, this);
                    return objDeleteBookSourcesFile == coroutine_suspended ? coroutine_suspended : objDeleteBookSourcesFile;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {188}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$21")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$21, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$21.class */
    static final class AnonymousClass21 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass21(BookController $bookController, Continuation<? super AnonymousClass21> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass21 anonymousClass21 = new AnonymousClass21(this.$bookController, $completion);
            anonymousClass21.L$0 = value;
            return anonymousClass21;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass21) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object bookshelf = this.$bookController.getBookshelf(it, this);
                    return bookshelf == coroutine_suspended ? coroutine_suspended : bookshelf;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {189}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$22")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$22, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$22.class */
    static final class AnonymousClass22 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass22(BookController $bookController, Continuation<? super AnonymousClass22> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass22 anonymousClass22 = new AnonymousClass22(this.$bookController, $completion);
            anonymousClass22.L$0 = value;
            return anonymousClass22;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass22) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object shelfBook = this.$bookController.getShelfBook(it, this);
                    return shelfBook == coroutine_suspended ? coroutine_suspended : shelfBook;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {190}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$23")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$23, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$23.class */
    static final class AnonymousClass23 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass23(BookController $bookController, Continuation<? super AnonymousClass23> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass23 anonymousClass23 = new AnonymousClass23(this.$bookController, $completion);
            anonymousClass23.L$0 = value;
            return anonymousClass23;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass23) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveBook = this.$bookController.saveBook(it, this);
                    return objSaveBook == coroutine_suspended ? coroutine_suspended : objSaveBook;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {191}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$24")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$24, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$24.class */
    static final class AnonymousClass24 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass24(BookController $bookController, Continuation<? super AnonymousClass24> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass24 anonymousClass24 = new AnonymousClass24(this.$bookController, $completion);
            anonymousClass24.L$0 = value;
            return anonymousClass24;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass24) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteBook = this.$bookController.deleteBook(it, this);
                    return objDeleteBook == coroutine_suspended ? coroutine_suspended : objDeleteBook;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {192}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$25")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$25, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$25.class */
    static final class AnonymousClass25 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass25(BookController $bookController, Continuation<? super AnonymousClass25> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass25 anonymousClass25 = new AnonymousClass25(this.$bookController, $completion);
            anonymousClass25.L$0 = value;
            return anonymousClass25;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass25) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteBooks = this.$bookController.deleteBooks(it, this);
                    return objDeleteBooks == coroutine_suspended ? coroutine_suspended : objDeleteBooks;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {195}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$26")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$26, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$26.class */
    static final class AnonymousClass26 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass26(BookController $bookController, Continuation<? super AnonymousClass26> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass26 anonymousClass26 = new AnonymousClass26(this.$bookController, $completion);
            anonymousClass26.L$0 = value;
            return anonymousClass26;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass26) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object invalidBookSources = this.$bookController.getInvalidBookSources(it, this);
                    return invalidBookSources == coroutine_suspended ? coroutine_suspended : invalidBookSources;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {198}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$27")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$27, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$27.class */
    static final class AnonymousClass27 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass27(BookController $bookController, Continuation<? super AnonymousClass27> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass27 anonymousClass27 = new AnonymousClass27(this.$bookController, $completion);
            anonymousClass27.L$0 = value;
            return anonymousClass27;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass27) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objExploreBook = this.$bookController.exploreBook(it, this);
                    return objExploreBook == coroutine_suspended ? coroutine_suspended : objExploreBook;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {199}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$28")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$28, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$28.class */
    static final class AnonymousClass28 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass28(BookController $bookController, Continuation<? super AnonymousClass28> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass28 anonymousClass28 = new AnonymousClass28(this.$bookController, $completion);
            anonymousClass28.L$0 = value;
            return anonymousClass28;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass28) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objExploreBook = this.$bookController.exploreBook(it, this);
                    return objExploreBook == coroutine_suspended ? coroutine_suspended : objExploreBook;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {ByteCode.BREAKPOINT}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$29")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$29, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$29.class */
    static final class AnonymousClass29 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass29(BookController $bookController, Continuation<? super AnonymousClass29> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass29 anonymousClass29 = new AnonymousClass29(this.$bookController, $completion);
            anonymousClass29.L$0 = value;
            return anonymousClass29;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass29) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSearchBook = this.$bookController.searchBook(it, this);
                    return objSearchBook == coroutine_suspended ? coroutine_suspended : objSearchBook;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {203}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$30")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$30, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$30.class */
    static final class AnonymousClass30 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass30(BookController $bookController, Continuation<? super AnonymousClass30> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass30 anonymousClass30 = new AnonymousClass30(this.$bookController, $completion);
            anonymousClass30.L$0 = value;
            return anonymousClass30;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass30) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSearchBook = this.$bookController.searchBook(it, this);
                    return objSearchBook == coroutine_suspended ? coroutine_suspended : objSearchBook;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {204}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$31")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$31, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$31.class */
    static final class AnonymousClass31 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass31(BookController $bookController, Continuation<? super AnonymousClass31> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass31 anonymousClass31 = new AnonymousClass31(this.$bookController, $completion);
            anonymousClass31.L$0 = value;
            return anonymousClass31;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass31) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSearchBookMulti = this.$bookController.searchBookMulti(it, this);
                    return objSearchBookMulti == coroutine_suspended ? coroutine_suspended : objSearchBookMulti;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {205}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$32")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$32, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$32.class */
    static final class AnonymousClass32 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass32(BookController $bookController, Continuation<? super AnonymousClass32> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass32 anonymousClass32 = new AnonymousClass32(this.$bookController, $completion);
            anonymousClass32.L$0 = value;
            return anonymousClass32;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass32) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSearchBookMulti = this.$bookController.searchBookMulti(it, this);
                    return objSearchBookMulti == coroutine_suspended ? coroutine_suspended : objSearchBookMulti;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {206}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$33")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$33, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$33.class */
    static final class AnonymousClass33 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Unit>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass33(BookController $bookController, Continuation<? super AnonymousClass33> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass33 anonymousClass33 = new AnonymousClass33(this.$bookController, $completion);
            anonymousClass33.L$0 = value;
            return anonymousClass33;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull RoutingContext p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass33) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    if (this.$bookController.searchBookMultiSSE(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {209}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$34")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$34, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$34.class */
    static final class AnonymousClass34 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass34(BookController $bookController, Continuation<? super AnonymousClass34> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass34 anonymousClass34 = new AnonymousClass34(this.$bookController, $completion);
            anonymousClass34.L$0 = value;
            return anonymousClass34;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass34) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object bookInfo = this.$bookController.getBookInfo(it, this);
                    return bookInfo == coroutine_suspended ? coroutine_suspended : bookInfo;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {210}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$35")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$35, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$35.class */
    static final class AnonymousClass35 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass35(BookController $bookController, Continuation<? super AnonymousClass35> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass35 anonymousClass35 = new AnonymousClass35(this.$bookController, $completion);
            anonymousClass35.L$0 = value;
            return anonymousClass35;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass35) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object bookInfo = this.$bookController.getBookInfo(it, this);
                    return bookInfo == coroutine_suspended ? coroutine_suspended : bookInfo;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {213}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$36")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$36, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$36.class */
    static final class AnonymousClass36 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass36(BookController $bookController, Continuation<? super AnonymousClass36> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass36 anonymousClass36 = new AnonymousClass36(this.$bookController, $completion);
            anonymousClass36.L$0 = value;
            return anonymousClass36;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass36) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object chapterList = this.$bookController.getChapterList(it, this);
                    return chapterList == coroutine_suspended ? coroutine_suspended : chapterList;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {214}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$37")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$37, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$37.class */
    static final class AnonymousClass37 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass37(BookController $bookController, Continuation<? super AnonymousClass37> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass37 anonymousClass37 = new AnonymousClass37(this.$bookController, $completion);
            anonymousClass37.L$0 = value;
            return anonymousClass37;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass37) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object chapterList = this.$bookController.getChapterList(it, this);
                    return chapterList == coroutine_suspended ? coroutine_suspended : chapterList;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {217}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$38")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$38, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$38.class */
    static final class AnonymousClass38 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass38(BookController $bookController, Continuation<? super AnonymousClass38> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass38 anonymousClass38 = new AnonymousClass38(this.$bookController, $completion);
            anonymousClass38.L$0 = value;
            return anonymousClass38;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass38) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object bookContent = this.$bookController.getBookContent(it, this);
                    return bookContent == coroutine_suspended ? coroutine_suspended : bookContent;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {218}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$39")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$39, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$39.class */
    static final class AnonymousClass39 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass39(BookController $bookController, Continuation<? super AnonymousClass39> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass39 anonymousClass39 = new AnonymousClass39(this.$bookController, $completion);
            anonymousClass39.L$0 = value;
            return anonymousClass39;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass39) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object bookContent = this.$bookController.getBookContent(it, this);
                    return bookContent == coroutine_suspended ? coroutine_suspended : bookContent;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {219}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$40")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$40, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$40.class */
    static final class AnonymousClass40 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass40(BookController $bookController, Continuation<? super AnonymousClass40> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass40 anonymousClass40 = new AnonymousClass40(this.$bookController, $completion);
            anonymousClass40.L$0 = value;
            return anonymousClass40;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass40) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveBookContent = this.$bookController.saveBookContent(it, this);
                    return objSaveBookContent == coroutine_suspended ? coroutine_suspended : objSaveBookContent;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {222}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$41")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$41, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$41.class */
    static final class AnonymousClass41 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass41(BookController $bookController, Continuation<? super AnonymousClass41> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass41 anonymousClass41 = new AnonymousClass41(this.$bookController, $completion);
            anonymousClass41.L$0 = value;
            return anonymousClass41;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass41) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveBookProgress = this.$bookController.saveBookProgress(it, this);
                    return objSaveBookProgress == coroutine_suspended ? coroutine_suspended : objSaveBookProgress;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {225}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$42")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$42, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$42.class */
    static final class AnonymousClass42 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Unit>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass42(BookController $bookController, Continuation<? super AnonymousClass42> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass42 anonymousClass42 = new AnonymousClass42(this.$bookController, $completion);
            anonymousClass42.L$0 = value;
            return anonymousClass42;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull RoutingContext p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass42) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    if (this.$bookController.getBookCover(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {228}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$43")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$43, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$43.class */
    static final class AnonymousClass43 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass43(BookController $bookController, Continuation<? super AnonymousClass43> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass43 anonymousClass43 = new AnonymousClass43(this.$bookController, $completion);
            anonymousClass43.L$0 = value;
            return anonymousClass43;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass43) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSearchBookSource = this.$bookController.searchBookSource(it, this);
                    return objSearchBookSource == coroutine_suspended ? coroutine_suspended : objSearchBookSource;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {229}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$44")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$44, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$44.class */
    static final class AnonymousClass44 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass44(BookController $bookController, Continuation<? super AnonymousClass44> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass44 anonymousClass44 = new AnonymousClass44(this.$bookController, $completion);
            anonymousClass44.L$0 = value;
            return anonymousClass44;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass44) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSearchBookSource = this.$bookController.searchBookSource(it, this);
                    return objSearchBookSource == coroutine_suspended ? coroutine_suspended : objSearchBookSource;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {230}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$45")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$45, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$45.class */
    static final class AnonymousClass45 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass45(BookController $bookController, Continuation<? super AnonymousClass45> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass45 anonymousClass45 = new AnonymousClass45(this.$bookController, $completion);
            anonymousClass45.L$0 = value;
            return anonymousClass45;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass45) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object availableBookSource = this.$bookController.getAvailableBookSource(it, this);
                    return availableBookSource == coroutine_suspended ? coroutine_suspended : availableBookSource;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {231}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$46")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$46, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$46.class */
    static final class AnonymousClass46 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass46(BookController $bookController, Continuation<? super AnonymousClass46> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass46 anonymousClass46 = new AnonymousClass46(this.$bookController, $completion);
            anonymousClass46.L$0 = value;
            return anonymousClass46;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass46) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object availableBookSource = this.$bookController.getAvailableBookSource(it, this);
                    return availableBookSource == coroutine_suspended ? coroutine_suspended : availableBookSource;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {232}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$47")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$47, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$47.class */
    static final class AnonymousClass47 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Unit>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass47(BookController $bookController, Continuation<? super AnonymousClass47> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass47 anonymousClass47 = new AnonymousClass47(this.$bookController, $completion);
            anonymousClass47.L$0 = value;
            return anonymousClass47;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull RoutingContext p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass47) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    if (this.$bookController.searchBookSourceSSE(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {235}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$48")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$48, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$48.class */
    static final class AnonymousClass48 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass48(BookController $bookController, Continuation<? super AnonymousClass48> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass48 anonymousClass48 = new AnonymousClass48(this.$bookController, $completion);
            anonymousClass48.L$0 = value;
            return anonymousClass48;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass48) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object bookSource = this.$bookController.setBookSource(it, this);
                    return bookSource == coroutine_suspended ? coroutine_suspended : bookSource;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {236}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$49")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$49, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$49.class */
    static final class AnonymousClass49 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass49(BookController $bookController, Continuation<? super AnonymousClass49> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass49 anonymousClass49 = new AnonymousClass49(this.$bookController, $completion);
            anonymousClass49.L$0 = value;
            return anonymousClass49;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass49) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object bookSource = this.$bookController.setBookSource(it, this);
                    return bookSource == coroutine_suspended ? coroutine_suspended : bookSource;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {239}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$50")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$50, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$50.class */
    static final class AnonymousClass50 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass50(BookController $bookController, Continuation<? super AnonymousClass50> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass50 anonymousClass50 = new AnonymousClass50(this.$bookController, $completion);
            anonymousClass50.L$0 = value;
            return anonymousClass50;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass50) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveBookGroupId = this.$bookController.saveBookGroupId(it, this);
                    return objSaveBookGroupId == coroutine_suspended ? coroutine_suspended : objSaveBookGroupId;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {240}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$51")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$51, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$51.class */
    static final class AnonymousClass51 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass51(BookController $bookController, Continuation<? super AnonymousClass51> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass51 anonymousClass51 = new AnonymousClass51(this.$bookController, $completion);
            anonymousClass51.L$0 = value;
            return anonymousClass51;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass51) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objAddBookGroupMulti = this.$bookController.addBookGroupMulti(it, this);
                    return objAddBookGroupMulti == coroutine_suspended ? coroutine_suspended : objAddBookGroupMulti;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {241}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$52")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$52, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$52.class */
    static final class AnonymousClass52 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass52(BookController $bookController, Continuation<? super AnonymousClass52> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass52 anonymousClass52 = new AnonymousClass52(this.$bookController, $completion);
            anonymousClass52.L$0 = value;
            return anonymousClass52;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass52) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objRemoveBookGroupMulti = this.$bookController.removeBookGroupMulti(it, this);
                    return objRemoveBookGroupMulti == coroutine_suspended ? coroutine_suspended : objRemoveBookGroupMulti;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {244}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$53")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$53, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$53.class */
    static final class AnonymousClass53 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass53(BookController $bookController, Continuation<? super AnonymousClass53> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass53 anonymousClass53 = new AnonymousClass53(this.$bookController, $completion);
            anonymousClass53.L$0 = value;
            return anonymousClass53;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass53) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objImportBookPreview = this.$bookController.importBookPreview(it, this);
                    return objImportBookPreview == coroutine_suspended ? coroutine_suspended : objImportBookPreview;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {245}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$54")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$54, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$54.class */
    static final class AnonymousClass54 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass54(BookController $bookController, Continuation<? super AnonymousClass54> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass54 anonymousClass54 = new AnonymousClass54(this.$bookController, $completion);
            anonymousClass54.L$0 = value;
            return anonymousClass54;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass54) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objRefreshLocalBook = this.$bookController.refreshLocalBook(it, this);
                    return objRefreshLocalBook == coroutine_suspended ? coroutine_suspended : objRefreshLocalBook;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {248}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$55")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$55, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$55.class */
    static final class AnonymousClass55 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass55(BookController $bookController, Continuation<? super AnonymousClass55> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass55 anonymousClass55 = new AnonymousClass55(this.$bookController, $completion);
            anonymousClass55.L$0 = value;
            return anonymousClass55;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass55) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object txtTocRules = this.$bookController.getTxtTocRules(it, this);
                    return txtTocRules == coroutine_suspended ? coroutine_suspended : txtTocRules;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {249}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$56")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$56, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$56.class */
    static final class AnonymousClass56 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass56(BookController $bookController, Continuation<? super AnonymousClass56> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass56 anonymousClass56 = new AnonymousClass56(this.$bookController, $completion);
            anonymousClass56.L$0 = value;
            return anonymousClass56;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass56) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object chapterListByRule = this.$bookController.getChapterListByRule(it, this);
                    return chapterListByRule == coroutine_suspended ? coroutine_suspended : chapterListByRule;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {252}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$57")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$57, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$57.class */
    static final class AnonymousClass57 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookGroupController $bookGroupController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass57(BookGroupController $bookGroupController, Continuation<? super AnonymousClass57> $completion) {
            super(2, $completion);
            this.$bookGroupController = $bookGroupController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass57 anonymousClass57 = new AnonymousClass57(this.$bookGroupController, $completion);
            anonymousClass57.L$0 = value;
            return anonymousClass57;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass57) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object list = this.$bookGroupController.list(it, this);
                    return list == coroutine_suspended ? coroutine_suspended : list;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {253}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$58")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$58, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$58.class */
    static final class AnonymousClass58 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookGroupController $bookGroupController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass58(BookGroupController $bookGroupController, Continuation<? super AnonymousClass58> $completion) {
            super(2, $completion);
            this.$bookGroupController = $bookGroupController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass58 anonymousClass58 = new AnonymousClass58(this.$bookGroupController, $completion);
            anonymousClass58.L$0 = value;
            return anonymousClass58;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass58) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSave = this.$bookGroupController.save(it, this);
                    return objSave == coroutine_suspended ? coroutine_suspended : objSave;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {254}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$59")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$59, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$59.class */
    static final class AnonymousClass59 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookGroupController $bookGroupController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass59(BookGroupController $bookGroupController, Continuation<? super AnonymousClass59> $completion) {
            super(2, $completion);
            this.$bookGroupController = $bookGroupController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass59 anonymousClass59 = new AnonymousClass59(this.$bookGroupController, $completion);
            anonymousClass59.L$0 = value;
            return anonymousClass59;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass59) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDelete = this.$bookGroupController.delete(it, this);
                    return objDelete == coroutine_suspended ? coroutine_suspended : objDelete;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {255}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$60")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$60, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$60.class */
    static final class AnonymousClass60 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookGroupController $bookGroupController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass60(BookGroupController $bookGroupController, Continuation<? super AnonymousClass60> $completion) {
            super(2, $completion);
            this.$bookGroupController = $bookGroupController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass60 anonymousClass60 = new AnonymousClass60(this.$bookGroupController, $completion);
            anonymousClass60.L$0 = value;
            return anonymousClass60;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass60) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveBookGroupOrder = this.$bookGroupController.saveBookGroupOrder(it, this);
                    return objSaveBookGroupOrder == coroutine_suspended ? coroutine_suspended : objSaveBookGroupOrder;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {WGL4Names.NUMBER_OF_MAC_GLYPHS}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$61")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$61, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$61.class */
    static final class AnonymousClass61 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Unit>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass61(BookController $bookController, Continuation<? super AnonymousClass61> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass61 anonymousClass61 = new AnonymousClass61(this.$bookController, $completion);
            anonymousClass61.L$0 = value;
            return anonymousClass61;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull RoutingContext p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass61) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    if (this.$bookController.bookSourceDebugSSE(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {261}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$62")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$62, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$62.class */
    static final class AnonymousClass62 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Unit>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass62(BookController $bookController, Continuation<? super AnonymousClass62> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass62 anonymousClass62 = new AnonymousClass62(this.$bookController, $completion);
            anonymousClass62.L$0 = value;
            return anonymousClass62;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull RoutingContext p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass62) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    if (this.$bookController.cacheBookSSE(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {263}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$63")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$63, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$63.class */
    static final class AnonymousClass63 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass63(BookController $bookController, Continuation<? super AnonymousClass63> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass63 anonymousClass63 = new AnonymousClass63(this.$bookController, $completion);
            anonymousClass63.L$0 = value;
            return anonymousClass63;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass63) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objCacheBookOnServer = this.$bookController.cacheBookOnServer(it, this);
                    return objCacheBookOnServer == coroutine_suspended ? coroutine_suspended : objCacheBookOnServer;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {265}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$64")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$64, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$64.class */
    static final class AnonymousClass64 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass64(BookController $bookController, Continuation<? super AnonymousClass64> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass64 anonymousClass64 = new AnonymousClass64(this.$bookController, $completion);
            anonymousClass64.L$0 = value;
            return anonymousClass64;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass64) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object shelfBookWithCacheInfo = this.$bookController.getShelfBookWithCacheInfo(it, this);
                    return shelfBookWithCacheInfo == coroutine_suspended ? coroutine_suspended : shelfBookWithCacheInfo;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {267}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$65")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$65, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$65.class */
    static final class AnonymousClass65 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass65(BookController $bookController, Continuation<? super AnonymousClass65> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass65 anonymousClass65 = new AnonymousClass65(this.$bookController, $completion);
            anonymousClass65.L$0 = value;
            return anonymousClass65;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass65) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteBookCache = this.$bookController.deleteBookCache(it, this);
                    return objDeleteBookCache == coroutine_suspended ? coroutine_suspended : objDeleteBookCache;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {270}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$66")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$66, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$66.class */
    static final class AnonymousClass66 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Unit>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass66(BookController $bookController, Continuation<? super AnonymousClass66> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass66 anonymousClass66 = new AnonymousClass66(this.$bookController, $completion);
            anonymousClass66.L$0 = value;
            return anonymousClass66;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull RoutingContext p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass66) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    if (this.$bookController.exportBook(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {271}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$67")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$67, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$67.class */
    static final class AnonymousClass67 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Unit>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass67(BookController $bookController, Continuation<? super AnonymousClass67> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass67 anonymousClass67 = new AnonymousClass67(this.$bookController, $completion);
            anonymousClass67.L$0 = value;
            return anonymousClass67;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull RoutingContext p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass67) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    if (this.$bookController.exportBook(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {274}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$68")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$68, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$68.class */
    static final class AnonymousClass68 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass68(BookController $bookController, Continuation<? super AnonymousClass68> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass68 anonymousClass68 = new AnonymousClass68(this.$bookController, $completion);
            anonymousClass68.L$0 = value;
            return anonymousClass68;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass68) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSearchBookContent = this.$bookController.searchBookContent(it, this);
                    return objSearchBookContent == coroutine_suspended ? coroutine_suspended : objSearchBookContent;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {275}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$69")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$69, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$69.class */
    static final class AnonymousClass69 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass69(BookController $bookController, Continuation<? super AnonymousClass69> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass69 anonymousClass69 = new AnonymousClass69(this.$bookController, $completion);
            anonymousClass69.L$0 = value;
            return anonymousClass69;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass69) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSearchBookContent = this.$bookController.searchBookContent(it, this);
                    return objSearchBookContent == coroutine_suspended ? coroutine_suspended : objSearchBookContent;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {278}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$70")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$70, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$70.class */
    static final class AnonymousClass70 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass70(BookController $bookController, Continuation<? super AnonymousClass70> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass70 anonymousClass70 = new AnonymousClass70(this.$bookController, $completion);
            anonymousClass70.L$0 = value;
            return anonymousClass70;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass70) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objBackupToMongodb = this.$bookController.backupToMongodb(it, this);
                    return objBackupToMongodb == coroutine_suspended ? coroutine_suspended : objBackupToMongodb;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {279}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$71")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$71, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$71.class */
    static final class AnonymousClass71 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass71(BookController $bookController, Continuation<? super AnonymousClass71> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass71 anonymousClass71 = new AnonymousClass71(this.$bookController, $completion);
            anonymousClass71.L$0 = value;
            return anonymousClass71;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass71) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objRestoreFromMongodb = this.$bookController.restoreFromMongodb(it, this);
                    return objRestoreFromMongodb == coroutine_suspended ? coroutine_suspended : objRestoreFromMongodb;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {282}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$72")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$72, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$72.class */
    static final class AnonymousClass72 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass72(BookController $bookController, Continuation<? super AnonymousClass72> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass72 anonymousClass72 = new AnonymousClass72(this.$bookController, $completion);
            anonymousClass72.L$0 = value;
            return anonymousClass72;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass72) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveBookConfig = this.$bookController.saveBookConfig(it, this);
                    return objSaveBookConfig == coroutine_suspended ? coroutine_suspended : objSaveBookConfig;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {285}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$73")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$73, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$73.class */
    static final class AnonymousClass73 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Unit>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass73(BookController $bookController, Continuation<? super AnonymousClass73> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass73 anonymousClass73 = new AnonymousClass73(this.$bookController, $completion);
            anonymousClass73.L$0 = value;
            return anonymousClass73;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull RoutingContext p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass73) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    if (this.$bookController.textToSpeech(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {286}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$74")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$74, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$74.class */
    static final class AnonymousClass74 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Unit>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookController $bookController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass74(BookController $bookController, Continuation<? super AnonymousClass74> $completion) {
            super(2, $completion);
            this.$bookController = $bookController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass74 anonymousClass74 = new AnonymousClass74(this.$bookController, $completion);
            anonymousClass74.L$0 = value;
            return anonymousClass74;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull RoutingContext p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass74) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    if (this.$bookController.textToSpeech(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {290}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$75")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$75, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$75.class */
    static final class AnonymousClass75 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass75(UserController $userController, Continuation<? super AnonymousClass75> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass75 anonymousClass75 = new AnonymousClass75(this.$userController, $completion);
            anonymousClass75.L$0 = value;
            return anonymousClass75;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass75) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objUploadFile = this.$userController.uploadFile(it, this);
                    return objUploadFile == coroutine_suspended ? coroutine_suspended : objUploadFile;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {293}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$76")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$76, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$76.class */
    static final class AnonymousClass76 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass76(UserController $userController, Continuation<? super AnonymousClass76> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass76 anonymousClass76 = new AnonymousClass76(this.$userController, $completion);
            anonymousClass76.L$0 = value;
            return anonymousClass76;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass76) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteFile = this.$userController.deleteFile(it, this);
                    return objDeleteFile == coroutine_suspended ? coroutine_suspended : objDeleteFile;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {296}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$77")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$77, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$77.class */
    static final class AnonymousClass77 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass77(UserController $userController, Continuation<? super AnonymousClass77> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass77 anonymousClass77 = new AnonymousClass77(this.$userController, $completion);
            anonymousClass77.L$0 = value;
            return anonymousClass77;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass77) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objLogin = this.$userController.login(it, this);
                    return objLogin == coroutine_suspended ? coroutine_suspended : objLogin;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {298}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$78")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$78, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$78.class */
    static final class AnonymousClass78 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass78(UserController $userController, Continuation<? super AnonymousClass78> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass78 anonymousClass78 = new AnonymousClass78(this.$userController, $completion);
            anonymousClass78.L$0 = value;
            return anonymousClass78;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass78) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objLogout = this.$userController.logout(it, this);
                    return objLogout == coroutine_suspended ? coroutine_suspended : objLogout;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {301}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$79")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$79, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$79.class */
    static final class AnonymousClass79 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass79(UserController $userController, Continuation<? super AnonymousClass79> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass79 anonymousClass79 = new AnonymousClass79(this.$userController, $completion);
            anonymousClass79.L$0 = value;
            return anonymousClass79;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass79) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object userInfo = this.$userController.getUserInfo(it, this);
                    return userInfo == coroutine_suspended ? coroutine_suspended : userInfo;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {304}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$80")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$80, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$80.class */
    static final class AnonymousClass80 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass80(UserController $userController, Continuation<? super AnonymousClass80> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass80 anonymousClass80 = new AnonymousClass80(this.$userController, $completion);
            anonymousClass80.L$0 = value;
            return anonymousClass80;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass80) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveUserConfig = this.$userController.saveUserConfig(it, this);
                    return objSaveUserConfig == coroutine_suspended ? coroutine_suspended : objSaveUserConfig;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {StatusLine.HTTP_TEMP_REDIRECT}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$81")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$81, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$81.class */
    static final class AnonymousClass81 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass81(UserController $userController, Continuation<? super AnonymousClass81> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass81 anonymousClass81 = new AnonymousClass81(this.$userController, $completion);
            anonymousClass81.L$0 = value;
            return anonymousClass81;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass81) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object userConfig = this.$userController.getUserConfig(it, this);
                    return userConfig == coroutine_suspended ? coroutine_suspended : userConfig;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {310}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$82")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$82, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$82.class */
    static final class AnonymousClass82 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass82(UserController $userController, Continuation<? super AnonymousClass82> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass82 anonymousClass82 = new AnonymousClass82(this.$userController, $completion);
            anonymousClass82.L$0 = value;
            return anonymousClass82;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass82) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object userList = this.$userController.getUserList(it, this);
                    return userList == coroutine_suspended ? coroutine_suspended : userList;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {313}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$83")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$83, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$83.class */
    static final class AnonymousClass83 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass83(UserController $userController, Continuation<? super AnonymousClass83> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass83 anonymousClass83 = new AnonymousClass83(this.$userController, $completion);
            anonymousClass83.L$0 = value;
            return anonymousClass83;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass83) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteUsers = this.$userController.deleteUsers(it, this);
                    return objDeleteUsers == coroutine_suspended ? coroutine_suspended : objDeleteUsers;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {316}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$84")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$84, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$84.class */
    static final class AnonymousClass84 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass84(UserController $userController, Continuation<? super AnonymousClass84> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass84 anonymousClass84 = new AnonymousClass84(this.$userController, $completion);
            anonymousClass84.L$0 = value;
            return anonymousClass84;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass84) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objClearInactiveUsers = this.$userController.clearInactiveUsers(it, this);
                    return objClearInactiveUsers == coroutine_suspended ? coroutine_suspended : objClearInactiveUsers;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {319}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$85")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$85, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$85.class */
    static final class AnonymousClass85 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass85(UserController $userController, Continuation<? super AnonymousClass85> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass85 anonymousClass85 = new AnonymousClass85(this.$userController, $completion);
            anonymousClass85.L$0 = value;
            return anonymousClass85;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass85) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objAddUser = this.$userController.addUser(it, this);
                    return objAddUser == coroutine_suspended ? coroutine_suspended : objAddUser;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {322}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$86")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$86, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$86.class */
    static final class AnonymousClass86 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass86(UserController $userController, Continuation<? super AnonymousClass86> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass86 anonymousClass86 = new AnonymousClass86(this.$userController, $completion);
            anonymousClass86.L$0 = value;
            return anonymousClass86;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass86) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objResetPassword = this.$userController.resetPassword(it, this);
                    return objResetPassword == coroutine_suspended ? coroutine_suspended : objResetPassword;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {325}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$87")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$87, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$87.class */
    static final class AnonymousClass87 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass87(UserController $userController, Continuation<? super AnonymousClass87> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass87 anonymousClass87 = new AnonymousClass87(this.$userController, $completion);
            anonymousClass87.L$0 = value;
            return anonymousClass87;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass87) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objUpdateUser = this.$userController.updateUser(it, this);
                    return objUpdateUser == coroutine_suspended ? coroutine_suspended : objUpdateUser;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {327}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$88")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$88, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$88.class */
    static final class AnonymousClass88 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Unit>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ UserController $userController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass88(UserController $userController, Continuation<? super AnonymousClass88> $completion) {
            super(2, $completion);
            this.$userController = $userController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass88 anonymousClass88 = new AnonymousClass88(this.$userController, $completion);
            anonymousClass88.L$0 = value;
            return anonymousClass88;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull RoutingContext p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass88) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    if (this.$userController.downloadBackupFile(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {330}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$89")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$89, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$89.class */
    static final class AnonymousClass89 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass89(LicenseController $licenseController, Continuation<? super AnonymousClass89> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass89 anonymousClass89 = new AnonymousClass89(this.$licenseController, $completion);
            anonymousClass89.L$0 = value;
            return anonymousClass89;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass89) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object license = this.$licenseController.getLicense(it, this);
                    return license == coroutine_suspended ? coroutine_suspended : license;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {331}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$90")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$90, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$90.class */
    static final class AnonymousClass90 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Unit>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass90(LicenseController $licenseController, Continuation<? super AnonymousClass90> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass90 anonymousClass90 = new AnonymousClass90(this.$licenseController, $completion);
            anonymousClass90.L$0 = value;
            return anonymousClass90;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull RoutingContext p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass90) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    if (this.$licenseController.importLicense(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {332}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$91")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$91, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$91.class */
    static final class AnonymousClass91 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass91(LicenseController $licenseController, Continuation<? super AnonymousClass91> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass91 anonymousClass91 = new AnonymousClass91(this.$licenseController, $completion);
            anonymousClass91.L$0 = value;
            return anonymousClass91;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass91) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objGenerateKeys = this.$licenseController.generateKeys(it, this);
                    return objGenerateKeys == coroutine_suspended ? coroutine_suspended : objGenerateKeys;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {333}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$92")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$92, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$92.class */
    static final class AnonymousClass92 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass92(LicenseController $licenseController, Continuation<? super AnonymousClass92> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass92 anonymousClass92 = new AnonymousClass92(this.$licenseController, $completion);
            anonymousClass92.L$0 = value;
            return anonymousClass92;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass92) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objGenerateKeys = this.$licenseController.generateKeys(it, this);
                    return objGenerateKeys == coroutine_suspended ? coroutine_suspended : objGenerateKeys;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {334}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$93")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$93, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$93.class */
    static final class AnonymousClass93 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass93(LicenseController $licenseController, Continuation<? super AnonymousClass93> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass93 anonymousClass93 = new AnonymousClass93(this.$licenseController, $completion);
            anonymousClass93.L$0 = value;
            return anonymousClass93;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass93) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objGenerateLicense = this.$licenseController.generateLicense(it, this);
                    return objGenerateLicense == coroutine_suspended ? coroutine_suspended : objGenerateLicense;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {335}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$94")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$94, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$94.class */
    static final class AnonymousClass94 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass94(LicenseController $licenseController, Continuation<? super AnonymousClass94> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass94 anonymousClass94 = new AnonymousClass94(this.$licenseController, $completion);
            anonymousClass94.L$0 = value;
            return anonymousClass94;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass94) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objGenerateLicense = this.$licenseController.generateLicense(it, this);
                    return objGenerateLicense == coroutine_suspended ? coroutine_suspended : objGenerateLicense;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {336}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$95")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$95, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$95.class */
    static final class AnonymousClass95 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass95(LicenseController $licenseController, Continuation<? super AnonymousClass95> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass95 anonymousClass95 = new AnonymousClass95(this.$licenseController, $completion);
            anonymousClass95.L$0 = value;
            return anonymousClass95;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass95) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objIsHostValid = this.$licenseController.isHostValid(it, this);
                    return objIsHostValid == coroutine_suspended ? coroutine_suspended : objIsHostValid;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {337}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$96")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$96, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$96.class */
    static final class AnonymousClass96 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass96(LicenseController $licenseController, Continuation<? super AnonymousClass96> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass96 anonymousClass96 = new AnonymousClass96(this.$licenseController, $completion);
            anonymousClass96.L$0 = value;
            return anonymousClass96;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass96) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objIsHostValid = this.$licenseController.isHostValid(it, this);
                    return objIsHostValid == coroutine_suspended ? coroutine_suspended : objIsHostValid;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {339}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$97")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$97, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$97.class */
    static final class AnonymousClass97 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass97(LicenseController $licenseController, Continuation<? super AnonymousClass97> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass97 anonymousClass97 = new AnonymousClass97(this.$licenseController, $completion);
            anonymousClass97.L$0 = value;
            return anonymousClass97;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass97) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objActivateLicense = this.$licenseController.activateLicense(it, this);
                    return objActivateLicense == coroutine_suspended ? coroutine_suspended : objActivateLicense;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {341}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$98")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$98, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$98.class */
    static final class AnonymousClass98 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass98(LicenseController $licenseController, Continuation<? super AnonymousClass98> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass98 anonymousClass98 = new AnonymousClass98(this.$licenseController, $completion);
            anonymousClass98.L$0 = value;
            return anonymousClass98;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass98) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objIsLicenseValid = this.$licenseController.isLicenseValid(it, this);
                    return objIsLicenseValid == coroutine_suspended ? coroutine_suspended : objIsLicenseValid;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {342}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$99")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$99, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$99.class */
    static final class AnonymousClass99 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass99(LicenseController $licenseController, Continuation<? super AnonymousClass99> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass99 anonymousClass99 = new AnonymousClass99(this.$licenseController, $completion);
            anonymousClass99.L$0 = value;
            return anonymousClass99;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass99) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objIsLicenseValid = this.$licenseController.isLicenseValid(it, this);
                    return objIsLicenseValid == coroutine_suspended ? coroutine_suspended : objIsLicenseValid;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {344}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$100")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$100, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$100.class */
    static final class AnonymousClass100 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass100(LicenseController $licenseController, Continuation<? super AnonymousClass100> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass100 anonymousClass100 = new AnonymousClass100(this.$licenseController, $completion);
            anonymousClass100.L$0 = value;
            return anonymousClass100;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass100) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDecryptLicense = this.$licenseController.decryptLicense(it, this);
                    return objDecryptLicense == coroutine_suspended ? coroutine_suspended : objDecryptLicense;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {346}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$101")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$101, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$101.class */
    static final class AnonymousClass101 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass101(LicenseController $licenseController, Continuation<? super AnonymousClass101> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass101 anonymousClass101 = new AnonymousClass101(this.$licenseController, $completion);
            anonymousClass101.L$0 = value;
            return anonymousClass101;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass101) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSendCodeToEmail = this.$licenseController.sendCodeToEmail(it, this);
                    return objSendCodeToEmail == coroutine_suspended ? coroutine_suspended : objSendCodeToEmail;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {348}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$102")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$102, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$102.class */
    static final class AnonymousClass102 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ LicenseController $licenseController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass102(LicenseController $licenseController, Continuation<? super AnonymousClass102> $completion) {
            super(2, $completion);
            this.$licenseController = $licenseController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass102 anonymousClass102 = new AnonymousClass102(this.$licenseController, $completion);
            anonymousClass102.L$0 = value;
            return anonymousClass102;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass102) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSupplyLicense = this.$licenseController.supplyLicense(it, this);
                    return objSupplyLicense == coroutine_suspended ? coroutine_suspended : objSupplyLicense;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {352}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$103")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$103, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$103.class */
    static final class AnonymousClass103 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ WebdavController $webdavController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass103(WebdavController $webdavController, Continuation<? super AnonymousClass103> $completion) {
            super(2, $completion);
            this.$webdavController = $webdavController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass103 anonymousClass103 = new AnonymousClass103(this.$webdavController, $completion);
            anonymousClass103.L$0 = value;
            return anonymousClass103;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass103) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objBackupToWebdav = this.$webdavController.backupToWebdav(it, this);
                    return objBackupToWebdav == coroutine_suspended ? coroutine_suspended : objBackupToWebdav;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {357}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$104")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$104, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$104.class */
    static final class AnonymousClass104 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ RssSourceController $rssSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass104(RssSourceController $rssSourceController, Continuation<? super AnonymousClass104> $completion) {
            super(2, $completion);
            this.$rssSourceController = $rssSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass104 anonymousClass104 = new AnonymousClass104(this.$rssSourceController, $completion);
            anonymousClass104.L$0 = value;
            return anonymousClass104;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass104) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object rssSources = this.$rssSourceController.getRssSources(it, this);
                    return rssSources == coroutine_suspended ? coroutine_suspended : rssSources;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {358}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$105")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$105, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$105.class */
    static final class AnonymousClass105 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ RssSourceController $rssSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass105(RssSourceController $rssSourceController, Continuation<? super AnonymousClass105> $completion) {
            super(2, $completion);
            this.$rssSourceController = $rssSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass105 anonymousClass105 = new AnonymousClass105(this.$rssSourceController, $completion);
            anonymousClass105.L$0 = value;
            return anonymousClass105;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass105) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveRssSource = this.$rssSourceController.saveRssSource(it, this);
                    return objSaveRssSource == coroutine_suspended ? coroutine_suspended : objSaveRssSource;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {359}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$106")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$106, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$106.class */
    static final class AnonymousClass106 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ RssSourceController $rssSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass106(RssSourceController $rssSourceController, Continuation<? super AnonymousClass106> $completion) {
            super(2, $completion);
            this.$rssSourceController = $rssSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass106 anonymousClass106 = new AnonymousClass106(this.$rssSourceController, $completion);
            anonymousClass106.L$0 = value;
            return anonymousClass106;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass106) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveRssSources = this.$rssSourceController.saveRssSources(it, this);
                    return objSaveRssSources == coroutine_suspended ? coroutine_suspended : objSaveRssSources;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {360}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$107")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$107, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$107.class */
    static final class AnonymousClass107 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ RssSourceController $rssSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass107(RssSourceController $rssSourceController, Continuation<? super AnonymousClass107> $completion) {
            super(2, $completion);
            this.$rssSourceController = $rssSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass107 anonymousClass107 = new AnonymousClass107(this.$rssSourceController, $completion);
            anonymousClass107.L$0 = value;
            return anonymousClass107;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass107) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteRssSource = this.$rssSourceController.deleteRssSource(it, this);
                    return objDeleteRssSource == coroutine_suspended ? coroutine_suspended : objDeleteRssSource;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {362}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$108")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$108, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$108.class */
    static final class AnonymousClass108 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ RssSourceController $rssSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass108(RssSourceController $rssSourceController, Continuation<? super AnonymousClass108> $completion) {
            super(2, $completion);
            this.$rssSourceController = $rssSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass108 anonymousClass108 = new AnonymousClass108(this.$rssSourceController, $completion);
            anonymousClass108.L$0 = value;
            return anonymousClass108;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass108) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object rssArticles = this.$rssSourceController.getRssArticles(it, this);
                    return rssArticles == coroutine_suspended ? coroutine_suspended : rssArticles;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {363}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$109")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$109, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$109.class */
    static final class AnonymousClass109 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ RssSourceController $rssSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass109(RssSourceController $rssSourceController, Continuation<? super AnonymousClass109> $completion) {
            super(2, $completion);
            this.$rssSourceController = $rssSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass109 anonymousClass109 = new AnonymousClass109(this.$rssSourceController, $completion);
            anonymousClass109.L$0 = value;
            return anonymousClass109;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass109) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object rssArticles = this.$rssSourceController.getRssArticles(it, this);
                    return rssArticles == coroutine_suspended ? coroutine_suspended : rssArticles;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {365}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$110")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$110, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$110.class */
    static final class AnonymousClass110 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ RssSourceController $rssSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass110(RssSourceController $rssSourceController, Continuation<? super AnonymousClass110> $completion) {
            super(2, $completion);
            this.$rssSourceController = $rssSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass110 anonymousClass110 = new AnonymousClass110(this.$rssSourceController, $completion);
            anonymousClass110.L$0 = value;
            return anonymousClass110;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass110) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object rssContent = this.$rssSourceController.getRssContent(it, this);
                    return rssContent == coroutine_suspended ? coroutine_suspended : rssContent;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {366}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$111")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$111, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$111.class */
    static final class AnonymousClass111 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ RssSourceController $rssSourceController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass111(RssSourceController $rssSourceController, Continuation<? super AnonymousClass111> $completion) {
            super(2, $completion);
            this.$rssSourceController = $rssSourceController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass111 anonymousClass111 = new AnonymousClass111(this.$rssSourceController, $completion);
            anonymousClass111.L$0 = value;
            return anonymousClass111;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass111) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object rssContent = this.$rssSourceController.getRssContent(it, this);
                    return rssContent == coroutine_suspended ? coroutine_suspended : rssContent;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {369}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$112")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$112, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$112.class */
    static final class AnonymousClass112 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ ReplaceRuleController $replaceRuleController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass112(ReplaceRuleController $replaceRuleController, Continuation<? super AnonymousClass112> $completion) {
            super(2, $completion);
            this.$replaceRuleController = $replaceRuleController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass112 anonymousClass112 = new AnonymousClass112(this.$replaceRuleController, $completion);
            anonymousClass112.L$0 = value;
            return anonymousClass112;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass112) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object list = this.$replaceRuleController.list(it, this);
                    return list == coroutine_suspended ? coroutine_suspended : list;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {370}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$113")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$113, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$113.class */
    static final class AnonymousClass113 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ ReplaceRuleController $replaceRuleController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass113(ReplaceRuleController $replaceRuleController, Continuation<? super AnonymousClass113> $completion) {
            super(2, $completion);
            this.$replaceRuleController = $replaceRuleController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass113 anonymousClass113 = new AnonymousClass113(this.$replaceRuleController, $completion);
            anonymousClass113.L$0 = value;
            return anonymousClass113;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass113) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSave = this.$replaceRuleController.save(it, this);
                    return objSave == coroutine_suspended ? coroutine_suspended : objSave;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {371}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$114")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$114, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$114.class */
    static final class AnonymousClass114 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ ReplaceRuleController $replaceRuleController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass114(ReplaceRuleController $replaceRuleController, Continuation<? super AnonymousClass114> $completion) {
            super(2, $completion);
            this.$replaceRuleController = $replaceRuleController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass114 anonymousClass114 = new AnonymousClass114(this.$replaceRuleController, $completion);
            anonymousClass114.L$0 = value;
            return anonymousClass114;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass114) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveMulti = this.$replaceRuleController.saveMulti(it, this);
                    return objSaveMulti == coroutine_suspended ? coroutine_suspended : objSaveMulti;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {372}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$115")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$115, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$115.class */
    static final class AnonymousClass115 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ ReplaceRuleController $replaceRuleController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass115(ReplaceRuleController $replaceRuleController, Continuation<? super AnonymousClass115> $completion) {
            super(2, $completion);
            this.$replaceRuleController = $replaceRuleController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass115 anonymousClass115 = new AnonymousClass115(this.$replaceRuleController, $completion);
            anonymousClass115.L$0 = value;
            return anonymousClass115;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass115) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDelete = this.$replaceRuleController.delete(it, this);
                    return objDelete == coroutine_suspended ? coroutine_suspended : objDelete;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {373}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$116")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$116, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$116.class */
    static final class AnonymousClass116 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ ReplaceRuleController $replaceRuleController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass116(ReplaceRuleController $replaceRuleController, Continuation<? super AnonymousClass116> $completion) {
            super(2, $completion);
            this.$replaceRuleController = $replaceRuleController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass116 anonymousClass116 = new AnonymousClass116(this.$replaceRuleController, $completion);
            anonymousClass116.L$0 = value;
            return anonymousClass116;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass116) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteMulti = this.$replaceRuleController.deleteMulti(it, this);
                    return objDeleteMulti == coroutine_suspended ? coroutine_suspended : objDeleteMulti;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {376}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$117")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$117, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$117.class */
    static final class AnonymousClass117 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookmarkController $bookmarkController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass117(BookmarkController $bookmarkController, Continuation<? super AnonymousClass117> $completion) {
            super(2, $completion);
            this.$bookmarkController = $bookmarkController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass117 anonymousClass117 = new AnonymousClass117(this.$bookmarkController, $completion);
            anonymousClass117.L$0 = value;
            return anonymousClass117;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass117) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object list = this.$bookmarkController.list(it, this);
                    return list == coroutine_suspended ? coroutine_suspended : list;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {377}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$118")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$118, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$118.class */
    static final class AnonymousClass118 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookmarkController $bookmarkController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass118(BookmarkController $bookmarkController, Continuation<? super AnonymousClass118> $completion) {
            super(2, $completion);
            this.$bookmarkController = $bookmarkController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass118 anonymousClass118 = new AnonymousClass118(this.$bookmarkController, $completion);
            anonymousClass118.L$0 = value;
            return anonymousClass118;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass118) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSave = this.$bookmarkController.save(it, this);
                    return objSave == coroutine_suspended ? coroutine_suspended : objSave;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {378}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$119")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$119, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$119.class */
    static final class AnonymousClass119 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookmarkController $bookmarkController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass119(BookmarkController $bookmarkController, Continuation<? super AnonymousClass119> $completion) {
            super(2, $completion);
            this.$bookmarkController = $bookmarkController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass119 anonymousClass119 = new AnonymousClass119(this.$bookmarkController, $completion);
            anonymousClass119.L$0 = value;
            return anonymousClass119;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass119) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveMulti = this.$bookmarkController.saveMulti(it, this);
                    return objSaveMulti == coroutine_suspended ? coroutine_suspended : objSaveMulti;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {379}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$120")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$120, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$120.class */
    static final class AnonymousClass120 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookmarkController $bookmarkController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass120(BookmarkController $bookmarkController, Continuation<? super AnonymousClass120> $completion) {
            super(2, $completion);
            this.$bookmarkController = $bookmarkController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass120 anonymousClass120 = new AnonymousClass120(this.$bookmarkController, $completion);
            anonymousClass120.L$0 = value;
            return anonymousClass120;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass120) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDelete = this.$bookmarkController.delete(it, this);
                    return objDelete == coroutine_suspended ? coroutine_suspended : objDelete;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {380}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$121")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$121, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$121.class */
    static final class AnonymousClass121 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ BookmarkController $bookmarkController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass121(BookmarkController $bookmarkController, Continuation<? super AnonymousClass121> $completion) {
            super(2, $completion);
            this.$bookmarkController = $bookmarkController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass121 anonymousClass121 = new AnonymousClass121(this.$bookmarkController, $completion);
            anonymousClass121.L$0 = value;
            return anonymousClass121;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass121) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteMulti = this.$bookmarkController.deleteMulti(it, this);
                    return objDeleteMulti == coroutine_suspended ? coroutine_suspended : objDeleteMulti;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {384}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$122")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$122, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$122.class */
    static final class AnonymousClass122 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ FileController $fileController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass122(FileController $fileController, Continuation<? super AnonymousClass122> $completion) {
            super(2, $completion);
            this.$fileController = $fileController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass122 anonymousClass122 = new AnonymousClass122(this.$fileController, $completion);
            anonymousClass122.L$0 = value;
            return anonymousClass122;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass122) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object list = this.$fileController.list(it, this);
                    return list == coroutine_suspended ? coroutine_suspended : list;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {387}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$123")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$123, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$123.class */
    static final class AnonymousClass123 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ FileController $fileController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass123(FileController $fileController, Continuation<? super AnonymousClass123> $completion) {
            super(2, $completion);
            this.$fileController = $fileController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass123 anonymousClass123 = new AnonymousClass123(this.$fileController, $completion);
            anonymousClass123.L$0 = value;
            return anonymousClass123;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass123) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object obj = this.$fileController.get(it, this);
                    return obj == coroutine_suspended ? coroutine_suspended : obj;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {390}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$124")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$124, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$124.class */
    static final class AnonymousClass124 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ FileController $fileController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass124(FileController $fileController, Continuation<? super AnonymousClass124> $completion) {
            super(2, $completion);
            this.$fileController = $fileController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass124 anonymousClass124 = new AnonymousClass124(this.$fileController, $completion);
            anonymousClass124.L$0 = value;
            return anonymousClass124;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass124) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSave = this.$fileController.save(it, this);
                    return objSave == coroutine_suspended ? coroutine_suspended : objSave;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {393}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$125")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$125, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$125.class */
    static final class AnonymousClass125 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ FileController $fileController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass125(FileController $fileController, Continuation<? super AnonymousClass125> $completion) {
            super(2, $completion);
            this.$fileController = $fileController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass125 anonymousClass125 = new AnonymousClass125(this.$fileController, $completion);
            anonymousClass125.L$0 = value;
            return anonymousClass125;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass125) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objMkdir = this.$fileController.mkdir(it, this);
                    return objMkdir == coroutine_suspended ? coroutine_suspended : objMkdir;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {396}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$126")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$126, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$126.class */
    static final class AnonymousClass126 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Unit>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ FileController $fileController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass126(FileController $fileController, Continuation<? super AnonymousClass126> $completion) {
            super(2, $completion);
            this.$fileController = $fileController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass126 anonymousClass126 = new AnonymousClass126(this.$fileController, $completion);
            anonymousClass126.L$0 = value;
            return anonymousClass126;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull RoutingContext p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass126) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    if (this.$fileController.download(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {399}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$127")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$127, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$127.class */
    static final class AnonymousClass127 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ FileController $fileController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass127(FileController $fileController, Continuation<? super AnonymousClass127> $completion) {
            super(2, $completion);
            this.$fileController = $fileController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass127 anonymousClass127 = new AnonymousClass127(this.$fileController, $completion);
            anonymousClass127.L$0 = value;
            return anonymousClass127;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass127) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objUpload = this.$fileController.upload(it, this);
                    return objUpload == coroutine_suspended ? coroutine_suspended : objUpload;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {402}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$128")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$128, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$128.class */
    static final class AnonymousClass128 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ FileController $fileController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass128(FileController $fileController, Continuation<? super AnonymousClass128> $completion) {
            super(2, $completion);
            this.$fileController = $fileController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass128 anonymousClass128 = new AnonymousClass128(this.$fileController, $completion);
            anonymousClass128.L$0 = value;
            return anonymousClass128;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass128) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDelete = this.$fileController.delete(it, this);
                    return objDelete == coroutine_suspended ? coroutine_suspended : objDelete;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {403}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$129")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$129, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$129.class */
    static final class AnonymousClass129 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ FileController $fileController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass129(FileController $fileController, Continuation<? super AnonymousClass129> $completion) {
            super(2, $completion);
            this.$fileController = $fileController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass129 anonymousClass129 = new AnonymousClass129(this.$fileController, $completion);
            anonymousClass129.L$0 = value;
            return anonymousClass129;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass129) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteMulti = this.$fileController.deleteMulti(it, this);
                    return objDeleteMulti == coroutine_suspended ? coroutine_suspended : objDeleteMulti;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {406}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$130")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$130, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$130.class */
    static final class AnonymousClass130 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ FileController $fileController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass130(FileController $fileController, Continuation<? super AnonymousClass130> $completion) {
            super(2, $completion);
            this.$fileController = $fileController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass130 anonymousClass130 = new AnonymousClass130(this.$fileController, $completion);
            anonymousClass130.L$0 = value;
            return anonymousClass130;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass130) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objImportPreview = this.$fileController.importPreview(it, this);
                    return objImportPreview == coroutine_suspended ? coroutine_suspended : objImportPreview;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {409}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$131")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$131, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$131.class */
    static final class AnonymousClass131 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ FileController $fileController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass131(FileController $fileController, Continuation<? super AnonymousClass131> $completion) {
            super(2, $completion);
            this.$fileController = $fileController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass131 anonymousClass131 = new AnonymousClass131(this.$fileController, $completion);
            anonymousClass131.L$0 = value;
            return anonymousClass131;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass131) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objRestore = this.$fileController.restore(it, this);
                    return objRestore == coroutine_suspended ? coroutine_suspended : objRestore;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {411}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$132")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$132, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$132.class */
    static final class AnonymousClass132 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ FileController $fileController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass132(FileController $fileController, Continuation<? super AnonymousClass132> $completion) {
            super(2, $completion);
            this.$fileController = $fileController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass132 anonymousClass132 = new AnonymousClass132(this.$fileController, $completion);
            anonymousClass132.L$0 = value;
            return anonymousClass132;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass132) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object obj = this.$fileController.parse(it, this);
                    return obj == coroutine_suspended ? coroutine_suspended : obj;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {412}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$133")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$133, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$133.class */
    static final class AnonymousClass133 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ FileController $fileController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass133(FileController $fileController, Continuation<? super AnonymousClass133> $completion) {
            super(2, $completion);
            this.$fileController = $fileController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass133 anonymousClass133 = new AnonymousClass133(this.$fileController, $completion);
            anonymousClass133.L$0 = value;
            return anonymousClass133;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass133) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object obj = this.$fileController.parse(it, this);
                    return obj == coroutine_suspended ? coroutine_suspended : obj;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {415}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$134")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$134, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$134.class */
    static final class AnonymousClass134 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ HttpTTSController $httpTTSController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass134(HttpTTSController $httpTTSController, Continuation<? super AnonymousClass134> $completion) {
            super(2, $completion);
            this.$httpTTSController = $httpTTSController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass134 anonymousClass134 = new AnonymousClass134(this.$httpTTSController, $completion);
            anonymousClass134.L$0 = value;
            return anonymousClass134;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass134) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object list = this.$httpTTSController.list(it, this);
                    return list == coroutine_suspended ? coroutine_suspended : list;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {416}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$135")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$135, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$135.class */
    static final class AnonymousClass135 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ HttpTTSController $httpTTSController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass135(HttpTTSController $httpTTSController, Continuation<? super AnonymousClass135> $completion) {
            super(2, $completion);
            this.$httpTTSController = $httpTTSController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass135 anonymousClass135 = new AnonymousClass135(this.$httpTTSController, $completion);
            anonymousClass135.L$0 = value;
            return anonymousClass135;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass135) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSave = this.$httpTTSController.save(it, this);
                    return objSave == coroutine_suspended ? coroutine_suspended : objSave;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {417}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$136")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$136, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$136.class */
    static final class AnonymousClass136 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ HttpTTSController $httpTTSController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass136(HttpTTSController $httpTTSController, Continuation<? super AnonymousClass136> $completion) {
            super(2, $completion);
            this.$httpTTSController = $httpTTSController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass136 anonymousClass136 = new AnonymousClass136(this.$httpTTSController, $completion);
            anonymousClass136.L$0 = value;
            return anonymousClass136;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass136) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objSaveMulti = this.$httpTTSController.saveMulti(it, this);
                    return objSaveMulti == coroutine_suspended ? coroutine_suspended : objSaveMulti;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {418}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$137")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$137, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$137.class */
    static final class AnonymousClass137 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ HttpTTSController $httpTTSController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass137(HttpTTSController $httpTTSController, Continuation<? super AnonymousClass137> $completion) {
            super(2, $completion);
            this.$httpTTSController = $httpTTSController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass137 anonymousClass137 = new AnonymousClass137(this.$httpTTSController, $completion);
            anonymousClass137.L$0 = value;
            return anonymousClass137;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass137) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDelete = this.$httpTTSController.delete(it, this);
                    return objDelete == coroutine_suspended ? coroutine_suspended : objDelete;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/vertx/ext/web/RoutingContext;"})
    @DebugMetadata(f = "YueduApi.kt", l = {419}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$initRouter$138")
    /* renamed from: com.htmake.reader.api.YueduApi$initRouter$138, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$initRouter$138.class */
    static final class AnonymousClass138 extends SuspendLambda implements Function2<RoutingContext, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ HttpTTSController $httpTTSController;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass138(HttpTTSController $httpTTSController, Continuation<? super AnonymousClass138> $completion) {
            super(2, $completion);
            this.$httpTTSController = $httpTTSController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass138 anonymousClass138 = new AnonymousClass138(this.$httpTTSController, $completion);
            anonymousClass138.L$0 = value;
            return anonymousClass138;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull RoutingContext p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass138) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(RoutingContext routingContext, Continuation<? super Object> continuation) {
            return invoke2(routingContext, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext it = (RoutingContext) this.L$0;
                    this.label = 1;
                    Object objDeleteMulti = this.$httpTTSController.deleteMulti(it, this);
                    return objDeleteMulti == coroutine_suspended ? coroutine_suspended : objDeleteMulti;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    static /* synthetic */ Object setupPort$suspendImpl(YueduApi yueduApi, Continuation $completion) {
        YueduApiKt.logger.info("port: {}", Boxing.boxInt(yueduApi.getPort()));
        Environment environment = yueduApi.env;
        if (environment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("env");
            throw null;
        }
        Integer serverPort = (Integer) environment.getProperty("reader.server.port", Integer.TYPE);
        YueduApiKt.logger.info("serverPort: {}", serverPort);
        if (serverPort != null && serverPort.intValue() > 0) {
            yueduApi.setPort(serverPort.intValue());
        }
        return Unit.INSTANCE;
    }

    static /* synthetic */ Object migration$suspendImpl(YueduApi yueduApi, Continuation $completion) {
        try {
            File storageDir = new File(ExtKt.getWorkDir("storage"));
            File dataDir = new File(ExtKt.getWorkDir("storage", "data", "default"));
            if (!storageDir.exists() || !dataDir.exists()) {
                dataDir.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }

    @Override // com.htmake.reader.verticle.RestVerticle
    @NotNull
    public String getContextPath() {
        Environment environment = this.env;
        if (environment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("env");
            throw null;
        }
        String contextPath = (String) environment.getProperty("reader.server.contextPath", String.class);
        String str = contextPath;
        if (!(str == null || str.length() == 0)) {
            Intrinsics.checkNotNullExpressionValue(contextPath, "contextPath");
            return contextPath;
        }
        return "";
    }

    @Override // com.htmake.reader.verticle.RestVerticle
    public void started() {
        SpringContextUtils.getApplicationContext().publishEvent((ApplicationEvent) new SpringEvent(this, "READY", ""));
    }

    @Override // com.htmake.reader.verticle.RestVerticle
    public void onStartError() {
        YueduApiKt.logger.error("应用启动失败，请检查" + getPort() + "端口是否被占用");
        SpringContextUtils.getApplicationContext().publishEvent((ApplicationEvent) new SpringEvent(this, "START_ERROR", "应用启动失败，请检查" + getPort() + "端口是否被占用"));
    }

    @Override // com.htmake.reader.verticle.RestVerticle
    public void onHandlerError(@NotNull RoutingContext ctx, @NotNull Exception error) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(error, "error");
        ReturnData returnData = new ReturnData();
        YueduApiKt.logger.error("onHandlerError: ", (Throwable) error);
        if (!ctx.response().headWritten()) {
            VertExtKt.success(ctx, returnData.setErrorMsg(error.toString()));
        } else {
            ctx.response().end(error.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getSystemInfo(io.vertx.ext.web.RoutingContext r14, kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 830
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.YueduApi.getSystemInfo(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", ClassicConstants.USER_MDC_KEY, "Lcom/htmake/reader/entity/User;"})
    @DebugMetadata(f = "YueduApi.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$getSystemInfo$2")
    /* renamed from: com.htmake.reader.api.YueduApi$getSystemInfo$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$getSystemInfo$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function3<CoroutineScope, User, Continuation<? super Boolean>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ Ref.IntRef $dayLoginUser;
        final /* synthetic */ Ref.IntRef $sevenDayLoginUser;
        final /* synthetic */ Calendar $calendar;
        final /* synthetic */ Ref.IntRef $monthLoginUser;
        final /* synthetic */ Ref.IntRef $dayRegisterUser;
        final /* synthetic */ Ref.IntRef $sevenDayRegisterUser;
        final /* synthetic */ Ref.IntRef $monthRegisterUser;
        final /* synthetic */ Ref.IntRef $keepUser;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Ref.IntRef $dayLoginUser, Ref.IntRef $sevenDayLoginUser, Calendar $calendar, Ref.IntRef $monthLoginUser, Ref.IntRef $dayRegisterUser, Ref.IntRef $sevenDayRegisterUser, Ref.IntRef $monthRegisterUser, Ref.IntRef $keepUser, Continuation<? super AnonymousClass2> $completion) {
            super(3, $completion);
            this.$dayLoginUser = $dayLoginUser;
            this.$sevenDayLoginUser = $sevenDayLoginUser;
            this.$calendar = $calendar;
            this.$monthLoginUser = $monthLoginUser;
            this.$dayRegisterUser = $dayRegisterUser;
            this.$sevenDayRegisterUser = $sevenDayRegisterUser;
            this.$monthRegisterUser = $monthRegisterUser;
            this.$keepUser = $keepUser;
        }

        @Override // kotlin.jvm.functions.Function3
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @NotNull User p2, @Nullable Continuation<? super Boolean> p3) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$dayLoginUser, this.$sevenDayLoginUser, this.$calendar, this.$monthLoginUser, this.$dayRegisterUser, this.$sevenDayRegisterUser, this.$monthRegisterUser, this.$keepUser, p3);
            anonymousClass2.L$0 = p2;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    User user = (User) this.L$0;
                    if (user.getLast_login_at() >= System.currentTimeMillis() - 86400000) {
                        this.$dayLoginUser.element++;
                    }
                    if (user.getLast_login_at() >= System.currentTimeMillis() - CoreConstants.MILLIS_IN_ONE_WEEK) {
                        this.$sevenDayLoginUser.element++;
                    }
                    if (user.getLast_login_at() >= this.$calendar.getTimeInMillis()) {
                        this.$monthLoginUser.element++;
                    }
                    if (user.getCreated_at() >= System.currentTimeMillis() - 86400000) {
                        this.$dayRegisterUser.element++;
                    }
                    if (user.getCreated_at() >= System.currentTimeMillis() - CoreConstants.MILLIS_IN_ONE_WEEK) {
                        this.$sevenDayRegisterUser.element++;
                    }
                    if (user.getCreated_at() >= this.$calendar.getTimeInMillis()) {
                        this.$monthRegisterUser.element++;
                    }
                    if (user.getLast_login_at() >= user.getCreated_at() + CoreConstants.MILLIS_IN_ONE_WEEK && user.getLast_login_at() >= System.currentTimeMillis() - CoreConstants.MILLIS_IN_ONE_WEEK) {
                        this.$keepUser.element++;
                    }
                    return Boxing.boxBoolean(false);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void shelfUpdateJob() throws IllegalArgumentException {
        AppConfig appConfig = this.appConfig;
        if (appConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appConfig");
            throw null;
        }
        if (appConfig.getShelfUpdateInteval() <= 0) {
            return;
        }
        Calendar now = Calendar.getInstance();
        int hour = now.get(11);
        int munite = now.get(12);
        int muniteFromToday = (hour * 60) + munite;
        AppConfig appConfig2 = this.appConfig;
        if (appConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appConfig");
            throw null;
        }
        if (muniteFromToday % appConfig2.getShelfUpdateInteval() != 0) {
            return;
        }
        MDC.put("traceId", ExtKt.getTraceId());
        BuildersKt__Builders_commonKt.launch$default(this, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new C00591(null), 2, null);
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "YueduApi.kt", l = {572, 576}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"$this$launch", "bookController"}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$shelfUpdateJob$1")
    /* renamed from: com.htmake.reader.api.YueduApi$shelfUpdateJob$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$shelfUpdateJob$1.class */
    static final class C00591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$1;
        int label;
        private /* synthetic */ Object L$0;

        C00591(Continuation<? super C00591> $completion) {
            super(2, $completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            C00591 c00591 = new C00591($completion);
            c00591.L$0 = value;
            return c00591;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((C00591) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x00bc  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) throws java.lang.Throwable {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                r12 = r0
                r0 = r7
                int r0 = r0.label
                switch(r0) {
                    case 0: goto L24;
                    case 1: goto L6c;
                    case 2: goto Lbf;
                    default: goto Ldc;
                }
            L24:
                r0 = r8
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r7
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                r9 = r0
                com.htmake.reader.api.controller.BookController r0 = new com.htmake.reader.api.controller.BookController     // Catch: java.lang.Exception -> Ld3
                r1 = r0
                r2 = r9
                kotlin.coroutines.CoroutineContext r2 = r2.getCoroutineContext()     // Catch: java.lang.Exception -> Ld3
                r1.<init>(r2)     // Catch: java.lang.Exception -> Ld3
                r10 = r0
                mu.KLogger r0 = com.htmake.reader.api.YueduApiKt.access$getLogger$p()     // Catch: java.lang.Exception -> Ld3
                java.lang.String r1 = "开始检查书架书籍更新"
                r0.info(r1)     // Catch: java.lang.Exception -> Ld3
                r0 = r10
                r1 = 1
                java.lang.String r2 = "default"
                r3 = r7
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3     // Catch: java.lang.Exception -> Ld3
                r4 = r7
                r5 = r9
                r4.L$0 = r5     // Catch: java.lang.Exception -> Ld3
                r4 = r7
                r5 = r10
                r4.L$1 = r5     // Catch: java.lang.Exception -> Ld3
                r4 = r7
                r5 = 1
                r4.label = r5     // Catch: java.lang.Exception -> Ld3
                java.lang.Object r0 = r0.getBookShelfBooks(r1, r2, r3)     // Catch: java.lang.Exception -> Ld3
                r1 = r0
                r2 = r12
                if (r1 != r2) goto L82
                r1 = r12
                return r1
            L6c:
                r0 = r7
                java.lang.Object r0 = r0.L$1
                com.htmake.reader.api.controller.BookController r0 = (com.htmake.reader.api.controller.BookController) r0
                r10 = r0
                r0 = r7
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                r9 = r0
                r0 = r8
                kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Exception -> Ld3
                r0 = r8
            L82:
                com.htmake.reader.api.controller.UserController r0 = new com.htmake.reader.api.controller.UserController     // Catch: java.lang.Exception -> Ld3
                r1 = r0
                r2 = r9
                kotlin.coroutines.CoroutineContext r2 = r2.getCoroutineContext()     // Catch: java.lang.Exception -> Ld3
                r1.<init>(r2)     // Catch: java.lang.Exception -> Ld3
                r11 = r0
                r0 = r11
                com.htmake.reader.api.YueduApi$shelfUpdateJob$1$1 r1 = new com.htmake.reader.api.YueduApi$shelfUpdateJob$1$1     // Catch: java.lang.Exception -> Ld3
                r2 = r1
                r3 = r10
                r4 = 0
                r2.<init>(r3, r4)     // Catch: java.lang.Exception -> Ld3
                kotlin.jvm.functions.Function3 r1 = (kotlin.jvm.functions.Function3) r1     // Catch: java.lang.Exception -> Ld3
                r2 = r7
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch: java.lang.Exception -> Ld3
                r3 = r7
                r4 = 0
                r3.L$0 = r4     // Catch: java.lang.Exception -> Ld3
                r3 = r7
                r4 = 0
                r3.L$1 = r4     // Catch: java.lang.Exception -> Ld3
                r3 = r7
                r4 = 2
                r3.label = r4     // Catch: java.lang.Exception -> Ld3
                java.lang.Object r0 = r0.forEachUser(r1, r2)     // Catch: java.lang.Exception -> Ld3
                r1 = r0
                r2 = r12
                if (r1 != r2) goto Lc5
                r1 = r12
                return r1
            Lbf:
                r0 = r8
                kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Exception -> Ld3
                r0 = r8
            Lc5:
                mu.KLogger r0 = com.htmake.reader.api.YueduApiKt.access$getLogger$p()     // Catch: java.lang.Exception -> Ld3
                java.lang.String r1 = "书架书籍更新检查结束"
                r0.info(r1)     // Catch: java.lang.Exception -> Ld3
                goto Ld8
            Ld3:
                r10 = move-exception
                r0 = r10
                r0.printStackTrace()
            Ld8:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            Ldc:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r1 = r0
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.YueduApi.C00591.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: YueduApi.kt */
        @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", ClassicConstants.USER_MDC_KEY, "Lcom/htmake/reader/entity/User;"})
        @DebugMetadata(f = "YueduApi.kt", l = {579}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$shelfUpdateJob$1$1")
        /* renamed from: com.htmake.reader.api.YueduApi$shelfUpdateJob$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$shelfUpdateJob$1$1.class */
        static final class C00241 extends SuspendLambda implements Function3<CoroutineScope, User, Continuation<? super Boolean>, Object> {
            int label;
            /* synthetic */ Object L$0;
            final /* synthetic */ BookController $bookController;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00241(BookController $bookController, Continuation<? super C00241> $completion) {
                super(3, $completion);
                this.$bookController = $bookController;
            }

            @Override // kotlin.jvm.functions.Function3
            @Nullable
            public final Object invoke(@NotNull CoroutineScope p1, @NotNull User p2, @Nullable Continuation<? super Boolean> p3) {
                C00241 c00241 = new C00241(this.$bookController, p3);
                c00241.L$0 = p2;
                return c00241.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        User user = (User) this.L$0;
                        if (user.getLast_login_at() >= System.currentTimeMillis() - 259200000) {
                            this.label = 1;
                            if (this.$bookController.getBookShelfBooks(true, user.getUsername(), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Boxing.boxBoolean(false);
            }
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void remoteBookSourceSubUpdateJob() throws IllegalArgumentException {
        AppConfig appConfig = this.appConfig;
        if (appConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appConfig");
            throw null;
        }
        if (appConfig.getRemoteBookSourceUpdateInterval() <= 0) {
            return;
        }
        Calendar now = Calendar.getInstance();
        int hour = now.get(11);
        int munite = now.get(12);
        int muniteFromToday = (hour * 60) + munite;
        AppConfig appConfig2 = this.appConfig;
        if (appConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appConfig");
            throw null;
        }
        if (muniteFromToday % appConfig2.getRemoteBookSourceUpdateInterval() != 0) {
            return;
        }
        MDC.put("traceId", ExtKt.getTraceId());
        BuildersKt__Builders_commonKt.launch$default(this, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new C00581(null), 2, null);
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "YueduApi.kt", l = {614, 618}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"$this$launch", "bookSourceController"}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$remoteBookSourceSubUpdateJob$1")
    /* renamed from: com.htmake.reader.api.YueduApi$remoteBookSourceSubUpdateJob$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$remoteBookSourceSubUpdateJob$1.class */
    static final class C00581 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$1;
        int label;
        private /* synthetic */ Object L$0;

        C00581(Continuation<? super C00581> $completion) {
            super(2, $completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            C00581 c00581 = new C00581($completion);
            c00581.L$0 = value;
            return c00581;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((C00581) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x00bc  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) throws java.lang.Throwable {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                r12 = r0
                r0 = r7
                int r0 = r0.label
                switch(r0) {
                    case 0: goto L24;
                    case 1: goto L6c;
                    case 2: goto Lbf;
                    default: goto Ldc;
                }
            L24:
                r0 = r8
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r7
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                r9 = r0
                com.htmake.reader.api.controller.BookSourceController r0 = new com.htmake.reader.api.controller.BookSourceController     // Catch: java.lang.Exception -> Ld3
                r1 = r0
                r2 = r9
                kotlin.coroutines.CoroutineContext r2 = r2.getCoroutineContext()     // Catch: java.lang.Exception -> Ld3
                r1.<init>(r2)     // Catch: java.lang.Exception -> Ld3
                r10 = r0
                mu.KLogger r0 = com.htmake.reader.api.YueduApiKt.access$getLogger$p()     // Catch: java.lang.Exception -> Ld3
                java.lang.String r1 = "开始检查远程书源更新"
                r0.info(r1)     // Catch: java.lang.Exception -> Ld3
                r0 = r10
                java.lang.String r1 = "default"
                r2 = 0
                r3 = r7
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3     // Catch: java.lang.Exception -> Ld3
                r4 = r7
                r5 = r9
                r4.L$0 = r5     // Catch: java.lang.Exception -> Ld3
                r4 = r7
                r5 = r10
                r4.L$1 = r5     // Catch: java.lang.Exception -> Ld3
                r4 = r7
                r5 = 1
                r4.label = r5     // Catch: java.lang.Exception -> Ld3
                java.lang.Object r0 = r0.updateRemoteSourceSub(r1, r2, r3)     // Catch: java.lang.Exception -> Ld3
                r1 = r0
                r2 = r12
                if (r1 != r2) goto L82
                r1 = r12
                return r1
            L6c:
                r0 = r7
                java.lang.Object r0 = r0.L$1
                com.htmake.reader.api.controller.BookSourceController r0 = (com.htmake.reader.api.controller.BookSourceController) r0
                r10 = r0
                r0 = r7
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                r9 = r0
                r0 = r8
                kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Exception -> Ld3
                r0 = r8
            L82:
                com.htmake.reader.api.controller.UserController r0 = new com.htmake.reader.api.controller.UserController     // Catch: java.lang.Exception -> Ld3
                r1 = r0
                r2 = r9
                kotlin.coroutines.CoroutineContext r2 = r2.getCoroutineContext()     // Catch: java.lang.Exception -> Ld3
                r1.<init>(r2)     // Catch: java.lang.Exception -> Ld3
                r11 = r0
                r0 = r11
                com.htmake.reader.api.YueduApi$remoteBookSourceSubUpdateJob$1$1 r1 = new com.htmake.reader.api.YueduApi$remoteBookSourceSubUpdateJob$1$1     // Catch: java.lang.Exception -> Ld3
                r2 = r1
                r3 = r10
                r4 = 0
                r2.<init>(r3, r4)     // Catch: java.lang.Exception -> Ld3
                kotlin.jvm.functions.Function3 r1 = (kotlin.jvm.functions.Function3) r1     // Catch: java.lang.Exception -> Ld3
                r2 = r7
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch: java.lang.Exception -> Ld3
                r3 = r7
                r4 = 0
                r3.L$0 = r4     // Catch: java.lang.Exception -> Ld3
                r3 = r7
                r4 = 0
                r3.L$1 = r4     // Catch: java.lang.Exception -> Ld3
                r3 = r7
                r4 = 2
                r3.label = r4     // Catch: java.lang.Exception -> Ld3
                java.lang.Object r0 = r0.forEachUser(r1, r2)     // Catch: java.lang.Exception -> Ld3
                r1 = r0
                r2 = r12
                if (r1 != r2) goto Lc5
                r1 = r12
                return r1
            Lbf:
                r0 = r8
                kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Exception -> Ld3
                r0 = r8
            Lc5:
                mu.KLogger r0 = com.htmake.reader.api.YueduApiKt.access$getLogger$p()     // Catch: java.lang.Exception -> Ld3
                java.lang.String r1 = "远程书源更新检查结束"
                r0.info(r1)     // Catch: java.lang.Exception -> Ld3
                goto Ld8
            Ld3:
                r10 = move-exception
                r0 = r10
                r0.printStackTrace()
            Ld8:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            Ldc:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r1 = r0
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.YueduApi.C00581.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: YueduApi.kt */
        @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", ClassicConstants.USER_MDC_KEY, "Lcom/htmake/reader/entity/User;"})
        @DebugMetadata(f = "YueduApi.kt", l = {621}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$remoteBookSourceSubUpdateJob$1$1")
        /* renamed from: com.htmake.reader.api.YueduApi$remoteBookSourceSubUpdateJob$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$remoteBookSourceSubUpdateJob$1$1.class */
        static final class C00231 extends SuspendLambda implements Function3<CoroutineScope, User, Continuation<? super Boolean>, Object> {
            int label;
            /* synthetic */ Object L$0;
            final /* synthetic */ BookSourceController $bookSourceController;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00231(BookSourceController $bookSourceController, Continuation<? super C00231> $completion) {
                super(3, $completion);
                this.$bookSourceController = $bookSourceController;
            }

            @Override // kotlin.jvm.functions.Function3
            @Nullable
            public final Object invoke(@NotNull CoroutineScope p1, @NotNull User p2, @Nullable Continuation<? super Boolean> p3) {
                C00231 c00231 = new C00231(this.$bookSourceController, p3);
                c00231.L$0 = p2;
                return c00231.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        User user = (User) this.L$0;
                        if (user.getLast_login_at() >= System.currentTimeMillis() - 259200000) {
                            this.label = 1;
                            if (this.$bookSourceController.updateRemoteSourceSub(user.getUsername(), user, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Boxing.boxBoolean(false);
            }
        }
    }

    @Scheduled(cron = "0 59 23 * * ?")
    public void clearUser() throws IllegalArgumentException {
        AppConfig appConfig = this.appConfig;
        if (appConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appConfig");
            throw null;
        }
        if (appConfig.getAutoClearInactiveUser() > 0) {
            AppConfig appConfig2 = this.appConfig;
            if (appConfig2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("appConfig");
                throw null;
            }
            if (!appConfig2.getSecure()) {
                return;
            }
            MDC.put("traceId", ExtKt.getTraceId());
            BuildersKt__Builders_commonKt.launch$default(this, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new C00551(null), 2, null);
        }
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "YueduApi.kt", l = {647}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$clearUser$1")
    /* renamed from: com.htmake.reader.api.YueduApi$clearUser$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$clearUser$1.class */
    static final class C00551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;

        C00551(Continuation<? super C00551> $completion) {
            super(2, $completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            C00551 c00551 = YueduApi.this.new C00551($completion);
            c00551.L$0 = value;
            return c00551;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((C00551) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        CoroutineScope $this$launch = (CoroutineScope) this.L$0;
                        KLogger kLogger = YueduApiKt.logger;
                        AppConfig appConfig = YueduApi.this.appConfig;
                        if (appConfig == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("appConfig");
                            throw null;
                        }
                        kLogger.info("开始清理 {} 天未登录用户", Boxing.boxInt(appConfig.getAutoClearInactiveUser()));
                        UserController userController = new UserController($this$launch.getCoroutineContext());
                        AppConfig appConfig2 = YueduApi.this.appConfig;
                        if (appConfig2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("appConfig");
                            throw null;
                        }
                        this.label = 1;
                        if (userController.clearInactiveUsers(appConfig2.getAutoClearInactiveUser(), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                YueduApiKt.logger.info("不活跃用户自动清理结束");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Unit.INSTANCE;
        }
    }

    @Scheduled(cron = "0 50 23 * * ?")
    public void autoBackup() throws IllegalArgumentException {
        AppConfig appConfig = this.appConfig;
        if (appConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appConfig");
            throw null;
        }
        if (!appConfig.getAutoBackupUserData()) {
            return;
        }
        MDC.put("traceId", ExtKt.getTraceId());
        BuildersKt__Builders_commonKt.launch$default(this, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new AnonymousClass1(null), 2, null);
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "YueduApi.kt", l = {672, 676}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"$this$launch", "bookController"}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$autoBackup$1")
    /* renamed from: com.htmake.reader.api.YueduApi$autoBackup$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$autoBackup$1.class */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$1;
        int label;
        private /* synthetic */ Object L$0;

        AnonymousClass1(Continuation<? super AnonymousClass1> $completion) {
            super(2, $completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1($completion);
            anonymousClass1.L$0 = value;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass1) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x00be  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) throws java.lang.Throwable {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                r14 = r0
                r0 = r9
                int r0 = r0.label
                switch(r0) {
                    case 0: goto L24;
                    case 1: goto L6e;
                    case 2: goto Lc1;
                    default: goto Lde;
                }
            L24:
                r0 = r10
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r9
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                r11 = r0
                com.htmake.reader.api.controller.BookController r0 = new com.htmake.reader.api.controller.BookController     // Catch: java.lang.Exception -> Ld5
                r1 = r0
                r2 = r11
                kotlin.coroutines.CoroutineContext r2 = r2.getCoroutineContext()     // Catch: java.lang.Exception -> Ld5
                r1.<init>(r2)     // Catch: java.lang.Exception -> Ld5
                r12 = r0
                mu.KLogger r0 = com.htmake.reader.api.YueduApiKt.access$getLogger$p()     // Catch: java.lang.Exception -> Ld5
                java.lang.String r1 = "开始备份用户数据"
                r0.info(r1)     // Catch: java.lang.Exception -> Ld5
                r0 = r12
                java.lang.String r1 = "default"
                r2 = 0
                r3 = r9
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3     // Catch: java.lang.Exception -> Ld5
                r4 = 2
                r5 = 0
                r6 = r9
                r7 = r11
                r6.L$0 = r7     // Catch: java.lang.Exception -> Ld5
                r6 = r9
                r7 = r12
                r6.L$1 = r7     // Catch: java.lang.Exception -> Ld5
                r6 = r9
                r7 = 1
                r6.label = r7     // Catch: java.lang.Exception -> Ld5
                java.lang.Object r0 = com.htmake.reader.api.controller.BookController.saveToWebdav$default(r0, r1, r2, r3, r4, r5)     // Catch: java.lang.Exception -> Ld5
                r1 = r0
                r2 = r14
                if (r1 != r2) goto L84
                r1 = r14
                return r1
            L6e:
                r0 = r9
                java.lang.Object r0 = r0.L$1
                com.htmake.reader.api.controller.BookController r0 = (com.htmake.reader.api.controller.BookController) r0
                r12 = r0
                r0 = r9
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                r11 = r0
                r0 = r10
                kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Exception -> Ld5
                r0 = r10
            L84:
                com.htmake.reader.api.controller.UserController r0 = new com.htmake.reader.api.controller.UserController     // Catch: java.lang.Exception -> Ld5
                r1 = r0
                r2 = r11
                kotlin.coroutines.CoroutineContext r2 = r2.getCoroutineContext()     // Catch: java.lang.Exception -> Ld5
                r1.<init>(r2)     // Catch: java.lang.Exception -> Ld5
                r13 = r0
                r0 = r13
                com.htmake.reader.api.YueduApi$autoBackup$1$1 r1 = new com.htmake.reader.api.YueduApi$autoBackup$1$1     // Catch: java.lang.Exception -> Ld5
                r2 = r1
                r3 = r12
                r4 = 0
                r2.<init>(r3, r4)     // Catch: java.lang.Exception -> Ld5
                kotlin.jvm.functions.Function3 r1 = (kotlin.jvm.functions.Function3) r1     // Catch: java.lang.Exception -> Ld5
                r2 = r9
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch: java.lang.Exception -> Ld5
                r3 = r9
                r4 = 0
                r3.L$0 = r4     // Catch: java.lang.Exception -> Ld5
                r3 = r9
                r4 = 0
                r3.L$1 = r4     // Catch: java.lang.Exception -> Ld5
                r3 = r9
                r4 = 2
                r3.label = r4     // Catch: java.lang.Exception -> Ld5
                java.lang.Object r0 = r0.forEachUser(r1, r2)     // Catch: java.lang.Exception -> Ld5
                r1 = r0
                r2 = r14
                if (r1 != r2) goto Lc7
                r1 = r14
                return r1
            Lc1:
                r0 = r10
                kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Exception -> Ld5
                r0 = r10
            Lc7:
                mu.KLogger r0 = com.htmake.reader.api.YueduApiKt.access$getLogger$p()     // Catch: java.lang.Exception -> Ld5
                java.lang.String r1 = "备份用户数据结束"
                r0.info(r1)     // Catch: java.lang.Exception -> Ld5
                goto Lda
            Ld5:
                r12 = move-exception
                r0 = r12
                r0.printStackTrace()
            Lda:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            Lde:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r1 = r0
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.YueduApi.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: YueduApi.kt */
        @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", ClassicConstants.USER_MDC_KEY, "Lcom/htmake/reader/entity/User;"})
        @DebugMetadata(f = "YueduApi.kt", l = {679}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$autoBackup$1$1")
        /* renamed from: com.htmake.reader.api.YueduApi$autoBackup$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$autoBackup$1$1.class */
        static final class C00221 extends SuspendLambda implements Function3<CoroutineScope, User, Continuation<? super Boolean>, Object> {
            int label;
            /* synthetic */ Object L$0;
            final /* synthetic */ BookController $bookController;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00221(BookController $bookController, Continuation<? super C00221> $completion) {
                super(3, $completion);
                this.$bookController = $bookController;
            }

            @Override // kotlin.jvm.functions.Function3
            @Nullable
            public final Object invoke(@NotNull CoroutineScope p1, @NotNull User p2, @Nullable Continuation<? super Boolean> p3) {
                C00221 c00221 = new C00221(this.$bookController, p3);
                c00221.L$0 = p2;
                return c00221.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        User user = (User) this.L$0;
                        if (user.getLast_login_at() >= System.currentTimeMillis() - 259200000) {
                            this.label = 1;
                            if (BookController.saveToWebdav$default(this.$bookController, user.getUsername(), null, this, 2, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Boxing.boxBoolean(false);
            }
        }
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void autoGC() {
        System.gc();
    }

    @Scheduled(cron = "0 4/15 7-23 * * ?")
    public void checkLicense() throws IOException, IllegalArgumentException {
        License license = ExtKt.getInstalledLicense(true);
        if ("default".equals(license.getType())) {
            return;
        }
        MDC.put("traceId", ExtKt.getTraceId());
        BuildersKt__Builders_commonKt.launch$default(this, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new C00541(license, null), 2, null);
    }

    /* compiled from: YueduApi.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "YueduApi.kt", l = {713, 714, 718}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.YueduApi$checkLicense$1")
    /* renamed from: com.htmake.reader.api.YueduApi$checkLicense$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/YueduApi$checkLicense$1.class */
    static final class C00541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ License $license;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00541(License $license, Continuation<? super C00541> $completion) {
            super(2, $completion);
            this.$license = $license;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            C00541 c00541 = new C00541(this.$license, $completion);
            c00541.L$0 = value;
            return c00541;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((C00541) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x00b0  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00f6  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 278
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.YueduApi.C00541.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
