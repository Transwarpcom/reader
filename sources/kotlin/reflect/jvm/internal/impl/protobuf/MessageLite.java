package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/MessageLite.class */
public interface MessageLite extends MessageLiteOrBuilder {

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/MessageLite$Builder.class */
    public interface Builder extends Cloneable, MessageLiteOrBuilder {
        MessageLite build();

        Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;
    }

    void writeTo(CodedOutputStream codedOutputStream) throws IOException;

    int getSerializedSize();

    Parser<? extends MessageLite> getParserForType();

    Builder newBuilderForType();

    Builder toBuilder();
}
