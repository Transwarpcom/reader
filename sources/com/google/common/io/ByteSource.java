package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/ByteSource.class */
public abstract class ByteSource {
    public abstract InputStream openStream() throws IOException;

    protected ByteSource() {
    }

    public CharSource asCharSource(Charset charset) {
        return new AsCharSource(charset);
    }

    public InputStream openBufferedStream() throws IOException {
        InputStream in = openStream();
        return in instanceof BufferedInputStream ? (BufferedInputStream) in : new BufferedInputStream(in);
    }

    public ByteSource slice(long offset, long length) {
        return new SlicedByteSource(offset, length);
    }

    public boolean isEmpty() throws Throwable {
        Optional<Long> sizeIfKnown = sizeIfKnown();
        if (sizeIfKnown.isPresent()) {
            return sizeIfKnown.get().longValue() == 0;
        }
        Closer closer = Closer.create();
        try {
            try {
                InputStream in = (InputStream) closer.register(openStream());
                return in.read() == -1;
            } catch (Throwable e) {
                throw closer.rethrow(e);
            }
        } finally {
            closer.close();
        }
    }

    @Beta
    public Optional<Long> sizeIfKnown() {
        return Optional.absent();
    }

    public long size() throws Throwable {
        RuntimeException runtimeExceptionRethrow;
        Optional<Long> sizeIfKnown = sizeIfKnown();
        if (sizeIfKnown.isPresent()) {
            return sizeIfKnown.get().longValue();
        }
        Closer closer = Closer.create();
        try {
            InputStream in = (InputStream) closer.register(openStream());
            long jCountBySkipping = countBySkipping(in);
            closer.close();
            return jCountBySkipping;
        } catch (IOException e) {
            closer.close();
            closer = Closer.create();
            try {
                try {
                    InputStream in2 = (InputStream) closer.register(openStream());
                    long jExhaust = ByteStreams.exhaust(in2);
                    closer.close();
                    return jExhaust;
                } finally {
                }
            } finally {
                closer.close();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private long countBySkipping(InputStream in) throws IOException {
        long j = 0;
        while (true) {
            long count = j;
            long skipped = ByteStreams.skipUpTo(in, 2147483647L);
            if (skipped > 0) {
                j = count + skipped;
            } else {
                return count;
            }
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(OutputStream output) throws Throwable {
        RuntimeException runtimeExceptionRethrow;
        Preconditions.checkNotNull(output);
        Closer closer = Closer.create();
        try {
            try {
                InputStream in = (InputStream) closer.register(openStream());
                long jCopy = ByteStreams.copy(in, output);
                closer.close();
                return jCopy;
            } finally {
            }
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(ByteSink sink) throws Throwable {
        RuntimeException runtimeExceptionRethrow;
        Preconditions.checkNotNull(sink);
        Closer closer = Closer.create();
        try {
            try {
                InputStream in = (InputStream) closer.register(openStream());
                OutputStream out = (OutputStream) closer.register(sink.openStream());
                long jCopy = ByteStreams.copy(in, out);
                closer.close();
                return jCopy;
            } finally {
            }
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    public byte[] read() throws Throwable {
        byte[] byteArray;
        Closer closer = Closer.create();
        try {
            try {
                InputStream in = (InputStream) closer.register(openStream());
                Optional<Long> size = sizeIfKnown();
                if (size.isPresent()) {
                    byteArray = ByteStreams.toByteArray(in, size.get().longValue());
                } else {
                    byteArray = ByteStreams.toByteArray(in);
                }
                return byteArray;
            } catch (Throwable e) {
                throw closer.rethrow(e);
            }
        } finally {
            closer.close();
        }
    }

    @CanIgnoreReturnValue
    @Beta
    public <T> T read(ByteProcessor<T> byteProcessor) throws Throwable {
        RuntimeException runtimeExceptionRethrow;
        Preconditions.checkNotNull(byteProcessor);
        Closer closerCreate = Closer.create();
        try {
            try {
                T t = (T) ByteStreams.readBytes((InputStream) closerCreate.register(openStream()), byteProcessor);
                closerCreate.close();
                return t;
            } finally {
            }
        } catch (Throwable th) {
            closerCreate.close();
            throw th;
        }
    }

    public HashCode hash(HashFunction hashFunction) throws Throwable {
        Hasher hasher = hashFunction.newHasher();
        copyTo(Funnels.asOutputStream(hasher));
        return hasher.hash();
    }

    public boolean contentEquals(ByteSource other) throws Throwable {
        RuntimeException runtimeExceptionRethrow;
        int read1;
        Preconditions.checkNotNull(other);
        byte[] buf1 = ByteStreams.createBuffer();
        byte[] buf2 = ByteStreams.createBuffer();
        Closer closer = Closer.create();
        try {
            try {
                InputStream in1 = (InputStream) closer.register(openStream());
                InputStream in2 = (InputStream) closer.register(other.openStream());
                do {
                    read1 = ByteStreams.read(in1, buf1, 0, buf1.length);
                    int read2 = ByteStreams.read(in2, buf2, 0, buf2.length);
                    if (read1 != read2 || !Arrays.equals(buf1, buf2)) {
                        return false;
                    }
                } while (read1 == buf1.length);
                closer.close();
                return true;
            } finally {
            }
        } finally {
            closer.close();
        }
    }

    public static ByteSource concat(Iterable<? extends ByteSource> sources) {
        return new ConcatenatedByteSource(sources);
    }

    public static ByteSource concat(Iterator<? extends ByteSource> sources) {
        return concat(ImmutableList.copyOf(sources));
    }

    public static ByteSource concat(ByteSource... sources) {
        return concat(ImmutableList.copyOf(sources));
    }

    public static ByteSource wrap(byte[] b) {
        return new ByteArrayByteSource(b);
    }

    public static ByteSource empty() {
        return EmptyByteSource.INSTANCE;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/ByteSource$AsCharSource.class */
    class AsCharSource extends CharSource {
        final Charset charset;

        AsCharSource(Charset charset) {
            this.charset = (Charset) Preconditions.checkNotNull(charset);
        }

        @Override // com.google.common.io.CharSource
        public ByteSource asByteSource(Charset charset) {
            if (charset.equals(this.charset)) {
                return ByteSource.this;
            }
            return super.asByteSource(charset);
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() throws IOException {
            return new InputStreamReader(ByteSource.this.openStream(), this.charset);
        }

        @Override // com.google.common.io.CharSource
        public String read() throws IOException {
            return new String(ByteSource.this.read(), this.charset);
        }

        public String toString() {
            return ByteSource.this.toString() + ".asCharSource(" + this.charset + ")";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/ByteSource$SlicedByteSource.class */
    private final class SlicedByteSource extends ByteSource {
        final long offset;
        final long length;

        SlicedByteSource(long offset, long length) {
            Preconditions.checkArgument(offset >= 0, "offset (%s) may not be negative", offset);
            Preconditions.checkArgument(length >= 0, "length (%s) may not be negative", length);
            this.offset = offset;
            this.length = length;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return sliceStream(ByteSource.this.openStream());
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openBufferedStream() throws IOException {
            return sliceStream(ByteSource.this.openBufferedStream());
        }

        private InputStream sliceStream(InputStream in) throws Throwable {
            if (this.offset > 0) {
                try {
                    long skipped = ByteStreams.skipUpTo(in, this.offset);
                    if (skipped < this.offset) {
                        in.close();
                        return new ByteArrayInputStream(new byte[0]);
                    }
                } finally {
                }
            }
            return ByteStreams.limit(in, this.length);
        }

        @Override // com.google.common.io.ByteSource
        public ByteSource slice(long offset, long length) {
            Preconditions.checkArgument(offset >= 0, "offset (%s) may not be negative", offset);
            Preconditions.checkArgument(length >= 0, "length (%s) may not be negative", length);
            long maxLength = this.length - offset;
            return ByteSource.this.slice(this.offset + offset, Math.min(length, maxLength));
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() throws IOException {
            return this.length == 0 || super.isEmpty();
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            Optional<Long> optionalUnslicedSize = ByteSource.this.sizeIfKnown();
            if (optionalUnslicedSize.isPresent()) {
                long unslicedSize = optionalUnslicedSize.get().longValue();
                long off = Math.min(this.offset, unslicedSize);
                return Optional.of(Long.valueOf(Math.min(this.length, unslicedSize - off)));
            }
            return Optional.absent();
        }

        public String toString() {
            return ByteSource.this.toString() + ".slice(" + this.offset + ", " + this.length + ")";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/ByteSource$ByteArrayByteSource.class */
    private static class ByteArrayByteSource extends ByteSource {
        final byte[] bytes;
        final int offset;
        final int length;

        ByteArrayByteSource(byte[] bytes) {
            this(bytes, 0, bytes.length);
        }

        ByteArrayByteSource(byte[] bytes, int offset, int length) {
            this.bytes = bytes;
            this.offset = offset;
            this.length = length;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() {
            return new ByteArrayInputStream(this.bytes, this.offset, this.length);
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openBufferedStream() throws IOException {
            return openStream();
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() {
            return this.length == 0;
        }

        @Override // com.google.common.io.ByteSource
        public long size() {
            return this.length;
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            return Optional.of(Long.valueOf(this.length));
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() {
            return Arrays.copyOfRange(this.bytes, this.offset, this.offset + this.length);
        }

        @Override // com.google.common.io.ByteSource
        public <T> T read(ByteProcessor<T> processor) throws IOException {
            processor.processBytes(this.bytes, this.offset, this.length);
            return processor.getResult();
        }

        @Override // com.google.common.io.ByteSource
        public long copyTo(OutputStream output) throws IOException {
            output.write(this.bytes, this.offset, this.length);
            return this.length;
        }

        @Override // com.google.common.io.ByteSource
        public HashCode hash(HashFunction hashFunction) throws IOException {
            return hashFunction.hashBytes(this.bytes, this.offset, this.length);
        }

        @Override // com.google.common.io.ByteSource
        public ByteSource slice(long offset, long length) {
            Preconditions.checkArgument(offset >= 0, "offset (%s) may not be negative", offset);
            Preconditions.checkArgument(length >= 0, "length (%s) may not be negative", length);
            long offset2 = Math.min(offset, this.length);
            long length2 = Math.min(length, this.length - offset2);
            int newOffset = this.offset + ((int) offset2);
            return new ByteArrayByteSource(this.bytes, newOffset, (int) length2);
        }

        public String toString() {
            return "ByteSource.wrap(" + Ascii.truncate(BaseEncoding.base16().encode(this.bytes, this.offset, this.length), 30, "...") + ")";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/ByteSource$EmptyByteSource.class */
    private static final class EmptyByteSource extends ByteArrayByteSource {
        static final EmptyByteSource INSTANCE = new EmptyByteSource();

        EmptyByteSource() {
            super(new byte[0]);
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            Preconditions.checkNotNull(charset);
            return CharSource.empty();
        }

        @Override // com.google.common.io.ByteSource.ByteArrayByteSource, com.google.common.io.ByteSource
        public byte[] read() {
            return this.bytes;
        }

        @Override // com.google.common.io.ByteSource.ByteArrayByteSource
        public String toString() {
            return "ByteSource.empty()";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/ByteSource$ConcatenatedByteSource.class */
    private static final class ConcatenatedByteSource extends ByteSource {
        final Iterable<? extends ByteSource> sources;

        ConcatenatedByteSource(Iterable<? extends ByteSource> sources) {
            this.sources = (Iterable) Preconditions.checkNotNull(sources);
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return new MultiInputStream(this.sources.iterator());
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() throws IOException {
            for (ByteSource source : this.sources) {
                if (!source.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            if (!(this.sources instanceof Collection)) {
                return Optional.absent();
            }
            long result = 0;
            for (ByteSource source : this.sources) {
                Optional<Long> sizeIfKnown = source.sizeIfKnown();
                if (!sizeIfKnown.isPresent()) {
                    return Optional.absent();
                }
                result += sizeIfKnown.get().longValue();
                if (result < 0) {
                    return Optional.of(Long.MAX_VALUE);
                }
            }
            return Optional.of(Long.valueOf(result));
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws IOException {
            long result = 0;
            for (ByteSource source : this.sources) {
                result += source.size();
                if (result < 0) {
                    return Long.MAX_VALUE;
                }
            }
            return result;
        }

        public String toString() {
            return "ByteSource.concat(" + this.sources + ")";
        }
    }
}
