package io.legado.app.model;

import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.data.entities.BookSource;
import io.legado.app.model.webBook.WebBook;
import io.legado.app.utils.HtmlFormatter;
import io.legado.app.utils.StringExtensionsKt;
import io.vertx.core.cli.UsageMessageFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Debugger.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018��2\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006J3\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004H\u0082@ø\u0001��¢\u0006\u0002\u0010\u0015J!\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0004H\u0082@ø\u0001��¢\u0006\u0002\u0010\u0018J!\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0082@ø\u0001��¢\u0006\u0002\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0004H\u0016J\u001a\u0010\u001b\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004J$\u0010\u001b\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001f\u001a\u00020 H\u0016J!\u0010!\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u0004H\u0082@ø\u0001��¢\u0006\u0002\u0010\u0018J!\u0010#\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018J!\u0010$\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0082@ø\u0001��¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n��R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n��\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"Lio/legado/app/model/Debugger;", "Lio/legado/app/model/DebugLog;", "logMsg", "Lkotlin/Function1;", "", "", "(Lkotlin/jvm/functions/Function1;)V", "debugTimeFormat", "Ljava/text/SimpleDateFormat;", "getLogMsg", "()Lkotlin/jvm/functions/Function1;", "startTime", "", "contentDebug", "webBook", "Lio/legado/app/model/webBook/WebBook;", "book", "Lio/legado/app/data/entities/Book;", "bookChapter", "Lio/legado/app/data/entities/BookChapter;", "nextChapterUrl", "(Lio/legado/app/model/webBook/WebBook;Lio/legado/app/data/entities/Book;Lio/legado/app/data/entities/BookChapter;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exploreDebug", "url", "(Lio/legado/app/model/webBook/WebBook;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "infoDebug", "(Lio/legado/app/model/webBook/WebBook;Lio/legado/app/data/entities/Book;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "log", "message", "sourceUrl", "msg", "isHtml", "", "searchDebug", "key", "startDebug", "tocDebug", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/Debugger.class */
public final class Debugger implements DebugLog {

    @NotNull
    private final Function1<String, Unit> logMsg;

    @NotNull
    private final SimpleDateFormat debugTimeFormat;
    private long startTime;

