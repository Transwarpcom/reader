package com.htmake.reader.api.controller;

import ch.qos.logback.core.CoreConstants;
import com.htmake.reader.api.ReturnData;
import com.htmake.reader.entity.License;
import com.htmake.reader.utils.ExtKt;
import com.htmake.reader.utils.SpringContextUtils;
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
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.slf4j.MDCContext;
import mu.KLogger;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* compiled from: LicenseController.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@ø\u0001��¢\u0006\u0002\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018J\u0019\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018J\u0019\u0010 \u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018J\u0019\u0010!\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018J\u0019\u0010\"\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018J\u0019\u0010#\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018J\u0019\u0010$\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018J\u0019\u0010%\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018J\u0019\u0010&\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n��R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0011\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"Lcom/htmake/reader/api/controller/LicenseController;", "Lcom/htmake/reader/api/controller/BaseController;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "backupFileNames", "", "", "getBackupFileNames", "()[Ljava/lang/String;", "backupFileNames$delegate", "Lkotlin/Lazy;", "privateKeyContent", "tryCodeCache", "Lio/legado/app/utils/ACache;", "webClient", "Lio/vertx/ext/web/client/WebClient;", "getWebClient", "()Lio/vertx/ext/web/client/WebClient;", "webClient$delegate", "activateLicense", "Lcom/htmake/reader/api/ReturnData;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkLicense", "", "license", "Lcom/htmake/reader/entity/License;", "(Lcom/htmake/reader/entity/License;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decryptLicense", "generateKeys", "generateLicense", "getLicense", "importLicense", "isHostValid", "isLicenseValid", "sendCodeToEmail", "supplyLicense", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/LicenseController.class */
public final class LicenseController extends BaseController {

    @NotNull
    private final Lazy webClient$delegate;

    @NotNull
    private String privateKeyContent;

    @NotNull
    private ACache tryCodeCache;

    @NotNull
    private final Lazy backupFileNames$delegate;

