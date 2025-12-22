package io.legado.app.model.webBook;

import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.data.entities.BookSource;
import io.legado.app.data.entities.rule.ContentRule;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.AnalyzeRule;
import io.legado.app.utils.HtmlFormatter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt;
import me.ag2s.epublib.epub.NCXDocumentV2;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BookContent.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jr\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J[\u0010\u0003\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lio/legado/app/model/webBook/BookContent;", "", "()V", "analyzeContent", "Lkotlin/Pair;", "", "", "book", "Lio/legado/app/data/entities/Book;", "baseUrl", "redirectUrl", NCXDocumentV3.XHTMLTgs.body, "contentRule", "Lio/legado/app/data/entities/rule/ContentRule;", NCXDocumentV2.NCXAttributeValues.chapter, "Lio/legado/app/data/entities/BookChapter;", "bookSource", "Lio/legado/app/data/entities/BookSource;", "nextChapterUrl", "printLog", "", "debugLog", "Lio/legado/app/model/DebugLog;", "bookChapter", "(Ljava/lang/String;Lio/legado/app/data/entities/Book;Lio/legado/app/data/entities/BookChapter;Lio/legado/app/data/entities/BookSource;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/BookContent.class */
public final class BookContent {

    @NotNull
    public static final BookContent INSTANCE = new BookContent();

    /* compiled from: BookContent.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookContent.kt", l = {69, 84}, i = {}, s = {}, n = {}, m = "analyzeContent", c = "io.legado.app.model.webBook.BookContent")
    /* renamed from: io.legado.app.model.webBook.BookContent$analyzeContent$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/BookContent$analyzeContent$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
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

        AnonymousClass1(Continuation<? super AnonymousClass1> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookContent.this.analyzeContent(null, null, null, null, null, null, null, null, this);
        }
    }

    private BookContent() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:39:0x01d3, B:41:0x01dd], limit reached: 111 */
    /* JADX WARN: Removed duplicated region for block: B:101:0x05d2  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0670  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0532  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x053c  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0544  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x055e  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x057a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x059d  */
    /* JADX WARN: Type inference failed for: r1v16, types: [T, kotlin.Pair] */
    /* JADX WARN: Type inference failed for: r1v40, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v70, types: [T, kotlin.Pair] */
    /* JADX WARN: Type inference failed for: r1v90, types: [T, java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x0346 -> B:30:0x0195). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object analyzeContent(@org.jetbrains.annotations.Nullable java.lang.String r17, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r18, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.BookChapter r19, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.BookSource r20, @org.jetbrains.annotations.NotNull java.lang.String r21, @org.jetbrains.annotations.NotNull java.lang.String r22, @org.jetbrains.annotations.Nullable java.lang.String r23, @org.jetbrains.annotations.Nullable io.legado.app.model.DebugLog r24, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r25) {
        /*
            Method dump skipped, instructions count: 1695
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.webBook.BookContent.analyzeContent(java.lang.String, io.legado.app.data.entities.Book, io.legado.app.data.entities.BookChapter, io.legado.app.data.entities.BookSource, java.lang.String, java.lang.String, java.lang.String, io.legado.app.model.DebugLog, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object analyzeContent$default(BookContent bookContent, String str, Book book, BookChapter bookChapter, BookSource bookSource, String str2, String str3, String str4, DebugLog debugLog, Continuation continuation, int i, Object obj) {
        if ((i & 64) != 0) {
            str4 = null;
        }
        if ((i & 128) != 0) {
            debugLog = null;
        }
        return bookContent.analyzeContent(str, book, bookChapter, bookSource, str2, str3, str4, debugLog, continuation);
    }

    /* compiled from: BookContent.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "BookContent.kt", l = {104}, i = {0}, s = {"L$0"}, n = {"$this$withContext"}, m = "invokeSuspend", c = "io.legado.app.model.webBook.BookContent$analyzeContent$3")
    /* renamed from: io.legado.app.model.webBook.BookContent$analyzeContent$3, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/BookContent$analyzeContent$3.class */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$1;
        Object L$2;
        Object L$3;
        int I$0;
        int I$1;
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ Ref.ObjectRef<Pair<String, List<String>>> $contentData;
        final /* synthetic */ BookSource $bookSource;
        final /* synthetic */ Book $book;
        final /* synthetic */ DebugLog $debugLog;
        final /* synthetic */ ContentRule $contentRule;
        final /* synthetic */ BookChapter $bookChapter;
        final /* synthetic */ String $mNextChapterUrl;
        final /* synthetic */ StringBuilder $content;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Ref.ObjectRef<Pair<String, List<String>>> $contentData, BookSource $bookSource, Book $book, DebugLog $debugLog, ContentRule $contentRule, BookChapter $bookChapter, String $mNextChapterUrl, StringBuilder $content, Continuation<? super AnonymousClass3> $completion) {
            super(2, $completion);
            this.$contentData = $contentData;
            this.$bookSource = $bookSource;
            this.$book = $book;
            this.$debugLog = $debugLog;
            this.$contentRule = $contentRule;
            this.$bookChapter = $bookChapter;
            this.$mNextChapterUrl = $mNextChapterUrl;
            this.$content = $content;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$contentData, this.$bookSource, this.$book, this.$debugLog, this.$contentRule, this.$bookChapter, this.$mNextChapterUrl, this.$content, $completion);
            anonymousClass3.L$0 = value;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass3) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            int length;
            Deferred[] deferredArr;
            StringBuilder sb;
            CoroutineScope $this$withContext;
            int i;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$withContext = (CoroutineScope) this.L$0;
                    int size = this.$contentData.element.getSecond().size();
                    Deferred[] asyncArray = new Deferred[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        int i3 = i2;
                        asyncArray[i3] = BuildersKt__Builders_commonKt.async$default($this$withContext, Dispatchers.getIO(), null, new BookContent$analyzeContent$3$asyncArray$1$1(this.$contentData, i3, this.$bookSource, this.$book, this.$debugLog, this.$contentRule, this.$bookChapter, this.$mNextChapterUrl, null), 2, null);
                    }
                    sb = this.$content;
                    deferredArr = asyncArray;
                    length = deferredArr.length;
                    i = 0;
                    break;
                case 1:
                    int i4 = this.I$1;
                    length = this.I$0;
                    StringBuilder sb2 = (StringBuilder) this.L$3;
                    deferredArr = (Deferred[]) this.L$2;
                    sb = (StringBuilder) this.L$1;
                    $this$withContext = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    sb2.append((String) $result);
                    i = i4 + 1;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            while (i < length) {
                Deferred deferred = deferredArr[i];
                JobKt.ensureActive($this$withContext.getCoroutineContext());
                StringBuilder sbAppend = sb.append("\n");
                this.L$0 = $this$withContext;
                this.L$1 = sb;
                this.L$2 = deferredArr;
                this.L$3 = sbAppend;
                this.I$0 = length;
                this.I$1 = i;
                this.label = 1;
                Object objAwait = deferred.await(this);
                if (objAwait == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sbAppend.append((String) objAwait);
                i++;
            }
            return Unit.INSTANCE;
        }
    }

    static /* synthetic */ Pair analyzeContent$default(BookContent bookContent, Book book, String str, String str2, String str3, ContentRule contentRule, BookChapter bookChapter, BookSource bookSource, String str4, boolean z, DebugLog debugLog, int i, Object obj) throws Exception {
        if ((i & 256) != 0) {
            z = true;
        }
        if ((i & 512) != 0) {
            debugLog = null;
        }
        return bookContent.analyzeContent(book, str, str2, str3, contentRule, bookChapter, bookSource, str4, z, debugLog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<String, List<String>> analyzeContent(Book book, String baseUrl, String redirectUrl, String body, ContentRule contentRule, BookChapter chapter, BookSource bookSource, String nextChapterUrl, boolean printLog, DebugLog debugLog) throws Exception {
        AnalyzeRule analyzeRule = new AnalyzeRule(book, bookSource, debugLog);
        analyzeRule.setContent(body, baseUrl);
        analyzeRule.setChapter(chapter);
        URL rUrl = analyzeRule.setRedirectUrl(redirectUrl);
        analyzeRule.setNextChapterUrl(nextChapterUrl);
        ArrayList nextUrlList = new ArrayList();
        analyzeRule.setChapter(chapter);
        String content = AnalyzeRule.getString$default(analyzeRule, contentRule.getContent(), (Object) null, false, 6, (Object) null);
        String content2 = HtmlFormatter.INSTANCE.formatKeepImg(content, rUrl);
        String nextUrlRule = contentRule.getNextContentUrl();
        String str = nextUrlRule;
        if (!(str == null || str.length() == 0)) {
            if (printLog && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取正文下一页链接", false, 4, null);
            }
            List it = AnalyzeRule.getStringList$default(analyzeRule, nextUrlRule, (Object) null, true, 2, (Object) null);
            if (it != null) {
                nextUrlList.addAll(it);
            }
            if (printLog && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", CollectionsKt.joinToString$default(nextUrlList, "，", null, null, 0, null, null, 62, null)), false, 4, null);
            }
        }
        return new Pair<>(content2, nextUrlList);
    }
}
