package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaModifierListOwner;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReflectJavaMember.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/structure/ReflectJavaMember.class */
public abstract class ReflectJavaMember extends ReflectJavaElement implements ReflectJavaAnnotationOwner, ReflectJavaModifierListOwner, JavaMember {
    @NotNull
    public abstract Member getMember();

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    @Nullable
    public ReflectJavaAnnotation findAnnotation(@NotNull FqName fqName) {
        return ReflectJavaAnnotationOwner.DefaultImpls.findAnnotation(this, fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    @NotNull
    public List<ReflectJavaAnnotation> getAnnotations() {
        return ReflectJavaAnnotationOwner.DefaultImpls.getAnnotations(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isAbstract() {
        return ReflectJavaModifierListOwner.DefaultImpls.isAbstract(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return ReflectJavaAnnotationOwner.DefaultImpls.isDeprecatedInJavaDoc(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isFinal() {
        return ReflectJavaModifierListOwner.DefaultImpls.isFinal(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isStatic() {
        return ReflectJavaModifierListOwner.DefaultImpls.isStatic(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    @NotNull
    public Visibility getVisibility() {
        return ReflectJavaModifierListOwner.DefaultImpls.getVisibility(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    @NotNull
    public AnnotatedElement getElement() {
        return (AnnotatedElement) getMember();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaModifierListOwner
    public int getModifiers() {
        return getMember().getModifiers();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaNamedElement
    @NotNull
    public Name getName() {
        String it = getMember().getName();
        Name nameIdentifier = it == null ? null : Name.identifier(it);
        if (nameIdentifier != null) {
            return nameIdentifier;
        }
        Name NO_NAME_PROVIDED = SpecialNames.NO_NAME_PROVIDED;
        Intrinsics.checkNotNullExpressionValue(NO_NAME_PROVIDED, "NO_NAME_PROVIDED");
        return NO_NAME_PROVIDED;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember
    @NotNull
    public ReflectJavaClass getContainingClass() {
        Class<?> declaringClass = getMember().getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "member.declaringClass");
        return new ReflectJavaClass(declaringClass);
    }

    @NotNull
    protected final List<JavaValueParameter> getValueParameters(@NotNull Type[] parameterTypes, @NotNull Annotation[][] parameterAnnotations, boolean isVararg) throws IllegalAccessException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        String str;
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Intrinsics.checkNotNullParameter(parameterAnnotations, "parameterAnnotations");
        ArrayList result = new ArrayList(parameterTypes.length);
        List names = Java8ParameterNamesLoader.INSTANCE.loadParameterNames(getMember());
        Integer numValueOf = names == null ? null : Integer.valueOf(names.size());
        int shift = numValueOf == null ? 0 : numValueOf.intValue() - parameterTypes.length;
        int i = 0;
        int length = parameterTypes.length - 1;
        if (0 <= length) {
            do {
                int i2 = i;
                i++;
                ReflectJavaType type = ReflectJavaType.Factory.create(parameterTypes[i2]);
                if (names == null) {
                    str = null;
                } else {
                    String str2 = (String) CollectionsKt.getOrNull(names, i2 + shift);
                    if (str2 == null) {
                        throw new IllegalStateException(("No parameter with index " + i2 + '+' + shift + " (name=" + getName() + " type=" + type + ") in " + names + "@ReflectJavaMember").toString());
                    }
                    str = str2;
                }
                String name = str;
                boolean isParamVararg = isVararg && i2 == ArraysKt.getLastIndex(parameterTypes);
                result.add(new ReflectJavaValueParameter(type, parameterAnnotations[i2], name, isParamVararg));
            } while (i <= length);
        }
        return result;
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof ReflectJavaMember) && Intrinsics.areEqual(getMember(), ((ReflectJavaMember) other).getMember());
    }

    public int hashCode() {
        return getMember().hashCode();
    }

    @NotNull
    public String toString() {
        return getClass().getName() + ": " + getMember();
    }
}
