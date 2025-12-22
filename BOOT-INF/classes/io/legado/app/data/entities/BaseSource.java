package io.legado.app.data.entities;

import com.google.gson.Gson;
import com.script.Bindings;
import com.script.SimpleBindings;
import io.legado.app.constant.AppConst;
import io.legado.app.help.CacheManager;
import io.legado.app.help.JsExtensions;
import io.legado.app.help.http.CookieStore;
import io.legado.app.help.http.StrResponse;
import io.legado.app.model.analyzeRule.QueryTTF;
import io.legado.app.utils.Base64;
import io.legado.app.utils.EncoderUtils;
import io.legado.app.utils.GsonExtensionsKt;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.Result.Companion;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Connection.Response;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u000f\bf\u0018\u00002\u00020\u0001J-\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00032\u0019\b\u0002\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001b¢\u0006\u0002\b\u001eH\u0016J.\u0010\u001f\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030 j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`!2\b\b\u0002\u0010\"\u001a\u00020\tH\u0016J\b\u0010#\u001a\u00020\u0003H&J\n\u0010$\u001a\u0004\u0018\u00010\u0003H\u0016J\u0016\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010&H\u0016J\n\u0010'\u001a\u0004\u0018\u00010\u0003H\u0016J\u0016\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010&H\u0016J\n\u0010)\u001a\u0004\u0018\u00010\u0003H\u0016J\n\u0010*\u001a\u0004\u0018\u00010\u0000H\u0016J\b\u0010+\u001a\u00020\u0003H&J\n\u0010,\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010-\u001a\u00020\u001dH\u0016J\u0010\u0010.\u001a\u00020\u001d2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u0010\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\u0003H\u0016J\b\u00101\u001a\u00020\u001dH\u0016J\b\u00102\u001a\u00020\u001dH\u0016J\u0012\u00103\u001a\u00020\u001d2\b\u00104\u001a\u0004\u0018\u00010\u0003H\u0016R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00010\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0005\"\u0004\b\u0010\u0010\u0007R\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0005\"\u0004\b\u0013\u0010\u0007R\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0005\"\u0004\b\u0016\u0010\u0007¨\u00065"},
   d2 = {"Lio/legado/app/data/entities/BaseSource;", "Lio/legado/app/help/JsExtensions;", "concurrentRate", "", "getConcurrentRate", "()Ljava/lang/String;", "setConcurrentRate", "(Ljava/lang/String;)V", "enabledCookieJar", "", "getEnabledCookieJar", "()Ljava/lang/Boolean;", "setEnabledCookieJar", "(Ljava/lang/Boolean;)V", "header", "getHeader", "setHeader", "loginUi", "getLoginUi", "setLoginUi", "loginUrl", "getLoginUrl", "setLoginUrl", "evalJS", "", "jsStr", "bindingsConfig", "Lkotlin/Function1;", "Lcom/script/SimpleBindings;", "", "Lkotlin/ExtensionFunctionType;", "getHeaderMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "hasLoginHeader", "getKey", "getLoginHeader", "getLoginHeaderMap", "", "getLoginInfo", "getLoginInfoMap", "getLoginJs", "getSource", "getTag", "getVariable", "login", "putLoginHeader", "putLoginInfo", "info", "removeLoginHeader", "removeLoginInfo", "setVariable", "variable", "reader-pro"}
)
public interface BaseSource extends JsExtensions {
   @Nullable
   String getConcurrentRate();

   void setConcurrentRate(@Nullable String <set-?>);

   @Nullable
   String getLoginUrl();

   void setLoginUrl(@Nullable String <set-?>);

   @Nullable
   String getLoginUi();

   void setLoginUi(@Nullable String <set-?>);

   @Nullable
   String getHeader();

   void setHeader(@Nullable String <set-?>);

   @Nullable
   Boolean getEnabledCookieJar();

   void setEnabledCookieJar(@Nullable Boolean <set-?>);

   @NotNull
   String getTag();

   @NotNull
   String getKey();

   @Nullable
   BaseSource getSource();

   @Nullable
   String getLoginJs();

   void login();

   @NotNull
   HashMap<String, String> getHeaderMap(boolean hasLoginHeader);

   @Nullable
   String getLoginHeader();

   @Nullable
   Map<String, String> getLoginHeaderMap();

   void putLoginHeader(@NotNull String header);

   void removeLoginHeader();

   @Nullable
   String getLoginInfo();

   @Nullable
   Map<String, String> getLoginInfoMap();

   boolean putLoginInfo(@NotNull String info);

   void removeLoginInfo();

   void setVariable(@Nullable String variable);

   @Nullable
   String getVariable();

   @Nullable
   Object evalJS(@NotNull String jsStr, @NotNull Function1<? super SimpleBindings, Unit> bindingsConfig) throws Exception;

   @Metadata(
      mv = {1, 5, 1},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      @Nullable
      public static BaseSource getSource(@NotNull BaseSource this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         return var0;
      }

      @Nullable
      public static String getLoginJs(@NotNull BaseSource this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         String loginJs = var0.getLoginUrl();
         String var10000;
         if (loginJs == null) {
            var10000 = null;
         } else {
            byte var3;
            if (StringsKt.startsWith$default(loginJs, "@js:", false, 2, (Object)null)) {
               var3 = 4;
               boolean var4 = false;
               var10000 = loginJs.substring(var3);
               Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
            } else if (StringsKt.startsWith$default(loginJs, "<js>", false, 2, (Object)null)) {
               var3 = 4;
               int var6 = StringsKt.lastIndexOf$default((CharSequence)loginJs, "<", 0, false, 6, (Object)null);
               boolean var5 = false;
               var10000 = loginJs.substring(var3, var6);
               Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            } else {
               var10000 = loginJs;
            }
         }

         return var10000;
      }

      public static void login(@NotNull BaseSource this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         String var1 = var0.getLoginJs();
         if (var1 != null) {
            boolean var3 = false;
            boolean var4 = false;
            int var6 = false;
            evalJS$default(var0, var1, (Function1)null, 2, (Object)null);
         }

      }

      @NotNull
      public static HashMap<String, String> getHeaderMap(@NotNull BaseSource this, boolean hasLoginHeader) {
         Intrinsics.checkNotNullParameter(var0, "this");
         HashMap var2 = new HashMap();
         boolean var3 = false;
         boolean var4 = false;
         int var6 = false;
         Map var7 = (Map)var2;
         String var8 = "User-Agent";
         String var9 = AppConst.INSTANCE.getUserAgent();
         boolean var10 = false;
         var7.put(var8, var9);
         String var23 = var0.getHeader();
         boolean var12;
         boolean var24;
         if (var23 != null) {
            var24 = false;
            var10 = false;
            var12 = false;
            Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
            byte var15;
            boolean var16;
            String var10000;
            String var10001;
            boolean var17;
            if (StringsKt.startsWith(var23, "@js:", true)) {
               var15 = 4;
               var16 = false;
               var10001 = var23.substring(var15);
               Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).substring(startIndex)");
               var10000 = String.valueOf(evalJS$default(var0, var10001, (Function1)null, 2, (Object)null));
            } else if (StringsKt.startsWith(var23, "<js>", true)) {
               var15 = 4;
               int var27 = StringsKt.lastIndexOf$default((CharSequence)var23, "<", 0, false, 6, (Object)null);
               var17 = false;
               var10001 = var23.substring(var15, var27);
               Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.Strin…ing(startIndex, endIndex)");
               var10000 = String.valueOf(evalJS$default(var0, var10001, (Function1)null, 2, (Object)null));
            } else {
               var10000 = var23;
            }

            String json$iv = var10000;
            int $i$f$fromJsonObject = false;
            var16 = false;

            Object var29;
            try {
               Companion var30 = Result.Companion;
               int var18 = false;
               int $i$f$genericType = false;
               Type var33 = (new BaseSource$DefaultImpls$getHeaderMap$lambda-4$lambda-2$$inlined$fromJsonObject$1()).getType();
               Intrinsics.checkNotNullExpressionValue(var33, "object : TypeToken<T>() {}.type");
               Object var28 = $this$fromJsonObject$iv.fromJson(json$iv, var33);
               if (!(var28 instanceof Map)) {
                  var28 = null;
               }

               Map var31 = (Map)var28;
               $i$f$genericType = false;
               var29 = Result.constructor-impl(var31);
            } catch (Throwable var22) {
               Companion var19 = Result.Companion;
               boolean var20 = false;
               var29 = Result.constructor-impl(ResultKt.createFailure(var22));
            }

            boolean var25 = false;
            Map var21 = (Map)(Result.isFailure-impl(var29) ? null : var29);
            if (var21 != null) {
               var25 = false;
               $i$f$fromJsonObject = false;
               var17 = false;
               var2.putAll(var21);
            }
         }

         if (hasLoginHeader) {
            var7 = var0.getLoginHeaderMap();
            if (var7 != null) {
               var24 = false;
               var10 = false;
               var12 = false;
               var2.putAll(var7);
            }
         }

         return var2;
      }

      // $FF: synthetic method
      public static HashMap getHeaderMap$default(BaseSource var0, boolean var1, int var2, Object var3) {
         if (var3 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getHeaderMap");
         } else {
            if ((var2 & 1) != 0) {
               var1 = false;
            }

            return var0.getHeaderMap(var1);
         }
      }

      @Nullable
      public static String getLoginHeader(@NotNull BaseSource this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         CacheManager cacheInstance = new CacheManager(var0.getUserNameSpace());
         return cacheInstance.get(Intrinsics.stringPlus("loginHeader_", var0.getKey()));
      }

      @Nullable
      public static Map<String, String> getLoginHeaderMap(@NotNull BaseSource this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         String var2 = var0.getLoginHeader();
         if (var2 == null) {
            return null;
         } else {
            String cache = var2;
            Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
            int $i$f$fromJsonObject = false;
            boolean var4 = false;

            Object var5;
            try {
               Companion var11 = Result.Companion;
               int var6 = false;
               int $i$f$genericType = false;
               Type var14 = (new BaseSource$DefaultImpls$getLoginHeaderMap$$inlined$fromJsonObject$1()).getType();
               Intrinsics.checkNotNullExpressionValue(var14, "object : TypeToken<T>() {}.type");
               Object var10000 = $this$fromJsonObject$iv.fromJson(cache, var14);
               if (!(var10000 instanceof Map)) {
                  var10000 = null;
               }

               Map var12 = (Map)var10000;
               $i$f$genericType = false;
               var5 = Result.constructor-impl(var12);
            } catch (Throwable var9) {
               Companion var7 = Result.Companion;
               boolean var8 = false;
               var5 = Result.constructor-impl(ResultKt.createFailure(var9));
            }

            $i$f$fromJsonObject = false;
            return (Map)(Result.isFailure-impl(var5) ? null : var5);
         }
      }

      public static void putLoginHeader(@NotNull BaseSource this, @NotNull String header) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(header, "header");
         CacheManager cacheInstance = new CacheManager(var0.getUserNameSpace());
         CacheManager.put$default(cacheInstance, Intrinsics.stringPlus("loginHeader_", var0.getKey()), header, 0, 4, (Object)null);
      }

      public static void removeLoginHeader(@NotNull BaseSource this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         CacheManager cacheInstance = new CacheManager(var0.getUserNameSpace());
         cacheInstance.delete(Intrinsics.stringPlus("loginHeader_", var0.getKey()));
      }

      @Nullable
      public static String getLoginInfo(@NotNull BaseSource this) {
         Intrinsics.checkNotNullParameter(var0, "this");

         try {
            byte[] key = StringsKt.encodeToByteArray$default(AppConst.INSTANCE.getUserAgent(), 0, 8, false, 4, (Object)null);
            CacheManager cacheInstance = new CacheManager(var0.getUserNameSpace());
            String var4 = cacheInstance.get(Intrinsics.stringPlus("userInfo_", var0.getKey()));
            if (var4 == null) {
               return null;
            } else {
               String var5 = EncoderUtils.INSTANCE.base64Decode(var4, 0);
               Charset var6 = Charsets.UTF_8;
               boolean var7 = false;
               if (var5 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               } else {
                  byte[] var10000 = var5.getBytes(var6);
                  Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).getBytes(charset)");
                  byte[] encodeBytes = var10000;
                  byte[] var11 = EncoderUtils.decryptAES$default(EncoderUtils.INSTANCE, encodeBytes, key, (String)null, (byte[])null, 12, (Object)null);
                  if (var11 == null) {
                     return null;
                  } else {
                     byte[] decodeBytes = var11;
                     boolean var12 = false;
                     return new String(decodeBytes, Charsets.UTF_8);
                  }
               }
            }
         } catch (Exception var8) {
            var0.log(Intrinsics.stringPlus("获取登陆信息出错 ", var8.getLocalizedMessage()));
            return null;
         }
      }

      @Nullable
      public static Map<String, String> getLoginInfoMap(@NotNull BaseSource this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
         String json$iv = var0.getLoginInfo();
         int $i$f$fromJsonObject = false;
         boolean var4 = false;

         Object var5;
         try {
            Companion var11 = Result.Companion;
            int var6 = false;
            int $i$f$genericType = false;
            Type var14 = (new BaseSource$DefaultImpls$getLoginInfoMap$$inlined$fromJsonObject$1()).getType();
            Intrinsics.checkNotNullExpressionValue(var14, "object : TypeToken<T>() {}.type");
            Object var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var14);
            if (!(var10000 instanceof Map)) {
               var10000 = null;
            }

            Map var12 = (Map)var10000;
            $i$f$genericType = false;
            var5 = Result.constructor-impl(var12);
         } catch (Throwable var9) {
            Companion var7 = Result.Companion;
            boolean var8 = false;
            var5 = Result.constructor-impl(ResultKt.createFailure(var9));
         }

         boolean var10 = false;
         return (Map)(Result.isFailure-impl(var5) ? null : var5);
      }

      public static boolean putLoginInfo(@NotNull BaseSource this, @NotNull String info) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(info, "info");

         boolean var2;
         try {
            byte[] key = StringsKt.encodeToByteArray$default(AppConst.INSTANCE.getUserAgent(), 0, 8, false, 4, (Object)null);
            EncoderUtils var10000 = EncoderUtils.INSTANCE;
            Charset var5 = Charsets.UTF_8;
            boolean var6 = false;
            byte[] var10001 = info.getBytes(var5);
            Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).getBytes(charset)");
            byte[] encodeBytes = EncoderUtils.encryptAES$default(var10000, var10001, key, (String)null, (byte[])null, 12, (Object)null);
            String encodeStr = Base64.encodeToString(encodeBytes, 0);
            CacheManager cacheInstance = new CacheManager(var0.getUserNameSpace());
            String var10 = Intrinsics.stringPlus("userInfo_", var0.getKey());
            Intrinsics.checkNotNullExpressionValue(encodeStr, "encodeStr");
            CacheManager.put$default(cacheInstance, var10, encodeStr, 0, 4, (Object)null);
            var2 = true;
         } catch (Exception var7) {
            var0.log(Intrinsics.stringPlus("保存登陆信息出错 ", var7.getLocalizedMessage()));
            var2 = false;
         }

         return var2;
      }

      public static void removeLoginInfo(@NotNull BaseSource this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         CacheManager cacheInstance = new CacheManager(var0.getUserNameSpace());
         cacheInstance.delete(Intrinsics.stringPlus("userInfo_", var0.getKey()));
      }

      public static void setVariable(@NotNull BaseSource this, @Nullable String variable) {
         Intrinsics.checkNotNullParameter(var0, "this");
         CacheManager cacheInstance = new CacheManager(var0.getUserNameSpace());
         if (variable != null) {
            CacheManager.put$default(cacheInstance, Intrinsics.stringPlus("sourceVariable_", var0.getKey()), variable, 0, 4, (Object)null);
         } else {
            cacheInstance.delete(Intrinsics.stringPlus("sourceVariable_", var0.getKey()));
         }

      }

      @Nullable
      public static String getVariable(@NotNull BaseSource this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         CacheManager cacheInstance = new CacheManager(var0.getUserNameSpace());
         return cacheInstance.get(Intrinsics.stringPlus("sourceVariable_", var0.getKey()));
      }

      @Nullable
      public static Object evalJS(@NotNull BaseSource this, @NotNull String jsStr, @NotNull Function1<? super SimpleBindings, Unit> bindingsConfig) throws Exception {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(jsStr, "jsStr");
         Intrinsics.checkNotNullParameter(bindingsConfig, "bindingsConfig");
         SimpleBindings bindings = new SimpleBindings();
         boolean var5 = false;
         boolean var6 = false;
         bindingsConfig.invoke(bindings);
         Map var4 = (Map)bindings;
         String var8 = "java";
         var6 = false;
         var4.put(var8, var0);
         var4 = (Map)bindings;
         var8 = "source";
         var6 = false;
         var4.put(var8, var0);
         var4 = (Map)bindings;
         var8 = "baseUrl";
         String var9 = var0.getKey();
         boolean var7 = false;
         var4.put(var8, var9);
         var4 = (Map)bindings;
         var8 = "cookie";
         CookieStore var10 = new CookieStore(var0.getUserNameSpace());
         var7 = false;
         var4.put(var8, var10);
         var4 = (Map)bindings;
         var8 = "cache";
         CacheManager var11 = new CacheManager(var0.getUserNameSpace());
         var7 = false;
         var4.put(var8, var11);
         return AppConst.INSTANCE.getSCRIPT_ENGINE().eval(jsStr, (Bindings)bindings);
      }

      // $FF: synthetic method
      public static Object evalJS$default(BaseSource var0, String var1, Function1 var2, int var3, Object var4) throws Exception {
         if (var4 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: evalJS");
         } else {
            if ((var3 & 2) != 0) {
               var2 = (Function1)null.INSTANCE;
            }

            return var0.evalJS(var1, var2);
         }
      }

      @Nullable
      public static byte[] aesBase64DecodeToByteArray(@NotNull BaseSource this, @NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.aesBase64DecodeToByteArray((JsExtensions)var0, str, key, transformation, iv);
      }

      @Nullable
      public static String aesBase64DecodeToString(@NotNull BaseSource this, @NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.aesBase64DecodeToString((JsExtensions)var0, str, key, transformation, iv);
      }

      @Nullable
      public static String aesDecodeArgsBase64Str(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(padding, "padding");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.aesDecodeArgsBase64Str((JsExtensions)var0, data, key, mode, padding, iv);
      }

      @Nullable
      public static byte[] aesDecodeToByteArray(@NotNull BaseSource this, @NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.aesDecodeToByteArray((JsExtensions)var0, str, key, transformation, iv);
      }

      @Nullable
      public static String aesDecodeToString(@NotNull BaseSource this, @NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.aesDecodeToString((JsExtensions)var0, str, key, transformation, iv);
      }

      @Nullable
      public static String aesEncodeArgsBase64Str(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(padding, "padding");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.aesEncodeArgsBase64Str((JsExtensions)var0, data, key, mode, padding, iv);
      }

      @Nullable
      public static byte[] aesEncodeToBase64ByteArray(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.aesEncodeToBase64ByteArray((JsExtensions)var0, data, key, transformation, iv);
      }

      @Nullable
      public static String aesEncodeToBase64String(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.aesEncodeToBase64String((JsExtensions)var0, data, key, transformation, iv);
      }

      @Nullable
      public static byte[] aesEncodeToByteArray(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.aesEncodeToByteArray((JsExtensions)var0, data, key, transformation, iv);
      }

      @Nullable
      public static String aesEncodeToString(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.aesEncodeToString((JsExtensions)var0, data, key, transformation, iv);
      }

      @Nullable
      public static String ajax(@NotNull BaseSource this, @NotNull String urlStr) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         return JsExtensions.DefaultImpls.ajax((JsExtensions)var0, urlStr);
      }

      @NotNull
      public static StrResponse[] ajaxAll(@NotNull BaseSource this, @NotNull String[] urlList) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlList, "urlList");
         return JsExtensions.DefaultImpls.ajaxAll((JsExtensions)var0, urlList);
      }

      @NotNull
      public static String androidId(@NotNull BaseSource this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         return JsExtensions.DefaultImpls.androidId((JsExtensions)var0);
      }

      @NotNull
      public static String base64Decode(@NotNull BaseSource this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return JsExtensions.DefaultImpls.base64Decode((JsExtensions)var0, str);
      }

      @NotNull
      public static String base64Decode(@NotNull BaseSource this, @NotNull String str, int flags) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return JsExtensions.DefaultImpls.base64Decode((JsExtensions)var0, str, flags);
      }

      @Nullable
      public static byte[] base64DecodeToByteArray(@NotNull BaseSource this, @Nullable String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         return JsExtensions.DefaultImpls.base64DecodeToByteArray((JsExtensions)var0, str);
      }

      @Nullable
      public static byte[] base64DecodeToByteArray(@NotNull BaseSource this, @Nullable String str, int flags) {
         Intrinsics.checkNotNullParameter(var0, "this");
         return JsExtensions.DefaultImpls.base64DecodeToByteArray((JsExtensions)var0, str, flags);
      }

      @Nullable
      public static String base64Encode(@NotNull BaseSource this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return JsExtensions.DefaultImpls.base64Encode((JsExtensions)var0, str);
      }

      @Nullable
      public static String base64Encode(@NotNull BaseSource this, @NotNull String str, int flags) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return JsExtensions.DefaultImpls.base64Encode((JsExtensions)var0, str, flags);
      }

      @Nullable
      public static String cacheFile(@NotNull BaseSource this, @NotNull String urlStr) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         return JsExtensions.DefaultImpls.cacheFile((JsExtensions)var0, urlStr);
      }

      @Nullable
      public static String cacheFile(@NotNull BaseSource this, @NotNull String urlStr, int saveTime) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         return JsExtensions.DefaultImpls.cacheFile((JsExtensions)var0, urlStr, saveTime);
      }

      @NotNull
      public static StrResponse connect(@NotNull BaseSource this, @NotNull String urlStr) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         return JsExtensions.DefaultImpls.connect((JsExtensions)var0, urlStr);
      }

      @NotNull
      public static StrResponse connect(@NotNull BaseSource this, @NotNull String urlStr, @Nullable String header) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         return JsExtensions.DefaultImpls.connect((JsExtensions)var0, urlStr, header);
      }

      public static void deleteFile(@NotNull BaseSource this, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(path, "path");
         JsExtensions.DefaultImpls.deleteFile((JsExtensions)var0, path);
      }

      @Nullable
      public static String desBase64DecodeToString(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.desBase64DecodeToString((JsExtensions)var0, data, key, transformation, iv);
      }

      @Nullable
      public static String desDecodeToString(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.desDecodeToString((JsExtensions)var0, data, key, transformation, iv);
      }

      @Nullable
      public static String desEncodeToBase64String(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.desEncodeToBase64String((JsExtensions)var0, data, key, transformation, iv);
      }

      @Nullable
      public static String desEncodeToString(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.desEncodeToString((JsExtensions)var0, data, key, transformation, iv);
      }

      @Nullable
      public static String digestBase64Str(@NotNull BaseSource this, @NotNull String data, @NotNull String algorithm) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(algorithm, "algorithm");
         return JsExtensions.DefaultImpls.digestBase64Str((JsExtensions)var0, data, algorithm);
      }

      @Nullable
      public static String digestHex(@NotNull BaseSource this, @NotNull String data, @NotNull String algorithm) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(algorithm, "algorithm");
         return JsExtensions.DefaultImpls.digestHex((JsExtensions)var0, data, algorithm);
      }

      @NotNull
      public static String downloadFile(@NotNull BaseSource this, @NotNull String content, @NotNull String url) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(content, "content");
         Intrinsics.checkNotNullParameter(url, "url");
         return JsExtensions.DefaultImpls.downloadFile((JsExtensions)var0, content, url);
      }

      @NotNull
      public static String encodeURI(@NotNull BaseSource this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return JsExtensions.DefaultImpls.encodeURI((JsExtensions)var0, str);
      }

      @NotNull
      public static String encodeURI(@NotNull BaseSource this, @NotNull String str, @NotNull String enc) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         Intrinsics.checkNotNullParameter(enc, "enc");
         return JsExtensions.DefaultImpls.encodeURI((JsExtensions)var0, str, enc);
      }

      @NotNull
      public static Response get(@NotNull BaseSource this, @NotNull String urlStr, @NotNull Map<String, String> headers) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         Intrinsics.checkNotNullParameter(headers, "headers");
         return JsExtensions.DefaultImpls.get((JsExtensions)var0, urlStr, headers);
      }

      @NotNull
      public static String getCookie(@NotNull BaseSource this, @NotNull String tag, @Nullable String key) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(tag, "tag");
         return JsExtensions.DefaultImpls.getCookie((JsExtensions)var0, tag, key);
      }

      @NotNull
      public static File getFile(@NotNull BaseSource this, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(path, "path");
         return JsExtensions.DefaultImpls.getFile((JsExtensions)var0, path);
      }

      @NotNull
      public static String getTxtInFolder(@NotNull BaseSource this, @NotNull String unzipPath) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(unzipPath, "unzipPath");
         return JsExtensions.DefaultImpls.getTxtInFolder((JsExtensions)var0, unzipPath);
      }

      @Nullable
      public static byte[] getZipByteArrayContent(@NotNull BaseSource this, @NotNull String url, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(url, "url");
         Intrinsics.checkNotNullParameter(path, "path");
         return JsExtensions.DefaultImpls.getZipByteArrayContent((JsExtensions)var0, url, path);
      }

      @NotNull
      public static String getZipStringContent(@NotNull BaseSource this, @NotNull String url, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(url, "url");
         Intrinsics.checkNotNullParameter(path, "path");
         return JsExtensions.DefaultImpls.getZipStringContent((JsExtensions)var0, url, path);
      }

      @NotNull
      public static String getZipStringContent(@NotNull BaseSource this, @NotNull String url, @NotNull String path, @NotNull String charsetName) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(url, "url");
         Intrinsics.checkNotNullParameter(path, "path");
         Intrinsics.checkNotNullParameter(charsetName, "charsetName");
         return JsExtensions.DefaultImpls.getZipStringContent((JsExtensions)var0, url, path, charsetName);
      }

      @NotNull
      public static Response head(@NotNull BaseSource this, @NotNull String urlStr, @NotNull Map<String, String> headers) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         Intrinsics.checkNotNullParameter(headers, "headers");
         return JsExtensions.DefaultImpls.head((JsExtensions)var0, urlStr, headers);
      }

      @NotNull
      public static String htmlFormat(@NotNull BaseSource this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return JsExtensions.DefaultImpls.htmlFormat((JsExtensions)var0, str);
      }

      @NotNull
      public static String importScript(@NotNull BaseSource this, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(path, "path");
         return JsExtensions.DefaultImpls.importScript((JsExtensions)var0, path);
      }

      @NotNull
      public static String log(@NotNull BaseSource this, @NotNull String msg) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(msg, "msg");
         return JsExtensions.DefaultImpls.log((JsExtensions)var0, msg);
      }

      public static void logType(@NotNull BaseSource this, @Nullable Object any) {
         Intrinsics.checkNotNullParameter(var0, "this");
         JsExtensions.DefaultImpls.logType((JsExtensions)var0, any);
      }

      public static void longToast(@NotNull BaseSource this, @Nullable Object msg) {
         Intrinsics.checkNotNullParameter(var0, "this");
         JsExtensions.DefaultImpls.longToast((JsExtensions)var0, msg);
      }

      @NotNull
      public static String md5Encode(@NotNull BaseSource this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return JsExtensions.DefaultImpls.md5Encode((JsExtensions)var0, str);
      }

      @NotNull
      public static String md5Encode16(@NotNull BaseSource this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return JsExtensions.DefaultImpls.md5Encode16((JsExtensions)var0, str);
      }

      @NotNull
      public static Response post(@NotNull BaseSource this, @NotNull String urlStr, @NotNull String body, @NotNull Map<String, String> headers) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         Intrinsics.checkNotNullParameter(body, "body");
         Intrinsics.checkNotNullParameter(headers, "headers");
         return JsExtensions.DefaultImpls.post((JsExtensions)var0, urlStr, body, headers);
      }

      @Nullable
      public static QueryTTF queryBase64TTF(@NotNull BaseSource this, @Nullable String base64) {
         Intrinsics.checkNotNullParameter(var0, "this");
         return JsExtensions.DefaultImpls.queryBase64TTF((JsExtensions)var0, base64);
      }

      @Nullable
      public static QueryTTF queryTTF(@NotNull BaseSource this, @Nullable String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         return JsExtensions.DefaultImpls.queryTTF((JsExtensions)var0, str);
      }

      @NotNull
      public static String randomUUID(@NotNull BaseSource this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         return JsExtensions.DefaultImpls.randomUUID((JsExtensions)var0);
      }

      @Nullable
      public static byte[] readFile(@NotNull BaseSource this, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(path, "path");
         return JsExtensions.DefaultImpls.readFile((JsExtensions)var0, path);
      }

      @NotNull
      public static String readTxtFile(@NotNull BaseSource this, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(path, "path");
         return JsExtensions.DefaultImpls.readTxtFile((JsExtensions)var0, path);
      }

      @NotNull
      public static String readTxtFile(@NotNull BaseSource this, @NotNull String path, @NotNull String charsetName) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(path, "path");
         Intrinsics.checkNotNullParameter(charsetName, "charsetName");
         return JsExtensions.DefaultImpls.readTxtFile((JsExtensions)var0, path, charsetName);
      }

      @NotNull
      public static String replaceFont(@NotNull BaseSource this, @NotNull String text, @Nullable QueryTTF font1, @Nullable QueryTTF font2) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(text, "text");
         return JsExtensions.DefaultImpls.replaceFont((JsExtensions)var0, text, font1, font2);
      }

      @NotNull
      public static String timeFormat(@NotNull BaseSource this, long time) {
         Intrinsics.checkNotNullParameter(var0, "this");
         return JsExtensions.DefaultImpls.timeFormat((JsExtensions)var0, time);
      }

      @Nullable
      public static String timeFormatUTC(@NotNull BaseSource this, long time, @NotNull String format, int sh) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(format, "format");
         return JsExtensions.DefaultImpls.timeFormatUTC((JsExtensions)var0, time, format, sh);
      }

      public static void toast(@NotNull BaseSource this, @Nullable Object msg) {
         Intrinsics.checkNotNullParameter(var0, "this");
         JsExtensions.DefaultImpls.toast((JsExtensions)var0, msg);
      }

      @Nullable
      public static String tripleDESDecodeArgsBase64Str(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(padding, "padding");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.tripleDESDecodeArgsBase64Str((JsExtensions)var0, data, key, mode, padding, iv);
      }

      @Nullable
      public static String tripleDESDecodeStr(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(padding, "padding");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.tripleDESDecodeStr((JsExtensions)var0, data, key, mode, padding, iv);
      }

      @Nullable
      public static String tripleDESEncodeArgsBase64Str(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(padding, "padding");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.tripleDESEncodeArgsBase64Str((JsExtensions)var0, data, key, mode, padding, iv);
      }

      @Nullable
      public static String tripleDESEncodeBase64Str(@NotNull BaseSource this, @NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(padding, "padding");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return JsExtensions.DefaultImpls.tripleDESEncodeBase64Str((JsExtensions)var0, data, key, mode, padding, iv);
      }

      @NotNull
      public static String unzipFile(@NotNull BaseSource this, @NotNull String zipPath) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(zipPath, "zipPath");
         return JsExtensions.DefaultImpls.unzipFile((JsExtensions)var0, zipPath);
      }

      @NotNull
      public static String utf8ToGbk(@NotNull BaseSource this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return JsExtensions.DefaultImpls.utf8ToGbk((JsExtensions)var0, str);
      }

      @Nullable
      public static String webView(@NotNull BaseSource this, @Nullable String html, @Nullable String url, @Nullable String js) {
         Intrinsics.checkNotNullParameter(var0, "this");
         return JsExtensions.DefaultImpls.webView((JsExtensions)var0, html, url, js);
      }
   }
}
