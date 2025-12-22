package kotlin.reflect.jvm.internal.impl.name;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/name/FqName.class */
public final class FqName {
    public static final FqName ROOT = new FqName("");

    @NotNull
    private final FqNameUnsafe fqName;
    private transient FqName parent;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 8:
            case 12:
            case 13:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 8:
            case 12:
            case 13:
            default:
                i2 = 3;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "names";
                break;
            case 1:
            case 2:
            case 3:
                objArr[0] = "fqName";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/name/FqName";
                break;
            case 8:
                objArr[0] = "name";
                break;
            case 12:
                objArr[0] = "segment";
                break;
            case 13:
                objArr[0] = "shortName";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 8:
            case 12:
            case 13:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/name/FqName";
                break;
            case 4:
                objArr[1] = "asString";
                break;
            case 5:
                objArr[1] = "toUnsafe";
                break;
            case 6:
            case 7:
                objArr[1] = "parent";
                break;
            case 9:
                objArr[1] = "shortName";
                break;
            case 10:
                objArr[1] = "shortNameOrSpecial";
                break;
            case 11:
                objArr[1] = "pathSegments";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = "fromSegments";
                break;
            case 1:
            case 2:
            case 3:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
                break;
            case 8:
                objArr[2] = "child";
                break;
            case 12:
                objArr[2] = "startsWith";
                break;
            case 13:
                objArr[2] = "topLevel";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 8:
            case 12:
            case 13:
            default:
                throw new IllegalArgumentException(str2);
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
                throw new IllegalStateException(str2);
        }
    }

    public FqName(@NotNull String fqName) {
        if (fqName == null) {
            $$$reportNull$$$0(1);
        }
        this.fqName = new FqNameUnsafe(fqName, this);
    }

    public FqName(@NotNull FqNameUnsafe fqName) {
        if (fqName == null) {
            $$$reportNull$$$0(2);
        }
        this.fqName = fqName;
    }

    private FqName(@NotNull FqNameUnsafe fqName, FqName parent) {
        if (fqName == null) {
            $$$reportNull$$$0(3);
        }
        this.fqName = fqName;
        this.parent = parent;
    }

    @NotNull
    public String asString() {
        String strAsString = this.fqName.asString();
        if (strAsString == null) {
            $$$reportNull$$$0(4);
        }
        return strAsString;
    }

    @NotNull
    public FqNameUnsafe toUnsafe() {
        FqNameUnsafe fqNameUnsafe = this.fqName;
        if (fqNameUnsafe == null) {
            $$$reportNull$$$0(5);
        }
        return fqNameUnsafe;
    }

    public boolean isRoot() {
        return this.fqName.isRoot();
    }

    @NotNull
    public FqName parent() {
        if (this.parent != null) {
            FqName fqName = this.parent;
            if (fqName == null) {
                $$$reportNull$$$0(6);
            }
            return fqName;
        }
        if (isRoot()) {
            throw new IllegalStateException("root");
        }
        this.parent = new FqName(this.fqName.parent());
        FqName fqName2 = this.parent;
        if (fqName2 == null) {
            $$$reportNull$$$0(7);
        }
        return fqName2;
    }

    @NotNull
    public FqName child(@NotNull Name name) {
        if (name == null) {
            $$$reportNull$$$0(8);
        }
        return new FqName(this.fqName.child(name), this);
    }

    @NotNull
    public Name shortName() {
        Name nameShortName = this.fqName.shortName();
        if (nameShortName == null) {
            $$$reportNull$$$0(9);
        }
        return nameShortName;
    }

    @NotNull
    public Name shortNameOrSpecial() {
        Name nameShortNameOrSpecial = this.fqName.shortNameOrSpecial();
        if (nameShortNameOrSpecial == null) {
            $$$reportNull$$$0(10);
        }
        return nameShortNameOrSpecial;
    }

    @NotNull
    public List<Name> pathSegments() {
        List<Name> listPathSegments = this.fqName.pathSegments();
        if (listPathSegments == null) {
            $$$reportNull$$$0(11);
        }
        return listPathSegments;
    }

    public boolean startsWith(@NotNull Name segment) {
        if (segment == null) {
            $$$reportNull$$$0(12);
        }
        return this.fqName.startsWith(segment);
    }

    @NotNull
    public static FqName topLevel(@NotNull Name shortName) {
        if (shortName == null) {
            $$$reportNull$$$0(13);
        }
        return new FqName(FqNameUnsafe.topLevel(shortName));
    }

    public String toString() {
        return this.fqName.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FqName)) {
            return false;
        }
        FqName otherFqName = (FqName) o;
        return this.fqName.equals(otherFqName.fqName);
    }

    public int hashCode() {
        return this.fqName.hashCode();
    }
}
