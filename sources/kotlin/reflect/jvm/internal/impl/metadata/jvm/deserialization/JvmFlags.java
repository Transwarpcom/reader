package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmFlags.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmFlags.class */
public final class JvmFlags {

    @NotNull
    public static final JvmFlags INSTANCE = new JvmFlags();
    private static final Flags.BooleanFlagField IS_MOVED_FROM_INTERFACE_COMPANION = Flags.FlagField.booleanFirst();
    private static final Flags.BooleanFlagField ARE_INTERFACE_METHOD_BODIES_INSIDE = Flags.FlagField.booleanFirst();
    private static final Flags.BooleanFlagField IS_ALL_COMPATIBILITY_MODE;

    private JvmFlags() {
    }

    public final Flags.BooleanFlagField getIS_MOVED_FROM_INTERFACE_COMPANION() {
        return IS_MOVED_FROM_INTERFACE_COMPANION;
    }

    static {
        JvmFlags jvmFlags = INSTANCE;
        IS_ALL_COMPATIBILITY_MODE = Flags.FlagField.booleanAfter(ARE_INTERFACE_METHOD_BODIES_INSIDE);
    }
}
