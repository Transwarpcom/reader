package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/annotation/AnnotatedElementUtils.class */
public abstract class AnnotatedElementUtils {

    @Nullable
    private static final Boolean CONTINUE = null;
    private static final Annotation[] EMPTY_ANNOTATION_ARRAY = new Annotation[0];
    private static final Processor<Boolean> alwaysTrueAnnotationProcessor = new AlwaysTrueBooleanAnnotationProcessor();

    /* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/annotation/AnnotatedElementUtils$Processor.class */
    private interface Processor<T> {
        @Nullable
        T process(@Nullable AnnotatedElement annotatedElement, Annotation annotation, int i);

        void postProcess(@Nullable AnnotatedElement annotatedElement, Annotation annotation, T t);

        boolean alwaysProcesses();

        boolean aggregates();

        List<T> getAggregatedResults();
    }

    public static AnnotatedElement forAnnotations(final Annotation... annotations) {
        return new AnnotatedElement() { // from class: org.springframework.core.annotation.AnnotatedElementUtils.1
            @Override // java.lang.reflect.AnnotatedElement
            @Nullable
            public <T extends Annotation> T getAnnotation(Class<T> cls) {
                for (Annotation annotation : annotations) {
                    T t = (T) annotation;
                    if (t.annotationType() == cls) {
                        return t;
                    }
                }
                return null;
            }

            @Override // java.lang.reflect.AnnotatedElement
            public Annotation[] getAnnotations() {
                return annotations;
            }

            @Override // java.lang.reflect.AnnotatedElement
            public Annotation[] getDeclaredAnnotations() {
                return annotations;
            }
        };
    }

    public static Set<String> getMetaAnnotationTypes(AnnotatedElement element, Class<? extends Annotation> annotationType) {
        return getMetaAnnotationTypes(element, element.getAnnotation(annotationType));
    }

    public static Set<String> getMetaAnnotationTypes(AnnotatedElement element, String annotationName) {
        return getMetaAnnotationTypes(element, AnnotationUtils.getAnnotation(element, annotationName));
    }

    private static Set<String> getMetaAnnotationTypes(AnnotatedElement element, @Nullable Annotation composed) {
        if (composed == null) {
            return Collections.emptySet();
        }
        try {
            final Set<String> types = new LinkedHashSet<>();
            searchWithGetSemantics(composed.annotationType(), Collections.emptySet(), null, null, new SimpleAnnotationProcessor<Object>(true) { // from class: org.springframework.core.annotation.AnnotatedElementUtils.2
                @Override // org.springframework.core.annotation.AnnotatedElementUtils.Processor
                @Nullable
                public Object process(@Nullable AnnotatedElement annotatedElement, Annotation annotation, int metaDepth) {
                    types.add(annotation.annotationType().getName());
                    return AnnotatedElementUtils.CONTINUE;
                }
            }, new HashSet(), 1);
            return types;
        } catch (Throwable ex) {
            AnnotationUtils.rethrowAnnotationConfigurationException(ex);
            throw new IllegalStateException("Failed to introspect annotations on " + element, ex);
        }
    }

    public static boolean hasMetaAnnotationTypes(AnnotatedElement element, Class<? extends Annotation> annotationType) {
        return hasMetaAnnotationTypes(element, annotationType, null);
    }

    public static boolean hasMetaAnnotationTypes(AnnotatedElement element, String annotationName) {
        return hasMetaAnnotationTypes(element, null, annotationName);
    }

    private static boolean hasMetaAnnotationTypes(AnnotatedElement element, @Nullable Class<? extends Annotation> annotationType, @Nullable String annotationName) {
        return Boolean.TRUE.equals(searchWithGetSemantics(element, annotationType, annotationName, new SimpleAnnotationProcessor<Boolean>() { // from class: org.springframework.core.annotation.AnnotatedElementUtils.3
            @Override // org.springframework.core.annotation.AnnotatedElementUtils.Processor
            @Nullable
            public Boolean process(@Nullable AnnotatedElement annotatedElement, Annotation annotation, int metaDepth) {
                return metaDepth > 0 ? Boolean.TRUE : AnnotatedElementUtils.CONTINUE;
            }
        }));
    }

