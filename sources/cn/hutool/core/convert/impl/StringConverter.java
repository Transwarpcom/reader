package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.XmlUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.TimeZone;
import org.w3c.dom.Node;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/convert/impl/StringConverter.class */
public class StringConverter extends AbstractConverter<String> {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.hutool.core.convert.AbstractConverter
    public String convertInternal(Object value) {
        if (value instanceof TimeZone) {
            return ((TimeZone) value).getID();
        }
        if (value instanceof Node) {
            return XmlUtil.toStr((Node) value);
        }
        if (value instanceof Clob) {
            return clobToStr((Clob) value);
        }
        if (value instanceof Blob) {
            return blobToStr((Blob) value);
        }
        if (value instanceof Type) {
            return ((Type) value).getTypeName();
        }
        return convertToStr(value);
    }

    private static String clobToStr(Clob clob) throws IOException {
        Reader reader = null;
        try {
            try {
                reader = clob.getCharacterStream();
                String str = IoUtil.read(reader);
                IoUtil.close((Closeable) reader);
                return str;
            } catch (SQLException e) {
                throw new ConvertException(e);
            }
        } catch (Throwable th) {
            IoUtil.close((Closeable) reader);
            throw th;
        }
    }

    private static String blobToStr(Blob blob) throws IOException {
        InputStream in = null;
        try {
            try {
                in = blob.getBinaryStream();
                String str = IoUtil.read(in, CharsetUtil.CHARSET_UTF_8);
                IoUtil.close((Closeable) in);
                return str;
            } catch (SQLException e) {
                throw new ConvertException(e);
            }
        } catch (Throwable th) {
            IoUtil.close((Closeable) in);
            throw th;
        }
    }
}
