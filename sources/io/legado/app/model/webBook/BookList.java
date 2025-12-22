package io.legado.app.model.webBook;

import io.legado.app.data.entities.BookSource;
import io.legado.app.data.entities.SearchBook;
import io.legado.app.help.BookHelp;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.AnalyzeRule;
import io.legado.app.model.analyzeRule.AnalyzeUrl;
import io.legado.app.utils.NetworkUtils;
import io.legado.app.utils.StringExtensionsKt;
import io.legado.app.utils.StringUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.JobKt;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BookList.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Ja\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0013JQ\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0082@ø\u0001��¢\u0006\u0002\u0010\u0018Já\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001b\u001a\u00020\u00102\u0010\u0010\u001c\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010\u001f\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010 \u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010!\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010\"\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010#\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010$\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010%\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0082@ø\u0001��¢\u0006\u0002\u0010&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"Lio/legado/app/model/webBook/BookList;", "", "()V", "analyzeBookList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/SearchBook;", "Lkotlin/collections/ArrayList;", NCXDocumentV3.XHTMLTgs.body, "", "bookSource", "Lio/legado/app/data/entities/BookSource;", "analyzeUrl", "Lio/legado/app/model/analyzeRule/AnalyzeUrl;", "baseUrl", "variableBook", "isSearch", "", "debugLog", "Lio/legado/app/model/DebugLog;", "(Ljava/lang/String;Lio/legado/app/data/entities/BookSource;Lio/legado/app/model/analyzeRule/AnalyzeUrl;Ljava/lang/String;Lio/legado/app/data/entities/SearchBook;ZLio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInfoItem", "analyzeRule", "Lio/legado/app/model/analyzeRule/AnalyzeRule;", "variable", "(Ljava/lang/String;Lio/legado/app/model/analyzeRule/AnalyzeRule;Lio/legado/app/data/entities/BookSource;Lio/legado/app/model/analyzeRule/AnalyzeUrl;Ljava/lang/String;Ljava/lang/String;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSearchItem", "item", "log", "ruleName", "", "Lio/legado/app/model/analyzeRule/AnalyzeRule$SourceRule;", "ruleBookUrl", "ruleAuthor", "ruleKind", "ruleCoverUrl", "ruleWordCount", "ruleIntro", "ruleLastChapter", "(Ljava/lang/Object;Lio/legado/app/model/analyzeRule/AnalyzeRule;Lio/legado/app/data/entities/BookSource;Ljava/lang/String;Ljava/lang/String;ZLjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/BookList.class */
public final class BookList {

    @NotNull
    public static final BookList INSTANCE = new BookList();

    /* compiled from: BookList.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookList.kt", l = {46, 73, 88}, i = {2, 2, 2, 2, 2, 2, 2, 2}, s = {"L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15"}, n = {"ruleName", "ruleBookUrl", "ruleAuthor", "ruleCoverUrl", "ruleIntro", "ruleKind", "ruleLastChapter", "ruleWordCount"}, m = "analyzeBookList", c = "io.legado.app.model.webBook.BookList")
    /* renamed from: io.legado.app.model.webBook.BookList$analyzeBookList$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/BookList$analyzeBookList$1.class */
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
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        int I$0;
        int I$1;
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
            return BookList.this.analyzeBookList(null, null, null, null, null, false, null, this);
        }
    }

    /* compiled from: BookList.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookList.kt", l = {125}, i = {0}, s = {"L$0"}, n = {"book"}, m = "getInfoItem", c = "io.legado.app.model.webBook.BookList")
    /* renamed from: io.legado.app.model.webBook.BookList$getInfoItem$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/BookList$getInfoItem$1.class */
    static final class C02161 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C02161(Continuation<? super C02161> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookList.this.getInfoItem(null, null, null, null, null, null, null, this);
        }
    }

    private BookList() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:127:0x0451, code lost:
    
        kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r29.add(r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0603  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0607  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0639  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x045b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:114:0x0603 -> B:97:0x0451). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object analyzeBookList(@org.jetbrains.annotations.Nullable java.lang.String r21, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.BookSource r22, @org.jetbrains.annotations.NotNull io.legado.app.model.analyzeRule.AnalyzeUrl r23, @org.jetbrains.annotations.NotNull java.lang.String r24, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.SearchBook r25, boolean r26, @org.jetbrains.annotations.Nullable io.legado.app.model.DebugLog r27, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.ArrayList<io.legado.app.data.entities.SearchBook>> r28) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1620
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.webBook.BookList.analyzeBookList(java.lang.String, io.legado.app.data.entities.BookSource, io.legado.app.model.analyzeRule.AnalyzeUrl, java.lang.String, io.legado.app.data.entities.SearchBook, boolean, io.legado.app.model.DebugLog, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object analyzeBookList$default(BookList bookList, String str, BookSource bookSource, AnalyzeUrl analyzeUrl, String str2, SearchBook searchBook, boolean z, DebugLog debugLog, Continuation continuation, int i, Object obj) throws Exception {
        if ((i & 32) != 0) {
            z = true;
        }
        if ((i & 64) != 0) {
            debugLog = null;
        }
        return bookList.analyzeBookList(str, bookSource, analyzeUrl, str2, searchBook, z, debugLog, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getInfoItem(java.lang.String r44, io.legado.app.model.analyzeRule.AnalyzeRule r45, io.legado.app.data.entities.BookSource r46, io.legado.app.model.analyzeRule.AnalyzeUrl r47, java.lang.String r48, java.lang.String r49, io.legado.app.model.DebugLog r50, kotlin.coroutines.Continuation<? super io.legado.app.data.entities.SearchBook> r51) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 317
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.webBook.BookList.getInfoItem(java.lang.String, io.legado.app.model.analyzeRule.AnalyzeRule, io.legado.app.data.entities.BookSource, io.legado.app.model.analyzeRule.AnalyzeUrl, java.lang.String, java.lang.String, io.legado.app.model.DebugLog, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object getInfoItem$default(BookList bookList, String str, AnalyzeRule analyzeRule, BookSource bookSource, AnalyzeUrl analyzeUrl, String str2, String str3, DebugLog debugLog, Continuation continuation, int i, Object obj) {
        if ((i & 64) != 0) {
            debugLog = null;
        }
        return bookList.getInfoItem(str, analyzeRule, bookSource, analyzeUrl, str2, str3, debugLog, continuation);
    }

    static /* synthetic */ Object getSearchItem$default(BookList bookList, Object obj, AnalyzeRule analyzeRule, BookSource bookSource, String str, String str2, boolean z, List list, List list2, List list3, List list4, List list5, List list6, List list7, List list8, DebugLog debugLog, Continuation continuation, int i, Object obj2) {
        if ((i & 16384) != 0) {
            debugLog = null;
        }
        return bookList.getSearchItem(obj, analyzeRule, bookSource, str, str2, z, list, list2, list3, list4, list5, list6, list7, list8, debugLog, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getSearchItem(Object item, AnalyzeRule analyzeRule, BookSource bookSource, String baseUrl, String variable, boolean log, List<AnalyzeRule.SourceRule> ruleName, List<AnalyzeRule.SourceRule> ruleBookUrl, List<AnalyzeRule.SourceRule> ruleAuthor, List<AnalyzeRule.SourceRule> ruleKind, List<AnalyzeRule.SourceRule> ruleCoverUrl, List<AnalyzeRule.SourceRule> ruleWordCount, List<AnalyzeRule.SourceRule> ruleIntro, List<AnalyzeRule.SourceRule> ruleLastChapter, DebugLog debugLog, Continuation<? super SearchBook> $completion) {
        SearchBook searchBook = new SearchBook(null, null, null, 0, null, null, null, null, null, null, null, null, 0L, variable, 0, 24575, null);
        searchBook.setOrigin(bookSource.getBookSourceUrl());
        searchBook.setOriginName(bookSource.getBookSourceName());
        searchBook.setType(bookSource.getBookSourceType());
        searchBook.setOriginOrder(bookSource.getCustomOrder());
        searchBook.setUserNameSpace(analyzeRule.getUserNameSpace());
        analyzeRule.setRuleData(searchBook);
        AnalyzeRule.setContent$default(analyzeRule, item, null, 2, null);
        JobKt.ensureActive($completion.getContext());
        if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取书名", false, 4, null);
        }
        searchBook.setName(BookHelp.INSTANCE.formatBookName(AnalyzeRule.getString$default(analyzeRule, (List) ruleName, (Object) null, false, 6, (Object) null)));
        if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getName()), false, 4, null);
        }
        if (searchBook.getName().length() > 0) {
            JobKt.ensureActive($completion.getContext());
            if (log && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取作者", false, 4, null);
            }
            searchBook.setAuthor(BookHelp.INSTANCE.formatBookAuthor(AnalyzeRule.getString$default(analyzeRule, (List) ruleAuthor, (Object) null, false, 6, (Object) null)));
            if (log && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getAuthor()), false, 4, null);
            }
            JobKt.ensureActive($completion.getContext());
            if (log && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取分类", false, 4, null);
            }
            try {
                List stringList$default = AnalyzeRule.getStringList$default(analyzeRule, (List) ruleKind, (Object) null, false, 6, (Object) null);
                searchBook.setKind(stringList$default == null ? null : CollectionsKt.joinToString$default(stringList$default, ",", null, null, 0, null, null, 62, null));
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getKind()), false, 4, null);
                }
            } catch (Exception e) {
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", e.getLocalizedMessage()), false, 4, null);
                }
            }
            JobKt.ensureActive($completion.getContext());
            if (log && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取字数", false, 4, null);
            }
            try {
                searchBook.setWordCount(StringUtils.INSTANCE.wordCountFormat(AnalyzeRule.getString$default(analyzeRule, (List) ruleWordCount, (Object) null, false, 6, (Object) null)));
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getWordCount()), false, 4, null);
                }
            } catch (Exception e2) {
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", e2.getLocalizedMessage()), false, 4, null);
                }
            }
            JobKt.ensureActive($completion.getContext());
            if (log && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取最新章节", false, 4, null);
            }
            try {
                searchBook.setLatestChapterTitle(AnalyzeRule.getString$default(analyzeRule, (List) ruleLastChapter, (Object) null, false, 6, (Object) null));
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getLatestChapterTitle()), false, 4, null);
                }
            } catch (Exception e3) {
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", e3.getLocalizedMessage()), false, 4, null);
                }
            }
            JobKt.ensureActive($completion.getContext());
            if (log && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取简介", false, 4, null);
            }
            try {
                searchBook.setIntro(StringExtensionsKt.htmlFormat(AnalyzeRule.getString$default(analyzeRule, (List) ruleIntro, (Object) null, false, 6, (Object) null)));
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getIntro()), false, 4, null);
                }
            } catch (Exception e4) {
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", e4.getLocalizedMessage()), false, 4, null);
                }
            }
            JobKt.ensureActive($completion.getContext());
            if (log && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取封面链接", false, 4, null);
            }
            try {
                String it = AnalyzeRule.getString$default(analyzeRule, (List) ruleCoverUrl, (Object) null, false, 6, (Object) null);
                if (it.length() > 0) {
                    searchBook.setCoverUrl(NetworkUtils.INSTANCE.getAbsoluteURL(baseUrl, it));
                }
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getCoverUrl()), false, 4, null);
                }
            } catch (Exception e5) {
                if (log && debugLog != null) {
                    DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", e5.getLocalizedMessage()), false, 4, null);
                }
            }
            JobKt.ensureActive($completion.getContext());
            if (log && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取详情页链接", false, 4, null);
            }
            searchBook.setBookUrl(AnalyzeRule.getString$default(analyzeRule, (List) ruleBookUrl, (Object) null, true, 2, (Object) null));
            if (searchBook.getBookUrl().length() == 0) {
                searchBook.setBookUrl(baseUrl);
            }
            if (log && debugLog != null) {
                DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getBookUrl()), false, 4, null);
            }
            return searchBook;
        }
        return null;
    }
}
