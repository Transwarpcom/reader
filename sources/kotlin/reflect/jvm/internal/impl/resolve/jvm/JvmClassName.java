package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmClassName.class */
public class JvmClassName {
    private final String internalName;
    private FqName fqName;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 4:
            case 5:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 3:
            case 6:
            case 7:
            case 8:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 4:
            case 5:
            default:
                i2 = 3;
                break;
            case 3:
            case 6:
            case 7:
            case 8:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 5:
            default:
                objArr[0] = "internalName";
                break;
            case 1:
                objArr[0] = "classId";
                break;
            case 2:
            case 4:
                objArr[0] = "fqName";
                break;
            case 3:
            case 6:
            case 7:
            case 8:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmClassName";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 4:
            case 5:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmClassName";
                break;
            case 3:
                objArr[1] = "byFqNameWithoutInnerClasses";
                break;
            case 6:
                objArr[1] = "getFqNameForClassNameWithoutDollars";
                break;
            case 7:
                objArr[1] = "getPackageFqName";
                break;
            case 8:
                objArr[1] = "getInternalName";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = "byInternalName";
                break;
            case 1:
                objArr[2] = "byClassId";
                break;
            case 2:
            case 4:
                objArr[2] = "byFqNameWithoutInnerClasses";
                break;
            case 3:
            case 6:
            case 7:
            case 8:
                break;
            case 5:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 4:
            case 5:
            default:
                throw new IllegalArgumentException(str2);
            case 3:
            case 6:
            case 7:
            case 8:
                throw new IllegalStateException(str2);
        }
    }

    @NotNull
    public static JvmClassName byInternalName(@NotNull String internalName) {
        if (internalName == null) {
            $$$reportNull$$$0(0);
        }
        return new JvmClassName(internalName);
    }

    @NotNull
    public static JvmClassName byClassId(@NotNull ClassId classId) {
        if (classId == null) {
            $$$reportNull$$$0(1);
        }
        FqName packageFqName = classId.getPackageFqName();
        String relativeClassName = classId.getRelativeClassName().asString().replace('.', '$');
        return packageFqName.isRoot() ? new JvmClassName(relativeClassName) : new JvmClassName(packageFqName.asString().replace('.', '/') + "/" + relativeClassName);
    }

    @NotNull
    public static JvmClassName byFqNameWithoutInnerClasses(@NotNull FqName fqName) {
        if (fqName == null) {
            $$$reportNull$$$0(2);
        }
        JvmClassName r = new JvmClassName(fqName.asString().replace('.', '/'));
        r.fqName = fqName;
        if (r == null) {
            $$$reportNull$$$0(3);
        }
        return r;
    }

    private JvmClassName(@NotNull String internalName) {
        if (internalName == null) {
            $$$reportNull$$$0(5);
        }
        this.internalName = internalName;
    }

    @NotNull
    public FqName getFqNameForTopLevelClassMaybeWithDollars() {
        return new FqName(this.internalName.replace('/', '.'));
    }

    @NotNull
    public FqName getPackageFqName() {
        int lastSlash = this.internalName.lastIndexOf("/");
        if (lastSlash != -1) {
            return new FqName(this.internalName.substring(0, lastSlash).replace('/', '.'));
        }
        FqName fqName = FqName.ROOT;
        if (fqName == null) {
            $$$reportNull$$$0(7);
        }
        return fqName;
    }

    @NotNull
    public String getInternalName() {
        String str = this.internalName;
        if (str == null) {
            $$$reportNull$$$0(8);
        }
        return str;
    }

    public String toString() {
        return this.internalName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.internalName.equals(((JvmClassName) o).internalName);
    }

    public int hashCode() {
        return this.internalName.hashCode();
    }
}
