package kotlin.reflect.jvm.internal.impl.name;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/name/SpecialNames.class */
public class SpecialNames {
    public static final Name NO_NAME_PROVIDED = Name.special("<no name provided>");
    public static final Name ROOT_PACKAGE = Name.special("<root package>");
    public static final Name DEFAULT_NAME_FOR_COMPANION_OBJECT = Name.identifier("Companion");
    public static final Name SAFE_IDENTIFIER_FOR_NO_NAME = Name.identifier("no_name_in_PSI_3d19d79d_1ba9_4cd0_b7f5_b46aa3cd5d40");
    public static final Name ANONYMOUS_FUNCTION = Name.special("<anonymous>");

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            default:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 1:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i) {
            case 0:
            default:
                i2 = 2;
                break;
            case 1:
                i2 = 3;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/name/SpecialNames";
                break;
            case 1:
                objArr[0] = "name";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[1] = "safeIdentifier";
                break;
            case 1:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/name/SpecialNames";
                break;
        }
        switch (i) {
            case 1:
                objArr[2] = "isSafeIdentifier";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            default:
                throw new IllegalStateException(str2);
            case 1:
                throw new IllegalArgumentException(str2);
        }
    }

    @NotNull
    public static Name safeIdentifier(@Nullable Name name) {
        Name name2 = (name == null || name.isSpecial()) ? SAFE_IDENTIFIER_FOR_NO_NAME : name;
        if (name2 == null) {
            $$$reportNull$$$0(0);
        }
        return name2;
    }

    public static boolean isSafeIdentifier(@NotNull Name name) {
        if (name == null) {
            $$$reportNull$$$0(1);
        }
        return (name.asString().isEmpty() || name.isSpecial()) ? false : true;
    }
}
