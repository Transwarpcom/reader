package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* compiled from: Modality.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/Modality.class */
public enum Modality {
    FINAL,
    SEALED,
    OPEN,
    ABSTRACT;


    @NotNull
    public static final Companion Companion = new Companion(null);

    /* compiled from: Modality.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/Modality$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final Modality convertFromFlags(boolean sealed, boolean z, boolean open) {
            return sealed ? Modality.SEALED : z ? Modality.ABSTRACT : open ? Modality.OPEN : Modality.FINAL;
        }
    }
}