    public static boolean isAnnotated(AnnotatedElement element, Class<? extends Annotation> annotationType) {
        if (element.isAnnotationPresent(annotationType)) {
            return true;
        }
        return Boolean.TRUE.equals(searchWithGetSemantics(element, annotationType, null, alwaysTrueAnnotationProcessor));
    }

    public static boolean isAnnotated(AnnotatedElement element, String annotationName) {
        return Boolean.TRUE.equals(searchWithGetSemantics(element, null, annotationName, alwaysTrueAnnotationProcessor));
    }

    @Nullable
    public static AnnotationAttributes getMergedAnnotationAttributes(AnnotatedElement element, Class<? extends Annotation> annotationType) {
        AnnotationAttributes attributes = (AnnotationAttributes) searchWithGetSemantics(element, annotationType, null, new MergedAnnotationAttributesProcessor());
        AnnotationUtils.postProcessAnnotationAttributes(element, attributes, false, false);
        return attributes;
    }

    @Nullable
    public static AnnotationAttributes getMergedAnnotationAttributes(AnnotatedElement element, String annotationName) {
        return getMergedAnnotationAttributes(element, annotationName, false, false);
    }

    @Nullable
    public static AnnotationAttributes getMergedAnnotationAttributes(AnnotatedElement element, String annotationName, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
        AnnotationAttributes attributes = (AnnotationAttributes) searchWithGetSemantics(element, null, annotationName, new MergedAnnotationAttributesProcessor(classValuesAsString, nestedAnnotationsAsMap));
        AnnotationUtils.postProcessAnnotationAttributes(element, attributes, classValuesAsString, nestedAnnotationsAsMap);
        return attributes;
    }

    @Nullable
    public static <A extends Annotation> A getMergedAnnotation(AnnotatedElement annotatedElement, Class<A> cls) {
        AnnotationAttributes mergedAnnotationAttributes;
        Annotation declaredAnnotation = annotatedElement.getDeclaredAnnotation(cls);
        if (declaredAnnotation != null) {
            return (A) AnnotationUtils.synthesizeAnnotation(declaredAnnotation, annotatedElement);
        }
        if (AnnotationUtils.hasPlainJavaAnnotationsOnly(annotatedElement) || (mergedAnnotationAttributes = getMergedAnnotationAttributes(annotatedElement, (Class<? extends Annotation>) cls)) == null) {
            return null;
        }
        return (A) AnnotationUtils.synthesizeAnnotation(mergedAnnotationAttributes, cls, annotatedElement);
    }

    public static <A extends Annotation> Set<A> getAllMergedAnnotations(AnnotatedElement element, Class<A> annotationType) {
        MergedAnnotationAttributesProcessor processor = new MergedAnnotationAttributesProcessor(false, false, true);
        searchWithGetSemantics(element, annotationType, null, processor);
        return postProcessAndSynthesizeAggregatedResults(element, processor.getAggregatedResults());
    }

    public static Set<Annotation> getAllMergedAnnotations(AnnotatedElement element, Set<Class<? extends Annotation>> annotationTypes) {
        MergedAnnotationAttributesProcessor processor = new MergedAnnotationAttributesProcessor(false, false, true);
        searchWithGetSemantics(element, annotationTypes, null, null, processor);
        return postProcessAndSynthesizeAggregatedResults(element, processor.getAggregatedResults());
    }

    public static <A extends Annotation> Set<A> getMergedRepeatableAnnotations(AnnotatedElement element, Class<A> annotationType) {
        return getMergedRepeatableAnnotations(element, annotationType, null);
    }

    public static <A extends Annotation> Set<A> getMergedRepeatableAnnotations(AnnotatedElement element, Class<A> annotationType, @Nullable Class<? extends Annotation> containerType) {
        if (containerType == null) {
            containerType = resolveContainerType(annotationType);
        } else {
            validateContainerType(annotationType, containerType);
        }
        MergedAnnotationAttributesProcessor processor = new MergedAnnotationAttributesProcessor(false, false, true);
        searchWithGetSemantics(element, Collections.singleton(annotationType), null, containerType, processor);
        return postProcessAndSynthesizeAggregatedResults(element, processor.getAggregatedResults());
    }

