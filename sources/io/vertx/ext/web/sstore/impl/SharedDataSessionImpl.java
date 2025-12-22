package io.vertx.ext.web.sstore.impl;

import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.shareddata.Shareable;
import io.vertx.core.shareddata.impl.ClusterSerializable;
import io.vertx.ext.auth.PRNG;
import io.vertx.ext.web.impl.Utils;
import io.vertx.ext.web.sstore.AbstractSession;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/sstore/impl/SharedDataSessionImpl.class */
public class SharedDataSessionImpl extends AbstractSession implements ClusterSerializable, Shareable {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final byte TYPE_LONG = 1;
    private static final byte TYPE_INT = 2;
    private static final byte TYPE_SHORT = 3;
    private static final byte TYPE_BYTE = 4;
    private static final byte TYPE_DOUBLE = 5;
    private static final byte TYPE_FLOAT = 6;
    private static final byte TYPE_CHAR = 7;
    private static final byte TYPE_BOOLEAN = 8;
    private static final byte TYPE_STRING = 9;
    private static final byte TYPE_BUFFER = 10;
    private static final byte TYPE_BYTES = 11;
    private static final byte TYPE_CLUSTER_SERIALIZABLE = 13;

    public SharedDataSessionImpl() {
    }

    public SharedDataSessionImpl(PRNG random) {
        super(random);
    }

    public SharedDataSessionImpl(PRNG random, long timeout, int length) {
        super(random, timeout, length);
    }

    @Override // io.vertx.core.shareddata.impl.ClusterSerializable
    public void writeToBuffer(Buffer buff) {
        byte[] bytes = id().getBytes(UTF8);
        buff.appendInt(bytes.length).appendBytes(bytes);
        buff.appendLong(timeout());
        buff.appendLong(lastAccessed());
        buff.appendInt(version());
        Buffer dataBuf = writeDataToBuffer();
        buff.appendBuffer(dataBuf);
    }

    @Override // io.vertx.core.shareddata.impl.ClusterSerializable
    public int readFromBuffer(int pos, Buffer buffer) {
        int len = buffer.getInt(pos);
        int pos2 = pos + 4;
        byte[] bytes = buffer.getBytes(pos2, pos2 + len);
        int pos3 = pos2 + len;
        setId(new String(bytes, UTF8));
        setTimeout(buffer.getLong(pos3));
        int pos4 = pos3 + 8;
        setLastAccessed(buffer.getLong(pos4));
        int pos5 = pos4 + 8;
        setVersion(buffer.getInt(pos5));
        return readDataFromBuffer(pos5 + 4, buffer);
    }

