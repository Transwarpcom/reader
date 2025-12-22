package io.legado.app.model.webBook;

import io.legado.app.data.entities.BaseSource;
import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookSource;
import io.legado.app.data.entities.SearchBook;
import io.legado.app.data.entities.rule.BookListRule;
import io.legado.app.help.BookHelp;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.AnalyzeRule;
import io.legado.app.model.analyzeRule.AnalyzeUrl;
import io.legado.app.model.analyzeRule.RuleDataInterface;
import io.legado.app.utils.NetworkUtils;
import io.legado.app.utils.StringExtensionsKt;
import io.legado.app.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.JobKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Ja\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013JQ\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0018Já\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001b\u001a\u00020\u00102\u0010\u0010\u001c\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010\u001f\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010 \u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010!\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010\"\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010#\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010$\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\u0010\u0010%\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00160\u001d2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"},
   d2 = {"Lio/legado/app/model/webBook/BookList;", "", "()V", "analyzeBookList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/SearchBook;", "Lkotlin/collections/ArrayList;", "body", "", "bookSource", "Lio/legado/app/data/entities/BookSource;", "analyzeUrl", "Lio/legado/app/model/analyzeRule/AnalyzeUrl;", "baseUrl", "variableBook", "isSearch", "", "debugLog", "Lio/legado/app/model/DebugLog;", "(Ljava/lang/String;Lio/legado/app/data/entities/BookSource;Lio/legado/app/model/analyzeRule/AnalyzeUrl;Ljava/lang/String;Lio/legado/app/data/entities/SearchBook;ZLio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInfoItem", "analyzeRule", "Lio/legado/app/model/analyzeRule/AnalyzeRule;", "variable", "(Ljava/lang/String;Lio/legado/app/model/analyzeRule/AnalyzeRule;Lio/legado/app/data/entities/BookSource;Lio/legado/app/model/analyzeRule/AnalyzeUrl;Ljava/lang/String;Ljava/lang/String;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSearchItem", "item", "log", "ruleName", "", "Lio/legado/app/model/analyzeRule/AnalyzeRule$SourceRule;", "ruleBookUrl", "ruleAuthor", "ruleKind", "ruleCoverUrl", "ruleWordCount", "ruleIntro", "ruleLastChapter", "(Ljava/lang/Object;Lio/legado/app/model/analyzeRule/AnalyzeRule;Lio/legado/app/data/entities/BookSource;Ljava/lang/String;Ljava/lang/String;ZLjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"}
)
public final class BookList {
   @NotNull
   public static final BookList INSTANCE = new BookList();

   private BookList() {
   }