    @Nullable
    public static MultiValueMap<String, Object> getAllAnnotationAttributes(AnnotatedElement element, String annotationName) {
        return getAllAnnotationAttributes(element, annotationName, false, false);
    }

    @Nullable
    public static MultiValueMap<String, Object> getAllAnnotationAttributes(AnnotatedElement element, String annotationName, final boolean classValuesAsString, final boolean nestedAnnotationsAsMap) {
        final MultiValueMap<String, Object> attributesMap = new LinkedMultiValueMap<>();
        searchWithGetSemantics(element, null, annotationName, new SimpleAnnotationProcessor<Object>() { // from class: org.springframework.core.annotation.AnnotatedElementUtils.4
            @Override // org.springframework.core.annotation.AnnotatedElementUtils.Processor
            @Nullable
            public Object process(@Nullable AnnotatedElement annotatedElement, Annotation annotation, int metaDepth) {
                AnnotationAttributes annotationAttributes = AnnotationUtils.getAnnotationAttributes(annotation, classValuesAsString, nestedAnnotationsAsMap);
                MultiValueMap multiValueMap = attributesMap;
                multiValueMap.getClass();
                annotationAttributes.forEach((v1, v2) -> {
                    r1.add(v1, v2);
                });
                return AnnotatedElementUtils.CONTINUE;
            }
        });
        if (attributesMap.isEmpty()) {
            return null;
        }
        return attributesMap;
    }

    public static boolean hasAnnotation(AnnotatedElement element, Class<? extends Annotation> annotationType) {
        if (element.isAnnotationPresent(annotationType)) {
            return true;
        }
        return Boolean.TRUE.equals(searchWithFindSemantics(element, annotationType, null, alwaysTrueAnnotationProcessor));
    }

    @Nullable
    public static AnnotationAttributes findMergedAnnotationAttributes(AnnotatedElement element, Class<? extends Annotation> annotationType, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
        AnnotationAttributes attributes = (AnnotationAttributes) searchWithFindSemantics(element, annotationType, null, new MergedAnnotationAttributesProcessor(classValuesAsString, nestedAnnotationsAsMap));
        AnnotationUtils.postProcessAnnotationAttributes(element, attributes, classValuesAsString, nestedAnnotationsAsMap);
        return attributes;
    }

    @Nullable
    public static AnnotationAttributes findMergedAnnotationAttributes(AnnotatedElement element, String annotationName, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
        AnnotationAttributes attributes = (AnnotationAttributes) searchWithFindSemantics(element, null, annotationName, new MergedAnnotationAttributesProcessor(classValuesAsString, nestedAnnotationsAsMap));
        AnnotationUtils.postProcessAnnotationAttributes(element, attributes, classValuesAsString, nestedAnnotationsAsMap);
        return attributes;
    }

    @Nullable
    public static <A extends Annotation> A findMergedAnnotation(AnnotatedElement annotatedElement, Class<A> cls) {
        AnnotationAttributes annotationAttributesFindMergedAnnotationAttributes;
        Annotation declaredAnnotation = annotatedElement.getDeclaredAnnotation(cls);
        if (declaredAnnotation != null) {
            return (A) AnnotationUtils.synthesizeAnnotation(declaredAnnotation, annotatedElement);
        }
        if (AnnotationUtils.hasPlainJavaAnnotationsOnly(annotatedElement) || (annotationAttributesFindMergedAnnotationAttributes = findMergedAnnotationAttributes(annotatedElement, (Class<? extends Annotation>) cls, false, false)) == null) {
            return null;
        }
        return (A) AnnotationUtils.synthesizeAnnotation(annotationAttributesFindMergedAnnotationAttributes, cls, annotatedElement);
    }

    public static <A extends Annotation> Set<A> findAllMergedAnnotations(AnnotatedElement element, Class<A> annotationType) {
        MergedAnnotationAttributesProcessor processor = new MergedAnnotationAttributesProcessor(false, false, true);
        searchWithFindSemantics(element, annotationType, null, processor);
        return postProcessAndSynthesizeAggregatedResults(element, processor.getAggregatedResults());
    }

