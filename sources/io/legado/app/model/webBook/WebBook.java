package io.legado.app.model.webBook;

import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.data.entities.BookSource;
import io.legado.app.model.Debug;
import io.legado.app.model.DebugLog;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: WebBook.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018��2\u00020\u0001B1\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tB/\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ+\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\"\u001a\u00020\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0086@ø\u0001��¢\u0006\u0002\u0010%J-\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010,J#\u0010-\u001a\u00020(2\u0006\u0010'\u001a\u00020(2\b\b\u0002\u0010.\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010/J#\u0010-\u001a\u00020(2\u0006\u00100\u001a\u00020\u00032\b\b\u0002\u0010.\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u00101J\u001f\u00102\u001a\b\u0012\u0004\u0012\u00020*0 2\u0006\u0010'\u001a\u00020(H\u0086@ø\u0001��¢\u0006\u0002\u00103J2\u00104\u001a\b\u0012\u0004\u0012\u00020(052\u0006\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u0003H\u0086@ø\u0001��ø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b8\u00109J+\u0010:\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010;\u001a\u00020\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0086@ø\u0001��¢\u0006\u0002\u0010%R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\u0017\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001e\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006<"}, d2 = {"Lio/legado/app/model/webBook/WebBook;", "", "bookSourceString", "", "debugLog", "", "debugLogger", "Lio/legado/app/model/DebugLog;", "userNameSpace", "(Ljava/lang/String;ZLio/legado/app/model/DebugLog;Ljava/lang/String;)V", "bookSource", "Lio/legado/app/data/entities/BookSource;", "(Lio/legado/app/data/entities/BookSource;ZLio/legado/app/model/DebugLog;Ljava/lang/String;)V", "getBookSource", "()Lio/legado/app/data/entities/BookSource;", "getDebugLog", "()Z", "getDebugLogger", "()Lio/legado/app/model/DebugLog;", "setDebugLogger", "(Lio/legado/app/model/DebugLog;)V", "debugger", "getDebugger", "sourceUrl", "getSourceUrl", "()Ljava/lang/String;", "userNS", "getUserNS", "getUserNameSpace", "setUserNameSpace", "(Ljava/lang/String;)V", "exploreBook", "", "Lio/legado/app/data/entities/SearchBook;", "url", "page", "", "(Ljava/lang/String;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBookContent", "book", "Lio/legado/app/data/entities/Book;", "bookChapter", "Lio/legado/app/data/entities/BookChapter;", "nextChapterUrl", "(Lio/legado/app/data/entities/Book;Lio/legado/app/data/entities/BookChapter;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBookInfo", "canReName", "(Lio/legado/app/data/entities/Book;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bookUrl", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getChapterList", "(Lio/legado/app/data/entities/Book;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "preciseSearch", "Lkotlin/Result;", "name", "author", "preciseSearch-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchBook", "key", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/WebBook.class */
public final class WebBook {

    @NotNull
    private final BookSource bookSource;
    private final boolean debugLog;

    @Nullable
    private DebugLog debugLogger;

    @Nullable
    private String userNameSpace;

    /* compiled from: WebBook.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "WebBook.kt", l = {111, 118}, i = {0, 0, 0, 0}, s = {"L$0", "L$1", "L$2", "L$3"}, n = {"this", "variableBook", "analyzeUrl", "res"}, m = "exploreBook", c = "io.legado.app.model.webBook.WebBook")
    /* renamed from: io.legado.app.model.webBook.WebBook$exploreBook$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/WebBook$exploreBook$1.class */
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
            return WebBook.this.exploreBook(null, null, this);
        }
    }

    /* compiled from: WebBook.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "WebBook.kt", l = {255, 259}, i = {}, s = {}, n = {}, m = "getBookContent", c = "io.legado.app.model.webBook.WebBook")
    /* renamed from: io.legado.app.model.webBook.WebBook$getBookContent$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/WebBook$getBookContent$1.class */
    static final class C02171 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        /* synthetic */ Object result;
        int label;

        C02171(Continuation<? super C02171> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return WebBook.this.getBookContent(null, null, null, this);
        }
    }

    /* compiled from: WebBook.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "WebBook.kt", l = {138, 156, 164}, i = {1, 1}, s = {"L$2", "L$3"}, n = {"analyzeUrl", "res"}, m = "getBookInfo", c = "io.legado.app.model.webBook.WebBook")
    /* renamed from: io.legado.app.model.webBook.WebBook$getBookInfo$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/WebBook$getBookInfo$1.class */
    static final class C02181 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        /* synthetic */ Object result;
        int label;

        C02181(Continuation<? super C02181> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return WebBook.this.getBookInfo((Book) null, false, (Continuation<? super Book>) this);
        }
    }

    /* compiled from: WebBook.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "WebBook.kt", l = {195, 211, 218}, i = {1, 1}, s = {"L$2", "L$3"}, n = {"analyzeUrl", "res"}, m = "getChapterList", c = "io.legado.app.model.webBook.WebBook")
    /* renamed from: io.legado.app.model.webBook.WebBook$getChapterList$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/WebBook$getChapterList$1.class */
    static final class C02191 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        /* synthetic */ Object result;
        int label;

        C02191(Continuation<? super C02191> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return WebBook.this.getChapterList(null, this);
        }
    }

    /* compiled from: WebBook.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "WebBook.kt", l = {67, 74}, i = {0, 0}, s = {"L$2", "L$3"}, n = {"analyzeUrl", "res"}, m = "searchBook", c = "io.legado.app.model.webBook.WebBook")
    /* renamed from: io.legado.app.model.webBook.WebBook$searchBook$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/WebBook$searchBook$1.class */
    static final class C02201 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        /* synthetic */ Object result;
        int label;

        C02201(Continuation<? super C02201> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return WebBook.this.searchBook(null, null, this);
        }
    }

    public WebBook(@NotNull BookSource bookSource, boolean debugLog, @Nullable DebugLog debugLogger, @Nullable String userNameSpace) {
        Intrinsics.checkNotNullParameter(bookSource, "bookSource");
        this.bookSource = bookSource;
        this.debugLog = debugLog;
        this.debugLogger = debugLogger;
        this.userNameSpace = userNameSpace;
    }

    public /* synthetic */ WebBook(BookSource bookSource, boolean z, DebugLog debugLog, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bookSource, (i & 2) != 0 ? true : z, (i & 4) != 0 ? null : debugLog, (i & 8) != 0 ? null : str);
    }

    @NotNull
    public final BookSource getBookSource() {
        return this.bookSource;
    }

    public final boolean getDebugLog() {
        return this.debugLog;
    }

    @Nullable
    public final DebugLog getDebugLogger() {
        return this.debugLogger;
    }

    public final void setDebugLogger(@Nullable DebugLog debugLog) {
        this.debugLogger = debugLog;
    }

    @Nullable
    public final String getUserNameSpace() {
        return this.userNameSpace;
    }

    public final void setUserNameSpace(@Nullable String str) {
        this.userNameSpace = str;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public WebBook(@NotNull String bookSourceString, boolean debugLog, @Nullable DebugLog debugLogger, @Nullable String userNameSpace) {
        Intrinsics.checkNotNullParameter(bookSourceString, "bookSourceString");
        Object objM1092fromJsonIoAF18A = BookSource.Companion.m1092fromJsonIoAF18A(bookSourceString);
        BookSource bookSource = (BookSource) (Result.m2101isFailureimpl(objM1092fromJsonIoAF18A) ? null : objM1092fromJsonIoAF18A);
        this(bookSource == null ? new BookSource(null, null, null, 0, null, 0, false, false, null, null, null, null, null, null, null, null, 0L, 0L, 0, null, null, null, null, null, null, null, 67108863, null) : bookSource, debugLog, debugLogger, userNameSpace);
    }

    public /* synthetic */ WebBook(String str, boolean z, DebugLog debugLog, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? true : z, (i & 4) != 0 ? null : debugLog, (i & 8) != 0 ? null : str2);
    }

    @NotNull
    public final String getSourceUrl() {
        return this.bookSource.getBookSourceUrl();
    }

    @Nullable
    public final DebugLog getDebugger() {
        if (this.debugLogger != null) {
            return this.debugLogger;
        }
        if (this.debugLog) {
            return Debug.INSTANCE;
        }
        return null;
    }

    @NotNull
    public final String getUserNS() {
        String str = this.userNameSpace;
        return str == null ? "unknow" : str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0280 A[LOOP:0: B:38:0x0276->B:40:0x0280, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Type inference failed for: r1v19, types: [T, io.legado.app.help.http.StrResponse] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object searchBook(@org.jetbrains.annotations.NotNull java.lang.String r22, @org.jetbrains.annotations.Nullable java.lang.Integer r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<io.legado.app.data.entities.SearchBook>> r24) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 741
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.webBook.WebBook.searchBook(java.lang.String, java.lang.Integer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object searchBook$default(WebBook webBook, String str, Integer num, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            num = 1;
        }
        return webBook.searchBook(str, num, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Type inference failed for: r1v13, types: [T, io.legado.app.help.http.StrResponse] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object exploreBook(@org.jetbrains.annotations.NotNull java.lang.String r22, @org.jetbrains.annotations.Nullable java.lang.Integer r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<io.legado.app.data.entities.SearchBook>> r24) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 557
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.webBook.WebBook.exploreBook(java.lang.String, java.lang.Integer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object exploreBook$default(WebBook webBook, String str, Integer num, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            num = 1;
        }
        return webBook.exploreBook(str, num, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Type inference failed for: r1v11, types: [T, io.legado.app.help.http.StrResponse] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getBookInfo(@org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r17, boolean r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.legado.app.data.entities.Book> r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 696
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.webBook.WebBook.getBookInfo(io.legado.app.data.entities.Book, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object getBookInfo$default(WebBook webBook, Book book, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return webBook.getBookInfo(book, z, (Continuation<? super Book>) continuation);
    }

    public static /* synthetic */ Object getBookInfo$default(WebBook webBook, String str, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return webBook.getBookInfo(str, z, (Continuation<? super Book>) continuation);
    }

    @Nullable
    public final Object getBookInfo(@NotNull String bookUrl, boolean canReName, @NotNull Continuation<? super Book> $completion) {
        Book book = new Book(null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0L, null, 0L, 0L, 0, 0, null, 0, 0, 0L, null, false, 0, 0, false, null, null, false, null, -1, 1, null);
        book.setBookUrl(bookUrl);
        book.setOrigin(getBookSource().getBookSourceUrl());
        book.setOriginName(getBookSource().getBookSourceName());
        book.setOriginOrder(getBookSource().getCustomOrder());
        book.setType(getBookSource().getBookSourceType());
        book.setUserNameSpace(getUserNS());
        return getBookInfo(book, canReName, $completion);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Type inference failed for: r1v10, types: [T, io.legado.app.help.http.StrResponse] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getChapterList(@org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<io.legado.app.data.entities.BookChapter>> r18) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 636
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.webBook.WebBook.getChapterList(io.legado.app.data.entities.Book, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getBookContent(@org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r17, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.BookChapter r18, @org.jetbrains.annotations.Nullable java.lang.String r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 573
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.webBook.WebBook.getBookContent(io.legado.app.data.entities.Book, io.legado.app.data.entities.BookChapter, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object getBookContent$default(WebBook webBook, Book book, BookChapter bookChapter, String str, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        return webBook.getBookContent(book, bookChapter, str, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00d9 A[Catch: Throwable -> 0x01c9, TryCatch #0 {Throwable -> 0x01c9, blocks: (B:11:0x0064, B:18:0x00be, B:19:0x00cf, B:21:0x00d9, B:23:0x00f8, B:27:0x0109, B:31:0x0118, B:45:0x018f, B:46:0x01b9, B:34:0x0125, B:36:0x014b, B:43:0x0185, B:47:0x01ba, B:17:0x00b6, B:42:0x017d), top: B:54:0x0043 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0125 A[Catch: Throwable -> 0x01c9, TryCatch #0 {Throwable -> 0x01c9, blocks: (B:11:0x0064, B:18:0x00be, B:19:0x00cf, B:21:0x00d9, B:23:0x00f8, B:27:0x0109, B:31:0x0118, B:45:0x018f, B:46:0x01b9, B:34:0x0125, B:36:0x014b, B:43:0x0185, B:47:0x01ba, B:17:0x00b6, B:42:0x017d), top: B:54:0x0043 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0117 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: preciseSearch-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m1195preciseSearch0E7RQCE(@org.jetbrains.annotations.NotNull java.lang.String r10, @org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<io.legado.app.data.entities.Book>> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 491
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.webBook.WebBook.m1195preciseSearch0E7RQCE(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
