package kotlin.reflect.jvm.internal.impl.storage;

import cn.hutool.core.text.StrPool;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.utils.ExceptionUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.WrappedValues;
import kotlin.text.StringsKt;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager.class */
public class LockBasedStorageManager implements StorageManager {
    private static final String PACKAGE_NAME;
    public static final StorageManager NO_LOCKS;
    protected final SimpleLock lock;
    private final ExceptionHandlingStrategy exceptionHandlingStrategy;
    private final String debugText;
    static final /* synthetic */ boolean $assertionsDisabled;

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$ExceptionHandlingStrategy.class */
    public interface ExceptionHandlingStrategy {
        public static final ExceptionHandlingStrategy THROW = new ExceptionHandlingStrategy() { // from class: kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.ExceptionHandlingStrategy.1
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "throwable", "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$ExceptionHandlingStrategy$1", "handleException"));
            }

            @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.ExceptionHandlingStrategy
            @NotNull
            public RuntimeException handleException(@NotNull Throwable throwable) {
                if (throwable == null) {
                    $$$reportNull$$$0(0);
                }
                throw ExceptionUtilsKt.rethrow(throwable);
            }
        };

        @NotNull
        RuntimeException handleException(@NotNull Throwable th);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$NotValue.class */
    private enum NotValue {
        NOT_COMPUTED,
        COMPUTING,
        RECURSION_WAS_DETECTED
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 10:
            case 13:
            case 20:
            case 37:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            default:
                i2 = 3;
                break;
            case 10:
            case 13:
            case 20:
            case 37:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 2:
            case 4:
            case 7:
            default:
                objArr[0] = "debugText";
                break;
            case 1:
            case 3:
            case 5:
            case 8:
                objArr[0] = "exceptionHandlingStrategy";
                break;
            case 6:
                objArr[0] = "lock";
                break;
            case 9:
            case 11:
            case 14:
            case 16:
            case 19:
            case 21:
                objArr[0] = "compute";
                break;
            case 10:
            case 13:
            case 20:
            case 37:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager";
                break;
            case 12:
            case 17:
            case 25:
            case 27:
                objArr[0] = "onRecursiveCall";
                break;
            case 15:
            case 18:
            case 22:
                objArr[0] = BeanDefinitionParserDelegate.MAP_ELEMENT;
                break;
            case 23:
            case 24:
            case 26:
            case 28:
            case 30:
            case 31:
            case 32:
            case 34:
                objArr[0] = "computable";
                break;
            case 29:
            case 33:
                objArr[0] = "postCompute";
                break;
            case 35:
                objArr[0] = PackageDocumentBase.DCTags.source;
                break;
            case 36:
                objArr[0] = "throwable";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager";
                break;
            case 10:
            case 13:
                objArr[1] = "createMemoizedFunction";
                break;
            case 20:
                objArr[1] = "createMemoizedFunctionWithNullableValues";
                break;
            case 37:
                objArr[1] = "sanitizeStackTrace";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            default:
                objArr[2] = "createWithExceptionHandling";
                break;
            case 4:
            case 5:
            case 6:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 7:
            case 8:
                objArr[2] = "replaceExceptionHandling";
                break;
            case 9:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                objArr[2] = "createMemoizedFunction";
                break;
            case 10:
            case 13:
            case 20:
            case 37:
                break;
            case 19:
            case 21:
            case 22:
                objArr[2] = "createMemoizedFunctionWithNullableValues";
                break;
            case 23:
            case 24:
            case 25:
                objArr[2] = "createLazyValue";
                break;
            case 26:
            case 27:
                objArr[2] = "createRecursionTolerantLazyValue";
                break;
            case 28:
            case 29:
                objArr[2] = "createLazyValueWithPostCompute";
                break;
            case 30:
                objArr[2] = "createNullableLazyValue";
                break;
            case 31:
                objArr[2] = "createRecursionTolerantNullableLazyValue";
                break;
            case 32:
            case 33:
                objArr[2] = "createNullableLazyValueWithPostCompute";
                break;
            case 34:
                objArr[2] = "compute";
                break;
            case 35:
                objArr[2] = "recursionDetectedDefault";
                break;
            case 36:
                objArr[2] = "sanitizeStackTrace";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            default:
                throw new IllegalArgumentException(str2);
            case 10:
            case 13:
            case 20:
            case 37:
                throw new IllegalStateException(str2);
        }
    }

    static {
        $assertionsDisabled = !LockBasedStorageManager.class.desiredAssertionStatus();
        PACKAGE_NAME = StringsKt.substringBeforeLast(LockBasedStorageManager.class.getCanonicalName(), ".", "");
        NO_LOCKS = new LockBasedStorageManager("NO_LOCKS", ExceptionHandlingStrategy.THROW, EmptySimpleLock.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.1
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                String str;
                int i2;
                switch (i) {
                    case 0:
                    default:
                        str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                        break;
                    case 1:
                        str = "@NotNull method %s.%s must not return null";
                        break;
                }
                switch (i) {
                    case 0:
                    default:
                        i2 = 3;
                        break;
                    case 1:
                        i2 = 2;
                        break;
                }
                Object[] objArr = new Object[i2];
                switch (i) {
                    case 0:
                    default:
                        objArr[0] = PackageDocumentBase.DCTags.source;
                        break;
                    case 1:
                        objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$1";
                        break;
                }
                switch (i) {
                    case 0:
                    default:
                        objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$1";
                        break;
                    case 1:
                        objArr[1] = "recursionDetectedDefault";
                        break;
                }
                switch (i) {
                    case 0:
                    default:
                        objArr[2] = "recursionDetectedDefault";
                        break;
                    case 1:
                        break;
                }
                String str2 = String.format(str, objArr);
                switch (i) {
                    case 0:
                    default:
                        throw new IllegalArgumentException(str2);
                    case 1:
                        throw new IllegalStateException(str2);
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager
            @NotNull
            protected <K, V> RecursionDetectedResult<V> recursionDetectedDefault(@NotNull String source, K input) {
                if (source == null) {
                    $$$reportNull$$$0(0);
                }
                RecursionDetectedResult<V> recursionDetectedResultFallThrough = RecursionDetectedResult.fallThrough();
                if (recursionDetectedResultFallThrough == null) {
                    $$$reportNull$$$0(1);
                }
                return recursionDetectedResultFallThrough;
            }
        };
    }

    private LockBasedStorageManager(@NotNull String debugText, @NotNull ExceptionHandlingStrategy exceptionHandlingStrategy, @NotNull SimpleLock lock) {
        if (debugText == null) {
            $$$reportNull$$$0(4);
        }
        if (exceptionHandlingStrategy == null) {
            $$$reportNull$$$0(5);
        }
        if (lock == null) {
            $$$reportNull$$$0(6);
        }
        this.lock = lock;
        this.exceptionHandlingStrategy = exceptionHandlingStrategy;
        this.debugText = debugText;
    }

    public LockBasedStorageManager(String debugText) {
        this(debugText, (Runnable) null, (Function1<InterruptedException, Unit>) null);
    }

    public LockBasedStorageManager(String debugText, @Nullable Runnable checkCancelled, @Nullable Function1<InterruptedException, Unit> interruptedExceptionHandler) {
        this(debugText, ExceptionHandlingStrategy.THROW, SimpleLock.Companion.simpleLock(checkCancelled, interruptedExceptionHandler));
    }

    public String toString() {
        return getClass().getSimpleName() + StrPool.AT + Integer.toHexString(hashCode()) + " (" + this.debugText + ")";
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    @NotNull
    public <K, V> MemoizedFunctionToNotNull<K, V> createMemoizedFunction(@NotNull Function1<? super K, ? extends V> compute) {
        if (compute == null) {
            $$$reportNull$$$0(9);
        }
        MemoizedFunctionToNotNull<K, V> memoizedFunctionToNotNullCreateMemoizedFunction = createMemoizedFunction(compute, createConcurrentHashMap());
        if (memoizedFunctionToNotNullCreateMemoizedFunction == null) {
            $$$reportNull$$$0(10);
        }
        return memoizedFunctionToNotNullCreateMemoizedFunction;
    }

    @NotNull
    public <K, V> MemoizedFunctionToNotNull<K, V> createMemoizedFunction(@NotNull Function1<? super K, ? extends V> compute, @NotNull ConcurrentMap<K, Object> map) {
        if (compute == null) {
            $$$reportNull$$$0(14);
        }
        if (map == null) {
            $$$reportNull$$$0(15);
        }
        return new MapBasedMemoizedFunctionToNotNull(this, map, compute);
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    @NotNull
    public <K, V> MemoizedFunctionToNullable<K, V> createMemoizedFunctionWithNullableValues(@NotNull Function1<? super K, ? extends V> compute) {
        if (compute == null) {
            $$$reportNull$$$0(19);
        }
        MemoizedFunctionToNullable<K, V> memoizedFunctionToNullableCreateMemoizedFunctionWithNullableValues = createMemoizedFunctionWithNullableValues(compute, createConcurrentHashMap());
        if (memoizedFunctionToNullableCreateMemoizedFunctionWithNullableValues == null) {
            $$$reportNull$$$0(20);
        }
        return memoizedFunctionToNullableCreateMemoizedFunctionWithNullableValues;
    }

    @NotNull
    public <K, V> MemoizedFunctionToNullable<K, V> createMemoizedFunctionWithNullableValues(@NotNull Function1<? super K, ? extends V> compute, @NotNull ConcurrentMap<K, Object> map) {
        if (compute == null) {
            $$$reportNull$$$0(21);
        }
        if (map == null) {
            $$$reportNull$$$0(22);
        }
        return new MapBasedMemoizedFunction(this, map, compute);
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    @NotNull
    public <T> NotNullLazyValue<T> createLazyValue(@NotNull Function0<? extends T> computable) {
        if (computable == null) {
            $$$reportNull$$$0(23);
        }
        return new LockBasedNotNullLazyValue(this, computable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    @NotNull
    public <T> NotNullLazyValue<T> createRecursionTolerantLazyValue(@NotNull Function0<? extends T> computable, @NotNull final T onRecursiveCall) {
        if (computable == null) {
            $$$reportNull$$$0(26);
        }
        if (onRecursiveCall == null) {
            $$$reportNull$$$0(27);
        }
        return new LockBasedNotNullLazyValue<T>(this, computable) { // from class: kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.4
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$4", "recursionDetected"));
            }

            @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.LockBasedLazyValue
            @NotNull
            protected RecursionDetectedResult<T> recursionDetected(boolean firstTime) {
                RecursionDetectedResult<T> recursionDetectedResultValue = RecursionDetectedResult.value(onRecursiveCall);
                if (recursionDetectedResultValue == null) {
                    $$$reportNull$$$0(0);
                }
                return recursionDetectedResultValue;
            }
        };
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    @NotNull
    public <T> NotNullLazyValue<T> createLazyValueWithPostCompute(@NotNull Function0<? extends T> computable, final Function1<? super Boolean, ? extends T> onRecursiveCall, @NotNull final Function1<? super T, Unit> postCompute) {
        if (computable == null) {
            $$$reportNull$$$0(28);
        }
        if (postCompute == null) {
            $$$reportNull$$$0(29);
        }
        return new LockBasedNotNullLazyValueWithPostCompute<T>(this, computable) { // from class: kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.5
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                String str;
                int i2;
                switch (i) {
                    case 0:
                    case 1:
                    default:
                        str = "@NotNull method %s.%s must not return null";
                        break;
                    case 2:
                        str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                        break;
                }
                switch (i) {
                    case 0:
                    case 1:
                    default:
                        i2 = 2;
                        break;
                    case 2:
                        i2 = 3;
                        break;
                }
                Object[] objArr = new Object[i2];
                switch (i) {
                    case 0:
                    case 1:
                    default:
                        objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$5";
                        break;
                    case 2:
                        objArr[0] = "value";
                        break;
                }
                switch (i) {
                    case 0:
                    case 1:
                    default:
                        objArr[1] = "recursionDetected";
                        break;
                    case 2:
                        objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$5";
                        break;
                }
                switch (i) {
                    case 2:
                        objArr[2] = "doPostCompute";
                        break;
                }
                String str2 = String.format(str, objArr);
                switch (i) {
                    case 0:
                    case 1:
                    default:
                        throw new IllegalStateException(str2);
                    case 2:
                        throw new IllegalArgumentException(str2);
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.LockBasedLazyValue
            @NotNull
            protected RecursionDetectedResult<T> recursionDetected(boolean firstTime) {
                if (onRecursiveCall == null) {
                    RecursionDetectedResult<T> recursionDetectedResultRecursionDetected = super.recursionDetected(firstTime);
                    if (recursionDetectedResultRecursionDetected == null) {
                        $$$reportNull$$$0(0);
                    }
                    return recursionDetectedResultRecursionDetected;
                }
                RecursionDetectedResult<T> recursionDetectedResultValue = RecursionDetectedResult.value(onRecursiveCall.invoke(Boolean.valueOf(firstTime)));
                if (recursionDetectedResultValue == null) {
                    $$$reportNull$$$0(1);
                }
                return recursionDetectedResultValue;
            }

            @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.LockBasedLazyValueWithPostCompute
            protected void doPostCompute(@NotNull T value) {
                if (value == null) {
                    $$$reportNull$$$0(2);
                }
                postCompute.invoke(value);
            }
        };
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    @NotNull
    public <T> NullableLazyValue<T> createNullableLazyValue(@NotNull Function0<? extends T> computable) {
        if (computable == null) {
            $$$reportNull$$$0(30);
        }
        return new LockBasedLazyValue(this, computable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public <T> T compute(@NotNull Function0<? extends T> computable) {
        if (computable == null) {
            $$$reportNull$$$0(34);
        }
        this.lock.lock();
        try {
            try {
                T tInvoke = computable.invoke();
                this.lock.unlock();
                return tInvoke;
            } catch (Throwable throwable) {
                throw this.exceptionHandlingStrategy.handleException(throwable);
            }
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    @NotNull
    private static <K> ConcurrentMap<K, Object> createConcurrentHashMap() {
        return new ConcurrentHashMap(3, 1.0f, 2);
    }

    @NotNull
    protected <K, V> RecursionDetectedResult<V> recursionDetectedDefault(@NotNull String source, K input) {
        if (source == null) {
            $$$reportNull$$$0(35);
        }
        throw ((AssertionError) sanitizeStackTrace(new AssertionError("Recursion detected " + source + (input == null ? "" : "on input: " + input) + " under " + this)));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$RecursionDetectedResult.class */
    private static class RecursionDetectedResult<T> {
        private final T value;
        private final boolean fallThrough;
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !LockBasedStorageManager.class.desiredAssertionStatus();
        }

        @NotNull
        public static <T> RecursionDetectedResult<T> value(T value) {
            return new RecursionDetectedResult<>(value, false);
        }

        @NotNull
        public static <T> RecursionDetectedResult<T> fallThrough() {
            return new RecursionDetectedResult<>(null, true);
        }

        private RecursionDetectedResult(T value, boolean fallThrough) {
            this.value = value;
            this.fallThrough = fallThrough;
        }

        public T getValue() {
            if ($assertionsDisabled || !this.fallThrough) {
                return this.value;
            }
            throw new AssertionError("A value requested from FALL_THROUGH in " + this);
        }

        public boolean isFallThrough() {
            return this.fallThrough;
        }

        public String toString() {
            return isFallThrough() ? "FALL_THROUGH" : String.valueOf(this.value);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedLazyValue.class */
    private static class LockBasedLazyValue<T> implements NullableLazyValue<T> {
        private final LockBasedStorageManager storageManager;
        private final Function0<? extends T> computable;

        @Nullable
        private volatile Object value;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 1:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 2:
                case 3:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                default:
                    i2 = 3;
                    break;
                case 2:
                case 3:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "storageManager";
                    break;
                case 1:
                    objArr[0] = "computable";
                    break;
                case 2:
                case 3:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedLazyValue";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedLazyValue";
                    break;
                case 2:
                    objArr[1] = "recursionDetected";
                    break;
                case 3:
                    objArr[1] = "renderDebugInformation";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
                case 2:
                case 3:
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 1:
                default:
                    throw new IllegalArgumentException(str2);
                case 2:
                case 3:
                    throw new IllegalStateException(str2);
            }
        }

        public LockBasedLazyValue(@NotNull LockBasedStorageManager storageManager, @NotNull Function0<? extends T> computable) {
            if (storageManager == null) {
                $$$reportNull$$$0(0);
            }
            if (computable == null) {
                $$$reportNull$$$0(1);
            }
            this.value = NotValue.NOT_COMPUTED;
            this.storageManager = storageManager;
            this.computable = computable;
        }

        public boolean isComputed() {
            return (this.value == NotValue.NOT_COMPUTED || this.value == NotValue.COMPUTING) ? false : true;
        }

        @Override // kotlin.jvm.functions.Function0
        public T invoke() {
            Object obj = this.value;
            if (!(obj instanceof NotValue)) {
                return (T) WrappedValues.unescapeThrowable(obj);
            }
            this.storageManager.lock.lock();
            try {
                Object obj2 = this.value;
                if (!(obj2 instanceof NotValue)) {
                    T t = (T) WrappedValues.unescapeThrowable(obj2);
                    this.storageManager.lock.unlock();
                    return t;
                }
                if (obj2 == NotValue.COMPUTING) {
                    this.value = NotValue.RECURSION_WAS_DETECTED;
                    RecursionDetectedResult<T> recursionDetectedResultRecursionDetected = recursionDetected(true);
                    if (!recursionDetectedResultRecursionDetected.isFallThrough()) {
                        T value = recursionDetectedResultRecursionDetected.getValue();
                        this.storageManager.lock.unlock();
                        return value;
                    }
                }
                if (obj2 == NotValue.RECURSION_WAS_DETECTED) {
                    RecursionDetectedResult<T> recursionDetectedResultRecursionDetected2 = recursionDetected(false);
                    if (!recursionDetectedResultRecursionDetected2.isFallThrough()) {
                        T value2 = recursionDetectedResultRecursionDetected2.getValue();
                        this.storageManager.lock.unlock();
                        return value2;
                    }
                }
                this.value = NotValue.COMPUTING;
                try {
                    T tInvoke = this.computable.invoke();
                    postCompute(tInvoke);
                    this.value = tInvoke;
                    this.storageManager.lock.unlock();
                    return tInvoke;
                } catch (Throwable th) {
                    if (ExceptionUtilsKt.isProcessCanceledException(th)) {
                        this.value = NotValue.NOT_COMPUTED;
                        throw ((RuntimeException) th);
                    }
                    if (this.value == NotValue.COMPUTING) {
                        this.value = WrappedValues.escapeThrowable(th);
                    }
                    throw this.storageManager.exceptionHandlingStrategy.handleException(th);
                }
            } catch (Throwable th2) {
                this.storageManager.lock.unlock();
                throw th2;
            }
        }

        @NotNull
        protected RecursionDetectedResult<T> recursionDetected(boolean firstTime) {
            RecursionDetectedResult<T> recursionDetectedResultRecursionDetectedDefault = this.storageManager.recursionDetectedDefault("in a lazy value", null);
            if (recursionDetectedResultRecursionDetectedDefault == null) {
                $$$reportNull$$$0(2);
            }
            return recursionDetectedResultRecursionDetectedDefault;
        }

        protected void postCompute(T value) {
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedLazyValueWithPostCompute.class */
    private static abstract class LockBasedLazyValueWithPostCompute<T> extends LockBasedLazyValue<T> {

        @Nullable
        private volatile SingleThreadValue<T> valuePostCompute;

        protected abstract void doPostCompute(T t);

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "storageManager";
                    break;
                case 1:
                    objArr[0] = "computable";
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedLazyValueWithPostCompute";
            objArr[2] = Constants.CONSTRUCTOR_NAME;
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LockBasedLazyValueWithPostCompute(@NotNull LockBasedStorageManager storageManager, @NotNull Function0<? extends T> computable) {
            super(storageManager, computable);
            if (storageManager == null) {
                $$$reportNull$$$0(0);
            }
            if (computable == null) {
                $$$reportNull$$$0(1);
            }
            this.valuePostCompute = null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.LockBasedLazyValue, kotlin.jvm.functions.Function0
        public T invoke() {
            SingleThreadValue<T> singleThreadValue = this.valuePostCompute;
            if (singleThreadValue != null && singleThreadValue.hasValue()) {
                return singleThreadValue.getValue();
            }
            return (T) super.invoke();
        }

        @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.LockBasedLazyValue
        protected final void postCompute(T value) {
            this.valuePostCompute = new SingleThreadValue<>(value);
            try {
                doPostCompute(value);
                this.valuePostCompute = null;
            } catch (Throwable th) {
                this.valuePostCompute = null;
                throw th;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValueWithPostCompute.class */
    private static abstract class LockBasedNotNullLazyValueWithPostCompute<T> extends LockBasedLazyValueWithPostCompute<T> implements NotNullLazyValue<T> {
        static final /* synthetic */ boolean $assertionsDisabled;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 1:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 2:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                default:
                    i2 = 3;
                    break;
                case 2:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "storageManager";
                    break;
                case 1:
                    objArr[0] = "computable";
                    break;
                case 2:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValueWithPostCompute";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValueWithPostCompute";
                    break;
                case 2:
                    objArr[1] = "invoke";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
                case 2:
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 1:
                default:
                    throw new IllegalArgumentException(str2);
                case 2:
                    throw new IllegalStateException(str2);
            }
        }

        static {
            $assertionsDisabled = !LockBasedStorageManager.class.desiredAssertionStatus();
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LockBasedNotNullLazyValueWithPostCompute(@NotNull LockBasedStorageManager storageManager, @NotNull Function0<? extends T> computable) {
            super(storageManager, computable);
            if (storageManager == null) {
                $$$reportNull$$$0(0);
            }
            if (computable == null) {
                $$$reportNull$$$0(1);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.LockBasedLazyValueWithPostCompute, kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.LockBasedLazyValue, kotlin.jvm.functions.Function0
        @NotNull
        public T invoke() {
            T t = (T) super.invoke();
            if (!$assertionsDisabled && t == null) {
                throw new AssertionError("compute() returned null");
            }
            if (t == null) {
                $$$reportNull$$$0(2);
            }
            return t;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValue.class */
    private static class LockBasedNotNullLazyValue<T> extends LockBasedLazyValue<T> implements NotNullLazyValue<T> {
        static final /* synthetic */ boolean $assertionsDisabled;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 1:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 2:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                default:
                    i2 = 3;
                    break;
                case 2:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "storageManager";
                    break;
                case 1:
                    objArr[0] = "computable";
                    break;
                case 2:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValue";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValue";
                    break;
                case 2:
                    objArr[1] = "invoke";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
                case 2:
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 1:
                default:
                    throw new IllegalArgumentException(str2);
                case 2:
                    throw new IllegalStateException(str2);
            }
        }

        static {
            $assertionsDisabled = !LockBasedStorageManager.class.desiredAssertionStatus();
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LockBasedNotNullLazyValue(@NotNull LockBasedStorageManager storageManager, @NotNull Function0<? extends T> computable) {
            super(storageManager, computable);
            if (storageManager == null) {
                $$$reportNull$$$0(0);
            }
            if (computable == null) {
                $$$reportNull$$$0(1);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.LockBasedLazyValue, kotlin.jvm.functions.Function0
        @NotNull
        public T invoke() {
            T t = (T) super.invoke();
            if (!$assertionsDisabled && t == null) {
                throw new AssertionError("compute() returned null");
            }
            if (t == null) {
                $$$reportNull$$$0(2);
            }
            return t;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunction.class */
    private static class MapBasedMemoizedFunction<K, V> implements MemoizedFunctionToNullable<K, V> {
        private final LockBasedStorageManager storageManager;
        private final ConcurrentMap<K, Object> cache;
        private final Function1<? super K, ? extends V> compute;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 3:
                case 4:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    i2 = 3;
                    break;
                case 3:
                case 4:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "storageManager";
                    break;
                case 1:
                    objArr[0] = BeanDefinitionParserDelegate.MAP_ELEMENT;
                    break;
                case 2:
                    objArr[0] = "compute";
                    break;
                case 3:
                case 4:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunction";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunction";
                    break;
                case 3:
                    objArr[1] = "recursionDetected";
                    break;
                case 4:
                    objArr[1] = "raceCondition";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
                case 3:
                case 4:
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    throw new IllegalArgumentException(str2);
                case 3:
                case 4:
                    throw new IllegalStateException(str2);
            }
        }

        public MapBasedMemoizedFunction(@NotNull LockBasedStorageManager storageManager, @NotNull ConcurrentMap<K, Object> map, @NotNull Function1<? super K, ? extends V> compute) {
            if (storageManager == null) {
                $$$reportNull$$$0(0);
            }
            if (map == null) {
                $$$reportNull$$$0(1);
            }
            if (compute == null) {
                $$$reportNull$$$0(2);
            }
            this.storageManager = storageManager;
            this.cache = map;
            this.compute = compute;
        }

        @Override // kotlin.jvm.functions.Function1
        @Nullable
        public V invoke(K k) {
            Object obj = this.cache.get(k);
            if (obj != null && obj != NotValue.COMPUTING) {
                return (V) WrappedValues.unescapeExceptionOrNull(obj);
            }
            this.storageManager.lock.lock();
            try {
                Object obj2 = this.cache.get(k);
                if (obj2 == NotValue.COMPUTING) {
                    obj2 = NotValue.RECURSION_WAS_DETECTED;
                    RecursionDetectedResult<V> recursionDetectedResultRecursionDetected = recursionDetected(k, true);
                    if (!recursionDetectedResultRecursionDetected.isFallThrough()) {
                        V value = recursionDetectedResultRecursionDetected.getValue();
                        this.storageManager.lock.unlock();
                        return value;
                    }
                }
                if (obj2 == NotValue.RECURSION_WAS_DETECTED) {
                    RecursionDetectedResult<V> recursionDetectedResultRecursionDetected2 = recursionDetected(k, false);
                    if (!recursionDetectedResultRecursionDetected2.isFallThrough()) {
                        V value2 = recursionDetectedResultRecursionDetected2.getValue();
                        this.storageManager.lock.unlock();
                        return value2;
                    }
                }
                if (obj2 != null) {
                    V v = (V) WrappedValues.unescapeExceptionOrNull(obj2);
                    this.storageManager.lock.unlock();
                    return v;
                }
                AssertionError assertionErrorRaceCondition = null;
                try {
                    this.cache.put(k, NotValue.COMPUTING);
                    V vInvoke = this.compute.invoke(k);
                    Object objPut = this.cache.put(k, WrappedValues.escapeNull(vInvoke));
                    if (objPut == NotValue.COMPUTING) {
                        return vInvoke;
                    }
                    assertionErrorRaceCondition = raceCondition(k, objPut);
                    throw assertionErrorRaceCondition;
                } catch (Throwable th) {
                    if (ExceptionUtilsKt.isProcessCanceledException(th)) {
                        this.cache.remove(k);
                        throw ((RuntimeException) th);
                    }
                    if (th == assertionErrorRaceCondition) {
                        throw this.storageManager.exceptionHandlingStrategy.handleException(th);
                    }
                    Object objPut2 = this.cache.put(k, WrappedValues.escapeThrowable(th));
                    if (objPut2 != NotValue.COMPUTING) {
                        throw raceCondition(k, objPut2);
                    }
                    throw this.storageManager.exceptionHandlingStrategy.handleException(th);
                }
            } finally {
                this.storageManager.lock.unlock();
            }
        }

        @NotNull
        protected RecursionDetectedResult<V> recursionDetected(K input, boolean firstTime) {
            RecursionDetectedResult<V> recursionDetectedResultRecursionDetectedDefault = this.storageManager.recursionDetectedDefault("", input);
            if (recursionDetectedResultRecursionDetectedDefault == null) {
                $$$reportNull$$$0(3);
            }
            return recursionDetectedResultRecursionDetectedDefault;
        }

        @NotNull
        private AssertionError raceCondition(K input, Object oldValue) {
            AssertionError assertionError = (AssertionError) LockBasedStorageManager.sanitizeStackTrace(new AssertionError("Race condition detected on input " + input + ". Old value is " + oldValue + " under " + this.storageManager));
            if (assertionError == null) {
                $$$reportNull$$$0(4);
            }
            return assertionError;
        }

        @Override // kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable
        public boolean isComputed(K key) {
            Object value = this.cache.get(key);
            return (value == null || value == NotValue.COMPUTING) ? false : true;
        }

        protected LockBasedStorageManager getStorageManager() {
            return this.storageManager;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunctionToNotNull.class */
    private static class MapBasedMemoizedFunctionToNotNull<K, V> extends MapBasedMemoizedFunction<K, V> implements MemoizedFunctionToNotNull<K, V> {
        static final /* synthetic */ boolean $assertionsDisabled;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 3:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    i2 = 3;
                    break;
                case 3:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "storageManager";
                    break;
                case 1:
                    objArr[0] = BeanDefinitionParserDelegate.MAP_ELEMENT;
                    break;
                case 2:
                    objArr[0] = "compute";
                    break;
                case 3:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunctionToNotNull";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunctionToNotNull";
                    break;
                case 3:
                    objArr[1] = "invoke";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
                case 3:
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    throw new IllegalArgumentException(str2);
                case 3:
                    throw new IllegalStateException(str2);
            }
        }

        static {
            $assertionsDisabled = !LockBasedStorageManager.class.desiredAssertionStatus();
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MapBasedMemoizedFunctionToNotNull(@NotNull LockBasedStorageManager storageManager, @NotNull ConcurrentMap<K, Object> map, @NotNull Function1<? super K, ? extends V> compute) {
            super(storageManager, map, compute);
            if (storageManager == null) {
                $$$reportNull$$$0(0);
            }
            if (map == null) {
                $$$reportNull$$$0(1);
            }
            if (compute == null) {
                $$$reportNull$$$0(2);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.MapBasedMemoizedFunction, kotlin.jvm.functions.Function1
        @NotNull
        public V invoke(K k) {
            V v = (V) super.invoke(k);
            if (!$assertionsDisabled && v == null) {
                throw new AssertionError("compute() returned null under " + getStorageManager());
            }
            if (v == null) {
                $$$reportNull$$$0(3);
            }
            return v;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NotNull
    public static <T extends Throwable> T sanitizeStackTrace(@NotNull T throwable) {
        if (throwable == null) {
            $$$reportNull$$$0(36);
        }
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        int size = stackTrace.length;
        int firstNonStorage = -1;
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            if (stackTrace[i].getClassName().startsWith(PACKAGE_NAME)) {
                i++;
            } else {
                firstNonStorage = i;
                break;
            }
        }
        if (!$assertionsDisabled && firstNonStorage < 0) {
            throw new AssertionError("This method should only be called on exceptions created in LockBasedStorageManager");
        }
        List<StackTraceElement> list = Arrays.asList(stackTrace).subList(firstNonStorage, size);
        throwable.setStackTrace((StackTraceElement[]) list.toArray(new StackTraceElement[list.size()]));
        if (throwable == null) {
            $$$reportNull$$$0(37);
        }
        return throwable;
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    @NotNull
    public <K, V> CacheWithNullableValues<K, V> createCacheWithNullableValues() {
        return new CacheWithNullableValuesBasedOnMemoizedFunction(createConcurrentHashMap());
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$CacheWithNullableValuesBasedOnMemoizedFunction.class */
    private static class CacheWithNullableValuesBasedOnMemoizedFunction<K, V> extends MapBasedMemoizedFunction<KeyWithComputation<K, V>, V> implements CacheWithNullableValues<K, V> {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "storageManager";
                    break;
                case 1:
                    objArr[0] = BeanDefinitionParserDelegate.MAP_ELEMENT;
                    break;
                case 2:
                    objArr[0] = "computation";
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$CacheWithNullableValuesBasedOnMemoizedFunction";
            switch (i) {
                case 0:
                case 1:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
                case 2:
                    objArr[2] = "computeIfAbsent";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private CacheWithNullableValuesBasedOnMemoizedFunction(@NotNull LockBasedStorageManager storageManager, @NotNull ConcurrentMap<KeyWithComputation<K, V>, Object> map) {
            super(storageManager, map, new Function1<KeyWithComputation<K, V>, V>() { // from class: kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.CacheWithNullableValuesBasedOnMemoizedFunction.1
                @Override // kotlin.jvm.functions.Function1
                public V invoke(KeyWithComputation<K, V> keyWithComputation) {
                    return (V) ((KeyWithComputation) keyWithComputation).computation.invoke();
                }
            });
            if (storageManager == null) {
                $$$reportNull$$$0(0);
            }
            if (map == null) {
                $$$reportNull$$$0(1);
            }
        }

        @Nullable
        public V computeIfAbsent(K key, @NotNull Function0<? extends V> computation) {
            if (computation == null) {
                $$$reportNull$$$0(2);
            }
            return invoke(new KeyWithComputation(key, computation));
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    @NotNull
    public <K, V> CacheWithNotNullValues<K, V> createCacheWithNotNullValues() {
        return new CacheWithNotNullValuesBasedOnMemoizedFunction(createConcurrentHashMap());
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$CacheWithNotNullValuesBasedOnMemoizedFunction.class */
    private static class CacheWithNotNullValuesBasedOnMemoizedFunction<K, V> extends CacheWithNullableValuesBasedOnMemoizedFunction<K, V> implements CacheWithNotNullValues<K, V> {
        static final /* synthetic */ boolean $assertionsDisabled;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 3:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    i2 = 3;
                    break;
                case 3:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "storageManager";
                    break;
                case 1:
                    objArr[0] = BeanDefinitionParserDelegate.MAP_ELEMENT;
                    break;
                case 2:
                    objArr[0] = "computation";
                    break;
                case 3:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$CacheWithNotNullValuesBasedOnMemoizedFunction";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$CacheWithNotNullValuesBasedOnMemoizedFunction";
                    break;
                case 3:
                    objArr[1] = "computeIfAbsent";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
                case 2:
                    objArr[2] = "computeIfAbsent";
                    break;
                case 3:
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 1:
                case 2:
                default:
                    throw new IllegalArgumentException(str2);
                case 3:
                    throw new IllegalStateException(str2);
            }
        }

        static {
            $assertionsDisabled = !LockBasedStorageManager.class.desiredAssertionStatus();
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private CacheWithNotNullValuesBasedOnMemoizedFunction(@NotNull LockBasedStorageManager storageManager, @NotNull ConcurrentMap<KeyWithComputation<K, V>, Object> map) {
            super(map);
            if (storageManager == null) {
                $$$reportNull$$$0(0);
            }
            if (map == null) {
                $$$reportNull$$$0(1);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.CacheWithNullableValuesBasedOnMemoizedFunction, kotlin.reflect.jvm.internal.impl.storage.CacheWithNotNullValues
        @NotNull
        public V computeIfAbsent(K k, @NotNull Function0<? extends V> function0) {
            if (function0 == null) {
                $$$reportNull$$$0(2);
            }
            V v = (V) super.computeIfAbsent(k, function0);
            if (!$assertionsDisabled && v == null) {
                throw new AssertionError("computeIfAbsent() returned null under " + getStorageManager());
            }
            if (v == null) {
                $$$reportNull$$$0(3);
            }
            return v;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$KeyWithComputation.class */
    private static class KeyWithComputation<K, V> {
        private final K key;
        private final Function0<? extends V> computation;

        public KeyWithComputation(K key, Function0<? extends V> computation) {
            this.key = key;
            this.computation = computation;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            KeyWithComputation<?, ?> that = (KeyWithComputation) o;
            return this.key.equals(that.key);
        }

        public int hashCode() {
            return this.key.hashCode();
        }
    }
}
