package com.htmake.reader.api.controller;

import ch.qos.logback.core.CoreConstants;
import com.htmake.reader.entity.User;
import com.htmake.reader.utils.ExtKt;
import io.legado.app.data.entities.RssSource;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mozilla.classfile.ByteCode;

/* compiled from: RssSourceController.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��6\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\tJ\u0019\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\tJ\u0019\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\tJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011J\u0019\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\tJ\u0019\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\tJ\u0019\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lcom/htmake/reader/api/controller/RssSourceController;", "Lcom/htmake/reader/api/controller/BaseController;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "canEditRssSource", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRssSource", "Lcom/htmake/reader/api/ReturnData;", "getRssArticles", "getRssContent", "getRssSourceByURL", "Lio/legado/app/data/entities/RssSource;", "url", "", "userNameSpace", "getRssSources", "saveRssSource", "saveRssSources", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/RssSourceController.class */
public final class RssSourceController extends BaseController {

    /* compiled from: RssSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "RssSourceController.kt", l = {198, ByteCode.JSR_W}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "deleteRssSource", c = "com.htmake.reader.api.controller.RssSourceController")
    /* renamed from: com.htmake.reader.api.controller.RssSourceController$deleteRssSource$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/RssSourceController$deleteRssSource$1.class */
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
            return RssSourceController.this.deleteRssSource(null, this);
        }
    }

    /* compiled from: RssSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "RssSourceController.kt", l = {249, 282}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getRssArticles", c = "com.htmake.reader.api.controller.RssSourceController")
    /* renamed from: com.htmake.reader.api.controller.RssSourceController$getRssArticles$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/RssSourceController$getRssArticles$1.class */
    static final class C01751 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01751(Continuation<? super C01751> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return RssSourceController.this.getRssArticles(null, this);
        }
    }

    /* compiled from: RssSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "RssSourceController.kt", l = {289, 324}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getRssContent", c = "com.htmake.reader.api.controller.RssSourceController")
    /* renamed from: com.htmake.reader.api.controller.RssSourceController$getRssContent$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/RssSourceController$getRssContent$1.class */
    static final class C01761 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01761(Continuation<? super C01761> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return RssSourceController.this.getRssContent(null, this);
        }
    }

    /* compiled from: RssSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "RssSourceController.kt", l = {77}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getRssSources", c = "com.htmake.reader.api.controller.RssSourceController")
    /* renamed from: com.htmake.reader.api.controller.RssSourceController$getRssSources$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/RssSourceController$getRssSources$1.class */
    static final class C01771 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01771(Continuation<? super C01771> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return RssSourceController.this.getRssSources(null, this);
        }
    }

    /* compiled from: RssSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "RssSourceController.kt", l = {101, 104}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "saveRssSource", c = "com.htmake.reader.api.controller.RssSourceController")
    /* renamed from: com.htmake.reader.api.controller.RssSourceController$saveRssSource$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/RssSourceController$saveRssSource$1.class */
    static final class C01781 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01781(Continuation<? super C01781> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return RssSourceController.this.saveRssSource(null, this);
        }
    }

    /* compiled from: RssSourceController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "RssSourceController.kt", l = {148, 151}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "saveRssSources", c = "com.htmake.reader.api.controller.RssSourceController")
    /* renamed from: com.htmake.reader.api.controller.RssSourceController$saveRssSources$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/RssSourceController$saveRssSources$1.class */
    static final class C01791 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01791(Continuation<? super C01791> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return RssSourceController.this.saveRssSources(null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RssSourceController(@NotNull CoroutineContext coroutineContext) {
        super(coroutineContext);
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getRssSources(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.RssSourceController.getRssSources(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object canEditRssSource(@NotNull RoutingContext context, @NotNull Continuation<? super Boolean> $completion) {
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
    public final java.lang.Object saveRssSource(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 670
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.RssSourceController.saveRssSource(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
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
    public final java.lang.Object saveRssSources(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 711
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.RssSourceController.saveRssSources(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
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
    public final java.lang.Object deleteRssSource(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 555
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.RssSourceController.deleteRssSource(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final RssSource getRssSourceByURL(@NotNull String url, @NotNull String userNameSpace) {
        JsonArray list;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
        if ((url.length() == 0) || (list = ExtKt.asJsonArray(getUserStorage(userNameSpace, "rssSources"))) == null) {
            return null;
        }
        int i = 0;
        int size = list.size();
        if (0 < size) {
            do {
                int i2 = i;
                i++;
                RssSource.Companion companion = RssSource.Companion;
                String string = list.getJsonObject(i2).toString();
                Intrinsics.checkNotNullExpressionValue(string, "list.getJsonObject(i).toString()");
                Object objM1102fromJsonIoAF18A = companion.m1102fromJsonIoAF18A(string);
                RssSource _rssSource = (RssSource) (Result.m2101isFailureimpl(objM1102fromJsonIoAF18A) ? null : objM1102fromJsonIoAF18A);
                if (_rssSource != null && _rssSource.getSourceUrl().equals(url)) {
                    return _rssSource;
                }
            } while (i < size);
            return null;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getRssArticles(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 752
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.RssSourceController.getRssArticles(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getRssContent(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 735
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.RssSourceController.getRssContent(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
