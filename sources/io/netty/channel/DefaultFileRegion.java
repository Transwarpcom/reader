package io.netty.channel;

import io.netty.util.AbstractReferenceCounted;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/DefaultFileRegion.class */
public class DefaultFileRegion extends AbstractReferenceCounted implements FileRegion {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultFileRegion.class);
    private final File f;
    private final long position;
    private final long count;
    private long transferred;
    private FileChannel file;

    public DefaultFileRegion(FileChannel file, long position, long count) {
        if (file == null) {
            throw new NullPointerException("file");
        }
        ObjectUtil.checkPositiveOrZero(position, "position");
        ObjectUtil.checkPositiveOrZero(count, "count");
        this.file = file;
        this.position = position;
        this.count = count;
        this.f = null;
    }

    public DefaultFileRegion(File f, long position, long count) {
        if (f == null) {
            throw new NullPointerException(OperatorName.FILL_NON_ZERO);
        }
        ObjectUtil.checkPositiveOrZero(position, "position");
        ObjectUtil.checkPositiveOrZero(count, "count");
        this.position = position;
        this.count = count;
        this.f = f;
    }

    public boolean isOpen() {
        return this.file != null;
    }

    public void open() throws IOException {
        if (!isOpen() && refCnt() > 0) {
            this.file = new RandomAccessFile(this.f, PDPageLabelRange.STYLE_ROMAN_LOWER).getChannel();
        }
    }

    @Override // io.netty.channel.FileRegion
    public long position() {
        return this.position;
    }

    @Override // io.netty.channel.FileRegion
    public long count() {
        return this.count;
    }

    @Override // io.netty.channel.FileRegion
    @Deprecated
    public long transfered() {
        return this.transferred;
    }

    @Override // io.netty.channel.FileRegion
    public long transferred() {
        return this.transferred;
    }

    @Override // io.netty.channel.FileRegion
    public long transferTo(WritableByteChannel target, long position) throws IOException {
        long count = this.count - position;
        if (count < 0 || position < 0) {
            throw new IllegalArgumentException("position out of range: " + position + " (expected: 0 - " + (this.count - 1) + ')');
        }
        if (count == 0) {
            return 0L;
        }
        if (refCnt() == 0) {
            throw new IllegalReferenceCountException(0);
        }
        open();
        long written = this.file.transferTo(this.position + position, count, target);
        if (written > 0) {
            this.transferred += written;
        } else if (written == 0) {
            validate(this, position);
        }
        return written;
    }

    @Override // io.netty.util.AbstractReferenceCounted
    protected void deallocate() {
        FileChannel file = this.file;
        if (file == null) {
            return;
        }
        this.file = null;
        try {
            file.close();
        } catch (IOException e) {
            logger.warn("Failed to close a file.", (Throwable) e);
        }
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public FileRegion retain() {
        super.retain();
        return this;
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public FileRegion retain(int increment) {
        super.retain(increment);
        return this;
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public FileRegion touch() {
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public FileRegion touch(Object hint) {
        return this;
    }

    static void validate(DefaultFileRegion region, long position) throws IOException {
        long size = region.file.size();
        long count = region.count - position;
        if (region.position + count + position > size) {
            throw new IOException("Underlying file size " + size + " smaller then requested count " + region.count);
        }
    }
}
