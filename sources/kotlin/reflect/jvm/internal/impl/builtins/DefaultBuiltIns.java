package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import org.jetbrains.annotations.NotNull;

/* compiled from: DefaultBuiltIns.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/DefaultBuiltIns.class */
public final class DefaultBuiltIns extends KotlinBuiltIns {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final DefaultBuiltIns Instance = new DefaultBuiltIns(false, 1, null);

    public DefaultBuiltIns() {
        this(false, 1, null);
    }

    @NotNull
    public static final DefaultBuiltIns getInstance() {
        return Companion.getInstance();
    }

    public DefaultBuiltIns(boolean loadBuiltInsFromCurrentClassLoader) {
        super(new LockBasedStorageManager("DefaultBuiltIns"));
        if (!loadBuiltInsFromCurrentClassLoader) {
            return;
        }
        createBuiltInsModule(false);
    }

    public /* synthetic */ DefaultBuiltIns(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    /* compiled from: DefaultBuiltIns.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/DefaultBuiltIns$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final DefaultBuiltIns getInstance() {
            return DefaultBuiltIns.Instance;
        }
    }
}
