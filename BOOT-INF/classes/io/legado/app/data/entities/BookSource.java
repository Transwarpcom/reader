package io.legado.app.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.script.SimpleBindings;
import io.legado.app.data.entities.rule.BookInfoRule;
import io.legado.app.data.entities.rule.ContentRule;
import io.legado.app.data.entities.rule.ExploreRule;
import io.legado.app.data.entities.rule.SearchRule;
import io.legado.app.data.entities.rule.TocRule;
import io.legado.app.help.SourceAnalyzer;
import io.legado.app.help.http.StrResponse;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.QueryTTF;
import io.legado.app.utils.GsonExtensionsKt;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Connection.Response;

@JsonIgnoreProperties({"headerMap", "source", "_userNameSpace", "userNameSpace", "loginHeader", "loginHeaderMap", "loginInfo", "loginInfoMap"})
@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\bZ\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\b\b\u0087\b\u0018\u0000 ª\u00012\u00020\u0001:\u0006ª\u0001«\u0001¬\u0001B«\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 \u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$¢\u0006\u0002\u0010%J\t\u0010w\u001a\u00020\u0003HÆ\u0003J\u000b\u0010x\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010y\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010z\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010{\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010|\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010}\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010~\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u007f\u001a\u00020\u0016HÆ\u0003J\n\u0010\u0080\u0001\u001a\u00020\u0016HÆ\u0003J\n\u0010\u0081\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0082\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u001bHÆ\u0003J\f\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u001eHÆ\u0003J\f\u0010\u0087\u0001\u001a\u0004\u0018\u00010 HÆ\u0003J\f\u0010\u0088\u0001\u001a\u0004\u0018\u00010\"HÆ\u0003J\f\u0010\u0089\u0001\u001a\u0004\u0018\u00010$HÆ\u0003J\f\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u008b\u0001\u001a\u00020\u0007HÆ\u0003J\f\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u008d\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010\u008e\u0001\u001a\u00020\u000bHÆ\u0003J\n\u0010\u008f\u0001\u001a\u00020\u000bHÆ\u0003J\u0011\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010DJ¶\u0002\u0010\u0091\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00072\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$HÆ\u0001¢\u0006\u0003\u0010\u0092\u0001J\u0010\u0010\u0093\u0001\u001a\u00020\u000b2\u0007\u0010\u0094\u0001\u001a\u00020\u0000J\u001f\u0010\u0093\u0001\u001a\u00020\u000b2\t\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u00032\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0003H\u0002J\u0016\u0010\u0097\u0001\u001a\u00020\u000b2\n\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0099\u0001H\u0096\u0002J\u0007\u0010\u009a\u0001\u001a\u00020 J\u0007\u0010\u009b\u0001\u001a\u00020$J\u0007\u0010\u009c\u0001\u001a\u00020\u001bJ\t\u0010\u009d\u0001\u001a\u00020\u0003H\u0016J\u000b\u0010\u009e\u0001\u001a\u0004\u0018\u00010>H\u0016J\u0007\u0010\u009f\u0001\u001a\u00020\u001eJ\t\u0010 \u0001\u001a\u00020\u0003H\u0016J\u0007\u0010¡\u0001\u001a\u00020\"J\t\u0010¢\u0001\u001a\u00020\u0003H\u0016J\t\u0010£\u0001\u001a\u00020\u0007H\u0016J\u0013\u0010¤\u0001\u001a\u00030¥\u00012\t\u0010¦\u0001\u001a\u0004\u0018\u00010>J\u0011\u0010§\u0001\u001a\u00030¥\u00012\u0007\u0010¨\u0001\u001a\u00020\u0003J\n\u0010©\u0001\u001a\u00020\u0003HÖ\u0001R\u000e\u0010&\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010)\"\u0004\b-\u0010+R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010)\"\u0004\b/\u0010+R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010)\"\u0004\b5\u0010+R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010)\"\u0004\b7\u0010+R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010)\"\u0004\b9\u0010+R\u0010\u0010:\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u00101\"\u0004\b<\u00103R\u0010\u0010=\u001a\u0004\u0018\u00010>X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001e\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0096\u000e¢\u0006\u0010\n\u0002\u0010G\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010@\"\u0004\bI\u0010BR\u0010\u0010J\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010)\"\u0004\bL\u0010+R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010)\"\u0004\bN\u0010+R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010)\"\u0004\bT\u0010+R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010)\"\u0004\bV\u0010+R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010)\"\u0004\bX\u0010+R\u001a\u0010\u0017\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010P\"\u0004\bZ\u0010RR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\u001c\u0010#\u001a\u0004\u0018\u00010$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010h\"\u0004\bi\u0010jR\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010l\"\u0004\bm\u0010nR\u0010\u0010o\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010)\"\u0004\bq\u0010+R\u0010\u0010r\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010)\"\u0004\bt\u0010+R\u001a\u0010\u0018\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u00101\"\u0004\bv\u00103¨\u0006\u00ad\u0001"},
   d2 = {"Lio/legado/app/data/entities/BookSource;", "Lio/legado/app/data/entities/BaseSource;", "bookSourceUrl", "", "bookSourceName", "bookSourceGroup", "bookSourceType", "", "bookUrlPattern", "customOrder", "enabled", "", "enabledExplore", "enabledCookieJar", "concurrentRate", "header", "loginUrl", "loginUi", "loginCheckJs", "bookSourceComment", "variableComment", "lastUpdateTime", "", "respondTime", "weight", "exploreUrl", "ruleExplore", "Lio/legado/app/data/entities/rule/ExploreRule;", "searchUrl", "ruleSearch", "Lio/legado/app/data/entities/rule/SearchRule;", "ruleBookInfo", "Lio/legado/app/data/entities/rule/BookInfoRule;", "ruleToc", "Lio/legado/app/data/entities/rule/TocRule;", "ruleContent", "Lio/legado/app/data/entities/rule/ContentRule;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;IZZLjava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJILjava/lang/String;Lio/legado/app/data/entities/rule/ExploreRule;Ljava/lang/String;Lio/legado/app/data/entities/rule/SearchRule;Lio/legado/app/data/entities/rule/BookInfoRule;Lio/legado/app/data/entities/rule/TocRule;Lio/legado/app/data/entities/rule/ContentRule;)V", "_userNameSpace", "bookInfoRuleV", "getBookSourceComment", "()Ljava/lang/String;", "setBookSourceComment", "(Ljava/lang/String;)V", "getBookSourceGroup", "setBookSourceGroup", "getBookSourceName", "setBookSourceName", "getBookSourceType", "()I", "setBookSourceType", "(I)V", "getBookSourceUrl", "setBookSourceUrl", "getBookUrlPattern", "setBookUrlPattern", "getConcurrentRate", "setConcurrentRate", "contentRuleV", "getCustomOrder", "setCustomOrder", "debugLog", "Lio/legado/app/model/DebugLog;", "getEnabled", "()Z", "setEnabled", "(Z)V", "getEnabledCookieJar", "()Ljava/lang/Boolean;", "setEnabledCookieJar", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getEnabledExplore", "setEnabledExplore", "exploreRuleV", "getExploreUrl", "setExploreUrl", "getHeader", "setHeader", "getLastUpdateTime", "()J", "setLastUpdateTime", "(J)V", "getLoginCheckJs", "setLoginCheckJs", "getLoginUi", "setLoginUi", "getLoginUrl", "setLoginUrl", "getRespondTime", "setRespondTime", "getRuleBookInfo", "()Lio/legado/app/data/entities/rule/BookInfoRule;", "setRuleBookInfo", "(Lio/legado/app/data/entities/rule/BookInfoRule;)V", "getRuleContent", "()Lio/legado/app/data/entities/rule/ContentRule;", "setRuleContent", "(Lio/legado/app/data/entities/rule/ContentRule;)V", "getRuleExplore", "()Lio/legado/app/data/entities/rule/ExploreRule;", "setRuleExplore", "(Lio/legado/app/data/entities/rule/ExploreRule;)V", "getRuleSearch", "()Lio/legado/app/data/entities/rule/SearchRule;", "setRuleSearch", "(Lio/legado/app/data/entities/rule/SearchRule;)V", "getRuleToc", "()Lio/legado/app/data/entities/rule/TocRule;", "setRuleToc", "(Lio/legado/app/data/entities/rule/TocRule;)V", "searchRuleV", "getSearchUrl", "setSearchUrl", "tocRuleV", "getVariableComment", "setVariableComment", "getWeight", "setWeight", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;IZZLjava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJILjava/lang/String;Lio/legado/app/data/entities/rule/ExploreRule;Ljava/lang/String;Lio/legado/app/data/entities/rule/SearchRule;Lio/legado/app/data/entities/rule/BookInfoRule;Lio/legado/app/data/entities/rule/TocRule;Lio/legado/app/data/entities/rule/ContentRule;)Lio/legado/app/data/entities/BookSource;", "equal", "source", "a", "b", "equals", "other", "", "getBookInfoRule", "getContentRule", "getExploreRule", "getKey", "getLogger", "getSearchRule", "getTag", "getTocRule", "getUserNameSpace", "hashCode", "setLogger", "", "logger", "setUserNameSpace", "nameSpace", "toString", "Companion", "Converters", "ExploreKind", "reader-pro"}
)
public final class BookSource implements BaseSource {
   @NotNull
   public static final BookSource.Companion Companion = new BookSource.Companion((DefaultConstructorMarker)null);
   @NotNull
   private String bookSourceUrl;
   @NotNull
   private String bookSourceName;
   @Nullable
   private String bookSourceGroup;
   private int bookSourceType;
   @Nullable
   private String bookUrlPattern;
   private int customOrder;
   private boolean enabled;
   private boolean enabledExplore;
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
   private String bookSourceComment;
   @Nullable
   private String variableComment;
   private long lastUpdateTime;
   private long respondTime;
   private int weight;
   @Nullable
   private String exploreUrl;
   @Nullable
   private ExploreRule ruleExplore;
   @Nullable
   private String searchUrl;
   @Nullable
   private SearchRule ruleSearch;
   @Nullable
   private BookInfoRule ruleBookInfo;
   @Nullable
   private TocRule ruleToc;
   @Nullable
   private ContentRule ruleContent;
   @Nullable
   private SearchRule searchRuleV;
   @Nullable
   private ExploreRule exploreRuleV;
   @Nullable
   private BookInfoRule bookInfoRuleV;
   @Nullable
   private TocRule tocRuleV;
   @Nullable
   private ContentRule contentRuleV;
   @NotNull
   private transient String _userNameSpace;
   @Nullable
   private transient DebugLog debugLog;

