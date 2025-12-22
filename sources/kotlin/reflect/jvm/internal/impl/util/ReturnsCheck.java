package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.util.Check;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: modifierChecks.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/util/ReturnsCheck.class */
public abstract class ReturnsCheck implements Check {

    @NotNull
    private final String name;

    @NotNull
    private final Function1<KotlinBuiltIns, KotlinType> type;

    @NotNull
    private final String description;

    public /* synthetic */ ReturnsCheck(String name, Function1 type, DefaultConstructorMarker $constructor_marker) {
        this(name, type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ReturnsCheck(String name, Function1<? super KotlinBuiltIns, ? extends KotlinType> function1) {
        this.name = name;
        this.type = function1;
        this.description = Intrinsics.stringPlus("must return ", this.name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.util.Check
    @Nullable
    public String invoke(@NotNull FunctionDescriptor functionDescriptor) {
        return Check.DefaultImpls.invoke(this, functionDescriptor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.util.Check
    @NotNull
    public String getDescription() {
        return this.description;
    }

    @Override // kotlin.reflect.jvm.internal.impl.util.Check
    public boolean check(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkNotNullParameter(functionDescriptor, "functionDescriptor");
        return Intrinsics.areEqual(functionDescriptor.getReturnType(), this.type.invoke(DescriptorUtilsKt.getBuiltIns(functionDescriptor)));
    }

    /* compiled from: modifierChecks.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/util/ReturnsCheck$ReturnsBoolean.class */
    public static final class ReturnsBoolean extends ReturnsCheck {

        @NotNull
        public static final ReturnsBoolean INSTANCE = new ReturnsBoolean();

        private ReturnsBoolean() {
            super("Boolean", new Function1<KotlinBuiltIns, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsBoolean.1
                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final KotlinType invoke(@NotNull KotlinBuiltIns $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                    SimpleType booleanType = $this$null.getBooleanType();
                    Intrinsics.checkNotNullExpressionValue(booleanType, "booleanType");
                    return booleanType;
                }
            }, null);
        }
    }

    /* compiled from: modifierChecks.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/util/ReturnsCheck$ReturnsInt.class */
    public static final class ReturnsInt extends ReturnsCheck {

        @NotNull
        public static final ReturnsInt INSTANCE = new ReturnsInt();

        private ReturnsInt() {
            super("Int", new Function1<KotlinBuiltIns, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsInt.1
                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final KotlinType invoke(@NotNull KotlinBuiltIns $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                    SimpleType intType = $this$null.getIntType();
                    Intrinsics.checkNotNullExpressionValue(intType, "intType");
                    return intType;
                }
            }, null);
        }
    }

    /* compiled from: modifierChecks.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/util/ReturnsCheck$ReturnsUnit.class */
    public static final class ReturnsUnit extends ReturnsCheck {

        @NotNull
        public static final ReturnsUnit INSTANCE = new ReturnsUnit();

        private ReturnsUnit() {
            super("Unit", new Function1<KotlinBuiltIns, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsUnit.1
                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final KotlinType invoke(@NotNull KotlinBuiltIns $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                    SimpleType unitType = $this$null.getUnitType();
                    Intrinsics.checkNotNullExpressionValue(unitType, "unitType");
                    return unitType;
                }
            }, null);
        }
    }
}