    private Buffer writeDataToBuffer() {
        Buffer buffer = Buffer.buffer();
        if (isEmpty()) {
            buffer.appendInt(0);
        } else {
            Map<String, Object> data = data();
            buffer.appendInt(data.size());
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String key = entry.getKey();
                byte[] keyBytes = key.getBytes(UTF8);
                buffer.appendInt(keyBytes.length).appendBytes(keyBytes);
                Object val = entry.getValue();
                if (val instanceof Long) {
                    buffer.appendByte((byte) 1).appendLong(((Long) val).longValue());
                } else if (val instanceof Integer) {
                    buffer.appendByte((byte) 2).appendInt(((Integer) val).intValue());
                } else if (val instanceof Short) {
                    buffer.appendByte((byte) 3).appendShort(((Short) val).shortValue());
                } else if (val instanceof Byte) {
                    buffer.appendByte((byte) 4).appendByte(((Byte) val).byteValue());
                } else if (val instanceof Double) {
                    buffer.appendByte((byte) 5).appendDouble(((Double) val).doubleValue());
                } else if (val instanceof Float) {
                    buffer.appendByte((byte) 6).appendFloat(((Float) val).floatValue());
                } else if (val instanceof Character) {
                    buffer.appendByte((byte) 7).appendShort((short) ((Character) val).charValue());
                } else if (val instanceof Boolean) {
                    buffer.appendByte((byte) 8).appendByte((byte) (((Boolean) val).booleanValue() ? 1 : 0));
                } else if (val instanceof String) {
                    byte[] bytes = ((String) val).getBytes(UTF8);
                    buffer.appendByte((byte) 9).appendInt(bytes.length).appendBytes(bytes);
                } else if (val instanceof Buffer) {
                    Buffer buff = (Buffer) val;
                    buffer.appendByte((byte) 10).appendInt(buff.length()).appendBuffer(buff);
                } else if (val instanceof byte[]) {
                    byte[] bytes2 = (byte[]) val;
                    buffer.appendByte((byte) 11).appendInt(bytes2.length).appendBytes(bytes2);
                } else if (val instanceof ClusterSerializable) {
                    buffer.appendByte((byte) 13);
                    String className = val.getClass().getName();
                    byte[] classNameBytes = className.getBytes(UTF8);
                    buffer.appendInt(classNameBytes.length).appendBytes(classNameBytes);
                    ((ClusterSerializable) val).writeToBuffer(buffer);
                } else if (val != null) {
                    throw new IllegalStateException("Invalid type for data in session: " + val.getClass());
                }
            }
        }
        return buffer;
    }

    private int readDataFromBuffer(int pos, Buffer buffer) throws ClassNotFoundException {
        Object val;
        try {
            int entries = buffer.getInt(pos);
            int pos2 = pos + 4;
            if (entries > 0) {
                Map<String, Object> data = new ConcurrentHashMap<>(entries);
                for (int i = 0; i < entries; i++) {
                    int keylen = buffer.getInt(pos2);
                    int pos3 = pos2 + 4;
                    byte[] keyBytes = buffer.getBytes(pos3, pos3 + keylen);
                    int pos4 = pos3 + keylen;
                    String key = new String(keyBytes, UTF8);
                    int pos5 = pos4 + 1;
                    byte type = buffer.getByte(pos4);
                    switch (type) {
                        case 1:
                            val = Long.valueOf(buffer.getLong(pos5));
                            pos2 = pos5 + 8;
                            break;
                        case 2:
                            val = Integer.valueOf(buffer.getInt(pos5));
                            pos2 = pos5 + 4;
                            break;
                        case 3:
                            val = Short.valueOf(buffer.getShort(pos5));
                            pos2 = pos5 + 2;
                            break;
                        case 4:
                            val = Byte.valueOf(buffer.getByte(pos5));
                            pos2 = pos5 + 1;
                            break;
                        case 5:
                            val = Double.valueOf(buffer.getDouble(pos5));
                            pos2 = pos5 + 8;
                            break;
                        case 6:
                            val = Float.valueOf(buffer.getFloat(pos5));
                            pos2 = pos5 + 4;
                            break;
                        case 7:
                            short s = buffer.getShort(pos5);
                            pos2 = pos5 + 2;
                            val = Character.valueOf((char) s);
                            break;
                        case 8:
                            byte b = buffer.getByte(pos5);
                            pos2 = pos5 + 1;
                            val = Boolean.valueOf(b == 1);
                            break;
                        case 9:
                            int len = buffer.getInt(pos5);
                            int pos6 = pos5 + 4;
                            byte[] bytes = buffer.getBytes(pos6, pos6 + len);
                            val = new String(bytes, UTF8);
                            pos2 = pos6 + len;
                            break;
                        case 10:
                            int len2 = buffer.getInt(pos5);
                            int pos7 = pos5 + 4;
                            byte[] bytes2 = buffer.getBytes(pos7, pos7 + len2);
                            val = Buffer.buffer(bytes2);
                            pos2 = pos7 + len2;
                            break;
                        case 11:
                            int len3 = buffer.getInt(pos5);
                            int pos8 = pos5 + 4;
                            val = buffer.getBytes(pos8, pos8 + len3);
                            pos2 = pos8 + len3;
                            break;
                        case 12:
                        default:
                            throw new IllegalStateException("Invalid serialized type: " + ((int) type));
                        case 13:
                            int classNameLen = buffer.getInt(pos5);
                            int pos9 = pos5 + 4;
                            byte[] classNameBytes = buffer.getBytes(pos9, pos9 + classNameLen);
                            int pos10 = pos9 + classNameLen;
                            String className = new String(classNameBytes, UTF8);
                            Class<?> clazz = Utils.getClassLoader().loadClass(className);
                            if (!ClusterSerializable.class.isAssignableFrom(clazz)) {
                                throw new ClassCastException(new String(classNameBytes) + " is not assignable from ClusterSerializable");
                            }
                            ClusterSerializable obj = (ClusterSerializable) clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                            pos2 = obj.readFromBuffer(pos10, buffer);
                            val = obj;
                            break;
                    }
                    data.put(key, val);
                }
                setData(data);
            }
            return pos2;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new VertxException(e);
        }
    }
}
