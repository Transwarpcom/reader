package io.legado.app.model.analyzeRule;

import com.google.gson.Gson;
import com.script.Bindings;
import com.script.SimpleBindings;
import io.legado.app.adapters.ReaderAdapterHelper;
import io.legado.app.adapters.ReaderAdapterInterface;
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
import io.legado.app.utils.Base64;
import io.legado.app.utils.EncoderUtils;
import io.legado.app.utils.GsonExtensionsKt;
import io.legado.app.utils.NetworkUtils;
import io.legado.app.utils.ParameterizedTypeImpl;
import io.legado.app.utils.StringExtensionsKt;
import io.legado.app.utils.StringUtils;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Request.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\u0018\u0000 l2\u00020\u0001:\u0003lmnB\u008f\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014J\u0010\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u0003H\u0002J\b\u0010C\u001a\u00020AH\u0002J\b\u0010D\u001a\u00020AH\u0002J\u001c\u0010E\u001a\u0004\u0018\u00010F2\u0006\u0010G\u001a\u00020\u00032\n\b\u0002\u0010H\u001a\u0004\u0018\u00010FJ\u0012\u0010I\u001a\u00020A2\b\u0010J\u001a\u0004\u0018\u00010KH\u0002J\n\u0010L\u001a\u0004\u0018\u00010KH\u0002J\u000e\u0010M\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003J\u0006\u0010N\u001a\u00020OJ\u0011\u0010P\u001a\u00020OH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010QJ\n\u0010R\u001a\u0004\u0018\u00010\u0013H\u0016J\u0006\u0010S\u001a\u00020TJ\u0011\u0010U\u001a\u00020TH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010QJ\n\u0010V\u001a\u0004\u0018\u00010\u000bH\u0016J*\u0010W\u001a\u00020X2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010>\u001a\u00020\"H\u0007J3\u0010Z\u001a\u00020X2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010>\u001a\u00020\"H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010[J\u0006\u0010\\\u001a\u00020\u0003J\b\u0010]\u001a\u00020\u0003H\u0016J\u0006\u0010^\u001a\u00020AJ\u0006\u0010_\u001a\u00020\"J\u0016\u0010`\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010a\u001a\u00020\u0003J\b\u0010b\u001a\u00020AH\u0002J\u000e\u0010c\u001a\u00020A2\u0006\u0010d\u001a\u00020TJ\u0012\u0010e\u001a\u00020A2\b\u0010f\u001a\u0004\u0018\u00010\u0003H\u0002J)\u0010g\u001a\u00020X2\u0006\u0010h\u001a\u00020\u00032\u0006\u0010i\u001a\u00020F2\u0006\u0010j\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010kR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u001a\u001a\u0004\u0018\u00010\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010#\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030$j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`%X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010&\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030'j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`(¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0016R\u000e\u0010-\u001a\u00020.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u00101\u001a\u0004\b/\u00100R\u0010\u00102\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00105\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0016R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u00101\u001a\u0004\b7\u00100R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0016R\"\u00109\u001a\u0004\u0018\u00010\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0016R\u001e\u0010;\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0016R\u000e\u0010=\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006o"},
   d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeUrl;", "Lio/legado/app/help/JsExtensions;", "mUrl", "", "key", "page", "", "speakText", "speakSpeed", "baseUrl", "source", "Lio/legado/app/data/entities/BaseSource;", "ruleData", "Lio/legado/app/model/analyzeRule/RuleDataInterface;", "chapter", "Lio/legado/app/data/entities/BookChapter;", "headerMapF", "", "debugLog", "Lio/legado/app/model/DebugLog;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lio/legado/app/data/entities/BaseSource;Lio/legado/app/model/analyzeRule/RuleDataInterface;Lio/legado/app/data/entities/BookChapter;Ljava/util/Map;Lio/legado/app/model/DebugLog;)V", "getBaseUrl", "()Ljava/lang/String;", "setBaseUrl", "(Ljava/lang/String;)V", "<set-?>", "body", "getBody", "charset", "getDebugLog", "()Lio/legado/app/model/DebugLog;", "setDebugLog", "(Lio/legado/app/model/DebugLog;)V", "enabledCookieJar", "", "fieldMap", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "headerMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getHeaderMap", "()Ljava/util/HashMap;", "getKey", "getMUrl", "method", "Lio/legado/app/help/http/RequestMethod;", "getPage", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "proxy", "queryStr", "retry", "ruleUrl", "getRuleUrl", "getSpeakSpeed", "getSpeakText", "type", "getType", "url", "getUrl", "urlNoQuery", "useWebView", "webJs", "analyzeFields", "", "fieldsTxt", "analyzeJs", "analyzeUrl", "evalJS", "", "jsStr", "result", "fetchEnd", "concurrentRecord", "Lio/legado/app/model/analyzeRule/AnalyzeUrl$ConcurrentRecord;", "fetchStart", "get", "getByteArray", "", "getByteArrayAwait", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLogger", "getResponse", "Lokhttp3/Response;", "getResponseAwait", "getSource", "getStrResponse", "Lio/legado/app/help/http/StrResponse;", "sourceRegex", "getStrResponseAwait", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserAgent", "getUserNameSpace", "initUrl", "isPost", "put", "value", "replaceKeyPageJs", "saveCookieJar", "response", "setCookie", "tag", "upload", "fileName", "file", "contentType", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "ConcurrentRecord", "UrlOption", "reader-pro"}
)
public final class AnalyzeUrl implements JsExtensions {
   @NotNull
   public static final AnalyzeUrl.Companion Companion = new AnalyzeUrl.Companion((DefaultConstructorMarker)null);
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
   private static final HashMap<String, AnalyzeUrl.ConcurrentRecord> concurrentRecordMap;