    /* compiled from: Debugger.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Debugger.kt", l = {184}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"this", "webBook"}, m = "contentDebug", c = "io.legado.app.model.Debugger")
    /* renamed from: io.legado.app.model.Debugger$contentDebug$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/Debugger$contentDebug$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
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
            return Debugger.this.contentDebug(null, null, null, null, this);
        }
    }

    /* compiled from: Debugger.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Debugger.kt", l = {95, 102}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"this", "webBook"}, m = "exploreDebug", c = "io.legado.app.model.Debugger")
    /* renamed from: io.legado.app.model.Debugger$exploreDebug$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/Debugger$exploreDebug$1.class */
    static final class C02021 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C02021(Continuation<? super C02021> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return Debugger.this.exploreDebug(null, null, this);
        }
    }

    /* compiled from: Debugger.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Debugger.kt", l = {138, 143}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"this", "webBook"}, m = "infoDebug", c = "io.legado.app.model.Debugger")
    /* renamed from: io.legado.app.model.Debugger$infoDebug$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/Debugger$infoDebug$1.class */
    static final class C02031 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C02031(Continuation<? super C02031> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return Debugger.this.infoDebug(null, null, this);
        }
    }

    /* compiled from: Debugger.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Debugger.kt", l = {117, 124}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"this", "webBook"}, m = "searchDebug", c = "io.legado.app.model.Debugger")
    /* renamed from: io.legado.app.model.Debugger$searchDebug$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/Debugger$searchDebug$1.class */
    static final class C02041 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C02041(Continuation<? super C02041> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return Debugger.this.searchDebug(null, null, this);
        }
    }

    /* compiled from: Debugger.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Debugger.kt", l = {155, 164}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", "webBook", "book"}, m = "tocDebug", c = "io.legado.app.model.Debugger")
    /* renamed from: io.legado.app.model.Debugger$tocDebug$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/Debugger$tocDebug$1.class */
    static final class C02051 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C02051(Continuation<? super C02051> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return Debugger.this.tocDebug(null, null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Debugger(@NotNull Function1<? super String, Unit> logMsg) {
        Intrinsics.checkNotNullParameter(logMsg, "logMsg");
        this.logMsg = logMsg;
        this.debugTimeFormat = new SimpleDateFormat("[mm:ss.SSS]", Locale.getDefault());
        this.startTime = System.currentTimeMillis();
    }

    @NotNull
    public final Function1<String, Unit> getLogMsg() {
        return this.logMsg;
    }

    public final void log(@Nullable String sourceUrl, @Nullable String msg) {
        log(sourceUrl, msg, false);
    }

    @Override // io.legado.app.model.DebugLog, okhttp3.logging.HttpLoggingInterceptor.Logger
    public void log(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        String time = this.debugTimeFormat.format(new Date(System.currentTimeMillis() - this.startTime));
        this.logMsg.invoke(((Object) time) + ' ' + message);
    }

    @Override // io.legado.app.model.DebugLog
    public void log(@Nullable String sourceUrl, @Nullable String msg, boolean isHtml) {
        if (sourceUrl == null || msg == null) {
            return;
        }
        DebuggerKt.logger.info("sourceUrl: {}, msg: {}", sourceUrl, msg);
        String printMsg = msg;
        if (isHtml) {
            printMsg = HtmlFormatter.format$default(HtmlFormatter.INSTANCE, msg, null, 2, null);
        }
        String time = this.debugTimeFormat.format(new Date(System.currentTimeMillis() - this.startTime));
        this.logMsg.invoke(new StringBuilder().append((Object) time).append(' ').append((Object) printMsg).toString());
    }

    @Nullable
    public final Object startDebug(@NotNull WebBook webBook, @NotNull String key, @NotNull Continuation<? super Unit> $completion) throws Throwable {
        BookSource bookSource = webBook.getBookSource();
        webBook.setDebugLogger(this);
        this.startTime = System.currentTimeMillis();
        if (StringExtensionsKt.isAbsUrl(key)) {
            Book book = new Book(null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0L, null, 0L, 0L, 0, 0, null, 0, 0, 0L, null, false, 0, 0, false, null, null, false, null, -1, 1, null);
            book.setOrigin(bookSource.getBookSourceUrl());
            book.setBookUrl(key);
            log(bookSource.getBookSourceUrl(), Intrinsics.stringPlus("⇒开始访问详情页:", key));
            Object objInfoDebug = infoDebug(webBook, book, $completion);
            return objInfoDebug == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInfoDebug : Unit.INSTANCE;
        }
        if (StringsKt.contains$default((CharSequence) key, (CharSequence) "::", false, 2, (Object) null)) {
            String url = StringsKt.substringAfter$default(key, "::", (String) null, 2, (Object) null);
            log(bookSource.getBookSourceUrl(), Intrinsics.stringPlus("⇒开始访问发现页:", url));
            Object objExploreDebug = exploreDebug(webBook, url, $completion);
            return objExploreDebug == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objExploreDebug : Unit.INSTANCE;
        }
        if (StringsKt.startsWith$default(key, "++", false, 2, (Object) null)) {
            if (key == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String url2 = key.substring(2);
            Intrinsics.checkNotNullExpressionValue(url2, "(this as java.lang.String).substring(startIndex)");
            Book book2 = new Book(null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0L, null, 0L, 0L, 0, 0, null, 0, 0, 0L, null, false, 0, 0, false, null, null, false, null, -1, 1, null);
            book2.setOrigin(bookSource.getBookSourceUrl());
            book2.setTocUrl(url2);
            log(bookSource.getBookSourceUrl(), Intrinsics.stringPlus("⇒开始访目录页:", url2));
            Object obj = tocDebug(webBook, book2, $completion);
            return obj == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? obj : Unit.INSTANCE;
        }
        if (!StringsKt.startsWith$default(key, UsageMessageFormatter.DEFAULT_LONG_OPT_PREFIX, false, 2, (Object) null)) {
            log(bookSource.getBookSourceUrl(), Intrinsics.stringPlus("⇒开始搜索关键字:", key));
            Object objSearchDebug = searchDebug(webBook, key, $completion);
            return objSearchDebug == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSearchDebug : Unit.INSTANCE;
        }
        if (key == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String url3 = key.substring(2);
        Intrinsics.checkNotNullExpressionValue(url3, "(this as java.lang.String).substring(startIndex)");
        Book book3 = new Book(null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0L, null, 0L, 0L, 0, 0, null, 0, 0, 0L, null, false, 0, 0, false, null, null, false, null, -1, 1, null);
        book3.setOrigin(bookSource.getBookSourceUrl());
        log(bookSource.getBookSourceUrl(), Intrinsics.stringPlus("⇒开始访正文页:", url3));
        BookChapter chapter = new BookChapter(null, null, false, null, null, 0, null, null, null, null, null, null, null, 8191, null);
        chapter.setTitle("调试");
        chapter.setUrl(url3);
        Object objContentDebug = contentDebug(webBook, book3, chapter, null, $completion);
        return objContentDebug == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objContentDebug : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object exploreDebug(io.legado.app.model.webBook.WebBook r8, java.lang.String r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 542
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.Debugger.exploreDebug(io.legado.app.model.webBook.WebBook, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object searchDebug(io.legado.app.model.webBook.WebBook r8, java.lang.String r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 549
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.Debugger.searchDebug(io.legado.app.model.webBook.WebBook, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object infoDebug(io.legado.app.model.webBook.WebBook r10, io.legado.app.data.entities.Book r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 471
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.Debugger.infoDebug(io.legado.app.model.webBook.WebBook, io.legado.app.data.entities.Book, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object tocDebug(io.legado.app.model.webBook.WebBook r10, io.legado.app.data.entities.Book r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 603
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.Debugger.tocDebug(io.legado.app.model.webBook.WebBook, io.legado.app.data.entities.Book, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object contentDebug(io.legado.app.model.webBook.WebBook r9, io.legado.app.data.entities.Book r10, io.legado.app.data.entities.BookChapter r11, java.lang.String r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 385
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.Debugger.contentDebug(io.legado.app.model.webBook.WebBook, io.legado.app.data.entities.Book, io.legado.app.data.entities.BookChapter, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
