package kotlin.reflect.jvm.internal.impl.name;

import io.vertx.core.cli.converters.FromStringBasedConverter;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/name/ClassId.class */
public final class ClassId {
    private final FqName packageFqName;
    private final FqName relativeClassName;
    private final boolean local;
    static final /* synthetic */ boolean $assertionsDisabled;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 8:
            case 10:
            case 11:
            case 12:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 5:
            case 6:
            case 7:
            case 9:
            case 13:
            case 14:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 8:
            case 10:
            case 11:
            case 12:
            default:
                i2 = 3;
                break;
            case 5:
            case 6:
            case 7:
            case 9:
            case 13:
            case 14:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "topLevelFqName";
                break;
            case 1:
            case 3:
                objArr[0] = "packageFqName";
                break;
            case 2:
                objArr[0] = "relativeClassName";
                break;
            case 4:
                objArr[0] = "topLevelName";
                break;
            case 5:
            case 6:
            case 7:
            case 9:
            case 13:
            case 14:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/name/ClassId";
                break;
            case 8:
                objArr[0] = "name";
                break;
            case 10:
                objArr[0] = "segment";
                break;
            case 11:
            case 12:
                objArr[0] = "string";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 8:
            case 10:
            case 11:
            case 12:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/name/ClassId";
                break;
            case 5:
                objArr[1] = "getPackageFqName";
                break;
            case 6:
                objArr[1] = "getRelativeClassName";
                break;
            case 7:
                objArr[1] = "getShortClassName";
                break;
            case 9:
                objArr[1] = "asSingleFqName";
                break;
            case 13:
            case 14:
                objArr[1] = "asString";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = "topLevel";
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 5:
            case 6:
            case 7:
            case 9:
            case 13:
            case 14:
                break;
            case 8:
                objArr[2] = "createNestedClassId";
                break;
            case 10:
                objArr[2] = "startsWith";
                break;
            case 11:
            case 12:
                objArr[2] = FromStringBasedConverter.FROM_STRING;
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 8:
            case 10:
            case 11:
            case 12:
            default:
                throw new IllegalArgumentException(str2);
            case 5:
            case 6:
            case 7:
            case 9:
            case 13:
            case 14:
                throw new IllegalStateException(str2);
        }
    }

    static {
        $assertionsDisabled = !ClassId.class.desiredAssertionStatus();
    }

    @NotNull
    public static ClassId topLevel(@NotNull FqName topLevelFqName) {
        if (topLevelFqName == null) {
            $$$reportNull$$$0(0);
        }
        return new ClassId(topLevelFqName.parent(), topLevelFqName.shortName());
    }

    public ClassId(@NotNull FqName packageFqName, @NotNull FqName relativeClassName, boolean local) {
        if (packageFqName == null) {
            $$$reportNull$$$0(1);
        }
        if (relativeClassName == null) {
            $$$reportNull$$$0(2);
        }
        this.packageFqName = packageFqName;
        if (!$assertionsDisabled && relativeClassName.isRoot()) {
            throw new AssertionError("Class name must not be root: " + packageFqName + (local ? " (local)" : ""));
        }
        this.relativeClassName = relativeClassName;
        this.local = local;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ClassId(@NotNull FqName packageFqName, @NotNull Name topLevelName) {
        this(packageFqName, FqName.topLevel(topLevelName), false);
        if (packageFqName == null) {
            $$$reportNull$$$0(3);
        }
        if (topLevelName == null) {
            $$$reportNull$$$0(4);
        }
    }

    @NotNull
    public FqName getPackageFqName() {
        FqName fqName = this.packageFqName;
        if (fqName == null) {
            $$$reportNull$$$0(5);
        }
        return fqName;
    }

    @NotNull
    public FqName getRelativeClassName() {
        FqName fqName = this.relativeClassName;
        if (fqName == null) {
            $$$reportNull$$$0(6);
        }
        return fqName;
    }

    @NotNull
    public Name getShortClassName() {
        Name nameShortName = this.relativeClassName.shortName();
        if (nameShortName == null) {
            $$$reportNull$$$0(7);
        }
        return nameShortName;
    }

    public boolean isLocal() {
        return this.local;
    }

    @NotNull
    public ClassId createNestedClassId(@NotNull Name name) {
        if (name == null) {
            $$$reportNull$$$0(8);
        }
        return new ClassId(getPackageFqName(), this.relativeClassName.child(name), this.local);
    }

    @Nullable
    public ClassId getOuterClassId() {
        FqName parent = this.relativeClassName.parent();
        if (parent.isRoot()) {
            return null;
        }
        return new ClassId(getPackageFqName(), parent, this.local);
    }

    public boolean isNestedClass() {
        return !this.relativeClassName.parent().isRoot();
    }

    @NotNull
    public FqName asSingleFqName() {
        if (!this.packageFqName.isRoot()) {
            return new FqName(this.packageFqName.asString() + "." + this.relativeClassName.asString());
        }
        FqName fqName = this.relativeClassName;
        if (fqName == null) {
            $$$reportNull$$$0(9);
        }
        return fqName;
    }

    @NotNull
    public static ClassId fromString(@NotNull String string) {
        if (string == null) {
            $$$reportNull$$$0(11);
        }
        return fromString(string, false);
    }

    @NotNull
    public static ClassId fromString(@NotNull String string, boolean isLocal) {
        if (string == null) {
            $$$reportNull$$$0(12);
        }
        String packageName = StringsKt.substringBeforeLast(string, '/', "").replace('/', '.');
        String className = StringsKt.substringAfterLast(string, '/', string);
        return new ClassId(new FqName(packageName), new FqName(className), isLocal);
    }

    @NotNull
    public String asString() {
        if (this.packageFqName.isRoot()) {
            String strAsString = this.relativeClassName.asString();
            if (strAsString == null) {
                $$$reportNull$$$0(13);
            }
            return strAsString;
        }
        String str = this.packageFqName.asString().replace('.', '/') + "/" + this.relativeClassName.asString();
        if (str == null) {
            $$$reportNull$$$0(14);
        }
        return str;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassId id = (ClassId) o;
        return this.packageFqName.equals(id.packageFqName) && this.relativeClassName.equals(id.relativeClassName) && this.local == id.local;
    }

    public int hashCode() {
        int result = this.packageFqName.hashCode();
        return (31 * ((31 * result) + this.relativeClassName.hashCode())) + Boolean.valueOf(this.local).hashCode();
    }

    public String toString() {
        return this.packageFqName.isRoot() ? "/" + asString() : asString();
    }
}
