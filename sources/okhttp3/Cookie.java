package okhttp3;

import cn.hutool.core.date.chinese.LunarInfo;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: Cookie.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018�� &2\u00020\u0001:\u0002%&BO\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n¢\u0006\u0002\u0010\u000eJ\r\u0010\u0007\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0012J\u0013\u0010\u0013\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\r\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0017J\r\u0010\r\u001a\u00020\nH\u0007¢\u0006\u0002\b\u0018J\r\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0002\b\u0019J\u000e\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001cJ\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u001dJ\r\u0010\b\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u001eJ\r\u0010\f\u001a\u00020\nH\u0007¢\u0006\u0002\b\u001fJ\r\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\b J\b\u0010!\u001a\u00020\u0003H\u0016J\u0015\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\nH��¢\u0006\u0002\b#J\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\b$R\u0013\u0010\u0007\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\u000fR\u0013\u0010\u0005\u001a\u00020\u00068\u0007¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0010R\u0013\u0010\r\u001a\u00020\n8\u0007¢\u0006\b\n��\u001a\u0004\b\r\u0010\u0011R\u0013\u0010\u000b\u001a\u00020\n8\u0007¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\u0011R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u000fR\u0013\u0010\b\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\b\u0010\u000fR\u0013\u0010\f\u001a\u00020\n8\u0007¢\u0006\b\n��\u001a\u0004\b\f\u0010\u0011R\u0013\u0010\t\u001a\u00020\n8\u0007¢\u0006\b\n��\u001a\u0004\b\t\u0010\u0011R\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0004\u0010\u000f¨\u0006'"}, d2 = {"Lokhttp3/Cookie;", "", "name", "", "value", "expiresAt", "", "domain", "path", "secure", "", "httpOnly", "persistent", "hostOnly", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;ZZZZ)V", "()Ljava/lang/String;", "()J", "()Z", "-deprecated_domain", "equals", "other", "-deprecated_expiresAt", IdentityNamingStrategy.HASH_CODE_KEY, "", "-deprecated_hostOnly", "-deprecated_httpOnly", "matches", "url", "Lokhttp3/HttpUrl;", "-deprecated_name", "-deprecated_path", "-deprecated_persistent", "-deprecated_secure", "toString", "forObsoleteRfc2965", "toString$okhttp", "-deprecated_value", "Builder", "Companion", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/Cookie.class */
public final class Cookie {

    @NotNull
    private final String name;

    @NotNull
    private final String value;
    private final long expiresAt;

    @NotNull
    private final String domain;

    @NotNull
    private final String path;
    private final boolean secure;
    private final boolean httpOnly;
    private final boolean persistent;
    private final boolean hostOnly;
    public static final Companion Companion = new Companion(null);
    private static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
    private static final Pattern MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    @JvmStatic
    @Nullable
    public static final Cookie parse(@NotNull HttpUrl url, @NotNull String setCookie) {
        return Companion.parse(url, setCookie);
    }

    @JvmStatic
    @NotNull
    public static final List<Cookie> parseAll(@NotNull HttpUrl url, @NotNull Headers headers) {
        return Companion.parseAll(url, headers);
    }

    private Cookie(String name, String value, long expiresAt, String domain, String path, boolean secure, boolean httpOnly, boolean persistent, boolean hostOnly) {
        this.name = name;
        this.value = value;
        this.expiresAt = expiresAt;
        this.domain = domain;
        this.path = path;
        this.secure = secure;
        this.httpOnly = httpOnly;
        this.persistent = persistent;
        this.hostOnly = hostOnly;
    }

    public /* synthetic */ Cookie(String name, String value, long expiresAt, String domain, String path, boolean secure, boolean httpOnly, boolean persistent, boolean hostOnly, DefaultConstructorMarker $constructor_marker) {
        this(name, value, expiresAt, domain, path, secure, httpOnly, persistent, hostOnly);
    }

    @JvmName(name = "name")
    @NotNull
    public final String name() {
        return this.name;
    }

    @JvmName(name = "value")
    @NotNull
    public final String value() {
        return this.value;
    }

    @JvmName(name = "expiresAt")
    public final long expiresAt() {
        return this.expiresAt;
    }

    @JvmName(name = "domain")
    @NotNull
    public final String domain() {
        return this.domain;
    }

