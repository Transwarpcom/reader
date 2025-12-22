package io.legado.app.model.rss;

import io.legado.app.data.entities.RssArticle;
import io.legado.app.model.DebugLog;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J<\u0010\u0012\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00132\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001b"},
   d2 = {"Lio/legado/app/model/rss/RssParserDefault;", "", "()V", "RSS_ITEM", "", "RSS_ITEM_CATEGORY", "RSS_ITEM_CONTENT", "RSS_ITEM_DESCRIPTION", "RSS_ITEM_ENCLOSURE", "RSS_ITEM_LINK", "RSS_ITEM_PUB_DATE", "RSS_ITEM_THUMBNAIL", "RSS_ITEM_TIME", "RSS_ITEM_TITLE", "RSS_ITEM_TYPE", "RSS_ITEM_URL", "getImageUrl", "input", "parseXML", "Lkotlin/Pair;", "", "Lio/legado/app/data/entities/RssArticle;", "sortName", "xml", "sourceUrl", "debugLog", "Lio/legado/app/model/DebugLog;", "reader-pro"}
)
public final class RssParserDefault {
   @NotNull
   public static final RssParserDefault INSTANCE = new RssParserDefault();
   @NotNull
   private static final String RSS_ITEM = "item";
   @NotNull
   private static final String RSS_ITEM_TITLE = "title";
   @NotNull
   private static final String RSS_ITEM_LINK = "link";
   @NotNull
   private static final String RSS_ITEM_CATEGORY = "category";
   @NotNull
   private static final String RSS_ITEM_THUMBNAIL = "media:thumbnail";
   @NotNull
   private static final String RSS_ITEM_ENCLOSURE = "enclosure";
   @NotNull
   private static final String RSS_ITEM_DESCRIPTION = "description";
   @NotNull
   private static final String RSS_ITEM_CONTENT = "content:encoded";
   @NotNull
   private static final String RSS_ITEM_PUB_DATE = "pubDate";
   @NotNull
   private static final String RSS_ITEM_TIME = "time";
   @NotNull
   private static final String RSS_ITEM_URL = "url";
   @NotNull
   private static final String RSS_ITEM_TYPE = "type";

   private RssParserDefault() {
   }

