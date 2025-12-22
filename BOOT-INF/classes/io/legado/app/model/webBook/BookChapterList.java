package io.legado.app.model.webBook;

import io.legado.app.data.entities.BaseSource;
import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.data.entities.BookSource;
import io.legado.app.data.entities.rule.TocRule;
import io.legado.app.exception.TocEmptyException;
import io.legado.app.help.http.StrResponse;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.AnalyzeRule;
import io.legado.app.model.analyzeRule.AnalyzeUrl;
import io.legado.app.model.analyzeRule.RuleDataInterface;
import io.legado.app.utils.StringExtensionsKt;
import io.legado.app.utils.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0081\u0001\u0010\u0003\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00050\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0017JM\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"},
   d2 = {"Lio/legado/app/model/webBook/BookChapterList;", "", "()V", "analyzeChapterList", "Lkotlin/Pair;", "", "Lio/legado/app/data/entities/BookChapter;", "", "book", "Lio/legado/app/data/entities/Book;", "baseUrl", "redirectUrl", "body", "tocRule", "Lio/legado/app/data/entities/rule/TocRule;", "listRule", "bookSource", "Lio/legado/app/data/entities/BookSource;", "getNextUrl", "", "log", "debugLog", "Lio/legado/app/model/DebugLog;", "(Lio/legado/app/data/entities/Book;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lio/legado/app/data/entities/rule/TocRule;Ljava/lang/String;Lio/legado/app/data/entities/BookSource;ZZLio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/legado/app/data/entities/Book;Ljava/lang/String;Lio/legado/app/data/entities/BookSource;Ljava/lang/String;Ljava/lang/String;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"}
)
public final class BookChapterList {
   @NotNull
   public static final BookChapterList INSTANCE = new BookChapterList();

   private BookChapterList() {
   }

