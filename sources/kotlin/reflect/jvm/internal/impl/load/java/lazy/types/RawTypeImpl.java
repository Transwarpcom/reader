package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.RawType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: RawType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/types/RawTypeImpl.class */
public final class RawTypeImpl extends FlexibleType implements RawType {
    private RawTypeImpl(SimpleType lowerBound, SimpleType upperBound, boolean disableAssertion) {
        super(lowerBound, upperBound);
        if (disableAssertion) {
            return;
        }
        boolean zIsSubtypeOf = KotlinTypeChecker.DEFAULT.isSubtypeOf(lowerBound, upperBound);
        if (!_Assertions.ENABLED || zIsSubtypeOf) {
        } else {
            throw new AssertionError("Lower bound " + lowerBound + " of a flexible type must be a subtype of the upper bound " + upperBound);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RawTypeImpl(@NotNull SimpleType lowerBound, @NotNull SimpleType upperBound) {
        this(lowerBound, upperBound, false);
        Intrinsics.checkNotNullParameter(lowerBound, "lowerBound");
        Intrinsics.checkNotNullParameter(upperBound, "upperBound");
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    @NotNull
    public SimpleType getDelegate() {
        return getLowerBound();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    public MemberScope getMemberScope() {
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = getConstructor().mo3831getDeclarationDescriptor();
        ClassDescriptor classDescriptor = classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor : null;
        if (classDescriptor == null) {
            throw new IllegalStateException(Intrinsics.stringPlus("Incorrect classifier: ", getConstructor().mo3831getDeclarationDescriptor()).toString());
        }
        MemberScope memberScope = classDescriptor.getMemberScope(RawSubstitution.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(memberScope, "classDescriptor.getMemberScope(RawSubstitution)");
        return memberScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    public RawTypeImpl replaceAnnotations(@NotNull Annotations newAnnotations) {
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        return new RawTypeImpl(getLowerBound().replaceAnnotations(newAnnotations), getUpperBound().replaceAnnotations(newAnnotations));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    public RawTypeImpl makeNullableAsSpecified(boolean newNullability) {
        return new RawTypeImpl(getLowerBound().makeNullableAsSpecified(newNullability), getUpperBound().makeNullableAsSpecified(newNullability));
    }

    private static final boolean render$onlyOutDiffers(String first, String second) {
        return Intrinsics.areEqual(first, StringsKt.removePrefix(second, (CharSequence) "out ")) || Intrinsics.areEqual(second, "*");
    }

    private static final List<String> render$renderArguments(DescriptorRenderer $renderer, KotlinType type) {
        Iterable $this$map$iv = type.getArguments();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            TypeProjection it = (TypeProjection) item$iv$iv;
            destination$iv$iv.add($renderer.renderTypeProjection(it));
        }
        return (List) destination$iv$iv;
    }

    private static final String render$replaceArgs(String $this$render_u24replaceArgs, String newArgs) {
        return !StringsKt.contains$default((CharSequence) $this$render_u24replaceArgs, '<', false, 2, (Object) null) ? $this$render_u24replaceArgs : StringsKt.substringBefore$default($this$render_u24replaceArgs, '<', (String) null, 2, (Object) null) + '<' + newArgs + '>' + StringsKt.substringAfterLast$default($this$render_u24replaceArgs, '>', (String) null, 2, (Object) null);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    @NotNull
    public String render(@NotNull DescriptorRenderer renderer, @NotNull DescriptorRendererOptions options) {
        boolean z;
        String strRender$replaceArgs;
        Intrinsics.checkNotNullParameter(renderer, "renderer");
        Intrinsics.checkNotNullParameter(options, "options");
        String lowerRendered = renderer.renderType(getLowerBound());
        String upperRendered = renderer.renderType(getUpperBound());
        if (options.getDebugMode()) {
            return "raw (" + lowerRendered + ".." + upperRendered + ')';
        }
        if (getUpperBound().getArguments().isEmpty()) {
            return renderer.renderFlexibleType(lowerRendered, upperRendered, TypeUtilsKt.getBuiltIns(this));
        }
        List lowerArgs = render$renderArguments(renderer, getLowerBound());
        List upperArgs = render$renderArguments(renderer, getUpperBound());
        String newArgs = CollectionsKt.joinToString$default(lowerArgs, ", ", null, null, 0, null, new Function1<String, CharSequence>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl$render$newArgs$1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final CharSequence invoke(@NotNull String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Intrinsics.stringPlus("(raw) ", it);
            }
        }, 30, null);
        Iterable $this$all$iv = CollectionsKt.zip(lowerArgs, upperArgs);
        if (!($this$all$iv instanceof Collection) || !((Collection) $this$all$iv).isEmpty()) {
            Iterator it = $this$all$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    Pair it2 = (Pair) element$iv;
                    if (!render$onlyOutDiffers((String) it2.getFirst(), (String) it2.getSecond())) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (z) {
            strRender$replaceArgs = render$replaceArgs(upperRendered, newArgs);
        } else {
            strRender$replaceArgs = upperRendered;
        }
        String newUpper = strRender$replaceArgs;
        String newLower = render$replaceArgs(lowerRendered, newArgs);
        return Intrinsics.areEqual(newLower, newUpper) ? newLower : renderer.renderFlexibleType(newLower, newUpper, TypeUtilsKt.getBuiltIns(this));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    public FlexibleType refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return new RawTypeImpl((SimpleType) kotlinTypeRefiner.refineType(getLowerBound()), (SimpleType) kotlinTypeRefiner.refineType(getUpperBound()), true);
    }
}
