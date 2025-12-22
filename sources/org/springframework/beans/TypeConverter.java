package org.springframework.beans;

import java.lang.reflect.Field;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/TypeConverter.class */
public interface TypeConverter {
    @Nullable
    <T> T convertIfNecessary(@Nullable Object obj, @Nullable Class<T> cls) throws TypeMismatchException;

    @Nullable
    <T> T convertIfNecessary(@Nullable Object obj, @Nullable Class<T> cls, @Nullable MethodParameter methodParameter) throws TypeMismatchException;

    @Nullable
    <T> T convertIfNecessary(@Nullable Object obj, @Nullable Class<T> cls, @Nullable Field field) throws TypeMismatchException;

    @Nullable
    default <T> T convertIfNecessary(@Nullable Object value, @Nullable Class<T> requiredType, @Nullable TypeDescriptor typeDescriptor) throws TypeMismatchException {
        throw new UnsupportedOperationException("TypeDescriptor resolution not supported");
    }
}
