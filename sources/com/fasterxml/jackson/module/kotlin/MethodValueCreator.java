package com.fasterxml.jackson.module.kotlin;

import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.full.KCallables;
import kotlin.reflect.jvm.KCallablesJvm;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MethodValueCreator.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b��\u0018�� \u0014*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u0014B%\b\u0002\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0014\u0010\u0005\u001a\u00020\u0006X\u0094\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004X\u0094\u0004¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/MethodValueCreator;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lcom/fasterxml/jackson/module/kotlin/ValueCreator;", "callable", "Lkotlin/reflect/KFunction;", "accessible", "", "companionObjectInstance", "", "(Lkotlin/reflect/KFunction;ZLjava/lang/Object;)V", "getAccessible", "()Z", "getCallable", "()Lkotlin/reflect/KFunction;", "getCompanionObjectInstance", "()Ljava/lang/Object;", "instanceParameter", "Lkotlin/reflect/KParameter;", "getInstanceParameter", "()Lkotlin/reflect/KParameter;", "Companion", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/MethodValueCreator.class */
public final class MethodValueCreator<T> extends ValueCreator<T> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final KFunction<T> callable;
    private final boolean accessible;

    @NotNull
    private final Object companionObjectInstance;

    @NotNull
    private final KParameter instanceParameter;

    public /* synthetic */ MethodValueCreator(KFunction callable, boolean accessible, Object companionObjectInstance, DefaultConstructorMarker $constructor_marker) {
        this(callable, accessible, companionObjectInstance);
    }

    @Override // com.fasterxml.jackson.module.kotlin.ValueCreator
    @NotNull
    protected KFunction<T> getCallable() {
        return this.callable;
    }

    @Override // com.fasterxml.jackson.module.kotlin.ValueCreator
    protected boolean getAccessible() {
        return this.accessible;
    }

    @NotNull
    public final Object getCompanionObjectInstance() {
        return this.companionObjectInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private MethodValueCreator(KFunction<? extends T> kFunction, boolean accessible, Object companionObjectInstance) {
        super(null);
        this.callable = kFunction;
        this.accessible = accessible;
        this.companionObjectInstance = companionObjectInstance;
        KParameter instanceParameter = KCallables.getInstanceParameter(getCallable());
        Intrinsics.checkNotNull(instanceParameter);
        this.instanceParameter = instanceParameter;
    }

    @NotNull
    public final KParameter getInstanceParameter() {
        return this.instanceParameter;
    }

    /* compiled from: MethodValueCreator.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u0004\"\u0004\b\u0001\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007¨\u0006\b"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/MethodValueCreator$Companion;", "", "()V", "of", "Lcom/fasterxml/jackson/module/kotlin/MethodValueCreator;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "callable", "Lkotlin/reflect/KFunction;", "jackson-module-kotlin"})
    /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/MethodValueCreator$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final <T> MethodValueCreator<T> of(@NotNull KFunction<? extends T> callable) throws IllegalAccessException, SecurityException {
            Object obj;
            Pair pair;
            Pair pair2;
            Intrinsics.checkNotNullParameter(callable, "callable");
            if (KCallables.getExtensionReceiverParameter(callable) != null) {
                return null;
            }
            KParameter instanceParameter = KCallables.getInstanceParameter(callable);
            Intrinsics.checkNotNull(instanceParameter);
            KClass possibleCompanion = JvmClassMappingKt.getKotlinClass(TypesKt.erasedType(instanceParameter.getType()));
            if (!possibleCompanion.isCompanion()) {
                return null;
            }
            boolean initialCallableAccessible = KCallablesJvm.isAccessible(callable);
            if (!initialCallableAccessible) {
                KCallablesJvm.setAccessible(callable, true);
            }
            try {
                Object instance = possibleCompanion.getObjectInstance();
                Intrinsics.checkNotNull(instance);
                pair2 = TuplesKt.to(instance, Boolean.valueOf(initialCallableAccessible));
            } catch (IllegalAccessException ex) {
                Object[] declaredFields = JvmClassMappingKt.getJavaClass(possibleCompanion).getEnclosingClass().getDeclaredFields();
                Intrinsics.checkNotNullExpressionValue(declaredFields, "possibleCompanion.java.e…osingClass.declaredFields");
                Object[] $this$firstOrNull$iv = declaredFields;
                int length = $this$firstOrNull$iv.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        obj = null;
                        break;
                    }
                    Object element$iv = $this$firstOrNull$iv[i];
                    Class<?> type = ((Field) element$iv).getType();
                    Intrinsics.checkNotNullExpressionValue(type, "it.type");
                    if (JvmClassMappingKt.getKotlinClass(type).isCompanion()) {
                        obj = element$iv;
                        break;
                    }
                    i++;
                }
                Field it = (Field) obj;
                if (it == null) {
                    pair = null;
                } else {
                    it.setAccessible(true);
                    pair = TuplesKt.to(it.get(null), false);
                }
                Pair pair3 = pair;
                if (pair3 == null) {
                    throw ex;
                }
                pair2 = pair3;
            }
            Pair pair4 = pair2;
            Object companionObjectInstance = pair4.component1();
            boolean accessible = ((Boolean) pair4.component2()).booleanValue();
            return new MethodValueCreator<>(callable, accessible, companionObjectInstance, null);
        }
    }
}
