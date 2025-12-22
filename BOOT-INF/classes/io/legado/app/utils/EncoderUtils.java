package io.legado.app.utils;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007J\u001c\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007J2\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ2\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ2\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ2\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ\u0016\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J2\u0010\u0019\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ2\u0010\u001a\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ\"\u0010\u001b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u0007J\"\u0010\u001d\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u0007J4\u0010\u001e\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ4\u0010\u001f\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ\u0016\u0010 \u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010!\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J4\u0010\"\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ4\u0010#\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ4\u0010$\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ4\u0010%\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ \u0010&\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u0007J \u0010'\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u0007J\u000e\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004J\u0006\u0010*\u001a\u00020+J@\u0010,\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010-\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\u0006\u0010.\u001a\u00020/H\u0002¨\u00060"},
   d2 = {"Lio/legado/app/utils/EncoderUtils;", "", "()V", "base64Decode", "", "str", "flags", "", "base64Encode", "decryptAES", "", "data", "key", "transformation", "iv", "decryptBase64AES", "decryptBase64DES", "decryptBase64DESede", "decryptByPrivateKey", "input", "privateKey", "Ljava/security/PrivateKey;", "decryptByPublicKey", "publicKey", "Ljava/security/PublicKey;", "decryptDES", "decryptDESede", "decryptSegmentByPrivateKey", "keySize", "decryptSegmentByPublicKey", "encryptAES", "encryptAES2Base64", "encryptByPrivateKey", "encryptByPublicKey", "encryptDES", "encryptDES2Base64", "encryptDESede", "encryptDESede2Base64", "encryptSegmentByPrivateKey", "encryptSegmentByPublicKey", "escape", "src", "generateKeys", "Ljava/security/KeyPair;", "symmetricTemplate", "algorithm", "isEncrypt", "", "reader-pro"}
)
public final class EncoderUtils {
   @NotNull
   public static final EncoderUtils INSTANCE = new EncoderUtils();

   private EncoderUtils() {
   }

   @NotNull
   public final String escape(@NotNull String src) {
      Intrinsics.checkNotNullParameter(src, "src");
      StringBuilder tmp = new StringBuilder();
      String var3 = src;
      int var4 = 0;
      int var5 = src.length();

      while(true) {
         while(var4 < var5) {
            char var6 = var3.charAt(var4);
            ++var4;
            boolean var9 = false;
            if (!('0' <= var6 ? var6 <= '9' : false) && !('A' <= var6 ? var6 <= 'Z' : false) && !('a' <= var6 ? var6 <= 'z' : false)) {
               String prefix = var6 < 16 ? "%0" : (var6 < 256 ? "%" : "%u");
               StringBuilder var10000 = tmp.append(prefix);
               byte var10 = 16;
               boolean var11 = false;
               String var10001 = Integer.toString(var6, CharsKt.checkRadix(var10));
               Intrinsics.checkNotNullExpressionValue(var10001, "java.lang.Integer.toStri…(this, checkRadix(radix))");
               var10000.append(var10001);
            } else {
               tmp.append(var6);
            }
         }

         var3 = tmp.toString();
         Intrinsics.checkNotNullExpressionValue(var3, "tmp.toString()");
         return var3;
      }
   }

   @JvmOverloads
   @NotNull
   public final String base64Decode(@NotNull String str, int flags) {
      Intrinsics.checkNotNullParameter(str, "str");
      byte[] bytes = Base64.decode(str, flags);
      Intrinsics.checkNotNullExpressionValue(bytes, "bytes");
      boolean var5 = false;
      return new String(bytes, Charsets.UTF_8);
   }

   // $FF: synthetic method
   public static String base64Decode$default(EncoderUtils var0, String var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      return var0.base64Decode(var1, var2);
   }