   @NotNull
   public final Pair<List<RssArticle>, String> parseXML(@NotNull String sortName, @NotNull String xml, @NotNull String sourceUrl, @Nullable DebugLog debugLog) throws XmlPullParserException, IOException {
      Intrinsics.checkNotNullParameter(sortName, "sortName");
      Intrinsics.checkNotNullParameter(xml, "xml");
      Intrinsics.checkNotNullParameter(sourceUrl, "sourceUrl");
      boolean var6 = false;
      List articleList = (List)(new ArrayList());
      RssArticle currentArticle = new RssArticle((String)null, (String)null, (String)null, 0L, (String)null, (String)null, (String)null, (String)null, (String)null, false, (String)null, 2047, (DefaultConstructorMarker)null);
      XmlPullParserFactory factory = XmlPullParserFactory.newInstance("\n        org.kxml2.io.KXmlParser\n        org.kxml2.io.KXmlSerializer\n               ", Thread.currentThread().getContextClassLoader().getClass());
      factory.setNamespaceAware(false);
      XmlPullParser xmlPullParser = factory.newPullParser();
      xmlPullParser.setInput((Reader)(new StringReader(xml)));
      boolean insideItem = false;
      int eventType = xmlPullParser.getEventType();

      while(true) {
         boolean var13;
         while(eventType != 1) {
            if (eventType == 2) {
               if (StringsKt.equals(xmlPullParser.getName(), "item", true)) {
                  insideItem = true;
               } else {
                  String content;
                  boolean var12;
                  if (StringsKt.equals(xmlPullParser.getName(), "title", true)) {
                     if (insideItem) {
                        content = xmlPullParser.nextText();
                        Intrinsics.checkNotNullExpressionValue(content, "xmlPullParser.nextText()");
                        var12 = false;
                        currentArticle.setTitle(StringsKt.trim((CharSequence)content).toString());
                     }
                  } else if (StringsKt.equals(xmlPullParser.getName(), "link", true)) {
                     if (insideItem) {
                        content = xmlPullParser.nextText();
                        Intrinsics.checkNotNullExpressionValue(content, "xmlPullParser.nextText()");
                        var12 = false;
                        currentArticle.setLink(StringsKt.trim((CharSequence)content).toString());
                     }
                  } else if (StringsKt.equals(xmlPullParser.getName(), "media:thumbnail", true)) {
                     if (insideItem) {
                        currentArticle.setImage(xmlPullParser.getAttributeValue((String)null, "url"));
                     }
                  } else if (StringsKt.equals(xmlPullParser.getName(), "enclosure", true)) {
                     if (insideItem) {
                        content = xmlPullParser.getAttributeValue((String)null, "type");
                        if (content != null && StringsKt.contains$default((CharSequence)content, (CharSequence)"image/", false, 2, (Object)null)) {
                           currentArticle.setImage(xmlPullParser.getAttributeValue((String)null, "url"));
                        }
                     }
                  } else if (StringsKt.equals(xmlPullParser.getName(), "description", true)) {
                     if (insideItem) {
                        content = xmlPullParser.nextText();
                        Intrinsics.checkNotNullExpressionValue(content, "description");
                        var13 = false;
                        currentArticle.setDescription(StringsKt.trim((CharSequence)content).toString());
                        if (currentArticle.getImage() == null) {
                           currentArticle.setImage(this.getImageUrl(content));
                        }
                     }
                  } else {
                     String var19;
                     if (StringsKt.equals(xmlPullParser.getName(), "content:encoded", true)) {
                        if (insideItem) {
                           var19 = xmlPullParser.nextText();
                           Intrinsics.checkNotNullExpressionValue(var19, "xmlPullParser.nextText()");
                           var13 = false;
                           content = StringsKt.trim((CharSequence)var19).toString();
                           currentArticle.setContent(content);
                           if (currentArticle.getImage() == null) {
                              currentArticle.setImage(this.getImageUrl(content));
                           }
                        }
                     } else if (StringsKt.equals(xmlPullParser.getName(), "pubDate", true)) {
                        if (insideItem) {
                           int nextTokenType = xmlPullParser.next();
                           if (nextTokenType == 4) {
                              var19 = xmlPullParser.getText();
                              Intrinsics.checkNotNullExpressionValue(var19, "xmlPullParser.text");
                              var13 = false;
                              currentArticle.setPubDate(StringsKt.trim((CharSequence)var19).toString());
                           }
                           continue;
                        }
                     } else if (StringsKt.equals(xmlPullParser.getName(), "time", true) && insideItem) {
                        currentArticle.setPubDate(xmlPullParser.nextText());
                     }
                  }
               }
            } else if (eventType == 3 && StringsKt.equals(xmlPullParser.getName(), "item", true)) {
               insideItem = false;
               currentArticle.setOrigin(sourceUrl);
               currentArticle.setSort(sortName);
               articleList.add(currentArticle);
               currentArticle = new RssArticle((String)null, (String)null, (String)null, 0L, (String)null, (String)null, (String)null, (String)null, (String)null, false, (String)null, 2047, (DefaultConstructorMarker)null);
            }

            eventType = xmlPullParser.next();
         }

         RssArticle var21 = (RssArticle)CollectionsKt.firstOrNull(articleList);
         if (var21 != null) {
            var13 = false;
            boolean var14 = false;
            int var16 = false;
            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, "┌获取标题", false, 4, (Object)null);
            }

            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, Intrinsics.stringPlus("└", var21.getTitle()), false, 4, (Object)null);
            }

            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, "┌获取时间", false, 4, (Object)null);
            }

            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, Intrinsics.stringPlus("└", var21.getPubDate()), false, 4, (Object)null);
            }

            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, "┌获取描述", false, 4, (Object)null);
            }

            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, Intrinsics.stringPlus("└", var21.getDescription()), false, 4, (Object)null);
            }

            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, "┌获取图片url", false, 4, (Object)null);
            }

            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, Intrinsics.stringPlus("└", var21.getImage()), false, 4, (Object)null);
            }

            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, "┌获取文章链接", false, 4, (Object)null);
            }

            if (debugLog != null) {
               DebugLog.DefaultImpls.log$default(debugLog, sourceUrl, Intrinsics.stringPlus("└", var21.getLink()), false, 4, (Object)null);
            }
         }

         return new Pair(articleList, (Object)null);
      }
   }

   private final String getImageUrl(String input) {
      String url = null;
      String var4 = "(<img [^>]*>)";
      byte var5 = 0;
      boolean var6 = false;
      Pattern var10000 = Pattern.compile(var4, var5);
      Intrinsics.checkNotNullExpressionValue(var10000, "java.util.regex.Pattern.compile(this, flags)");
      Pattern patternImg = var10000;
      Matcher matcherImg = patternImg.matcher((CharSequence)input);
      if (matcherImg.find()) {
         String imgTag = matcherImg.group(1);
         String var7 = "src\\s*=\\s*\"([^\"]+)\"";
         byte var8 = 0;
         boolean var9 = false;
         var10000 = Pattern.compile(var7, var8);
         Intrinsics.checkNotNullExpressionValue(var10000, "java.util.regex.Pattern.compile(this, flags)");
         Pattern patternLink = var10000;
         Intrinsics.checkNotNull(imgTag);
         Matcher matcherLink = patternLink.matcher((CharSequence)imgTag);
         if (matcherLink.find()) {
            String var15 = matcherLink.group(1);
            Intrinsics.checkNotNull(var15);
            String var14 = var15;
            var9 = false;
            if (var14 == null) {
               throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
            }

            url = StringsKt.trim((CharSequence)var14).toString();
         }
      }

      return url;
   }
}
