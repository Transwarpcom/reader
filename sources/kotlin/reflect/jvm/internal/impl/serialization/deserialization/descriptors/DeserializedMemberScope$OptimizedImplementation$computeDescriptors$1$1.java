package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.io.ByteArrayInputStream;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [M] */
/* compiled from: DeserializedMemberScope.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/descriptors/DeserializedMemberScope$OptimizedImplementation$computeDescriptors$1$1.class */
final class DeserializedMemberScope$OptimizedImplementation$computeDescriptors$1$1<M> extends Lambda implements Function0<M> {
    final /* synthetic */ Parser<M> $parser;
    final /* synthetic */ ByteArrayInputStream $inputStream;
    final /* synthetic */ DeserializedMemberScope this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeserializedMemberScope$OptimizedImplementation$computeDescriptors$1$1(Parser<M> parser, ByteArrayInputStream $inputStream, DeserializedMemberScope $receiver) {
        super(0);
        this.$parser = parser;
        this.$inputStream = $inputStream;
        this.this$0 = $receiver;
    }

    /* JADX WARN: Incorrect return type in method signature: ()TM; */
    @Override // kotlin.jvm.functions.Function0
    @Nullable
    public final MessageLite invoke() {
        return (MessageLite) this.$parser.parseDelimitedFrom(this.$inputStream, this.this$0.getC().getComponents().getExtensionRegistryLite());
    }
}
