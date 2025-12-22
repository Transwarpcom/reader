package io.legado.app.model.analyzeRule;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.script.SimpleBindings;
import io.legado.app.constant.AppConst;
import io.legado.app.constant.AppPattern;
import io.legado.app.data.entities.BaseSource;
import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.exception.ConcurrentException;
import io.legado.app.help.CacheManager;
import io.legado.app.help.JsExtensions;
import io.legado.app.help.http.CookieStore;
import io.legado.app.help.http.HttpHelperKt;
import io.legado.app.help.http.OkHttpUtilsKt;
import io.legado.app.help.http.RequestMethod;
import io.legado.app.help.http.StrResponse;
import io.legado.app.model.DebugLog;
import io.legado.app.utils.EncoderUtils;
import io.legado.app.utils.GsonExtensionsKt;
import io.legado.app.utils.NetworkUtils;
import io.legado.app.utils.ParameterizedTypeImpl;
import io.legado.app.utils.StringExtensionsKt;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import me.ag2s.epublib.epub.NCXDocumentV2;
import me.ag2s.epublib.epub.NCXDocumentV3;
import me.ag2s.epublib.epub.PackageDocumentBase;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Connection;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: AnalyzeUrl.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n��\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\u0018�� l2\u00020\u0001:\u0003lmnB\u008f\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014J\u0010\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u0003H\u0002J\b\u0010C\u001a\u00020AH\u0002J\b\u0010D\u001a\u00020AH\u0002J\u001c\u0010E\u001a\u0004\u0018\u00010F2\u0006\u0010G\u001a\u00020\u00032\n\b\u0002\u0010H\u001a\u0004\u0018\u00010FJ\u0012\u0010I\u001a\u00020A2\b\u0010J\u001a\u0004\u0018\u00010KH\u0002J\n\u0010L\u001a\u0004\u0018\u00010KH\u0002J\u000e\u0010M\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003J\u0006\u0010N\u001a\u00020OJ\u0011\u0010P\u001a\u00020OH\u0086@ø\u0001��¢\u0006\u0002\u0010QJ\n\u0010R\u001a\u0004\u0018\u00010\u0013H\u0016J\u0006\u0010S\u001a\u00020TJ\u0011\u0010U\u001a\u00020TH\u0086@ø\u0001��¢\u0006\u0002\u0010QJ\n\u0010V\u001a\u0004\u0018\u00010\u000bH\u0016J*\u0010W\u001a\u00020X2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010>\u001a\u00020\"H\u0007J3\u0010Z\u001a\u00020X2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010>\u001a\u00020\"H\u0086@ø\u0001��¢\u0006\u0002\u0010[J\u0006\u0010\\\u001a\u00020\u0003J\b\u0010]\u001a\u00020\u0003H\u0016J\u0006\u0010^\u001a\u00020AJ\u0006\u0010_\u001a\u00020\"J\u0016\u0010`\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010a\u001a\u00020\u0003J\b\u0010b\u001a\u00020AH\u0002J\u000e\u0010c\u001a\u00020A2\u0006\u0010d\u001a\u00020TJ\u0012\u0010e\u001a\u00020A2\b\u0010f\u001a\u0004\u0018\u00010\u0003H\u0002J)\u0010g\u001a\u00020X2\u0006\u0010h\u001a\u00020\u00032\u0006\u0010i\u001a\u00020F2\u0006\u0010j\u001a\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010kR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u001a\u001a\u0004\u0018\u00010\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u0003@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u0016R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n��R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n��R*\u0010#\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030$j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`%X\u0082\u0004¢\u0006\u0002\n��R-\u0010&\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030'j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`(¢\u0006\b\n��\u001a\u0004\b)\u0010*R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n��\u001a\u0004\b+\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b,\u0010\u0016R\u000e\u0010-\u001a\u00020.X\u0082\u000e¢\u0006\u0002\n��R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u00101\u001a\u0004\b/\u00100R\u0010\u00102\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n��R\u0010\u00103\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n��R\u000e\u00104\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n��R\u001e\u00105\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b6\u0010\u0016R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n��R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u00101\u001a\u0004\b7\u00100R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n��\u001a\u0004\b8\u0010\u0016R\"\u00109\u001a\u0004\u0018\u00010\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u0003@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b:\u0010\u0016R\u001e\u0010;\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b<\u0010\u0016R\u000e\u0010=\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010>\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010?\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n��\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006o"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeUrl;", "Lio/legado/app/help/JsExtensions;", "mUrl", "", "key", "page", "", "speakText", "speakSpeed", "baseUrl", PackageDocumentBase.DCTags.source, "Lio/legado/app/data/entities/BaseSource;", "ruleData", "Lio/legado/app/model/analyzeRule/RuleDataInterface;", NCXDocumentV2.NCXAttributeValues.chapter, "Lio/legado/app/data/entities/BookChapter;", "headerMapF", "", "debugLog", "Lio/legado/app/model/DebugLog;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lio/legado/app/data/entities/BaseSource;Lio/legado/app/model/analyzeRule/RuleDataInterface;Lio/legado/app/data/entities/BookChapter;Ljava/util/Map;Lio/legado/app/model/DebugLog;)V", "getBaseUrl", "()Ljava/lang/String;", "setBaseUrl", "(Ljava/lang/String;)V", "<set-?>", NCXDocumentV3.XHTMLTgs.body, "getBody", "charset", "getDebugLog", "()Lio/legado/app/model/DebugLog;", "setDebugLog", "(Lio/legado/app/model/DebugLog;)V", "enabledCookieJar", "", "fieldMap", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "headerMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getHeaderMap", "()Ljava/util/HashMap;", "getKey", "getMUrl", "method", "Lio/legado/app/help/http/RequestMethod;", "getPage", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "proxy", "queryStr", "retry", "ruleUrl", "getRuleUrl", "getSpeakSpeed", "getSpeakText", "type", "getType", "url", "getUrl", "urlNoQuery", "useWebView", "webJs", "analyzeFields", "", "fieldsTxt", "analyzeJs", "analyzeUrl", "evalJS", "", "jsStr", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "fetchEnd", "concurrentRecord", "Lio/legado/app/model/analyzeRule/AnalyzeUrl$ConcurrentRecord;", "fetchStart", BeanUtil.PREFIX_GETTER_GET, "getByteArray", "", "getByteArrayAwait", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLogger", "getResponse", "Lokhttp3/Response;", "getResponseAwait", "getSource", "getStrResponse", "Lio/legado/app/help/http/StrResponse;", "sourceRegex", "getStrResponseAwait", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserAgent", "getUserNameSpace", "initUrl", "isPost", "put", "value", "replaceKeyPageJs", "saveCookieJar", "response", "setCookie", "tag", "upload", "fileName", "file", "contentType", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "ConcurrentRecord", "UrlOption", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeUrl.class */
public final class AnalyzeUrl implements JsExtensions {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final String mUrl;

    @Nullable
    private final String key;

    @Nullable
    private final Integer page;

    @Nullable
    private final String speakText;

    @Nullable
    private final Integer speakSpeed;

    @NotNull
    private String baseUrl;

    @Nullable
    private final BaseSource source;

    @Nullable
    private final RuleDataInterface ruleData;

    @Nullable
    private final BookChapter chapter;

    @Nullable
    private DebugLog debugLog;

    @NotNull
    private String ruleUrl;

    @NotNull
    private String url;

    @Nullable
    private String body;

    @Nullable
    private String type;

    @NotNull
    private final HashMap<String, String> headerMap;

    @NotNull
    private String urlNoQuery;

    @Nullable
    private String queryStr;

    @NotNull
    private final LinkedHashMap<String, String> fieldMap;

    @Nullable
    private String charset;

    @NotNull
    private RequestMethod method;

    @Nullable
    private String proxy;
    private int retry;
    private boolean useWebView;

    @Nullable
    private String webJs;
    private final boolean enabledCookieJar;

    @NotNull
    private static final Pattern paramPattern;
    private static final Pattern pagePattern;

    @NotNull
    private static final HashMap<String, ConcurrentRecord> concurrentRecordMap;

