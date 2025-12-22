package io.legado.app.model.webBook;

import io.legado.app.data.entities.BaseSource;
import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.data.entities.BookSource;
import io.legado.app.data.entities.rule.ContentRule;
import io.legado.app.help.http.StrResponse;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.AnalyzeRule;
import io.legado.app.model.analyzeRule.AnalyzeUrl;
import io.legado.app.model.analyzeRule.RuleDataInterface;
import io.legado.app.utils.HtmlFormatter;
import io.legado.app.utils.NetworkUtils;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
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
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jr\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J[\u0010\u0003\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"},
   d2 = {"Lio/legado/app/model/webBook/BookContent;", "", "()V", "analyzeContent", "Lkotlin/Pair;", "", "", "book", "Lio/legado/app/data/entities/Book;", "baseUrl", "redirectUrl", "body", "contentRule", "Lio/legado/app/data/entities/rule/ContentRule;", "chapter", "Lio/legado/app/data/entities/BookChapter;", "bookSource", "Lio/legado/app/data/entities/BookSource;", "nextChapterUrl", "printLog", "", "debugLog", "Lio/legado/app/model/DebugLog;", "bookChapter", "(Ljava/lang/String;Lio/legado/app/data/entities/Book;Lio/legado/app/data/entities/BookChapter;Lio/legado/app/data/entities/BookSource;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"}
)
public final class BookContent {
   @NotNull
   public static final BookContent INSTANCE = new BookContent();

   private BookContent() {
   }

