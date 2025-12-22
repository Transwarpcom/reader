package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ImplicitClassReceiver.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/ImplicitClassReceiver.class */
public class ImplicitClassReceiver implements ImplicitReceiver, ThisClassReceiver {

    @NotNull
    private final ClassDescriptor classDescriptor;

    @NotNull
    private final ImplicitClassReceiver original;

    @NotNull
    private final ClassDescriptor declarationDescriptor;

    public ImplicitClassReceiver(@NotNull ClassDescriptor classDescriptor, @Nullable ImplicitClassReceiver original) {
        Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
        this.classDescriptor = classDescriptor;
        this.original = original == null ? this : original;
        this.declarationDescriptor = this.classDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ThisClassReceiver
    @NotNull
    public final ClassDescriptor getClassDescriptor() {
        return this.classDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
    @NotNull
    public SimpleType getType() {
        SimpleType defaultType = this.classDescriptor.getDefaultType();
        Intrinsics.checkNotNullExpressionValue(defaultType, "classDescriptor.defaultType");
        return defaultType;
    }

    public boolean equals(@Nullable Object other) {
        ClassDescriptor classDescriptor = this.classDescriptor;
        ImplicitClassReceiver implicitClassReceiver = other instanceof ImplicitClassReceiver ? (ImplicitClassReceiver) other : null;
        return Intrinsics.areEqual(classDescriptor, implicitClassReceiver == null ? null : implicitClassReceiver.classDescriptor);
    }

    public int hashCode() {
        return this.classDescriptor.hashCode();
    }

    @NotNull
    public String toString() {
        return "Class{" + getType() + '}';
    }
}
