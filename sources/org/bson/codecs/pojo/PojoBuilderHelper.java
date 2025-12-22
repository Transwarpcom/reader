package org.bson.codecs.pojo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.bson.assertions.Assertions;
import org.bson.codecs.configuration.CodecConfigurationException;
import org.bson.codecs.pojo.PropertyReflectionUtils;
import org.bson.codecs.pojo.TypeData;
import org.bson.codecs.pojo.TypeParameterMap;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/PojoBuilderHelper.class */
final class PojoBuilderHelper {
    static <T> void configureClassModelBuilder(ClassModelBuilder<T> classModelBuilder, Class<T> clazz) {
        classModelBuilder.type((Class) Assertions.notNull("clazz", clazz));
        ArrayList<Annotation> annotations = new ArrayList<>();
        Set<String> propertyNames = new TreeSet<>();
        Map<String, TypeParameterMap> propertyTypeParameterMap = new HashMap<>();
        Class<T> superclass = clazz;
        String declaringClassName = clazz.getSimpleName();
        TypeData<?> parentClassTypeData = null;
        Map<String, PropertyMetadata<?>> propertyNameMap = new HashMap<>();
        while (!superclass.isEnum() && superclass.getSuperclass() != null) {
            annotations.addAll(Arrays.asList(superclass.getDeclaredAnnotations()));
            List<String> genericTypeNames = new ArrayList<>();
            for (TypeVariable<? extends Class<? super T>> classTypeVariable : superclass.getTypeParameters()) {
                genericTypeNames.add(classTypeVariable.getName());
            }
            PropertyReflectionUtils.PropertyMethods propertyMethods = PropertyReflectionUtils.getPropertyMethods(superclass);
            for (Method method : propertyMethods.getSetterMethods()) {
                String propertyName = PropertyReflectionUtils.toPropertyName(method);
                propertyNames.add(propertyName);
                PropertyMetadata<?> propertyMetadata = getOrCreateMethodPropertyMetadata(propertyName, declaringClassName, propertyNameMap, TypeData.newInstance(method), propertyTypeParameterMap, parentClassTypeData, genericTypeNames, getGenericType(method));
                if (propertyMetadata.getSetter() == null) {
                    propertyMetadata.setSetter(method);
                    for (Annotation annotation : method.getDeclaredAnnotations()) {
                        propertyMetadata.addWriteAnnotation(annotation);
                    }
                }
            }
            for (Method method2 : propertyMethods.getGetterMethods()) {
                String propertyName2 = PropertyReflectionUtils.toPropertyName(method2);
                propertyNames.add(propertyName2);
                PropertyMetadata<?> propertyMetadata2 = propertyNameMap.get(propertyName2);
                if (propertyMetadata2 == null || propertyMetadata2.getGetter() == null) {
                    PropertyMetadata<?> propertyMetadata3 = getOrCreateMethodPropertyMetadata(propertyName2, declaringClassName, propertyNameMap, TypeData.newInstance(method2), propertyTypeParameterMap, parentClassTypeData, genericTypeNames, getGenericType(method2));
                    if (propertyMetadata3.getGetter() == null) {
                        propertyMetadata3.setGetter(method2);
                        for (Annotation annotation2 : method2.getDeclaredAnnotations()) {
                            propertyMetadata3.addReadAnnotation(annotation2);
                        }
                    }
                }
            }
            for (Field field : superclass.getDeclaredFields()) {
                propertyNames.add(field.getName());
                PropertyMetadata<?> propertyMetadata4 = getOrCreateFieldPropertyMetadata(field.getName(), declaringClassName, propertyNameMap, TypeData.newInstance(field), propertyTypeParameterMap, parentClassTypeData, genericTypeNames, field.getGenericType());
                if (propertyMetadata4 != null && propertyMetadata4.getField() == null) {
                    propertyMetadata4.field(field);
                    for (Annotation annotation3 : field.getDeclaredAnnotations()) {
                        propertyMetadata4.addReadAnnotation(annotation3);
                        propertyMetadata4.addWriteAnnotation(annotation3);
                    }
                }
            }
            parentClassTypeData = TypeData.newInstance(superclass.getGenericSuperclass(), superclass);
            superclass = superclass.getSuperclass();
        }
        if (superclass.isInterface()) {
            annotations.addAll(Arrays.asList(superclass.getDeclaredAnnotations()));
        }
        Iterator<String> it = propertyNames.iterator();
        while (it.hasNext()) {
            PropertyMetadata<?> propertyMetadata5 = propertyNameMap.get(it.next());
            if (propertyMetadata5.isSerializable() || propertyMetadata5.isDeserializable()) {
                classModelBuilder.addProperty(createPropertyModelBuilder(propertyMetadata5));
            }
        }
        Collections.reverse(annotations);
        classModelBuilder.annotations(annotations);
        classModelBuilder.propertyNameToTypeParameterMap(propertyTypeParameterMap);
        Constructor<?> constructor = null;
        for (Constructor<?> constructor2 : clazz.getDeclaredConstructors()) {
            if (constructor2.getParameterTypes().length == 0 && (Modifier.isPublic(constructor2.getModifiers()) || Modifier.isProtected(constructor2.getModifiers()))) {
                constructor = constructor2;
                constructor.setAccessible(true);
            }
        }
        classModelBuilder.instanceCreatorFactory(new InstanceCreatorFactoryImpl<>(new CreatorExecutable(clazz, constructor)));
    }

