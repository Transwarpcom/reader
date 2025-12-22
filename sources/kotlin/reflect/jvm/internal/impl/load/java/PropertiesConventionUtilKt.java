package kotlin.reflect.jvm.internal.impl.load.java;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: propertiesConventionUtil.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/PropertiesConventionUtilKt.class */
public final class PropertiesConventionUtilKt {
    @Nullable
    public static final Name propertyNameByGetMethodName(@NotNull Name methodName) {
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Name namePropertyNameFromAccessorMethodName$default = propertyNameFromAccessorMethodName$default(methodName, BeanUtil.PREFIX_GETTER_GET, false, null, 12, null);
        return namePropertyNameFromAccessorMethodName$default == null ? propertyNameFromAccessorMethodName$default(methodName, BeanUtil.PREFIX_GETTER_IS, false, null, 8, null) : namePropertyNameFromAccessorMethodName$default;
    }

    @Nullable
    public static final Name propertyNameBySetMethodName(@NotNull Name methodName, boolean withIsPrefix) {
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        return propertyNameFromAccessorMethodName$default(methodName, "set", false, withIsPrefix ? BeanUtil.PREFIX_GETTER_IS : null, 4, null);
    }

    @NotNull
    public static final List<Name> propertyNamesBySetMethodName(@NotNull Name methodName) {
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        return CollectionsKt.listOfNotNull((Object[]) new Name[]{propertyNameBySetMethodName(methodName, false), propertyNameBySetMethodName(methodName, true)});
    }

    static /* synthetic */ Name propertyNameFromAccessorMethodName$default(Name name, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        return propertyNameFromAccessorMethodName(name, str, z, str2);
    }

    private static final Name propertyNameFromAccessorMethodName(Name methodName, String prefix, boolean removePrefix, String addPrefix) {
        if (methodName.isSpecial()) {
            return null;
        }
        String identifier = methodName.getIdentifier();
        Intrinsics.checkNotNullExpressionValue(identifier, "methodName.identifier");
        if (!StringsKt.startsWith$default(identifier, prefix, false, 2, (Object) null) || identifier.length() == prefix.length()) {
            return null;
        }
        char cCharAt = identifier.charAt(prefix.length());
        boolean z = 'a' <= cCharAt && cCharAt <= 'z';
        if (z) {
            return null;
        }
        if (addPrefix != null) {
            if (!_Assertions.ENABLED || removePrefix) {
                return Name.identifier(Intrinsics.stringPlus(addPrefix, StringsKt.removePrefix(identifier, (CharSequence) prefix)));
            }
            throw new AssertionError("Assertion failed");
        }
        if (!removePrefix) {
            return methodName;
        }
        String name = CapitalizeDecapitalizeKt.decapitalizeSmartForCompiler(StringsKt.removePrefix(identifier, (CharSequence) prefix), true);
        if (Name.isValidIdentifier(name)) {
            return Name.identifier(name);
        }
        return null;
    }

    @NotNull
    public static final List<Name> getPropertyNamesCandidatesByAccessorName(@NotNull Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String nameAsString = name.asString();
        Intrinsics.checkNotNullExpressionValue(nameAsString, "name.asString()");
        JvmAbi jvmAbi = JvmAbi.INSTANCE;
        if (JvmAbi.isGetterName(nameAsString)) {
            return CollectionsKt.listOfNotNull(propertyNameByGetMethodName(name));
        }
        JvmAbi jvmAbi2 = JvmAbi.INSTANCE;
        if (JvmAbi.isSetterName(nameAsString)) {
            return propertyNamesBySetMethodName(name);
        }
        return BuiltinSpecialProperties.INSTANCE.getPropertyNameCandidatesBySpecialGetterName(name);
    }
}
