package kotlin.text;

import io.netty.handler.codec.rtsp.RtspHeaders;
import java.io.IOException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: StringBuilderJVM.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��\\\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n��\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\r\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010��\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u001a\u001f\u0010��\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0087\b\u001a\u001d\u0010��\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u001d\u0010��\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b\u001a\u001d\u0010��\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\b\u001a\u001d\u0010��\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\bH\u0087\b\u001a\u001d\u0010��\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\tH\u0087\b\u001a\u001d\u0010��\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\nH\u0087\b\u001a%\u0010��\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u000e\u0010\u0003\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\u0087\b\u001a-\u0010\u000b\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a-\u0010\u000b\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a\u0014\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012*\u00060\u0011j\u0002`\u0012H\u0007\u001a\u001d\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012*\u00060\u0011j\u0002`\u00122\u0006\u0010\u0003\u001a\u00020\u0013H\u0087\b\u001a\u001f\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012*\u00060\u0011j\u0002`\u00122\b\u0010\u0003\u001a\u0004\u0018\u00010\u000fH\u0087\b\u001a\u0014\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0007\u001a\u001f\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0087\b\u001a\u001f\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0014H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0015H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0013H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\fH\u0087\b\u001a\u001f\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u000fH\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\bH\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\tH\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\nH\u0087\b\u001a\u001f\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0016H\u0087\b\u001a%\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u000e\u0010\u0003\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\u0087\b\u001a\u0014\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0007\u001a\u001d\u0010\u0018\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001a\u00020\bH\u0087\b\u001a%\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a5\u0010\u001b\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a5\u0010\u001b\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a!\u0010\u001c\u001a\u00020\u001d*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0013H\u0087\n\u001a-\u0010\u001e\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0016H\u0087\b\u001a7\u0010\u001f\u001a\u00020\u001d*\u00060\u0001j\u0002`\u00022\u0006\u0010 \u001a\u00020\f2\b\b\u0002\u0010!\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\bH\u0087\b¨\u0006\""}, d2 = {"appendLine", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "value", "Ljava/lang/StringBuffer;", "", "", "", "", "", "", "appendRange", "", "startIndex", "endIndex", "", "appendln", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "", "", "", "", "clear", "deleteAt", "index", "deleteRange", "insertRange", "set", "", "setRange", "toCharArray", RtspHeaders.Values.DESTINATION, "destinationOffset", "kotlin-stdlib"}, xs = "kotlin/text/StringsKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/text/StringsKt__StringBuilderJVMKt.class */
class StringsKt__StringBuilderJVMKt extends StringsKt__RegexExtensionsKt {
    @SinceKotlin(version = "1.3")
    @NotNull
    public static final StringBuilder clear(@NotNull StringBuilder clear) {
        Intrinsics.checkNotNullParameter(clear, "$this$clear");
        clear.setLength(0);
        return clear;
    }

