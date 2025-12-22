package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import me.ag2s.epublib.epub.NCXDocumentV2;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FileReadWrite.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��z\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\u001a\u0012\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a!\u0010\n\u001a\u00020\u000b*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\b\u001a!\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\b\u001aB\u0010\u0010\u001a\u00020\u0001*\u00020\u000226\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001aJ\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\r26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001a7\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00010\u0019\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0002H\u0087\b\u001a\r\u0010\u001d\u001a\u00020\u001e*\u00020\u0002H\u0087\b\u001a\u0017\u0010\u001f\u001a\u00020 *\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u001a\n\u0010!\u001a\u00020\u0004*\u00020\u0002\u001a\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070#*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0014\u0010$\u001a\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010%\u001a\u00020&*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u001aB\u0010'\u001a\u0002H(\"\u0004\b��\u0010(*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\u0018\u0010)\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070*\u0012\u0004\u0012\u0002H(0\u0019H\u0086\bø\u0001��ø\u0001\u0001¢\u0006\u0002\u0010,\u001a\u0012\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010.\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010/\u001a\u000200*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u0082\u0002\u000f\n\u0006\b\u0011(+0\u0001\n\u0005\b\u009920\u0001¨\u00061"}, d2 = {"appendBytes", "", "Ljava/io/File;", BeanDefinitionParserDelegate.ARRAY_ELEMENT, "", "appendText", NCXDocumentV2.NCXTags.text, "", "charset", "Ljava/nio/charset/Charset;", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "bufferedWriter", "Ljava/io/BufferedWriter;", "forEachBlock", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "blockSize", "forEachLine", "Lkotlin/Function1;", "line", "inputStream", "Ljava/io/FileInputStream;", "outputStream", "Ljava/io/FileOutputStream;", "printWriter", "Ljava/io/PrintWriter;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "useLines", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "block", "Lkotlin/sequences/Sequence;", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "writeText", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, xs = "kotlin/io/FilesKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/io/FilesKt__FileReadWriteKt.class */
public class FilesKt__FileReadWriteKt extends FilesKt__FilePathComponentsKt {
    static /* synthetic */ InputStreamReader reader$default(File $this$reader, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new InputStreamReader(new FileInputStream($this$reader), charset);
    }

    @InlineOnly
    private static final InputStreamReader reader(File $this$reader, Charset charset) {
        return new InputStreamReader(new FileInputStream($this$reader), charset);
    }

