package okhttp3.internal.http;

import io.netty.handler.codec.http.HttpHeaders;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Challenge;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* compiled from: HttpHeaders.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"��R\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n��\u001a\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f\u001a\n\u0010\r\u001a\u00020\u0004*\u00020\u0006\u001a\u001a\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0012H\u0002\u001a\u000e\u0010\u0013\u001a\u0004\u0018\u00010\f*\u00020\u0010H\u0002\u001a\u000e\u0010\u0014\u001a\u0004\u0018\u00010\f*\u00020\u0010H\u0002\u001a\u001a\u0010\u0015\u001a\u00020\u000f*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\n\u001a\f\u0010\u001a\u001a\u00020\u0004*\u00020\u0010H\u0002\u001a\u0014\u0010\u001b\u001a\u00020\u0004*\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001dH\u0002\"\u000e\u0010��\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u001e"}, d2 = {"QUOTED_STRING_DELIMITERS", "Lokio/ByteString;", "TOKEN_DELIMITERS", "hasBody", "", "response", "Lokhttp3/Response;", "parseChallenges", "", "Lokhttp3/Challenge;", "Lokhttp3/Headers;", "headerName", "", "promisesBody", "readChallengeHeader", "", "Lokio/Buffer;", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "", "readQuotedString", "readToken", "receiveHeaders", "Lokhttp3/CookieJar;", "url", "Lokhttp3/HttpUrl;", "headers", "skipCommasAndWhitespace", "startsWith", "prefix", "", "okhttp"})
@JvmName(name = "HttpHeaders")
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/http/HttpHeaders.class */
public final class HttpHeaders {
    private static final ByteString QUOTED_STRING_DELIMITERS = ByteString.Companion.encodeUtf8("\"\\");
    private static final ByteString TOKEN_DELIMITERS = ByteString.Companion.encodeUtf8("\t ,=");

    @NotNull
    public static final List<Challenge> parseChallenges(@NotNull Headers parseChallenges, @NotNull String headerName) {
        Intrinsics.checkNotNullParameter(parseChallenges, "$this$parseChallenges");
        Intrinsics.checkNotNullParameter(headerName, "headerName");
        List result = new ArrayList();
        int size = parseChallenges.size();
        for (int h = 0; h < size; h++) {
            if (StringsKt.equals(headerName, parseChallenges.name(h), true)) {
                Buffer header = new Buffer().writeUtf8(parseChallenges.value(h));
                try {
                    readChallengeHeader(header, result);
                } catch (EOFException e) {
                    Platform.Companion.get().log("Unable to parse challenge", 5, e);
                }
            }
        }
        return result;
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x013d, code lost:
    
        r10.add(new okhttp3.Challenge(r0, (java.util.Map<java.lang.String, java.lang.String>) r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x013d A[EDGE_INSN: B:73:0x013d->B:56:0x013d BREAK  A[LOOP:1: B:23:0x00bc->B:74:0x00bc], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void readChallengeHeader(okio.Buffer r9, java.util.List<okhttp3.Challenge> r10) throws java.io.EOFException {
        /*
            Method dump skipped, instructions count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.HttpHeaders.readChallengeHeader(okio.Buffer, java.util.List):void");
    }

    private static final boolean skipCommasAndWhitespace(Buffer $this$skipCommasAndWhitespace) throws EOFException {
        boolean commaFound = false;
        while (!$this$skipCommasAndWhitespace.exhausted()) {
            switch ($this$skipCommasAndWhitespace.getByte(0L)) {
                case 9:
                case 32:
                    $this$skipCommasAndWhitespace.readByte();
                    break;
                case 44:
                    $this$skipCommasAndWhitespace.readByte();
                    commaFound = true;
                    break;
                default:
                    return commaFound;
            }
        }
        return commaFound;
    }

    private static final boolean startsWith(Buffer $this$startsWith, byte prefix) {
        return !$this$startsWith.exhausted() && $this$startsWith.getByte(0L) == prefix;
    }

    private static final String readQuotedString(Buffer $this$readQuotedString) throws EOFException {
        if (!($this$readQuotedString.readByte() == ((byte) 34))) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Buffer result = new Buffer();
        while (true) {
            long i = $this$readQuotedString.indexOfElement(QUOTED_STRING_DELIMITERS);
            if (i == -1) {
                return null;
            }
            if ($this$readQuotedString.getByte(i) == ((byte) 34)) {
                result.write($this$readQuotedString, i);
                $this$readQuotedString.readByte();
                return result.readUtf8();
            }
            if ($this$readQuotedString.size() == i + 1) {
                return null;
            }
            result.write($this$readQuotedString, i);
            $this$readQuotedString.readByte();
            result.write($this$readQuotedString, 1L);
        }
    }

    private static final String readToken(Buffer $this$readToken) {
        long tokenSize = $this$readToken.indexOfElement(TOKEN_DELIMITERS);
        if (tokenSize == -1) {
            tokenSize = $this$readToken.size();
        }
        if (tokenSize != 0) {
            return $this$readToken.readUtf8(tokenSize);
        }
        return null;
    }

    public static final void receiveHeaders(@NotNull CookieJar receiveHeaders, @NotNull HttpUrl url, @NotNull Headers headers) {
        Intrinsics.checkNotNullParameter(receiveHeaders, "$this$receiveHeaders");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        if (receiveHeaders == CookieJar.NO_COOKIES) {
            return;
        }
        List cookies = Cookie.Companion.parseAll(url, headers);
        if (cookies.isEmpty()) {
            return;
        }
        receiveHeaders.saveFromResponse(url, cookies);
    }

    public static final boolean promisesBody(@NotNull Response promisesBody) {
        Intrinsics.checkNotNullParameter(promisesBody, "$this$promisesBody");
        if (Intrinsics.areEqual(promisesBody.request().method(), "HEAD")) {
            return false;
        }
        int responseCode = promisesBody.code();
        if (((responseCode < 100 || responseCode >= 200) && responseCode != 204 && responseCode != 304) || Util.headersContentLength(promisesBody) != -1 || StringsKt.equals(HttpHeaders.Values.CHUNKED, Response.header$default(promisesBody, "Transfer-Encoding", null, 2, null), true)) {
            return true;
        }
        return false;
    }

    @Deprecated(message = "No longer supported", level = DeprecationLevel.ERROR, replaceWith = @ReplaceWith(imports = {}, expression = "response.promisesBody()"))
    public static final boolean hasBody(@NotNull Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        return promisesBody(response);
    }
}
