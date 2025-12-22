package io.legado.app.model.analyzeRule;

import com.google.gson.Gson;
import com.script.Bindings;
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
import java.io.File;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Entities;
import org.mozilla.javascript.NativeObject;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 g2\u00020\u0001:\u0003ghiB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0012\u00108\u001a\u0004\u0018\u00010\u00102\u0006\u00109\u001a\u00020\u0010H\u0016J\u001c\u0010:\u001a\u0004\u0018\u00010\u001e2\u0006\u0010;\u001a\u00020\u00102\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u001eJ\u000e\u0010=\u001a\u00020\u00102\u0006\u0010>\u001a\u00020\u0010J\u0010\u0010?\u001a\u00020\n2\u0006\u0010@\u001a\u00020\u001eH\u0002J\u0010\u0010A\u001a\u00020\f2\u0006\u0010@\u001a\u00020\u001eH\u0002J\u0010\u0010B\u001a\u00020\u000e2\u0006\u0010@\u001a\u00020\u001eH\u0002J\u0010\u0010C\u001a\u0004\u0018\u00010\u001e2\u0006\u0010D\u001a\u00020\u0010J\u0014\u0010E\u001a\b\u0012\u0004\u0012\u00020\u001e0F2\u0006\u0010D\u001a\u00020\u0010J\n\u0010G\u001a\u0004\u0018\u00010\u0007H\u0016J\n\u0010H\u001a\u0004\u0018\u00010\u0005H\u0016J(\u0010I\u001a\u00020\u00102\b\u0010D\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010K\u001a\u00020'H\u0007J0\u0010I\u001a\u00020\u00102\u0010\u0010L\u001a\f\u0012\b\u0012\u00060MR\u00020\u00000F2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010K\u001a\u00020'H\u0007J0\u0010N\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010F2\b\u0010O\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010K\u001a\u00020'H\u0007J8\u0010N\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010F2\u0010\u0010L\u001a\f\u0012\b\u0012\u00060MR\u00020\u00000F2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010K\u001a\u00020'H\u0007J\b\u0010P\u001a\u00020\u0010H\u0016J\u0016\u0010Q\u001a\u00020\u00102\u0006\u0010>\u001a\u00020\u00102\u0006\u0010R\u001a\u00020\u0010J\u001c\u0010S\u001a\u00020T2\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100VH\u0002J\u0006\u0010W\u001a\u00020TJ\u0006\u0010X\u001a\u00020TJ\u0006\u0010Y\u001a\u00020TJ\u001c\u0010Z\u001a\u00020\u00102\u0006\u0010<\u001a\u00020\u00102\n\u0010O\u001a\u00060MR\u00020\u0000H\u0002J\u0010\u0010[\u001a\u00020\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010J\u001e\u0010\\\u001a\u00020\u00002\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0007J\u0010\u0010]\u001a\u0004\u0018\u0001002\u0006\u0010^\u001a\u00020\u0010J4\u0010_\u001a\u00020\u00102\u0006\u0010D\u001a\u00020\u00102\"\u0010`\u001a\u001e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100aj\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010`bH\u0002J$\u0010c\u001a\f\u0012\b\u0012\u00060MR\u00020\u00000F2\b\u0010D\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010d\u001a\u00020'J\u0012\u0010e\u001a\u0004\u0018\u00010\u00102\b\u0010f\u001a\u0004\u0018\u00010\u0010R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u001e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010)\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0013\"\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u00101\u001a\u0004\u0018\u0001002\b\u0010\u000f\u001a\u0004\u0018\u000100@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006j"},
   d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeRule;", "Lio/legado/app/help/JsExtensions;", "ruleData", "Lio/legado/app/model/analyzeRule/RuleDataInterface;", "source", "Lio/legado/app/data/entities/BaseSource;", "debugLog", "Lio/legado/app/model/DebugLog;", "(Lio/legado/app/model/analyzeRule/RuleDataInterface;Lio/legado/app/data/entities/BaseSource;Lio/legado/app/model/DebugLog;)V", "analyzeByJSonPath", "Lio/legado/app/model/analyzeRule/AnalyzeByJSonPath;", "analyzeByJSoup", "Lio/legado/app/model/analyzeRule/AnalyzeByJSoup;", "analyzeByXPath", "Lio/legado/app/model/analyzeRule/AnalyzeByXPath;", "<set-?>", "", "baseUrl", "getBaseUrl", "()Ljava/lang/String;", "book", "Lio/legado/app/data/entities/BaseBook;", "getBook", "()Lio/legado/app/data/entities/BaseBook;", "chapter", "Lio/legado/app/data/entities/BookChapter;", "getChapter", "()Lio/legado/app/data/entities/BookChapter;", "setChapter", "(Lio/legado/app/data/entities/BookChapter;)V", "", "content", "getContent", "()Ljava/lang/Object;", "getDebugLog", "()Lio/legado/app/model/DebugLog;", "setDebugLog", "(Lio/legado/app/model/DebugLog;)V", "isJSON", "", "isRegex", "nextChapterUrl", "getNextChapterUrl", "setNextChapterUrl", "(Ljava/lang/String;)V", "objectChangedJP", "objectChangedJS", "objectChangedXP", "Ljava/net/URL;", "redirectUrl", "getRedirectUrl", "()Ljava/net/URL;", "getRuleData", "()Lio/legado/app/model/analyzeRule/RuleDataInterface;", "setRuleData", "(Lio/legado/app/model/analyzeRule/RuleDataInterface;)V", "ajax", "urlStr", "evalJS", "jsStr", "result", "get", "key", "getAnalyzeByJSonPath", "o", "getAnalyzeByJSoup", "getAnalyzeByXPath", "getElement", "ruleStr", "getElements", "", "getLogger", "getSource", "getString", "mContent", "isUrl", "ruleList", "Lio/legado/app/model/analyzeRule/AnalyzeRule$SourceRule;", "getStringList", "rule", "getUserNameSpace", "put", "value", "putRule", "", "map", "", "reGetBook", "refreshBookUrl", "refreshTocUrl", "replaceRegex", "setBaseUrl", "setContent", "setRedirectUrl", "url", "splitPutRule", "putMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "splitSourceRule", "allInOne", "toNumChapter", "s", "Companion", "Mode", "SourceRule", "reader-pro"}
)
public final class AnalyzeRule implements JsExtensions {
   @NotNull
   public static final AnalyzeRule.Companion Companion = new AnalyzeRule.Companion((DefaultConstructorMarker)null);
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
   private static final Pattern putPattern = Pattern.compile("@put:(\\{[^}]+?\\})", 2);
   private static final Pattern evalPattern = Pattern.compile("@get:\\{[^}]+?\\}|\\{\\{[\\w\\W]*?\\}\\}", 2);
   private static final Pattern regexPattern = Pattern.compile("\\$\\d{1,2}");
   private static final Pattern titleNumPattern = Pattern.compile("(第)(.+?)(章)");

   public AnalyzeRule(@NotNull RuleDataInterface ruleData, @Nullable BaseSource source, @Nullable DebugLog debugLog) {
      Intrinsics.checkNotNullParameter(ruleData, "ruleData");
      super();
      this.ruleData = ruleData;
      this.source = source;
      this.debugLog = debugLog;
   }

