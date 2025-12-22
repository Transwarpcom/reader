package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/AbstractMessageLite.class */
public abstract class AbstractMessageLite implements MessageLite {
    protected int memoizedHashCode = 0;

    public void writeDelimitedTo(OutputStream output) throws IOException {
        int serialized = getSerializedSize();
        int bufferSize = CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeRawVarint32Size(serialized) + serialized);
        CodedOutputStream codedOutput = CodedOutputStream.newInstance(output, bufferSize);
        codedOutput.writeRawVarint32(serialized);
        writeTo(codedOutput);
        codedOutput.flush();
    }

    UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException(this);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/AbstractMessageLite$Builder.class */
    public static abstract class Builder<BuilderType extends Builder> implements MessageLite.Builder {
        @Override // 
        /* renamed from: clone */
        public abstract BuilderType mo3702clone();

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
        public abstract BuilderType mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/AbstractMessageLite$Builder$LimitedInputStream.class */
        static final class LimitedInputStream extends FilterInputStream {
            private int limit;

            LimitedInputStream(InputStream in, int limit) {
                super(in);
                this.limit = limit;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int available() throws IOException {
                return Math.min(super.available(), this.limit);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read() throws IOException {
                if (this.limit <= 0) {
                    return -1;
                }
                int result = super.read();
                if (result >= 0) {
                    this.limit--;
                }
                return result;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] b, int off, int len) throws IOException {
                if (this.limit <= 0) {
                    return -1;
                }
                int result = super.read(b, off, Math.min(len, this.limit));
                if (result >= 0) {
                    this.limit -= result;
                }
                return result;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public long skip(long n) throws IOException {
                long result = super.skip(Math.min(n, this.limit));
                if (result >= 0) {
                    this.limit = (int) (this.limit - result);
                }
                return result;
            }
        }

        protected static UninitializedMessageException newUninitializedMessageException(MessageLite message) {
            return new UninitializedMessageException(message);
        }
    }
}
