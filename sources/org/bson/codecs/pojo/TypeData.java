package org.bson.codecs.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bson.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/TypeData.class */
final class TypeData<T> implements TypeWithTypeParameters<T> {
    private final Class<T> type;
    private final List<TypeData<?>> typeParameters;
    private static final Map<Class<?>, Class<?>> PRIMITIVE_CLASS_MAP;

    public static <T> Builder<T> builder(Class<T> type) {
        return new Builder<>((Class) Assertions.notNull("type", type));
    }

    public static TypeData<?> newInstance(Method method) {
        if (PropertyReflectionUtils.isGetter(method)) {
            return newInstance(method.getGenericReturnType(), method.getReturnType());
        }
        return newInstance(method.getGenericParameterTypes()[0], method.getParameterTypes()[0]);
    }

    public static TypeData<?> newInstance(Field field) {
        return newInstance(field.getGenericType(), field.getType());
    }

    public static <T> TypeData<T> newInstance(Type genericType, Class<T> clazz) {
        Builder<T> builder = builder(clazz);
        if (genericType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) genericType;
            for (Type argType : pType.getActualTypeArguments()) {
                getNestedTypeData(builder, argType);
            }
        }
        return builder.build();
    }

    private static <T> void getNestedTypeData(Builder<T> builder, Type type) {
        if (!(type instanceof ParameterizedType)) {
            if (type instanceof TypeVariable) {
                builder.addTypeParameter(builder(Object.class).build());
                return;
            } else {
                if (type instanceof Class) {
                    builder.addTypeParameter(builder((Class) type).build());
                    return;
                }
                return;
            }
        }
        ParameterizedType pType = (ParameterizedType) type;
        Builder paramBuilder = builder((Class) pType.getRawType());
        for (Type argType : pType.getActualTypeArguments()) {
            getNestedTypeData(paramBuilder, argType);
        }
        builder.addTypeParameter(paramBuilder.build());
    }

    @Override // org.bson.codecs.pojo.TypeWithTypeParameters
    public Class<T> getType() {
        return this.type;
    }

    @Override // org.bson.codecs.pojo.TypeWithTypeParameters
    public List<TypeData<?>> getTypeParameters() {
        return this.typeParameters;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/TypeData$Builder.class */
    public static final class Builder<T> {
        private final Class<T> type;
        private final List<TypeData<?>> typeParameters;

        private Builder(Class<T> type) {
            this.typeParameters = new ArrayList();
            this.type = type;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public <S> Builder<T> addTypeParameter(TypeData<S> typeParameter) {
            this.typeParameters.add(Assertions.notNull("typeParameter", typeParameter));
            return this;
        }

        public Builder<T> addTypeParameters(List<TypeData<?>> typeParameters) {
            Assertions.notNull("typeParameters", typeParameters);
            Iterator<TypeData<?>> it = typeParameters.iterator();
            while (it.hasNext()) {
                addTypeParameter((TypeData) it.next());
            }
            return this;
        }

        public TypeData<T> build() {
            return new TypeData<>(this.type, Collections.unmodifiableList(this.typeParameters));
        }
    }

    public String toString() {
        String typeParams = this.typeParameters.isEmpty() ? "" : ", typeParameters=[" + nestedTypeParameters(this.typeParameters) + "]";
        return "TypeData{type=" + this.type.getSimpleName() + typeParams + "}";
    }

    private static String nestedTypeParameters(List<TypeData<?>> typeParameters) {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        int last = typeParameters.size();
        for (TypeData<?> typeParameter : typeParameters) {
            count++;
            builder.append(typeParameter.getType().getSimpleName());
            if (!typeParameter.getTypeParameters().isEmpty()) {
                builder.append(String.format("<%s>", nestedTypeParameters(typeParameter.getTypeParameters())));
            }
            if (count < last) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeData)) {
            return false;
        }
        TypeData<?> that = (TypeData) o;
        if (!getType().equals(that.getType()) || !getTypeParameters().equals(that.getTypeParameters())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = getType().hashCode();
        return (31 * result) + getTypeParameters().hashCode();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TypeData(Class<T> cls, List<TypeData<?>> list) {
        this.type = (Class<T>) boxType(cls);
        this.typeParameters = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean isAssignableFrom(Class<?> cls) {
        return ((Class<T>) this.type).isAssignableFrom(boxType(cls));
    }

    private <S> Class<S> boxType(Class<S> clazz) {
        if (clazz.isPrimitive()) {
            return (Class) PRIMITIVE_CLASS_MAP.get(clazz);
        }
        return clazz;
    }

    static {
        Map<Class<?>, Class<?>> map = new HashMap<>();
        map.put(Boolean.TYPE, Boolean.class);
        map.put(Byte.TYPE, Byte.class);
        map.put(Character.TYPE, Character.class);
        map.put(Double.TYPE, Double.class);
        map.put(Float.TYPE, Float.class);
        map.put(Integer.TYPE, Integer.class);
        map.put(Long.TYPE, Long.class);
        map.put(Short.TYPE, Short.class);
        PRIMITIVE_CLASS_MAP = map;
    }
}
