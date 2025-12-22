package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Modifier;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.java.JavaVisibilities;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner;
import org.jetbrains.annotations.NotNull;

/* compiled from: ReflectJavaModifierListOwner.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/structure/ReflectJavaModifierListOwner.class */
public interface ReflectJavaModifierListOwner extends JavaModifierListOwner {
    int getModifiers();

    /* compiled from: ReflectJavaModifierListOwner.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/structure/ReflectJavaModifierListOwner$DefaultImpls.class */
    public static final class DefaultImpls {
        public static boolean isAbstract(@NotNull ReflectJavaModifierListOwner reflectJavaModifierListOwner) {
            Intrinsics.checkNotNullParameter(reflectJavaModifierListOwner, "this");
            return Modifier.isAbstract(reflectJavaModifierListOwner.getModifiers());
        }

        public static boolean isStatic(@NotNull ReflectJavaModifierListOwner reflectJavaModifierListOwner) {
            Intrinsics.checkNotNullParameter(reflectJavaModifierListOwner, "this");
            return Modifier.isStatic(reflectJavaModifierListOwner.getModifiers());
        }

        public static boolean isFinal(@NotNull ReflectJavaModifierListOwner reflectJavaModifierListOwner) {
            Intrinsics.checkNotNullParameter(reflectJavaModifierListOwner, "this");
            return Modifier.isFinal(reflectJavaModifierListOwner.getModifiers());
        }

        @NotNull
        public static Visibility getVisibility(@NotNull ReflectJavaModifierListOwner reflectJavaModifierListOwner) {
            Intrinsics.checkNotNullParameter(reflectJavaModifierListOwner, "this");
            int modifiers = reflectJavaModifierListOwner.getModifiers();
            if (Modifier.isPublic(modifiers)) {
                return Visibilities.Public.INSTANCE;
            }
            if (Modifier.isPrivate(modifiers)) {
                return Visibilities.Private.INSTANCE;
            }
            if (Modifier.isProtected(modifiers)) {
                return Modifier.isStatic(modifiers) ? JavaVisibilities.ProtectedStaticVisibility.INSTANCE : JavaVisibilities.ProtectedAndPackage.INSTANCE;
            }
            return JavaVisibilities.PackageVisibility.INSTANCE;
        }
    }
}
