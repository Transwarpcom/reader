package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/annotation/DefaultAnnotationAttributeExtractor.class */
class DefaultAnnotationAttributeExtractor extends AbstractAliasAwareAnnotationAttributeExtractor<Annotation> {
    DefaultAnnotationAttributeExtractor(Annotation annotation, @Nullable Object annotatedElement) {
        super(annotation.annotationType(), annotatedElement, annotation);
    }

    @Override // org.springframework.core.annotation.AbstractAliasAwareAnnotationAttributeExtractor
    @Nullable
    protected Object getRawAttributeValue(Method attributeMethod) {
        ReflectionUtils.makeAccessible(attributeMethod);
        return ReflectionUtils.invokeMethod(attributeMethod, getSource());
    }

    @Override // org.springframework.core.annotation.AbstractAliasAwareAnnotationAttributeExtractor
    @Nullable
    protected Object getRawAttributeValue(String attributeName) {
        Method attributeMethod = ReflectionUtils.findMethod(getAnnotationType(), attributeName);
        if (attributeMethod != null) {
            return getRawAttributeValue(attributeMethod);
        }
        return null;
    }
}
