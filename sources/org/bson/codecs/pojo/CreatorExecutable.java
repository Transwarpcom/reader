package org.bson.codecs.pojo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.codecs.configuration.CodecConfigurationException;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/CreatorExecutable.class */
final class CreatorExecutable<T> {
    private final Class<T> clazz;
    private final Constructor<T> constructor;
    private final Method method;
    private final List<BsonProperty> properties;
    private final Integer idPropertyIndex;
    private final List<Class<?>> parameterTypes;
    private final List<Type> parameterGenericTypes;

    CreatorExecutable(Class<T> clazz, Constructor<T> constructor) {
        this(clazz, constructor, null);
    }

    CreatorExecutable(Class<T> clazz, Method method) {
        this(clazz, null, method);
    }

    private CreatorExecutable(Class<T> clazz, Constructor<T> constructor, Method method) {
        this.properties = new ArrayList();
        this.parameterTypes = new ArrayList();
        this.parameterGenericTypes = new ArrayList();
        this.clazz = clazz;
        this.constructor = constructor;
        this.method = method;
        Integer idPropertyIndex = null;
        if (constructor != null || method != null) {
            Class<?>[] paramTypes = constructor != null ? constructor.getParameterTypes() : method.getParameterTypes();
            Type[] genericParamTypes = constructor != null ? constructor.getGenericParameterTypes() : method.getGenericParameterTypes();
            this.parameterTypes.addAll(Arrays.asList(paramTypes));
            this.parameterGenericTypes.addAll(Arrays.asList(genericParamTypes));
            Annotation[][] parameterAnnotations = constructor != null ? constructor.getParameterAnnotations() : method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Annotation[] parameterAnnotation = parameterAnnotations[i];
                int length = parameterAnnotation.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        Annotation annotation = parameterAnnotation[i2];
                        if (annotation.annotationType().equals(BsonProperty.class)) {
                            this.properties.add((BsonProperty) annotation);
                            break;
                        } else if (!annotation.annotationType().equals(BsonId.class)) {
                            i2++;
                        } else {
                            this.properties.add(null);
                            idPropertyIndex = Integer.valueOf(i);
                            break;
                        }
                    }
                }
            }
        }
        this.idPropertyIndex = idPropertyIndex;
    }

    Class<T> getType() {
        return this.clazz;
    }

    List<BsonProperty> getProperties() {
        return this.properties;
    }

    Integer getIdPropertyIndex() {
        return this.idPropertyIndex;
    }

    List<Class<?>> getParameterTypes() {
        return this.parameterTypes;
    }

    List<Type> getParameterGenericTypes() {
        return this.parameterGenericTypes;
    }

    T getInstance() {
        checkHasAnExecutable();
        try {
            if (this.constructor != null) {
                return this.constructor.newInstance(new Object[0]);
            }
            return (T) this.method.invoke(this.clazz, new Object[0]);
        } catch (Exception e) {
            throw new CodecConfigurationException(e.getMessage(), e);
        }
    }

    T getInstance(Object[] objArr) {
        checkHasAnExecutable();
        try {
            if (this.constructor != null) {
                return this.constructor.newInstance(objArr);
            }
            return (T) this.method.invoke(this.clazz, objArr);
        } catch (Exception e) {
            throw new CodecConfigurationException(e.getMessage(), e);
        }
    }

    CodecConfigurationException getError(Class<?> clazz, String msg) {
        return getError(clazz, this.constructor != null, msg);
    }

    private void checkHasAnExecutable() {
        if (this.constructor == null && this.method == null) {
            throw new CodecConfigurationException(String.format("Cannot find a public constructor for '%s'.", this.clazz.getSimpleName()));
        }
    }

    private static CodecConfigurationException getError(Class<?> clazz, boolean isConstructor, String msg) {
        Object[] objArr = new Object[3];
        objArr[0] = isConstructor ? BeanDefinitionParserDelegate.AUTOWIRE_CONSTRUCTOR_VALUE : "method";
        objArr[1] = clazz.getSimpleName();
        objArr[2] = msg;
        return new CodecConfigurationException(String.format("Invalid @BsonCreator %s in %s. %s", objArr));
    }
}
