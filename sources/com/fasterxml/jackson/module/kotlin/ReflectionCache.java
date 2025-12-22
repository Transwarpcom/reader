package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.util.LRUMap;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.jvm.ReflectJvmMapping;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReflectionCache.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��n\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b��\u0018��2\u00020\u0001:\u0001%B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\n2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\u001dJ\"\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u00192\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u000b0\u001dJ+\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001b\u001a\u00020\u00122\u0014\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u001d¢\u0006\u0002\u0010\u001fJ\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007J\u001c\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\rJ\u0014\u0010 \u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e2\u0006\u0010\u001b\u001a\u00020\u0015J\u0014\u0010!\u001a\b\u0012\u0002\b\u0003\u0018\u00010\"2\u0006\u0010#\u001a\u00020$R&\u0010\u0005\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b0\u0006X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n��R&\u0010\f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e0\u0006X\u0082\u0004¢\u0006\u0002\n��R$\u0010\u000f\u001a\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\r\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u0006X\u0082\u0004¢\u0006\u0002\n��R\u001c\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0006X\u0082\u0004¢\u0006\u0002\n��R\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u0015\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\u0006X\u0082\u0004¢\u0006\u0002\n��R\u001e\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u0015\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u0006X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n��¨\u0006&"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "", "reflectionCacheSize", "", "(I)V", "javaClassToKotlin", "Lcom/fasterxml/jackson/databind/util/LRUMap;", "Ljava/lang/Class;", "Lkotlin/reflect/KClass;", "javaConstructorIsCreatorAnnotated", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedConstructor;", "", "javaConstructorToKotlin", "Ljava/lang/reflect/Constructor;", "Lkotlin/reflect/KFunction;", "javaConstructorToValueCreator", "Lcom/fasterxml/jackson/module/kotlin/ConstructorValueCreator;", "javaMemberIsRequired", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMember;", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", "javaMethodToKotlin", "Ljava/lang/reflect/Method;", "javaMethodToValueCreator", "Lcom/fasterxml/jackson/module/kotlin/MethodValueCreator;", "kotlinGeneratedMethod", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMethod;", "checkConstructorIsCreatorAnnotated", "key", "calc", "Lkotlin/Function1;", "isKotlinGeneratedMethod", "(Lcom/fasterxml/jackson/databind/introspect/AnnotatedMember;Lkotlin/jvm/functions/Function1;)Ljava/lang/Boolean;", "kotlinFromJava", "valueCreatorFromJava", "Lcom/fasterxml/jackson/module/kotlin/ValueCreator;", "_withArgsCreator", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedWithParams;", "BooleanTriState", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ReflectionCache.class */
public final class ReflectionCache {

    @NotNull
    private final LRUMap<Class<Object>, KClass<Object>> javaClassToKotlin;

    @NotNull
    private final LRUMap<Constructor<Object>, KFunction<Object>> javaConstructorToKotlin;

    @NotNull
    private final LRUMap<Method, KFunction<?>> javaMethodToKotlin;

    @NotNull
    private final LRUMap<Constructor<Object>, ConstructorValueCreator<?>> javaConstructorToValueCreator;

    @NotNull
    private final LRUMap<Method, MethodValueCreator<?>> javaMethodToValueCreator;

    @NotNull
    private final LRUMap<AnnotatedConstructor, Boolean> javaConstructorIsCreatorAnnotated;

    @NotNull
    private final LRUMap<AnnotatedMember, BooleanTriState> javaMemberIsRequired;

    @NotNull
    private final LRUMap<AnnotatedMethod, Boolean> kotlinGeneratedMethod;

    /* compiled from: ReflectionCache.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b6\u0018�� \b2\u00020\u0001:\u0004\b\t\n\u000bB\u0011\b\u0004\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\f\r\u000e¨\u0006\u000f"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", "", "value", "", "(Ljava/lang/Boolean;)V", "getValue", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "Companion", "Empty", "False", "True", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$True;", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$False;", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$Empty;", "jackson-module-kotlin"})
    /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState.class */
    public static abstract class BooleanTriState {

        @Nullable
        private final Boolean value;

        @NotNull
        public static final Companion Companion = new Companion(null);

        @NotNull
        private static final True TRUE = new True();

        @NotNull
        private static final False FALSE = new False();

        @NotNull
        private static final Empty EMPTY = new Empty();

