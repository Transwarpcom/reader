package io.legado.app.model.webBook;

import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookSource;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.AnalyzeRule;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BookInfo.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��<\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JO\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011JW\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lio/legado/app/model/webBook/BookInfo;", "", "()V", "analyzeBookInfo", "", "book", "Lio/legado/app/data/entities/Book;", NCXDocumentV3.XHTMLTgs.body, "", "bookSource", "Lio/legado/app/data/entities/BookSource;", "baseUrl", "redirectUrl", "canReName", "", "debugLog", "Lio/legado/app/model/DebugLog;", "(Lio/legado/app/data/entities/Book;Ljava/lang/String;Lio/legado/app/data/entities/BookSource;Ljava/lang/String;Ljava/lang/String;ZLio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "analyzeRule", "Lio/legado/app/model/analyzeRule/AnalyzeRule;", "(Lio/legado/app/data/entities/Book;Ljava/lang/String;Lio/legado/app/model/analyzeRule/AnalyzeRule;Lio/legado/app/data/entities/BookSource;Ljava/lang/String;Ljava/lang/String;ZLio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/BookInfo.class */
public final class BookInfo {

    @NotNull
    public static final BookInfo INSTANCE = new BookInfo();

    private BookInfo() {
    }

    public static /* synthetic */ Object analyzeBookInfo$default(BookInfo bookInfo, Book book, String str, BookSource bookSource, String str2, String str3, boolean z, DebugLog debugLog, Continuation continuation, int i, Object obj) throws Exception {
        if ((i & 64) != 0) {
            debugLog = null;
        }
        return bookInfo.analyzeBookInfo(book, str, bookSource, str2, str3, z, debugLog, continuation);
    }

    @Nullable
    public final Object analyzeBookInfo(@NotNull Book book, @Nullable String body, @NotNull BookSource bookSource, @NotNull String baseUrl, @NotNull String redirectUrl, boolean canReName, @Nullable DebugLog debugLog, @NotNull Continuation<? super Unit> $completion) throws Exception {
        if (body == null) {
            throw new Exception(Intrinsics.stringPlus("error_get_web_content: ", baseUrl));
        }
        if (debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("≡获取成功:", baseUrl), false, 4, null);
        }
        AnalyzeRule analyzeRule = new AnalyzeRule(book, bookSource, debugLog);
        AnalyzeRule.setContent$default(analyzeRule, body, null, 2, null).setBaseUrl(baseUrl);
        analyzeRule.setRedirectUrl(redirectUrl);
        Object objAnalyzeBookInfo = analyzeBookInfo(book, body, analyzeRule, bookSource, baseUrl, redirectUrl, canReName, debugLog, $completion);
        return objAnalyzeBookInfo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnalyzeBookInfo : Unit.INSTANCE;
    }

    public static /* synthetic */ Object analyzeBookInfo$default(BookInfo bookInfo, Book book, String str, AnalyzeRule analyzeRule, BookSource bookSource, String str2, String str3, boolean z, DebugLog debugLog, Continuation continuation, int i, Object obj) throws Exception {
        if ((i & 128) != 0) {
            debugLog = null;
        }
        return bookInfo.analyzeBookInfo(book, str, analyzeRule, bookSource, str2, str3, z, debugLog, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x020f  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object analyzeBookInfo(@org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r11, @org.jetbrains.annotations.Nullable java.lang.String r12, @org.jetbrains.annotations.NotNull io.legado.app.model.analyzeRule.AnalyzeRule r13, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.BookSource r14, @org.jetbrains.annotations.NotNull java.lang.String r15, @org.jetbrains.annotations.NotNull java.lang.String r16, boolean r17, @org.jetbrains.annotations.Nullable io.legado.app.model.DebugLog r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r19) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1759
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.webBook.BookInfo.analyzeBookInfo(io.legado.app.data.entities.Book, java.lang.String, io.legado.app.model.analyzeRule.AnalyzeRule, io.legado.app.data.entities.BookSource, java.lang.String, java.lang.String, boolean, io.legado.app.model.DebugLog, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
