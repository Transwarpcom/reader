package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/LazyStringList.class */
public interface LazyStringList extends ProtocolStringList {
    ByteString getByteString(int i);

    void add(ByteString byteString);

    List<?> getUnderlyingElements();

    LazyStringList getUnmodifiableView();
}
