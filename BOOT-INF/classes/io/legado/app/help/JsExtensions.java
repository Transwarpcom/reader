package io.legado.app.help;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DESede;
import io.legado.app.adapters.ReaderAdapterHelper;
import io.legado.app.constant.AppConst;
import io.legado.app.data.entities.BaseSource;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.exception.NoStackTraceException;
import io.legado.app.help.http.CookieStore;
import io.legado.app.help.http.HttpHelperKt;
import io.legado.app.help.http.OkHttpUtilsKt;
import io.legado.app.help.http.SSLHelper;
import io.legado.app.help.http.StrResponse;
import io.legado.app.model.Debug;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.AnalyzeUrl;
import io.legado.app.model.analyzeRule.QueryTTF;
import io.legado.app.model.analyzeRule.RuleDataInterface;
import io.legado.app.utils.Base64;
import io.legado.app.utils.EncoderUtils;
import io.legado.app.utils.EncodingDetect;
import io.legado.app.utils.FileUtils;
import io.legado.app.utils.HtmlFormatter;
import io.legado.app.utils.LogUtilsKt;
import io.legado.app.utils.MD5Utils;
import io.legado.app.utils.NetworkUtils;
import io.legado.app.utils.StringExtensionsKt;
import io.legado.app.utils.StringUtils;
import io.legado.app.utils.ThrowableExtensionsKt;
import io.legado.app.utils.ZipUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.Result.Companion;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.Request.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u000f\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J*\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J2\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J*\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J*\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J2\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J*\u0010\u0011\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J*\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J*\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J*\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J#\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0018H\u0016¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u0005H\u0016J\u0010\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0014\u0010 \u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010 \u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0012\u0010!\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010!\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0012\u0010\"\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J\u001c\u0010\"\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\u001fH\u0016J\u0010\u0010$\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J\u001a\u0010$\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0005H\u0016J*\u0010)\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J*\u0010*\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J*\u0010+\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J*\u0010,\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u001a\u0010-\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0005H\u0016J\u001a\u0010/\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0005H\u0016J\u0018\u00100\u001a\u00020\u00052\u0006\u00101\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u0005H\u0016J\u0010\u00103\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u00103\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u0005H\u0016J$\u00105\u001a\u0002062\u0006\u0010\u0016\u001a\u00020\u00052\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000508H\u0016J\u001c\u00109\u001a\u00020\u00052\u0006\u0010:\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010;\u001a\u00020<2\u0006\u0010(\u001a\u00020\u0005H\u0016J\n\u0010=\u001a\u0004\u0018\u00010>H&J\n\u0010?\u001a\u0004\u0018\u00010@H&J\u0010\u0010A\u001a\u00020\u00052\u0006\u0010B\u001a\u00020\u0005H\u0016J\b\u0010C\u001a\u00020\u0005H&J\u001a\u0010D\u001a\u0004\u0018\u00010\u00032\u0006\u00102\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u0005H\u0016J\u0018\u0010E\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u0005H\u0016J \u0010E\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\u0005H\u0016J$\u0010G\u001a\u0002062\u0006\u0010\u0016\u001a\u00020\u00052\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000508H\u0016J\u0010\u0010H\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010I\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u0005H\u0016J\u0010\u0010J\u001a\u00020\u00052\u0006\u0010K\u001a\u00020\u0005H\u0016J\u0012\u0010L\u001a\u00020'2\b\u0010M\u001a\u0004\u0018\u00010\u0001H\u0016J\u0012\u0010N\u001a\u00020'2\b\u0010K\u001a\u0004\u0018\u00010\u0001H\u0016J\u0010\u0010O\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010P\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J,\u0010Q\u001a\u0002062\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010R\u001a\u00020\u00052\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000508H\u0016J\u0014\u0010S\u001a\u0004\u0018\u00010T2\b\u0010U\u001a\u0004\u0018\u00010\u0005H\u0016J\u0014\u0010V\u001a\u0004\u0018\u00010T2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010W\u001a\u00020\u0005H\u0016J\u0012\u0010X\u001a\u0004\u0018\u00010\u00032\u0006\u0010(\u001a\u00020\u0005H\u0016J\u0010\u0010Y\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u0005H\u0016J\u0018\u0010Y\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\u0005H\u0016J$\u0010Z\u001a\u00020\u00052\u0006\u0010[\u001a\u00020\u00052\b\u0010\\\u001a\u0004\u0018\u00010T2\b\u0010]\u001a\u0004\u0018\u00010TH\u0016J\u0010\u0010^\u001a\u00020\u00052\u0006\u0010_\u001a\u00020`H\u0016J\"\u0010a\u001a\u0004\u0018\u00010\u00052\u0006\u0010_\u001a\u00020`2\u0006\u0010b\u001a\u00020\u00052\u0006\u0010c\u001a\u00020\u001fH\u0016J\u0012\u0010d\u001a\u00020'2\b\u0010K\u001a\u0004\u0018\u00010\u0001H\u0016J2\u0010e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J2\u0010f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J2\u0010g\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J2\u0010h\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0010\u0010i\u001a\u00020\u00052\u0006\u0010j\u001a\u00020\u0005H\u0016J\u0010\u0010k\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J(\u0010l\u001a\u0004\u0018\u00010\u00052\b\u0010m\u001a\u0004\u0018\u00010\u00052\b\u00102\u001a\u0004\u0018\u00010\u00052\b\u0010n\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006o"},
   d2 = {"Lio/legado/app/help/JsExtensions;", "", "aesBase64DecodeToByteArray", "", "str", "", "key", "transformation", "iv", "aesBase64DecodeToString", "aesDecodeArgsBase64Str", "data", "mode", "padding", "aesDecodeToByteArray", "aesDecodeToString", "aesEncodeArgsBase64Str", "aesEncodeToBase64ByteArray", "aesEncodeToBase64String", "aesEncodeToByteArray", "aesEncodeToString", "ajax", "urlStr", "ajaxAll", "", "Lio/legado/app/help/http/StrResponse;", "urlList", "([Ljava/lang/String;)[Lio/legado/app/help/http/StrResponse;", "androidId", "base64Decode", "flags", "", "base64DecodeToByteArray", "base64Encode", "cacheFile", "saveTime", "connect", "header", "deleteFile", "", "path", "desBase64DecodeToString", "desDecodeToString", "desEncodeToBase64String", "desEncodeToString", "digestBase64Str", "algorithm", "digestHex", "downloadFile", "content", "url", "encodeURI", "enc", "get", "Lorg/jsoup/Connection$Response;", "headers", "", "getCookie", "tag", "getFile", "Ljava/io/File;", "getLogger", "Lio/legado/app/model/DebugLog;", "getSource", "Lio/legado/app/data/entities/BaseSource;", "getTxtInFolder", "unzipPath", "getUserNameSpace", "getZipByteArrayContent", "getZipStringContent", "charsetName", "head", "htmlFormat", "importScript", "log", "msg", "logType", "any", "longToast", "md5Encode", "md5Encode16", "post", "body", "queryBase64TTF", "Lio/legado/app/model/analyzeRule/QueryTTF;", "base64", "queryTTF", "randomUUID", "readFile", "readTxtFile", "replaceFont", "text", "font1", "font2", "timeFormat", "time", "", "timeFormatUTC", "format", "sh", "toast", "tripleDESDecodeArgsBase64Str", "tripleDESDecodeStr", "tripleDESEncodeArgsBase64Str", "tripleDESEncodeBase64Str", "unzipFile", "zipPath", "utf8ToGbk", "webView", "html", "js", "reader-pro"}
)
public interface JsExtensions {
   @Nullable
   BaseSource getSource();

