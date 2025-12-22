package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Streams;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.MustBeClosed;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/CharSource.class */
public abstract class CharSource {
    public abstract Reader openStream() throws IOException;

    protected CharSource() {
    }

    @Beta
    public ByteSource asByteSource(Charset charset) {
        return new AsByteSource(charset);
    }

    public BufferedReader openBufferedStream() throws IOException {
        Reader reader = openStream();
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    @Beta
    @MustBeClosed
    public Stream<String> lines() throws IOException {
        BufferedReader reader = openBufferedStream();
        return (Stream) reader.lines().onClose(() -> {
            try {
                reader.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    @Beta
    public Optional<Long> lengthIfKnown() {
        return Optional.absent();
    }

    @Beta
    public long length() throws Throwable {
        RuntimeException runtimeExceptionRethrow;
        Optional<Long> lengthIfKnown = lengthIfKnown();
        if (lengthIfKnown.isPresent()) {
            return lengthIfKnown.get().longValue();
        }
        Closer closer = Closer.create();
        try {
            try {
                Reader reader = (Reader) closer.register(openStream());
                long jCountBySkipping = countBySkipping(reader);
                closer.close();
                return jCountBySkipping;
            } finally {
            }
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    private long countBySkipping(Reader reader) throws IOException {
        long j = 0;
        while (true) {
            long count = j;
            long read = reader.skip(Long.MAX_VALUE);
            if (read != 0) {
                j = count + read;
            } else {
                return count;
            }
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(Appendable appendable) throws Throwable {
        RuntimeException runtimeExceptionRethrow;
        Preconditions.checkNotNull(appendable);
        Closer closer = Closer.create();
        try {
            try {
                Reader reader = (Reader) closer.register(openStream());
                long jCopy = CharStreams.copy(reader, appendable);
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
    public long copyTo(CharSink sink) throws Throwable {
        RuntimeException runtimeExceptionRethrow;
        Preconditions.checkNotNull(sink);
        Closer closer = Closer.create();
        try {
            try {
                Reader reader = (Reader) closer.register(openStream());
                Writer writer = (Writer) closer.register(sink.openStream());
                long jCopy = CharStreams.copy(reader, writer);
                closer.close();
                return jCopy;
            } finally {
            }
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    public String read() throws Throwable {
        RuntimeException runtimeExceptionRethrow;
        Closer closer = Closer.create();
        try {
            try {
                Reader reader = (Reader) closer.register(openStream());
                String string = CharStreams.toString(reader);
                closer.close();
                return string;
            } finally {
            }
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    public String readFirstLine() throws Throwable {
        RuntimeException runtimeExceptionRethrow;
        Closer closer = Closer.create();
        try {
            try {
                BufferedReader reader = (BufferedReader) closer.register(openBufferedStream());
                String line = reader.readLine();
                closer.close();
                return line;
            } finally {
            }
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    public ImmutableList<String> readLines() throws Throwable {
        RuntimeException runtimeExceptionRethrow;
        Closer closer = Closer.create();
        try {
            try {
                BufferedReader reader = (BufferedReader) closer.register(openBufferedStream());
                List<String> result = Lists.newArrayList();
                while (true) {
                    String line = reader.readLine();
                    if (line != null) {
                        result.add(line);
                    } else {
                        ImmutableList<String> immutableListCopyOf = ImmutableList.copyOf((Collection) result);
                        closer.close();
                        return immutableListCopyOf;
                    }
                }
            } finally {
            }
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    @CanIgnoreReturnValue
    @Beta
    public <T> T readLines(LineProcessor<T> lineProcessor) throws Throwable {
        RuntimeException runtimeExceptionRethrow;
        Preconditions.checkNotNull(lineProcessor);
        Closer closerCreate = Closer.create();
        try {
            try {
                T t = (T) CharStreams.readLines((Reader) closerCreate.register(openStream()), lineProcessor);
                closerCreate.close();
                return t;
            } finally {
            }
        } catch (Throwable th) {
            closerCreate.close();
            throw th;
        }
    }

    @Beta
    public void forEachLine(Consumer<? super String> action) throws IOException {
        try {
            Stream<String> lines = lines();
            Throwable th = null;
            try {
                try {
                    lines.forEachOrdered(action);
                    if (lines != null) {
                        if (0 != 0) {
                            try {
                                lines.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        } else {
                            lines.close();
                        }
                    }
                } finally {
                }
            } finally {
            }
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }

    public boolean isEmpty() throws Throwable {
        Optional<Long> lengthIfKnown = lengthIfKnown();
        if (lengthIfKnown.isPresent()) {
            return lengthIfKnown.get().longValue() == 0;
        }
        Closer closer = Closer.create();
        try {
            try {
                Reader reader = (Reader) closer.register(openStream());
                return reader.read() == -1;
            } catch (Throwable e) {
                throw closer.rethrow(e);
            }
        } finally {
            closer.close();
        }
    }

    public static CharSource concat(Iterable<? extends CharSource> sources) {
        return new ConcatenatedCharSource(sources);
    }

    public static CharSource concat(Iterator<? extends CharSource> sources) {
        return concat(ImmutableList.copyOf(sources));
    }

    public static CharSource concat(CharSource... sources) {
        return concat(ImmutableList.copyOf(sources));
    }

    public static CharSource wrap(CharSequence charSequence) {
        return charSequence instanceof String ? new StringCharSource((String) charSequence) : new CharSequenceCharSource(charSequence);
    }

    public static CharSource empty() {
        return EmptyCharSource.INSTANCE;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/CharSource$AsByteSource.class */
    private final class AsByteSource extends ByteSource {
        final Charset charset;

        AsByteSource(Charset charset) {
            this.charset = (Charset) Preconditions.checkNotNull(charset);
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            if (charset.equals(this.charset)) {
                return CharSource.this;
            }
            return super.asCharSource(charset);
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return new ReaderInputStream(CharSource.this.openStream(), this.charset, 8192);
        }

        public String toString() {
            return CharSource.this.toString() + ".asByteSource(" + this.charset + ")";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/CharSource$CharSequenceCharSource.class */
    private static class CharSequenceCharSource extends CharSource {
        private static final Splitter LINE_SPLITTER = Splitter.onPattern("\r\n|\n|\r");
        protected final CharSequence seq;

        protected CharSequenceCharSource(CharSequence seq) {
            this.seq = (CharSequence) Preconditions.checkNotNull(seq);
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() {
            return new CharSequenceReader(this.seq);
        }

        @Override // com.google.common.io.CharSource
        public String read() {
            return this.seq.toString();
        }

        @Override // com.google.common.io.CharSource
        public boolean isEmpty() {
            return this.seq.length() == 0;
        }

        @Override // com.google.common.io.CharSource
        public long length() {
            return this.seq.length();
        }

        @Override // com.google.common.io.CharSource
        public Optional<Long> lengthIfKnown() {
            return Optional.of(Long.valueOf(this.seq.length()));
        }

        private Iterator<String> linesIterator() {
            return new AbstractIterator<String>() { // from class: com.google.common.io.CharSource.CharSequenceCharSource.1
                Iterator<String> lines;

                {
                    this.lines = CharSequenceCharSource.LINE_SPLITTER.split(CharSequenceCharSource.this.seq).iterator();
                }

                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.common.collect.AbstractIterator
                public String computeNext() {
                    if (this.lines.hasNext()) {
                        String next = this.lines.next();
                        if (this.lines.hasNext() || !next.isEmpty()) {
                            return next;
                        }
                    }
                    return endOfData();
                }
            };
        }

        @Override // com.google.common.io.CharSource
        public Stream<String> lines() {
            return Streams.stream(linesIterator());
        }

        @Override // com.google.common.io.CharSource
        public String readFirstLine() {
            Iterator<String> lines = linesIterator();
            if (lines.hasNext()) {
                return lines.next();
            }
            return null;
        }

        @Override // com.google.common.io.CharSource
        public ImmutableList<String> readLines() {
            return ImmutableList.copyOf(linesIterator());
        }

        @Override // com.google.common.io.CharSource
        public <T> T readLines(LineProcessor<T> processor) throws IOException {
            Iterator<String> lines = linesIterator();
            while (lines.hasNext() && processor.processLine(lines.next())) {
            }
            return processor.getResult();
        }

        public String toString() {
            return "CharSource.wrap(" + Ascii.truncate(this.seq, 30, "...") + ")";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/CharSource$StringCharSource.class */
    private static class StringCharSource extends CharSequenceCharSource {
        protected StringCharSource(String seq) {
            super(seq);
        }

        @Override // com.google.common.io.CharSource.CharSequenceCharSource, com.google.common.io.CharSource
        public Reader openStream() {
            return new StringReader((String) this.seq);
        }

        @Override // com.google.common.io.CharSource
        public long copyTo(Appendable appendable) throws IOException {
            appendable.append(this.seq);
            return this.seq.length();
        }

        @Override // com.google.common.io.CharSource
        public long copyTo(CharSink sink) throws Throwable {
            RuntimeException runtimeExceptionRethrow;
            Preconditions.checkNotNull(sink);
            Closer closer = Closer.create();
            try {
                try {
                    Writer writer = (Writer) closer.register(sink.openStream());
                    writer.write((String) this.seq);
                    long length = this.seq.length();
                    closer.close();
                    return length;
                } finally {
                }
            } catch (Throwable th) {
                closer.close();
                throw th;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/CharSource$EmptyCharSource.class */
    private static final class EmptyCharSource extends StringCharSource {
        private static final EmptyCharSource INSTANCE = new EmptyCharSource();

        private EmptyCharSource() {
            super("");
        }

        @Override // com.google.common.io.CharSource.CharSequenceCharSource
        public String toString() {
            return "CharSource.empty()";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/CharSource$ConcatenatedCharSource.class */
    private static final class ConcatenatedCharSource extends CharSource {
        private final Iterable<? extends CharSource> sources;

        ConcatenatedCharSource(Iterable<? extends CharSource> sources) {
            this.sources = (Iterable) Preconditions.checkNotNull(sources);
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() throws IOException {
            return new MultiReader(this.sources.iterator());
        }

        @Override // com.google.common.io.CharSource
        public boolean isEmpty() throws IOException {
            for (CharSource source : this.sources) {
                if (!source.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.CharSource
        public Optional<Long> lengthIfKnown() {
            long result = 0;
            for (CharSource source : this.sources) {
                Optional<Long> lengthIfKnown = source.lengthIfKnown();
                if (!lengthIfKnown.isPresent()) {
                    return Optional.absent();
                }
                result += lengthIfKnown.get().longValue();
            }
            return Optional.of(Long.valueOf(result));
        }

        @Override // com.google.common.io.CharSource
        public long length() throws IOException {
            long result = 0;
            for (CharSource source : this.sources) {
                result += source.length();
            }
            return result;
        }

        public String toString() {
            return "CharSource.concat(" + this.sources + ")";
        }
    }
}
