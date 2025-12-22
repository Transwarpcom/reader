package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReflectJavaMember.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/structure/Java8ParameterNamesLoader.class */
final class Java8ParameterNamesLoader {

    @NotNull
    public static final Java8ParameterNamesLoader INSTANCE = new Java8ParameterNamesLoader();

    @Nullable
    private static Cache cache;

    /* compiled from: ReflectJavaMember.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/structure/Java8ParameterNamesLoader$Cache.class */
    public static final class Cache {

        @Nullable
        private final Method getParameters;

        @Nullable
        private final Method getName;

        public Cache(@Nullable Method getParameters, @Nullable Method getName) {
            this.getParameters = getParameters;
            this.getName = getName;
        }

        @Nullable
        public final Method getGetParameters() {
            return this.getParameters;
        }

        @Nullable
        public final Method getGetName() {
            return this.getName;
        }
    }

    private Java8ParameterNamesLoader() {
    }

    @NotNull
    public final Cache buildCache(@NotNull Member member) throws NoSuchMethodException, SecurityException, ClassNotFoundException {
        Intrinsics.checkNotNullParameter(member, "member");
        Class methodOrConstructorClass = member.getClass();
        try {
            Method getParameters = methodOrConstructorClass.getMethod("getParameters", new Class[0]);
            Class parameterClass = ReflectClassUtilKt.getSafeClassLoader(methodOrConstructorClass).loadClass("java.lang.reflect.Parameter");
            return new Cache(getParameters, parameterClass.getMethod("getName", new Class[0]));
        } catch (NoSuchMethodException e) {
            return new Cache(null, null);
        }
    }

    @Nullable
    public final List<String> loadParameterNames(@NotNull Member member) throws IllegalAccessException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Method getName;
        Intrinsics.checkNotNullParameter(member, "member");
        Cache cache2 = cache;
        if (cache2 == null) {
            cache2 = buildCache(member);
            cache = cache2;
        }
        Method getParameters = cache2.getGetParameters();
        if (getParameters == null || (getName = cache2.getGetName()) == null) {
            return null;
        }
        Object objInvoke = getParameters.invoke(member, new Object[0]);
        if (objInvoke == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<*>");
        }
        Object[] $this$map$iv = (Object[]) objInvoke;
        Collection destination$iv$iv = new ArrayList($this$map$iv.length);
        for (Object item$iv$iv : $this$map$iv) {
            Object objInvoke2 = getName.invoke(item$iv$iv, new Object[0]);
            if (objInvoke2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            destination$iv$iv.add((String) objInvoke2);
        }
        return (List) destination$iv$iv;
    }
}