    static /* synthetic */ BufferedReader bufferedReader$default(File $this$bufferedReader, Charset charset, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        Reader inputStreamReader = new InputStreamReader(new FileInputStream($this$bufferedReader), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, bufferSize);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(File $this$bufferedReader, Charset charset, int bufferSize) {
        Reader inputStreamReader = new InputStreamReader(new FileInputStream($this$bufferedReader), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, bufferSize);
    }

    static /* synthetic */ OutputStreamWriter writer$default(File $this$writer, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new OutputStreamWriter(new FileOutputStream($this$writer), charset);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(File $this$writer, Charset charset) {
        return new OutputStreamWriter(new FileOutputStream($this$writer), charset);
    }

    static /* synthetic */ BufferedWriter bufferedWriter$default(File $this$bufferedWriter, Charset charset, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($this$bufferedWriter), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, bufferSize);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(File $this$bufferedWriter, Charset charset, int bufferSize) {
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($this$bufferedWriter), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, bufferSize);
    }

    static /* synthetic */ PrintWriter printWriter$default(File $this$printWriter, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($this$printWriter), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    @InlineOnly
    private static final PrintWriter printWriter(File $this$printWriter, Charset charset) {
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($this$printWriter), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    @NotNull
    public static final byte[] readBytes(@NotNull File readBytes) {
        byte[] bArrCopyInto;
        int read;
        Intrinsics.checkNotNullParameter(readBytes, "$this$readBytes");
        FileInputStream fileInputStream = new FileInputStream(readBytes);
        Throwable th = (Throwable) null;
        try {
            FileInputStream input = fileInputStream;
            int offset = 0;
            long length = readBytes.length();
            if (length > Integer.MAX_VALUE) {
                throw new OutOfMemoryError("File " + readBytes + " is too big (" + length + " bytes) to fit in memory.");
            }
            int remaining = (int) length;
            byte[] result = new byte[remaining];
            while (remaining > 0 && (read = input.read(result, offset, remaining)) >= 0) {
                remaining -= read;
                offset += read;
            }
            if (remaining > 0) {
                bArrCopyInto = Arrays.copyOf(result, offset);
                Intrinsics.checkNotNullExpressionValue(bArrCopyInto, "java.util.Arrays.copyOf(this, newSize)");
            } else {
                int extraByte = input.read();
                if (extraByte == -1) {
                    bArrCopyInto = result;
                } else {
                    ExposingBufferByteArrayOutputStream extra = new ExposingBufferByteArrayOutputStream(8193);
                    extra.write(extraByte);
                    ByteStreamsKt.copyTo$default(input, extra, 0, 2, null);
                    int resultingSize = result.length + extra.size();
                    if (resultingSize < 0) {
                        throw new OutOfMemoryError("File " + readBytes + " is too big to fit in memory.");
                    }
                    byte[] buffer = extra.getBuffer();
                    byte[] bArrCopyOf = Arrays.copyOf(result, resultingSize);
                    Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
                    bArrCopyInto = ArraysKt.copyInto(buffer, bArrCopyOf, result.length, 0, extra.size());
                }
            }
            return bArrCopyInto;
        } finally {
            CloseableKt.closeFinally(fileInputStream, th);
        }
    }

    public static final void writeBytes(@NotNull File writeBytes, @NotNull byte[] array) {
        Intrinsics.checkNotNullParameter(writeBytes, "$this$writeBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        FileOutputStream fileOutputStream = new FileOutputStream(writeBytes);
        Throwable th = (Throwable) null;
        try {
            try {
                FileOutputStream it = fileOutputStream;
                it.write(array);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, th);
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(fileOutputStream, th);
            throw th2;
        }
    }

    public static final void appendBytes(@NotNull File appendBytes, @NotNull byte[] array) {
        Intrinsics.checkNotNullParameter(appendBytes, "$this$appendBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        FileOutputStream fileOutputStream = new FileOutputStream(appendBytes, true);
        Throwable th = (Throwable) null;
        try {
            try {
                FileOutputStream it = fileOutputStream;
                it.write(array);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, th);
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(fileOutputStream, th);
            throw th2;
        }
    }

    @NotNull
    public static final String readText(@NotNull File readText, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(readText, "$this$readText");
        Intrinsics.checkNotNullParameter(charset, "charset");
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(readText), charset);
        Throwable th = (Throwable) null;
        try {
            try {
                InputStreamReader it = inputStreamReader;
                String text = TextStreamsKt.readText(it);
                CloseableKt.closeFinally(inputStreamReader, th);
                return text;
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(inputStreamReader, th);
            throw th2;
        }
    }

    public static /* synthetic */ String readText$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return FilesKt.readText(file, charset);
    }

    public static final void writeText(@NotNull File writeText, @NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(writeText, "$this$writeText");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        byte[] bytes = text.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        FilesKt.writeBytes(writeText, bytes);
    }

    public static /* synthetic */ void writeText$default(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.writeText(file, str, charset);
    }

    public static final void appendText(@NotNull File appendText, @NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(appendText, "$this$appendText");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        byte[] bytes = text.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        FilesKt.appendBytes(appendText, bytes);
    }

    public static /* synthetic */ void appendText$default(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.appendText(file, str, charset);
    }

    public static final void forEachBlock(@NotNull File forEachBlock, @NotNull Function2<? super byte[], ? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachBlock, "$this$forEachBlock");
        Intrinsics.checkNotNullParameter(action, "action");
        FilesKt.forEachBlock(forEachBlock, 4096, action);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [byte[], java.lang.Object] */
    public static final void forEachBlock(@NotNull File forEachBlock, int blockSize, @NotNull Function2<? super byte[], ? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachBlock, "$this$forEachBlock");
        Intrinsics.checkNotNullParameter(action, "action");
        ?? r0 = new byte[RangesKt.coerceAtLeast(blockSize, 512)];
        FileInputStream fileInputStream = new FileInputStream(forEachBlock);
        Throwable th = (Throwable) null;
        try {
            try {
                FileInputStream fileInputStream2 = fileInputStream;
                while (true) {
                    int size = fileInputStream2.read(r0);
                    if (size > 0) {
                        action.invoke(r0, Integer.valueOf(size));
                    } else {
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileInputStream, th);
                        return;
                    }
                }
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(fileInputStream, th);
            throw th2;
        }
    }

    public static /* synthetic */ void forEachLine$default(File file, Charset charset, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.forEachLine(file, charset, function1);
    }

    public static final void forEachLine(@NotNull File forEachLine, @NotNull Charset charset, @NotNull Function1<? super String, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachLine, "$this$forEachLine");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(action, "action");
        TextStreamsKt.forEachLine(new BufferedReader(new InputStreamReader(new FileInputStream(forEachLine), charset)), action);
    }

    @InlineOnly
    private static final FileInputStream inputStream(File $this$inputStream) {
        return new FileInputStream($this$inputStream);
    }

    @InlineOnly
    private static final FileOutputStream outputStream(File $this$outputStream) {
        return new FileOutputStream($this$outputStream);
    }

    public static /* synthetic */ List readLines$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return FilesKt.readLines(file, charset);
    }

    @NotNull
    public static final List<String> readLines(@NotNull File readLines, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(readLines, "$this$readLines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        final ArrayList result = new ArrayList();
        FilesKt.forEachLine(readLines, charset, new Function1<String, Unit>() { // from class: kotlin.io.FilesKt__FileReadWriteKt.readLines.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                result.add(it);
            }
        });
        return result;
    }

    public static /* synthetic */ Object useLines$default(File useLines, Charset charset, Function1 block, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(useLines, "$this$useLines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(block, "block");
        Reader inputStreamReader = new InputStreamReader(new FileInputStream(useLines), charset);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        Throwable th = (Throwable) null;
        try {
            BufferedReader it = bufferedReader;
            Object objInvoke = block.invoke(TextStreamsKt.lineSequence(it));
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                CloseableKt.closeFinally(bufferedReader, th);
            } else {
                bufferedReader.close();
            }
            InlineMarker.finallyEnd(1);
            return objInvoke;
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                CloseableKt.closeFinally(bufferedReader, th);
            } else if (th == null) {
                bufferedReader.close();
            } else {
                try {
                    bufferedReader.close();
                } catch (Throwable th3) {
                }
            }
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    public static final <T> T useLines(@NotNull File useLines, @NotNull Charset charset, @NotNull Function1<? super Sequence<String>, ? extends T> block) throws IOException {
        Intrinsics.checkNotNullParameter(useLines, "$this$useLines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(block, "block");
        Reader inputStreamReader = new InputStreamReader(new FileInputStream(useLines), charset);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        Throwable th = (Throwable) null;
        try {
            try {
                BufferedReader it = bufferedReader;
                T tInvoke = block.invoke(TextStreamsKt.lineSequence(it));
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    CloseableKt.closeFinally(bufferedReader, th);
                } else {
                    bufferedReader.close();
                }
                InlineMarker.finallyEnd(1);
                return tInvoke;
            } finally {
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                CloseableKt.closeFinally(bufferedReader, th);
            } else if (th == null) {
                bufferedReader.close();
            } else {
                try {
                    bufferedReader.close();
                } catch (Throwable th3) {
                }
            }
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }
}