   public AnalyzeUrl(@NotNull String mUrl, @Nullable String key, @Nullable Integer page, @Nullable String speakText, @Nullable Integer speakSpeed, @NotNull String baseUrl, @Nullable BaseSource source, @Nullable RuleDataInterface ruleData, @Nullable BookChapter chapter, @Nullable Map<String, String> headerMapF, @Nullable DebugLog debugLog) {
      Intrinsics.checkNotNullParameter(mUrl, "mUrl");
      Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
      super();
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
      this.headerMap = new HashMap();
      this.urlNoQuery = "";
      this.fieldMap = new LinkedHashMap();
      this.method = RequestMethod.GET;
      BaseSource var12 = this.source;
      boolean var10001;
      if (var12 == null) {
         var10001 = false;
      } else {
         Boolean var13 = var12.getEnabledCookieJar();
         var10001 = var13 == null ? false : var13;
      }

      this.enabledCookieJar = var10001;
      if (!StringExtensionsKt.isDataUrl(this.mUrl)) {
         Matcher urlMatcher = paramPattern.matcher((CharSequence)this.baseUrl);
         boolean var16;
         if (urlMatcher.find()) {
            String var20 = this.baseUrl;
            byte var14 = 0;
            int var15 = urlMatcher.start();
            var16 = false;
            if (var20 == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            String var24 = var20.substring(var14, var15);
            Intrinsics.checkNotNullExpressionValue(var24, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            this.baseUrl = var24;
         }

         Map var10000;
         if (headerMapF == null) {
            BaseSource var22 = this.source;
            var10000 = (Map)(var22 == null ? null : var22.getHeaderMap(true));
         } else {
            var10000 = headerMapF;
         }

         Map var21 = var10000;
         if (var21 != null) {
            boolean var23 = false;
            var16 = false;
            int var18 = false;
            this.getHeaderMap().putAll(var21);
            if (var21.containsKey("proxy")) {
               this.proxy = (String)var21.get("proxy");
               this.getHeaderMap().remove("proxy");
            }
         }

         this.initUrl();
      }

   }

   // $FF: synthetic method
   public AnalyzeUrl(String var1, String var2, Integer var3, String var4, Integer var5, String var6, BaseSource var7, RuleDataInterface var8, BookChapter var9, Map var10, DebugLog var11, int var12, DefaultConstructorMarker var13) {
      if ((var12 & 2) != 0) {
         var2 = null;
      }

      if ((var12 & 4) != 0) {
         var3 = null;
      }

      if ((var12 & 8) != 0) {
         var4 = null;
      }

      if ((var12 & 16) != 0) {
         var5 = null;
      }

      if ((var12 & 32) != 0) {
         var6 = "";
      }

      if ((var12 & 64) != 0) {
         var7 = null;
      }

      if ((var12 & 128) != 0) {
         var8 = null;
      }

      if ((var12 & 256) != 0) {
         var9 = null;
      }

      if ((var12 & 512) != 0) {
         var10 = null;
      }

      if ((var12 & 1024) != 0) {
         var11 = null;
      }

      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
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

   public final void setBaseUrl(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.baseUrl = var1;
   }

   @Nullable
   public final DebugLog getDebugLog() {
      return this.debugLog;
   }

   public final void setDebugLog(@Nullable DebugLog <set-?>) {
      this.debugLog = var1;
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

   @NotNull
   public String getUserNameSpace() {
      RuleDataInterface var1 = this.ruleData;
      String var10000;
      if (var1 == null) {
         var10000 = "unknow";
      } else {
         String var2 = var1.getUserNameSpace();
         var10000 = var2 == null ? "unknow" : var2;
      }

      return var10000;
   }

   @Nullable
   public BaseSource getSource() {
      return this.source;
   }

   @Nullable
   public DebugLog getLogger() {
      return this.debugLog;
   }

   public final void initUrl() {
      this.ruleUrl = this.mUrl;
      this.analyzeJs();
      this.replaceKeyPageJs();
      this.analyzeUrl();
   }

   private final void analyzeJs() {
      int start = 0;
      String tmp = null;

      String $this$trim$iv;
      boolean $i$f$trim;
      int startIndex$iv$iv;
      int endIndex$iv$iv;
      boolean startFound$iv$iv;
      int index$iv$iv;
      char it;
      boolean var13;
      CharSequence var14;
      String var10000;
      boolean $i$f$trim;
      CharSequence $this$trim$iv$iv;
      boolean match$iv$iv;
      for(Matcher jsMatcher = AppPattern.INSTANCE.getJS_PATTERN().matcher((CharSequence)this.ruleUrl); jsMatcher.find(); start = jsMatcher.end()) {
         if (jsMatcher.start() > start) {
            $this$trim$iv = this.ruleUrl;
            int var5 = jsMatcher.start();
            boolean var6 = false;
            if ($this$trim$iv == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var10000 = $this$trim$iv.substring(start, var5);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            $this$trim$iv = var10000;
            $i$f$trim = false;
            $this$trim$iv$iv = (CharSequence)$this$trim$iv;
            $i$f$trim = false;
            startIndex$iv$iv = 0;
            endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
            startFound$iv$iv = false;

            while(startIndex$iv$iv <= endIndex$iv$iv) {
               index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
               it = $this$trim$iv$iv.charAt(index$iv$iv);
               var13 = false;
               match$iv$iv = Intrinsics.compare(it, 32) <= 0;
               if (!startFound$iv$iv) {
                  if (!match$iv$iv) {
                     startFound$iv$iv = true;
                  } else {
                     ++startIndex$iv$iv;
                  }
               } else {
                  if (!match$iv$iv) {
                     break;
                  }

                  --endIndex$iv$iv;
               }
            }

            tmp = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
            var14 = (CharSequence)tmp;
            $i$f$trim = false;
            if (var14.length() > 0) {
               this.ruleUrl = StringsKt.replace$default(tmp, "@result", this.ruleUrl, false, 4, (Object)null);
            }
         }

         String var19 = jsMatcher.group(2);
         String var17 = var19 == null ? jsMatcher.group(1) : var19;
         Intrinsics.checkNotNullExpressionValue(var17, "jsMatcher.group(2) ?: jsMatcher.group(1)");
         Object var15 = this.evalJS(var17, this.ruleUrl);
         if (var15 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
         }

         this.ruleUrl = (String)var15;
      }

      if (this.ruleUrl.length() > start) {
         $this$trim$iv = this.ruleUrl;
         $i$f$trim = false;
         if ($this$trim$iv == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
         }

         var10000 = $this$trim$iv.substring(start);
         Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
         $this$trim$iv = var10000;
         $i$f$trim = false;
         $this$trim$iv$iv = (CharSequence)$this$trim$iv;
         $i$f$trim = false;
         startIndex$iv$iv = 0;
         endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
         startFound$iv$iv = false;

         while(startIndex$iv$iv <= endIndex$iv$iv) {
            index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
            it = $this$trim$iv$iv.charAt(index$iv$iv);
            var13 = false;
            match$iv$iv = Intrinsics.compare(it, 32) <= 0;
            if (!startFound$iv$iv) {
               if (!match$iv$iv) {
                  startFound$iv$iv = true;
               } else {
                  ++startIndex$iv$iv;
               }
            } else {
               if (!match$iv$iv) {
                  break;
               }

               --endIndex$iv$iv;
            }
         }

         tmp = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
         var14 = (CharSequence)tmp;
         $i$f$trim = false;
         if (var14.length() > 0) {
            this.ruleUrl = StringsKt.replace$default(tmp, "@result", this.ruleUrl, false, 4, (Object)null);
         }
      }

   }

   private final void replaceKeyPageJs() {
      boolean var4;
      if (StringsKt.contains$default((CharSequence)this.ruleUrl, (CharSequence)"{{", false, 2, (Object)null) && StringsKt.contains$default((CharSequence)this.ruleUrl, (CharSequence)"}}", false, 2, (Object)null)) {
         RuleAnalyzer analyze = new RuleAnalyzer(this.ruleUrl, false, 2, (DefaultConstructorMarker)null);
         String url = analyze.innerRule("{{", "}}", (Function1)(new Function1<String, String>() {
            @Nullable
            public final String invoke(@NotNull String it) {
               Intrinsics.checkNotNullParameter(it, "it");
               Object var3 = AnalyzeUrl.evalJS$default(AnalyzeUrl.this, it, (Object)null, 2, (Object)null);
               Object jsEval = var3 == null ? "" : var3;
               String var10000;
               if (jsEval instanceof String) {
                  var10000 = (String)jsEval;
               } else if (jsEval instanceof Double && ((Number)jsEval).doubleValue() % 1.0D == 0.0D) {
                  StringCompanionObject var7 = StringCompanionObject.INSTANCE;
                  String var4 = "%.0f";
                  Object[] var5 = new Object[]{jsEval};
                  boolean var6 = false;
                  var10000 = String.format(var4, Arrays.copyOf(var5, var5.length));
                  Intrinsics.checkNotNullExpressionValue(var10000, "java.lang.String.format(format, *args)");
               } else {
                  var10000 = jsEval.toString();
               }

               return var10000;
            }
         }));
         CharSequence var3 = (CharSequence)url;
         var4 = false;
         if (var3.length() > 0) {
            this.ruleUrl = url;
         }
      }

      Integer var23 = this.page;
      if (var23 != null) {
         boolean var24 = false;
         var4 = false;
         int it = ((Number)var23).intValue();
         int var6 = false;

         String var10001;
         AnalyzeUrl var27;
         for(Matcher matcher = pagePattern.matcher((CharSequence)this.getRuleUrl()); matcher.find(); var27.ruleUrl = var10001) {
            String var10000 = matcher.group(1);
            Intrinsics.checkNotNull(var10000);
            CharSequence var26 = (CharSequence)var10000;
            String[] var8 = new String[]{","};
            List pages = StringsKt.split$default(var26, var8, false, 0, 6, (Object)null);
            String var10;
            String var11;
            boolean $i$f$trim;
            CharSequence $this$trim$iv$iv;
            boolean $i$f$trim;
            int startIndex$iv$iv;
            int endIndex$iv$iv;
            String var10002;
            boolean startFound$iv$iv;
            int index$iv$iv;
            char it;
            boolean var21;
            String var22;
            String $this$trim$iv;
            boolean match$iv$iv;
            if (this.getPage() < pages.size()) {
               var10001 = this.getRuleUrl();
               $this$trim$iv = matcher.group();
               Intrinsics.checkNotNullExpressionValue($this$trim$iv, "matcher.group()");
               var10002 = $this$trim$iv;
               $this$trim$iv = (String)pages.get(this.getPage() - 1);
               var10 = var10002;
               var11 = var10001;
               $i$f$trim = false;
               $this$trim$iv$iv = (CharSequence)$this$trim$iv;
               $i$f$trim = false;
               startIndex$iv$iv = 0;
               endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
               startFound$iv$iv = false;

               while(startIndex$iv$iv <= endIndex$iv$iv) {
                  index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
                  it = $this$trim$iv$iv.charAt(index$iv$iv);
                  var21 = false;
                  match$iv$iv = Intrinsics.compare(it, 32) <= 0;
                  if (!startFound$iv$iv) {
                     if (!match$iv$iv) {
                        startFound$iv$iv = true;
                     } else {
                        ++startIndex$iv$iv;
                     }
                  } else {
                     if (!match$iv$iv) {
                        break;
                     }

                     --endIndex$iv$iv;
                  }
               }

               var22 = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
               var27 = this;
               var10001 = StringsKt.replace$default(var11, var10, var22, false, 4, (Object)null);
            } else {
               var10001 = this.getRuleUrl();
               $this$trim$iv = matcher.group();
               Intrinsics.checkNotNullExpressionValue($this$trim$iv, "matcher.group()");
               var10002 = $this$trim$iv;
               $this$trim$iv = (String)CollectionsKt.last(pages);
               var10 = var10002;
               var11 = var10001;
               $i$f$trim = false;
               $this$trim$iv$iv = (CharSequence)$this$trim$iv;
               $i$f$trim = false;
               startIndex$iv$iv = 0;
               endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
               startFound$iv$iv = false;

               while(startIndex$iv$iv <= endIndex$iv$iv) {
                  index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
                  it = $this$trim$iv$iv.charAt(index$iv$iv);
                  var21 = false;
                  match$iv$iv = Intrinsics.compare(it, 32) <= 0;
                  if (!startFound$iv$iv) {
                     if (!match$iv$iv) {
                        startFound$iv$iv = true;
                     } else {
                        ++startIndex$iv$iv;
                     }
                  } else {
                     if (!match$iv$iv) {
                        break;
                     }

                     --endIndex$iv$iv;
                  }
               }

               var22 = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
               var27 = this;
               var10001 = StringsKt.replace$default(var11, var10, var22, false, 4, (Object)null);
            }
         }
      }

   }

   private final void analyzeUrl() {
      Matcher urlMatcher = paramPattern.matcher((CharSequence)this.ruleUrl);
      String var3;
      int pos;
      boolean $i$f$fromJsonObject;
      String var10000;
      if (urlMatcher.find()) {
         var3 = this.ruleUrl;
         byte var4 = 0;
         pos = urlMatcher.start();
         $i$f$fromJsonObject = false;
         if (var3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
         }

         var10000 = var3.substring(var4, pos);
         Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
      } else {
         var10000 = this.ruleUrl;
      }

      String urlNoOption = var10000;
      this.url = NetworkUtils.INSTANCE.getAbsoluteURL(this.baseUrl, urlNoOption);
      var3 = NetworkUtils.INSTANCE.getBaseUrl(this.url);
      boolean var8;
      boolean var27;
      if (var3 != null) {
         var27 = false;
         $i$f$fromJsonObject = false;
         var8 = false;
         this.setBaseUrl(var3);
      }

      boolean var7;
      boolean var12;
      boolean var13;
      String json$iv;
      Map var38;
      boolean $i$f$genericType;
      if (urlNoOption.length() != this.ruleUrl.length()) {
         Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
         json$iv = this.ruleUrl;
         int var29 = urlMatcher.end();
         var7 = false;
         if (json$iv == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
         }

         var10000 = json$iv.substring(var29);
         Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
         json$iv = var10000;
         $i$f$fromJsonObject = false;
         var7 = false;

         boolean $i$f$forEach;
         Object var32;
         try {
            kotlin.Result.Companion var34 = Result.Companion;
            int var9 = false;
            $i$f$genericType = false;
            Type var41 = (new AnalyzeUrl$analyzeUrl$$inlined$fromJsonObject$1()).getType();
            Intrinsics.checkNotNullExpressionValue(var41, "object : TypeToken<T>() {}.type");
            Object var46 = $this$fromJsonObject$iv.fromJson(json$iv, var41);
            if (!(var46 instanceof AnalyzeUrl.UrlOption)) {
               var46 = null;
            }

            AnalyzeUrl.UrlOption var36 = (AnalyzeUrl.UrlOption)var46;
            $i$f$genericType = false;
            var32 = Result.constructor-impl(var36);
         } catch (Throwable var22) {
            kotlin.Result.Companion var10 = Result.Companion;
            $i$f$forEach = false;
            var32 = Result.constructor-impl(ResultKt.createFailure(var22));
         }

         var27 = false;
         AnalyzeUrl.UrlOption var23 = (AnalyzeUrl.UrlOption)(Result.isFailure-impl(var32) ? null : var32);
         if (var23 != null) {
            var27 = false;
            $i$f$fromJsonObject = false;
            var8 = false;
            String var37 = var23.getMethod();
            boolean var14;
            if (var37 != null) {
               $i$f$forEach = false;
               var12 = false;
               var14 = false;
               if (StringsKt.equals(var37, "POST", true)) {
                  this.method = RequestMethod.POST;
               }
            }

            var38 = var23.getHeaderMap();
            boolean var21;
            if (var38 != null) {
               $i$f$forEach = false;
               var13 = false;
               Iterator var44 = var38.entrySet().iterator();

               while(var44.hasNext()) {
                  Entry element$iv = (Entry)var44.next();
                  int var17 = false;
                  Map var18 = (Map)this.getHeaderMap();
                  String var19 = String.valueOf(element$iv.getKey());
                  String var20 = String.valueOf(element$iv.getValue());
                  var21 = false;
                  var18.put(var19, var20);
               }
            }

            var37 = var23.getBody();
            if (var37 != null) {
               $i$f$forEach = false;
               var12 = false;
               var14 = false;
               this.body = var37;
            }

            this.type = var23.getType();
            this.charset = var23.getCharset();
            this.retry = var23.getRetry();
            this.useWebView = var23.useWebView();
            this.webJs = var23.getWebJs();
            var37 = var23.getJs();
            if (var37 != null) {
               $i$f$forEach = false;
               var12 = false;
               var14 = false;
               Object var45 = this.evalJS(var37, this.getUrl());
               if (var45 != null) {
                  String var16 = var45.toString();
                  if (var16 != null) {
                     boolean var47 = false;
                     boolean var48 = false;
                     var21 = false;
                     this.url = var16;
                  }
               }
            }
         }
      }

      var3 = (String)this.headerMap.get("User-Agent");
      if (var3 == null) {
         var27 = false;
         $i$f$fromJsonObject = false;
         AnalyzeUrl it = (AnalyzeUrl)this;
         var8 = false;
         var38 = (Map)this.getHeaderMap();
         String var40 = "User-Agent";
         String var42 = AppConst.INSTANCE.getUserAgent();
         var12 = false;
         var38.put(var40, var42);
      }

      this.urlNoQuery = this.url;
      RequestMethod var25 = this.method;
      int var26 = AnalyzeUrl.WhenMappings.$EnumSwitchMapping$0[var25.ordinal()];
      switch(var26) {
      case 1:
         pos = StringsKt.indexOf$default((CharSequence)this.url, '?', 0, false, 6, (Object)null);
         if (pos != -1) {
            String var31 = this.url;
            int var33 = pos + 1;
            var8 = false;
            if (var31 == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            String var10001 = var31.substring(var33);
            Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).substring(startIndex)");
            this.analyzeFields(var10001);
            var31 = this.url;
            byte var35 = 0;
            var8 = false;
            if (var31 == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var10001 = var31.substring(var35, pos);
            Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            this.urlNoQuery = var10001;
         }
         break;
      case 2:
         json$iv = this.body;
         if (json$iv != null) {
            var7 = false;
            var8 = false;
            $i$f$genericType = false;
            if (!StringExtensionsKt.isJson(json$iv) && !StringExtensionsKt.isXml(json$iv)) {
               CharSequence var43 = (CharSequence)this.getHeaderMap().get("Content-Type");
               var12 = false;
               var13 = false;
               if (var43 == null || var43.length() == 0) {
                  this.analyzeFields(json$iv);
               }
            }
         }
      }

   }

   private final void analyzeFields(String fieldsTxt) {
      this.queryStr = fieldsTxt;
      String[] var3 = new String[]{"&"};
      String[] queryS = StringExtensionsKt.splitNotBlank(fieldsTxt, var3);
      var3 = queryS;
      int var4 = 0;
      int var5 = queryS.length;

      while(var4 < var5) {
         String query = var3[var4];
         ++var4;
         String[] var8 = new String[]{"="};
         String[] queryM = StringExtensionsKt.splitNotBlank(query, var8);
         String value = queryM.length > 1 ? queryM[1] : "";
         CharSequence var9 = (CharSequence)this.charset;
         boolean var10 = false;
         boolean var11 = false;
         boolean var12;
         Map var14;
         String var15;
         String var16;
         if (var9 == null || var9.length() == 0) {
            if (NetworkUtils.INSTANCE.hasUrlEncoded(value)) {
               var14 = (Map)this.fieldMap;
               var15 = queryM[0];
               var11 = false;
               var14.put(var15, value);
            } else {
               var14 = (Map)this.fieldMap;
               var15 = queryM[0];
               var16 = URLEncoder.encode(value, "UTF-8");
               Intrinsics.checkNotNullExpressionValue(var16, "encode(value, \"UTF-8\")");
               var12 = false;
               var14.put(var15, var16);
            }
         } else if (Intrinsics.areEqual(this.charset, "escape")) {
            var14 = (Map)this.fieldMap;
            var15 = queryM[0];
            var16 = EncoderUtils.INSTANCE.escape(value);
            var12 = false;
            var14.put(var15, var16);
         } else {
            var14 = (Map)this.fieldMap;
            var15 = queryM[0];
            var16 = URLEncoder.encode(value, this.charset);
            Intrinsics.checkNotNullExpressionValue(var16, "encode(value, charset)");
            var12 = false;
            var14.put(var15, var16);
         }
      }

   }

   @Nullable
   public final Object evalJS(@NotNull String jsStr, @Nullable Object result) {
      Intrinsics.checkNotNullParameter(jsStr, "jsStr");
      SimpleBindings bindings = new SimpleBindings();
      Map var4 = (Map)bindings;
      String var5 = "java";
      boolean var6 = false;
      var4.put(var5, this);
      var4 = (Map)bindings;
      var5 = "baseUrl";
      String var8 = this.baseUrl;
      boolean var7 = false;
      var4.put(var5, var8);
      var4 = (Map)bindings;
      var5 = "cookie";
      CookieStore var9 = new CookieStore(this.getUserNameSpace());
      var7 = false;
      var4.put(var5, var9);
      var4 = (Map)bindings;
      var5 = "cache";
      CacheManager var10 = new CacheManager(this.getUserNameSpace());
      var7 = false;
      var4.put(var5, var10);
      var4 = (Map)bindings;
      var5 = "page";
      Integer var11 = this.page;
      var7 = false;
      var4.put(var5, var11);
      var4 = (Map)bindings;
      var5 = "key";
      var8 = this.key;
      var7 = false;
      var4.put(var5, var8);
      var4 = (Map)bindings;
      var5 = "speakText";
      var8 = this.speakText;
      var7 = false;
      var4.put(var5, var8);
      var4 = (Map)bindings;
      var5 = "speakSpeed";
      var11 = this.speakSpeed;
      var7 = false;
      var4.put(var5, var11);
      var4 = (Map)bindings;
      var5 = "book";
      RuleDataInterface var12 = this.ruleData;
      Book var13 = var12 instanceof Book ? (Book)var12 : null;
      var7 = false;
      var4.put(var5, var13);
      var4 = (Map)bindings;
      var5 = "source";
      BaseSource var14 = this.source;
      var7 = false;
      var4.put(var5, var14);
      var4 = (Map)bindings;
      var5 = "result";
      var6 = false;
      var4.put(var5, result);
      return AppConst.INSTANCE.getSCRIPT_ENGINE().eval(jsStr, (Bindings)bindings);
   }

   // $FF: synthetic method
   public static Object evalJS$default(AnalyzeUrl var0, String var1, Object var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
      }

      return var0.evalJS(var1, var2);
   }

   @NotNull
   public final String put(@NotNull String key, @NotNull String value) {
      Intrinsics.checkNotNullParameter(key, "key");
      Intrinsics.checkNotNullParameter(value, "value");
      BookChapter var4 = this.chapter;
      Unit var10000;
      if (var4 == null) {
         var10000 = null;
      } else {
         var4.putVariable(key, value);
         var10000 = Unit.INSTANCE;
      }

      Unit var3 = var10000;
      if (var3 == null) {
         RuleDataInterface var5 = this.ruleData;
         if (var5 != null) {
            var5.putVariable(key, value);
         }
      }

      return value;
   }

   @NotNull
   public final String get(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      boolean var5;
      boolean var6;
      boolean var8;
      BookChapter var9;
      if (Intrinsics.areEqual(key, "bookName")) {
         RuleDataInterface var4 = this.ruleData;
         Book var3 = var4 instanceof Book ? (Book)var4 : null;
         if (var3 != null) {
            var5 = false;
            var6 = false;
            var8 = false;
            return var3.getName();
         }
      } else if (Intrinsics.areEqual(key, "title")) {
         var9 = this.chapter;
         if (var9 != null) {
            var5 = false;
            var6 = false;
            var8 = false;
            return var9.getTitle();
         }
      }

      var9 = this.chapter;
      String var2 = var9 == null ? null : var9.getVariable(key);
      String var10000;
      if (var2 == null) {
         RuleDataInterface var10 = this.ruleData;
         if (var10 == null) {
            var10000 = "";
         } else {
            String var11 = var10.getVariable(key);
            var10000 = var11 == null ? "" : var11;
         }
      } else {
         var10000 = var2;
      }

      return var10000;
   }

   private final AnalyzeUrl.ConcurrentRecord fetchStart() {
      BaseSource var1 = this.source;
      if (var1 == null) {
         return null;
      } else {
         String concurrentRate = this.source.getConcurrentRate();
         CharSequence var2 = (CharSequence)concurrentRate;
         boolean var3 = false;
         boolean var4 = false;
         if (var2 == null || var2.length() == 0) {
            return null;
         } else {
            int rateIndex = StringsKt.indexOf$default((CharSequence)concurrentRate, "/", 0, false, 6, (Object)null);
            Object fetchRecord = null;
            fetchRecord = concurrentRecordMap.get(this.source.getKey());
            boolean var7;
            if (fetchRecord == null) {
               Object fetchRecord = new AnalyzeUrl.ConcurrentRecord(rateIndex > 0, System.currentTimeMillis(), 1);
               Map var28 = (Map)concurrentRecordMap;
               String var5 = this.source.getKey();
               var7 = false;
               var28.put(var5, fetchRecord);
               return fetchRecord;
            } else {
               boolean var6 = false;
               var7 = false;
               synchronized(fetchRecord){}

               int var29;
               try {
                  boolean var8 = false;

                  int var10000;
                  try {
                     boolean var12;
                     long var31;
                     if (rateIndex == -1) {
                        if (((AnalyzeUrl.ConcurrentRecord)fetchRecord).getFrequency() > 0) {
                           boolean var10 = false;
                           var10000 = Integer.parseInt(concurrentRate);
                        } else {
                           var31 = ((AnalyzeUrl.ConcurrentRecord)fetchRecord).getTime();
                           var12 = false;
                           long nextTime = var31 + (long)Integer.parseInt(concurrentRate);
                           if (System.currentTimeMillis() >= nextTime) {
                              ((AnalyzeUrl.ConcurrentRecord)fetchRecord).setTime(System.currentTimeMillis());
                              ((AnalyzeUrl.ConcurrentRecord)fetchRecord).setFrequency(1);
                              var10000 = 0;
                           } else {
                              var10000 = (int)(nextTime - System.currentTimeMillis());
                           }
                        }
                     } else {
                        int var11 = rateIndex + 1;
                        var12 = false;
                        if (concurrentRate == null) {
                           throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }

                        String var32 = concurrentRate.substring(var11);
                        Intrinsics.checkNotNullExpressionValue(var32, "(this as java.lang.String).substring(startIndex)");
                        String sj = var32;
                        var31 = ((AnalyzeUrl.ConcurrentRecord)fetchRecord).getTime();
                        boolean var15 = false;
                        long nextTime = var31 + (long)Integer.parseInt(sj);
                        if (System.currentTimeMillis() >= nextTime) {
                           ((AnalyzeUrl.ConcurrentRecord)fetchRecord).setTime(System.currentTimeMillis());
                           ((AnalyzeUrl.ConcurrentRecord)fetchRecord).setFrequency(1);
                           var10000 = 0;
                        } else {
                           byte var18 = 0;
                           boolean var19 = false;
                           if (concurrentRate == null) {
                              throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                           }

                           var32 = concurrentRate.substring(var18, rateIndex);
                           Intrinsics.checkNotNullExpressionValue(var32, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                           String cs = var32;
                           var10000 = ((AnalyzeUrl.ConcurrentRecord)fetchRecord).getFrequency();
                           boolean var33 = false;
                           if (var10000 > Integer.parseInt(cs)) {
                              var10000 = (int)(nextTime - System.currentTimeMillis());
                           } else {
                              ((AnalyzeUrl.ConcurrentRecord)fetchRecord).setFrequency(((AnalyzeUrl.ConcurrentRecord)fetchRecord).getFrequency() + 1);
                              var10000 = 0;
                           }
                        }
                     }
                  } catch (Exception var22) {
                     var10000 = 0;
                  }

                  var29 = var10000;
               } finally {
                  ;
               }

               if (var29 > 0) {
                  throw new ConcurrentException("根据并发率还需等待" + var29 + "毫秒才可以访问", var29);
               } else {
                  return (AnalyzeUrl.ConcurrentRecord)fetchRecord;
               }
            }
         }
      }
   }

   private final void fetchEnd(AnalyzeUrl.ConcurrentRecord concurrentRecord) {
      if (concurrentRecord != null && !concurrentRecord.getConcurrent()) {
         boolean var2 = false;
         boolean var3 = false;
         synchronized(concurrentRecord) {
            int var4 = false;
            concurrentRecord.setFrequency(concurrentRecord.getFrequency() - 1);
            Unit var6 = Unit.INSTANCE;
         }
      }

   }

   @Nullable
   public final Object getStrResponseAwait(@Nullable String jsStr, @Nullable String sourceRegex, boolean useWebView, @NotNull Continuation<? super StrResponse> $completion) {
      Object $continuation;
      label97: {
         if (var4 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var4;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label97;
            }
         }

         $continuation = new ContinuationImpl(var4) {
            Object L$0;
            Object L$1;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return AnalyzeUrl.this.getStrResponseAwait((String)null, (String)null, false, (Continuation)this);
            }
         };
      }

      AnalyzeUrl.ConcurrentRecord concurrentRecord;
      StrResponse strResponse;
      label116: {
         Object var10000;
         String var18;
         StringUtils var19;
         label90: {
            StrResponse var31;
            label117: {
               label85: {
                  label84: {
                     Object $result = ((<undefinedtype>)$continuation).result;
                     Object var25 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     switch(((<undefinedtype>)$continuation).label) {
                     case 0:
                        ResultKt.throwOnFailure($result);
                        if (this.getType() != null) {
                           String var33 = this.getUrl();
                           var19 = StringUtils.INSTANCE;
                           var18 = var33;
                           ((<undefinedtype>)$continuation).L$0 = var18;
                           ((<undefinedtype>)$continuation).L$1 = var19;
                           ((<undefinedtype>)$continuation).label = 1;
                           var10000 = this.getByteArrayAwait((Continuation)$continuation);
                           if (var10000 == var25) {
                              return var25;
                           }
                           break label90;
                        }

                        concurrentRecord = this.fetchStart();
                        BaseSource var6 = this.source;
                        this.setCookie(var6 == null ? null : var6.getKey());
                        var6 = null;
                        if (!this.useWebView || !useWebView) {
                           OkHttpClient var32 = HttpHelperKt.getProxyClient(this.proxy, this.getDebugLog());
                           int var10001 = this.retry;
                           Function1 var10002 = (Function1)(new Function1<Builder, Unit>() {
                              public final void invoke(@NotNull Builder $this$newCallStrResponse) {
                                 Intrinsics.checkNotNullParameter($this$newCallStrResponse, "$this$newCallStrResponse");
                                 OkHttpUtilsKt.addHeaders($this$newCallStrResponse, (Map)AnalyzeUrl.this.getHeaderMap());
                                 RequestMethod var2 = AnalyzeUrl.this.method;
                                 int var3 = null.WhenMappings.$EnumSwitchMapping$0[var2.ordinal()];
                                 if (var3 == 1) {
                                    $this$newCallStrResponse.url(AnalyzeUrl.this.urlNoQuery);
                                    String contentType = (String)AnalyzeUrl.this.getHeaderMap().get("Content-Type");
                                    String body = AnalyzeUrl.this.getBody();
                                    Map var6 = (Map)AnalyzeUrl.this.fieldMap;
                                    boolean var7 = false;
                                    if (var6.isEmpty()) {
                                       CharSequence var9 = (CharSequence)body;
                                       var7 = false;
                                       boolean var8 = false;
                                       if (var9 != null && !StringsKt.isBlank(var9)) {
                                          var9 = (CharSequence)contentType;
                                          var7 = false;
                                          var8 = false;
                                          if (var9 != null && !StringsKt.isBlank(var9)) {
                                             RequestBody requestBody = RequestBody.Companion.create(body, MediaType.Companion.get(contentType));
                                             $this$newCallStrResponse.post(requestBody);
                                          } else {
                                             OkHttpUtilsKt.postJson($this$newCallStrResponse, body);
                                          }

                                          return;
                                       }
                                    }

                                    OkHttpUtilsKt.postForm($this$newCallStrResponse, (Map)AnalyzeUrl.this.fieldMap, true);
                                 } else {
                                    OkHttpUtilsKt.get($this$newCallStrResponse, AnalyzeUrl.this.urlNoQuery, (Map)AnalyzeUrl.this.fieldMap, true);
                                 }

                              }

                              // $FF: synthetic class
                              @Metadata(
                                 mv = {1, 5, 1},
                                 k = 3,
                                 xi = 48
                              )
                              public class WhenMappings {
                                 // $FF: synthetic field
                                 public static final int[] $EnumSwitchMapping$0;

                                 static {
                                    int[] var0 = new int[RequestMethod.values().length];
                                    var0[RequestMethod.POST.ordinal()] = 1;
                                    $EnumSwitchMapping$0 = var0;
                                 }
                              }
                           });
                           ((<undefinedtype>)$continuation).L$0 = this;
                           ((<undefinedtype>)$continuation).L$1 = concurrentRecord;
                           ((<undefinedtype>)$continuation).label = 4;
                           var10000 = OkHttpUtilsKt.newCallStrResponse(var32, var10001, var10002, (Continuation)$continuation);
                           if (var10000 == var25) {
                              return var25;
                           }
                           break label85;
                        }

                        RequestMethod var7 = this.method;
                        int var8 = AnalyzeUrl.WhenMappings.$EnumSwitchMapping$0[var7.ordinal()];
                        String var9;
                        String var10;
                        BaseSource var11;
                        String var12;
                        String var13;
                        Map var10005;
                        String var27;
                        HashMap var28;
                        ReaderAdapterInterface var30;
                        if (var8 != 2) {
                           var9 = this.getUrl();
                           var11 = this.source;
                           var10 = var11 == null ? null : var11.getKey();
                           var12 = this.webJs;
                           var27 = var12 == null ? jsStr : var12;
                           var28 = this.getHeaderMap();
                           var13 = this.getUserNameSpace();
                           DebugLog var29 = this.getDebugLog();
                           var30 = ReaderAdapterHelper.INSTANCE.getAdapter();
                           var10005 = (Map)var28;
                           ((<undefinedtype>)$continuation).L$0 = this;
                           ((<undefinedtype>)$continuation).L$1 = concurrentRecord;
                           ((<undefinedtype>)$continuation).label = 3;
                           var10000 = ReaderAdapterInterface.DefaultImpls.getStrResponseByRemoteWebview$default(var30, var9, (String)null, (String)null, var10, var10005, sourceRegex, var27, (String)null, false, (String)null, var13, var29, (Continuation)$continuation, 902, (Object)null);
                           if (var10000 == var25) {
                              return var25;
                           }
                           break label84;
                        }

                        var9 = this.urlNoQuery;
                        var11 = this.source;
                        var10 = var11 == null ? null : var11.getKey();
                        var12 = this.webJs;
                        var27 = var12 == null ? jsStr : var12;
                        var28 = this.getHeaderMap();
                        var13 = this.getBody();
                        String var14 = this.getUserNameSpace();
                        DebugLog var15 = this.getDebugLog();
                        var30 = ReaderAdapterHelper.INSTANCE.getAdapter();
                        var10005 = (Map)var28;
                        ((<undefinedtype>)$continuation).L$0 = this;
                        ((<undefinedtype>)$continuation).L$1 = concurrentRecord;
                        ((<undefinedtype>)$continuation).label = 2;
                        var10000 = ReaderAdapterInterface.DefaultImpls.getStrResponseByRemoteWebview$default(var30, var9, (String)null, (String)null, var10, var10005, sourceRegex, var27, (String)null, true, var13, var14, var15, (Continuation)$continuation, 134, (Object)null);
                        if (var10000 == var25) {
                           return var25;
                        }
                        break;
                     case 1:
                        var19 = (StringUtils)((<undefinedtype>)$continuation).L$1;
                        var18 = (String)((<undefinedtype>)$continuation).L$0;
                        ResultKt.throwOnFailure($result);
                        var10000 = $result;
                        break label90;
                     case 2:
                        concurrentRecord = (AnalyzeUrl.ConcurrentRecord)((<undefinedtype>)$continuation).L$1;
                        this = (AnalyzeUrl)((<undefinedtype>)$continuation).L$0;
                        ResultKt.throwOnFailure($result);
                        var10000 = $result;
                        break;
                     case 3:
                        concurrentRecord = (AnalyzeUrl.ConcurrentRecord)((<undefinedtype>)$continuation).L$1;
                        this = (AnalyzeUrl)((<undefinedtype>)$continuation).L$0;
                        ResultKt.throwOnFailure($result);
                        var10000 = $result;
                        break label84;
                     case 4:
                        concurrentRecord = (AnalyzeUrl.ConcurrentRecord)((<undefinedtype>)$continuation).L$1;
                        this = (AnalyzeUrl)((<undefinedtype>)$continuation).L$0;
                        ResultKt.throwOnFailure($result);
                        var10000 = $result;
                        break label85;
                     default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     var31 = (StrResponse)var10000;
                     break label117;
                  }

                  var31 = (StrResponse)var10000;
                  break label117;
               }

               strResponse = (StrResponse)var10000;
               this.saveCookieJar(strResponse.getRaw());
               break label116;
            }

            strResponse = var31;
            break label116;
         }

         Object var20 = var10000;
         String var23 = var19.byteToHexString((byte[])var20);
         return new StrResponse(var18, var23);
      }

      this.fetchEnd(concurrentRecord);
      return strResponse;
   }

   // $FF: synthetic method
   public static Object getStrResponseAwait$default(AnalyzeUrl var0, String var1, String var2, boolean var3, Continuation var4, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = null;
      }

      if ((var5 & 2) != 0) {
         var2 = null;
      }

      if ((var5 & 4) != 0) {
         var3 = true;
      }

      return var0.getStrResponseAwait(var1, var2, var3, var4);
   }

   public final void saveCookieJar(@NotNull Response response) {
      Intrinsics.checkNotNullParameter(response, "response");
      List cookieList = response.headers("Set-Cookie");
      if (cookieList.size() > 0) {
         CookieStore cookieStore = new CookieStore(this.getUserNameSpace());
         String domain = NetworkUtils.INSTANCE.getSubDomain(this.url);
         Iterable $this$forEach$iv = (Iterable)cookieList;
         int $i$f$forEach = false;
         Iterator var7 = $this$forEach$iv.iterator();

         while(var7.hasNext()) {
            Object element$iv = var7.next();
            String it = (String)element$iv;
            int var10 = false;
            cookieStore.replaceCookie(Intrinsics.stringPlus(domain, "_cookieJar"), it);
         }
      }

   }

   @JvmOverloads
   @NotNull
   public final StrResponse getStrResponse(@Nullable String jsStr, @Nullable String sourceRegex, boolean useWebView) {
      return (StrResponse)BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2<CoroutineScope, Continuation<? super StrResponse>, Object>((Continuation)null) {
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            Object var2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            Object var10000;
            switch(this.label) {
            case 0:
               ResultKt.throwOnFailure($result);
               AnalyzeUrl var3 = AnalyzeUrl.this;
               String var10001 = jsStr;
               String var10002 = sourceRegex;
               boolean var10003 = useWebView;
               Continuation var10004 = (Continuation)this;
               this.label = 1;
               var10000 = var3.getStrResponseAwait(var10001, var10002, var10003, var10004);
               if (var10000 == var2) {
                  return var2;
               }
               break;
            case 1:
               ResultKt.throwOnFailure($result);
               var10000 = $result;
               break;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            return var10000;
         }

         @NotNull
         public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return (Continuation)(new <anonymous constructor>($completion));
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super StrResponse> p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }), 1, (Object)null);
   }

   // $FF: synthetic method
   public static StrResponse getStrResponse$default(AnalyzeUrl var0, String var1, String var2, boolean var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = null;
      }

      if ((var4 & 2) != 0) {
         var2 = null;
      }

      if ((var4 & 4) != 0) {
         var3 = true;
      }

      return var0.getStrResponse(var1, var2, var3);
   }

   @Nullable
   public final Object getResponseAwait(@NotNull Continuation<? super Response> $completion) {
      Object $continuation;
      label25: {
         if (var1 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var1;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label25;
            }
         }

         $continuation = new ContinuationImpl(var1) {
            Object L$0;
            Object L$1;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return AnalyzeUrl.this.getResponseAwait((Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      AnalyzeUrl.ConcurrentRecord concurrentRecord;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         concurrentRecord = this.fetchStart();
         BaseSource var3 = this.source;
         this.setCookie(var3 == null ? null : var3.getKey());
         OkHttpClient var8 = HttpHelperKt.getProxyClient$default(this.proxy, (DebugLog)null, 2, (Object)null);
         int var10001 = this.retry;
         Function1 var10002 = (Function1)(new Function1<Builder, Unit>() {
            public final void invoke(@NotNull Builder $this$newCallResponse) {
               Intrinsics.checkNotNullParameter($this$newCallResponse, "$this$newCallResponse");
               OkHttpUtilsKt.addHeaders($this$newCallResponse, (Map)AnalyzeUrl.this.getHeaderMap());
               RequestMethod var2 = AnalyzeUrl.this.method;
               int var3 = null.WhenMappings.$EnumSwitchMapping$0[var2.ordinal()];
               if (var3 == 1) {
                  $this$newCallResponse.url(AnalyzeUrl.this.urlNoQuery);
                  String contentType = (String)AnalyzeUrl.this.getHeaderMap().get("Content-Type");
                  String body = AnalyzeUrl.this.getBody();
                  Map var6 = (Map)AnalyzeUrl.this.fieldMap;
                  boolean var7 = false;
                  if (var6.isEmpty()) {
                     CharSequence var9 = (CharSequence)body;
                     var7 = false;
                     boolean var8 = false;
                     if (var9 != null && !StringsKt.isBlank(var9)) {
                        var9 = (CharSequence)contentType;
                        var7 = false;
                        var8 = false;
                        if (var9 != null && !StringsKt.isBlank(var9)) {
                           RequestBody requestBody = RequestBody.Companion.create(body, MediaType.Companion.get(contentType));
                           $this$newCallResponse.post(requestBody);
                        } else {
                           OkHttpUtilsKt.postJson($this$newCallResponse, body);
                        }

                        return;
                     }
                  }

                  OkHttpUtilsKt.postForm($this$newCallResponse, (Map)AnalyzeUrl.this.fieldMap, true);
               } else {
                  OkHttpUtilsKt.get($this$newCallResponse, AnalyzeUrl.this.urlNoQuery, (Map)AnalyzeUrl.this.fieldMap, true);
               }

            }

            // $FF: synthetic class
            @Metadata(
               mv = {1, 5, 1},
               k = 3,
               xi = 48
            )
            public class WhenMappings {
               // $FF: synthetic field
               public static final int[] $EnumSwitchMapping$0;

               static {
                  int[] var0 = new int[RequestMethod.values().length];
                  var0[RequestMethod.POST.ordinal()] = 1;
                  $EnumSwitchMapping$0 = var0;
               }
            }
         });
         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = concurrentRecord;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = OkHttpUtilsKt.newCallResponse(var8, var10001, var10002, (Continuation)$continuation);
         if (var10000 == var6) {
            return var6;
         }
         break;
      case 1:
         concurrentRecord = (AnalyzeUrl.ConcurrentRecord)((<undefinedtype>)$continuation).L$1;
         this = (AnalyzeUrl)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      Response response = (Response)var10000;
      this.fetchEnd(concurrentRecord);
      return response;
   }

   @NotNull
   public final Response getResponse() {
      return (Response)BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2<CoroutineScope, Continuation<? super Response>, Object>((Continuation)null) {
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            Object var2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            Object var10000;
            switch(this.label) {
            case 0:
               ResultKt.throwOnFailure($result);
               AnalyzeUrl var3 = AnalyzeUrl.this;
               Continuation var10001 = (Continuation)this;
               this.label = 1;
               var10000 = var3.getResponseAwait(var10001);
               if (var10000 == var2) {
                  return var2;
               }
               break;
            case 1:
               ResultKt.throwOnFailure($result);
               var10000 = $result;
               break;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            return var10000;
         }

         @NotNull
         public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return (Continuation)(new <anonymous constructor>($completion));
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Response> p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }), 1, (Object)null);
   }

   @Nullable
   public final Object getByteArrayAwait(@NotNull Continuation<? super byte[]> $completion) {
      Object $continuation;
      label29: {
         if (var1 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var1;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label29;
            }
         }

         $continuation = new ContinuationImpl(var1) {
            Object L$0;
            Object L$1;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return AnalyzeUrl.this.getByteArrayAwait((Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var8 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      AnalyzeUrl.ConcurrentRecord concurrentRecord;
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         concurrentRecord = this.fetchStart();
         MatchResult dataUriFindResult = Regex.find$default(AppPattern.INSTANCE.getDataUriRegex(), (CharSequence)this.urlNoQuery, 0, 2, (Object)null);
         if (dataUriFindResult != null) {
            String dataUriBase64 = (String)dataUriFindResult.getGroupValues().get(1);
            byte[] byteArray = Base64.decode((String)dataUriBase64, 0);
            this.fetchEnd(concurrentRecord);
            Intrinsics.checkNotNullExpressionValue(byteArray, "byteArray");
            return byteArray;
         }

         BaseSource var4 = this.source;
         this.setCookie(var4 == null ? null : var4.getKey());
         OkHttpClient var11 = HttpHelperKt.getProxyClient$default(this.proxy, (DebugLog)null, 2, (Object)null);
         int var10001 = this.retry;
         Function1 var10002 = (Function1)(new Function1<Builder, Unit>() {
            public final void invoke(@NotNull Builder $this$newCallResponseBody) {
               Intrinsics.checkNotNullParameter($this$newCallResponseBody, "$this$newCallResponseBody");
               OkHttpUtilsKt.addHeaders($this$newCallResponseBody, (Map)AnalyzeUrl.this.getHeaderMap());
               RequestMethod var2 = AnalyzeUrl.this.method;
               int var3 = null.WhenMappings.$EnumSwitchMapping$0[var2.ordinal()];
               if (var3 == 1) {
                  $this$newCallResponseBody.url(AnalyzeUrl.this.urlNoQuery);
                  String contentType = (String)AnalyzeUrl.this.getHeaderMap().get("Content-Type");
                  String body = AnalyzeUrl.this.getBody();
                  Map var6 = (Map)AnalyzeUrl.this.fieldMap;
                  boolean var7 = false;
                  if (var6.isEmpty()) {
                     CharSequence var9 = (CharSequence)body;
                     var7 = false;
                     boolean var8 = false;
                     if (var9 != null && !StringsKt.isBlank(var9)) {
                        var9 = (CharSequence)contentType;
                        var7 = false;
                        var8 = false;
                        if (var9 != null && !StringsKt.isBlank(var9)) {
                           RequestBody requestBody = RequestBody.Companion.create(body, MediaType.Companion.get(contentType));
                           $this$newCallResponseBody.post(requestBody);
                        } else {
                           OkHttpUtilsKt.postJson($this$newCallResponseBody, body);
                        }

                        return;
                     }
                  }

                  OkHttpUtilsKt.postForm($this$newCallResponseBody, (Map)AnalyzeUrl.this.fieldMap, true);
               } else {
                  OkHttpUtilsKt.get($this$newCallResponseBody, AnalyzeUrl.this.urlNoQuery, (Map)AnalyzeUrl.this.fieldMap, true);
               }

            }

            // $FF: synthetic class
            @Metadata(
               mv = {1, 5, 1},
               k = 3,
               xi = 48
            )
            public class WhenMappings {
               // $FF: synthetic field
               public static final int[] $EnumSwitchMapping$0;

               static {
                  int[] var0 = new int[RequestMethod.values().length];
                  var0[RequestMethod.POST.ordinal()] = 1;
                  $EnumSwitchMapping$0 = var0;
               }
            }
         });
         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = concurrentRecord;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = OkHttpUtilsKt.newCallResponseBody(var11, var10001, var10002, (Continuation)$continuation);
         if (var10000 == var8) {
            return var8;
         }
         break;
      case 1:
         concurrentRecord = (AnalyzeUrl.ConcurrentRecord)((<undefinedtype>)$continuation).L$1;
         this = (AnalyzeUrl)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      byte[] byteArray = ((ResponseBody)var10000).bytes();
      this.fetchEnd(concurrentRecord);
      return byteArray;
   }

   @NotNull
   public final byte[] getByteArray() {
      return (byte[])BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2<CoroutineScope, Continuation<? super byte[]>, Object>((Continuation)null) {
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            Object var2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            Object var10000;
            switch(this.label) {
            case 0:
               ResultKt.throwOnFailure($result);
               AnalyzeUrl var3 = AnalyzeUrl.this;
               Continuation var10001 = (Continuation)this;
               this.label = 1;
               var10000 = var3.getByteArrayAwait(var10001);
               if (var10000 == var2) {
                  return var2;
               }
               break;
            case 1:
               ResultKt.throwOnFailure($result);
               var10000 = $result;
               break;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            return var10000;
         }

         @NotNull
         public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return (Continuation)(new <anonymous constructor>($completion));
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super byte[]> p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }), 1, (Object)null);
   }

   @Nullable
   public final Object upload(@NotNull String fileName, @NotNull Object file, @NotNull String contentType, @NotNull Continuation<? super StrResponse> $completion) {
      return OkHttpUtilsKt.newCallStrResponse(HttpHelperKt.getProxyClient$default(this.proxy, (DebugLog)null, 2, (Object)null), this.retry, (Function1)(new Function1<Builder, Unit>() {
         public final void invoke(@NotNull Builder $this$newCallStrResponse) {
            Intrinsics.checkNotNullParameter($this$newCallStrResponse, "$this$newCallStrResponse");
            $this$newCallStrResponse.url(AnalyzeUrl.this.urlNoQuery);
            Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
            String json$iv = AnalyzeUrl.this.getBody();
            int $i$f$fromJsonObject = false;
            boolean var6 = false;

            Object var7;
            Object var10000;
            boolean $i$f$genericType;
            try {
               kotlin.Result.Companion var23 = Result.Companion;
               int var8 = false;
               $i$f$genericType = false;
               Type var27 = (new AnalyzeUrl$upload$2$invoke$$inlined$fromJsonObject$1()).getType();
               Intrinsics.checkNotNullExpressionValue(var27, "object : TypeToken<T>() {}.type");
               var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var27);
               if (!(var10000 instanceof HashMap)) {
                  var10000 = null;
               }

               HashMap var25 = (HashMap)var10000;
               $i$f$genericType = false;
               var7 = Result.constructor-impl(var25);
            } catch (Throwable var18) {
               kotlin.Result.Companion var9 = Result.Companion;
               boolean var10 = false;
               var7 = Result.constructor-impl(ResultKt.createFailure(var18));
            }

            boolean var20 = false;
            var10000 = Result.isFailure-impl(var7) ? null : var7;
            Intrinsics.checkNotNull(var10000);
            HashMap bodyMap = (HashMap)var10000;
            Map $this$forEach$iv = (Map)bodyMap;
            json$iv = fileName;
            Object var21 = file;
            String var22 = contentType;
            int $i$f$forEach = false;
            $i$f$genericType = false;
            Iterator var28 = $this$forEach$iv.entrySet().iterator();

            while(var28.hasNext()) {
               Entry element$iv = (Entry)var28.next();
               int var13 = false;
               if (Intrinsics.areEqual(element$iv.getValue().toString(), "fileRequest")) {
                  Map var14 = (Map)bodyMap;
                  Object var15 = element$iv.getKey();
                  Pair[] var16 = new Pair[]{new Pair("fileName", json$iv), new Pair("file", var21), new Pair("contentType", var22)};
                  Map var29 = MapsKt.mapOf(var16);
                  boolean var17 = false;
                  var14.put(var15, var29);
               }
            }

            OkHttpUtilsKt.postMultipart($this$newCallStrResponse, AnalyzeUrl.this.getType(), (Map)bodyMap);
         }
      }), $completion);
   }

   private final void setCookie(String tag) {
      String domain = NetworkUtils.INSTANCE.getSubDomain(tag == null ? this.url : tag);
      CharSequence var3 = (CharSequence)domain;
      boolean var4 = false;
      if (var3.length() != 0) {
         CookieStore cookieStore = new CookieStore(this.getUserNameSpace());
         boolean var10;
         String cookie;
         if (this.enabledCookieJar) {
            cookie = Intrinsics.stringPlus(domain, "_cookieJar");
            String var5 = cookieStore.getCookie(cookie);
            if (var5 != null) {
               boolean var7 = false;
               boolean var8 = false;
               var10 = false;
               cookieStore.replaceCookie(domain, var5);
            }
         }

         cookie = cookieStore.getCookie(domain);
         CharSequence var16 = (CharSequence)cookie;
         boolean var6 = false;
         if (var16.length() > 0) {
            Map cookieMap = cookieStore.cookieToMap(cookie);
            String var20 = (String)this.headerMap.get("Cookie");
            String newCookie = var20 == null ? "" : var20;
            Map customCookieMap = cookieStore.cookieToMap(newCookie);
            cookieMap.putAll(customCookieMap);
            newCookie = cookieStore.mapToCookie(cookieMap);
            if (newCookie != null) {
               var10 = false;
               boolean var11 = false;
               int var13 = false;
               String var10000 = (String)this.getHeaderMap().put("Cookie", newCookie);
            }
         }

      }
   }

   @NotNull
   public final String getUserAgent() {
      String var1 = (String)this.headerMap.get("User-Agent");
      return var1 == null ? AppConst.INSTANCE.getUserAgent() : var1;
   }

   public final boolean isPost() {
      return this.method == RequestMethod.POST;
   }

   @Nullable
   public byte[] aesBase64DecodeToByteArray(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return JsExtensions.DefaultImpls.aesBase64DecodeToByteArray(this, str, key, transformation, iv);
   }

   @Nullable
   public String aesBase64DecodeToString(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return JsExtensions.DefaultImpls.aesBase64DecodeToString(this, str, key, transformation, iv);
   }

   @Nullable
   public String aesDecodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return JsExtensions.DefaultImpls.aesDecodeArgsBase64Str(this, data, key, mode, padding, iv);
   }

   @Nullable
   public byte[] aesDecodeToByteArray(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return JsExtensions.DefaultImpls.aesDecodeToByteArray(this, str, key, transformation, iv);
   }

   @Nullable
   public String aesDecodeToString(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return JsExtensions.DefaultImpls.aesDecodeToString(this, str, key, transformation, iv);
   }

   @Nullable
   public String aesEncodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return JsExtensions.DefaultImpls.aesEncodeArgsBase64Str(this, data, key, mode, padding, iv);
   }

