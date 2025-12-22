package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Visibilities.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/Visibilities.class */
public final class Visibilities {

    @NotNull
    public static final Visibilities INSTANCE = new Visibilities();

    @NotNull
    private static final Map<Visibility, Integer> ORDERED_VISIBILITIES;

    @NotNull
    private static final Public DEFAULT_VISIBILITY;

    /* compiled from: Visibilities.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$Private.class */
    public static final class Private extends Visibility {

        @NotNull
        public static final Private INSTANCE = new Private();

        private Private() {
            super("private", false);
        }
    }

    private Visibilities() {
    }

    /* compiled from: Visibilities.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$PrivateToThis.class */
    public static final class PrivateToThis extends Visibility {

        @NotNull
        public static final PrivateToThis INSTANCE = new PrivateToThis();

        private PrivateToThis() {
            super("private_to_this", false);
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        @NotNull
        public String getInternalDisplayName() {
            return "private/*private to this*/";
        }
    }

    /* compiled from: Visibilities.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$Protected.class */
    public static final class Protected extends Visibility {

        @NotNull
        public static final Protected INSTANCE = new Protected();

        private Protected() {
            super("protected", true);
        }
    }

    /* compiled from: Visibilities.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$Internal.class */
    public static final class Internal extends Visibility {

        @NotNull
        public static final Internal INSTANCE = new Internal();

        private Internal() {
            super("internal", false);
        }
    }

    /* compiled from: Visibilities.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$Public.class */
    public static final class Public extends Visibility {

        @NotNull
        public static final Public INSTANCE = new Public();

        private Public() {
            super("public", true);
        }
    }

    /* compiled from: Visibilities.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$Local.class */
    public static final class Local extends Visibility {

        @NotNull
        public static final Local INSTANCE = new Local();

        private Local() {
            super("local", false);
        }
    }

    /* compiled from: Visibilities.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$Inherited.class */
    public static final class Inherited extends Visibility {

        @NotNull
        public static final Inherited INSTANCE = new Inherited();

        private Inherited() {
            super("inherited", false);
        }
    }

    /* compiled from: Visibilities.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$InvisibleFake.class */
    public static final class InvisibleFake extends Visibility {

        @NotNull
        public static final InvisibleFake INSTANCE = new InvisibleFake();

        private InvisibleFake() {
            super("invisible_fake", false);
        }
    }

    /* compiled from: Visibilities.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$Unknown.class */
    public static final class Unknown extends Visibility {

        @NotNull
        public static final Unknown INSTANCE = new Unknown();

        private Unknown() {
            super("unknown", false);
        }
    }

    static {
        Map $this$ORDERED_VISIBILITIES_u24lambda_u2d0 = MapsKt.createMapBuilder();
        $this$ORDERED_VISIBILITIES_u24lambda_u2d0.put(PrivateToThis.INSTANCE, 0);
        $this$ORDERED_VISIBILITIES_u24lambda_u2d0.put(Private.INSTANCE, 0);
        $this$ORDERED_VISIBILITIES_u24lambda_u2d0.put(Internal.INSTANCE, 1);
        $this$ORDERED_VISIBILITIES_u24lambda_u2d0.put(Protected.INSTANCE, 1);
        $this$ORDERED_VISIBILITIES_u24lambda_u2d0.put(Public.INSTANCE, 2);
        ORDERED_VISIBILITIES = MapsKt.build($this$ORDERED_VISIBILITIES_u24lambda_u2d0);
        DEFAULT_VISIBILITY = Public.INSTANCE;
    }

    @Nullable
    public final Integer compareLocal$compiler_common(@NotNull Visibility first, @NotNull Visibility second) {
        Intrinsics.checkNotNullParameter(first, "first");
        Intrinsics.checkNotNullParameter(second, "second");
        if (first == second) {
            return 0;
        }
        Integer firstIndex = ORDERED_VISIBILITIES.get(first);
        Integer secondIndex = ORDERED_VISIBILITIES.get(second);
        if (firstIndex == null || secondIndex == null || Intrinsics.areEqual(firstIndex, secondIndex)) {
            return (Integer) null;
        }
        return Integer.valueOf(firstIndex.intValue() - secondIndex.intValue());
    }

    public final boolean isPrivate(@NotNull Visibility visibility) {
        Intrinsics.checkNotNullParameter(visibility, "visibility");
        return visibility == Private.INSTANCE || visibility == PrivateToThis.INSTANCE;
    }
}
