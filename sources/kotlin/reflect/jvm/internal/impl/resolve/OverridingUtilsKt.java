package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.jetbrains.annotations.NotNull;

/* compiled from: overridingUtils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/OverridingUtilsKt.class */
public final class OverridingUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <H> Collection<H> selectMostSpecificInEachOverridableGroup(@NotNull Collection<? extends H> collection, @NotNull Function1<? super H, ? extends CallableDescriptor> descriptorByHandle) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(descriptorByHandle, "descriptorByHandle");
        if (collection.size() <= 1) {
            return collection;
        }
        LinkedList queue = new LinkedList(collection);
        SmartSet result = SmartSet.Companion.create();
        while (true) {
            if (!queue.isEmpty()) {
                Object nextHandle = CollectionsKt.first((List<? extends Object>) queue);
                final SmartSet conflictedHandles = SmartSet.Companion.create();
                Collection overridableGroup = OverridingUtil.extractMembersOverridableInBothWays(nextHandle, queue, descriptorByHandle, new Function1<H, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt$selectMostSpecificInEachOverridableGroup$overridableGroup$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(H it) {
                        SmartSet<H> smartSet = conflictedHandles;
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        smartSet.add(it);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                        invoke2((OverridingUtilsKt$selectMostSpecificInEachOverridableGroup$overridableGroup$1<H>) obj);
                        return Unit.INSTANCE;
                    }
                });
                Intrinsics.checkNotNullExpressionValue(overridableGroup, "val conflictedHandles = SmartSet.create<H>()\n\n        val overridableGroup =\n            OverridingUtil.extractMembersOverridableInBothWays(nextHandle, queue, descriptorByHandle) { conflictedHandles.add(it) }");
                if (overridableGroup.size() == 1 && conflictedHandles.isEmpty()) {
                    Object objSingle = CollectionsKt.single(overridableGroup);
                    Intrinsics.checkNotNullExpressionValue(objSingle, "overridableGroup.single()");
                    result.add(objSingle);
                } else {
                    Object mostSpecific = OverridingUtil.selectMostSpecificMember(overridableGroup, descriptorByHandle);
                    Intrinsics.checkNotNullExpressionValue(mostSpecific, "selectMostSpecificMember(overridableGroup, descriptorByHandle)");
                    CallableDescriptor mostSpecificDescriptor = descriptorByHandle.invoke(mostSpecific);
                    Collection $this$filterNotTo$iv = overridableGroup;
                    for (Object it : $this$filterNotTo$iv) {
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        if (!OverridingUtil.isMoreSpecific(mostSpecificDescriptor, descriptorByHandle.invoke(it))) {
                            conflictedHandles.add(it);
                        }
                    }
                    if (!conflictedHandles.isEmpty()) {
                        result.addAll(conflictedHandles);
                    }
                    result.add(mostSpecific);
                }
            } else {
                return result;
            }
        }
    }
}
