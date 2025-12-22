package kotlin.reflect.jvm.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: RuntimeTypeMapper.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��\u000e\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010��\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"signature", "", "Ljava/lang/reflect/Method;", "getSignature", "(Ljava/lang/reflect/Method;)Ljava/lang/String;", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/RuntimeTypeMapperKt.class */
public final class RuntimeTypeMapperKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String getSignature(Method $this$signature) {
        StringBuilder sbAppend = new StringBuilder().append($this$signature.getName());
        Class<?>[] parameterTypes = $this$signature.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes, "parameterTypes");
        StringBuilder sbAppend2 = sbAppend.append(ArraysKt.joinToString$default(parameterTypes, "", "(", ")", 0, (CharSequence) null, new Function1<Class<?>, CharSequence>() { // from class: kotlin.reflect.jvm.internal.RuntimeTypeMapperKt$signature$1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final CharSequence invoke(Class<?> it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                return ReflectClassUtilKt.getDesc(it);
            }
        }, 24, (Object) null));
        Class<?> returnType = $this$signature.getReturnType();
        Intrinsics.checkNotNullExpressionValue(returnType, "returnType");
        return sbAppend2.append(ReflectClassUtilKt.getDesc(returnType)).toString();
    }
}
