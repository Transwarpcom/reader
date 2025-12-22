package kotlin.io.path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import me.ag2s.epublib.epub.NCXDocumentV2;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: PathReadWrite.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��\u0082\u0001\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0010\r\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a%\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a%\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u001e\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a:\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u0010\u0015\u001a:\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u0010\u0018\u001a=\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2!\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00010\u001bH\u0087\bø\u0001��\u001a&\u0010 \u001a\u00020!*\u00020\u00022\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u0010\"\u001a&\u0010#\u001a\u00020$*\u00020\u00022\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u0010%\u001a\r\u0010&\u001a\u00020\u0004*\u00020\u0002H\u0087\b\u001a\u001d\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0(*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0016\u0010)\u001a\u00020\u001c*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a0\u0010*\u001a\u00020+*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u0010,\u001a?\u0010-\u001a\u0002H.\"\u0004\b��\u0010.*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0018\u0010/\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u000b\u0012\u0004\u0012\u0002H.0\u001bH\u0087\bø\u0001��¢\u0006\u0002\u00100\u001a.\u00101\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u00102\u001a>\u00103\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u00104\u001a>\u00103\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u00105\u001a7\u00106\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0007¢\u0006\u0002\u00107\u001a0\u00108\u001a\u000209*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u0010:\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006;"}, d2 = {"appendBytes", "", "Ljava/nio/file/Path;", BeanDefinitionParserDelegate.ARRAY_ELEMENT, "", "appendLines", "lines", "", "", "charset", "Ljava/nio/charset/Charset;", "Lkotlin/sequences/Sequence;", "appendText", NCXDocumentV2.NCXTags.text, "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "options", "", "Ljava/nio/file/OpenOption;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedReader;", "bufferedWriter", "Ljava/io/BufferedWriter;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedWriter;", "forEachLine", "action", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "line", "inputStream", "Ljava/io/InputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/InputStream;", "outputStream", "Ljava/io/OutputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStream;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/InputStreamReader;", "useLines", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "block", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "(Ljava/nio/file/Path;[B[Ljava/nio/file/OpenOption;)V", "writeLines", "(Ljava/nio/file/Path;Ljava/lang/Iterable;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "(Ljava/nio/file/Path;Lkotlin/sequences/Sequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "writeText", "(Ljava/nio/file/Path;Ljava/lang/CharSequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)V", "writer", "Ljava/io/OutputStreamWriter;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStreamWriter;", "kotlin-stdlib-jdk7"}, xs = "kotlin/io/path/PathsKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-jdk7-1.5.21.jar:kotlin/io/path/PathsKt__PathReadWriteKt.class */
class PathsKt__PathReadWriteKt {
    static /* synthetic */ InputStreamReader reader$default(Path $this$reader, Charset charset, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new InputStreamReader(Files.newInputStream($this$reader, (OpenOption[]) Arrays.copyOf(options, options.length)), charset);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final InputStreamReader reader(Path $this$reader, Charset charset, OpenOption... options) throws IOException {
        return new InputStreamReader(Files.newInputStream($this$reader, (OpenOption[]) Arrays.copyOf(options, options.length)), charset);
    }

    static /* synthetic */ BufferedReader bufferedReader$default(Path $this$bufferedReader, Charset charset, int bufferSize, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        return new BufferedReader(new InputStreamReader(Files.newInputStream($this$bufferedReader, (OpenOption[]) Arrays.copyOf(options, options.length)), charset), bufferSize);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final BufferedReader bufferedReader(Path $this$bufferedReader, Charset charset, int bufferSize, OpenOption... options) throws IOException {
        return new BufferedReader(new InputStreamReader(Files.newInputStream($this$bufferedReader, (OpenOption[]) Arrays.copyOf(options, options.length)), charset), bufferSize);
    }

    static /* synthetic */ OutputStreamWriter writer$default(Path $this$writer, Charset charset, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new OutputStreamWriter(Files.newOutputStream($this$writer, (OpenOption[]) Arrays.copyOf(options, options.length)), charset);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final OutputStreamWriter writer(Path $this$writer, Charset charset, OpenOption... options) throws IOException {
        return new OutputStreamWriter(Files.newOutputStream($this$writer, (OpenOption[]) Arrays.copyOf(options, options.length)), charset);
    }

    static /* synthetic */ BufferedWriter bufferedWriter$default(Path $this$bufferedWriter, Charset charset, int bufferSize, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream($this$bufferedWriter, (OpenOption[]) Arrays.copyOf(options, options.length)), charset), bufferSize);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final BufferedWriter bufferedWriter(Path $this$bufferedWriter, Charset charset, int bufferSize, OpenOption... options) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream($this$bufferedWriter, (OpenOption[]) Arrays.copyOf(options, options.length)), charset), bufferSize);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final byte[] readBytes(Path $this$readBytes) throws IOException {
        byte[] allBytes = Files.readAllBytes($this$readBytes);
        Intrinsics.checkNotNullExpressionValue(allBytes, "Files.readAllBytes(this)");
        return allBytes;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final void writeBytes(Path $this$writeBytes, byte[] array, OpenOption... options) throws IOException {
        Files.write($this$writeBytes, array, (OpenOption[]) Arrays.copyOf(options, options.length));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final void appendBytes(Path $this$appendBytes, byte[] array) throws IOException {
        Files.write($this$appendBytes, array, StandardOpenOption.APPEND);
    }

    public static /* synthetic */ String readText$default(Path path, Charset charset, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return PathsKt.readText(path, charset);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @NotNull
    public static final String readText(@NotNull Path readText, @NotNull Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter(readText, "$this$readText");
        Intrinsics.checkNotNullParameter(charset, "charset");
        OpenOption[] openOptionArr = new OpenOption[0];
        InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream(readText, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), charset);
        Throwable th = (Throwable) null;
        try {
            InputStreamReader it = inputStreamReader;
            String text = TextStreamsKt.readText(it);
            CloseableKt.closeFinally(inputStreamReader, th);
            return text;
        } catch (Throwable th2) {
            CloseableKt.closeFinally(inputStreamReader, th);
            throw th2;
        }
    }

    public static /* synthetic */ void writeText$default(Path path, CharSequence charSequence, Charset charset, OpenOption[] openOptionArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        PathsKt.writeText(path, charSequence, charset, openOptionArr);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final void writeText(@NotNull Path writeText, @NotNull CharSequence text, @NotNull Charset charset, @NotNull OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(writeText, "$this$writeText");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(writeText, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "Files.newOutputStream(this, *options)");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStreamNewOutputStream, charset);
        Throwable th = (Throwable) null;
        try {
            OutputStreamWriter it = outputStreamWriter;
            it.append(text);
            CloseableKt.closeFinally(outputStreamWriter, th);
        } catch (Throwable th2) {
            CloseableKt.closeFinally(outputStreamWriter, th);
            throw th2;
        }
    }

    public static /* synthetic */ void appendText$default(Path path, CharSequence charSequence, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        PathsKt.appendText(path, charSequence, charset);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final void appendText(@NotNull Path appendText, @NotNull CharSequence text, @NotNull Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter(appendText, "$this$appendText");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(appendText, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "Files.newOutputStream(th…tandardOpenOption.APPEND)");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStreamNewOutputStream, charset);
        Throwable th = (Throwable) null;
        try {
            try {
                OutputStreamWriter it = outputStreamWriter;
                it.append(text);
                CloseableKt.closeFinally(outputStreamWriter, th);
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(outputStreamWriter, th);
            throw th2;
        }
    }

    static /* synthetic */ void forEachLine$default(Path $this$forEachLine, Charset charset, Function1 action, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Reader readerNewBufferedReader = Files.newBufferedReader($this$forEachLine, charset);
        Intrinsics.checkNotNullExpressionValue(readerNewBufferedReader, "Files.newBufferedReader(this, charset)");
        Reader $this$useLines$iv = readerNewBufferedReader;
        BufferedReader bufferedReader = $this$useLines$iv instanceof BufferedReader ? (BufferedReader) $this$useLines$iv : new BufferedReader($this$useLines$iv, 8192);
        Throwable th = (Throwable) null;
        try {
            BufferedReader it$iv = bufferedReader;
            Sequence it = TextStreamsKt.lineSequence(it$iv);
            Iterator<String> it2 = it.iterator();
            while (it2.hasNext()) {
                action.invoke(it2.next());
            }
            Unit unit = Unit.INSTANCE;
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                CloseableKt.closeFinally(bufferedReader, th);
            } else {
                bufferedReader.close();
            }
            InlineMarker.finallyEnd(1);
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

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final void forEachLine(Path $this$forEachLine, Charset charset, Function1<? super String, Unit> function1) throws IOException {
        Reader readerNewBufferedReader = Files.newBufferedReader($this$forEachLine, charset);
        Intrinsics.checkNotNullExpressionValue(readerNewBufferedReader, "Files.newBufferedReader(this, charset)");
        Reader $this$useLines$iv = readerNewBufferedReader;
        BufferedReader bufferedReader = $this$useLines$iv instanceof BufferedReader ? (BufferedReader) $this$useLines$iv : new BufferedReader($this$useLines$iv, 8192);
        Throwable th = (Throwable) null;
        try {
            try {
                BufferedReader it$iv = bufferedReader;
                Sequence it = TextStreamsKt.lineSequence(it$iv);
                for (Object element$iv : it) {
                    function1.invoke(element$iv);
                }
                Unit unit = Unit.INSTANCE;
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    CloseableKt.closeFinally(bufferedReader, th);
                } else {
                    bufferedReader.close();
                }
                InlineMarker.finallyEnd(1);
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

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final InputStream inputStream(Path $this$inputStream, OpenOption... options) throws IOException {
        InputStream inputStreamNewInputStream = Files.newInputStream($this$inputStream, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "Files.newInputStream(this, *options)");
        return inputStreamNewInputStream;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final OutputStream outputStream(Path $this$outputStream, OpenOption... options) throws IOException {
        OutputStream outputStreamNewOutputStream = Files.newOutputStream($this$outputStream, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "Files.newOutputStream(this, *options)");
        return outputStreamNewOutputStream;
    }

    static /* synthetic */ List readLines$default(Path $this$readLines, Charset charset, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        List<String> allLines = Files.readAllLines($this$readLines, charset);
        Intrinsics.checkNotNullExpressionValue(allLines, "Files.readAllLines(this, charset)");
        return allLines;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final List<String> readLines(Path $this$readLines, Charset charset) throws IOException {
        List<String> allLines = Files.readAllLines($this$readLines, charset);
        Intrinsics.checkNotNullExpressionValue(allLines, "Files.readAllLines(this, charset)");
        return allLines;
    }

    static /* synthetic */ Object useLines$default(Path $this$useLines, Charset charset, Function1 block, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        BufferedReader bufferedReaderNewBufferedReader = Files.newBufferedReader($this$useLines, charset);
        Throwable th = (Throwable) null;
        try {
            try {
                BufferedReader it = bufferedReaderNewBufferedReader;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                Object objInvoke = block.invoke(TextStreamsKt.lineSequence(it));
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    CloseableKt.closeFinally(bufferedReaderNewBufferedReader, th);
                } else if (bufferedReaderNewBufferedReader != null) {
                    bufferedReaderNewBufferedReader.close();
                }
                InlineMarker.finallyEnd(1);
                return objInvoke;
            } finally {
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                CloseableKt.closeFinally(bufferedReaderNewBufferedReader, th);
            } else if (bufferedReaderNewBufferedReader != null) {
                if (th == null) {
                    bufferedReaderNewBufferedReader.close();
                } else {
                    try {
                        bufferedReaderNewBufferedReader.close();
                    } catch (Throwable th3) {
                    }
                }
            }
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final <T> T useLines(Path $this$useLines, Charset charset, Function1<? super Sequence<String>, ? extends T> function1) throws IOException {
        BufferedReader bufferedReaderNewBufferedReader = Files.newBufferedReader($this$useLines, charset);
        Throwable th = (Throwable) null;
        try {
            BufferedReader it = bufferedReaderNewBufferedReader;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            T tInvoke = function1.invoke(TextStreamsKt.lineSequence(it));
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                CloseableKt.closeFinally(bufferedReaderNewBufferedReader, th);
            } else if (bufferedReaderNewBufferedReader != null) {
                bufferedReaderNewBufferedReader.close();
            }
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                CloseableKt.closeFinally(bufferedReaderNewBufferedReader, th);
            } else if (bufferedReaderNewBufferedReader != null) {
                if (th == null) {
                    bufferedReaderNewBufferedReader.close();
                } else {
                    try {
                        bufferedReaderNewBufferedReader.close();
                    } catch (Throwable th3) {
                    }
                }
            }
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    static /* synthetic */ Path writeLines$default(Path $this$writeLines, Iterable lines, Charset charset, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Path pathWrite = Files.write($this$writeLines, lines, charset, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(pathWrite, "Files.write(this, lines, charset, *options)");
        return pathWrite;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final Path writeLines(Path $this$writeLines, Iterable<? extends CharSequence> iterable, Charset charset, OpenOption... options) throws IOException {
        Path pathWrite = Files.write($this$writeLines, iterable, charset, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(pathWrite, "Files.write(this, lines, charset, *options)");
        return pathWrite;
    }

    static /* synthetic */ Path writeLines$default(Path $this$writeLines, Sequence lines, Charset charset, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Path pathWrite = Files.write($this$writeLines, SequencesKt.asIterable(lines), charset, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(pathWrite, "Files.write(this, lines.…ble(), charset, *options)");
        return pathWrite;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final Path writeLines(Path $this$writeLines, Sequence<? extends CharSequence> sequence, Charset charset, OpenOption... options) throws IOException {
        Path pathWrite = Files.write($this$writeLines, SequencesKt.asIterable(sequence), charset, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(pathWrite, "Files.write(this, lines.…ble(), charset, *options)");
        return pathWrite;
    }

    static /* synthetic */ Path appendLines$default(Path $this$appendLines, Iterable lines, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Path pathWrite = Files.write($this$appendLines, lines, charset, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(pathWrite, "Files.write(this, lines,…tandardOpenOption.APPEND)");
        return pathWrite;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final Path appendLines(Path $this$appendLines, Iterable<? extends CharSequence> iterable, Charset charset) throws IOException {
        Path pathWrite = Files.write($this$appendLines, iterable, charset, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(pathWrite, "Files.write(this, lines,…tandardOpenOption.APPEND)");
        return pathWrite;
    }

    static /* synthetic */ Path appendLines$default(Path $this$appendLines, Sequence lines, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Path pathWrite = Files.write($this$appendLines, SequencesKt.asIterable(lines), charset, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(pathWrite, "Files.write(this, lines.…tandardOpenOption.APPEND)");
        return pathWrite;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    private static final Path appendLines(Path $this$appendLines, Sequence<? extends CharSequence> sequence, Charset charset) throws IOException {
        Path pathWrite = Files.write($this$appendLines, SequencesKt.asIterable(sequence), charset, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(pathWrite, "Files.write(this, lines.…tandardOpenOption.APPEND)");
        return pathWrite;
    }
}
