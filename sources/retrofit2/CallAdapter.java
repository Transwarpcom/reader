package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/CallAdapter.class */
public interface CallAdapter<R, T> {
    Type responseType();

    T adapt(Call<R> call);

    /* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/CallAdapter$Factory.class */
    public static abstract class Factory {
        @Nullable
        public abstract CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit);

        protected static Type getParameterUpperBound(int index, ParameterizedType type) {
            return Utils.getParameterUpperBound(index, type);
        }

        protected static Class<?> getRawType(Type type) {
            return Utils.getRawType(type);
        }
    }
}