        public /* synthetic */ BooleanTriState(Boolean value, DefaultConstructorMarker $constructor_marker) {
            this(value);
        }

        /* compiled from: ReflectionCache.kt */
        @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$True;", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", "()V", "jackson-module-kotlin"})
        /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$True.class */
        public static final class True extends BooleanTriState {
            public True() {
                super(true, null);
            }
        }

        private BooleanTriState(Boolean value) {
            this.value = value;
        }

        @Nullable
        public final Boolean getValue() {
            return this.value;
        }

        /* compiled from: ReflectionCache.kt */
        @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$False;", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", "()V", "jackson-module-kotlin"})
        /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$False.class */
        public static final class False extends BooleanTriState {
            public False() {
                super(false, null);
            }
        }

        /* compiled from: ReflectionCache.kt */
        @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$Empty;", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", "()V", "jackson-module-kotlin"})
        /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$Empty.class */
        public static final class Empty extends BooleanTriState {
            public Empty() {
                super(null, null);
            }
        }

        /* compiled from: ReflectionCache.kt */
        @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000e"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$Companion;", "", "()V", "EMPTY", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$Empty;", "FALSE", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$False;", "TRUE", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$True;", "fromBoolean", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", "value", "", "(Ljava/lang/Boolean;)Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", "jackson-module-kotlin"})
        /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$Companion.class */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            private Companion() {
            }

            @NotNull
            public final BooleanTriState fromBoolean(@Nullable Boolean value) {
                if (value == null) {
                    return BooleanTriState.EMPTY;
                }
                if (Intrinsics.areEqual((Object) value, (Object) true)) {
                    return BooleanTriState.TRUE;
                }
                if (Intrinsics.areEqual((Object) value, (Object) false)) {
                    return BooleanTriState.FALSE;
                }
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    public ReflectionCache(int reflectionCacheSize) {
        this.javaClassToKotlin = new LRUMap<>(reflectionCacheSize, reflectionCacheSize);
        this.javaConstructorToKotlin = new LRUMap<>(reflectionCacheSize, reflectionCacheSize);
        this.javaMethodToKotlin = new LRUMap<>(reflectionCacheSize, reflectionCacheSize);
        this.javaConstructorToValueCreator = new LRUMap<>(reflectionCacheSize, reflectionCacheSize);
        this.javaMethodToValueCreator = new LRUMap<>(reflectionCacheSize, reflectionCacheSize);
        this.javaConstructorIsCreatorAnnotated = new LRUMap<>(reflectionCacheSize, reflectionCacheSize);
        this.javaMemberIsRequired = new LRUMap<>(reflectionCacheSize, reflectionCacheSize);
        this.kotlinGeneratedMethod = new LRUMap<>(reflectionCacheSize, reflectionCacheSize);
    }

    @NotNull
    public final KClass<Object> kotlinFromJava(@NotNull Class<Object> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        KClass<Object> kClass = this.javaClassToKotlin.get(key);
        if (kClass != null) {
            return kClass;
        }
        KClass it = JvmClassMappingKt.getKotlinClass(key);
        KClass<Object> kClassPutIfAbsent = this.javaClassToKotlin.putIfAbsent(key, it);
        return kClassPutIfAbsent == null ? it : kClassPutIfAbsent;
    }

    @Nullable
    public final KFunction<Object> kotlinFromJava(@NotNull Constructor<Object> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        KFunction<Object> kFunction = this.javaConstructorToKotlin.get(key);
        if (kFunction != null) {
            return kFunction;
        }
        KFunction it = ReflectJvmMapping.getKotlinFunction(key);
        if (it == null) {
            return null;
        }
        KFunction<Object> kFunctionPutIfAbsent = this.javaConstructorToKotlin.putIfAbsent(key, it);
        return kFunctionPutIfAbsent == null ? it : kFunctionPutIfAbsent;
    }

    @Nullable
    public final KFunction<?> kotlinFromJava(@NotNull Method key) {
        Intrinsics.checkNotNullParameter(key, "key");
        KFunction<?> kFunction = this.javaMethodToKotlin.get(key);
        if (kFunction != null) {
            return kFunction;
        }
        KFunction it = ReflectJvmMapping.getKotlinFunction(key);
        if (it == null) {
            return null;
        }
        KFunction<?> kFunctionPutIfAbsent = this.javaMethodToKotlin.putIfAbsent(key, it);
        return kFunctionPutIfAbsent == null ? it : kFunctionPutIfAbsent;
    }

    @Nullable
    public final ValueCreator<?> valueCreatorFromJava(@NotNull AnnotatedWithParams _withArgsCreator) throws IllegalAccessException, SecurityException {
        MethodValueCreator methodValueCreator;
        ConstructorValueCreator constructorValueCreator;
        Intrinsics.checkNotNullParameter(_withArgsCreator, "_withArgsCreator");
        if (_withArgsCreator instanceof AnnotatedConstructor) {
            Constructor constructor = ((AnnotatedConstructor) _withArgsCreator).getAnnotated();
            if (constructor == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.reflect.Constructor<kotlin.Any>");
            }
            ConstructorValueCreator constructorValueCreator2 = this.javaConstructorToValueCreator.get(constructor);
            if (constructorValueCreator2 != null) {
                constructorValueCreator = constructorValueCreator2;
            } else {
                KFunction it = kotlinFromJava((Constructor<Object>) constructor);
                if (it == null) {
                    constructorValueCreator = null;
                } else {
                    ConstructorValueCreator value = new ConstructorValueCreator(it);
                    ConstructorValueCreator constructorValueCreatorPutIfAbsent = this.javaConstructorToValueCreator.putIfAbsent(constructor, value);
                    constructorValueCreator = constructorValueCreatorPutIfAbsent == null ? value : constructorValueCreatorPutIfAbsent;
                }
            }
            return constructorValueCreator;
        }
        if (_withArgsCreator instanceof AnnotatedMethod) {
            Method method = ((AnnotatedMethod) _withArgsCreator).getAnnotated();
            if (method == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.reflect.Method");
            }
            MethodValueCreator methodValueCreator2 = this.javaMethodToValueCreator.get(method);
            if (methodValueCreator2 != null) {
                methodValueCreator = methodValueCreator2;
            } else {
                KFunction it2 = kotlinFromJava(method);
                if (it2 == null) {
                    methodValueCreator = null;
                } else {
                    MethodValueCreator value2 = MethodValueCreator.Companion.of(it2);
                    MethodValueCreator methodValueCreatorPutIfAbsent = this.javaMethodToValueCreator.putIfAbsent(method, value2);
                    methodValueCreator = methodValueCreatorPutIfAbsent == null ? value2 : methodValueCreatorPutIfAbsent;
                }
            }
            return methodValueCreator;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Expected a constructor or method to create a Kotlin object, instead found ", _withArgsCreator.getAnnotated().getClass().getName()));
    }

    public final boolean checkConstructorIsCreatorAnnotated(@NotNull AnnotatedConstructor key, @NotNull Function1<? super AnnotatedConstructor, Boolean> calc) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(calc, "calc");
        Boolean bool = this.javaConstructorIsCreatorAnnotated.get(key);
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean it = calc.invoke(key).booleanValue();
        Boolean boolPutIfAbsent = this.javaConstructorIsCreatorAnnotated.putIfAbsent(key, Boolean.valueOf(it));
        return boolPutIfAbsent == null ? it : boolPutIfAbsent.booleanValue();
    }

    @Nullable
    public final Boolean javaMemberIsRequired(@NotNull AnnotatedMember key, @NotNull Function1<? super AnnotatedMember, Boolean> calc) {
        Boolean value;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(calc, "calc");
        BooleanTriState booleanTriState = this.javaMemberIsRequired.get(key);
        Boolean value2 = booleanTriState == null ? null : booleanTriState.getValue();
        if (value2 != null) {
            return value2;
        }
        Boolean it = calc.invoke(key);
        BooleanTriState booleanTriStatePutIfAbsent = this.javaMemberIsRequired.putIfAbsent(key, BooleanTriState.Companion.fromBoolean(it));
        if (booleanTriStatePutIfAbsent != null && (value = booleanTriStatePutIfAbsent.getValue()) != null) {
            return value;
        }
        return it;
    }

    public final boolean isKotlinGeneratedMethod(@NotNull AnnotatedMethod key, @NotNull Function1<? super AnnotatedMethod, Boolean> calc) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(calc, "calc");
        Boolean bool = this.kotlinGeneratedMethod.get(key);
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean it = calc.invoke(key).booleanValue();
        Boolean boolPutIfAbsent = this.kotlinGeneratedMethod.putIfAbsent(key, Boolean.valueOf(it));
        return boolPutIfAbsent == null ? it : boolPutIfAbsent.booleanValue();
    }
}