    public static Set<Annotation> findAllMergedAnnotations(AnnotatedElement element, Set<Class<? extends Annotation>> annotationTypes) {
        MergedAnnotationAttributesProcessor processor = new MergedAnnotationAttributesProcessor(false, false, true);
        searchWithFindSemantics(element, annotationTypes, null, null, processor);
        return postProcessAndSynthesizeAggregatedResults(element, processor.getAggregatedResults());
    }

    public static <A extends Annotation> Set<A> findMergedRepeatableAnnotations(AnnotatedElement element, Class<A> annotationType) {
        return findMergedRepeatableAnnotations(element, annotationType, null);
    }

    public static <A extends Annotation> Set<A> findMergedRepeatableAnnotations(AnnotatedElement element, Class<A> annotationType, @Nullable Class<? extends Annotation> containerType) {
        if (containerType == null) {
            containerType = resolveContainerType(annotationType);
        } else {
            validateContainerType(annotationType, containerType);
        }
        MergedAnnotationAttributesProcessor processor = new MergedAnnotationAttributesProcessor(false, false, true);
        searchWithFindSemantics(element, Collections.singleton(annotationType), null, containerType, processor);
        return postProcessAndSynthesizeAggregatedResults(element, processor.getAggregatedResults());
    }

    @Nullable
    private static <T> T searchWithGetSemantics(AnnotatedElement annotatedElement, @Nullable Class<? extends Annotation> cls, @Nullable String str, Processor<T> processor) {
        return (T) searchWithGetSemantics(annotatedElement, cls != null ? Collections.singleton(cls) : Collections.emptySet(), str, null, processor);
    }

    @Nullable
    private static <T> T searchWithGetSemantics(AnnotatedElement annotatedElement, Set<Class<? extends Annotation>> set, @Nullable String str, @Nullable Class<? extends Annotation> cls, Processor<T> processor) {
        try {
            return (T) searchWithGetSemantics(annotatedElement, set, str, cls, processor, new HashSet(), 0);
        } catch (Throwable th) {
            AnnotationUtils.rethrowAnnotationConfigurationException(th);
            throw new IllegalStateException("Failed to introspect annotations on " + annotatedElement, th);
        }
    }

    @Nullable
    private static <T> T searchWithGetSemantics(AnnotatedElement annotatedElement, Set<Class<? extends Annotation>> set, @Nullable String str, @Nullable Class<? extends Annotation> cls, Processor<T> processor, Set<AnnotatedElement> set2, int i) {
        Class<? super T> superclass;
        if (set2.add(annotatedElement)) {
            try {
                List listAsList = Arrays.asList(AnnotationUtils.getDeclaredAnnotations(annotatedElement));
                T t = (T) searchWithGetSemanticsInAnnotations(annotatedElement, listAsList, set, str, cls, processor, set2, i);
                if (t != null) {
                    return t;
                }
                if ((annotatedElement instanceof Class) && (superclass = ((Class) annotatedElement).getSuperclass()) != null && superclass != Object.class) {
                    LinkedList linkedList = new LinkedList();
                    for (Annotation annotation : annotatedElement.getAnnotations()) {
                        if (!listAsList.contains(annotation)) {
                            linkedList.add(annotation);
                        }
                    }
                    T t2 = (T) searchWithGetSemanticsInAnnotations(annotatedElement, linkedList, set, str, cls, processor, set2, i);
                    if (t2 != null) {
                        return t2;
                    }
                    return null;
                }
                return null;
            } catch (Throwable th) {
                AnnotationUtils.handleIntrospectionFailure(annotatedElement, th);
                return null;
            }
        }
        return null;
    }

