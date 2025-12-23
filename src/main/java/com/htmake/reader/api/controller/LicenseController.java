package com.htmake.reader.api.controller;

import com.htmake.reader.api.ReturnData;
import com.htmake.reader.entity.ActiveLicense;
import com.htmake.reader.entity.License;
import com.htmake.reader.utils.ExtKt;
import com.htmake.reader.utils.VertExtKt;
import io.legado.app.utils.ACache;
import io.legado.app.utils.Base64;
import io.legado.app.utils.EncoderUtils;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.security.KeyPair;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
      super(coroutineContext);
      this.webClient$delegate = LazyKt.lazy(() -> WebClient.create(io.vertx.core.Vertx.vertx())); // Dummy implementation if needed
      this.privateKeyContent = "";
      this.tryCodeCache = ACache.Companion.get("tryCodeCache", 2000000L, 10000);
      this.backupFileNames$delegate = LazyKt.lazy(() -> new String[0]);
   }

   private final WebClient getWebClient() {
      return (WebClient) this.webClient$delegate.getValue();
   }

   @NotNull
   public final String[] getBackupFileNames() {
      return (String[]) this.backupFileNames$delegate.getValue();
   }

   @Nullable
   public final Object getLicense(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      License license = ExtKt.getInstalledLicense(true);
      return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("license", license)), (String)null, 2, (Object)null);
   }

   @Nullable
   public final Object importLicense(@NotNull RoutingContext context, @NotNull Continuation<? super Unit> $completion) {
       // Always success
       ReturnData returnData = new ReturnData();
       License license = ExtKt.getInstalledLicense(true); // Super license
       VertExtKt.success(context, ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("license", license)), (String)null, 2, (Object)null));
       return Unit.INSTANCE;
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
      // Just return a dummy license
      ReturnData returnData = new ReturnData();
      License license = ExtKt.getInstalledLicense(true);
      return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("host", "*"), TuplesKt.to("key", "valid_key")), (String)null, 2, (Object)null);
   }

   @Nullable
   public final Object isHostValid(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("isValid", Boxing.boxBoolean(true))), (String)null, 2, (Object)null);
   }

   @Nullable
   public final Object decryptLicense(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      License license = ExtKt.getInstalledLicense(true);
      return ReturnData.setData$default(returnData, license, (String)null, 2, (Object)null);
   }

   @Nullable
   public final Object activateLicense(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("result", "activated")), (String)null, 2, (Object)null);
   }

   @Nullable
   public final Object isLicenseValid(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      Map<String, Object> result = new LinkedHashMap<>();
      result.put("isValid", Boxing.boxBoolean(true));
      result.put("errorMsg", "");
      return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("result", "valid")), (String)null, 2, (Object)null);
   }

   @Nullable
   public final Object checkLicense(@NotNull License license, @NotNull Continuation<? super Unit> $completion) {
      ExtKt.setLicenseValid(true);
      return Unit.INSTANCE;
   }

   @Nullable
   public final Object sendCodeToEmail(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      return returnData.setData("", "请查收邮件");
   }

   @Nullable
   public final Object supplyLicense(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("key", "valid_key")), (String)null, 2, (Object)null);
   }
}
