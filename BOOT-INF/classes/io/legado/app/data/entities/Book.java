package io.legado.app.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.ReadContext;
import io.legado.app.constant.AppPattern;
import io.legado.app.model.localBook.CbzFile;
import io.legado.app.model.localBook.EpubFile;
import io.legado.app.model.localBook.LocalBook;
import io.legado.app.model.localBook.UmdFile;
import io.legado.app.utils.FileUtils;
import io.legado.app.utils.GsonExtensionsKt;
import io.legado.app.utils.JsonExtensionsKt;
import io.legado.app.utils.MD5Utils;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@JsonIgnoreProperties({"variableMap", "infoHtml", "tocHtml", "config", "rootDir", "localBook", "epub", "epubRootDir", "onLineTxt", "localTxt", "umd", "realAuthor", "unreadChapterNum", "folderName", "pdfImageWidth", "localFile", "kindList", "_userNameSpace", "bookDir", "userNameSpace"})
@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\bU\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\b\u0087\b\u0018\u0000 Õ\u00012\u00020\u0001:\u0006Õ\u0001Ö\u0001×\u0001Bé\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u001f\u0012\b\b\u0002\u0010 \u001a\u00020\u0011\u0012\b\b\u0002\u0010!\u001a\u00020\u0011\u0012\b\b\u0002\u0010\"\u001a\u00020\u001f\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%\u0012\b\b\u0002\u0010&\u001a\u00020\u001f\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010(J\n\u0010\u0083\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u0088\u0001\u001a\u00020\u0011HÆ\u0003J\n\u0010\u0089\u0001\u001a\u00020\u0013HÆ\u0003J\f\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u008b\u0001\u001a\u00020\u0013HÆ\u0003J\n\u0010\u008c\u0001\u001a\u00020\u0013HÆ\u0003J\n\u0010\u008d\u0001\u001a\u00020\u0011HÆ\u0003J\n\u0010\u008e\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u008f\u0001\u001a\u00020\u0011HÆ\u0003J\f\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u0091\u0001\u001a\u00020\u0011HÆ\u0003J\n\u0010\u0092\u0001\u001a\u00020\u0011HÆ\u0003J\n\u0010\u0093\u0001\u001a\u00020\u0013HÆ\u0003J\f\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u0095\u0001\u001a\u00020\u001fHÆ\u0003J\n\u0010\u0096\u0001\u001a\u00020\u0011HÆ\u0003J\n\u0010\u0097\u0001\u001a\u00020\u0011HÆ\u0003J\n\u0010\u0098\u0001\u001a\u00020\u001fHÆ\u0003J\n\u0010\u0099\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u009b\u0001\u001a\u0004\u0018\u00010%HÆ\u0003J\n\u0010\u009c\u0001\u001a\u00020\u001fHÆ\u0003J\f\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u009e\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u009f\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010 \u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010¡\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010¢\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010£\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010¤\u0001\u001a\u00020%H\u0002Jî\u0002\u0010¥\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00112\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00112\b\b\u0002\u0010\u001b\u001a\u00020\u00112\b\b\u0002\u0010\u001c\u001a\u00020\u00132\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u00112\b\b\u0002\u0010!\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020\u001f2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\b\b\u0002\u0010&\u001a\u00020\u001f2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0016\u0010¦\u0001\u001a\u00020\u001f2\n\u0010§\u0001\u001a\u0005\u0018\u00010¨\u0001H\u0096\u0002J\b\u0010©\u0001\u001a\u00030ª\u0001J\u0007\u0010«\u0001\u001a\u00020\u0003J\u0010\u0010¬\u0001\u001a\u00020\u001f2\u0007\u0010\u00ad\u0001\u001a\u00020\u0013J\t\u0010®\u0001\u001a\u0004\u0018\u00010\u0003J\t\u0010¯\u0001\u001a\u0004\u0018\u00010\u0003J\u0007\u0010°\u0001\u001a\u00020\u0003J\u0007\u0010±\u0001\u001a\u00020\u0003J\b\u0010²\u0001\u001a\u00030³\u0001J\b\u0010´\u0001\u001a\u00030µ\u0001J\u0007\u0010¶\u0001\u001a\u00020\u0003J\u0007\u0010·\u0001\u001a\u00020\u001fJ\u0007\u0010¸\u0001\u001a\u00020\u0011J\t\u0010¹\u0001\u001a\u00020\u0003H\u0016J\t\u0010º\u0001\u001a\u00020\u0011H\u0016J\u0007\u0010»\u0001\u001a\u00020\u001fJ\u0007\u0010¼\u0001\u001a\u00020\u001fJ\u0007\u0010½\u0001\u001a\u00020\u001fJ\u0007\u0010¾\u0001\u001a\u00020\u001fJ\u0007\u0010¿\u0001\u001a\u00020\u001fJ\u0007\u0010À\u0001\u001a\u00020\u001fJ\u0007\u0010Á\u0001\u001a\u00020\u001fJ\u0007\u0010Â\u0001\u001a\u00020\u001fJ\u0007\u0010Ã\u0001\u001a\u00020\u001fJ\u001e\u0010Ä\u0001\u001a\u00030Å\u00012\u0007\u0010Æ\u0001\u001a\u00020\u00032\t\u0010Ç\u0001\u001a\u0004\u0018\u00010\u0003H\u0016J\u0011\u0010È\u0001\u001a\u00030Å\u00012\u0007\u0010\u00ad\u0001\u001a\u00020\u0013J\u0012\u0010É\u0001\u001a\u00030Å\u00012\b\u0010Ê\u0001\u001a\u00030µ\u0001J\u0011\u0010Ë\u0001\u001a\u00030Å\u00012\u0007\u0010Ì\u0001\u001a\u00020\u0003J\u0011\u0010Í\u0001\u001a\u00030Å\u00012\u0007\u0010Î\u0001\u001a\u00020\u0003J\b\u0010Ï\u0001\u001a\u00030Ð\u0001J\n\u0010Ñ\u0001\u001a\u00020\u0003HÖ\u0001J\u0013\u0010Ò\u0001\u001a\u00030Å\u00012\t\b\u0002\u0010Ó\u0001\u001a\u00020\u001fJ\u0007\u0010Ô\u0001\u001a\u00020\u0003R\u000e\u0010)\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010+\"\u0004\b/\u0010-R\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010+\"\u0004\b5\u0010-R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010+\"\u0004\b7\u0010-R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010+\"\u0004\b9\u0010-R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010+\"\u0004\b;\u0010-R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010+\"\u0004\b=\u0010-R\u001a\u0010\u001a\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001a\u0010\u001b\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010?\"\u0004\bC\u0010AR\u001a\u0010\u001c\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010+\"\u0004\bI\u0010-R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010E\"\u0004\bK\u0010GR\u001c\u0010L\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010+\"\u0004\bN\u0010-R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010+\"\u0004\bP\u0010-R\u001c\u0010&\u001a\u00020\u001f8\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u00101\"\u0004\bQ\u00103R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010+\"\u0004\bS\u0010-R\u001a\u0010\u0017\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010?\"\u0004\bU\u0010AR\u001c\u0010'\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010+\"\u0004\bW\u0010-R\u001a\u0010\u0016\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010E\"\u0004\bY\u0010GR\u001a\u0010\u0015\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010E\"\u0004\b[\u0010GR\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010+\"\u0004\b]\u0010-R\u001a\u0010\u0007\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010+\"\u0004\b_\u0010-R\u001a\u0010 \u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010?\"\u0004\ba\u0010AR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010+\"\u0004\bc\u0010-R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010+\"\u0004\be\u0010-R\u001a\u0010!\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010?\"\u0004\bg\u0010AR\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010i\"\u0004\bj\u0010kR\u000e\u0010l\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010m\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010+\"\u0004\bo\u0010-R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010+\"\u0004\bq\u0010-R\u001a\u0010\u0018\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010?\"\u0004\bs\u0010AR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010?\"\u0004\bu\u0010AR\u001a\u0010\"\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u00101\"\u0004\bw\u00103R\u001c\u0010#\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010+\"\u0004\by\u0010-R8\u0010z\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030{j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`|8VX\u0096\u0084\u0002¢\u0006\r\n\u0005\b\u007f\u0010\u0080\u0001\u001a\u0004\b}\u0010~R\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0081\u0001\u0010+\"\u0005\b\u0082\u0001\u0010-¨\u0006Ø\u0001"},
   d2 = {"Lio/legado/app/data/entities/Book;", "Lio/legado/app/data/entities/BaseBook;", "bookUrl", "", "tocUrl", "origin", "originName", "name", "author", "kind", "customTag", "coverUrl", "customCoverUrl", "intro", "customIntro", "charset", "type", "", "group", "", "latestChapterTitle", "latestChapterTime", "lastCheckTime", "lastCheckCount", "totalChapterNum", "durChapterTitle", "durChapterIndex", "durChapterPos", "durChapterTime", "wordCount", "canUpdate", "", "order", "originOrder", "useReplaceRule", "variable", "readConfig", "Lio/legado/app/data/entities/Book$ReadConfig;", "isInShelf", "lastCheckError", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJLjava/lang/String;JJIILjava/lang/String;IIJLjava/lang/String;ZIIZLjava/lang/String;Lio/legado/app/data/entities/Book$ReadConfig;ZLjava/lang/String;)V", "_userNameSpace", "getAuthor", "()Ljava/lang/String;", "setAuthor", "(Ljava/lang/String;)V", "getBookUrl", "setBookUrl", "getCanUpdate", "()Z", "setCanUpdate", "(Z)V", "getCharset", "setCharset", "getCoverUrl", "setCoverUrl", "getCustomCoverUrl", "setCustomCoverUrl", "getCustomIntro", "setCustomIntro", "getCustomTag", "setCustomTag", "getDurChapterIndex", "()I", "setDurChapterIndex", "(I)V", "getDurChapterPos", "setDurChapterPos", "getDurChapterTime", "()J", "setDurChapterTime", "(J)V", "getDurChapterTitle", "setDurChapterTitle", "getGroup", "setGroup", "infoHtml", "getInfoHtml", "setInfoHtml", "getIntro", "setIntro", "setInShelf", "getKind", "setKind", "getLastCheckCount", "setLastCheckCount", "getLastCheckError", "setLastCheckError", "getLastCheckTime", "setLastCheckTime", "getLatestChapterTime", "setLatestChapterTime", "getLatestChapterTitle", "setLatestChapterTitle", "getName", "setName", "getOrder", "setOrder", "getOrigin", "setOrigin", "getOriginName", "setOriginName", "getOriginOrder", "setOriginOrder", "getReadConfig", "()Lio/legado/app/data/entities/Book$ReadConfig;", "setReadConfig", "(Lio/legado/app/data/entities/Book$ReadConfig;)V", "rootDir", "tocHtml", "getTocHtml", "setTocHtml", "getTocUrl", "setTocUrl", "getTotalChapterNum", "setTotalChapterNum", "getType", "setType", "getUseReplaceRule", "setUseReplaceRule", "getVariable", "setVariable", "variableMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getVariableMap", "()Ljava/util/HashMap;", "variableMap$delegate", "Lkotlin/Lazy;", "getWordCount", "setWordCount", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component4", "component5", "component6", "component7", "component8", "component9", "config", "copy", "equals", "other", "", "fileCharset", "Ljava/nio/charset/Charset;", "getBookDir", "getDelTag", "tag", "getDisplayCover", "getDisplayIntro", "getEpubRootDir", "getFolderName", "getLocalFile", "Ljava/io/File;", "getPdfImageWidth", "", "getRealAuthor", "getSplitLongChapter", "getUnreadChapterNum", "getUserNameSpace", "hashCode", "isCbz", "isEpub", "isLocalBook", "isLocalEpub", "isLocalPdf", "isLocalTxt", "isOnLineTxt", "isPdf", "isUmd", "putVariable", "", "key", "value", "setDelTag", "setPdfImageWidth", "pdfImageWidth", "setRootDir", "root", "setUserNameSpace", "nameSpace", "toSearchBook", "Lio/legado/app/data/entities/SearchBook;", "toString", "updateFromLocal", "onlyCover", "workRoot", "Companion", "Converters", "ReadConfig", "reader-pro"}
)
public final class Book implements BaseBook {
   @NotNull
   public static final Book.Companion Companion = new Book.Companion((DefaultConstructorMarker)null);
   @NotNull
   private String bookUrl;
   @NotNull
   private String tocUrl;
   @NotNull
   private String origin;
   @NotNull
   private String originName;
   @NotNull
   private String name;
   @NotNull
   private String author;
   @Nullable
   private String kind;
   @Nullable
   private String customTag;
   @Nullable
   private String coverUrl;
   @Nullable
   private String customCoverUrl;
   @Nullable
   private String intro;
   @Nullable
   private String customIntro;
   @Nullable
   private String charset;
   private int type;
   private long group;
   @Nullable
   private String latestChapterTitle;
   private long latestChapterTime;
   private long lastCheckTime;
   private int lastCheckCount;
   private int totalChapterNum;
   @Nullable
   private String durChapterTitle;
   private int durChapterIndex;
   private int durChapterPos;
   private long durChapterTime;
   @Nullable
   private String wordCount;
   private boolean canUpdate;
   private int order;
   private int originOrder;
   private boolean useReplaceRule;
   @Nullable
   private String variable;
   @Nullable
   private Book.ReadConfig readConfig;
   private boolean isInShelf;
   @Nullable
   private String lastCheckError;
   @NotNull
   private final transient Lazy variableMap$delegate;
   @Nullable
   private String infoHtml;
   @Nullable
   private String tocHtml;
   @NotNull
   private transient String rootDir;
   @NotNull
   private transient String _userNameSpace;
   public static final long hTag = 2L;
   public static final long rubyTag = 4L;
   public static final long imgTag = 8L;
   @NotNull
   public static final String imgStyleDefault = "DEFAULT";
   @NotNull
   public static final String imgStyleFull = "FULL";
   @NotNull
   public static final String imgStyleText = "TEXT";

