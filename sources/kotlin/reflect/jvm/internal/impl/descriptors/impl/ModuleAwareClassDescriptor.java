package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;

/* compiled from: ModuleAwareClassDescriptor.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/ModuleAwareClassDescriptor.class */
public abstract class ModuleAwareClassDescriptor implements ClassDescriptor {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    protected abstract MemberScope getUnsubstitutedMemberScope(@NotNull KotlinTypeRefiner kotlinTypeRefiner);

    @NotNull
    protected abstract MemberScope getMemberScope(@NotNull TypeSubstitution typeSubstitution, @NotNull KotlinTypeRefiner kotlinTypeRefiner);

    /* compiled from: ModuleAwareClassDescriptor.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/ModuleAwareClassDescriptor$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final MemberScope getRefinedUnsubstitutedMemberScopeIfPossible$descriptors(@NotNull ClassDescriptor $this$getRefinedUnsubstitutedMemberScopeIfPossible, @NotNull KotlinTypeRefiner kotlinTypeRefiner) {
            Intrinsics.checkNotNullParameter($this$getRefinedUnsubstitutedMemberScopeIfPossible, "<this>");
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
            ModuleAwareClassDescriptor moduleAwareClassDescriptor = $this$getRefinedUnsubstitutedMemberScopeIfPossible instanceof ModuleAwareClassDescriptor ? (ModuleAwareClassDescriptor) $this$getRefinedUnsubstitutedMemberScopeIfPossible : null;
            if (moduleAwareClassDescriptor != null) {
                return moduleAwareClassDescriptor.getUnsubstitutedMemberScope(kotlinTypeRefiner);
            }
            MemberScope unsubstitutedMemberScope = $this$getRefinedUnsubstitutedMemberScopeIfPossible.getUnsubstitutedMemberScope();
            Intrinsics.checkNotNullExpressionValue(unsubstitutedMemberScope, "this.unsubstitutedMemberScope");
            return unsubstitutedMemberScope;
        }

        @NotNull
        public final MemberScope getRefinedMemberScopeIfPossible$descriptors(@NotNull ClassDescriptor $this$getRefinedMemberScopeIfPossible, @NotNull TypeSubstitution typeSubstitution, @NotNull KotlinTypeRefiner kotlinTypeRefiner) {
            Intrinsics.checkNotNullParameter($this$getRefinedMemberScopeIfPossible, "<this>");
            Intrinsics.checkNotNullParameter(typeSubstitution, "typeSubstitution");
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
            ModuleAwareClassDescriptor moduleAwareClassDescriptor = $this$getRefinedMemberScopeIfPossible instanceof ModuleAwareClassDescriptor ? (ModuleAwareClassDescriptor) $this$getRefinedMemberScopeIfPossible : null;
            if (moduleAwareClassDescriptor != null) {
                return moduleAwareClassDescriptor.getMemberScope(typeSubstitution, kotlinTypeRefiner);
            }
            MemberScope memberScope = $this$getRefinedMemberScopeIfPossible.getMemberScope(typeSubstitution);
            Intrinsics.checkNotNullExpressionValue(memberScope, "this.getMemberScope(\n                typeSubstitution\n            )");
            return memberScope;
        }
    }
}
