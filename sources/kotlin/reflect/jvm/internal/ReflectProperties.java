package kotlin.reflect.jvm.internal;

import java.lang.ref.SoftReference;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/ReflectProperties.class */
public class ReflectProperties {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        objArr[0] = "initializer";
        objArr[1] = "kotlin/reflect/jvm/internal/ReflectProperties";
        switch (i) {
            case 0:
            default:
                objArr[2] = "lazy";
                break;
            case 1:
            case 2:
                objArr[2] = "lazySoft";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/ReflectProperties$Val.class */
    public static abstract class Val<T> {
        private static final Object NULL_VALUE = new Object() { // from class: kotlin.reflect.jvm.internal.ReflectProperties.Val.1
        };

        public abstract T invoke();

        public final T getValue(Object instance, Object metadata) {
            return invoke();
        }

        protected Object escape(T value) {
            return value == null ? NULL_VALUE : value;
        }

        /* JADX WARN: Multi-variable type inference failed */
        protected T unescape(Object obj) {
            if (obj == NULL_VALUE) {
                return null;
            }
            return obj;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/ReflectProperties$LazyVal.class */
    public static class LazyVal<T> extends Val<T> {
        private final Function0<T> initializer;
        private volatile Object value;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initializer", "kotlin/reflect/jvm/internal/ReflectProperties$LazyVal", Constants.CONSTRUCTOR_NAME));
        }

        public LazyVal(@NotNull Function0<T> initializer) {
            if (initializer == null) {
                $$$reportNull$$$0(0);
            }
            this.value = null;
            this.initializer = initializer;
        }

        @Override // kotlin.reflect.jvm.internal.ReflectProperties.Val, kotlin.jvm.functions.Function0
        public T invoke() {
            Object cached = this.value;
            if (cached != null) {
                return unescape(cached);
            }
            T result = this.initializer.invoke();
            this.value = escape(result);
            return result;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal.class */
    public static class LazySoftVal<T> extends Val<T> implements Function0<T> {
        private final Function0<T> initializer;
        private volatile SoftReference<Object> value;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initializer", "kotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal", Constants.CONSTRUCTOR_NAME));
        }

        public LazySoftVal(@Nullable T initialValue, @NotNull Function0<T> initializer) {
            if (initializer == null) {
                $$$reportNull$$$0(0);
            }
            this.value = null;
            this.initializer = initializer;
            if (initialValue != null) {
                this.value = new SoftReference<>(escape(initialValue));
            }
        }

        @Override // kotlin.reflect.jvm.internal.ReflectProperties.Val, kotlin.jvm.functions.Function0
        public T invoke() {
            Object result;
            SoftReference<Object> cached = this.value;
            if (cached != null && (result = cached.get()) != null) {
                return unescape(result);
            }
            T result2 = this.initializer.invoke();
            this.value = new SoftReference<>(escape(result2));
            return result2;
        }
    }

    @NotNull
    public static <T> LazyVal<T> lazy(@NotNull Function0<T> initializer) {
        if (initializer == null) {
            $$$reportNull$$$0(0);
        }
        return new LazyVal<>(initializer);
    }

    @NotNull
    public static <T> LazySoftVal<T> lazySoft(@Nullable T initialValue, @NotNull Function0<T> initializer) {
        if (initializer == null) {
            $$$reportNull$$$0(1);
        }
        return new LazySoftVal<>(initialValue, initializer);
    }

    @NotNull
    public static <T> LazySoftVal<T> lazySoft(@NotNull Function0<T> initializer) {
        if (initializer == null) {
            $$$reportNull$$$0(2);
        }
        return lazySoft(null, initializer);
    }
}
