package okhttp3;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.ext.web.handler.FormLoginHandler;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import okio.Buffer;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Marker;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: HttpUrl.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n��\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018�� J2\u00020\u0001:\u0002IJBa\b��\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\u000f\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b!J\r\u0010\u0011\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\"J\r\u0010\u0012\u001a\u00020\u0003H\u0007¢\u0006\u0002\b#J\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0007¢\u0006\u0002\b$J\u000f\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b%J\r\u0010\u0016\u001a\u00020\u0003H\u0007¢\u0006\u0002\b&J\u0013\u0010'\u001a\u00020\u00182\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u000f\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b)J\b\u0010*\u001a\u00020\bH\u0016J\r\u0010\u0006\u001a\u00020\u0003H\u0007¢\u0006\u0002\b+J\u0006\u0010,\u001a\u00020-J\u0010\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020\u0003J\r\u0010\u0005\u001a\u00020\u0003H\u0007¢\u0006\u0002\b/J\u0013\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0007¢\u0006\u0002\b0J\r\u0010\u001a\u001a\u00020\bH\u0007¢\u0006\u0002\b1J\r\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\b2J\u000f\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b3J\u0010\u00104\u001a\u0004\u0018\u00010\u00032\u0006\u00105\u001a\u00020\u0003J\u000e\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\bJ\u0013\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001eH\u0007¢\u0006\u0002\b8J\u0010\u00109\u001a\u0004\u0018\u00010\u00032\u0006\u00107\u001a\u00020\bJ\u0016\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\n2\u0006\u00105\u001a\u00020\u0003J\r\u0010 \u001a\u00020\bH\u0007¢\u0006\u0002\b;J\u0006\u0010<\u001a\u00020\u0003J\u0010\u0010=\u001a\u0004\u0018\u00010��2\u0006\u0010.\u001a\u00020\u0003J\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b>J\b\u0010?\u001a\u00020\u0003H\u0016J\r\u0010@\u001a\u00020AH\u0007¢\u0006\u0002\bBJ\r\u0010C\u001a\u00020DH\u0007¢\u0006\u0002\b\rJ\b\u0010E\u001a\u0004\u0018\u00010\u0003J\r\u0010B\u001a\u00020AH\u0007¢\u0006\u0002\bFJ\r\u0010\r\u001a\u00020DH\u0007¢\u0006\u0002\bGJ\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\bHR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00038G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0012\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\n8G¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00038G¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\u0016\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0015\u0010\f\u001a\u0004\u0018\u00010\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\f\u0010\u0010R\u0013\u0010\u0006\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0010R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0019R\u0013\u0010\u0005\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0010R\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n8\u0007¢\u0006\b\n��\u001a\u0004\b\t\u0010\u0014R\u0011\u0010\u001a\u001a\u00020\b8G¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0007\u001a\u00020\b8\u0007¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\u001bR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u00038G¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0010R\u0018\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n��R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001e8G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u0011\u0010 \u001a\u00020\b8G¢\u0006\u0006\u001a\u0004\b \u0010\u001bR\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0010R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0004\u0010\u0010¨\u0006K"}, d2 = {"Lokhttp3/HttpUrl;", "", "scheme", "", FormLoginHandler.DEFAULT_USERNAME_PARAM, FormLoginHandler.DEFAULT_PASSWORD_PARAM, "host", RtspHeaders.Values.PORT, "", "pathSegments", "", "queryNamesAndValues", "fragment", "url", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "encodedFragment", "()Ljava/lang/String;", "encodedPassword", "encodedPath", "encodedPathSegments", "()Ljava/util/List;", "encodedQuery", "encodedUsername", "isHttps", "", "()Z", "pathSize", "()I", "query", "queryParameterNames", "", "()Ljava/util/Set;", "querySize", "-deprecated_encodedFragment", "-deprecated_encodedPassword", "-deprecated_encodedPath", "-deprecated_encodedPathSegments", "-deprecated_encodedQuery", "-deprecated_encodedUsername", "equals", "other", "-deprecated_fragment", IdentityNamingStrategy.HASH_CODE_KEY, "-deprecated_host", "newBuilder", "Lokhttp3/HttpUrl$Builder;", "link", "-deprecated_password", "-deprecated_pathSegments", "-deprecated_pathSize", "-deprecated_port", "-deprecated_query", "queryParameter", "name", "queryParameterName", "index", "-deprecated_queryParameterNames", "queryParameterValue", "queryParameterValues", "-deprecated_querySize", "redact", "resolve", "-deprecated_scheme", "toString", "toUri", "Ljava/net/URI;", "uri", "toUrl", "Ljava/net/URL;", "topPrivateDomain", "-deprecated_uri", "-deprecated_url", "-deprecated_username", "Builder", "Companion", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/HttpUrl.class */
public final class HttpUrl {
    private final boolean isHttps;

    @NotNull
    private final String scheme;

    @NotNull
    private final String username;

    @NotNull
    private final String password;

    @NotNull
    private final String host;
    private final int port;

    @NotNull
    private final List<String> pathSegments;
    private final List<String> queryNamesAndValues;

    @Nullable
    private final String fragment;
    private final String url;

    @NotNull
    public static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";

    @NotNull
    public static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";

    @NotNull
    public static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";

    @NotNull
    public static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";

    @NotNull
    public static final String QUERY_ENCODE_SET = " \"'<>#";

    @NotNull
    public static final String QUERY_COMPONENT_REENCODE_SET = " \"'<>#&=";

    @NotNull
    public static final String QUERY_COMPONENT_ENCODE_SET = " !\"#$&'(),/:;<=>?@[]\\^`{|}~";

    @NotNull
    public static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";

    @NotNull
    public static final String FORM_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#&!$(),~";

    @NotNull
    public static final String FRAGMENT_ENCODE_SET = "";

    @NotNull
    public static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
    public static final Companion Companion = new Companion(null);
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    @JvmStatic
    public static final int defaultPort(@NotNull String scheme) {
        return Companion.defaultPort(scheme);
    }

    @JvmStatic
    @JvmName(name = BeanUtil.PREFIX_GETTER_GET)
    @NotNull
    public static final HttpUrl get(@NotNull String $this$toHttpUrl) {
        return Companion.get($this$toHttpUrl);
    }

    @JvmStatic
    @JvmName(name = "parse")
    @Nullable
    public static final HttpUrl parse(@NotNull String $this$toHttpUrlOrNull) {
        return Companion.parse($this$toHttpUrlOrNull);
    }

    @JvmStatic
    @JvmName(name = BeanUtil.PREFIX_GETTER_GET)
    @Nullable
    public static final HttpUrl get(@NotNull URL $this$toHttpUrlOrNull) {
        return Companion.get($this$toHttpUrlOrNull);
    }

    @JvmStatic
    @JvmName(name = BeanUtil.PREFIX_GETTER_GET)
    @Nullable
    public static final HttpUrl get(@NotNull URI $this$toHttpUrlOrNull) {
        return Companion.get($this$toHttpUrlOrNull);
    }

    public HttpUrl(@NotNull String scheme, @NotNull String username, @NotNull String password, @NotNull String host, int port, @NotNull List<String> pathSegments, @Nullable List<String> list, @Nullable String fragment, @NotNull String url) {
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
        Intrinsics.checkNotNullParameter(url, "url");
        this.scheme = scheme;
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.pathSegments = pathSegments;
        this.queryNamesAndValues = list;
        this.fragment = fragment;
        this.url = url;
        this.isHttps = Intrinsics.areEqual(this.scheme, "https");
    }

    @JvmName(name = "scheme")
    @NotNull
    public final String scheme() {
        return this.scheme;
    }

    @JvmName(name = FormLoginHandler.DEFAULT_USERNAME_PARAM)
    @NotNull
    public final String username() {
        return this.username;
    }

    @JvmName(name = FormLoginHandler.DEFAULT_PASSWORD_PARAM)
    @NotNull
    public final String password() {
        return this.password;
    }

    @JvmName(name = "host")
    @NotNull
    public final String host() {
        return this.host;
    }

    @JvmName(name = RtspHeaders.Values.PORT)
    public final int port() {
        return this.port;
    }

    @JvmName(name = "pathSegments")
    @NotNull
    public final List<String> pathSegments() {
        return this.pathSegments;
    }

    @JvmName(name = "fragment")
    @Nullable
    public final String fragment() {
        return this.fragment;
    }

    public final boolean isHttps() {
        return this.isHttps;
    }

    @JvmName(name = "url")
    @NotNull
    public final URL url() {
        try {
            return new URL(this.url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @JvmName(name = "uri")
    @NotNull
    public final URI uri() {
        URI uri;
        String uri2 = newBuilder().reencodeForUri$okhttp().toString();
        try {
            uri = new URI(uri2);
        } catch (URISyntaxException e) {
            try {
                String stripped = new Regex("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]").replace(uri2, "");
                URI uriCreate = URI.create(stripped);
                Intrinsics.checkNotNullExpressionValue(uriCreate, "try {\n        val stripp…e) // Unexpected!\n      }");
                uri = uriCreate;
            } catch (Exception e2) {
                throw new RuntimeException(e);
            }
        }
        return uri;
    }

    @JvmName(name = "encodedUsername")
    @NotNull
    public final String encodedUsername() {
        if (this.username.length() == 0) {
            return "";
        }
        int usernameStart = this.scheme.length() + 3;
        int usernameEnd = Util.delimiterOffset(this.url, ":@", usernameStart, this.url.length());
        String str = this.url;
        if (str == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = str.substring(usernameStart, usernameEnd);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @JvmName(name = "encodedPassword")
    @NotNull
    public final String encodedPassword() {
        if (this.password.length() == 0) {
            return "";
        }
        int passwordStart = StringsKt.indexOf$default((CharSequence) this.url, ':', this.scheme.length() + 3, false, 4, (Object) null) + 1;
        int passwordEnd = StringsKt.indexOf$default((CharSequence) this.url, '@', 0, false, 6, (Object) null);
        String str = this.url;
        if (str == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = str.substring(passwordStart, passwordEnd);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @JvmName(name = "pathSize")
    public final int pathSize() {
        return this.pathSegments.size();
    }

    @JvmName(name = "encodedPath")
    @NotNull
    public final String encodedPath() {
        int pathStart = StringsKt.indexOf$default((CharSequence) this.url, '/', this.scheme.length() + 3, false, 4, (Object) null);
        int pathEnd = Util.delimiterOffset(this.url, "?#", pathStart, this.url.length());
        String str = this.url;
        if (str == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = str.substring(pathStart, pathEnd);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @JvmName(name = "encodedPathSegments")
    @NotNull
    public final List<String> encodedPathSegments() {
        int pathStart = StringsKt.indexOf$default((CharSequence) this.url, '/', this.scheme.length() + 3, false, 4, (Object) null);
        int pathEnd = Util.delimiterOffset(this.url, "?#", pathStart, this.url.length());
        List result = new ArrayList();
        int i = pathStart;
        while (true) {
            int i2 = i;
            if (i2 < pathEnd) {
                int i3 = i2 + 1;
                int segmentEnd = Util.delimiterOffset(this.url, '/', i3, pathEnd);
                String str = this.url;
                if (str == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring = str.substring(i3, segmentEnd);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                result.add(strSubstring);
                i = segmentEnd;
            } else {
                return result;
            }
        }
    }

    @JvmName(name = "encodedQuery")
    @Nullable
    public final String encodedQuery() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        int queryStart = StringsKt.indexOf$default((CharSequence) this.url, '?', 0, false, 6, (Object) null) + 1;
        int queryEnd = Util.delimiterOffset(this.url, '#', queryStart, this.url.length());
        String str = this.url;
        if (str == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = str.substring(queryStart, queryEnd);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @JvmName(name = "query")
    @Nullable
    public final String query() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        Companion.toQueryString$okhttp(this.queryNamesAndValues, result);
        return result.toString();
    }

    @JvmName(name = "querySize")
    public final int querySize() {
        if (this.queryNamesAndValues != null) {
            return this.queryNamesAndValues.size() / 2;
        }
        return 0;
    }

    @Nullable
    public final String queryParameter(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (this.queryNamesAndValues == null) {
            return null;
        }
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.queryNamesAndValues.size()), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if (step >= 0) {
            if (i > last) {
                return null;
            }
        } else if (i < last) {
            return null;
        }
        while (!Intrinsics.areEqual(name, this.queryNamesAndValues.get(i))) {
            if (i == last) {
                return null;
            }
            i += step;
        }
        return this.queryNamesAndValues.get(i + 1);
    }

    @JvmName(name = "queryParameterNames")
    @NotNull
    public final Set<String> queryParameterNames() {
        if (this.queryNamesAndValues == null) {
            return SetsKt.emptySet();
        }
        LinkedHashSet result = new LinkedHashSet();
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.queryNamesAndValues.size()), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if (step < 0 ? i >= last : i <= last) {
            while (true) {
                String str = this.queryNamesAndValues.get(i);
                Intrinsics.checkNotNull(str);
                result.add(str);
                if (i == last) {
                    break;
                }
                i += step;
            }
        }
        Set<String> setUnmodifiableSet = Collections.unmodifiableSet(result);
        Intrinsics.checkNotNullExpressionValue(setUnmodifiableSet, "Collections.unmodifiableSet(result)");
        return setUnmodifiableSet;
    }

    @NotNull
    public final List<String> queryParameterValues(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (this.queryNamesAndValues == null) {
            return CollectionsKt.emptyList();
        }
        List result = new ArrayList();
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.queryNamesAndValues.size()), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if (step < 0 ? i >= last : i <= last) {
            while (true) {
                if (Intrinsics.areEqual(name, this.queryNamesAndValues.get(i))) {
                    result.add(this.queryNamesAndValues.get(i + 1));
                }
                if (i == last) {
                    break;
                }
                i += step;
            }
        }
        List<String> listUnmodifiableList = Collections.unmodifiableList(result);
        Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "Collections.unmodifiableList(result)");
        return listUnmodifiableList;
    }

    @NotNull
    public final String queryParameterName(int index) {
        if (this.queryNamesAndValues == null) {
            throw new IndexOutOfBoundsException();
        }
        String str = this.queryNamesAndValues.get(index * 2);
        Intrinsics.checkNotNull(str);
        return str;
    }

    @Nullable
    public final String queryParameterValue(int index) {
        if (this.queryNamesAndValues == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.queryNamesAndValues.get((index * 2) + 1);
    }

    @JvmName(name = "encodedFragment")
    @Nullable
    public final String encodedFragment() {
        if (this.fragment == null) {
            return null;
        }
        int fragmentStart = StringsKt.indexOf$default((CharSequence) this.url, '#', 0, false, 6, (Object) null) + 1;
        String str = this.url;
        if (str == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = str.substring(fragmentStart);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }

    @NotNull
    public final String redact() {
        Builder builderNewBuilder = newBuilder("/...");
        Intrinsics.checkNotNull(builderNewBuilder);
        return builderNewBuilder.username("").password("").build().toString();
    }

    @Nullable
    public final HttpUrl resolve(@NotNull String link) {
        Intrinsics.checkNotNullParameter(link, "link");
        Builder builderNewBuilder = newBuilder(link);
        if (builderNewBuilder != null) {
            return builderNewBuilder.build();
        }
        return null;
    }

    @NotNull
    public final Builder newBuilder() {
        Builder result = new Builder();
        result.setScheme$okhttp(this.scheme);
        result.setEncodedUsername$okhttp(encodedUsername());
        result.setEncodedPassword$okhttp(encodedPassword());
        result.setHost$okhttp(this.host);
        result.setPort$okhttp(this.port != Companion.defaultPort(this.scheme) ? this.port : -1);
        result.getEncodedPathSegments$okhttp().clear();
        result.getEncodedPathSegments$okhttp().addAll(encodedPathSegments());
        result.encodedQuery(encodedQuery());
        result.setEncodedFragment$okhttp(encodedFragment());
        return result;
    }

    @Nullable
    public final Builder newBuilder(@NotNull String link) {
        Builder builder;
        Intrinsics.checkNotNullParameter(link, "link");
        try {
            builder = new Builder().parse$okhttp(this, link);
        } catch (IllegalArgumentException e) {
            builder = null;
        }
        return builder;
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof HttpUrl) && Intrinsics.areEqual(((HttpUrl) other).url, this.url);
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    @NotNull
    public String toString() {
        return this.url;
    }

    @Nullable
    public final String topPrivateDomain() {
        if (Util.canParseAsIpAddress(this.host)) {
            return null;
        }
        return PublicSuffixDatabase.Companion.get().getEffectiveTldPlusOne(this.host);
    }

    @Deprecated(message = "moved to toUrl()", replaceWith = @ReplaceWith(imports = {}, expression = "toUrl()"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_url")
    @NotNull
    /* renamed from: -deprecated_url, reason: not valid java name */
    public final URL m4478deprecated_url() {
        return url();
    }

    @Deprecated(message = "moved to toUri()", replaceWith = @ReplaceWith(imports = {}, expression = "toUri()"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_uri")
    @NotNull
    /* renamed from: -deprecated_uri, reason: not valid java name */
    public final URI m4479deprecated_uri() {
        return uri();
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "scheme"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_scheme")
    @NotNull
    /* renamed from: -deprecated_scheme, reason: not valid java name */
    public final String m4480deprecated_scheme() {
        return this.scheme;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "encodedUsername"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_encodedUsername")
    @NotNull
    /* renamed from: -deprecated_encodedUsername, reason: not valid java name */
    public final String m4481deprecated_encodedUsername() {
        return encodedUsername();
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = FormLoginHandler.DEFAULT_USERNAME_PARAM), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_username")
    @NotNull
    /* renamed from: -deprecated_username, reason: not valid java name */
    public final String m4482deprecated_username() {
        return this.username;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "encodedPassword"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_encodedPassword")
    @NotNull
    /* renamed from: -deprecated_encodedPassword, reason: not valid java name */
    public final String m4483deprecated_encodedPassword() {
        return encodedPassword();
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = FormLoginHandler.DEFAULT_PASSWORD_PARAM), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_password")
    @NotNull
    /* renamed from: -deprecated_password, reason: not valid java name */
    public final String m4484deprecated_password() {
        return this.password;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "host"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_host")
    @NotNull
    /* renamed from: -deprecated_host, reason: not valid java name */
    public final String m4485deprecated_host() {
        return this.host;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = RtspHeaders.Values.PORT), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_port")
    /* renamed from: -deprecated_port, reason: not valid java name */
    public final int m4486deprecated_port() {
        return this.port;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "pathSize"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_pathSize")
    /* renamed from: -deprecated_pathSize, reason: not valid java name */
    public final int m4487deprecated_pathSize() {
        return pathSize();
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "encodedPath"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_encodedPath")
    @NotNull
    /* renamed from: -deprecated_encodedPath, reason: not valid java name */
    public final String m4488deprecated_encodedPath() {
        return encodedPath();
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "encodedPathSegments"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_encodedPathSegments")
    @NotNull
    /* renamed from: -deprecated_encodedPathSegments, reason: not valid java name */
    public final List<String> m4489deprecated_encodedPathSegments() {
        return encodedPathSegments();
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "pathSegments"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_pathSegments")
    @NotNull
    /* renamed from: -deprecated_pathSegments, reason: not valid java name */
    public final List<String> m4490deprecated_pathSegments() {
        return this.pathSegments;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "encodedQuery"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_encodedQuery")
    @Nullable
    /* renamed from: -deprecated_encodedQuery, reason: not valid java name */
    public final String m4491deprecated_encodedQuery() {
        return encodedQuery();
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "query"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_query")
    @Nullable
    /* renamed from: -deprecated_query, reason: not valid java name */
    public final String m4492deprecated_query() {
        return query();
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "querySize"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_querySize")
    /* renamed from: -deprecated_querySize, reason: not valid java name */
    public final int m4493deprecated_querySize() {
        return querySize();
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "queryParameterNames"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_queryParameterNames")
    @NotNull
    /* renamed from: -deprecated_queryParameterNames, reason: not valid java name */
    public final Set<String> m4494deprecated_queryParameterNames() {
        return queryParameterNames();
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "encodedFragment"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_encodedFragment")
    @Nullable
    /* renamed from: -deprecated_encodedFragment, reason: not valid java name */
    public final String m4495deprecated_encodedFragment() {
        return encodedFragment();
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "fragment"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_fragment")
    @Nullable
    /* renamed from: -deprecated_fragment, reason: not valid java name */
    public final String m4496deprecated_fragment() {
        return this.fragment;
    }

    /* compiled from: HttpUrl.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��<\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0017\u0018�� V2\u00020\u0001:\u0001VB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010#\u001a\u00020��2\u0006\u0010$\u001a\u00020\u0004J\u000e\u0010%\u001a\u00020��2\u0006\u0010\f\u001a\u00020\u0004J\u0018\u0010&\u001a\u00020��2\u0006\u0010'\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010\u0004J\u000e\u0010)\u001a\u00020��2\u0006\u0010*\u001a\u00020\u0004J\u000e\u0010+\u001a\u00020��2\u0006\u0010,\u001a\u00020\u0004J\u0018\u0010+\u001a\u00020��2\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020.H\u0002J\u0018\u0010/\u001a\u00020��2\u0006\u00100\u001a\u00020\u00042\b\u00101\u001a\u0004\u0018\u00010\u0004J\u0006\u00102\u001a\u000203J\b\u00104\u001a\u00020\u001bH\u0002J\u0010\u0010\u0003\u001a\u00020��2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\t\u001a\u00020��2\u0006\u0010\t\u001a\u00020\u0004J\u000e\u00105\u001a\u00020��2\u0006\u00105\u001a\u00020\u0004J\u0010\u00106\u001a\u00020��2\b\u00106\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0014\u001a\u00020��2\u0006\u0010\u0014\u001a\u00020\u0004J\u0010\u00107\u001a\u00020��2\b\u00107\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0017\u001a\u00020��2\u0006\u0010\u0017\u001a\u00020\u0004J\u0010\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020\u0004H\u0002J\u0010\u0010:\u001a\u00020.2\u0006\u00109\u001a\u00020\u0004H\u0002J\u001f\u0010;\u001a\u00020��2\b\u0010<\u001a\u0004\u0018\u0001032\u0006\u00109\u001a\u00020\u0004H��¢\u0006\u0002\b=J\u000e\u0010>\u001a\u00020��2\u0006\u0010>\u001a\u00020\u0004J\b\u0010?\u001a\u00020@H\u0002J\u000e\u0010\u001a\u001a\u00020��2\u0006\u0010\u001a\u001a\u00020\u001bJ0\u0010A\u001a\u00020@2\u0006\u00109\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020\u001b2\u0006\u0010D\u001a\u00020.2\u0006\u0010-\u001a\u00020.H\u0002J\u0010\u0010E\u001a\u00020��2\b\u0010E\u001a\u0004\u0018\u00010\u0004J\r\u0010F\u001a\u00020��H��¢\u0006\u0002\bGJ\u0010\u0010H\u001a\u00020@2\u0006\u0010I\u001a\u00020\u0004H\u0002J\u000e\u0010J\u001a\u00020��2\u0006\u0010'\u001a\u00020\u0004J\u000e\u0010K\u001a\u00020��2\u0006\u00100\u001a\u00020\u0004J\u000e\u0010L\u001a\u00020��2\u0006\u0010M\u001a\u00020\u001bJ \u0010N\u001a\u00020@2\u0006\u00109\u001a\u00020\u00042\u0006\u0010O\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020\u001bH\u0002J\u000e\u0010 \u001a\u00020��2\u0006\u0010 \u001a\u00020\u0004J\u0016\u0010P\u001a\u00020��2\u0006\u0010M\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u0004J\u0018\u0010Q\u001a\u00020��2\u0006\u0010'\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010\u0004J\u0016\u0010R\u001a\u00020��2\u0006\u0010M\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020\u0004J\u0018\u0010S\u001a\u00020��2\u0006\u00100\u001a\u00020\u00042\b\u00101\u001a\u0004\u0018\u00010\u0004J\b\u0010T\u001a\u00020\u0004H\u0016J\u000e\u0010U\u001a\u00020��2\u0006\u0010U\u001a\u00020\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\rX\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\rX\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0011\u0010\u000f\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\bR\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0018\u0010\u0006\"\u0004\b\u0019\u0010\bR\u001a\u0010\u001a\u001a\u00020\u001bX\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\b¨\u0006W"}, d2 = {"Lokhttp3/HttpUrl$Builder;", "", "()V", "encodedFragment", "", "getEncodedFragment$okhttp", "()Ljava/lang/String;", "setEncodedFragment$okhttp", "(Ljava/lang/String;)V", "encodedPassword", "getEncodedPassword$okhttp", "setEncodedPassword$okhttp", "encodedPathSegments", "", "getEncodedPathSegments$okhttp", "()Ljava/util/List;", "encodedQueryNamesAndValues", "getEncodedQueryNamesAndValues$okhttp", "setEncodedQueryNamesAndValues$okhttp", "(Ljava/util/List;)V", "encodedUsername", "getEncodedUsername$okhttp", "setEncodedUsername$okhttp", "host", "getHost$okhttp", "setHost$okhttp", RtspHeaders.Values.PORT, "", "getPort$okhttp", "()I", "setPort$okhttp", "(I)V", "scheme", "getScheme$okhttp", "setScheme$okhttp", "addEncodedPathSegment", "encodedPathSegment", "addEncodedPathSegments", "addEncodedQueryParameter", "encodedName", "encodedValue", "addPathSegment", "pathSegment", "addPathSegments", "pathSegments", "alreadyEncoded", "", "addQueryParameter", "name", "value", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lokhttp3/HttpUrl;", "effectivePort", "encodedPath", "encodedQuery", "fragment", "isDot", "input", "isDotDot", "parse", "base", "parse$okhttp", FormLoginHandler.DEFAULT_PASSWORD_PARAM, "pop", "", "push", "pos", "limit", "addTrailingSlash", "query", "reencodeForUri", "reencodeForUri$okhttp", "removeAllCanonicalQueryParameters", "canonicalName", "removeAllEncodedQueryParameters", "removeAllQueryParameters", "removePathSegment", "index", "resolvePath", "startPos", "setEncodedPathSegment", "setEncodedQueryParameter", "setPathSegment", "setQueryParameter", "toString", FormLoginHandler.DEFAULT_USERNAME_PARAM, "Companion", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/HttpUrl$Builder.class */
    public static final class Builder {

        @Nullable
        private String scheme;

        @Nullable
        private String host;

        @Nullable
        private List<String> encodedQueryNamesAndValues;

        @Nullable
        private String encodedFragment;

        @NotNull
        public static final String INVALID_HOST = "Invalid URL host";
        public static final Companion Companion = new Companion(null);

        @NotNull
        private String encodedUsername = "";

        @NotNull
        private String encodedPassword = "";
        private int port = -1;

        @NotNull
        private final List<String> encodedPathSegments = new ArrayList();

        public Builder() {
            this.encodedPathSegments.add("");
        }

        @Nullable
        public final String getScheme$okhttp() {
            return this.scheme;
        }

        public final void setScheme$okhttp(@Nullable String str) {
            this.scheme = str;
        }

        @NotNull
        public final String getEncodedUsername$okhttp() {
            return this.encodedUsername;
        }

        public final void setEncodedUsername$okhttp(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.encodedUsername = str;
        }

        @NotNull
        public final String getEncodedPassword$okhttp() {
            return this.encodedPassword;
        }

        public final void setEncodedPassword$okhttp(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.encodedPassword = str;
        }

        @Nullable
        public final String getHost$okhttp() {
            return this.host;
        }

        public final void setHost$okhttp(@Nullable String str) {
            this.host = str;
        }

        public final int getPort$okhttp() {
            return this.port;
        }

        public final void setPort$okhttp(int i) {
            this.port = i;
        }

        @NotNull
        public final List<String> getEncodedPathSegments$okhttp() {
            return this.encodedPathSegments;
        }

        @Nullable
        public final List<String> getEncodedQueryNamesAndValues$okhttp() {
            return this.encodedQueryNamesAndValues;
        }

        public final void setEncodedQueryNamesAndValues$okhttp(@Nullable List<String> list) {
            this.encodedQueryNamesAndValues = list;
        }

        @Nullable
        public final String getEncodedFragment$okhttp() {
            return this.encodedFragment;
        }

        public final void setEncodedFragment$okhttp(@Nullable String str) {
            this.encodedFragment = str;
        }

        @NotNull
        public final Builder scheme(@NotNull String scheme) {
            Intrinsics.checkNotNullParameter(scheme, "scheme");
            Builder $this$apply = this;
            if (StringsKt.equals(scheme, "http", true)) {
                $this$apply.scheme = "http";
            } else {
                if (!StringsKt.equals(scheme, "https", true)) {
                    throw new IllegalArgumentException("unexpected scheme: " + scheme);
                }
                $this$apply.scheme = "https";
            }
            return this;
        }

        @NotNull
        public final Builder username(@NotNull String username) {
            Intrinsics.checkNotNullParameter(username, "username");
            Builder $this$apply = this;
            $this$apply.encodedUsername = Companion.canonicalize$okhttp$default(HttpUrl.Companion, username, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
            return this;
        }

        @NotNull
        public final Builder encodedUsername(@NotNull String encodedUsername) {
            Intrinsics.checkNotNullParameter(encodedUsername, "encodedUsername");
            Builder $this$apply = this;
            $this$apply.encodedUsername = Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedUsername, 0, 0, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 243, null);
            return this;
        }

        @NotNull
        public final Builder password(@NotNull String password) {
            Intrinsics.checkNotNullParameter(password, "password");
            Builder $this$apply = this;
            $this$apply.encodedPassword = Companion.canonicalize$okhttp$default(HttpUrl.Companion, password, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
            return this;
        }

        @NotNull
        public final Builder encodedPassword(@NotNull String encodedPassword) {
            Intrinsics.checkNotNullParameter(encodedPassword, "encodedPassword");
            Builder $this$apply = this;
            $this$apply.encodedPassword = Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedPassword, 0, 0, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 243, null);
            return this;
        }

        @NotNull
        public final Builder host(@NotNull String host) {
            Intrinsics.checkNotNullParameter(host, "host");
            Builder $this$apply = this;
            String encoded = HostnamesKt.toCanonicalHost(Companion.percentDecode$okhttp$default(HttpUrl.Companion, host, 0, 0, false, 7, null));
            if (encoded == null) {
                throw new IllegalArgumentException("unexpected host: " + host);
            }
            $this$apply.host = encoded;
            return this;
        }

        @NotNull
        public final Builder port(int port) {
            Builder $this$apply = this;
            if (!(1 <= port && 65535 >= port)) {
                throw new IllegalArgumentException(("unexpected port: " + port).toString());
            }
            $this$apply.port = port;
            return this;
        }

        private final int effectivePort() {
            if (this.port != -1) {
                return this.port;
            }
            Companion companion = HttpUrl.Companion;
            String str = this.scheme;
            Intrinsics.checkNotNull(str);
            return companion.defaultPort(str);
        }

        @NotNull
        public final Builder addPathSegment(@NotNull String pathSegment) {
            Intrinsics.checkNotNullParameter(pathSegment, "pathSegment");
            Builder $this$apply = this;
            $this$apply.push(pathSegment, 0, pathSegment.length(), false, false);
            return this;
        }

        @NotNull
        public final Builder addPathSegments(@NotNull String pathSegments) {
            Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
            return addPathSegments(pathSegments, false);
        }

        @NotNull
        public final Builder addEncodedPathSegment(@NotNull String encodedPathSegment) {
            Intrinsics.checkNotNullParameter(encodedPathSegment, "encodedPathSegment");
            Builder $this$apply = this;
            $this$apply.push(encodedPathSegment, 0, encodedPathSegment.length(), false, true);
            return this;
        }

        @NotNull
        public final Builder addEncodedPathSegments(@NotNull String encodedPathSegments) {
            Intrinsics.checkNotNullParameter(encodedPathSegments, "encodedPathSegments");
            return addPathSegments(encodedPathSegments, true);
        }

        private final Builder addPathSegments(String pathSegments, boolean alreadyEncoded) {
            Builder $this$apply = this;
            int offset = 0;
            do {
                int segmentEnd = Util.delimiterOffset(pathSegments, "/\\", offset, pathSegments.length());
                boolean addTrailingSlash = segmentEnd < pathSegments.length();
                $this$apply.push(pathSegments, offset, segmentEnd, addTrailingSlash, alreadyEncoded);
                offset = segmentEnd + 1;
            } while (offset <= pathSegments.length());
            return this;
        }

        @NotNull
        public final Builder setPathSegment(int index, @NotNull String pathSegment) {
            Intrinsics.checkNotNullParameter(pathSegment, "pathSegment");
            Builder $this$apply = this;
            String canonicalPathSegment = Companion.canonicalize$okhttp$default(HttpUrl.Companion, pathSegment, 0, 0, HttpUrl.PATH_SEGMENT_ENCODE_SET, false, false, false, false, null, 251, null);
            if (!(($this$apply.isDot(canonicalPathSegment) || $this$apply.isDotDot(canonicalPathSegment)) ? false : true)) {
                throw new IllegalArgumentException(("unexpected path segment: " + pathSegment).toString());
            }
            $this$apply.encodedPathSegments.set(index, canonicalPathSegment);
            return this;
        }

        @NotNull
        public final Builder setEncodedPathSegment(int index, @NotNull String encodedPathSegment) {
            Intrinsics.checkNotNullParameter(encodedPathSegment, "encodedPathSegment");
            Builder $this$apply = this;
            String canonicalPathSegment = Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedPathSegment, 0, 0, HttpUrl.PATH_SEGMENT_ENCODE_SET, true, false, false, false, null, 243, null);
            $this$apply.encodedPathSegments.set(index, canonicalPathSegment);
            if (($this$apply.isDot(canonicalPathSegment) || $this$apply.isDotDot(canonicalPathSegment)) ? false : true) {
                return this;
            }
            throw new IllegalArgumentException(("unexpected path segment: " + encodedPathSegment).toString());
        }

        @NotNull
        public final Builder removePathSegment(int index) {
            Builder $this$apply = this;
            $this$apply.encodedPathSegments.remove(index);
            if ($this$apply.encodedPathSegments.isEmpty()) {
                $this$apply.encodedPathSegments.add("");
            }
            return this;
        }

        @NotNull
        public final Builder encodedPath(@NotNull String encodedPath) {
            Intrinsics.checkNotNullParameter(encodedPath, "encodedPath");
            Builder $this$apply = this;
            if (!StringsKt.startsWith$default(encodedPath, "/", false, 2, (Object) null)) {
                throw new IllegalArgumentException(("unexpected encodedPath: " + encodedPath).toString());
            }
            $this$apply.resolvePath(encodedPath, 0, encodedPath.length());
            return this;
        }

        @NotNull
        public final Builder query(@Nullable String query) {
            String strCanonicalize$okhttp$default;
            Builder $this$apply = this;
            $this$apply.encodedQueryNamesAndValues = (query == null || (strCanonicalize$okhttp$default = Companion.canonicalize$okhttp$default(HttpUrl.Companion, query, 0, 0, HttpUrl.QUERY_ENCODE_SET, false, false, true, false, null, 219, null)) == null) ? null : HttpUrl.Companion.toQueryNamesAndValues$okhttp(strCanonicalize$okhttp$default);
            return this;
        }

        @NotNull
        public final Builder encodedQuery(@Nullable String encodedQuery) {
            String strCanonicalize$okhttp$default;
            Builder $this$apply = this;
            $this$apply.encodedQueryNamesAndValues = (encodedQuery == null || (strCanonicalize$okhttp$default = Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedQuery, 0, 0, HttpUrl.QUERY_ENCODE_SET, true, false, true, false, null, 211, null)) == null) ? null : HttpUrl.Companion.toQueryNamesAndValues$okhttp(strCanonicalize$okhttp$default);
            return this;
        }

        @NotNull
        public final Builder addQueryParameter(@NotNull String name, @Nullable String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder $this$apply = this;
            if ($this$apply.encodedQueryNamesAndValues == null) {
                $this$apply.encodedQueryNamesAndValues = new ArrayList();
            }
            List<String> list = $this$apply.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list);
            list.add(Companion.canonicalize$okhttp$default(HttpUrl.Companion, name, 0, 0, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, false, null, 219, null));
            List<String> list2 = $this$apply.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list2);
            list2.add(value != null ? Companion.canonicalize$okhttp$default(HttpUrl.Companion, value, 0, 0, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, false, null, 219, null) : null);
            return this;
        }

        @NotNull
        public final Builder addEncodedQueryParameter(@NotNull String encodedName, @Nullable String encodedValue) {
            Intrinsics.checkNotNullParameter(encodedName, "encodedName");
            Builder $this$apply = this;
            if ($this$apply.encodedQueryNamesAndValues == null) {
                $this$apply.encodedQueryNamesAndValues = new ArrayList();
            }
            List<String> list = $this$apply.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list);
            list.add(Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedName, 0, 0, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, false, null, 211, null));
            List<String> list2 = $this$apply.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list2);
            list2.add(encodedValue != null ? Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedValue, 0, 0, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, false, null, 211, null) : null);
            return this;
        }

        @NotNull
        public final Builder setQueryParameter(@NotNull String name, @Nullable String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder $this$apply = this;
            $this$apply.removeAllQueryParameters(name);
            $this$apply.addQueryParameter(name, value);
            return this;
        }

        @NotNull
        public final Builder setEncodedQueryParameter(@NotNull String encodedName, @Nullable String encodedValue) {
            Intrinsics.checkNotNullParameter(encodedName, "encodedName");
            Builder $this$apply = this;
            $this$apply.removeAllEncodedQueryParameters(encodedName);
            $this$apply.addEncodedQueryParameter(encodedName, encodedValue);
            return this;
        }

        @NotNull
        public final Builder removeAllQueryParameters(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder $this$apply = this;
            if ($this$apply.encodedQueryNamesAndValues == null) {
                return $this$apply;
            }
            String nameToRemove = Companion.canonicalize$okhttp$default(HttpUrl.Companion, name, 0, 0, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, false, null, 219, null);
            $this$apply.removeAllCanonicalQueryParameters(nameToRemove);
            return this;
        }

        @NotNull
        public final Builder removeAllEncodedQueryParameters(@NotNull String encodedName) {
            Intrinsics.checkNotNullParameter(encodedName, "encodedName");
            Builder $this$apply = this;
            if ($this$apply.encodedQueryNamesAndValues == null) {
                return $this$apply;
            }
            $this$apply.removeAllCanonicalQueryParameters(Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedName, 0, 0, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, false, null, 211, null));
            return this;
        }

        private final void removeAllCanonicalQueryParameters(String canonicalName) {
            List<String> list = this.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list);
            IntProgression intProgressionStep = RangesKt.step(RangesKt.downTo(list.size() - 2, 0), 2);
            int i = intProgressionStep.getFirst();
            int last = intProgressionStep.getLast();
            int step = intProgressionStep.getStep();
            if (step >= 0) {
                if (i > last) {
                    return;
                }
            } else if (i < last) {
                return;
            }
            while (true) {
                List<String> list2 = this.encodedQueryNamesAndValues;
                Intrinsics.checkNotNull(list2);
                if (Intrinsics.areEqual(canonicalName, list2.get(i))) {
                    List<String> list3 = this.encodedQueryNamesAndValues;
                    Intrinsics.checkNotNull(list3);
                    list3.remove(i + 1);
                    List<String> list4 = this.encodedQueryNamesAndValues;
                    Intrinsics.checkNotNull(list4);
                    list4.remove(i);
                    List<String> list5 = this.encodedQueryNamesAndValues;
                    Intrinsics.checkNotNull(list5);
                    if (list5.isEmpty()) {
                        this.encodedQueryNamesAndValues = (List) null;
                        return;
                    }
                }
                if (i == last) {
                    return;
                } else {
                    i += step;
                }
            }
        }

        @NotNull
        public final Builder fragment(@Nullable String fragment) {
            Builder $this$apply = this;
            $this$apply.encodedFragment = fragment != null ? Companion.canonicalize$okhttp$default(HttpUrl.Companion, fragment, 0, 0, "", false, false, false, true, null, 187, null) : null;
            return this;
        }

        @NotNull
        public final Builder encodedFragment(@Nullable String encodedFragment) {
            Builder $this$apply = this;
            $this$apply.encodedFragment = encodedFragment != null ? Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedFragment, 0, 0, "", true, false, false, true, null, 179, null) : null;
            return this;
        }

        @NotNull
        public final Builder reencodeForUri$okhttp() {
            Builder $this$apply = this;
            String str = $this$apply.host;
            $this$apply.host = str != null ? new Regex("[\"<>^`{|}]").replace(str, "") : null;
            int size = $this$apply.encodedPathSegments.size();
            for (int i = 0; i < size; i++) {
                $this$apply.encodedPathSegments.set(i, Companion.canonicalize$okhttp$default(HttpUrl.Companion, $this$apply.encodedPathSegments.get(i), 0, 0, "[]", true, true, false, false, null, 227, null));
            }
            List encodedQueryNamesAndValues = $this$apply.encodedQueryNamesAndValues;
            if (encodedQueryNamesAndValues != null) {
                int size2 = encodedQueryNamesAndValues.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    int i3 = i2;
                    String str2 = encodedQueryNamesAndValues.get(i2);
                    encodedQueryNamesAndValues.set(i3, str2 != null ? Companion.canonicalize$okhttp$default(HttpUrl.Companion, str2, 0, 0, HttpUrl.QUERY_COMPONENT_ENCODE_SET_URI, true, true, true, false, null, 195, null) : null);
                }
            }
            String str3 = $this$apply.encodedFragment;
            $this$apply.encodedFragment = str3 != null ? Companion.canonicalize$okhttp$default(HttpUrl.Companion, str3, 0, 0, HttpUrl.FRAGMENT_ENCODE_SET_URI, true, true, false, true, null, 163, null) : null;
            return this;
        }

        @NotNull
        public final HttpUrl build() {
            ArrayList arrayList;
            String str = this.scheme;
            if (str == null) {
                throw new IllegalStateException("scheme == null");
            }
            String strPercentDecode$okhttp$default = Companion.percentDecode$okhttp$default(HttpUrl.Companion, this.encodedUsername, 0, 0, false, 7, null);
            String strPercentDecode$okhttp$default2 = Companion.percentDecode$okhttp$default(HttpUrl.Companion, this.encodedPassword, 0, 0, false, 7, null);
            String str2 = this.host;
            if (str2 == null) {
                throw new IllegalStateException("host == null");
            }
            int iEffectivePort = effectivePort();
            Iterable $this$map$iv = this.encodedPathSegments;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                destination$iv$iv.add(Companion.percentDecode$okhttp$default(HttpUrl.Companion, (String) item$iv$iv, 0, 0, false, 7, null));
            }
            ArrayList arrayList2 = (List) destination$iv$iv;
            String str3 = str;
            String str4 = strPercentDecode$okhttp$default;
            String str5 = strPercentDecode$okhttp$default2;
            String str6 = str2;
            int i = iEffectivePort;
            ArrayList arrayList3 = arrayList2;
            Iterable iterable = this.encodedQueryNamesAndValues;
            if (iterable != null) {
                Iterable $this$map$iv2 = iterable;
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                for (Object item$iv$iv2 : $this$map$iv2) {
                    String it = (String) item$iv$iv2;
                    destination$iv$iv2.add(it != null ? Companion.percentDecode$okhttp$default(HttpUrl.Companion, it, 0, 0, true, 3, null) : null);
                }
                ArrayList arrayList4 = (List) destination$iv$iv2;
                str3 = str3;
                str4 = str4;
                str5 = str5;
                str6 = str6;
                i = i;
                arrayList3 = arrayList3;
                arrayList = arrayList4;
            } else {
                arrayList = null;
            }
            String str7 = this.encodedFragment;
            return new HttpUrl(str3, str4, str5, str6, i, arrayList3, arrayList, str7 != null ? Companion.percentDecode$okhttp$default(HttpUrl.Companion, str7, 0, 0, false, 7, null) : null, toString());
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0079  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x012e  */
        @org.jetbrains.annotations.NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String toString() {
            /*
                Method dump skipped, instructions count: 399
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.HttpUrl.Builder.toString():java.lang.String");
        }

        @NotNull
        public final Builder parse$okhttp(@Nullable HttpUrl base, @NotNull String input) {
            int iCharAt;
            String str;
            Intrinsics.checkNotNullParameter(input, "input");
            int pos = Util.indexOfFirstNonAsciiWhitespace$default(input, 0, 0, 3, null);
            int limit = Util.indexOfLastNonAsciiWhitespace$default(input, pos, 0, 2, null);
            int schemeDelimiterOffset = Companion.schemeDelimiterOffset(input, pos, limit);
            if (schemeDelimiterOffset != -1) {
                if (StringsKt.startsWith(input, "https:", pos, true)) {
                    this.scheme = "https";
                    pos += "https:".length();
                } else if (StringsKt.startsWith(input, "http:", pos, true)) {
                    this.scheme = "http";
                    pos += "http:".length();
                } else {
                    StringBuilder sbAppend = new StringBuilder().append("Expected URL scheme 'http' or 'https' but was '");
                    String strSubstring = input.substring(0, schemeDelimiterOffset);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    throw new IllegalArgumentException(sbAppend.append(strSubstring).append(OperatorName.SHOW_TEXT_LINE).toString());
                }
            } else if (base != null) {
                this.scheme = base.scheme();
            } else {
                throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no colon was found");
            }
            boolean hasUsername = false;
            boolean hasPassword = false;
            int slashCount = Companion.slashCount(input, pos, limit);
            if (slashCount >= 2 || base == null || (!Intrinsics.areEqual(base.scheme(), this.scheme))) {
                int pos2 = pos + slashCount;
                while (true) {
                    int componentDelimiterOffset = Util.delimiterOffset(input, "@/\\?#", pos2, limit);
                    if (componentDelimiterOffset != limit) {
                        iCharAt = input.charAt(componentDelimiterOffset);
                    } else {
                        iCharAt = -1;
                    }
                    int c = iCharAt;
                    switch (c) {
                        case -1:
                        case 35:
                        case 47:
                        case 63:
                        case 92:
                            int portColonOffset = Companion.portColonOffset(input, pos2, componentDelimiterOffset);
                            if (portColonOffset + 1 < componentDelimiterOffset) {
                                this.host = HostnamesKt.toCanonicalHost(Companion.percentDecode$okhttp$default(HttpUrl.Companion, input, pos2, portColonOffset, false, 4, null));
                                this.port = Companion.parsePort(input, portColonOffset + 1, componentDelimiterOffset);
                                if (!(this.port != -1)) {
                                    StringBuilder sbAppend2 = new StringBuilder().append("Invalid URL port: \"");
                                    String strSubstring2 = input.substring(portColonOffset + 1, componentDelimiterOffset);
                                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                    throw new IllegalArgumentException(sbAppend2.append(strSubstring2).append('\"').toString().toString());
                                }
                            } else {
                                this.host = HostnamesKt.toCanonicalHost(Companion.percentDecode$okhttp$default(HttpUrl.Companion, input, pos2, portColonOffset, false, 4, null));
                                Companion companion = HttpUrl.Companion;
                                String str2 = this.scheme;
                                Intrinsics.checkNotNull(str2);
                                this.port = companion.defaultPort(str2);
                            }
                            if (!(this.host != null)) {
                                StringBuilder sbAppend3 = new StringBuilder().append("Invalid URL host: \"");
                                String strSubstring3 = input.substring(pos2, portColonOffset);
                                Intrinsics.checkNotNullExpressionValue(strSubstring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                throw new IllegalArgumentException(sbAppend3.append(strSubstring3).append('\"').toString().toString());
                            }
                            pos = componentDelimiterOffset;
                            break;
                        case 64:
                            if (!hasPassword) {
                                int passwordColonOffset = Util.delimiterOffset(input, ':', pos2, componentDelimiterOffset);
                                String canonicalUsername = Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos2, passwordColonOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                                if (hasUsername) {
                                    str = this.encodedUsername + "%40" + canonicalUsername;
                                } else {
                                    str = canonicalUsername;
                                }
                                this.encodedUsername = str;
                                if (passwordColonOffset != componentDelimiterOffset) {
                                    hasPassword = true;
                                    this.encodedPassword = Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, passwordColonOffset + 1, componentDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                                }
                                hasUsername = true;
                            } else {
                                this.encodedPassword += "%40" + Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos2, componentDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                            }
                            pos2 = componentDelimiterOffset + 1;
                            continue;
                    }
                }
            } else {
                this.encodedUsername = base.encodedUsername();
                this.encodedPassword = base.encodedPassword();
                this.host = base.host();
                this.port = base.port();
                this.encodedPathSegments.clear();
                this.encodedPathSegments.addAll(base.encodedPathSegments());
                if (pos == limit || input.charAt(pos) == '#') {
                    encodedQuery(base.encodedQuery());
                }
            }
            int pathDelimiterOffset = Util.delimiterOffset(input, "?#", pos, limit);
            resolvePath(input, pos, pathDelimiterOffset);
            int pos3 = pathDelimiterOffset;
            if (pos3 < limit && input.charAt(pos3) == '?') {
                int queryDelimiterOffset = Util.delimiterOffset(input, '#', pos3, limit);
                this.encodedQueryNamesAndValues = HttpUrl.Companion.toQueryNamesAndValues$okhttp(Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos3 + 1, queryDelimiterOffset, HttpUrl.QUERY_ENCODE_SET, true, false, true, false, null, 208, null));
                pos3 = queryDelimiterOffset;
            }
            if (pos3 < limit && input.charAt(pos3) == '#') {
                this.encodedFragment = Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos3 + 1, limit, "", true, false, false, true, null, 176, null);
            }
            return this;
        }

        private final void resolvePath(String input, int startPos, int limit) {
            int pos = startPos;
            if (pos == limit) {
                return;
            }
            char c = input.charAt(pos);
            if (c == '/' || c == '\\') {
                this.encodedPathSegments.clear();
                this.encodedPathSegments.add("");
                pos++;
            } else {
                this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, "");
            }
            int i = pos;
            while (i < limit) {
                int pathSegmentDelimiterOffset = Util.delimiterOffset(input, "/\\", i, limit);
                boolean segmentHasTrailingSlash = pathSegmentDelimiterOffset < limit;
                push(input, i, pathSegmentDelimiterOffset, segmentHasTrailingSlash, true);
                i = pathSegmentDelimiterOffset;
                if (segmentHasTrailingSlash) {
                    i++;
                }
            }
        }

        private final void push(String input, int pos, int limit, boolean addTrailingSlash, boolean alreadyEncoded) {
            String segment = Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos, limit, HttpUrl.PATH_SEGMENT_ENCODE_SET, alreadyEncoded, false, false, false, null, 240, null);
            if (isDot(segment)) {
                return;
            }
            if (isDotDot(segment)) {
                pop();
                return;
            }
            if (this.encodedPathSegments.get(this.encodedPathSegments.size() - 1).length() == 0) {
                this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, segment);
            } else {
                this.encodedPathSegments.add(segment);
            }
            if (addTrailingSlash) {
                this.encodedPathSegments.add("");
            }
        }

        private final boolean isDot(String input) {
            return Intrinsics.areEqual(input, ".") || StringsKt.equals(input, "%2e", true);
        }

        private final boolean isDotDot(String input) {
            return Intrinsics.areEqual(input, "..") || StringsKt.equals(input, "%2e.", true) || StringsKt.equals(input, ".%2e", true) || StringsKt.equals(input, "%2e%2e", true);
        }

        private final void pop() {
            String removed = this.encodedPathSegments.remove(this.encodedPathSegments.size() - 1);
            if (removed.length() == 0) {
                if (!this.encodedPathSegments.isEmpty()) {
                    this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, "");
                    return;
                }
            }
            this.encodedPathSegments.add("");
        }

        /* compiled from: HttpUrl.kt */
        @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J \u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J \u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u001c\u0010\f\u001a\u00020\u0006*\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��¨\u0006\r"}, d2 = {"Lokhttp3/HttpUrl$Builder$Companion;", "", "()V", "INVALID_HOST", "", "parsePort", "", "input", "pos", "limit", "portColonOffset", "schemeDelimiterOffset", "slashCount", "okhttp"})
        /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/HttpUrl$Builder$Companion.class */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final int schemeDelimiterOffset(String input, int pos, int limit) {
                if (limit - pos < 2) {
                    return -1;
                }
                char c0 = input.charAt(pos);
                if ((Intrinsics.compare((int) c0, 97) < 0 || Intrinsics.compare((int) c0, 122) > 0) && (Intrinsics.compare((int) c0, 65) < 0 || Intrinsics.compare((int) c0, 90) > 0)) {
                    return -1;
                }
                for (int i = pos + 1; i < limit; i++) {
                    char cCharAt = input.charAt(i);
                    if (('a' > cCharAt || 'z' < cCharAt) && (('A' > cCharAt || 'Z' < cCharAt) && (('0' > cCharAt || '9' < cCharAt) && cCharAt != '+' && cCharAt != '-' && cCharAt != '.'))) {
                        if (cCharAt == ':') {
                            return i;
                        }
                        return -1;
                    }
                }
                return -1;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final int slashCount(String $this$slashCount, int pos, int limit) {
                char c;
                int slashCount = 0;
                for (int i = pos; i < limit && ((c = $this$slashCount.charAt(i)) == '\\' || c == '/'); i++) {
                    slashCount++;
                }
                return slashCount;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            public final int portColonOffset(String input, int pos, int limit) {
                int i = pos;
                while (i < limit) {
                    switch (input.charAt(i)) {
                        case ':':
                            return i;
                        case '[':
                            do {
                                i++;
                                if (i < limit) {
                                }
                                i++;
                            } while (input.charAt(i) != ']');
                            i++;
                            break;
                        default:
                            i++;
                    }
                }
                return limit;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final int parsePort(String input, int pos, int limit) throws NumberFormatException {
                int i;
                try {
                    String portString = Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos, limit, "", false, false, false, false, null, 248, null);
                    int i2 = Integer.parseInt(portString);
                    i = (1 <= i2 && 65535 >= i2) ? i2 : -1;
                } catch (NumberFormatException e) {
                    i = -1;
                }
                return i;
            }
        }
    }

    /* compiled from: HttpUrl.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��p\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H\u0007J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0007¢\u0006\u0002\b\u0018J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0007¢\u0006\u0002\b\u0018J\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0004H\u0007¢\u0006\u0002\b\u0018J\u0017\u0010\u001b\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001a\u00020\u0004H\u0007¢\u0006\u0002\b\u001cJa\u0010\u001d\u001a\u00020\u0004*\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00042\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'H��¢\u0006\u0002\b(J\u001c\u0010)\u001a\u00020\"*\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\u0002J/\u0010*\u001a\u00020\u0004*\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020\u00122\b\b\u0002\u0010$\u001a\u00020\"H��¢\u0006\u0002\b+J\u0011\u0010,\u001a\u00020\u0015*\u00020\u0004H\u0007¢\u0006\u0002\b\u0014J\u0013\u0010-\u001a\u0004\u0018\u00010\u0015*\u00020\u0017H\u0007¢\u0006\u0002\b\u0014J\u0013\u0010-\u001a\u0004\u0018\u00010\u0015*\u00020\u001aH\u0007¢\u0006\u0002\b\u0014J\u0013\u0010-\u001a\u0004\u0018\u00010\u0015*\u00020\u0004H\u0007¢\u0006\u0002\b\u001bJ#\u0010.\u001a\u00020/*\b\u0012\u0004\u0012\u00020\u0004002\n\u00101\u001a\u000602j\u0002`3H��¢\u0006\u0002\b4J\u0019\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000406*\u00020\u0004H��¢\u0006\u0002\b7J%\u00108\u001a\u00020/*\n\u0012\u0006\u0012\u0004\u0018\u00010\u0004002\n\u00101\u001a\u000602j\u0002`3H��¢\u0006\u0002\b9JV\u0010:\u001a\u00020/*\u00020;2\u0006\u0010<\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\"2\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J,\u0010=\u001a\u00020/*\u00020;2\u0006\u0010>\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\"H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��R\u000e\u0010\f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��R\u000e\u0010\r\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��R\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��R\u000e\u0010\u000f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��R\u000e\u0010\u0010\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n��¨\u0006?"}, d2 = {"Lokhttp3/HttpUrl$Companion;", "", "()V", "FORM_ENCODE_SET", "", "FRAGMENT_ENCODE_SET", "FRAGMENT_ENCODE_SET_URI", "HEX_DIGITS", "", "PASSWORD_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET_URI", "QUERY_COMPONENT_ENCODE_SET", "QUERY_COMPONENT_ENCODE_SET_URI", "QUERY_COMPONENT_REENCODE_SET", "QUERY_ENCODE_SET", "USERNAME_ENCODE_SET", "defaultPort", "", "scheme", BeanUtil.PREFIX_GETTER_GET, "Lokhttp3/HttpUrl;", "uri", "Ljava/net/URI;", "-deprecated_get", "url", "Ljava/net/URL;", "parse", "-deprecated_parse", "canonicalize", "pos", "limit", "encodeSet", "alreadyEncoded", "", "strict", "plusIsSpace", "unicodeAllowed", "charset", "Ljava/nio/charset/Charset;", "canonicalize$okhttp", "isPercentEncoded", "percentDecode", "percentDecode$okhttp", "toHttpUrl", "toHttpUrlOrNull", "toPathString", "", "", "out", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "toPathString$okhttp", "toQueryNamesAndValues", "", "toQueryNamesAndValues$okhttp", "toQueryString", "toQueryString$okhttp", "writeCanonicalized", "Lokio/Buffer;", "input", "writePercentDecoded", "encoded", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/HttpUrl$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:12:0x004b A[ORIG_RETURN, RETURN] */
        @kotlin.jvm.JvmStatic
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int defaultPort(@org.jetbrains.annotations.NotNull java.lang.String r4) {
            /*
                r3 = this;
                r0 = r4
                java.lang.String r1 = "scheme"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                r0 = r4
                r5 = r0
                r0 = r5
                int r0 = r0.hashCode()
                switch(r0) {
                    case 3213448: goto L28;
                    case 99617003: goto L34;
                    default: goto L4b;
                }
            L28:
                r0 = r5
                java.lang.String r1 = "http"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L4b
                goto L40
            L34:
                r0 = r5
                java.lang.String r1 = "https"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L4b
                goto L45
            L40:
                r0 = 80
                goto L4c
            L45:
                r0 = 443(0x1bb, float:6.21E-43)
                goto L4c
            L4b:
                r0 = -1
            L4c:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.HttpUrl.Companion.defaultPort(java.lang.String):int");
        }

        public final void toPathString$okhttp(@NotNull List<String> toPathString, @NotNull StringBuilder out) {
            Intrinsics.checkNotNullParameter(toPathString, "$this$toPathString");
            Intrinsics.checkNotNullParameter(out, "out");
            int size = toPathString.size();
            for (int i = 0; i < size; i++) {
                out.append('/');
                out.append(toPathString.get(i));
            }
        }

        public final void toQueryString$okhttp(@NotNull List<String> toQueryString, @NotNull StringBuilder out) {
            Intrinsics.checkNotNullParameter(toQueryString, "$this$toQueryString");
            Intrinsics.checkNotNullParameter(out, "out");
            IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, toQueryString.size()), 2);
            int i = intProgressionStep.getFirst();
            int last = intProgressionStep.getLast();
            int step = intProgressionStep.getStep();
            if (step >= 0) {
                if (i > last) {
                    return;
                }
            } else if (i < last) {
                return;
            }
            while (true) {
                String name = toQueryString.get(i);
                String value = toQueryString.get(i + 1);
                if (i > 0) {
                    out.append('&');
                }
                out.append(name);
                if (value != null) {
                    out.append('=');
                    out.append(value);
                }
                if (i == last) {
                    return;
                } else {
                    i += step;
                }
            }
        }

        @NotNull
        public final List<String> toQueryNamesAndValues$okhttp(@NotNull String toQueryNamesAndValues) {
            Intrinsics.checkNotNullParameter(toQueryNamesAndValues, "$this$toQueryNamesAndValues");
            List result = new ArrayList();
            int i = 0;
            while (true) {
                int pos = i;
                if (pos <= toQueryNamesAndValues.length()) {
                    int ampersandOffset = StringsKt.indexOf$default((CharSequence) toQueryNamesAndValues, '&', pos, false, 4, (Object) null);
                    if (ampersandOffset == -1) {
                        ampersandOffset = toQueryNamesAndValues.length();
                    }
                    int equalsOffset = StringsKt.indexOf$default((CharSequence) toQueryNamesAndValues, '=', pos, false, 4, (Object) null);
                    if (equalsOffset == -1 || equalsOffset > ampersandOffset) {
                        String strSubstring = toQueryNamesAndValues.substring(pos, ampersandOffset);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        result.add(strSubstring);
                        result.add(null);
                    } else {
                        String strSubstring2 = toQueryNamesAndValues.substring(pos, equalsOffset);
                        Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        result.add(strSubstring2);
                        String strSubstring3 = toQueryNamesAndValues.substring(equalsOffset + 1, ampersandOffset);
                        Intrinsics.checkNotNullExpressionValue(strSubstring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        result.add(strSubstring3);
                    }
                    i = ampersandOffset + 1;
                } else {
                    return result;
                }
            }
        }

        @JvmStatic
        @JvmName(name = BeanUtil.PREFIX_GETTER_GET)
        @NotNull
        public final HttpUrl get(@NotNull String toHttpUrl) {
            Intrinsics.checkNotNullParameter(toHttpUrl, "$this$toHttpUrl");
            return new Builder().parse$okhttp(null, toHttpUrl).build();
        }

        @JvmStatic
        @JvmName(name = "parse")
        @Nullable
        public final HttpUrl parse(@NotNull String toHttpUrlOrNull) {
            HttpUrl httpUrl;
            Intrinsics.checkNotNullParameter(toHttpUrlOrNull, "$this$toHttpUrlOrNull");
            try {
                httpUrl = get(toHttpUrlOrNull);
            } catch (IllegalArgumentException e) {
                httpUrl = null;
            }
            return httpUrl;
        }

        @JvmStatic
        @JvmName(name = BeanUtil.PREFIX_GETTER_GET)
        @Nullable
        public final HttpUrl get(@NotNull URL toHttpUrlOrNull) {
            Intrinsics.checkNotNullParameter(toHttpUrlOrNull, "$this$toHttpUrlOrNull");
            String string = toHttpUrlOrNull.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString()");
            return parse(string);
        }

        @JvmStatic
        @JvmName(name = BeanUtil.PREFIX_GETTER_GET)
        @Nullable
        public final HttpUrl get(@NotNull URI toHttpUrlOrNull) {
            Intrinsics.checkNotNullParameter(toHttpUrlOrNull, "$this$toHttpUrlOrNull");
            String string = toHttpUrlOrNull.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString()");
            return parse(string);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okhttp3.HttpUrl.Companion.toHttpUrl"}, expression = "url.toHttpUrl()"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_get")
        @NotNull
        /* renamed from: -deprecated_get, reason: not valid java name */
        public final HttpUrl m4499deprecated_get(@NotNull String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return get(url);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}, expression = "url.toHttpUrlOrNull()"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_parse")
        @Nullable
        /* renamed from: -deprecated_parse, reason: not valid java name */
        public final HttpUrl m4500deprecated_parse(@NotNull String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return parse(url);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}, expression = "url.toHttpUrlOrNull()"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_get")
        @Nullable
        /* renamed from: -deprecated_get, reason: not valid java name */
        public final HttpUrl m4501deprecated_get(@NotNull URL url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return get(url);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}, expression = "uri.toHttpUrlOrNull()"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_get")
        @Nullable
        /* renamed from: -deprecated_get, reason: not valid java name */
        public final HttpUrl m4502deprecated_get(@NotNull URI uri) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            return get(uri);
        }

        public static /* synthetic */ String percentDecode$okhttp$default(Companion companion, String str, int i, int i2, boolean z, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = str.length();
            }
            if ((i3 & 4) != 0) {
                z = false;
            }
            return companion.percentDecode$okhttp(str, i, i2, z);
        }

        @NotNull
        public final String percentDecode$okhttp(@NotNull String percentDecode, int pos, int limit, boolean plusIsSpace) {
            Intrinsics.checkNotNullParameter(percentDecode, "$this$percentDecode");
            for (int i = pos; i < limit; i++) {
                char c = percentDecode.charAt(i);
                if (c == '%' || (c == '+' && plusIsSpace)) {
                    Buffer out = new Buffer();
                    out.writeUtf8(percentDecode, pos, i);
                    writePercentDecoded(out, percentDecode, i, limit, plusIsSpace);
                    return out.readUtf8();
                }
            }
            String strSubstring = percentDecode.substring(pos, limit);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return strSubstring;
        }

        private final void writePercentDecoded(Buffer $this$writePercentDecoded, String encoded, int pos, int limit, boolean plusIsSpace) {
            int i = pos;
            while (i < limit) {
                if (encoded == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                int codePoint = encoded.codePointAt(i);
                if (codePoint == 37 && i + 2 < limit) {
                    int d1 = Util.parseHexDigit(encoded.charAt(i + 1));
                    int d2 = Util.parseHexDigit(encoded.charAt(i + 2));
                    if (d1 != -1 && d2 != -1) {
                        $this$writePercentDecoded.writeByte((d1 << 4) + d2);
                        i = i + 2 + Character.charCount(codePoint);
                    } else {
                        $this$writePercentDecoded.writeUtf8CodePoint(codePoint);
                        i += Character.charCount(codePoint);
                    }
                } else if (codePoint == 43 && plusIsSpace) {
                    $this$writePercentDecoded.writeByte(32);
                    i++;
                } else {
                    $this$writePercentDecoded.writeUtf8CodePoint(codePoint);
                    i += Character.charCount(codePoint);
                }
            }
        }

        private final boolean isPercentEncoded(String $this$isPercentEncoded, int pos, int limit) {
            return pos + 2 < limit && $this$isPercentEncoded.charAt(pos) == '%' && Util.parseHexDigit($this$isPercentEncoded.charAt(pos + 1)) != -1 && Util.parseHexDigit($this$isPercentEncoded.charAt(pos + 2)) != -1;
        }

        public static /* synthetic */ String canonicalize$okhttp$default(Companion companion, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = str.length();
            }
            if ((i3 & 8) != 0) {
                z = false;
            }
            if ((i3 & 16) != 0) {
                z2 = false;
            }
            if ((i3 & 32) != 0) {
                z3 = false;
            }
            if ((i3 & 64) != 0) {
                z4 = false;
            }
            if ((i3 & 128) != 0) {
                charset = (Charset) null;
            }
            return companion.canonicalize$okhttp(str, i, i2, str2, z, z2, z3, z4, charset);
        }

        @NotNull
        public final String canonicalize$okhttp(@NotNull String canonicalize, int pos, int limit, @NotNull String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean unicodeAllowed, @Nullable Charset charset) {
            int i;
            Intrinsics.checkNotNullParameter(canonicalize, "$this$canonicalize");
            Intrinsics.checkNotNullParameter(encodeSet, "encodeSet");
            int iCharCount = pos;
            while (true) {
                i = iCharCount;
                if (i < limit) {
                    int codePoint = canonicalize.codePointAt(i);
                    if (codePoint < 32 || codePoint == 127 || ((codePoint >= 128 && !unicodeAllowed) || StringsKt.contains$default((CharSequence) encodeSet, (char) codePoint, false, 2, (Object) null) || ((codePoint == 37 && (!alreadyEncoded || (strict && !isPercentEncoded(canonicalize, i, limit)))) || (codePoint == 43 && plusIsSpace)))) {
                        break;
                    }
                    iCharCount = i + Character.charCount(codePoint);
                } else {
                    String strSubstring = canonicalize.substring(pos, limit);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    return strSubstring;
                }
            }
            Buffer out = new Buffer();
            out.writeUtf8(canonicalize, pos, i);
            writeCanonicalized(out, canonicalize, i, limit, encodeSet, alreadyEncoded, strict, plusIsSpace, unicodeAllowed, charset);
            return out.readUtf8();
        }

        private final void writeCanonicalized(Buffer $this$writeCanonicalized, String input, int pos, int limit, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean unicodeAllowed, Charset charset) {
            Buffer encodedCharBuffer = (Buffer) null;
            int iCharCount = pos;
            while (true) {
                int i = iCharCount;
                if (i < limit) {
                    if (input == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    int codePoint = input.codePointAt(i);
                    if (!alreadyEncoded || (codePoint != 9 && codePoint != 10 && codePoint != 12 && codePoint != 13)) {
                        if (codePoint == 43 && plusIsSpace) {
                            $this$writeCanonicalized.writeUtf8(alreadyEncoded ? Marker.ANY_NON_NULL_MARKER : "%2B");
                        } else if (codePoint < 32 || codePoint == 127 || ((codePoint >= 128 && !unicodeAllowed) || StringsKt.contains$default((CharSequence) encodeSet, (char) codePoint, false, 2, (Object) null) || (codePoint == 37 && (!alreadyEncoded || (strict && !isPercentEncoded(input, i, limit)))))) {
                            if (encodedCharBuffer == null) {
                                encodedCharBuffer = new Buffer();
                            }
                            if (charset == null || Intrinsics.areEqual(charset, StandardCharsets.UTF_8)) {
                                encodedCharBuffer.writeUtf8CodePoint(codePoint);
                            } else {
                                encodedCharBuffer.writeString(input, i, i + Character.charCount(codePoint), charset);
                            }
                            while (!encodedCharBuffer.exhausted()) {
                                int b = encodedCharBuffer.readByte() & 255;
                                $this$writeCanonicalized.writeByte(37);
                                $this$writeCanonicalized.writeByte((int) HttpUrl.HEX_DIGITS[(b >> 4) & 15]);
                                $this$writeCanonicalized.writeByte((int) HttpUrl.HEX_DIGITS[b & 15]);
                            }
                        } else {
                            $this$writeCanonicalized.writeUtf8CodePoint(codePoint);
                        }
                    }
                    iCharCount = i + Character.charCount(codePoint);
                } else {
                    return;
                }
            }
        }
    }
}
