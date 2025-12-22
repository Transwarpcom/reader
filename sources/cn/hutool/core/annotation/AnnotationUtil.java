package cn.hutool.core.annotation;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/annotation/AnnotationUtil.class */
public class AnnotationUtil {
    public static CombinationAnnotationElement toCombination(AnnotatedElement annotationEle) {
        if (annotationEle instanceof CombinationAnnotationElement) {
            return (CombinationAnnotationElement) annotationEle;
        }
        return new CombinationAnnotationElement(annotationEle);
    }

    public static Annotation[] getAnnotations(AnnotatedElement annotationEle, boolean isToCombination) {
        if (null == annotationEle) {
            return null;
        }
        return (isToCombination ? toCombination(annotationEle) : annotationEle).getAnnotations();
    }

    public static <A extends Annotation> A getAnnotation(AnnotatedElement annotatedElement, Class<A> cls) {
        if (null == annotatedElement) {
            return null;
        }
        return (A) toCombination(annotatedElement).getAnnotation(cls);
    }

    public static boolean hasAnnotation(AnnotatedElement annotationEle, Class<? extends Annotation> annotationType) {
        return null != getAnnotation(annotationEle, annotationType);
    }

    public static <T> T getAnnotationValue(AnnotatedElement annotatedElement, Class<? extends Annotation> cls) throws UtilException {
        return (T) getAnnotationValue(annotatedElement, cls, "value");
    }

    public static <T> T getAnnotationValue(AnnotatedElement annotatedElement, Class<? extends Annotation> cls, String str) throws UtilException {
        Method methodOfObj;
        Annotation annotation = getAnnotation(annotatedElement, cls);
        if (null == annotation || null == (methodOfObj = ReflectUtil.getMethodOfObj(annotation, str, new Object[0]))) {
            return null;
        }
        return (T) ReflectUtil.invoke(annotation, methodOfObj, new Object[0]);
    }

    public static Map<String, Object> getAnnotationValueMap(AnnotatedElement annotationEle, Class<? extends Annotation> annotationType) throws UtilException, SecurityException {
        Annotation annotation = getAnnotation(annotationEle, annotationType);
        if (null == annotation) {
            return null;
        }
        Method[] methods = ReflectUtil.getMethods(annotationType, t -> {
            if (ArrayUtil.isEmpty((Object[]) t.getParameterTypes())) {
                String name = t.getName();
                return false == IdentityNamingStrategy.HASH_CODE_KEY.equals(name) && false == "toString".equals(name) && false == "annotationType".equals(name);
            }
            return false;
        });
        HashMap<String, Object> result = new HashMap<>(methods.length, 1.0f);
        for (Method method : methods) {
            result.put(method.getName(), ReflectUtil.invoke(annotation, method, new Object[0]));
        }
        return result;
    }

    public static RetentionPolicy getRetentionPolicy(Class<? extends Annotation> annotationType) {
        Retention retention = (Retention) annotationType.getAnnotation(Retention.class);
        if (null == retention) {
            return RetentionPolicy.CLASS;
        }
        return retention.value();
    }

    public static ElementType[] getTargetType(Class<? extends Annotation> annotationType) {
        Target target = (Target) annotationType.getAnnotation(Target.class);
        if (null == target) {
            return new ElementType[]{ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE, ElementType.PACKAGE};
        }
        return target.value();
    }

    public static boolean isDocumented(Class<? extends Annotation> annotationType) {
        return annotationType.isAnnotationPresent(Documented.class);
    }

    public static boolean isInherited(Class<? extends Annotation> annotationType) {
        return annotationType.isAnnotationPresent(Inherited.class);
    }

    public static void setValue(Annotation annotation, String annotationField, Object value) {
        Map memberValues = (Map) ReflectUtil.getFieldValue(Proxy.getInvocationHandler(annotation), "memberValues");
        memberValues.put(annotationField, value);
    }

    public static <T extends Annotation> T getAnnotationAlias(AnnotatedElement annotationEle, Class<T> annotationType) {
        return (T) Proxy.newProxyInstance(annotationType.getClassLoader(), new Class[]{annotationType}, new AnnotationProxy(getAnnotation(annotationEle, annotationType)));
    }
}