    @Nullable
    private static <T> T searchWithGetSemanticsInAnnotations(@Nullable AnnotatedElement annotatedElement, List<Annotation> list, Set<Class<? extends Annotation>> set, @Nullable String str, @Nullable Class<? extends Annotation> cls, Processor<T> processor, Set<AnnotatedElement> set2, int i) {
        T t;
        for (Annotation annotation : list) {
            Class<? extends Annotation> clsAnnotationType = annotation.annotationType();
            if (!AnnotationUtils.isInJavaLangAnnotationPackage(clsAnnotationType)) {
                if (set.contains(clsAnnotationType) || clsAnnotationType.getName().equals(str) || processor.alwaysProcesses()) {
                    T tProcess = processor.process(annotatedElement, annotation, i);
                    if (tProcess == null) {
                        continue;
                    } else if (processor.aggregates() && i == 0) {
                        processor.getAggregatedResults().add(tProcess);
                    } else {
                        return tProcess;
                    }
                } else if (clsAnnotationType == cls) {
                    for (Annotation annotation2 : getRawAnnotationsFromContainer(annotatedElement, annotation)) {
                        T tProcess2 = processor.process(annotatedElement, annotation2, i);
                        if (tProcess2 != null) {
                            processor.getAggregatedResults().add(tProcess2);
                        }
                    }
                }
            }
        }
        for (Annotation annotation3 : list) {
            Class<? extends Annotation> clsAnnotationType2 = annotation3.annotationType();
            if (!AnnotationUtils.hasPlainJavaAnnotationsOnly(clsAnnotationType2) && (t = (T) searchWithGetSemantics(clsAnnotationType2, set, str, cls, processor, set2, i + 1)) != null) {
                processor.postProcess(annotatedElement, annotation3, t);
                if (processor.aggregates() && i == 0) {
                    processor.getAggregatedResults().add(t);
                } else {
                    return t;
                }
            }
        }
        return null;
    }

    @Nullable
    private static <T> T searchWithFindSemantics(AnnotatedElement annotatedElement, @Nullable Class<? extends Annotation> cls, @Nullable String str, Processor<T> processor) {
        return (T) searchWithFindSemantics(annotatedElement, cls != null ? Collections.singleton(cls) : Collections.emptySet(), str, null, processor);
    }

    @Nullable
    private static <T> T searchWithFindSemantics(AnnotatedElement annotatedElement, Set<Class<? extends Annotation>> set, @Nullable String str, @Nullable Class<? extends Annotation> cls, Processor<T> processor) {
        if (cls != null && !processor.aggregates()) {
            throw new IllegalArgumentException("Searches for repeatable annotations must supply an aggregating Processor");
        }
        try {
            return (T) searchWithFindSemantics(annotatedElement, set, str, cls, processor, new HashSet(), 0);
        } catch (Throwable th) {
            AnnotationUtils.rethrowAnnotationConfigurationException(th);
            throw new IllegalStateException("Failed to introspect annotations on " + annotatedElement, th);
        }
    }

