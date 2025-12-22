package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpConstants;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/multipart/AbstractMemoryHttpData.class */
public abstract class AbstractMemoryHttpData extends AbstractHttpData {
    private ByteBuf byteBuf;
    private int chunkPosition;

    protected AbstractMemoryHttpData(String name, Charset charset, long size) {
        super(name, charset, size);
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void setContent(ByteBuf buffer) throws IOException {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        long localsize = buffer.readableBytes();
        checkSize(localsize);
        if (this.definedSize > 0 && this.definedSize < localsize) {
            throw new IOException("Out of size: " + localsize + " > " + this.definedSize);
        }
        if (this.byteBuf != null) {
            this.byteBuf.release();
        }
        this.byteBuf = buffer;
        this.size = localsize;
        setCompleted();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void setContent(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new NullPointerException("inputStream");
        }
        ByteBuf buffer = Unpooled.buffer();
        byte[] bytes = new byte[16384];
        int read = inputStream.read(bytes);
        int written = 0;
        while (read > 0) {
            buffer.writeBytes(bytes, 0, read);
            written += read;
            checkSize(written);
            read = inputStream.read(bytes);
        }
        this.size = written;
        if (this.definedSize > 0 && this.definedSize < this.size) {
            throw new IOException("Out of size: " + this.size + " > " + this.definedSize);
        }
        if (this.byteBuf != null) {
            this.byteBuf.release();
        }
        this.byteBuf = buffer;
        setCompleted();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void addContent(ByteBuf buffer, boolean last) throws IOException {
        if (buffer != null) {
            long localsize = buffer.readableBytes();
            checkSize(this.size + localsize);
            if (this.definedSize > 0 && this.definedSize < this.size + localsize) {
                throw new IOException("Out of size: " + (this.size + localsize) + " > " + this.definedSize);
            }
            this.size += localsize;
            if (this.byteBuf == null) {
                this.byteBuf = buffer;
            } else if (this.byteBuf instanceof CompositeByteBuf) {
                ((CompositeByteBuf) this.byteBuf).addComponent(true, buffer);
            } else {
                CompositeByteBuf cbb = Unpooled.compositeBuffer(Integer.MAX_VALUE);
                cbb.addComponents(true, this.byteBuf, buffer);
                this.byteBuf = cbb;
            }
        }
        if (last) {
            setCompleted();
        } else if (buffer == null) {
            throw new NullPointerException("buffer");
        }
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void setContent(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("file");
        }
        long newsize = file.length();
        if (newsize > 2147483647L) {
            throw new IllegalArgumentException("File too big to be loaded in memory");
        }
        checkSize(newsize);
        RandomAccessFile accessFile = new RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER);
        FileChannel fileChannel = accessFile.getChannel();
        byte[] array = new byte[(int) newsize];
        ByteBuffer byteBuffer = ByteBuffer.wrap(array);
        int i = 0;
        while (true) {
            int read = i;
            if (read >= newsize) {
                break;
            } else {
                i = read + fileChannel.read(byteBuffer);
            }
        }
        fileChannel.close();
        accessFile.close();
        byteBuffer.flip();
        if (this.byteBuf != null) {
            this.byteBuf.release();
        }
        this.byteBuf = Unpooled.wrappedBuffer(Integer.MAX_VALUE, byteBuffer);
        this.size = newsize;
        setCompleted();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void delete() {
        if (this.byteBuf != null) {
            this.byteBuf.release();
            this.byteBuf = null;
        }
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public byte[] get() {
        if (this.byteBuf == null) {
            return Unpooled.EMPTY_BUFFER.array();
        }
        byte[] array = new byte[this.byteBuf.readableBytes()];
        this.byteBuf.getBytes(this.byteBuf.readerIndex(), array);
        return array;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public String getString() {
        return getString(HttpConstants.DEFAULT_CHARSET);
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public String getString(Charset encoding) {
        if (this.byteBuf == null) {
            return "";
        }
        if (encoding == null) {
            encoding = HttpConstants.DEFAULT_CHARSET;
        }
        return this.byteBuf.toString(encoding);
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public ByteBuf getByteBuf() {
        return this.byteBuf;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public ByteBuf getChunk(int length) throws IOException {
        if (this.byteBuf == null || length == 0 || this.byteBuf.readableBytes() == 0) {
            this.chunkPosition = 0;
            return Unpooled.EMPTY_BUFFER;
        }
        int sizeLeft = this.byteBuf.readableBytes() - this.chunkPosition;
        if (sizeLeft == 0) {
            this.chunkPosition = 0;
            return Unpooled.EMPTY_BUFFER;
        }
        int sliceLength = length;
        if (sizeLeft < length) {
            sliceLength = sizeLeft;
        }
        ByteBuf chunk = this.byteBuf.retainedSlice(this.chunkPosition, sliceLength);
        this.chunkPosition += sliceLength;
        return chunk;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public boolean isInMemory() {
        return true;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public boolean renameTo(File dest) throws IOException {
        if (dest == null) {
            throw new NullPointerException("dest");
        }
        if (this.byteBuf == null) {
            if (!dest.createNewFile()) {
                throw new IOException("file exists already: " + dest);
            }
            return true;
        }
        int length = this.byteBuf.readableBytes();
        RandomAccessFile accessFile = new RandomAccessFile(dest, "rw");
        FileChannel fileChannel = accessFile.getChannel();
        int written = 0;
        if (this.byteBuf.nioBufferCount() == 1) {
            ByteBuffer byteBuffer = this.byteBuf.nioBuffer();
            while (written < length) {
                written += fileChannel.write(byteBuffer);
            }
        } else {
            ByteBuffer[] byteBuffers = this.byteBuf.nioBuffers();
            while (written < length) {
                written = (int) (written + fileChannel.write(byteBuffers));
            }
        }
        fileChannel.force(false);
        fileChannel.close();
        accessFile.close();
        return written == length;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public File getFile() throws IOException {
        throw new IOException("Not represented by a file");
    }

    @Override // io.netty.handler.codec.http.multipart.AbstractHttpData, io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public HttpData touch() {
        return touch((Object) null);
    }

    @Override // io.netty.handler.codec.http.multipart.AbstractHttpData, io.netty.util.ReferenceCounted
    public HttpData touch(Object hint) {
        if (this.byteBuf != null) {
            this.byteBuf.touch(hint);
        }
        return this;
    }
}
