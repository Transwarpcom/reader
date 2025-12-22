package kotlin.io;

import java.io.InputStream;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.action.PDWindowsLaunchParams;
import org.jetbrains.annotations.Nullable;

/* compiled from: Console.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��:\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010��\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\u001a\u0013\u0010��\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0087\b\u001a\u0011\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0087\b\u001a\u0011\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0005H\u0087\b\u001a\u0011\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\b\u001a\u0011\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\bH\u0087\b\u001a\u0011\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\tH\u0087\b\u001a\u0011\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\nH\u0087\b\u001a\u0011\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\fH\u0087\b\u001a\t\u0010\r\u001a\u00020\u0001H\u0087\b\u001a\u0013\u0010\r\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0005H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\bH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\nH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\fH\u0087\b\u001a\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¨\u0006\u0010"}, d2 = {PDWindowsLaunchParams.OPERATION_PRINT, "", "message", "", "", "", "", "", "", "", "", "", "", "println", "readLine", "", "kotlin-stdlib"})
@JvmName(name = "ConsoleKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/io/ConsoleKt.class */
public final class ConsoleKt {
    @InlineOnly
    private static final void print(Object message) {
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(int message) {
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(long message) {
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(byte message) {
        System.out.print(Byte.valueOf(message));
    }

    @InlineOnly
    private static final void print(short message) {
        System.out.print(Short.valueOf(message));
    }

    @InlineOnly
    private static final void print(char message) {
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(boolean message) {
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(float message) {
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(double message) {
        System.out.print(message);
    }

    @InlineOnly
    private static final void print(char[] message) {
        System.out.print(message);
    }

    @InlineOnly
    private static final void println(Object message) {
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(int message) {
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(long message) {
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(byte message) {
        System.out.println(Byte.valueOf(message));
    }

    @InlineOnly
    private static final void println(short message) {
        System.out.println(Short.valueOf(message));
    }

    @InlineOnly
    private static final void println(char message) {
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(boolean message) {
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(float message) {
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(double message) {
        System.out.println(message);
    }

    @InlineOnly
    private static final void println(char[] message) {
        System.out.println(message);
    }

    @InlineOnly
    private static final void println() {
        System.out.println();
    }

    @Nullable
    public static final String readLine() {
        LineReader lineReader = LineReader.INSTANCE;
        InputStream inputStream = System.in;
        Intrinsics.checkNotNullExpressionValue(inputStream, "System.`in`");
        Charset charsetDefaultCharset = Charset.defaultCharset();
        Intrinsics.checkNotNullExpressionValue(charsetDefaultCharset, "Charset.defaultCharset()");
        return lineReader.readLine(inputStream, charsetDefaultCharset);
    }
}
