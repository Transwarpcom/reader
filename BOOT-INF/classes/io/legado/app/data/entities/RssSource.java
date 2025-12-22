package io.legado.app.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.ReadContext;
import com.script.SimpleBindings;
import io.legado.app.help.http.StrResponse;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.QueryTTF;
import io.legado.app.utils.JsonExtensionsKt;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Connection.Response;

@JsonIgnoreProperties({"headerMap", "source", "_userNameSpace", "userNameSpace", "loginHeader", "loginHeaderMap", "loginInfo", "loginInfoMap"})
@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b[\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u0099\u00012\u00020\u0001:\u0002\u0099\u0001BÃ\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\t\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u001e\u001a\u00020\t\u0012\b\b\u0002\u0010\u001f\u001a\u00020\t\u0012\b\b\u0002\u0010 \u001a\u00020\u0014¢\u0006\u0002\u0010!J\t\u0010f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010l\u001a\u00020\tHÆ\u0003J\t\u0010m\u001a\u00020\u0014HÆ\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010q\u001a\u00020\u0003HÆ\u0003J\u000b\u0010r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010u\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010v\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010w\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010x\u001a\u00020\tHÆ\u0003J\t\u0010y\u001a\u00020\tHÆ\u0003J\t\u0010z\u001a\u00020\u0014HÆ\u0003J\t\u0010{\u001a\u00020\u0003HÆ\u0003J\u000b\u0010|\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010}\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010~\u001a\u00020\tHÆ\u0003J\u000b\u0010\u007f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0080\u0001\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u00106J\f\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003JÎ\u0002\u0010\u0082\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001e\u001a\u00020\t2\b\b\u0002\u0010\u001f\u001a\u00020\t2\b\b\u0002\u0010 \u001a\u00020\u0014HÆ\u0001¢\u0006\u0003\u0010\u0083\u0001J\u0010\u0010\u0084\u0001\u001a\u00020\t2\u0007\u0010\u0085\u0001\u001a\u00020\u0000J\u001f\u0010\u0084\u0001\u001a\u00020\t2\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00032\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0003H\u0002J\u0016\u0010\u0088\u0001\u001a\u00020\t2\n\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u008a\u0001H\u0096\u0002J\t\u0010\u008b\u0001\u001a\u00020\u0003H\u0016J\u000b\u0010\u008c\u0001\u001a\u0004\u0018\u00010.H\u0016J\t\u0010\u008d\u0001\u001a\u00020\u0003H\u0016J\t\u0010\u008e\u0001\u001a\u00020\u0003H\u0016J\t\u0010\u008f\u0001\u001a\u00020\u0014H\u0016J\u0013\u0010\u0090\u0001\u001a\u00030\u0091\u00012\t\u0010\u0092\u0001\u001a\u0004\u0018\u00010.J\u0011\u0010\u0093\u0001\u001a\u00030\u0091\u00012\u0007\u0010\u0094\u0001\u001a\u00020\u0003J\u001b\u0010\u0095\u0001\u001a\u0016\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0097\u00010\u0096\u0001J\n\u0010\u0098\u0001\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\"\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010 \u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00100\"\u0004\b4\u00102R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u0010\n\u0002\u00109\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010(\"\u0004\b;\u0010*R\u001a\u0010\u001f\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u00100\"\u0004\b=\u00102R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010(\"\u0004\b?\u0010*R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010(\"\u0004\bA\u0010*R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010(\"\u0004\bC\u0010*R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010(\"\u0004\bE\u0010*R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010(\"\u0004\bG\u0010*R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010(\"\u0004\bI\u0010*R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010(\"\u0004\bK\u0010*R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010(\"\u0004\bM\u0010*R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010(\"\u0004\bO\u0010*R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010(\"\u0004\bQ\u0010*R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010(\"\u0004\bS\u0010*R\u001a\u0010\u0012\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u00100\"\u0004\bU\u00102R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010(\"\u0004\bW\u0010*R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010(\"\u0004\bY\u0010*R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010(\"\u0004\b[\u0010*R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010(\"\u0004\b]\u0010*R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010(\"\u0004\b_\u0010*R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010(\"\u0004\ba\u0010*R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010(\"\u0004\bc\u0010*R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010(\"\u0004\be\u0010*¨\u0006\u009a\u0001"},
   d2 = {"Lio/legado/app/data/entities/RssSource;", "Lio/legado/app/data/entities/BaseSource;", "sourceUrl", "", "sourceName", "sourceIcon", "sourceGroup", "sourceComment", "enabled", "", "variableComment", "enabledCookieJar", "concurrentRate", "header", "loginUrl", "loginUi", "loginCheckJs", "sortUrl", "singleUrl", "articleStyle", "", "ruleArticles", "ruleNextPage", "ruleTitle", "rulePubDate", "ruleDescription", "ruleImage", "ruleLink", "ruleContent", "style", "enableJs", "loadWithBaseUrl", "customOrder", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZI)V", "_userNameSpace", "getArticleStyle", "()I", "setArticleStyle", "(I)V", "getConcurrentRate", "()Ljava/lang/String;", "setConcurrentRate", "(Ljava/lang/String;)V", "getCustomOrder", "setCustomOrder", "debugLog", "Lio/legado/app/model/DebugLog;", "getEnableJs", "()Z", "setEnableJs", "(Z)V", "getEnabled", "setEnabled", "getEnabledCookieJar", "()Ljava/lang/Boolean;", "setEnabledCookieJar", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getHeader", "setHeader", "getLoadWithBaseUrl", "setLoadWithBaseUrl", "getLoginCheckJs", "setLoginCheckJs", "getLoginUi", "setLoginUi", "getLoginUrl", "setLoginUrl", "getRuleArticles", "setRuleArticles", "getRuleContent", "setRuleContent", "getRuleDescription", "setRuleDescription", "getRuleImage", "setRuleImage", "getRuleLink", "setRuleLink", "getRuleNextPage", "setRuleNextPage", "getRulePubDate", "setRulePubDate", "getRuleTitle", "setRuleTitle", "getSingleUrl", "setSingleUrl", "getSortUrl", "setSortUrl", "getSourceComment", "setSourceComment", "getSourceGroup", "setSourceGroup", "getSourceIcon", "setSourceIcon", "getSourceName", "setSourceName", "getSourceUrl", "setSourceUrl", "getStyle", "setStyle", "getVariableComment", "setVariableComment", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZI)Lio/legado/app/data/entities/RssSource;", "equal", "source", "a", "b", "equals", "other", "", "getKey", "getLogger", "getTag", "getUserNameSpace", "hashCode", "setLogger", "", "logger", "setUserNameSpace", "nameSpace", "sortUrls", "", "Lkotlin/Pair;", "toString", "Companion", "reader-pro"}
)
public final class RssSource implements BaseSource {
   @NotNull
   public static final RssSource.Companion Companion = new RssSource.Companion((DefaultConstructorMarker)null);
   @NotNull
   private String sourceUrl;
   @NotNull
   private String sourceName;
   @NotNull
   private String sourceIcon;
   @Nullable
   private String sourceGroup;
   @Nullable
   private String sourceComment;
   private boolean enabled;
   @Nullable
   private String variableComment;
   @Nullable
   private Boolean enabledCookieJar;
   @Nullable
   private String concurrentRate;
   @Nullable
   private String header;
   @Nullable
   private String loginUrl;
   @Nullable
   private String loginUi;
   @Nullable
   private String loginCheckJs;
   @Nullable
   private String sortUrl;
   private boolean singleUrl;
   private int articleStyle;
   @Nullable
   private String ruleArticles;
   @Nullable
   private String ruleNextPage;
   @Nullable
   private String ruleTitle;
   @Nullable
   private String rulePubDate;
   @Nullable
   private String ruleDescription;
   @Nullable
   private String ruleImage;
   @Nullable
   private String ruleLink;
   @Nullable
   private String ruleContent;
   @Nullable
   private String style;
   private boolean enableJs;
   private boolean loadWithBaseUrl;
   private int customOrder;
   @NotNull
   private transient String _userNameSpace;
   @Nullable
   private transient DebugLog debugLog;