   @Nullable
   public byte[] aesEncodeToBase64ByteArray(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return JsExtensions.DefaultImpls.aesEncodeToBase64ByteArray(this, data, key, transformation, iv);
   }

   @Nullable
   public String aesEncodeToBase64String(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return JsExtensions.DefaultImpls.aesEncodeToBase64String(this, data, key, transformation, iv);
   }

   @Nullable
   public byte[] aesEncodeToByteArray(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return JsExtensions.DefaultImpls.aesEncodeToByteArray(this, data, key, transformation, iv);
   }

   @Nullable
   public String aesEncodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return JsExtensions.DefaultImpls.aesEncodeToString(this, data, key, transformation, iv);
   }

   @Nullable
   public String ajax(@NotNull String urlStr) {
      return JsExtensions.DefaultImpls.ajax(this, urlStr);
   }

   @NotNull
   public StrResponse[] ajaxAll(@NotNull String[] urlList) {
      return JsExtensions.DefaultImpls.ajaxAll(this, urlList);
   }

   @NotNull
   public String androidId() {
      return JsExtensions.DefaultImpls.androidId(this);
   }

   @NotNull
   public String base64Decode(@NotNull String str) {
      return JsExtensions.DefaultImpls.base64Decode(this, str);
   }

   @NotNull
   public String base64Decode(@NotNull String str, int flags) {
      return JsExtensions.DefaultImpls.base64Decode(this, str, flags);
   }

   @Nullable
   public byte[] base64DecodeToByteArray(@Nullable String str) {
      return JsExtensions.DefaultImpls.base64DecodeToByteArray(this, str);
   }

   @Nullable
   public byte[] base64DecodeToByteArray(@Nullable String str, int flags) {
      return JsExtensions.DefaultImpls.base64DecodeToByteArray(this, str, flags);
   }

   @Nullable
   public String base64Encode(@NotNull String str) {
      return JsExtensions.DefaultImpls.base64Encode(this, str);
   }

   @Nullable
   public String base64Encode(@NotNull String str, int flags) {
      return JsExtensions.DefaultImpls.base64Encode(this, str, flags);
   }

   @Nullable
   public String cacheFile(@NotNull String urlStr) {
      return JsExtensions.DefaultImpls.cacheFile(this, urlStr);
   }

   @Nullable
   public String cacheFile(@NotNull String urlStr, int saveTime) {
      return JsExtensions.DefaultImpls.cacheFile(this, urlStr, saveTime);
   }

   @NotNull
   public StrResponse connect(@NotNull String urlStr) {
      return JsExtensions.DefaultImpls.connect(this, urlStr);
   }

   @NotNull
   public StrResponse connect(@NotNull String urlStr, @Nullable String header) {
      return JsExtensions.DefaultImpls.connect(this, urlStr, header);
   }

   public void deleteFile(@NotNull String path) {
      JsExtensions.DefaultImpls.deleteFile(this, path);
   }

   @Nullable
   public String desBase64DecodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return JsExtensions.DefaultImpls.desBase64DecodeToString(this, data, key, transformation, iv);
   }

   @Nullable
   public String desDecodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return JsExtensions.DefaultImpls.desDecodeToString(this, data, key, transformation, iv);
   }

   @Nullable
   public String desEncodeToBase64String(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return JsExtensions.DefaultImpls.desEncodeToBase64String(this, data, key, transformation, iv);
   }

   @Nullable
   public String desEncodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return JsExtensions.DefaultImpls.desEncodeToString(this, data, key, transformation, iv);
   }

   @Nullable
   public String digestBase64Str(@NotNull String data, @NotNull String algorithm) {
      return JsExtensions.DefaultImpls.digestBase64Str(this, data, algorithm);
   }

   @Nullable
   public String digestHex(@NotNull String data, @NotNull String algorithm) {
      return JsExtensions.DefaultImpls.digestHex(this, data, algorithm);
   }

   @NotNull
   public String downloadFile(@NotNull String content, @NotNull String url) {
      return JsExtensions.DefaultImpls.downloadFile(this, content, url);
   }

   @NotNull
   public String encodeURI(@NotNull String str) {
      return JsExtensions.DefaultImpls.encodeURI(this, str);
   }

   @NotNull
   public String encodeURI(@NotNull String str, @NotNull String enc) {
      return JsExtensions.DefaultImpls.encodeURI(this, str, enc);
   }

   @NotNull
   public org.jsoup.Connection.Response get(@NotNull String urlStr, @NotNull Map<String, String> headers) {
      return JsExtensions.DefaultImpls.get(this, urlStr, headers);
   }

   @NotNull
   public String getCookie(@NotNull String tag, @Nullable String key) {
      return JsExtensions.DefaultImpls.getCookie(this, tag, key);
   }

   @NotNull
   public File getFile(@NotNull String path) {
      return JsExtensions.DefaultImpls.getFile(this, path);
   }

   @NotNull
   public String getTxtInFolder(@NotNull String unzipPath) {
      return JsExtensions.DefaultImpls.getTxtInFolder(this, unzipPath);
   }

   @Nullable
   public byte[] getZipByteArrayContent(@NotNull String url, @NotNull String path) {
      return JsExtensions.DefaultImpls.getZipByteArrayContent(this, url, path);
   }

   @NotNull
   public String getZipStringContent(@NotNull String url, @NotNull String path) {
      return JsExtensions.DefaultImpls.getZipStringContent(this, url, path);
   }

   @NotNull
   public String getZipStringContent(@NotNull String url, @NotNull String path, @NotNull String charsetName) {
      return JsExtensions.DefaultImpls.getZipStringContent(this, url, path, charsetName);
   }

   @NotNull
   public org.jsoup.Connection.Response head(@NotNull String urlStr, @NotNull Map<String, String> headers) {
      return JsExtensions.DefaultImpls.head(this, urlStr, headers);
   }

   @NotNull
   public String htmlFormat(@NotNull String str) {
      return JsExtensions.DefaultImpls.htmlFormat(this, str);
   }

   @NotNull
   public String importScript(@NotNull String path) {
      return JsExtensions.DefaultImpls.importScript(this, path);
   }

   @NotNull
   public String log(@NotNull String msg) {
      return JsExtensions.DefaultImpls.log(this, msg);
   }

   public void logType(@Nullable Object any) {
      JsExtensions.DefaultImpls.logType(this, any);
   }

   public void longToast(@Nullable Object msg) {
      JsExtensions.DefaultImpls.longToast(this, msg);
   }

   @NotNull
   public String md5Encode(@NotNull String str) {
      return JsExtensions.DefaultImpls.md5Encode(this, str);
   }

   @NotNull
   public String md5Encode16(@NotNull String str) {
      return JsExtensions.DefaultImpls.md5Encode16(this, str);
   }

   @NotNull
   public org.jsoup.Connection.Response post(@NotNull String urlStr, @NotNull String body, @NotNull Map<String, String> headers) {
      return JsExtensions.DefaultImpls.post(this, urlStr, body, headers);
   }

   @Nullable
   public QueryTTF queryBase64TTF(@Nullable String base64) {
      return JsExtensions.DefaultImpls.queryBase64TTF(this, base64);
   }

   @Nullable
   public QueryTTF queryTTF(@Nullable String str) {
      return JsExtensions.DefaultImpls.queryTTF(this, str);
   }

   @NotNull
   public String randomUUID() {
      return JsExtensions.DefaultImpls.randomUUID(this);
   }

   @Nullable
   public byte[] readFile(@NotNull String path) {
      return JsExtensions.DefaultImpls.readFile(this, path);
   }

   @NotNull
   public String readTxtFile(@NotNull String path) {
      return JsExtensions.DefaultImpls.readTxtFile(this, path);
   }

   @NotNull
   public String readTxtFile(@NotNull String path, @NotNull String charsetName) {
      return JsExtensions.DefaultImpls.readTxtFile(this, path, charsetName);
   }

   @NotNull
   public String replaceFont(@NotNull String text, @Nullable QueryTTF font1, @Nullable QueryTTF font2) {
      return JsExtensions.DefaultImpls.replaceFont(this, text, font1, font2);
   }

   @NotNull
   public String timeFormat(long time) {
      return JsExtensions.DefaultImpls.timeFormat(this, time);
   }

   @Nullable
   public String timeFormatUTC(long time, @NotNull String format, int sh) {
      return JsExtensions.DefaultImpls.timeFormatUTC(this, time, format, sh);
   }

   public void toast(@Nullable Object msg) {
      JsExtensions.DefaultImpls.toast(this, msg);
   }

   @Nullable
   public String tripleDESDecodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return JsExtensions.DefaultImpls.tripleDESDecodeArgsBase64Str(this, data, key, mode, padding, iv);
   }

   @Nullable
   public String tripleDESDecodeStr(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return JsExtensions.DefaultImpls.tripleDESDecodeStr(this, data, key, mode, padding, iv);
   }

   @Nullable
   public String tripleDESEncodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return JsExtensions.DefaultImpls.tripleDESEncodeArgsBase64Str(this, data, key, mode, padding, iv);
   }

   @Nullable
   public String tripleDESEncodeBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return JsExtensions.DefaultImpls.tripleDESEncodeBase64Str(this, data, key, mode, padding, iv);
   }

   @NotNull
   public String unzipFile(@NotNull String zipPath) {
      return JsExtensions.DefaultImpls.unzipFile(this, zipPath);
   }

   @NotNull
   public String utf8ToGbk(@NotNull String str) {
      return JsExtensions.DefaultImpls.utf8ToGbk(this, str);
   }

   @Nullable
   public String webView(@Nullable String html, @Nullable String url, @Nullable String js) {
      return JsExtensions.DefaultImpls.webView(this, html, url, js);
   }

   @JvmOverloads
   @NotNull
   public final StrResponse getStrResponse(@Nullable String jsStr, @Nullable String sourceRegex) {
      return getStrResponse$default(this, jsStr, sourceRegex, false, 4, (Object)null);
   }

   @JvmOverloads
   @NotNull
   public final StrResponse getStrResponse(@Nullable String jsStr) {
      return getStrResponse$default(this, jsStr, (String)null, false, 6, (Object)null);
   }

   @JvmOverloads
   @NotNull
   public final StrResponse getStrResponse() {
      return getStrResponse$default(this, (String)null, (String)null, false, 7, (Object)null);
   }

   static {
      Pattern var0 = Pattern.compile("\\s*,\\s*(?=\\{)");
      Intrinsics.checkNotNullExpressionValue(var0, "compile(\"\\\\s*,\\\\s*(?=\\\\{)\")");
      paramPattern = var0;
      pagePattern = Pattern.compile("<(.*?)>");
      boolean var1 = false;
      concurrentRecordMap = new HashMap();
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"},
      d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeUrl$Companion;", "", "()V", "concurrentRecordMap", "Ljava/util/HashMap;", "", "Lio/legado/app/model/analyzeRule/AnalyzeUrl$ConcurrentRecord;", "Lkotlin/collections/HashMap;", "pagePattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "paramPattern", "getParamPattern", "()Ljava/util/regex/Pattern;", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final Pattern getParamPattern() {
         return AnalyzeUrl.paramPattern;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÂ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÂ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\bHÂ\u0003¢\u0006\u0002\u0010\u0014J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÂ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÂ\u0003Jz\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003J\b\u0010\u001f\u001a\u0004\u0018\u00010\u0003J\u0010\u0010 \u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010!J\b\u0010\"\u001a\u0004\u0018\u00010\u0003J\b\u0010#\u001a\u0004\u0018\u00010\u0003J\u0006\u0010$\u001a\u00020\bJ\b\u0010%\u001a\u0004\u0018\u00010\u0003J\b\u0010&\u001a\u0004\u0018\u00010\u0003J\t\u0010'\u001a\u00020\bHÖ\u0001J\u0010\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u0010+\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u0010,\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u0010-\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u0010.\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u0010/\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u00100\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u0010\u00101\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0003J\t\u00102\u001a\u00020\u0003HÖ\u0001J\u0006\u00103\u001a\u00020\u001cJ\u000e\u00103\u001a\u00020)2\u0006\u00104\u001a\u00020\u001cR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"},
      d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeUrl$UrlOption;", "", "method", "", "charset", "headers", "body", "retry", "", "type", "webView", "webJs", "js", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "()Ljava/lang/Integer;", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)Lio/legado/app/model/analyzeRule/AnalyzeUrl$UrlOption;", "equals", "", "other", "getBody", "getCharset", "getHeaderMap", "", "getJs", "getMethod", "getRetry", "getType", "getWebJs", "hashCode", "setBody", "", "value", "setCharset", "setHeaders", "setJs", "setMethod", "setRetry", "setType", "setWebJs", "toString", "useWebView", "boolean", "reader-pro"}
   )
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

      // $FF: synthetic method
      public UrlOption(String var1, String var2, Object var3, Object var4, Integer var5, String var6, Object var7, String var8, String var9, int var10, DefaultConstructorMarker var11) {
         if ((var10 & 1) != 0) {
            var1 = null;
         }

         if ((var10 & 2) != 0) {
            var2 = null;
         }

         if ((var10 & 4) != 0) {
            var3 = null;
         }

         if ((var10 & 8) != 0) {
            var4 = null;
         }

         if ((var10 & 16) != 0) {
            var5 = null;
         }

         if ((var10 & 32) != 0) {
            var6 = null;
         }

         if ((var10 & 64) != 0) {
            var7 = null;
         }

         if ((var10 & 128) != 0) {
            var8 = null;
         }

         if ((var10 & 256) != 0) {
            var9 = null;
         }

         this(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      }

      public final void setMethod(@Nullable String value) {
         CharSequence var2 = (CharSequence)value;
         boolean var3 = false;
         boolean var4 = false;
         this.method = var2 == null || StringsKt.isBlank(var2) ? null : value;
      }

      @Nullable
      public final String getMethod() {
         return this.method;
      }

      public final void setCharset(@Nullable String value) {
         CharSequence var2 = (CharSequence)value;
         boolean var3 = false;
         boolean var4 = false;
         this.charset = var2 == null || StringsKt.isBlank(var2) ? null : value;
      }

      @Nullable
      public final String getCharset() {
         return this.charset;
      }

      public final void setRetry(@Nullable String value) {
         CharSequence var2 = (CharSequence)value;
         boolean var3 = false;
         boolean var4 = false;
         this.retry = var2 == null || var2.length() == 0 ? null : StringsKt.toIntOrNull(value);
      }

      public final int getRetry() {
         Integer var1 = this.retry;
         return var1 == null ? 0 : var1;
      }

      public final void setType(@Nullable String value) {
         CharSequence var2 = (CharSequence)value;
         boolean var3 = false;
         boolean var4 = false;
         this.type = var2 == null || StringsKt.isBlank(var2) ? null : value;
      }

      @Nullable
      public final String getType() {
         return this.type;
      }

      public final boolean useWebView() {
         Object var1 = this.webView;
         return !(((var1 == null ? true : Intrinsics.areEqual(var1, "")) ? true : Intrinsics.areEqual(var1, false)) ? true : Intrinsics.areEqual(var1, "false"));
      }

      public final void useWebView(boolean boolean) {
         this.webView = var1 ? true : null;
      }

      public final void setHeaders(@Nullable String value) {
         AnalyzeUrl.UrlOption var10000 = this;
         CharSequence var2 = (CharSequence)value;
         boolean $i$f$fromJsonObject = false;
         boolean var4 = false;
         Map var10001;
         if (var2 == null || StringsKt.isBlank(var2)) {
            var10001 = (Map)null;
         } else {
            Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
            $i$f$fromJsonObject = false;
            var4 = false;

            Object var5;
            try {
               kotlin.Result.Companion var13 = Result.Companion;
               int var6 = false;
               int $i$f$genericType = false;
               Type var16 = (new AnalyzeUrl$UrlOption$setHeaders$$inlined$fromJsonObject$1()).getType();
               Intrinsics.checkNotNullExpressionValue(var16, "object : TypeToken<T>() {}.type");
               Object var17 = $this$fromJsonObject$iv.fromJson(value, var16);
               if (!(var17 instanceof Map)) {
                  var17 = null;
               }

               Map var14 = (Map)var17;
               $i$f$genericType = false;
               var5 = Result.constructor-impl(var14);
            } catch (Throwable var11) {
               kotlin.Result.Companion var7 = Result.Companion;
               boolean var8 = false;
               var5 = Result.constructor-impl(ResultKt.createFailure(var11));
            }

            var10000 = this;
            $i$f$fromJsonObject = false;
            var10001 = (Map)(Result.isFailure-impl(var5) ? null : var5);
         }

         var10000.headers = var10001;
      }

      @Nullable
      public final Map<?, ?> getHeaderMap() {
         Object value = this.headers;
         Map var10000;
         if (value instanceof Map) {
            var10000 = (Map)value;
         } else if (value instanceof String) {
            Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
            String json$iv = (String)value;
            int $i$f$fromJsonObject = false;
            boolean var5 = false;

            Object var6;
            try {
               kotlin.Result.Companion var12 = Result.Companion;
               int var7 = false;
               int $i$f$genericType = false;
               Type var15 = (new AnalyzeUrl$UrlOption$getHeaderMap$$inlined$fromJsonObject$1()).getType();
               Intrinsics.checkNotNullExpressionValue(var15, "object : TypeToken<T>() {}.type");
               Object var16 = $this$fromJsonObject$iv.fromJson(json$iv, var15);
               if (!(var16 instanceof Map)) {
                  var16 = null;
               }

               Map var13 = (Map)var16;
               $i$f$genericType = false;
               var6 = Result.constructor-impl(var13);
            } catch (Throwable var10) {
               kotlin.Result.Companion var8 = Result.Companion;
               boolean var9 = false;
               var6 = Result.constructor-impl(ResultKt.createFailure(var10));
            }

            boolean var11 = false;
            var10000 = (Map)(Result.isFailure-impl(var6) ? null : var6);
         } else {
            var10000 = null;
         }

         return var10000;
      }

      public final void setBody(@Nullable String value) {
         AnalyzeUrl.UrlOption var10000 = this;
         CharSequence var2 = (CharSequence)value;
         boolean $i$f$fromJsonArray = false;
         boolean var4 = false;
         Object var10001;
         if (var2 == null || StringsKt.isBlank(var2)) {
            var10001 = null;
         } else {
            Object var5;
            boolean var6;
            kotlin.Result.Companion var7;
            boolean var8;
            Gson $this$fromJsonArray$iv;
            kotlin.Result.Companion var14;
            boolean $i$f$genericType;
            if (StringExtensionsKt.isJsonObject(value)) {
               $this$fromJsonArray$iv = GsonExtensionsKt.getGSON();
               $i$f$fromJsonArray = false;
               var4 = false;

               try {
                  var14 = Result.Companion;
                  var6 = false;
                  $i$f$genericType = false;
                  Type var18 = (new AnalyzeUrl$UrlOption$setBody$$inlined$fromJsonObject$1()).getType();
                  Intrinsics.checkNotNullExpressionValue(var18, "object : TypeToken<T>() {}.type");
                  Object var20 = $this$fromJsonArray$iv.fromJson(value, var18);
                  if (!(var20 instanceof Map)) {
                     var20 = null;
                  }

                  Map var15 = (Map)var20;
                  $i$f$genericType = false;
                  var5 = Result.constructor-impl(var15);
               } catch (Throwable var12) {
                  var7 = Result.Companion;
                  var8 = false;
                  var5 = Result.constructor-impl(ResultKt.createFailure(var12));
               }

               var10000 = this;
               var10001 = Result.box-impl(var5);
            } else if (StringExtensionsKt.isJsonArray(value)) {
               $this$fromJsonArray$iv = GsonExtensionsKt.getGSON();
               $i$f$fromJsonArray = false;
               var4 = false;

               try {
                  var14 = Result.Companion;
                  var6 = false;
                  Object var19 = $this$fromJsonArray$iv.fromJson(value, (Type)(new ParameterizedTypeImpl(Map.class)));
                  List var16 = var19 instanceof List ? (List)var19 : null;
                  $i$f$genericType = false;
                  var5 = Result.constructor-impl(var16);
               } catch (Throwable var11) {
                  var7 = Result.Companion;
                  var8 = false;
                  var5 = Result.constructor-impl(ResultKt.createFailure(var11));
               }

               var10000 = this;
               var10001 = Result.box-impl(var5);
            } else {
               var10001 = (Serializable)value;
            }
         }

         var10000.body = var10001;
      }

      @Nullable
      public final String getBody() {
         Object var1 = this.body;
         String var10000;
         if (var1 == null) {
            var10000 = null;
         } else {
            boolean var3 = false;
            boolean var4 = false;
            int var6 = false;
            var10000 = var1 instanceof String ? (String)var1 : GsonExtensionsKt.getGSON().toJson(var1);
         }

         return var10000;
      }

      public final void setWebJs(@Nullable String value) {
         CharSequence var2 = (CharSequence)value;
         boolean var3 = false;
         boolean var4 = false;
         this.webJs = var2 == null || StringsKt.isBlank(var2) ? null : value;
      }

      @Nullable
      public final String getWebJs() {
         return this.webJs;
      }

      public final void setJs(@Nullable String value) {
         CharSequence var2 = (CharSequence)value;
         boolean var3 = false;
         boolean var4 = false;
         this.js = var2 == null || StringsKt.isBlank(var2) ? null : value;
      }

      @Nullable
      public final String getJs() {
         return this.js;
      }

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
      public final AnalyzeUrl.UrlOption copy(@Nullable String method, @Nullable String charset, @Nullable Object headers, @Nullable Object body, @Nullable Integer retry, @Nullable String type, @Nullable Object webView, @Nullable String webJs, @Nullable String js) {
         return new AnalyzeUrl.UrlOption(method, charset, headers, body, retry, type, webView, webJs, js);
      }

      // $FF: synthetic method
      public static AnalyzeUrl.UrlOption copy$default(AnalyzeUrl.UrlOption var0, String var1, String var2, Object var3, Object var4, Integer var5, String var6, Object var7, String var8, String var9, int var10, Object var11) {
         if ((var10 & 1) != 0) {
            var1 = var0.method;
         }

         if ((var10 & 2) != 0) {
            var2 = var0.charset;
         }

         if ((var10 & 4) != 0) {
            var3 = var0.headers;
         }

         if ((var10 & 8) != 0) {
            var4 = var0.body;
         }

         if ((var10 & 16) != 0) {
            var5 = var0.retry;
         }

         if ((var10 & 32) != 0) {
            var6 = var0.type;
         }

         if ((var10 & 64) != 0) {
            var7 = var0.webView;
         }

         if ((var10 & 128) != 0) {
            var8 = var0.webJs;
         }

         if ((var10 & 256) != 0) {
            var9 = var0.js;
         }

         return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      }

      @NotNull
      public String toString() {
         return "UrlOption(method=" + this.method + ", charset=" + this.charset + ", headers=" + this.headers + ", body=" + this.body + ", retry=" + this.retry + ", type=" + this.type + ", webView=" + this.webView + ", webJs=" + this.webJs + ", js=" + this.js + ')';
      }

      public int hashCode() {
         int result = this.method == null ? 0 : this.method.hashCode();
         result = result * 31 + (this.charset == null ? 0 : this.charset.hashCode());
         result = result * 31 + (this.headers == null ? 0 : this.headers.hashCode());
         result = result * 31 + (this.body == null ? 0 : this.body.hashCode());
         result = result * 31 + (this.retry == null ? 0 : this.retry.hashCode());
         result = result * 31 + (this.type == null ? 0 : this.type.hashCode());
         result = result * 31 + (this.webView == null ? 0 : this.webView.hashCode());
         result = result * 31 + (this.webJs == null ? 0 : this.webJs.hashCode());
         result = result * 31 + (this.js == null ? 0 : this.js.hashCode());
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof AnalyzeUrl.UrlOption)) {
            return false;
         } else {
            AnalyzeUrl.UrlOption var2 = (AnalyzeUrl.UrlOption)other;
            if (!Intrinsics.areEqual(this.method, var2.method)) {
               return false;
            } else if (!Intrinsics.areEqual(this.charset, var2.charset)) {
               return false;
            } else if (!Intrinsics.areEqual(this.headers, var2.headers)) {
               return false;
            } else if (!Intrinsics.areEqual(this.body, var2.body)) {
               return false;
            } else if (!Intrinsics.areEqual(this.retry, var2.retry)) {
               return false;
            } else if (!Intrinsics.areEqual(this.type, var2.type)) {
               return false;
            } else if (!Intrinsics.areEqual(this.webView, var2.webView)) {
               return false;
            } else if (!Intrinsics.areEqual(this.webJs, var2.webJs)) {
               return false;
            } else {
               return Intrinsics.areEqual(this.js, var2.js);
            }
         }
      }

      public UrlOption() {
         this((String)null, (String)null, (Object)null, (Object)null, (Integer)null, (String)null, (Object)null, (String)null, (String)null, 511, (DefaultConstructorMarker)null);
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001c"},
      d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeUrl$ConcurrentRecord;", "", "concurrent", "", "time", "", "frequency", "", "(ZJI)V", "getConcurrent", "()Z", "getFrequency", "()I", "setFrequency", "(I)V", "getTime", "()J", "setTime", "(J)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "reader-pro"}
   )
   public static final class ConcurrentRecord {
      private final boolean concurrent;
      private long time;
      private int frequency;

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

      public final void setTime(long <set-?>) {
         this.time = var1;
      }

      public final int getFrequency() {
         return this.frequency;
      }

      public final void setFrequency(int <set-?>) {
         this.frequency = var1;
      }

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
      public final AnalyzeUrl.ConcurrentRecord copy(boolean concurrent, long time, int frequency) {
         return new AnalyzeUrl.ConcurrentRecord(concurrent, time, frequency);
      }

      // $FF: synthetic method
      public static AnalyzeUrl.ConcurrentRecord copy$default(AnalyzeUrl.ConcurrentRecord var0, boolean var1, long var2, int var4, int var5, Object var6) {
         if ((var5 & 1) != 0) {
            var1 = var0.concurrent;
         }

         if ((var5 & 2) != 0) {
            var2 = var0.time;
         }

         if ((var5 & 4) != 0) {
            var4 = var0.frequency;
         }

         return var0.copy(var1, var2, var4);
      }

      @NotNull
      public String toString() {
         return "ConcurrentRecord(concurrent=" + this.concurrent + ", time=" + this.time + ", frequency=" + this.frequency + ')';
      }

      public int hashCode() {
         byte var10000 = this.concurrent;
         if (var10000 != 0) {
            var10000 = 1;
         }

         int result = var10000;
         int result = result * 31 + Long.hashCode(this.time);
         result = result * 31 + Integer.hashCode(this.frequency);
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof AnalyzeUrl.ConcurrentRecord)) {
            return false;
         } else {
            AnalyzeUrl.ConcurrentRecord var2 = (AnalyzeUrl.ConcurrentRecord)other;
            if (this.concurrent != var2.concurrent) {
               return false;
            } else if (this.time != var2.time) {
               return false;
            } else {
               return this.frequency == var2.frequency;
            }
         }
      }
   }

   // $FF: synthetic class
   @Metadata(
      mv = {1, 5, 1},
      k = 3,
      xi = 48
   )
   public class WhenMappings {
      // $FF: synthetic field
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[RequestMethod.values().length];
         var0[RequestMethod.GET.ordinal()] = 1;
         var0[RequestMethod.POST.ordinal()] = 2;
         $EnumSwitchMapping$0 = var0;
      }
   }
}
