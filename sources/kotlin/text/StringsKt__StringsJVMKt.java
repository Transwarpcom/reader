package kotlin.text;

import ch.qos.logback.core.pattern.parser.Parser;
import com.jayway.jsonpath.internal.function.text.Length;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.internal.InlineOnly;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StringsJVM.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��~\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0012\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0019\n��\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\u0010��\n\u0002\b\n\n\u0002\u0010\f\n\u0002\b\u0011\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0087\b\u001a\u0019\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a)\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a\f\u0010\u0017\u001a\u00020\u0002*\u00020\u0002H\u0007\u001a\u0014\u0010\u0017\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\u0015\u0010\u001a\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0011H\u0087\b\u001a\u0015\u0010\u001c\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0011H\u0087\b\u001a\u001d\u0010\u001d\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a\u001c\u0010 \u001a\u00020\u0011*\u00020\u00022\u0006\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a\f\u0010$\u001a\u00020\u0002*\u00020\u0014H\u0007\u001a \u0010$\u001a\u00020\u0002*\u00020\u00142\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0007\u001a\u0019\u0010&\u001a\u00020#*\u0004\u0018\u00010'2\b\u0010!\u001a\u0004\u0018\u00010'H\u0087\u0004\u001a \u0010&\u001a\u00020#*\u0004\u0018\u00010'2\b\u0010!\u001a\u0004\u0018\u00010'2\u0006\u0010\"\u001a\u00020#H\u0007\u001a\u0015\u0010&\u001a\u00020#*\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0087\b\u001a\u0015\u0010&\u001a\u00020#*\u00020\u00022\u0006\u0010(\u001a\u00020'H\u0087\b\u001a\f\u0010)\u001a\u00020\u0002*\u00020\u0002H\u0007\u001a\u0014\u0010)\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\f\u0010*\u001a\u00020\u0002*\u00020\rH\u0007\u001a*\u0010*\u001a\u00020\u0002*\u00020\r2\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\b\b\u0002\u0010+\u001a\u00020#H\u0007\u001a\f\u0010,\u001a\u00020\r*\u00020\u0002H\u0007\u001a*\u0010,\u001a\u00020\r*\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\b\b\u0002\u0010+\u001a\u00020#H\u0007\u001a\u001c\u0010-\u001a\u00020#*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a \u0010/\u001a\u00020#*\u0004\u0018\u00010\u00022\b\u0010!\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a2\u00100\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0002\u00104\u001a6\u00100\u001a\u00020\u0002*\u00020\u00022\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0004\b5\u00104\u001a*\u00100\u001a\u00020\u0002*\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0002\u00106\u001a:\u00100\u001a\u00020\u0002*\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0002\u00107\u001a>\u00100\u001a\u00020\u0002*\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0004\b5\u00107\u001a2\u00100\u001a\u00020\u0002*\u00020\u00042\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0002\u00108\u001a\r\u00109\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\n\u0010:\u001a\u00020#*\u00020'\u001a\r\u0010;\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010;\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\u001a\u001d\u0010<\u001a\u00020\u0011*\u00020\u00022\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010<\u001a\u00020\u0011*\u00020\u00022\u0006\u0010@\u001a\u00020\u00022\u0006\u0010?\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010A\u001a\u00020\u0011*\u00020\u00022\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010A\u001a\u00020\u0011*\u00020\u00022\u0006\u0010@\u001a\u00020\u00022\u0006\u0010?\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010B\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010C\u001a\u00020\u0011H\u0087\b\u001a4\u0010D\u001a\u00020#*\u00020'2\u0006\u0010E\u001a\u00020\u00112\u0006\u0010!\u001a\u00020'2\u0006\u0010F\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a4\u0010D\u001a\u00020#*\u00020\u00022\u0006\u0010E\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00022\u0006\u0010F\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a\u0012\u0010G\u001a\u00020\u0002*\u00020'2\u0006\u0010H\u001a\u00020\u0011\u001a$\u0010I\u001a\u00020\u0002*\u00020\u00022\u0006\u0010J\u001a\u00020>2\u0006\u0010K\u001a\u00020>2\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010I\u001a\u00020\u0002*\u00020\u00022\u0006\u0010L\u001a\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010J\u001a\u00020>2\u0006\u0010K\u001a\u00020>2\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010L\u001a\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a\"\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00020P*\u00020'2\u0006\u0010Q\u001a\u00020R2\b\b\u0002\u0010S\u001a\u00020\u0011\u001a\u001c\u0010T\u001a\u00020#*\u00020\u00022\u0006\u0010U\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010T\u001a\u00020#*\u00020\u00022\u0006\u0010U\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a\u0015\u0010V\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0011H\u0087\b\u001a\u001d\u0010V\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a\u0017\u0010W\u001a\u00020\r*\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a\r\u0010X\u001a\u00020\u0014*\u00020\u0002H\u0087\b\u001a3\u0010X\u001a\u00020\u0014*\u00020\u00022\u0006\u0010Y\u001a\u00020\u00142\b\b\u0002\u0010Z\u001a\u00020\u00112\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a \u0010X\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0007\u001a\r\u0010[\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010[\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\u001a\u0017\u0010\\\u001a\u00020R*\u00020\u00022\b\b\u0002\u0010]\u001a\u00020\u0011H\u0087\b\u001a\r\u0010^\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010^\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\u001a\r\u0010_\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010_\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\"%\u0010��\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006`"}, d2 = {"CASE_INSENSITIVE_ORDER", "Ljava/util/Comparator;", "", "Lkotlin/Comparator;", "Lkotlin/String$Companion;", "getCASE_INSENSITIVE_ORDER", "(Lkotlin/jvm/internal/StringCompanionObject;)Ljava/util/Comparator;", "String", "stringBuffer", "Ljava/lang/StringBuffer;", "stringBuilder", "Ljava/lang/StringBuilder;", "bytes", "", "charset", "Ljava/nio/charset/Charset;", "offset", "", Length.TOKEN_NAME, "chars", "", "codePoints", "", "capitalize", "locale", "Ljava/util/Locale;", "codePointAt", "index", "codePointBefore", "codePointCount", "beginIndex", "endIndex", "compareTo", "other", "ignoreCase", "", "concatToString", "startIndex", "contentEquals", "", "charSequence", "decapitalize", "decodeToString", "throwOnInvalidSequence", "encodeToByteArray", "endsWith", "suffix", "equals", PackageDocumentBase.DCTags.format, "args", "", "", "(Ljava/lang/String;Ljava/util/Locale;[Ljava/lang/Object;)Ljava/lang/String;", "formatNullable", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "intern", "isBlank", "lowercase", "nativeIndexOf", "ch", "", "fromIndex", "str", "nativeLastIndexOf", "offsetByCodePoints", "codePointOffset", "regionMatches", "thisOffset", "otherOffset", "repeat", OperatorName.ENDPATH, Parser.REPLACE_CONVERTER_WORD, "oldChar", "newChar", "oldValue", "newValue", "replaceFirst", "split", "", "regex", "Ljava/util/regex/Pattern;", "limit", "startsWith", "prefix", "substring", "toByteArray", "toCharArray", RtspHeaders.Values.DESTINATION, "destinationOffset", "toLowerCase", "toPattern", "flags", "toUpperCase", "uppercase", "kotlin-stdlib"}, xs = "kotlin/text/StringsKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/text/StringsKt__StringsJVMKt.class */
public class StringsKt__StringsJVMKt extends StringsKt__StringNumberConversionsKt {
    @InlineOnly
    private static final int nativeIndexOf(String $this$nativeIndexOf, char ch2, int fromIndex) {
        if ($this$nativeIndexOf == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return $this$nativeIndexOf.indexOf(ch2, fromIndex);
    }

    @InlineOnly
    private static final int nativeIndexOf(String $this$nativeIndexOf, String str, int fromIndex) {
        if ($this$nativeIndexOf == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return $this$nativeIndexOf.indexOf(str, fromIndex);
    }

    @InlineOnly
    private static final int nativeLastIndexOf(String $this$nativeLastIndexOf, char ch2, int fromIndex) {
        if ($this$nativeLastIndexOf == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return $this$nativeLastIndexOf.lastIndexOf(ch2, fromIndex);
    }

    @InlineOnly
    private static final int nativeLastIndexOf(String $this$nativeLastIndexOf, String str, int fromIndex) {
        if ($this$nativeLastIndexOf == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return $this$nativeLastIndexOf.lastIndexOf(str, fromIndex);
    }

    public static /* synthetic */ boolean equals$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.equals(str, str2, z);
    }

    public static final boolean equals(@Nullable String $this$equals, @Nullable String other, boolean ignoreCase) {
        if ($this$equals == null) {
            return other == null;
        }
        if (!ignoreCase) {
            return $this$equals.equals(other);
        }
        return $this$equals.equalsIgnoreCase(other);
    }

    public static /* synthetic */ String replace$default(String str, char c, char c2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return StringsKt.replace(str, c, c2, z);
    }

    @NotNull
    public static final String replace(@NotNull String replace, char oldChar, char newChar, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(replace, "$this$replace");
        if (!ignoreCase) {
            String strReplace = replace.replace(oldChar, newChar);
            Intrinsics.checkNotNullExpressionValue(strReplace, "(this as java.lang.Strin…replace(oldChar, newChar)");
            return strReplace;
        }
        StringBuilder $this$buildString = new StringBuilder(replace.length());
        String $this$forEach$iv = replace;
        for (int i = 0; i < $this$forEach$iv.length(); i++) {
            char element$iv = $this$forEach$iv.charAt(i);
            $this$buildString.append(CharsKt.equals(element$iv, oldChar, ignoreCase) ? newChar : element$iv);
        }
        String string = $this$buildString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    public static /* synthetic */ String replace$default(String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return StringsKt.replace(str, str2, str3, z);
    }

    @NotNull
    public static final String replace(@NotNull String replace, @NotNull String oldValue, @NotNull String newValue, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(replace, "$this$replace");
        Intrinsics.checkNotNullParameter(oldValue, "oldValue");
        Intrinsics.checkNotNullParameter(newValue, "newValue");
        int occurrenceIndex = StringsKt.indexOf(replace, oldValue, 0, ignoreCase);
        if (occurrenceIndex < 0) {
            return replace;
        }
        int oldValueLength = oldValue.length();
        int searchStep = RangesKt.coerceAtLeast(oldValueLength, 1);
        int newLengthHint = (replace.length() - oldValueLength) + newValue.length();
        if (newLengthHint < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder stringBuilder = new StringBuilder(newLengthHint);
        int i = 0;
        do {
            stringBuilder.append((CharSequence) replace, i, occurrenceIndex).append(newValue);
            i = occurrenceIndex + oldValueLength;
            if (occurrenceIndex >= replace.length()) {
                break;
            }
            occurrenceIndex = StringsKt.indexOf(replace, oldValue, occurrenceIndex + searchStep, ignoreCase);
        } while (occurrenceIndex > 0);
        String string = stringBuilder.append((CharSequence) replace, i, replace.length()).toString();
        Intrinsics.checkNotNullExpressionValue(string, "stringBuilder.append(this, i, length).toString()");
        return string;
    }

    public static /* synthetic */ String replaceFirst$default(String str, char c, char c2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return StringsKt.replaceFirst(str, c, c2, z);
    }

    @NotNull
    public static final String replaceFirst(@NotNull String replaceFirst, char oldChar, char newChar, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(replaceFirst, "$this$replaceFirst");
        int index = StringsKt.indexOf$default(replaceFirst, oldChar, 0, ignoreCase, 2, (Object) null);
        if (index < 0) {
            return replaceFirst;
        }
        return StringsKt.replaceRange((CharSequence) replaceFirst, index, index + 1, (CharSequence) String.valueOf(newChar)).toString();
    }

    public static /* synthetic */ String replaceFirst$default(String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return StringsKt.replaceFirst(str, str2, str3, z);
    }

    @NotNull
    public static final String replaceFirst(@NotNull String replaceFirst, @NotNull String oldValue, @NotNull String newValue, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(replaceFirst, "$this$replaceFirst");
        Intrinsics.checkNotNullParameter(oldValue, "oldValue");
        Intrinsics.checkNotNullParameter(newValue, "newValue");
        int index = StringsKt.indexOf$default(replaceFirst, oldValue, 0, ignoreCase, 2, (Object) null);
        if (index < 0) {
            return replaceFirst;
        }
        return StringsKt.replaceRange((CharSequence) replaceFirst, index, index + oldValue.length(), (CharSequence) newValue).toString();
    }

    @Deprecated(message = "Use uppercase() instead.", replaceWith = @ReplaceWith(imports = {"java.util.Locale"}, expression = "uppercase(Locale.getDefault())"))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @InlineOnly
    private static final String toUpperCase(String $this$toUpperCase) {
        if ($this$toUpperCase == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = $this$toUpperCase.toUpperCase();
        Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase()");
        return upperCase;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final String uppercase(String $this$uppercase) {
        if ($this$uppercase == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = $this$uppercase.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.Strin….toUpperCase(Locale.ROOT)");
        return upperCase;
    }

    @Deprecated(message = "Use lowercase() instead.", replaceWith = @ReplaceWith(imports = {"java.util.Locale"}, expression = "lowercase(Locale.getDefault())"))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @InlineOnly
    private static final String toLowerCase(String $this$toLowerCase) {
        if ($this$toLowerCase == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = $this$toLowerCase.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
        return lowerCase;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final String lowercase(String $this$lowercase) {
        if ($this$lowercase == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = $this$lowercase.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.Strin….toLowerCase(Locale.ROOT)");
        return lowerCase;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final String concatToString(@NotNull char[] concatToString) {
        Intrinsics.checkNotNullParameter(concatToString, "$this$concatToString");
        return new String(concatToString);
    }

    public static /* synthetic */ String concatToString$default(char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = cArr.length;
        }
        return StringsKt.concatToString(cArr, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final String concatToString(@NotNull char[] concatToString, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(concatToString, "$this$concatToString");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, concatToString.length);
        return new String(concatToString, startIndex, endIndex - startIndex);
    }

    public static /* synthetic */ char[] toCharArray$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return StringsKt.toCharArray(str, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final char[] toCharArray(@NotNull String toCharArray, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(toCharArray, "$this$toCharArray");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, toCharArray.length());
        char[] cArr = new char[endIndex - startIndex];
        toCharArray.getChars(startIndex, endIndex, cArr, 0);
        return cArr;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final String decodeToString(@NotNull byte[] decodeToString) {
        Intrinsics.checkNotNullParameter(decodeToString, "$this$decodeToString");
        return new String(decodeToString, Charsets.UTF_8);
    }

    public static /* synthetic */ String decodeToString$default(byte[] bArr, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return StringsKt.decodeToString(bArr, i, i2, z);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final String decodeToString(@NotNull byte[] decodeToString, int startIndex, int endIndex, boolean throwOnInvalidSequence) {
        Intrinsics.checkNotNullParameter(decodeToString, "$this$decodeToString");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, decodeToString.length);
        if (!throwOnInvalidSequence) {
            return new String(decodeToString, startIndex, endIndex - startIndex, Charsets.UTF_8);
        }
        CharsetDecoder decoder = Charsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
        String string = decoder.decode(ByteBuffer.wrap(decodeToString, startIndex, endIndex - startIndex)).toString();
        Intrinsics.checkNotNullExpressionValue(string, "decoder.decode(ByteBuffe…- startIndex)).toString()");
        return string;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final byte[] encodeToByteArray(@NotNull String encodeToByteArray) {
        Intrinsics.checkNotNullParameter(encodeToByteArray, "$this$encodeToByteArray");
        byte[] bytes = encodeToByteArray.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    public static /* synthetic */ byte[] encodeToByteArray$default(String str, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return StringsKt.encodeToByteArray(str, i, i2, z);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final byte[] encodeToByteArray(@NotNull String encodeToByteArray, int startIndex, int endIndex, boolean throwOnInvalidSequence) throws CharacterCodingException {
        Intrinsics.checkNotNullParameter(encodeToByteArray, "$this$encodeToByteArray");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, encodeToByteArray.length());
        if (!throwOnInvalidSequence) {
            String strSubstring = encodeToByteArray.substring(startIndex, endIndex);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            Charset charset = Charsets.UTF_8;
            if (strSubstring == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            byte[] bytes = strSubstring.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            return bytes;
        }
        CharsetEncoder encoder = Charsets.UTF_8.newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
        ByteBuffer byteBuffer = encoder.encode(CharBuffer.wrap(encodeToByteArray, startIndex, endIndex));
        if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            int iRemaining = byteBuffer.remaining();
            byte[] bArrArray = byteBuffer.array();
            Intrinsics.checkNotNull(bArrArray);
            if (iRemaining == bArrArray.length) {
                byte[] bArrArray2 = byteBuffer.array();
                Intrinsics.checkNotNullExpressionValue(bArrArray2, "byteBuffer.array()");
                return bArrArray2;
            }
        }
        byte[] it = new byte[byteBuffer.remaining()];
        byteBuffer.get(it);
        return it;
    }

    @InlineOnly
    private static final char[] toCharArray(String $this$toCharArray) {
        if ($this$toCharArray == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        char[] charArray = $this$toCharArray.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "(this as java.lang.String).toCharArray()");
        return charArray;
    }

    static /* synthetic */ char[] toCharArray$default(String $this$toCharArray, char[] destination, int destinationOffset, int startIndex, int endIndex, int i, Object obj) {
        if ((i & 2) != 0) {
            destinationOffset = 0;
        }
        if ((i & 4) != 0) {
            startIndex = 0;
        }
        if ((i & 8) != 0) {
            endIndex = $this$toCharArray.length();
        }
        if ($this$toCharArray == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        $this$toCharArray.getChars(startIndex, endIndex, destination, destinationOffset);
        return destination;
    }

    @InlineOnly
    private static final char[] toCharArray(String $this$toCharArray, char[] destination, int destinationOffset, int startIndex, int endIndex) {
        if ($this$toCharArray == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        $this$toCharArray.getChars(startIndex, endIndex, destination, destinationOffset);
        return destination;
    }

    @InlineOnly
    private static final String format(String $this$format, Object... args) {
        String str = String.format($this$format, Arrays.copyOf(args, args.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        return str;
    }

    @InlineOnly
    private static final String format(StringCompanionObject $this$format, String format, Object... args) {
        String str = String.format(format, Arrays.copyOf(args, args.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        return str;
    }

    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    @InlineOnly
    private static final /* synthetic */ String format(String $this$format, Locale locale, Object... args) {
        String str = String.format(locale, $this$format, Arrays.copyOf(args, args.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(locale, this, *args)");
        return str;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "formatNullable")
    @InlineOnly
    private static final String formatNullable(String $this$format, Locale locale, Object... args) {
        String str = String.format(locale, $this$format, Arrays.copyOf(args, args.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(locale, this, *args)");
        return str;
    }

    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    @InlineOnly
    private static final /* synthetic */ String format(StringCompanionObject $this$format, Locale locale, String format, Object... args) {
        String str = String.format(locale, format, Arrays.copyOf(args, args.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(locale, format, *args)");
        return str;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "formatNullable")
    @InlineOnly
    private static final String formatNullable(StringCompanionObject $this$format, Locale locale, String format, Object... args) {
        String str = String.format(locale, format, Arrays.copyOf(args, args.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(locale, format, *args)");
        return str;
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, Pattern pattern, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return StringsKt.split(charSequence, pattern, i);
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence split, @NotNull Pattern regex, int limit) {
        Intrinsics.checkNotNullParameter(split, "$this$split");
        Intrinsics.checkNotNullParameter(regex, "regex");
        if (!(limit >= 0)) {
            throw new IllegalArgumentException(("Limit must be non-negative, but was " + limit + '.').toString());
        }
        String[] strArrSplit = regex.split(split, limit == 0 ? -1 : limit);
        Intrinsics.checkNotNullExpressionValue(strArrSplit, "regex.split(this, if (limit == 0) -1 else limit)");
        return ArraysKt.asList(strArrSplit);
    }

    @InlineOnly
    private static final String substring(String $this$substring, int startIndex) {
        if ($this$substring == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = $this$substring.substring(startIndex);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }

    @InlineOnly
    private static final String substring(String $this$substring, int startIndex, int endIndex) {
        if ($this$substring == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = $this$substring.substring(startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ boolean startsWith$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.startsWith(str, str2, z);
    }

    public static final boolean startsWith(@NotNull String startsWith, @NotNull String prefix, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(startsWith, "$this$startsWith");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!ignoreCase) {
            return startsWith.startsWith(prefix);
        }
        return StringsKt.regionMatches(startsWith, 0, prefix, 0, prefix.length(), ignoreCase);
    }

    public static /* synthetic */ boolean startsWith$default(String str, String str2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.startsWith(str, str2, i, z);
    }

    public static final boolean startsWith(@NotNull String startsWith, @NotNull String prefix, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(startsWith, "$this$startsWith");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!ignoreCase) {
            return startsWith.startsWith(prefix, startIndex);
        }
        return StringsKt.regionMatches(startsWith, startIndex, prefix, 0, prefix.length(), ignoreCase);
    }

    public static /* synthetic */ boolean endsWith$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.endsWith(str, str2, z);
    }

    public static final boolean endsWith(@NotNull String endsWith, @NotNull String suffix, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(endsWith, "$this$endsWith");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if (!ignoreCase) {
            return endsWith.endsWith(suffix);
        }
        return StringsKt.regionMatches(endsWith, endsWith.length() - suffix.length(), suffix, 0, suffix.length(), true);
    }

    @InlineOnly
    private static final String String(byte[] bytes, int offset, int length, Charset charset) {
        return new String(bytes, offset, length, charset);
    }

    @InlineOnly
    private static final String String(byte[] bytes, Charset charset) {
        return new String(bytes, charset);
    }

    @InlineOnly
    private static final String String(byte[] bytes, int offset, int length) {
        return new String(bytes, offset, length, Charsets.UTF_8);
    }

    @InlineOnly
    private static final String String(byte[] bytes) {
        return new String(bytes, Charsets.UTF_8);
    }

    @InlineOnly
    private static final String String(char[] chars) {
        return new String(chars);
    }

    @InlineOnly
    private static final String String(char[] chars, int offset, int length) {
        return new String(chars, offset, length);
    }

    @InlineOnly
    private static final String String(int[] codePoints, int offset, int length) {
        return new String(codePoints, offset, length);
    }

    @InlineOnly
    private static final String String(StringBuffer stringBuffer) {
        return new String(stringBuffer);
    }

    @InlineOnly
    private static final String String(StringBuilder stringBuilder) {
        return new String(stringBuilder);
    }

    @InlineOnly
    private static final int codePointAt(String $this$codePointAt, int index) {
        if ($this$codePointAt == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return $this$codePointAt.codePointAt(index);
    }

    @InlineOnly
    private static final int codePointBefore(String $this$codePointBefore, int index) {
        if ($this$codePointBefore == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return $this$codePointBefore.codePointBefore(index);
    }

    @InlineOnly
    private static final int codePointCount(String $this$codePointCount, int beginIndex, int endIndex) {
        if ($this$codePointCount == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return $this$codePointCount.codePointCount(beginIndex, endIndex);
    }

    public static /* synthetic */ int compareTo$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.compareTo(str, str2, z);
    }

    public static final int compareTo(@NotNull String compareTo, @NotNull String other, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(compareTo, "$this$compareTo");
        Intrinsics.checkNotNullParameter(other, "other");
        if (ignoreCase) {
            return compareTo.compareToIgnoreCase(other);
        }
        return compareTo.compareTo(other);
    }

    @InlineOnly
    private static final boolean contentEquals(String $this$contentEquals, CharSequence charSequence) {
        if ($this$contentEquals == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return $this$contentEquals.contentEquals(charSequence);
    }

    @InlineOnly
    private static final boolean contentEquals(String $this$contentEquals, StringBuffer stringBuilder) {
        if ($this$contentEquals == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return $this$contentEquals.contentEquals(stringBuilder);
    }

    @SinceKotlin(version = "1.5")
    public static final boolean contentEquals(@Nullable CharSequence $this$contentEquals, @Nullable CharSequence other) {
        if (($this$contentEquals instanceof String) && other != null) {
            return ((String) $this$contentEquals).contentEquals(other);
        }
        return StringsKt.contentEqualsImpl($this$contentEquals, other);
    }

    @SinceKotlin(version = "1.5")
    public static final boolean contentEquals(@Nullable CharSequence $this$contentEquals, @Nullable CharSequence other, boolean ignoreCase) {
        if (ignoreCase) {
            return StringsKt.contentEqualsIgnoreCaseImpl($this$contentEquals, other);
        }
        return StringsKt.contentEquals($this$contentEquals, other);
    }

    @InlineOnly
    private static final String intern(String $this$intern) {
        if ($this$intern == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strIntern = $this$intern.intern();
        Intrinsics.checkNotNullExpressionValue(strIntern, "(this as java.lang.String).intern()");
        return strIntern;
    }

    public static final boolean isBlank(@NotNull CharSequence isBlank) {
        boolean z;
        Intrinsics.checkNotNullParameter(isBlank, "$this$isBlank");
        if (isBlank.length() != 0) {
            Iterable $this$all$iv = StringsKt.getIndices(isBlank);
            if (!($this$all$iv instanceof Collection) || !((Collection) $this$all$iv).isEmpty()) {
                Iterator it = $this$all$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        int element$iv = ((IntIterator) it).nextInt();
                        if (!CharsKt.isWhitespace(isBlank.charAt(element$iv))) {
                            z = false;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    @InlineOnly
    private static final int offsetByCodePoints(String $this$offsetByCodePoints, int index, int codePointOffset) {
        if ($this$offsetByCodePoints == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return $this$offsetByCodePoints.offsetByCodePoints(index, codePointOffset);
    }

    public static /* synthetic */ boolean regionMatches$default(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 16) != 0) {
            z = false;
        }
        return StringsKt.regionMatches(charSequence, i, charSequence2, i2, i3, z);
    }

    public static final boolean regionMatches(@NotNull CharSequence regionMatches, int thisOffset, @NotNull CharSequence other, int otherOffset, int length, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(regionMatches, "$this$regionMatches");
        Intrinsics.checkNotNullParameter(other, "other");
        if ((regionMatches instanceof String) && (other instanceof String)) {
            return StringsKt.regionMatches((String) regionMatches, thisOffset, (String) other, otherOffset, length, ignoreCase);
        }
        return StringsKt.regionMatchesImpl(regionMatches, thisOffset, other, otherOffset, length, ignoreCase);
    }

    public static /* synthetic */ boolean regionMatches$default(String str, int i, String str2, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 16) != 0) {
            z = false;
        }
        return StringsKt.regionMatches(str, i, str2, i2, i3, z);
    }

    public static final boolean regionMatches(@NotNull String regionMatches, int thisOffset, @NotNull String other, int otherOffset, int length, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(regionMatches, "$this$regionMatches");
        Intrinsics.checkNotNullParameter(other, "other");
        if (!ignoreCase) {
            return regionMatches.regionMatches(thisOffset, other, otherOffset, length);
        }
        return regionMatches.regionMatches(ignoreCase, thisOffset, other, otherOffset, length);
    }

    @Deprecated(message = "Use lowercase() instead.", replaceWith = @ReplaceWith(imports = {}, expression = "lowercase(locale)"))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @InlineOnly
    private static final String toLowerCase(String $this$toLowerCase, Locale locale) {
        if ($this$toLowerCase == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = $this$toLowerCase.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        return lowerCase;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final String lowercase(String $this$lowercase, Locale locale) {
        if ($this$lowercase == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = $this$lowercase.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        return lowerCase;
    }

    @Deprecated(message = "Use uppercase() instead.", replaceWith = @ReplaceWith(imports = {}, expression = "uppercase(locale)"))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @InlineOnly
    private static final String toUpperCase(String $this$toUpperCase, Locale locale) {
        if ($this$toUpperCase == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = $this$toUpperCase.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase(locale)");
        return upperCase;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final String uppercase(String $this$uppercase, Locale locale) {
        if ($this$uppercase == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = $this$uppercase.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase(locale)");
        return upperCase;
    }

    @InlineOnly
    private static final byte[] toByteArray(String $this$toByteArray, Charset charset) {
        if ($this$toByteArray == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = $this$toByteArray.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    static /* synthetic */ byte[] toByteArray$default(String $this$toByteArray, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ($this$toByteArray == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = $this$toByteArray.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    static /* synthetic */ Pattern toPattern$default(String $this$toPattern, int flags, int i, Object obj) {
        if ((i & 1) != 0) {
            flags = 0;
        }
        Pattern patternCompile = Pattern.compile($this$toPattern, flags);
        Intrinsics.checkNotNullExpressionValue(patternCompile, "java.util.regex.Pattern.compile(this, flags)");
        return patternCompile;
    }

    @InlineOnly
    private static final Pattern toPattern(String $this$toPattern, int flags) {
        Pattern patternCompile = Pattern.compile($this$toPattern, flags);
        Intrinsics.checkNotNullExpressionValue(patternCompile, "java.util.regex.Pattern.compile(this, flags)");
        return patternCompile;
    }

    @Deprecated(message = "Use replaceFirstChar instead.", replaceWith = @ReplaceWith(imports = {"java.util.Locale"}, expression = "replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }"))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @NotNull
    public static final String capitalize(@NotNull String capitalize) {
        Intrinsics.checkNotNullParameter(capitalize, "$this$capitalize");
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "Locale.getDefault()");
        return StringsKt.capitalize(capitalize, locale);
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    @Deprecated(message = "Use replaceFirstChar instead.", replaceWith = @ReplaceWith(imports = {}, expression = "replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }"))
    @LowPriorityInOverloadResolution
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final String capitalize(@NotNull String capitalize, @NotNull Locale locale) {
        Intrinsics.checkNotNullParameter(capitalize, "$this$capitalize");
        Intrinsics.checkNotNullParameter(locale, "locale");
        if (capitalize.length() > 0) {
            char firstChar = capitalize.charAt(0);
            if (Character.isLowerCase(firstChar)) {
                StringBuilder $this$buildString = new StringBuilder();
                char titleChar = Character.toTitleCase(firstChar);
                if (titleChar != Character.toUpperCase(firstChar)) {
                    $this$buildString.append(titleChar);
                } else {
                    String strSubstring = capitalize.substring(0, 1);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    if (strSubstring == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String upperCase = strSubstring.toUpperCase(locale);
                    Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase(locale)");
                    $this$buildString.append(upperCase);
                }
                String strSubstring2 = capitalize.substring(1);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
                $this$buildString.append(strSubstring2);
                String string = $this$buildString.toString();
                Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
                return string;
            }
        }
        return capitalize;
    }

    @Deprecated(message = "Use replaceFirstChar instead.", replaceWith = @ReplaceWith(imports = {"java.util.Locale"}, expression = "replaceFirstChar { it.lowercase(Locale.getDefault()) }"))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @NotNull
    public static final String decapitalize(@NotNull String decapitalize) {
        Intrinsics.checkNotNullParameter(decapitalize, "$this$decapitalize");
        if (!(decapitalize.length() > 0) || Character.isLowerCase(decapitalize.charAt(0))) {
            return decapitalize;
        }
        StringBuilder sb = new StringBuilder();
        String strSubstring = decapitalize.substring(0, 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (strSubstring == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = strSubstring.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
        StringBuilder sbAppend = sb.append(lowerCase);
        String strSubstring2 = decapitalize.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
        return sbAppend.append(strSubstring2).toString();
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    @Deprecated(message = "Use replaceFirstChar instead.", replaceWith = @ReplaceWith(imports = {}, expression = "replaceFirstChar { it.lowercase(locale) }"))
    @LowPriorityInOverloadResolution
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final String decapitalize(@NotNull String decapitalize, @NotNull Locale locale) {
        Intrinsics.checkNotNullParameter(decapitalize, "$this$decapitalize");
        Intrinsics.checkNotNullParameter(locale, "locale");
        if (!(decapitalize.length() > 0) || Character.isLowerCase(decapitalize.charAt(0))) {
            return decapitalize;
        }
        StringBuilder sb = new StringBuilder();
        String strSubstring = decapitalize.substring(0, 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (strSubstring == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = strSubstring.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        StringBuilder sbAppend = sb.append(lowerCase);
        String strSubstring2 = decapitalize.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
        return sbAppend.append(strSubstring2).toString();
    }

    @NotNull
    public static final String repeat(@NotNull CharSequence repeat, int n) {
        Intrinsics.checkNotNullParameter(repeat, "$this$repeat");
        if (!(n >= 0)) {
            throw new IllegalArgumentException(("Count 'n' must be non-negative, but was " + n + '.').toString());
        }
        switch (n) {
            case 0:
                return "";
            case 1:
                return repeat.toString();
            default:
                switch (repeat.length()) {
                    case 0:
                        return "";
                    case 1:
                        char cCharAt = repeat.charAt(0);
                        char[] cArr = new char[n];
                        for (int i = 0; i < n; i++) {
                            cArr[i] = cCharAt;
                        }
                        return new String(cArr);
                    default:
                        StringBuilder sb = new StringBuilder(n * repeat.length());
                        int i2 = 1;
                        if (1 <= n) {
                            while (true) {
                                sb.append(repeat);
                                if (i2 != n) {
                                    i2++;
                                }
                            }
                        }
                        String string = sb.toString();
                        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
                        return string;
                }
        }
    }

    @NotNull
    public static final Comparator<String> getCASE_INSENSITIVE_ORDER(@NotNull StringCompanionObject CASE_INSENSITIVE_ORDER) {
        Intrinsics.checkNotNullParameter(CASE_INSENSITIVE_ORDER, "$this$CASE_INSENSITIVE_ORDER");
        Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
        Intrinsics.checkNotNullExpressionValue(comparator, "java.lang.String.CASE_INSENSITIVE_ORDER");
        return comparator;
    }
}
