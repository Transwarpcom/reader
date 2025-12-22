package org.bson.types;

import cn.hutool.core.text.StrPool;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;
import org.bson.assertions.Assertions;
import org.bson.diagnostics.Logger;
import org.bson.diagnostics.Loggers;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/types/ObjectId.class */
public final class ObjectId implements Comparable<ObjectId>, Serializable {
    private static final long serialVersionUID = 3670079982654483072L;
    private static final int LOW_ORDER_THREE_BYTES = 16777215;
    private static final int MACHINE_IDENTIFIER;
    private static final short PROCESS_IDENTIFIER;
    private final int timestamp;
    private final int machineIdentifier;
    private final short processIdentifier;
    private final int counter;
    static final Logger LOGGER = Loggers.getLogger("ObjectId");
    private static final AtomicInteger NEXT_COUNTER = new AtomicInteger(new SecureRandom().nextInt());
    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    static {
        try {
            MACHINE_IDENTIFIER = createMachineIdentifier();
            PROCESS_IDENTIFIER = createProcessIdentifier();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectId get() {
        return new ObjectId();
    }

    public static boolean isValid(String hexString) {
        if (hexString == null) {
            throw new IllegalArgumentException();
        }
        int len = hexString.length();
        if (len != 24) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            char c = hexString.charAt(i);
            if ((c < '0' || c > '9') && ((c < 'a' || c > 'f') && (c < 'A' || c > 'F'))) {
                return false;
            }
        }
        return true;
    }

    public static int getGeneratedMachineIdentifier() {
        return MACHINE_IDENTIFIER;
    }

    public static int getGeneratedProcessIdentifier() {
        return PROCESS_IDENTIFIER;
    }

    public static int getCurrentCounter() {
        return NEXT_COUNTER.get();
    }

    public static ObjectId createFromLegacyFormat(int time, int machine, int inc) {
        return new ObjectId(time, machine, inc);
    }

    public ObjectId() {
        this(new Date());
    }

    public ObjectId(Date date) {
        this(dateToTimestampSeconds(date), MACHINE_IDENTIFIER, PROCESS_IDENTIFIER, NEXT_COUNTER.getAndIncrement(), false);
    }

    public ObjectId(Date date, int counter) {
        this(date, MACHINE_IDENTIFIER, PROCESS_IDENTIFIER, counter);
    }

    public ObjectId(Date date, int machineIdentifier, short processIdentifier, int counter) {
        this(dateToTimestampSeconds(date), machineIdentifier, processIdentifier, counter);
    }

    public ObjectId(int timestamp, int machineIdentifier, short processIdentifier, int counter) {
        this(timestamp, machineIdentifier, processIdentifier, counter, true);
    }

    private ObjectId(int timestamp, int machineIdentifier, short processIdentifier, int counter, boolean checkCounter) {
        if ((machineIdentifier & (-16777216)) != 0) {
            throw new IllegalArgumentException("The machine identifier must be between 0 and 16777215 (it must fit in three bytes).");
        }
        if (checkCounter && (counter & (-16777216)) != 0) {
            throw new IllegalArgumentException("The counter must be between 0 and 16777215 (it must fit in three bytes).");
        }
        this.timestamp = timestamp;
        this.machineIdentifier = machineIdentifier;
        this.processIdentifier = processIdentifier;
        this.counter = counter & 16777215;
    }

    public ObjectId(String hexString) {
        this(parseHexString(hexString));
    }

    public ObjectId(byte[] bytes) {
        this(ByteBuffer.wrap((byte[]) Assertions.notNull("bytes", bytes)));
    }

    ObjectId(int timestamp, int machineAndProcessIdentifier, int counter) {
        this(legacyToBytes(timestamp, machineAndProcessIdentifier, counter));
    }

    public ObjectId(ByteBuffer buffer) {
        Assertions.notNull("buffer", buffer);
        Assertions.isTrueArgument("buffer.remaining() >=12", buffer.remaining() >= 12);
        this.timestamp = makeInt(buffer.get(), buffer.get(), buffer.get(), buffer.get());
        this.machineIdentifier = makeInt((byte) 0, buffer.get(), buffer.get(), buffer.get());
        this.processIdentifier = (short) makeInt((byte) 0, (byte) 0, buffer.get(), buffer.get());
        this.counter = makeInt((byte) 0, buffer.get(), buffer.get(), buffer.get());
    }

    private static byte[] legacyToBytes(int timestamp, int machineAndProcessIdentifier, int counter) {
        byte[] bytes = {int3(timestamp), int2(timestamp), int1(timestamp), int0(timestamp), int3(machineAndProcessIdentifier), int2(machineAndProcessIdentifier), int1(machineAndProcessIdentifier), int0(machineAndProcessIdentifier), int3(counter), int2(counter), int1(counter), int0(counter)};
        return bytes;
    }

    public byte[] toByteArray() {
        ByteBuffer buffer = ByteBuffer.allocate(12);
        putToByteBuffer(buffer);
        return buffer.array();
    }

    public void putToByteBuffer(ByteBuffer buffer) {
        Assertions.notNull("buffer", buffer);
        Assertions.isTrueArgument("buffer.remaining() >=12", buffer.remaining() >= 12);
        buffer.put(int3(this.timestamp));
        buffer.put(int2(this.timestamp));
        buffer.put(int1(this.timestamp));
        buffer.put(int0(this.timestamp));
        buffer.put(int2(this.machineIdentifier));
        buffer.put(int1(this.machineIdentifier));
        buffer.put(int0(this.machineIdentifier));
        buffer.put(short1(this.processIdentifier));
        buffer.put(short0(this.processIdentifier));
        buffer.put(int2(this.counter));
        buffer.put(int1(this.counter));
        buffer.put(int0(this.counter));
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public int getMachineIdentifier() {
        return this.machineIdentifier;
    }

    public short getProcessIdentifier() {
        return this.processIdentifier;
    }

    public int getCounter() {
        return this.counter;
    }

    public Date getDate() {
        return new Date(this.timestamp * 1000);
    }

    public String toHexString() {
        char[] chars = new char[24];
        int i = 0;
        for (byte b : toByteArray()) {
            int i2 = i;
            int i3 = i + 1;
            chars[i2] = HEX_CHARS[(b >> 4) & 15];
            i = i3 + 1;
            chars[i3] = HEX_CHARS[b & 15];
        }
        return new String(chars);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjectId objectId = (ObjectId) o;
        if (this.counter != objectId.counter || this.machineIdentifier != objectId.machineIdentifier || this.processIdentifier != objectId.processIdentifier || this.timestamp != objectId.timestamp) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.timestamp;
        return (31 * ((31 * ((31 * result) + this.machineIdentifier)) + this.processIdentifier)) + this.counter;
    }

    @Override // java.lang.Comparable
    public int compareTo(ObjectId other) {
        if (other == null) {
            throw new NullPointerException();
        }
        byte[] byteArray = toByteArray();
        byte[] otherByteArray = other.toByteArray();
        for (int i = 0; i < 12; i++) {
            if (byteArray[i] != otherByteArray[i]) {
                return (byteArray[i] & 255) < (otherByteArray[i] & 255) ? -1 : 1;
            }
        }
        return 0;
    }

    public String toString() {
        return toHexString();
    }

    @Deprecated
    public int getTimeSecond() {
        return this.timestamp;
    }

    @Deprecated
    public long getTime() {
        return this.timestamp * 1000;
    }

    @Deprecated
    public String toStringMongod() {
        return toHexString();
    }

    private static int createMachineIdentifier() {
        int machinePiece;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface ni = e.nextElement();
                sb.append(ni.toString());
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    ByteBuffer bb = ByteBuffer.wrap(mac);
                    try {
                        sb.append(bb.getChar());
                        sb.append(bb.getChar());
                        sb.append(bb.getChar());
                    } catch (BufferUnderflowException e2) {
                    }
                }
            }
            machinePiece = sb.toString().hashCode();
        } catch (Throwable th) {
            machinePiece = new SecureRandom().nextInt();
            LOGGER.debug("Failed to get machine identifier from network interface, using SecureRandom instead");
        }
        return machinePiece & 16777215;
    }

    private static short createProcessIdentifier() {
        short processId;
        try {
            String processName = ManagementFactory.getRuntimeMXBean().getName();
            if (processName.contains(StrPool.AT)) {
                processId = (short) Integer.parseInt(processName.substring(0, processName.indexOf(64)));
            } else {
                processId = (short) ManagementFactory.getRuntimeMXBean().getName().hashCode();
            }
        } catch (Throwable th) {
            processId = (short) new SecureRandom().nextInt();
            LOGGER.debug("Failed to get process identifier from JMX, using SecureRandom instead");
        }
        return processId;
    }

    private static byte[] parseHexString(String s) {
        if (!isValid(s)) {
            throw new IllegalArgumentException("invalid hexadecimal representation of an ObjectId: [" + s + "]");
        }
        byte[] b = new byte[12];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) Integer.parseInt(s.substring(i * 2, (i * 2) + 2), 16);
        }
        return b;
    }

    private static int dateToTimestampSeconds(Date time) {
        return (int) (time.getTime() / 1000);
    }

    private static int makeInt(byte b3, byte b2, byte b1, byte b0) {
        return (b3 << 24) | ((b2 & 255) << 16) | ((b1 & 255) << 8) | (b0 & 255);
    }

    private static byte int3(int x) {
        return (byte) (x >> 24);
    }

    private static byte int2(int x) {
        return (byte) (x >> 16);
    }

    private static byte int1(int x) {
        return (byte) (x >> 8);
    }

    private static byte int0(int x) {
        return (byte) x;
    }

    private static byte short1(short x) {
        return (byte) (x >> 8);
    }

    private static byte short0(short x) {
        return (byte) x;
    }
}
