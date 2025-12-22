package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.stream.Stream;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/CharSink.class */
public abstract class CharSink {
    public abstract Writer openStream() throws IOException;

    protected CharSink() {
    }

    public Writer openBufferedStream() throws IOException {
        Writer writer = openStream();
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer);
    }

    public void write(CharSequence charSequence) throws Throwable {
        Preconditions.checkNotNull(charSequence);
        Closer closer = Closer.create();
        try {
            try {
                Writer out = (Writer) closer.register(openStream());
                out.append(charSequence);
                out.flush();
                closer.close();
            } catch (Throwable e) {
                throw closer.rethrow(e);
            }
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    public void writeLines(Iterable<? extends CharSequence> lines) throws IOException {
        writeLines(lines, System.getProperty("line.separator"));
    }

    public void writeLines(Iterable<? extends CharSequence> lines, String lineSeparator) throws IOException {
        writeLines(lines.iterator(), lineSeparator);
    }

    @Beta
    public void writeLines(Stream<? extends CharSequence> lines) throws IOException {
        writeLines(lines, System.getProperty("line.separator"));
    }

    @Beta
    public void writeLines(Stream<? extends CharSequence> lines, String lineSeparator) throws IOException {
        writeLines(lines.iterator(), lineSeparator);
    }

    private void writeLines(Iterator<? extends CharSequence> lines, String lineSeparator) throws IOException {
        Preconditions.checkNotNull(lineSeparator);
        Writer out = openBufferedStream();
        Throwable th = null;
        while (lines.hasNext()) {
            try {
                try {
                    out.append(lines.next()).append((CharSequence) lineSeparator);
                } catch (Throwable th2) {
                    th = th2;
                    throw th2;
                }
            } catch (Throwable th3) {
                if (out != null) {
                    if (th != null) {
                        try {
                            out.close();
                        } catch (Throwable th4) {
                            th.addSuppressed(th4);
                        }
                    } else {
                        out.close();
                    }
                }
                throw th3;
            }
        }
        if (out != null) {
            if (0 != 0) {
                try {
                    out.close();
                    return;
                } catch (Throwable th5) {
                    th.addSuppressed(th5);
                    return;
                }
            }
            out.close();
        }
    }

    @CanIgnoreReturnValue
    public long writeFrom(Readable readable) throws Throwable {
        RuntimeException runtimeExceptionRethrow;
        Preconditions.checkNotNull(readable);
        Closer closer = Closer.create();
        try {
            try {
                Writer out = (Writer) closer.register(openStream());
                long written = CharStreams.copy(readable, out);
                out.flush();
                closer.close();
                return written;
            } finally {
            }
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }
}
