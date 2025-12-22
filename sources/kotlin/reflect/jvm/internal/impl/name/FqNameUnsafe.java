package kotlin.reflect.jvm.internal.impl.name;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/name/FqNameUnsafe.class */
public final class FqNameUnsafe {
    private static final Name ROOT_NAME = Name.special("<root>");
    private static final Pattern SPLIT_BY_DOTS = Pattern.compile("\\.");
    private static final Function1<String, Name> STRING_TO_NAME = new Function1<String, Name>() { // from class: kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe.1
        @Override // kotlin.jvm.functions.Function1
        public Name invoke(String name) {
            return Name.guessByFirstCharacter(name);
        }
    };

    @NotNull
    private final String fqName;
    private transient FqName safe;
    private transient FqNameUnsafe parent;
    private transient Name shortName;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 9:
            case 15:
            case 16:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 17:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 9:
            case 15:
            case 16:
            default:
                i2 = 3;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 17:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 2:
            case 3:
            default:
                objArr[0] = "fqName";
                break;
            case 1:
                objArr[0] = "safe";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 17:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/name/FqNameUnsafe";
                break;
            case 9:
                objArr[0] = "name";
                break;
            case 15:
                objArr[0] = "segment";
                break;
            case 16:
                objArr[0] = "shortName";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 9:
            case 15:
            case 16:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/name/FqNameUnsafe";
                break;
            case 4:
                objArr[1] = "asString";
                break;
            case 5:
            case 6:
                objArr[1] = "toSafe";
                break;
            case 7:
            case 8:
                objArr[1] = "parent";
                break;
            case 10:
            case 11:
                objArr[1] = "shortName";
                break;
            case 12:
            case 13:
                objArr[1] = "shortNameOrSpecial";
                break;
            case 14:
                objArr[1] = "pathSegments";
                break;
            case 17:
                objArr[1] = "toString";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 17:
                break;
            case 9:
                objArr[2] = "child";
                break;
            case 15:
                objArr[2] = "startsWith";
                break;
            case 16:
                objArr[2] = "topLevel";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 9:
            case 15:
            case 16:
            default:
                throw new IllegalArgumentException(str2);
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 17:
                throw new IllegalStateException(str2);
        }
    }

    FqNameUnsafe(@NotNull String fqName, @NotNull FqName safe) {
        if (fqName == null) {
            $$$reportNull$$$0(0);
        }
        if (safe == null) {
            $$$reportNull$$$0(1);
        }
        this.fqName = fqName;
        this.safe = safe;
    }

    public FqNameUnsafe(@NotNull String fqName) {
        if (fqName == null) {
            $$$reportNull$$$0(2);
        }
        this.fqName = fqName;
    }

    private FqNameUnsafe(@NotNull String fqName, FqNameUnsafe parent, Name shortName) {
        if (fqName == null) {
            $$$reportNull$$$0(3);
        }
        this.fqName = fqName;
        this.parent = parent;
        this.shortName = shortName;
    }

    private void compute() {
        int lastDot = this.fqName.lastIndexOf(46);
        if (lastDot >= 0) {
            this.shortName = Name.guessByFirstCharacter(this.fqName.substring(lastDot + 1));
            this.parent = new FqNameUnsafe(this.fqName.substring(0, lastDot));
        } else {
            this.shortName = Name.guessByFirstCharacter(this.fqName);
            this.parent = FqName.ROOT.toUnsafe();
        }
    }

    @NotNull
    public String asString() {
        String str = this.fqName;
        if (str == null) {
            $$$reportNull$$$0(4);
        }
        return str;
    }

    public boolean isSafe() {
        return this.safe != null || asString().indexOf(60) < 0;
    }

    @NotNull
    public FqName toSafe() {
        if (this.safe != null) {
            FqName fqName = this.safe;
            if (fqName == null) {
                $$$reportNull$$$0(5);
            }
            return fqName;
        }
        this.safe = new FqName(this);
        FqName fqName2 = this.safe;
        if (fqName2 == null) {
            $$$reportNull$$$0(6);
        }
        return fqName2;
    }

    public boolean isRoot() {
        return this.fqName.isEmpty();
    }

    @NotNull
    public FqNameUnsafe parent() {
        if (this.parent != null) {
            FqNameUnsafe fqNameUnsafe = this.parent;
            if (fqNameUnsafe == null) {
                $$$reportNull$$$0(7);
            }
            return fqNameUnsafe;
        }
        if (isRoot()) {
            throw new IllegalStateException("root");
        }
        compute();
        FqNameUnsafe fqNameUnsafe2 = this.parent;
        if (fqNameUnsafe2 == null) {
            $$$reportNull$$$0(8);
        }
        return fqNameUnsafe2;
    }

    @NotNull
    public FqNameUnsafe child(@NotNull Name name) {
        String childFqName;
        if (name == null) {
            $$$reportNull$$$0(9);
        }
        if (isRoot()) {
            childFqName = name.asString();
        } else {
            childFqName = this.fqName + "." + name.asString();
        }
        return new FqNameUnsafe(childFqName, this, name);
    }

    @NotNull
    public Name shortName() {
        if (this.shortName != null) {
            Name name = this.shortName;
            if (name == null) {
                $$$reportNull$$$0(10);
            }
            return name;
        }
        if (isRoot()) {
            throw new IllegalStateException("root");
        }
        compute();
        Name name2 = this.shortName;
        if (name2 == null) {
            $$$reportNull$$$0(11);
        }
        return name2;
    }

    @NotNull
    public Name shortNameOrSpecial() {
        if (isRoot()) {
            Name name = ROOT_NAME;
            if (name == null) {
                $$$reportNull$$$0(12);
            }
            return name;
        }
        Name nameShortName = shortName();
        if (nameShortName == null) {
            $$$reportNull$$$0(13);
        }
        return nameShortName;
    }

    @NotNull
    public List<Name> pathSegments() {
        List<Name> listEmptyList = isRoot() ? Collections.emptyList() : ArraysKt.map(SPLIT_BY_DOTS.split(this.fqName), STRING_TO_NAME);
        if (listEmptyList == null) {
            $$$reportNull$$$0(14);
        }
        return listEmptyList;
    }

    public boolean startsWith(@NotNull Name segment) {
        if (segment == null) {
            $$$reportNull$$$0(15);
        }
        if (isRoot()) {
            return false;
        }
        int firstDot = this.fqName.indexOf(46);
        return this.fqName.regionMatches(0, segment.asString(), 0, firstDot == -1 ? this.fqName.length() : firstDot);
    }

    @NotNull
    public static FqNameUnsafe topLevel(@NotNull Name shortName) {
        if (shortName == null) {
            $$$reportNull$$$0(16);
        }
        return new FqNameUnsafe(shortName.asString(), FqName.ROOT.toUnsafe(), shortName);
    }

    @NotNull
    public String toString() {
        String strAsString = isRoot() ? ROOT_NAME.asString() : this.fqName;
        if (strAsString == null) {
            $$$reportNull$$$0(17);
        }
        return strAsString;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FqNameUnsafe)) {
            return false;
        }
        FqNameUnsafe that = (FqNameUnsafe) o;
        return this.fqName.equals(that.fqName);
    }

    public int hashCode() {
        return this.fqName.hashCode();
    }
}