   public RssSource(@NotNull String sourceUrl, @NotNull String sourceName, @NotNull String sourceIcon, @Nullable String sourceGroup, @Nullable String sourceComment, boolean enabled, @Nullable String variableComment, @Nullable Boolean enabledCookieJar, @Nullable String concurrentRate, @Nullable String header, @Nullable String loginUrl, @Nullable String loginUi, @Nullable String loginCheckJs, @Nullable String sortUrl, boolean singleUrl, int articleStyle, @Nullable String ruleArticles, @Nullable String ruleNextPage, @Nullable String ruleTitle, @Nullable String rulePubDate, @Nullable String ruleDescription, @Nullable String ruleImage, @Nullable String ruleLink, @Nullable String ruleContent, @Nullable String style, boolean enableJs, boolean loadWithBaseUrl, int customOrder) {
      Intrinsics.checkNotNullParameter(sourceUrl, "sourceUrl");
      Intrinsics.checkNotNullParameter(sourceName, "sourceName");
      Intrinsics.checkNotNullParameter(sourceIcon, "sourceIcon");
      super();
      this.sourceUrl = sourceUrl;
      this.sourceName = sourceName;
      this.sourceIcon = sourceIcon;
      this.sourceGroup = sourceGroup;
      this.sourceComment = sourceComment;
      this.enabled = enabled;
      this.variableComment = variableComment;
      this.enabledCookieJar = enabledCookieJar;
      this.concurrentRate = concurrentRate;
      this.header = header;
      this.loginUrl = loginUrl;
      this.loginUi = loginUi;
      this.loginCheckJs = loginCheckJs;
      this.sortUrl = sortUrl;
      this.singleUrl = singleUrl;
      this.articleStyle = articleStyle;
      this.ruleArticles = ruleArticles;
      this.ruleNextPage = ruleNextPage;
      this.ruleTitle = ruleTitle;
      this.rulePubDate = rulePubDate;
      this.ruleDescription = ruleDescription;
      this.ruleImage = ruleImage;
      this.ruleLink = ruleLink;
      this.ruleContent = ruleContent;
      this.style = style;
      this.enableJs = enableJs;
      this.loadWithBaseUrl = loadWithBaseUrl;
      this.customOrder = customOrder;
      this._userNameSpace = "";
   }

