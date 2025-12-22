package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: InlineClassAwareCaller.kt */
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��B\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010��\n��\n\u0002\u0010\u0011\n\u0002\b\u0003\b��\u0018��*\f\b��\u0010\u0001 \u0001*\u0004\u0018\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u001cB#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0016¢\u0006\u0002\u0010\u001bR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\f\u001a\u00028��8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001d"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller;", OperatorName.SET_LINE_MITERLIMIT, "Ljava/lang/reflect/Member;", "Lkotlin/reflect/jvm/internal/calls/Caller;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "caller", "isDefault", "", "(Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;Lkotlin/reflect/jvm/internal/calls/Caller;Z)V", "data", "Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller$BoxUnboxData;", "member", "getMember", "()Ljava/lang/reflect/Member;", "parameterTypes", "", "Ljava/lang/reflect/Type;", "getParameterTypes", "()Ljava/util/List;", "returnType", "getReturnType", "()Ljava/lang/reflect/Type;", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "BoxUnboxData", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/calls/InlineClassAwareCaller.class */
public final class InlineClassAwareCaller<M extends Member> implements Caller<M> {
    private final BoxUnboxData data;
    private final Caller<M> caller;
    private final boolean isDefault;

    /* JADX WARN: Multi-variable type inference failed */
    public InlineClassAwareCaller(@NotNull CallableMemberDescriptor descriptor, @NotNull Caller<? extends M> caller, boolean isDefault) {
        int i;
        BoxUnboxData boxUnboxData;
        Method unboxMethod;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(caller, "caller");
        this.caller = caller;
        this.isDefault = isDefault;
        InlineClassAwareCaller<M> $this$run = this;
        KotlinType returnType = descriptor.getReturnType();
        Intrinsics.checkNotNull(returnType);
        Intrinsics.checkNotNullExpressionValue(returnType, "descriptor.returnType!!");
        Class<?> inlineClass = InlineClassAwareCallerKt.toInlineClass(returnType);
        Method box = inlineClass != null ? InlineClassAwareCallerKt.getBoxMethod(inlineClass, descriptor) : null;
        if (InlineClassesUtilsKt.isGetterOfUnderlyingPropertyOfInlineClass(descriptor)) {
            boxUnboxData = new BoxUnboxData(IntRange.Companion.getEMPTY(), new Method[0], box);
        } else {
            if ($this$run.caller instanceof CallerImpl.Method.BoundStatic) {
                i = -1;
            } else if (descriptor instanceof ConstructorDescriptor) {
                i = $this$run.caller instanceof BoundCaller ? -1 : 0;
            } else if (descriptor.getDispatchReceiverParameter() != null && !($this$run.caller instanceof BoundCaller)) {
                DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
                Intrinsics.checkNotNullExpressionValue(containingDeclaration, "descriptor.containingDeclaration");
                if (InlineClassesUtilsKt.isInlineClass(containingDeclaration)) {
                    i = 0;
                } else {
                    i = 1;
                }
            } else {
                i = 0;
            }
            int shift = i;
            int extraArgumentsTail = ($this$run.isDefault ? 2 : 0) + (((descriptor instanceof FunctionDescriptor) && ((FunctionDescriptor) descriptor).isSuspend()) ? 1 : 0);
            ArrayList kotlinParameterTypes = new ArrayList();
            ReceiverParameterDescriptor extensionReceiverParameter = descriptor.getExtensionReceiverParameter();
            KotlinType extensionReceiverType = extensionReceiverParameter != null ? extensionReceiverParameter.getType() : null;
            if (extensionReceiverType != null) {
                kotlinParameterTypes.add(extensionReceiverType);
            } else if (descriptor instanceof ConstructorDescriptor) {
                ClassDescriptor constructedClass = ((ConstructorDescriptor) descriptor).getConstructedClass();
                Intrinsics.checkNotNullExpressionValue(constructedClass, "descriptor.constructedClass");
                if (constructedClass.isInner()) {
                    DeclarationDescriptor containingDeclaration2 = constructedClass.getContainingDeclaration();
                    if (containingDeclaration2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                    }
                    kotlinParameterTypes.add(((ClassDescriptor) containingDeclaration2).getDefaultType());
                }
            } else {
                DeclarationDescriptor containingDeclaration3 = descriptor.getContainingDeclaration();
                Intrinsics.checkNotNullExpressionValue(containingDeclaration3, "descriptor.containingDeclaration");
                if ((containingDeclaration3 instanceof ClassDescriptor) && InlineClassesUtilsKt.isInlineClass(containingDeclaration3)) {
                    kotlinParameterTypes.add(((ClassDescriptor) containingDeclaration3).getDefaultType());
                }
            }
            Iterable valueParameters = descriptor.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(valueParameters, "descriptor.valueParameters");
            Iterable $this$mapTo$iv = valueParameters;
            for (Object item$iv : $this$mapTo$iv) {
                ValueParameterDescriptor p1 = (ValueParameterDescriptor) item$iv;
                kotlinParameterTypes.add(p1.getType());
            }
            ArrayList kotlinParameterTypes2 = kotlinParameterTypes;
            int expectedArgsSize = kotlinParameterTypes2.size() + shift + extraArgumentsTail;
            if (CallerKt.getArity($this$run) != expectedArgsSize) {
                throw new KotlinReflectionInternalError("Inconsistent number of parameters in the descriptor and Java reflection object: " + CallerKt.getArity($this$run) + " != " + expectedArgsSize + "\nCalling: " + descriptor + "\nParameter types: " + $this$run.getParameterTypes() + ")\nDefault: " + $this$run.isDefault);
            }
            IntRange argumentRange = RangesKt.until(Math.max(shift, 0), kotlinParameterTypes2.size() + shift);
            Method[] unbox = new Method[expectedArgsSize];
            for (int i2 = 0; i2 < expectedArgsSize; i2++) {
                int i3 = i2;
                int i4 = i2;
                if (argumentRange.contains(i4)) {
                    Class<?> inlineClass2 = InlineClassAwareCallerKt.toInlineClass((KotlinType) kotlinParameterTypes2.get(i4 - shift));
                    unboxMethod = inlineClass2 != null ? InlineClassAwareCallerKt.getUnboxMethod(inlineClass2, descriptor) : null;
                } else {
                    unboxMethod = null;
                }
                unbox[i3] = unboxMethod;
            }
            boxUnboxData = new BoxUnboxData(argumentRange, unbox, box);
        }
        this.data = boxUnboxData;
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    /* renamed from: getMember */
    public M mo3475getMember() {
        return (M) this.caller.mo3475getMember();
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    @NotNull
    public Type getReturnType() {
        return this.caller.getReturnType();
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    @NotNull
    public List<Type> getParameterTypes() {
        return this.caller.getParameterTypes();
    }

    /* compiled from: InlineClassAwareCaller.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\r\b\u0002\u0018��2\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003H\u0086\u0002J\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u0086\u0002¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0086\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller$BoxUnboxData;", "", "argumentRange", "Lkotlin/ranges/IntRange;", "unbox", "", "Ljava/lang/reflect/Method;", "box", "(Lkotlin/ranges/IntRange;[Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "getArgumentRange", "()Lkotlin/ranges/IntRange;", "getBox", "()Ljava/lang/reflect/Method;", "getUnbox", "()[Ljava/lang/reflect/Method;", "[Ljava/lang/reflect/Method;", "component1", "component2", "component3", "kotlin-reflection"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/calls/InlineClassAwareCaller$BoxUnboxData.class */
    private static final class BoxUnboxData {

        @NotNull
        private final IntRange argumentRange;

        @NotNull
        private final Method[] unbox;

        @Nullable
        private final Method box;

        public BoxUnboxData(@NotNull IntRange argumentRange, @NotNull Method[] unbox, @Nullable Method box) {
            Intrinsics.checkNotNullParameter(argumentRange, "argumentRange");
            Intrinsics.checkNotNullParameter(unbox, "unbox");
            this.argumentRange = argumentRange;
            this.unbox = unbox;
            this.box = box;
        }

        @NotNull
        public final IntRange component1() {
            return this.argumentRange;
        }

        @NotNull
        public final Method[] component2() {
            return this.unbox;
        }

        @Nullable
        public final Method component3() {
            return this.box;
        }
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    @Nullable
    public Object call(@NotNull Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object objDefaultPrimitiveValue;
        Intrinsics.checkNotNullParameter(args, "args");
        BoxUnboxData boxUnboxData = this.data;
        IntRange range = boxUnboxData.component1();
        Method[] unbox = boxUnboxData.component2();
        Method box = boxUnboxData.component3();
        Object[] unboxed = Arrays.copyOf(args, args.length);
        Intrinsics.checkNotNullExpressionValue(unboxed, "java.util.Arrays.copyOf(this, size)");
        if (unboxed == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        }
        int index = range.getFirst();
        int last = range.getLast();
        if (index <= last) {
            while (true) {
                Method method = unbox[index];
                Object arg = args[index];
                int i = index;
                if (method != null) {
                    if (arg != null) {
                        objDefaultPrimitiveValue = method.invoke(arg, new Object[0]);
                    } else {
                        Class<?> returnType = method.getReturnType();
                        Intrinsics.checkNotNullExpressionValue(returnType, "method.returnType");
                        objDefaultPrimitiveValue = UtilKt.defaultPrimitiveValue(returnType);
                    }
                } else {
                    objDefaultPrimitiveValue = arg;
                }
                unboxed[i] = objDefaultPrimitiveValue;
                if (index == last) {
                    break;
                }
                index++;
            }
        }
        Object result = this.caller.call(unboxed);
        if (box != null) {
            Object objInvoke = box.invoke(null, result);
            if (objInvoke != null) {
                return objInvoke;
            }
        }
        return result;
    }
}