   @Nullable
   public final Object analyzeChapterList(@NotNull Book book, @Nullable String body, @NotNull BookSource bookSource, @NotNull String baseUrl, @NotNull String redirectUrl, @Nullable DebugLog debugLog, @NotNull Continuation<? super List<BookChapter>> $completion) {
      Object $continuation;
      label197: {
         if (var7 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var7;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label197;
            }
         }

         $continuation = new ContinuationImpl(var7) {
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
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookChapterList.this.analyzeChapterList((Book)null, (String)null, (BookSource)null, (String)null, (String)null, (DebugLog)null, (Continuation)this);
            }
         };
      }

      final ArrayList chapterList;
      int reverse;
      Unit var10000;
      boolean $i$f$forEachIndexed;
      label189: {
         final TocRule tocRule;
         ArrayList nextUrlList;
         final ObjectRef listRule;
         final ObjectRef chapterData;
         ObjectRef nextUrl;
         String var16;
         boolean var18;
         String var10002;
         boolean var19;
         String var10003;
         boolean var21;
         String var10006;
         Object var23;
         String var24;
         Object var30;
         BookChapterList var38;
         Object var39;
         label188: {
            Object $result = ((<undefinedtype>)$continuation).result;
            var30 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ObjectRef var26;
            switch(((<undefinedtype>)$continuation).label) {
            case 0:
               ResultKt.throwOnFailure($result);
               if (body == null) {
                  throw new Exception("error_get_web_content");
               }

               boolean var31 = false;
               chapterList = new ArrayList();
               if (debugLog == null) {
                  var10000 = null;
               } else {
                  DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("≡获取成功:", baseUrl), false, 4, (Object)null);
                  var10000 = Unit.INSTANCE;
               }

               tocRule = bookSource.getTocRule();
               String[] var32 = new String[]{redirectUrl};
               nextUrlList = CollectionsKt.arrayListOf(var32);
               reverse = 0;
               listRule = new ObjectRef();
               String var33 = tocRule.getChapterList();
               listRule.element = var33 == null ? "" : var33;
               byte var14;
               String var10001;
               boolean var36;
               if (StringsKt.startsWith$default((String)listRule.element, "-", false, 2, (Object)null)) {
                  reverse = 1;
                  var33 = (String)listRule.element;
                  var14 = 1;
                  var36 = false;
                  if (var33 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  }

                  var10001 = var33.substring(var14);
                  Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).substring(startIndex)");
                  listRule.element = var10001;
               }

               if (StringsKt.startsWith$default((String)listRule.element, "+", false, 2, (Object)null)) {
                  var33 = (String)listRule.element;
                  var14 = 1;
                  var36 = false;
                  if (var33 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  }

                  var10001 = var33.substring(var14);
                  Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).substring(startIndex)");
                  listRule.element = var10001;
               }

               chapterData = new ObjectRef();
               var26 = chapterData;
               var10006 = (String)listRule.element;
               ((<undefinedtype>)$continuation).L$0 = book;
               ((<undefinedtype>)$continuation).L$1 = bookSource;
               ((<undefinedtype>)$continuation).L$2 = debugLog;
               ((<undefinedtype>)$continuation).L$3 = chapterList;
               ((<undefinedtype>)$continuation).L$4 = tocRule;
               ((<undefinedtype>)$continuation).L$5 = nextUrlList;
               ((<undefinedtype>)$continuation).L$6 = listRule;
               ((<undefinedtype>)$continuation).L$7 = chapterData;
               ((<undefinedtype>)$continuation).L$8 = chapterData;
               ((<undefinedtype>)$continuation).I$0 = reverse;
               ((<undefinedtype>)$continuation).label = 1;
               var39 = this.analyzeChapterList(book, baseUrl, redirectUrl, body, tocRule, var10006, bookSource, true, true, debugLog, (Continuation)$continuation);
               if (var39 == var30) {
                  return var30;
               }
               break;
            case 1:
               reverse = ((<undefinedtype>)$continuation).I$0;
               var26 = (ObjectRef)((<undefinedtype>)$continuation).L$8;
               chapterData = (ObjectRef)((<undefinedtype>)$continuation).L$7;
               listRule = (ObjectRef)((<undefinedtype>)$continuation).L$6;
               nextUrlList = (ArrayList)((<undefinedtype>)$continuation).L$5;
               tocRule = (TocRule)((<undefinedtype>)$continuation).L$4;
               chapterList = (ArrayList)((<undefinedtype>)$continuation).L$3;
               debugLog = (DebugLog)((<undefinedtype>)$continuation).L$2;
               bookSource = (BookSource)((<undefinedtype>)$continuation).L$1;
               book = (Book)((<undefinedtype>)$continuation).L$0;
               ResultKt.throwOnFailure($result);
               var39 = $result;
               break;
            case 2:
               reverse = ((<undefinedtype>)$continuation).I$0;
               nextUrl = (ObjectRef)((<undefinedtype>)$continuation).L$8;
               chapterData = (ObjectRef)((<undefinedtype>)$continuation).L$7;
               listRule = (ObjectRef)((<undefinedtype>)$continuation).L$6;
               nextUrlList = (ArrayList)((<undefinedtype>)$continuation).L$5;
               tocRule = (TocRule)((<undefinedtype>)$continuation).L$4;
               chapterList = (ArrayList)((<undefinedtype>)$continuation).L$3;
               debugLog = (DebugLog)((<undefinedtype>)$continuation).L$2;
               bookSource = (BookSource)((<undefinedtype>)$continuation).L$1;
               book = (Book)((<undefinedtype>)$continuation).L$0;
               ResultKt.throwOnFailure($result);
               var16 = ((StrResponse)$result).getBody();
               if (var16 == null) {
                  var10000 = null;
               } else {
                  var18 = false;
                  var19 = false;
                  var21 = false;
                  var38 = INSTANCE;
                  var10002 = (String)nextUrl.element;
                  var10003 = (String)nextUrl.element;
                  var10006 = (String)listRule.element;
                  ((<undefinedtype>)$continuation).L$0 = book;
                  ((<undefinedtype>)$continuation).L$1 = bookSource;
                  ((<undefinedtype>)$continuation).L$2 = debugLog;
                  ((<undefinedtype>)$continuation).L$3 = chapterList;
                  ((<undefinedtype>)$continuation).L$4 = tocRule;
                  ((<undefinedtype>)$continuation).L$5 = nextUrlList;
                  ((<undefinedtype>)$continuation).L$6 = listRule;
                  ((<undefinedtype>)$continuation).L$7 = chapterData;
                  ((<undefinedtype>)$continuation).L$8 = nextUrl;
                  ((<undefinedtype>)$continuation).L$9 = chapterData;
                  ((<undefinedtype>)$continuation).I$0 = reverse;
                  ((<undefinedtype>)$continuation).label = 3;
                  var39 = var38.analyzeChapterList(book, var10002, var10003, var16, tocRule, var10006, bookSource, true, false, debugLog, (Continuation)$continuation);
                  if (var39 == var30) {
                     return var30;
                  }

                  var23 = var39;
                  chapterData.element = var23;
                  var24 = (String)CollectionsKt.firstOrNull((List)((Pair)chapterData.element).getSecond());
                  nextUrl.element = var24 == null ? "" : var24;
                  Boxing.boxBoolean(chapterList.addAll((Collection)((Pair)chapterData.element).getFirst()));
               }
               break label188;
            case 3:
               var21 = false;
               reverse = ((<undefinedtype>)$continuation).I$0;
               ObjectRef var22 = (ObjectRef)((<undefinedtype>)$continuation).L$9;
               nextUrl = (ObjectRef)((<undefinedtype>)$continuation).L$8;
               chapterData = (ObjectRef)((<undefinedtype>)$continuation).L$7;
               listRule = (ObjectRef)((<undefinedtype>)$continuation).L$6;
               nextUrlList = (ArrayList)((<undefinedtype>)$continuation).L$5;
               tocRule = (TocRule)((<undefinedtype>)$continuation).L$4;
               chapterList = (ArrayList)((<undefinedtype>)$continuation).L$3;
               debugLog = (DebugLog)((<undefinedtype>)$continuation).L$2;
               bookSource = (BookSource)((<undefinedtype>)$continuation).L$1;
               book = (Book)((<undefinedtype>)$continuation).L$0;
               ResultKt.throwOnFailure($result);
               var22.element = $result;
               var24 = (String)CollectionsKt.firstOrNull((List)((Pair)chapterData.element).getSecond());
               nextUrl.element = var24 == null ? "" : var24;
               Boxing.boxBoolean(chapterList.addAll((Collection)((Pair)chapterData.element).getFirst()));
               break label188;
            case 4:
               reverse = ((<undefinedtype>)$continuation).I$0;
               chapterList = (ArrayList)((<undefinedtype>)$continuation).L$2;
               debugLog = (DebugLog)((<undefinedtype>)$continuation).L$1;
               book = (Book)((<undefinedtype>)$continuation).L$0;
               ResultKt.throwOnFailure($result);
               var10000 = Unit.INSTANCE;
               break label189;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            Object var27 = var39;
            var26.element = var27;
            chapterList.addAll((Collection)((Pair)chapterData.element).getFirst());
            int var34 = ((List)((Pair)chapterData.element).getSecond()).size();
            switch(var34) {
            case 0:
               var10000 = Unit.INSTANCE;
               break label189;
            case 1:
               nextUrl = new ObjectRef();
               nextUrl.element = ((List)((Pair)chapterData.element).getSecond()).get(0);
               break;
            default:
               if (debugLog == null) {
                  var10000 = null;
               } else {
                  DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("◇并发解析目录,总页数:", Boxing.boxInt(((List)((Pair)chapterData.element).getSecond()).size())), false, 4, (Object)null);
                  var10000 = Unit.INSTANCE;
               }

               CoroutineContext var47 = (CoroutineContext)Dispatchers.getIO();
               Function2 var42 = (Function2)(new Function2<CoroutineScope, Continuation<? super Unit>, Object>((Continuation)null) {
                  Object L$1;
                  Object L$2;
                  int I$0;
                  int I$1;
                  int label;
                  // $FF: synthetic field
                  private Object L$0;

                  @Nullable
                  public final Object invokeSuspend(@NotNull Object $result) {
                     Object var15 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     ArrayList var5;
                     boolean $i$f$forEach;
                     Deferred[] var7;
                     int var8;
                     int var9;
                     boolean var12;
                     switch(this.label) {
                     case 0:
                        ResultKt.throwOnFailure($result);
                        CoroutineScope $this$withContext = (CoroutineScope)this.L$0;
                        final int var4 = 0;
                        int var17 = ((List)((Pair)chapterData.element).getSecond()).size();

                        Deferred[] var18;
                        for(var18 = new Deferred[var17]; var4 < var17; ++var4) {
                           var18[var4] = BuildersKt.async$default($this$withContext, (CoroutineContext)Dispatchers.getIO(), (CoroutineStart)null, (Function2)(new Function2<CoroutineScope, Continuation<? super List<? extends BookChapter>>, Object>((Continuation)null) {
                              Object L$0;
                              int label;

                              @Nullable
                              public final Object invokeSuspend(@NotNull Object $result) {
                                 Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                 String urlStr;
                                 Object var10000;
                                 switch(this.label) {
                                 case 0:
                                    ResultKt.throwOnFailure($result);
                                    urlStr = (String)((List)((Pair)chapterData.element).getSecond()).get(var4);
                                    AnalyzeUrl analyzeUrl = new AnalyzeUrl(urlStr, (String)null, (Integer)null, (String)null, (Integer)null, (String)null, (BaseSource)bookSource, (RuleDataInterface)book, (BookChapter)null, (Map)BaseSource.DefaultImpls.getHeaderMap$default((BaseSource)bookSource, false, 1, (Object)null), debugLog, 318, (DefaultConstructorMarker)null);
                                    Continuation var10004 = (Continuation)this;
                                    this.L$0 = urlStr;
                                    this.label = 1;
                                    var10000 = AnalyzeUrl.getStrResponseAwait$default(analyzeUrl, (String)null, (String)null, false, var10004, 7, (Object)null);
                                    if (var10000 == var5) {
                                       return var5;
                                    }
                                    break;
                                 case 1:
                                    urlStr = (String)this.L$0;
                                    ResultKt.throwOnFailure($result);
                                    var10000 = $result;
                                    break;
                                 case 2:
                                    ResultKt.throwOnFailure($result);
                                    var10000 = $result;
                                    return ((Pair)var10000).getFirst();
                                 default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                 }

                                 StrResponse res = (StrResponse)var10000;
                                 BookChapterList var6 = BookChapterList.INSTANCE;
                                 Book var10001 = book;
                                 String var10003 = res.getUrl();
                                 String var7 = res.getBody();
                                 Intrinsics.checkNotNull(var7);
                                 TocRule var10005 = tocRule;
                                 String var10006 = (String)listRule.element;
                                 BookSource var10007 = bookSource;
                                 DebugLog var10010 = debugLog;
                                 Continuation var10011 = (Continuation)this;
                                 this.L$0 = null;
                                 this.label = 2;
                                 var10000 = var6.analyzeChapterList(var10001, urlStr, var10003, var7, var10005, var10006, var10007, false, false, var10010, var10011);
                                 if (var10000 == var5) {
                                    return var5;
                                 } else {
                                    return ((Pair)var10000).getFirst();
                                 }
                              }

                              @NotNull
                              public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
                                 return (Continuation)(new <anonymous constructor>($completion));
                              }

                              @Nullable
                              public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super List<BookChapter>> p2) {
                                 return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                              }
                           }), 2, (Object)null);
                        }

                        var5 = chapterList;
                        $i$f$forEach = false;
                        var7 = var18;
                        var8 = var18.length;
                        var9 = 0;
                        break;
                     case 1:
                        $i$f$forEach = false;
                        var12 = false;
                        var9 = this.I$1;
                        var8 = this.I$0;
                        ArrayList var13 = (ArrayList)this.L$2;
                        var7 = (Deferred[])this.L$1;
                        var5 = (ArrayList)this.L$0;
                        ResultKt.throwOnFailure($result);
                        var13.addAll((Collection)$result);
                        ++var9;
                        break;
                     default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     while(var9 < var8) {
                        Object element$iv = var7[var9];
                        var12 = false;
                        this.L$0 = var5;
                        this.L$1 = var7;
                        this.L$2 = var5;
                        this.I$0 = var8;
                        this.I$1 = var9;
                        this.label = 1;
                        Object var10000 = element$iv.await(this);
                        if (var10000 == var15) {
                           return var15;
                        }

                        Object var14 = var10000;
                        var5.addAll((Collection)var14);
                        ++var9;
                     }

                     return Unit.INSTANCE;
                  }

                  @NotNull
                  public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
                     Function2 var3 = new <anonymous constructor>($completion);
                     var3.L$0 = value;
                     return (Continuation)var3;
                  }

                  @Nullable
                  public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
                     return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                  }
               });
               ((<undefinedtype>)$continuation).L$0 = book;
               ((<undefinedtype>)$continuation).L$1 = debugLog;
               ((<undefinedtype>)$continuation).L$2 = chapterList;
               ((<undefinedtype>)$continuation).L$3 = null;
               ((<undefinedtype>)$continuation).L$4 = null;
               ((<undefinedtype>)$continuation).L$5 = null;
               ((<undefinedtype>)$continuation).L$6 = null;
               ((<undefinedtype>)$continuation).L$7 = null;
               ((<undefinedtype>)$continuation).L$8 = null;
               ((<undefinedtype>)$continuation).I$0 = reverse;
               ((<undefinedtype>)$continuation).label = 4;
               if (BuildersKt.withContext(var47, var42, (Continuation)$continuation) == var30) {
                  return var30;
               }

               var10000 = Unit.INSTANCE;
               break label189;
            }
         }

         while(true) {
            CharSequence var37 = (CharSequence)nextUrl.element;
            $i$f$forEachIndexed = false;
            if (var37.length() <= 0 || nextUrlList.contains(nextUrl.element)) {
               if (debugLog == null) {
                  var10000 = null;
               } else {
                  DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("◇目录总页数:", Boxing.boxInt(nextUrlList.size())), false, 4, (Object)null);
                  var10000 = Unit.INSTANCE;
               }
               break;
            }

            nextUrlList.add(nextUrl.element);
            AnalyzeUrl var44 = new AnalyzeUrl((String)nextUrl.element, (String)null, (Integer)null, (String)null, (Integer)null, (String)null, (BaseSource)bookSource, (RuleDataInterface)book, (BookChapter)null, (Map)BaseSource.DefaultImpls.getHeaderMap$default((BaseSource)bookSource, false, 1, (Object)null), debugLog, 318, (DefaultConstructorMarker)null);
            ((<undefinedtype>)$continuation).L$0 = book;
            ((<undefinedtype>)$continuation).L$1 = bookSource;
            ((<undefinedtype>)$continuation).L$2 = debugLog;
            ((<undefinedtype>)$continuation).L$3 = chapterList;
            ((<undefinedtype>)$continuation).L$4 = tocRule;
            ((<undefinedtype>)$continuation).L$5 = nextUrlList;
            ((<undefinedtype>)$continuation).L$6 = listRule;
            ((<undefinedtype>)$continuation).L$7 = chapterData;
            ((<undefinedtype>)$continuation).L$8 = nextUrl;
            ((<undefinedtype>)$continuation).L$9 = null;
            ((<undefinedtype>)$continuation).I$0 = reverse;
            ((<undefinedtype>)$continuation).label = 2;
            var39 = AnalyzeUrl.getStrResponseAwait$default(var44, (String)null, (String)null, false, (Continuation)$continuation, 7, (Object)null);
            if (var39 == var30) {
               return var30;
            }

            var16 = ((StrResponse)var39).getBody();
            if (var16 == null) {
               var10000 = null;
            } else {
               var18 = false;
               var19 = false;
               var21 = false;
               var38 = INSTANCE;
               var10002 = (String)nextUrl.element;
               var10003 = (String)nextUrl.element;
               var10006 = (String)listRule.element;
               ((<undefinedtype>)$continuation).L$0 = book;
               ((<undefinedtype>)$continuation).L$1 = bookSource;
               ((<undefinedtype>)$continuation).L$2 = debugLog;
               ((<undefinedtype>)$continuation).L$3 = chapterList;
               ((<undefinedtype>)$continuation).L$4 = tocRule;
               ((<undefinedtype>)$continuation).L$5 = nextUrlList;
               ((<undefinedtype>)$continuation).L$6 = listRule;
               ((<undefinedtype>)$continuation).L$7 = chapterData;
               ((<undefinedtype>)$continuation).L$8 = nextUrl;
               ((<undefinedtype>)$continuation).L$9 = chapterData;
               ((<undefinedtype>)$continuation).I$0 = reverse;
               ((<undefinedtype>)$continuation).label = 3;
               var39 = var38.analyzeChapterList(book, var10002, var10003, var16, tocRule, var10006, bookSource, true, false, debugLog, (Continuation)$continuation);
               if (var39 == var30) {
                  return var30;
               }

               var23 = var39;
               chapterData.element = var23;
               var24 = (String)CollectionsKt.firstOrNull((List)((Pair)chapterData.element).getSecond());
               nextUrl.element = var24 == null ? "" : var24;
               Boxing.boxBoolean(chapterList.addAll((Collection)((Pair)chapterData.element).getFirst()));
            }
         }
      }

      if (chapterList.isEmpty()) {
         throw new TocEmptyException("目录为空");
      } else {
         if (reverse == 0) {
            CollectionsKt.reverse((List)chapterList);
         }

         JobKt.ensureActive(((Continuation)$continuation).getContext());
         LinkedHashSet lh = new LinkedHashSet((Collection)chapterList);
         ArrayList list = new ArrayList((Collection)lh);
         CollectionsKt.reverse((List)list);
         if (debugLog == null) {
            var10000 = null;
         } else {
            DebugLog.DefaultImpls.log$default(debugLog, book.getOrigin(), Intrinsics.stringPlus("◇目录总数:", Boxing.boxInt(list.size())), false, 4, (Object)null);
            var10000 = Unit.INSTANCE;
         }

         JobKt.ensureActive(((Continuation)$continuation).getContext());
         Iterable $this$forEachIndexed$iv = (Iterable)list;
         $i$f$forEachIndexed = false;
         int index$iv = 0;
         Iterator var45 = $this$forEachIndexed$iv.iterator();

         while(var45.hasNext()) {
            Object item$iv = var45.next();
            int var46 = index$iv++;
            boolean var48 = false;
            if (var46 < 0) {
               CollectionsKt.throwIndexOverflow();
            }

            Integer var51 = Boxing.boxInt(var46);
            BookChapter bookChapter = (BookChapter)item$iv;
            int index = ((Number)var51).intValue();
            int var25 = false;
            bookChapter.setIndex(index);
         }

         if (list.size() > 0) {
            book.setLatestChapterTitle(((BookChapter)CollectionsKt.last((List)list)).getTitle());
         }

         if (book.getTotalChapterNum() < list.size()) {
            book.setLastCheckCount(list.size() - book.getTotalChapterNum());
         }

         book.setTotalChapterNum(list.size());
         JobKt.ensureActive(((Continuation)$continuation).getContext());
         return list;
      }
   }

   // $FF: synthetic method
   public static Object analyzeChapterList$default(BookChapterList var0, Book var1, String var2, BookSource var3, String var4, String var5, DebugLog var6, Continuation var7, int var8, Object var9) {
      if ((var8 & 32) != 0) {
         var6 = null;
      }

      return var0.analyzeChapterList(var1, var2, var3, var4, var5, var6, var7);
   }

   private final Object analyzeChapterList(Book book, String baseUrl, String redirectUrl, String body, TocRule tocRule, String listRule, BookSource bookSource, boolean getNextUrl, boolean log, DebugLog debugLog, Continuation<? super Pair<? extends List<BookChapter>, ? extends List<String>>> $completion) {
      AnalyzeRule analyzeRule = new AnalyzeRule((RuleDataInterface)book, (BaseSource)bookSource, debugLog);
      AnalyzeRule.setContent$default(analyzeRule, body, (String)null, 2, (Object)null).setBaseUrl(baseUrl);
      analyzeRule.setRedirectUrl(redirectUrl);
      boolean var14 = false;
      ArrayList chapterList = new ArrayList();
      if (log && debugLog != null) {
         DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取目录列表", false, 4, (Object)null);
      }

      List elements = analyzeRule.getElements(listRule);
      if (log && debugLog != null) {
         DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└列表大小:", Boxing.boxInt(elements.size())), false, 4, (Object)null);
      }

      boolean var16 = false;
      ArrayList nextUrlList = new ArrayList();
      String nextTocRule = tocRule.getNextTocUrl();
      boolean var18;
      List nameRule;
      if (getNextUrl) {
         CharSequence var17 = (CharSequence)nextTocRule;
         var18 = false;
         boolean var19 = false;
         if (var17 != null && var17.length() != 0) {
            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取目录下一页列表", false, 4, (Object)null);
            }

            nameRule = AnalyzeRule.getStringList$default(analyzeRule, (String)nextTocRule, (Object)null, true, 2, (Object)null);
            if (nameRule != null) {
               var19 = false;
               boolean var20 = false;
               int var22 = false;
               Iterator var23 = nameRule.iterator();

               while(var23.hasNext()) {
                  String item = (String)var23.next();
                  if (!Intrinsics.areEqual(item, redirectUrl)) {
                     nextUrlList.add(item);
                  }
               }
            }

            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", TextUtils.join((CharSequence)"，\n", (Iterable)nextUrlList)), false, 4, (Object)null);
            }
         }
      }

      JobKt.ensureActive($completion.getContext());
      Collection var39 = (Collection)elements;
      var18 = false;
      if (!var39.isEmpty()) {
         if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌解析目录列表", false, 4, (Object)null);
         }

         nameRule = AnalyzeRule.splitSourceRule$default(analyzeRule, tocRule.getChapterName(), false, 2, (Object)null);
         List urlRule = AnalyzeRule.splitSourceRule$default(analyzeRule, tocRule.getChapterUrl(), false, 2, (Object)null);
         List vipRule = AnalyzeRule.splitSourceRule$default(analyzeRule, tocRule.isVip(), false, 2, (Object)null);
         List upTimeRule = AnalyzeRule.splitSourceRule$default(analyzeRule, tocRule.getUpdateTime(), false, 2, (Object)null);
         List isVolumeRule = AnalyzeRule.splitSourceRule$default(analyzeRule, tocRule.isVolume(), false, 2, (Object)null);
         Iterable $this$forEachIndexed$iv = (Iterable)elements;
         int $i$f$forEachIndexed = false;
         int index$iv = 0;
         Iterator var25 = $this$forEachIndexed$iv.iterator();

         while(var25.hasNext()) {
            Object item$iv = var25.next();
            int var27 = index$iv++;
            boolean var28 = false;
            if (var27 < 0) {
               CollectionsKt.throwIndexOverflow();
            }

            int index = ((Number)Boxing.boxInt(var27)).intValue();
            int var31 = false;
            JobKt.ensureActive($completion.getContext());
            AnalyzeRule.setContent$default(analyzeRule, item$iv, (String)null, 2, (Object)null);
            String isVolume = book.getBookUrl();
            BookChapter bookChapter = new BookChapter((String)null, (String)null, false, redirectUrl, isVolume, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8167, (DefaultConstructorMarker)null);
            analyzeRule.setChapter(bookChapter);
            bookChapter.setTitle(AnalyzeRule.getString$default(analyzeRule, (List)nameRule, (Object)null, false, 6, (Object)null));
            bookChapter.setUrl(AnalyzeRule.getString$default(analyzeRule, (List)urlRule, (Object)null, false, 6, (Object)null));
            bookChapter.setTag(AnalyzeRule.getString$default(analyzeRule, (List)upTimeRule, (Object)null, false, 6, (Object)null));
            bookChapter.setUserNameSpace(book.getUserNameSpace());
            isVolume = AnalyzeRule.getString$default(analyzeRule, (List)isVolumeRule, (Object)null, false, 6, (Object)null);
            bookChapter.setVolume(false);
            if (StringExtensionsKt.isTrue$default(isVolume, false, 1, (Object)null)) {
               bookChapter.setVolume(true);
            }

            CharSequence var34 = (CharSequence)bookChapter.getUrl();
            boolean var35 = false;
            if (var34.length() == 0) {
               if (bookChapter.isVolume()) {
                  bookChapter.setUrl(Intrinsics.stringPlus(bookChapter.getTitle(), Boxing.boxInt(index)));
                  if (log && debugLog != null) {
                     DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "⇒一级目录" + index + "未获取到url,使用标题替代", false, 4, (Object)null);
                  }
               } else {
                  bookChapter.setUrl(baseUrl);
                  if (log && debugLog != null) {
                     DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "⇒目录" + index + "未获取到url,使用baseUrl替代", false, 4, (Object)null);
                  }
               }
            }

            var34 = (CharSequence)bookChapter.getTitle();
            var35 = false;
            if (var34.length() > 0) {
               String isVip = AnalyzeRule.getString$default(analyzeRule, (List)vipRule, (Object)null, false, 6, (Object)null);
               if (StringExtensionsKt.isTrue$default(isVip, false, 1, (Object)null)) {
                  bookChapter.setTitle(Intrinsics.stringPlus("\ud83d\udd12", bookChapter.getTitle()));
               }

               chapterList.add(bookChapter);
            } else if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "章节名为空", false, 4, (Object)null);
            }
         }

         if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "└目录列表解析完成", false, 4, (Object)null);
         }

         if (chapterList.size() > 0) {
            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "≡首章信息", false, 4, (Object)null);
            }

            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("◇章节名称:", ((BookChapter)chapterList.get(0)).getTitle()), false, 4, (Object)null);
            }

            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("◇章节链接:", ((BookChapter)chapterList.get(0)).getUrl()), false, 4, (Object)null);
            }

            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("◇章节信息:", ((BookChapter)chapterList.get(0)).getTag()), false, 4, (Object)null);
            }

            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("◇是否卷名:", Boxing.boxBoolean(((BookChapter)chapterList.get(0)).isVolume())), false, 4, (Object)null);
            }
         } else if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "章节列表为空", false, 4, (Object)null);
         }
      }

      return new Pair(chapterList, nextUrlList);
   }

   // $FF: synthetic method
   static Object analyzeChapterList$default(BookChapterList var0, Book var1, String var2, String var3, String var4, TocRule var5, String var6, BookSource var7, boolean var8, boolean var9, DebugLog var10, Continuation var11, int var12, Object var13) {
      if ((var12 & 128) != 0) {
         var8 = true;
      }

      if ((var12 & 256) != 0) {
         var9 = false;
      }

      if ((var12 & 512) != 0) {
         var10 = null;
      }

      return var0.analyzeChapterList(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
   }
}