   @NotNull
   String getUserNameSpace();

   @Nullable
   DebugLog getLogger();

   @Nullable
   String ajax(@NotNull String urlStr);

   @NotNull
   StrResponse[] ajaxAll(@NotNull String[] urlList);

   @NotNull
   StrResponse connect(@NotNull String urlStr);

   @NotNull
   StrResponse connect(@NotNull String urlStr, @Nullable String header);

   @Nullable
   String webView(@Nullable String html, @Nullable String url, @Nullable String js);

   @NotNull
   String importScript(@NotNull String path);

   @Nullable
   String cacheFile(@NotNull String urlStr);

   @Nullable
   String cacheFile(@NotNull String urlStr, int saveTime);

   @NotNull
   String getCookie(@NotNull String tag, @Nullable String key);

   @NotNull
   String downloadFile(@NotNull String content, @NotNull String url);

   @NotNull
   Response get(@NotNull String urlStr, @NotNull Map<String, String> headers);

   @NotNull
   Response head(@NotNull String urlStr, @NotNull Map<String, String> headers);

   @NotNull
   Response post(@NotNull String urlStr, @NotNull String body, @NotNull Map<String, String> headers);

   @NotNull
   String base64Decode(@NotNull String str);

   @NotNull
   String base64Decode(@NotNull String str, int flags);

   @Nullable
   byte[] base64DecodeToByteArray(@Nullable String str);

   @Nullable
   byte[] base64DecodeToByteArray(@Nullable String str, int flags);

   @Nullable
   String base64Encode(@NotNull String str);

   @Nullable
   String base64Encode(@NotNull String str, int flags);

   @NotNull
   String md5Encode(@NotNull String str);

   @NotNull
   String md5Encode16(@NotNull String str);

   @Nullable
   String timeFormatUTC(long time, @NotNull String format, int sh);

   @NotNull
   String timeFormat(long time);

   @NotNull
   String utf8ToGbk(@NotNull String str);

   @NotNull
   String encodeURI(@NotNull String str);

   @NotNull
   String encodeURI(@NotNull String str, @NotNull String enc);

   @NotNull
   String htmlFormat(@NotNull String str);

   @NotNull
   File getFile(@NotNull String path);

   @Nullable
   byte[] readFile(@NotNull String path);

   @NotNull
   String readTxtFile(@NotNull String path);

   @NotNull
   String readTxtFile(@NotNull String path, @NotNull String charsetName);

   void deleteFile(@NotNull String path);

   @NotNull
   String unzipFile(@NotNull String zipPath);

   @NotNull
   String getTxtInFolder(@NotNull String unzipPath);

   @NotNull
   String getZipStringContent(@NotNull String url, @NotNull String path);

   @NotNull
   String getZipStringContent(@NotNull String url, @NotNull String path, @NotNull String charsetName);

   @Nullable
   byte[] getZipByteArrayContent(@NotNull String url, @NotNull String path);

   @Nullable
   QueryTTF queryBase64TTF(@Nullable String base64);

   @Nullable
   QueryTTF queryTTF(@Nullable String str);

   @NotNull
   String replaceFont(@NotNull String text, @Nullable QueryTTF font1, @Nullable QueryTTF font2);

   void toast(@Nullable Object msg);

   void longToast(@Nullable Object msg);

   @NotNull
   String log(@NotNull String msg);

   void logType(@Nullable Object any);

   @NotNull
   String randomUUID();

   @Nullable
   byte[] aesDecodeToByteArray(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv);

   @Nullable
   String aesDecodeToString(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv);

   @Nullable
   byte[] aesBase64DecodeToByteArray(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv);

   @Nullable
   String aesBase64DecodeToString(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv);

   @Nullable
   byte[] aesEncodeToByteArray(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv);

