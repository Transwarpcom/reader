package kotlin.reflect.jvm.internal.impl.name;

import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/name/Name.class */
public final class Name implements Comparable<Name> {

    @NotNull
    private final String name;
    private final boolean special;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 1:
            case 2:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
            default:
                i2 = 3;
                break;
            case 1:
            case 2:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
            default:
                objArr[0] = "name";
                break;
            case 1:
            case 2:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/name/Name";
                break;
        }
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/name/Name";
                break;
            case 1:
                objArr[1] = "asString";
                break;
            case 2:
                objArr[1] = "getIdentifier";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 1:
            case 2:
                break;
            case 3:
                objArr[2] = PackageDocumentBase.DCTags.identifier;
                break;
            case 4:
                objArr[2] = "isValidIdentifier";
                break;
            case 5:
                objArr[2] = "special";
                break;
            case 6:
                objArr[2] = "guessByFirstCharacter";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
            default:
                throw new IllegalArgumentException(str2);
            case 1:
            case 2:
                throw new IllegalStateException(str2);
        }
    }

    private Name(@NotNull String name, boolean special) {
        if (name == null) {
            $$$reportNull$$$0(0);
        }
        this.name = name;
        this.special = special;
    }

    @NotNull
    public String asString() {
        String str = this.name;
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        return str;
    }

    @NotNull
    public String getIdentifier() {
        if (this.special) {
            throw new IllegalStateException("not identifier: " + this);
        }
        String strAsString = asString();
        if (strAsString == null) {
            $$$reportNull$$$0(2);
        }
        return strAsString;
    }

    public boolean isSpecial() {
        return this.special;
    }

    @Override // java.lang.Comparable
    public int compareTo(Name that) {
        return this.name.compareTo(that.name);
    }

    @NotNull
    public static Name identifier(@NotNull String name) {
        if (name == null) {
            $$$reportNull$$$0(3);
        }
        return new Name(name, false);
    }

    public static boolean isValidIdentifier(@NotNull String name) {
        if (name == null) {
            $$$reportNull$$$0(4);
        }
        if (name.isEmpty() || name.startsWith("<")) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            char ch2 = name.charAt(i);
            if (ch2 == '.' || ch2 == '/' || ch2 == '\\') {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static Name special(@NotNull String name) {
        if (name == null) {
            $$$reportNull$$$0(5);
        }
        if (!name.startsWith("<")) {
            throw new IllegalArgumentException("special name must start with '<': " + name);
        }
        return new Name(name, true);
    }

    @NotNull
    public static Name guessByFirstCharacter(@NotNull String name) {
        if (name == null) {
            $$$reportNull$$$0(6);
        }
        if (name.startsWith("<")) {
            return special(name);
        }
        return identifier(name);
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }
        Name name1 = (Name) o;
        return this.special == name1.special && this.name.equals(name1.name);
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (31 * result) + (this.special ? 1 : 0);
    }
}
