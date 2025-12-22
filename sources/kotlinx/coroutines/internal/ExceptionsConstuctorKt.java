package kotlinx.coroutines.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: ExceptionsConstuctor.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��.\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u001a*\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0006j\u0004\u0018\u0001`\u00072\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0002\u001a1\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006j\u0002`\u00072\u0014\b\u0004\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0006H\u0082\b\u001a!\u0010\u000f\u001a\u0004\u0018\u0001H\u0010\"\b\b��\u0010\u0010*\u00020\u00052\u0006\u0010\u0011\u001a\u0002H\u0010H��¢\u0006\u0002\u0010\u0012\u001a\u001b\u0010\u0013\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\u00042\b\b\u0002\u0010\u0014\u001a\u00020\tH\u0082\u0010\u001a\u0018\u0010\u0015\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0016\u001a\u00020\tH\u0002\"\u000e\u0010��\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��\"4\u0010\u0002\u001a(\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006j\u0002`\u00070\u0003X\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��*(\b\u0002\u0010\u0017\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00062\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006¨\u0006\u0018"}, d2 = {"cacheLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "exceptionCtors", "Ljava/util/WeakHashMap;", "Ljava/lang/Class;", "", "Lkotlin/Function1;", "Lkotlinx/coroutines/internal/Ctor;", "throwableFields", "", "createConstructor", BeanDefinitionParserDelegate.AUTOWIRE_CONSTRUCTOR_VALUE, "Ljava/lang/reflect/Constructor;", "safeCtor", "block", "tryCopyException", "E", "exception", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "fieldsCount", "accumulator", "fieldsCountOrDefault", "defaultValue", "Ctor", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/ExceptionsConstuctorKt.class */
public final class ExceptionsConstuctorKt {
    private static final int throwableFields = fieldsCountOrDefault(Throwable.class, -1);

    @NotNull
    private static final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();

    @NotNull
    private static final WeakHashMap<Class<? extends Throwable>, Function1<Throwable, Throwable>> exceptionCtors = new WeakHashMap<>();

    /* JADX WARN: Finally extract failed */
    @Nullable
    public static final <E extends Throwable> E tryCopyException(@NotNull E e) throws SecurityException {
        Object objM2105constructorimpl;
        if (e instanceof CopyableThrowable) {
            try {
                Result.Companion companion = Result.Companion;
                objM2105constructorimpl = Result.m2105constructorimpl(((CopyableThrowable) e).createCopy());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
            }
            Object obj = objM2105constructorimpl;
            return (E) (Result.m2101isFailureimpl(obj) ? null : obj);
        }
        ReentrantReadWriteLock.ReadLock lock = cacheLock.readLock();
        lock.lock();
        try {
            Function1<Throwable, Throwable> cachedCtor = exceptionCtors.get(e.getClass());
            lock.unlock();
            if (cachedCtor != null) {
                return (E) cachedCtor.invoke(e);
            }
            if (throwableFields != fieldsCountOrDefault(e.getClass(), 0)) {
                ReentrantReadWriteLock reentrantReadWriteLock = cacheLock;
                ReentrantReadWriteLock.ReadLock lock2 = reentrantReadWriteLock.readLock();
                int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
                for (int i = 0; i < readHoldCount; i++) {
                    lock2.unlock();
                }
                ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
                writeLock.lock();
                try {
                    exceptionCtors.put(e.getClass(), new Function1() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$tryCopyException$4$1
                        @Override // kotlin.jvm.functions.Function1
                        @Nullable
                        public final Void invoke(@NotNull Throwable it) {
                            return null;
                        }
                    });
                    Unit unit = Unit.INSTANCE;
                    for (int i2 = 0; i2 < readHoldCount; i2++) {
                        lock2.lock();
                    }
                    writeLock.unlock();
                    return null;
                } catch (Throwable th2) {
                    for (int i3 = 0; i3 < readHoldCount; i3++) {
                        lock2.lock();
                    }
                    writeLock.unlock();
                    throw th2;
                }
            }
            Function1<Throwable, Throwable> function1CreateConstructor = null;
            Constructor<?>[] $this$sortedByDescending$iv = e.getClass().getConstructors();
            List<Constructor> constructors = ArraysKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$tryCopyException$$inlined$sortedByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    Constructor it = (Constructor) t2;
                    Constructor it2 = (Constructor) t;
                    return ComparisonsKt.compareValues(Integer.valueOf(it.getParameterTypes().length), Integer.valueOf(it2.getParameterTypes().length));
                }
            });
            for (Constructor constructor : constructors) {
                function1CreateConstructor = createConstructor(constructor);
                if (function1CreateConstructor != null) {
                    break;
                }
            }
            ReentrantReadWriteLock reentrantReadWriteLock2 = cacheLock;
            ReentrantReadWriteLock.ReadLock lock3 = reentrantReadWriteLock2.readLock();
            int readHoldCount2 = reentrantReadWriteLock2.getWriteHoldCount() == 0 ? reentrantReadWriteLock2.getReadHoldCount() : 0;
            for (int i4 = 0; i4 < readHoldCount2; i4++) {
                lock3.unlock();
            }
            ReentrantReadWriteLock.WriteLock writeLock2 = reentrantReadWriteLock2.writeLock();
            writeLock2.lock();
            try {
                Function1<Throwable, Throwable> function1 = function1CreateConstructor;
                exceptionCtors.put(e.getClass(), function1 == null ? new Function1() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$tryCopyException$5$1
                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final Void invoke(@NotNull Throwable it) {
                        return null;
                    }
                } : function1);
                Unit unit2 = Unit.INSTANCE;
                for (int i5 = 0; i5 < readHoldCount2; i5++) {
                    lock3.lock();
                }
                writeLock2.unlock();
                Function1<Throwable, Throwable> function12 = function1CreateConstructor;
                if (function12 == null) {
                    return null;
                }
                return (E) function12.invoke(e);
            } catch (Throwable th3) {
                for (int i6 = 0; i6 < readHoldCount2; i6++) {
                    lock3.lock();
                }
                writeLock2.unlock();
                throw th3;
            }
        } catch (Throwable th4) {
            lock.unlock();
            throw th4;
        }
    }

    private static final Function1<Throwable, Throwable> createConstructor(final Constructor<?> constructor) {
        Class[] p = constructor.getParameterTypes();
        switch (p.length) {
            case 0:
                return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$createConstructor$$inlined$safeCtor$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final Throwable invoke(@NotNull Throwable e) {
                        Object objM2105constructorimpl;
                        Object objNewInstance;
                        try {
                            Result.Companion companion = Result.Companion;
                            objNewInstance = constructor.newInstance(new Object[0]);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.Companion;
                            objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
                        }
                        if (objNewInstance == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                        }
                        Throwable it = (Throwable) objNewInstance;
                        it.initCause(e);
                        objM2105constructorimpl = Result.m2105constructorimpl(it);
                        Object obj = objM2105constructorimpl;
                        return (Throwable) (Result.m2101isFailureimpl(obj) ? null : obj);
                    }
                };
            case 1:
                Class cls = p[0];
                if (!Intrinsics.areEqual(cls, Throwable.class)) {
                    if (!Intrinsics.areEqual(cls, String.class)) {
                        return null;
                    }
                    return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$createConstructor$$inlined$safeCtor$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        @Nullable
                        public final Throwable invoke(@NotNull Throwable e) {
                            Object objM2105constructorimpl;
                            Object objNewInstance;
                            try {
                                Result.Companion companion = Result.Companion;
                                objNewInstance = constructor.newInstance(e.getMessage());
                            } catch (Throwable th) {
                                Result.Companion companion2 = Result.Companion;
                                objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
                            }
                            if (objNewInstance == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                            }
                            Throwable it = (Throwable) objNewInstance;
                            it.initCause(e);
                            objM2105constructorimpl = Result.m2105constructorimpl(it);
                            Object obj = objM2105constructorimpl;
                            return (Throwable) (Result.m2101isFailureimpl(obj) ? null : obj);
                        }
                    };
                }
                return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$createConstructor$$inlined$safeCtor$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final Throwable invoke(@NotNull Throwable e) {
                        Object objM2105constructorimpl;
                        Object objNewInstance;
                        try {
                            Result.Companion companion = Result.Companion;
                            objNewInstance = constructor.newInstance(e);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.Companion;
                            objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
                        }
                        if (objNewInstance == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                        }
                        objM2105constructorimpl = Result.m2105constructorimpl((Throwable) objNewInstance);
                        Object obj = objM2105constructorimpl;
                        return (Throwable) (Result.m2101isFailureimpl(obj) ? null : obj);
                    }
                };
            case 2:
                if (!Intrinsics.areEqual(p[0], String.class) || !Intrinsics.areEqual(p[1], Throwable.class)) {
                    return null;
                }
                return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$createConstructor$$inlined$safeCtor$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final Throwable invoke(@NotNull Throwable e) {
                        Object objM2105constructorimpl;
                        Object objNewInstance;
                        try {
                            Result.Companion companion = Result.Companion;
                            objNewInstance = constructor.newInstance(e.getMessage(), e);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.Companion;
                            objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
                        }
                        if (objNewInstance == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                        }
                        objM2105constructorimpl = Result.m2105constructorimpl((Throwable) objNewInstance);
                        Object obj = objM2105constructorimpl;
                        return (Throwable) (Result.m2101isFailureimpl(obj) ? null : obj);
                    }
                };
            default:
                return null;
        }
    }

    private static final Function1<Throwable, Throwable> safeCtor(final Function1<? super Throwable, ? extends Throwable> function1) {
        return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt.safeCtor.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final Throwable invoke(@NotNull Throwable e) {
                Object objM2105constructorimpl;
                Function1<Throwable, Throwable> function12 = function1;
                try {
                    Result.Companion companion = Result.Companion;
                    objM2105constructorimpl = Result.m2105constructorimpl(function12.invoke(e));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
                }
                Object obj = objM2105constructorimpl;
                return (Throwable) (Result.m2101isFailureimpl(obj) ? null : obj);
            }
        };
    }

    private static final int fieldsCountOrDefault(Class<?> cls, int defaultValue) {
        Object objM2105constructorimpl;
        JvmClassMappingKt.getKotlinClass(cls);
        try {
            Result.Companion companion = Result.Companion;
            objM2105constructorimpl = Result.m2105constructorimpl(Integer.valueOf(fieldsCount$default(cls, 0, 1, null)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
        }
        Object obj = objM2105constructorimpl;
        return ((Number) (Result.m2101isFailureimpl(obj) ? Integer.valueOf(defaultValue) : obj)).intValue();
    }

    private static final int fieldsCount(Class<?> cls, int accumulator) {
        Class cls2 = cls;
        int i = accumulator;
        while (true) {
            int i2 = i;
            Class cls3 = cls2;
            int count$iv = 0;
            for (Field field : cls3.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    count$iv++;
                }
            }
            int fieldsCount = count$iv;
            int totalFields = i2 + fieldsCount;
            Class superClass = cls3.getSuperclass();
            if (superClass == null) {
                return totalFields;
            }
            cls2 = superClass;
            i = totalFields;
        }
    }

    static /* synthetic */ int fieldsCount$default(Class cls, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return fieldsCount(cls, i);
    }
}
