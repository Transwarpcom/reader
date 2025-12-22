package retrofit2;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/Platform.class */
class Platform {
    private static final Platform PLATFORM = findPlatform();

    Platform() {
    }

    static Platform get() {
        return PLATFORM;
    }

    private static Platform findPlatform() throws ClassNotFoundException {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new Android();
            }
        } catch (ClassNotFoundException e) {
        }
        try {
            Class.forName("java.util.Optional");
            return new Java8();
        } catch (ClassNotFoundException e2) {
            return new Platform();
        }
    }

    @Nullable
    Executor defaultCallbackExecutor() {
        return null;
    }

    List<? extends CallAdapter.Factory> defaultCallAdapterFactories(@Nullable Executor callbackExecutor) {
        return Collections.singletonList(new DefaultCallAdapterFactory(callbackExecutor));
    }

    int defaultCallAdapterFactoriesSize() {
        return 1;
    }

    List<? extends Converter.Factory> defaultConverterFactories() {
        return Collections.emptyList();
    }

    int defaultConverterFactoriesSize() {
        return 0;
    }

    boolean isDefaultMethod(Method method) {
        return false;
    }

    @Nullable
    Object invokeDefaultMethod(Method method, Class<?> declaringClass, Object object, @Nullable Object... args) throws Throwable {
        throw new UnsupportedOperationException();
    }

    @IgnoreJRERequirement
    /* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/Platform$Java8.class */
    static class Java8 extends Platform {
        Java8() {
        }

        @Override // retrofit2.Platform
        boolean isDefaultMethod(Method method) {
            return method.isDefault();
        }

        @Override // retrofit2.Platform
        Object invokeDefaultMethod(Method method, Class<?> declaringClass, Object object, @Nullable Object... args) throws Throwable {
            Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
            constructor.setAccessible(true);
            return constructor.newInstance(declaringClass, -1).unreflectSpecial(method, declaringClass).bindTo(object).invokeWithArguments(args);
        }

        @Override // retrofit2.Platform
        List<? extends CallAdapter.Factory> defaultCallAdapterFactories(@Nullable Executor callbackExecutor) {
            List<CallAdapter.Factory> factories = new ArrayList<>(2);
            factories.add(CompletableFutureCallAdapterFactory.INSTANCE);
            factories.add(new DefaultCallAdapterFactory(callbackExecutor));
            return Collections.unmodifiableList(factories);
        }

        @Override // retrofit2.Platform
        int defaultCallAdapterFactoriesSize() {
            return 2;
        }

        @Override // retrofit2.Platform
        List<? extends Converter.Factory> defaultConverterFactories() {
            return Collections.singletonList(OptionalConverterFactory.INSTANCE);
        }

        @Override // retrofit2.Platform
        int defaultConverterFactoriesSize() {
            return 1;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/Platform$Android.class */
    static class Android extends Platform {
        Android() {
        }

        @Override // retrofit2.Platform
        @IgnoreJRERequirement
        boolean isDefaultMethod(Method method) {
            if (Build.VERSION.SDK_INT < 24) {
                return false;
            }
            return method.isDefault();
        }

        @Override // retrofit2.Platform
        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        @Override // retrofit2.Platform
        List<? extends CallAdapter.Factory> defaultCallAdapterFactories(@Nullable Executor callbackExecutor) {
            if (callbackExecutor == null) {
                throw new AssertionError();
            }
            DefaultCallAdapterFactory executorFactory = new DefaultCallAdapterFactory(callbackExecutor);
            return Build.VERSION.SDK_INT >= 24 ? Arrays.asList(CompletableFutureCallAdapterFactory.INSTANCE, executorFactory) : Collections.singletonList(executorFactory);
        }

        @Override // retrofit2.Platform
        int defaultCallAdapterFactoriesSize() {
            return Build.VERSION.SDK_INT >= 24 ? 2 : 1;
        }

        @Override // retrofit2.Platform
        List<? extends Converter.Factory> defaultConverterFactories() {
            if (Build.VERSION.SDK_INT >= 24) {
                return Collections.singletonList(OptionalConverterFactory.INSTANCE);
            }
            return Collections.emptyList();
        }

        @Override // retrofit2.Platform
        int defaultConverterFactoriesSize() {
            return Build.VERSION.SDK_INT >= 24 ? 1 : 0;
        }

        /* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/Platform$Android$MainThreadExecutor.class */
        static class MainThreadExecutor implements Executor {
            private final Handler handler = new Handler(Looper.getMainLooper());

            MainThreadExecutor() {
            }

            @Override // java.util.concurrent.Executor
            public void execute(Runnable r) {
                this.handler.post(r);
            }
        }
    }
}