   // $FF: synthetic method
   public AnalyzeRule(RuleDataInterface var1, BaseSource var2, DebugLog var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 2) != 0) {
         var2 = null;
      }

      if ((var4 & 4) != 0) {
         var3 = null;
      }

      this(var1, var2, var3);
   }

   @NotNull
   public final RuleDataInterface getRuleData() {
      return this.ruleData;
   }

   public final void setRuleData(@NotNull RuleDataInterface <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.ruleData = var1;
   }

   @Nullable
   public final DebugLog getDebugLog() {
      return this.debugLog;
   }

   public final void setDebugLog(@Nullable DebugLog <set-?>) {
      this.debugLog = var1;
   }

   @Nullable
   public final BaseBook getBook() {
      RuleDataInterface var1 = this.ruleData;
      return var1 instanceof BaseBook ? (BaseBook)var1 : null;
   }

   @Nullable
   public final BookChapter getChapter() {
      return this.chapter;
   }

   public final void setChapter(@Nullable BookChapter <set-?>) {
      this.chapter = var1;
   }

   @Nullable
   public final String getNextChapterUrl() {
      return this.nextChapterUrl;
   }

   public final void setNextChapterUrl(@Nullable String <set-?>) {
      this.nextChapterUrl = var1;
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

   @NotNull
   public String getUserNameSpace() {
      return this.ruleData.getUserNameSpace();
   }

   @Nullable
   public BaseSource getSource() {
      return this.source;
   }

   @Nullable
   public DebugLog getLogger() {
      return this.debugLog;
   }

   @JvmOverloads
   @NotNull
   public final AnalyzeRule setContent(@Nullable Object content, @Nullable String baseUrl) {
      if (content == null) {
         throw new AssertionError("内容不可空（Content cannot be null）");
      } else {
         this.content = content;
         this.isJSON = StringExtensionsKt.isJson(content.toString());
         this.setBaseUrl(baseUrl);
         this.objectChangedXP = true;
         this.objectChangedJS = true;
         this.objectChangedJP = true;
         return this;
      }
   }

   // $FF: synthetic method
   public static AnalyzeRule setContent$default(AnalyzeRule var0, Object var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
      }

      return var0.setContent(var1, var2);
   }

   @NotNull
   public final AnalyzeRule setBaseUrl(@Nullable String baseUrl) {
      if (baseUrl != null) {
         boolean var4 = false;
         boolean var5 = false;
         int var7 = false;
         this.baseUrl = baseUrl;
      }

      return this;
   }

   @Nullable
   public final URL setRedirectUrl(@NotNull String url) {
      Intrinsics.checkNotNullParameter(url, "url");

      try {
         this.redirectUrl = new URL(url);
      } catch (Exception var3) {
         this.log("URL(" + url + ") error\n" + var3.getLocalizedMessage());
      }

      return this.redirectUrl;
   }

   private final AnalyzeByXPath getAnalyzeByXPath(Object o) {
      AnalyzeByXPath var10000;
      if (!Intrinsics.areEqual(o, this.content)) {
         var10000 = new AnalyzeByXPath(o);
      } else {
         if (this.analyzeByXPath == null || this.objectChangedXP) {
            Object var10003 = this.content;
            Intrinsics.checkNotNull(var10003);
            this.analyzeByXPath = new AnalyzeByXPath(var10003);
            this.objectChangedXP = false;
         }

         var10000 = this.analyzeByXPath;
         Intrinsics.checkNotNull(var10000);
      }

      return var10000;
   }

   private final AnalyzeByJSoup getAnalyzeByJSoup(Object o) {
      AnalyzeByJSoup var10000;
      if (!Intrinsics.areEqual(o, this.content)) {
         var10000 = new AnalyzeByJSoup(o);
      } else {
         if (this.analyzeByJSoup == null || this.objectChangedJS) {
            Object var10003 = this.content;
            Intrinsics.checkNotNull(var10003);
            this.analyzeByJSoup = new AnalyzeByJSoup(var10003);
            this.objectChangedJS = false;
         }

         var10000 = this.analyzeByJSoup;
         Intrinsics.checkNotNull(var10000);
      }

      return var10000;
   }

   private final AnalyzeByJSonPath getAnalyzeByJSonPath(Object o) {
      AnalyzeByJSonPath var10000;
      if (!Intrinsics.areEqual(o, this.content)) {
         var10000 = new AnalyzeByJSonPath(o);
      } else {
         if (this.analyzeByJSonPath == null || this.objectChangedJP) {
            Object var10003 = this.content;
            Intrinsics.checkNotNull(var10003);
            this.analyzeByJSonPath = new AnalyzeByJSonPath(var10003);
            this.objectChangedJP = false;
         }

         var10000 = this.analyzeByJSonPath;
         Intrinsics.checkNotNull(var10000);
      }

      return var10000;
   }

   @JvmOverloads
   @Nullable
   public final List<String> getStringList(@Nullable String rule, @Nullable Object mContent, boolean isUrl) {
      CharSequence var4 = (CharSequence)rule;
      boolean var5 = false;
      boolean var6 = false;
      if (var4 == null || var4.length() == 0) {
         return null;
      } else {
         List ruleList = this.splitSourceRule(rule, false);
         return this.getStringList(ruleList, mContent, isUrl);
      }
   }

   // $FF: synthetic method
   public static List getStringList$default(AnalyzeRule var0, String var1, Object var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = null;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return var0.getStringList(var1, var2, var3);
   }

   @JvmOverloads
   @Nullable
   public final List<String> getStringList(@NotNull List<AnalyzeRule.SourceRule> ruleList, @Nullable Object mContent, boolean isUrl) {
      Intrinsics.checkNotNullParameter(ruleList, "ruleList");
      Object result = null;
      Object content = mContent == null ? this.content : mContent;
      boolean var11;
      if (content != null) {
         Collection var6 = (Collection)ruleList;
         boolean var7 = false;
         if (!var6.isEmpty()) {
            result = content;
            if (content instanceof NativeObject) {
               Object var17 = ((NativeObject)content).get(((AnalyzeRule.SourceRule)ruleList.get(0)).getRule$reader_pro());
               result = var17 == null ? null : var17.toString();
            } else {
               Iterator var18 = ruleList.iterator();

               label120:
               while(true) {
                  AnalyzeRule.SourceRule sourceRule;
                  do {
                     if (!var18.hasNext()) {
                        break label120;
                     }

                     sourceRule = (AnalyzeRule.SourceRule)var18.next();
                     this.putRule((Map)sourceRule.getPutMap$reader_pro());
                     sourceRule.makeUpRule(result);
                  } while(result == null);

                  boolean var10 = false;
                  var11 = false;
                  int var13 = false;
                  CharSequence var14 = (CharSequence)sourceRule.getRule$reader_pro();
                  boolean var15 = false;
                  if (var14.length() > 0) {
                     AnalyzeRule.Mode var24 = sourceRule.getMode$reader_pro();
                     int var25 = AnalyzeRule.WhenMappings.$EnumSwitchMapping$0[var24.ordinal()];
                     Object var10000;
                     switch(var25) {
                     case 1:
                        var10000 = this.evalJS(sourceRule.getRule$reader_pro(), result);
                        break;
                     case 2:
                        var10000 = this.getAnalyzeByJSonPath(result).getStringList$reader_pro(sourceRule.getRule$reader_pro());
                        break;
                     case 3:
                        var10000 = this.getAnalyzeByXPath(result).getStringList$reader_pro(sourceRule.getRule$reader_pro());
                        break;
                     case 4:
                        var10000 = this.getAnalyzeByJSoup(result).getStringList$reader_pro(sourceRule.getRule$reader_pro());
                        break;
                     default:
                        var10000 = sourceRule.getRule$reader_pro();
                     }

                     result = var10000;
                  }

                  var14 = (CharSequence)sourceRule.getReplaceRegex$reader_pro();
                  var15 = false;
                  if (var14.length() > 0 && result instanceof List) {
                     ArrayList newList = new ArrayList();
                     Iterator var27 = ((List)result).iterator();

                     while(var27.hasNext()) {
                        Object item = var27.next();
                        newList.add(this.replaceRegex(String.valueOf(item), sourceRule));
                     }

                     result = newList;
                  } else {
                     var14 = (CharSequence)sourceRule.getReplaceRegex$reader_pro();
                     var15 = false;
                     if (var14.length() > 0) {
                        result = this.replaceRegex(String.valueOf(result), sourceRule);
                     }
                  }
               }
            }
         }
      }

      if (result == null) {
         return null;
      } else {
         if (result instanceof String) {
            CharSequence var28 = (CharSequence)((String)result);
            String[] var19 = new String[]{"\n"};
            result = StringsKt.split$default(var28, var19, false, 0, 6, (Object)null);
         }

         if (!isUrl) {
            return result instanceof List ? (List)result : null;
         } else {
            ArrayList urlList = new ArrayList();
            if (result instanceof List) {
               Iterator var22 = ((List)result).iterator();

               while(var22.hasNext()) {
                  Object url = var22.next();
                  String absoluteURL = NetworkUtils.INSTANCE.getAbsoluteURL(this.redirectUrl, String.valueOf(url));
                  CharSequence var23 = (CharSequence)absoluteURL;
                  var11 = false;
                  if (var23.length() > 0 && !urlList.contains(absoluteURL)) {
                     urlList.add(absoluteURL);
                  }
               }
            }

            return (List)urlList;
         }
      }
   }

   // $FF: synthetic method
   public static List getStringList$default(AnalyzeRule var0, List var1, Object var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = null;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return var0.getStringList(var1, var2, var3);
   }

   @JvmOverloads
   @NotNull
   public final String getString(@Nullable String ruleStr, @Nullable Object mContent, boolean isUrl) {
      if (TextUtils.isEmpty((CharSequence)ruleStr)) {
         return "";
      } else {
         List ruleList = splitSourceRule$default(this, ruleStr, false, 2, (Object)null);
         return this.getString(ruleList, mContent, isUrl);
      }
   }

   // $FF: synthetic method
   public static String getString$default(AnalyzeRule var0, String var1, Object var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = null;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return var0.getString(var1, var2, var3);
   }

   @JvmOverloads
   @NotNull
   public final String getString(@NotNull List<AnalyzeRule.SourceRule> ruleList, @Nullable Object mContent, boolean isUrl) {
      Intrinsics.checkNotNullParameter(ruleList, "ruleList");
      Object result = null;
      Object content = mContent == null ? this.content : mContent;
      boolean var7;
      boolean var10;
      boolean var11;
      boolean var13;
      boolean var15;
      Object var10000;
      if (content != null) {
         Collection var6 = (Collection)ruleList;
         var7 = false;
         if (!var6.isEmpty()) {
            result = content;
            if (content instanceof NativeObject) {
               Object var17 = ((NativeObject)content).get(((AnalyzeRule.SourceRule)ruleList.get(0)).getRule$reader_pro());
               result = var17 == null ? null : var17.toString();
            } else {
               Iterator var18 = ruleList.iterator();

               label109:
               while(true) {
                  AnalyzeRule.SourceRule sourceRule;
                  do {
                     if (!var18.hasNext()) {
                        break label109;
                     }

                     sourceRule = (AnalyzeRule.SourceRule)var18.next();
                     this.putRule((Map)sourceRule.getPutMap$reader_pro());
                     sourceRule.makeUpRule(result);
                  } while(result == null);

                  CharSequence var14;
                  label119: {
                     var10 = false;
                     var11 = false;
                     var13 = false;
                     var14 = (CharSequence)sourceRule.getRule$reader_pro();
                     var15 = false;
                     if (StringsKt.isBlank(var14)) {
                        var14 = (CharSequence)sourceRule.getReplaceRegex$reader_pro();
                        var15 = false;
                        if (var14.length() != 0) {
                           break label119;
                        }
                     }

                     AnalyzeRule.Mode var28 = sourceRule.getMode$reader_pro();
                     int var29 = AnalyzeRule.WhenMappings.$EnumSwitchMapping$0[var28.ordinal()];
                     switch(var29) {
                     case 1:
                        var10000 = this.evalJS(sourceRule.getRule$reader_pro(), result);
                        break;
                     case 2:
                        var10000 = this.getAnalyzeByJSonPath(result).getString(sourceRule.getRule$reader_pro());
                        break;
                     case 3:
                        var10000 = this.getAnalyzeByXPath(result).getString(sourceRule.getRule$reader_pro());
                        break;
                     case 4:
                        var10000 = isUrl ? this.getAnalyzeByJSoup(result).getString0$reader_pro(sourceRule.getRule$reader_pro()) : this.getAnalyzeByJSoup(result).getString$reader_pro(sourceRule.getRule$reader_pro());
                        break;
                     default:
                        var10000 = sourceRule.getRule$reader_pro();
                     }

                     result = var10000;
                  }

                  if (result != null) {
                     var14 = (CharSequence)sourceRule.getReplaceRegex$reader_pro();
                     var15 = false;
                     if (var14.length() > 0) {
                        result = this.replaceRegex(String.valueOf(result), sourceRule);
                     }
                  }
               }
            }
         }
      }

      if (result == null) {
         result = "";
      }

      var7 = false;

      Object var8;
      boolean var9;
      try {
         kotlin.Result.Companion var22 = Result.Companion;
         var9 = false;
         String var24 = Entities.unescape(String.valueOf(result));
         var10 = false;
         var8 = Result.constructor-impl(var24);
      } catch (Throwable var16) {
         kotlin.Result.Companion var27 = Result.Companion;
         var11 = false;
         var8 = Result.constructor-impl(ResultKt.createFailure(var16));
      }

      Object var21 = var8;
      boolean var25 = false;
      var9 = false;
      Throwable var30 = Result.exceptionOrNull-impl(var21);
      Throwable var26;
      if (var30 != null) {
         var26 = var30;
         var10 = false;
         var11 = false;
         var13 = false;
         var15 = false;
         this.log(Intrinsics.stringPlus("Entities.unescape() error\n", var26.getLocalizedMessage()));
      }

      var25 = false;
      var9 = false;
      var26 = Result.exceptionOrNull-impl(var21);
      if (var26 == null) {
         var10000 = var21;
      } else {
         var11 = false;
         var10000 = String.valueOf(result);
      }

      String str = (String)var10000;
      if (isUrl) {
         Intrinsics.checkNotNullExpressionValue(str, "str");
         String var31;
         if (StringsKt.isBlank((CharSequence)str)) {
            String var23 = this.baseUrl;
            var31 = var23 == null ? "" : var23;
         } else {
            var31 = NetworkUtils.INSTANCE.getAbsoluteURL(this.redirectUrl, str);
         }

         return var31;
      } else {
         Intrinsics.checkNotNullExpressionValue(str, "str");
         return str;
      }
   }

   // $FF: synthetic method
   public static String getString$default(AnalyzeRule var0, List var1, Object var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = null;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return var0.getString(var1, var2, var3);
   }

   @Nullable
   public final Object getElement(@NotNull String ruleStr) {
      Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
      if (TextUtils.isEmpty((CharSequence)ruleStr)) {
         return null;
      } else {
         Object result = null;
         Object content = this.content;
         List ruleList = this.splitSourceRule(ruleStr, true);
         if (content != null) {
            Collection var5 = (Collection)ruleList;
            boolean var6 = false;
            if (!var5.isEmpty()) {
               result = content;
               Iterator var16 = ruleList.iterator();

               while(var16.hasNext()) {
                  AnalyzeRule.SourceRule sourceRule = (AnalyzeRule.SourceRule)var16.next();
                  this.putRule((Map)sourceRule.getPutMap$reader_pro());
                  sourceRule.makeUpRule(result);
                  if (result != null) {
                     boolean var9 = false;
                     boolean var10 = false;
                     int var12 = false;
                     AnalyzeRule.Mode var13 = sourceRule.getMode$reader_pro();
                     int var14 = AnalyzeRule.WhenMappings.$EnumSwitchMapping$0[var13.ordinal()];
                     Object var20;
                     switch(var14) {
                     case 1:
                        var20 = this.evalJS(sourceRule.getRule$reader_pro(), result);
                        break;
                     case 2:
                        var20 = this.getAnalyzeByJSonPath(result).getObject$reader_pro(sourceRule.getRule$reader_pro());
                        break;
                     case 3:
                        var20 = this.getAnalyzeByXPath(result).getElements$reader_pro(sourceRule.getRule$reader_pro());
                        break;
                     case 4:
                     default:
                        var20 = this.getAnalyzeByJSoup(result).getElements$reader_pro(sourceRule.getRule$reader_pro());
                        break;
                     case 5:
                        AnalyzeByRegex var10000 = AnalyzeByRegex.INSTANCE;
                        String var10001 = String.valueOf(result);
                        String var10002 = sourceRule.getRule$reader_pro();
                        String[] var15 = new String[]{"&&"};
                        var20 = AnalyzeByRegex.getElement$default(var10000, var10001, StringExtensionsKt.splitNotBlank(var10002, var15), 0, 4, (Object)null);
                     }

                     result = var20;
                     CharSequence var18 = (CharSequence)sourceRule.getReplaceRegex$reader_pro();
                     boolean var19 = false;
                     if (var18.length() > 0) {
                        result = this.replaceRegex(String.valueOf(result), sourceRule);
                     }
                  }
               }
            }
         }

         return result;
      }
   }

   @NotNull
   public final List<Object> getElements(@NotNull String ruleStr) {
      Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
      Object result = null;
      Object content = this.content;
      List ruleList = this.splitSourceRule(ruleStr, true);
      boolean var10;
      if (content != null) {
         Collection var5 = (Collection)ruleList;
         boolean var6 = false;
         if (!var5.isEmpty()) {
            result = content;
            Iterator var16 = ruleList.iterator();

            while(var16.hasNext()) {
               AnalyzeRule.SourceRule sourceRule = (AnalyzeRule.SourceRule)var16.next();
               this.putRule((Map)sourceRule.getPutMap$reader_pro());
               if (result != null) {
                  boolean var9 = false;
                  var10 = false;
                  int var12 = false;
                  AnalyzeRule.Mode var13 = sourceRule.getMode$reader_pro();
                  int var14 = AnalyzeRule.WhenMappings.$EnumSwitchMapping$0[var13.ordinal()];
                  Object var20;
                  switch(var14) {
                  case 1:
                     var20 = this.evalJS(sourceRule.getRule$reader_pro(), result);
                     break;
                  case 2:
                     var20 = this.getAnalyzeByJSonPath(result).getList$reader_pro(sourceRule.getRule$reader_pro());
                     break;
                  case 3:
                     var20 = this.getAnalyzeByXPath(result).getElements$reader_pro(sourceRule.getRule$reader_pro());
                     break;
                  case 4:
                  default:
                     var20 = this.getAnalyzeByJSoup(result).getElements$reader_pro(sourceRule.getRule$reader_pro());
                     break;
                  case 5:
                     AnalyzeByRegex var10000 = AnalyzeByRegex.INSTANCE;
                     String var10001 = String.valueOf(result);
                     String var10002 = sourceRule.getRule$reader_pro();
                     String[] var15 = new String[]{"&&"};
                     var20 = AnalyzeByRegex.getElements$default(var10000, var10001, StringExtensionsKt.splitNotBlank(var10002, var15), 0, 4, (Object)null);
                  }

                  result = var20;
                  CharSequence var18 = (CharSequence)sourceRule.getReplaceRegex$reader_pro();
                  boolean var19 = false;
                  if (var18.length() > 0) {
                     result = this.replaceRegex(String.valueOf(result), sourceRule);
                  }
               }
            }
         }
      }

      if (result == null) {
         return (List)(new ArrayList());
      } else {
         boolean var7 = false;
         boolean var8 = false;
         var10 = false;
         return (List)result;
      }
   }

   private final void putRule(Map<String, String> map) {
      boolean var4 = false;
      Iterator var2 = map.entrySet().iterator();

      while(var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         boolean var6 = false;
         String key = (String)var3.getKey();
         boolean var7 = false;
         String value = (String)var3.getValue();
         this.put(key, getString$default(this, (String)value, (Object)null, false, 6, (Object)null));
      }

   }

   private final String splitPutRule(String ruleStr, HashMap<String, String> putMap) {
      String vRuleStr = ruleStr;
      Matcher putMatcher = putPattern.matcher((CharSequence)ruleStr);

      while(putMatcher.find()) {
         String var5 = putMatcher.group();
         Intrinsics.checkNotNullExpressionValue(var5, "putMatcher.group()");
         vRuleStr = StringsKt.replace$default(vRuleStr, var5, "", false, 4, (Object)null);
         Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
         String json$iv = putMatcher.group(1);
         int $i$f$fromJsonObject = false;
         boolean var9 = false;

         Object var10;
         try {
            kotlin.Result.Companion var17 = Result.Companion;
            int var11 = false;
            int $i$f$genericType = false;
            Type var21 = (new AnalyzeRule$splitPutRule$$inlined$fromJsonObject$1()).getType();
            Intrinsics.checkNotNullExpressionValue(var21, "object : TypeToken<T>() {}.type");
            Object var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var21);
            if (!(var10000 instanceof Map)) {
               var10000 = null;
            }

            Map var18 = (Map)var10000;
            $i$f$genericType = false;
            var10 = Result.constructor-impl(var18);
         } catch (Throwable var14) {
            kotlin.Result.Companion var12 = Result.Companion;
            boolean var13 = false;
            var10 = Result.constructor-impl(ResultKt.createFailure(var14));
         }

         boolean var16 = false;
         Map var15 = (Map)(Result.isFailure-impl(var10) ? null : var10);
         if (var15 != null) {
            var16 = false;
            $i$f$fromJsonObject = false;
            int var19 = false;
            putMap.putAll(var15);
         }
      }

      return vRuleStr;
   }

   private final String replaceRegex(String result, AnalyzeRule.SourceRule rule) {
      CharSequence vResult = (CharSequence)rule.getReplaceRegex$reader_pro();
      boolean var4 = false;
      if (vResult.length() == 0) {
         return result;
      } else {
         vResult = null;
         Object vResult = result;
         Object var5;
         boolean var6;
         kotlin.Result.Companion var7;
         boolean var8;
         String var13;
         String var10000;
         Object var17;
         kotlin.Result.Companion var18;
         boolean var19;
         String var20;
         Throwable var21;
         boolean var23;
         Object var33;
         if (rule.getReplaceFirst$reader_pro()) {
            var4 = false;

            try {
               var18 = Result.Companion;
               var6 = false;
               Pattern pattern = Pattern.compile(rule.getReplaceRegex$reader_pro());
               Matcher matcher = pattern.matcher((CharSequence)vResult);
               if (matcher.find()) {
                  var10000 = matcher.group(0);
                  Intrinsics.checkNotNull(var10000);
                  CharSequence var9 = (CharSequence)var10000;
                  String var10 = rule.getReplaceRegex$reader_pro();
                  boolean var11 = false;
                  Regex var30 = new Regex(var10);
                  String var32 = rule.getReplacement$reader_pro();
                  boolean var12 = false;
                  var10000 = var30.replaceFirst(var9, var32);
               } else {
                  var10000 = "";
               }

               var20 = var10000;
               var23 = false;
               var5 = Result.constructor-impl(var20);
            } catch (Throwable var15) {
               var7 = Result.Companion;
               var8 = false;
               var5 = Result.constructor-impl(ResultKt.createFailure(var15));
            }

            var17 = var5;
            var19 = false;
            var6 = false;
            var21 = Result.exceptionOrNull-impl(var17);
            if (var21 == null) {
               var33 = var17;
            } else {
               var8 = false;
               var13 = StringsKt.replaceFirst$default(result, rule.getReplaceRegex$reader_pro(), rule.getReplacement$reader_pro(), false, 4, (Object)null);
               var33 = var13;
            }

            var10000 = (String)var33;
         } else {
            var4 = false;

            try {
               var18 = Result.Companion;
               var6 = false;
               CharSequence var24 = (CharSequence)vResult;
               String var26 = rule.getReplaceRegex$reader_pro();
               boolean var27 = false;
               Regex var28 = new Regex(var26);
               String var29 = rule.getReplacement$reader_pro();
               boolean var31 = false;
               var20 = var28.replace(var24, var29);
               var23 = false;
               var5 = Result.constructor-impl(var20);
            } catch (Throwable var14) {
               var7 = Result.Companion;
               var8 = false;
               var5 = Result.constructor-impl(ResultKt.createFailure(var14));
            }

            var17 = var5;
            var19 = false;
            var6 = false;
            var21 = Result.exceptionOrNull-impl(var17);
            if (var21 == null) {
               var33 = var17;
            } else {
               var8 = false;
               var13 = StringsKt.replace$default(result, rule.getReplaceRegex$reader_pro(), rule.getReplacement$reader_pro(), false, 4, (Object)null);
               var33 = var13;
            }

            var10000 = (String)var33;
         }

         vResult = var10000;
         return vResult;
      }
   }

   @NotNull
   public final List<AnalyzeRule.SourceRule> splitSourceRule(@Nullable String ruleStr, boolean allInOne) {
      CharSequence var3 = (CharSequence)ruleStr;
      boolean var4 = false;
      boolean var5 = false;
      if (var3 == null || var3.length() == 0) {
         return CollectionsKt.emptyList();
      } else {
         ArrayList ruleList = new ArrayList();
         AnalyzeRule.Mode mMode = AnalyzeRule.Mode.Default;
         int start = 0;
         if (allInOne && StringsKt.startsWith$default(ruleStr, ":", false, 2, (Object)null)) {
            mMode = AnalyzeRule.Mode.Regex;
            this.isRegex = true;
            start = 1;
         } else if (this.isRegex) {
            mMode = AnalyzeRule.Mode.Regex;
         }

         String tmp = null;

         String $this$trim$iv;
         boolean $i$f$trim;
         int startIndex$iv$iv;
         int endIndex$iv$iv;
         boolean startFound$iv$iv;
         int index$iv$iv;
         String var10000;
         char it;
         boolean var17;
         CharSequence var21;
         boolean $i$f$trim;
         CharSequence $this$trim$iv$iv;
         boolean match$iv$iv;
         for(Matcher jsMatcher = AppPattern.INSTANCE.getJS_PATTERN().matcher((CharSequence)ruleStr); jsMatcher.find(); start = jsMatcher.end()) {
            if (jsMatcher.start() > start) {
               int var9 = jsMatcher.start();
               boolean var10 = false;
               if (ruleStr == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               }

               var10000 = ruleStr.substring(start, var9);
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
                  var17 = false;
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
               var21 = (CharSequence)tmp;
               $i$f$trim = false;
               if (var21.length() > 0) {
                  ruleList.add(new AnalyzeRule.SourceRule(tmp, mMode));
               }
            }

            String var23 = jsMatcher.group(2);
            $this$trim$iv = var23 == null ? jsMatcher.group(1) : var23;
            Intrinsics.checkNotNullExpressionValue($this$trim$iv, "jsMatcher.group(2) ?: jsMatcher.group(1)");
            ruleList.add(new AnalyzeRule.SourceRule($this$trim$iv, AnalyzeRule.Mode.Js));
         }

         if (ruleStr.length() > start) {
            $i$f$trim = false;
            if (ruleStr == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var10000 = ruleStr.substring(start);
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
               var17 = false;
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
            var21 = (CharSequence)tmp;
            $i$f$trim = false;
            if (var21.length() > 0) {
               ruleList.add(new AnalyzeRule.SourceRule(tmp, mMode));
            }
         }

         return (List)ruleList;
      }
   }

   // $FF: synthetic method
   public static List splitSourceRule$default(AnalyzeRule var0, String var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return var0.splitSourceRule(var1, var2);
   }

   @NotNull
   public final String put(@NotNull String key, @NotNull String value) {
      Intrinsics.checkNotNullParameter(key, "key");
      Intrinsics.checkNotNullParameter(value, "value");
      BookChapter var5 = this.chapter;
      Unit var10000;
      if (var5 == null) {
         var10000 = null;
      } else {
         var5.putVariable(key, value);
         var10000 = Unit.INSTANCE;
      }

      Unit var4 = var10000;
      if (var4 == null) {
         BaseBook var6 = this.getBook();
         if (var6 == null) {
            var10000 = null;
         } else {
            var6.putVariable(key, value);
            var10000 = Unit.INSTANCE;
         }
      } else {
         var10000 = var4;
      }

      Unit var3 = var10000;
      if (var3 == null) {
         this.ruleData.putVariable(key, value);
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
         BaseBook var3 = this.getBook();
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
         BaseBook var4 = this.getBook();
         String var10 = var4 == null ? null : var4.getVariable(key);
         if (var10 == null) {
            RuleDataInterface var11 = this.ruleData;
            if (var11 == null) {
               var10000 = "";
            } else {
               String var12 = var11.getVariable(key);
               var10000 = var12 == null ? "" : var12;
            }
         } else {
            var10000 = var10;
         }
      } else {
         var10000 = var2;
      }

      return var10000;
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
      var5 = "cookie";
      CookieStore var8 = new CookieStore(this.getUserNameSpace());
      boolean var7 = false;
      var4.put(var5, var8);
      var4 = (Map)bindings;
      var5 = "cache";
      CacheManager var9 = new CacheManager(this.getUserNameSpace());
      var7 = false;
      var4.put(var5, var9);
      var4 = (Map)bindings;
      var5 = "source";
      BaseSource var10 = this.source;
      var7 = false;
      var4.put(var5, var10);
      var4 = (Map)bindings;
      var5 = "book";
      BaseBook var11 = this.getBook();
      var7 = false;
      var4.put(var5, var11);
      var4 = (Map)bindings;
      var5 = "result";
      var6 = false;
      var4.put(var5, result);
      var4 = (Map)bindings;
      var5 = "baseUrl";
      String var12 = this.baseUrl;
      var7 = false;
      var4.put(var5, var12);
      var4 = (Map)bindings;
      var5 = "chapter";
      BookChapter var13 = this.chapter;
      var7 = false;
      var4.put(var5, var13);
      var4 = (Map)bindings;
      var5 = "title";
      var13 = this.chapter;
      var12 = var13 == null ? null : var13.getTitle();
      var7 = false;
      var4.put(var5, var12);
      var4 = (Map)bindings;
      var5 = "src";
      Object var14 = this.content;
      var7 = false;
      var4.put(var5, var14);
      var4 = (Map)bindings;
      var5 = "nextChapterUrl";
      var12 = this.nextChapterUrl;
      var7 = false;
      var4.put(var5, var12);
      return AppConst.INSTANCE.getSCRIPT_ENGINE().eval(jsStr, (Bindings)bindings);
   }

   // $FF: synthetic method
   public static Object evalJS$default(AnalyzeRule var0, String var1, Object var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
      }

      return var0.evalJS(var1, var2);
   }

   @Nullable
   public String ajax(@NotNull String urlStr) {
      Intrinsics.checkNotNullParameter(urlStr, "urlStr");
      return (String)BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2<CoroutineScope, Continuation<? super String>, Object>((Continuation)null) {
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            // $FF: Couldn't be decompiled
         }

         @NotNull
         public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return (Continuation)(new <anonymous constructor>($completion));
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super String> p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }), 1, (Object)null);
   }

   @Nullable
   public final String toNumChapter(@Nullable String s) {
      if (s == null) {
         return null;
      } else {
         Matcher matcher = titleNumPattern.matcher((CharSequence)s);
         return matcher.find() ? matcher.group(1) + StringUtils.INSTANCE.stringToInt(matcher.group(2)) + matcher.group(3) : s;
      }
   }

   public final void reGetBook() {
      BaseSource var2 = this.source;
      final BookSource bookSource = var2 instanceof BookSource ? (BookSource)var2 : null;
      BaseBook var3 = this.getBook();
      final Book book = var3 instanceof Book ? (Book)var3 : null;
      if (bookSource != null && book != null) {
         BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2<CoroutineScope, Continuation<? super Book>, Object>((Continuation)null) {
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               Object var2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               Object var10000;
               switch(this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  Function2 var10001 = (Function2)(new Function2<CoroutineScope, Continuation<? super Book>, Object>((Continuation)null) {
                     int label;

                     @Nullable
                     public final Object invokeSuspend(@NotNull Object $result) {
                        Object var16 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        Object var10000;
                        WebBook var18;
                        Continuation var10003;
                        switch(this.label) {
                        case 0:
                           ResultKt.throwOnFailure($result);
                           var18 = new WebBook(bookSource, false, (DebugLog)null, AnalyzeRule.this.getUserNameSpace(), 6, (DefaultConstructorMarker)null);
                           String var10001 = book.getName();
                           String var10002 = book.getAuthor();
                           var10003 = (Continuation)this;
                           this.label = 1;
                           var10000 = var18.preciseSearch-0E7RQCE(var10001, var10002, var10003);
                           if (var10000 == var16) {
                              return var16;
                           }
                           break;
                        case 1:
                           ResultKt.throwOnFailure($result);
                           var10000 = ((Result)$result).unbox-impl();
                           break;
                        case 2:
                           ResultKt.throwOnFailure($result);
                           var10000 = $result;
                           return var10000;
                        default:
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        Object var2 = var10000;
                        boolean var3 = false;
                        ResultKt.throwOnFailure(var2);
                        Book var17 = book;
                        boolean var4 = false;
                        boolean var5 = false;
                        Book it = (Book)var2;
                        int var7 = false;
                        var17.setBookUrl(it.getBookUrl());
                        Map $this$forEach$iv = (Map)it.getVariableMap();
                        int $i$f$forEach = false;
                        boolean var11 = false;
                        Iterator var12 = $this$forEach$iv.entrySet().iterator();

                        while(var12.hasNext()) {
                           Entry element$iv = (Entry)var12.next();
                           int var15 = false;
                           var17.putVariable((String)element$iv.getKey(), (String)element$iv.getValue());
                        }

                        var18 = new WebBook(bookSource, false, (DebugLog)null, AnalyzeRule.this.getUserNameSpace(), 6, (DefaultConstructorMarker)null);
                        Book var19 = book;
                        var10003 = (Continuation)this;
                        this.label = 2;
                        var10000 = var18.getBookInfo(var19, false, var10003);
                        if (var10000 == var16) {
                           return var16;
                        } else {
                           return var10000;
                        }
                     }

                     @NotNull
                     public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
                        return (Continuation)(new <anonymous constructor>($completion));
                     }

                     @Nullable
                     public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Book> p2) {
                        return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                     }
                  });
                  Continuation var10002 = (Continuation)this;
                  this.label = 1;
                  var10000 = TimeoutKt.withTimeout(1800000L, var10001, var10002);
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
            public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Book> p2) {
               return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
            }
         }), 1, (Object)null);
      }
   }

   public final void refreshBookUrl() {
      BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2<CoroutineScope, Continuation<? super Unit>, Object>((Continuation)null) {
         Object L$0;
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            Object var13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            Book book;
            Object var10000;
            switch(this.label) {
            case 0:
               ResultKt.throwOnFailure($result);
               BaseSource var14 = AnalyzeRule.this.source;
               BookSource bookSource = var14 instanceof BookSource ? (BookSource)var14 : null;
               BaseBook var4 = AnalyzeRule.this.getBook();
               book = var4 instanceof Book ? (Book)var4 : null;
               if (bookSource != null && book != null) {
                  WebBook var16 = new WebBook(bookSource, false, (DebugLog)null, AnalyzeRule.this.getUserNameSpace(), 6, (DefaultConstructorMarker)null);
                  String var10001 = book.getName();
                  Continuation var10003 = (Continuation)this;
                  this.L$0 = book;
                  this.label = 1;
                  var10000 = WebBook.searchBook$default(var16, var10001, (Integer)null, var10003, 2, (Object)null);
                  if (var10000 == var13) {
                     return var13;
                  }
                  break;
               }

               return Unit.INSTANCE;
            case 1:
               book = (Book)this.L$0;
               ResultKt.throwOnFailure($result);
               var10000 = $result;
               break;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            List books = (List)var10000;
            Iterable $this$forEach$iv = (Iterable)books;
            int $i$f$forEach = false;
            Iterator var7 = $this$forEach$iv.iterator();

            SearchBook it;
            do {
               if (!var7.hasNext()) {
                  return Unit.INSTANCE;
               }

               Object element$iv = var7.next();
               it = (SearchBook)element$iv;
               int var10 = false;
            } while(!Intrinsics.areEqual(it.getName(), book.getName()) || !Intrinsics.areEqual(it.getAuthor(), book.getAuthor()));

            book.setBookUrl(it.getBookUrl());
            CharSequence var11 = (CharSequence)it.getTocUrl();
            boolean var12 = false;
            if (!StringsKt.isBlank(var11)) {
               book.setTocUrl(it.getTocUrl());
            }

            return Unit.INSTANCE;
         }

         @NotNull
         public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return (Continuation)(new <anonymous constructor>($completion));
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }), 1, (Object)null);
   }

   public final void refreshTocUrl() {
      BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2<CoroutineScope, Continuation<? super Unit>, Object>((Continuation)null) {
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch(this.label) {
            case 0:
               ResultKt.throwOnFailure($result);
               BaseSource var3 = AnalyzeRule.this.source;
               BookSource bookSource = var3 instanceof BookSource ? (BookSource)var3 : null;
               BaseBook var4 = AnalyzeRule.this.getBook();
               Book book = var4 instanceof Book ? (Book)var4 : null;
               if (bookSource == null || book == null) {
                  return Unit.INSTANCE;
               }

               WebBook var10000 = new WebBook(bookSource, false, (DebugLog)null, AnalyzeRule.this.getUserNameSpace(), 6, (DefaultConstructorMarker)null);
               Continuation var10003 = (Continuation)this;
               this.label = 1;
               if (WebBook.getBookInfo$default(var10000, (Book)book, false, var10003, 2, (Object)null) == var5) {
                  return var5;
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

         @NotNull
         public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return (Continuation)(new <anonymous constructor>($completion));
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }), 1, (Object)null);
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
   public Response get(@NotNull String urlStr, @NotNull Map<String, String> headers) {
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
   public Response head(@NotNull String urlStr, @NotNull Map<String, String> headers) {
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
   public Response post(@NotNull String urlStr, @NotNull String body, @NotNull Map<String, String> headers) {
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
   public final AnalyzeRule setContent(@Nullable Object content) {
      return setContent$default(this, content, (String)null, 2, (Object)null);
   }

   @JvmOverloads
   @Nullable
   public final List<String> getStringList(@Nullable String rule, @Nullable Object mContent) {
      return getStringList$default(this, (String)rule, mContent, false, 4, (Object)null);
   }

   @JvmOverloads
   @Nullable
   public final List<String> getStringList(@Nullable String rule) {
      return getStringList$default(this, (String)rule, (Object)null, false, 6, (Object)null);
   }

   @JvmOverloads
   @Nullable
   public final List<String> getStringList(@NotNull List<AnalyzeRule.SourceRule> ruleList, @Nullable Object mContent) {
      Intrinsics.checkNotNullParameter(ruleList, "ruleList");
      return getStringList$default(this, (List)ruleList, mContent, false, 4, (Object)null);
   }

   @JvmOverloads
   @Nullable
   public final List<String> getStringList(@NotNull List<AnalyzeRule.SourceRule> ruleList) {
      Intrinsics.checkNotNullParameter(ruleList, "ruleList");
      return getStringList$default(this, (List)ruleList, (Object)null, false, 6, (Object)null);
   }

   @JvmOverloads
   @NotNull
   public final String getString(@Nullable String ruleStr, @Nullable Object mContent) {
      return getString$default(this, (String)ruleStr, mContent, false, 4, (Object)null);
   }

   @JvmOverloads
   @NotNull
   public final String getString(@Nullable String ruleStr) {
      return getString$default(this, (String)ruleStr, (Object)null, false, 6, (Object)null);
   }

   @JvmOverloads
   @NotNull
   public final String getString(@NotNull List<AnalyzeRule.SourceRule> ruleList, @Nullable Object mContent) {
      Intrinsics.checkNotNullParameter(ruleList, "ruleList");
      return getString$default(this, (List)ruleList, mContent, false, 4, (Object)null);
   }

   @JvmOverloads
   @NotNull
   public final String getString(@NotNull List<AnalyzeRule.SourceRule> ruleList) {
      Intrinsics.checkNotNullParameter(ruleList, "ruleList");
      return getString$default(this, (List)ruleList, (Object)null, false, 6, (Object)null);
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010(\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001J\u0010\u0010,\u001a\u00020*2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR0\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0010j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0011X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\u001a\u0010\"\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001c\"\u0004\b$\u0010\u001eR\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\b0&X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"},
      d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeRule$SourceRule;", "", "ruleStr", "", "mode", "Lio/legado/app/model/analyzeRule/AnalyzeRule$Mode;", "(Lio/legado/app/model/analyzeRule/AnalyzeRule;Ljava/lang/String;Lio/legado/app/model/analyzeRule/AnalyzeRule$Mode;)V", "defaultRuleType", "", "getRuleType", "jsRuleType", "getMode$reader_pro", "()Lio/legado/app/model/analyzeRule/AnalyzeRule$Mode;", "setMode$reader_pro", "(Lio/legado/app/model/analyzeRule/AnalyzeRule$Mode;)V", "putMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getPutMap$reader_pro", "()Ljava/util/HashMap;", "replaceFirst", "", "getReplaceFirst$reader_pro", "()Z", "setReplaceFirst$reader_pro", "(Z)V", "replaceRegex", "getReplaceRegex$reader_pro", "()Ljava/lang/String;", "setReplaceRegex$reader_pro", "(Ljava/lang/String;)V", "replacement", "getReplacement$reader_pro", "setReplacement$reader_pro", "rule", "getRule$reader_pro", "setRule$reader_pro", "ruleParam", "Ljava/util/ArrayList;", "ruleType", "isRule", "makeUpRule", "", "result", "splitRegex", "reader-pro"}
   )
   public final class SourceRule {
      @NotNull
      private AnalyzeRule.Mode mode;
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

      public SourceRule(@NotNull String ruleStr, @NotNull AnalyzeRule.Mode mode) {
         Intrinsics.checkNotNullParameter(AnalyzeRule.this, "this$0");
         Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
         Intrinsics.checkNotNullParameter(mode, "mode");
         super();
         this.mode = mode;
         this.replaceRegex = "";
         this.replacement = "";
         this.putMap = new HashMap();
         this.ruleParam = new ArrayList();
         this.ruleType = new ArrayList();
         this.getRuleType = -2;
         this.jsRuleType = -1;
         String var10001;
         if (this.mode != AnalyzeRule.Mode.Js && this.mode != AnalyzeRule.Mode.Regex) {
            if (StringsKt.startsWith(ruleStr, "@CSS:", true)) {
               this.mode = AnalyzeRule.Mode.Default;
               var10001 = ruleStr;
            } else {
               byte var5;
               boolean var6;
               if (StringsKt.startsWith$default(ruleStr, "@@", false, 2, (Object)null)) {
                  this.mode = AnalyzeRule.Mode.Default;
                  var5 = 2;
                  var6 = false;
                  var10001 = ruleStr.substring(var5);
                  Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).substring(startIndex)");
               } else if (StringsKt.startsWith(ruleStr, "@XPath:", true)) {
                  this.mode = AnalyzeRule.Mode.XPath;
                  var5 = 7;
                  var6 = false;
                  var10001 = ruleStr.substring(var5);
                  Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).substring(startIndex)");
               } else if (StringsKt.startsWith(ruleStr, "@Json:", true)) {
                  this.mode = AnalyzeRule.Mode.Json;
                  var5 = 6;
                  var6 = false;
                  var10001 = ruleStr.substring(var5);
                  Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).substring(startIndex)");
               } else if (!AnalyzeRule.this.isJSON && !StringsKt.startsWith$default(ruleStr, "$.", false, 2, (Object)null) && !StringsKt.startsWith$default(ruleStr, "$[", false, 2, (Object)null)) {
                  if (StringsKt.startsWith$default(ruleStr, "/", false, 2, (Object)null)) {
                     this.mode = AnalyzeRule.Mode.XPath;
                     var10001 = ruleStr;
                  } else {
                     var10001 = ruleStr;
                  }
               } else {
                  this.mode = AnalyzeRule.Mode.Json;
                  var10001 = ruleStr;
               }
            }
         } else {
            var10001 = ruleStr;
         }

         this.rule = var10001;
         this.rule = AnalyzeRule.this.splitPutRule(this.rule, this.putMap);
         int start = 0;
         String tmp = null;
         Matcher evalMatcher = AnalyzeRule.evalPattern.matcher((CharSequence)this.rule);
         String var7;
         String var10000;
         if (evalMatcher.find()) {
            var7 = this.rule;
            int var8 = evalMatcher.start();
            boolean var9 = false;
            if (var7 == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var10000 = var7.substring(start, var8);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            tmp = var10000;
            if (this.mode != AnalyzeRule.Mode.Js && this.mode != AnalyzeRule.Mode.Regex && (evalMatcher.start() == 0 || !StringsKt.contains$default((CharSequence)tmp, (CharSequence)"##", false, 2, (Object)null))) {
               this.mode = AnalyzeRule.Mode.Regex;
            }

            do {
               if (evalMatcher.start() > start) {
                  var7 = this.rule;
                  var8 = evalMatcher.start();
                  var9 = false;
                  if (var7 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  }

                  var10000 = var7.substring(start, var8);
                  Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                  tmp = var10000;
                  this.splitRegex(tmp);
               }

               var7 = evalMatcher.group();
               Intrinsics.checkNotNullExpressionValue(var7, "evalMatcher.group()");
               boolean var10;
               byte var13;
               int var15;
               ArrayList var16;
               if (StringsKt.startsWith(var7, "@get:", true)) {
                  this.ruleType.add(this.getRuleType);
                  var16 = this.ruleParam;
                  var13 = 6;
                  var15 = StringsKt.getLastIndex((CharSequence)var7);
                  var10 = false;
                  var10001 = var7.substring(var13, var15);
                  Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                  var16.add(var10001);
               } else if (StringsKt.startsWith$default(var7, "{{", false, 2, (Object)null)) {
                  this.ruleType.add(this.jsRuleType);
                  var16 = this.ruleParam;
                  var13 = 2;
                  var15 = var7.length() - 2;
                  var10 = false;
                  var10001 = var7.substring(var13, var15);
                  Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                  var16.add(var10001);
               } else {
                  this.splitRegex(var7);
               }

               start = evalMatcher.end();
            } while(evalMatcher.find());
         }

         if (this.rule.length() > start) {
            var7 = this.rule;
            boolean var14 = false;
            if (var7 == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var10000 = var7.substring(start);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
            tmp = var10000;
            this.splitRegex(tmp);
         }

      }

      // $FF: synthetic method
      public SourceRule(String var2, AnalyzeRule.Mode var3, int var4, DefaultConstructorMarker var5) {
         if ((var4 & 2) != 0) {
            var3 = AnalyzeRule.Mode.Default;
         }

         this(var2, var3);
      }

      @NotNull
      public final AnalyzeRule.Mode getMode$reader_pro() {
         return this.mode;
      }

      public final void setMode$reader_pro(@NotNull AnalyzeRule.Mode <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.mode = var1;
      }

      @NotNull
      public final String getRule$reader_pro() {
         return this.rule;
      }

      public final void setRule$reader_pro(@NotNull String <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.rule = var1;
      }

      @NotNull
      public final String getReplaceRegex$reader_pro() {
         return this.replaceRegex;
      }

      public final void setReplaceRegex$reader_pro(@NotNull String <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.replaceRegex = var1;
      }

      @NotNull
      public final String getReplacement$reader_pro() {
         return this.replacement;
      }

      public final void setReplacement$reader_pro(@NotNull String <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.replacement = var1;
      }

      public final boolean getReplaceFirst$reader_pro() {
         return this.replaceFirst;
      }

      public final void setReplaceFirst$reader_pro(boolean <set-?>) {
         this.replaceFirst = var1;
      }

      @NotNull
      public final HashMap<String, String> getPutMap$reader_pro() {
         return this.putMap;
      }

      private final void splitRegex(String ruleStr) {
         int start = 0;
         String tmp = null;
         CharSequence var10000 = (CharSequence)ruleStr;
         String[] var5 = new String[]{"##"};
         List ruleStrArray = StringsKt.split$default(var10000, var5, false, 0, 6, (Object)null);
         Matcher regexMatcher = AnalyzeRule.regexPattern.matcher((CharSequence)ruleStrArray.get(0));
         boolean var11;
         String var12;
         if (regexMatcher.find()) {
            if (this.mode != AnalyzeRule.Mode.Js && this.mode != AnalyzeRule.Mode.Regex) {
               this.mode = AnalyzeRule.Mode.Regex;
            }

            do {
               boolean var8;
               if (regexMatcher.start() > start) {
                  int var7 = regexMatcher.start();
                  var8 = false;
                  if (ruleStr == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  }

                  var12 = ruleStr.substring(start, var7);
                  Intrinsics.checkNotNullExpressionValue(var12, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                  tmp = var12;
                  this.ruleType.add(this.defaultRuleType);
                  this.ruleParam.add(tmp);
               }

               String var6 = regexMatcher.group();
               Intrinsics.checkNotNullExpressionValue(var6, "regexMatcher.group()");
               tmp = var6;
               ArrayList var13 = this.ruleType;
               byte var10 = 1;
               var8 = false;
               String var10001 = var6.substring(var10);
               Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).substring(startIndex)");
               var6 = var10001;
               var11 = false;
               var13.add(Integer.parseInt(var6));
               this.ruleParam.add(tmp);
               start = regexMatcher.end();
            } while(regexMatcher.find());
         }

         if (ruleStr.length() > start) {
            var11 = false;
            if (ruleStr == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var12 = ruleStr.substring(start);
            Intrinsics.checkNotNullExpressionValue(var12, "(this as java.lang.String).substring(startIndex)");
            tmp = var12;
            this.ruleType.add(this.defaultRuleType);
            this.ruleParam.add(tmp);
         }

      }

      public final void makeUpRule(@Nullable Object result) {
         StringBuilder infoVal = new StringBuilder();
         Collection var3 = (Collection)this.ruleParam;
         boolean var4 = false;
         String var21;
         if (!var3.isEmpty()) {
            int index = this.ruleParam.size();

            label90:
            while(true) {
               while(true) {
                  int regType = index;
                  index += -1;
                  if (regType <= 0) {
                     var21 = infoVal.toString();
                     Intrinsics.checkNotNullExpressionValue(var21, "infoVal.toString()");
                     this.rule = var21;
                     break label90;
                  }

                  Object jsEval = this.ruleType.get(index);
                  Intrinsics.checkNotNullExpressionValue(jsEval, "ruleType[index]");
                  regType = ((Number)jsEval).intValue();
                  boolean var9;
                  if (regType > this.defaultRuleType) {
                     List var29 = result instanceof List ? (List)result : null;
                     Unit var32;
                     if (var29 == null) {
                        var32 = null;
                     } else {
                        boolean var31 = false;
                        var9 = false;
                        int var11 = false;
                        if (var29.size() > regType) {
                           String var12 = (String)var29.get(regType);
                           if (var12 != null) {
                              boolean var14 = false;
                              boolean var15 = false;
                              int var17 = false;
                              infoVal.insert(0, var12);
                           }
                        }

                        var32 = Unit.INSTANCE;
                     }

                     Unit var26 = var32;
                     if (var26 == null) {
                        infoVal.insert(0, (String)this.ruleParam.get(index));
                     }
                  } else if (regType == this.jsRuleType) {
                     jsEval = this.ruleParam.get(index);
                     Intrinsics.checkNotNullExpressionValue(jsEval, "ruleParam[index]");
                     Object var6;
                     AnalyzeRule var10000;
                     if (this.isRule((String)jsEval)) {
                        var10000 = AnalyzeRule.this;
                        AnalyzeRule.SourceRule[] var23 = new AnalyzeRule.SourceRule[1];
                        AnalyzeRule var10005 = AnalyzeRule.this;
                        var6 = this.ruleParam.get(index);
                        Intrinsics.checkNotNullExpressionValue(var6, "ruleParam[index]");
                        var23[0] = var10005.new SourceRule((String)var6, (AnalyzeRule.Mode)null, 2, (DefaultConstructorMarker)null);
                        String var25 = AnalyzeRule.getString$default(var10000, (List)((List)CollectionsKt.arrayListOf(var23)), (Object)null, false, 6, (Object)null);
                        boolean var27 = false;
                        boolean var30 = false;
                        var9 = false;
                        infoVal.insert(0, var25);
                     } else {
                        var10000 = AnalyzeRule.this;
                        var6 = this.ruleParam.get(index);
                        Intrinsics.checkNotNullExpressionValue(var6, "ruleParam[index]");
                        jsEval = var10000.evalJS((String)var6, result);
                        if (jsEval != null) {
                           if (jsEval instanceof String) {
                              infoVal.insert(0, (String)jsEval);
                           } else if (jsEval instanceof Double && ((Number)jsEval).doubleValue() % 1.0D == 0.0D) {
                              StringCompanionObject var24 = StringCompanionObject.INSTANCE;
                              String var7 = "%.0f";
                              Object[] var8 = new Object[]{jsEval};
                              var9 = false;
                              String var34 = String.format(var7, Arrays.copyOf(var8, var8.length));
                              Intrinsics.checkNotNullExpressionValue(var34, "java.lang.String.format(format, *args)");
                              infoVal.insert(0, var34);
                           } else {
                              infoVal.insert(0, jsEval.toString());
                           }
                        }
                     }
                  } else if (regType == this.getRuleType) {
                     AnalyzeRule var10002 = AnalyzeRule.this;
                     jsEval = this.ruleParam.get(index);
                     Intrinsics.checkNotNullExpressionValue(jsEval, "ruleParam[index]");
                     infoVal.insert(0, var10002.get((String)jsEval));
                  } else {
                     infoVal.insert(0, (String)this.ruleParam.get(index));
                  }
               }
            }
         }

         CharSequence var33 = (CharSequence)this.rule;
         String[] var22 = new String[]{"##"};
         List ruleStrS = StringsKt.split$default(var33, var22, false, 0, 6, (Object)null);
         var21 = (String)ruleStrS.get(0);
         boolean var28 = false;
         if (var21 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
         } else {
            this.rule = StringsKt.trim((CharSequence)var21).toString();
            if (ruleStrS.size() > 1) {
               this.replaceRegex = (String)ruleStrS.get(1);
            }

            if (ruleStrS.size() > 2) {
               this.replacement = (String)ruleStrS.get(2);
            }

            if (ruleStrS.size() > 3) {
               this.replaceFirst = true;
            }

         }
      }

      private final boolean isRule(String ruleStr) {
         return StringsKt.startsWith$default((CharSequence)ruleStr, '@', false, 2, (Object)null) || StringsKt.startsWith$default(ruleStr, "$.", false, 2, (Object)null) || StringsKt.startsWith$default(ruleStr, "$[", false, 2, (Object)null) || StringsKt.startsWith$default(ruleStr, "//", false, 2, (Object)null);
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"},
      d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeRule$Mode;", "", "(Ljava/lang/String;I)V", "XPath", "Json", "Default", "Js", "Regex", "reader-pro"}
   )
   public static enum Mode {
      XPath,
      Json,
      Default,
      Js,
      Regex;

      // $FF: synthetic method
      private static final AnalyzeRule.Mode[] $values() {
         AnalyzeRule.Mode[] var0 = new AnalyzeRule.Mode[]{XPath, Json, Default, Js, Regex};
         return var0;
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"},
      d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeRule$Companion;", "", "()V", "evalPattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "putPattern", "regexPattern", "titleNumPattern", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
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
         int[] var0 = new int[AnalyzeRule.Mode.values().length];
         var0[AnalyzeRule.Mode.Js.ordinal()] = 1;
         var0[AnalyzeRule.Mode.Json.ordinal()] = 2;
         var0[AnalyzeRule.Mode.XPath.ordinal()] = 3;
         var0[AnalyzeRule.Mode.Default.ordinal()] = 4;
         var0[AnalyzeRule.Mode.Regex.ordinal()] = 5;
         $EnumSwitchMapping$0 = var0;
      }
   }
}
