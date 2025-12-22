package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmPrimitiveType.class */
public enum JvmPrimitiveType {
    BOOLEAN(PrimitiveType.BOOLEAN, "boolean", "Z", "java.lang.Boolean"),
    CHAR(PrimitiveType.CHAR, "char", "C", "java.lang.Character"),
    BYTE(PrimitiveType.BYTE, "byte", "B", "java.lang.Byte"),
    SHORT(PrimitiveType.SHORT, "short", "S", "java.lang.Short"),
    INT(PrimitiveType.INT, "int", "I", "java.lang.Integer"),
    FLOAT(PrimitiveType.FLOAT, "float", "F", "java.lang.Float"),
    LONG(PrimitiveType.LONG, "long", OperatorName.SET_LINE_CAPSTYLE, "java.lang.Long"),
    DOUBLE(PrimitiveType.DOUBLE, "double", "D", "java.lang.Double");

    private static final Set<FqName> WRAPPERS_CLASS_NAMES = new HashSet();
    private static final Map<String, JvmPrimitiveType> TYPE_BY_NAME = new HashMap();
    private static final Map<PrimitiveType, JvmPrimitiveType> TYPE_BY_PRIMITIVE_TYPE = new EnumMap(PrimitiveType.class);
    private static final Map<String, JvmPrimitiveType> TYPE_BY_DESC = new HashMap();
    private final PrimitiveType primitiveType;
    private final String name;
    private final String desc;
    private final FqName wrapperFqName;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 2:
            case 4:
            case 10:
            case 11:
            case 12:
            case 13:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            default:
                i2 = 3;
                break;
            case 2:
            case 4:
            case 10:
            case 11:
            case 12:
            case 13:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "className";
                break;
            case 1:
            case 7:
                objArr[0] = "name";
                break;
            case 2:
            case 4:
            case 10:
            case 11:
            case 12:
            case 13:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmPrimitiveType";
                break;
            case 3:
                objArr[0] = "type";
                break;
            case 5:
            case 8:
                objArr[0] = "desc";
                break;
            case 6:
                objArr[0] = "primitiveType";
                break;
            case 9:
                objArr[0] = "wrapperClassName";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmPrimitiveType";
                break;
            case 2:
            case 4:
                objArr[1] = BeanUtil.PREFIX_GETTER_GET;
                break;
            case 10:
                objArr[1] = "getPrimitiveType";
                break;
            case 11:
                objArr[1] = "getJavaKeywordName";
                break;
            case 12:
                objArr[1] = "getDesc";
                break;
            case 13:
                objArr[1] = "getWrapperFqName";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = "isWrapperClassName";
                break;
            case 1:
            case 3:
                objArr[2] = BeanUtil.PREFIX_GETTER_GET;
                break;
            case 2:
            case 4:
            case 10:
            case 11:
            case 12:
            case 13:
                break;
            case 5:
                objArr[2] = "getByDesc";
                break;
            case 6:
            case 7:
            case 8:
            case 9:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            default:
                throw new IllegalArgumentException(str2);
            case 2:
            case 4:
            case 10:
            case 11:
            case 12:
            case 13:
                throw new IllegalStateException(str2);
        }
    }

    static {
        JvmPrimitiveType[] arr$ = values();
        for (JvmPrimitiveType type : arr$) {
            WRAPPERS_CLASS_NAMES.add(type.getWrapperFqName());
            TYPE_BY_NAME.put(type.getJavaKeywordName(), type);
            TYPE_BY_PRIMITIVE_TYPE.put(type.getPrimitiveType(), type);
            TYPE_BY_DESC.put(type.getDesc(), type);
        }
    }

    @NotNull
    public static JvmPrimitiveType get(@NotNull String name) {
        if (name == null) {
            $$$reportNull$$$0(1);
        }
        JvmPrimitiveType result = TYPE_BY_NAME.get(name);
        if (result == null) {
            throw new AssertionError("Non-primitive type name passed: " + name);
        }
        if (result == null) {
            $$$reportNull$$$0(2);
        }
        return result;
    }

    @NotNull
    public static JvmPrimitiveType get(@NotNull PrimitiveType type) {
        if (type == null) {
            $$$reportNull$$$0(3);
        }
        JvmPrimitiveType jvmPrimitiveType = TYPE_BY_PRIMITIVE_TYPE.get(type);
        if (jvmPrimitiveType == null) {
            $$$reportNull$$$0(4);
        }
        return jvmPrimitiveType;
    }

    JvmPrimitiveType(@NotNull PrimitiveType primitiveType, @NotNull String name, @NotNull String desc, @NotNull String wrapperClassName) {
        if (primitiveType == null) {
            $$$reportNull$$$0(6);
        }
        if (name == null) {
            $$$reportNull$$$0(7);
        }
        if (desc == null) {
            $$$reportNull$$$0(8);
        }
        if (wrapperClassName == null) {
            $$$reportNull$$$0(9);
        }
        this.primitiveType = primitiveType;
        this.name = name;
        this.desc = desc;
        this.wrapperFqName = new FqName(wrapperClassName);
    }

    @NotNull
    public PrimitiveType getPrimitiveType() {
        PrimitiveType primitiveType = this.primitiveType;
        if (primitiveType == null) {
            $$$reportNull$$$0(10);
        }
        return primitiveType;
    }

    @NotNull
    public String getJavaKeywordName() {
        String str = this.name;
        if (str == null) {
            $$$reportNull$$$0(11);
        }
        return str;
    }

    @NotNull
    public String getDesc() {
        String str = this.desc;
        if (str == null) {
            $$$reportNull$$$0(12);
        }
        return str;
    }

    @NotNull
    public FqName getWrapperFqName() {
        FqName fqName = this.wrapperFqName;
        if (fqName == null) {
            $$$reportNull$$$0(13);
        }
        return fqName;
    }
}
