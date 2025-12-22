package io.legado.app.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.BitSet;
import java.util.Enumeration;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\rJ\u0018\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\rJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\rJ\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u0010\u0010\u0015\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\rJ\u0012\u0010\u0016\u001a\u00020\r2\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0018J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\rJ\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\rR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006!"},
   d2 = {"Lio/legado/app/utils/NetworkUtils;", "", "()V", "IPV4_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "notNeedEncoding", "Ljava/util/BitSet;", "getNotNeedEncoding", "()Ljava/util/BitSet;", "notNeedEncoding$delegate", "Lkotlin/Lazy;", "getAbsoluteURL", "", "baseURL", "Ljava/net/URL;", "relativePath", "getBaseUrl", "url", "getLocalIPAddress", "Ljava/net/InetAddress;", "getSubDomain", "getUrl", "response", "Lretrofit2/Response;", "hasUrlEncoded", "", "str", "isDigit16Char", "c", "", "isIPv4Address", "input", "reader-pro"}
)
public final class NetworkUtils {
   @NotNull
   public static final NetworkUtils INSTANCE = new NetworkUtils();
   @NotNull
   private static final Lazy notNeedEncoding$delegate;
   private static final Pattern IPV4_PATTERN;

   private NetworkUtils() {
   }

   @NotNull
   public final String getUrl(@NotNull Response<?> response) {
      Intrinsics.checkNotNullParameter(response, "response");
      okhttp3.Response networkResponse = response.raw().networkResponse();
      String var10000;
      if (networkResponse == null) {
         var10000 = null;
      } else {
         Request var5 = networkResponse.request();
         if (var5 == null) {
            var10000 = null;
         } else {
            HttpUrl var6 = var5.url();
            var10000 = var6 == null ? null : var6.toString();
         }
      }

      String var3 = var10000;
      return var3 == null ? response.raw().request().url().toString() : var3;
   }

   private final BitSet getNotNeedEncoding() {
      Lazy var1 = notNeedEncoding$delegate;
      boolean var3 = false;
      return (BitSet)var1.getValue();
   }

   public final boolean hasUrlEncoded(@NotNull String str) {
      Intrinsics.checkNotNullParameter(str, "str");
      boolean needEncode = false;
      int i = 0;

      while(i < str.length()) {
         char c = str.charAt(i);
         BitSet var10000 = this.getNotNeedEncoding();
         boolean var6 = false;
         if (var10000.get(c)) {
            ++i;
         } else {
            if (c == '%' && i + 2 < str.length()) {
               ++i;
               char c1 = str.charAt(i);
               ++i;
               char c2 = str.charAt(i);
               if (this.isDigit16Char(c1) && this.isDigit16Char(c2)) {
                  ++i;
                  continue;
               }
            }

            needEncode = true;
            break;
         }
      }

      return !needEncode;
   }

   private final boolean isDigit16Char(char c) {
      return ('0' <= c ? c <= '9' : false) || ('A' <= c ? c <= 'F' : false) || ('a' <= c ? c <= 'f' : false);
   }

   @NotNull
   public final String getAbsoluteURL(@Nullable String baseURL, @NotNull String relativePath) {
      Intrinsics.checkNotNullParameter(relativePath, "relativePath");
      CharSequence var3 = (CharSequence)baseURL;
      boolean var4 = false;
      boolean var5 = false;
      if (var3 == null || var3.length() == 0) {
         return relativePath;
      } else {
         var3 = (CharSequence)relativePath;
         var4 = false;
         var5 = false;
         if (var3.length() == 0) {
            return baseURL;
         } else {
            try {
               URL absoluteUrl = new URL(StringsKt.substringBefore$default(baseURL, ",", (String)null, 2, (Object)null));
               URL parseUrl = new URL(absoluteUrl, relativePath);
               String var6 = parseUrl.toString();
               Intrinsics.checkNotNullExpressionValue(var6, "parseUrl.toString()");
               return var6;
            } catch (Exception var7) {
               var7.printStackTrace();
               return relativePath;
            }
         }
      }
   }

   @NotNull
   public final String getAbsoluteURL(@Nullable URL baseURL, @NotNull String relativePath) {
      Intrinsics.checkNotNullParameter(relativePath, "relativePath");
      if (baseURL == null) {
         return relativePath;
      } else {
         try {
            URL parseUrl = new URL(baseURL, relativePath);
            String var5 = parseUrl.toString();
            Intrinsics.checkNotNullExpressionValue(var5, "parseUrl.toString()");
            return var5;
         } catch (Exception var6) {
            var6.printStackTrace();
            return relativePath;
         }
      }
   }

   @Nullable
   public final String getBaseUrl(@Nullable String url) {
      if (url != null && StringsKt.startsWith$default(url, "http", false, 2, (Object)null)) {
         int index = StringsKt.indexOf$default((CharSequence)url, "/", 9, false, 4, (Object)null);
         String var10000;
         if (index == -1) {
            var10000 = url;
         } else {
            byte var4 = 0;
            boolean var5 = false;
            var10000 = url.substring(var4, index);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
         }

         return var10000;
      } else {
         return null;
      }
   }

   @NotNull
   public final String getSubDomain(@Nullable String url) {
      String var3 = this.getBaseUrl(url);
      if (var3 == null) {
         return "";
      } else {
         String var10000;
         int var4;
         boolean var5;
         if (StringsKt.indexOf$default((CharSequence)var3, ".", 0, false, 6, (Object)null) == StringsKt.lastIndexOf$default((CharSequence)var3, ".", 0, false, 6, (Object)null)) {
            var4 = StringsKt.lastIndexOf$default((CharSequence)var3, "/", 0, false, 6, (Object)null) + 1;
            var5 = false;
            var10000 = var3.substring(var4);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
         } else {
            var4 = StringsKt.indexOf$default((CharSequence)var3, ".", 0, false, 6, (Object)null) + 1;
            var5 = false;
            var10000 = var3.substring(var4);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
         }

         return var10000;
      }
   }

   @Nullable
   public final InetAddress getLocalIPAddress() {
      Enumeration enumeration = null;

      try {
         enumeration = NetworkInterface.getNetworkInterfaces();
      } catch (SocketException var6) {
         var6.printStackTrace();
      }

      if (enumeration != null) {
         while(true) {
            Enumeration addresses;
            do {
               if (!enumeration.hasMoreElements()) {
                  return null;
               }

               NetworkInterface nif = (NetworkInterface)enumeration.nextElement();
               addresses = nif.getInetAddresses();
            } while(addresses == null);

            while(addresses.hasMoreElements()) {
               InetAddress address = (InetAddress)addresses.nextElement();
               if (!address.isLoopbackAddress()) {
                  String var5 = address.getHostAddress();
                  Intrinsics.checkNotNullExpressionValue(var5, "address.hostAddress");
                  if (this.isIPv4Address(var5)) {
                     return address;
                  }
               }
            }
         }
      } else {
         return null;
      }
   }

   public final boolean isIPv4Address(@NotNull String input) {
      Intrinsics.checkNotNullParameter(input, "input");
      return IPV4_PATTERN.matcher((CharSequence)input).matches();
   }

   static {
      notNeedEncoding$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      IPV4_PATTERN = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
   }
}