    /* compiled from: AnalyzeUrl.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeUrl$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RequestMethod.values().length];
            iArr[RequestMethod.GET.ordinal()] = 1;
            iArr[RequestMethod.POST.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* compiled from: AnalyzeUrl.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "AnalyzeUrl.kt", l = {491}, i = {}, s = {}, n = {}, m = "getByteArrayAwait", c = "io.legado.app.model.analyzeRule.AnalyzeUrl")
    /* renamed from: io.legado.app.model.analyzeRule.AnalyzeUrl$getByteArrayAwait$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeUrl$getByteArrayAwait$1.class */
    static final class C02091 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C02091(Continuation<? super C02091> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return AnalyzeUrl.this.getByteArrayAwait(this);
        }
    }

    /* compiled from: AnalyzeUrl.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "AnalyzeUrl.kt", l = {446}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"this", "concurrentRecord"}, m = "getResponseAwait", c = "io.legado.app.model.analyzeRule.AnalyzeUrl")
    /* renamed from: io.legado.app.model.analyzeRule.AnalyzeUrl$getResponseAwait$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeUrl$getResponseAwait$1.class */
    static final class C02111 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C02111(Continuation<? super C02111> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return AnalyzeUrl.this.getResponseAwait(this);
        }
    }

    /* compiled from: AnalyzeUrl.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "AnalyzeUrl.kt", l = {360, 369, 381, 392}, i = {1, 1, 2, 2, 3, 3}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1"}, n = {"this", "concurrentRecord", "this", "concurrentRecord", "this", "concurrentRecord"}, m = "getStrResponseAwait", c = "io.legado.app.model.analyzeRule.AnalyzeUrl")
    /* renamed from: io.legado.app.model.analyzeRule.AnalyzeUrl$getStrResponseAwait$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeUrl$getStrResponseAwait$1.class */
    static final class C02131 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C02131(Continuation<? super C02131> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return AnalyzeUrl.this.getStrResponseAwait(null, null, false, this);
        }
    }

    @JvmOverloads
    @NotNull
    public final StrResponse getStrResponse(@Nullable String jsStr, @Nullable String sourceRegex) {
        return getStrResponse$default(this, jsStr, sourceRegex, false, 4, null);
    }

    @JvmOverloads
    @NotNull
    public final StrResponse getStrResponse(@Nullable String jsStr) {
        return getStrResponse$default(this, jsStr, null, false, 6, null);
    }

    @JvmOverloads
    @NotNull
    public final StrResponse getStrResponse() {
        return getStrResponse$default(this, null, null, false, 7, null);
    }

    public AnalyzeUrl(@NotNull String mUrl, @Nullable String key, @Nullable Integer page, @Nullable String speakText, @Nullable Integer speakSpeed, @NotNull String baseUrl, @Nullable BaseSource source, @Nullable RuleDataInterface ruleData, @Nullable BookChapter chapter, @Nullable Map<String, String> headerMapF, @Nullable DebugLog debugLog) throws UnsupportedEncodingException {
        Boolean enabledCookieJar;
        Map headerMap;
        Intrinsics.checkNotNullParameter(mUrl, "mUrl");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        this.mUrl = mUrl;
        this.key = key;
        this.page = page;
        this.speakText = speakText;
        this.speakSpeed = speakSpeed;
        this.baseUrl = baseUrl;
        this.source = source;
        this.ruleData = ruleData;
        this.chapter = chapter;
        this.debugLog = debugLog;
        this.ruleUrl = "";
        this.url = "";
        this.headerMap = new HashMap<>();
        this.urlNoQuery = "";
        this.fieldMap = new LinkedHashMap<>();
        this.method = RequestMethod.GET;
        BaseSource baseSource = this.source;
        boolean zBooleanValue = (baseSource == null || (enabledCookieJar = baseSource.getEnabledCookieJar()) == null) ? false : enabledCookieJar.booleanValue();
        this.enabledCookieJar = zBooleanValue;
        if (StringExtensionsKt.isDataUrl(this.mUrl)) {
            return;
        }
        Matcher urlMatcher = paramPattern.matcher(this.baseUrl);
        if (urlMatcher.find()) {
            String str = this.baseUrl;
            int iStart = urlMatcher.start();
            if (str == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = str.substring(0, iStart);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            this.baseUrl = strSubstring;
        }
        if (headerMapF == null) {
            BaseSource baseSource2 = this.source;
            headerMap = baseSource2 == null ? null : baseSource2.getHeaderMap(true);
        } else {
            headerMap = headerMapF;
        }
        Map it = headerMap;
        if (it != null) {
            getHeaderMap().putAll(it);
            if (it.containsKey("proxy")) {
                this.proxy = it.get("proxy");
                getHeaderMap().remove("proxy");
            }
        }
        initUrl();
    }

    public /* synthetic */ AnalyzeUrl(String str, String str2, Integer num, String str3, Integer num2, String str4, BaseSource baseSource, RuleDataInterface ruleDataInterface, BookChapter bookChapter, Map map, DebugLog debugLog, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? "" : str4, (i & 64) != 0 ? null : baseSource, (i & 128) != 0 ? null : ruleDataInterface, (i & 256) != 0 ? null : bookChapter, (i & 512) != 0 ? null : map, (i & 1024) != 0 ? null : debugLog);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public byte[] aesBase64DecodeToByteArray(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
        return JsExtensions.DefaultImpls.aesBase64DecodeToByteArray(this, str, key, transformation, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String aesBase64DecodeToString(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
        return JsExtensions.DefaultImpls.aesBase64DecodeToString(this, str, key, transformation, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String aesDecodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
        return JsExtensions.DefaultImpls.aesDecodeArgsBase64Str(this, data, key, mode, padding, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public byte[] aesDecodeToByteArray(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
        return JsExtensions.DefaultImpls.aesDecodeToByteArray(this, str, key, transformation, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String aesDecodeToString(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
        return JsExtensions.DefaultImpls.aesDecodeToString(this, str, key, transformation, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String aesEncodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
        return JsExtensions.DefaultImpls.aesEncodeArgsBase64Str(this, data, key, mode, padding, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public byte[] aesEncodeToBase64ByteArray(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
        return JsExtensions.DefaultImpls.aesEncodeToBase64ByteArray(this, data, key, transformation, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String aesEncodeToBase64String(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
        return JsExtensions.DefaultImpls.aesEncodeToBase64String(this, data, key, transformation, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public byte[] aesEncodeToByteArray(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
        return JsExtensions.DefaultImpls.aesEncodeToByteArray(this, data, key, transformation, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String aesEncodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
        return JsExtensions.DefaultImpls.aesEncodeToString(this, data, key, transformation, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String ajax(@NotNull String urlStr) {
        return JsExtensions.DefaultImpls.ajax(this, urlStr);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public StrResponse[] ajaxAll(@NotNull String[] urlList) {
        return JsExtensions.DefaultImpls.ajaxAll(this, urlList);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String androidId() {
        return JsExtensions.DefaultImpls.androidId(this);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String base64Decode(@NotNull String str) {
        return JsExtensions.DefaultImpls.base64Decode(this, str);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String base64Decode(@NotNull String str, int flags) {
        return JsExtensions.DefaultImpls.base64Decode(this, str, flags);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public byte[] base64DecodeToByteArray(@Nullable String str) {
        return JsExtensions.DefaultImpls.base64DecodeToByteArray(this, str);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public byte[] base64DecodeToByteArray(@Nullable String str, int flags) {
        return JsExtensions.DefaultImpls.base64DecodeToByteArray(this, str, flags);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String base64Encode(@NotNull String str) {
        return JsExtensions.DefaultImpls.base64Encode(this, str);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String base64Encode(@NotNull String str, int flags) {
        return JsExtensions.DefaultImpls.base64Encode(this, str, flags);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String cacheFile(@NotNull String urlStr) {
        return JsExtensions.DefaultImpls.cacheFile(this, urlStr);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String cacheFile(@NotNull String urlStr, int saveTime) {
        return JsExtensions.DefaultImpls.cacheFile(this, urlStr, saveTime);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public StrResponse connect(@NotNull String urlStr) {
        return JsExtensions.DefaultImpls.connect(this, urlStr);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public StrResponse connect(@NotNull String urlStr, @Nullable String header) {
        return JsExtensions.DefaultImpls.connect(this, urlStr, header);
    }

    @Override // io.legado.app.help.JsExtensions
    public void deleteFile(@NotNull String path) {
        JsExtensions.DefaultImpls.deleteFile(this, path);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String desBase64DecodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
        return JsExtensions.DefaultImpls.desBase64DecodeToString(this, data, key, transformation, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String desDecodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
        return JsExtensions.DefaultImpls.desDecodeToString(this, data, key, transformation, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String desEncodeToBase64String(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
        return JsExtensions.DefaultImpls.desEncodeToBase64String(this, data, key, transformation, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String desEncodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
        return JsExtensions.DefaultImpls.desEncodeToString(this, data, key, transformation, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String digestBase64Str(@NotNull String data, @NotNull String algorithm) {
        return JsExtensions.DefaultImpls.digestBase64Str(this, data, algorithm);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String digestHex(@NotNull String data, @NotNull String algorithm) {
        return JsExtensions.DefaultImpls.digestHex(this, data, algorithm);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String downloadFile(@NotNull String content, @NotNull String url) {
        return JsExtensions.DefaultImpls.downloadFile(this, content, url);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String encodeURI(@NotNull String str) {
        return JsExtensions.DefaultImpls.encodeURI(this, str);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String encodeURI(@NotNull String str, @NotNull String enc) {
        return JsExtensions.DefaultImpls.encodeURI(this, str, enc);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public Connection.Response get(@NotNull String urlStr, @NotNull Map<String, String> headers) {
        return JsExtensions.DefaultImpls.get(this, urlStr, headers);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String getCookie(@NotNull String tag, @Nullable String key) {
        return JsExtensions.DefaultImpls.getCookie(this, tag, key);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public File getFile(@NotNull String path) {
        return JsExtensions.DefaultImpls.getFile(this, path);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String getTxtInFolder(@NotNull String unzipPath) {
        return JsExtensions.DefaultImpls.getTxtInFolder(this, unzipPath);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public byte[] getZipByteArrayContent(@NotNull String url, @NotNull String path) {
        return JsExtensions.DefaultImpls.getZipByteArrayContent(this, url, path);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String getZipStringContent(@NotNull String url, @NotNull String path) {
        return JsExtensions.DefaultImpls.getZipStringContent(this, url, path);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String getZipStringContent(@NotNull String url, @NotNull String path, @NotNull String charsetName) {
        return JsExtensions.DefaultImpls.getZipStringContent(this, url, path, charsetName);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public Connection.Response head(@NotNull String urlStr, @NotNull Map<String, String> headers) {
        return JsExtensions.DefaultImpls.head(this, urlStr, headers);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String htmlFormat(@NotNull String str) {
        return JsExtensions.DefaultImpls.htmlFormat(this, str);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String importScript(@NotNull String path) {
        return JsExtensions.DefaultImpls.importScript(this, path);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String log(@NotNull String msg) {
        return JsExtensions.DefaultImpls.log(this, msg);
    }

    @Override // io.legado.app.help.JsExtensions
    public void logType(@Nullable Object any) {
        JsExtensions.DefaultImpls.logType(this, any);
    }

    @Override // io.legado.app.help.JsExtensions
    public void longToast(@Nullable Object msg) {
        JsExtensions.DefaultImpls.longToast(this, msg);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String md5Encode(@NotNull String str) {
        return JsExtensions.DefaultImpls.md5Encode(this, str);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String md5Encode16(@NotNull String str) {
        return JsExtensions.DefaultImpls.md5Encode16(this, str);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public Connection.Response post(@NotNull String urlStr, @NotNull String body, @NotNull Map<String, String> headers) {
        return JsExtensions.DefaultImpls.post(this, urlStr, body, headers);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public QueryTTF queryBase64TTF(@Nullable String base64) {
        return JsExtensions.DefaultImpls.queryBase64TTF(this, base64);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public QueryTTF queryTTF(@Nullable String str) {
        return JsExtensions.DefaultImpls.queryTTF(this, str);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String randomUUID() {
        return JsExtensions.DefaultImpls.randomUUID(this);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public byte[] readFile(@NotNull String path) {
        return JsExtensions.DefaultImpls.readFile(this, path);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String readTxtFile(@NotNull String path) {
        return JsExtensions.DefaultImpls.readTxtFile(this, path);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String readTxtFile(@NotNull String path, @NotNull String charsetName) {
        return JsExtensions.DefaultImpls.readTxtFile(this, path, charsetName);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String replaceFont(@NotNull String text, @Nullable QueryTTF font1, @Nullable QueryTTF font2) {
        return JsExtensions.DefaultImpls.replaceFont(this, text, font1, font2);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String timeFormat(long time) {
        return JsExtensions.DefaultImpls.timeFormat(this, time);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String timeFormatUTC(long time, @NotNull String format, int sh) {
        return JsExtensions.DefaultImpls.timeFormatUTC(this, time, format, sh);
    }

    @Override // io.legado.app.help.JsExtensions
    public void toast(@Nullable Object msg) {
        JsExtensions.DefaultImpls.toast(this, msg);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String tripleDESDecodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
        return JsExtensions.DefaultImpls.tripleDESDecodeArgsBase64Str(this, data, key, mode, padding, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String tripleDESDecodeStr(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
        return JsExtensions.DefaultImpls.tripleDESDecodeStr(this, data, key, mode, padding, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String tripleDESEncodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
        return JsExtensions.DefaultImpls.tripleDESEncodeArgsBase64Str(this, data, key, mode, padding, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String tripleDESEncodeBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
        return JsExtensions.DefaultImpls.tripleDESEncodeBase64Str(this, data, key, mode, padding, iv);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String unzipFile(@NotNull String zipPath) {
        return JsExtensions.DefaultImpls.unzipFile(this, zipPath);
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String utf8ToGbk(@NotNull String str) {
        return JsExtensions.DefaultImpls.utf8ToGbk(this, str);
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String webView(@Nullable String html, @Nullable String url, @Nullable String js) {
        return JsExtensions.DefaultImpls.webView(this, html, url, js);
    }

    @NotNull
    public final String getMUrl() {
        return this.mUrl;
    }

    @Nullable
    public final String getKey() {
        return this.key;
    }

    @Nullable
    public final Integer getPage() {
        return this.page;
    }

    @Nullable
    public final String getSpeakText() {
        return this.speakText;
    }

    @Nullable
    public final Integer getSpeakSpeed() {
        return this.speakSpeed;
    }

    @NotNull
    public final String getBaseUrl() {
        return this.baseUrl;
    }

    public final void setBaseUrl(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.baseUrl = str;
    }

    @Nullable
    public final DebugLog getDebugLog() {
        return this.debugLog;
    }

    public final void setDebugLog(@Nullable DebugLog debugLog) {
        this.debugLog = debugLog;
    }

    /* compiled from: AnalyzeUrl.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeUrl$Companion;", "", "()V", "concurrentRecordMap", "Ljava/util/HashMap;", "", "Lio/legado/app/model/analyzeRule/AnalyzeUrl$ConcurrentRecord;", "Lkotlin/collections/HashMap;", "pagePattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "paramPattern", "getParamPattern", "()Ljava/util/regex/Pattern;", "reader-pro"})
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeUrl$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final Pattern getParamPattern() {
            return AnalyzeUrl.paramPattern;
        }
    }

    static {
        Pattern patternCompile = Pattern.compile("\\s*,\\s*(?=\\{)");
        Intrinsics.checkNotNullExpressionValue(patternCompile, "compile(\"\\\\s*,\\\\s*(?=\\\\{)\")");
        paramPattern = patternCompile;
        pagePattern = Pattern.compile("<(.*?)>");
        concurrentRecordMap = new HashMap<>();
    }

    @NotNull
    public final String getRuleUrl() {
        return this.ruleUrl;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    @Nullable
    public final String getBody() {
        return this.body;
    }

    @Nullable
    public final String getType() {
        return this.type;
    }

    @NotNull
    public final HashMap<String, String> getHeaderMap() {
        return this.headerMap;
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String getUserNameSpace() {
        String userNameSpace;
        RuleDataInterface ruleDataInterface = this.ruleData;
        return (ruleDataInterface == null || (userNameSpace = ruleDataInterface.getUserNameSpace()) == null) ? "unknow" : userNameSpace;
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public BaseSource getSource() {
        return this.source;
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public DebugLog getLogger() {
        return this.debugLog;
    }

    public final void initUrl() throws UnsupportedEncodingException {
        this.ruleUrl = this.mUrl;
        analyzeJs();
        replaceKeyPageJs();
        analyzeUrl();
    }

    private final void analyzeJs() {
        int start = 0;
        Matcher jsMatcher = AppPattern.INSTANCE.getJS_PATTERN().matcher(this.ruleUrl);
        while (jsMatcher.find()) {
            if (jsMatcher.start() > start) {
                String str = this.ruleUrl;
                int iStart = jsMatcher.start();
                if (str == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                CharSequence $this$trim$iv = str.substring(start, iStart);
                Intrinsics.checkNotNullExpressionValue($this$trim$iv, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                CharSequence $this$trim$iv$iv = $this$trim$iv;
                int startIndex$iv$iv = 0;
                int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
                boolean startFound$iv$iv = false;
                while (startIndex$iv$iv <= endIndex$iv$iv) {
                    int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
                    char it = $this$trim$iv$iv.charAt(index$iv$iv);
                    boolean match$iv$iv = Intrinsics.compare((int) it, 32) <= 0;
                    if (startFound$iv$iv) {
                        if (!match$iv$iv) {
                            break;
                        } else {
                            endIndex$iv$iv--;
                        }
                    } else if (match$iv$iv) {
                        startIndex$iv$iv++;
                    } else {
                        startFound$iv$iv = true;
                    }
                }
                String tmp = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
                if (tmp.length() > 0) {
                    this.ruleUrl = StringsKt.replace$default(tmp, "@result", this.ruleUrl, false, 4, (Object) null);
                }
            }
            String strGroup = jsMatcher.group(2);
            String strGroup2 = strGroup == null ? jsMatcher.group(1) : strGroup;
            Intrinsics.checkNotNullExpressionValue(strGroup2, "jsMatcher.group(2) ?: jsMatcher.group(1)");
            Object objEvalJS = evalJS(strGroup2, this.ruleUrl);
            if (objEvalJS == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            this.ruleUrl = (String) objEvalJS;
            start = jsMatcher.end();
        }
        if (this.ruleUrl.length() > start) {
            String str2 = this.ruleUrl;
            if (str2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            CharSequence $this$trim$iv2 = str2.substring(start);
            Intrinsics.checkNotNullExpressionValue($this$trim$iv2, "(this as java.lang.String).substring(startIndex)");
            CharSequence $this$trim$iv$iv2 = $this$trim$iv2;
            int startIndex$iv$iv2 = 0;
            int endIndex$iv$iv2 = $this$trim$iv$iv2.length() - 1;
            boolean startFound$iv$iv2 = false;
            while (startIndex$iv$iv2 <= endIndex$iv$iv2) {
                int index$iv$iv2 = !startFound$iv$iv2 ? startIndex$iv$iv2 : endIndex$iv$iv2;
                char it2 = $this$trim$iv$iv2.charAt(index$iv$iv2);
                boolean match$iv$iv2 = Intrinsics.compare((int) it2, 32) <= 0;
                if (startFound$iv$iv2) {
                    if (!match$iv$iv2) {
                        break;
                    } else {
                        endIndex$iv$iv2--;
                    }
                } else if (match$iv$iv2) {
                    startIndex$iv$iv2++;
                } else {
                    startFound$iv$iv2 = true;
                }
            }
            String tmp2 = $this$trim$iv$iv2.subSequence(startIndex$iv$iv2, endIndex$iv$iv2 + 1).toString();
            if (tmp2.length() > 0) {
                this.ruleUrl = StringsKt.replace$default(tmp2, "@result", this.ruleUrl, false, 4, (Object) null);
            }
        }
    }

    private final void replaceKeyPageJs() {
        AnalyzeUrl analyzeUrl;
        String strReplace$default;
        if (StringsKt.contains$default((CharSequence) this.ruleUrl, (CharSequence) "{{", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) this.ruleUrl, (CharSequence) "}}", false, 2, (Object) null)) {
            RuleAnalyzer analyze = new RuleAnalyzer(this.ruleUrl, false, 2, null);
            String url = analyze.innerRule("{{", "}}", new Function1<String, String>() { // from class: io.legado.app.model.analyzeRule.AnalyzeUrl$replaceKeyPageJs$url$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                @Nullable
                public final String invoke(@NotNull String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object objEvalJS$default = AnalyzeUrl.evalJS$default(this.this$0, it, null, 2, null);
                    Object jsEval = objEvalJS$default == null ? "" : objEvalJS$default;
                    if (jsEval instanceof String) {
                        return (String) jsEval;
                    }
                    if (jsEval instanceof Double) {
                        if (((Number) jsEval).doubleValue() % 1.0d == 0.0d) {
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            Object[] objArr = {jsEval};
                            String str = String.format("%.0f", Arrays.copyOf(objArr, objArr.length));
                            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                            return str;
                        }
                    }
                    return jsEval.toString();
                }
            });
            if (url.length() > 0) {
                this.ruleUrl = url;
            }
        }
        Integer num = this.page;
        if (num != null) {
            num.intValue();
            Matcher matcher = pagePattern.matcher(getRuleUrl());
            while (matcher.find()) {
                String strGroup = matcher.group(1);
                Intrinsics.checkNotNull(strGroup);
                List pages = StringsKt.split$default((CharSequence) strGroup, new String[]{","}, false, 0, 6, (Object) null);
                if (getPage().intValue() < pages.size()) {
                    String ruleUrl = getRuleUrl();
                    String strGroup2 = matcher.group();
                    Intrinsics.checkNotNullExpressionValue(strGroup2, "matcher.group()");
                    CharSequence $this$trim$iv = (String) pages.get(getPage().intValue() - 1);
                    CharSequence $this$trim$iv$iv = $this$trim$iv;
                    int startIndex$iv$iv = 0;
                    int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
                    boolean startFound$iv$iv = false;
                    while (startIndex$iv$iv <= endIndex$iv$iv) {
                        int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
                        char it = $this$trim$iv$iv.charAt(index$iv$iv);
                        boolean match$iv$iv = Intrinsics.compare((int) it, 32) <= 0;
                        if (!startFound$iv$iv) {
                            if (!match$iv$iv) {
                                startFound$iv$iv = true;
                            } else {
                                startIndex$iv$iv++;
                            }
                        } else if (!match$iv$iv) {
                            break;
                        } else {
                            endIndex$iv$iv--;
                        }
                    }
                    String string = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
                    analyzeUrl = this;
                    strReplace$default = StringsKt.replace$default(ruleUrl, strGroup2, string, false, 4, (Object) null);
                } else {
                    String ruleUrl2 = getRuleUrl();
                    String strGroup3 = matcher.group();
                    Intrinsics.checkNotNullExpressionValue(strGroup3, "matcher.group()");
                    CharSequence $this$trim$iv2 = (String) CollectionsKt.last(pages);
                    CharSequence $this$trim$iv$iv2 = $this$trim$iv2;
                    int startIndex$iv$iv2 = 0;
                    int endIndex$iv$iv2 = $this$trim$iv$iv2.length() - 1;
                    boolean startFound$iv$iv2 = false;
                    while (startIndex$iv$iv2 <= endIndex$iv$iv2) {
                        int index$iv$iv2 = !startFound$iv$iv2 ? startIndex$iv$iv2 : endIndex$iv$iv2;
                        char it2 = $this$trim$iv$iv2.charAt(index$iv$iv2);
                        boolean match$iv$iv2 = Intrinsics.compare((int) it2, 32) <= 0;
                        if (!startFound$iv$iv2) {
                            if (!match$iv$iv2) {
                                startFound$iv$iv2 = true;
                            } else {
                                startIndex$iv$iv2++;
                            }
                        } else if (!match$iv$iv2) {
                            break;
                        } else {
                            endIndex$iv$iv2--;
                        }
                    }
                    String string2 = $this$trim$iv$iv2.subSequence(startIndex$iv$iv2, endIndex$iv$iv2 + 1).toString();
                    analyzeUrl = this;
                    strReplace$default = StringsKt.replace$default(ruleUrl2, strGroup3, string2, false, 4, (Object) null);
                }
                analyzeUrl.ruleUrl = strReplace$default;
            }
        }
    }

    private final void analyzeUrl() throws UnsupportedEncodingException {
        String strSubstring;
        Object objM2105constructorimpl;
        Object objEvalJS;
        String it;
        Matcher urlMatcher = paramPattern.matcher(this.ruleUrl);
        if (urlMatcher.find()) {
            String str = this.ruleUrl;
            int iStart = urlMatcher.start();
            if (str == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            strSubstring = str.substring(0, iStart);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        } else {
            strSubstring = this.ruleUrl;
        }
        String urlNoOption = strSubstring;
        this.url = NetworkUtils.INSTANCE.getAbsoluteURL(this.baseUrl, urlNoOption);
        String it2 = NetworkUtils.INSTANCE.getBaseUrl(this.url);
        if (it2 != null) {
            setBaseUrl(it2);
        }
        if (urlNoOption.length() != this.ruleUrl.length()) {
            Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
            String str2 = this.ruleUrl;
            int iEnd = urlMatcher.end();
            if (str2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String json$iv = str2.substring(iEnd);
            Intrinsics.checkNotNullExpressionValue(json$iv, "(this as java.lang.String).substring(startIndex)");
            try {
                Result.Companion companion = Result.Companion;
                Type type = new TypeToken<UrlOption>() { // from class: io.legado.app.model.analyzeRule.AnalyzeUrl$analyzeUrl$$inlined$fromJsonObject$1
                }.getType();
                Intrinsics.checkNotNullExpressionValue(type, "object : TypeToken<T>() {}.type");
                Object objFromJson = $this$fromJsonObject$iv.fromJson(json$iv, type);
                if (!(objFromJson instanceof UrlOption)) {
                    objFromJson = null;
                }
                objM2105constructorimpl = Result.m2105constructorimpl((UrlOption) objFromJson);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
            }
            Object obj = objM2105constructorimpl;
            UrlOption option = (UrlOption) (Result.m2101isFailureimpl(obj) ? null : obj);
            if (option != null) {
                String it3 = option.getMethod();
                if (it3 != null && StringsKt.equals(it3, "POST", true)) {
                    this.method = RequestMethod.POST;
                }
                Map $this$forEach$iv = option.getHeaderMap();
                if ($this$forEach$iv != null) {
                    for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
                        getHeaderMap().put(String.valueOf(element$iv.getKey()), String.valueOf(element$iv.getValue()));
                    }
                }
                String it4 = option.getBody();
                if (it4 != null) {
                    this.body = it4;
                }
                this.type = option.getType();
                this.charset = option.getCharset();
                this.retry = option.getRetry();
                this.useWebView = option.useWebView();
                this.webJs = option.getWebJs();
                String jsStr = option.getJs();
                if (jsStr != null && (objEvalJS = evalJS(jsStr, getUrl())) != null && (it = objEvalJS.toString()) != null) {
                    this.url = it;
                }
            }
        }
        if (this.headerMap.get("User-Agent") == null) {
            getHeaderMap().put("User-Agent", AppConst.INSTANCE.getUserAgent());
        }
        this.urlNoQuery = this.url;
        switch (WhenMappings.$EnumSwitchMapping$0[this.method.ordinal()]) {
            case 1:
                int pos = StringsKt.indexOf$default((CharSequence) this.url, '?', 0, false, 6, (Object) null);
                if (pos != -1) {
                    String str3 = this.url;
                    int i = pos + 1;
                    if (str3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring2 = str3.substring(i);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
                    analyzeFields(strSubstring2);
                    String str4 = this.url;
                    if (str4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring3 = str4.substring(0, pos);
                    Intrinsics.checkNotNullExpressionValue(strSubstring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    this.urlNoQuery = strSubstring3;
                    return;
                }
                return;
            case 2:
                String it5 = this.body;
                if (it5 == null || StringExtensionsKt.isJson(it5) || StringExtensionsKt.isXml(it5)) {
                    return;
                }
                String str5 = getHeaderMap().get("Content-Type");
                if (str5 == null || str5.length() == 0) {
                    analyzeFields(it5);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private final void analyzeFields(String fieldsTxt) throws UnsupportedEncodingException {
        this.queryStr = fieldsTxt;
        String[] queryS = StringExtensionsKt.splitNotBlank(fieldsTxt, BeanFactory.FACTORY_BEAN_PREFIX);
        int i = 0;
        int length = queryS.length;
        while (i < length) {
            String query = queryS[i];
            i++;
            String[] queryM = StringExtensionsKt.splitNotBlank(query, "=");
            String value = queryM.length > 1 ? queryM[1] : "";
            String str = this.charset;
            if (str == null || str.length() == 0) {
                if (NetworkUtils.INSTANCE.hasUrlEncoded(value)) {
                    this.fieldMap.put(queryM[0], value);
                } else {
                    LinkedHashMap<String, String> linkedHashMap = this.fieldMap;
                    String str2 = queryM[0];
                    String strEncode = URLEncoder.encode(value, "UTF-8");
                    Intrinsics.checkNotNullExpressionValue(strEncode, "encode(value, \"UTF-8\")");
                    linkedHashMap.put(str2, strEncode);
                }
            } else if (Intrinsics.areEqual(this.charset, "escape")) {
                this.fieldMap.put(queryM[0], EncoderUtils.INSTANCE.escape(value));
            } else {
                LinkedHashMap<String, String> linkedHashMap2 = this.fieldMap;
                String str3 = queryM[0];
                String strEncode2 = URLEncoder.encode(value, this.charset);
                Intrinsics.checkNotNullExpressionValue(strEncode2, "encode(value, charset)");
                linkedHashMap2.put(str3, strEncode2);
            }
        }
    }

    public static /* synthetic */ Object evalJS$default(AnalyzeUrl analyzeUrl, String str, Object obj, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = null;
        }
        return analyzeUrl.evalJS(str, obj);
    }

    @Nullable
    public final Object evalJS(@NotNull String jsStr, @Nullable Object result) {
        Intrinsics.checkNotNullParameter(jsStr, "jsStr");
        SimpleBindings bindings = new SimpleBindings();
        bindings.put((SimpleBindings) "java", (String) this);
        bindings.put((SimpleBindings) "baseUrl", this.baseUrl);
        bindings.put((SimpleBindings) "cookie", (String) new CookieStore(getUserNameSpace()));
        bindings.put((SimpleBindings) "cache", (String) new CacheManager(getUserNameSpace()));
        bindings.put((SimpleBindings) "page", (String) this.page);
        bindings.put((SimpleBindings) "key", this.key);
        bindings.put((SimpleBindings) "speakText", this.speakText);
        bindings.put((SimpleBindings) "speakSpeed", (String) this.speakSpeed);
        SimpleBindings simpleBindings = bindings;
        RuleDataInterface ruleDataInterface = this.ruleData;
        simpleBindings.put((SimpleBindings) "book", (String) (ruleDataInterface instanceof Book ? (Book) ruleDataInterface : null));
        bindings.put((SimpleBindings) PackageDocumentBase.DCTags.source, (String) this.source);
        bindings.put((SimpleBindings) CacheOperationExpressionEvaluator.RESULT_VARIABLE, (String) result);
        return AppConst.INSTANCE.getSCRIPT_ENGINE().eval(jsStr, bindings);
    }

    @NotNull
    public final String put(@NotNull String key, @NotNull String value) {
        Unit unit;
        RuleDataInterface ruleDataInterface;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        BookChapter bookChapter = this.chapter;
        if (bookChapter == null) {
            unit = null;
        } else {
            bookChapter.putVariable(key, value);
            unit = Unit.INSTANCE;
        }
        if (unit == null && (ruleDataInterface = this.ruleData) != null) {
            ruleDataInterface.putVariable(key, value);
        }
        return value;
    }

    @NotNull
    public final String get(@NotNull String key) {
        BookChapter it;
        String variable;
        Intrinsics.checkNotNullParameter(key, "key");
        if (Intrinsics.areEqual(key, "bookName")) {
            RuleDataInterface ruleDataInterface = this.ruleData;
            Book it2 = ruleDataInterface instanceof Book ? (Book) ruleDataInterface : null;
            if (it2 != null) {
                return it2.getName();
            }
        } else if (Intrinsics.areEqual(key, "title") && (it = this.chapter) != null) {
            return it.getTitle();
        }
        BookChapter bookChapter = this.chapter;
        String variable2 = bookChapter == null ? null : bookChapter.getVariable(key);
        if (variable2 != null) {
            return variable2;
        }
        RuleDataInterface ruleDataInterface2 = this.ruleData;
        if (ruleDataInterface2 != null && (variable = ruleDataInterface2.getVariable(key)) != null) {
            return variable;
        }
        return "";
    }

    private final ConcurrentRecord fetchStart() throws ConcurrentException {
        int iCurrentTimeMillis;
        int waitTime;
        if (this.source == null) {
            return null;
        }
        String concurrentRate = this.source.getConcurrentRate();
        String str = concurrentRate;
        if (str == null || str.length() == 0) {
            return null;
        }
        int rateIndex = StringsKt.indexOf$default((CharSequence) concurrentRate, "/", 0, false, 6, (Object) null);
        Object fetchRecord = concurrentRecordMap.get(this.source.getKey());
        if (fetchRecord == null) {
            ConcurrentRecord concurrentRecord = new ConcurrentRecord(rateIndex > 0, System.currentTimeMillis(), 1);
            concurrentRecordMap.put(this.source.getKey(), concurrentRecord);
            return concurrentRecord;
        }
        synchronized (fetchRecord) {
            try {
                if (rateIndex == -1) {
                    if (((ConcurrentRecord) fetchRecord).getFrequency() > 0) {
                        iCurrentTimeMillis = Integer.parseInt(concurrentRate);
                    } else {
                        long nextTime = ((ConcurrentRecord) fetchRecord).getTime() + Integer.parseInt(concurrentRate);
                        if (System.currentTimeMillis() >= nextTime) {
                            ((ConcurrentRecord) fetchRecord).setTime(System.currentTimeMillis());
                            ((ConcurrentRecord) fetchRecord).setFrequency(1);
                            iCurrentTimeMillis = 0;
                        } else {
                            iCurrentTimeMillis = (int) (nextTime - System.currentTimeMillis());
                        }
                    }
                } else {
                    int i = rateIndex + 1;
                    if (concurrentRate == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String sj = concurrentRate.substring(i);
                    Intrinsics.checkNotNullExpressionValue(sj, "(this as java.lang.String).substring(startIndex)");
                    long nextTime2 = ((ConcurrentRecord) fetchRecord).getTime() + Integer.parseInt(sj);
                    if (System.currentTimeMillis() >= nextTime2) {
                        ((ConcurrentRecord) fetchRecord).setTime(System.currentTimeMillis());
                        ((ConcurrentRecord) fetchRecord).setFrequency(1);
                        iCurrentTimeMillis = 0;
                    } else {
                        if (concurrentRate == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }
                        String cs = concurrentRate.substring(0, rateIndex);
                        Intrinsics.checkNotNullExpressionValue(cs, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        if (((ConcurrentRecord) fetchRecord).getFrequency() > Integer.parseInt(cs)) {
                            iCurrentTimeMillis = (int) (nextTime2 - System.currentTimeMillis());
                        } else {
                            ((ConcurrentRecord) fetchRecord).setFrequency(((ConcurrentRecord) fetchRecord).getFrequency() + 1);
                            iCurrentTimeMillis = 0;
                        }
                    }
                }
            } catch (Exception e) {
                iCurrentTimeMillis = 0;
            }
            waitTime = iCurrentTimeMillis;
        }
        if (waitTime > 0) {
            throw new ConcurrentException("根据并发率还需等待" + waitTime + "毫秒才可以访问", waitTime);
        }
        return (ConcurrentRecord) fetchRecord;
    }

    private final void fetchEnd(ConcurrentRecord concurrentRecord) {
        if (concurrentRecord != null && !concurrentRecord.getConcurrent()) {
            synchronized (concurrentRecord) {
                concurrentRecord.setFrequency(concurrentRecord.getFrequency() - 1);
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getStrResponseAwait(@org.jetbrains.annotations.Nullable java.lang.String r20, @org.jetbrains.annotations.Nullable java.lang.String r21, boolean r22, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.legado.app.help.http.StrResponse> r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 746
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.analyzeRule.AnalyzeUrl.getStrResponseAwait(java.lang.String, java.lang.String, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object getStrResponseAwait$default(AnalyzeUrl analyzeUrl, String str, String str2, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            z = true;
        }
        return analyzeUrl.getStrResponseAwait(str, str2, z, continuation);
    }

    public final void saveCookieJar(@NotNull Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        List cookieList = response.headers("Set-Cookie");
        if (cookieList.size() > 0) {
            CookieStore cookieStore = new CookieStore(getUserNameSpace());
            String domain = NetworkUtils.INSTANCE.getSubDomain(this.url);
            List $this$forEach$iv = cookieList;
            for (Object element$iv : $this$forEach$iv) {
                String it = (String) element$iv;
                cookieStore.replaceCookie(Intrinsics.stringPlus(domain, "_cookieJar"), it);
            }
        }
    }

    public static /* synthetic */ StrResponse getStrResponse$default(AnalyzeUrl analyzeUrl, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            z = true;
        }
        return analyzeUrl.getStrResponse(str, str2, z);
    }

    /* compiled from: AnalyzeUrl.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lio/legado/app/help/http/StrResponse;", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "AnalyzeUrl.kt", l = {435}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.legado.app.model.analyzeRule.AnalyzeUrl$getStrResponse$1")
    /* renamed from: io.legado.app.model.analyzeRule.AnalyzeUrl$getStrResponse$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeUrl$getStrResponse$1.class */
    static final class C02121 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super StrResponse>, Object> {
        int label;
        final /* synthetic */ String $jsStr;
        final /* synthetic */ String $sourceRegex;
        final /* synthetic */ boolean $useWebView;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02121(String $jsStr, String $sourceRegex, boolean $useWebView, Continuation<? super C02121> $completion) {
            super(2, $completion);
            this.$jsStr = $jsStr;
            this.$sourceRegex = $sourceRegex;
            this.$useWebView = $useWebView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return AnalyzeUrl.this.new C02121(this.$jsStr, this.$sourceRegex, this.$useWebView, $completion);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super StrResponse> p2) {
            return ((C02121) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object strResponseAwait = AnalyzeUrl.this.getStrResponseAwait(this.$jsStr, this.$sourceRegex, this.$useWebView, this);
                    if (strResponseAwait == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return strResponseAwait;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @JvmOverloads
    @NotNull
    public final StrResponse getStrResponse(@Nullable String jsStr, @Nullable String sourceRegex, boolean useWebView) {
        return (StrResponse) BuildersKt__BuildersKt.runBlocking$default(null, new C02121(jsStr, sourceRegex, useWebView, null), 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getResponseAwait(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super okhttp3.Response> r8) throws java.lang.Throwable {
        /*
            r7 = this;
            r0 = r8
            boolean r0 = r0 instanceof io.legado.app.model.analyzeRule.AnalyzeUrl.C02111
            if (r0 == 0) goto L29
            r0 = r8
            io.legado.app.model.analyzeRule.AnalyzeUrl$getResponseAwait$1 r0 = (io.legado.app.model.analyzeRule.AnalyzeUrl.C02111) r0
            r12 = r0
            r0 = r12
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L29
            r0 = r12
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L34
        L29:
            io.legado.app.model.analyzeRule.AnalyzeUrl$getResponseAwait$1 r0 = new io.legado.app.model.analyzeRule.AnalyzeUrl$getResponseAwait$1
            r1 = r0
            r2 = r7
            r3 = r8
            r1.<init>(r3)
            r12 = r0
        L34:
            r0 = r12
            java.lang.Object r0 = r0.result
            r11 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r13 = r0
            r0 = r12
            int r0 = r0.label
            switch(r0) {
                case 0: goto L5c;
                case 1: goto Lb6;
                default: goto Lda;
            }
        L5c:
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
            io.legado.app.model.analyzeRule.AnalyzeUrl$ConcurrentRecord r0 = r0.fetchStart()
            r9 = r0
            r0 = r7
            r1 = r7
            io.legado.app.data.entities.BaseSource r1 = r1.source
            r10 = r1
            r1 = r10
            if (r1 != 0) goto L74
            r1 = 0
            goto L7a
        L74:
            r1 = r10
            java.lang.String r1 = r1.getKey()
        L7a:
            r0.setCookie(r1)
            r0 = r7
            java.lang.String r0 = r0.proxy
            r1 = 0
            r2 = 2
            r3 = 0
            okhttp3.OkHttpClient r0 = io.legado.app.help.http.HttpHelperKt.getProxyClient$default(r0, r1, r2, r3)
            r1 = r7
            int r1 = r1.retry
            io.legado.app.model.analyzeRule.AnalyzeUrl$getResponseAwait$response$1 r2 = new io.legado.app.model.analyzeRule.AnalyzeUrl$getResponseAwait$response$1
            r3 = r2
            r4 = r7
            r3.<init>()
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            r3 = r12
            r4 = r12
            r5 = r7
            r4.L$0 = r5
            r4 = r12
            r5 = r9
            r4.L$1 = r5
            r4 = r12
            r5 = 1
            r4.label = r5
            java.lang.Object r0 = io.legado.app.help.http.OkHttpUtilsKt.newCallResponse(r0, r1, r2, r3)
            r1 = r0
            r2 = r13
            if (r1 != r2) goto Lcf
            r1 = r13
            return r1
        Lb6:
            r0 = r12
            java.lang.Object r0 = r0.L$1
            io.legado.app.model.analyzeRule.AnalyzeUrl$ConcurrentRecord r0 = (io.legado.app.model.analyzeRule.AnalyzeUrl.ConcurrentRecord) r0
            r9 = r0
            r0 = r12
            java.lang.Object r0 = r0.L$0
            io.legado.app.model.analyzeRule.AnalyzeUrl r0 = (io.legado.app.model.analyzeRule.AnalyzeUrl) r0
            r7 = r0
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r11
        Lcf:
            okhttp3.Response r0 = (okhttp3.Response) r0
            r10 = r0
            r0 = r7
            r1 = r9
            r0.fetchEnd(r1)
            r0 = r10
            return r0
        Lda:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.analyzeRule.AnalyzeUrl.getResponseAwait(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: AnalyzeUrl.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lokhttp3/Response;", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "AnalyzeUrl.kt", l = {471}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.legado.app.model.analyzeRule.AnalyzeUrl$getResponse$1")
    /* renamed from: io.legado.app.model.analyzeRule.AnalyzeUrl$getResponse$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeUrl$getResponse$1.class */
    static final class C02101 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Response>, Object> {
        int label;

        C02101(Continuation<? super C02101> $completion) {
            super(2, $completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return AnalyzeUrl.this.new C02101($completion);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Response> p2) {
            return ((C02101) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object responseAwait = AnalyzeUrl.this.getResponseAwait(this);
                    if (responseAwait == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return responseAwait;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @NotNull
    public final Response getResponse() {
        return (Response) BuildersKt__BuildersKt.runBlocking$default(null, new C02101(null), 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getByteArrayAwait(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super byte[]> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.analyzeRule.AnalyzeUrl.getByteArrayAwait(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: AnalyzeUrl.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0012\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "AnalyzeUrl.kt", l = {517}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.legado.app.model.analyzeRule.AnalyzeUrl$getByteArray$1")
    /* renamed from: io.legado.app.model.analyzeRule.AnalyzeUrl$getByteArray$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeUrl$getByteArray$1.class */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super byte[]>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> $completion) {
            super(2, $completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return AnalyzeUrl.this.new AnonymousClass1($completion);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super byte[]> p2) {
            return ((AnonymousClass1) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object byteArrayAwait = AnalyzeUrl.this.getByteArrayAwait(this);
                    if (byteArrayAwait == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return byteArrayAwait;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @NotNull
    public final byte[] getByteArray() {
        return (byte[]) BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(null), 1, null);
    }

    @Nullable
    public final Object upload(@NotNull final String fileName, @NotNull final Object file, @NotNull final String contentType, @NotNull Continuation<? super StrResponse> $completion) {
        return OkHttpUtilsKt.newCallStrResponse(HttpHelperKt.getProxyClient$default(this.proxy, null, 2, null), this.retry, new Function1<Request.Builder, Unit>() { // from class: io.legado.app.model.analyzeRule.AnalyzeUrl.upload.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Request.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Request.Builder newCallStrResponse) {
                Object objM2105constructorimpl;
                Intrinsics.checkNotNullParameter(newCallStrResponse, "$this$newCallStrResponse");
                newCallStrResponse.url(AnalyzeUrl.this.urlNoQuery);
                Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
                String json$iv = AnalyzeUrl.this.getBody();
                try {
                    Result.Companion companion = Result.Companion;
                    Type type = new TypeToken<HashMap<String, Object>>() { // from class: io.legado.app.model.analyzeRule.AnalyzeUrl$upload$2$invoke$$inlined$fromJsonObject$1
                    }.getType();
                    Intrinsics.checkNotNullExpressionValue(type, "object : TypeToken<T>() {}.type");
                    Object objFromJson = $this$fromJsonObject$iv.fromJson(json$iv, type);
                    if (!(objFromJson instanceof HashMap)) {
                        objFromJson = null;
                    }
                    objM2105constructorimpl = Result.m2105constructorimpl((HashMap) objFromJson);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
                }
                Object obj = objM2105constructorimpl;
                Object obj2 = Result.m2101isFailureimpl(obj) ? null : obj;
                Intrinsics.checkNotNull(obj2);
                Map bodyMap = (HashMap) obj2;
                Map $this$forEach$iv = bodyMap;
                String str = fileName;
                Object obj3 = file;
                String str2 = contentType;
                for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
                    if (Intrinsics.areEqual(element$iv.getValue().toString(), "fileRequest")) {
                        bodyMap.put(element$iv.getKey(), MapsKt.mapOf(new Pair("fileName", str), new Pair("file", obj3), new Pair("contentType", str2)));
                    }
                }
                OkHttpUtilsKt.postMultipart(newCallStrResponse, AnalyzeUrl.this.getType(), bodyMap);
            }
        }, $completion);
    }

    private final void setCookie(String tag) {
        String domain = NetworkUtils.INSTANCE.getSubDomain(tag == null ? this.url : tag);
        if (domain.length() == 0) {
            return;
        }
        CookieStore cookieStore = new CookieStore(getUserNameSpace());
        if (this.enabledCookieJar) {
            String key = Intrinsics.stringPlus(domain, "_cookieJar");
            String it = cookieStore.getCookie(key);
            if (it != null) {
                cookieStore.replaceCookie(domain, it);
            }
        }
        String cookie = cookieStore.getCookie(domain);
        if (cookie.length() > 0) {
            Map cookieMap = cookieStore.cookieToMap(cookie);
            String str = this.headerMap.get("Cookie");
            Map customCookieMap = cookieStore.cookieToMap(str == null ? "" : str);
            cookieMap.putAll(customCookieMap);
            String newCookie = cookieStore.mapToCookie(cookieMap);
            if (newCookie != null) {
                getHeaderMap().put("Cookie", newCookie);
            }
        }
    }

    @NotNull
    public final String getUserAgent() {
        String str = this.headerMap.get("User-Agent");
        return str == null ? AppConst.INSTANCE.getUserAgent() : str;
    }

    public final boolean isPost() {
        return this.method == RequestMethod.POST;
    }

    /* compiled from: AnalyzeUrl.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\f\b\u0086\b\u0018��2\u00020\u0001Bq\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÂ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÂ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\bHÂ\u0003¢\u0006\u0002\u0010\u0014J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÂ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÂ\u0003Jz\u0010\u0019\u001a\u00020��2\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003J\b\u0010\u001f\u001a\u0004\u0018\u00010\u0003J\u0010\u0010 \u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010!J\b\u0010\"\u001a\u0004\u0018\u00010\u0003J\b\u0010#\u001a\u0004\u0018\u00010\u0003J\u0006\u0010$\u001a\u00020\bJ\b\u0010%\u001a\u0004\u0018\u00010\u0003J\b\u0010&\u001a\u0004\u0018\u00010\u0003J\t\u0010'\u001a\u00020\bHÖ\u0001J\u0010\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u0010+\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u0010,\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u0010-\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u0010.\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u0010/\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u00100\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u00101\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\t\u00102\u001a\u00020\u0003HÖ\u0001J\u0006\u00103\u001a\u00020\u001cJ\u000e\u00103\u001a\u00020)2\u0006\u00104\u001a\u00020\u001cR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n��R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n��¨\u00065"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeUrl$UrlOption;", "", "method", "", "charset", "headers", NCXDocumentV3.XHTMLTgs.body, "retry", "", "type", "webView", "webJs", "js", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "()Ljava/lang/Integer;", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)Lio/legado/app/model/analyzeRule/AnalyzeUrl$UrlOption;", "equals", "", "other", "getBody", "getCharset", "getHeaderMap", "", "getJs", "getMethod", "getRetry", "getType", "getWebJs", IdentityNamingStrategy.HASH_CODE_KEY, "setBody", "", "value", "setCharset", "setHeaders", "setJs", "setMethod", "setRetry", "setType", "setWebJs", "toString", "useWebView", "boolean", "reader-pro"})
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeUrl$UrlOption.class */
    public static final class UrlOption {

        @Nullable
        private String method;

        @Nullable
        private String charset;

        @Nullable
        private Object headers;

        @Nullable
        private Object body;

        @Nullable
        private Integer retry;

        @Nullable
        private String type;

        @Nullable
        private Object webView;

        @Nullable
        private String webJs;

        @Nullable
        private String js;

        private final String component1() {
            return this.method;
        }

        private final String component2() {
            return this.charset;
        }

        private final Object component3() {
            return this.headers;
        }

        private final Object component4() {
            return this.body;
        }

        private final Integer component5() {
            return this.retry;
        }

        private final String component6() {
            return this.type;
        }

        private final Object component7() {
            return this.webView;
        }

        private final String component8() {
            return this.webJs;
        }

        private final String component9() {
            return this.js;
        }

        @NotNull
        public final UrlOption copy(@Nullable String method, @Nullable String charset, @Nullable Object headers, @Nullable Object body, @Nullable Integer retry, @Nullable String type, @Nullable Object webView, @Nullable String webJs, @Nullable String js) {
            return new UrlOption(method, charset, headers, body, retry, type, webView, webJs, js);
        }

        public static /* synthetic */ UrlOption copy$default(UrlOption urlOption, String str, String str2, Object obj, Object obj2, Integer num, String str3, Object obj3, String str4, String str5, int i, Object obj4) {
            if ((i & 1) != 0) {
                str = urlOption.method;
            }
            if ((i & 2) != 0) {
                str2 = urlOption.charset;
            }
            if ((i & 4) != 0) {
                obj = urlOption.headers;
            }
            if ((i & 8) != 0) {
                obj2 = urlOption.body;
            }
            if ((i & 16) != 0) {
                num = urlOption.retry;
            }
            if ((i & 32) != 0) {
                str3 = urlOption.type;
            }
            if ((i & 64) != 0) {
                obj3 = urlOption.webView;
            }
            if ((i & 128) != 0) {
                str4 = urlOption.webJs;
            }
            if ((i & 256) != 0) {
                str5 = urlOption.js;
            }
            return urlOption.copy(str, str2, obj, obj2, num, str3, obj3, str4, str5);
        }

        @NotNull
        public String toString() {
            return "UrlOption(method=" + ((Object) this.method) + ", charset=" + ((Object) this.charset) + ", headers=" + this.headers + ", body=" + this.body + ", retry=" + this.retry + ", type=" + ((Object) this.type) + ", webView=" + this.webView + ", webJs=" + ((Object) this.webJs) + ", js=" + ((Object) this.js) + ')';
        }

        public int hashCode() {
            int result = this.method == null ? 0 : this.method.hashCode();
            return (((((((((((((((result * 31) + (this.charset == null ? 0 : this.charset.hashCode())) * 31) + (this.headers == null ? 0 : this.headers.hashCode())) * 31) + (this.body == null ? 0 : this.body.hashCode())) * 31) + (this.retry == null ? 0 : this.retry.hashCode())) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.webView == null ? 0 : this.webView.hashCode())) * 31) + (this.webJs == null ? 0 : this.webJs.hashCode())) * 31) + (this.js == null ? 0 : this.js.hashCode());
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UrlOption)) {
                return false;
            }
            UrlOption urlOption = (UrlOption) other;
            return Intrinsics.areEqual(this.method, urlOption.method) && Intrinsics.areEqual(this.charset, urlOption.charset) && Intrinsics.areEqual(this.headers, urlOption.headers) && Intrinsics.areEqual(this.body, urlOption.body) && Intrinsics.areEqual(this.retry, urlOption.retry) && Intrinsics.areEqual(this.type, urlOption.type) && Intrinsics.areEqual(this.webView, urlOption.webView) && Intrinsics.areEqual(this.webJs, urlOption.webJs) && Intrinsics.areEqual(this.js, urlOption.js);
        }

        public UrlOption() {
            this(null, null, null, null, null, null, null, null, null, 511, null);
        }

        public UrlOption(@Nullable String method, @Nullable String charset, @Nullable Object headers, @Nullable Object body, @Nullable Integer retry, @Nullable String type, @Nullable Object webView, @Nullable String webJs, @Nullable String js) {
            this.method = method;
            this.charset = charset;
            this.headers = headers;
            this.body = body;
            this.retry = retry;
            this.type = type;
            this.webView = webView;
            this.webJs = webJs;
            this.js = js;
        }

        public /* synthetic */ UrlOption(String str, String str2, Object obj, Object obj2, Integer num, String str3, Object obj3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : obj, (i & 8) != 0 ? null : obj2, (i & 16) != 0 ? null : num, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : obj3, (i & 128) != 0 ? null : str4, (i & 256) != 0 ? null : str5);
        }

        public final void setMethod(@Nullable String value) {
            String str = value;
            this.method = str == null || StringsKt.isBlank(str) ? null : value;
        }

        @Nullable
        public final String getMethod() {
            return this.method;
        }

        public final void setCharset(@Nullable String value) {
            String str = value;
            this.charset = str == null || StringsKt.isBlank(str) ? null : value;
        }

        @Nullable
        public final String getCharset() {
            return this.charset;
        }

        public final void setRetry(@Nullable String value) {
            String str = value;
            this.retry = str == null || str.length() == 0 ? null : StringsKt.toIntOrNull(value);
        }

        public final int getRetry() {
            Integer num = this.retry;
            if (num == null) {
                return 0;
            }
            return num.intValue();
        }

        public final void setType(@Nullable String value) {
            String str = value;
            this.type = str == null || StringsKt.isBlank(str) ? null : value;
        }

        @Nullable
        public final String getType() {
            return this.type;
        }

        public final boolean useWebView() {
            Object obj = this.webView;
            return !(obj == null ? true : Intrinsics.areEqual(obj, "") ? true : Intrinsics.areEqual(obj, (Object) false) ? true : Intrinsics.areEqual(obj, "false"));
        }

        public final void useWebView(boolean z) {
            this.webView = z ? true : null;
        }

        public final void setHeaders(@Nullable String value) {
            Object objM2105constructorimpl;
            Map map;
            UrlOption urlOption = this;
            String str = value;
            if (str == null || StringsKt.isBlank(str)) {
                map = (Map) null;
            } else {
                Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
                try {
                    Result.Companion companion = Result.Companion;
                    Type type = new TypeToken<Map<String, ? extends Object>>() { // from class: io.legado.app.model.analyzeRule.AnalyzeUrl$UrlOption$setHeaders$$inlined$fromJsonObject$1
                    }.getType();
                    Intrinsics.checkNotNullExpressionValue(type, "object : TypeToken<T>() {}.type");
                    Object objFromJson = $this$fromJsonObject$iv.fromJson(value, type);
                    if (!(objFromJson instanceof Map)) {
                        objFromJson = null;
                    }
                    objM2105constructorimpl = Result.m2105constructorimpl((Map) objFromJson);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
                }
                Object obj = objM2105constructorimpl;
                urlOption = urlOption;
                map = (Map) (Result.m2101isFailureimpl(obj) ? null : obj);
            }
            urlOption.headers = map;
        }

        @Nullable
        public final Map<?, ?> getHeaderMap() {
            Object objM2105constructorimpl;
            Object value = this.headers;
            if (value instanceof Map) {
                return (Map) value;
            }
            if (!(value instanceof String)) {
                return null;
            }
            Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
            String json$iv = (String) value;
            try {
                Result.Companion companion = Result.Companion;
                Type type = new TypeToken<Map<String, ? extends Object>>() { // from class: io.legado.app.model.analyzeRule.AnalyzeUrl$UrlOption$getHeaderMap$$inlined$fromJsonObject$1
                }.getType();
                Intrinsics.checkNotNullExpressionValue(type, "object : TypeToken<T>() {}.type");
                Object objFromJson = $this$fromJsonObject$iv.fromJson(json$iv, type);
                if (!(objFromJson instanceof Map)) {
                    objFromJson = null;
                }
                objM2105constructorimpl = Result.m2105constructorimpl((Map) objFromJson);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
            }
            Object obj = objM2105constructorimpl;
            return (Map) (Result.m2101isFailureimpl(obj) ? null : obj);
        }

        public final void setBody(@Nullable String value) {
            String strM2106boximpl;
            Object objM2105constructorimpl;
            Object objM2105constructorimpl2;
            UrlOption urlOption = this;
            String str = value;
            if (str == null || StringsKt.isBlank(str)) {
                strM2106boximpl = null;
            } else if (!StringExtensionsKt.isJsonObject(value)) {
                if (!StringExtensionsKt.isJsonArray(value)) {
                    strM2106boximpl = value;
                } else {
                    Gson $this$fromJsonArray$iv = GsonExtensionsKt.getGSON();
                    try {
                        Result.Companion companion = Result.Companion;
                        Object objFromJson = $this$fromJsonArray$iv.fromJson(value, new ParameterizedTypeImpl(Map.class));
                        objM2105constructorimpl = Result.m2105constructorimpl(objFromJson instanceof List ? (List) objFromJson : null);
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.Companion;
                        objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
                    }
                    urlOption = urlOption;
                    strM2106boximpl = Result.m2106boximpl(objM2105constructorimpl);
                }
            } else {
                Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
                try {
                    Result.Companion companion3 = Result.Companion;
                    Type type = new TypeToken<Map<String, ? extends Object>>() { // from class: io.legado.app.model.analyzeRule.AnalyzeUrl$UrlOption$setBody$$inlined$fromJsonObject$1
                    }.getType();
                    Intrinsics.checkNotNullExpressionValue(type, "object : TypeToken<T>() {}.type");
                    Object objFromJson2 = $this$fromJsonObject$iv.fromJson(value, type);
                    if (!(objFromJson2 instanceof Map)) {
                        objFromJson2 = null;
                    }
                    objM2105constructorimpl2 = Result.m2105constructorimpl((Map) objFromJson2);
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.Companion;
                    objM2105constructorimpl2 = Result.m2105constructorimpl(ResultKt.createFailure(th2));
                }
                urlOption = urlOption;
                strM2106boximpl = Result.m2106boximpl(objM2105constructorimpl2);
            }
            urlOption.body = strM2106boximpl;
        }

        @Nullable
        public final String getBody() {
            Object it = this.body;
            if (it == null) {
                return null;
            }
            return it instanceof String ? (String) it : GsonExtensionsKt.getGSON().toJson(it);
        }

        public final void setWebJs(@Nullable String value) {
            String str = value;
            this.webJs = str == null || StringsKt.isBlank(str) ? null : value;
        }

        @Nullable
        public final String getWebJs() {
            return this.webJs;
        }

        public final void setJs(@Nullable String value) {
            String str = value;
            this.js = str == null || StringsKt.isBlank(str) ? null : value;
        }

        @Nullable
        public final String getJs() {
            return this.js;
        }
    }

    /* compiled from: AnalyzeUrl.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\t\n��\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J'\u0010\u0016\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeUrl$ConcurrentRecord;", "", "concurrent", "", "time", "", "frequency", "", "(ZJI)V", "getConcurrent", "()Z", "getFrequency", "()I", "setFrequency", "(I)V", "getTime", "()J", "setTime", "(J)V", "component1", "component2", "component3", "copy", "equals", "other", IdentityNamingStrategy.HASH_CODE_KEY, "toString", "", "reader-pro"})
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeUrl$ConcurrentRecord.class */
    public static final class ConcurrentRecord {
        private final boolean concurrent;
        private long time;
        private int frequency;

        public final boolean component1() {
            return this.concurrent;
        }

        public final long component2() {
            return this.time;
        }

        public final int component3() {
            return this.frequency;
        }

        @NotNull
        public final ConcurrentRecord copy(boolean concurrent, long time, int frequency) {
            return new ConcurrentRecord(concurrent, time, frequency);
        }

        public static /* synthetic */ ConcurrentRecord copy$default(ConcurrentRecord concurrentRecord, boolean z, long j, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z = concurrentRecord.concurrent;
            }
            if ((i2 & 2) != 0) {
                j = concurrentRecord.time;
            }
            if ((i2 & 4) != 0) {
                i = concurrentRecord.frequency;
            }
            return concurrentRecord.copy(z, j, i);
        }

        @NotNull
        public String toString() {
            return "ConcurrentRecord(concurrent=" + this.concurrent + ", time=" + this.time + ", frequency=" + this.frequency + ')';
        }

        public int hashCode() {
            boolean z = this.concurrent;
            if (z) {
                z = true;
            }
            return ((((z ? 1 : 0) * 31) + Long.hashCode(this.time)) * 31) + Integer.hashCode(this.frequency);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ConcurrentRecord)) {
                return false;
            }
            ConcurrentRecord concurrentRecord = (ConcurrentRecord) other;
            return this.concurrent == concurrentRecord.concurrent && this.time == concurrentRecord.time && this.frequency == concurrentRecord.frequency;
        }

        public ConcurrentRecord(boolean concurrent, long time, int frequency) {
            this.concurrent = concurrent;
            this.time = time;
            this.frequency = frequency;
        }

        public final boolean getConcurrent() {
            return this.concurrent;
        }

        public final long getTime() {
            return this.time;
        }

        public final void setTime(long j) {
            this.time = j;
        }

        public final int getFrequency() {
            return this.frequency;
        }

        public final void setFrequency(int i) {
            this.frequency = i;
        }
    }
}