   @JvmOverloads
   @Nullable
   public final String base64Encode(@NotNull String str, int flags) {
      Intrinsics.checkNotNullParameter(str, "str");
      Charset var4 = Charsets.UTF_8;
      boolean var5 = false;
      byte[] var10000 = str.getBytes(var4);
      Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).getBytes(charset)");
      return Base64.encodeToString(var10000, flags);
   }

   // $FF: synthetic method
   public static String base64Encode$default(EncoderUtils var0, String var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 2;
      }

      return var0.base64Encode(var1, var2);
   }

   @Nullable
   public final byte[] encryptAES2Base64(@Nullable byte[] data, @Nullable byte[] key, @Nullable String transformation, @Nullable byte[] iv) throws Exception {
      return Base64.encode(this.encryptAES(data, key, transformation, iv), 2);
   }

   // $FF: synthetic method
   public static byte[] encryptAES2Base64$default(EncoderUtils var0, byte[] var1, byte[] var2, String var3, byte[] var4, int var5, Object var6) throws Exception {
      if ((var5 & 4) != 0) {
         var3 = "DES/ECB/PKCS5Padding";
      }

      if ((var5 & 8) != 0) {
         var4 = null;
      }

      return var0.encryptAES2Base64(var1, var2, var3, var4);
   }

   @Nullable
   public final byte[] encryptAES(@Nullable byte[] data, @Nullable byte[] key, @Nullable String transformation, @Nullable byte[] iv) throws Exception {
      Intrinsics.checkNotNull(transformation);
      return this.symmetricTemplate(data, key, "AES", transformation, iv, true);
   }

   // $FF: synthetic method
   public static byte[] encryptAES$default(EncoderUtils var0, byte[] var1, byte[] var2, String var3, byte[] var4, int var5, Object var6) throws Exception {
      if ((var5 & 4) != 0) {
         var3 = "DES/ECB/PKCS5Padding";
      }

      if ((var5 & 8) != 0) {
         var4 = null;
      }

      return var0.encryptAES(var1, var2, var3, var4);
   }

   @Nullable
   public final byte[] decryptBase64AES(@Nullable byte[] data, @Nullable byte[] key, @NotNull String transformation, @Nullable byte[] iv) throws Exception {
      Intrinsics.checkNotNullParameter(transformation, "transformation");
      return this.decryptAES(Base64.decode((byte[])data, 2), key, transformation, iv);
   }

   // $FF: synthetic method
   public static byte[] decryptBase64AES$default(EncoderUtils var0, byte[] var1, byte[] var2, String var3, byte[] var4, int var5, Object var6) throws Exception {
      if ((var5 & 4) != 0) {
         var3 = "DES/ECB/PKCS5Padding";
      }

      if ((var5 & 8) != 0) {
         var4 = null;
      }

      return var0.decryptBase64AES(var1, var2, var3, var4);
   }

   @Nullable
   public final byte[] decryptAES(@Nullable byte[] data, @Nullable byte[] key, @NotNull String transformation, @Nullable byte[] iv) throws Exception {
      Intrinsics.checkNotNullParameter(transformation, "transformation");
      return this.symmetricTemplate(data, key, "AES", transformation, iv, false);
   }

   // $FF: synthetic method
   public static byte[] decryptAES$default(EncoderUtils var0, byte[] var1, byte[] var2, String var3, byte[] var4, int var5, Object var6) throws Exception {
      if ((var5 & 4) != 0) {
         var3 = "DES/ECB/PKCS5Padding";
      }

      if ((var5 & 8) != 0) {
         var4 = null;
      }

      return var0.decryptAES(var1, var2, var3, var4);
   }

   private final byte[] symmetricTemplate(byte[] data, byte[] key, String algorithm, String transformation, byte[] iv, boolean isEncrypt) throws Exception {
      byte[] var10000;
      if (data != null) {
         boolean var8 = false;
         if (data.length != 0 && key != null) {
            var8 = false;
            if (key.length != 0) {
               Cipher cipher;
               label35: {
                  SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
                  cipher = Cipher.getInstance(transformation);
                  int mode = isEncrypt ? 1 : 2;
                  if (iv != null) {
                     boolean var11 = false;
                     if (iv.length != 0) {
                        AlgorithmParameterSpec params = (AlgorithmParameterSpec)(new IvParameterSpec(iv));
                        cipher.init(mode, (Key)keySpec, params);
                        break label35;
                     }
                  }

                  cipher.init(mode, (Key)keySpec);
               }

               var10000 = cipher.doFinal(data);
               return var10000;
            }
         }
      }

      var10000 = null;
      return var10000;
   }

   @Nullable
   public final byte[] encryptDES2Base64(@Nullable byte[] data, @Nullable byte[] key, @Nullable String transformation, @Nullable byte[] iv) throws Exception {
      return Base64.encode(this.encryptDES(data, key, transformation, iv), 2);
   }

   // $FF: synthetic method
   public static byte[] encryptDES2Base64$default(EncoderUtils var0, byte[] var1, byte[] var2, String var3, byte[] var4, int var5, Object var6) throws Exception {
      if ((var5 & 4) != 0) {
         var3 = "DES/ECB/PKCS5Padding";
      }

      if ((var5 & 8) != 0) {
         var4 = null;
      }

      return var0.encryptDES2Base64(var1, var2, var3, var4);
   }

   @Nullable
   public final byte[] encryptDES(@Nullable byte[] data, @Nullable byte[] key, @Nullable String transformation, @Nullable byte[] iv) throws Exception {
      Intrinsics.checkNotNull(transformation);
      return this.symmetricTemplate(data, key, "DES", transformation, iv, true);
   }

   // $FF: synthetic method
   public static byte[] encryptDES$default(EncoderUtils var0, byte[] var1, byte[] var2, String var3, byte[] var4, int var5, Object var6) throws Exception {
      if ((var5 & 4) != 0) {
         var3 = "DES/ECB/PKCS5Padding";
      }

      if ((var5 & 8) != 0) {
         var4 = null;
      }

      return var0.encryptDES(var1, var2, var3, var4);
   }

   @Nullable
   public final byte[] decryptBase64DES(@Nullable byte[] data, @Nullable byte[] key, @NotNull String transformation, @Nullable byte[] iv) throws Exception {
      Intrinsics.checkNotNullParameter(transformation, "transformation");
      return this.decryptDES(Base64.decode((byte[])data, 2), key, transformation, iv);
   }

   // $FF: synthetic method
   public static byte[] decryptBase64DES$default(EncoderUtils var0, byte[] var1, byte[] var2, String var3, byte[] var4, int var5, Object var6) throws Exception {
      if ((var5 & 4) != 0) {
         var3 = "DES/ECB/PKCS5Padding";
      }

      if ((var5 & 8) != 0) {
         var4 = null;
      }

      return var0.decryptBase64DES(var1, var2, var3, var4);
   }

   @Nullable
   public final byte[] decryptDES(@Nullable byte[] data, @Nullable byte[] key, @NotNull String transformation, @Nullable byte[] iv) throws Exception {
      Intrinsics.checkNotNullParameter(transformation, "transformation");
      return this.symmetricTemplate(data, key, "DES", transformation, iv, false);
   }

   // $FF: synthetic method
   public static byte[] decryptDES$default(EncoderUtils var0, byte[] var1, byte[] var2, String var3, byte[] var4, int var5, Object var6) throws Exception {
      if ((var5 & 4) != 0) {
         var3 = "DES/ECB/PKCS5Padding";
      }

      if ((var5 & 8) != 0) {
         var4 = null;
      }

      return var0.decryptDES(var1, var2, var3, var4);
   }

   @Nullable
   public final byte[] encryptDESede2Base64(@Nullable byte[] data, @Nullable byte[] key, @Nullable String transformation, @Nullable byte[] iv) throws Exception {
      return Base64.encode(this.encryptDESede(data, key, transformation, iv), 2);
   }

   // $FF: synthetic method
   public static byte[] encryptDESede2Base64$default(EncoderUtils var0, byte[] var1, byte[] var2, String var3, byte[] var4, int var5, Object var6) throws Exception {
      if ((var5 & 4) != 0) {
         var3 = "DESede/ECB/PKCS5Padding";
      }

      if ((var5 & 8) != 0) {
         var4 = null;
      }

      return var0.encryptDESede2Base64(var1, var2, var3, var4);
   }

   @Nullable
   public final byte[] encryptDESede(@Nullable byte[] data, @Nullable byte[] key, @Nullable String transformation, @Nullable byte[] iv) throws Exception {
      Intrinsics.checkNotNull(transformation);
      return this.symmetricTemplate(data, key, "DESede", transformation, iv, true);
   }

   // $FF: synthetic method
   public static byte[] encryptDESede$default(EncoderUtils var0, byte[] var1, byte[] var2, String var3, byte[] var4, int var5, Object var6) throws Exception {
      if ((var5 & 4) != 0) {
         var3 = "DESede/ECB/PKCS5Padding";
      }

      if ((var5 & 8) != 0) {
         var4 = null;
      }

      return var0.encryptDESede(var1, var2, var3, var4);
   }

   @Nullable
   public final byte[] decryptBase64DESede(@Nullable byte[] data, @Nullable byte[] key, @NotNull String transformation, @Nullable byte[] iv) throws Exception {
      Intrinsics.checkNotNullParameter(transformation, "transformation");
      return this.decryptDESede(Base64.decode((byte[])data, 2), key, transformation, iv);
   }

   // $FF: synthetic method
   public static byte[] decryptBase64DESede$default(EncoderUtils var0, byte[] var1, byte[] var2, String var3, byte[] var4, int var5, Object var6) throws Exception {
      if ((var5 & 4) != 0) {
         var3 = "DESede/ECB/PKCS5Padding";
      }

      if ((var5 & 8) != 0) {
         var4 = null;
      }

      return var0.decryptBase64DESede(var1, var2, var3, var4);
   }

   @Nullable
   public final byte[] decryptDESede(@Nullable byte[] data, @Nullable byte[] key, @NotNull String transformation, @Nullable byte[] iv) throws Exception {
      Intrinsics.checkNotNullParameter(transformation, "transformation");
      return this.symmetricTemplate(data, key, "DESede", transformation, iv, false);
   }

   // $FF: synthetic method
   public static byte[] decryptDESede$default(EncoderUtils var0, byte[] var1, byte[] var2, String var3, byte[] var4, int var5, Object var6) throws Exception {
      if ((var5 & 4) != 0) {
         var3 = "DESede/ECB/PKCS5Padding";
      }

      if ((var5 & 8) != 0) {
         var4 = null;
      }

      return var0.decryptDESede(var1, var2, var3, var4);
   }

   @NotNull
   public final String encryptByPrivateKey(@NotNull String input, @NotNull PrivateKey privateKey) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(privateKey, "privateKey");
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(1, (Key)privateKey);
      Charset var6 = Charsets.UTF_8;
      boolean var7 = false;
      byte[] var10001 = input.getBytes(var6);
      Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).getBytes(charset)");
      byte[] encrypt = cipher.doFinal(var10001);
      String var5 = Base64.encodeToString(encrypt, 2);
      Intrinsics.checkNotNullExpressionValue(var5, "encodeToString(encrypt, Base64.NO_WRAP)");
      return var5;
   }

   @NotNull
   public final String decryptByPublicKey(@NotNull String input, @NotNull PublicKey publicKey) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(publicKey, "publicKey");
      byte[] decode = Base64.decode((String)input, 2);
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(2, (Key)publicKey);
      byte[] encrypt = cipher.doFinal(decode);
      Intrinsics.checkNotNullExpressionValue(encrypt, "encrypt");
      boolean var7 = false;
      return new String(encrypt, Charsets.UTF_8);
   }

   @NotNull
   public final String encryptByPublicKey(@NotNull String input, @NotNull PublicKey publicKey) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(publicKey, "publicKey");
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(1, (Key)publicKey);
      Charset var6 = Charsets.UTF_8;
      boolean var7 = false;
      byte[] var10001 = input.getBytes(var6);
      Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).getBytes(charset)");
      byte[] encrypt = cipher.doFinal(var10001);
      String var5 = Base64.encodeToString(encrypt, 2);
      Intrinsics.checkNotNullExpressionValue(var5, "encodeToString(encrypt, Base64.NO_WRAP)");
      return var5;
   }

   @NotNull
   public final String decryptByPrivateKey(@NotNull String input, @NotNull PrivateKey privateKey) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(privateKey, "privateKey");
      byte[] decode = Base64.decode((String)input, 2);
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(2, (Key)privateKey);
      byte[] encrypt = cipher.doFinal(decode);
      Intrinsics.checkNotNullExpressionValue(encrypt, "encrypt");
      boolean var7 = false;
      return new String(encrypt, Charsets.UTF_8);
   }

   @NotNull
   public final String encryptSegmentByPrivateKey(@NotNull String input, @NotNull PrivateKey privateKey, int keySize) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(privateKey, "privateKey");
      Charset var6 = Charsets.UTF_8;
      boolean var7 = false;
      byte[] var10000 = input.getBytes(var6);
      Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).getBytes(charset)");
      byte[] byteArray = var10000;
      byte[] temp = null;
      int offset = 0;
      int MAX_ENCRYPT_BLOCK = keySize / 8 - 11;
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(1, (Key)privateKey);

      byte[] temp;
      for(; byteArray.length - offset > 0; bos.write(temp)) {
         byte[] var10;
         if (byteArray.length - offset >= MAX_ENCRYPT_BLOCK) {
            var10 = cipher.doFinal(byteArray, offset, MAX_ENCRYPT_BLOCK);
            Intrinsics.checkNotNullExpressionValue(var10, "cipher.doFinal(byteArray, offset, MAX_ENCRYPT_BLOCK)");
            temp = var10;
            offset += MAX_ENCRYPT_BLOCK;
         } else {
            var10 = cipher.doFinal(byteArray, offset, byteArray.length - offset);
            Intrinsics.checkNotNullExpressionValue(var10, "cipher.doFinal(byteArray, offset, byteArray.size - offset)");
            temp = var10;
            offset = byteArray.length;
         }
      }

      bos.close();
      String var11 = Base64.encodeToString(bos.toByteArray(), 2);
      Intrinsics.checkNotNullExpressionValue(var11, "encodeToString(bos.toByteArray(), Base64.NO_WRAP)");
      return var11;
   }

   // $FF: synthetic method
   public static String encryptSegmentByPrivateKey$default(EncoderUtils var0, String var1, PrivateKey var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 2048;
      }

      return var0.encryptSegmentByPrivateKey(var1, var2, var3);
   }

   @Nullable
   public final String decryptSegmentByPublicKey(@NotNull String input, @NotNull PublicKey publicKey, int keySize) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(publicKey, "publicKey");
      byte[] byteArray = Base64.decode((String)input, 2);
      byte[] temp = null;
      int offset = 0;
      int MAX_DECRYPT_BLOCK = keySize / 8;
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(2, (Key)publicKey);

      byte[] var10;
      byte[] temp;
      for(; byteArray.length - offset > 0; bos.write(temp)) {
         if (byteArray.length - offset >= MAX_DECRYPT_BLOCK) {
            var10 = cipher.doFinal(byteArray, offset, MAX_DECRYPT_BLOCK);
            Intrinsics.checkNotNullExpressionValue(var10, "cipher.doFinal(byteArray, offset, MAX_DECRYPT_BLOCK)");
            temp = var10;
            offset += MAX_DECRYPT_BLOCK;
         } else {
            var10 = cipher.doFinal(byteArray, offset, byteArray.length - offset);
            Intrinsics.checkNotNullExpressionValue(var10, "cipher.doFinal(byteArray, offset, byteArray.size - offset)");
            temp = var10;
            offset = byteArray.length;
         }
      }

      bos.close();
      var10 = bos.toByteArray();
      Intrinsics.checkNotNullExpressionValue(var10, "bos.toByteArray()");
      boolean var11 = false;
      return new String(var10, Charsets.UTF_8);
   }

   // $FF: synthetic method
   public static String decryptSegmentByPublicKey$default(EncoderUtils var0, String var1, PublicKey var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 2048;
      }

      return var0.decryptSegmentByPublicKey(var1, var2, var3);
   }

   @NotNull
   public final String encryptSegmentByPublicKey(@NotNull String input, @NotNull PublicKey publicKey, int keySize) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(publicKey, "publicKey");
      Charset var6 = Charsets.UTF_8;
      boolean var7 = false;
      byte[] var10000 = input.getBytes(var6);
      Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).getBytes(charset)");
      byte[] byteArray = var10000;
      byte[] temp = null;
      int offset = 0;
      int MAX_ENCRYPT_BLOCK = keySize / 8 - 11;
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(1, (Key)publicKey);

      byte[] temp;
      for(; byteArray.length - offset > 0; bos.write(temp)) {
         byte[] var10;
         if (byteArray.length - offset >= MAX_ENCRYPT_BLOCK) {
            var10 = cipher.doFinal(byteArray, offset, MAX_ENCRYPT_BLOCK);
            Intrinsics.checkNotNullExpressionValue(var10, "cipher.doFinal(byteArray, offset, MAX_ENCRYPT_BLOCK)");
            temp = var10;
            offset += MAX_ENCRYPT_BLOCK;
         } else {
            var10 = cipher.doFinal(byteArray, offset, byteArray.length - offset);
            Intrinsics.checkNotNullExpressionValue(var10, "cipher.doFinal(byteArray, offset, byteArray.size - offset)");
            temp = var10;
            offset = byteArray.length;
         }
      }

      bos.close();
      String var11 = Base64.encodeToString(bos.toByteArray(), 2);
      Intrinsics.checkNotNullExpressionValue(var11, "encodeToString(bos.toByteArray(), Base64.NO_WRAP)");
      return var11;
   }

   // $FF: synthetic method
   public static String encryptSegmentByPublicKey$default(EncoderUtils var0, String var1, PublicKey var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 2048;
      }

      return var0.encryptSegmentByPublicKey(var1, var2, var3);
   }

   @Nullable
   public final String decryptSegmentByPrivateKey(@NotNull String input, @NotNull PrivateKey privateKey, int keySize) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(privateKey, "privateKey");
      byte[] byteArray = Base64.decode((String)input, 2);
      byte[] temp = null;
      int offset = 0;
      int MAX_DECRYPT_BLOCK = keySize / 8;
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(2, (Key)privateKey);

      byte[] var10;
      byte[] temp;
      for(; byteArray.length - offset > 0; bos.write(temp)) {
         if (byteArray.length - offset >= MAX_DECRYPT_BLOCK) {
            var10 = cipher.doFinal(byteArray, offset, MAX_DECRYPT_BLOCK);
            Intrinsics.checkNotNullExpressionValue(var10, "cipher.doFinal(byteArray, offset, MAX_DECRYPT_BLOCK)");
            temp = var10;
            offset += MAX_DECRYPT_BLOCK;
         } else {
            var10 = cipher.doFinal(byteArray, offset, byteArray.length - offset);
            Intrinsics.checkNotNullExpressionValue(var10, "cipher.doFinal(byteArray, offset, byteArray.size - offset)");
            temp = var10;
            offset = byteArray.length;
         }
      }

      bos.close();
      var10 = bos.toByteArray();
      Intrinsics.checkNotNullExpressionValue(var10, "bos.toByteArray()");
      boolean var11 = false;
      return new String(var10, Charsets.UTF_8);
   }

   // $FF: synthetic method
   public static String decryptSegmentByPrivateKey$default(EncoderUtils var0, String var1, PrivateKey var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 2048;
      }

      return var0.decryptSegmentByPrivateKey(var1, var2, var3);
   }

   @NotNull
   public final KeyPair generateKeys() {
      KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
      KeyPair var2 = generator.genKeyPair();
      Intrinsics.checkNotNullExpressionValue(var2, "generator.genKeyPair()");
      return var2;
   }

   @JvmOverloads
   @NotNull
   public final String base64Decode(@NotNull String str) {
      Intrinsics.checkNotNullParameter(str, "str");
      return base64Decode$default(this, str, 0, 2, (Object)null);
   }

   @JvmOverloads
   @Nullable
   public final String base64Encode(@NotNull String str) {
      Intrinsics.checkNotNullParameter(str, "str");
      return base64Encode$default(this, str, 0, 2, (Object)null);
   }
}
