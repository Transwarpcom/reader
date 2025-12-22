package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MemberScope.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/scopes/DescriptorKindFilter.class */
public final class DescriptorKindFilter {

    @NotNull
    private final List<DescriptorKindExclude> excludes;
    private final int kindMask;

    @NotNull
    private static final List<Companion.MaskToName> DEBUG_PREDEFINED_FILTERS_MASK_NAMES;

    @NotNull
    private static final List<Companion.MaskToName> DEBUG_MASK_BIT_NAMES;

    @NotNull
    public static final Companion Companion = new Companion(null);
    private static int nextMaskValue = 1;
    private static final int NON_SINGLETON_CLASSIFIERS_MASK = Companion.nextMask();
    private static final int SINGLETON_CLASSIFIERS_MASK = Companion.nextMask();
    private static final int TYPE_ALIASES_MASK = Companion.nextMask();
    private static final int PACKAGES_MASK = Companion.nextMask();
    private static final int FUNCTIONS_MASK = Companion.nextMask();
    private static final int VARIABLES_MASK = Companion.nextMask();
    private static final int ALL_KINDS_MASK = Companion.nextMask() - 1;
    private static final int CLASSIFIERS_MASK = (NON_SINGLETON_CLASSIFIERS_MASK | SINGLETON_CLASSIFIERS_MASK) | TYPE_ALIASES_MASK;
    private static final int VALUES_MASK = (SINGLETON_CLASSIFIERS_MASK | FUNCTIONS_MASK) | VARIABLES_MASK;
    private static final int CALLABLES_MASK = FUNCTIONS_MASK | VARIABLES_MASK;

    @JvmField
    @NotNull
    public static final DescriptorKindFilter ALL = new DescriptorKindFilter(ALL_KINDS_MASK, null, 2, null);

    @JvmField
    @NotNull
    public static final DescriptorKindFilter CALLABLES = new DescriptorKindFilter(CALLABLES_MASK, null, 2, null);

    @JvmField
    @NotNull
    public static final DescriptorKindFilter NON_SINGLETON_CLASSIFIERS = new DescriptorKindFilter(NON_SINGLETON_CLASSIFIERS_MASK, null, 2, null);

    @JvmField
    @NotNull
    public static final DescriptorKindFilter SINGLETON_CLASSIFIERS = new DescriptorKindFilter(SINGLETON_CLASSIFIERS_MASK, null, 2, null);

    @JvmField
    @NotNull
    public static final DescriptorKindFilter TYPE_ALIASES = new DescriptorKindFilter(TYPE_ALIASES_MASK, null, 2, null);

    @JvmField
    @NotNull
    public static final DescriptorKindFilter CLASSIFIERS = new DescriptorKindFilter(CLASSIFIERS_MASK, null, 2, null);

    @JvmField
    @NotNull
    public static final DescriptorKindFilter PACKAGES = new DescriptorKindFilter(PACKAGES_MASK, null, 2, null);

    @JvmField
    @NotNull
    public static final DescriptorKindFilter FUNCTIONS = new DescriptorKindFilter(FUNCTIONS_MASK, null, 2, null);

    @JvmField
    @NotNull
    public static final DescriptorKindFilter VARIABLES = new DescriptorKindFilter(VARIABLES_MASK, null, 2, null);

    @JvmField
    @NotNull
    public static final DescriptorKindFilter VALUES = new DescriptorKindFilter(VALUES_MASK, null, 2, null);

    /* JADX WARN: Multi-variable type inference failed */
    public DescriptorKindFilter(int kindMask, @NotNull List<? extends DescriptorKindExclude> excludes) {
        Intrinsics.checkNotNullParameter(excludes, "excludes");
        this.excludes = excludes;
        int mask = kindMask;
        Iterable $this$forEach$iv = this.excludes;
        for (Object element$iv : $this$forEach$iv) {
            DescriptorKindExclude it = (DescriptorKindExclude) element$iv;
            mask &= it.getFullyExcludedDescriptorKinds() ^ (-1);
        }
        this.kindMask = mask;
    }