    @Nullable
    private static <T> T searchWithFindSemantics(AnnotatedElement annotatedElement, Set<Class<? extends Annotation>> set, @Nullable String str, @Nullable Class<? extends Annotation> cls, Processor<T> processor, Set<AnnotatedElement> set2, int i) {
        T t;
        T t2;
        T t3;
        T t4;
        T t5;
        T t6;
        if (set2.add(annotatedElement)) {
            try {
                Annotation[] declaredAnnotations = AnnotationUtils.getDeclaredAnnotations(annotatedElement);
                if (declaredAnnotations.length > 0) {
                    ArrayList arrayList = processor.aggregates() ? new ArrayList() : null;
                    for (Annotation annotation : declaredAnnotations) {
                        Class<? extends Annotation> clsAnnotationType = annotation.annotationType();
                        if (!AnnotationUtils.isInJavaLangAnnotationPackage(clsAnnotationType)) {
                            if (set.contains(clsAnnotationType) || clsAnnotationType.getName().equals(str) || processor.alwaysProcesses()) {
                                T tProcess = processor.process(annotatedElement, annotation, i);
                                if (tProcess != null) {
                                    if (arrayList != null && i == 0) {
                                        arrayList.add(tProcess);
                                    } else {
                                        return tProcess;
                                    }
                                }
                            } else if (clsAnnotationType == cls) {
                                for (Annotation annotation2 : getRawAnnotationsFromContainer(annotatedElement, annotation)) {
                                    T tProcess2 = processor.process(annotatedElement, annotation2, i);
                                    if (arrayList != null && tProcess2 != null) {
                                        arrayList.add(tProcess2);
                                    }
                                }
                            }
                        }
                    }
                    for (Annotation annotation3 : declaredAnnotations) {
                        Class<? extends Annotation> clsAnnotationType2 = annotation3.annotationType();
                        if (!AnnotationUtils.hasPlainJavaAnnotationsOnly(clsAnnotationType2) && (t6 = (T) searchWithFindSemantics(clsAnnotationType2, set, str, cls, processor, set2, i + 1)) != null) {
                            processor.postProcess(clsAnnotationType2, annotation3, t6);
                            if (arrayList != null && i == 0) {
                                arrayList.add(t6);
                            } else {
                                return t6;
                            }
                        }
                    }
                    if (!CollectionUtils.isEmpty(arrayList)) {
                        processor.getAggregatedResults().addAll(0, arrayList);
                    }
                }
                if (annotatedElement instanceof Method) {
                    Method method = (Method) annotatedElement;
                    Method methodFindBridgedMethod = BridgeMethodResolver.findBridgedMethod(method);
                    if (methodFindBridgedMethod != method && (t5 = (T) searchWithFindSemantics(methodFindBridgedMethod, set, str, cls, processor, set2, i)) != null) {
                        return t5;
                    }
                    Class<?>[] interfaces = method.getDeclaringClass().getInterfaces();
                    if (interfaces.length > 0 && (t4 = (T) searchOnInterfaces(method, set, str, cls, processor, set2, i, interfaces)) != null) {
                        return t4;
                    }
                    Class<?> declaringClass = method.getDeclaringClass();
                    do {
                        declaringClass = declaringClass.getSuperclass();
                        if (declaringClass != null && declaringClass != Object.class) {
                            Set<Method> annotatedMethodsInBaseType = AnnotationUtils.getAnnotatedMethodsInBaseType(declaringClass);
                            if (!annotatedMethodsInBaseType.isEmpty()) {
                                for (Method method2 : annotatedMethodsInBaseType) {
                                    if (AnnotationUtils.isOverride(method, method2) && (t3 = (T) searchWithFindSemantics(BridgeMethodResolver.findBridgedMethod(method2), set, str, cls, processor, set2, i)) != null) {
                                        return t3;
                                    }
                                }
                            }
                            t2 = (T) searchOnInterfaces(method, set, str, cls, processor, set2, i, declaringClass.getInterfaces());
                        }
                        return null;
                    } while (t2 == null);
                    return t2;
                }
                if (annotatedElement instanceof Class) {
                    Class cls2 = (Class) annotatedElement;
                    if (!Annotation.class.isAssignableFrom(cls2)) {
                        for (Class<?> cls3 : cls2.getInterfaces()) {
                            T t7 = (T) searchWithFindSemantics(cls3, set, str, cls, processor, set2, i);
                            if (t7 != null) {
                                return t7;
                            }
                        }
                        Class<? super T> superclass = cls2.getSuperclass();
                        if (superclass != null && superclass != Object.class && (t = (T) searchWithFindSemantics(superclass, set, str, cls, processor, set2, i)) != null) {
                            return t;
                        }
                        return null;
                    }
                    return null;
                }
                return null;
            } catch (Throwable th) {
                AnnotationUtils.handleIntrospectionFailure(annotatedElement, th);
                return null;
            }
        }
        return null;
    }

    @Nullable
    private static <T> T searchOnInterfaces(Method method, Set<Class<? extends Annotation>> set, @Nullable String str, @Nullable Class<? extends Annotation> cls, Processor<T> processor, Set<AnnotatedElement> set2, int i, Class<?>[] clsArr) {
        T t;
        for (Class<?> cls2 : clsArr) {
            Set<Method> annotatedMethodsInBaseType = AnnotationUtils.getAnnotatedMethodsInBaseType(cls2);
            if (!annotatedMethodsInBaseType.isEmpty()) {
                for (Method method2 : annotatedMethodsInBaseType) {
                    if (AnnotationUtils.isOverride(method, method2) && (t = (T) searchWithFindSemantics(method2, set, str, cls, processor, set2, i)) != null) {
                        return t;
                    }
                }
            }
        }
        return null;
    }