   @Nullable
   String aesEncodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv);

   @Nullable
   byte[] aesEncodeToBase64ByteArray(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv);

   @Nullable
   String aesEncodeToBase64String(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv);

   @NotNull
   String androidId();

   @Nullable
   String aesDecodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv);

   @Nullable
   String tripleDESDecodeStr(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv);

   @Nullable
   String tripleDESDecodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv);

   @Nullable
   String aesEncodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv);

   @Nullable
   String desDecodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv);

   @Nullable
   String desBase64DecodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv);

   @Nullable
   String desEncodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv);

   @Nullable
   String desEncodeToBase64String(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv);

   @Nullable
   String tripleDESEncodeBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv);

   @Nullable
   String tripleDESEncodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv);

   @Nullable
   String digestHex(@NotNull String data, @NotNull String algorithm);

   @Nullable
   String digestBase64Str(@NotNull String data, @NotNull String algorithm);

   @Metadata(
      mv = {1, 5, 1},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      @Nullable
      public static String ajax(@NotNull JsExtensions this, @NotNull String urlStr) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         return (String)BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2<CoroutineScope, Continuation<? super String>, Object>((Continuation)null) {
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               Object var11 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               switch(this.label) {
               case 0:
                  ResultKt.throwOnFailure(var1);
                  String var2 = urlStr;
                  JsExtensions var3 = var0;
                  boolean var4 = false;

                  Object var5;
                  boolean var6;
                  boolean var8;
                  try {
                     Companion var15 = Result.Companion;
                     var6 = false;
                     AnalyzeUrl analyzeUrl = new AnalyzeUrl(var2, (String)null, (Integer)null, (String)null, (Integer)null, (String)null, var3.getSource(), (RuleDataInterface)null, (BookChapter)null, (Map)null, var3.getLogger(), 958, (DefaultConstructorMarker)null);
                     String var16 = AnalyzeUrl.getStrResponse$default(analyzeUrl, var2, (String)null, false, 6, (Object)null).getBody();
                     boolean var18 = false;
                     var5 = Result.constructor-impl(var16);
                  } catch (Throwable var12) {
                     Companion var7 = Result.Companion;
                     var8 = false;
                     var5 = Result.constructor-impl(ResultKt.createFailure(var12));
                  }

                  boolean var13 = false;
                  var4 = false;
                  Throwable var10000 = Result.exceptionOrNull-impl(var5);
                  Throwable var14;
                  if (var10000 != null) {
                     var14 = var10000;
                     var5 = false;
                     var6 = false;
                     var8 = false;
                     int var10 = false;
                     LogUtilsKt.printOnDebug(var14);
                  }

                  var13 = false;
                  var4 = false;
                  var14 = Result.exceptionOrNull-impl(var5);
                  Object var19;
                  if (var14 == null) {
                     var19 = var5;
                  } else {
                     var6 = false;
                     var19 = ThrowableExtensionsKt.getMsg(var14);
                  }

                  return var19;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }
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

      @NotNull
      public static StrResponse[] ajaxAll(@NotNull JsExtensions this, @NotNull String[] urlList) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlList, "urlList");
         return (StrResponse[])BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2<CoroutineScope, Continuation<? super StrResponse[]>, Object>((Continuation)null) {
            Object L$1;
            Object L$2;
            int I$0;
            int I$1;
            int I$2;
            int label;
            // $FF: synthetic field
            private Object L$0;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               Object var12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               Deferred[] asyncArray;
               int var5;
               int var6;
               StrResponse[] var7;
               switch(this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  CoroutineScope $this$runBlocking = (CoroutineScope)this.L$0;
                  final int var4 = 0;
                  var5 = urlList.length;

                  Deferred[] var13;
                  for(var13 = new Deferred[var5]; var4 < var5; ++var4) {
                     var13[var4] = BuildersKt.async$default($this$runBlocking, (CoroutineContext)Dispatchers.getIO(), (CoroutineStart)null, (Function2)(new Function2<CoroutineScope, Continuation<? super StrResponse>, Object>((Continuation)null) {
                        int label;

                        @Nullable
                        public final Object invokeSuspend(@NotNull Object $result) {
                           Object var4x = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                           switch(this.label) {
                           case 0:
                              ResultKt.throwOnFailure(var1);
                              String url = urlList[var4];
                              AnalyzeUrl analyzeUrl = new AnalyzeUrl(url, (String)null, (Integer)null, (String)null, (Integer)null, (String)null, var0.getSource(), (RuleDataInterface)null, (BookChapter)null, (Map)null, var0.getLogger(), 958, (DefaultConstructorMarker)null);
                              return AnalyzeUrl.getStrResponse$default(analyzeUrl, url, (String)null, false, 6, (Object)null);
                           default:
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }
                        }

                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
                           return (Continuation)(new <anonymous constructor>($completion));
                        }

                        @Nullable
                        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super StrResponse> p2) {
                           return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                        }
                     }), 2, (Object)null);
                  }

                  asyncArray = var13;
                  var5 = 0;
                  var6 = urlList.length;
                  var7 = new StrResponse[var6];
                  break;
               case 1:
                  int var10 = this.I$2;
                  var6 = this.I$1;
                  var5 = this.I$0;
                  StrResponse[] var9 = (StrResponse[])this.L$2;
                  var7 = (StrResponse[])this.L$1;
                  asyncArray = (Deferred[])this.L$0;
                  ResultKt.throwOnFailure($result);
                  var9[var10] = (StrResponse)$result;
                  ++var5;
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               while(var5 < var6) {
                  Deferred var10000 = asyncArray[var5];
                  Continuation var10001 = (Continuation)this;
                  this.L$0 = asyncArray;
                  this.L$1 = var7;
                  this.L$2 = var7;
                  this.I$0 = var5;
                  this.I$1 = var6;
                  this.I$2 = var5;
                  this.label = 1;
                  Object var14 = var10000.await(var10001);
                  if (var14 == var12) {
                     return var12;
                  }

                  Object var11 = var14;
                  var7[var5] = (StrResponse)var11;
                  ++var5;
               }

               return var7;
            }

            @NotNull
            public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
               Function2 var3 = new <anonymous constructor>($completion);
               var3.L$0 = value;
               return (Continuation)var3;
            }

            @Nullable
            public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super StrResponse[]> p2) {
               return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
            }
         }), 1, (Object)null);
      }

      @NotNull
      public static StrResponse connect(@NotNull JsExtensions this, @NotNull String urlStr) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         return (StrResponse)BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2<CoroutineScope, Continuation<? super StrResponse>, Object>((Continuation)null) {
            Object L$0;
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
            public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super StrResponse> p2) {
               return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
            }
         }), 1, (Object)null);
      }

      @NotNull
      public static StrResponse connect(@NotNull JsExtensions this, @NotNull String urlStr, @Nullable String header) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         return (StrResponse)BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2<CoroutineScope, Continuation<? super StrResponse>, Object>((Continuation)null) {
            Object L$0;
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
            public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super StrResponse> p2) {
               return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
            }
         }), 1, (Object)null);
      }

      @Nullable
      public static String webView(@NotNull JsExtensions this, @Nullable String html, @Nullable String url, @Nullable String js) {
         Intrinsics.checkNotNullParameter(var0, "this");
         return null;
      }

      @NotNull
      public static String importScript(@NotNull JsExtensions this, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(path, "path");
         String var10000;
         if (StringsKt.startsWith$default(path, "http", false, 2, (Object)null)) {
            String var3 = var0.cacheFile(path);
            var10000 = var3 == null ? "" : var3;
         } else {
            var10000 = StringsKt.startsWith$default(path, "/storage", false, 2, (Object)null) ? FileUtils.readText$default(FileUtils.INSTANCE, path, (String)null, 2, (Object)null) : var0.readTxtFile(path);
         }

         String result = var10000;
         if (StringsKt.isBlank((CharSequence)result)) {
            throw new NoStackTraceException(Intrinsics.stringPlus(path, " 内容获取失败或者为空"));
         } else {
            return result;
         }
      }

      @Nullable
      public static String cacheFile(@NotNull JsExtensions this, @NotNull String urlStr) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         return var0.cacheFile(urlStr, 0);
      }

      @Nullable
      public static String cacheFile(@NotNull JsExtensions this, @NotNull String urlStr, int saveTime) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         String key = var0.md5Encode16(urlStr);
         CacheManager cacheInstance = new CacheManager(var0.getUserNameSpace());
         String cache = cacheInstance.getFile(key);
         CharSequence var6 = (CharSequence)cache;
         boolean var7 = false;
         boolean var8 = false;
         if (var6 == null || StringsKt.isBlank(var6)) {
            var0.log(Intrinsics.stringPlus("首次下载 ", urlStr));
            String var9 = var0.ajax(urlStr);
            if (var9 == null) {
               return null;
            } else {
               cacheInstance.putFile(key, var9, saveTime);
               return var9;
            }
         } else {
            return cache;
         }
      }

      // $FF: synthetic method
      public static String cacheFile$default(JsExtensions var0, String var1, int var2, int var3, Object var4) {
         if (var4 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cacheFile");
         } else {
            if ((var3 & 2) != 0) {
               var2 = 0;
            }

            return var0.cacheFile(var1, var2);
         }
      }

      @NotNull
      public static String getCookie(@NotNull JsExtensions this, @NotNull String tag, @Nullable String key) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(tag, "tag");
         CookieStore cookieStore = new CookieStore(var0.getUserNameSpace());
         String cookie = cookieStore.getCookie(tag);
         Map cookieMap = cookieStore.cookieToMap(cookie);
         String var10000;
         if (key != null) {
            String var6 = (String)cookieMap.get(key);
            var10000 = var6 == null ? "" : var6;
         } else {
            var10000 = cookie;
         }

         return var10000;
      }

      // $FF: synthetic method
      public static String getCookie$default(JsExtensions var0, String var1, String var2, int var3, Object var4) {
         if (var4 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCookie");
         } else {
            if ((var3 & 2) != 0) {
               var2 = null;
            }

            return var0.getCookie(var1, var2);
         }
      }

      @NotNull
      public static String downloadFile(@NotNull JsExtensions this, @NotNull String content, @NotNull String url) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(content, "content");
         Intrinsics.checkNotNullParameter(url, "url");
         String zipPath = (new AnalyzeUrl(url, (String)null, (Integer)null, (String)null, (Integer)null, (String)null, (BaseSource)null, (RuleDataInterface)null, (BookChapter)null, (Map)null, (DebugLog)null, 2046, (DefaultConstructorMarker)null)).getType();
         if (zipPath == null) {
            return "";
         } else {
            FileUtils var10000 = FileUtils.INSTANCE;
            File var10001 = FileUtils.INSTANCE.createFolderIfNotExist(FileUtils.INSTANCE.getCachePath());
            String[] var5 = new String[]{MD5Utils.INSTANCE.md5Encode16(url) + '.' + zipPath};
            zipPath = var10000.getPath(var10001, var5);
            FileUtils.INSTANCE.deleteFile(zipPath);
            File zipFile = FileUtils.INSTANCE.createFileIfNotExist(zipPath);
            byte[] var6 = StringUtils.INSTANCE.hexStringToByte(content);
            boolean var7 = false;
            boolean var8 = false;
            int var10 = false;
            boolean var12 = false;
            boolean var14 = false;
            if (var6.length != 0) {
               FilesKt.writeBytes(zipFile, var6);
            }

            int var16 = FileUtils.INSTANCE.getCachePath().length();
            var8 = false;
            if (zipPath == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            } else {
               String var17 = zipPath.substring(var16);
               Intrinsics.checkNotNullExpressionValue(var17, "(this as java.lang.String).substring(startIndex)");
               return var17;
            }
         }
      }

      @NotNull
      public static Response get(@NotNull JsExtensions this, @NotNull String urlStr, @NotNull Map<String, String> headers) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         Intrinsics.checkNotNullParameter(headers, "headers");
         Response response = Jsoup.connect(urlStr).sslSocketFactory(SSLHelper.INSTANCE.getUnsafeSSLSocketFactory()).ignoreContentType(true).followRedirects(false).headers(headers).method(Method.GET).execute();
         Map cookies = response.cookies();
         CookieStore cookieStore = new CookieStore(var0.getUserNameSpace());
         String var6 = cookieStore.mapToCookie(cookies);
         if (var6 != null) {
            boolean var8 = false;
            boolean var9 = false;
            int var11 = false;
            String domain = NetworkUtils.INSTANCE.getSubDomain(urlStr);
            cookieStore.replaceCookie(Intrinsics.stringPlus(domain, "_cookieJar"), var6);
         }

         Intrinsics.checkNotNullExpressionValue(response, "response");
         return response;
      }

      @NotNull
      public static Response head(@NotNull JsExtensions this, @NotNull String urlStr, @NotNull Map<String, String> headers) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         Intrinsics.checkNotNullParameter(headers, "headers");
         Response response = Jsoup.connect(urlStr).sslSocketFactory(SSLHelper.INSTANCE.getUnsafeSSLSocketFactory()).ignoreContentType(true).followRedirects(false).headers(headers).method(Method.HEAD).execute();
         Map cookies = response.cookies();
         CookieStore cookieStore = new CookieStore(var0.getUserNameSpace());
         String var6 = cookieStore.mapToCookie(cookies);
         if (var6 != null) {
            boolean var8 = false;
            boolean var9 = false;
            int var11 = false;
            String domain = NetworkUtils.INSTANCE.getSubDomain(urlStr);
            cookieStore.replaceCookie(Intrinsics.stringPlus(domain, "_cookieJar"), var6);
         }

         Intrinsics.checkNotNullExpressionValue(response, "response");
         return response;
      }

      @NotNull
      public static Response post(@NotNull JsExtensions this, @NotNull String urlStr, @NotNull String body, @NotNull Map<String, String> headers) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(urlStr, "urlStr");
         Intrinsics.checkNotNullParameter(body, "body");
         Intrinsics.checkNotNullParameter(headers, "headers");
         Response response = Jsoup.connect(urlStr).sslSocketFactory(SSLHelper.INSTANCE.getUnsafeSSLSocketFactory()).ignoreContentType(true).followRedirects(false).requestBody(body).headers(headers).method(Method.POST).execute();
         Map cookies = response.cookies();
         CookieStore cookieStore = new CookieStore(var0.getUserNameSpace());
         String var7 = cookieStore.mapToCookie(cookies);
         if (var7 != null) {
            boolean var9 = false;
            boolean var10 = false;
            int var12 = false;
            String domain = NetworkUtils.INSTANCE.getSubDomain(urlStr);
            cookieStore.replaceCookie(Intrinsics.stringPlus(domain, "_cookieJar"), var7);
         }

         Intrinsics.checkNotNullExpressionValue(response, "response");
         return response;
      }

      @NotNull
      public static String base64Decode(@NotNull JsExtensions this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return EncoderUtils.INSTANCE.base64Decode(str, 2);
      }

      @NotNull
      public static String base64Decode(@NotNull JsExtensions this, @NotNull String str, int flags) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return EncoderUtils.INSTANCE.base64Decode(str, flags);
      }

      @Nullable
      public static byte[] base64DecodeToByteArray(@NotNull JsExtensions this, @Nullable String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         CharSequence var2 = (CharSequence)str;
         boolean var3 = false;
         boolean var4 = false;
         return var2 == null || StringsKt.isBlank(var2) ? null : Base64.decode((String)str, 0);
      }

      @Nullable
      public static byte[] base64DecodeToByteArray(@NotNull JsExtensions this, @Nullable String str, int flags) {
         Intrinsics.checkNotNullParameter(var0, "this");
         CharSequence var3 = (CharSequence)str;
         boolean var4 = false;
         boolean var5 = false;
         return var3 == null || StringsKt.isBlank(var3) ? null : Base64.decode(str, flags);
      }

      @Nullable
      public static String base64Encode(@NotNull JsExtensions this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return EncoderUtils.INSTANCE.base64Encode(str, 2);
      }

      @Nullable
      public static String base64Encode(@NotNull JsExtensions this, @NotNull String str, int flags) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return EncoderUtils.INSTANCE.base64Encode(str, flags);
      }

      @NotNull
      public static String md5Encode(@NotNull JsExtensions this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return MD5Utils.INSTANCE.md5Encode(str);
      }

      @NotNull
      public static String md5Encode16(@NotNull JsExtensions this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return MD5Utils.INSTANCE.md5Encode16(str);
      }

      @Nullable
      public static String timeFormatUTC(@NotNull JsExtensions this, long time, @NotNull String format, int sh) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(format, "format");
         SimpleTimeZone utc = new SimpleTimeZone(sh, "UTC");
         SimpleDateFormat var6 = new SimpleDateFormat(format, Locale.getDefault());
         boolean var7 = false;
         boolean var8 = false;
         int var10 = false;
         var6.setTimeZone((TimeZone)utc);
         return var6.format(new Date(time));
      }

      @NotNull
      public static String timeFormat(@NotNull JsExtensions this, long time) {
         Intrinsics.checkNotNullParameter(var0, "this");
         String var3 = AppConst.INSTANCE.getDateFormat().format(new Date(time));
         Intrinsics.checkNotNullExpressionValue(var3, "dateFormat.format(Date(time))");
         return var3;
      }

      @NotNull
      public static String utf8ToGbk(@NotNull JsExtensions this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         String var4 = "UTF-8";
         boolean var5 = false;
         Charset var10000 = Charset.forName(var4);
         Intrinsics.checkNotNullExpressionValue(var10000, "Charset.forName(charsetName)");
         Charset var8 = var10000;
         var5 = false;
         byte[] var13 = str.getBytes(var8);
         Intrinsics.checkNotNullExpressionValue(var13, "(this as java.lang.String).getBytes(charset)");
         byte[] var3 = var13;
         boolean var9 = false;
         String utf8 = new String(var3, Charsets.UTF_8);
         Charset var11 = Charsets.UTF_8;
         boolean var6 = false;
         var13 = utf8.getBytes(var11);
         Intrinsics.checkNotNullExpressionValue(var13, "(this as java.lang.String).getBytes(charset)");
         byte[] var10 = var13;
         String var12 = "UTF-8";
         var6 = false;
         var10000 = Charset.forName(var12);
         Intrinsics.checkNotNullExpressionValue(var10000, "Charset.forName(charsetName)");
         var11 = var10000;
         var6 = false;
         String unicode = new String(var10, var11);
         var12 = "GBK";
         var6 = false;
         var10000 = Charset.forName(var12);
         Intrinsics.checkNotNullExpressionValue(var10000, "Charset.forName(charsetName)");
         var11 = var10000;
         var6 = false;
         var13 = unicode.getBytes(var11);
         Intrinsics.checkNotNullExpressionValue(var13, "(this as java.lang.String).getBytes(charset)");
         var10 = var13;
         var5 = false;
         return new String(var10, Charsets.UTF_8);
      }

      @NotNull
      public static String encodeURI(@NotNull JsExtensions this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");

         String var2;
         try {
            var2 = URLEncoder.encode(str, "UTF-8");
            Intrinsics.checkNotNullExpressionValue(var2, "{\n            URLEncoder.encode(str, \"UTF-8\")\n        }");
            var2 = var2;
         } catch (Exception var4) {
            var2 = "";
         }

         return var2;
      }

      @NotNull
      public static String encodeURI(@NotNull JsExtensions this, @NotNull String str, @NotNull String enc) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         Intrinsics.checkNotNullParameter(enc, "enc");

         String var3;
         try {
            var3 = URLEncoder.encode(str, enc);
            Intrinsics.checkNotNullExpressionValue(var3, "{\n            URLEncoder.encode(str, enc)\n        }");
            var3 = var3;
         } catch (Exception var5) {
            var3 = "";
         }

         return var3;
      }

      @NotNull
      public static String htmlFormat(@NotNull JsExtensions this, @NotNull String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         return HtmlFormatter.formatKeepImg$default(HtmlFormatter.INSTANCE, str, (URL)null, 2, (Object)null);
      }

      @NotNull
      public static File getFile(@NotNull JsExtensions this, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(path, "path");
         String cachePath = ReaderAdapterHelper.INSTANCE.getAdapter().getCacheDir();
         String var4 = File.separator;
         Intrinsics.checkNotNullExpressionValue(var4, "separator");
         String aPath = StringsKt.startsWith$default(path, var4, false, 2, (Object)null) ? Intrinsics.stringPlus(cachePath, path) : cachePath + File.separator + path;
         return new File(aPath);
      }

      @Nullable
      public static byte[] readFile(@NotNull JsExtensions this, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(path, "path");
         File file = var0.getFile(path);
         return file.exists() ? FilesKt.readBytes(file) : null;
      }

      @NotNull
      public static String readTxtFile(@NotNull JsExtensions this, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(path, "path");
         File file = var0.getFile(path);
         if (file.exists()) {
            String charsetName = EncodingDetect.INSTANCE.getEncode(file);
            byte[] var4 = FilesKt.readBytes(file);
            boolean var5 = false;
            Charset var10000 = Charset.forName(charsetName);
            Intrinsics.checkNotNullExpressionValue(var10000, "Charset.forName(charsetName)");
            Charset var7 = var10000;
            boolean var6 = false;
            return new String(var4, var7);
         } else {
            return "";
         }
      }

      @NotNull
      public static String readTxtFile(@NotNull JsExtensions this, @NotNull String path, @NotNull String charsetName) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(path, "path");
         Intrinsics.checkNotNullParameter(charsetName, "charsetName");
         File file = var0.getFile(path);
         if (file.exists()) {
            byte[] var4 = FilesKt.readBytes(file);
            boolean var5 = false;
            Charset var10000 = Charset.forName(charsetName);
            Intrinsics.checkNotNullExpressionValue(var10000, "Charset.forName(charsetName)");
            Charset var7 = var10000;
            boolean var6 = false;
            return new String(var4, var7);
         } else {
            return "";
         }
      }

      public static void deleteFile(@NotNull JsExtensions this, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(path, "path");
         File file = var0.getFile(path);
         FileUtils.INSTANCE.delete(file, true);
      }

      @NotNull
      public static String unzipFile(@NotNull JsExtensions this, @NotNull String zipPath) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(zipPath, "zipPath");
         CharSequence var2 = (CharSequence)zipPath;
         boolean var3 = false;
         if (var2.length() == 0) {
            return "";
         } else {
            FileUtils var10000 = FileUtils.INSTANCE;
            File var10001 = FileUtils.INSTANCE.createFolderIfNotExist(FileUtils.INSTANCE.getCachePath());
            String[] var9 = new String[]{FileUtils.INSTANCE.getNameExcludeExtension(zipPath)};
            String unzipPath = var10000.getPath(var10001, var9);
            FileUtils.INSTANCE.deleteFile(unzipPath);
            File zipFile = var0.getFile(zipPath);
            File unzipFolder = FileUtils.INSTANCE.createFolderIfNotExist(unzipPath);
            ZipUtils.INSTANCE.unzipFile(zipFile, unzipFolder);
            var10000 = FileUtils.INSTANCE;
            String var5 = zipFile.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(var5, "zipFile.absolutePath");
            var10000.deleteFile(var5);
            int var6 = FileUtils.INSTANCE.getCachePath().length();
            boolean var7 = false;
            if (unzipPath == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            } else {
               String var11 = unzipPath.substring(var6);
               Intrinsics.checkNotNullExpressionValue(var11, "(this as java.lang.String).substring(startIndex)");
               return var11;
            }
         }
      }

      @NotNull
      public static String getTxtInFolder(@NotNull JsExtensions this, @NotNull String unzipPath) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(unzipPath, "unzipPath");
         CharSequence var2 = (CharSequence)unzipPath;
         boolean var3 = false;
         if (var2.length() == 0) {
            return "";
         } else {
            File unzipFolder = var0.getFile(unzipPath);
            StringBuilder contents = new StringBuilder();
            File[] var4 = unzipFolder.listFiles();
            boolean var5 = false;
            boolean var6 = false;
            int var8 = false;
            if (var4 != null) {
               File[] var9 = var4;
               int var10 = 0;
               int var11 = var4.length;

               while(var10 < var11) {
                  File f = var9[var10];
                  ++var10;
                  EncodingDetect var10000 = EncodingDetect.INSTANCE;
                  Intrinsics.checkNotNullExpressionValue(f, "f");
                  String charsetName = var10000.getEncode(f);
                  byte[] var14 = FilesKt.readBytes(f);
                  boolean var15 = false;
                  Charset var10001 = Charset.forName(charsetName);
                  Intrinsics.checkNotNullExpressionValue(var10001, "Charset.forName(charsetName)");
                  Charset var20 = var10001;
                  boolean var16 = false;
                  contents.append(new String(var14, var20)).append("\n");
               }

               contents.deleteCharAt(contents.length() - 1);
            }

            FileUtils var21 = FileUtils.INSTANCE;
            String var19 = unzipFolder.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(var19, "unzipFolder.absolutePath");
            var21.deleteFile(var19);
            var19 = contents.toString();
            Intrinsics.checkNotNullExpressionValue(var19, "contents.toString()");
            return var19;
         }
      }

      @NotNull
      public static String getZipStringContent(@NotNull JsExtensions this, @NotNull String url, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(url, "url");
         Intrinsics.checkNotNullParameter(path, "path");
         byte[] var4 = var0.getZipByteArrayContent(url, path);
         if (var4 == null) {
            return "";
         } else {
            byte[] byteArray = var4;
            String charsetName = EncodingDetect.INSTANCE.getEncode(var4);
            Charset var5 = Charset.forName(charsetName);
            Intrinsics.checkNotNullExpressionValue(var5, "forName(charsetName)");
            boolean var6 = false;
            return new String(byteArray, var5);
         }
      }

      @NotNull
      public static String getZipStringContent(@NotNull JsExtensions this, @NotNull String url, @NotNull String path, @NotNull String charsetName) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(url, "url");
         Intrinsics.checkNotNullParameter(path, "path");
         Intrinsics.checkNotNullParameter(charsetName, "charsetName");
         byte[] var5 = var0.getZipByteArrayContent(url, path);
         if (var5 == null) {
            return "";
         } else {
            byte[] byteArray = var5;
            Charset var7 = Charset.forName(charsetName);
            Intrinsics.checkNotNullExpressionValue(var7, "forName(charsetName)");
            boolean var6 = false;
            return new String(byteArray, var7);
         }
      }

      @Nullable
      public static byte[] getZipByteArrayContent(@NotNull JsExtensions this, @NotNull String url, @NotNull String path) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(url, "url");
         Intrinsics.checkNotNullParameter(path, "path");
         byte[] bytes = !StringsKt.startsWith$default(url, "http://", false, 2, (Object)null) && !StringsKt.startsWith$default(url, "https://", false, 2, (Object)null) ? StringUtils.INSTANCE.hexStringToByte(url) : (byte[])BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2<CoroutineScope, Continuation<? super byte[]>, Object>((Continuation)null) {
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               Object var2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               Object var10000;
               switch(this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  OkHttpClient var3 = HttpHelperKt.getOkHttpClient();
                  Function1 var10002 = (Function1)(new Function1<Builder, Unit>() {
                     public final void invoke(@NotNull Builder $this$newCall) {
                        Intrinsics.checkNotNullParameter($this$newCall, "$this$newCall");
                        $this$newCall.url(url);
                     }
                  });
                  Continuation var10003 = (Continuation)this;
                  this.label = 1;
                  var10000 = OkHttpUtilsKt.newCall$default(var3, 0, var10002, var10003, 1, (Object)null);
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

               return ((ResponseBody)var10000).bytes();
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
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         ZipInputStream zis = new ZipInputStream((InputStream)(new ByteArrayInputStream(bytes)));

         for(ZipEntry entry = zis.getNextEntry(); entry != null; entry = zis.getNextEntry()) {
            if (entry.getName().equals(path)) {
               Closeable var7 = (Closeable)zis;
               boolean var8 = false;
               boolean var9 = false;
               Throwable var17 = (Throwable)null;

               try {
                  ZipInputStream it = (ZipInputStream)var7;
                  int var12 = false;
                  long var18 = ByteStreamsKt.copyTo$default((InputStream)it, (OutputStream)bos, 0, 2, (Object)null);
               } catch (Throwable var15) {
                  var17 = var15;
                  throw var15;
               } finally {
                  CloseableKt.closeFinally(var7, var17);
               }

               return bos.toByteArray();
            }
         }

         Debug.INSTANCE.log("getZipContent 未发现内容");
         return null;
      }

      @Nullable
      public static QueryTTF queryBase64TTF(@NotNull JsExtensions this, @Nullable String base64) {
         Intrinsics.checkNotNullParameter(var0, "this");
         byte[] var2 = var0.base64DecodeToByteArray(base64);
         if (var2 == null) {
            return null;
         } else {
            boolean var4 = false;
            boolean var5 = false;
            int var7 = false;
            return new QueryTTF(var2);
         }
      }

      @Nullable
      public static QueryTTF queryTTF(@NotNull JsExtensions this, @Nullable String str) {
         Intrinsics.checkNotNullParameter(var0, "this");
         if (str == null) {
            return null;
         } else {
            final String key = var0.md5Encode16(str);
            final ObjectRef cacheInstance = new ObjectRef();
            cacheInstance.element = new CacheManager(var0.getUserNameSpace());
            QueryTTF qTTF = ((CacheManager)cacheInstance.element).getQueryTTF(key);
            if (qTTF != null) {
               return qTTF;
            } else {
               byte[] font = StringExtensionsKt.isAbsUrl(str) ? (byte[])BuildersKt.runBlocking$default((CoroutineContext)null, (Function2)(new Function2<CoroutineScope, Continuation<? super byte[]>, Object>((Continuation)null) {
                  int label;

                  @Nullable
                  public final Object invokeSuspend(@NotNull Object $result) {
                     Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     byte[] x;
                     Object var10000;
                     switch(this.label) {
                     case 0:
                        ResultKt.throwOnFailure($result);
                        x = ((CacheManager)cacheInstance.element).getByteArray(key);
                        if (x != null) {
                           return x;
                        }

                        OkHttpClient var11 = HttpHelperKt.getOkHttpClient();
                        Function1 var10002 = (Function1)(new Function1<Builder, Unit>() {
                           public final void invoke(@NotNull Builder $this$newCall) {
                              Intrinsics.checkNotNullParameter($this$newCall, "$this$newCall");
                              $this$newCall.url(str);
                           }
                        });
                        Continuation var10003 = (Continuation)this;
                        this.label = 1;
                        var10000 = OkHttpUtilsKt.newCall$default(var11, 0, var10002, var10003, 1, (Object)null);
                        if (var10000 == var10) {
                           return var10;
                        }
                        break;
                     case 1:
                        ResultKt.throwOnFailure($result);
                        var10000 = $result;
                        break;
                     default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     x = ((ResponseBody)var10000).bytes();
                     ObjectRef var4 = cacheInstance;
                     String var5 = key;
                     boolean var6 = false;
                     boolean var7 = false;
                     int var9 = false;
                     CacheManager.put$default((CacheManager)var4.element, var5, x, 0, 4, (Object)null);
                     return x;
                  }

                  @NotNull
                  public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
                     return (Continuation)(new <anonymous constructor>($completion));
                  }

                  @Nullable
                  public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super byte[]> p2) {
                     return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                  }
               }), 1, (Object)null) : (StringsKt.indexOf$default((CharSequence)str, "storage/", 0, false, 6, (Object)null) > 0 ? FilesKt.readBytes(new File(str)) : var0.base64DecodeToByteArray(str));
               if (font == null) {
                  return null;
               } else {
                  qTTF = new QueryTTF(font);
                  CacheManager.put$default((CacheManager)cacheInstance.element, key, qTTF, 0, 4, (Object)null);
                  return qTTF;
               }
            }
         }
      }

      @NotNull
      public static String replaceFont(@NotNull JsExtensions this, @NotNull String text, @Nullable QueryTTF font1, @Nullable QueryTTF font2) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(text, "text");
         if (font1 != null && font2 != null) {
            boolean $i$f$forEachIndexed = false;
            char[] var10000 = text.toCharArray();
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).toCharArray()");
            char[] contentArray = var10000;
            $i$f$forEachIndexed = false;
            int index$iv = 0;
            char[] var8 = contentArray;
            int var9 = contentArray.length;

            for(int var10 = 0; var10 < var9; ++var10) {
               char item$iv = var8[var10];
               int index = index$iv++;
               int var14 = false;
               boolean var16 = false;
               if (font1.inLimit(item$iv)) {
                  int code = font2.getCodeByGlyf(font1.getGlyfByCode(item$iv));
                  if (code != 0) {
                     contentArray[index] = (char)code;
                  }
               }
            }

            return ArraysKt.joinToString$default(contentArray, (CharSequence)"", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
         } else {
            return text;
         }
      }

      public static void toast(@NotNull JsExtensions this, @Nullable Object msg) {
         Intrinsics.checkNotNullParameter(var0, "this");
         DebugLog var2 = var0.getLogger();
         if (var2 != null) {
            var2.log(Intrinsics.stringPlus("toast: ", msg));
         }

         Debug.INSTANCE.log(Intrinsics.stringPlus("toast: ", msg));
      }

      public static void longToast(@NotNull JsExtensions this, @Nullable Object msg) {
         Intrinsics.checkNotNullParameter(var0, "this");
         DebugLog var2 = var0.getLogger();
         if (var2 != null) {
            var2.log(Intrinsics.stringPlus("longToast: ", msg));
         }

         Debug.INSTANCE.log(Intrinsics.stringPlus("longToast: ", msg));
      }

      @NotNull
      public static String log(@NotNull JsExtensions this, @NotNull String msg) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(msg, "msg");
         DebugLog var2 = var0.getLogger();
         if (var2 != null) {
            var2.log(msg);
         }

         Debug.INSTANCE.log(msg);
         return msg;
      }

      public static void logType(@NotNull JsExtensions this, @Nullable Object any) {
         Intrinsics.checkNotNullParameter(var0, "this");
         if (any == null) {
            var0.log("null");
         } else {
            String var2 = any.getClass().getName();
            Intrinsics.checkNotNullExpressionValue(var2, "any.javaClass.name");
            var0.log(var2);
         }

      }

      @NotNull
      public static String randomUUID(@NotNull JsExtensions this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         String var1 = UUID.randomUUID().toString();
         Intrinsics.checkNotNullExpressionValue(var1, "randomUUID().toString()");
         return var1;
      }

      @Nullable
      public static byte[] aesDecodeToByteArray(@NotNull JsExtensions this, @NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");

         byte[] var5;
         try {
            var5 = EncoderUtils.INSTANCE.decryptAES(StringsKt.encodeToByteArray(str), StringsKt.encodeToByteArray(key), transformation, StringsKt.encodeToByteArray(iv));
         } catch (Exception var8) {
            LogUtilsKt.printOnDebug((Throwable)var8);
            String var7 = var8.getLocalizedMessage();
            var0.log(var7 == null ? "aesDecodeToByteArrayERROR" : var7);
            var5 = (byte[])null;
         }

         return var5;
      }

      @Nullable
      public static String aesDecodeToString(@NotNull JsExtensions this, @NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         byte[] var5 = var0.aesDecodeToByteArray(str, key, transformation, iv);
         String var10000;
         if (var5 == null) {
            var10000 = null;
         } else {
            boolean var7 = false;
            boolean var8 = false;
            int var10 = false;
            boolean var11 = false;
            var10000 = new String(var5, Charsets.UTF_8);
         }

         return var10000;
      }

      @Nullable
      public static byte[] aesBase64DecodeToByteArray(@NotNull JsExtensions this, @NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");

         byte[] var5;
         try {
            var5 = EncoderUtils.INSTANCE.decryptBase64AES(StringsKt.encodeToByteArray(str), StringsKt.encodeToByteArray(key), transformation, StringsKt.encodeToByteArray(iv));
         } catch (Exception var8) {
            LogUtilsKt.printOnDebug((Throwable)var8);
            String var7 = var8.getLocalizedMessage();
            var0.log(var7 == null ? "aesDecodeToByteArrayERROR" : var7);
            var5 = (byte[])null;
         }

         return var5;
      }

      @Nullable
      public static String aesBase64DecodeToString(@NotNull JsExtensions this, @NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(str, "str");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         byte[] var5 = var0.aesBase64DecodeToByteArray(str, key, transformation, iv);
         String var10000;
         if (var5 == null) {
            var10000 = null;
         } else {
            boolean var7 = false;
            boolean var8 = false;
            int var10 = false;
            boolean var11 = false;
            var10000 = new String(var5, Charsets.UTF_8);
         }

         return var10000;
      }

      @Nullable
      public static byte[] aesEncodeToByteArray(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");

         byte[] var5;
         try {
            var5 = EncoderUtils.INSTANCE.encryptAES(StringsKt.encodeToByteArray(data), StringsKt.encodeToByteArray(key), transformation, StringsKt.encodeToByteArray(iv));
         } catch (Exception var8) {
            LogUtilsKt.printOnDebug((Throwable)var8);
            String var7 = var8.getLocalizedMessage();
            var0.log(var7 == null ? "aesEncodeToByteArrayERROR" : var7);
            var5 = (byte[])null;
         }

         return var5;
      }

      @Nullable
      public static String aesEncodeToString(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         byte[] var5 = var0.aesEncodeToByteArray(data, key, transformation, iv);
         String var10000;
         if (var5 == null) {
            var10000 = null;
         } else {
            boolean var7 = false;
            boolean var8 = false;
            int var10 = false;
            boolean var11 = false;
            var10000 = new String(var5, Charsets.UTF_8);
         }

         return var10000;
      }

      @Nullable
      public static byte[] aesEncodeToBase64ByteArray(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");

         byte[] var5;
         try {
            var5 = EncoderUtils.INSTANCE.encryptAES2Base64(StringsKt.encodeToByteArray(data), StringsKt.encodeToByteArray(key), transformation, StringsKt.encodeToByteArray(iv));
         } catch (Exception var8) {
            LogUtilsKt.printOnDebug((Throwable)var8);
            String var7 = var8.getLocalizedMessage();
            var0.log(var7 == null ? "aesEncodeToBase64ByteArrayERROR" : var7);
            var5 = (byte[])null;
         }

         return var5;
      }

      @Nullable
      public static String aesEncodeToBase64String(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         byte[] var5 = var0.aesEncodeToBase64ByteArray(data, key, transformation, iv);
         String var10000;
         if (var5 == null) {
            var10000 = null;
         } else {
            boolean var7 = false;
            boolean var8 = false;
            int var10 = false;
            boolean var11 = false;
            var10000 = new String(var5, Charsets.UTF_8);
         }

         return var10000;
      }

      @NotNull
      public static String androidId(@NotNull JsExtensions this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         return "";
      }

      @Nullable
      public static String aesDecodeArgsBase64Str(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(padding, "padding");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return (new AES(mode, padding, Base64.decode((String)key, 2), Base64.decode((String)iv, 2))).decryptStr(data);
      }

      @Nullable
      public static String tripleDESDecodeStr(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(padding, "padding");
         Intrinsics.checkNotNullParameter(iv, "iv");
         Charset var7 = Charsets.UTF_8;
         boolean var8 = false;
         byte[] var10004 = key.getBytes(var7);
         Intrinsics.checkNotNullExpressionValue(var10004, "(this as java.lang.String).getBytes(charset)");
         var7 = Charsets.UTF_8;
         var8 = false;
         byte[] var10005 = iv.getBytes(var7);
         Intrinsics.checkNotNullExpressionValue(var10005, "(this as java.lang.String).getBytes(charset)");
         return (new DESede(mode, padding, var10004, var10005)).decryptStr(data);
      }

      @Nullable
      public static String tripleDESDecodeArgsBase64Str(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(padding, "padding");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return (new DESede(mode, padding, Base64.decode((String)key, 2), Base64.decode((String)iv, 2))).decryptStr(data);
      }

      @Nullable
      public static String aesEncodeArgsBase64Str(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(padding, "padding");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return (new AES(mode, padding, Base64.decode((String)key, 2), Base64.decode((String)iv, 2))).encryptBase64(data);
      }

      @Nullable
      public static String desDecodeToString(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         byte[] var5 = EncoderUtils.INSTANCE.decryptDES(StringsKt.encodeToByteArray(data), StringsKt.encodeToByteArray(key), transformation, StringsKt.encodeToByteArray(iv));
         String var10000;
         if (var5 == null) {
            var10000 = null;
         } else {
            boolean var7 = false;
            boolean var8 = false;
            int var10 = false;
            boolean var11 = false;
            var10000 = new String(var5, Charsets.UTF_8);
         }

         return var10000;
      }

      @Nullable
      public static String desBase64DecodeToString(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         byte[] var5 = EncoderUtils.INSTANCE.decryptBase64DES(StringsKt.encodeToByteArray(data), StringsKt.encodeToByteArray(key), transformation, StringsKt.encodeToByteArray(iv));
         String var10000;
         if (var5 == null) {
            var10000 = null;
         } else {
            boolean var7 = false;
            boolean var8 = false;
            int var10 = false;
            boolean var11 = false;
            var10000 = new String(var5, Charsets.UTF_8);
         }

         return var10000;
      }

      @Nullable
      public static String desEncodeToString(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         byte[] var5 = EncoderUtils.INSTANCE.encryptDES(StringsKt.encodeToByteArray(data), StringsKt.encodeToByteArray(key), transformation, StringsKt.encodeToByteArray(iv));
         String var10000;
         if (var5 == null) {
            var10000 = null;
         } else {
            boolean var7 = false;
            boolean var8 = false;
            int var10 = false;
            boolean var11 = false;
            var10000 = new String(var5, Charsets.UTF_8);
         }

         return var10000;
      }

      @Nullable
      public static String desEncodeToBase64String(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(transformation, "transformation");
         Intrinsics.checkNotNullParameter(iv, "iv");
         byte[] var5 = EncoderUtils.INSTANCE.encryptDES2Base64(StringsKt.encodeToByteArray(data), StringsKt.encodeToByteArray(key), transformation, StringsKt.encodeToByteArray(iv));
         String var10000;
         if (var5 == null) {
            var10000 = null;
         } else {
            boolean var7 = false;
            boolean var8 = false;
            int var10 = false;
            boolean var11 = false;
            var10000 = new String(var5, Charsets.UTF_8);
         }

         return var10000;
      }

      @Nullable
      public static String tripleDESEncodeBase64Str(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(padding, "padding");
         Intrinsics.checkNotNullParameter(iv, "iv");
         Charset var7 = Charsets.UTF_8;
         boolean var8 = false;
         byte[] var10004 = key.getBytes(var7);
         Intrinsics.checkNotNullExpressionValue(var10004, "(this as java.lang.String).getBytes(charset)");
         var7 = Charsets.UTF_8;
         var8 = false;
         byte[] var10005 = iv.getBytes(var7);
         Intrinsics.checkNotNullExpressionValue(var10005, "(this as java.lang.String).getBytes(charset)");
         return (new DESede(mode, padding, var10004, var10005)).encryptBase64(data);
      }

      @Nullable
      public static String tripleDESEncodeArgsBase64Str(@NotNull JsExtensions this, @NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(key, "key");
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(padding, "padding");
         Intrinsics.checkNotNullParameter(iv, "iv");
         return (new DESede(mode, padding, Base64.decode((String)key, 2), Base64.decode((String)iv, 2))).encryptBase64(data);
      }

      @Nullable
      public static String digestHex(@NotNull JsExtensions this, @NotNull String data, @NotNull String algorithm) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(algorithm, "algorithm");
         return DigestUtil.digester(algorithm).digestHex(data);
      }

      @Nullable
      public static String digestBase64Str(@NotNull JsExtensions this, @NotNull String data, @NotNull String algorithm) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(data, "data");
         Intrinsics.checkNotNullParameter(algorithm, "algorithm");
         return Base64.encodeToString(DigestUtil.digester(algorithm).digest(data), 2);
      }
   }
}