    @JvmName(name = "path")
    @NotNull
    public final String path() {
        return this.path;
    }

    @JvmName(name = "secure")
    public final boolean secure() {
        return this.secure;
    }

    @JvmName(name = "httpOnly")
    public final boolean httpOnly() {
        return this.httpOnly;
    }

    @JvmName(name = "persistent")
    public final boolean persistent() {
        return this.persistent;
    }

    @JvmName(name = "hostOnly")
    public final boolean hostOnly() {
        return this.hostOnly;
    }

    public final boolean matches(@NotNull HttpUrl url) {
        boolean zDomainMatch;
        Intrinsics.checkNotNullParameter(url, "url");
        if (this.hostOnly) {
            zDomainMatch = Intrinsics.areEqual(url.host(), this.domain);
        } else {
            zDomainMatch = Companion.domainMatch(url.host(), this.domain);
        }
        boolean domainMatch = zDomainMatch;
        if (domainMatch && Companion.pathMatch(url, this.path)) {
            return !this.secure || url.isHttps();
        }
        return false;
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof Cookie) && Intrinsics.areEqual(((Cookie) other).name, this.name) && Intrinsics.areEqual(((Cookie) other).value, this.value) && ((Cookie) other).expiresAt == this.expiresAt && Intrinsics.areEqual(((Cookie) other).domain, this.domain) && Intrinsics.areEqual(((Cookie) other).path, this.path) && ((Cookie) other).secure == this.secure && ((Cookie) other).httpOnly == this.httpOnly && ((Cookie) other).persistent == this.persistent && ((Cookie) other).hostOnly == this.hostOnly;
    }

    @IgnoreJRERequirement
    public int hashCode() {
        int result = (31 * 17) + this.name.hashCode();
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + this.value.hashCode())) + Long.hashCode(this.expiresAt))) + this.domain.hashCode())) + this.path.hashCode())) + Boolean.hashCode(this.secure))) + Boolean.hashCode(this.httpOnly))) + Boolean.hashCode(this.persistent))) + Boolean.hashCode(this.hostOnly);
    }

    @NotNull
    public String toString() {
        return toString$okhttp(false);
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "name"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_name")
    @NotNull
    /* renamed from: -deprecated_name, reason: not valid java name */
    public final String m4449deprecated_name() {
        return this.name;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "value"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_value")
    @NotNull
    /* renamed from: -deprecated_value, reason: not valid java name */
    public final String m4450deprecated_value() {
        return this.value;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "persistent"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_persistent")
    /* renamed from: -deprecated_persistent, reason: not valid java name */
    public final boolean m4451deprecated_persistent() {
        return this.persistent;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "expiresAt"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_expiresAt")
    /* renamed from: -deprecated_expiresAt, reason: not valid java name */
    public final long m4452deprecated_expiresAt() {
        return this.expiresAt;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "hostOnly"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_hostOnly")
    /* renamed from: -deprecated_hostOnly, reason: not valid java name */
    public final boolean m4453deprecated_hostOnly() {
        return this.hostOnly;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "domain"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_domain")
    @NotNull
    /* renamed from: -deprecated_domain, reason: not valid java name */
    public final String m4454deprecated_domain() {
        return this.domain;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "path"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_path")
    @NotNull
    /* renamed from: -deprecated_path, reason: not valid java name */
    public final String m4455deprecated_path() {
        return this.path;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "httpOnly"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_httpOnly")
    /* renamed from: -deprecated_httpOnly, reason: not valid java name */
    public final boolean m4456deprecated_httpOnly() {
        return this.httpOnly;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "secure"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_secure")
    /* renamed from: -deprecated_secure, reason: not valid java name */
    public final boolean m4457deprecated_secure() {
        return this.secure;
    }

    @NotNull
    public final String toString$okhttp(boolean forObsoleteRfc2965) {
        StringBuilder $this$buildString = new StringBuilder();
        $this$buildString.append(this.name);
        $this$buildString.append('=');
        $this$buildString.append(this.value);
        if (this.persistent) {
            if (this.expiresAt == Long.MIN_VALUE) {
                $this$buildString.append("; max-age=0");
            } else {
                $this$buildString.append("; expires=").append(DatesKt.toHttpDateString(new Date(this.expiresAt)));
            }
        }
        if (!this.hostOnly) {
            $this$buildString.append("; domain=");
            if (forObsoleteRfc2965) {
                $this$buildString.append(".");
            }
            $this$buildString.append(this.domain);
        }
        $this$buildString.append("; path=").append(this.path);
        if (this.secure) {
            $this$buildString.append("; secure");
        }
        if (this.httpOnly) {
            $this$buildString.append("; httponly");
        }
        String string = $this$buildString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString()");
        return string;
    }

    /* compiled from: Cookie.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0003\u001a\u00020��2\u0006\u0010\u0003\u001a\u00020\u0004J\u0018\u0010\u0003\u001a\u00020��2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\u0005\u001a\u00020��2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020��2\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020��J\u000e\u0010\n\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020��2\u0006\u0010\u000b\u001a\u00020\u0004J\u0006\u0010\r\u001a\u00020��J\u000e\u0010\u000e\u001a\u00020��2\u0006\u0010\u000e\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0012"}, d2 = {"Lokhttp3/Cookie$Builder;", "", "()V", "domain", "", "expiresAt", "", "hostOnly", "", "httpOnly", "name", "path", "persistent", "secure", "value", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lokhttp3/Cookie;", "hostOnlyDomain", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/Cookie$Builder.class */
    public static final class Builder {
        private String name;
        private String value;
        private String domain;
        private boolean secure;
        private boolean httpOnly;
        private boolean persistent;
        private boolean hostOnly;
        private long expiresAt = DatesKt.MAX_DATE;
        private String path = "/";

        @NotNull
        public final Builder name(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder $this$apply = this;
            if (!Intrinsics.areEqual(StringsKt.trim((CharSequence) name).toString(), name)) {
                throw new IllegalArgumentException("name is not trimmed".toString());
            }
            $this$apply.name = name;
            return this;
        }

        @NotNull
        public final Builder value(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            Builder $this$apply = this;
            if (!Intrinsics.areEqual(StringsKt.trim((CharSequence) value).toString(), value)) {
                throw new IllegalArgumentException("value is not trimmed".toString());
            }
            $this$apply.value = value;
            return this;
        }

        @NotNull
        public final Builder expiresAt(long expiresAt) {
            Builder $this$apply = this;
            long expiresAt2 = expiresAt;
            if (expiresAt2 <= 0) {
                expiresAt2 = Long.MIN_VALUE;
            }
            if (expiresAt2 > DatesKt.MAX_DATE) {
                expiresAt2 = 253402300799999L;
            }
            $this$apply.expiresAt = expiresAt2;
            $this$apply.persistent = true;
            return this;
        }

        @NotNull
        public final Builder domain(@NotNull String domain) {
            Intrinsics.checkNotNullParameter(domain, "domain");
            return domain(domain, false);
        }

        @NotNull
        public final Builder hostOnlyDomain(@NotNull String domain) {
            Intrinsics.checkNotNullParameter(domain, "domain");
            return domain(domain, true);
        }

        private final Builder domain(String domain, boolean hostOnly) {
            Builder $this$apply = this;
            String canonicalDomain = HostnamesKt.toCanonicalHost(domain);
            if (canonicalDomain == null) {
                throw new IllegalArgumentException("unexpected domain: " + domain);
            }
            $this$apply.domain = canonicalDomain;
            $this$apply.hostOnly = hostOnly;
            return this;
        }

        @NotNull
        public final Builder path(@NotNull String path) {
            Intrinsics.checkNotNullParameter(path, "path");
            Builder $this$apply = this;
            if (!StringsKt.startsWith$default(path, "/", false, 2, (Object) null)) {
                throw new IllegalArgumentException("path must start with '/'".toString());
            }
            $this$apply.path = path;
            return this;
        }

        @NotNull
        public final Builder secure() {
            Builder $this$apply = this;
            $this$apply.secure = true;
            return this;
        }

        @NotNull
        public final Builder httpOnly() {
            Builder $this$apply = this;
            $this$apply.httpOnly = true;
            return this;
        }

        @NotNull
        public final Cookie build() {
            String str = this.name;
            if (str == null) {
                throw new NullPointerException("builder.name == null");
            }
            String str2 = this.value;
            if (str2 == null) {
                throw new NullPointerException("builder.value == null");
            }
            long j = this.expiresAt;
            String str3 = this.domain;
            if (str3 != null) {
                return new Cookie(str, str2, j, str3, this.path, this.secure, this.httpOnly, this.persistent, this.hostOnly, null);
            }
            throw new NullPointerException("builder.domain == null");
        }
    }

    /* compiled from: Cookie.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0002J'\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fH��¢\u0006\u0002\b\u001bJ\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fH\u0007J\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\u0010\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\fH\u0002J \u0010\"\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH\u0002J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\fH\u0002J\u0018\u0010$\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010%\u001a\u00020\fH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\u0007\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\b\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n��¨\u0006&"}, d2 = {"Lokhttp3/Cookie$Companion;", "", "()V", "DAY_OF_MONTH_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "MONTH_PATTERN", "TIME_PATTERN", "YEAR_PATTERN", "dateCharacterOffset", "", "input", "", "pos", "limit", "invert", "", "domainMatch", "urlHost", "domain", "parse", "Lokhttp3/Cookie;", "currentTimeMillis", "", "url", "Lokhttp3/HttpUrl;", "setCookie", "parse$okhttp", "parseAll", "", "headers", "Lokhttp3/Headers;", "parseDomain", OperatorName.CLOSE_AND_STROKE, "parseExpires", "parseMaxAge", "pathMatch", "path", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/Cookie$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean domainMatch(String urlHost, String domain) {
            if (Intrinsics.areEqual(urlHost, domain)) {
                return true;
            }
            return StringsKt.endsWith$default(urlHost, domain, false, 2, (Object) null) && urlHost.charAt((urlHost.length() - domain.length()) - 1) == '.' && !Util.canParseAsIpAddress(urlHost);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean pathMatch(HttpUrl url, String path) {
            String urlPath = url.encodedPath();
            if (Intrinsics.areEqual(urlPath, path)) {
                return true;
            }
            if (StringsKt.startsWith$default(urlPath, path, false, 2, (Object) null)) {
                return StringsKt.endsWith$default(path, "/", false, 2, (Object) null) || urlPath.charAt(path.length()) == '/';
            }
            return false;
        }

        @JvmStatic
        @Nullable
        public final Cookie parse(@NotNull HttpUrl url, @NotNull String setCookie) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(setCookie, "setCookie");
            return parse$okhttp(System.currentTimeMillis(), url, setCookie);
        }

        /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
            jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:28:0x00fd
            	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
            	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
            */
        @org.jetbrains.annotations.Nullable
        public final okhttp3.Cookie parse$okhttp(long r15, @org.jetbrains.annotations.NotNull okhttp3.HttpUrl r17, @org.jetbrains.annotations.NotNull java.lang.String r18) {
            /*
                Method dump skipped, instructions count: 670
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cookie.Companion.parse$okhttp(long, okhttp3.HttpUrl, java.lang.String):okhttp3.Cookie");
        }

        private final long parseExpires(String s, int pos, int limit) throws NumberFormatException {
            int pos2 = dateCharacterOffset(s, pos, limit, false);
            int hour = -1;
            int minute = -1;
            int second = -1;
            int dayOfMonth = -1;
            int month = -1;
            int year = -1;
            Matcher matcher = Cookie.TIME_PATTERN.matcher(s);
            while (pos2 < limit) {
                int end = dateCharacterOffset(s, pos2 + 1, limit, true);
                matcher.region(pos2, end);
                if (hour == -1 && matcher.usePattern(Cookie.TIME_PATTERN).matches()) {
                    String strGroup = matcher.group(1);
                    Intrinsics.checkNotNullExpressionValue(strGroup, "matcher.group(1)");
                    hour = Integer.parseInt(strGroup);
                    String strGroup2 = matcher.group(2);
                    Intrinsics.checkNotNullExpressionValue(strGroup2, "matcher.group(2)");
                    minute = Integer.parseInt(strGroup2);
                    String strGroup3 = matcher.group(3);
                    Intrinsics.checkNotNullExpressionValue(strGroup3, "matcher.group(3)");
                    second = Integer.parseInt(strGroup3);
                } else if (dayOfMonth == -1 && matcher.usePattern(Cookie.DAY_OF_MONTH_PATTERN).matches()) {
                    String strGroup4 = matcher.group(1);
                    Intrinsics.checkNotNullExpressionValue(strGroup4, "matcher.group(1)");
                    dayOfMonth = Integer.parseInt(strGroup4);
                } else if (month == -1 && matcher.usePattern(Cookie.MONTH_PATTERN).matches()) {
                    String strGroup5 = matcher.group(1);
                    Intrinsics.checkNotNullExpressionValue(strGroup5, "matcher.group(1)");
                    Locale locale = Locale.US;
                    Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
                    if (strGroup5 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String monthString = strGroup5.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(monthString, "(this as java.lang.String).toLowerCase(locale)");
                    String strPattern = Cookie.MONTH_PATTERN.pattern();
                    Intrinsics.checkNotNullExpressionValue(strPattern, "MONTH_PATTERN.pattern()");
                    month = StringsKt.indexOf$default((CharSequence) strPattern, monthString, 0, false, 6, (Object) null) / 4;
                } else if (year == -1 && matcher.usePattern(Cookie.YEAR_PATTERN).matches()) {
                    String strGroup6 = matcher.group(1);
                    Intrinsics.checkNotNullExpressionValue(strGroup6, "matcher.group(1)");
                    year = Integer.parseInt(strGroup6);
                }
                pos2 = dateCharacterOffset(s, end + 1, limit, false);
            }
            int i = year;
            if (70 <= i && 99 >= i) {
                year += LunarInfo.BASE_YEAR;
            }
            int i2 = year;
            if (0 <= i2 && 69 >= i2) {
                year += 2000;
            }
            if (!(year >= 1601)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (!(month != -1)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            int i3 = dayOfMonth;
            if (!(1 <= i3 && 31 >= i3)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            int i4 = hour;
            if (!(0 <= i4 && 23 >= i4)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            int i5 = minute;
            if (!(0 <= i5 && 59 >= i5)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            int i6 = second;
            if (!(0 <= i6 && 59 >= i6)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            GregorianCalendar $this$apply = new GregorianCalendar(Util.UTC);
            $this$apply.setLenient(false);
            $this$apply.set(1, year);
            $this$apply.set(2, month - 1);
            $this$apply.set(5, dayOfMonth);
            $this$apply.set(11, hour);
            $this$apply.set(12, minute);
            $this$apply.set(13, second);
            $this$apply.set(14, 0);
            return $this$apply.getTimeInMillis();
        }

        private final int dateCharacterOffset(String input, int pos, int limit, boolean invert) {
            for (int i = pos; i < limit; i++) {
                int c = input.charAt(i);
                boolean dateCharacter = (c < 32 && c != 9) || c >= 127 || (48 <= c && 57 >= c) || ((97 <= c && 122 >= c) || ((65 <= c && 90 >= c) || c == 58));
                if (dateCharacter == (!invert)) {
                    return i;
                }
            }
            return limit;
        }

        private final long parseMaxAge(String s) throws NumberFormatException {
            try {
                long parsed = Long.parseLong(s);
                if (parsed <= 0) {
                    return Long.MIN_VALUE;
                }
                return parsed;
            } catch (NumberFormatException e) {
                if (new Regex("-?\\d+").matches(s)) {
                    return StringsKt.startsWith$default(s, "-", false, 2, (Object) null) ? Long.MIN_VALUE : Long.MAX_VALUE;
                }
                throw e;
            }
        }

        private final String parseDomain(String s) {
            if (!(!StringsKt.endsWith$default(s, ".", false, 2, (Object) null))) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            String canonicalHost = HostnamesKt.toCanonicalHost(StringsKt.removePrefix(s, (CharSequence) "."));
            if (canonicalHost != null) {
                return canonicalHost;
            }
            throw new IllegalArgumentException();
        }

        @JvmStatic
        @NotNull
        public final List<Cookie> parseAll(@NotNull HttpUrl url, @NotNull Headers headers) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(headers, "headers");
            List cookieStrings = headers.values("Set-Cookie");
            List cookies = (List) null;
            int size = cookieStrings.size();
            for (int i = 0; i < size; i++) {
                Cookie cookie = parse(url, cookieStrings.get(i));
                if (cookie != null) {
                    if (cookies == null) {
                        cookies = new ArrayList();
                    }
                    cookies.add(cookie);
                }
            }
            if (cookies != null) {
                List<Cookie> listUnmodifiableList = Collections.unmodifiableList(cookies);
                Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "Collections.unmodifiableList(cookies)");
                return listUnmodifiableList;
            }
            return CollectionsKt.emptyList();
        }
    }
}