   @Nullable
   public final Object analyzeContent(@Nullable String body, @NotNull Book book, @NotNull BookChapter bookChapter, @NotNull BookSource bookSource, @NotNull String baseUrl, @NotNull String redirectUrl, @Nullable String nextChapterUrl, @Nullable DebugLog debugLog, @NotNull Continuation<? super String> $completion) {
      Object $continuation;
      label194: {
         if (var9 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var9;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label194;
            }
         }

         $continuation = new ContinuationImpl(var9) {
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
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookContent.this.analyzeContent((String)null, (Book)null, (BookChapter)null, (BookSource)null, (String)null, (String)null, (String)null, (DebugLog)null, (Continuation)this);
            }
         };
      }

      final StringBuilder content;
      final ContentRule contentRule;
      AnalyzeRule analyzeRule;
      boolean var19;
      boolean var20;
      label199: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var28 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         final String mNextChapterUrl;
         ArrayList nextUrlList;
         final ObjectRef contentData;
         ObjectRef nextUrl;
         StrResponse res;
         String var18;
         boolean var21;
         boolean var23;
         Collection var24;
         boolean var25;
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            if (body == null) {
               throw new Exception("error_get_web_content");
            }

            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("≡获取成功:", baseUrl), false, 4, (Object)null);
            }

            CharSequence var29 = (CharSequence)nextChapterUrl;
            boolean var30 = false;
            boolean var31 = false;
            mNextChapterUrl = var29 != null && var29.length() != 0 ? nextChapterUrl : (String)null;
            content = new StringBuilder();
            String[] var32 = new String[]{redirectUrl};
            nextUrlList = CollectionsKt.arrayListOf(var32);
            contentRule = bookSource.getContentRule();
            analyzeRule = (new AnalyzeRule((RuleDataInterface)book, (BaseSource)bookSource, debugLog)).setContent(body, baseUrl);
            analyzeRule.setRedirectUrl(redirectUrl);
            analyzeRule.setChapter(bookChapter);
            analyzeRule.setNextChapterUrl(mNextChapterUrl);
            JobKt.ensureActive(((Continuation)$continuation).getContext());
            contentData = new ObjectRef();
            contentData.element = analyzeContent$default(this, book, baseUrl, redirectUrl, body, contentRule, bookChapter, bookSource, mNextChapterUrl, false, debugLog, 256, (Object)null);
            content.append((String)((Pair)contentData.element).getFirst());
            if (((List)((Pair)contentData.element).getSecond()).size() != 1) {
               if (((List)((Pair)contentData.element).getSecond()).size() <= 1) {
                  break label199;
               }

               JobKt.ensureActive(((Continuation)$continuation).getContext());
               if (debugLog != null) {
                  DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("◇并发解析正文,总页数:", Boxing.boxInt(((List)((Pair)contentData.element).getSecond()).size())), false, 4, (Object)null);
               }

               CoroutineContext var34 = (CoroutineContext)Dispatchers.getIO();
               Function2 var10001 = (Function2)(new Function2<CoroutineScope, Continuation<? super Unit>, Object>((Continuation)null) {
                  Object L$1;
                  Object L$2;
                  Object L$3;
                  int I$0;
                  int I$1;
                  int label;
                  // $FF: synthetic field
                  private Object L$0;

                  @Nullable
                  public final Object invokeSuspend(@NotNull Object $result) {
                     Object var15 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     CoroutineScope $this$withContext;
                     StringBuilder var5;
                     boolean $i$f$forEach;
                     Deferred[] var7;
                     int var8;
                     int var9;
                     boolean var12;
                     StringBuilder var13;
                     switch(this.label) {
                     case 0:
                        ResultKt.throwOnFailure($result);
                        $this$withContext = (CoroutineScope)this.L$0;
                        final int var4 = 0;
                        int var17 = ((List)((Pair)contentData.element).getSecond()).size();

                        Deferred[] var18;
                        for(var18 = new Deferred[var17]; var4 < var17; ++var4) {
                           var18[var4] = BuildersKt.async$default($this$withContext, (CoroutineContext)Dispatchers.getIO(), (CoroutineStart)null, (Function2)(new Function2<CoroutineScope, Continuation<? super String>, Object>((Continuation)null) {
                              Object L$0;
                              int label;

                              @Nullable
                              public final Object invokeSuspend(@NotNull Object $result) {
                                 Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                 Object var10000;
                                 String urlStr;
                                 switch(this.label) {
                                 case 0:
                                    ResultKt.throwOnFailure($result);
                                    urlStr = (String)((List)((Pair)contentData.element).getSecond()).get(var4);
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
                                 default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                 }

                                 StrResponse res = (StrResponse)var10000;
                                 BookContent var6 = BookContent.INSTANCE;
                                 Book var10001 = book;
                                 String var10003 = res.getUrl();
                                 String var7 = res.getBody();
                                 Intrinsics.checkNotNull(var7);
                                 return var6.analyzeContent(var10001, urlStr, var10003, var7, contentRule, bookChapter, bookSource, mNextChapterUrl, false, debugLog).getFirst();
                              }

                              @NotNull
                              public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
                                 return (Continuation)(new <anonymous constructor>($completion));
                              }

                              @Nullable
                              public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super String> p2) {
                                 return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                              }
                           }), 2, (Object)null);
                        }

                        var5 = content;
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
                        var13 = (StringBuilder)this.L$3;
                        var7 = (Deferred[])this.L$2;
                        var5 = (StringBuilder)this.L$1;
                        $this$withContext = (CoroutineScope)this.L$0;
                        ResultKt.throwOnFailure($result);
                        var13.append((String)$result);
                        ++var9;
                        break;
                     default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     while(var9 < var8) {
                        Object element$iv = var7[var9];
                        var12 = false;
                        JobKt.ensureActive($this$withContext.getCoroutineContext());
                        var13 = var5.append("\n");
                        this.L$0 = $this$withContext;
                        this.L$1 = var5;
                        this.L$2 = var7;
                        this.L$3 = var13;
                        this.I$0 = var8;
                        this.I$1 = var9;
                        this.label = 1;
                        Object var10000 = element$iv.await(this);
                        if (var10000 == var15) {
                           return var15;
                        }

                        Object var14 = var10000;
                        var13.append((String)var14);
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
               ((<undefinedtype>)$continuation).L$0 = bookChapter;
               ((<undefinedtype>)$continuation).L$1 = bookSource;
               ((<undefinedtype>)$continuation).L$2 = debugLog;
               ((<undefinedtype>)$continuation).L$3 = content;
               ((<undefinedtype>)$continuation).L$4 = contentRule;
               ((<undefinedtype>)$continuation).L$5 = analyzeRule;
               ((<undefinedtype>)$continuation).label = 2;
               if (BuildersKt.withContext(var34, var10001, (Continuation)$continuation) == var28) {
                  return var28;
               }
               break label199;
            }

            nextUrl = new ObjectRef();
            nextUrl.element = ((List)((Pair)contentData.element).getSecond()).get(0);
            break;
         case 1:
            nextUrl = (ObjectRef)((<undefinedtype>)$continuation).L$11;
            contentData = (ObjectRef)((<undefinedtype>)$continuation).L$10;
            analyzeRule = (AnalyzeRule)((<undefinedtype>)$continuation).L$9;
            contentRule = (ContentRule)((<undefinedtype>)$continuation).L$8;
            nextUrlList = (ArrayList)((<undefinedtype>)$continuation).L$7;
            content = (StringBuilder)((<undefinedtype>)$continuation).L$6;
            mNextChapterUrl = (String)((<undefinedtype>)$continuation).L$5;
            debugLog = (DebugLog)((<undefinedtype>)$continuation).L$4;
            redirectUrl = (String)((<undefinedtype>)$continuation).L$3;
            bookSource = (BookSource)((<undefinedtype>)$continuation).L$2;
            bookChapter = (BookChapter)((<undefinedtype>)$continuation).L$1;
            book = (Book)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            res = (StrResponse)$result;
            var18 = res.getBody();
            if (var18 != null) {
               var20 = false;
               var21 = false;
               var23 = false;
               contentData.element = INSTANCE.analyzeContent(book, (String)nextUrl.element, res.getUrl(), var18, contentRule, bookChapter, bookSource, mNextChapterUrl, false, debugLog);
               var24 = (Collection)((Pair)contentData.element).getSecond();
               var25 = false;
               nextUrl.element = !var24.isEmpty() ? (String)((List)((Pair)contentData.element).getSecond()).get(0) : "";
               content.append("\n").append((String)((Pair)contentData.element).getFirst());
            }
            break;
         case 2:
            analyzeRule = (AnalyzeRule)((<undefinedtype>)$continuation).L$5;
            contentRule = (ContentRule)((<undefinedtype>)$continuation).L$4;
            content = (StringBuilder)((<undefinedtype>)$continuation).L$3;
            debugLog = (DebugLog)((<undefinedtype>)$continuation).L$2;
            bookSource = (BookSource)((<undefinedtype>)$continuation).L$1;
            bookChapter = (BookChapter)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            break label199;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         while(true) {
            CharSequence var36 = (CharSequence)nextUrl.element;
            boolean var38 = false;
            if (var36.length() <= 0 || nextUrlList.contains(nextUrl.element)) {
               break;
            }

            var36 = (CharSequence)mNextChapterUrl;
            var38 = false;
            var19 = false;
            if (var36 != null && var36.length() != 0 && Intrinsics.areEqual(NetworkUtils.INSTANCE.getAbsoluteURL(redirectUrl, (String)nextUrl.element), NetworkUtils.INSTANCE.getAbsoluteURL(redirectUrl, mNextChapterUrl))) {
               break;
            }

            nextUrlList.add(nextUrl.element);
            JobKt.ensureActive(((Continuation)$continuation).getContext());
            AnalyzeUrl var10000 = new AnalyzeUrl((String)nextUrl.element, (String)null, (Integer)null, (String)null, (Integer)null, (String)null, (BaseSource)bookSource, (RuleDataInterface)book, (BookChapter)null, (Map)BaseSource.DefaultImpls.getHeaderMap$default((BaseSource)bookSource, false, 1, (Object)null), debugLog, 318, (DefaultConstructorMarker)null);
            ((<undefinedtype>)$continuation).L$0 = book;
            ((<undefinedtype>)$continuation).L$1 = bookChapter;
            ((<undefinedtype>)$continuation).L$2 = bookSource;
            ((<undefinedtype>)$continuation).L$3 = redirectUrl;
            ((<undefinedtype>)$continuation).L$4 = debugLog;
            ((<undefinedtype>)$continuation).L$5 = mNextChapterUrl;
            ((<undefinedtype>)$continuation).L$6 = content;
            ((<undefinedtype>)$continuation).L$7 = nextUrlList;
            ((<undefinedtype>)$continuation).L$8 = contentRule;
            ((<undefinedtype>)$continuation).L$9 = analyzeRule;
            ((<undefinedtype>)$continuation).L$10 = contentData;
            ((<undefinedtype>)$continuation).L$11 = nextUrl;
            ((<undefinedtype>)$continuation).label = 1;
            Object var33 = AnalyzeUrl.getStrResponseAwait$default(var10000, (String)null, (String)null, false, (Continuation)$continuation, 7, (Object)null);
            if (var33 == var28) {
               return var28;
            }

            res = (StrResponse)var33;
            var18 = res.getBody();
            if (var18 != null) {
               var20 = false;
               var21 = false;
               var23 = false;
               contentData.element = INSTANCE.analyzeContent(book, (String)nextUrl.element, res.getUrl(), var18, contentRule, bookChapter, bookSource, mNextChapterUrl, false, debugLog);
               var24 = (Collection)((Pair)contentData.element).getSecond();
               var25 = false;
               nextUrl.element = !var24.isEmpty() ? (String)((List)((Pair)contentData.element).getSecond()).get(0) : "";
               content.append("\n").append((String)((Pair)contentData.element).getFirst());
            }
         }

         if (debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("◇本章总页数:", Boxing.boxInt(nextUrlList.size())), false, 4, (Object)null);
         }
      }

      String replaceRegex = content.toString();
      Intrinsics.checkNotNullExpressionValue(replaceRegex, "content.toString()");
      String contentStr = replaceRegex;
      replaceRegex = contentRule.getReplaceRegex();
      CharSequence var40 = (CharSequence)replaceRegex;
      var19 = false;
      var20 = false;
      if (var40 != null && var40.length() != 0) {
         contentStr = AnalyzeRule.getString$default(analyzeRule, (String)replaceRegex, contentStr, false, 4, (Object)null);
      }

      if (debugLog != null) {
         DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取章节名称", false, 4, (Object)null);
      }

      if (debugLog != null) {
         DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", bookChapter.getTitle()), false, 4, (Object)null);
      }

      if (debugLog != null) {
         DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取正文内容 (长度：" + contentStr.length() + ')', false, 4, (Object)null);
      }

      if (contentStr.length() > 300) {
         if (debugLog != null) {
            String var37 = bookSource.getBookSourceUrl();
            StringBuilder var10002 = (new StringBuilder()).append("└\n");
            byte var41 = 0;
            short var43 = 150;
            boolean var22 = false;
            if (contentStr == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            String var10003 = contentStr.substring(var41, var43);
            Intrinsics.checkNotNullExpressionValue(var10003, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            var10002 = var10002.append(var10003).append(" ... ");
            int var42 = contentStr.length() - 150;
            int var44 = contentStr.length();
            var22 = false;
            if (contentStr == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var10003 = contentStr.substring(var42, var44);
            Intrinsics.checkNotNullExpressionValue(var10003, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            DebugLog.DefaultImpls.log$default(debugLog, var37, var10002.append(var10003).toString(), false, 4, (Object)null);
         }
      } else if (debugLog != null) {
         DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└\n", contentStr), false, 4, (Object)null);
      }

      return contentStr;
   }

   // $FF: synthetic method
   public static Object analyzeContent$default(BookContent var0, String var1, Book var2, BookChapter var3, BookSource var4, String var5, String var6, String var7, DebugLog var8, Continuation var9, int var10, Object var11) {
      if ((var10 & 64) != 0) {
         var7 = null;
      }

      if ((var10 & 128) != 0) {
         var8 = null;
      }

      return var0.analyzeContent(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   private final Pair<String, List<String>> analyzeContent(Book book, String baseUrl, String redirectUrl, String body, ContentRule contentRule, BookChapter chapter, BookSource bookSource, String nextChapterUrl, boolean printLog, DebugLog debugLog) throws Exception {
      AnalyzeRule analyzeRule = new AnalyzeRule((RuleDataInterface)book, (BaseSource)bookSource, debugLog);
      analyzeRule.setContent(body, baseUrl);
      analyzeRule.setChapter(chapter);
      URL rUrl = analyzeRule.setRedirectUrl(redirectUrl);
      analyzeRule.setNextChapterUrl(nextChapterUrl);
      boolean var14 = false;
      ArrayList nextUrlList = new ArrayList();
      analyzeRule.setChapter(chapter);
      String content = AnalyzeRule.getString$default(analyzeRule, (String)contentRule.getContent(), (Object)null, false, 6, (Object)null);
      content = HtmlFormatter.INSTANCE.formatKeepImg(content, rUrl);
      String nextUrlRule = contentRule.getNextContentUrl();
      CharSequence var16 = (CharSequence)nextUrlRule;
      boolean var17 = false;
      boolean var18 = false;
      if (var16 != null && var16.length() != 0) {
         if (printLog && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取正文下一页链接", false, 4, (Object)null);
         }

         List var23 = AnalyzeRule.getStringList$default(analyzeRule, (String)nextUrlRule, (Object)null, true, 2, (Object)null);
         if (var23 != null) {
            var18 = false;
            boolean var19 = false;
            int var21 = false;
            nextUrlList.addAll((Collection)var23);
         }

         if (printLog && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", CollectionsKt.joinToString$default((Iterable)nextUrlList, (CharSequence)"，", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null)), false, 4, (Object)null);
         }
      }

      return new Pair(content, nextUrlList);
   }

   // $FF: synthetic method
   static Pair analyzeContent$default(BookContent var0, Book var1, String var2, String var3, String var4, ContentRule var5, BookChapter var6, BookSource var7, String var8, boolean var9, DebugLog var10, int var11, Object var12) throws Exception {
      if ((var11 & 256) != 0) {
         var9 = true;
      }

      if ((var11 & 512) != 0) {
         var10 = null;
      }

      return var0.analyzeContent(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }
}
