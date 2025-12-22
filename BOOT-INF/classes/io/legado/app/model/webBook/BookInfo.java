package io.legado.app.model.webBook;

import io.legado.app.data.entities.BaseSource;
import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookSource;
import io.legado.app.data.entities.rule.BookInfoRule;
import io.legado.app.help.BookHelp;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.AnalyzeRule;
import io.legado.app.model.analyzeRule.RuleDataInterface;
import io.legado.app.utils.NetworkUtils;
import io.legado.app.utils.StringExtensionsKt;
import io.legado.app.utils.StringUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.JobKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JO\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011JW\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"},
   d2 = {"Lio/legado/app/model/webBook/BookInfo;", "", "()V", "analyzeBookInfo", "", "book", "Lio/legado/app/data/entities/Book;", "body", "", "bookSource", "Lio/legado/app/data/entities/BookSource;", "baseUrl", "redirectUrl", "canReName", "", "debugLog", "Lio/legado/app/model/DebugLog;", "(Lio/legado/app/data/entities/Book;Ljava/lang/String;Lio/legado/app/data/entities/BookSource;Ljava/lang/String;Ljava/lang/String;ZLio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "analyzeRule", "Lio/legado/app/model/analyzeRule/AnalyzeRule;", "(Lio/legado/app/data/entities/Book;Ljava/lang/String;Lio/legado/app/model/analyzeRule/AnalyzeRule;Lio/legado/app/data/entities/BookSource;Ljava/lang/String;Ljava/lang/String;ZLio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"}
)
public final class BookInfo {
   @NotNull
   public static final BookInfo INSTANCE = new BookInfo();

   private BookInfo() {
   }

   @Nullable
   public final Object analyzeBookInfo(@NotNull Book book, @Nullable String body, @NotNull BookSource bookSource, @NotNull String baseUrl, @NotNull String redirectUrl, boolean canReName, @Nullable DebugLog debugLog, @NotNull Continuation<? super Unit> $completion) throws Exception {
      if (body == null) {
         throw new Exception(Intrinsics.stringPlus("error_get_web_content: ", baseUrl));
      } else {
         if (debugLog != null) {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("≡获取成功:", baseUrl), false, 4, (Object)null);
         }

         AnalyzeRule analyzeRule = new AnalyzeRule((RuleDataInterface)book, (BaseSource)bookSource, debugLog);
         AnalyzeRule.setContent$default(analyzeRule, body, (String)null, 2, (Object)null).setBaseUrl(baseUrl);
         analyzeRule.setRedirectUrl(redirectUrl);
         Object var10000 = this.analyzeBookInfo(book, body, analyzeRule, bookSource, baseUrl, redirectUrl, canReName, debugLog, $completion);
         return var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var10000 : Unit.INSTANCE;
      }
   }

   // $FF: synthetic method
   public static Object analyzeBookInfo$default(BookInfo var0, Book var1, String var2, BookSource var3, String var4, String var5, boolean var6, DebugLog var7, Continuation var8, int var9, Object var10) throws Exception {
      if ((var9 & 64) != 0) {
         var7 = null;
      }

      return var0.analyzeBookInfo(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   @Nullable
   public final Object analyzeBookInfo(@NotNull Book book, @Nullable String body, @NotNull AnalyzeRule analyzeRule, @NotNull BookSource bookSource, @NotNull String baseUrl, @NotNull String redirectUrl, boolean canReName, @Nullable DebugLog debugLog, @NotNull Continuation<? super Unit> $completion) throws Exception {
      if (body == null) {
         throw new Exception(Intrinsics.stringPlus("error_get_web_content: ", baseUrl));
      } else {
         BookInfoRule infoRule = bookSource.getBookInfoRule();
         String var11 = infoRule.getInit();
         boolean var13;
         boolean var14;
         Unit var10000;
         boolean var16;
         CharSequence var17;
         boolean var18;
         if (var11 == null) {
            var10000 = null;
         } else {
            var13 = false;
            var14 = false;
            var16 = false;
            var17 = (CharSequence)var11;
            var18 = false;
            if (var17.length() > 0) {
               JobKt.ensureActive($completion.getContext());
               if (debugLog == null) {
                  var10000 = null;
               } else {
                  DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "≡执行详情页初始化规则", false, 4, (Object)null);
                  var10000 = Unit.INSTANCE;
               }

               AnalyzeRule.setContent$default(analyzeRule, analyzeRule.getElement(var11), (String)null, 2, (Object)null);
            }

            var10000 = Unit.INSTANCE;
         }

         CharSequence var12;
         boolean var30;
         label307: {
            if (canReName) {
               var12 = (CharSequence)infoRule.getCanReName();
               var13 = false;
               var14 = false;
               if (var12 != null && !StringsKt.isBlank(var12)) {
                  var30 = true;
                  break label307;
               }
            }

            var30 = false;
         }

         boolean mCanReName = var30;
         JobKt.ensureActive($completion.getContext());
         if (debugLog == null) {
            var10000 = null;
         } else {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取书名", false, 4, (Object)null);
            var10000 = Unit.INSTANCE;
         }

         String var27 = BookHelp.INSTANCE.formatBookName(AnalyzeRule.getString$default(analyzeRule, (String)infoRule.getName(), (Object)null, false, 6, (Object)null));
         var13 = false;
         var14 = false;
         var16 = false;
         var17 = (CharSequence)var27;
         var18 = false;
         if (var17.length() > 0) {
            label295: {
               if (!mCanReName) {
                  var17 = (CharSequence)book.getName();
                  var18 = false;
                  if (var17.length() != 0) {
                     break label295;
                  }
               }

               book.setName(var27);
            }
         }

         if (debugLog == null) {
            var10000 = null;
         } else {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", var27), false, 4, (Object)null);
            var10000 = Unit.INSTANCE;
         }

         JobKt.ensureActive($completion.getContext());
         if (debugLog == null) {
            var10000 = null;
         } else {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取作者", false, 4, (Object)null);
            var10000 = Unit.INSTANCE;
         }

         var27 = BookHelp.INSTANCE.formatBookAuthor(AnalyzeRule.getString$default(analyzeRule, (String)infoRule.getAuthor(), (Object)null, false, 6, (Object)null));
         var13 = false;
         var14 = false;
         var16 = false;
         var17 = (CharSequence)var27;
         var18 = false;
         if (var17.length() > 0) {
            label287: {
               if (!mCanReName) {
                  var17 = (CharSequence)book.getAuthor();
                  var18 = false;
                  if (var17.length() != 0) {
                     break label287;
                  }
               }

               book.setAuthor(var27);
            }
         }

         if (debugLog == null) {
            var10000 = null;
         } else {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", var27), false, 4, (Object)null);
            var10000 = Unit.INSTANCE;
         }

         JobKt.ensureActive($completion.getContext());
         if (debugLog == null) {
            var10000 = null;
         } else {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取分类", false, 4, (Object)null);
            var10000 = Unit.INSTANCE;
         }

         try {
            List var28 = AnalyzeRule.getStringList$default(analyzeRule, (String)infoRule.getKind(), (Object)null, false, 6, (Object)null);
            if (var28 == null) {
               var10000 = null;
            } else {
               String var29 = CollectionsKt.joinToString$default((Iterable)var28, (CharSequence)",", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
               if (var29 == null) {
                  var10000 = null;
               } else {
                  boolean var15 = false;
                  var16 = false;
                  var18 = false;
                  CharSequence var19 = (CharSequence)var29;
                  boolean var20 = false;
                  if (var19.length() > 0) {
                     book.setKind(var29);
                  }

                  var10000 = Unit.INSTANCE;
               }
            }

            if (debugLog == null) {
               var10000 = null;
            } else {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", book.getKind()), false, 4, (Object)null);
               var10000 = Unit.INSTANCE;
            }
         } catch (Exception var25) {
            if (debugLog == null) {
               var10000 = null;
            } else {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", var25.getLocalizedMessage()), false, 4, (Object)null);
               var10000 = Unit.INSTANCE;
            }
         }

         JobKt.ensureActive($completion.getContext());
         if (debugLog == null) {
            var10000 = null;
         } else {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取字数", false, 4, (Object)null);
            var10000 = Unit.INSTANCE;
         }

         try {
            var27 = StringUtils.INSTANCE.wordCountFormat(AnalyzeRule.getString$default(analyzeRule, (String)infoRule.getWordCount(), (Object)null, false, 6, (Object)null));
            var13 = false;
            var14 = false;
            var16 = false;
            var17 = (CharSequence)var27;
            var18 = false;
            if (var17.length() > 0) {
               book.setWordCount(var27);
            }

            if (debugLog == null) {
               var10000 = null;
            } else {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", book.getWordCount()), false, 4, (Object)null);
               var10000 = Unit.INSTANCE;
            }
         } catch (Exception var24) {
            if (debugLog == null) {
               var10000 = null;
            } else {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", var24.getLocalizedMessage()), false, 4, (Object)null);
               var10000 = Unit.INSTANCE;
            }
         }

         JobKt.ensureActive($completion.getContext());
         if (debugLog == null) {
            var10000 = null;
         } else {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取最新章节", false, 4, (Object)null);
            var10000 = Unit.INSTANCE;
         }

         try {
            var27 = AnalyzeRule.getString$default(analyzeRule, (String)infoRule.getLastChapter(), (Object)null, false, 6, (Object)null);
            var13 = false;
            var14 = false;
            var16 = false;
            var17 = (CharSequence)var27;
            var18 = false;
            if (var17.length() > 0) {
               book.setLatestChapterTitle(var27);
            }

            if (debugLog == null) {
               var10000 = null;
            } else {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", book.getLatestChapterTitle()), false, 4, (Object)null);
               var10000 = Unit.INSTANCE;
            }
         } catch (Exception var23) {
            if (debugLog == null) {
               var10000 = null;
            } else {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", var23.getLocalizedMessage()), false, 4, (Object)null);
               var10000 = Unit.INSTANCE;
            }
         }

         JobKt.ensureActive($completion.getContext());
         if (debugLog == null) {
            var10000 = null;
         } else {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取简介", false, 4, (Object)null);
            var10000 = Unit.INSTANCE;
         }

         try {
            var27 = AnalyzeRule.getString$default(analyzeRule, (String)infoRule.getIntro(), (Object)null, false, 6, (Object)null);
            var13 = false;
            var14 = false;
            var16 = false;
            var17 = (CharSequence)var27;
            var18 = false;
            if (var17.length() > 0) {
               book.setIntro(StringExtensionsKt.htmlFormat(var27));
            }

            if (debugLog == null) {
               var10000 = null;
            } else {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", book.getIntro()), false, 4, (Object)null);
               var10000 = Unit.INSTANCE;
            }
         } catch (Exception var22) {
            if (debugLog == null) {
               var10000 = null;
            } else {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", var22.getLocalizedMessage()), false, 4, (Object)null);
               var10000 = Unit.INSTANCE;
            }
         }

         JobKt.ensureActive($completion.getContext());
         if (debugLog == null) {
            var10000 = null;
         } else {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取封面链接", false, 4, (Object)null);
            var10000 = Unit.INSTANCE;
         }

         try {
            var27 = AnalyzeRule.getString$default(analyzeRule, (String)infoRule.getCoverUrl(), (Object)null, false, 6, (Object)null);
            var13 = false;
            var14 = false;
            var16 = false;
            var17 = (CharSequence)var27;
            var18 = false;
            if (var17.length() > 0) {
               book.setCoverUrl(NetworkUtils.INSTANCE.getAbsoluteURL(redirectUrl, var27));
            }

            if (debugLog == null) {
               var10000 = null;
            } else {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", book.getCoverUrl()), false, 4, (Object)null);
               var10000 = Unit.INSTANCE;
            }
         } catch (Exception var21) {
            if (debugLog == null) {
               var10000 = null;
            } else {
               DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", var21.getLocalizedMessage()), false, 4, (Object)null);
               var10000 = Unit.INSTANCE;
            }
         }

         JobKt.ensureActive($completion.getContext());
         if (debugLog == null) {
            var10000 = null;
         } else {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), "┌获取目录链接", false, 4, (Object)null);
            var10000 = Unit.INSTANCE;
         }

         book.setTocUrl(AnalyzeRule.getString$default(analyzeRule, (String)infoRule.getTocUrl(), (Object)null, true, 2, (Object)null));
         var12 = (CharSequence)book.getTocUrl();
         var13 = false;
         if (var12.length() == 0) {
            book.setTocUrl(baseUrl);
         }

         if (Intrinsics.areEqual(book.getTocUrl(), baseUrl)) {
            book.setTocHtml(body);
         }

         if (debugLog == null) {
            var10000 = null;
         } else {
            DebugLog.DefaultImpls.log$default(debugLog, bookSource.getBookSourceUrl(), Intrinsics.stringPlus("└", book.getTocUrl()), false, 4, (Object)null);
            var10000 = Unit.INSTANCE;
         }

         return var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var10000 : Unit.INSTANCE;
      }
   }

   // $FF: synthetic method
   public static Object analyzeBookInfo$default(BookInfo var0, Book var1, String var2, AnalyzeRule var3, BookSource var4, String var5, String var6, boolean var7, DebugLog var8, Continuation var9, int var10, Object var11) throws Exception {
      if ((var10 & 128) != 0) {
         var8 = null;
      }

      return var0.analyzeBookInfo(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }
}
