package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: KClassifierImpl.kt */
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018��2\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/reflect/jvm/internal/KClassifierImpl;", "", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassifierDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ClassifierDescriptor;", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/KClassifierImpl.class */
public interface KClassifierImpl {
    @NotNull
    ClassifierDescriptor getDescriptor();
}
