package kotlin.reflect.jvm.internal.impl.incremental.components;

import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LookupLocation.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/incremental/components/Position.class */
public final class Position implements Serializable {
    private final int line;
    private final int column;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final Position NO_POSITION = new Position(-1, -1);

    @NotNull
    public String toString() {
        return "Position(line=" + this.line + ", column=" + this.column + ')';
    }

    public int hashCode() {
        int result = this.line;
        return (result * 31) + this.column;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Position)) {
            return false;
        }
        Position position = (Position) other;
        return this.line == position.line && this.column == position.column;
    }

    /* compiled from: LookupLocation.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/incremental/components/Position$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final Position getNO_POSITION() {
            return Position.NO_POSITION;
        }
    }

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }
}