    @InlineOnly
    private static final void set(StringBuilder set, int index, char value) {
        Intrinsics.checkNotNullParameter(set, "$this$set");
        set.setCharAt(index, value);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder setRange(StringBuilder $this$setRange, int startIndex, int endIndex, String value) {
        StringBuilder sbReplace = $this$setRange.replace(startIndex, endIndex, value);
        Intrinsics.checkNotNullExpressionValue(sbReplace, "this.replace(startIndex, endIndex, value)");
        return sbReplace;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder deleteAt(StringBuilder $this$deleteAt, int index) {
        StringBuilder sbDeleteCharAt = $this$deleteAt.deleteCharAt(index);
        Intrinsics.checkNotNullExpressionValue(sbDeleteCharAt, "this.deleteCharAt(index)");
        return sbDeleteCharAt;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder deleteRange(StringBuilder $this$deleteRange, int startIndex, int endIndex) {
        StringBuilder sbDelete = $this$deleteRange.delete(startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(sbDelete, "this.delete(startIndex, endIndex)");
        return sbDelete;
    }

    static /* synthetic */ void toCharArray$default(StringBuilder $this$toCharArray, char[] destination, int destinationOffset, int startIndex, int endIndex, int i, Object obj) {
        if ((i & 2) != 0) {
            destinationOffset = 0;
        }
        if ((i & 4) != 0) {
            startIndex = 0;
        }
        if ((i & 8) != 0) {
            endIndex = $this$toCharArray.length();
        }
        $this$toCharArray.getChars(startIndex, endIndex, destination, destinationOffset);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final void toCharArray(StringBuilder $this$toCharArray, char[] destination, int destinationOffset, int startIndex, int endIndex) {
        $this$toCharArray.getChars(startIndex, endIndex, destination, destinationOffset);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder appendRange(StringBuilder $this$appendRange, char[] value, int startIndex, int endIndex) {
        StringBuilder sbAppend = $this$appendRange.append(value, startIndex, endIndex - startIndex);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "this.append(value, start…x, endIndex - startIndex)");
        return sbAppend;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder appendRange(StringBuilder $this$appendRange, CharSequence value, int startIndex, int endIndex) {
        StringBuilder sbAppend = $this$appendRange.append(value, startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "this.append(value, startIndex, endIndex)");
        return sbAppend;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder insertRange(StringBuilder $this$insertRange, int index, char[] value, int startIndex, int endIndex) {
        StringBuilder sbInsert = $this$insertRange.insert(index, value, startIndex, endIndex - startIndex);
        Intrinsics.checkNotNullExpressionValue(sbInsert, "this.insert(index, value…x, endIndex - startIndex)");
        return sbInsert;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder insertRange(StringBuilder $this$insertRange, int index, CharSequence value, int startIndex, int endIndex) {
        StringBuilder sbInsert = $this$insertRange.insert(index, value, startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(sbInsert, "this.insert(index, value, startIndex, endIndex)");
        return sbInsert;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, StringBuffer value) {
        StringBuilder sbAppend = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        StringBuilder sbAppend2 = sbAppend.append('\n');
        Intrinsics.checkNotNullExpressionValue(sbAppend2, "append('\\n')");
        return sbAppend2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, StringBuilder value) {
        StringBuilder sbAppend = $this$appendLine.append((CharSequence) value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        StringBuilder sbAppend2 = sbAppend.append('\n');
        Intrinsics.checkNotNullExpressionValue(sbAppend2, "append('\\n')");
        return sbAppend2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, int value) {
        StringBuilder sbAppend = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        StringBuilder sbAppend2 = sbAppend.append('\n');
        Intrinsics.checkNotNullExpressionValue(sbAppend2, "append('\\n')");
        return sbAppend2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, short value) {
        StringBuilder sbAppend = $this$appendLine.append((int) value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value.toInt())");
        StringBuilder sbAppend2 = sbAppend.append('\n');
        Intrinsics.checkNotNullExpressionValue(sbAppend2, "append('\\n')");
        return sbAppend2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, byte value) {
        StringBuilder sbAppend = $this$appendLine.append((int) value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value.toInt())");
        StringBuilder sbAppend2 = sbAppend.append('\n');
        Intrinsics.checkNotNullExpressionValue(sbAppend2, "append('\\n')");
        return sbAppend2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, long value) {
        StringBuilder sbAppend = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        StringBuilder sbAppend2 = sbAppend.append('\n');
        Intrinsics.checkNotNullExpressionValue(sbAppend2, "append('\\n')");
        return sbAppend2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, float value) {
        StringBuilder sbAppend = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        StringBuilder sbAppend2 = sbAppend.append('\n');
        Intrinsics.checkNotNullExpressionValue(sbAppend2, "append('\\n')");
        return sbAppend2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, double value) {
        StringBuilder sbAppend = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        StringBuilder sbAppend2 = sbAppend.append('\n');
        Intrinsics.checkNotNullExpressionValue(sbAppend2, "append('\\n')");
        return sbAppend2;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine()"), level = DeprecationLevel.WARNING)
    @NotNull
    public static final Appendable appendln(@NotNull Appendable appendln) throws IOException {
        Intrinsics.checkNotNullParameter(appendln, "$this$appendln");
        Appendable appendableAppend = appendln.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkNotNullExpressionValue(appendableAppend, "append(SystemProperties.LINE_SEPARATOR)");
        return appendableAppend;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final Appendable appendln(Appendable $this$appendln, CharSequence value) throws IOException {
        Appendable appendableAppend = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(appendableAppend, "append(value)");
        return StringsKt.appendln(appendableAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final Appendable appendln(Appendable $this$appendln, char value) throws IOException {
        Appendable appendableAppend = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(appendableAppend, "append(value)");
        return StringsKt.appendln(appendableAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine()"), level = DeprecationLevel.WARNING)
    @NotNull
    public static final StringBuilder appendln(@NotNull StringBuilder appendln) {
        Intrinsics.checkNotNullParameter(appendln, "$this$appendln");
        StringBuilder sbAppend = appendln.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(SystemProperties.LINE_SEPARATOR)");
        return sbAppend;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, StringBuffer value) {
        StringBuilder sbAppend = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        return StringsKt.appendln(sbAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, CharSequence value) {
        StringBuilder sbAppend = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        return StringsKt.appendln(sbAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, String value) {
        StringBuilder sbAppend = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        return StringsKt.appendln(sbAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, Object value) {
        StringBuilder sbAppend = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        return StringsKt.appendln(sbAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, StringBuilder value) {
        StringBuilder sbAppend = $this$appendln.append((CharSequence) value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        return StringsKt.appendln(sbAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, char[] value) {
        StringBuilder sbAppend = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        return StringsKt.appendln(sbAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, char value) {
        StringBuilder sbAppend = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        return StringsKt.appendln(sbAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, boolean value) {
        StringBuilder sbAppend = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        return StringsKt.appendln(sbAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, int value) {
        StringBuilder sbAppend = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        return StringsKt.appendln(sbAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, short value) {
        StringBuilder sbAppend = $this$appendln.append((int) value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value.toInt())");
        return StringsKt.appendln(sbAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, byte value) {
        StringBuilder sbAppend = $this$appendln.append((int) value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value.toInt())");
        return StringsKt.appendln(sbAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, long value) {
        StringBuilder sbAppend = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        return StringsKt.appendln(sbAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, float value) {
        StringBuilder sbAppend = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        return StringsKt.appendln(sbAppend);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(imports = {}, expression = "appendLine(value)"), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, double value) {
        StringBuilder sbAppend = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        return StringsKt.appendln(sbAppend);
    }
}
