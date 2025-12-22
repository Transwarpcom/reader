package kotlin.io;

import com.jayway.jsonpath.internal.function.text.Length;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.collections.ByteIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* compiled from: IOStreams.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��Z\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u0017\u0010��\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010��\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0007\u001a\u00020\b*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0017\u0010\u000b\u001a\u00020\f*\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0017\u0010\r\u001a\u00020\u000e*\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u001c\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\r\u0010\u0013\u001a\u00020\u000e*\u00020\u0014H\u0087\b\u001a\u001d\u0010\u0013\u001a\u00020\u000e*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\u0001H\u0086\u0002\u001a\f\u0010\u0019\u001a\u00020\u0014*\u00020\u0002H\u0007\u001a\u0016\u0010\u0019\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u0004H\u0007\u001a\u0017\u0010\u001b\u001a\u00020\u001c*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0017\u0010\u001d\u001a\u00020\u001e*\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b¨\u0006\u001f"}, d2 = {"buffered", "Ljava/io/BufferedInputStream;", "Ljava/io/InputStream;", "bufferSize", "", "Ljava/io/BufferedOutputStream;", "Ljava/io/OutputStream;", "bufferedReader", "Ljava/io/BufferedReader;", "charset", "Ljava/nio/charset/Charset;", "bufferedWriter", "Ljava/io/BufferedWriter;", "byteInputStream", "Ljava/io/ByteArrayInputStream;", "", "copyTo", "", "out", "inputStream", "", "offset", Length.TOKEN_NAME, "iterator", "Lkotlin/collections/ByteIterator;", "readBytes", "estimatedSize", "reader", "Ljava/io/InputStreamReader;", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"})
@JvmName(name = "ByteStreamsKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/io/ByteStreamsKt.class */
public final class ByteStreamsKt {
    @NotNull
    public static final ByteIterator iterator(@NotNull final BufferedInputStream iterator) {
        Intrinsics.checkNotNullParameter(iterator, "$this$iterator");
        return new ByteIterator() { // from class: kotlin.io.ByteStreamsKt.iterator.1
            private int nextByte = -1;
            private boolean nextPrepared;
            private boolean finished;

            public final int getNextByte() {
                return this.nextByte;
            }

            public final void setNextByte(int i) {
                this.nextByte = i;
            }

            public final boolean getNextPrepared() {
                return this.nextPrepared;
            }

            public final void setNextPrepared(boolean z) {
                this.nextPrepared = z;
            }

            public final boolean getFinished() {
                return this.finished;
            }

            public final void setFinished(boolean z) {
                this.finished = z;
            }

            private final void prepareNext() {
                if (!this.nextPrepared && !this.finished) {
                    this.nextByte = iterator.read();
                    this.nextPrepared = true;
                    this.finished = this.nextByte == -1;
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                prepareNext();
                return !this.finished;
            }

            @Override // kotlin.collections.ByteIterator
            public byte nextByte() {
                prepareNext();
                if (this.finished) {
                    throw new NoSuchElementException("Input stream is over.");
                }
                byte res = (byte) this.nextByte;
                this.nextPrepared = false;
                return res;
            }
        };
    }

    @InlineOnly
    private static final ByteArrayInputStream byteInputStream(String $this$byteInputStream, Charset charset) {
        if ($this$byteInputStream == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = $this$byteInputStream.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        return new ByteArrayInputStream(bytes);
    }

    static /* synthetic */ ByteArrayInputStream byteInputStream$default(String $this$byteInputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ($this$byteInputStream == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = $this$byteInputStream.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        return new ByteArrayInputStream(bytes);
    }

    @InlineOnly
    private static final ByteArrayInputStream inputStream(byte[] $this$inputStream) {
        return new ByteArrayInputStream($this$inputStream);
    }

    @InlineOnly
    private static final ByteArrayInputStream inputStream(byte[] $this$inputStream, int offset, int length) {
        return new ByteArrayInputStream($this$inputStream, offset, length);
    }

    static /* synthetic */ BufferedInputStream buffered$default(InputStream $this$buffered, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            bufferSize = 8192;
        }
        return $this$buffered instanceof BufferedInputStream ? (BufferedInputStream) $this$buffered : new BufferedInputStream($this$buffered, bufferSize);
    }

    @InlineOnly
    private static final BufferedInputStream buffered(InputStream $this$buffered, int bufferSize) {
        return $this$buffered instanceof BufferedInputStream ? (BufferedInputStream) $this$buffered : new BufferedInputStream($this$buffered, bufferSize);
    }

    @InlineOnly
    private static final InputStreamReader reader(InputStream $this$reader, Charset charset) {
        return new InputStreamReader($this$reader, charset);
    }

    static /* synthetic */ InputStreamReader reader$default(InputStream $this$reader, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new InputStreamReader($this$reader, charset);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(InputStream $this$bufferedReader, Charset charset) {
        Reader inputStreamReader = new InputStreamReader($this$bufferedReader, charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
    }

    static /* synthetic */ BufferedReader bufferedReader$default(InputStream $this$bufferedReader, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Reader inputStreamReader = new InputStreamReader($this$bufferedReader, charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
    }

    static /* synthetic */ BufferedOutputStream buffered$default(OutputStream $this$buffered, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            bufferSize = 8192;
        }
        return $this$buffered instanceof BufferedOutputStream ? (BufferedOutputStream) $this$buffered : new BufferedOutputStream($this$buffered, bufferSize);
    }

    @InlineOnly
    private static final BufferedOutputStream buffered(OutputStream $this$buffered, int bufferSize) {
        return $this$buffered instanceof BufferedOutputStream ? (BufferedOutputStream) $this$buffered : new BufferedOutputStream($this$buffered, bufferSize);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(OutputStream $this$writer, Charset charset) {
        return new OutputStreamWriter($this$writer, charset);
    }

    static /* synthetic */ OutputStreamWriter writer$default(OutputStream $this$writer, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new OutputStreamWriter($this$writer, charset);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(OutputStream $this$bufferedWriter, Charset charset) {
        Writer outputStreamWriter = new OutputStreamWriter($this$bufferedWriter, charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192);
    }

    static /* synthetic */ BufferedWriter bufferedWriter$default(OutputStream $this$bufferedWriter, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Writer outputStreamWriter = new OutputStreamWriter($this$bufferedWriter, charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192);
    }

    public static /* synthetic */ long copyTo$default(InputStream inputStream, OutputStream outputStream, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return copyTo(inputStream, outputStream, i);
    }

    public static final long copyTo(@NotNull InputStream copyTo, @NotNull OutputStream out, int bufferSize) throws IOException {
        Intrinsics.checkNotNullParameter(copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(out, "out");
        long bytesCopied = 0;
        byte[] buffer = new byte[bufferSize];
        int i = copyTo.read(buffer);
        while (true) {
            int bytes = i;
            if (bytes >= 0) {
                out.write(buffer, 0, bytes);
                bytesCopied += bytes;
                i = copyTo.read(buffer);
            } else {
                return bytesCopied;
            }
        }
    }

    public static /* synthetic */ byte[] readBytes$default(InputStream inputStream, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return readBytes(inputStream, i);
    }

    @Deprecated(message = "Use readBytes() overload without estimatedSize parameter", replaceWith = @ReplaceWith(imports = {}, expression = "readBytes()"))
    @DeprecatedSinceKotlin(warningSince = "1.3", errorSince = "1.5")
    @NotNull
    public static final byte[] readBytes(@NotNull InputStream readBytes, int estimatedSize) {
        Intrinsics.checkNotNullParameter(readBytes, "$this$readBytes");
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(Math.max(estimatedSize, readBytes.available()));
        copyTo$default(readBytes, buffer, 0, 2, null);
        byte[] byteArray = buffer.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "buffer.toByteArray()");
        return byteArray;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final byte[] readBytes(@NotNull InputStream readBytes) {
        Intrinsics.checkNotNullParameter(readBytes, "$this$readBytes");
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(Math.max(8192, readBytes.available()));
        copyTo$default(readBytes, buffer, 0, 2, null);
        byte[] byteArray = buffer.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "buffer.toByteArray()");
        return byteArray;
    }
}