    public /* synthetic */ DescriptorKindFilter(int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? CollectionsKt.emptyList() : list);
    }

    @NotNull
    public final List<DescriptorKindExclude> getExcludes() {
        return this.excludes;
    }

    public final int getKindMask() {
        return this.kindMask;
    }

    public final boolean acceptsKinds(int kinds) {
        return (this.kindMask & kinds) != 0;
    }

    @Nullable
    public final DescriptorKindFilter restrictedToKindsOrNull(int kinds) {
        int mask = this.kindMask & kinds;
        if (mask == 0) {
            return null;
        }
        return new DescriptorKindFilter(mask, this.excludes);
    }

    @NotNull
    public String toString() {
        Object obj;
        String strJoinToString$default;
        Iterable $this$firstOrNull$iv = DEBUG_PREDEFINED_FILTERS_MASK_NAMES;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv = it.next();
                if (((Companion.MaskToName) element$iv).getMask() == getKindMask()) {
                    obj = element$iv;
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        Companion.MaskToName maskToName = (Companion.MaskToName) obj;
        String predefinedFilterName = maskToName == null ? null : maskToName.getName();
        if (predefinedFilterName != null) {
            strJoinToString$default = predefinedFilterName;
        } else {
            Iterable $this$mapNotNull$iv = DEBUG_MASK_BIT_NAMES;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                Companion.MaskToName it2 = (Companion.MaskToName) element$iv$iv$iv;
                String name = acceptsKinds(it2.getMask()) ? it2.getName() : null;
                if (name != null) {
                    destination$iv$iv.add(name);
                }
            }
            strJoinToString$default = CollectionsKt.joinToString$default((List) destination$iv$iv, " | ", null, null, 0, null, null, 62, null);
        }
        String kindString = strJoinToString$default;
        return "DescriptorKindFilter(" + kindString + ", " + this.excludes + ')';
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other == null ? null : other.getClass())) {
            return false;
        }
        if (other == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.resolve.scopes.DescriptorKindFilter");
        }
        return Intrinsics.areEqual(this.excludes, ((DescriptorKindFilter) other).excludes) && this.kindMask == ((DescriptorKindFilter) other).kindMask;
    }

    public int hashCode() {
        int result = this.excludes.hashCode();
        return (31 * result) + this.kindMask;
    }

    /* compiled from: MemberScope.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/scopes/DescriptorKindFilter$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int nextMask() {
            int i = DescriptorKindFilter.nextMaskValue;
            Companion companion = DescriptorKindFilter.Companion;
            DescriptorKindFilter.nextMaskValue <<= 1;
            return i;
        }

        public final int getNON_SINGLETON_CLASSIFIERS_MASK() {
            return DescriptorKindFilter.NON_SINGLETON_CLASSIFIERS_MASK;
        }

        public final int getSINGLETON_CLASSIFIERS_MASK() {
            return DescriptorKindFilter.SINGLETON_CLASSIFIERS_MASK;
        }

        public final int getTYPE_ALIASES_MASK() {
            return DescriptorKindFilter.TYPE_ALIASES_MASK;
        }

        public final int getPACKAGES_MASK() {
            return DescriptorKindFilter.PACKAGES_MASK;
        }

        public final int getFUNCTIONS_MASK() {
            return DescriptorKindFilter.FUNCTIONS_MASK;
        }

        public final int getVARIABLES_MASK() {
            return DescriptorKindFilter.VARIABLES_MASK;
        }

        public final int getALL_KINDS_MASK() {
            return DescriptorKindFilter.ALL_KINDS_MASK;
        }

        public final int getCLASSIFIERS_MASK() {
            return DescriptorKindFilter.CLASSIFIERS_MASK;
        }

        /* compiled from: MemberScope.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/scopes/DescriptorKindFilter$Companion$MaskToName.class */
        private static final class MaskToName {
            private final int mask;

            @NotNull
            private final String name;

            public MaskToName(int mask, @NotNull String name) {
                Intrinsics.checkNotNullParameter(name, "name");
                this.mask = mask;
                this.name = name;
            }

            public final int getMask() {
                return this.mask;
            }

            @NotNull
            public final String getName() {
                return this.name;
            }
        }
    }

    static {
        Companion.MaskToName maskToName;
        Companion.MaskToName maskToName2;
        Companion companion = Companion;
        Field[] fields = DescriptorKindFilter.class.getFields();
        Intrinsics.checkNotNullExpressionValue(fields, "T::class.java.fields");
        Field[] fieldArr = fields;
        Collection destination$iv$iv$iv = new ArrayList();
        for (Field field : fieldArr) {
            Field it$iv = field;
            if (Modifier.isStatic(it$iv.getModifiers())) {
                destination$iv$iv$iv.add(field);
            }
        }
        Iterable $this$mapNotNull$iv = (List) destination$iv$iv$iv;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            Field field2 = (Field) element$iv$iv$iv;
            Object obj = field2.get(null);
            DescriptorKindFilter filter = obj instanceof DescriptorKindFilter ? (DescriptorKindFilter) obj : null;
            if (filter != null) {
                int kindMask = filter.getKindMask();
                String name = field2.getName();
                Intrinsics.checkNotNullExpressionValue(name, "field.name");
                maskToName2 = new Companion.MaskToName(kindMask, name);
            } else {
                maskToName2 = null;
            }
            if (maskToName2 != null) {
                destination$iv$iv.add(maskToName2);
            }
        }
        DEBUG_PREDEFINED_FILTERS_MASK_NAMES = (List) destination$iv$iv;
        Companion companion2 = Companion;
        Field[] fields2 = DescriptorKindFilter.class.getFields();
        Intrinsics.checkNotNullExpressionValue(fields2, "T::class.java.fields");
        Field[] fieldArr2 = fields2;
        Collection destination$iv$iv$iv2 = new ArrayList();
        for (Field field3 : fieldArr2) {
            Field it$iv2 = field3;
            if (Modifier.isStatic(it$iv2.getModifiers())) {
                destination$iv$iv$iv2.add(field3);
            }
        }
        Iterable $this$filter$iv = (List) destination$iv$iv$iv2;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Field it = (Field) element$iv$iv;
            if (Intrinsics.areEqual(it.getType(), Integer.TYPE)) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        Iterable $this$mapNotNull$iv2 = (List) destination$iv$iv2;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv$iv2 : $this$mapNotNull$iv2) {
            Field field4 = (Field) element$iv$iv$iv2;
            Object obj2 = field4.get(null);
            if (obj2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
            }
            int mask = ((Integer) obj2).intValue();
            boolean isOneBitMask = mask == (mask & (-mask));
            if (isOneBitMask) {
                String name2 = field4.getName();
                Intrinsics.checkNotNullExpressionValue(name2, "field.name");
                maskToName = new Companion.MaskToName(mask, name2);
            } else {
                maskToName = null;
            }
            if (maskToName != null) {
                destination$iv$iv3.add(maskToName);
            }
        }
        DEBUG_MASK_BIT_NAMES = (List) destination$iv$iv3;
    }
}
