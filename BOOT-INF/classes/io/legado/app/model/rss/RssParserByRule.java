package io.legado.app.model.rss;

import io.legado.app.data.entities.BaseSource;
import io.legado.app.data.entities.RssArticle;
import io.legado.app.data.entities.RssSource;
import io.legado.app.exception.NoStackTraceException;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.AnalyzeRule;
import io.legado.app.model.analyzeRule.RuleData;
import io.legado.app.model.analyzeRule.RuleDataInterface;
import io.legado.app.utils.NetworkUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0098\u0001\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0010\u0010\r\u001a\f\u0012\b\u0012\u00060\u000fR\u00020\t0\u000e2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u000fR\u00020\t0\u000e2\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u00060\u000fR\u00020\t0\u000e2\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u000fR\u00020\t0\u000e2\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u00060\u000fR\u00020\t0\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002JN\u0010\u0016\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00172\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015¨\u0006 "},
   d2 = {"Lio/legado/app/model/rss/RssParserByRule;", "", "()V", "getItem", "Lio/legado/app/data/entities/RssArticle;", "sourceUrl", "", "item", "analyzeRule", "Lio/legado/app/model/analyzeRule/AnalyzeRule;", "variable", "log", "", "ruleTitle", "", "Lio/legado/app/model/analyzeRule/AnalyzeRule$SourceRule;", "rulePubDate", "ruleDescription", "ruleImage", "ruleLink", "debugLog", "Lio/legado/app/model/DebugLog;", "parseXML", "Lkotlin/Pair;", "", "sortName", "sortUrl", "body", "rssSource", "Lio/legado/app/data/entities/RssSource;", "ruleData", "Lio/legado/app/model/analyzeRule/RuleData;", "reader-pro"}
)
public final class RssParserByRule {
   @NotNull
   public static final RssParserByRule INSTANCE = new RssParserByRule();

   private RssParserByRule() {
   }

