package cn.hutool.core.util;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/util/SerializeUtil.class */
public class SerializeUtil {
    public static <T> T clone(T t) {
        if (false == (t instanceof Serializable)) {
            return null;
        }
        return (T) deserialize(serialize(t));
    }

    public static <T> byte[] serialize(T obj) throws IOException, IORuntimeException {
        if (false == (obj instanceof Serializable)) {
            return null;
        }
        FastByteArrayOutputStream byteOut = new FastByteArrayOutputStream();
        IoUtil.writeObjects(byteOut, false, (Serializable) obj);
        return byteOut.toByteArray();
    }

    public static <T> T deserialize(byte[] bArr) {
        return (T) IoUtil.readObj(new ByteArrayInputStream(bArr));
    }
}
