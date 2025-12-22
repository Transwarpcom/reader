package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Constants;

/* compiled from: ReflectKotlinClass.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/components/ReflectClassStructure.class */
final class ReflectClassStructure {

    @NotNull
    public static final ReflectClassStructure INSTANCE = new ReflectClassStructure();

    private ReflectClassStructure() {
    }

    public final void loadClassAnnotations(@NotNull Class<?> klass, @NotNull KotlinJvmBinaryClass.AnnotationVisitor visitor) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        Annotation[] declaredAnnotations = klass.getDeclaredAnnotations();
        Intrinsics.checkNotNullExpressionValue(declaredAnnotations, "klass.declaredAnnotations");
        int i = 0;
        int length = declaredAnnotations.length;
        while (i < length) {
            Annotation annotation = declaredAnnotations[i];
            i++;
            Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
            processAnnotation(visitor, annotation);
        }
        visitor.visitEnd();
    }

    public final void visitMembers(@NotNull Class<?> klass, @NotNull KotlinJvmBinaryClass.MemberVisitor memberVisitor) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        Intrinsics.checkNotNullParameter(memberVisitor, "memberVisitor");
        loadMethodAnnotations(klass, memberVisitor);
        loadConstructorAnnotations(klass, memberVisitor);
        loadFieldAnnotations(klass, memberVisitor);
    }

    private final void loadMethodAnnotations(Class<?> cls, KotlinJvmBinaryClass.MemberVisitor memberVisitor) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Method[] declaredMethods = cls.getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(declaredMethods, "klass.declaredMethods");
        int i = 0;
        int length = declaredMethods.length;
        while (i < length) {
            Method method = declaredMethods[i];
            i++;
            Name nameIdentifier = Name.identifier(method.getName());
            Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(method.name)");
            SignatureSerializer signatureSerializer = SignatureSerializer.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(method, "method");
            KotlinJvmBinaryClass.MethodAnnotationVisitor visitor = memberVisitor.visitMethod(nameIdentifier, signatureSerializer.methodDesc(method));
            if (visitor != null) {
                Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                Intrinsics.checkNotNullExpressionValue(declaredAnnotations, "method.declaredAnnotations");
                int i2 = 0;
                int length2 = declaredAnnotations.length;
                while (i2 < length2) {
                    Annotation annotation = declaredAnnotations[i2];
                    i2++;
                    Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
                    processAnnotation(visitor, annotation);
                }
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                Intrinsics.checkNotNullExpressionValue(parameterAnnotations, "method.parameterAnnotations");
                Annotation[][] annotationArr = parameterAnnotations;
                int i3 = 0;
                int length3 = annotationArr.length;
                while (i3 < length3) {
                    int parameterIndex = i3;
                    Annotation[] annotations = annotationArr[i3];
                    i3++;
                    Intrinsics.checkNotNullExpressionValue(annotations, "annotations");
                    int i4 = 0;
                    int length4 = annotations.length;
                    while (i4 < length4) {
                        Annotation annotation2 = annotations[i4];
                        i4++;
                        Class annotationType = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation2));
                        ClassId classId = ReflectClassUtilKt.getClassId(annotationType);
                        Intrinsics.checkNotNullExpressionValue(annotation2, "annotation");
                        KotlinJvmBinaryClass.AnnotationArgumentVisitor it = visitor.visitParameterAnnotation(parameterIndex, classId, new ReflectAnnotationSource(annotation2));
                        if (it != null) {
                            INSTANCE.processAnnotationArguments(it, annotation2, annotationType);
                        }
                    }
                }
                visitor.visitEnd();
            }
        }
    }

    private final void loadConstructorAnnotations(Class<?> cls, KotlinJvmBinaryClass.MemberVisitor memberVisitor) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Constructor[] declaredConstructors = cls.getDeclaredConstructors();
        Intrinsics.checkNotNullExpressionValue(declaredConstructors, "klass.declaredConstructors");
        int i = 0;
        int length = declaredConstructors.length;
        while (i < length) {
            Constructor constructor = declaredConstructors[i];
            i++;
            Name nameSpecial = Name.special(Constants.CONSTRUCTOR_NAME);
            Intrinsics.checkNotNullExpressionValue(nameSpecial, "special(\"<init>\")");
            SignatureSerializer signatureSerializer = SignatureSerializer.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(constructor, "constructor");
            KotlinJvmBinaryClass.MethodAnnotationVisitor visitor = memberVisitor.visitMethod(nameSpecial, signatureSerializer.constructorDesc(constructor));
            if (visitor != null) {
                Annotation[] declaredAnnotations = constructor.getDeclaredAnnotations();
                Intrinsics.checkNotNullExpressionValue(declaredAnnotations, "constructor.declaredAnnotations");
                int i2 = 0;
                int length2 = declaredAnnotations.length;
                while (i2 < length2) {
                    Annotation annotation = declaredAnnotations[i2];
                    i2++;
                    Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
                    processAnnotation(visitor, annotation);
                }
                Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
                Intrinsics.checkNotNullExpressionValue(parameterAnnotations, "parameterAnnotations");
                if (!(parameterAnnotations.length == 0)) {
                    int shift = constructor.getParameterTypes().length - parameterAnnotations.length;
                    int i3 = 0;
                    int length3 = parameterAnnotations.length;
                    while (i3 < length3) {
                        int parameterIndex = i3;
                        Annotation[] annotations = parameterAnnotations[i3];
                        i3++;
                        Intrinsics.checkNotNullExpressionValue(annotations, "annotations");
                        int i4 = 0;
                        int length4 = annotations.length;
                        while (i4 < length4) {
                            Annotation annotation2 = annotations[i4];
                            i4++;
                            Class annotationType = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation2));
                            ClassId classId = ReflectClassUtilKt.getClassId(annotationType);
                            Intrinsics.checkNotNullExpressionValue(annotation2, "annotation");
                            KotlinJvmBinaryClass.AnnotationArgumentVisitor it = visitor.visitParameterAnnotation(parameterIndex + shift, classId, new ReflectAnnotationSource(annotation2));
                            if (it != null) {
                                INSTANCE.processAnnotationArguments(it, annotation2, annotationType);
                            }
                        }
                    }
                }
                visitor.visitEnd();
            }
        }
    }

    private final void loadFieldAnnotations(Class<?> cls, KotlinJvmBinaryClass.MemberVisitor memberVisitor) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Field[] declaredFields = cls.getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(declaredFields, "klass.declaredFields");
        int i = 0;
        int length = declaredFields.length;
        while (i < length) {
            Field field = declaredFields[i];
            i++;
            Name nameIdentifier = Name.identifier(field.getName());
            Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(field.name)");
            SignatureSerializer signatureSerializer = SignatureSerializer.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(field, "field");
            KotlinJvmBinaryClass.AnnotationVisitor visitor = memberVisitor.visitField(nameIdentifier, signatureSerializer.fieldDesc(field), null);
            if (visitor != null) {
                Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                Intrinsics.checkNotNullExpressionValue(declaredAnnotations, "field.declaredAnnotations");
                int i2 = 0;
                int length2 = declaredAnnotations.length;
                while (i2 < length2) {
                    Annotation annotation = declaredAnnotations[i2];
                    i2++;
                    Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
                    processAnnotation(visitor, annotation);
                }
                visitor.visitEnd();
            }
        }
    }

    private final void processAnnotation(KotlinJvmBinaryClass.AnnotationVisitor visitor, Annotation annotation) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Class annotationType = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation));
        KotlinJvmBinaryClass.AnnotationArgumentVisitor it = visitor.visitAnnotation(ReflectClassUtilKt.getClassId(annotationType), new ReflectAnnotationSource(annotation));
        if (it != null) {
            INSTANCE.processAnnotationArguments(it, annotation, annotationType);
        }
    }

    private final void processAnnotationArguments(KotlinJvmBinaryClass.AnnotationArgumentVisitor visitor, Annotation annotation, Class<?> cls) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Method[] declaredMethods = cls.getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(declaredMethods, "annotationType.declaredMethods");
        int i = 0;
        int length = declaredMethods.length;
        while (i < length) {
            Method method = declaredMethods[i];
            i++;
            try {
                Object value = method.invoke(annotation, new Object[0]);
                Intrinsics.checkNotNull(value);
                Name nameIdentifier = Name.identifier(method.getName());
                Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(method.name)");
                processAnnotationArgumentValue(visitor, nameIdentifier, value);
            } catch (IllegalAccessException e) {
            }
        }
        visitor.visitEnd();
    }

    private final ClassLiteralValue classLiteralValue(Class<?> cls) {
        Class currentClass = cls;
        int dimensions = 0;
        while (currentClass.isArray()) {
            dimensions++;
            Class componentType = currentClass.getComponentType();
            Intrinsics.checkNotNullExpressionValue(componentType, "currentClass.componentType");
            currentClass = componentType;
        }
        if (currentClass.isPrimitive()) {
            if (Intrinsics.areEqual(currentClass, Void.TYPE)) {
                ClassId classId = ClassId.topLevel(StandardNames.FqNames.unit.toSafe());
                Intrinsics.checkNotNullExpressionValue(classId, "topLevel(StandardNames.FqNames.unit.toSafe())");
                return new ClassLiteralValue(classId, dimensions);
            }
            PrimitiveType primitiveType = JvmPrimitiveType.get(currentClass.getName()).getPrimitiveType();
            Intrinsics.checkNotNullExpressionValue(primitiveType, "get(currentClass.name).primitiveType");
            if (dimensions > 0) {
                ClassId classId2 = ClassId.topLevel(primitiveType.getArrayTypeFqName());
                Intrinsics.checkNotNullExpressionValue(classId2, "topLevel(primitiveType.arrayTypeFqName)");
                return new ClassLiteralValue(classId2, dimensions - 1);
            }
            ClassId classId3 = ClassId.topLevel(primitiveType.getTypeFqName());
            Intrinsics.checkNotNullExpressionValue(classId3, "topLevel(primitiveType.typeFqName)");
            return new ClassLiteralValue(classId3, dimensions);
        }
        ClassId javaClassId = ReflectClassUtilKt.getClassId(currentClass);
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        FqName fqNameAsSingleFqName = javaClassId.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(fqNameAsSingleFqName, "javaClassId.asSingleFqName()");
        ClassId classIdMapJavaToKotlin = javaToKotlinClassMap.mapJavaToKotlin(fqNameAsSingleFqName);
        ClassId kotlinClassId = classIdMapJavaToKotlin == null ? javaClassId : classIdMapJavaToKotlin;
        return new ClassLiteralValue(kotlinClassId, dimensions);
    }

    private final void processAnnotationArgumentValue(KotlinJvmBinaryClass.AnnotationArgumentVisitor visitor, Name name, Object value) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Class clazz = value.getClass();
        if (!Intrinsics.areEqual(clazz, Class.class)) {
            if (ReflectKotlinClassKt.TYPES_ELIGIBLE_FOR_SIMPLE_VISIT.contains(clazz)) {
                visitor.visit(name, value);
                return;
            }
            if (ReflectClassUtilKt.isEnumClassOrSpecializedEnumEntryClass(clazz)) {
                Class enclosingClass = clazz.isEnum() ? clazz : clazz.getEnclosingClass();
                Intrinsics.checkNotNullExpressionValue(enclosingClass, "if (clazz.isEnum) clazz else clazz.enclosingClass");
                ClassId classId = ReflectClassUtilKt.getClassId(enclosingClass);
                Name nameIdentifier = Name.identifier(((Enum) value).name());
                Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier((value as Enum<*>).name)");
                visitor.visitEnum(name, classId, nameIdentifier);
                return;
            }
            if (Annotation.class.isAssignableFrom(clazz)) {
                Class<?>[] interfaces = clazz.getInterfaces();
                Intrinsics.checkNotNullExpressionValue(interfaces, "clazz.interfaces");
                Class annotationClass = (Class) ArraysKt.single(interfaces);
                Intrinsics.checkNotNullExpressionValue(annotationClass, "annotationClass");
                KotlinJvmBinaryClass.AnnotationArgumentVisitor v = visitor.visitAnnotation(name, ReflectClassUtilKt.getClassId(annotationClass));
                if (v == null) {
                    return;
                }
                processAnnotationArguments(v, (Annotation) value, annotationClass);
                return;
            }
            if (clazz.isArray()) {
                KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor v2 = visitor.visitArray(name);
                if (v2 == null) {
                    return;
                }
                Class componentType = clazz.getComponentType();
                if (componentType.isEnum()) {
                    Intrinsics.checkNotNullExpressionValue(componentType, "componentType");
                    ClassId enumClassId = ReflectClassUtilKt.getClassId(componentType);
                    Object[] objArr = (Object[]) value;
                    int i = 0;
                    int length = objArr.length;
                    while (i < length) {
                        Object element = objArr[i];
                        i++;
                        if (element == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Enum<*>");
                        }
                        Name nameIdentifier2 = Name.identifier(((Enum) element).name());
                        Intrinsics.checkNotNullExpressionValue(nameIdentifier2, "identifier((element as Enum<*>).name)");
                        v2.visitEnum(enumClassId, nameIdentifier2);
                    }
                } else if (Intrinsics.areEqual(componentType, Class.class)) {
                    Object[] objArr2 = (Object[]) value;
                    int i2 = 0;
                    int length2 = objArr2.length;
                    while (i2 < length2) {
                        Object element2 = objArr2[i2];
                        i2++;
                        if (element2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<*>");
                        }
                        v2.visitClassLiteral(classLiteralValue((Class) element2));
                    }
                } else if (Annotation.class.isAssignableFrom(componentType)) {
                    Object[] objArr3 = (Object[]) value;
                    int i3 = 0;
                    int length3 = objArr3.length;
                    while (i3 < length3) {
                        Object element3 = objArr3[i3];
                        i3++;
                        Intrinsics.checkNotNullExpressionValue(componentType, "componentType");
                        KotlinJvmBinaryClass.AnnotationArgumentVisitor vv = v2.visitAnnotation(ReflectClassUtilKt.getClassId(componentType));
                        if (vv != null) {
                            if (element3 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Annotation");
                            }
                            processAnnotationArguments(vv, (Annotation) element3, componentType);
                        }
                    }
                } else {
                    Object[] objArr4 = (Object[]) value;
                    int i4 = 0;
                    int length4 = objArr4.length;
                    while (i4 < length4) {
                        Object element4 = objArr4[i4];
                        i4++;
                        v2.visit(element4);
                    }
                }
                v2.visitEnd();
                return;
            }
            throw new UnsupportedOperationException("Unsupported annotation argument value (" + clazz + "): " + value);
        }
        visitor.visitClassLiteral(name, classLiteralValue((Class) value));
    }
}
