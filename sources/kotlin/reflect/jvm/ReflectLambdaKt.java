package kotlin.reflect.jvm;

import java.io.IOException;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.reflect.jvm.internal.EmptyContainerForLocal;
import kotlin.reflect.jvm.internal.KFunctionImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: reflectLambda.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a \u0010��\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007¨\u0006\u0004"}, d2 = {"reflect", "Lkotlin/reflect/KFunction;", "R", "Lkotlin/Function;", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/ReflectLambdaKt.class */
public final class ReflectLambdaKt {
    @ExperimentalReflectionOnLambdas
    @Nullable
    public static final <R> KFunction<R> reflect(@NotNull Function<? extends R> reflect) throws IOException {
        Intrinsics.checkNotNullParameter(reflect, "$this$reflect");
        Metadata annotation = (Metadata) reflect.getClass().getAnnotation(Metadata.class);
        if (annotation == null) {
            return null;
        }
        String[] p1 = annotation.d1();
        String[] strArr = !(p1.length == 0) ? p1 : null;
        if (strArr == null) {
            return null;
        }
        String[] data = strArr;
        Pair<JvmNameResolver, ProtoBuf.Function> functionDataFrom = JvmProtoBufUtil.readFunctionDataFrom(data, annotation.d2());
        JvmNameResolver nameResolver = functionDataFrom.component1();
        ProtoBuf.Function proto = functionDataFrom.component2();
        JvmMetadataVersion metadataVersion = new JvmMetadataVersion(annotation.mv(), (annotation.xi() & 8) != 0);
        ProtoBuf.TypeTable typeTable = proto.getTypeTable();
        Intrinsics.checkNotNullExpressionValue(typeTable, "proto.typeTable");
        SimpleFunctionDescriptor descriptor = (SimpleFunctionDescriptor) UtilKt.deserializeToDescriptor(reflect.getClass(), proto, nameResolver, new TypeTable(typeTable), metadataVersion, ReflectLambdaKt$reflect$descriptor$1.INSTANCE);
        if (descriptor == null) {
            return null;
        }
        return new KFunctionImpl(EmptyContainerForLocal.INSTANCE, descriptor);
    }
}
