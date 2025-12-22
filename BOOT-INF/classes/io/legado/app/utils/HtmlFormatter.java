package io.legado.app.utils;

import io.legado.app.model.analyzeRule.AnalyzeUrl;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000e\u001a\u00020\u0004J\u001c\u0010\u000f\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"},
   d2 = {"Lio/legado/app/utils/HtmlFormatter;", "", "()V", "commentRegex", "Lkotlin/text/Regex;", "formatImagePattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "notImgHtmlRegex", "otherHtmlRegex", "wrapHtmlRegex", "format", "", "html", "otherRegex", "formatKeepImg", "redirectUrl", "Ljava/net/URL;", "reader-pro"}
)
public final class HtmlFormatter {
   @NotNull
   public static final HtmlFormatter INSTANCE = new HtmlFormatter();
   @NotNull
   private static final Regex wrapHtmlRegex;
   @NotNull
   private static final Regex commentRegex;
   @NotNull
   private static final Regex notImgHtmlRegex;
   @NotNull
   private static final Regex otherHtmlRegex;
   private static final Pattern formatImagePattern;

   private HtmlFormatter() {
   }

   @NotNull
   public final String format(@Nullable String html, @NotNull Regex otherRegex) {
      Intrinsics.checkNotNullParameter(otherRegex, "otherRegex");
      if (html == null) {
         return "";
      } else {
         CharSequence var3 = (CharSequence)html;
         Regex var4 = wrapHtmlRegex;
         String var5 = "\n";
         boolean var6 = false;
         var3 = (CharSequence)var4.replace(var3, var5);
         var4 = commentRegex;
         var5 = "";
         var6 = false;
         var3 = (CharSequence)var4.replace(var3, var5);
         String var7 = "";
         boolean var8 = false;
         var3 = (CharSequence)otherRegex.replace(var3, var7);
         var7 = "\\s*\\n+\\s*";
         var8 = false;
         var4 = new Regex(var7);
         var5 = "\n　　";
         var6 = false;
         var3 = (CharSequence)var4.replace(var3, var5);
         var7 = "^[\\n\\s]+";
         var8 = false;
         var4 = new Regex(var7);
         var5 = "　　";
         var6 = false;
         var3 = (CharSequence)var4.replace(var3, var5);
         var7 = "[\\n\\s]+$";
         var8 = false;
         var4 = new Regex(var7);
         var5 = "";
         var6 = false;
         return var4.replace(var3, var5);
      }
   }

   // $FF: synthetic method
   public static String format$default(HtmlFormatter var0, String var1, Regex var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = otherHtmlRegex;
      }

      return var0.format(var1, var2);
   }

   @NotNull
   public final String formatKeepImg(@Nullable String html, @Nullable URL redirectUrl) {
      if (html == null) {
         return "";
      } else {
         String keepImgHtml = this.format(html, notImgHtmlRegex);
         Matcher matcher = formatImagePattern.matcher((CharSequence)keepImgHtml);
         int appendPos = 0;

         StringBuffer sb;
         String param;
         for(sb = new StringBuffer(); matcher.find(); appendPos = matcher.end()) {
            param = null;
            param = "";
            Appendable var10000 = (Appendable)sb;
            CharSequence[] var8 = new CharSequence[2];
            int var10 = matcher.start();
            boolean var11 = false;
            if (keepImgHtml == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            String var10003 = keepImgHtml.substring(appendPos, var10);
            Intrinsics.checkNotNullExpressionValue(var10003, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            var8[0] = (CharSequence)var10003;
            CharSequence[] var10001 = var8;
            byte var10002 = 1;
            StringBuilder var35 = (new StringBuilder()).append("<img src=\"");
            NetworkUtils var10004 = NetworkUtils.INSTANCE;
            URL var10005 = redirectUrl;
            String var32 = matcher.group(1);
            String var10006;
            if (var32 == null) {
               var10006 = null;
            } else {
               boolean var13 = false;
               boolean var14 = false;
               NetworkUtils var26 = var10004;
               StringBuilder var25 = var35;
               byte var24 = 1;
               Appendable var22 = var10000;
               int var16 = false;
               Matcher urlMatcher = AnalyzeUrl.Companion.getParamPattern().matcher((CharSequence)var32);
               String var33;
               if (urlMatcher.find()) {
                  char var18 = ',';
                  int var20 = urlMatcher.end();
                  boolean var21 = false;
                  var33 = var32.substring(var20);
                  Intrinsics.checkNotNullExpressionValue(var33, "(this as java.lang.String).substring(startIndex)");
                  String var19 = var33;
                  boolean var37 = false;
                  param = var18 + var19;
                  byte var36 = 0;
                  var20 = urlMatcher.start();
                  var21 = false;
                  var33 = var32.substring(var36, var20);
                  Intrinsics.checkNotNullExpressionValue(var33, "(this as java.lang.Strin…ing(startIndex, endIndex)");
               } else {
                  var33 = var32;
               }

               String var28 = var33;
               var10000 = var22;
               var10001 = var8;
               var10002 = var24;
               var35 = var25;
               var10004 = var26;
               var10005 = redirectUrl;
               var10006 = var28;
            }

            String var31 = var10006;
            String var9 = var31 == null ? matcher.group(2) : var31;
            if (var9 == null) {
               var10006 = matcher.group(3);
               Intrinsics.checkNotNull(var10006);
            } else {
               var10006 = var9;
            }

            var10001[var10002] = (CharSequence)var35.append(var10004.getAbsoluteURL(var10005, var10006)).append(param).append("\">").toString();
            StringsKt.append(var10000, var8);
         }

         if (appendPos < keepImgHtml.length()) {
            int var29 = keepImgHtml.length();
            boolean var30 = false;
            if (keepImgHtml == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            String var34 = keepImgHtml.substring(appendPos, var29);
            Intrinsics.checkNotNullExpressionValue(var34, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            sb.append(var34);
         }

         param = sb.toString();
         Intrinsics.checkNotNullExpressionValue(param, "sb.toString()");
         return param;
      }
   }

   // $FF: synthetic method
   public static String formatKeepImg$default(HtmlFormatter var0, String var1, URL var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
      }

      return var0.formatKeepImg(var1, var2);
   }

   static {
      String var0 = "</?(?:div|p|br|hr|h\\d|article|dd|dl)[^>]*>";
      boolean var1 = false;
      wrapHtmlRegex = new Regex(var0);
      var0 = "<!--[^>]*-->";
      var1 = false;
      commentRegex = new Regex(var0);
      var0 = "</?(?!img)[a-zA-Z]+(?=[ >])[^<>]*>";
      var1 = false;
      notImgHtmlRegex = new Regex(var0);
      var0 = "</?[a-zA-Z]+(?=[ >])[^<>]*>";
      var1 = false;
      otherHtmlRegex = new Regex(var0);
      formatImagePattern = Pattern.compile("<img[^>]*src *= *\"([^\"{]*\\{(?:[^{}]|\\{[^}]+\\})+\\})\"[^>]*>|<img[^>]*data-[^=]*= *\"([^\"]*)\"[^>]*>|<img[^>]*src *= *\"([^\"]*)\"[^>]*>", 2);
   }
}
