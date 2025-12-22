package okhttp3;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: MediaType.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018�� \u00182\u00020\u0001:\u0001\u0018B-\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u0007J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0014\u001a\u00020\u0003J\r\u0010\u0005\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0015J\b\u0010\u0016\u001a\u00020\u0003H\u0016J\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0013\u0010\u0005\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\nR\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0004\u0010\n¨\u0006\u0019"}, d2 = {"Lokhttp3/MediaType;", "", "mediaType", "", "type", "subtype", "parameterNamesAndValues", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "[Ljava/lang/String;", "()Ljava/lang/String;", "charset", "Ljava/nio/charset/Charset;", "defaultValue", "equals", "", "other", IdentityNamingStrategy.HASH_CODE_KEY, "", "parameter", "name", "-deprecated_subtype", "toString", "-deprecated_type", "Companion", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/MediaType.class */
public final class MediaType {
    private final String mediaType;

    @NotNull
    private final String type;

    @NotNull
    private final String subtype;
    private final String[] parameterNamesAndValues;
    private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
    private static final String QUOTED = "\"([^\"]*)\"";
    public static final Companion Companion = new Companion(null);
    private static final Pattern TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    private static final Pattern PARAMETER = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    @JvmOverloads
    @Nullable
    public final Charset charset() {
        return charset$default(this, null, 1, null);
    }

    @JvmStatic
    @JvmName(name = BeanUtil.PREFIX_GETTER_GET)
    @NotNull
    public static final MediaType get(@NotNull String $this$toMediaType) {
        return Companion.get($this$toMediaType);
    }

    @JvmStatic
    @JvmName(name = "parse")
    @Nullable
    public static final MediaType parse(@NotNull String $this$toMediaTypeOrNull) {
        return Companion.parse($this$toMediaTypeOrNull);
    }

    private MediaType(String mediaType, String type, String subtype, String[] parameterNamesAndValues) {
        this.mediaType = mediaType;
        this.type = type;
        this.subtype = subtype;
        this.parameterNamesAndValues = parameterNamesAndValues;
    }

    public /* synthetic */ MediaType(String mediaType, String type, String subtype, String[] parameterNamesAndValues, DefaultConstructorMarker $constructor_marker) {
        this(mediaType, type, subtype, parameterNamesAndValues);
    }

    @JvmName(name = "type")
    @NotNull
    public final String type() {
        return this.type;
    }

    @JvmName(name = "subtype")
    @NotNull
    public final String subtype() {
        return this.subtype;
    }

    public static /* synthetic */ Charset charset$default(MediaType mediaType, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = (Charset) null;
        }
        return mediaType.charset(charset);
    }

    @JvmOverloads
    @Nullable
    public final Charset charset(@Nullable Charset defaultValue) {
        Charset charsetForName;
        String charset = parameter("charset");
        if (charset == null) {
            return defaultValue;
        }
        try {
            charsetForName = Charset.forName(charset);
        } catch (IllegalArgumentException e) {
            charsetForName = defaultValue;
        }
        return charsetForName;
    }

    @Nullable
    public final String parameter(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        IntProgression intProgressionStep = RangesKt.step(ArraysKt.getIndices(this.parameterNamesAndValues), 2);
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
        while (!StringsKt.equals(this.parameterNamesAndValues[i], name, true)) {
            if (i == last) {
                return null;
            }
            i += step;
        }
        return this.parameterNamesAndValues[i + 1];
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "type"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_type")
    @NotNull
    /* renamed from: -deprecated_type, reason: not valid java name */
    public final String m4505deprecated_type() {
        return this.type;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "subtype"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_subtype")
    @NotNull
    /* renamed from: -deprecated_subtype, reason: not valid java name */
    public final String m4506deprecated_subtype() {
        return this.subtype;
    }

    @NotNull
    public String toString() {
        return this.mediaType;
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof MediaType) && Intrinsics.areEqual(((MediaType) other).mediaType, this.mediaType);
    }

    public int hashCode() {
        return this.mediaType.hashCode();
    }

    /* compiled from: MediaType.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0007¢\u0006\u0002\b\rJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0007¢\u0006\u0002\b\u000fJ\u0011\u0010\u0010\u001a\u00020\u000b*\u00020\u0007H\u0007¢\u0006\u0002\b\nJ\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u000b*\u00020\u0007H\u0007¢\u0006\u0002\b\u000eR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n��R\u0016\u0010\t\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0012"}, d2 = {"Lokhttp3/MediaType$Companion;", "", "()V", "PARAMETER", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "QUOTED", "", "TOKEN", "TYPE_SUBTYPE", BeanUtil.PREFIX_GETTER_GET, "Lokhttp3/MediaType;", "mediaType", "-deprecated_get", "parse", "-deprecated_parse", "toMediaType", "toMediaTypeOrNull", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/MediaType$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @JvmStatic
        @JvmName(name = BeanUtil.PREFIX_GETTER_GET)
        @NotNull
        public final MediaType get(@NotNull String toMediaType) {
            String strSubstring;
            Intrinsics.checkNotNullParameter(toMediaType, "$this$toMediaType");
            Matcher typeSubtype = MediaType.TYPE_SUBTYPE.matcher(toMediaType);
            if (!typeSubtype.lookingAt()) {
                throw new IllegalArgumentException(("No subtype found for: \"" + toMediaType + '\"').toString());
            }
            String strGroup = typeSubtype.group(1);
            Intrinsics.checkNotNullExpressionValue(strGroup, "typeSubtype.group(1)");
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
            if (strGroup == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String type = strGroup.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(type, "(this as java.lang.String).toLowerCase(locale)");
            String strGroup2 = typeSubtype.group(2);
            Intrinsics.checkNotNullExpressionValue(strGroup2, "typeSubtype.group(2)");
            Locale locale2 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale2, "Locale.US");
            if (strGroup2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String subtype = strGroup2.toLowerCase(locale2);
            Intrinsics.checkNotNullExpressionValue(subtype, "(this as java.lang.String).toLowerCase(locale)");
            Collection parameterNamesAndValues = (List) new ArrayList();
            Matcher parameter = MediaType.PARAMETER.matcher(toMediaType);
            int iEnd = typeSubtype.end();
            while (true) {
                int s = iEnd;
                if (s >= toMediaType.length()) {
                    Collection $this$toTypedArray$iv = parameterNamesAndValues;
                    Object[] array = $this$toTypedArray$iv.toArray(new String[0]);
                    if (array == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    return new MediaType(toMediaType, type, subtype, (String[]) array, null);
                }
                parameter.region(s, toMediaType.length());
                if (!parameter.lookingAt()) {
                    StringBuilder sbAppend = new StringBuilder().append("Parameter is not formatted correctly: \"");
                    String strSubstring2 = toMediaType.substring(s);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
                    throw new IllegalArgumentException(sbAppend.append(strSubstring2).append("\" for: \"").append(toMediaType).append('\"').toString().toString());
                }
                String name = parameter.group(1);
                if (name == null) {
                    iEnd = parameter.end();
                } else {
                    String token = parameter.group(2);
                    if (token == null) {
                        strSubstring = parameter.group(3);
                    } else if (StringsKt.startsWith$default(token, OperatorName.SHOW_TEXT_LINE, false, 2, (Object) null) && StringsKt.endsWith$default(token, OperatorName.SHOW_TEXT_LINE, false, 2, (Object) null) && token.length() > 2) {
                        strSubstring = token.substring(1, token.length() - 1);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    } else {
                        strSubstring = token;
                    }
                    String value = strSubstring;
                    parameterNamesAndValues.add(name);
                    parameterNamesAndValues.add(value);
                    iEnd = parameter.end();
                }
            }
        }

        @JvmStatic
        @JvmName(name = "parse")
        @Nullable
        public final MediaType parse(@NotNull String toMediaTypeOrNull) {
            MediaType mediaType;
            Intrinsics.checkNotNullParameter(toMediaTypeOrNull, "$this$toMediaTypeOrNull");
            try {
                mediaType = get(toMediaTypeOrNull);
            } catch (IllegalArgumentException e) {
                mediaType = null;
            }
            return mediaType;
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okhttp3.MediaType.Companion.toMediaType"}, expression = "mediaType.toMediaType()"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_get")
        @NotNull
        /* renamed from: -deprecated_get, reason: not valid java name */
        public final MediaType m4508deprecated_get(@NotNull String mediaType) {
            Intrinsics.checkNotNullParameter(mediaType, "mediaType");
            return get(mediaType);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okhttp3.MediaType.Companion.toMediaTypeOrNull"}, expression = "mediaType.toMediaTypeOrNull()"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_parse")
        @Nullable
        /* renamed from: -deprecated_parse, reason: not valid java name */
        public final MediaType m4509deprecated_parse(@NotNull String mediaType) {
            Intrinsics.checkNotNullParameter(mediaType, "mediaType");
            return parse(mediaType);
        }
    }
}