   public BookSource(@NotNull String bookSourceUrl, @NotNull String bookSourceName, @Nullable String bookSourceGroup, int bookSourceType, @Nullable String bookUrlPattern, int customOrder, boolean enabled, boolean enabledExplore, @Nullable Boolean enabledCookieJar, @Nullable String concurrentRate, @Nullable String header, @Nullable String loginUrl, @Nullable String loginUi, @Nullable String loginCheckJs, @Nullable String bookSourceComment, @Nullable String variableComment, long lastUpdateTime, long respondTime, int weight, @Nullable String exploreUrl, @Nullable ExploreRule ruleExplore, @Nullable String searchUrl, @Nullable SearchRule ruleSearch, @Nullable BookInfoRule ruleBookInfo, @Nullable TocRule ruleToc, @Nullable ContentRule ruleContent) {
      Intrinsics.checkNotNullParameter(bookSourceUrl, "bookSourceUrl");
      Intrinsics.checkNotNullParameter(bookSourceName, "bookSourceName");
      super();
      this.bookSourceUrl = bookSourceUrl;
      this.bookSourceName = bookSourceName;
      this.bookSourceGroup = bookSourceGroup;
      this.bookSourceType = bookSourceType;
      this.bookUrlPattern = bookUrlPattern;
      this.customOrder = customOrder;
      this.enabled = enabled;
      this.enabledExplore = enabledExplore;
      this.enabledCookieJar = enabledCookieJar;
      this.concurrentRate = concurrentRate;
      this.header = header;
      this.loginUrl = loginUrl;
      this.loginUi = loginUi;
      this.loginCheckJs = loginCheckJs;
      this.bookSourceComment = bookSourceComment;
      this.variableComment = variableComment;
      this.lastUpdateTime = lastUpdateTime;
      this.respondTime = respondTime;
      this.weight = weight;
      this.exploreUrl = exploreUrl;
      this.ruleExplore = ruleExplore;
      this.searchUrl = searchUrl;
      this.ruleSearch = ruleSearch;
      this.ruleBookInfo = ruleBookInfo;
      this.ruleToc = ruleToc;
      this.ruleContent = ruleContent;
      this._userNameSpace = "";
   }

