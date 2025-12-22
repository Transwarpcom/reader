package org.antlr.v4.runtime;

import java.nio.charset.StandardCharsets;
import org.antlr.v4.runtime.misc.Interval;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/CodePointCharStream.class */
public abstract class CodePointCharStream implements CharStream {
    protected final int size;
    protected final String name;
    protected int position;
    static final /* synthetic */ boolean $assertionsDisabled;

    abstract Object getInternalStorage();

    static {
        $assertionsDisabled = !CodePointCharStream.class.desiredAssertionStatus();
    }

    private CodePointCharStream(int position, int remaining, String name) {
        if (!$assertionsDisabled && position != 0) {
            throw new AssertionError();
        }
        this.size = remaining;
        this.name = name;
        this.position = 0;
    }

    public static CodePointCharStream fromBuffer(CodePointBuffer codePointBuffer) {
        return fromBuffer(codePointBuffer, IntStream.UNKNOWN_SOURCE_NAME);
    }

    public static CodePointCharStream fromBuffer(CodePointBuffer codePointBuffer, String name) {
        switch (codePointBuffer.getType()) {
            case BYTE:
                return new CodePoint8BitCharStream(codePointBuffer.position(), codePointBuffer.remaining(), name, codePointBuffer.byteArray(), codePointBuffer.arrayOffset());
            case CHAR:
                return new CodePoint16BitCharStream(codePointBuffer.position(), codePointBuffer.remaining(), name, codePointBuffer.charArray(), codePointBuffer.arrayOffset());
            case INT:
                return new CodePoint32BitCharStream(codePointBuffer.position(), codePointBuffer.remaining(), name, codePointBuffer.intArray(), codePointBuffer.arrayOffset());
            default:
                throw new UnsupportedOperationException("Not reached");
        }
    }

    @Override // org.antlr.v4.runtime.IntStream
    public final void consume() {
        if (this.size - this.position == 0) {
            if (!$assertionsDisabled && LA(1) != -1) {
                throw new AssertionError();
            }
            throw new IllegalStateException("cannot consume EOF");
        }
        this.position++;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public final int index() {
        return this.position;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public final int size() {
        return this.size;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public final int mark() {
        return -1;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public final void release(int marker) {
    }

    @Override // org.antlr.v4.runtime.IntStream
    public final void seek(int index) {
        this.position = index;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public final String getSourceName() {
        if (this.name == null || this.name.isEmpty()) {
            return IntStream.UNKNOWN_SOURCE_NAME;
        }
        return this.name;
    }

    public final String toString() {
        return getText(Interval.of(0, this.size - 1));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/CodePointCharStream$CodePoint8BitCharStream.class */
    private static final class CodePoint8BitCharStream extends CodePointCharStream {
        private final byte[] byteArray;
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !CodePointCharStream.class.desiredAssertionStatus();
        }

        private CodePoint8BitCharStream(int position, int remaining, String name, byte[] byteArray, int arrayOffset) {
            super(position, remaining, name);
            if (!$assertionsDisabled && arrayOffset != 0) {
                throw new AssertionError();
            }
            this.byteArray = byteArray;
        }

        @Override // org.antlr.v4.runtime.CharStream
        public String getText(Interval interval) {
            int startIdx = Math.min(interval.a, this.size);
            int len = Math.min((interval.b - interval.a) + 1, this.size - startIdx);
            return new String(this.byteArray, startIdx, len, StandardCharsets.ISO_8859_1);
        }

        @Override // org.antlr.v4.runtime.IntStream
        public int LA(int i) {
            switch (Integer.signum(i)) {
                case -1:
                    int offset = this.position + i;
                    if (offset < 0) {
                        return -1;
                    }
                    return this.byteArray[offset] & 255;
                case 0:
                    return 0;
                case 1:
                    int offset2 = (this.position + i) - 1;
                    if (offset2 >= this.size) {
                        return -1;
                    }
                    return this.byteArray[offset2] & 255;
                default:
                    throw new UnsupportedOperationException("Not reached");
            }
        }

        @Override // org.antlr.v4.runtime.CodePointCharStream
        Object getInternalStorage() {
            return this.byteArray;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/CodePointCharStream$CodePoint16BitCharStream.class */
    private static final class CodePoint16BitCharStream extends CodePointCharStream {
        private final char[] charArray;
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !CodePointCharStream.class.desiredAssertionStatus();
        }

        private CodePoint16BitCharStream(int position, int remaining, String name, char[] charArray, int arrayOffset) {
            super(position, remaining, name);
            this.charArray = charArray;
            if (!$assertionsDisabled && arrayOffset != 0) {
                throw new AssertionError();
            }
        }

        @Override // org.antlr.v4.runtime.CharStream
        public String getText(Interval interval) {
            int startIdx = Math.min(interval.a, this.size);
            int len = Math.min((interval.b - interval.a) + 1, this.size - startIdx);
            return new String(this.charArray, startIdx, len);
        }

        @Override // org.antlr.v4.runtime.IntStream
        public int LA(int i) {
            switch (Integer.signum(i)) {
                case -1:
                    int offset = this.position + i;
                    if (offset < 0) {
                        return -1;
                    }
                    return this.charArray[offset] & 65535;
                case 0:
                    return 0;
                case 1:
                    int offset2 = (this.position + i) - 1;
                    if (offset2 >= this.size) {
                        return -1;
                    }
                    return this.charArray[offset2] & 65535;
                default:
                    throw new UnsupportedOperationException("Not reached");
            }
        }

        @Override // org.antlr.v4.runtime.CodePointCharStream
        Object getInternalStorage() {
            return this.charArray;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/CodePointCharStream$CodePoint32BitCharStream.class */
    private static final class CodePoint32BitCharStream extends CodePointCharStream {
        private final int[] intArray;
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !CodePointCharStream.class.desiredAssertionStatus();
        }

        private CodePoint32BitCharStream(int position, int remaining, String name, int[] intArray, int arrayOffset) {
            super(position, remaining, name);
            this.intArray = intArray;
            if (!$assertionsDisabled && arrayOffset != 0) {
                throw new AssertionError();
            }
        }

        @Override // org.antlr.v4.runtime.CharStream
        public String getText(Interval interval) {
            int startIdx = Math.min(interval.a, this.size);
            int len = Math.min((interval.b - interval.a) + 1, this.size - startIdx);
            return new String(this.intArray, startIdx, len);
        }

        @Override // org.antlr.v4.runtime.IntStream
        public int LA(int i) {
            switch (Integer.signum(i)) {
                case -1:
                    int offset = this.position + i;
                    if (offset < 0) {
                        return -1;
                    }
                    return this.intArray[offset];
                case 0:
                    return 0;
                case 1:
                    int offset2 = (this.position + i) - 1;
                    if (offset2 >= this.size) {
                        return -1;
                    }
                    return this.intArray[offset2];
                default:
                    throw new UnsupportedOperationException("Not reached");
            }
        }

        @Override // org.antlr.v4.runtime.CodePointCharStream
        Object getInternalStorage() {
            return this.intArray;
        }
    }
}
