package io.legado.app.model.analyzeRule;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.script.SimpleBindings;
import io.legado.app.constant.AppConst;
import io.legado.app.constant.AppPattern;
import io.legado.app.data.entities.BaseBook;
import io.legado.app.data.entities.BaseSource;
import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.data.entities.BookSource;
import io.legado.app.data.entities.SearchBook;
import io.legado.app.help.CacheManager;
import io.legado.app.help.JsExtensions;
import io.legado.app.help.http.CookieStore;
import io.legado.app.help.http.StrResponse;
import io.legado.app.model.DebugLog;
import io.legado.app.model.webBook.WebBook;
import io.legado.app.utils.GsonExtensionsKt;
import io.legado.app.utils.NetworkUtils;
import io.legado.app.utils.StringExtensionsKt;
import io.legado.app.utils.StringUtils;
import io.legado.app.utils.TextUtils;
import io.legado.app.utils.ThrowableExtensionsKt;
import io.vertx.ext.web.handler.SessionHandler;
import java.io.File;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;
import me.ag2s.epublib.epub.NCXDocumentV2;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Connection;
import org.mozilla.javascript.NativeObject;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* compiled from: AnalyzeRule.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010��\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n��\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018�� g2\u00020\u0001:\u0003ghiB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0012\u00108\u001a\u0004\u0018\u00010\u00102\u0006\u00109\u001a\u00020\u0010H\u0016J\u001c\u0010:\u001a\u0004\u0018\u00010\u001e2\u0006\u0010;\u001a\u00020\u00102\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u001eJ\u000e\u0010=\u001a\u00020\u00102\u0006\u0010>\u001a\u00020\u0010J\u0010\u0010?\u001a\u00020\n2\u0006\u0010@\u001a\u00020\u001eH\u0002J\u0010\u0010A\u001a\u00020\f2\u0006\u0010@\u001a\u00020\u001eH\u0002J\u0010\u0010B\u001a\u00020\u000e2\u0006\u0010@\u001a\u00020\u001eH\u0002J\u0010\u0010C\u001a\u0004\u0018\u00010\u001e2\u0006\u0010D\u001a\u00020\u0010J\u0014\u0010E\u001a\b\u0012\u0004\u0012\u00020\u001e0F2\u0006\u0010D\u001a\u00020\u0010J\n\u0010G\u001a\u0004\u0018\u00010\u0007H\u0016J\n\u0010H\u001a\u0004\u0018\u00010\u0005H\u0016J(\u0010I\u001a\u00020\u00102\b\u0010D\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010K\u001a\u00020'H\u0007J0\u0010I\u001a\u00020\u00102\u0010\u0010L\u001a\f\u0012\b\u0012\u00060MR\u00020��0F2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010K\u001a\u00020'H\u0007J0\u0010N\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010F2\b\u0010O\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010K\u001a\u00020'H\u0007J8\u0010N\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010F2\u0010\u0010L\u001a\f\u0012\b\u0012\u00060MR\u00020��0F2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010K\u001a\u00020'H\u0007J\b\u0010P\u001a\u00020\u0010H\u0016J\u0016\u0010Q\u001a\u00020\u00102\u0006\u0010>\u001a\u00020\u00102\u0006\u0010R\u001a\u00020\u0010J\u001c\u0010S\u001a\u00020T2\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100VH\u0002J\u0006\u0010W\u001a\u00020TJ\u0006\u0010X\u001a\u00020TJ\u0006\u0010Y\u001a\u00020TJ\u001c\u0010Z\u001a\u00020\u00102\u0006\u0010<\u001a\u00020\u00102\n\u0010O\u001a\u00060MR\u00020��H\u0002J\u0010\u0010[\u001a\u00020��2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010J\u001e\u0010\\\u001a\u00020��2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0007J\u0010\u0010]\u001a\u0004\u0018\u0001002\u0006\u0010^\u001a\u00020\u0010J4\u0010_\u001a\u00020\u00102\u0006\u0010D\u001a\u00020\u00102\"\u0010`\u001a\u001e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100aj\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010`bH\u0002J$\u0010c\u001a\f\u0012\b\u0012\u00060MR\u00020��0F2\b\u0010D\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010d\u001a\u00020'J\u0012\u0010e\u001a\u0004\u0018\u00010\u00102\b\u0010f\u001a\u0004\u0018\u00010\u0010R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n��R\"\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u001e@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b \u0010!R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010(\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n��R\u001c\u0010)\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b*\u0010\u0013\"\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010.\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010/\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n��R\"\u00101\u001a\u0004\u0018\u0001002\b\u0010\u000f\u001a\u0004\u0018\u000100@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b2\u00103R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006j"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeRule;", "Lio/legado/app/help/JsExtensions;", "ruleData", "Lio/legado/app/model/analyzeRule/RuleDataInterface;", PackageDocumentBase.DCTags.source, "Lio/legado/app/data/entities/BaseSource;", "debugLog", "Lio/legado/app/model/DebugLog;", "(Lio/legado/app/model/analyzeRule/RuleDataInterface;Lio/legado/app/data/entities/BaseSource;Lio/legado/app/model/DebugLog;)V", "analyzeByJSonPath", "Lio/legado/app/model/analyzeRule/AnalyzeByJSonPath;", "analyzeByJSoup", "Lio/legado/app/model/analyzeRule/AnalyzeByJSoup;", "analyzeByXPath", "Lio/legado/app/model/analyzeRule/AnalyzeByXPath;", "<set-?>", "", "baseUrl", "getBaseUrl", "()Ljava/lang/String;", "book", "Lio/legado/app/data/entities/BaseBook;", "getBook", "()Lio/legado/app/data/entities/BaseBook;", NCXDocumentV2.NCXAttributeValues.chapter, "Lio/legado/app/data/entities/BookChapter;", "getChapter", "()Lio/legado/app/data/entities/BookChapter;", "setChapter", "(Lio/legado/app/data/entities/BookChapter;)V", "", "content", "getContent", "()Ljava/lang/Object;", "getDebugLog", "()Lio/legado/app/model/DebugLog;", "setDebugLog", "(Lio/legado/app/model/DebugLog;)V", "isJSON", "", "isRegex", "nextChapterUrl", "getNextChapterUrl", "setNextChapterUrl", "(Ljava/lang/String;)V", "objectChangedJP", "objectChangedJS", "objectChangedXP", "Ljava/net/URL;", "redirectUrl", "getRedirectUrl", "()Ljava/net/URL;", "getRuleData", "()Lio/legado/app/model/analyzeRule/RuleDataInterface;", "setRuleData", "(Lio/legado/app/model/analyzeRule/RuleDataInterface;)V", "ajax", "urlStr", "evalJS", "jsStr", CacheOperationExpressionEvaluator.RESULT_VARIABLE, BeanUtil.PREFIX_GETTER_GET, "key", "getAnalyzeByJSonPath", "o", "getAnalyzeByJSoup", "getAnalyzeByXPath", "getElement", "ruleStr", "getElements", "", "getLogger", "getSource", "getString", "mContent", "isUrl", "ruleList", "Lio/legado/app/model/analyzeRule/AnalyzeRule$SourceRule;", "getStringList", "rule", "getUserNameSpace", "put", "value", "putRule", "", BeanDefinitionParserDelegate.MAP_ELEMENT, "", "reGetBook", "refreshBookUrl", "refreshTocUrl", "replaceRegex", "setBaseUrl", "setContent", "setRedirectUrl", "url", "splitPutRule", "putMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "splitSourceRule", "allInOne", "toNumChapter", OperatorName.CLOSE_AND_STROKE, "Companion", "Mode", "SourceRule", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeRule.class */
public final class AnalyzeRule implements JsExtensions {

    @NotNull
    private RuleDataInterface ruleData;

    @Nullable
    private final BaseSource source;

    @Nullable
    private DebugLog debugLog;

    @Nullable
    private BookChapter chapter;

    @Nullable
    private String nextChapterUrl;

    @Nullable
    private Object content;

    @Nullable
    private String baseUrl;

    @Nullable
    private URL redirectUrl;
    private boolean isJSON;
    private boolean isRegex;

    @Nullable
    private AnalyzeByXPath analyzeByXPath;

    @Nullable
    private AnalyzeByJSoup analyzeByJSoup;

    @Nullable
    private AnalyzeByJSonPath analyzeByJSonPath;
    private boolean objectChangedXP;
    private boolean objectChangedJS;
    private boolean objectChangedJP;

    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final Pattern putPattern = Pattern.compile("@put:(\\{[^}]+?\\})", 2);
    private static final Pattern evalPattern = Pattern.compile("@get:\\{[^}]+?\\}|\\{\\{[\\w\\W]*?\\}\\}", 2);
    private static final Pattern regexPattern = Pattern.compile("\\$\\d{1,2}");
    private static final Pattern titleNumPattern = Pattern.compile("(第)(.+?)(章)");

    /* compiled from: AnalyzeRule.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeRule$Mode;", "", "(Ljava/lang/String;I)V", "XPath", "Json", "Default", "Js", "Regex", "reader-pro"})
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeRule$Mode.class */
    public enum Mode {
        XPath,
        Json,
        Default,
        Js,
        Regex
    }

    /* compiled from: AnalyzeRule.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeRule$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Mode.values().length];
            iArr[Mode.Js.ordinal()] = 1;
            iArr[Mode.Json.ordinal()] = 2;
            iArr[Mode.XPath.ordinal()] = 3;
            iArr[Mode.Default.ordinal()] = 4;
            iArr[Mode.Regex.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmOverloads
    @NotNull
    public final AnalyzeRule setContent(@Nullable Object content) {
        return setContent$default(this, content, null, 2, null);
    }

    @JvmOverloads
    @Nullable
    public final List<String> getStringList(@Nullable String rule, @Nullable Object mContent) {
        return getStringList$default(this, rule, mContent, false, 4, (Object) null);
    }

    @JvmOverloads
    @Nullable
    public final List<String> getStringList(@Nullable String rule) {
        return getStringList$default(this, rule, (Object) null, false, 6, (Object) null);
    }

    @JvmOverloads
    @Nullable
    public final List<String> getStringList(@NotNull List<SourceRule> ruleList, @Nullable Object mContent) {
        Intrinsics.checkNotNullParameter(ruleList, "ruleList");
        return getStringList$default(this, (List) ruleList, mContent, false, 4, (Object) null);
    }

    @JvmOverloads
    @Nullable
    public final List<String> getStringList(@NotNull List<SourceRule> ruleList) {
        Intrinsics.checkNotNullParameter(ruleList, "ruleList");
        return getStringList$default(this, (List) ruleList, (Object) null, false, 6, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public final String getString(@Nullable String ruleStr, @Nullable Object mContent) {
        return getString$default(this, ruleStr, mContent, false, 4, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public final String getString(@Nullable String ruleStr) {
        return getString$default(this, ruleStr, (Object) null, false, 6, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public final String getString(@NotNull List<SourceRule> ruleList, @Nullable Object mContent) {
        Intrinsics.checkNotNullParameter(ruleList, "ruleList");
        return getString$default(this, (List) ruleList, mContent, false, 4, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public final String getString(@NotNull List<SourceRule> ruleList) {
        Intrinsics.checkNotNullParameter(ruleList, "ruleList");
        return getString$default(this, (List) ruleList, (Object) null, false, 6, (Object) null);
    }

    public AnalyzeRule(@NotNull RuleDataInterface ruleData, @Nullable BaseSource source, @Nullable DebugLog debugLog) {
        Intrinsics.checkNotNullParameter(ruleData, "ruleData");
        this.ruleData = ruleData;
        this.source = source;
        this.debugLog = debugLog;
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

    public /* synthetic */ AnalyzeRule(RuleDataInterface ruleDataInterface, BaseSource baseSource, DebugLog debugLog, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(ruleDataInterface, (i & 2) != 0 ? null : baseSource, (i & 4) != 0 ? null : debugLog);
    }

    @NotNull
    public final RuleDataInterface getRuleData() {
        return this.ruleData;
    }

    public final void setRuleData(@NotNull RuleDataInterface ruleDataInterface) {
        Intrinsics.checkNotNullParameter(ruleDataInterface, "<set-?>");
        this.ruleData = ruleDataInterface;
    }

    @Nullable
    public final DebugLog getDebugLog() {
        return this.debugLog;
    }

    public final void setDebugLog(@Nullable DebugLog debugLog) {
        this.debugLog = debugLog;
    }

    @Nullable
    public final BaseBook getBook() {
        RuleDataInterface ruleDataInterface = this.ruleData;
        if (ruleDataInterface instanceof BaseBook) {
            return (BaseBook) ruleDataInterface;
        }
        return null;
    }

    @Nullable
    public final BookChapter getChapter() {
        return this.chapter;
    }

    public final void setChapter(@Nullable BookChapter bookChapter) {
        this.chapter = bookChapter;
    }

    @Nullable
    public final String getNextChapterUrl() {
        return this.nextChapterUrl;
    }

    public final void setNextChapterUrl(@Nullable String str) {
        this.nextChapterUrl = str;
    }

    @Nullable
    public final Object getContent() {
        return this.content;
    }

    @Nullable
    public final String getBaseUrl() {
        return this.baseUrl;
    }

    @Nullable
    public final URL getRedirectUrl() {
        return this.redirectUrl;
    }

    @Override // io.legado.app.help.JsExtensions
    @NotNull
    public String getUserNameSpace() {
        return this.ruleData.getUserNameSpace();
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

    public static /* synthetic */ AnalyzeRule setContent$default(AnalyzeRule analyzeRule, Object obj, String str, int i, Object obj2) {
        if ((i & 2) != 0) {
            str = null;
        }
        return analyzeRule.setContent(obj, str);
    }

    @JvmOverloads
    @NotNull
    public final AnalyzeRule setContent(@Nullable Object content, @Nullable String baseUrl) {
        if (content == null) {
            throw new AssertionError("内容不可空（Content cannot be null）");
        }
        this.content = content;
        this.isJSON = StringExtensionsKt.isJson(content.toString());
        setBaseUrl(baseUrl);
        this.objectChangedXP = true;
        this.objectChangedJS = true;
        this.objectChangedJP = true;
        return this;
    }

    @NotNull
    public final AnalyzeRule setBaseUrl(@Nullable String baseUrl) {
        if (baseUrl != null) {
            this.baseUrl = baseUrl;
        }
        return this;
    }

    @Nullable
    public final URL setRedirectUrl(@NotNull String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        try {
            this.redirectUrl = new URL(url);
        } catch (Exception e) {
            log("URL(" + url + ") error\n" + ((Object) e.getLocalizedMessage()));
        }
        return this.redirectUrl;
    }

    private final AnalyzeByXPath getAnalyzeByXPath(Object o) {
        if (!Intrinsics.areEqual(o, this.content)) {
            return new AnalyzeByXPath(o);
        }
        if (this.analyzeByXPath == null || this.objectChangedXP) {
            Object obj = this.content;
            Intrinsics.checkNotNull(obj);
            this.analyzeByXPath = new AnalyzeByXPath(obj);
            this.objectChangedXP = false;
        }
        AnalyzeByXPath analyzeByXPath = this.analyzeByXPath;
        Intrinsics.checkNotNull(analyzeByXPath);
        return analyzeByXPath;
    }

    private final AnalyzeByJSoup getAnalyzeByJSoup(Object o) {
        if (!Intrinsics.areEqual(o, this.content)) {
            return new AnalyzeByJSoup(o);
        }
        if (this.analyzeByJSoup == null || this.objectChangedJS) {
            Object obj = this.content;
            Intrinsics.checkNotNull(obj);
            this.analyzeByJSoup = new AnalyzeByJSoup(obj);
            this.objectChangedJS = false;
        }
        AnalyzeByJSoup analyzeByJSoup = this.analyzeByJSoup;
        Intrinsics.checkNotNull(analyzeByJSoup);
        return analyzeByJSoup;
    }

    private final AnalyzeByJSonPath getAnalyzeByJSonPath(Object o) {
        if (!Intrinsics.areEqual(o, this.content)) {
            return new AnalyzeByJSonPath(o);
        }
        if (this.analyzeByJSonPath == null || this.objectChangedJP) {
            Object obj = this.content;
            Intrinsics.checkNotNull(obj);
            this.analyzeByJSonPath = new AnalyzeByJSonPath(obj);
            this.objectChangedJP = false;
        }
        AnalyzeByJSonPath analyzeByJSonPath = this.analyzeByJSonPath;
        Intrinsics.checkNotNull(analyzeByJSonPath);
        return analyzeByJSonPath;
    }

    public static /* synthetic */ List getStringList$default(AnalyzeRule analyzeRule, String str, Object obj, boolean z, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return analyzeRule.getStringList(str, obj, z);
    }

    @JvmOverloads
    @Nullable
    public final List<String> getStringList(@Nullable String rule, @Nullable Object mContent, boolean isUrl) {
        String str = rule;
        if (str == null || str.length() == 0) {
            return null;
        }
        List ruleList = splitSourceRule(rule, false);
        return getStringList((List<SourceRule>) ruleList, mContent, isUrl);
    }

    public static /* synthetic */ List getStringList$default(AnalyzeRule analyzeRule, List list, Object obj, boolean z, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return analyzeRule.getStringList((List<SourceRule>) list, obj, z);
    }

    @JvmOverloads
    @Nullable
    public final List<String> getStringList(@NotNull List<SourceRule> ruleList, @Nullable Object mContent, boolean isUrl) {
        Object rule$reader_pro;
        Intrinsics.checkNotNullParameter(ruleList, "ruleList");
        Object result = null;
        Object content = mContent == null ? this.content : mContent;
        if (content != null) {
            if (!ruleList.isEmpty()) {
                result = content;
                if (content instanceof NativeObject) {
                    Object obj = ((NativeObject) content).get(ruleList.get(0).getRule$reader_pro());
                    result = obj == null ? null : obj.toString();
                } else {
                    for (SourceRule sourceRule : ruleList) {
                        putRule(sourceRule.getPutMap$reader_pro());
                        sourceRule.makeUpRule(result);
                        Object it = result;
                        if (it != null) {
                            if (sourceRule.getRule$reader_pro().length() > 0) {
                                switch (WhenMappings.$EnumSwitchMapping$0[sourceRule.getMode$reader_pro().ordinal()]) {
                                    case 1:
                                        rule$reader_pro = evalJS(sourceRule.getRule$reader_pro(), result);
                                        break;
                                    case 2:
                                        rule$reader_pro = getAnalyzeByJSonPath(it).getStringList$reader_pro(sourceRule.getRule$reader_pro());
                                        break;
                                    case 3:
                                        rule$reader_pro = getAnalyzeByXPath(it).getStringList$reader_pro(sourceRule.getRule$reader_pro());
                                        break;
                                    case 4:
                                        rule$reader_pro = getAnalyzeByJSoup(it).getStringList$reader_pro(sourceRule.getRule$reader_pro());
                                        break;
                                    default:
                                        rule$reader_pro = sourceRule.getRule$reader_pro();
                                        break;
                                }
                                result = rule$reader_pro;
                            }
                            if ((sourceRule.getReplaceRegex$reader_pro().length() > 0) && (result instanceof List)) {
                                ArrayList newList = new ArrayList();
                                for (Object item : (List) result) {
                                    newList.add(replaceRegex(String.valueOf(item), sourceRule));
                                }
                                result = newList;
                            } else if (sourceRule.getReplaceRegex$reader_pro().length() > 0) {
                                result = replaceRegex(String.valueOf(result), sourceRule);
                            }
                        }
                    }
                }
            }
        }
        if (result == null) {
            return null;
        }
        if (result instanceof String) {
            result = StringsKt.split$default((CharSequence) result, new String[]{"\n"}, false, 0, 6, (Object) null);
        }
        if (isUrl) {
            ArrayList urlList = new ArrayList();
            if (result instanceof List) {
                for (Object url : (List) result) {
                    String absoluteURL = NetworkUtils.INSTANCE.getAbsoluteURL(this.redirectUrl, String.valueOf(url));
                    if ((absoluteURL.length() > 0) && !urlList.contains(absoluteURL)) {
                        urlList.add(absoluteURL);
                    }
                }
            }
            return urlList;
        }
        Object obj2 = result;
        if (obj2 instanceof List) {
            return (List) obj2;
        }
        return null;
    }

    public static /* synthetic */ String getString$default(AnalyzeRule analyzeRule, String str, Object obj, boolean z, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return analyzeRule.getString(str, obj, z);
    }

    @JvmOverloads
    @NotNull
    public final String getString(@Nullable String ruleStr, @Nullable Object mContent, boolean isUrl) {
        if (TextUtils.isEmpty(ruleStr)) {
            return "";
        }
        List ruleList = splitSourceRule$default(this, ruleStr, false, 2, null);
        return getString((List<SourceRule>) ruleList, mContent, isUrl);
    }

    public static /* synthetic */ String getString$default(AnalyzeRule analyzeRule, List list, Object obj, boolean z, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return analyzeRule.getString((List<SourceRule>) list, obj, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0192 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x007a A[SYNTHETIC] */
    @kotlin.jvm.JvmOverloads
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String getString(@org.jetbrains.annotations.NotNull java.util.List<io.legado.app.model.analyzeRule.AnalyzeRule.SourceRule> r5, @org.jetbrains.annotations.Nullable java.lang.Object r6, boolean r7) {
        /*
            Method dump skipped, instructions count: 692
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.analyzeRule.AnalyzeRule.getString(java.util.List, java.lang.Object, boolean):java.lang.String");
    }

    @Nullable
    public final Object getElement(@NotNull String ruleStr) {
        Object elements$reader_pro;
        Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
        if (TextUtils.isEmpty(ruleStr)) {
            return null;
        }
        Object result = null;
        Object content = this.content;
        List ruleList = splitSourceRule(ruleStr, true);
        if (content != null) {
            if (!ruleList.isEmpty()) {
                result = content;
                for (SourceRule sourceRule : ruleList) {
                    putRule(sourceRule.getPutMap$reader_pro());
                    sourceRule.makeUpRule(result);
                    Object it = result;
                    if (it != null) {
                        switch (WhenMappings.$EnumSwitchMapping$0[sourceRule.getMode$reader_pro().ordinal()]) {
                            case 1:
                                elements$reader_pro = evalJS(sourceRule.getRule$reader_pro(), it);
                                break;
                            case 2:
                                elements$reader_pro = getAnalyzeByJSonPath(it).getObject$reader_pro(sourceRule.getRule$reader_pro());
                                break;
                            case 3:
                                elements$reader_pro = getAnalyzeByXPath(it).getElements$reader_pro(sourceRule.getRule$reader_pro());
                                break;
                            case 4:
                            default:
                                elements$reader_pro = getAnalyzeByJSoup(it).getElements$reader_pro(sourceRule.getRule$reader_pro());
                                break;
                            case 5:
                                elements$reader_pro = AnalyzeByRegex.getElement$default(AnalyzeByRegex.INSTANCE, String.valueOf(result), StringExtensionsKt.splitNotBlank(sourceRule.getRule$reader_pro(), "&&"), 0, 4, null);
                                break;
                        }
                        result = elements$reader_pro;
                        if (sourceRule.getReplaceRegex$reader_pro().length() > 0) {
                            result = replaceRegex(String.valueOf(result), sourceRule);
                        }
                    }
                }
            }
        }
        return result;
    }

    @NotNull
    public final List<Object> getElements(@NotNull String ruleStr) {
        Object elements$reader_pro;
        Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
        Object result = null;
        Object content = this.content;
        List ruleList = splitSourceRule(ruleStr, true);
        if (content != null) {
            if (!ruleList.isEmpty()) {
                result = content;
                for (SourceRule sourceRule : ruleList) {
                    putRule(sourceRule.getPutMap$reader_pro());
                    Object it = result;
                    if (it != null) {
                        switch (WhenMappings.$EnumSwitchMapping$0[sourceRule.getMode$reader_pro().ordinal()]) {
                            case 1:
                                elements$reader_pro = evalJS(sourceRule.getRule$reader_pro(), result);
                                break;
                            case 2:
                                elements$reader_pro = getAnalyzeByJSonPath(it).getList$reader_pro(sourceRule.getRule$reader_pro());
                                break;
                            case 3:
                                elements$reader_pro = getAnalyzeByXPath(it).getElements$reader_pro(sourceRule.getRule$reader_pro());
                                break;
                            case 4:
                            default:
                                elements$reader_pro = getAnalyzeByJSoup(it).getElements$reader_pro(sourceRule.getRule$reader_pro());
                                break;
                            case 5:
                                elements$reader_pro = AnalyzeByRegex.getElements$default(AnalyzeByRegex.INSTANCE, String.valueOf(result), StringExtensionsKt.splitNotBlank(sourceRule.getRule$reader_pro(), "&&"), 0, 4, null);
                                break;
                        }
                        result = elements$reader_pro;
                        if (sourceRule.getReplaceRegex$reader_pro().length() > 0) {
                            result = replaceRegex(String.valueOf(result), sourceRule);
                        }
                    }
                }
            }
        }
        Object it2 = result;
        if (it2 != null) {
            return (List) it2;
        }
        return new ArrayList();
    }

    private final void putRule(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            put(key, getString$default(this, value, (Object) null, false, 6, (Object) null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String splitPutRule(String ruleStr, HashMap<String, String> putMap) {
        Object objM2105constructorimpl;
        String vRuleStr = ruleStr;
        Matcher putMatcher = putPattern.matcher(vRuleStr);
        while (putMatcher.find()) {
            String strGroup = putMatcher.group();
            Intrinsics.checkNotNullExpressionValue(strGroup, "putMatcher.group()");
            vRuleStr = StringsKt.replace$default(vRuleStr, strGroup, "", false, 4, (Object) null);
            Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
            String json$iv = putMatcher.group(1);
            try {
                Result.Companion companion = Result.Companion;
                Type type = new TypeToken<Map<String, ? extends String>>() { // from class: io.legado.app.model.analyzeRule.AnalyzeRule$splitPutRule$$inlined$fromJsonObject$1
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
            Map it = (Map) (Result.m2101isFailureimpl(obj) ? null : obj);
            if (it != null) {
                putMap.putAll(it);
            }
        }
        return vRuleStr;
    }

    private final String replaceRegex(String result, SourceRule rule) {
        Object objM2105constructorimpl;
        String str;
        Object objM2105constructorimpl2;
        String strReplaceFirst;
        if (rule.getReplaceRegex$reader_pro().length() == 0) {
            return result;
        }
        if (rule.getReplaceFirst$reader_pro()) {
            try {
                Result.Companion companion = Result.Companion;
                Pattern pattern = Pattern.compile(rule.getReplaceRegex$reader_pro());
                Matcher matcher = pattern.matcher(result);
                if (matcher.find()) {
                    String strGroup = matcher.group(0);
                    Intrinsics.checkNotNull(strGroup);
                    strReplaceFirst = new Regex(rule.getReplaceRegex$reader_pro()).replaceFirst(strGroup, rule.getReplacement$reader_pro());
                } else {
                    strReplaceFirst = "";
                }
                objM2105constructorimpl2 = Result.m2105constructorimpl(strReplaceFirst);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM2105constructorimpl2 = Result.m2105constructorimpl(ResultKt.createFailure(th));
            }
            Object obj = objM2105constructorimpl2;
            str = (String) (Result.m2103exceptionOrNullimpl(obj) == null ? obj : StringsKt.replaceFirst$default(result, rule.getReplaceRegex$reader_pro(), rule.getReplacement$reader_pro(), false, 4, (Object) null));
        } else {
            try {
                Result.Companion companion3 = Result.Companion;
                objM2105constructorimpl = Result.m2105constructorimpl(new Regex(rule.getReplaceRegex$reader_pro()).replace(result, rule.getReplacement$reader_pro()));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.Companion;
                objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th2));
            }
            Object obj2 = objM2105constructorimpl;
            str = (String) (Result.m2103exceptionOrNullimpl(obj2) == null ? obj2 : StringsKt.replace$default(result, rule.getReplaceRegex$reader_pro(), rule.getReplacement$reader_pro(), false, 4, (Object) null));
        }
        return str;
    }

    public static /* synthetic */ List splitSourceRule$default(AnalyzeRule analyzeRule, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return analyzeRule.splitSourceRule(str, z);
    }

    @NotNull
    public final List<SourceRule> splitSourceRule(@Nullable String ruleStr, boolean allInOne) {
        String str = ruleStr;
        if (str == null || str.length() == 0) {
            return CollectionsKt.emptyList();
        }
        ArrayList ruleList = new ArrayList();
        Mode mMode = Mode.Default;
        int start = 0;
        if (allInOne && StringsKt.startsWith$default(ruleStr, ":", false, 2, (Object) null)) {
            mMode = Mode.Regex;
            this.isRegex = true;
            start = 1;
        } else if (this.isRegex) {
            mMode = Mode.Regex;
        }
        Matcher jsMatcher = AppPattern.INSTANCE.getJS_PATTERN().matcher(ruleStr);
        while (jsMatcher.find()) {
            if (jsMatcher.start() > start) {
                int iStart = jsMatcher.start();
                if (ruleStr == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                CharSequence $this$trim$iv = ruleStr.substring(start, iStart);
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
                        }
                        endIndex$iv$iv--;
                    } else if (match$iv$iv) {
                        startIndex$iv$iv++;
                    } else {
                        startFound$iv$iv = true;
                    }
                }
                String tmp = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
                if (tmp.length() > 0) {
                    ruleList.add(new SourceRule(this, tmp, mMode));
                }
            }
            String strGroup = jsMatcher.group(2);
            String strGroup2 = strGroup == null ? jsMatcher.group(1) : strGroup;
            Intrinsics.checkNotNullExpressionValue(strGroup2, "jsMatcher.group(2) ?: jsMatcher.group(1)");
            ruleList.add(new SourceRule(this, strGroup2, Mode.Js));
            start = jsMatcher.end();
        }
        if (ruleStr.length() > start) {
            if (ruleStr == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            CharSequence $this$trim$iv2 = ruleStr.substring(start);
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
                    }
                    endIndex$iv$iv2--;
                } else if (match$iv$iv2) {
                    startIndex$iv$iv2++;
                } else {
                    startFound$iv$iv2 = true;
                }
            }
            String tmp2 = $this$trim$iv$iv2.subSequence(startIndex$iv$iv2, endIndex$iv$iv2 + 1).toString();
            if (tmp2.length() > 0) {
                ruleList.add(new SourceRule(this, tmp2, mMode));
            }
        }
        return ruleList;
    }

    /* compiled from: AnalyzeRule.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018��2\u00020\u0001B\u0019\b��\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010(\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001J\u0010\u0010,\u001a\u00020*2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n��R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR0\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0010j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0011X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\u001a\u0010\"\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b#\u0010\u001c\"\u0004\b$\u0010\u001eR\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030&X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\b0&X\u0082\u0004¢\u0006\u0002\n��¨\u0006-"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeRule$SourceRule;", "", "ruleStr", "", "mode", "Lio/legado/app/model/analyzeRule/AnalyzeRule$Mode;", "(Lio/legado/app/model/analyzeRule/AnalyzeRule;Ljava/lang/String;Lio/legado/app/model/analyzeRule/AnalyzeRule$Mode;)V", "defaultRuleType", "", "getRuleType", "jsRuleType", "getMode$reader_pro", "()Lio/legado/app/model/analyzeRule/AnalyzeRule$Mode;", "setMode$reader_pro", "(Lio/legado/app/model/analyzeRule/AnalyzeRule$Mode;)V", "putMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getPutMap$reader_pro", "()Ljava/util/HashMap;", "replaceFirst", "", "getReplaceFirst$reader_pro", "()Z", "setReplaceFirst$reader_pro", "(Z)V", "replaceRegex", "getReplaceRegex$reader_pro", "()Ljava/lang/String;", "setReplaceRegex$reader_pro", "(Ljava/lang/String;)V", "replacement", "getReplacement$reader_pro", "setReplacement$reader_pro", "rule", "getRule$reader_pro", "setRule$reader_pro", "ruleParam", "Ljava/util/ArrayList;", "ruleType", "isRule", "makeUpRule", "", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "splitRegex", "reader-pro"})
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeRule$SourceRule.class */
    public final class SourceRule {

        @NotNull
        private Mode mode;

        @NotNull
        private String rule;

        @NotNull
        private String replaceRegex;

        @NotNull
        private String replacement;
        private boolean replaceFirst;

        @NotNull
        private final HashMap<String, String> putMap;

        @NotNull
        private final ArrayList<String> ruleParam;

        @NotNull
        private final ArrayList<Integer> ruleType;
        private final int getRuleType;
        private final int jsRuleType;
        private final int defaultRuleType;

        public SourceRule(@NotNull AnalyzeRule this$0, @NotNull String ruleStr, Mode mode) {
            String strSubstring;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
            Intrinsics.checkNotNullParameter(mode, "mode");
            AnalyzeRule.this = this$0;
            this.mode = mode;
            this.replaceRegex = "";
            this.replacement = "";
            this.putMap = new HashMap<>();
            this.ruleParam = new ArrayList<>();
            this.ruleType = new ArrayList<>();
            this.getRuleType = -2;
            this.jsRuleType = -1;
            if (this.mode == Mode.Js || this.mode == Mode.Regex) {
                strSubstring = ruleStr;
            } else if (StringsKt.startsWith(ruleStr, "@CSS:", true)) {
                this.mode = Mode.Default;
                strSubstring = ruleStr;
            } else if (StringsKt.startsWith$default(ruleStr, "@@", false, 2, (Object) null)) {
                this.mode = Mode.Default;
                strSubstring = ruleStr.substring(2);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            } else if (StringsKt.startsWith(ruleStr, "@XPath:", true)) {
                this.mode = Mode.XPath;
                strSubstring = ruleStr.substring(7);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            } else if (!StringsKt.startsWith(ruleStr, "@Json:", true)) {
                if (AnalyzeRule.this.isJSON || StringsKt.startsWith$default(ruleStr, "$.", false, 2, (Object) null) || StringsKt.startsWith$default(ruleStr, "$[", false, 2, (Object) null)) {
                    this.mode = Mode.Json;
                    strSubstring = ruleStr;
                } else if (StringsKt.startsWith$default(ruleStr, "/", false, 2, (Object) null)) {
                    this.mode = Mode.XPath;
                    strSubstring = ruleStr;
                } else {
                    strSubstring = ruleStr;
                }
            } else {
                this.mode = Mode.Json;
                strSubstring = ruleStr.substring(6);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            }
            this.rule = strSubstring;
            this.rule = AnalyzeRule.this.splitPutRule(this.rule, this.putMap);
            int start = 0;
            Matcher evalMatcher = AnalyzeRule.evalPattern.matcher(this.rule);
            if (evalMatcher.find()) {
                String str = this.rule;
                int iStart = evalMatcher.start();
                if (str == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String tmp = str.substring(0, iStart);
                Intrinsics.checkNotNullExpressionValue(tmp, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                if (this.mode != Mode.Js && this.mode != Mode.Regex && (evalMatcher.start() == 0 || !StringsKt.contains$default((CharSequence) tmp, (CharSequence) "##", false, 2, (Object) null))) {
                    this.mode = Mode.Regex;
                }
                do {
                    if (evalMatcher.start() > start) {
                        String str2 = this.rule;
                        int iStart2 = evalMatcher.start();
                        if (str2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }
                        String tmp2 = str2.substring(start, iStart2);
                        Intrinsics.checkNotNullExpressionValue(tmp2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        splitRegex(tmp2);
                    }
                    String tmp3 = evalMatcher.group();
                    Intrinsics.checkNotNullExpressionValue(tmp3, "evalMatcher.group()");
                    if (StringsKt.startsWith(tmp3, "@get:", true)) {
                        this.ruleType.add(Integer.valueOf(this.getRuleType));
                        ArrayList<String> arrayList = this.ruleParam;
                        String strSubstring2 = tmp3.substring(6, StringsKt.getLastIndex(tmp3));
                        Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        arrayList.add(strSubstring2);
                    } else if (StringsKt.startsWith$default(tmp3, "{{", false, 2, (Object) null)) {
                        this.ruleType.add(Integer.valueOf(this.jsRuleType));
                        ArrayList<String> arrayList2 = this.ruleParam;
                        String strSubstring3 = tmp3.substring(2, tmp3.length() - 2);
                        Intrinsics.checkNotNullExpressionValue(strSubstring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        arrayList2.add(strSubstring3);
                    } else {
                        splitRegex(tmp3);
                    }
                    start = evalMatcher.end();
                } while (evalMatcher.find());
            }
            if (this.rule.length() <= start) {
                return;
            }
            String str3 = this.rule;
            if (str3 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String tmp4 = str3.substring(start);
            Intrinsics.checkNotNullExpressionValue(tmp4, "(this as java.lang.String).substring(startIndex)");
            splitRegex(tmp4);
        }

        public /* synthetic */ SourceRule(String str, Mode mode, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(AnalyzeRule.this, str, (i & 2) != 0 ? Mode.Default : mode);
        }

        @NotNull
        public final Mode getMode$reader_pro() {
            return this.mode;
        }

        public final void setMode$reader_pro(@NotNull Mode mode) {
            Intrinsics.checkNotNullParameter(mode, "<set-?>");
            this.mode = mode;
        }

        @NotNull
        public final String getRule$reader_pro() {
            return this.rule;
        }

        public final void setRule$reader_pro(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.rule = str;
        }

        @NotNull
        public final String getReplaceRegex$reader_pro() {
            return this.replaceRegex;
        }

        public final void setReplaceRegex$reader_pro(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.replaceRegex = str;
        }

        @NotNull
        public final String getReplacement$reader_pro() {
            return this.replacement;
        }

        public final void setReplacement$reader_pro(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.replacement = str;
        }

        public final boolean getReplaceFirst$reader_pro() {
            return this.replaceFirst;
        }

        public final void setReplaceFirst$reader_pro(boolean z) {
            this.replaceFirst = z;
        }

        @NotNull
        public final HashMap<String, String> getPutMap$reader_pro() {
            return this.putMap;
        }

        private final void splitRegex(String ruleStr) {
            int start = 0;
            List ruleStrArray = StringsKt.split$default((CharSequence) ruleStr, new String[]{"##"}, false, 0, 6, (Object) null);
            Matcher regexMatcher = AnalyzeRule.regexPattern.matcher((CharSequence) ruleStrArray.get(0));
            if (regexMatcher.find()) {
                if (this.mode != Mode.Js && this.mode != Mode.Regex) {
                    this.mode = Mode.Regex;
                }
                do {
                    if (regexMatcher.start() > start) {
                        int iStart = regexMatcher.start();
                        if (ruleStr == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }
                        String tmp = ruleStr.substring(start, iStart);
                        Intrinsics.checkNotNullExpressionValue(tmp, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        this.ruleType.add(Integer.valueOf(this.defaultRuleType));
                        this.ruleParam.add(tmp);
                    }
                    String tmp2 = regexMatcher.group();
                    Intrinsics.checkNotNullExpressionValue(tmp2, "regexMatcher.group()");
                    ArrayList<Integer> arrayList = this.ruleType;
                    String strSubstring = tmp2.substring(1);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
                    arrayList.add(Integer.valueOf(Integer.parseInt(strSubstring)));
                    this.ruleParam.add(tmp2);
                    start = regexMatcher.end();
                } while (regexMatcher.find());
            }
            if (ruleStr.length() > start) {
                if (ruleStr == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String tmp3 = ruleStr.substring(start);
                Intrinsics.checkNotNullExpressionValue(tmp3, "(this as java.lang.String).substring(startIndex)");
                this.ruleType.add(Integer.valueOf(this.defaultRuleType));
                this.ruleParam.add(tmp3);
            }
        }

        public final void makeUpRule(@Nullable Object result) {
            Unit unit;
            String it;
            StringBuilder infoVal = new StringBuilder();
            if (!this.ruleParam.isEmpty()) {
                int index = this.ruleParam.size();
                while (true) {
                    int i = index;
                    index = i - 1;
                    if (i <= 0) {
                        break;
                    }
                    Integer num = this.ruleType.get(index);
                    Intrinsics.checkNotNullExpressionValue(num, "ruleType[index]");
                    int regType = num.intValue();
                    if (regType > this.defaultRuleType) {
                        List $this$makeUpRule_u24lambda_u2d1 = result instanceof List ? (List) result : null;
                        if ($this$makeUpRule_u24lambda_u2d1 == null) {
                            unit = null;
                        } else {
                            if ($this$makeUpRule_u24lambda_u2d1.size() > regType && (it = (String) $this$makeUpRule_u24lambda_u2d1.get(regType)) != null) {
                                infoVal.insert(0, it);
                            }
                            unit = Unit.INSTANCE;
                        }
                        if (unit == null) {
                            infoVal.insert(0, this.ruleParam.get(index));
                        }
                    } else if (regType == this.jsRuleType) {
                        String str = this.ruleParam.get(index);
                        Intrinsics.checkNotNullExpressionValue(str, "ruleParam[index]");
                        if (isRule(str)) {
                            AnalyzeRule analyzeRule = AnalyzeRule.this;
                            AnalyzeRule analyzeRule2 = AnalyzeRule.this;
                            String str2 = this.ruleParam.get(index);
                            Intrinsics.checkNotNullExpressionValue(str2, "ruleParam[index]");
                            infoVal.insert(0, AnalyzeRule.getString$default(analyzeRule, (List) CollectionsKt.arrayListOf(analyzeRule2.new SourceRule(str2, null, 2, null)), (Object) null, false, 6, (Object) null));
                        } else {
                            AnalyzeRule analyzeRule3 = AnalyzeRule.this;
                            String str3 = this.ruleParam.get(index);
                            Intrinsics.checkNotNullExpressionValue(str3, "ruleParam[index]");
                            Object jsEval = analyzeRule3.evalJS(str3, result);
                            if (jsEval != null) {
                                if (jsEval instanceof String) {
                                    infoVal.insert(0, (String) jsEval);
                                } else {
                                    if (jsEval instanceof Double) {
                                        if (((Number) jsEval).doubleValue() % 1.0d == 0.0d) {
                                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                            Object[] objArr = {jsEval};
                                            String str4 = String.format("%.0f", Arrays.copyOf(objArr, objArr.length));
                                            Intrinsics.checkNotNullExpressionValue(str4, "java.lang.String.format(format, *args)");
                                            infoVal.insert(0, str4);
                                        }
                                    }
                                    infoVal.insert(0, jsEval.toString());
                                }
                            }
                        }
                    } else if (regType == this.getRuleType) {
                        AnalyzeRule analyzeRule4 = AnalyzeRule.this;
                        String str5 = this.ruleParam.get(index);
                        Intrinsics.checkNotNullExpressionValue(str5, "ruleParam[index]");
                        infoVal.insert(0, analyzeRule4.get(str5));
                    } else {
                        infoVal.insert(0, this.ruleParam.get(index));
                    }
                }
                String string = infoVal.toString();
                Intrinsics.checkNotNullExpressionValue(string, "infoVal.toString()");
                this.rule = string;
            }
            List ruleStrS = StringsKt.split$default((CharSequence) this.rule, new String[]{"##"}, false, 0, 6, (Object) null);
            String str6 = (String) ruleStrS.get(0);
            if (str6 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            this.rule = StringsKt.trim((CharSequence) str6).toString();
            if (ruleStrS.size() > 1) {
                this.replaceRegex = (String) ruleStrS.get(1);
            }
            if (ruleStrS.size() > 2) {
                this.replacement = (String) ruleStrS.get(2);
            }
            if (ruleStrS.size() > 3) {
                this.replaceFirst = true;
            }
        }

        private final boolean isRule(String ruleStr) {
            return StringsKt.startsWith$default((CharSequence) ruleStr, '@', false, 2, (Object) null) || StringsKt.startsWith$default(ruleStr, "$.", false, 2, (Object) null) || StringsKt.startsWith$default(ruleStr, "$[", false, 2, (Object) null) || StringsKt.startsWith$default(ruleStr, "//", false, 2, (Object) null);
        }
    }

    @NotNull
    public final String put(@NotNull String key, @NotNull String value) {
        Unit unit;
        Unit unit2;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        BookChapter bookChapter = this.chapter;
        if (bookChapter == null) {
            unit = null;
        } else {
            bookChapter.putVariable(key, value);
            unit = Unit.INSTANCE;
        }
        Unit unit3 = unit;
        if (unit3 != null) {
            unit2 = unit3;
        } else {
            BaseBook book = getBook();
            if (book == null) {
                unit2 = null;
            } else {
                book.putVariable(key, value);
                unit2 = Unit.INSTANCE;
            }
        }
        if (unit2 == null) {
            this.ruleData.putVariable(key, value);
        }
        return value;
    }

    @NotNull
    public final String get(@NotNull String key) {
        BookChapter it;
        String variable;
        Intrinsics.checkNotNullParameter(key, "key");
        if (Intrinsics.areEqual(key, "bookName")) {
            BaseBook it2 = getBook();
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
        BaseBook book = getBook();
        String variable3 = book == null ? null : book.getVariable(key);
        if (variable3 != null) {
            return variable3;
        }
        RuleDataInterface ruleDataInterface = this.ruleData;
        if (ruleDataInterface != null && (variable = ruleDataInterface.getVariable(key)) != null) {
            return variable;
        }
        return "";
    }

    public static /* synthetic */ Object evalJS$default(AnalyzeRule analyzeRule, String str, Object obj, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = null;
        }
        return analyzeRule.evalJS(str, obj);
    }

    @Nullable
    public final Object evalJS(@NotNull String jsStr, @Nullable Object result) {
        Intrinsics.checkNotNullParameter(jsStr, "jsStr");
        SimpleBindings bindings = new SimpleBindings();
        bindings.put((SimpleBindings) "java", (String) this);
        bindings.put((SimpleBindings) "cookie", (String) new CookieStore(getUserNameSpace()));
        bindings.put((SimpleBindings) "cache", (String) new CacheManager(getUserNameSpace()));
        bindings.put((SimpleBindings) PackageDocumentBase.DCTags.source, (String) this.source);
        bindings.put((SimpleBindings) "book", (String) getBook());
        bindings.put((SimpleBindings) CacheOperationExpressionEvaluator.RESULT_VARIABLE, (String) result);
        bindings.put((SimpleBindings) "baseUrl", this.baseUrl);
        bindings.put((SimpleBindings) NCXDocumentV2.NCXAttributeValues.chapter, (String) this.chapter);
        SimpleBindings simpleBindings = bindings;
        BookChapter bookChapter = this.chapter;
        simpleBindings.put((SimpleBindings) "title", bookChapter == null ? null : bookChapter.getTitle());
        bindings.put((SimpleBindings) NCXDocumentV2.NCXAttributes.src, (String) this.content);
        bindings.put((SimpleBindings) "nextChapterUrl", this.nextChapterUrl);
        return AppConst.INSTANCE.getSCRIPT_ENGINE().eval(jsStr, bindings);
    }

    /* compiled from: AnalyzeRule.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "AnalyzeRule.kt", l = {682}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.legado.app.model.analyzeRule.AnalyzeRule$ajax$1")
    /* renamed from: io.legado.app.model.analyzeRule.AnalyzeRule$ajax$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeRule$ajax$1.class */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        int label;
        final /* synthetic */ String $urlStr;
        final /* synthetic */ AnalyzeRule this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String $urlStr, AnalyzeRule this$0, Continuation<? super AnonymousClass1> $completion) {
            super(2, $completion);
            this.$urlStr = $urlStr;
            this.this$0 = this$0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return new AnonymousClass1(this.$urlStr, this.this$0, $completion);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super String> p2) {
            return ((AnonymousClass1) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object objM2105constructorimpl;
            Object strResponseAwait$default;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        String str = this.$urlStr;
                        AnalyzeRule analyzeRule = this.this$0;
                        Result.Companion companion = Result.Companion;
                        AnalyzeUrl analyzeUrl = new AnalyzeUrl(str, null, null, null, null, null, analyzeRule.source, analyzeRule.getBook(), null, null, analyzeRule.getDebugLog(), 830, null);
                        this.label = 1;
                        strResponseAwait$default = AnalyzeUrl.getStrResponseAwait$default(analyzeUrl, null, null, false, this, 7, null);
                        if (strResponseAwait$default == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        strResponseAwait$default = $result;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objM2105constructorimpl = Result.m2105constructorimpl(((StrResponse) strResponseAwait$default).getBody());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
            }
            Object obj = objM2105constructorimpl;
            AnalyzeRule analyzeRule2 = this.this$0;
            String str2 = this.$urlStr;
            Throwable it = Result.m2103exceptionOrNullimpl(obj);
            if (it != null) {
                analyzeRule2.log("ajax(" + str2 + ") error\n" + ExceptionsKt.stackTraceToString(it));
            }
            Throwable it2 = Result.m2103exceptionOrNullimpl(obj);
            return it2 == null ? obj : ThrowableExtensionsKt.getMsg(it2);
        }
    }

    @Override // io.legado.app.help.JsExtensions
    @Nullable
    public String ajax(@NotNull String urlStr) {
        Intrinsics.checkNotNullParameter(urlStr, "urlStr");
        return (String) BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(urlStr, this, null), 1, null);
    }

    @Nullable
    public final String toNumChapter(@Nullable String s) {
        if (s == null) {
            return null;
        }
        Matcher matcher = titleNumPattern.matcher(s);
        if (matcher.find()) {
            return new StringBuilder().append((Object) matcher.group(1)).append(StringUtils.INSTANCE.stringToInt(matcher.group(2))).append((Object) matcher.group(3)).toString();
        }
        return s;
    }

    public final void reGetBook() throws InterruptedException {
        BaseSource baseSource = this.source;
        BookSource bookSource = baseSource instanceof BookSource ? (BookSource) baseSource : null;
        BaseBook book = getBook();
        Book book2 = book instanceof Book ? (Book) book : null;
        if (bookSource == null || book2 == null) {
            return;
        }
        BuildersKt__BuildersKt.runBlocking$default(null, new C02061(bookSource, this, book2, null), 1, null);
    }

    /* compiled from: AnalyzeRule.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lio/legado/app/data/entities/Book;", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "AnalyzeRule.kt", l = {712}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.legado.app.model.analyzeRule.AnalyzeRule$reGetBook$1")
    /* renamed from: io.legado.app.model.analyzeRule.AnalyzeRule$reGetBook$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeRule$reGetBook$1.class */
    static final class C02061 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Book>, Object> {
        int label;
        final /* synthetic */ BookSource $bookSource;
        final /* synthetic */ AnalyzeRule this$0;
        final /* synthetic */ Book $book;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02061(BookSource $bookSource, AnalyzeRule this$0, Book $book, Continuation<? super C02061> $completion) {
            super(2, $completion);
            this.$bookSource = $bookSource;
            this.this$0 = this$0;
            this.$book = $book;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return new C02061(this.$bookSource, this.this$0, this.$book, $completion);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Book> p2) {
            return ((C02061) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: AnalyzeRule.kt */
        @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lio/legado/app/data/entities/Book;", "Lkotlinx/coroutines/CoroutineScope;"})
        @DebugMetadata(f = "AnalyzeRule.kt", l = {713, 720}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.legado.app.model.analyzeRule.AnalyzeRule$reGetBook$1$1")
        /* renamed from: io.legado.app.model.analyzeRule.AnalyzeRule$reGetBook$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeRule$reGetBook$1$1.class */
        static final class C00331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Book>, Object> {
            int label;
            final /* synthetic */ BookSource $bookSource;
            final /* synthetic */ AnalyzeRule this$0;
            final /* synthetic */ Book $book;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00331(BookSource $bookSource, AnalyzeRule this$0, Book $book, Continuation<? super C00331> $completion) {
                super(2, $completion);
                this.$bookSource = $bookSource;
                this.this$0 = this$0;
                this.$book = $book;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
                return new C00331(this.$bookSource, this.this$0, this.$book, $completion);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Book> p2) {
                return ((C00331) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
                Object objM2110unboximpl;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        objM2110unboximpl = new WebBook(this.$bookSource, false, (DebugLog) null, this.this$0.getUserNameSpace(), 6, (DefaultConstructorMarker) null).m1195preciseSearch0E7RQCE(this.$book.getName(), this.$book.getAuthor(), this);
                        if (objM2110unboximpl == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        objM2110unboximpl = ((Result) $result).m2110unboximpl();
                        break;
                    case 2:
                        ResultKt.throwOnFailure($result);
                        return $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Object obj = objM2110unboximpl;
                ResultKt.throwOnFailure(obj);
                Book book = this.$book;
                Book it = (Book) obj;
                book.setBookUrl(it.getBookUrl());
                Map $this$forEach$iv = it.getVariableMap();
                for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
                    book.putVariable(element$iv.getKey(), element$iv.getValue());
                }
                this.label = 2;
                Object bookInfo = new WebBook(this.$bookSource, false, (DebugLog) null, this.this$0.getUserNameSpace(), 6, (DefaultConstructorMarker) null).getBookInfo(this.$book, false, (Continuation<? super Book>) this);
                if (bookInfo == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return bookInfo;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object objWithTimeout = TimeoutKt.withTimeout(SessionHandler.DEFAULT_SESSION_TIMEOUT, new C00331(this.$bookSource, this.this$0, this.$book, null), this);
                    if (objWithTimeout == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return objWithTimeout;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: AnalyzeRule.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "AnalyzeRule.kt", l = {733}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.legado.app.model.analyzeRule.AnalyzeRule$refreshBookUrl$1")
    /* renamed from: io.legado.app.model.analyzeRule.AnalyzeRule$refreshBookUrl$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeRule$refreshBookUrl$1.class */
    static final class C02071 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        C02071(Continuation<? super C02071> $completion) {
            super(2, $completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return AnalyzeRule.this.new C02071($completion);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((C02071) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Book book;
            Object objSearchBook$default;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    BaseSource baseSource = AnalyzeRule.this.source;
                    BookSource bookSource = baseSource instanceof BookSource ? (BookSource) baseSource : null;
                    BaseBook book2 = AnalyzeRule.this.getBook();
                    book = book2 instanceof Book ? (Book) book2 : null;
                    if (bookSource == null || book == null) {
                        return Unit.INSTANCE;
                    }
                    this.L$0 = book;
                    this.label = 1;
                    objSearchBook$default = WebBook.searchBook$default(new WebBook(bookSource, false, (DebugLog) null, AnalyzeRule.this.getUserNameSpace(), 6, (DefaultConstructorMarker) null), book.getName(), null, this, 2, null);
                    if (objSearchBook$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    book = (Book) this.L$0;
                    ResultKt.throwOnFailure($result);
                    objSearchBook$default = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Iterable books = (List) objSearchBook$default;
            Iterable $this$forEach$iv = books;
            for (Object element$iv : $this$forEach$iv) {
                SearchBook it = (SearchBook) element$iv;
                if (Intrinsics.areEqual(it.getName(), book.getName()) && Intrinsics.areEqual(it.getAuthor(), book.getAuthor())) {
                    book.setBookUrl(it.getBookUrl());
                    if (!StringsKt.isBlank(it.getTocUrl())) {
                        book.setTocUrl(it.getTocUrl());
                    }
                    return Unit.INSTANCE;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final void refreshBookUrl() throws InterruptedException {
        BuildersKt__BuildersKt.runBlocking$default(null, new C02071(null), 1, null);
    }

    /* compiled from: AnalyzeRule.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "AnalyzeRule.kt", l = {754}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.legado.app.model.analyzeRule.AnalyzeRule$refreshTocUrl$1")
    /* renamed from: io.legado.app.model.analyzeRule.AnalyzeRule$refreshTocUrl$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeRule$refreshTocUrl$1.class */
    static final class C02081 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C02081(Continuation<? super C02081> $completion) {
            super(2, $completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return AnalyzeRule.this.new C02081($completion);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((C02081) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    BaseSource baseSource = AnalyzeRule.this.source;
                    BookSource bookSource = baseSource instanceof BookSource ? (BookSource) baseSource : null;
                    BaseBook book = AnalyzeRule.this.getBook();
                    Book book2 = book instanceof Book ? (Book) book : null;
                    if (bookSource == null || book2 == null) {
                        return Unit.INSTANCE;
                    }
                    this.label = 1;
                    if (WebBook.getBookInfo$default(new WebBook(bookSource, false, (DebugLog) null, AnalyzeRule.this.getUserNameSpace(), 6, (DefaultConstructorMarker) null), book2, false, (Continuation) this, 2, (Object) null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void refreshTocUrl() throws InterruptedException {
        BuildersKt__BuildersKt.runBlocking$default(null, new C02081(null), 1, null);
    }

    /* compiled from: AnalyzeRule.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\u0007\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\b\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n��¨\u0006\t"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeRule$Companion;", "", "()V", "evalPattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "putPattern", "regexPattern", "titleNumPattern", "reader-pro"})
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeRule$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }
}