   // $FF: synthetic method
   public BookSource(String var1, String var2, String var3, int var4, String var5, int var6, boolean var7, boolean var8, Boolean var9, String var10, String var11, String var12, String var13, String var14, String var15, String var16, long var17, long var19, int var21, String var22, ExploreRule var23, String var24, SearchRule var25, BookInfoRule var26, TocRule var27, ContentRule var28, int var29, DefaultConstructorMarker var30) {
      if ((var29 & 1) != 0) {
         var1 = "";
      }

      if ((var29 & 2) != 0) {
         var2 = "";
      }

      if ((var29 & 4) != 0) {
         var3 = null;
      }

      if ((var29 & 8) != 0) {
         var4 = 0;
      }

      if ((var29 & 16) != 0) {
         var5 = null;
      }

      if ((var29 & 32) != 0) {
         var6 = 0;
      }

      if ((var29 & 64) != 0) {
         var7 = true;
      }

      if ((var29 & 128) != 0) {
         var8 = true;
      }

      if ((var29 & 256) != 0) {
         var9 = false;
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
         var15 = null;
      }

      if ((var29 & '耀') != 0) {
         var16 = null;
      }

      if ((var29 & 65536) != 0) {
         var17 = 0L;
      }

      if ((var29 & 131072) != 0) {
         var19 = 180000L;
      }

      if ((var29 & 262144) != 0) {
         var21 = 0;
      }

      if ((var29 & 524288) != 0) {
         var22 = null;
      }

      if ((var29 & 1048576) != 0) {
         var23 = null;
      }

      if ((var29 & 2097152) != 0) {
         var24 = null;
      }

      if ((var29 & 4194304) != 0) {
         var25 = null;
      }

      if ((var29 & 8388608) != 0) {
         var26 = null;
      }

      if ((var29 & 16777216) != 0) {
         var27 = null;
      }

      if ((var29 & 33554432) != 0) {
         var28 = null;
      }

      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var19, var21, var22, var23, var24, var25, var26, var27, var28);
   }

   @NotNull
   public final String getBookSourceUrl() {
      return this.bookSourceUrl;
   }

   public final void setBookSourceUrl(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.bookSourceUrl = var1;
   }

   @NotNull
   public final String getBookSourceName() {
      return this.bookSourceName;
   }

   public final void setBookSourceName(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.bookSourceName = var1;
   }

   @Nullable
   public final String getBookSourceGroup() {
      return this.bookSourceGroup;
   }

   public final void setBookSourceGroup(@Nullable String <set-?>) {
      this.bookSourceGroup = var1;
   }

   public final int getBookSourceType() {
      return this.bookSourceType;
   }

   public final void setBookSourceType(int <set-?>) {
      this.bookSourceType = var1;
   }

   @Nullable
   public final String getBookUrlPattern() {
      return this.bookUrlPattern;
   }

   public final void setBookUrlPattern(@Nullable String <set-?>) {
      this.bookUrlPattern = var1;
   }

   public final int getCustomOrder() {
      return this.customOrder;
   }

   public final void setCustomOrder(int <set-?>) {
      this.customOrder = var1;
   }

   public final boolean getEnabled() {
      return this.enabled;
   }

   public final void setEnabled(boolean <set-?>) {
      this.enabled = var1;
   }

   public final boolean getEnabledExplore() {
      return this.enabledExplore;
   }

   public final void setEnabledExplore(boolean <set-?>) {
      this.enabledExplore = var1;
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
   public final String getBookSourceComment() {
      return this.bookSourceComment;
   }

   public final void setBookSourceComment(@Nullable String <set-?>) {
      this.bookSourceComment = var1;
   }

   @Nullable
   public final String getVariableComment() {
      return this.variableComment;
   }

   public final void setVariableComment(@Nullable String <set-?>) {
      this.variableComment = var1;
   }

   public final long getLastUpdateTime() {
      return this.lastUpdateTime;
   }

   public final void setLastUpdateTime(long <set-?>) {
      this.lastUpdateTime = var1;
   }

   public final long getRespondTime() {
      return this.respondTime;
   }

   public final void setRespondTime(long <set-?>) {
      this.respondTime = var1;
   }

   public final int getWeight() {
      return this.weight;
   }

   public final void setWeight(int <set-?>) {
      this.weight = var1;
   }

   @Nullable
   public final String getExploreUrl() {
      return this.exploreUrl;
   }

   public final void setExploreUrl(@Nullable String <set-?>) {
      this.exploreUrl = var1;
   }

   @Nullable
   public final ExploreRule getRuleExplore() {
      return this.ruleExplore;
   }

   public final void setRuleExplore(@Nullable ExploreRule <set-?>) {
      this.ruleExplore = var1;
   }

   @Nullable
   public final String getSearchUrl() {
      return this.searchUrl;
   }

   public final void setSearchUrl(@Nullable String <set-?>) {
      this.searchUrl = var1;
   }

   @Nullable
   public final SearchRule getRuleSearch() {
      return this.ruleSearch;
   }

   public final void setRuleSearch(@Nullable SearchRule <set-?>) {
      this.ruleSearch = var1;
   }

   @Nullable
   public final BookInfoRule getRuleBookInfo() {
      return this.ruleBookInfo;
   }

   public final void setRuleBookInfo(@Nullable BookInfoRule <set-?>) {
      this.ruleBookInfo = var1;
   }

   @Nullable
   public final TocRule getRuleToc() {
      return this.ruleToc;
   }

   public final void setRuleToc(@Nullable TocRule <set-?>) {
      this.ruleToc = var1;
   }

   @Nullable
   public final ContentRule getRuleContent() {
      return this.ruleContent;
   }

   public final void setRuleContent(@Nullable ContentRule <set-?>) {
      this.ruleContent = var1;
   }

   @NotNull
   public String getTag() {
      return this.bookSourceName;
   }

   @NotNull
   public String getKey() {
      return this.bookSourceUrl;
   }

   public int hashCode() {
      return this.bookSourceUrl.hashCode();
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof BookSource ? Intrinsics.areEqual(((BookSource)other).bookSourceUrl, this.bookSourceUrl) : false;
   }

   @NotNull
   public final SearchRule getSearchRule() {
      SearchRule var1 = this.ruleSearch;
      return var1 == null ? new SearchRule((String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 1023, (DefaultConstructorMarker)null) : var1;
   }

   @NotNull
   public final ExploreRule getExploreRule() {
      ExploreRule var1 = this.ruleExplore;
      return var1 == null ? new ExploreRule((String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 1023, (DefaultConstructorMarker)null) : var1;
   }

   @NotNull
   public final BookInfoRule getBookInfoRule() {
      BookInfoRule var1 = this.ruleBookInfo;
      return var1 == null ? new BookInfoRule((String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 2047, (DefaultConstructorMarker)null) : var1;
   }

   @NotNull
   public final TocRule getTocRule() {
      TocRule var1 = this.ruleToc;
      return var1 == null ? new TocRule((String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 255, (DefaultConstructorMarker)null) : var1;
   }

   @NotNull
   public final ContentRule getContentRule() {
      ContentRule var1 = this.ruleContent;
      return var1 == null ? new ContentRule((String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 63, (DefaultConstructorMarker)null) : var1;
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
      return this.debugLog;
   }

   public final boolean equal(@NotNull BookSource source) {
      Intrinsics.checkNotNullParameter(source, "source");
      return this.equal(this.bookSourceName, source.bookSourceName) && this.equal(this.bookSourceUrl, source.bookSourceUrl) && this.equal(this.bookSourceGroup, source.bookSourceGroup) && this.bookSourceType == source.bookSourceType && this.equal(this.bookUrlPattern, source.bookUrlPattern) && this.enabled == source.enabled && this.enabledExplore == source.enabledExplore && Intrinsics.areEqual(this.getEnabledCookieJar(), source.getEnabledCookieJar()) && this.equal(this.getHeader(), source.getHeader()) && this.equal(this.getLoginUrl(), source.getLoginUrl()) && this.equal(this.exploreUrl, source.exploreUrl) && this.equal(this.searchUrl, source.searchUrl) && Intrinsics.areEqual(this.getSearchRule(), source.getSearchRule()) && Intrinsics.areEqual(this.getExploreRule(), source.getExploreRule()) && Intrinsics.areEqual(this.getBookInfoRule(), source.getBookInfoRule()) && Intrinsics.areEqual(this.getTocRule(), source.getTocRule()) && Intrinsics.areEqual(this.getContentRule(), source.getContentRule());
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
      return this.bookSourceUrl;
   }

   @NotNull
   public final String component2() {
      return this.bookSourceName;
   }

   @Nullable
   public final String component3() {
      return this.bookSourceGroup;
   }

   public final int component4() {
      return this.bookSourceType;
   }

   @Nullable
   public final String component5() {
      return this.bookUrlPattern;
   }

   public final int component6() {
      return this.customOrder;
   }

   public final boolean component7() {
      return this.enabled;
   }

   public final boolean component8() {
      return this.enabledExplore;
   }

   @Nullable
   public final Boolean component9() {
      return this.getEnabledCookieJar();
   }

   @Nullable
   public final String component10() {
      return this.getConcurrentRate();
   }

   @Nullable
   public final String component11() {
      return this.getHeader();
   }

   @Nullable
   public final String component12() {
      return this.getLoginUrl();
   }

   @Nullable
   public final String component13() {
      return this.getLoginUi();
   }

   @Nullable
   public final String component14() {
      return this.loginCheckJs;
   }

   @Nullable
   public final String component15() {
      return this.bookSourceComment;
   }

   @Nullable
   public final String component16() {
      return this.variableComment;
   }

   public final long component17() {
      return this.lastUpdateTime;
   }

   public final long component18() {
      return this.respondTime;
   }

   public final int component19() {
      return this.weight;
   }

   @Nullable
   public final String component20() {
      return this.exploreUrl;
   }

   @Nullable
   public final ExploreRule component21() {
      return this.ruleExplore;
   }

   @Nullable
   public final String component22() {
      return this.searchUrl;
   }

   @Nullable
   public final SearchRule component23() {
      return this.ruleSearch;
   }

   @Nullable
   public final BookInfoRule component24() {
      return this.ruleBookInfo;
   }

   @Nullable
   public final TocRule component25() {
      return this.ruleToc;
   }

   @Nullable
   public final ContentRule component26() {
      return this.ruleContent;
   }

   @NotNull
   public final BookSource copy(@NotNull String bookSourceUrl, @NotNull String bookSourceName, @Nullable String bookSourceGroup, int bookSourceType, @Nullable String bookUrlPattern, int customOrder, boolean enabled, boolean enabledExplore, @Nullable Boolean enabledCookieJar, @Nullable String concurrentRate, @Nullable String header, @Nullable String loginUrl, @Nullable String loginUi, @Nullable String loginCheckJs, @Nullable String bookSourceComment, @Nullable String variableComment, long lastUpdateTime, long respondTime, int weight, @Nullable String exploreUrl, @Nullable ExploreRule ruleExplore, @Nullable String searchUrl, @Nullable SearchRule ruleSearch, @Nullable BookInfoRule ruleBookInfo, @Nullable TocRule ruleToc, @Nullable ContentRule ruleContent) {
      Intrinsics.checkNotNullParameter(bookSourceUrl, "bookSourceUrl");
      Intrinsics.checkNotNullParameter(bookSourceName, "bookSourceName");
      return new BookSource(bookSourceUrl, bookSourceName, bookSourceGroup, bookSourceType, bookUrlPattern, customOrder, enabled, enabledExplore, enabledCookieJar, concurrentRate, header, loginUrl, loginUi, loginCheckJs, bookSourceComment, variableComment, lastUpdateTime, respondTime, weight, exploreUrl, ruleExplore, searchUrl, ruleSearch, ruleBookInfo, ruleToc, ruleContent);
   }

   // $FF: synthetic method
   public static BookSource copy$default(BookSource var0, String var1, String var2, String var3, int var4, String var5, int var6, boolean var7, boolean var8, Boolean var9, String var10, String var11, String var12, String var13, String var14, String var15, String var16, long var17, long var19, int var21, String var22, ExploreRule var23, String var24, SearchRule var25, BookInfoRule var26, TocRule var27, ContentRule var28, int var29, Object var30) {
      if ((var29 & 1) != 0) {
         var1 = var0.bookSourceUrl;
      }

      if ((var29 & 2) != 0) {
         var2 = var0.bookSourceName;
      }

      if ((var29 & 4) != 0) {
         var3 = var0.bookSourceGroup;
      }

      if ((var29 & 8) != 0) {
         var4 = var0.bookSourceType;
      }

      if ((var29 & 16) != 0) {
         var5 = var0.bookUrlPattern;
      }

      if ((var29 & 32) != 0) {
         var6 = var0.customOrder;
      }

      if ((var29 & 64) != 0) {
         var7 = var0.enabled;
      }

      if ((var29 & 128) != 0) {
         var8 = var0.enabledExplore;
      }

      if ((var29 & 256) != 0) {
         var9 = var0.getEnabledCookieJar();
      }

      if ((var29 & 512) != 0) {
         var10 = var0.getConcurrentRate();
      }

      if ((var29 & 1024) != 0) {
         var11 = var0.getHeader();
      }

      if ((var29 & 2048) != 0) {
         var12 = var0.getLoginUrl();
      }

      if ((var29 & 4096) != 0) {
         var13 = var0.getLoginUi();
      }

      if ((var29 & 8192) != 0) {
         var14 = var0.loginCheckJs;
      }

      if ((var29 & 16384) != 0) {
         var15 = var0.bookSourceComment;
      }

      if ((var29 & '耀') != 0) {
         var16 = var0.variableComment;
      }

      if ((var29 & 65536) != 0) {
         var17 = var0.lastUpdateTime;
      }

      if ((var29 & 131072) != 0) {
         var19 = var0.respondTime;
      }

      if ((var29 & 262144) != 0) {
         var21 = var0.weight;
      }

      if ((var29 & 524288) != 0) {
         var22 = var0.exploreUrl;
      }

      if ((var29 & 1048576) != 0) {
         var23 = var0.ruleExplore;
      }

      if ((var29 & 2097152) != 0) {
         var24 = var0.searchUrl;
      }

      if ((var29 & 4194304) != 0) {
         var25 = var0.ruleSearch;
      }

      if ((var29 & 8388608) != 0) {
         var26 = var0.ruleBookInfo;
      }

      if ((var29 & 16777216) != 0) {
         var27 = var0.ruleToc;
      }

      if ((var29 & 33554432) != 0) {
         var28 = var0.ruleContent;
      }

      return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var19, var21, var22, var23, var24, var25, var26, var27, var28);
   }

   @NotNull
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("BookSource(bookSourceUrl=").append(this.bookSourceUrl).append(", bookSourceName=").append(this.bookSourceName).append(", bookSourceGroup=").append(this.bookSourceGroup).append(", bookSourceType=").append(this.bookSourceType).append(", bookUrlPattern=").append(this.bookUrlPattern).append(", customOrder=").append(this.customOrder).append(", enabled=").append(this.enabled).append(", enabledExplore=").append(this.enabledExplore).append(", enabledCookieJar=").append(this.getEnabledCookieJar()).append(", concurrentRate=").append(this.getConcurrentRate()).append(", header=").append(this.getHeader()).append(", loginUrl=");
      var1.append(this.getLoginUrl()).append(", loginUi=").append(this.getLoginUi()).append(", loginCheckJs=").append(this.loginCheckJs).append(", bookSourceComment=").append(this.bookSourceComment).append(", variableComment=").append(this.variableComment).append(", lastUpdateTime=").append(this.lastUpdateTime).append(", respondTime=").append(this.respondTime).append(", weight=").append(this.weight).append(", exploreUrl=").append(this.exploreUrl).append(", ruleExplore=").append(this.ruleExplore).append(", searchUrl=").append(this.searchUrl).append(", ruleSearch=").append(this.ruleSearch);
      var1.append(", ruleBookInfo=").append(this.ruleBookInfo).append(", ruleToc=").append(this.ruleToc).append(", ruleContent=").append(this.ruleContent).append(')');
      return var1.toString();
   }

   public BookSource() {
      this((String)null, (String)null, (String)null, 0, (String)null, 0, false, false, (Boolean)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 0L, 0L, 0, (String)null, (ExploreRule)null, (String)null, (SearchRule)null, (BookInfoRule)null, (TocRule)null, (ContentRule)null, 67108863, (DefaultConstructorMarker)null);
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"},
      d2 = {"Lio/legado/app/data/entities/BookSource$ExploreKind;", "", "title", "", "url", "(Ljava/lang/String;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "getUrl", "setUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "reader-pro"}
   )
   public static final class ExploreKind {
      @NotNull
      private String title;
      @Nullable
      private String url;

      public ExploreKind(@NotNull String title, @Nullable String url) {
         Intrinsics.checkNotNullParameter(title, "title");
         super();
         this.title = title;
         this.url = url;
      }

      // $FF: synthetic method
      public ExploreKind(String var1, String var2, int var3, DefaultConstructorMarker var4) {
         if ((var3 & 2) != 0) {
            var2 = null;
         }

         this(var1, var2);
      }

      @NotNull
      public final String getTitle() {
         return this.title;
      }

      public final void setTitle(@NotNull String <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.title = var1;
      }

      @Nullable
      public final String getUrl() {
         return this.url;
      }

      public final void setUrl(@Nullable String <set-?>) {
         this.url = var1;
      }

      @NotNull
      public final String component1() {
         return this.title;
      }

      @Nullable
      public final String component2() {
         return this.url;
      }

      @NotNull
      public final BookSource.ExploreKind copy(@NotNull String title, @Nullable String url) {
         Intrinsics.checkNotNullParameter(title, "title");
         return new BookSource.ExploreKind(title, url);
      }

      // $FF: synthetic method
      public static BookSource.ExploreKind copy$default(BookSource.ExploreKind var0, String var1, String var2, int var3, Object var4) {
         if ((var3 & 1) != 0) {
            var1 = var0.title;
         }

         if ((var3 & 2) != 0) {
            var2 = var0.url;
         }

         return var0.copy(var1, var2);
      }

      @NotNull
      public String toString() {
         return "ExploreKind(title=" + this.title + ", url=" + this.url + ')';
      }

      public int hashCode() {
         int result = this.title.hashCode();
         result = result * 31 + (this.url == null ? 0 : this.url.hashCode());
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof BookSource.ExploreKind)) {
            return false;
         } else {
            BookSource.ExploreKind var2 = (BookSource.ExploreKind)other;
            if (!Intrinsics.areEqual(this.title, var2.title)) {
               return false;
            } else {
               return Intrinsics.areEqual(this.url, var2.url);
            }
         }
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\b\u0010\tJ*\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\u00042\u0006\u0010\f\u001a\u00020\rø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ*\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\u00042\u0006\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u000e\u0010\t\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0010"},
      d2 = {"Lio/legado/app/data/entities/BookSource$Companion;", "", "()V", "fromJson", "Lkotlin/Result;", "Lio/legado/app/data/entities/BookSource;", "json", "", "fromJson-IoAF18A", "(Ljava/lang/String;)Ljava/lang/Object;", "fromJsonArray", "", "inputStream", "Ljava/io/InputStream;", "fromJsonArray-IoAF18A", "(Ljava/io/InputStream;)Ljava/lang/Object;", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final Object fromJson_IoAF18A/* $FF was: fromJson-IoAF18A*/(@NotNull String json) {
         Intrinsics.checkNotNullParameter(json, "json");
         return SourceAnalyzer.INSTANCE.jsonToBookSource-IoAF18A(json);
      }

      @NotNull
      public final Object fromJsonArray_IoAF18A/* $FF was: fromJsonArray-IoAF18A*/(@NotNull String json) {
         Intrinsics.checkNotNullParameter(json, "json");
         return SourceAnalyzer.INSTANCE.jsonToBookSources-IoAF18A(json);
      }

      @NotNull
      public final Object fromJsonArray_IoAF18A/* $FF was: fromJsonArray-IoAF18A*/(@NotNull InputStream inputStream) {
         Intrinsics.checkNotNullParameter(inputStream, "inputStream");
         return SourceAnalyzer.INSTANCE.jsonToBookSources-IoAF18A(inputStream);
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
      d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0010\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0010\u0010\r\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0017\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0016¨\u0006\u0019"},
      d2 = {"Lio/legado/app/data/entities/BookSource$Converters;", "", "()V", "bookInfoRuleToString", "", "bookInfoRule", "Lio/legado/app/data/entities/rule/BookInfoRule;", "contentRuleToString", "contentRule", "Lio/legado/app/data/entities/rule/ContentRule;", "exploreRuleToString", "exploreRule", "Lio/legado/app/data/entities/rule/ExploreRule;", "searchRuleToString", "searchRule", "Lio/legado/app/data/entities/rule/SearchRule;", "stringToBookInfoRule", "json", "stringToContentRule", "stringToExploreRule", "stringToSearchRule", "stringToTocRule", "Lio/legado/app/data/entities/rule/TocRule;", "tocRuleToString", "tocRule", "reader-pro"}
   )
   public static final class Converters {
      @NotNull
      public final String exploreRuleToString(@Nullable ExploreRule exploreRule) {
         String var2 = GsonExtensionsKt.getGSON().toJson(exploreRule);
         Intrinsics.checkNotNullExpressionValue(var2, "GSON.toJson(exploreRule)");
         return var2;
      }

      @Nullable
      public final ExploreRule stringToExploreRule(@Nullable String json) {
         Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
         int $i$f$fromJsonObject = false;
         boolean var4 = false;

         Object var5;
         try {
            kotlin.Result.Companion var10 = Result.Companion;
            int var6 = false;
            int $i$f$genericType = false;
            Type var13 = (new BookSource$Converters$stringToExploreRule$$inlined$fromJsonObject$1()).getType();
            Intrinsics.checkNotNullExpressionValue(var13, "object : TypeToken<T>() {}.type");
            Object var10000 = $this$fromJsonObject$iv.fromJson(json, var13);
            if (!(var10000 instanceof ExploreRule)) {
               var10000 = null;
            }

            ExploreRule var11 = (ExploreRule)var10000;
            $i$f$genericType = false;
            var5 = Result.constructor-impl(var11);
         } catch (Throwable var9) {
            kotlin.Result.Companion var7 = Result.Companion;
            boolean var8 = false;
            var5 = Result.constructor-impl(ResultKt.createFailure(var9));
         }

         $i$f$fromJsonObject = false;
         return (ExploreRule)(Result.isFailure-impl(var5) ? null : var5);
      }

      @NotNull
      public final String searchRuleToString(@Nullable SearchRule searchRule) {
         String var2 = GsonExtensionsKt.getGSON().toJson(searchRule);
         Intrinsics.checkNotNullExpressionValue(var2, "GSON.toJson(searchRule)");
         return var2;
      }

      @Nullable
      public final SearchRule stringToSearchRule(@Nullable String json) {
         Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
         int $i$f$fromJsonObject = false;
         boolean var4 = false;

         Object var5;
         try {
            kotlin.Result.Companion var10 = Result.Companion;
            int var6 = false;
            int $i$f$genericType = false;
            Type var13 = (new BookSource$Converters$stringToSearchRule$$inlined$fromJsonObject$1()).getType();
            Intrinsics.checkNotNullExpressionValue(var13, "object : TypeToken<T>() {}.type");
            Object var10000 = $this$fromJsonObject$iv.fromJson(json, var13);
            if (!(var10000 instanceof SearchRule)) {
               var10000 = null;
            }

            SearchRule var11 = (SearchRule)var10000;
            $i$f$genericType = false;
            var5 = Result.constructor-impl(var11);
         } catch (Throwable var9) {
            kotlin.Result.Companion var7 = Result.Companion;
            boolean var8 = false;
            var5 = Result.constructor-impl(ResultKt.createFailure(var9));
         }

         $i$f$fromJsonObject = false;
         return (SearchRule)(Result.isFailure-impl(var5) ? null : var5);
      }

      @NotNull
      public final String bookInfoRuleToString(@Nullable BookInfoRule bookInfoRule) {
         String var2 = GsonExtensionsKt.getGSON().toJson(bookInfoRule);
         Intrinsics.checkNotNullExpressionValue(var2, "GSON.toJson(bookInfoRule)");
         return var2;
      }

      @Nullable
      public final BookInfoRule stringToBookInfoRule(@Nullable String json) {
         Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
         int $i$f$fromJsonObject = false;
         boolean var4 = false;

         Object var5;
         try {
            kotlin.Result.Companion var10 = Result.Companion;
            int var6 = false;
            int $i$f$genericType = false;
            Type var13 = (new BookSource$Converters$stringToBookInfoRule$$inlined$fromJsonObject$1()).getType();
            Intrinsics.checkNotNullExpressionValue(var13, "object : TypeToken<T>() {}.type");
            Object var10000 = $this$fromJsonObject$iv.fromJson(json, var13);
            if (!(var10000 instanceof BookInfoRule)) {
               var10000 = null;
            }

            BookInfoRule var11 = (BookInfoRule)var10000;
            $i$f$genericType = false;
            var5 = Result.constructor-impl(var11);
         } catch (Throwable var9) {
            kotlin.Result.Companion var7 = Result.Companion;
            boolean var8 = false;
            var5 = Result.constructor-impl(ResultKt.createFailure(var9));
         }

         $i$f$fromJsonObject = false;
         return (BookInfoRule)(Result.isFailure-impl(var5) ? null : var5);
      }

      @NotNull
      public final String tocRuleToString(@Nullable TocRule tocRule) {
         String var2 = GsonExtensionsKt.getGSON().toJson(tocRule);
         Intrinsics.checkNotNullExpressionValue(var2, "GSON.toJson(tocRule)");
         return var2;
      }

      @Nullable
      public final TocRule stringToTocRule(@Nullable String json) {
         Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
         int $i$f$fromJsonObject = false;
         boolean var4 = false;

         Object var5;
         try {
            kotlin.Result.Companion var10 = Result.Companion;
            int var6 = false;
            int $i$f$genericType = false;
            Type var13 = (new BookSource$Converters$stringToTocRule$$inlined$fromJsonObject$1()).getType();
            Intrinsics.checkNotNullExpressionValue(var13, "object : TypeToken<T>() {}.type");
            Object var10000 = $this$fromJsonObject$iv.fromJson(json, var13);
            if (!(var10000 instanceof TocRule)) {
               var10000 = null;
            }

            TocRule var11 = (TocRule)var10000;
            $i$f$genericType = false;
            var5 = Result.constructor-impl(var11);
         } catch (Throwable var9) {
            kotlin.Result.Companion var7 = Result.Companion;
            boolean var8 = false;
            var5 = Result.constructor-impl(ResultKt.createFailure(var9));
         }

         $i$f$fromJsonObject = false;
         return (TocRule)(Result.isFailure-impl(var5) ? null : var5);
      }

      @NotNull
      public final String contentRuleToString(@Nullable ContentRule contentRule) {
         String var2 = GsonExtensionsKt.getGSON().toJson(contentRule);
         Intrinsics.checkNotNullExpressionValue(var2, "GSON.toJson(contentRule)");
         return var2;
      }

      @Nullable
      public final ContentRule stringToContentRule(@Nullable String json) {
         Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
         int $i$f$fromJsonObject = false;
         boolean var4 = false;

         Object var5;
         try {
            kotlin.Result.Companion var10 = Result.Companion;
            int var6 = false;
            int $i$f$genericType = false;
            Type var13 = (new BookSource$Converters$stringToContentRule$$inlined$fromJsonObject$1()).getType();
            Intrinsics.checkNotNullExpressionValue(var13, "object : TypeToken<T>() {}.type");
            Object var10000 = $this$fromJsonObject$iv.fromJson(json, var13);
            if (!(var10000 instanceof ContentRule)) {
               var10000 = null;
            }

            ContentRule var11 = (ContentRule)var10000;
            $i$f$genericType = false;
            var5 = Result.constructor-impl(var11);
         } catch (Throwable var9) {
            kotlin.Result.Companion var7 = Result.Companion;
            boolean var8 = false;
            var5 = Result.constructor-impl(ResultKt.createFailure(var9));
         }

         $i$f$fromJsonObject = false;
         return (ContentRule)(Result.isFailure-impl(var5) ? null : var5);
      }
   }
}
