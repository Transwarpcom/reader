package com.htmake.reader.api.controller;

import ch.qos.logback.classic.ClassicConstants;
import ch.qos.logback.core.CoreConstants;
import com.htmake.reader.api.ReturnData;
import com.htmake.reader.entity.User;
import com.htmake.reader.utils.ExtKt;
import com.htmake.reader.utils.SpringContextUtils;
import com.htmake.reader.utils.VertExtKt;
import io.vertx.core.AsyncResult;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BookSourceController.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u000f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u0010\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u0011\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ&\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\u0006\u0010\u0016\u001a\u00020\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0019\u0010\u0019\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\u0006\u0010\u0016\u001a\u00020\u0014J\u0019\u0010\u001b\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0016\u001a\u00020\u0014J4\u0010\u001d\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0016\u001a\u00020\u00142\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001f2\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001fJ\u0019\u0010!\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ\u0019\u0010\"\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ\u0019\u0010#\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ\u0016\u0010#\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010$\u001a\u00020\u0018J\u0019\u0010%\u001a\u00020&2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ \u0010'\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00142\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010$\u001a\u00020\u0018J\u0019\u0010*\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ#\u0010+\u001a\u00020&2\u0006\u0010\u0016\u001a\u00020\u00142\b\u0010,\u001a\u0004\u0018\u00010)H\u0086@ø\u0001��¢\u0006\u0002\u0010-R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n��\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, d2 = {"Lcom/htmake/reader/api/controller/BookSourceController;", "Lcom/htmake/reader/api/controller/BaseController;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "webClient", "Lio/vertx/ext/web/client/WebClient;", "canEditBookSource", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllBookSources", "Lcom/htmake/reader/api/ReturnData;", "deleteBookSource", "deleteBookSources", "deleteBookSourcesFile", "deleteUserBookSource", "generateBookSourceMap", "", "", "", "userNameSpace", "bookSourceList", "Lio/vertx/core/json/JsonArray;", "getBookSource", "getBookSourceMap", "getBookSources", "getUserBookSourceJson", "getUserBookSourceJsonOpt", "fields", "", "checkNotEmpty", "readSourceFile", "saveBookSource", "saveBookSources", "bookSourceJsonArray", "saveFromRemoteSource", "", "saveUserBookSources", "userInfo", "Lcom/htmake/reader/entity/User;", "setAsDefaultBookSources", "updateRemoteSourceSub", ClassicConstants.USER_MDC_KEY, "(Ljava/lang/String;Lcom/htmake/reader/entity/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController.class */
public final class BookSourceController extends BaseController {

    @NotNull
    private WebClient webClient;

    /* compiled from: BookSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookSourceController.kt", l = {312, 315}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "deleteAllBookSources", c = "com.htmake.reader.api.controller.BookSourceController")
    /* renamed from: com.htmake.reader.api.controller.BookSourceController$deleteAllBookSources$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController$deleteAllBookSources$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
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
            return BookSourceController.this.deleteAllBookSources(null, this);
        }
    }

    /* compiled from: BookSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookSourceController.kt", l = {240, 243}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "deleteBookSource", c = "com.htmake.reader.api.controller.BookSourceController")
    /* renamed from: com.htmake.reader.api.controller.BookSourceController$deleteBookSource$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController$deleteBookSource$1.class */
    static final class C01431 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01431(Continuation<? super C01431> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookSourceController.this.deleteBookSource(null, this);
        }
    }

    /* compiled from: BookSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookSourceController.kt", l = {271, 274}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "deleteBookSources", c = "com.htmake.reader.api.controller.BookSourceController")
    /* renamed from: com.htmake.reader.api.controller.BookSourceController$deleteBookSources$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController$deleteBookSources$1.class */
    static final class C01441 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01441(Continuation<? super C01441> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookSourceController.this.deleteBookSources(null, this);
        }
    }

    /* compiled from: BookSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookSourceController.kt", l = {440}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "deleteBookSourcesFile", c = "com.htmake.reader.api.controller.BookSourceController")
    /* renamed from: com.htmake.reader.api.controller.BookSourceController$deleteBookSourcesFile$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController$deleteBookSourcesFile$1.class */
    static final class C01451 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01451(Continuation<? super C01451> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookSourceController.this.deleteBookSourcesFile(null, this);
        }
    }

    /* compiled from: BookSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookSourceController.kt", l = {420}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "deleteUserBookSource", c = "com.htmake.reader.api.controller.BookSourceController")
    /* renamed from: com.htmake.reader.api.controller.BookSourceController$deleteUserBookSource$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController$deleteUserBookSource$1.class */
    static final class C01461 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01461(Continuation<? super C01461> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookSourceController.this.deleteUserBookSource(null, this);
        }
    }

    /* compiled from: BookSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookSourceController.kt", l = {183}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getBookSource", c = "com.htmake.reader.api.controller.BookSourceController")
    /* renamed from: com.htmake.reader.api.controller.BookSourceController$getBookSource$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController$getBookSource$1.class */
    static final class C01471 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01471(Continuation<? super C01471> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookSourceController.this.getBookSource(null, this);
        }
    }

    /* compiled from: BookSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookSourceController.kt", l = {219}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getBookSources", c = "com.htmake.reader.api.controller.BookSourceController")
    /* renamed from: com.htmake.reader.api.controller.BookSourceController$getBookSources$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController$getBookSources$1.class */
    static final class C01481 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01481(Continuation<? super C01481> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookSourceController.this.getBookSources(null, this);
        }
    }

    /* compiled from: BookSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookSourceController.kt", l = {65, 68}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "saveBookSource", c = "com.htmake.reader.api.controller.BookSourceController")
    /* renamed from: com.htmake.reader.api.controller.BookSourceController$saveBookSource$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController$saveBookSource$1.class */
    static final class C01491 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01491(Continuation<? super C01491> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookSourceController.this.saveBookSource(null, this);
        }
    }

    /* compiled from: BookSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookSourceController.kt", l = {108, 111}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "saveBookSources", c = "com.htmake.reader.api.controller.BookSourceController")
    /* renamed from: com.htmake.reader.api.controller.BookSourceController$saveBookSources$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController$saveBookSources$1.class */
    static final class C01501 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01501(Continuation<? super C01501> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookSourceController.this.saveBookSources((RoutingContext) null, this);
        }
    }

    /* compiled from: BookSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookSourceController.kt", l = {363}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "saveFromRemoteSource", c = "com.htmake.reader.api.controller.BookSourceController")
    /* renamed from: com.htmake.reader.api.controller.BookSourceController$saveFromRemoteSource$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController$saveFromRemoteSource$1.class */
    static final class C01511 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01511(Continuation<? super C01511> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookSourceController.this.saveFromRemoteSource(null, this);
        }
    }

    /* compiled from: BookSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookSourceController.kt", l = {326}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "setAsDefaultBookSources", c = "com.htmake.reader.api.controller.BookSourceController")
    /* renamed from: com.htmake.reader.api.controller.BookSourceController$setAsDefaultBookSources$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController$setAsDefaultBookSources$1.class */
    static final class C01521 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01521(Continuation<? super C01521> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookSourceController.this.setAsDefaultBookSources(null, this);
        }
    }

    /* compiled from: BookSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookSourceController.kt", l = {OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL}, i = {}, s = {}, n = {}, m = "updateRemoteSourceSub", c = "com.htmake.reader.api.controller.BookSourceController")
    /* renamed from: com.htmake.reader.api.controller.BookSourceController$updateRemoteSourceSub$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController$updateRemoteSourceSub$1.class */
    static final class C01531 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int I$0;
        int I$1;
        /* synthetic */ Object result;
        int label;

        C01531(Continuation<? super C01531> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookSourceController.this.updateRemoteSourceSub(null, null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BookSourceController(@NotNull CoroutineContext coroutineContext) {
        super(coroutineContext);
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        Object bean = SpringContextUtils.getBean("webClient", WebClient.class);
        Intrinsics.checkNotNullExpressionValue(bean, "getBean(\"webClient\", WebClient::class.java)");
        this.webClient = (WebClient) bean;
    }

    public static /* synthetic */ JsonArray getUserBookSourceJsonOpt$default(BookSourceController bookSourceController, String str, Set set, Set set2, int i, Object obj) {
        if ((i & 2) != 0) {
            set = null;
        }
        if ((i & 4) != 0) {
            set2 = null;
        }
        return bookSourceController.getUserBookSourceJsonOpt(str, set, set2);
    }

    @Nullable
    public final JsonArray getUserBookSourceJsonOpt(@NotNull String userNameSpace, @Nullable Set<String> fields, @Nullable Set<String> checkNotEmpty) {
        Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
        File bookSourceFile = ExtKt.getStorageFile$default(new String[]{"data", userNameSpace, "bookSource"}, null, 2, null);
        if (!bookSourceFile.exists()) {
            bookSourceFile = ExtKt.getStorageFile$default(new String[]{"data", "default", "bookSource"}, null, 2, null);
        }
        JsonArray bookSourceList = ExtKt.parseJsonStringList$default(bookSourceFile, fields, null, 0, 0, checkNotEmpty, null, 92, null);
        return bookSourceList;
    }

    @Nullable
    public final JsonArray getUserBookSourceJson(@NotNull String userNameSpace) {
        JsonArray systemBookSourceList;
        Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
        JsonArray bookSourceList = ExtKt.asJsonArray(getUserStorage(userNameSpace, "bookSource"));
        if (bookSourceList == null && !userNameSpace.equals("default") && (systemBookSourceList = ExtKt.asJsonArray(getUserStorage("default", "bookSource"))) != null) {
            bookSourceList = systemBookSourceList;
        }
        return bookSourceList;
    }

    @Nullable
    public final Object canEditBookSource(@NotNull RoutingContext context, @NotNull Continuation<? super Boolean> $completion) {
        if (!getAppConfig().getSecure()) {
            return Boxing.boxBoolean(true);
        }
        User userInfo = (User) context.get("userInfo");
        if (userInfo == null) {
            return Boxing.boxBoolean(false);
        }
        return Boxing.boxBoolean(userInfo.getEnable_book_source());
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveBookSource(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 620
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookSourceController.saveBookSource(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveBookSources(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookSourceController.saveBookSources(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final ReturnData saveBookSources(@NotNull RoutingContext context, @NotNull JsonArray bookSourceJsonArray) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bookSourceJsonArray, "bookSourceJsonArray");
        return saveUserBookSources(getUserNameSpace(context), (User) context.get("userInfo"), bookSourceJsonArray);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0188 A[EDGE_INSN: B:45:0x0188->B:35:0x0188 BREAK  A[LOOP:1: B:11:0x00ac->B:47:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[LOOP:1: B:11:0x00ac->B:47:?, LOOP_END, SYNTHETIC] */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.htmake.reader.api.ReturnData saveUserBookSources(@org.jetbrains.annotations.NotNull java.lang.String r6, @org.jetbrains.annotations.Nullable com.htmake.reader.entity.User r7, @org.jetbrains.annotations.NotNull io.vertx.core.json.JsonArray r8) {
        /*
            Method dump skipped, instructions count: 484
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookSourceController.saveUserBookSources(java.lang.String, com.htmake.reader.entity.User, io.vertx.core.json.JsonArray):com.htmake.reader.api.ReturnData");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getBookSource(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 509
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookSourceController.getBookSource(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getBookSources(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 568
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookSourceController.getBookSources(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object deleteBookSource(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 467
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookSourceController.deleteBookSource(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object deleteBookSources(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 533
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookSourceController.deleteBookSources(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object deleteAllBookSources(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 351
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookSourceController.deleteAllBookSources(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object setAsDefaultBookSources(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookSourceController.setAsDefaultBookSources(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object readSourceFile(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
        ReturnData returnData = new ReturnData();
        if (context.fileUploads() == null || context.fileUploads().isEmpty()) {
            return returnData.setErrorMsg("请上传文件");
        }
        JsonArray jsonArray = new JsonArray();
        Iterable iterableFileUploads = context.fileUploads();
        Intrinsics.checkNotNullExpressionValue(iterableFileUploads, "context.fileUploads()");
        Iterable $this$forEach$iv = iterableFileUploads;
        for (Object element$iv : $this$forEach$iv) {
            FileUpload it = (FileUpload) element$iv;
            File file = new File(it.uploadedFileName());
            if (file.exists()) {
                jsonArray.add(FilesKt.readText$default(file, null, 1, null));
                file.delete();
            }
        }
        List list = jsonArray.getList();
        Intrinsics.checkNotNullExpressionValue(list, "sourceList.getList()");
        return ReturnData.setData$default(returnData, list, null, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveFromRemoteSource(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 429
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookSourceController.saveFromRemoteSource(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: BookSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "BookSourceController.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BookSourceController$saveFromRemoteSource$2")
    /* renamed from: com.htmake.reader.api.controller.BookSourceController$saveFromRemoteSource$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookSourceController$saveFromRemoteSource$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ Ref.ObjectRef<String> $url;
        final /* synthetic */ RoutingContext $context;
        final /* synthetic */ ReturnData $returnData;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Ref.ObjectRef<String> $url, RoutingContext $context, ReturnData $returnData, Continuation<? super AnonymousClass2> $completion) {
            super(2, $completion);
            this.$url = $url;
            this.$context = $context;
            this.$returnData = $returnData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return BookSourceController.this.new AnonymousClass2(this.$url, this.$context, this.$returnData, $completion);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass2) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    HttpRequest<Buffer> httpRequestTimeout = BookSourceController.this.webClient.getAbs(this.$url.element).timeout(3000L);
                    RoutingContext routingContext = this.$context;
                    BookSourceController bookSourceController = BookSourceController.this;
                    ReturnData returnData = this.$returnData;
                    httpRequestTimeout.send((v3) -> {
                        m844invokeSuspend$lambda0(r1, r2, r3, v3);
                    });
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* renamed from: invokeSuspend$lambda-0, reason: not valid java name */
        private static final void m844invokeSuspend$lambda0(RoutingContext $context, BookSourceController this$0, ReturnData $returnData, AsyncResult it) {
            HttpResponse httpResponse = (HttpResponse) it.result();
            JsonArray body = httpResponse == null ? null : httpResponse.bodyAsJsonArray();
            if (body != null) {
                VertExtKt.success($context, this$0.saveBookSources($context, body));
            } else {
                VertExtKt.success($context, $returnData.setErrorMsg("远程书源链接错误"));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x00a3, code lost:
    
        if (0 < r16) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00a6, code lost:
    
        r0 = r15;
        r15 = r15 + 1;
        r0 = new kotlin.jvm.internal.Ref.ObjectRef();
        r0.element = ((io.vertx.core.json.JsonArray) r14.element).getJsonObject(r0);
        r0 = new kotlin.jvm.internal.Ref.ObjectRef();
        r0.element = ((io.vertx.core.json.JsonObject) r0.element).getString("link");
        r0 = (java.lang.CharSequence) r0.element;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00f6, code lost:
    
        if (r0 == null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0100, code lost:
    
        if (r0.length() != 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0103, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0107, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0108, code lost:
    
        if (r0 == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x010e, code lost:
    
        r4 = r11;
        r5 = r12;
        r6 = r14;
        r24.L$0 = r10;
        r24.L$1 = r11;
        r24.L$2 = r12;
        r24.L$3 = r14;
        r24.I$0 = r15;
        r24.I$1 = r16;
        r24.label = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0158, code lost:
    
        if (io.vertx.kotlin.coroutines.VertxCoroutineKt.awaitResult(new com.htmake.reader.api.controller.BookSourceController.C01542(r10), r24) != r0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x015d, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x019d, code lost:
    
        if (r15 >= r16) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x01a0, code lost:
    
        generateBookSourceMap$default(r10, r11, null, 2, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x01ac, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Type inference failed for: r1v13, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v21, types: [T, io.vertx.core.json.JsonArray] */
    /* JADX WARN: Type inference failed for: r1v8, types: [T, io.vertx.core.json.JsonObject] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x019d -> B:16:0x00a6). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object updateRemoteSourceSub(@org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.Nullable com.htmake.reader.entity.User r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 440
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookSourceController.updateRemoteSourceSub(java.lang.String, com.htmake.reader.entity.User, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object deleteUserBookSource(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 365
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookSourceController.deleteUserBookSource(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object deleteBookSourcesFile(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookSourceController.deleteBookSourcesFile(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Map generateBookSourceMap$default(BookSourceController bookSourceController, String str, JsonArray jsonArray, int i, Object obj) {
        if ((i & 2) != 0) {
            jsonArray = null;
        }
        return bookSourceController.generateBookSourceMap(str, jsonArray);
    }

    @NotNull
    public final Map<String, Integer> generateBookSourceMap(@NotNull String userNameSpace, @Nullable JsonArray bookSourceList) {
        Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
        JsonArray bookSourceJsonArray = bookSourceList != null ? bookSourceList : getUserBookSourceJson(userNameSpace);
        if (bookSourceJsonArray == null) {
            bookSourceJsonArray = new JsonArray();
        }
        Map urlMap = new LinkedHashMap();
        List exploreList = new ArrayList();
        int i = 0;
        int size = bookSourceJsonArray.size();
        if (0 < size) {
            do {
                int i2 = i;
                i++;
                String string = bookSourceJsonArray.getJsonObject(i2).getString("bookSourceUrl");
                Intrinsics.checkNotNullExpressionValue(string, "bookSourceJsonArray.getJsonObject(i).getString(\"bookSourceUrl\")");
                urlMap.put(string, Integer.valueOf(i2));
                String string2 = bookSourceJsonArray.getJsonObject(i2).getString("exploreUrl");
                if (!(string2 == null || string2.length() == 0)) {
                    exploreList.add(MapsKt.mutableMapOf(TuplesKt.to("bookSourceUrl", bookSourceJsonArray.getJsonObject(i2).getString("bookSourceUrl")), TuplesKt.to("bookSourceGroup", bookSourceJsonArray.getJsonObject(i2).getString("bookSourceGroup")), TuplesKt.to("bookSourceName", bookSourceJsonArray.getJsonObject(i2).getString("bookSourceName"))));
                }
            } while (i < size);
        }
        saveUserStorage(userNameSpace, "bookSourceMap", urlMap);
        saveUserStorage(userNameSpace, "bookSourceExploreList", exploreList);
        return urlMap;
    }

    @NotNull
    public final Map<String, Integer> getBookSourceMap(@NotNull String userNameSpace) {
        String userStorage;
        Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
        if (ExtKt.getStorageFile$default(new String[]{"data", userNameSpace, "bookSource"}, null, 2, null).exists()) {
            userStorage = getUserStorage(userNameSpace, "bookSourceMap");
        } else {
            userStorage = getUserStorage("default", "bookSourceMap");
        }
        String content = userStorage;
        String str = content;
        if (str == null || str.length() == 0) {
            if (ExtKt.getStorageFile$default(new String[]{"data", userNameSpace, "bookSource"}, null, 2, null).exists()) {
                return generateBookSourceMap$default(this, userNameSpace, null, 2, null);
            }
            return generateBookSourceMap$default(this, "default", null, 2, null);
        }
        JsonObject jsonObjectAsJsonObject = ExtKt.asJsonObject(content);
        Map<String, Object> map = jsonObjectAsJsonObject == null ? null : jsonObjectAsJsonObject.getMap();
        if (map == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Int>");
        }
        return TypeIntrinsics.asMutableMap(map);
    }
}
