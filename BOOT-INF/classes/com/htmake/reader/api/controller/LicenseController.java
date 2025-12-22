package com.htmake.reader.api.controller;

import com.htmake.reader.api.ReturnData;
import com.htmake.reader.entity.ActiveLicense;
import com.htmake.reader.entity.License;
import com.htmake.reader.utils.ExtKt;
import com.htmake.reader.utils.VertExtKt;
import io.legado.app.utils.ACache;
import io.legado.app.utils.Base64;
import io.legado.app.utils.EncoderUtils;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.SocketAddress;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.Result.Companion;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.CoroutineExceptionHandler.Key;
import kotlinx.coroutines.slf4j.MDCContext;
import mu.KLogger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0019\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0019\u0010 \u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0019\u0010!\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0019\u0010\"\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0019\u0010#\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0019\u0010$\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0019\u0010%\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0019\u0010&\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0011\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"},
   d2 = {"Lcom/htmake/reader/api/controller/LicenseController;", "Lcom/htmake/reader/api/controller/BaseController;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "backupFileNames", "", "", "getBackupFileNames", "()[Ljava/lang/String;", "backupFileNames$delegate", "Lkotlin/Lazy;", "privateKeyContent", "tryCodeCache", "Lio/legado/app/utils/ACache;", "webClient", "Lio/vertx/ext/web/client/WebClient;", "getWebClient", "()Lio/vertx/ext/web/client/WebClient;", "webClient$delegate", "activateLicense", "Lcom/htmake/reader/api/ReturnData;", "context", "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkLicense", "", "license", "Lcom/htmake/reader/entity/License;", "(Lcom/htmake/reader/entity/License;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decryptLicense", "generateKeys", "generateLicense", "getLicense", "importLicense", "isHostValid", "isLicenseValid", "sendCodeToEmail", "supplyLicense", "reader-pro"}
)
public final class LicenseController extends BaseController {
   @NotNull
   private final Lazy webClient$delegate;
   @NotNull
   private String privateKeyContent;
   @NotNull
   private ACache tryCodeCache;
   @NotNull
   private final Lazy backupFileNames$delegate;

   public LicenseController(@NotNull CoroutineContext coroutineContext) {
      Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
      super(coroutineContext);
      this.webClient$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      this.privateKeyContent = "";
      this.tryCodeCache = ACache.Companion.get((String)"tryCodeCache", 2000000L, 10000);
      this.backupFileNames$delegate = LazyKt.lazy((Function0)null.INSTANCE);
   }

   private final WebClient getWebClient() {
      Lazy var2 = this.webClient$delegate;
      boolean var4 = false;
      Object var1 = var2.getValue();
      Intrinsics.checkNotNullExpressionValue(var1, "<get-webClient>(...)");
      return (WebClient)var1;
   }

   @NotNull
   public final String[] getBackupFileNames() {
      Lazy var1 = this.backupFileNames$delegate;
      boolean var3 = false;
      return (String[])var1.getValue();
   }

   @Nullable
   public final Object getLicense(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      License license = ExtKt.getInstalledLicense$default(false, 1, (Object)null);
      return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("license", license)), (String)null, 2, (Object)null);
   }

   @Nullable
   public final Object importLicense(@NotNull RoutingContext context, @NotNull Continuation<? super Unit> $completion) {
      Object $continuation;
      label42: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label42;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return LicenseController.this.importLicense((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      final ReturnData returnData;
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         returnData = new ReturnData();
         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).L$2 = returnData;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = this.checkAuth(context, (Continuation)$continuation);
         if (var10000 == var10) {
            return var10;
         }
         break;
      case 1:
         returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
         context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
         this = (LicenseController)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (!(Boolean)var10000) {
         VertExtKt.success(context, ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用"));
         return Unit.INSTANCE;
      } else if (!this.checkManagerAuth(context)) {
         VertExtKt.success(context, ReturnData.setData$default(returnData, "NEED_SECURE_KEY", (String)null, 2, (Object)null).setErrorMsg("请输入管理密码"));
         return Unit.INSTANCE;
      } else {
         String var5 = context.getBodyAsJson().getString("content");
         final String content = var5 == null ? "" : var5;
         CharSequence var11 = (CharSequence)content;
         boolean $i$f$CoroutineExceptionHandler = false;
         boolean var7 = false;
         if (var11.length() == 0) {
            VertExtKt.success(context, returnData.setErrorMsg("请输入密钥"));
            return Unit.INSTANCE;
         } else {
             License license = ExtKt.decryptToLicense(content);
             String[] var31 = new String[]{"data", "license"};
             ExtKt.saveStorage$default(var31, content, false, ".key", 4, (Object)null);
             VertExtKt.success(context, ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("license", license)), (String)null, 2, (Object)null));
             return Unit.INSTANCE;
         }
      }
   }

   @Nullable
   public final Object generateKeys(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      KeyPair keyPair = EncoderUtils.INSTANCE.generateKeys();
      Pair[] var5 = new Pair[]{TuplesKt.to("publicKey", Base64.encodeToString(keyPair.getPublic().getEncoded(), 2)), TuplesKt.to("privateKey", Base64.encodeToString(keyPair.getPrivate().getEncoded(), 2))};
      return ReturnData.setData$default(returnData, MapsKt.mapOf(var5), (String)null, 2, (Object)null);
   }

   @Nullable
   public final Object generateLicense(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      String host = null;
      long expiredAt = 0L;
      int userMaxLimit = false;
      boolean openApi = false;
      long simpleWebExpiredAt = 0L;
      int instances = false;
      String type = null;
      String key = null;
      String code = null;
      String var15;
      boolean var18;
      int userMaxLimit;
      int instances;
      if (context.request().method() == HttpMethod.POST) {
         var15 = context.getBodyAsJson().getString("host");
         host = var15 == null ? "" : var15;
         Long var22 = context.getBodyAsJson().getLong("expiredAt");
         expiredAt = var22 == null ? 0L : var22;
         Integer var23 = context.getBodyAsJson().getInteger("userMaxLimit");
         userMaxLimit = var23 == null ? 15 : var23;
         Boolean var24 = context.getBodyAsJson().getBoolean("openApi");
         openApi = var24 == null ? false : var24;
         var22 = context.getBodyAsJson().getLong("simpleWebExpiredAt");
         simpleWebExpiredAt = var22 == null ? 0L : var22;
         var23 = context.getBodyAsJson().getInteger("instances");
         instances = var23 == null ? 1 : var23;
         var15 = context.getBodyAsJson().getString("type");
         type = var15 == null ? "" : var15;
         var15 = context.getBodyAsJson().getString("key");
         key = var15 == null ? "" : var15;
         var15 = context.getBodyAsJson().getString("code");
         code = var15 == null ? "" : var15;
      } else {
         List var16 = context.queryParam("host");
         Intrinsics.checkNotNullExpressionValue(var16, "context.queryParam(\"host\")");
         var15 = (String)CollectionsKt.firstOrNull(var16);
         host = var15 == null ? "" : var15;
         var16 = context.queryParam("expiredAt");
         Intrinsics.checkNotNullExpressionValue(var16, "context.queryParam(\"expiredAt\")");
         var15 = (String)CollectionsKt.firstOrNull(var16);
         long var10000;
         Long var25;
         if (var15 == null) {
            var10000 = 0L;
         } else {
            var18 = false;
            var25 = Boxing.boxLong(Long.parseLong(var15));
            var10000 = var25 == null ? 0L : var25;
         }

         expiredAt = var10000;
         var16 = context.queryParam("userMaxLimit");
         Intrinsics.checkNotNullExpressionValue(var16, "context.queryParam(\"userMaxLimit\")");
         var15 = (String)CollectionsKt.firstOrNull(var16);
         int var26;
         Integer var27;
         if (var15 == null) {
            var26 = 15;
         } else {
            var18 = false;
            var27 = Boxing.boxInt(Integer.parseInt(var15));
            var26 = var27 == null ? 15 : var27;
         }

         userMaxLimit = var26;
         var16 = context.queryParam("openApi");
         Intrinsics.checkNotNullExpressionValue(var16, "context.queryParam(\"openApi\")");
         var15 = (String)CollectionsKt.firstOrNull(var16);
         boolean var30;
         if (var15 == null) {
            var30 = false;
         } else {
            var18 = false;
            Boolean var29 = Boxing.boxBoolean(Boolean.parseBoolean(var15));
            var30 = var29 == null ? false : var29;
         }

         openApi = var30;
         var16 = context.queryParam("simpleWebExpiredAt");
         Intrinsics.checkNotNullExpressionValue(var16, "context.queryParam(\"simpleWebExpiredAt\")");
         var15 = (String)CollectionsKt.firstOrNull(var16);
         if (var15 == null) {
            var10000 = 0L;
         } else {
            var18 = false;
            var25 = Boxing.boxLong(Long.parseLong(var15));
            var10000 = var25 == null ? 0L : var25;
         }

         simpleWebExpiredAt = var10000;
         var16 = context.queryParam("instances");
         Intrinsics.checkNotNullExpressionValue(var16, "context.queryParam(\"instances\")");
         var15 = (String)CollectionsKt.firstOrNull(var16);
         if (var15 == null) {
            var26 = 1;
         } else {
            var18 = false;
            var27 = Boxing.boxInt(Integer.parseInt(var15));
            var26 = var27 == null ? 1 : var27;
         }

         instances = var26;
         var16 = context.queryParam("type");
         Intrinsics.checkNotNullExpressionValue(var16, "context.queryParam(\"type\")");
         var15 = (String)CollectionsKt.firstOrNull(var16);
         type = var15 == null ? "" : var15;
         var16 = context.queryParam("key");
         Intrinsics.checkNotNullExpressionValue(var16, "context.queryParam(\"key\")");
         var15 = (String)CollectionsKt.firstOrNull(var16);
         key = var15 == null ? "" : var15;
         var16 = context.queryParam("code");
         Intrinsics.checkNotNullExpressionValue(var16, "context.queryParam(\"code\")");
         var15 = (String)CollectionsKt.firstOrNull(var16);
         code = var15 == null ? "" : var15;
      }

      CharSequence var36 = (CharSequence)host;
      boolean var34 = false;
      boolean var17 = false;
      if (var36.length() == 0) {
         return returnData.setErrorMsg("请输入域名");
      } else if (!"Pvkp7tMQJpi4kWBE".equals(key)) {
         return returnData.setErrorMsg("参数错误");
      } else {
         License license = new License(host, userMaxLimit, expiredAt, openApi, simpleWebExpiredAt, instances, type, (String)null, (String)null, false, (Long)null, 1408, (DefaultConstructorMarker)null);
         CharSequence var37 = (CharSequence)code;
         var17 = false;
         var18 = false;
         if (var37.length() != 0) {
            license.setCode(code);
         }

         String licenseContent = ExtKt.jsonEncode$default(license, false, 2, (Object)null);
         CharSequence var28 = (CharSequence)this.privateKeyContent;
         var18 = false;
         if (var28.length() == 0) {
            String[] var33 = new String[]{"data", "privateKey"};
            String var31 = ExtKt.getStorage(var33, ".key");
            this.privateKeyContent = var31 == null ? "" : var31;
         }

         PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate((KeySpec)(new PKCS8EncodedKeySpec(Base64.decode((String)this.privateKeyContent, 2))));
         EncoderUtils var38 = EncoderUtils.INSTANCE;
         Intrinsics.checkNotNullExpressionValue(privateKey, "privateKey");
         String licenseKey = EncoderUtils.encryptSegmentByPrivateKey$default(var38, licenseContent, privateKey, 0, 4, (Object)null);
         Pair[] var19 = new Pair[]{TuplesKt.to("host", host), TuplesKt.to("key", licenseKey)};
         return ReturnData.setData$default(returnData, MapsKt.mapOf(var19), (String)null, 2, (Object)null);
      }
   }

   @Nullable
   public final Object isHostValid(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      String host = null;
      String var5;
      if (context.request().method() == HttpMethod.POST) {
         var5 = context.getBodyAsJson().getString("host");
         Intrinsics.checkNotNullExpressionValue(var5, "context.bodyAsJson.getString(\"host\")");
         host = var5;
      } else {
         List var6 = context.queryParam("host");
         Intrinsics.checkNotNullExpressionValue(var6, "context.queryParam(\"host\")");
         var5 = (String)CollectionsKt.firstOrNull(var6);
         host = var5 == null ? "" : var5;
      }

      License license = ExtKt.getInstalledLicense$default(false, 1, (Object)null);
      return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("isValid", Boxing.boxBoolean(license.validHost(host)))), (String)null, 2, (Object)null);
   }

   @Nullable
   public final Object decryptLicense(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      String var5 = context.getBodyAsJson().getString("content");
      String content = var5 == null ? "" : var5;
      CharSequence var8 = (CharSequence)content;
      boolean var6 = false;
      boolean var7 = false;
      if (var8.length() == 0) {
         return returnData.setErrorMsg("请输入密钥");
      } else {
         License license = ExtKt.decryptToLicense(content);
         return license == null ? returnData.setErrorMsg("密钥错误") : ReturnData.setData$default(returnData, license, (String)null, 2, (Object)null);
      }
   }

   @Nullable
   public final Object activateLicense(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      String var5 = context.getBodyAsJson().getString("content");
      String content = var5 == null ? "" : var5;
      CharSequence var16 = (CharSequence)content;
      boolean var6 = false;
      boolean var7 = false;
      if (var16.length() == 0) {
         return returnData.setErrorMsg("请输入密钥");
      } else {
         License license = ExtKt.decryptToLicense(content);
         if (license == null) {
            return returnData.setErrorMsg("密钥错误");
         } else {
            String[] var19 = new String[]{"data", "activeLicense"};
            JsonArray activeLicenseList = ExtKt.asJsonArray(ExtKt.getStorage$default(var19, (String)null, 2, (Object)null));
            if (activeLicenseList == null) {
               activeLicenseList = new JsonArray();
            }

            int activeTimes = 0;
            int var8 = 0;
            int var9 = activeLicenseList.size();
            if (var8 < var9) {
               do {
                  int i = var8++;
                  ActiveLicense _activeLicense = (ActiveLicense)activeLicenseList.getJsonObject(i).mapTo(ActiveLicense.class);
                  if (_activeLicense.getType().equals(license.getType()) && _activeLicense.getCode().equals(license.getCode())) {
                     ++activeTimes;
                  }
               } while(var8 < var9);
            }

            if (activeTimes >= license.getInstances()) {
               return returnData.setErrorMsg("密钥已超过最大使用次数");
            } else {
               String ip;
               label84: {
                  ip = null;
                  ip = context.request().getHeader("X-Real-IP");
                  if (ip != null) {
                     CharSequence var22 = (CharSequence)ip;
                     boolean var25 = false;
                     if (var22.length() != 0) {
                        break label84;
                     }
                  }

                  SocketAddress var23 = context.request().remoteAddress();
                  String var10000;
                  if (var23 == null) {
                     var10000 = "";
                  } else {
                     String var26 = var23.host();
                     var10000 = var26 == null ? "" : var26;
                  }

                  ip = var10000;
               }

               license.setVerified(true);
               license.setVerifyTime(Boxing.boxLong(System.currentTimeMillis()));
               String var24 = UUID.randomUUID().toString();
               Intrinsics.checkNotNullExpressionValue(var24, "randomUUID().toString()");
               license.setId(var24);
               ActiveLicense var27 = license.toActiveLicense();
               boolean var28 = false;
               boolean var12 = false;
               int var14 = false;
               var27.setActiveOrder(activeTimes + 1);
               var27.setActiveTime(System.currentTimeMillis());
               var27.setActiveIp(ip);
               var27.setActiveEmail("");
               JsonObject var29 = JsonObject.mapFrom(var27);
               Intrinsics.checkNotNullExpressionValue(var29, "mapFrom(activeLicense)");
               activeLicenseList.add(var29);
               LicenseControllerKt.access$getLogger$p().info("activeLicenseList: {}", activeLicenseList);
               String[] var30 = new String[]{"data", "activeLicense"};
               ExtKt.saveStorage$default(var30, activeLicenseList, false, (String)null, 12, (Object)null);
               String licenseContent = ExtKt.jsonEncode$default(license, false, 2, (Object)null);
               CharSequence var32 = (CharSequence)this.privateKeyContent;
               boolean var13 = false;
               if (var32.length() == 0) {
                  String[] var35 = new String[]{"data", "privateKey"};
                  String var33 = ExtKt.getStorage(var35, ".key");
                  this.privateKeyContent = var33 == null ? "" : var33;
               }

               PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate((KeySpec)(new PKCS8EncodedKeySpec(Base64.decode((String)this.privateKeyContent, 2))));
               EncoderUtils var37 = EncoderUtils.INSTANCE;
               Intrinsics.checkNotNullExpressionValue(privateKey, "privateKey");
               String licenseKey = EncoderUtils.encryptSegmentByPrivateKey$default(var37, licenseContent, privateKey, 0, 4, (Object)null);
               return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("result", licenseKey)), (String)null, 2, (Object)null);
            }
         }
      }
   }

   @Nullable
   public final Object isLicenseValid(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      String id = null;
      String var5;
      if (context.request().method() == HttpMethod.POST) {
         var5 = context.getBodyAsJson().getString("id");
         Intrinsics.checkNotNullExpressionValue(var5, "context.bodyAsJson.getString(\"id\")");
         id = var5;
      } else {
         List var6 = context.queryParam("id");
         Intrinsics.checkNotNullExpressionValue(var6, "context.queryParam(\"id\")");
         var5 = (String)CollectionsKt.firstOrNull(var6);
         id = var5 == null ? "" : var5;
      }

      String[] var14 = new String[]{"data", "activeLicense"};
      JsonArray activeLicenseList = ExtKt.asJsonArray(ExtKt.getStorage$default(var14, (String)null, 2, (Object)null));
      if (activeLicenseList == null) {
         activeLicenseList = new JsonArray();
      }

      ActiveLicense activeLicense = null;
      int index = -1;
      int var8 = 0;
      int var9 = activeLicenseList.size();
      if (var8 < var9) {
         do {
            int i = var8++;
            ActiveLicense _activeLicense = (ActiveLicense)activeLicenseList.getJsonObject(i).mapTo(ActiveLicense.class);
            if (_activeLicense.getId().equals(id)) {
               activeLicense = _activeLicense;
               index = i;
               break;
            }
         } while(var8 < var9);
      }

      Map result;
      String ip;
      String var24;
      label79: {
         boolean var17 = false;
         result = (Map)(new LinkedHashMap());
         ip = context.request().getHeader("X-Real-IP");
         if (ip != null) {
            CharSequence var19 = (CharSequence)ip;
            boolean var22 = false;
            if (var19.length() != 0) {
               break label79;
            }
         }

         SocketAddress var20 = context.request().remoteAddress();
         String var10000;
         if (var20 == null) {
            var10000 = "";
         } else {
            var24 = var20.host();
            var10000 = var24 == null ? "" : var24;
         }

         ip = var10000;
      }

      if (activeLicense == null) {
         result.put("isValid", Boxing.boxBoolean(false));
         result.put("errorMsg", "密钥未激活");
      } else {
         result.put("isValid", Boxing.boxBoolean(activeLicense.getVerified()));
         result.put("errorMsg", activeLicense.getErrorMsg());
         if (activeLicense.getLastOnlineTime() != null) {
            long var30 = System.currentTimeMillis();
            Long var10001 = activeLicense.getLastOnlineTime();
            Intrinsics.checkNotNull(var10001);
            if (var30 < var10001 + (long)600000 && !ip.equals(activeLicense.getLastOnlineIp())) {
               Pair[] var21 = new Pair[]{TuplesKt.to("lastOnlineTime", activeLicense.getLastOnlineTime()), TuplesKt.to("lastOnlineIp", activeLicense.getLastOnlineIp())};
               result.put("repeat", MapsKt.mapOf(var21));
            }
         }

         activeLicense.setLastOnlineTime(Boxing.boxLong(System.currentTimeMillis()));
         activeLicense.setLastOnlineIp(ip);
         activeLicenseList.set(index, JsonObject.mapFrom(activeLicense));
         String[] var23 = new String[]{"data", "activeLicense"};
         ExtKt.saveStorage$default(var23, activeLicenseList, false, (String)null, 12, (Object)null);
      }

      String resultContent = ExtKt.jsonEncode$default(result, false, 2, (Object)null);
      CharSequence var26 = (CharSequence)this.privateKeyContent;
      boolean var12 = false;
      if (var26.length() == 0) {
         String[] var27 = new String[]{"data", "privateKey"};
         var24 = ExtKt.getStorage(var27, ".key");
         this.privateKeyContent = var24 == null ? "" : var24;
      }

      PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate((KeySpec)(new PKCS8EncodedKeySpec(Base64.decode((String)this.privateKeyContent, 2))));
      EncoderUtils var31 = EncoderUtils.INSTANCE;
      Intrinsics.checkNotNullExpressionValue(privateKey, "privateKey");
      String resultKey = EncoderUtils.encryptSegmentByPrivateKey$default(var31, resultContent, privateKey, 0, 4, (Object)null);
      return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("result", resultKey)), (String)null, 2, (Object)null);
   }

   @Nullable
   public final Object checkLicense(@NotNull License license, @NotNull Continuation<? super Unit> $completion) {
      ExtKt.setLicenseValid(true);
      return Unit.INSTANCE;
   }

   @Nullable
   public final Object sendCodeToEmail(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      String email = null;
      String var5;
      if (context.request().method() == HttpMethod.POST) {
         var5 = context.getBodyAsJson().getString("email");
         email = var5 == null ? "" : var5;
      } else {
         List var6 = context.queryParam("email");
         Intrinsics.checkNotNullExpressionValue(var6, "context.queryParam(\"email\")");
         var5 = (String)CollectionsKt.firstOrNull(var6);
         email = var5 == null ? "" : var5;
      }

      CharSequence var12 = (CharSequence)email;
      boolean var14 = false;
      if (var12.length() == 0) {
         return returnData.setErrorMsg("邮箱错误");
      } else if (!ExtKt.validateEmail(email)) {
         return returnData.setErrorMsg("仅支持 163|126|qq|yahoo|sina|sohu|yeah|139|189|21cn|outlook|gmail|icloud 等邮箱");
      } else {
         String[] var15 = new String[]{"data", "activeLicense"};
         JsonArray activeLicenseList = ExtKt.asJsonArray(ExtKt.getStorage$default(var15, (String)null, 2, (Object)null));
         if (activeLicenseList == null) {
            activeLicenseList = new JsonArray();
         }

         int var16 = 0;
         int var7 = activeLicenseList.size();
         if (var16 < var7) {
            do {
               int i = var16++;
               if ("trial".equals(activeLicenseList.getJsonObject(i).getString("type")) && email.equals(activeLicenseList.getJsonObject(i).getString("code"))) {
                  return returnData.setErrorMsg("该邮箱已被使用");
               }
            } while(var16 < var7);
         }

         String verifyCode = this.tryCodeCache.getAsString(email);
         CharSequence var17 = (CharSequence)verifyCode;
         boolean var20 = false;
         boolean var9 = false;
         if (var17 != null && var17.length() != 0) {
            return returnData.setData("", "您的验证码仍在有效期内，请勿重复获取");
         } else {
            String var21 = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(var21, "randomUUID().toString()");
            byte var22 = 0;
            byte var10 = 6;
            boolean var11 = false;
            String var10000 = var21.substring(var22, var10);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            String code = var10000;
            this.tryCodeCache.put(email, (String)code, 900);
            ExtKt.sendEmail(email, "Reader Kindle端的试用申请验证", "您正在申请Reader Kindle端的试用，验证码是: " + code + "，15分钟内有效，请勿回复");
            return returnData.setData("", "请查收邮件");
         }
      }
   }

   @Nullable
   public final Object supplyLicense(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      String code = context.getBodyAsJson().getString("email");
      String email = code == null ? "" : code;
      String verifyCode = context.getBodyAsJson().getString("code");
      code = verifyCode == null ? "" : verifyCode;
      CharSequence var11 = (CharSequence)email;
      boolean var7 = false;
      boolean var8 = false;
      if (var11.length() != 0) {
         var11 = (CharSequence)code;
         var7 = false;
         var8 = false;
         if (var11.length() != 0) {
            verifyCode = this.tryCodeCache.getAsString(email);
            this.tryCodeCache.remove(email);
            if (!code.equals(verifyCode)) {
               return returnData.setErrorMsg("验证码错误");
            }

            long var13 = System.currentTimeMillis() + 604800000L;
            License license = new License("*", 15, 0L, false, var13, 1, "trial", (String)null, email, false, (Long)null, 1152, (DefaultConstructorMarker)null);
            String licenseContent = ExtKt.jsonEncode$default(license, false, 2, (Object)null);
            CharSequence var9 = (CharSequence)this.privateKeyContent;
            boolean var10 = false;
            if (var9.length() == 0) {
               String[] var17 = new String[]{"data", "privateKey"};
               String var15 = ExtKt.getStorage(var17, ".key");
               this.privateKeyContent = var15 == null ? "" : var15;
            }

            PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate((KeySpec)(new PKCS8EncodedKeySpec(Base64.decode((String)this.privateKeyContent, 2))));
            EncoderUtils var10000 = EncoderUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(privateKey, "privateKey");
            String licenseKey = EncoderUtils.encryptSegmentByPrivateKey$default(var10000, licenseContent, privateKey, 0, 4, (Object)null);
            return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("key", licenseKey)), (String)null, 2, (Object)null);
         }
      }

      return returnData.setErrorMsg("参数错误");
   }
}
