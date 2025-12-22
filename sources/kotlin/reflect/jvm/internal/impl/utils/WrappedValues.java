package kotlin.reflect.jvm.internal.impl.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/WrappedValues.class */
public class WrappedValues {
    private static final Object NULL_VALUE = new Object() { // from class: kotlin.reflect.jvm.internal.impl.utils.WrappedValues.1
        public String toString() {
            return "NULL_VALUE";
        }
    };
    public static volatile boolean throwWrappedProcessCanceledException = false;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 3:
            case 4:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 1:
            case 2:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 3:
            case 4:
            default:
                i2 = 3;
                break;
            case 1:
            case 2:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 4:
            default:
                objArr[0] = "value";
                break;
            case 1:
            case 2:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/utils/WrappedValues";
                break;
            case 3:
                objArr[0] = "throwable";
                break;
        }
        switch (i) {
            case 0:
            case 3:
            case 4:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/WrappedValues";
                break;
            case 1:
            case 2:
                objArr[1] = "escapeNull";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = "unescapeNull";
                break;
            case 1:
            case 2:
                break;
            case 3:
                objArr[2] = "escapeThrowable";
                break;
            case 4:
                objArr[2] = "unescapeExceptionOrNull";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 3:
            case 4:
            default:
                throw new IllegalArgumentException(str2);
            case 1:
            case 2:
                throw new IllegalStateException(str2);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/WrappedValues$ThrowableWrapper.class */
    private static final class ThrowableWrapper {
        private final Throwable throwable;

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
                    objArr[0] = "throwable";
                    break;
                case 1:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/utils/WrappedValues$ThrowableWrapper";
                    break;
            }
            switch (i) {
                case 0:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/WrappedValues$ThrowableWrapper";
                    break;
                case 1:
                    objArr[1] = "getThrowable";
                    break;
            }
            switch (i) {
                case 0:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
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

        private ThrowableWrapper(@NotNull Throwable throwable) {
            if (throwable == null) {
                $$$reportNull$$$0(0);
            }
            this.throwable = throwable;
        }

        @NotNull
        public Throwable getThrowable() {
            Throwable th = this.throwable;
            if (th == null) {
                $$$reportNull$$$0(1);
            }
            return th;
        }

        public String toString() {
            return this.throwable.toString();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static <V> V unescapeNull(@NotNull Object obj) {
        if (obj == 0) {
            $$$reportNull$$$0(0);
        }
        if (obj == NULL_VALUE) {
            return null;
        }
        return obj;
    }

    @NotNull
    public static <V> Object escapeNull(@Nullable V value) {
        if (value != null) {
            if (value == null) {
                $$$reportNull$$$0(2);
            }
            return value;
        }
        Object obj = NULL_VALUE;
        if (obj == null) {
            $$$reportNull$$$0(1);
        }
        return obj;
    }

    @NotNull
    public static Object escapeThrowable(@NotNull Throwable throwable) {
        if (throwable == null) {
            $$$reportNull$$$0(3);
        }
        return new ThrowableWrapper(throwable);
    }

    @Nullable
    public static <V> V unescapeExceptionOrNull(@NotNull Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(4);
        }
        return (V) unescapeNull(unescapeThrowable(obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static <V> V unescapeThrowable(@Nullable Object obj) {
        if (obj instanceof ThrowableWrapper) {
            Throwable originThrowable = ((ThrowableWrapper) obj).getThrowable();
            if (throwWrappedProcessCanceledException && ExceptionUtilsKt.isProcessCanceledException(originThrowable)) {
                throw new WrappedProcessCanceledException(originThrowable);
            }
            throw ExceptionUtilsKt.rethrow(originThrowable);
        }
        return obj;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/WrappedValues$WrappedProcessCanceledException.class */
    public static class WrappedProcessCanceledException extends RuntimeException {
        public WrappedProcessCanceledException(Throwable cause) {
            super("Rethrow stored exception", cause);
        }
    }
}