    private static <A extends Annotation> A[] getRawAnnotationsFromContainer(@Nullable AnnotatedElement annotatedElement, Annotation annotation) {
        try {
            A[] aArr = (A[]) ((Annotation[]) AnnotationUtils.getValue(annotation));
            if (aArr != null) {
                return aArr;
            }
        } catch (Throwable th) {
            AnnotationUtils.handleIntrospectionFailure(annotatedElement, th);
        }
        return (A[]) EMPTY_ANNOTATION_ARRAY;
    }

    private static Class<? extends Annotation> resolveContainerType(Class<? extends Annotation> annotationType) {
        Class<? extends Annotation> containerType = AnnotationUtils.resolveContainerAnnotationType(annotationType);
        if (containerType == null) {
            throw new IllegalArgumentException("Annotation type must be a repeatable annotation: failed to resolve container type for " + annotationType.getName());
        }
        return containerType;
    }

    private static void validateContainerType(Class<? extends Annotation> annotationType, Class<? extends Annotation> containerType) {
        try {
            Method method = containerType.getDeclaredMethod("value", new Class[0]);
            Class<?> returnType = method.getReturnType();
            if (!returnType.isArray() || returnType.getComponentType() != annotationType) {
                String msg = String.format("Container type [%s] must declare a 'value' attribute for an array of type [%s]", containerType.getName(), annotationType.getName());
                throw new AnnotationConfigurationException(msg);
            }
        } catch (Throwable ex) {
            AnnotationUtils.rethrowAnnotationConfigurationException(ex);
            String msg2 = String.format("Invalid declaration of container type [%s] for repeatable annotation [%s]", containerType.getName(), annotationType.getName());
            throw new AnnotationConfigurationException(msg2, ex);
        }
    }

    private static <A extends Annotation> Set<A> postProcessAndSynthesizeAggregatedResults(AnnotatedElement element, List<AnnotationAttributes> aggregatedResults) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (AnnotationAttributes attributes : aggregatedResults) {
            AnnotationUtils.postProcessAnnotationAttributes(element, attributes, false, false);
            Class<? extends Annotation> annType = attributes.annotationType();
            if (annType != null) {
                linkedHashSet.add(AnnotationUtils.synthesizeAnnotation(attributes, annType, element));
            }
        }
        return linkedHashSet;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/annotation/AnnotatedElementUtils$SimpleAnnotationProcessor.class */
    private static abstract class SimpleAnnotationProcessor<T> implements Processor<T> {
        private final boolean alwaysProcesses;

        public SimpleAnnotationProcessor() {
            this(false);
        }

        public SimpleAnnotationProcessor(boolean alwaysProcesses) {
            this.alwaysProcesses = alwaysProcesses;
        }

        @Override // org.springframework.core.annotation.AnnotatedElementUtils.Processor
        public final boolean alwaysProcesses() {
            return this.alwaysProcesses;
        }

        @Override // org.springframework.core.annotation.AnnotatedElementUtils.Processor
        public final void postProcess(@Nullable AnnotatedElement annotatedElement, Annotation annotation, T result) {
        }

        @Override // org.springframework.core.annotation.AnnotatedElementUtils.Processor
        public final boolean aggregates() {
            return false;
        }