    /* compiled from: LicenseController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "LicenseController.kt", l = {118}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "importLicense", c = "com.htmake.reader.api.controller.LicenseController")
    /* renamed from: com.htmake.reader.api.controller.LicenseController$importLicense$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/LicenseController$importLicense$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return LicenseController.this.importLicense(null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LicenseController(@NotNull CoroutineContext coroutineContext) {
        super(coroutineContext);
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        this.webClient$delegate = LazyKt.lazy(new Function0<WebClient>() { // from class: com.htmake.reader.api.controller.LicenseController$webClient$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final WebClient invoke() {
                return (WebClient) SpringContextUtils.getBean("webClient", WebClient.class);
            }
        });
        this.privateKeyContent = "";
        this.tryCodeCache = ACache.Companion.get("tryCodeCache", 2000000L, 10000);
        this.backupFileNames$delegate = LazyKt.lazy(new Function0<String[]>() { // from class: com.htmake.reader.api.controller.LicenseController$backupFileNames$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final String[] invoke() {
                return new String[]{"bookSource.json", "bookshelf.json", "bookGroup.json", "rssSources.json", "replaceRule.json", "bookmark.json", "userConfig.json"};
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WebClient getWebClient() {
        Object value = this.webClient$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-webClient>(...)");
        return (WebClient) value;
    }

    @NotNull
    public final String[] getBackupFileNames() {
        return (String[]) this.backupFileNames$delegate.getValue();
    }

    @Nullable
    public final Object getLicense(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
        ReturnData returnData = new ReturnData();
        License license = ExtKt.getInstalledLicense$default(false, 1, null);
        return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("license", license)), null, 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object importLicense(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 422
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.LicenseController.importLicense(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: LicenseController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "LicenseController.kt", l = {141}, i = {0}, s = {"L$0"}, n = {"$this$launch"}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.LicenseController$importLicense$2")
    /* renamed from: com.htmake.reader.api.controller.LicenseController$importLicense$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/LicenseController$importLicense$2.class */
    static final class C01742 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ Ref.ObjectRef<String> $checkUrl;
        final /* synthetic */ String $content;
        final /* synthetic */ RoutingContext $context;
        final /* synthetic */ ReturnData $returnData;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01742(Ref.ObjectRef<String> $checkUrl, String $content, RoutingContext $context, ReturnData $returnData, Continuation<? super C01742> $completion) {
            super(2, $completion);
            this.$checkUrl = $checkUrl;
            this.$content = $content;
            this.$context = $context;
            this.$returnData = $returnData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            C01742 c01742 = LicenseController.this.new C01742(this.$checkUrl, this.$content, this.$context, this.$returnData, $completion);
            c01742.L$0 = value;
            return c01742;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((C01742) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            CoroutineScope $this$launch;
            Object objAwaitResult;
            Object objM2105constructorimpl;
            JsonObject it;
            String it2;
            Pair pair;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$launch = (CoroutineScope) this.L$0;
                    final LicenseController licenseController = LicenseController.this;
                    final Ref.ObjectRef<String> objectRef = this.$checkUrl;
                    final String str = this.$content;
                    this.L$0 = $this$launch;
                    this.label = 1;
                    objAwaitResult = VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpResponse<Buffer>>>, Unit>() { // from class: com.htmake.reader.api.controller.LicenseController$importLicense$2$result$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<HttpResponse<Buffer>>> handler) {
                            invoke2(handler);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(@NotNull Handler<AsyncResult<HttpResponse<Buffer>>> handler) {
                            Intrinsics.checkNotNullParameter(handler, "handler");
                            licenseController.getWebClient().postAbs(objectRef.element).timeout(5000L).sendJsonObject(new JsonObject((Map<String, Object>) MapsKt.mapOf(TuplesKt.to("content", str))), handler);
                        }
                    }, this);
                    if (objAwaitResult == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    $this$launch = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    objAwaitResult = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            HttpResponse result = (HttpResponse) objAwaitResult;
            RoutingContext routingContext = this.$context;
            try {
                Result.Companion companion = Result.Companion;
                if (result == null || (it = result.bodyAsJsonObject()) == null) {
                    pair = null;
                } else {
                    if (!it.getBoolean("isSuccess").booleanValue()) {
                        throw new Exception(it.getString("errorMsg"));
                    }
                    JsonObject jsonObject = it.getJsonObject("data");
                    if (jsonObject == null || (it2 = jsonObject.getString(CacheOperationExpressionEvaluator.RESULT_VARIABLE)) == null) {
                        pair = null;
                    } else {
                        License license = ExtKt.decryptToLicense(it2);
                        if (license == null) {
                            throw new Exception("密钥错误");
                        }
                        String strHost = routingContext.request().host();
                        Intrinsics.checkNotNullExpressionValue(strHost, "context.request().host()");
                        if (!license.validHost(strHost)) {
                            throw new Exception("密钥授权域名错误");
                        }
                        pair = new Pair(it2, license);
                    }
                }
                objM2105constructorimpl = Result.m2105constructorimpl(pair);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
            }
            Object obj = objM2105constructorimpl;
            RoutingContext routingContext2 = this.$context;
            ReturnData returnData = this.$returnData;
            if (Result.m2100isSuccessimpl(obj)) {
                Pair it3 = (Pair) obj;
                if (it3 != null) {
                    ExtKt.saveStorage$default(new String[]{"data", "license"}, it3.getFirst(), false, ".key", 4, null);
                    VertExtKt.success(routingContext2, ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("license", it3.getSecond())), null, 2, null));
                } else {
                    VertExtKt.success(routingContext2, returnData.setErrorMsg("密钥激活失败"));
                }
            }
            RoutingContext routingContext3 = this.$context;
            ReturnData returnData2 = this.$returnData;
            Throwable it4 = Result.m2103exceptionOrNullimpl(obj);
            if (it4 != null) {
                LicenseControllerKt.logger.info("import license error: {}", it4.getMessage());
                String message = it4.getMessage();
                VertExtKt.success(routingContext3, returnData2.setErrorMsg(message == null ? "密钥激活错误" : message));
            }
            return Unit.INSTANCE;
        }
    }

    @Nullable
    public final Object generateKeys(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) throws NoSuchAlgorithmException {
        ReturnData returnData = new ReturnData();
        KeyPair keyPair = EncoderUtils.INSTANCE.generateKeys();
        return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("publicKey", Base64.encodeToString(keyPair.getPublic().getEncoded(), 2)), TuplesKt.to("privateKey", Base64.encodeToString(keyPair.getPrivate().getEncoded(), 2))), null, 2, null);
    }

    @Nullable
    public final Object generateLicense(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) throws InvalidKeySpecException, IOException {
        String host;
        Long lBoxLong;
        long expiredAt;
        Integer numBoxInt;
        int userMaxLimit;
        Boolean boolBoxBoolean;
        boolean openApi;
        Long lBoxLong2;
        long simpleWebExpiredAt;
        Integer numBoxInt2;
        int instances;
        String type;
        String key;
        String code;
        ReturnData returnData = new ReturnData();
        if (context.request().method() == HttpMethod.POST) {
            String string = context.getBodyAsJson().getString("host");
            host = string == null ? "" : string;
            Long l = context.getBodyAsJson().getLong("expiredAt");
            expiredAt = l == null ? 0L : l.longValue();
            Integer integer = context.getBodyAsJson().getInteger("userMaxLimit");
            userMaxLimit = integer == null ? 15 : integer.intValue();
            Boolean bool = context.getBodyAsJson().getBoolean("openApi");
            openApi = bool == null ? false : bool.booleanValue();
            Long l2 = context.getBodyAsJson().getLong("simpleWebExpiredAt");
            simpleWebExpiredAt = l2 == null ? 0L : l2.longValue();
            Integer integer2 = context.getBodyAsJson().getInteger("instances");
            instances = integer2 == null ? 1 : integer2.intValue();
            String string2 = context.getBodyAsJson().getString("type");
            type = string2 == null ? "" : string2;
            String string3 = context.getBodyAsJson().getString("key");
            key = string3 == null ? "" : string3;
            String string4 = context.getBodyAsJson().getString("code");
            code = string4 == null ? "" : string4;
        } else {
            List<String> listQueryParam = context.queryParam("host");
            Intrinsics.checkNotNullExpressionValue(listQueryParam, "context.queryParam(\"host\")");
            String str = (String) CollectionsKt.firstOrNull((List) listQueryParam);
            host = str == null ? "" : str;
            List<String> listQueryParam2 = context.queryParam("expiredAt");
            Intrinsics.checkNotNullExpressionValue(listQueryParam2, "context.queryParam(\"expiredAt\")");
            String str2 = (String) CollectionsKt.firstOrNull((List) listQueryParam2);
            long jLongValue = (str2 == null || (lBoxLong = Boxing.boxLong(Long.parseLong(str2))) == null) ? 0L : lBoxLong.longValue();
            expiredAt = jLongValue;
            List<String> listQueryParam3 = context.queryParam("userMaxLimit");
            Intrinsics.checkNotNullExpressionValue(listQueryParam3, "context.queryParam(\"userMaxLimit\")");
            String str3 = (String) CollectionsKt.firstOrNull((List) listQueryParam3);
            int iIntValue = (str3 == null || (numBoxInt = Boxing.boxInt(Integer.parseInt(str3))) == null) ? 15 : numBoxInt.intValue();
            userMaxLimit = iIntValue;
            List<String> listQueryParam4 = context.queryParam("openApi");
            Intrinsics.checkNotNullExpressionValue(listQueryParam4, "context.queryParam(\"openApi\")");
            String str4 = (String) CollectionsKt.firstOrNull((List) listQueryParam4);
            boolean zBooleanValue = (str4 == null || (boolBoxBoolean = Boxing.boxBoolean(Boolean.parseBoolean(str4))) == null) ? false : boolBoxBoolean.booleanValue();
            openApi = zBooleanValue;
            List<String> listQueryParam5 = context.queryParam("simpleWebExpiredAt");
            Intrinsics.checkNotNullExpressionValue(listQueryParam5, "context.queryParam(\"simpleWebExpiredAt\")");
            String str5 = (String) CollectionsKt.firstOrNull((List) listQueryParam5);
            long jLongValue2 = (str5 == null || (lBoxLong2 = Boxing.boxLong(Long.parseLong(str5))) == null) ? 0L : lBoxLong2.longValue();
            simpleWebExpiredAt = jLongValue2;
            List<String> listQueryParam6 = context.queryParam("instances");
            Intrinsics.checkNotNullExpressionValue(listQueryParam6, "context.queryParam(\"instances\")");
            String str6 = (String) CollectionsKt.firstOrNull((List) listQueryParam6);
            int iIntValue2 = (str6 == null || (numBoxInt2 = Boxing.boxInt(Integer.parseInt(str6))) == null) ? 1 : numBoxInt2.intValue();
            instances = iIntValue2;
            List<String> listQueryParam7 = context.queryParam("type");
            Intrinsics.checkNotNullExpressionValue(listQueryParam7, "context.queryParam(\"type\")");
            String str7 = (String) CollectionsKt.firstOrNull((List) listQueryParam7);
            type = str7 == null ? "" : str7;
            List<String> listQueryParam8 = context.queryParam("key");
            Intrinsics.checkNotNullExpressionValue(listQueryParam8, "context.queryParam(\"key\")");
            String str8 = (String) CollectionsKt.firstOrNull((List) listQueryParam8);
            key = str8 == null ? "" : str8;
            List<String> listQueryParam9 = context.queryParam("code");
            Intrinsics.checkNotNullExpressionValue(listQueryParam9, "context.queryParam(\"code\")");
            String str9 = (String) CollectionsKt.firstOrNull((List) listQueryParam9);
            code = str9 == null ? "" : str9;
        }
        if (host.length() == 0) {
            return returnData.setErrorMsg("请输入域名");
        }
        if (!"Pvkp7tMQJpi4kWBE".equals(key)) {
            return returnData.setErrorMsg("参数错误");
        }
        License license = new License(host, userMaxLimit, expiredAt, openApi, simpleWebExpiredAt, instances, type, null, null, false, null, 1408, null);
        if (!(code.length() == 0)) {
            license.setCode(code);
        }
        String licenseContent = ExtKt.jsonEncode$default(license, false, 2, null);
        if (this.privateKeyContent.length() == 0) {
            String storage = ExtKt.getStorage(new String[]{"data", "privateKey"}, ".key");
            this.privateKeyContent = storage == null ? "" : storage;
        }
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(this.privateKeyContent, 2)));
        EncoderUtils encoderUtils = EncoderUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(privateKey, "privateKey");
        String licenseKey = EncoderUtils.encryptSegmentByPrivateKey$default(encoderUtils, licenseContent, privateKey, 0, 4, null);
        return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("host", host), TuplesKt.to("key", licenseKey)), null, 2, null);
    }

    @Nullable
    public final Object isHostValid(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
        String host;
        ReturnData returnData = new ReturnData();
        if (context.request().method() == HttpMethod.POST) {
            String string = context.getBodyAsJson().getString("host");
            Intrinsics.checkNotNullExpressionValue(string, "context.bodyAsJson.getString(\"host\")");
            host = string;
        } else {
            List<String> listQueryParam = context.queryParam("host");
            Intrinsics.checkNotNullExpressionValue(listQueryParam, "context.queryParam(\"host\")");
            String str = (String) CollectionsKt.firstOrNull((List) listQueryParam);
            host = str == null ? "" : str;
        }
        License license = ExtKt.getInstalledLicense$default(false, 1, null);
        return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("isValid", Boxing.boxBoolean(license.validHost(host)))), null, 2, null);
    }

    @Nullable
    public final Object decryptLicense(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
        ReturnData returnData = new ReturnData();
        String string = context.getBodyAsJson().getString("content");
        String content = string == null ? "" : string;
        if (content.length() == 0) {
            return returnData.setErrorMsg("请输入密钥");
        }
        License license = ExtKt.decryptToLicense(content);
        if (license == null) {
            return returnData.setErrorMsg("密钥错误");
        }
        return ReturnData.setData$default(returnData, license, null, 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0144  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object activateLicense(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.security.spec.InvalidKeySpecException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 699
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.LicenseController.activateLicense(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0126  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object isLicenseValid(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.security.spec.InvalidKeySpecException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 733
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.LicenseController.isLicenseValid(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [T, java.lang.String] */
    @Nullable
    public final Object checkLicense(@NotNull License license, @NotNull Continuation<? super Unit> $completion) {
        CoroutineExceptionHandler exceptionHandler = new LicenseController$checkLicense$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key);
        Ref.ObjectRef checkUrl = new Ref.ObjectRef();
        checkUrl.element = Intrinsics.stringPlus("https://r.htmake.com/reader3/isLicenseValid?id=", license.getId());
        Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(this, new MDCContext(null, 1, null).plus(Dispatchers.getIO()).plus(exceptionHandler), null, new AnonymousClass2(checkUrl, null), 2, null);
        return jobLaunch$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? jobLaunch$default : Unit.INSTANCE;
    }

    /* compiled from: LicenseController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "LicenseController.kt", l = {431}, i = {0}, s = {"L$0"}, n = {"$this$launch"}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.LicenseController$checkLicense$2")
    /* renamed from: com.htmake.reader.api.controller.LicenseController$checkLicense$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/LicenseController$checkLicense$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ Ref.ObjectRef<String> $checkUrl;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Ref.ObjectRef<String> $checkUrl, Continuation<? super AnonymousClass2> $completion) {
            super(2, $completion);
            this.$checkUrl = $checkUrl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass2 anonymousClass2 = LicenseController.this.new AnonymousClass2(this.$checkUrl, $completion);
            anonymousClass2.L$0 = value;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((AnonymousClass2) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            CoroutineScope $this$launch;
            Object objAwaitResult;
            Object objM2105constructorimpl;
            JsonObject it;
            String it2;
            Unit unit;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$launch = (CoroutineScope) this.L$0;
                    final LicenseController licenseController = LicenseController.this;
                    final Ref.ObjectRef<String> objectRef = this.$checkUrl;
                    this.L$0 = $this$launch;
                    this.label = 1;
                    objAwaitResult = VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpResponse<Buffer>>>, Unit>() { // from class: com.htmake.reader.api.controller.LicenseController$checkLicense$2$result$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<HttpResponse<Buffer>>> handler) {
                            invoke2(handler);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(@NotNull Handler<AsyncResult<HttpResponse<Buffer>>> handler) {
                            Intrinsics.checkNotNullParameter(handler, "handler");
                            licenseController.getWebClient().getAbs(objectRef.element).timeout(5000L).send(handler);
                        }
                    }, this);
                    if (objAwaitResult == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    $this$launch = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    objAwaitResult = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            HttpResponse result = (HttpResponse) objAwaitResult;
            try {
                Result.Companion companion = Result.Companion;
                if (result == null || (it = result.bodyAsJsonObject()) == null) {
                    unit = null;
                } else {
                    LicenseControllerKt.logger.info("isLicenseValid: {}", it);
                    JsonObject jsonObject = it.getJsonObject("data");
                    if (jsonObject == null || (it2 = jsonObject.getString(CacheOperationExpressionEvaluator.RESULT_VARIABLE)) == null) {
                        unit = null;
                    } else {
                        JsonObject it3 = new JsonObject(ExtKt.decryptData(it2));
                        Boolean bool = it3.getBoolean("isValid");
                        boolean isValid = bool == null ? true : bool.booleanValue();
                        ExtKt.setLicenseValid(isValid);
                        if (!isValid) {
                            KLogger kLogger = LicenseControllerKt.logger;
                            String string = it3.getString("errorMsg");
                            kLogger.info("密钥错误：{}", string == null ? "" : string);
                        }
                        JsonObject it4 = it3.getJsonObject("repeat");
                        if (it4 == null) {
                            unit = null;
                        } else {
                            KLogger kLogger2 = LicenseControllerKt.logger;
                            Long l = it4.getLong("lastOnlineTime");
                            Intrinsics.checkNotNullExpressionValue(l, "it.getLong(\"lastOnlineTime\")");
                            kLogger2.info("请勿重复使用授权，上次检查时间：{}，上次检查ip：{}", LocalDateTime.ofInstant(Instant.ofEpochMilli(l.longValue()), ZoneId.systemDefault()), it4.getString("lastOnlineIp"));
                            unit = Unit.INSTANCE;
                        }
                    }
                }
                objM2105constructorimpl = Result.m2105constructorimpl(unit);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
            }
            Object obj = objM2105constructorimpl;
            if (Result.m2100isSuccessimpl(obj)) {
            }
            Throwable it5 = Result.m2103exceptionOrNullimpl(obj);
            if (it5 != null) {
                LicenseControllerKt.logger.info("check license error: {}", it5.getMessage());
            }
            return Unit.INSTANCE;
        }
    }

    @Nullable
    public final Object sendCodeToEmail(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) throws IOException {
        String email;
        ReturnData returnData = new ReturnData();
        if (context.request().method() == HttpMethod.POST) {
            String string = context.getBodyAsJson().getString("email");
            email = string == null ? "" : string;
        } else {
            List<String> listQueryParam = context.queryParam("email");
            Intrinsics.checkNotNullExpressionValue(listQueryParam, "context.queryParam(\"email\")");
            String str = (String) CollectionsKt.firstOrNull((List) listQueryParam);
            email = str == null ? "" : str;
        }
        if (email.length() == 0) {
            return returnData.setErrorMsg("邮箱错误");
        }
        if (!ExtKt.validateEmail(email)) {
            return returnData.setErrorMsg("仅支持 163|126|qq|yahoo|sina|sohu|yeah|139|189|21cn|outlook|gmail|icloud 等邮箱");
        }
        JsonArray activeLicenseList = ExtKt.asJsonArray(ExtKt.getStorage$default(new String[]{"data", "activeLicense"}, null, 2, null));
        if (activeLicenseList == null) {
            activeLicenseList = new JsonArray();
        }
        int i = 0;
        int size = activeLicenseList.size();
        if (0 < size) {
            do {
                int i2 = i;
                i++;
                if ("trial".equals(activeLicenseList.getJsonObject(i2).getString("type")) && email.equals(activeLicenseList.getJsonObject(i2).getString("code"))) {
                    return returnData.setErrorMsg("该邮箱已被使用");
                }
            } while (i < size);
        }
        String verifyCode = this.tryCodeCache.getAsString(email);
        String str2 = verifyCode;
        if (!(str2 == null || str2.length() == 0)) {
            return returnData.setData("", "您的验证码仍在有效期内，请勿重复获取");
        }
        String string2 = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string2, "randomUUID().toString()");
        String code = string2.substring(0, 6);
        Intrinsics.checkNotNullExpressionValue(code, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        this.tryCodeCache.put(email, code, OS2WindowsMetricsTable.WEIGHT_CLASS_BLACK);
        ExtKt.sendEmail(email, "Reader Kindle端的试用申请验证", "您正在申请Reader Kindle端的试用，验证码是: " + code + "，15分钟内有效，请勿回复");
        return returnData.setData("", "请查收邮件");
    }

    @Nullable
    public final Object supplyLicense(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) throws InvalidKeySpecException, IOException {
        ReturnData returnData = new ReturnData();
        String string = context.getBodyAsJson().getString("email");
        String email = string == null ? "" : string;
        String string2 = context.getBodyAsJson().getString("code");
        String code = string2 == null ? "" : string2;
        if (!(email.length() == 0)) {
            if (!(code.length() == 0)) {
                String verifyCode = this.tryCodeCache.getAsString(email);
                this.tryCodeCache.remove(email);
                if (!code.equals(verifyCode)) {
                    return returnData.setErrorMsg("验证码错误");
                }
                License license = new License("*", 15, 0L, false, System.currentTimeMillis() + CoreConstants.MILLIS_IN_ONE_WEEK, 1, "trial", null, email, false, null, 1152, null);
                String licenseContent = ExtKt.jsonEncode$default(license, false, 2, null);
                if (this.privateKeyContent.length() == 0) {
                    String storage = ExtKt.getStorage(new String[]{"data", "privateKey"}, ".key");
                    this.privateKeyContent = storage == null ? "" : storage;
                }
                PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(this.privateKeyContent, 2)));
                EncoderUtils encoderUtils = EncoderUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(privateKey, "privateKey");
                String licenseKey = EncoderUtils.encryptSegmentByPrivateKey$default(encoderUtils, licenseContent, privateKey, 0, 4, null);
                return ReturnData.setData$default(returnData, MapsKt.mapOf(TuplesKt.to("key", licenseKey)), null, 2, null);
            }
        }
        return returnData.setErrorMsg("参数错误");
    }
}