    private static <T, S> PropertyMetadata<T> getOrCreateMethodPropertyMetadata(String propertyName, String declaringClassName, Map<String, PropertyMetadata<?>> propertyNameMap, TypeData<T> typeData, Map<String, TypeParameterMap> propertyTypeParameterMap, TypeData<S> parentClassTypeData, List<String> genericTypeNames, Type genericType) {
        PropertyMetadata<T> propertyMetadata = getOrCreatePropertyMetadata(propertyName, declaringClassName, propertyNameMap, typeData);
        if (!propertyMetadata.getTypeData().getType().isAssignableFrom(typeData.getType())) {
            throw new CodecConfigurationException(String.format("Property '%s' in %s, has differing data types: %s and %s", propertyName, declaringClassName, propertyMetadata.getTypeData(), typeData));
        }
        cachePropertyTypeData(propertyMetadata, propertyTypeParameterMap, parentClassTypeData, genericTypeNames, genericType);
        return propertyMetadata;
    }

    private static <T, S> PropertyMetadata<T> getOrCreateFieldPropertyMetadata(String propertyName, String declaringClassName, Map<String, PropertyMetadata<?>> propertyNameMap, TypeData<T> typeData, Map<String, TypeParameterMap> propertyTypeParameterMap, TypeData<S> parentClassTypeData, List<String> genericTypeNames, Type genericType) {
        PropertyMetadata<T> propertyMetadata = getOrCreatePropertyMetadata(propertyName, declaringClassName, propertyNameMap, typeData);
        if (!propertyMetadata.getTypeData().getType().isAssignableFrom(typeData.getType())) {
            return null;
        }
        cachePropertyTypeData(propertyMetadata, propertyTypeParameterMap, parentClassTypeData, genericTypeNames, genericType);
        return propertyMetadata;
    }

    private static <T> PropertyMetadata<T> getOrCreatePropertyMetadata(String str, String str2, Map<String, PropertyMetadata<?>> map, TypeData<T> typeData) {
        PropertyMetadata<?> propertyMetadata = map.get(str);
        if (propertyMetadata == null) {
            propertyMetadata = new PropertyMetadata<>(str, str2, typeData);
            map.put(str, propertyMetadata);
        }
        return (PropertyMetadata<T>) propertyMetadata;
    }

