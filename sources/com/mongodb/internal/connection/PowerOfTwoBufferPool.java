package com.mongodb.internal.connection;

import com.mongodb.connection.BufferProvider;
import com.mongodb.internal.connection.ConcurrentPool;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;
import org.bson.ByteBuf;
import org.bson.ByteBufNIO;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/PowerOfTwoBufferPool.class */
public class PowerOfTwoBufferPool implements BufferProvider {
    private final Map<Integer, ConcurrentPool<ByteBuffer>> powerOfTwoToPoolMap;

    public PowerOfTwoBufferPool() {
        this(24);
    }

    public PowerOfTwoBufferPool(int highestPowerOfTwo) {
        this.powerOfTwoToPoolMap = new HashMap();
        int powerOfTwo = 1;
        for (int i = 0; i <= highestPowerOfTwo; i++) {
            final int size = powerOfTwo;
            this.powerOfTwoToPoolMap.put(Integer.valueOf(i), new ConcurrentPool<>(Integer.MAX_VALUE, new ConcurrentPool.ItemFactory<ByteBuffer>() { // from class: com.mongodb.internal.connection.PowerOfTwoBufferPool.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.mongodb.internal.connection.ConcurrentPool.ItemFactory
                public ByteBuffer create(boolean initialize) {
                    return PowerOfTwoBufferPool.this.createNew(size);
                }

                @Override // com.mongodb.internal.connection.ConcurrentPool.ItemFactory
                public void close(ByteBuffer byteBuffer) {
                }

                @Override // com.mongodb.internal.connection.ConcurrentPool.ItemFactory
                public ConcurrentPool.Prune shouldPrune(ByteBuffer byteBuffer) {
                    return ConcurrentPool.Prune.STOP;
                }
            }));
            powerOfTwo <<= 1;
        }
    }

    @Override // com.mongodb.connection.BufferProvider
    public ByteBuf getBuffer(int size) {
        ConcurrentPool<ByteBuffer> pool = this.powerOfTwoToPoolMap.get(Integer.valueOf(log2(roundUpToNextHighestPowerOfTwo(size))));
        ByteBuffer byteBuffer = pool == null ? createNew(size) : pool.get();
        byteBuffer.clear();
        byteBuffer.limit(size);
        return new PooledByteBufNIO(byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ByteBuffer createNew(int size) {
        ByteBuffer buf = ByteBuffer.allocate(size);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        return buf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release(ByteBuffer buffer) {
        ConcurrentPool<ByteBuffer> pool = this.powerOfTwoToPoolMap.get(Integer.valueOf(log2(roundUpToNextHighestPowerOfTwo(buffer.capacity()))));
        if (pool != null) {
            pool.release(buffer);
        }
    }

    static int log2(int powerOfTwo) {
        return 31 - Integer.numberOfLeadingZeros(powerOfTwo);
    }

    static int roundUpToNextHighestPowerOfTwo(int size) {
        int v = size - 1;
        int v2 = v | (v >> 1);
        int v3 = v2 | (v2 >> 2);
        int v4 = v3 | (v3 >> 4);
        int v5 = v4 | (v4 >> 8);
        return (v5 | (v5 >> 16)) + 1;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/PowerOfTwoBufferPool$PooledByteBufNIO.class */
    private class PooledByteBufNIO extends ByteBufNIO {
        PooledByteBufNIO(ByteBuffer buf) {
            super(buf);
        }

        @Override // org.bson.ByteBufNIO, org.bson.ByteBuf
        public void release() {
            ByteBuffer wrapped = asNIO();
            super.release();
            if (getReferenceCount() == 0) {
                PowerOfTwoBufferPool.this.release(wrapped);
            }
        }
    }
}