   @Nullable
   public final Object analyzeBookList(@Nullable String body, @NotNull BookSource bookSource, @NotNull AnalyzeUrl analyzeUrl, @NotNull String baseUrl, @NotNull SearchBook variableBook, boolean isSearch, @Nullable DebugLog debugLog, @NotNull Continuation<? super ArrayList<SearchBook>> $completion) throws Exception {
      Object $continuation;
      label194: {
         if (var8 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var8;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label194;
            }
         }

         $continuation = new ContinuationImpl(var8) {
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
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookList.this.analyzeBookList((String)null, (BookSource)null, (AnalyzeUrl)null, (String)null, (SearchBook)null, false, (DebugLog)null, (Continuation)this);
            }
         };
      }

      ArrayList bookList;
      Object var10000;
      boolean var52;
      boolean var55;
      label235: {
         boolean var48;
         label236: {
            Object $result = ((<undefinedtype>)$continuation).result;
            Object var35 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            AnalyzeRule analyzeRule;
            int reverse;
            List ruleName;
            List ruleBookUrl;
            List ruleAuthor;
            List ruleCoverUrl;
            List ruleIntro;
            List ruleKind;
            List ruleLastChapter;
            List ruleWordCount;
            Iterator var23;
            int var24;
            SearchBook var27;
            boolean var29;
            boolean var30;
            boolean var32;
            boolean var43;
            switch(((<undefinedtype>)$continuation).label) {
            case 0:
               ResultKt.throwOnFailure($result);
               bookList = new ArrayList();
               if (body == null) {
                  throw new Exception("error_get_web_content");
               }

               Unit var45;
               if (debugLog == null) {
                  var10000 = null;
               } else {
                  DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("≡获取成功:", analyzeUrl.getRuleUrl()), false, 4, (Object)null);
                  var45 = Unit.INSTANCE;
               }

               analyzeRule = new AnalyzeRule((RuleDataInterface)variableBook, (BaseSource)bookSource, debugLog);
               AnalyzeRule.setContent$default(analyzeRule, body, (String)null, 2, (Object)null).setBaseUrl(baseUrl);
               analyzeRule.setRedirectUrl(baseUrl);
               String var11 = bookSource.getBookUrlPattern();
               String var56;
               if (var11 == null) {
                  var10000 = null;
               } else {
                  boolean var13 = false;
                  boolean var14 = false;
                  var43 = false;
                  JobKt.ensureActive(((Continuation)$continuation).getContext());
                  CharSequence var46 = (CharSequence)baseUrl;
                  var52 = false;
                  Regex var49 = new Regex(var11);
                  var52 = false;
                  if (var49.matches(var46)) {
                     if (debugLog == null) {
                        var10000 = null;
                     } else {
                        DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "≡链接为详情页", false, 4, (Object)null);
                        var45 = Unit.INSTANCE;
                     }

                     BookList var58 = INSTANCE;
                     var56 = variableBook.getVariable();
                     ((<undefinedtype>)$continuation).L$0 = body;
                     ((<undefinedtype>)$continuation).L$1 = bookList;
                     ((<undefinedtype>)$continuation).label = 1;
                     var10000 = var58.getInfoItem(body, analyzeRule, bookSource, analyzeUrl, baseUrl, var56, debugLog, (Continuation)$continuation);
                     if (var10000 == var35) {
                        return var35;
                     }
                     break label235;
                  }

                  var45 = Unit.INSTANCE;
               }

               var11 = null;
               reverse = 0;
               BookListRule var50;
               if (var6) {
                  var50 = (BookListRule)bookSource.getSearchRule();
               } else {
                  CharSequence var38 = (CharSequence)bookSource.getExploreRule().getBookList();
                  boolean var40 = false;
                  var43 = false;
                  var50 = var38 == null || StringsKt.isBlank(var38) ? (BookListRule)bookSource.getSearchRule() : (BookListRule)bookSource.getExploreRule();
               }

               BookListRule bookListRule = var50;
               String var41 = bookListRule.getBookList();
               String ruleList = var41 == null ? "" : var41;
               byte var47;
               String var53;
               if (StringsKt.startsWith$default(ruleList, "-", false, 2, (Object)null)) {
                  reverse = 1;
                  var47 = 1;
                  var48 = false;
                  var53 = ruleList.substring(var47);
                  Intrinsics.checkNotNullExpressionValue(var53, "(this as java.lang.String).substring(startIndex)");
                  ruleList = var53;
               }

               if (StringsKt.startsWith$default(ruleList, "+", false, 2, (Object)null)) {
                  var47 = 1;
                  var48 = false;
                  if (ruleList == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  }

                  var53 = ruleList.substring(var47);
                  Intrinsics.checkNotNullExpressionValue(var53, "(this as java.lang.String).substring(startIndex)");
                  ruleList = var53;
               }

               if (debugLog == null) {
                  var10000 = null;
               } else {
                  DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取书籍列表", false, 4, (Object)null);
                  var45 = Unit.INSTANCE;
               }

               List collections = analyzeRule.getElements(ruleList);
               JobKt.ensureActive(((Continuation)$continuation).getContext());
               if (collections.isEmpty()) {
                  CharSequence var42 = (CharSequence)bookSource.getBookUrlPattern();
                  var43 = false;
                  var48 = false;
                  if (var42 != null ? var42.length() == 0 : true) {
                     if (debugLog == null) {
                        var10000 = null;
                     } else {
                        DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "└列表为空,按详情页解析", false, 4, (Object)null);
                        var45 = Unit.INSTANCE;
                     }

                     var56 = variableBook.getVariable();
                     ((<undefinedtype>)$continuation).L$0 = body;
                     ((<undefinedtype>)$continuation).L$1 = bookList;
                     ((<undefinedtype>)$continuation).label = 2;
                     var10000 = this.getInfoItem(body, analyzeRule, bookSource, analyzeUrl, baseUrl, var56, debugLog, (Continuation)$continuation);
                     if (var10000 == var35) {
                        return var35;
                     }
                     break label236;
                  }
               }

               ruleName = AnalyzeRule.splitSourceRule$default(analyzeRule, bookListRule.getName(), false, 2, (Object)null);
               ruleBookUrl = AnalyzeRule.splitSourceRule$default(analyzeRule, bookListRule.getBookUrl(), false, 2, (Object)null);
               ruleAuthor = AnalyzeRule.splitSourceRule$default(analyzeRule, bookListRule.getAuthor(), false, 2, (Object)null);
               ruleCoverUrl = AnalyzeRule.splitSourceRule$default(analyzeRule, bookListRule.getCoverUrl(), false, 2, (Object)null);
               ruleIntro = AnalyzeRule.splitSourceRule$default(analyzeRule, bookListRule.getIntro(), false, 2, (Object)null);
               ruleKind = AnalyzeRule.splitSourceRule$default(analyzeRule, bookListRule.getKind(), false, 2, (Object)null);
               ruleLastChapter = AnalyzeRule.splitSourceRule$default(analyzeRule, bookListRule.getLastChapter(), false, 2, (Object)null);
               ruleWordCount = AnalyzeRule.splitSourceRule$default(analyzeRule, bookListRule.getWordCount(), false, 2, (Object)null);
               if (debugLog == null) {
                  var10000 = null;
               } else {
                  DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└列表大小:", Boxing.boxInt(collections.size())), false, 4, (Object)null);
                  var45 = Unit.INSTANCE;
               }

               var23 = collections.iterator();
               var24 = 0;
               break;
            case 1:
               var43 = false;
               bookList = (ArrayList)((<undefinedtype>)$continuation).L$1;
               body = (String)((<undefinedtype>)$continuation).L$0;
               ResultKt.throwOnFailure($result);
               var10000 = $result;
               break label235;
            case 2:
               bookList = (ArrayList)((<undefinedtype>)$continuation).L$1;
               body = (String)((<undefinedtype>)$continuation).L$0;
               ResultKt.throwOnFailure($result);
               var10000 = $result;
               break label236;
            case 3:
               var24 = ((<undefinedtype>)$continuation).I$1;
               reverse = ((<undefinedtype>)$continuation).I$0;
               var23 = (Iterator)((<undefinedtype>)$continuation).L$16;
               ruleWordCount = (List)((<undefinedtype>)$continuation).L$15;
               ruleLastChapter = (List)((<undefinedtype>)$continuation).L$14;
               ruleKind = (List)((<undefinedtype>)$continuation).L$13;
               ruleIntro = (List)((<undefinedtype>)$continuation).L$12;
               ruleCoverUrl = (List)((<undefinedtype>)$continuation).L$11;
               ruleAuthor = (List)((<undefinedtype>)$continuation).L$10;
               ruleBookUrl = (List)((<undefinedtype>)$continuation).L$9;
               ruleName = (List)((<undefinedtype>)$continuation).L$8;
               analyzeRule = (AnalyzeRule)((<undefinedtype>)$continuation).L$7;
               bookList = (ArrayList)((<undefinedtype>)$continuation).L$6;
               debugLog = (DebugLog)((<undefinedtype>)$continuation).L$5;
               variableBook = (SearchBook)((<undefinedtype>)$continuation).L$4;
               baseUrl = (String)((<undefinedtype>)$continuation).L$3;
               bookSource = (BookSource)((<undefinedtype>)$continuation).L$2;
               body = (String)((<undefinedtype>)$continuation).L$1;
               this = (BookList)((<undefinedtype>)$continuation).L$0;
               ResultKt.throwOnFailure($result);
               var27 = (SearchBook)$result;
               if (var27 == null) {
                  var10000 = null;
               } else {
                  var29 = false;
                  var30 = false;
                  var32 = false;
                  if (Intrinsics.areEqual(baseUrl, var27.getBookUrl())) {
                     var27.setInfoHtml(body);
                  }

                  Boxing.boxBoolean(bookList.add(var27));
               }
               break;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            while(var23.hasNext()) {
               int index = var24++;
               Object item = var23.next();
               String var28 = variableBook.getVariable();
               var29 = index == 0;
               boolean var10006 = var29;
               ((<undefinedtype>)$continuation).L$0 = this;
               ((<undefinedtype>)$continuation).L$1 = body;
               ((<undefinedtype>)$continuation).L$2 = bookSource;
               ((<undefinedtype>)$continuation).L$3 = baseUrl;
               ((<undefinedtype>)$continuation).L$4 = variableBook;
               ((<undefinedtype>)$continuation).L$5 = debugLog;
               ((<undefinedtype>)$continuation).L$6 = bookList;
               ((<undefinedtype>)$continuation).L$7 = analyzeRule;
               ((<undefinedtype>)$continuation).L$8 = ruleName;
               ((<undefinedtype>)$continuation).L$9 = ruleBookUrl;
               ((<undefinedtype>)$continuation).L$10 = ruleAuthor;
               ((<undefinedtype>)$continuation).L$11 = ruleCoverUrl;
               ((<undefinedtype>)$continuation).L$12 = ruleIntro;
               ((<undefinedtype>)$continuation).L$13 = ruleKind;
               ((<undefinedtype>)$continuation).L$14 = ruleLastChapter;
               ((<undefinedtype>)$continuation).L$15 = ruleWordCount;
               ((<undefinedtype>)$continuation).L$16 = var23;
               ((<undefinedtype>)$continuation).I$0 = reverse;
               ((<undefinedtype>)$continuation).I$1 = var24;
               ((<undefinedtype>)$continuation).label = 3;
               var10000 = this.getSearchItem(item, analyzeRule, bookSource, baseUrl, var28, var10006, ruleName, ruleBookUrl, ruleAuthor, ruleKind, ruleCoverUrl, ruleWordCount, ruleIntro, ruleLastChapter, debugLog, (Continuation)$continuation);
               if (var10000 == var35) {
                  return var35;
               }

               var27 = (SearchBook)var10000;
               if (var27 == null) {
                  var10000 = null;
               } else {
                  var29 = false;
                  var30 = false;
                  var32 = false;
                  if (Intrinsics.areEqual(baseUrl, var27.getBookUrl())) {
                     var27.setInfoHtml(body);
                  }

                  Boxing.boxBoolean(bookList.add(var27));
               }
            }

            if (reverse != 0) {
               CollectionsKt.reverse((List)bookList);
            }

            return bookList;
         }

         SearchBook var44 = (SearchBook)var10000;
         if (var44 == null) {
            var10000 = null;
         } else {
            var48 = false;
            boolean var51 = false;
            var55 = false;
            var44.setInfoHtml(body);
            Boxing.boxBoolean(bookList.add(var44));
         }

         return bookList;
      }

      SearchBook var54 = (SearchBook)var10000;
      if (var54 == null) {
         var10000 = null;
      } else {
         var52 = false;
         var55 = false;
         int var57 = false;
         var54.setInfoHtml(body);
         Boxing.boxBoolean(bookList.add(var54));
      }

      return bookList;
   }

   // $FF: synthetic method
   public static Object analyzeBookList$default(BookList var0, String var1, BookSource var2, AnalyzeUrl var3, String var4, SearchBook var5, boolean var6, DebugLog var7, Continuation var8, int var9, Object var10) throws Exception {
      if ((var9 & 32) != 0) {
         var6 = true;
      }

      if ((var9 & 64) != 0) {
         var7 = null;
      }

      return var0.analyzeBookList(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   private final Object getInfoItem(String body, AnalyzeRule analyzeRule, BookSource bookSource, AnalyzeUrl analyzeUrl, String baseUrl, String variable, DebugLog debugLog, Continuation<? super SearchBook> $completion) {
      Object $continuation;
      label29: {
         if (var8 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var8;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label29;
            }
         }

         $continuation = new ContinuationImpl(var8) {
            Object L$0;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookList.this.getInfoItem((String)null, (AnalyzeRule)null, (BookSource)null, (AnalyzeUrl)null, (String)null, (String)null, (DebugLog)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var14 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Book book;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         book = new Book((String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 0, 0L, (String)null, 0L, 0L, 0, 0, (String)null, 0, 0, 0L, (String)null, false, 0, 0, false, variable, (Book.ReadConfig)null, false, (String)null, -536870913, 1, (DefaultConstructorMarker)null);
         book.setBookUrl(analyzeUrl.getRuleUrl());
         book.setOrigin(bookSource.getBookSourceUrl());
         book.setOriginName(bookSource.getBookSourceName());
         book.setOriginOrder(bookSource.getCustomOrder());
         book.setType(bookSource.getBookSourceType());
         book.setUserNameSpace(analyzeRule.getUserNameSpace());
         analyzeRule.setRuleData((RuleDataInterface)book);
         BookInfo var10000 = BookInfo.INSTANCE;
         ((<undefinedtype>)$continuation).L$0 = book;
         ((<undefinedtype>)$continuation).label = 1;
         if (var10000.analyzeBookInfo(book, body, analyzeRule, bookSource, baseUrl, baseUrl, false, debugLog, (Continuation)$continuation) == var14) {
            return var14;
         }
         break;
      case 1:
         book = (Book)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      CharSequence var10 = (CharSequence)book.getName();
      boolean var11 = false;
      return !StringsKt.isBlank(var10) ? book.toSearchBook() : null;
   }

   // $FF: synthetic method
   static Object getInfoItem$default(BookList var0, String var1, AnalyzeRule var2, BookSource var3, AnalyzeUrl var4, String var5, String var6, DebugLog var7, Continuation var8, int var9, Object var10) {
      if ((var9 & 64) != 0) {
         var7 = null;
      }

      return var0.getInfoItem(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   private final Object getSearchItem(Object item, AnalyzeRule analyzeRule, BookSource bookSource, String baseUrl, String variable, boolean log, List<AnalyzeRule.SourceRule> ruleName, List<AnalyzeRule.SourceRule> ruleBookUrl, List<AnalyzeRule.SourceRule> ruleAuthor, List<AnalyzeRule.SourceRule> ruleKind, List<AnalyzeRule.SourceRule> ruleCoverUrl, List<AnalyzeRule.SourceRule> ruleWordCount, List<AnalyzeRule.SourceRule> ruleIntro, List<AnalyzeRule.SourceRule> ruleLastChapter, DebugLog debugLog, Continuation<? super SearchBook> $completion) {
      SearchBook searchBook = new SearchBook((String)null, (String)null, (String)null, 0, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 0L, variable, 0, 24575, (DefaultConstructorMarker)null);
      searchBook.setOrigin(bookSource.getBookSourceUrl());
      searchBook.setOriginName(bookSource.getBookSourceName());
      searchBook.setType(bookSource.getBookSourceType());
      searchBook.setOriginOrder(bookSource.getCustomOrder());
      searchBook.setUserNameSpace(analyzeRule.getUserNameSpace());
      analyzeRule.setRuleData((RuleDataInterface)searchBook);
      AnalyzeRule.setContent$default(analyzeRule, item, (String)null, 2, (Object)null);
      JobKt.ensureActive($completion.getContext());
      if (log && debugLog != null) {
         DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取书名", false, 4, (Object)null);
      }

      searchBook.setName(BookHelp.INSTANCE.formatBookName(AnalyzeRule.getString$default(analyzeRule, (List)ruleName, (Object)null, false, 6, (Object)null)));
      if (log && debugLog != null) {
         DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getName()), false, 4, (Object)null);
      }

      CharSequence var18 = (CharSequence)searchBook.getName();
      boolean var19 = false;
      if (var18.length() <= 0) {
         return null;
      } else {
         JobKt.ensureActive($completion.getContext());
         if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取作者", false, 4, (Object)null);
         }

         searchBook.setAuthor(BookHelp.INSTANCE.formatBookAuthor(AnalyzeRule.getString$default(analyzeRule, (List)ruleAuthor, (Object)null, false, 6, (Object)null)));
         if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getAuthor()), false, 4, (Object)null);
         }

         JobKt.ensureActive($completion.getContext());
         if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取分类", false, 4, (Object)null);
         }

         try {
            List var30 = AnalyzeRule.getStringList$default(analyzeRule, (List)ruleKind, (Object)null, false, 6, (Object)null);
            searchBook.setKind(var30 == null ? null : CollectionsKt.joinToString$default((Iterable)var30, (CharSequence)",", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null));
            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getKind()), false, 4, (Object)null);
            }
         } catch (Exception var29) {
            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", var29.getLocalizedMessage()), false, 4, (Object)null);
            }
         }

         JobKt.ensureActive($completion.getContext());
         if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取字数", false, 4, (Object)null);
         }

         try {
            searchBook.setWordCount(StringUtils.INSTANCE.wordCountFormat(AnalyzeRule.getString$default(analyzeRule, (List)ruleWordCount, (Object)null, false, 6, (Object)null)));
            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getWordCount()), false, 4, (Object)null);
            }
         } catch (Exception var28) {
            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", var28.getLocalizedMessage()), false, 4, (Object)null);
            }
         }

         JobKt.ensureActive($completion.getContext());
         if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取最新章节", false, 4, (Object)null);
         }

         try {
            searchBook.setLatestChapterTitle(AnalyzeRule.getString$default(analyzeRule, (List)ruleLastChapter, (Object)null, false, 6, (Object)null));
            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getLatestChapterTitle()), false, 4, (Object)null);
            }
         } catch (Exception var27) {
            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", var27.getLocalizedMessage()), false, 4, (Object)null);
            }
         }

         JobKt.ensureActive($completion.getContext());
         if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取简介", false, 4, (Object)null);
         }

         try {
            searchBook.setIntro(StringExtensionsKt.htmlFormat(AnalyzeRule.getString$default(analyzeRule, (List)ruleIntro, (Object)null, false, 6, (Object)null)));
            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getIntro()), false, 4, (Object)null);
            }
         } catch (Exception var26) {
            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", var26.getLocalizedMessage()), false, 4, (Object)null);
            }
         }

         JobKt.ensureActive($completion.getContext());
         if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取封面链接", false, 4, (Object)null);
         }

         try {
            String var31 = AnalyzeRule.getString$default(analyzeRule, (List)ruleCoverUrl, (Object)null, false, 6, (Object)null);
            var19 = false;
            boolean var20 = false;
            int var22 = false;
            CharSequence var23 = (CharSequence)var31;
            boolean var24 = false;
            if (var23.length() > 0) {
               searchBook.setCoverUrl(NetworkUtils.INSTANCE.getAbsoluteURL(baseUrl, var31));
            }

            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getCoverUrl()), false, 4, (Object)null);
            }
         } catch (Exception var25) {
            if (log && debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", var25.getLocalizedMessage()), false, 4, (Object)null);
            }
         }

         JobKt.ensureActive($completion.getContext());
         if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取详情页链接", false, 4, (Object)null);
         }

         searchBook.setBookUrl(AnalyzeRule.getString$default(analyzeRule, (List)ruleBookUrl, (Object)null, true, 2, (Object)null));
         var18 = (CharSequence)searchBook.getBookUrl();
         var19 = false;
         if (var18.length() == 0) {
            searchBook.setBookUrl(baseUrl);
         }

         if (log && debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", searchBook.getBookUrl()), false, 4, (Object)null);
         }

         return searchBook;
      }
   }

   // $FF: synthetic method
   static Object getSearchItem$default(BookList var0, Object var1, AnalyzeRule var2, BookSource var3, String var4, String var5, boolean var6, List var7, List var8, List var9, List var10, List var11, List var12, List var13, List var14, DebugLog var15, Continuation var16, int var17, Object var18) {
      if ((var17 & 16384) != 0) {
         var15 = null;
      }

      return var0.getSearchItem(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16);
   }

   // $FF: synthetic method
   public static final Object access$getSearchItem(BookList $this, Object item, AnalyzeRule analyzeRule, BookSource bookSource, String baseUrl, String variable, boolean log, List ruleName, List ruleBookUrl, List ruleAuthor, List ruleKind, List ruleCoverUrl, List ruleWordCount, List ruleIntro, List ruleLastChapter, DebugLog debugLog, Continuation $completion) {
      return $this.getSearchItem(item, analyzeRule, bookSource, baseUrl, variable, log, ruleName, ruleBookUrl, ruleAuthor, ruleKind, ruleCoverUrl, ruleWordCount, ruleIntro, ruleLastChapter, debugLog, $completion);
   }
}
