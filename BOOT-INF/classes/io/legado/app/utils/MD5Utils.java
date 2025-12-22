package io.legado.app.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0007"},
   d2 = {"Lio/legado/app/utils/MD5Utils;", "", "()V", "md5Encode", "", "str", "md5Encode16", "reader-pro"}
)
public final class MD5Utils {
   @NotNull
   public static final MD5Utils INSTANCE = new MD5Utils();

   private MD5Utils() {
   }

   @NotNull
   public final String md5Encode(@Nullable String str) {
      if (str == null) {
         return "";
      } else {
         String reStr = "";

         try {
            MessageDigest var4 = MessageDigest.getInstance("MD5");
            Intrinsics.checkNotNullExpressionValue(var4, "getInstance(\"MD5\")");
            Charset var7 = Charsets.UTF_8;
            boolean var8 = false;
            byte[] var10001 = str.getBytes(var7);
            Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).getBytes(charset)");
            byte[] var5 = var4.digest(var10001);
            Intrinsics.checkNotNullExpressionValue(var5, "md5.digest(str.toByteArray())");
            StringBuilder stringBuffer = new StringBuilder();
            byte[] var6 = var5;
            int var15 = 0;

            int bt;
            for(int var16 = var5.length; var15 < var16; stringBuffer.append(Integer.toHexString(bt))) {
               byte b = var6[var15];
               ++var15;
               bt = b & 255;
               if (bt < 16) {
                  stringBuffer.append(0);
               }
            }

            String var14 = stringBuffer.toString();
            Intrinsics.checkNotNullExpressionValue(var14, "stringBuffer.toString()");
            reStr = var14;
         } catch (NoSuchAlgorithmException var11) {
            var11.printStackTrace();
         }

         return reStr;
      }
   }

   @NotNull
   public final String md5Encode16(@NotNull String str) {
      Intrinsics.checkNotNullParameter(str, "str");
      String reStr = this.md5Encode(str);
      byte var4 = 8;
      byte var5 = 24;
      boolean var6 = false;
      if (reStr == null) {
         throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
      } else {
         String var10000 = reStr.substring(var4, var5);
         Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
         reStr = var10000;
         return reStr;
      }
   }
}