        @Override // org.springframework.core.annotation.AnnotatedElementUtils.Processor
        public final List<T> getAggregatedResults() {
            throw new UnsupportedOperationException("SimpleAnnotationProcessor does not support aggregated results");
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/annotation/AnnotatedElementUtils$AlwaysTrueBooleanAnnotationProcessor.class */
    static class AlwaysTrueBooleanAnnotationProcessor extends SimpleAnnotationProcessor<Boolean> {
        AlwaysTrueBooleanAnnotationProcessor() {
        }

        @Override // org.springframework.core.annotation.AnnotatedElementUtils.Processor
        public final Boolean process(@Nullable AnnotatedElement annotatedElement, Annotation annotation, int metaDepth) {
            return Boolean.TRUE;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/annotation/AnnotatedElementUtils$MergedAnnotationAttributesProcessor.class */
    private static class MergedAnnotationAttributesProcessor implements Processor<AnnotationAttributes> {
        private final boolean classValuesAsString;
        private final boolean nestedAnnotationsAsMap;
        private final boolean aggregates;
        private final List<AnnotationAttributes> aggregatedResults;

        MergedAnnotationAttributesProcessor() {
            this(false, false, false);
        }

        MergedAnnotationAttributesProcessor(boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
            this(classValuesAsString, nestedAnnotationsAsMap, false);
        }

        MergedAnnotationAttributesProcessor(boolean classValuesAsString, boolean nestedAnnotationsAsMap, boolean aggregates) {
            this.classValuesAsString = classValuesAsString;
            this.nestedAnnotationsAsMap = nestedAnnotationsAsMap;
            this.aggregates = aggregates;
            this.aggregatedResults = aggregates ? new ArrayList<>() : Collections.emptyList();
        }

        @Override // org.springframework.core.annotation.AnnotatedElementUtils.Processor
        public boolean alwaysProcesses() {
            return false;
        }

        @Override // org.springframework.core.annotation.AnnotatedElementUtils.Processor
        public boolean aggregates() {
            return this.aggregates;
        }

        @Override // org.springframework.core.annotation.AnnotatedElementUtils.Processor
        public List<AnnotationAttributes> getAggregatedResults() {
            return this.aggregatedResults;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.springframework.core.annotation.AnnotatedElementUtils.Processor
        @Nullable
        public AnnotationAttributes process(@Nullable AnnotatedElement annotatedElement, Annotation annotation, int metaDepth) {
            return AnnotationUtils.retrieveAnnotationAttributes(annotatedElement, annotation, this.classValuesAsString, this.nestedAnnotationsAsMap);
        }

        @Override // org.springframework.core.annotation.AnnotatedElementUtils.Processor
        public void postProcess(@Nullable AnnotatedElement element, Annotation annotation, AnnotationAttributes attributes) {
            Annotation annotation2 = AnnotationUtils.synthesizeAnnotation(annotation, element);
            Class<? extends Annotation> targetAnnotationType = attributes.annotationType();
            Set<String> valuesAlreadyReplaced = new HashSet<>();
            for (Method attributeMethod : AnnotationUtils.getAttributeMethods(annotation2.annotationType())) {
                String attributeName = attributeMethod.getName();
                String attributeOverrideName = AnnotationUtils.getAttributeOverrideName(attributeMethod, targetAnnotationType);
                if (attributeOverrideName != null) {
                    if (!valuesAlreadyReplaced.contains(attributeOverrideName)) {
                        List<String> targetAttributeNames = new ArrayList<>();
                        targetAttributeNames.add(attributeOverrideName);
                        valuesAlreadyReplaced.add(attributeOverrideName);
                        List<String> aliases = AnnotationUtils.getAttributeAliasMap(targetAnnotationType).get(attributeOverrideName);
                        if (aliases != null) {
                            for (String alias : aliases) {
                                if (!valuesAlreadyReplaced.contains(alias)) {
                                    targetAttributeNames.add(alias);
                                    valuesAlreadyReplaced.add(alias);
                                }
                            }
                        }
                        overrideAttributes(element, annotation2, attributes, attributeName, targetAttributeNames);
                    }
                } else if (!"value".equals(attributeName) && attributes.containsKey(attributeName)) {
                    overrideAttribute(element, annotation2, attributes, attributeName, attributeName);
                }
            }
        }

        private void overrideAttributes(@Nullable AnnotatedElement element, Annotation annotation, AnnotationAttributes attributes, String sourceAttributeName, List<String> targetAttributeNames) {
            Object adaptedValue = getAdaptedValue(element, annotation, sourceAttributeName);
            for (String targetAttributeName : targetAttributeNames) {
                attributes.put(targetAttributeName, adaptedValue);
            }
        }

        private void overrideAttribute(@Nullable AnnotatedElement element, Annotation annotation, AnnotationAttributes attributes, String sourceAttributeName, String targetAttributeName) {
            attributes.put(targetAttributeName, getAdaptedValue(element, annotation, sourceAttributeName));
        }

        @Nullable
        private Object getAdaptedValue(@Nullable AnnotatedElement element, Annotation annotation, String sourceAttributeName) {
            Object value = AnnotationUtils.getValue(annotation, sourceAttributeName);
            return AnnotationUtils.adaptValue(element, value, this.classValuesAsString, this.nestedAnnotationsAsMap);
        }
    }
}
