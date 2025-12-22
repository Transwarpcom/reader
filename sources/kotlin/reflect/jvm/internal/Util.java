package kotlin.reflect.jvm.internal;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/Util.class */
class Util {
    public static Object getEnumConstantByName(Class<? extends Enum<?>> enumClass, String name) {
        return Enum.valueOf(enumClass, name);
    }
}
