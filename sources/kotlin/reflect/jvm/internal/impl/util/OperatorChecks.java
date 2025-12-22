package kotlin.reflect.jvm.internal.impl.util;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.util.MemberKindCheck;
import kotlin.reflect.jvm.internal.impl.util.ReturnsCheck;
import kotlin.reflect.jvm.internal.impl.util.ValueParameterCountCheck;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: modifierChecks.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/util/OperatorChecks.class */
public final class OperatorChecks extends AbstractModifierChecks {

    @NotNull
    public static final OperatorChecks INSTANCE = new OperatorChecks();

    @NotNull
    private static final List<Checks> checks = CollectionsKt.listOf((Object[]) new Checks[]{new Checks(OperatorNameConventions.GET, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, new ValueParameterCountCheck.AtLeast(1)}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.SET, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, new ValueParameterCountCheck.AtLeast(2)}, new Function1<FunctionDescriptor, String>() { // from class: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$1
        @Override // kotlin.jvm.functions.Function1
        @Nullable
        public final String invoke(@NotNull FunctionDescriptor $receiver) {
            Boolean boolValueOf;
            Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
            List<ValueParameterDescriptor> valueParameters = $receiver.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(valueParameters, "valueParameters");
            ValueParameterDescriptor it = (ValueParameterDescriptor) CollectionsKt.lastOrNull((List) valueParameters);
            if (it == null) {
                boolValueOf = null;
            } else {
                boolValueOf = Boolean.valueOf(!DescriptorUtilsKt.declaresOrInheritsDefaultValue(it) && it.getVarargElementType() == null);
            }
            boolean lastIsOk = Intrinsics.areEqual((Object) boolValueOf, (Object) true);
            OperatorChecks operatorChecks = OperatorChecks.INSTANCE;
            if (lastIsOk) {
                return null;
            }
            return "last parameter should not have a default value or be a vararg";
        }
    }), new Checks(OperatorNameConventions.GET_VALUE, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new ValueParameterCountCheck.AtLeast(2), IsKPropertyCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.SET_VALUE, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new ValueParameterCountCheck.AtLeast(3), IsKPropertyCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.PROVIDE_DELEGATE, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new ValueParameterCountCheck.Equals(2), IsKPropertyCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.INVOKE, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.CONTAINS, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, ReturnsCheck.ReturnsBoolean.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.ITERATOR, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.NEXT, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.HAS_NEXT, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE, ReturnsCheck.ReturnsBoolean.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.RANGE_TO, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.EQUALS, new Check[]{MemberKindCheck.Member.INSTANCE}, new Function1<FunctionDescriptor, String>() { // from class: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$2
        private static final boolean invoke$isAny(DeclarationDescriptor $this$invoke_u24isAny) {
            return ($this$invoke_u24isAny instanceof ClassDescriptor) && KotlinBuiltIns.isAny((ClassDescriptor) $this$invoke_u24isAny);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x008b  */
        @Override // kotlin.jvm.functions.Function1
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.String invoke(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r4) {
            /*
                r3 = this;
                r0 = r4
                java.lang.String r1 = "$this$$receiver"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlin.reflect.jvm.internal.impl.util.OperatorChecks r0 = kotlin.reflect.jvm.internal.impl.util.OperatorChecks.INSTANCE
                kotlin.reflect.jvm.internal.impl.util.AbstractModifierChecks r0 = (kotlin.reflect.jvm.internal.impl.util.AbstractModifierChecks) r0
                r5 = r0
                r0 = r4
                kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r0.getContainingDeclaration()
                r6 = r0
                r0 = r6
                java.lang.String r1 = "containingDeclaration"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                r0 = r6
                boolean r0 = invoke$isAny(r0)
                if (r0 != 0) goto L8b
                r0 = r4
                java.util.Collection r0 = r0.getOverriddenDescriptors()
                r6 = r0
                r0 = r6
                java.lang.String r1 = "overriddenDescriptors"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                r0 = r6
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                r6 = r0
                r0 = 0
                r7 = r0
                r0 = r6
                java.util.Collection r0 = (java.util.Collection) r0
                boolean r0 = r0.isEmpty()
                if (r0 == 0) goto L46
                r0 = 0
                goto L88
            L46:
                r0 = r6
                java.util.Iterator r0 = r0.iterator()
                r8 = r0
            L4e:
                r0 = r8
                boolean r0 = r0.hasNext()
                if (r0 == 0) goto L87
                r0 = r8
                java.lang.Object r0 = r0.next()
                r9 = r0
                r0 = r9
                kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r0
                r10 = r0
                r0 = 0
                r11 = r0
                r0 = r10
                kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r0.getContainingDeclaration()
                r12 = r0
                r0 = r12
                java.lang.String r1 = "it.containingDeclaration"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                r0 = r12
                boolean r0 = invoke$isAny(r0)
                if (r0 == 0) goto L4e
                r0 = 1
                goto L88
            L87:
                r0 = 0
            L88:
                if (r0 == 0) goto L8f
            L8b:
                r0 = 1
                goto L90
            L8f:
                r0 = 0
            L90:
                r6 = r0
                r0 = 0
                r7 = r0
                r0 = r6
                if (r0 != 0) goto La1
                r0 = 0
                r8 = r0
                java.lang.String r0 = "must override ''equals()'' in Any"
                goto La2
            La1:
                r0 = 0
            La2:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$2.invoke(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor):java.lang.String");
        }
    }), new Checks(OperatorNameConventions.COMPARE_TO, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ReturnsCheck.ReturnsInt.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.BINARY_OPERATION_NAMES, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.SIMPLE_UNARY_OPERATION_NAMES, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(CollectionsKt.listOf((Object[]) new Name[]{OperatorNameConventions.INC, OperatorNameConventions.DEC}), new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE}, new Function1<FunctionDescriptor, String>() { // from class: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$3
        /* JADX WARN: Removed duplicated region for block: B:14:0x0059  */
        @Override // kotlin.jvm.functions.Function1
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.String invoke(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r5) {
            /*
                r4 = this;
                r0 = r5
                java.lang.String r1 = "$this$$receiver"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                r0 = r5
                kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r0 = r0.getDispatchReceiverParameter()
                r7 = r0
                r0 = r7
                if (r0 != 0) goto L1a
                r0 = r5
                kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r0 = r0.getExtensionReceiverParameter()
                goto L1b
            L1a:
                r0 = r7
            L1b:
                r6 = r0
                kotlin.reflect.jvm.internal.impl.util.OperatorChecks r0 = kotlin.reflect.jvm.internal.impl.util.OperatorChecks.INSTANCE
                kotlin.reflect.jvm.internal.impl.util.AbstractModifierChecks r0 = (kotlin.reflect.jvm.internal.impl.util.AbstractModifierChecks) r0
                r7 = r0
                r0 = r6
                if (r0 == 0) goto L59
                r0 = r5
                kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getReturnType()
                r8 = r0
                r0 = r8
                if (r0 != 0) goto L38
                r0 = 0
                goto L52
            L38:
                r0 = r8
                r1 = r6
                kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = r1.getType()
                r10 = r1
                r1 = r10
                java.lang.String r2 = "receiver.type"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                r1 = r10
                boolean r0 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.isSubtypeOf(r0, r1)
                r9 = r0
                r0 = r9
            L52:
                if (r0 == 0) goto L59
                r0 = 1
                goto L5a
            L59:
                r0 = 0
            L5a:
                r8 = r0
                r0 = 0
                r9 = r0
                r0 = r8
                if (r0 != 0) goto L6c
                r0 = 0
                r10 = r0
                java.lang.String r0 = "receiver must be a supertype of the return type"
                goto L6d
            L6c:
                r0 = 0
            L6d:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$3.invoke(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor):java.lang.String");
        }
    }), new Checks(OperatorNameConventions.ASSIGNMENT_OPERATIONS, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ReturnsCheck.ReturnsUnit.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.COMPONENT_REGEX, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null)});

    private OperatorChecks() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.util.AbstractModifierChecks
    @NotNull
    public List<Checks> getChecks$descriptors() {
        return checks;
    }
}