   public Book(@NotNull String bookUrl, @NotNull String tocUrl, @NotNull String origin, @NotNull String originName, @NotNull String name, @NotNull String author, @Nullable String kind, @Nullable String customTag, @Nullable String coverUrl, @Nullable String customCoverUrl, @Nullable String intro, @Nullable String customIntro, @Nullable String charset, int type, long group, @Nullable String latestChapterTitle, long latestChapterTime, long lastCheckTime, int lastCheckCount, int totalChapterNum, @Nullable String durChapterTitle, int durChapterIndex, int durChapterPos, long durChapterTime, @Nullable String wordCount, boolean canUpdate, int order, int originOrder, boolean useReplaceRule, @Nullable String variable, @Nullable Book.ReadConfig readConfig, boolean isInShelf, @Nullable String lastCheckError) {
      Intrinsics.checkNotNullParameter(bookUrl, "bookUrl");
      Intrinsics.checkNotNullParameter(tocUrl, "tocUrl");
      Intrinsics.checkNotNullParameter(origin, "origin");
      Intrinsics.checkNotNullParameter(originName, "originName");
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(author, "author");
      super();
      this.bookUrl = bookUrl;
      this.tocUrl = tocUrl;
      this.origin = origin;
      this.originName = originName;
      this.name = name;
      this.author = author;
      this.kind = kind;
      this.customTag = customTag;
      this.coverUrl = coverUrl;
      this.customCoverUrl = customCoverUrl;
      this.intro = intro;
      this.customIntro = customIntro;
      this.charset = charset;
      this.type = type;
      this.group = group;
      this.latestChapterTitle = latestChapterTitle;
      this.latestChapterTime = latestChapterTime;
      this.lastCheckTime = lastCheckTime;
      this.lastCheckCount = lastCheckCount;
      this.totalChapterNum = totalChapterNum;
      this.durChapterTitle = durChapterTitle;
      this.durChapterIndex = durChapterIndex;
      this.durChapterPos = durChapterPos;
      this.durChapterTime = durChapterTime;
      this.wordCount = wordCount;
      this.canUpdate = canUpdate;
      this.order = order;
      this.originOrder = originOrder;
      this.useReplaceRule = useReplaceRule;
      this.variable = variable;
      this.readConfig = readConfig;
      this.isInShelf = isInShelf;
      this.lastCheckError = lastCheckError;
      this.variableMap$delegate = LazyKt.lazy((Function0)(new Function0<HashMap<String, String>>() {
         @NotNull
         public final HashMap<String, String> invoke() {
            Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
            String json$iv = Book.this.getVariable();
            int $i$f$fromJsonObject = false;
            boolean var5 = false;

            Object var6;
            try {
               kotlin.Result.Companion var13 = Result.Companion;
               int var7 = false;
               int $i$f$genericType = false;
               Type var16 = (new Book$variableMap$2$invoke$$inlined$fromJsonObject$1()).getType();
               Intrinsics.checkNotNullExpressionValue(var16, "object : TypeToken<T>() {}.type");
               Object var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var16);
               if (!(var10000 instanceof HashMap)) {
                  var10000 = null;
               }

               HashMap var14 = (HashMap)var10000;
               $i$f$genericType = false;
               var6 = Result.constructor-impl(var14);
            } catch (Throwable var10) {
               kotlin.Result.Companion var8 = Result.Companion;
               boolean var9 = false;
               var6 = Result.constructor-impl(ResultKt.createFailure(var10));
            }

            boolean var12 = false;
            HashMap var1 = (HashMap)(Result.isFailure-impl(var6) ? null : var6);
            HashMap var17;
            if (var1 == null) {
               boolean var11 = false;
               var17 = new HashMap();
            } else {
               var17 = var1;
            }

            return var17;
         }
      }));
      this.rootDir = "";
      this._userNameSpace = "";
   }

   // $FF: synthetic method
   public Book(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, int var14, long var15, String var17, long var18, long var20, int var22, int var23, String var24, int var25, int var26, long var27, String var29, boolean var30, int var31, int var32, boolean var33, String var34, Book.ReadConfig var35, boolean var36, String var37, int var38, int var39, DefaultConstructorMarker var40) {
      if ((var38 & 1) != 0) {
         var1 = "";
      }

      if ((var38 & 2) != 0) {
         var2 = "";
      }

      if ((var38 & 4) != 0) {
         var3 = "loc_book";
      }

      if ((var38 & 8) != 0) {
         var4 = "";
      }

      if ((var38 & 16) != 0) {
         var5 = "";
      }

      if ((var38 & 32) != 0) {
         var6 = "";
      }

      if ((var38 & 64) != 0) {
         var7 = null;
      }

      if ((var38 & 128) != 0) {
         var8 = null;
      }

      if ((var38 & 256) != 0) {
         var9 = null;
      }

      if ((var38 & 512) != 0) {
         var10 = null;
      }

      if ((var38 & 1024) != 0) {
         var11 = null;
      }

      if ((var38 & 2048) != 0) {
         var12 = null;
      }

      if ((var38 & 4096) != 0) {
         var13 = null;
      }

      if ((var38 & 8192) != 0) {
         var14 = 0;
      }

      if ((var38 & 16384) != 0) {
         var15 = 0L;
      }

      if ((var38 & '耀') != 0) {
         var17 = null;
      }

      if ((var38 & 65536) != 0) {
         var18 = System.currentTimeMillis();
      }

      if ((var38 & 131072) != 0) {
         var20 = System.currentTimeMillis();
      }

      if ((var38 & 262144) != 0) {
         var22 = 0;
      }

      if ((var38 & 524288) != 0) {
         var23 = 0;
      }

      if ((var38 & 1048576) != 0) {
         var24 = null;
      }

      if ((var38 & 2097152) != 0) {
         var25 = 0;
      }

      if ((var38 & 4194304) != 0) {
         var26 = 0;
      }

      if ((var38 & 8388608) != 0) {
         var27 = System.currentTimeMillis();
      }

      if ((var38 & 16777216) != 0) {
         var29 = null;
      }

      if ((var38 & 33554432) != 0) {
         var30 = true;
      }

      if ((var38 & 67108864) != 0) {
         var31 = 0;
      }

      if ((var38 & 134217728) != 0) {
         var32 = 0;
      }

      if ((var38 & 268435456) != 0) {
         var33 = true;
      }

      if ((var38 & 536870912) != 0) {
         var34 = null;
      }

      if ((var38 & 1073741824) != 0) {
         var35 = null;
      }

      if ((var38 & Integer.MIN_VALUE) != 0) {
         var36 = false;
      }

      if ((var39 & 1) != 0) {
         var37 = null;
      }

      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var17, var18, var20, var22, var23, var24, var25, var26, var27, var29, var30, var31, var32, var33, var34, var35, var36, var37);
   }

   @NotNull
   public String getBookUrl() {
      return this.bookUrl;
   }

   public void setBookUrl(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.bookUrl = var1;
   }

   @NotNull
   public final String getTocUrl() {
      return this.tocUrl;
   }

   public final void setTocUrl(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.tocUrl = var1;
   }

   @NotNull
   public final String getOrigin() {
      return this.origin;
   }

   public final void setOrigin(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.origin = var1;
   }

   @NotNull
   public final String getOriginName() {
      return this.originName;
   }

   public final void setOriginName(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.originName = var1;
   }

   @NotNull
   public String getName() {
      return this.name;
   }

   public void setName(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.name = var1;
   }

   @NotNull
   public String getAuthor() {
      return this.author;
   }

   public void setAuthor(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.author = var1;
   }

   @Nullable
   public String getKind() {
      return this.kind;
   }

   public void setKind(@Nullable String <set-?>) {
      this.kind = var1;
   }

   @Nullable
   public final String getCustomTag() {
      return this.customTag;
   }

   public final void setCustomTag(@Nullable String <set-?>) {
      this.customTag = var1;
   }

   @Nullable
   public final String getCoverUrl() {
      return this.coverUrl;
   }

   public final void setCoverUrl(@Nullable String <set-?>) {
      this.coverUrl = var1;
   }

   @Nullable
   public final String getCustomCoverUrl() {
      return this.customCoverUrl;
   }

   public final void setCustomCoverUrl(@Nullable String <set-?>) {
      this.customCoverUrl = var1;
   }

   @Nullable
   public final String getIntro() {
      return this.intro;
   }

   public final void setIntro(@Nullable String <set-?>) {
      this.intro = var1;
   }

   @Nullable
   public final String getCustomIntro() {
      return this.customIntro;
   }

   public final void setCustomIntro(@Nullable String <set-?>) {
      this.customIntro = var1;
   }

   @Nullable
   public final String getCharset() {
      return this.charset;
   }

   public final void setCharset(@Nullable String <set-?>) {
      this.charset = var1;
   }

   public final int getType() {
      return this.type;
   }

   public final void setType(int <set-?>) {
      this.type = var1;
   }

   public final long getGroup() {
      return this.group;
   }

   public final void setGroup(long <set-?>) {
      this.group = var1;
   }

   @Nullable
   public final String getLatestChapterTitle() {
      return this.latestChapterTitle;
   }

   public final void setLatestChapterTitle(@Nullable String <set-?>) {
      this.latestChapterTitle = var1;
   }

   public final long getLatestChapterTime() {
      return this.latestChapterTime;
   }

   public final void setLatestChapterTime(long <set-?>) {
      this.latestChapterTime = var1;
   }

   public final long getLastCheckTime() {
      return this.lastCheckTime;
   }

   public final void setLastCheckTime(long <set-?>) {
      this.lastCheckTime = var1;
   }

   public final int getLastCheckCount() {
      return this.lastCheckCount;
   }

   public final void setLastCheckCount(int <set-?>) {
      this.lastCheckCount = var1;
   }

   public final int getTotalChapterNum() {
      return this.totalChapterNum;
   }

   public final void setTotalChapterNum(int <set-?>) {
      this.totalChapterNum = var1;
   }

   @Nullable
   public final String getDurChapterTitle() {
      return this.durChapterTitle;
   }

   public final void setDurChapterTitle(@Nullable String <set-?>) {
      this.durChapterTitle = var1;
   }

   public final int getDurChapterIndex() {
      return this.durChapterIndex;
   }

   public final void setDurChapterIndex(int <set-?>) {
      this.durChapterIndex = var1;
   }

   public final int getDurChapterPos() {
      return this.durChapterPos;
   }

   public final void setDurChapterPos(int <set-?>) {
      this.durChapterPos = var1;
   }

   public final long getDurChapterTime() {
      return this.durChapterTime;
   }

   public final void setDurChapterTime(long <set-?>) {
      this.durChapterTime = var1;
   }

   @Nullable
   public String getWordCount() {
      return this.wordCount;
   }

   public void setWordCount(@Nullable String <set-?>) {
      this.wordCount = var1;
   }

   public final boolean getCanUpdate() {
      return this.canUpdate;
   }

   public final void setCanUpdate(boolean <set-?>) {
      this.canUpdate = var1;
   }

   public final int getOrder() {
      return this.order;
   }

   public final void setOrder(int <set-?>) {
      this.order = var1;
   }

   public final int getOriginOrder() {
      return this.originOrder;
   }

   public final void setOriginOrder(int <set-?>) {
      this.originOrder = var1;
   }

   public final boolean getUseReplaceRule() {
      return this.useReplaceRule;
   }

   public final void setUseReplaceRule(boolean <set-?>) {
      this.useReplaceRule = var1;
   }

   @Nullable
   public final String getVariable() {
      return this.variable;
   }

   public final void setVariable(@Nullable String <set-?>) {
      this.variable = var1;
   }

   @Nullable
   public final Book.ReadConfig getReadConfig() {
      return this.readConfig;
   }

   public final void setReadConfig(@Nullable Book.ReadConfig <set-?>) {
      this.readConfig = var1;
   }

   @JsonProperty("isInShelf")
   public final boolean isInShelf() {
      return this.isInShelf;
   }

   public final void setInShelf(boolean <set-?>) {
      this.isInShelf = var1;
   }

   @Nullable
   public final String getLastCheckError() {
      return this.lastCheckError;
   }

   public final void setLastCheckError(@Nullable String <set-?>) {
      this.lastCheckError = var1;
   }

   public final boolean isLocalBook() {
      return Intrinsics.areEqual(this.origin, "loc_book");
   }

   public final boolean isLocalTxt() {
      return this.isLocalBook() && StringsKt.endsWith(this.originName, ".txt", true);
   }

   public final boolean isLocalEpub() {
      return this.isLocalBook() && StringsKt.endsWith(this.originName, ".epub", true);
   }

   public final boolean isLocalPdf() {
      return this.isLocalBook() && StringsKt.endsWith(this.originName, ".pdf", true);
   }

   public final boolean isEpub() {
      return StringsKt.endsWith(this.originName, ".epub", true);
   }

   public final boolean isCbz() {
      return StringsKt.endsWith(this.originName, ".cbz", true);
   }

   public final boolean isPdf() {
      return StringsKt.endsWith(this.originName, ".pdf", true);
   }

   public final boolean isUmd() {
      return StringsKt.endsWith(this.originName, ".umd", true);
   }

   public final boolean isOnLineTxt() {
      return !this.isLocalBook() && this.type == 0;
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof Book ? Intrinsics.areEqual(((Book)other).getBookUrl(), this.getBookUrl()) : false;
   }

   public int hashCode() {
      return this.getBookUrl().hashCode();
   }

   @NotNull
   public HashMap<String, String> getVariableMap() {
      Lazy var1 = this.variableMap$delegate;
      boolean var3 = false;
      return (HashMap)var1.getValue();
   }

   public void putVariable(@NotNull String key, @Nullable String value) {
      Intrinsics.checkNotNullParameter(key, "key");
      if (value != null) {
         Map var3 = (Map)this.getVariableMap();
         boolean var4 = false;
         var3.put(key, value);
      } else {
         this.getVariableMap().remove(key);
      }

      this.variable = GsonExtensionsKt.getGSON().toJson(this.getVariableMap());
   }

   @Nullable
   public String getInfoHtml() {
      return this.infoHtml;
   }

   public void setInfoHtml(@Nullable String <set-?>) {
      this.infoHtml = var1;
   }

   @Nullable
   public String getTocHtml() {
      return this.tocHtml;
   }

   public void setTocHtml(@Nullable String <set-?>) {
      this.tocHtml = var1;
   }

   @NotNull
   public final String getRealAuthor() {
      CharSequence var1 = (CharSequence)this.getAuthor();
      Regex var2 = AppPattern.INSTANCE.getAuthorRegex();
      String var3 = "";
      boolean var4 = false;
      return var2.replace(var1, var3);
   }

   public final int getUnreadChapterNum() {
      int var1 = this.totalChapterNum - this.durChapterIndex - 1;
      byte var2 = 0;
      boolean var3 = false;
      return Math.max(var1, var2);
   }

   @Nullable
   public final String getDisplayCover() {
      CharSequence var1 = (CharSequence)this.customCoverUrl;
      boolean var2 = false;
      boolean var3 = false;
      return var1 == null || var1.length() == 0 ? this.coverUrl : this.customCoverUrl;
   }

   @Nullable
   public final String getDisplayIntro() {
      CharSequence var1 = (CharSequence)this.customIntro;
      boolean var2 = false;
      boolean var3 = false;
      return var1 == null || var1.length() == 0 ? this.intro : this.customIntro;
   }

   @NotNull
   public final Charset fileCharset() {
      String var1 = this.charset;
      var1 = var1 == null ? "UTF-8" : var1;
      boolean var2 = false;
      Charset var10000 = Charset.forName(var1);
      Intrinsics.checkNotNullExpressionValue(var10000, "Charset.forName(charsetName)");
      return var10000;
   }

   private final Book.ReadConfig config() {
      if (this.readConfig == null) {
         this.readConfig = new Book.ReadConfig(false, 0, false, (String)null, false, 0L, 0.0F, 127, (DefaultConstructorMarker)null);
      }

      Book.ReadConfig var10000 = this.readConfig;
      Intrinsics.checkNotNull(var10000);
      return var10000;
   }

   public final void setDelTag(long tag) {
      this.config().setDelTag((this.config().getDelTag() & tag) == tag ? this.config().getDelTag() & ~tag : this.config().getDelTag() | tag);
   }

   public final boolean getDelTag(long tag) {
      return (this.config().getDelTag() & tag) == tag;
   }

   public final float getPdfImageWidth() {
      return this.config().getPdfImageWidth();
   }

   public final void setPdfImageWidth(float pdfImageWidth) {
      this.config().setPdfImageWidth(pdfImageWidth);
   }

   @NotNull
   public final String getFolderName() {
      CharSequence var2 = (CharSequence)this.getName();
      Regex var3 = AppPattern.INSTANCE.getFileNameRegex();
      String var4 = "";
      boolean var5 = false;
      String folderName = var3.replace(var2, var4);
      byte var7 = 0;
      byte var8 = 9;
      int var10 = folderName.length();
      boolean var6 = false;
      int var9 = Math.min(var8, var10);
      var5 = false;
      if (folderName == null) {
         throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
      } else {
         String var10000 = folderName.substring(var7, var9);
         Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
         folderName = var10000;
         return Intrinsics.stringPlus(folderName, MD5Utils.INSTANCE.md5Encode16(this.getBookUrl()));
      }
   }

   public final void setRootDir(@NotNull String root) {
      Intrinsics.checkNotNullParameter(root, "root");
      CharSequence var2 = (CharSequence)root;
      boolean var3 = false;
      if (var2.length() > 0) {
         String var4 = File.separator;
         Intrinsics.checkNotNullExpressionValue(var4, "separator");
         if (!StringsKt.endsWith$default(root, var4, false, 2, (Object)null)) {
            this.rootDir = Intrinsics.stringPlus(root, File.separator);
            return;
         }
      }

      this.rootDir = root;
   }

   @NotNull
   public final File getLocalFile() {
      if (StringsKt.startsWith$default(this.originName, this.rootDir, false, 2, (Object)null)) {
         this.originName = StringsKt.replaceFirst$default(this.originName, this.rootDir, "", false, 4, (Object)null);
      }

      BookKt.getLogger().info("getLocalFile rootDir: {} originName: {}", this.rootDir, this.originName);
      FileUtils var10000;
      File var10001;
      String[] var1;
      if (this.isEpub() && StringsKt.indexOf$default((CharSequence)this.originName, "localStore", 0, false, 6, (Object)null) < 0 && StringsKt.indexOf$default((CharSequence)this.originName, "webdav", 0, false, 6, (Object)null) < 0) {
         var10000 = FileUtils.INSTANCE;
         var10001 = new File(Intrinsics.stringPlus(this.rootDir, this.originName));
         var1 = new String[]{"index.epub"};
         return var10000.getFile(var10001, var1);
      } else if (this.isCbz() && StringsKt.indexOf$default((CharSequence)this.originName, "localStore", 0, false, 6, (Object)null) < 0 && StringsKt.indexOf$default((CharSequence)this.originName, "webdav", 0, false, 6, (Object)null) < 0) {
         var10000 = FileUtils.INSTANCE;
         var10001 = new File(Intrinsics.stringPlus(this.rootDir, this.originName));
         var1 = new String[]{"index.cbz"};
         return var10000.getFile(var10001, var1);
      } else if (this.isPdf() && StringsKt.indexOf$default((CharSequence)this.originName, "localStore", 0, false, 6, (Object)null) < 0 && StringsKt.indexOf$default((CharSequence)this.originName, "webdav", 0, false, 6, (Object)null) < 0) {
         var10000 = FileUtils.INSTANCE;
         var10001 = new File(Intrinsics.stringPlus(this.rootDir, this.originName));
         var1 = new String[]{"index.pdf"};
         return var10000.getFile(var10001, var1);
      } else {
         return new File(Intrinsics.stringPlus(this.rootDir, this.originName));
      }
   }

   public final void setUserNameSpace(@NotNull String nameSpace) {
      Intrinsics.checkNotNullParameter(nameSpace, "nameSpace");
      this._userNameSpace = nameSpace;
   }

   @NotNull
   public String getUserNameSpace() {
      return this._userNameSpace;
   }

   @NotNull
   public final String getBookDir() {
      FileUtils var10000 = FileUtils.INSTANCE;
      File var10001 = new File(this.rootDir);
      String[] var1 = new String[]{"storage", "data", this._userNameSpace, this.getName() + '_' + this.getAuthor()};
      return var10000.getPath(var10001, var1);
   }

   public final boolean getSplitLongChapter() {
      return false;
   }

   @NotNull
   public final SearchBook toSearchBook() {
      String var1 = this.getName();
      String var2 = this.getAuthor();
      String var3 = this.getKind();
      String var4 = this.getBookUrl();
      String var5 = this.origin;
      String var6 = this.originName;
      int var7 = this.type;
      String var8 = this.getWordCount();
      String var9 = this.latestChapterTitle;
      String var10 = this.coverUrl;
      String var11 = this.intro;
      String var12 = this.tocUrl;
      String var13 = this.variable;
      SearchBook var14 = new SearchBook(var4, var5, var6, var7, var1, var2, var3, var10, var11, var8, var9, var12, 0L, var13, 0, 20480, (DefaultConstructorMarker)null);
      boolean var15 = false;
      boolean var16 = false;
      int var17 = false;
      var14.setInfoHtml(this.getInfoHtml());
      var14.setTocHtml(this.getTocHtml());
      var14.setUserNameSpace(this.getUserNameSpace());
      return var14;
   }

   @NotNull
   public final String getEpubRootDir() {
      String defaultPath = "OEBPS";
      File containerRes = new File(this.getBookUrl() + File.separator + "index" + File.separator + "META-INF" + File.separator + "container.xml");
      if (containerRes.exists()) {
         try {
            Document document = Jsoup.parse(FilesKt.readText$default(containerRes, (Charset)null, 1, (Object)null));
            Element rootFileElement = (Element)((Element)document.getElementsByTag("rootfiles").get(0)).getElementsByTag("rootfile").get(0);
            String result = rootFileElement.attr("full-path");
            System.out.println(Intrinsics.stringPlus("result: ", result));
            if (result != null) {
               CharSequence var6 = (CharSequence)result;
               boolean var7 = false;
               if (var6.length() > 0) {
                  File var14 = (new File(result)).getParentFile();
                  String var10000;
                  if (var14 == null) {
                     var10000 = "";
                  } else {
                     boolean var9 = false;
                     boolean var10 = false;
                     int var12 = false;
                     String var15 = var14.toString();
                     var10000 = var15 == null ? "" : var15;
                  }

                  return var10000;
               }
            }
         } catch (Exception var13) {
            var13.printStackTrace();
         }
      }

      return defaultPath;
   }

   public final void updateFromLocal(boolean onlyCover) {
      try {
         if (this.isEpub()) {
            EpubFile.Companion.upBookInfo(this, onlyCover);
         } else if (this.isUmd()) {
            UmdFile.Companion.upBookInfo(this, onlyCover);
         } else if (this.isCbz()) {
            CbzFile.Companion.upBookInfo(this, onlyCover);
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   // $FF: synthetic method
   public static void updateFromLocal$default(Book var0, boolean var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = false;
      }

      var0.updateFromLocal(var1);
   }

   @NotNull
   public final String workRoot() {
      return this.rootDir;
   }

   @NotNull
   public List<String> getKindList() {
      return BaseBook.DefaultImpls.getKindList(this);
   }

   @Nullable
   public String getVariable(@NotNull String key) {
      return BaseBook.DefaultImpls.getVariable((BaseBook)this, key);
   }

   @NotNull
   public final String component1() {
      return this.getBookUrl();
   }

   @NotNull
   public final String component2() {
      return this.tocUrl;
   }

   @NotNull
   public final String component3() {
      return this.origin;
   }

   @NotNull
   public final String component4() {
      return this.originName;
   }

   @NotNull
   public final String component5() {
      return this.getName();
   }

   @NotNull
   public final String component6() {
      return this.getAuthor();
   }

   @Nullable
   public final String component7() {
      return this.getKind();
   }

   @Nullable
   public final String component8() {
      return this.customTag;
   }

   @Nullable
   public final String component9() {
      return this.coverUrl;
   }

   @Nullable
   public final String component10() {
      return this.customCoverUrl;
   }

   @Nullable
   public final String component11() {
      return this.intro;
   }

   @Nullable
   public final String component12() {
      return this.customIntro;
   }

   @Nullable
   public final String component13() {
      return this.charset;
   }

   public final int component14() {
      return this.type;
   }

   public final long component15() {
      return this.group;
   }

   @Nullable
   public final String component16() {
      return this.latestChapterTitle;
   }

   public final long component17() {
      return this.latestChapterTime;
   }

   public final long component18() {
      return this.lastCheckTime;
   }

   public final int component19() {
      return this.lastCheckCount;
   }

   public final int component20() {
      return this.totalChapterNum;
   }

   @Nullable
   public final String component21() {
      return this.durChapterTitle;
   }

   public final int component22() {
      return this.durChapterIndex;
   }

   public final int component23() {
      return this.durChapterPos;
   }

   public final long component24() {
      return this.durChapterTime;
   }

   @Nullable
   public final String component25() {
      return this.getWordCount();
   }

   public final boolean component26() {
      return this.canUpdate;
   }

   public final int component27() {
      return this.order;
   }

   public final int component28() {
      return this.originOrder;
   }

   public final boolean component29() {
      return this.useReplaceRule;
   }

   @Nullable
   public final String component30() {
      return this.variable;
   }

   @Nullable
   public final Book.ReadConfig component31() {
      return this.readConfig;
   }

   public final boolean component32() {
      return this.isInShelf;
   }

   @Nullable
   public final String component33() {
      return this.lastCheckError;
   }

   @NotNull
   public final Book copy(@NotNull String bookUrl, @NotNull String tocUrl, @NotNull String origin, @NotNull String originName, @NotNull String name, @NotNull String author, @Nullable String kind, @Nullable String customTag, @Nullable String coverUrl, @Nullable String customCoverUrl, @Nullable String intro, @Nullable String customIntro, @Nullable String charset, int type, long group, @Nullable String latestChapterTitle, long latestChapterTime, long lastCheckTime, int lastCheckCount, int totalChapterNum, @Nullable String durChapterTitle, int durChapterIndex, int durChapterPos, long durChapterTime, @Nullable String wordCount, boolean canUpdate, int order, int originOrder, boolean useReplaceRule, @Nullable String variable, @Nullable Book.ReadConfig readConfig, boolean isInShelf, @Nullable String lastCheckError) {
      Intrinsics.checkNotNullParameter(bookUrl, "bookUrl");
      Intrinsics.checkNotNullParameter(tocUrl, "tocUrl");
      Intrinsics.checkNotNullParameter(origin, "origin");
      Intrinsics.checkNotNullParameter(originName, "originName");
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(author, "author");
      return new Book(bookUrl, tocUrl, origin, originName, name, author, kind, customTag, coverUrl, customCoverUrl, intro, customIntro, charset, type, group, latestChapterTitle, latestChapterTime, lastCheckTime, lastCheckCount, totalChapterNum, durChapterTitle, durChapterIndex, durChapterPos, durChapterTime, wordCount, canUpdate, order, originOrder, useReplaceRule, variable, readConfig, isInShelf, lastCheckError);
   }

   // $FF: synthetic method
   public static Book copy$default(Book var0, String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, int var14, long var15, String var17, long var18, long var20, int var22, int var23, String var24, int var25, int var26, long var27, String var29, boolean var30, int var31, int var32, boolean var33, String var34, Book.ReadConfig var35, boolean var36, String var37, int var38, int var39, Object var40) {
      if ((var38 & 1) != 0) {
         var1 = var0.getBookUrl();
      }

      if ((var38 & 2) != 0) {
         var2 = var0.tocUrl;
      }

      if ((var38 & 4) != 0) {
         var3 = var0.origin;
      }

      if ((var38 & 8) != 0) {
         var4 = var0.originName;
      }

      if ((var38 & 16) != 0) {
         var5 = var0.getName();
      }

      if ((var38 & 32) != 0) {
         var6 = var0.getAuthor();
      }

      if ((var38 & 64) != 0) {
         var7 = var0.getKind();
      }

      if ((var38 & 128) != 0) {
         var8 = var0.customTag;
      }

      if ((var38 & 256) != 0) {
         var9 = var0.coverUrl;
      }

      if ((var38 & 512) != 0) {
         var10 = var0.customCoverUrl;
      }

      if ((var38 & 1024) != 0) {
         var11 = var0.intro;
      }

      if ((var38 & 2048) != 0) {
         var12 = var0.customIntro;
      }

      if ((var38 & 4096) != 0) {
         var13 = var0.charset;
      }

      if ((var38 & 8192) != 0) {
         var14 = var0.type;
      }

      if ((var38 & 16384) != 0) {
         var15 = var0.group;
      }

      if ((var38 & '耀') != 0) {
         var17 = var0.latestChapterTitle;
      }

      if ((var38 & 65536) != 0) {
         var18 = var0.latestChapterTime;
      }

      if ((var38 & 131072) != 0) {
         var20 = var0.lastCheckTime;
      }

      if ((var38 & 262144) != 0) {
         var22 = var0.lastCheckCount;
      }

      if ((var38 & 524288) != 0) {
         var23 = var0.totalChapterNum;
      }

      if ((var38 & 1048576) != 0) {
         var24 = var0.durChapterTitle;
      }

      if ((var38 & 2097152) != 0) {
         var25 = var0.durChapterIndex;
      }

      if ((var38 & 4194304) != 0) {
         var26 = var0.durChapterPos;
      }

      if ((var38 & 8388608) != 0) {
         var27 = var0.durChapterTime;
      }

      if ((var38 & 16777216) != 0) {
         var29 = var0.getWordCount();
      }

      if ((var38 & 33554432) != 0) {
         var30 = var0.canUpdate;
      }

      if ((var38 & 67108864) != 0) {
         var31 = var0.order;
      }

      if ((var38 & 134217728) != 0) {
         var32 = var0.originOrder;
      }

      if ((var38 & 268435456) != 0) {
         var33 = var0.useReplaceRule;
      }

      if ((var38 & 536870912) != 0) {
         var34 = var0.variable;
      }

      if ((var38 & 1073741824) != 0) {
         var35 = var0.readConfig;
      }

      if ((var38 & Integer.MIN_VALUE) != 0) {
         var36 = var0.isInShelf;
      }

      if ((var39 & 1) != 0) {
         var37 = var0.lastCheckError;
      }

      return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var17, var18, var20, var22, var23, var24, var25, var26, var27, var29, var30, var31, var32, var33, var34, var35, var36, var37);
   }

   @NotNull
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("Book(bookUrl=").append(this.getBookUrl()).append(", tocUrl=").append(this.tocUrl).append(", origin=").append(this.origin).append(", originName=").append(this.originName).append(", name=").append(this.getName()).append(", author=").append(this.getAuthor()).append(", kind=").append(this.getKind()).append(", customTag=").append(this.customTag).append(", coverUrl=").append(this.coverUrl).append(", customCoverUrl=").append(this.customCoverUrl).append(", intro=").append(this.intro).append(", customIntro=");
      var1.append(this.customIntro).append(", charset=").append(this.charset).append(", type=").append(this.type).append(", group=").append(this.group).append(", latestChapterTitle=").append(this.latestChapterTitle).append(", latestChapterTime=").append(this.latestChapterTime).append(", lastCheckTime=").append(this.lastCheckTime).append(", lastCheckCount=").append(this.lastCheckCount).append(", totalChapterNum=").append(this.totalChapterNum).append(", durChapterTitle=").append(this.durChapterTitle).append(", durChapterIndex=").append(this.durChapterIndex).append(", durChapterPos=").append(this.durChapterPos);
      var1.append(", durChapterTime=").append(this.durChapterTime).append(", wordCount=").append(this.getWordCount()).append(", canUpdate=").append(this.canUpdate).append(", order=").append(this.order).append(", originOrder=").append(this.originOrder).append(", useReplaceRule=").append(this.useReplaceRule).append(", variable=").append(this.variable).append(", readConfig=").append(this.readConfig).append(", isInShelf=").append(this.isInShelf).append(", lastCheckError=").append(this.lastCheckError).append(')');
      return var1.toString();
   }

   public Book() {
      this((String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 0, 0L, (String)null, 0L, 0L, 0, 0, (String)null, 0, 0, 0L, (String)null, false, 0, 0, false, (String)null, (Book.ReadConfig)null, false, (String)null, -1, 1, (DefaultConstructorMarker)null);
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u000f\u0010\u0010J4\u0010\u0011\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\r0\u0012j\b\u0012\u0004\u0012\u00020\r`\u00130\f2\u0006\u0010\u0014\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0015\u0010\u0010J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0017\u001a\u00020\u0018ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ \u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\b\b\u0002\u0010\u001e\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u001f"},
      d2 = {"Lio/legado/app/data/entities/Book$Companion;", "", "()V", "hTag", "", "imgStyleDefault", "", "imgStyleFull", "imgStyleText", "imgTag", "rubyTag", "fromJson", "Lkotlin/Result;", "Lio/legado/app/data/entities/Book;", "json", "fromJson-IoAF18A", "(Ljava/lang/String;)Ljava/lang/Object;", "fromJsonArray", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "jsonArray", "fromJsonArray-IoAF18A", "fromJsonDoc", "doc", "Lcom/jayway/jsonpath/DocumentContext;", "fromJsonDoc-IoAF18A", "(Lcom/jayway/jsonpath/DocumentContext;)Ljava/lang/Object;", "initLocalBook", "bookUrl", "localPath", "rootDir", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final Book initLocalBook(@NotNull String bookUrl, @NotNull String localPath, @NotNull String rootDir) {
         Intrinsics.checkNotNullParameter(bookUrl, "bookUrl");
         Intrinsics.checkNotNullParameter(localPath, "localPath");
         Intrinsics.checkNotNullParameter(rootDir, "rootDir");
         String fileName = (new File(localPath)).getName();
         LocalBook var10000 = LocalBook.INSTANCE;
         Intrinsics.checkNotNullExpressionValue(fileName, "fileName");
         Pair nameAuthor = var10000.analyzeNameAuthor(fileName);
         Book var7 = new Book(bookUrl, "", "loc_book", localPath, (String)nameAuthor.getFirst(), (String)nameAuthor.getSecond(), (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 0, 0L, (String)null, 0L, 0L, 0, 0, (String)null, 0, 0, 0L, (String)null, false, 0, 0, false, (String)null, (Book.ReadConfig)null, false, (String)null, -64, 1, (DefaultConstructorMarker)null);
         boolean var8 = false;
         boolean var9 = false;
         int var11 = false;
         var7.setCanUpdate(false);
         var7.setRootDir(rootDir);
         Book.updateFromLocal$default(var7, false, 1, (Object)null);
         return var7;
      }

      // $FF: synthetic method
      public static Book initLocalBook$default(Book.Companion var0, String var1, String var2, String var3, int var4, Object var5) {
         if ((var4 & 4) != 0) {
            var3 = "";
         }

         return var0.initLocalBook(var1, var2, var3);
      }

      @NotNull
      public final Object fromJsonDoc_IoAF18A/* $FF was: fromJsonDoc-IoAF18A*/(@NotNull DocumentContext doc) {
         Intrinsics.checkNotNullParameter(doc, "doc");
         boolean var2 = false;

         Object var3;
         boolean var6;
         try {
            kotlin.Result.Companion var90 = Result.Companion;
            int var4 = false;
            Object readConfig = doc.read("$.readConfig", new Predicate[0]);
            String var10000 = JsonExtensionsKt.readString((ReadContext)doc, "$.bookUrl");
            Intrinsics.checkNotNull(var10000);
            String var10001 = JsonExtensionsKt.readString((ReadContext)doc, "$.tocUrl");
            Intrinsics.checkNotNull(var10001);
            String var94 = JsonExtensionsKt.readString((ReadContext)doc, "$.origin");
            String var10002 = var94 == null ? "loc_book" : var94;
            var94 = JsonExtensionsKt.readString((ReadContext)doc, "$.originName");
            String var10003 = var94 == null ? "" : var94;
            String var10004 = JsonExtensionsKt.readString((ReadContext)doc, "$.name");
            Intrinsics.checkNotNull(var10004);
            var94 = JsonExtensionsKt.readString((ReadContext)doc, "$.author");
            String var10005 = var94 == null ? "" : var94;
            String var10006 = JsonExtensionsKt.readString((ReadContext)doc, "$.kind");
            String var10007 = JsonExtensionsKt.readString((ReadContext)doc, "$.customTag");
            String var10008 = JsonExtensionsKt.readString((ReadContext)doc, "$.coverUrl");
            String var10009 = JsonExtensionsKt.readString((ReadContext)doc, "$.customCoverUrl");
            String var10010 = JsonExtensionsKt.readString((ReadContext)doc, "$.intro");
            String var10011 = JsonExtensionsKt.readString((ReadContext)doc, "$.customIntro");
            String var10012 = JsonExtensionsKt.readString((ReadContext)doc, "$.charset");
            Integer var95 = JsonExtensionsKt.readInt((ReadContext)doc, "$.type");
            int var10013 = var95 == null ? 0 : var95;
            Long var96 = JsonExtensionsKt.readLong((ReadContext)doc, "$.group");
            long var10014 = var96 == null ? 0L : var96;
            String var10015 = JsonExtensionsKt.readString((ReadContext)doc, "$.latestChapterTitle");
            var96 = JsonExtensionsKt.readLong((ReadContext)doc, "$.latestChapterTime");
            long var10016 = var96 == null ? System.currentTimeMillis() : var96;
            var96 = JsonExtensionsKt.readLong((ReadContext)doc, "$.lastCheckTime");
            long var10017 = var96 == null ? System.currentTimeMillis() : var96;
            var95 = JsonExtensionsKt.readInt((ReadContext)doc, "$.lastCheckCount");
            int var10018 = var95 == null ? 0 : var95;
            var95 = JsonExtensionsKt.readInt((ReadContext)doc, "$.totalChapterNum");
            int var10019 = var95 == null ? 0 : var95;
            String var10020 = JsonExtensionsKt.readString((ReadContext)doc, "$.durChapterTitle");
            var95 = JsonExtensionsKt.readInt((ReadContext)doc, "$.durChapterIndex");
            int var10021 = var95 == null ? 0 : var95;
            var95 = JsonExtensionsKt.readInt((ReadContext)doc, "$.durChapterPos");
            int var10022 = var95 == null ? 0 : var95;
            var96 = JsonExtensionsKt.readLong((ReadContext)doc, "$.durChapterTime");
            long var10023 = var96 == null ? System.currentTimeMillis() : var96;
            String var10024 = JsonExtensionsKt.readString((ReadContext)doc, "$.wordCount");
            Boolean var97 = JsonExtensionsKt.readBool((ReadContext)doc, "$.canUpdate");
            boolean var10025 = var97 == null ? true : var97;
            var95 = JsonExtensionsKt.readInt((ReadContext)doc, "$.order");
            int var10026 = var95 == null ? 0 : var95;
            var95 = JsonExtensionsKt.readInt((ReadContext)doc, "$.originOrder");
            int var10027 = var95 == null ? 0 : var95;
            var97 = JsonExtensionsKt.readBool((ReadContext)doc, "$.useReplaceRule");
            boolean var10028 = var97 == null ? true : var97;
            String var10029 = JsonExtensionsKt.readString((ReadContext)doc, "$.variable");
            Book.ReadConfig var10030;
            if (readConfig != null) {
               String var7 = var10029;
               boolean var8 = var10028;
               int var9 = var10027;
               int var10 = var10026;
               boolean var11 = var10025;
               String var12 = var10024;
               long var13 = var10023;
               int var15 = var10022;
               int var16 = var10021;
               String var17 = var10020;
               int var18 = var10019;
               int var19 = var10018;
               long var20 = var10017;
               long var22 = var10016;
               String var24 = var10015;
               long var25 = var10014;
               int var27 = var10013;
               String var28 = var10012;
               String var29 = var10011;
               String var30 = var10010;
               String var31 = var10009;
               String var32 = var10008;
               String var33 = var10007;
               String var34 = var10006;
               String var35 = var10005;
               String var36 = var10004;
               String var37 = var10003;
               String var38 = var10002;
               String var39 = var10001;
               String var40 = var10000;
               var6 = false;

               Object var43;
               try {
                  kotlin.Result.Companion var103 = Result.Companion;
                  int var44 = false;
                  Boolean var106 = JsonExtensionsKt.readBool((ReadContext)doc, "$.readConfig.reverseToc");
                  boolean var99 = var106 == null ? false : var106;
                  Integer var107 = JsonExtensionsKt.readInt((ReadContext)doc, "$.readConfig.pageAnim");
                  int var100 = var107 == null ? -1 : var107;
                  var106 = JsonExtensionsKt.readBool((ReadContext)doc, "$.readConfig.reSegment");
                  boolean var101 = var106 == null ? false : var106;
                  var10005 = JsonExtensionsKt.readString((ReadContext)doc, "$.readConfig.imageStyle");
                  var106 = JsonExtensionsKt.readBool((ReadContext)doc, "$.readConfig.useReplaceRule");
                  boolean var102 = var106 == null ? false : var106;
                  Long var108 = JsonExtensionsKt.readLong((ReadContext)doc, "$.readConfig.delTag");
                  Book.ReadConfig var104 = new Book.ReadConfig(var99, var100, var101, var10005, var102, var108 == null ? 0L : var108, 0.0F, 64, (DefaultConstructorMarker)null);
                  boolean var109 = false;
                  var43 = Result.constructor-impl(var104);
               } catch (Throwable var88) {
                  kotlin.Result.Companion var45 = Result.Companion;
                  boolean var46 = false;
                  var43 = Result.constructor-impl(ResultKt.createFailure(var88));
               }

               var10000 = var40;
               var10001 = var39;
               var10002 = var38;
               var10003 = var37;
               var10004 = var36;
               var10005 = var35;
               var10006 = var34;
               var10007 = var33;
               var10008 = var32;
               var10009 = var31;
               var10010 = var30;
               var10011 = var29;
               var10012 = var28;
               var10013 = var27;
               var10014 = var25;
               var10015 = var24;
               var10016 = var22;
               var10017 = var20;
               var10018 = var19;
               var10019 = var18;
               var10020 = var17;
               var10021 = var16;
               var10022 = var15;
               var10023 = var13;
               var10024 = var12;
               var10025 = var11;
               var10026 = var10;
               var10027 = var9;
               var10028 = var8;
               var10029 = var7;
               Object var98 = var43;
               boolean var105 = false;
               var10030 = (Book.ReadConfig)(Result.isFailure-impl(var98) ? null : var98);
            } else {
               var10030 = null;
            }

            var97 = JsonExtensionsKt.readBool((ReadContext)doc, "$.isInShelf");
            boolean var10031 = var97 == null ? false : var97;
            Object var48 = null;
            byte var49 = 1;
            byte var50 = 0;
            Object var51 = null;
            boolean var52 = var10031;
            Book.ReadConfig var53 = var10030;
            String var54 = var10029;
            boolean var55 = var10028;
            int var56 = var10027;
            int var57 = var10026;
            boolean var58 = var10025;
            String var59 = var10024;
            long var60 = var10023;
            int var62 = var10022;
            int var63 = var10021;
            String var64 = var10020;
            int var65 = var10019;
            int var66 = var10018;
            long var67 = var10017;
            long var69 = var10016;
            String var71 = var10015;
            long var72 = var10014;
            int var74 = var10013;
            String var75 = var10012;
            String var76 = var10011;
            String var77 = var10010;
            String var78 = var10009;
            String var79 = var10008;
            String var80 = var10007;
            String var81 = var10006;
            String var82 = var10005;
            String var83 = var10004;
            String var84 = var10003;
            String var85 = var10002;
            String var86 = var10001;
            String var87 = var10000;
            Book var91 = new Book(var87, var86, var85, var84, var83, var82, var81, var80, var79, var78, var77, var76, var75, var74, var72, var71, var69, var67, var66, var65, var64, var63, var62, var60, var59, var58, var57, var56, var55, var54, var53, var52, (String)var51, var50, var49, (DefaultConstructorMarker)var48);
            boolean var93 = false;
            var3 = Result.constructor-impl(var91);
         } catch (Throwable var89) {
            kotlin.Result.Companion var5 = Result.Companion;
            var6 = false;
            var3 = Result.constructor-impl(ResultKt.createFailure(var89));
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
               Book.Companion var10000 = Book.Companion;
               Intrinsics.checkNotNullExpressionValue(jsonItem, "jsonItem");
               Object var14 = var10000.fromJsonDoc-IoAF18A(jsonItem);
               boolean var15 = false;
               ResultKt.throwOnFailure(var14);
               var15 = false;
               boolean var16 = false;
               Book source = (Book)var14;
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

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b&\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u000bHÆ\u0003J\t\u0010-\u001a\u00020\rHÆ\u0003JQ\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010/\u001a\u00020\u00032\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00101\u001a\u00020\u0005HÖ\u0001J\t\u00102\u001a\u00020\bHÖ\u0001R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010 \"\u0004\b$\u0010\"R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010 \"\u0004\b&\u0010\"¨\u00063"},
      d2 = {"Lio/legado/app/data/entities/Book$ReadConfig;", "", "reverseToc", "", "pageAnim", "", "reSegment", "imageStyle", "", "useReplaceRule", "delTag", "", "pdfImageWidth", "", "(ZIZLjava/lang/String;ZJF)V", "getDelTag", "()J", "setDelTag", "(J)V", "getImageStyle", "()Ljava/lang/String;", "setImageStyle", "(Ljava/lang/String;)V", "getPageAnim", "()I", "setPageAnim", "(I)V", "getPdfImageWidth", "()F", "setPdfImageWidth", "(F)V", "getReSegment", "()Z", "setReSegment", "(Z)V", "getReverseToc", "setReverseToc", "getUseReplaceRule", "setUseReplaceRule", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "reader-pro"}
   )
   public static final class ReadConfig {
      private boolean reverseToc;
      private int pageAnim;
      private boolean reSegment;
      @Nullable
      private String imageStyle;
      private boolean useReplaceRule;
      private long delTag;
      private float pdfImageWidth;

      public ReadConfig(boolean reverseToc, int pageAnim, boolean reSegment, @Nullable String imageStyle, boolean useReplaceRule, long delTag, float pdfImageWidth) {
         this.reverseToc = reverseToc;
         this.pageAnim = pageAnim;
         this.reSegment = reSegment;
         this.imageStyle = imageStyle;
         this.useReplaceRule = useReplaceRule;
         this.delTag = delTag;
         this.pdfImageWidth = pdfImageWidth;
      }

      // $FF: synthetic method
      public ReadConfig(boolean var1, int var2, boolean var3, String var4, boolean var5, long var6, float var8, int var9, DefaultConstructorMarker var10) {
         if ((var9 & 1) != 0) {
            var1 = false;
         }

         if ((var9 & 2) != 0) {
            var2 = -1;
         }

         if ((var9 & 4) != 0) {
            var3 = false;
         }

         if ((var9 & 8) != 0) {
            var4 = null;
         }

         if ((var9 & 16) != 0) {
            var5 = false;
         }

         if ((var9 & 32) != 0) {
            var6 = 0L;
         }

         if ((var9 & 64) != 0) {
            var8 = 800.0F;
         }

         this(var1, var2, var3, var4, var5, var6, var8);
      }

      public final boolean getReverseToc() {
         return this.reverseToc;
      }

      public final void setReverseToc(boolean <set-?>) {
         this.reverseToc = var1;
      }

      public final int getPageAnim() {
         return this.pageAnim;
      }

      public final void setPageAnim(int <set-?>) {
         this.pageAnim = var1;
      }

      public final boolean getReSegment() {
         return this.reSegment;
      }

      public final void setReSegment(boolean <set-?>) {
         this.reSegment = var1;
      }

      @Nullable
      public final String getImageStyle() {
         return this.imageStyle;
      }

      public final void setImageStyle(@Nullable String <set-?>) {
         this.imageStyle = var1;
      }

      public final boolean getUseReplaceRule() {
         return this.useReplaceRule;
      }

      public final void setUseReplaceRule(boolean <set-?>) {
         this.useReplaceRule = var1;
      }

      public final long getDelTag() {
         return this.delTag;
      }

      public final void setDelTag(long <set-?>) {
         this.delTag = var1;
      }

      public final float getPdfImageWidth() {
         return this.pdfImageWidth;
      }

      public final void setPdfImageWidth(float <set-?>) {
         this.pdfImageWidth = var1;
      }

      public final boolean component1() {
         return this.reverseToc;
      }

      public final int component2() {
         return this.pageAnim;
      }

      public final boolean component3() {
         return this.reSegment;
      }

      @Nullable
      public final String component4() {
         return this.imageStyle;
      }

      public final boolean component5() {
         return this.useReplaceRule;
      }

      public final long component6() {
         return this.delTag;
      }

      public final float component7() {
         return this.pdfImageWidth;
      }

      @NotNull
      public final Book.ReadConfig copy(boolean reverseToc, int pageAnim, boolean reSegment, @Nullable String imageStyle, boolean useReplaceRule, long delTag, float pdfImageWidth) {
         return new Book.ReadConfig(reverseToc, pageAnim, reSegment, imageStyle, useReplaceRule, delTag, pdfImageWidth);
      }

      // $FF: synthetic method
      public static Book.ReadConfig copy$default(Book.ReadConfig var0, boolean var1, int var2, boolean var3, String var4, boolean var5, long var6, float var8, int var9, Object var10) {
         if ((var9 & 1) != 0) {
            var1 = var0.reverseToc;
         }

         if ((var9 & 2) != 0) {
            var2 = var0.pageAnim;
         }

         if ((var9 & 4) != 0) {
            var3 = var0.reSegment;
         }

         if ((var9 & 8) != 0) {
            var4 = var0.imageStyle;
         }

         if ((var9 & 16) != 0) {
            var5 = var0.useReplaceRule;
         }

         if ((var9 & 32) != 0) {
            var6 = var0.delTag;
         }

         if ((var9 & 64) != 0) {
            var8 = var0.pdfImageWidth;
         }

         return var0.copy(var1, var2, var3, var4, var5, var6, var8);
      }

      @NotNull
      public String toString() {
         return "ReadConfig(reverseToc=" + this.reverseToc + ", pageAnim=" + this.pageAnim + ", reSegment=" + this.reSegment + ", imageStyle=" + this.imageStyle + ", useReplaceRule=" + this.useReplaceRule + ", delTag=" + this.delTag + ", pdfImageWidth=" + this.pdfImageWidth + ')';
      }

      public int hashCode() {
         byte var10000 = this.reverseToc;
         if (var10000 != 0) {
            var10000 = 1;
         }

         int result = var10000;
         int result = result * 31 + Integer.hashCode(this.pageAnim);
         int var3 = result * 31;
         byte var10001 = this.reSegment;
         if (var10001 != 0) {
            var10001 = 1;
         }

         result = var3 + var10001;
         result = result * 31 + (this.imageStyle == null ? 0 : this.imageStyle.hashCode());
         var3 = result * 31;
         var10001 = this.useReplaceRule;
         if (var10001 != 0) {
            var10001 = 1;
         }

         result = var3 + var10001;
         result = result * 31 + Long.hashCode(this.delTag);
         result = result * 31 + Float.hashCode(this.pdfImageWidth);
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof Book.ReadConfig)) {
            return false;
         } else {
            Book.ReadConfig var2 = (Book.ReadConfig)other;
            if (this.reverseToc != var2.reverseToc) {
               return false;
            } else if (this.pageAnim != var2.pageAnim) {
               return false;
            } else if (this.reSegment != var2.reSegment) {
               return false;
            } else if (!Intrinsics.areEqual(this.imageStyle, var2.imageStyle)) {
               return false;
            } else if (this.useReplaceRule != var2.useReplaceRule) {
               return false;
            } else if (this.delTag != var2.delTag) {
               return false;
            } else {
               return Intrinsics.areEqual(this.pdfImageWidth, var2.pdfImageWidth);
            }
         }
      }

      public ReadConfig() {
         this(false, 0, false, (String)null, false, 0L, 0.0F, 127, (DefaultConstructorMarker)null);
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J(\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\f"},
      d2 = {"Lio/legado/app/data/entities/Book$Converters;", "", "()V", "readConfigToString", "", "config", "Lio/legado/app/data/entities/Book$ReadConfig;", "stringToReadConfig", "Lkotlin/Result;", "json", "stringToReadConfig-IoAF18A", "(Ljava/lang/String;)Ljava/lang/Object;", "reader-pro"}
   )
   public static final class Converters {
      @NotNull
      public final String readConfigToString(@Nullable Book.ReadConfig config) {
         String var2 = GsonExtensionsKt.getGSON().toJson(config);
         Intrinsics.checkNotNullExpressionValue(var2, "GSON.toJson(config)");
         return var2;
      }

      @NotNull
      public final Object stringToReadConfig_IoAF18A/* $FF was: stringToReadConfig-IoAF18A*/(@Nullable String json) {
         Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
         int $i$f$fromJsonObject = false;
         boolean var4 = false;

         Object var5;
         try {
            kotlin.Result.Companion var10 = Result.Companion;
            int var6 = false;
            int $i$f$genericType = false;
            Type var13 = (new Book$Converters$stringToReadConfig-IoAF18A$$inlined$fromJsonObject$1()).getType();
            Intrinsics.checkNotNullExpressionValue(var13, "object : TypeToken<T>() {}.type");
            Object var10000 = $this$fromJsonObject$iv.fromJson(json, var13);
            if (!(var10000 instanceof Book.ReadConfig)) {
               var10000 = null;
            }

            Book.ReadConfig var11 = (Book.ReadConfig)var10000;
            $i$f$genericType = false;
            var5 = Result.constructor-impl(var11);
         } catch (Throwable var9) {
            kotlin.Result.Companion var7 = Result.Companion;
            boolean var8 = false;
            var5 = Result.constructor-impl(ResultKt.createFailure(var9));
         }

         return var5;
      }
   }
}
