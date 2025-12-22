package kotlin.reflect.jvm.internal.impl.load.java.components;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/components/JavaResolverCache.class */
public interface JavaResolverCache {
    public static final JavaResolverCache EMPTY = new JavaResolverCache() { // from class: kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache.1
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "fqName";
                    break;
                case 1:
                    objArr[0] = "member";
                    break;
                case 2:
                case 4:
                case 6:
                case 8:
                    objArr[0] = "descriptor";
                    break;
                case 3:
                    objArr[0] = "element";
                    break;
                case 5:
                    objArr[0] = "field";
                    break;
                case 7:
                    objArr[0] = "javaClass";
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/JavaResolverCache$1";
            switch (i) {
                case 0:
                default:
                    objArr[2] = "getClassResolvedFromSource";
                    break;
                case 1:
                case 2:
                    objArr[2] = "recordMethod";
                    break;
                case 3:
                case 4:
                    objArr[2] = "recordConstructor";
                    break;
                case 5:
                case 6:
                    objArr[2] = "recordField";
                    break;
                case 7:
                case 8:
                    objArr[2] = "recordClass";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
        @Nullable
        public ClassDescriptor getClassResolvedFromSource(@NotNull FqName fqName) {
            if (fqName == null) {
                $$$reportNull$$$0(0);
                return null;
            }
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
        public void recordMethod(@NotNull JavaMember member, @NotNull SimpleFunctionDescriptor descriptor) {
            if (member == null) {
                $$$reportNull$$$0(1);
            }
            if (descriptor == null) {
                $$$reportNull$$$0(2);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
        public void recordConstructor(@NotNull JavaElement element, @NotNull ConstructorDescriptor descriptor) {
            if (element == null) {
                $$$reportNull$$$0(3);
            }
            if (descriptor == null) {
                $$$reportNull$$$0(4);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
        public void recordField(@NotNull JavaField field, @NotNull PropertyDescriptor descriptor) {
            if (field == null) {
                $$$reportNull$$$0(5);
            }
            if (descriptor == null) {
                $$$reportNull$$$0(6);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
        public void recordClass(@NotNull JavaClass javaClass, @NotNull ClassDescriptor descriptor) {
            if (javaClass == null) {
                $$$reportNull$$$0(7);
            }
            if (descriptor == null) {
                $$$reportNull$$$0(8);
            }
        }
    };

    @Nullable
    ClassDescriptor getClassResolvedFromSource(@NotNull FqName fqName);

    void recordMethod(@NotNull JavaMember javaMember, @NotNull SimpleFunctionDescriptor simpleFunctionDescriptor);

    void recordConstructor(@NotNull JavaElement javaElement, @NotNull ConstructorDescriptor constructorDescriptor);

    void recordField(@NotNull JavaField javaField, @NotNull PropertyDescriptor propertyDescriptor);

    void recordClass(@NotNull JavaClass javaClass, @NotNull ClassDescriptor classDescriptor);
}
