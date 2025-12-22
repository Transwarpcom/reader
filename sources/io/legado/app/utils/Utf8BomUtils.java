package io.legado.app.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* compiled from: Utf8BomUtils.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u0012\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lio/legado/app/utils/Utf8BomUtils;", "", "()V", "UTF8_BOM_BYTES", "", "hasBom", "", "bytes", "removeUTF8BOM", "", "xmlText", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/utils/Utf8BomUtils.class */
public final class Utf8BomUtils {

    @NotNull
    public static final Utf8BomUtils INSTANCE = new Utf8BomUtils();

    @NotNull
    private static final byte[] UTF8_BOM_BYTES = {-17, -69, -65};

    private Utf8BomUtils() {
    }

    @NotNull
    public final String removeUTF8BOM(@NotNull String xmlText) {
        Intrinsics.checkNotNullParameter(xmlText, "xmlText");
        byte[] bytes = xmlText.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        boolean containsBOM = bytes.length > 3 && bytes[0] == UTF8_BOM_BYTES[0] && bytes[1] == UTF8_BOM_BYTES[1] && bytes[2] == UTF8_BOM_BYTES[2];
        if (containsBOM) {
            return new String(bytes, 3, bytes.length - 3, Charsets.UTF_8);
        }
        return xmlText;
    }

    @NotNull
    public final byte[] removeUTF8BOM(@NotNull byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        boolean containsBOM = bytes.length > 3 && bytes[0] == UTF8_BOM_BYTES[0] && bytes[1] == UTF8_BOM_BYTES[1] && bytes[2] == UTF8_BOM_BYTES[2];
        if (containsBOM) {
            byte[] copy = new byte[bytes.length - 3];
            System.arraycopy(bytes, 3, copy, 0, bytes.length - 3);
            return copy;
        }
        return bytes;
    }

    public final boolean hasBom(@NotNull byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return bytes.length > 3 && bytes[0] == UTF8_BOM_BYTES[0] && bytes[1] == UTF8_BOM_BYTES[1] && bytes[2] == UTF8_BOM_BYTES[2];
    }
}
