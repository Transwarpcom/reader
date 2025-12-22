package kotlin.reflect.jvm.internal.impl.resolve.constants;

import ch.qos.logback.classic.spi.CallerData;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* compiled from: constantValues.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/constants/CharValue.class */
public final class CharValue extends IntegerValueConstant<Character> {
    public CharValue(char value) {
        super(Character.valueOf(value));
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    @NotNull
    public SimpleType getType(@NotNull ModuleDescriptor module) {
        Intrinsics.checkNotNullParameter(module, "module");
        SimpleType charType = module.getBuiltIns().getCharType();
        Intrinsics.checkNotNullExpressionValue(charType, "module.builtIns.charType");
        return charType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    @NotNull
    public String toString() {
        Object[] objArr = {Integer.valueOf(getValue().charValue()), getPrintablePart(getValue().charValue())};
        String str = String.format("\\u%04X ('%s')", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        return str;
    }

    private final String getPrintablePart(char c) {
        return c == '\b' ? "\\b" : c == '\t' ? "\\t" : c == '\n' ? "\\n" : c == '\f' ? "\\f" : c == '\r' ? "\\r" : isPrintableUnicode(c) ? String.valueOf(c) : CallerData.NA;
    }

    private final boolean isPrintableUnicode(char c) {
        byte t = (byte) Character.getType(c);
        return (t == 0 || t == 13 || t == 14 || t == 15 || t == 16 || t == 18 || t == 19) ? false : true;
    }
}
