package io.legado.app.model.rss;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Rss.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JO\u0010\u0003\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010J3\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lio/legado/app/model/rss/Rss;", "", "()V", "getArticles", "Lkotlin/Pair;", "", "Lio/legado/app/data/entities/RssArticle;", "", "sortName", "sortUrl", "rssSource", "Lio/legado/app/data/entities/RssSource;", "page", "", "debugLog", "Lio/legado/app/model/DebugLog;", "(Ljava/lang/String;Ljava/lang/String;Lio/legado/app/data/entities/RssSource;ILio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getContent", "rssArticle", "ruleContent", "(Lio/legado/app/data/entities/RssArticle;Ljava/lang/String;Lio/legado/app/data/entities/RssSource;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/rss/Rss.class */
public final class Rss {

    @NotNull
    public static final Rss INSTANCE = new Rss();

    /* compiled from: Rss.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Rss.kt", l = {32}, i = {0, 0, 0, 0, 0}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"}, n = {"sortName", "sortUrl", "rssSource", "debugLog", "ruleData"}, m = "getArticles", c = "io.legado.app.model.rss.Rss")
    /* renamed from: io.legado.app.model.rss.Rss$getArticles$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/rss/Rss$getArticles$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
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
            return Rss.this.getArticles(null, null, null, 0, null, this);
        }
    }

    /* compiled from: Rss.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Rss.kt", l = {52}, i = {0, 0, 0, 0}, s = {"L$0", "L$1", "L$2", "L$3"}, n = {"rssArticle", "ruleContent", "rssSource", "debugLog"}, m = "getContent", c = "io.legado.app.model.rss.Rss")
    /* renamed from: io.legado.app.model.rss.Rss$getContent$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/rss/Rss$getContent$1.class */
    static final class C02151 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        /* synthetic */ Object result;
        int label;

        C02151(Continuation<? super C02151> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return Rss.this.getContent(null, null, null, null, this);
        }
    }

    private Rss() {
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getArticles(@org.jetbrains.annotations.NotNull java.lang.String r17, @org.jetbrains.annotations.NotNull java.lang.String r18, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.RssSource r19, int r20, @org.jetbrains.annotations.Nullable io.legado.app.model.DebugLog r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<io.legado.app.data.entities.RssArticle>, java.lang.String>> r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.rss.Rss.getArticles(java.lang.String, java.lang.String, io.legado.app.data.entities.RssSource, int, io.legado.app.model.DebugLog, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getContent(@org.jetbrains.annotations.NotNull io.legado.app.data.entities.RssArticle r17, @org.jetbrains.annotations.NotNull java.lang.String r18, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.RssSource r19, @org.jetbrains.annotations.Nullable io.legado.app.model.DebugLog r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.rss.Rss.getContent(io.legado.app.data.entities.RssArticle, java.lang.String, io.legado.app.data.entities.RssSource, io.legado.app.model.DebugLog, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
