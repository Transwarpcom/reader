package io.legado.app.utils;

import io.legado.app.lib.icu4j.CharsetDetector;
import io.legado.app.lib.icu4j.CharsetMatch;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004J\u0012\u0010\n\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\b¨\u0006\f"},
   d2 = {"Lio/legado/app/utils/EncodingDetect;", "", "()V", "getEncode", "", "file", "Ljava/io/File;", "bytes", "", "filePath", "getFileBytes", "getHtmlEncode", "reader-pro"}
)
public final class EncodingDetect {
   @NotNull
   public static final EncodingDetect INSTANCE = new EncodingDetect();

   private EncodingDetect() {
   }

   @Nullable
   public final String getHtmlEncode(@NotNull byte[] bytes) {
      Intrinsics.checkNotNullParameter(bytes, "bytes");

      try {
         Charset var3 = StandardCharsets.UTF_8;
         Intrinsics.checkNotNullExpressionValue(var3, "UTF_8");
         boolean var4 = false;
         Document doc = Jsoup.parse(new String(bytes, var3));
         Elements metaTags = doc.getElementsByTag("meta");
         String charsetStr = null;
         Iterator var5 = metaTags.iterator();

         while(var5.hasNext()) {
            Element metaTag = (Element)var5.next();
            String content = metaTag.attr("charset");
            Intrinsics.checkNotNullExpressionValue(content, "metaTag.attr(\"charset\")");
            charsetStr = content;
            CharSequence var16 = (CharSequence)content;
            boolean var8 = false;
            if (var16.length() != 0) {
               return charsetStr;
            }

            content = metaTag.attr("content");
            String httpEquiv = metaTag.attr("http-equiv");
            Intrinsics.checkNotNullExpressionValue(httpEquiv, "httpEquiv");
            Locale var10 = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(var10, "getDefault()");
            boolean var11 = false;
            String var10000 = httpEquiv.toLowerCase(var10);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).toLowerCase(locale)");
            if (Intrinsics.areEqual(var10000, "content-type")) {
               Intrinsics.checkNotNullExpressionValue(content, "content");
               var10 = Locale.getDefault();
               Intrinsics.checkNotNullExpressionValue(var10, "getDefault()");
               var11 = false;
               var10000 = content.toLowerCase(var10);
               Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).toLowerCase(locale)");
               boolean var12;
               int var18;
               Locale var20;
               if (StringsKt.contains$default((CharSequence)var10000, (CharSequence)"charset", false, 2, (Object)null)) {
                  var20 = Locale.getDefault();
                  Intrinsics.checkNotNullExpressionValue(var20, "getDefault()");
                  var12 = false;
                  var10000 = content.toLowerCase(var20);
                  Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).toLowerCase(locale)");
                  var18 = StringsKt.indexOf$default((CharSequence)var10000, "charset", 0, false, 6, (Object)null) + "charset=".length();
                  var11 = false;
                  var10000 = content.substring(var18);
                  Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
               } else {
                  var20 = Locale.getDefault();
                  Intrinsics.checkNotNullExpressionValue(var20, "getDefault()");
                  var12 = false;
                  var10000 = content.toLowerCase(var20);
                  Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).toLowerCase(locale)");
                  var18 = StringsKt.indexOf$default((CharSequence)var10000, ";", 0, false, 6, (Object)null) + 1;
                  var11 = false;
                  var10000 = content.substring(var18);
                  Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
               }

               charsetStr = var10000;
               CharSequence var9 = (CharSequence)charsetStr;
               boolean var19 = false;
               if (var9.length() != 0) {
                  return charsetStr;
               }
            }
         }
      } catch (Exception var13) {
      }

      return this.getEncode(bytes);
   }

   @NotNull
   public final String getEncode(@NotNull byte[] bytes) {
      Intrinsics.checkNotNullParameter(bytes, "bytes");
      CharsetMatch match = (new CharsetDetector()).setText(bytes).detect();
      String var10000;
      if (match == null) {
         var10000 = "UTF-8";
      } else {
         String var4 = match.getName();
         var10000 = var4 == null ? "UTF-8" : var4;
      }

      return var10000;
   }

   @NotNull
   public final String getEncode(@NotNull String filePath) {
      Intrinsics.checkNotNullParameter(filePath, "filePath");
      return this.getEncode(new File(filePath));
   }

   @NotNull
   public final String getEncode(@NotNull File file) {
      Intrinsics.checkNotNullParameter(file, "file");
      byte[] tempByte = this.getFileBytes(file);
      return this.getEncode(tempByte);
   }

   private final byte[] getFileBytes(File file) {
      byte[] byteArray = new byte[8000];

      try {
         Closeable var3 = (Closeable)(new FileInputStream(file));
         boolean var4 = false;
         boolean var5 = false;
         Throwable var14 = (Throwable)null;

         try {
            FileInputStream it = (FileInputStream)var3;
            int var7 = false;
            it.read(byteArray);
         } catch (Throwable var11) {
            var14 = var11;
            throw var11;
         } finally {
            CloseableKt.closeFinally(var3, var14);
         }
      } catch (Exception var13) {
         System.err.println(Intrinsics.stringPlus("Error: ", var13));
      }

      return byteArray;
   }
}
