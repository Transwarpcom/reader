package io.legado.app.model.webBook;

import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.data.entities.BookSource;
import io.legado.app.data.entities.rule.TocRule;
import io.legado.app.model.DebugLog;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BookChapterList.kt */
@Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/legado/app/data/entities/BookChapter;", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "BookChapterList.kt", l = {91, 92}, i = {0}, s = {"L$0"}, n = {"urlStr"}, m = "invokeSuspend", c = "io.legado.app.model.webBook.BookChapterList$analyzeChapterList$3$asyncArray$1$1")
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/webBook/BookChapterList$analyzeChapterList$3$asyncArray$1$1.class */
final class BookChapterList$analyzeChapterList$3$asyncArray$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends BookChapter>>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ Ref.ObjectRef<Pair<List<BookChapter>, List<String>>> $chapterData;
    final /* synthetic */ int $tmp;
    final /* synthetic */ BookSource $bookSource;
    final /* synthetic */ Book $book;
    final /* synthetic */ DebugLog $debugLog;
    final /* synthetic */ TocRule $tocRule;
    final /* synthetic */ Ref.ObjectRef<String> $listRule;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BookChapterList$analyzeChapterList$3$asyncArray$1$1(Ref.ObjectRef<Pair<List<BookChapter>, List<String>>> $chapterData, int $tmp, BookSource $bookSource, Book $book, DebugLog $debugLog, TocRule $tocRule, Ref.ObjectRef<String> $listRule, Continuation<? super BookChapterList$analyzeChapterList$3$asyncArray$1$1> $completion) {
        super(2, $completion);
        this.$chapterData = $chapterData;
        this.$tmp = $tmp;
        this.$bookSource = $bookSource;
        this.$book = $book;
        this.$debugLog = $debugLog;
        this.$tocRule = $tocRule;
        this.$listRule = $listRule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
        return new BookChapterList$analyzeChapterList$3$asyncArray$1$1(this.$chapterData, this.$tmp, this.$bookSource, this.$book, this.$debugLog, this.$tocRule, this.$listRule, $completion);
    }

    @Nullable
    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(@NotNull CoroutineScope p1, @Nullable Continuation<? super List<BookChapter>> p2) {
        return ((BookChapterList$analyzeChapterList$3$asyncArray$1$1) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends BookChapter>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<BookChapter>>) continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00f2  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 267
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.webBook.BookChapterList$analyzeChapterList$3$asyncArray$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