   // $FF: synthetic method
   public RssSource(String var1, String var2, String var3, String var4, String var5, boolean var6, String var7, Boolean var8, String var9, String var10, String var11, String var12, String var13, String var14, boolean var15, int var16, String var17, String var18, String var19, String var20, String var21, String var22, String var23, String var24, String var25, boolean var26, boolean var27, int var28, int var29, DefaultConstructorMarker var30) {
      if ((var29 & 1) != 0) {
         var1 = "";
      }

      if ((var29 & 2) != 0) {
         var2 = "";
      }

      if ((var29 & 4) != 0) {
         var3 = "";
      }

      if ((var29 & 8) != 0) {
         var4 = null;
      }

      if ((var29 & 16) != 0) {
         var5 = null;
      }

      if ((var29 & 32) != 0) {
         var6 = true;
      }

      if ((var29 & 64) != 0) {
         var7 = null;
      }

      if ((var29 & 128) != 0) {
         var8 = false;
      }

      if ((var29 & 256) != 0) {
         var9 = null;
      }

      if ((var29 & 512) != 0) {
         var10 = null;
      }

      if ((var29 & 1024) != 0) {
         var11 = null;
      }

      if ((var29 & 2048) != 0) {
         var12 = null;
      }

      if ((var29 & 4096) != 0) {
         var13 = null;
      }

      if ((var29 & 8192) != 0) {
         var14 = null;
      }

      if ((var29 & 16384) != 0) {
         var15 = false;
      }

      if ((var29 & '耀') != 0) {
         var16 = 0;
      }

      if ((var29 & 65536) != 0) {
         var17 = null;
      }

      if ((var29 & 131072) != 0) {
         var18 = null;
      }

      if ((var29 & 262144) != 0) {
         var19 = null;
      }

      if ((var29 & 524288) != 0) {
         var20 = null;
      }

      if ((var29 & 1048576) != 0) {
         var21 = null;
      }

      if ((var29 & 2097152) != 0) {
         var22 = null;
      }

      if ((var29 & 4194304) != 0) {
         var23 = null;
      }

      if ((var29 & 8388608) != 0) {
         var24 = null;
      }

      if ((var29 & 16777216) != 0) {
         var25 = null;
      }

      if ((var29 & 33554432) != 0) {
         var26 = true;
      }

      if ((var29 & 67108864) != 0) {
         var27 = true;
      }

      if ((var29 & 134217728) != 0) {
         var28 = 0;
      }

      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24, var25, var26, var27, var28);
   }

   @NotNull
   public final String getSourceUrl() {
      return this.sourceUrl;
   }

   public final void setSourceUrl(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.sourceUrl = var1;
   }

   @NotNull
   public final String getSourceName() {
      return this.sourceName;
   }

   public final void setSourceName(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.sourceName = var1;
   }

   @NotNull
   public final String getSourceIcon() {
      return this.sourceIcon;
   }

   public final void setSourceIcon(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.sourceIcon = var1;
   }

   @Nullable
   public final String getSourceGroup() {
      return this.sourceGroup;
   }

   public final void setSourceGroup(@Nullable String <set-?>) {
      this.sourceGroup = var1;
   }

   @Nullable
   public final String getSourceComment() {
      return this.sourceComment;
   }

   public final void setSourceComment(@Nullable String <set-?>) {
      this.sourceComment = var1;
   }

   public final boolean getEnabled() {
      return this.enabled;
   }

   public final void setEnabled(boolean <set-?>) {
      this.enabled = var1;
   }

   @Nullable
   public final String getVariableComment() {
      return this.variableComment;
   }

   public final void setVariableComment(@Nullable String <set-?>) {
      this.variableComment = var1;
   }

   @Nullable
   public Boolean getEnabledCookieJar() {
      return this.enabledCookieJar;
   }

   public void setEnabledCookieJar(@Nullable Boolean <set-?>) {
      this.enabledCookieJar = var1;
   }

   @Nullable
   public String getConcurrentRate() {
      return this.concurrentRate;
   }

   public void setConcurrentRate(@Nullable String <set-?>) {
      this.concurrentRate = var1;
   }

   @Nullable
   public String getHeader() {
      return this.header;
   }

   public void setHeader(@Nullable String <set-?>) {
      this.header = var1;
   }

   @Nullable
   public String getLoginUrl() {
      return this.loginUrl;
   }

   public void setLoginUrl(@Nullable String <set-?>) {
      this.loginUrl = var1;
   }

   @Nullable
   public String getLoginUi() {
      return this.loginUi;
   }

   public void setLoginUi(@Nullable String <set-?>) {
      this.loginUi = var1;
   }

   @Nullable
   public final String getLoginCheckJs() {
      return this.loginCheckJs;
   }

   public final void setLoginCheckJs(@Nullable String <set-?>) {
      this.loginCheckJs = var1;
   }

   @Nullable
   public final String getSortUrl() {
      return this.sortUrl;
   }

   public final void setSortUrl(@Nullable String <set-?>) {
      this.sortUrl = var1;
   }

   public final boolean getSingleUrl() {
      return this.singleUrl;
   }

   public final void setSingleUrl(boolean <set-?>) {
      this.singleUrl = var1;
   }

   public final int getArticleStyle() {
      return this.articleStyle;
   }

   public final void setArticleStyle(int <set-?>) {
      this.articleStyle = var1;
   }

   @Nullable
   public final String getRuleArticles() {
      return this.ruleArticles;
   }

   public final void setRuleArticles(@Nullable String <set-?>) {
      this.ruleArticles = var1;
   }

   @Nullable
   public final String getRuleNextPage() {
      return this.ruleNextPage;
   }

   public final void setRuleNextPage(@Nullable String <set-?>) {
      this.ruleNextPage = var1;
   }

   @Nullable
   public final String getRuleTitle() {
      return this.ruleTitle;
   }

   public final void setRuleTitle(@Nullable String <set-?>) {
      this.ruleTitle = var1;
   }

   @Nullable
   public final String getRulePubDate() {
      return this.rulePubDate;
   }

   public final void setRulePubDate(@Nullable String <set-?>) {
      this.rulePubDate = var1;
   }

   @Nullable
   public final String getRuleDescription() {
      return this.ruleDescription;
   }

   public final void setRuleDescription(@Nullable String <set-?>) {
      this.ruleDescription = var1;
   }

   @Nullable
   public final String getRuleImage() {
      return this.ruleImage;
   }

   public final void setRuleImage(@Nullable String <set-?>) {
      this.ruleImage = var1;
   }

   @Nullable
   public final String getRuleLink() {
      return this.ruleLink;
   }

   public final void setRuleLink(@Nullable String <set-?>) {
      this.ruleLink = var1;
   }

   @Nullable
   public final String getRuleContent() {
      return this.ruleContent;
   }

   public final void setRuleContent(@Nullable String <set-?>) {
      this.ruleContent = var1;
   }

   @Nullable
   public final String getStyle() {
      return this.style;
   }

   public final void setStyle(@Nullable String <set-?>) {
      this.style = var1;
   }

   public final boolean getEnableJs() {
      return this.enableJs;
   }

   public final void setEnableJs(boolean <set-?>) {
      this.enableJs = var1;
   }

   public final boolean getLoadWithBaseUrl() {
      return this.loadWithBaseUrl;
   }

   public final void setLoadWithBaseUrl(boolean <set-?>) {
      this.loadWithBaseUrl = var1;
   }

   public final int getCustomOrder() {
      return this.customOrder;
   }

   public final void setCustomOrder(int <set-?>) {
      this.customOrder = var1;
   }

   @NotNull
   public String getTag() {
      return this.sourceName;
   }

   @NotNull
   public String getKey() {
      return this.sourceUrl;
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof RssSource ? Intrinsics.areEqual(((RssSource)other).sourceUrl, this.sourceUrl) : false;
   }

   public int hashCode() {
      return this.sourceUrl.hashCode();
   }

   public final boolean equal(@NotNull RssSource source) {
      Intrinsics.checkNotNullParameter(source, "source");
      return this.equal(this.sourceUrl, source.sourceUrl) && this.equal(this.sourceIcon, source.sourceIcon) && this.enabled == source.enabled && Intrinsics.areEqual(this.getEnabledCookieJar(), source.getEnabledCookieJar()) && this.equal(this.sourceComment, source.sourceComment) && this.equal(this.sourceGroup, source.sourceGroup) && this.equal(this.ruleArticles, source.ruleArticles) && this.equal(this.ruleNextPage, source.ruleNextPage) && this.equal(this.ruleTitle, source.ruleTitle) && this.equal(this.rulePubDate, source.rulePubDate) && this.equal(this.ruleDescription, source.ruleDescription) && this.equal(this.ruleLink, source.ruleLink) && this.equal(this.ruleContent, source.ruleContent) && this.enableJs == source.enableJs && this.loadWithBaseUrl == source.loadWithBaseUrl;
   }

   private final boolean equal(String a, String b) {
      boolean var10000;
      if (!Intrinsics.areEqual(a, b)) {
         label55: {
            CharSequence var3 = (CharSequence)a;
            boolean var4 = false;
            boolean var5 = false;
            if (var3 == null || var3.length() == 0) {
               var3 = (CharSequence)b;
               var4 = false;
               var5 = false;
               if (var3 == null || var3.length() == 0) {
                  break label55;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   @NotNull
   public final List<Pair<String, String>> sortUrls() {
      boolean var1 = false;
      ArrayList var21 = new ArrayList();
      boolean var2 = false;
      boolean var3 = false;
      ArrayList $this$sortUrls_u24lambda_u2d2 = var21;
      int var5 = false;
      boolean var6 = false;

      Object var7;
      try {
         boolean $i$f$forEach;
         boolean var14;
         String a;
         label81: {
            kotlin.Result.Companion var22 = Result.Companion;
            int var8 = false;
            a = this.getSortUrl();
            String jsStr = this.getSortUrl();
            if (!(jsStr == null ? false : StringsKt.startsWith(jsStr, "<js>", false))) {
               jsStr = this.getSortUrl();
               if (!(jsStr == null ? false : StringsKt.startsWith(jsStr, "@js:", false))) {
                  break label81;
               }
            }

            String var10000 = this.getSortUrl();
            Intrinsics.checkNotNull(var10000);
            String var11;
            byte var12;
            if (StringsKt.startsWith$default(var10000, "@", false, 2, (Object)null)) {
               var10000 = this.getSortUrl();
               Intrinsics.checkNotNull(var10000);
               var11 = var10000;
               var12 = 4;
               $i$f$forEach = false;
               if (var11 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               }

               var10000 = var11.substring(var12);
               Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
            } else {
               var10000 = this.getSortUrl();
               Intrinsics.checkNotNull(var10000);
               var11 = var10000;
               var12 = 4;
               var10000 = this.getSortUrl();
               Intrinsics.checkNotNull(var10000);
               int var30 = StringsKt.lastIndexOf$default((CharSequence)var10000, "<", 0, false, 6, (Object)null);
               var14 = false;
               if (var11 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               }

               var10000 = var11.substring(var12, var30);
               Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            }

            jsStr = var10000;
            a = String.valueOf(BaseSource.DefaultImpls.evalJS$default((BaseSource)this, jsStr, (Function1)null, 2, (Object)null));
         }

         if (a != null) {
            CharSequence var28 = (CharSequence)a;
            String var31 = "(&&|\n)+";
            var14 = false;
            Regex var32 = new Regex(var31);
            byte var33 = 0;
            boolean var15 = false;
            List var27 = var32.split(var28, var33);
            if (var27 != null) {
               Iterable $this$forEach$iv = (Iterable)var27;
               $i$f$forEach = false;
               Iterator var34 = $this$forEach$iv.iterator();

               while(var34.hasNext()) {
                  Object element$iv = var34.next();
                  String c = (String)element$iv;
                  int var17 = false;
                  CharSequence var36 = (CharSequence)c;
                  String[] var18 = new String[]{"::"};
                  List d = StringsKt.split$default(var36, var18, false, 0, 6, (Object)null);
                  if (d.size() > 1) {
                     $this$sortUrls_u24lambda_u2d2.add(new Pair(d.get(0), d.get(1)));
                  }
               }
            }
         }

         if ($this$sortUrls_u24lambda_u2d2.isEmpty()) {
            $this$sortUrls_u24lambda_u2d2.add(new Pair("", this.getSourceUrl()));
         }

         Unit var23 = Unit.INSTANCE;
         boolean var26 = false;
         var7 = Result.constructor-impl(var23);
      } catch (Throwable var20) {
         kotlin.Result.Companion var9 = Result.Companion;
         boolean var10 = false;
         var7 = Result.constructor-impl(ResultKt.createFailure(var20));
      }

      return (List)var21;
   }

   public final void setUserNameSpace(@NotNull String nameSpace) {
      Intrinsics.checkNotNullParameter(nameSpace, "nameSpace");
      this._userNameSpace = nameSpace;
   }

   @NotNull
   public String getUserNameSpace() {
      return this._userNameSpace;
   }

   public final void setLogger(@Nullable DebugLog logger) {
      this.debugLog = logger;
   }

   @Nullable
   public DebugLog getLogger() {
      return null;
   }

   @Nullable
   public Object evalJS(@NotNull String jsStr, @NotNull Function1<? super SimpleBindings, Unit> bindingsConfig) throws Exception {
      return BaseSource.DefaultImpls.evalJS(this, jsStr, bindingsConfig);
   }

   @Nullable
   public byte[] aesBase64DecodeToByteArray(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesBase64DecodeToByteArray((BaseSource)this, str, key, transformation, iv);
   }

   @Nullable
   public String aesBase64DecodeToString(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesBase64DecodeToString((BaseSource)this, str, key, transformation, iv);
   }

   @Nullable
   public String aesDecodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesDecodeArgsBase64Str((BaseSource)this, data, key, mode, padding, iv);
   }

   @Nullable
   public byte[] aesDecodeToByteArray(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesDecodeToByteArray((BaseSource)this, str, key, transformation, iv);
   }

   @Nullable
   public String aesDecodeToString(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesDecodeToString((BaseSource)this, str, key, transformation, iv);
   }

   @Nullable
   public String aesEncodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesEncodeArgsBase64Str((BaseSource)this, data, key, mode, padding, iv);
   }

   @Nullable
   public byte[] aesEncodeToBase64ByteArray(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesEncodeToBase64ByteArray((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String aesEncodeToBase64String(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesEncodeToBase64String((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public byte[] aesEncodeToByteArray(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesEncodeToByteArray((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String aesEncodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesEncodeToString((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String ajax(@NotNull String urlStr) {
      return BaseSource.DefaultImpls.ajax((BaseSource)this, urlStr);
   }

   @NotNull
   public StrResponse[] ajaxAll(@NotNull String[] urlList) {
      return BaseSource.DefaultImpls.ajaxAll((BaseSource)this, urlList);
   }

   @NotNull
   public String androidId() {
      return BaseSource.DefaultImpls.androidId((BaseSource)this);
   }

   @NotNull
   public String base64Decode(@NotNull String str) {
      return BaseSource.DefaultImpls.base64Decode((BaseSource)this, str);
   }

   @NotNull
   public String base64Decode(@NotNull String str, int flags) {
      return BaseSource.DefaultImpls.base64Decode((BaseSource)this, str, flags);
   }

   @Nullable
   public byte[] base64DecodeToByteArray(@Nullable String str) {
      return BaseSource.DefaultImpls.base64DecodeToByteArray((BaseSource)this, str);
   }

   @Nullable
   public byte[] base64DecodeToByteArray(@Nullable String str, int flags) {
      return BaseSource.DefaultImpls.base64DecodeToByteArray((BaseSource)this, str, flags);
   }

   @Nullable
   public String base64Encode(@NotNull String str) {
      return BaseSource.DefaultImpls.base64Encode((BaseSource)this, str);
   }

   @Nullable
   public String base64Encode(@NotNull String str, int flags) {
      return BaseSource.DefaultImpls.base64Encode((BaseSource)this, str, flags);
   }

   @Nullable
   public String cacheFile(@NotNull String urlStr) {
      return BaseSource.DefaultImpls.cacheFile((BaseSource)this, urlStr);
   }

   @Nullable
   public String cacheFile(@NotNull String urlStr, int saveTime) {
      return BaseSource.DefaultImpls.cacheFile((BaseSource)this, urlStr, saveTime);
   }

   @NotNull
   public StrResponse connect(@NotNull String urlStr) {
      return BaseSource.DefaultImpls.connect((BaseSource)this, urlStr);
   }

   @NotNull
   public StrResponse connect(@NotNull String urlStr, @Nullable String header) {
      return BaseSource.DefaultImpls.connect((BaseSource)this, urlStr, header);
   }

   public void deleteFile(@NotNull String path) {
      BaseSource.DefaultImpls.deleteFile((BaseSource)this, path);
   }

   @Nullable
   public String desBase64DecodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.desBase64DecodeToString((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String desDecodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.desDecodeToString((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String desEncodeToBase64String(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.desEncodeToBase64String((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String desEncodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.desEncodeToString((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String digestBase64Str(@NotNull String data, @NotNull String algorithm) {
      return BaseSource.DefaultImpls.digestBase64Str((BaseSource)this, data, algorithm);
   }

   @Nullable
   public String digestHex(@NotNull String data, @NotNull String algorithm) {
      return BaseSource.DefaultImpls.digestHex((BaseSource)this, data, algorithm);
   }

   @NotNull
   public String downloadFile(@NotNull String content, @NotNull String url) {
      return BaseSource.DefaultImpls.downloadFile((BaseSource)this, content, url);
   }

   @NotNull
   public String encodeURI(@NotNull String str) {
      return BaseSource.DefaultImpls.encodeURI((BaseSource)this, str);
   }

   @NotNull
   public String encodeURI(@NotNull String str, @NotNull String enc) {
      return BaseSource.DefaultImpls.encodeURI((BaseSource)this, str, enc);
   }

   @NotNull
   public Response get(@NotNull String urlStr, @NotNull Map<String, String> headers) {
      return BaseSource.DefaultImpls.get((BaseSource)this, urlStr, headers);
   }

   @NotNull
   public String getCookie(@NotNull String tag, @Nullable String key) {
      return BaseSource.DefaultImpls.getCookie((BaseSource)this, tag, key);
   }

   @NotNull
   public File getFile(@NotNull String path) {
      return BaseSource.DefaultImpls.getFile((BaseSource)this, path);
   }

   @NotNull
   public HashMap<String, String> getHeaderMap(boolean hasLoginHeader) {
      return BaseSource.DefaultImpls.getHeaderMap(this, hasLoginHeader);
   }

   @Nullable
   public String getLoginHeader() {
      return BaseSource.DefaultImpls.getLoginHeader(this);
   }

   @Nullable
   public Map<String, String> getLoginHeaderMap() {
      return BaseSource.DefaultImpls.getLoginHeaderMap(this);
   }

   @Nullable
   public String getLoginInfo() {
      return BaseSource.DefaultImpls.getLoginInfo(this);
   }

   @Nullable
   public Map<String, String> getLoginInfoMap() {
      return BaseSource.DefaultImpls.getLoginInfoMap(this);
   }

   @Nullable
   public String getLoginJs() {
      return BaseSource.DefaultImpls.getLoginJs(this);
   }

   @Nullable
   public BaseSource getSource() {
      return BaseSource.DefaultImpls.getSource(this);
   }

   @NotNull
   public String getTxtInFolder(@NotNull String unzipPath) {
      return BaseSource.DefaultImpls.getTxtInFolder((BaseSource)this, unzipPath);
   }

   @Nullable
   public String getVariable() {
      return BaseSource.DefaultImpls.getVariable(this);
   }

   @Nullable
   public byte[] getZipByteArrayContent(@NotNull String url, @NotNull String path) {
      return BaseSource.DefaultImpls.getZipByteArrayContent((BaseSource)this, url, path);
   }

   @NotNull
   public String getZipStringContent(@NotNull String url, @NotNull String path) {
      return BaseSource.DefaultImpls.getZipStringContent((BaseSource)this, url, path);
   }

   @NotNull
   public String getZipStringContent(@NotNull String url, @NotNull String path, @NotNull String charsetName) {
      return BaseSource.DefaultImpls.getZipStringContent((BaseSource)this, url, path, charsetName);
   }

   @NotNull
   public Response head(@NotNull String urlStr, @NotNull Map<String, String> headers) {
      return BaseSource.DefaultImpls.head((BaseSource)this, urlStr, headers);
   }

   @NotNull
   public String htmlFormat(@NotNull String str) {
      return BaseSource.DefaultImpls.htmlFormat((BaseSource)this, str);
   }

   @NotNull
   public String importScript(@NotNull String path) {
      return BaseSource.DefaultImpls.importScript((BaseSource)this, path);
   }

   @NotNull
   public String log(@NotNull String msg) {
      return BaseSource.DefaultImpls.log((BaseSource)this, msg);
   }

   public void logType(@Nullable Object any) {
      BaseSource.DefaultImpls.logType((BaseSource)this, any);
   }

   public void login() {
      BaseSource.DefaultImpls.login(this);
   }

   public void longToast(@Nullable Object msg) {
      BaseSource.DefaultImpls.longToast((BaseSource)this, msg);
   }

   @NotNull
   public String md5Encode(@NotNull String str) {
      return BaseSource.DefaultImpls.md5Encode((BaseSource)this, str);
   }

   @NotNull
   public String md5Encode16(@NotNull String str) {
      return BaseSource.DefaultImpls.md5Encode16((BaseSource)this, str);
   }

   @NotNull
   public Response post(@NotNull String urlStr, @NotNull String body, @NotNull Map<String, String> headers) {
      return BaseSource.DefaultImpls.post((BaseSource)this, urlStr, body, headers);
   }

   public void putLoginHeader(@NotNull String header) {
      BaseSource.DefaultImpls.putLoginHeader(this, header);
   }

   public boolean putLoginInfo(@NotNull String info) {
      return BaseSource.DefaultImpls.putLoginInfo(this, info);
   }

   @Nullable
   public QueryTTF queryBase64TTF(@Nullable String base64) {
      return BaseSource.DefaultImpls.queryBase64TTF((BaseSource)this, base64);
   }

   @Nullable
   public QueryTTF queryTTF(@Nullable String str) {
      return BaseSource.DefaultImpls.queryTTF((BaseSource)this, str);
   }

   @NotNull
   public String randomUUID() {
      return BaseSource.DefaultImpls.randomUUID((BaseSource)this);
   }

   @Nullable
   public byte[] readFile(@NotNull String path) {
      return BaseSource.DefaultImpls.readFile((BaseSource)this, path);
   }

   @NotNull
   public String readTxtFile(@NotNull String path) {
      return BaseSource.DefaultImpls.readTxtFile((BaseSource)this, path);
   }

   @NotNull
   public String readTxtFile(@NotNull String path, @NotNull String charsetName) {
      return BaseSource.DefaultImpls.readTxtFile((BaseSource)this, path, charsetName);
   }

   public void removeLoginHeader() {
      BaseSource.DefaultImpls.removeLoginHeader(this);
   }

   public void removeLoginInfo() {
      BaseSource.DefaultImpls.removeLoginInfo(this);
   }

   @NotNull
   public String replaceFont(@NotNull String text, @Nullable QueryTTF font1, @Nullable QueryTTF font2) {
      return BaseSource.DefaultImpls.replaceFont((BaseSource)this, text, font1, font2);
   }

   public void setVariable(@Nullable String variable) {
      BaseSource.DefaultImpls.setVariable(this, variable);
   }

   @NotNull
   public String timeFormat(long time) {
      return BaseSource.DefaultImpls.timeFormat((BaseSource)this, time);
   }

   @Nullable
   public String timeFormatUTC(long time, @NotNull String format, int sh) {
      return BaseSource.DefaultImpls.timeFormatUTC((BaseSource)this, time, format, sh);
   }

   public void toast(@Nullable Object msg) {
      BaseSource.DefaultImpls.toast((BaseSource)this, msg);
   }

   @Nullable
   public String tripleDESDecodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return BaseSource.DefaultImpls.tripleDESDecodeArgsBase64Str((BaseSource)this, data, key, mode, padding, iv);
   }

   @Nullable
   public String tripleDESDecodeStr(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return BaseSource.DefaultImpls.tripleDESDecodeStr((BaseSource)this, data, key, mode, padding, iv);
   }

   @Nullable
   public String tripleDESEncodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return BaseSource.DefaultImpls.tripleDESEncodeArgsBase64Str((BaseSource)this, data, key, mode, padding, iv);
   }

   @Nullable
   public String tripleDESEncodeBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return BaseSource.DefaultImpls.tripleDESEncodeBase64Str((BaseSource)this, data, key, mode, padding, iv);
   }

   @NotNull
   public String unzipFile(@NotNull String zipPath) {
      return BaseSource.DefaultImpls.unzipFile((BaseSource)this, zipPath);
   }

   @NotNull
   public String utf8ToGbk(@NotNull String str) {
      return BaseSource.DefaultImpls.utf8ToGbk((BaseSource)this, str);
   }

   @Nullable
   public String webView(@Nullable String html, @Nullable String url, @Nullable String js) {
      return BaseSource.DefaultImpls.webView((BaseSource)this, html, url, js);
   }

   @NotNull
   public final String component1() {
      return this.sourceUrl;
   }

   @NotNull
   public final String component2() {
      return this.sourceName;
   }

   @NotNull
   public final String component3() {
      return this.sourceIcon;
   }

   @Nullable
   public final String component4() {
      return this.sourceGroup;
   }

   @Nullable
   public final String component5() {
      return this.sourceComment;
   }

   public final boolean component6() {
      return this.enabled;
   }

   @Nullable
   public final String component7() {
      return this.variableComment;
   }

   @Nullable
   public final Boolean component8() {
      return this.getEnabledCookieJar();
   }

   @Nullable
   public final String component9() {
      return this.getConcurrentRate();
   }

   @Nullable
   public final String component10() {
      return this.getHeader();
   }

   @Nullable
   public final String component11() {
      return this.getLoginUrl();
   }

   @Nullable
   public final String component12() {
      return this.getLoginUi();
   }

   @Nullable
   public final String component13() {
      return this.loginCheckJs;
   }

   @Nullable
   public final String component14() {
      return this.sortUrl;
   }

   public final boolean component15() {
      return this.singleUrl;
   }

   public final int component16() {
      return this.articleStyle;
   }

   @Nullable
   public final String component17() {
      return this.ruleArticles;
   }

   @Nullable
   public final String component18() {
      return this.ruleNextPage;
   }

   @Nullable
   public final String component19() {
      return this.ruleTitle;
   }

   @Nullable
   public final String component20() {
      return this.rulePubDate;
   }

   @Nullable
   public final String component21() {
      return this.ruleDescription;
   }

   @Nullable
   public final String component22() {
      return this.ruleImage;
   }

   @Nullable
   public final String component23() {
      return this.ruleLink;
   }

   @Nullable
   public final String component24() {
      return this.ruleContent;
   }

   @Nullable
   public final String component25() {
      return this.style;
   }

   public final boolean component26() {
      return this.enableJs;
   }

   public final boolean component27() {
      return this.loadWithBaseUrl;
   }

   public final int component28() {
      return this.customOrder;
   }

   @NotNull
   public final RssSource copy(@NotNull String sourceUrl, @NotNull String sourceName, @NotNull String sourceIcon, @Nullable String sourceGroup, @Nullable String sourceComment, boolean enabled, @Nullable String variableComment, @Nullable Boolean enabledCookieJar, @Nullable String concurrentRate, @Nullable String header, @Nullable String loginUrl, @Nullable String loginUi, @Nullable String loginCheckJs, @Nullable String sortUrl, boolean singleUrl, int articleStyle, @Nullable String ruleArticles, @Nullable String ruleNextPage, @Nullable String ruleTitle, @Nullable String rulePubDate, @Nullable String ruleDescription, @Nullable String ruleImage, @Nullable String ruleLink, @Nullable String ruleContent, @Nullable String style, boolean enableJs, boolean loadWithBaseUrl, int customOrder) {
      Intrinsics.checkNotNullParameter(sourceUrl, "sourceUrl");
      Intrinsics.checkNotNullParameter(sourceName, "sourceName");
      Intrinsics.checkNotNullParameter(sourceIcon, "sourceIcon");
      return new RssSource(sourceUrl, sourceName, sourceIcon, sourceGroup, sourceComment, enabled, variableComment, enabledCookieJar, concurrentRate, header, loginUrl, loginUi, loginCheckJs, sortUrl, singleUrl, articleStyle, ruleArticles, ruleNextPage, ruleTitle, rulePubDate, ruleDescription, ruleImage, ruleLink, ruleContent, style, enableJs, loadWithBaseUrl, customOrder);
   }

   // $FF: synthetic method
   public static RssSource copy$default(RssSource var0, String var1, String var2, String var3, String var4, String var5, boolean var6, String var7, Boolean var8, String var9, String var10, String var11, String var12, String var13, String var14, boolean var15, int var16, String var17, String var18, String var19, String var20, String var21, String var22, String var23, String var24, String var25, boolean var26, boolean var27, int var28, int var29, Object var30) {
      if ((var29 & 1) != 0) {
         var1 = var0.sourceUrl;
      }

      if ((var29 & 2) != 0) {
         var2 = var0.sourceName;
      }

      if ((var29 & 4) != 0) {
         var3 = var0.sourceIcon;
      }

      if ((var29 & 8) != 0) {
         var4 = var0.sourceGroup;
      }

      if ((var29 & 16) != 0) {
         var5 = var0.sourceComment;
      }

      if ((var29 & 32) != 0) {
         var6 = var0.enabled;
      }

      if ((var29 & 64) != 0) {
         var7 = var0.variableComment;
      }

      if ((var29 & 128) != 0) {
         var8 = var0.getEnabledCookieJar();
      }

      if ((var29 & 256) != 0) {
         var9 = var0.getConcurrentRate();
      }

      if ((var29 & 512) != 0) {
         var10 = var0.getHeader();
      }

      if ((var29 & 1024) != 0) {
         var11 = var0.getLoginUrl();
      }

      if ((var29 & 2048) != 0) {
         var12 = var0.getLoginUi();
      }

      if ((var29 & 4096) != 0) {
         var13 = var0.loginCheckJs;
      }

      if ((var29 & 8192) != 0) {
         var14 = var0.sortUrl;
      }

      if ((var29 & 16384) != 0) {
         var15 = var0.singleUrl;
      }

      if ((var29 & '耀') != 0) {
         var16 = var0.articleStyle;
      }

      if ((var29 & 65536) != 0) {
         var17 = var0.ruleArticles;
      }

      if ((var29 & 131072) != 0) {
         var18 = var0.ruleNextPage;
      }

      if ((var29 & 262144) != 0) {
         var19 = var0.ruleTitle;
      }

      if ((var29 & 524288) != 0) {
         var20 = var0.rulePubDate;
      }

      if ((var29 & 1048576) != 0) {
         var21 = var0.ruleDescription;
      }

      if ((var29 & 2097152) != 0) {
         var22 = var0.ruleImage;
      }

      if ((var29 & 4194304) != 0) {
         var23 = var0.ruleLink;
      }

      if ((var29 & 8388608) != 0) {
         var24 = var0.ruleContent;
      }

      if ((var29 & 16777216) != 0) {
         var25 = var0.style;
      }

      if ((var29 & 33554432) != 0) {
         var26 = var0.enableJs;
      }

      if ((var29 & 67108864) != 0) {
         var27 = var0.loadWithBaseUrl;
      }

      if ((var29 & 134217728) != 0) {
         var28 = var0.customOrder;
      }

      return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24, var25, var26, var27, var28);
   }

   @NotNull
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("RssSource(sourceUrl=").append(this.sourceUrl).append(", sourceName=").append(this.sourceName).append(", sourceIcon=").append(this.sourceIcon).append(", sourceGroup=").append(this.sourceGroup).append(", sourceComment=").append(this.sourceComment).append(", enabled=").append(this.enabled).append(", variableComment=").append(this.variableComment).append(", enabledCookieJar=").append(this.getEnabledCookieJar()).append(", concurrentRate=").append(this.getConcurrentRate()).append(", header=").append(this.getHeader()).append(", loginUrl=").append(this.getLoginUrl()).append(", loginUi=");
      var1.append(this.getLoginUi()).append(", loginCheckJs=").append(this.loginCheckJs).append(", sortUrl=").append(this.sortUrl).append(", singleUrl=").append(this.singleUrl).append(", articleStyle=").append(this.articleStyle).append(", ruleArticles=").append(this.ruleArticles).append(", ruleNextPage=").append(this.ruleNextPage).append(", ruleTitle=").append(this.ruleTitle).append(", rulePubDate=").append(this.rulePubDate).append(", ruleDescription=").append(this.ruleDescription).append(", ruleImage=").append(this.ruleImage).append(", ruleLink=").append(this.ruleLink);
      var1.append(", ruleContent=").append(this.ruleContent).append(", style=").append(this.style).append(", enableJs=").append(this.enableJs).append(", loadWithBaseUrl=").append(this.loadWithBaseUrl).append(", customOrder=").append(this.customOrder).append(')');
      return var1.toString();
   }

   public RssSource() {
      this((String)null, (String)null, (String)null, (String)null, (String)null, false, (String)null, (Boolean)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, false, 0, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, false, false, 0, 268435455, (DefaultConstructorMarker)null);
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\b\u0010\tJ4\u0010\n\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00050\u000bj\b\u0012\u0004\u0012\u00020\u0005`\f0\u00042\u0006\u0010\r\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u000e\u0010\tJ$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0010\u001a\u00020\u0011ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0014"},
      d2 = {"Lio/legado/app/data/entities/RssSource$Companion;", "", "()V", "fromJson", "Lkotlin/Result;", "Lio/legado/app/data/entities/RssSource;", "json", "", "fromJson-IoAF18A", "(Ljava/lang/String;)Ljava/lang/Object;", "fromJsonArray", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "jsonArray", "fromJsonArray-IoAF18A", "fromJsonDoc", "doc", "Lcom/jayway/jsonpath/DocumentContext;", "fromJsonDoc-IoAF18A", "(Lcom/jayway/jsonpath/DocumentContext;)Ljava/lang/Object;", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final Object fromJsonDoc_IoAF18A/* $FF was: fromJsonDoc-IoAF18A*/(@NotNull DocumentContext doc) {
         Intrinsics.checkNotNullParameter(doc, "doc");
         boolean var2 = false;

         Object var3;
         try {
            kotlin.Result.Companion var33 = Result.Companion;
            int var4 = false;
            String var10000 = JsonExtensionsKt.readString((ReadContext)doc, "$.sourceUrl");
            Intrinsics.checkNotNull(var10000);
            String var35 = var10000;
            var10000 = JsonExtensionsKt.readString((ReadContext)doc, "$.sourceName");
            Intrinsics.checkNotNull(var10000);
            String var37 = var10000;
            String var7 = JsonExtensionsKt.readString((ReadContext)doc, "$.sourceIcon");
            String var8 = var7 == null ? "" : var7;
            var7 = JsonExtensionsKt.readString((ReadContext)doc, "$.sourceGroup");
            String var9 = JsonExtensionsKt.readString((ReadContext)doc, "$.sourceComment");
            Boolean var10 = JsonExtensionsKt.readBool((ReadContext)doc, "$.enabled");
            boolean var11 = var10 == null ? true : var10;
            String var38 = JsonExtensionsKt.readString((ReadContext)doc, "$.concurrentRate");
            String var12 = JsonExtensionsKt.readString((ReadContext)doc, "$.header");
            String var13 = JsonExtensionsKt.readString((ReadContext)doc, "$.loginUrl");
            String var14 = JsonExtensionsKt.readString((ReadContext)doc, "$.loginCheckJs");
            String var15 = JsonExtensionsKt.readString((ReadContext)doc, "$.sortUrl");
            Boolean var16 = JsonExtensionsKt.readBool((ReadContext)doc, "$.singleUrl");
            boolean var17 = var16 == null ? false : var16;
            Integer var18 = JsonExtensionsKt.readInt((ReadContext)doc, "$.articleStyle");
            int var39 = var18 == null ? 0 : var18;
            String var40 = JsonExtensionsKt.readString((ReadContext)doc, "$.ruleArticles");
            String var19 = JsonExtensionsKt.readString((ReadContext)doc, "$.ruleNextPage");
            String var20 = JsonExtensionsKt.readString((ReadContext)doc, "$.ruleTitle");
            String var21 = JsonExtensionsKt.readString((ReadContext)doc, "$.rulePubDate");
            String var22 = JsonExtensionsKt.readString((ReadContext)doc, "$.ruleDescription");
            String var23 = JsonExtensionsKt.readString((ReadContext)doc, "$.ruleImage");
            String var24 = JsonExtensionsKt.readString((ReadContext)doc, "$.ruleLink");
            String var25 = JsonExtensionsKt.readString((ReadContext)doc, "$.ruleContent");
            String var26 = JsonExtensionsKt.readString((ReadContext)doc, "$.style");
            Boolean var27 = JsonExtensionsKt.readBool((ReadContext)doc, "$.enableJs");
            boolean var28 = var27 == null ? true : var27;
            Boolean var29 = JsonExtensionsKt.readBool((ReadContext)doc, "$.loadWithBaseUrl");
            boolean var41 = var29 == null ? true : var29;
            Boolean var30 = JsonExtensionsKt.readBool((ReadContext)doc, "$.enabledCookieJar");
            boolean var42 = var30 == null ? false : var30;
            Integer var31 = JsonExtensionsKt.readInt((ReadContext)doc, "$.customOrder");
            int var43 = var31 == null ? 0 : var31;
            RssSource var34 = new RssSource(var35, var37, var8, var7, var9, var11, (String)null, var42, var38, var12, var13, (String)null, var14, var15, var17, var39, var40, var19, var20, var21, var22, var23, var24, var25, var26, var28, var41, var43, 2112, (DefaultConstructorMarker)null);
            boolean var36 = false;
            var3 = Result.constructor-impl(var34);
         } catch (Throwable var32) {
            kotlin.Result.Companion var5 = Result.Companion;
            boolean var6 = false;
            var3 = Result.constructor-impl(ResultKt.createFailure(var32));
         }

         return var3;
      }

      @NotNull
      public final Object fromJson_IoAF18A/* $FF was: fromJson-IoAF18A*/(@NotNull String json) {
         Intrinsics.checkNotNullParameter(json, "json");
         DocumentContext var2 = JsonExtensionsKt.getJsonPath().parse(json);
         Intrinsics.checkNotNullExpressionValue(var2, "jsonPath.parse(json)");
         return this.fromJsonDoc-IoAF18A(var2);
      }

      @NotNull
      public final Object fromJsonArray_IoAF18A/* $FF was: fromJsonArray-IoAF18A*/(@NotNull String jsonArray) {
         Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
         boolean var2 = false;

         Object var3;
         try {
            kotlin.Result.Companion var20 = Result.Companion;
            int var4 = false;
            boolean var21 = false;
            ArrayList sources = new ArrayList();
            List doc = (List)JsonExtensionsKt.getJsonPath().parse(jsonArray).read("$", new Predicate[0]);
            Intrinsics.checkNotNullExpressionValue(doc, "doc");
            Iterable $this$forEach$iv = (Iterable)doc;
            int $i$f$forEach = false;
            Iterator var9 = $this$forEach$iv.iterator();

            while(var9.hasNext()) {
               Object element$iv = var9.next();
               int var12 = false;
               DocumentContext jsonItem = JsonExtensionsKt.getJsonPath().parse(element$iv);
               RssSource.Companion var10000 = RssSource.Companion;
               Intrinsics.checkNotNullExpressionValue(jsonItem, "jsonItem");
               Object var14 = var10000.fromJsonDoc-IoAF18A(jsonItem);
               boolean var15 = false;
               ResultKt.throwOnFailure(var14);
               var15 = false;
               boolean var16 = false;
               RssSource source = (RssSource)var14;
               int var18 = false;
               sources.add(source);
            }

            var21 = false;
            var3 = Result.constructor-impl(sources);
         } catch (Throwable var19) {
            kotlin.Result.Companion var5 = Result.Companion;
            boolean var6 = false;
            var3 = Result.constructor-impl(ResultKt.createFailure(var19));
         }

         return var3;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
