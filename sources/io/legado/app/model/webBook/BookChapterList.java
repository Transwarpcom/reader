package io.legado.app.model.webBook;

import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.data.entities.BookSource;
import io.legado.app.data.entities.rule.TocRule;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.AnalyzeRule;
import io.legado.app.utils.StringExtensionsKt;
import io.legado.app.utils.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BookChapterList.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0081\u0001\u0010\u0003\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00050\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0082@ø\u0001��¢\u0006\u0002\u0010\u0017JM\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lio/legado/app/model/webBook/BookChapterList;", "", "()V", "analyzeChapterList", "Lkotlin/Pair;", "", "Lio/legado/app/data/entities/BookChapter;", "", "book", "Lio/legado/app/data/entities/Book;", "baseUrl", "redirectUrl", NCXDocumentV3.XHTMLTgs.body, "tocRule", "Lio/legado/app/data/entities/rule/TocRule;", "listRule", "bookSource", "Lio/legado/app/data/entities/BookSource;", "getNextUrl", "", "log", "debugLog", "Lio/legado/app/model/DebugLog;", "(Lio/legado/app/data/entities/Book;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lio/legado/app/data/entities/rule/TocRule;Ljava/lang/String;Lio/legado/app/data/entities/BookSource;ZZLio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/legado/app/data/entities/Book;Ljava/lang/String;Lio/legado/app/data/entities/BookSource;Ljava/lang/String;Ljava/lang/String;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/BookChapterList.class */
public final class BookChapterList {

    @NotNull
    public static final BookChapterList INSTANCE = new BookChapterList();

    /* compiled from: BookChapterList.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookChapterList.kt", l = {50, 67, 68, 80}, i = {0}, s = {"L$7"}, n = {"chapterData"}, m = "analyzeChapterList", c = "io.legado.app.model.webBook.BookChapterList")
    /* renamed from: io.legado.app.model.webBook.BookChapterList$analyzeChapterList$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/BookChapterList$analyzeChapterList$1.class */
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
        int I$0;
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
            return BookChapterList.this.analyzeChapterList(null, null, null, null, null, null, this);
        }
    }

    private BookChapterList() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0545  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x056d  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0571  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0590  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0671  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x067c  */
    /* JADX WARN: Type inference failed for: r1v104, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v74, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v9, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v97, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x040a -> B:45:0x02cc). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object analyzeChapterList(@org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r17, @org.jetbrains.annotations.Nullable java.lang.String r18, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.BookSource r19, @org.jetbrains.annotations.NotNull java.lang.String r20, @org.jetbrains.annotations.NotNull java.lang.String r21, @org.jetbrains.annotations.Nullable io.legado.app.model.DebugLog r22, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<io.legado.app.data.entities.BookChapter>> r23) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1951
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.webBook.BookChapterList.analyzeChapterList(io.legado.app.data.entities.Book, java.lang.String, io.legado.app.data.entities.BookSource, java.lang.String, java.lang.String, io.legado.app.model.DebugLog, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object analyzeChapterList$default(BookChapterList bookChapterList, Book book, String str, BookSource bookSource, String str2, String str3, DebugLog debugLog, Continuation continuation, int i, Object obj) throws Exception {
        if ((i & 32) != 0) {
            debugLog = null;
        }
        return bookChapterList.analyzeChapterList(book, str, bookSource, str2, str3, debugLog, continuation);
    }

    /* compiled from: BookChapterList.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "BookChapterList.kt", l = {99}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.legado.app.model.webBook.BookChapterList$analyzeChapterList$3")
    /* renamed from: io.legado.app.model.webBook.BookChapterList$analyzeChapterList$3, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/BookChapterList$analyzeChapterList$3.class */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$1;
        Object L$2;
        int I$0;
        int I$1;
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ Ref.ObjectRef<Pair<List<BookChapter>, List<String>>> $chapterData;
        final /* synthetic */ BookSource $bookSource;
        final /* synthetic */ Book $book;
        final /* synthetic */ DebugLog $debugLog;
        final /* synthetic */ TocRule $tocRule;
        final /* synthetic */ Ref.ObjectRef<String> $listRule;
        final /* synthetic */ ArrayList<BookChapter> $chapterList;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Ref.ObjectRef<Pair<List<BookChapter>, List<String>>> $chapterData, BookSource $bookSource, Book $book, DebugLog $debugLog, TocRule $tocRule, Ref.ObjectRef<String> $listRule, ArrayList<BookChapter> $chapterList, Continuation<? super AnonymousClass3> $completion) {
            super(2, $completion);
            this.$chapterData = $chapterData;
            this.$bookSource = $bookSource;
            this.$book = $book;
            this.$debugLog = $debugLog;
            this.$tocRule = $tocRule;
            this.$listRule = $listRule;
            this.$chapterList = $chapterList;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$chapterData, this.$bookSource, this.$book, this.$debugLog, this.$tocRule, this.$listRule, this.$chapterList, $completion);
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
            ArrayList<BookChapter> arrayList;
            int i;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$withContext = (CoroutineScope) this.L$0;
                    int size = this.$chapterData.element.getSecond().size();
                    Deferred[] asyncArray = new Deferred[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        int i3 = i2;
                        asyncArray[i3] = BuildersKt__Builders_commonKt.async$default($this$withContext, Dispatchers.getIO(), null, new BookChapterList$analyzeChapterList$3$asyncArray$1$1(this.$chapterData, i3, this.$bookSource, this.$book, this.$debugLog, this.$tocRule, this.$listRule, null), 2, null);
                    }
                    arrayList = this.$chapterList;
                    deferredArr = asyncArray;
                    length = deferredArr.length;
                    i = 0;
                    break;
                case 1:
                    int i4 = this.I$1;
                    length = this.I$0;
                    ArrayList arrayList2 = (ArrayList) this.L$2;
                    deferredArr = (Deferred[]) this.L$1;
                    arrayList = (ArrayList) this.L$0;
                    ResultKt.throwOnFailure($result);
                    arrayList2.addAll((Collection) $result);
                    i = i4 + 1;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            while (i < length) {
                Deferred deferred = deferredArr[i];
                ArrayList<BookChapter> arrayList3 = arrayList;
                this.L$0 = arrayList;
                this.L$1 = deferredArr;
                this.L$2 = arrayList3;
                this.I$0 = length;
                this.I$1 = i;
                this.label = 1;
                Object objAwait = deferred.await(this);
                if (objAwait == coroutine_suspended) {
                    return coroutine_suspended;
                }
                arrayList3.addAll((Collection) objAwait);
                i++;
            }
            return Unit.INSTANCE;
        }
    }

    static /* synthetic */ Object analyzeChapterList$default(BookChapterList bookChapterList, Book book, String str, String str2, String str3, TocRule tocRule, String str4, BookSource bookSource, boolean z, boolean z2, DebugLog debugLog, Continuation continuation, int i, Object obj) {
        if ((i & 128) != 0) {
            z = true;
        }
        if ((i & 256) != 0) {
            z2 = false;
        }
        if ((i & 512) != 0) {
            debugLog = null;
        }
        return bookChapterList.analyzeChapterList(book, str, str2, str3, tocRule, str4, bookSource, z, z2, debugLog, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object analyzeChapterList(Book book, String baseUrl, String redirectUrl, String body, TocRule tocRule, String listRule, BookSource bookSource, boolean getNextUrl, boolean log, DebugLog debugLog, Continuation<? super Pair<? extends List<BookChapter>, ? extends List<String>>> $completion) {
        AnalyzeRule analyzeRule = new AnalyzeRule(book, bookSource, debugLog);
        AnalyzeRule.setContent$default(analyzeRule, body, null, 2, null).setBaseUrl(baseUrl);
        analyzeRule.setRedirectUrl(redirectUrl);
        ArrayList chapterList = new ArrayList();
        if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取目录列表", false, 4, null);
        }
        List elements = analyzeRule.getElements(listRule);
        if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└列表大小:", Boxing.boxInt(elements.size())), false, 4, null);
        }
        ArrayList nextUrlList = new ArrayList();
        String nextTocRule = tocRule.getNextTocUrl();
        if (getNextUrl) {
            String str = nextTocRule;
            if (!(str == null || str.length() == 0)) {
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取目录下一页列表", false, 4, null);
                }
                List<String> it = AnalyzeRule.getStringList$default(analyzeRule, nextTocRule, (Object) null, true, 2, (Object) null);
                if (it != null) {
                    for (String item : it) {
                        if (!Intrinsics.areEqual(item, redirectUrl)) {
                            nextUrlList.add(item);
                        }
                    }
                }
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", TextUtils.join("，\n", nextUrlList)), false, 4, null);
                }
            }
        }
        JobKt.ensureActive($completion.getContext());
        if (!elements.isEmpty()) {
            if (log && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌解析目录列表", false, 4, null);
            }
            List nameRule = AnalyzeRule.splitSourceRule$default(analyzeRule, tocRule.getChapterName(), false, 2, null);
            List urlRule = AnalyzeRule.splitSourceRule$default(analyzeRule, tocRule.getChapterUrl(), false, 2, null);
            List vipRule = AnalyzeRule.splitSourceRule$default(analyzeRule, tocRule.isVip(), false, 2, null);
            List upTimeRule = AnalyzeRule.splitSourceRule$default(analyzeRule, tocRule.getUpdateTime(), false, 2, null);
            List isVolumeRule = AnalyzeRule.splitSourceRule$default(analyzeRule, tocRule.isVolume(), false, 2, null);
            List $this$forEachIndexed$iv = elements;
            int index$iv = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                int i = index$iv;
                index$iv++;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                int index = Boxing.boxInt(i).intValue();
                JobKt.ensureActive($completion.getContext());
                AnalyzeRule.setContent$default(analyzeRule, item$iv, null, 2, null);
                BookChapter bookChapter = new BookChapter(null, null, false, redirectUrl, book.getBookUrl(), 0, null, null, null, null, null, null, null, 8167, null);
                analyzeRule.setChapter(bookChapter);
                bookChapter.setTitle(AnalyzeRule.getString$default(analyzeRule, nameRule, (Object) null, false, 6, (Object) null));
                bookChapter.setUrl(AnalyzeRule.getString$default(analyzeRule, urlRule, (Object) null, false, 6, (Object) null));
                bookChapter.setTag(AnalyzeRule.getString$default(analyzeRule, upTimeRule, (Object) null, false, 6, (Object) null));
                bookChapter.setUserNameSpace(book.getUserNameSpace());
                String isVolume = AnalyzeRule.getString$default(analyzeRule, isVolumeRule, (Object) null, false, 6, (Object) null);
                bookChapter.setVolume(false);
                if (StringExtensionsKt.isTrue$default(isVolume, false, 1, null)) {
                    bookChapter.setVolume(true);
                }
                if (bookChapter.getUrl().length() == 0) {
                    if (bookChapter.isVolume()) {
                        bookChapter.setUrl(Intrinsics.stringPlus(bookChapter.getTitle(), Boxing.boxInt(index)));
                        if (log && debugLog != null) {
                            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "⇒一级目录" + index + "未获取到url,使用标题替代", false, 4, null);
                        }
                    } else {
                        bookChapter.setUrl(baseUrl);
                        if (log && debugLog != null) {
                            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "⇒目录" + index + "未获取到url,使用baseUrl替代", false, 4, null);
                        }
                    }
                }
                if (bookChapter.getTitle().length() > 0) {
                    String isVip = AnalyzeRule.getString$default(analyzeRule, vipRule, (Object) null, false, 6, (Object) null);
                    if (StringExtensionsKt.isTrue$default(isVip, false, 1, null)) {
                        bookChapter.setTitle(Intrinsics.stringPlus("��", bookChapter.getTitle()));
                    }
                    chapterList.add(bookChapter);
                } else if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "章节名为空", false, 4, null);
                }
            }
            if (log && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "└目录列表解析完成", false, 4, null);
            }
            if (chapterList.size() > 0) {
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "≡首章信息", false, 4, null);
                }
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("◇章节名称:", ((BookChapter) chapterList.get(0)).getTitle()), false, 4, null);
                }
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("◇章节链接:", ((BookChapter) chapterList.get(0)).getUrl()), false, 4, null);
                }
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("◇章节信息:", ((BookChapter) chapterList.get(0)).getTag()), false, 4, null);
                }
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("◇是否卷名:", Boxing.boxBoolean(((BookChapter) chapterList.get(0)).isVolume())), false, 4, null);
                }
            } else if (log && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "章节列表为空", false, 4, null);
            }
        }
        return new Pair(chapterList, nextUrlList);
    }
}
