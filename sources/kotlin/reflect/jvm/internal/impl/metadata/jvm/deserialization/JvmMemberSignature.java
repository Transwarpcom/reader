package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JvmMemberSignature.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmMemberSignature.class */
public abstract class JvmMemberSignature {
    @NotNull
    public abstract String getName();

    @NotNull
    public abstract String getDesc();

    @NotNull
    public abstract String asString();

    public /* synthetic */ JvmMemberSignature(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    private JvmMemberSignature() {
    }

    /* compiled from: JvmMemberSignature.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmMemberSignature$Method.class */
    public static final class Method extends JvmMemberSignature {

        @NotNull
        private final String name;

        @NotNull
        private final String desc;

        public int hashCode() {
            int result = getName().hashCode();
            return (result * 31) + getDesc().hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Method)) {
                return false;
            }
            Method method = (Method) other;
            return Intrinsics.areEqual(getName(), method.getName()) && Intrinsics.areEqual(getDesc(), method.getDesc());
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Method(@NotNull String name, @NotNull String desc) {
            super(null);
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(desc, "desc");
            this.name = name;
            this.desc = desc;
        }

        @Override // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        @NotNull
        public String getName() {
            return this.name;
        }

        @Override // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        @NotNull
        public String getDesc() {
            return this.desc;
        }

        @Override // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        @NotNull
        public String asString() {
            return Intrinsics.stringPlus(getName(), getDesc());
        }
    }

    /* compiled from: JvmMemberSignature.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmMemberSignature$Field.class */
    public static final class Field extends JvmMemberSignature {

        @NotNull
        private final String name;

        @NotNull
        private final String desc;

        @NotNull
        public final String component1() {
            return getName();
        }

        @NotNull
        public final String component2() {
            return getDesc();
        }

        public int hashCode() {
            int result = getName().hashCode();
            return (result * 31) + getDesc().hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Field)) {
                return false;
            }
            Field field = (Field) other;
            return Intrinsics.areEqual(getName(), field.getName()) && Intrinsics.areEqual(getDesc(), field.getDesc());
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Field(@NotNull String name, @NotNull String desc) {
            super(null);
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(desc, "desc");
            this.name = name;
            this.desc = desc;
        }

        @Override // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        @NotNull
        public String getName() {
            return this.name;
        }

        @Override // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        @NotNull
        public String getDesc() {
            return this.desc;
        }

        @Override // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        @NotNull
        public String asString() {
            return getName() + ':' + getDesc();
        }
    }

    @NotNull
    public final String toString() {
        return asString();
    }
}