    private static <T, S> void cachePropertyTypeData(PropertyMetadata<T> propertyMetadata, Map<String, TypeParameterMap> propertyTypeParameterMap, TypeData<S> parentClassTypeData, List<String> genericTypeNames, Type genericType) {
        TypeParameterMap typeParameterMap = getTypeParameterMap(genericTypeNames, genericType);
        propertyTypeParameterMap.put(propertyMetadata.getName(), typeParameterMap);
        propertyMetadata.typeParameterInfo(typeParameterMap, parentClassTypeData);
    }

    private static Type getGenericType(Method method) {
        return PropertyReflectionUtils.isGetter(method) ? method.getGenericReturnType() : method.getGenericParameterTypes()[0];
    }

    static <T> PropertyModelBuilder<T> createPropertyModelBuilder(PropertyMetadata<T> propertyMetadata) {
        PropertyModelBuilder<T> propertyModelBuilder = PropertyModel.builder().propertyName(propertyMetadata.getName()).readName(propertyMetadata.getName()).writeName(propertyMetadata.getName()).typeData(propertyMetadata.getTypeData()).readAnnotations(propertyMetadata.getReadAnnotations()).writeAnnotations(propertyMetadata.getWriteAnnotations()).propertySerialization(new PropertyModelSerializationImpl()).propertyAccessor(new PropertyAccessorImpl(propertyMetadata));
        if (propertyMetadata.getTypeParameters() != null) {
            specializePropertyModelBuilder(propertyModelBuilder, propertyMetadata);
        }
        return propertyModelBuilder;
    }

    private static TypeParameterMap getTypeParameterMap(List<String> genericTypeNames, Type propertyType) {
        int classParamIndex = genericTypeNames.indexOf(propertyType.toString());
        TypeParameterMap.Builder builder = TypeParameterMap.builder();
        if (classParamIndex != -1) {
            builder.addIndex(classParamIndex);
        } else if (propertyType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) propertyType;
            for (int i = 0; i < pt.getActualTypeArguments().length; i++) {
                int classParamIndex2 = genericTypeNames.indexOf(pt.getActualTypeArguments()[i].toString());
                if (classParamIndex2 != -1) {
                    builder.addIndex(i, classParamIndex2);
                }
            }
        }
        return builder.build();
    }

    private static <V> void specializePropertyModelBuilder(PropertyModelBuilder<V> propertyModelBuilder, PropertyMetadata<V> propertyMetadata) {
        TypeData<V> specializedFieldType;
        if (propertyMetadata.getTypeParameterMap().hasTypeParameters() && !propertyMetadata.getTypeParameters().isEmpty()) {
            Map<Integer, Integer> fieldToClassParamIndexMap = propertyMetadata.getTypeParameterMap().getPropertyToClassParamIndexMap();
            Integer classTypeParamRepresentsWholeField = fieldToClassParamIndexMap.get(-1);
            if (classTypeParamRepresentsWholeField != null) {
                specializedFieldType = (TypeData) propertyMetadata.getTypeParameters().get(classTypeParamRepresentsWholeField.intValue());
            } else {
                TypeData.Builder<V> builder = TypeData.builder(propertyModelBuilder.getTypeData().getType());
                List<TypeData<?>> typeParameters = new ArrayList<>(propertyModelBuilder.getTypeData().getTypeParameters());
                for (int i = 0; i < typeParameters.size(); i++) {
                    for (Map.Entry<Integer, Integer> mapping : fieldToClassParamIndexMap.entrySet()) {
                        if (mapping.getKey().equals(Integer.valueOf(i))) {
                            typeParameters.set(i, propertyMetadata.getTypeParameters().get(mapping.getValue().intValue()));
                        }
                    }
                }
                builder.addTypeParameters(typeParameters);
                specializedFieldType = builder.build();
            }
            propertyModelBuilder.typeData(specializedFieldType);
        }
    }

    static <V> V stateNotNull(String property, V value) {
        if (value == null) {
            throw new IllegalStateException(String.format("%s cannot be null", property));
        }
        return value;
    }

    private PojoBuilderHelper() {
    }
}
