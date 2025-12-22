package io.legado.app.model.rss;

import io.legado.app.data.entities.BaseSource;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.data.entities.RssArticle;
import io.legado.app.data.entities.RssSource;
import io.legado.app.help.http.StrResponse;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.AnalyzeRule;
import io.legado.app.model.analyzeRule.AnalyzeUrl;
import io.legado.app.model.analyzeRule.RuleData;
import io.legado.app.model.analyzeRule.RuleDataInterface;
import io.legado.app.utils.NetworkUtils;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JO\u0010\u0003\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J3\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"},
   d2 = {"Lio/legado/app/model/rss/Rss;", "", "()V", "getArticles", "Lkotlin/Pair;", "", "Lio/legado/app/data/entities/RssArticle;", "", "sortName", "sortUrl", "rssSource", "Lio/legado/app/data/entities/RssSource;", "page", "", "debugLog", "Lio/legado/app/model/DebugLog;", "(Ljava/lang/String;Ljava/lang/String;Lio/legado/app/data/entities/RssSource;ILio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getContent", "rssArticle", "ruleContent", "(Lio/legado/app/data/entities/RssArticle;Ljava/lang/String;Lio/legado/app/data/entities/RssSource;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"}
)
public final class Rss {
   @NotNull
   public static final Rss INSTANCE = new Rss();

   private Rss() {
   }

   @Nullable
   public final Object getArticles(@NotNull String sortName, @NotNull String sortUrl, @NotNull RssSource rssSource, int page, @Nullable DebugLog debugLog, @NotNull Continuation<? super Pair<? extends List<RssArticle>, String>> $completion) {
      Object $continuation;
      label20: {
         if (var6 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var6;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         $continuation = new ContinuationImpl(var6) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return Rss.this.getArticles((String)null, (String)null, (RssSource)null, 0, (DebugLog)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      RuleData ruleData;
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         ruleData = new RuleData();
         AnalyzeUrl analyzeUrl = new AnalyzeUrl(sortUrl, (String)null, Boxing.boxInt(page), (String)null, (Integer)null, (String)null, (BaseSource)rssSource, (RuleDataInterface)ruleData, (BookChapter)null, (Map)BaseSource.DefaultImpls.getHeaderMap$default((BaseSource)rssSource, false, 1, (Object)null), debugLog, 314, (DefaultConstructorMarker)null);
         ((<undefinedtype>)$continuation).L$0 = sortName;
         ((<undefinedtype>)$continuation).L$1 = sortUrl;
         ((<undefinedtype>)$continuation).L$2 = rssSource;
         ((<undefinedtype>)$continuation).L$3 = debugLog;
         ((<undefinedtype>)$continuation).L$4 = ruleData;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = AnalyzeUrl.getStrResponseAwait$default(analyzeUrl, (String)null, (String)null, false, (Continuation)$continuation, 7, (Object)null);
         if (var10000 == var12) {
            return var12;
         }
         break;
      case 1:
         ruleData = (RuleData)((<undefinedtype>)$continuation).L$4;
         debugLog = (DebugLog)((<undefinedtype>)$continuation).L$3;
         rssSource = (RssSource)((<undefinedtype>)$continuation).L$2;
         sortUrl = (String)((<undefinedtype>)$continuation).L$1;
         sortName = (String)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      String body = ((StrResponse)var10000).getBody();
      return RssParserByRule.INSTANCE.parseXML(sortName, sortUrl, body, rssSource, ruleData, debugLog);
   }

   @Nullable
   public final Object getContent(@NotNull RssArticle rssArticle, @NotNull String ruleContent, @NotNull RssSource rssSource, @Nullable DebugLog debugLog, @NotNull Continuation<? super String> $completion) {
      Object $continuation;
      label20: {
         if (var5 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var5;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         $continuation = new ContinuationImpl(var5) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return Rss.this.getContent((RssArticle)null, (String)null, (RssSource)null, (DebugLog)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var11 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         AnalyzeUrl analyzeUrl = new AnalyzeUrl(rssArticle.getLink(), (String)null, (Integer)null, (String)null, (Integer)null, rssArticle.getOrigin(), (BaseSource)rssSource, (RuleDataInterface)rssArticle, (BookChapter)null, (Map)BaseSource.DefaultImpls.getHeaderMap$default((BaseSource)rssSource, false, 1, (Object)null), debugLog, 286, (DefaultConstructorMarker)null);
         ((<undefinedtype>)$continuation).L$0 = rssArticle;
         ((<undefinedtype>)$continuation).L$1 = ruleContent;
         ((<undefinedtype>)$continuation).L$2 = rssSource;
         ((<undefinedtype>)$continuation).L$3 = debugLog;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = AnalyzeUrl.getStrResponseAwait$default(analyzeUrl, (String)null, (String)null, false, (Continuation)$continuation, 7, (Object)null);
         if (var10000 == var11) {
            return var11;
         }
         break;
      case 1:
         debugLog = (DebugLog)((<undefinedtype>)$continuation).L$3;
         rssSource = (RssSource)((<undefinedtype>)$continuation).L$2;
         ruleContent = (String)((<undefinedtype>)$continuation).L$1;
         rssArticle = (RssArticle)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      String body = ((StrResponse)var10000).getBody();
      AnalyzeRule analyzeRule = new AnalyzeRule((RuleDataInterface)rssArticle, (BaseSource)rssSource, debugLog);
      AnalyzeRule.setContent$default(analyzeRule, body, (String)null, 2, (Object)null).setBaseUrl(NetworkUtils.INSTANCE.getAbsoluteURL(rssArticle.getOrigin(), rssArticle.getLink()));
      return AnalyzeRule.getString$default(analyzeRule, (String)ruleContent, (Object)null, false, 6, (Object)null);
   }
}
