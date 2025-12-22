package kotlin.reflect.jvm.internal.impl.load.java;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmAbi.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/JvmAbi.class */
public final class JvmAbi {

    @NotNull
    public static final JvmAbi INSTANCE = new JvmAbi();

    @JvmField
    @NotNull
    public static final FqName JVM_FIELD_ANNOTATION_FQ_NAME = new FqName("kotlin.jvm.JvmField");

    @NotNull
    private static final ClassId REFLECTION_FACTORY_IMPL;

    private JvmAbi() {
    }

    static {
        ClassId classId = ClassId.topLevel(new FqName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl"));
        Intrinsics.checkNotNullExpressionValue(classId, "topLevel(FqName(\"kotlin.reflect.jvm.internal.ReflectionFactoryImpl\"))");
        REFLECTION_FACTORY_IMPL = classId;
    }

    @JvmStatic
    public static final boolean isGetterName(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        JvmAbi jvmAbi = INSTANCE;
        if (!StringsKt.startsWith$default(name, BeanUtil.PREFIX_GETTER_GET, false, 2, (Object) null)) {
            JvmAbi jvmAbi2 = INSTANCE;
            if (!StringsKt.startsWith$default(name, BeanUtil.PREFIX_GETTER_IS, false, 2, (Object) null)) {
                return false;
            }
        }
        return true;
    }

    @JvmStatic
    public static final boolean isSetterName(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        JvmAbi jvmAbi = INSTANCE;
        return StringsKt.startsWith$default(name, "set", false, 2, (Object) null);
    }

    @JvmStatic
    @NotNull
    public static final String getterName(@NotNull String propertyName) {
        Intrinsics.checkNotNullParameter(propertyName, "propertyName");
        JvmAbi jvmAbi = INSTANCE;
        if (startsWithIsPrefix(propertyName)) {
            return propertyName;
        }
        JvmAbi jvmAbi2 = INSTANCE;
        return Intrinsics.stringPlus(BeanUtil.PREFIX_GETTER_GET, CapitalizeDecapitalizeKt.capitalizeAsciiOnly(propertyName));
    }

    @JvmStatic
    @NotNull
    public static final String setterName(@NotNull String propertyName) {
        String strCapitalizeAsciiOnly;
        Intrinsics.checkNotNullParameter(propertyName, "propertyName");
        JvmAbi jvmAbi = INSTANCE;
        JvmAbi jvmAbi2 = INSTANCE;
        if (startsWithIsPrefix(propertyName)) {
            JvmAbi jvmAbi3 = INSTANCE;
            strCapitalizeAsciiOnly = propertyName.substring(BeanUtil.PREFIX_GETTER_IS.length());
            Intrinsics.checkNotNullExpressionValue(strCapitalizeAsciiOnly, "(this as java.lang.String).substring(startIndex)");
        } else {
            strCapitalizeAsciiOnly = CapitalizeDecapitalizeKt.capitalizeAsciiOnly(propertyName);
        }
        return Intrinsics.stringPlus("set", strCapitalizeAsciiOnly);
    }

    @JvmStatic
    public static final boolean startsWithIsPrefix(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        JvmAbi jvmAbi = INSTANCE;
        if (!StringsKt.startsWith$default(name, BeanUtil.PREFIX_GETTER_IS, false, 2, (Object) null)) {
            return false;
        }
        int length = name.length();
        JvmAbi jvmAbi2 = INSTANCE;
        if (length == BeanUtil.PREFIX_GETTER_IS.length()) {
            return false;
        }
        JvmAbi jvmAbi3 = INSTANCE;
        char c = name.charAt(BeanUtil.PREFIX_GETTER_IS.length());
        return Intrinsics.compare(97, (int) c) > 0 || Intrinsics.compare((int) c, 122) > 0;
    }
}