   @NotNull
   public final Pair<List<RssArticle>, String> parseXML(@NotNull String sortName, @NotNull String sortUrl, @Nullable String body, @NotNull RssSource rssSource, @NotNull RuleData ruleData, @Nullable DebugLog debugLog) throws Exception {
      Intrinsics.checkNotNullParameter(sortName, "sortName");
      Intrinsics.checkNotNullParameter(sortUrl, "sortUrl");
      Intrinsics.checkNotNullParameter(rssSource, "rssSource");
      Intrinsics.checkNotNullParameter(ruleData, "ruleData");
      String sourceUrl = rssSource.getSourceUrl();
      String nextUrl = null;
      CharSequence var9 = (CharSequence)body;
      boolean var10 = false;
      boolean var11 = false;
      if (var9 == null || StringsKt.isBlank(var9)) {
         throw new NoStackTraceException(Intrinsics.stringPlus("error_get_web_content: ", rssSource.getSourceUrl()));
      } else {
         String ruleArticles = rssSource.getRuleArticles();
         CharSequence var31 = (CharSequence)ruleArticles;
         var11 = false;
         boolean reverse = false;
         if (var31 == null || StringsKt.isBlank(var31)) {
            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, "⇒列表规则为空, 使用默认规则解析", false, 4, (Object)null);
            }

            return RssParserDefault.INSTANCE.parseXML(sortName, body, sourceUrl, debugLog);
         } else {
            var11 = false;
            List articleList = (List)(new ArrayList());
            AnalyzeRule analyzeRule = new AnalyzeRule((RuleDataInterface)ruleData, (BaseSource)rssSource, debugLog);
            AnalyzeRule.setContent$default(analyzeRule, body, (String)null, 2, (Object)null).setBaseUrl(sortUrl);
            analyzeRule.setRedirectUrl(sortUrl);
            reverse = false;
            boolean var15;
            String var10000;
            if (StringsKt.startsWith$default(ruleArticles, "-", false, 2, (Object)null)) {
               reverse = true;
               byte var14 = 1;
               var15 = false;
               if (ruleArticles == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               }

               var10000 = ruleArticles.substring(var14);
               Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
               ruleArticles = var10000;
            }

            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, "┌获取列表", false, 4, (Object)null);
            }

            List collections = analyzeRule.getElements(ruleArticles);
            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, Intrinsics.stringPlus("└列表大小:", collections.size()), false, 4, (Object)null);
            }

            CharSequence var34 = (CharSequence)rssSource.getRuleNextPage();
            var15 = false;
            boolean var16 = false;
            if (var34 != null && var34.length() != 0) {
               if (debugLog != null) {
                  DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, "┌获取下一页链接", false, 4, (Object)null);
               }

               var10000 = rssSource.getRuleNextPage();
               Intrinsics.checkNotNull(var10000);
               String var35 = var10000;
               Locale var37 = Locale.getDefault();
               Intrinsics.checkNotNullExpressionValue(var37, "getDefault()");
               var16 = false;
               if (var35 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               }

               var10000 = var35.toUpperCase(var37);
               Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).toUpperCase(locale)");
               if (Intrinsics.areEqual(var10000, "PAGE")) {
                  nextUrl = sortUrl;
               } else {
                  nextUrl = AnalyzeRule.getString$default(analyzeRule, (String)rssSource.getRuleNextPage(), (Object)null, false, 6, (Object)null);
                  var34 = (CharSequence)nextUrl;
                  var15 = false;
                  if (var34.length() > 0) {
                     nextUrl = NetworkUtils.INSTANCE.getAbsoluteURL(sortUrl, nextUrl);
                  }
               }

               if (debugLog != null) {
                  DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, Intrinsics.stringPlus("└", nextUrl), false, 4, (Object)null);
               }
            }

            List ruleTitle = AnalyzeRule.splitSourceRule$default(analyzeRule, rssSource.getRuleTitle(), false, 2, (Object)null);
            List rulePubDate = AnalyzeRule.splitSourceRule$default(analyzeRule, rssSource.getRulePubDate(), false, 2, (Object)null);
            List ruleDescription = AnalyzeRule.splitSourceRule$default(analyzeRule, rssSource.getRuleDescription(), false, 2, (Object)null);
            List ruleImage = AnalyzeRule.splitSourceRule$default(analyzeRule, rssSource.getRuleImage(), false, 2, (Object)null);
            List ruleLink = AnalyzeRule.splitSourceRule$default(analyzeRule, rssSource.getRuleLink(), false, 2, (Object)null);
            String variable = ruleData.getVariable();
            Iterator var20 = collections.iterator();
            int var21 = 0;

            while(var20.hasNext()) {
               int index = var21++;
               Object item = var20.next();
               RssArticle var24 = this.getItem(sourceUrl, item, analyzeRule, variable, index == 0, ruleTitle, rulePubDate, ruleDescription, ruleImage, ruleLink, debugLog);
               if (var24 != null) {
                  boolean var26 = false;
                  boolean var27 = false;
                  int var29 = false;
                  var24.setSort(sortName);
                  var24.setOrigin(sourceUrl);
                  articleList.add(var24);
               }
            }

            if (reverse) {
               CollectionsKt.reverse(articleList);
            }

            return new Pair(articleList, nextUrl);
         }
      }
   }

   private final RssArticle getItem(String sourceUrl, Object item, AnalyzeRule analyzeRule, String variable, boolean log, List<AnalyzeRule.SourceRule> ruleTitle, List<AnalyzeRule.SourceRule> rulePubDate, List<AnalyzeRule.SourceRule> ruleDescription, List<AnalyzeRule.SourceRule> ruleImage, List<AnalyzeRule.SourceRule> ruleLink, DebugLog debugLog) {
      RssArticle rssArticle = new RssArticle((String)null, (String)null, (String)null, 0L, (String)null, (String)null, (String)null, (String)null, (String)null, false, variable, 1023, (DefaultConstructorMarker)null);
      analyzeRule.setRuleData((RuleDataInterface)rssArticle);
      AnalyzeRule.setContent$default(analyzeRule, item, (String)null, 2, (Object)null);
      if (debugLog != null) {
         debugLog.log(sourceUrl, "┌获取标题", log);
      }

      rssArticle.setTitle(AnalyzeRule.getString$default(analyzeRule, (List)ruleTitle, (Object)null, false, 6, (Object)null));
      if (debugLog != null) {
         debugLog.log(sourceUrl, Intrinsics.stringPlus("└", rssArticle.getTitle()), log);
      }

      if (debugLog != null) {
         debugLog.log(sourceUrl, "┌获取时间", log);
      }

      rssArticle.setPubDate(AnalyzeRule.getString$default(analyzeRule, (List)rulePubDate, (Object)null, false, 6, (Object)null));
      if (debugLog != null) {
         debugLog.log(sourceUrl, Intrinsics.stringPlus("└", rssArticle.getPubDate()), log);
      }

      if (debugLog != null) {
         debugLog.log(sourceUrl, "┌获取描述", log);
      }

      Collection var13 = (Collection)ruleDescription;
      boolean var14 = false;
      boolean var15 = false;
      if (var13 == null || var13.isEmpty()) {
         rssArticle.setDescription((String)null);
         if (debugLog != null) {
            debugLog.log(sourceUrl, "└描述规则为空，将会解析内容页", log);
         }
      } else {
         rssArticle.setDescription(AnalyzeRule.getString$default(analyzeRule, (List)ruleDescription, (Object)null, false, 6, (Object)null));
         if (debugLog != null) {
            debugLog.log(sourceUrl, Intrinsics.stringPlus("└", rssArticle.getDescription()), log);
         }
      }

      if (debugLog != null) {
         debugLog.log(sourceUrl, "┌获取图片url", log);
      }

      rssArticle.setImage(AnalyzeRule.getString$default(analyzeRule, (List)ruleImage, (Object)null, true, 2, (Object)null));
      if (debugLog != null) {
         debugLog.log(sourceUrl, Intrinsics.stringPlus("└", rssArticle.getImage()), log);
      }

      if (debugLog != null) {
         debugLog.log(sourceUrl, "┌获取文章链接", log);
      }

      rssArticle.setLink(NetworkUtils.INSTANCE.getAbsoluteURL(sourceUrl, AnalyzeRule.getString$default(analyzeRule, (List)ruleLink, (Object)null, false, 6, (Object)null)));
      if (debugLog != null) {
         debugLog.log(sourceUrl, Intrinsics.stringPlus("└", rssArticle.getLink()), log);
      }

      return StringsKt.isBlank((CharSequence)rssArticle.getTitle()) ? null : rssArticle;
   }
}
