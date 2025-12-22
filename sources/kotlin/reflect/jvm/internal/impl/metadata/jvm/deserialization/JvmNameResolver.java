package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmNameResolver.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmNameResolver.class */
public final class JvmNameResolver implements NameResolver {

    @NotNull
    private final JvmProtoBuf.StringTableTypes types;

    @NotNull
    private final String[] strings;

    @NotNull
    private final Set<Integer> localNameIndices;

    @NotNull
    private final List<JvmProtoBuf.StringTableTypes.Record> records;

    @NotNull
    private static final Map<String, Integer> PREDEFINED_STRINGS_MAP;

    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: kotlin, reason: collision with root package name */
    @NotNull
    private static final String f10kotlin = CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new Character[]{'k', 'o', 't', 'l', 'i', 'n'}), "", null, null, 0, null, null, 62, null);

    @NotNull
    private static final List<String> PREDEFINED_STRINGS = CollectionsKt.listOf((Object[]) new String[]{Intrinsics.stringPlus(f10kotlin, "/Any"), Intrinsics.stringPlus(f10kotlin, "/Nothing"), Intrinsics.stringPlus(f10kotlin, "/Unit"), Intrinsics.stringPlus(f10kotlin, "/Throwable"), Intrinsics.stringPlus(f10kotlin, "/Number"), Intrinsics.stringPlus(f10kotlin, "/Byte"), Intrinsics.stringPlus(f10kotlin, "/Double"), Intrinsics.stringPlus(f10kotlin, "/Float"), Intrinsics.stringPlus(f10kotlin, "/Int"), Intrinsics.stringPlus(f10kotlin, "/Long"), Intrinsics.stringPlus(f10kotlin, "/Short"), Intrinsics.stringPlus(f10kotlin, "/Boolean"), Intrinsics.stringPlus(f10kotlin, "/Char"), Intrinsics.stringPlus(f10kotlin, "/CharSequence"), Intrinsics.stringPlus(f10kotlin, "/String"), Intrinsics.stringPlus(f10kotlin, "/Comparable"), Intrinsics.stringPlus(f10kotlin, "/Enum"), Intrinsics.stringPlus(f10kotlin, "/Array"), Intrinsics.stringPlus(f10kotlin, "/ByteArray"), Intrinsics.stringPlus(f10kotlin, "/DoubleArray"), Intrinsics.stringPlus(f10kotlin, "/FloatArray"), Intrinsics.stringPlus(f10kotlin, "/IntArray"), Intrinsics.stringPlus(f10kotlin, "/LongArray"), Intrinsics.stringPlus(f10kotlin, "/ShortArray"), Intrinsics.stringPlus(f10kotlin, "/BooleanArray"), Intrinsics.stringPlus(f10kotlin, "/CharArray"), Intrinsics.stringPlus(f10kotlin, "/Cloneable"), Intrinsics.stringPlus(f10kotlin, "/Annotation"), Intrinsics.stringPlus(f10kotlin, "/collections/Iterable"), Intrinsics.stringPlus(f10kotlin, "/collections/MutableIterable"), Intrinsics.stringPlus(f10kotlin, "/collections/Collection"), Intrinsics.stringPlus(f10kotlin, "/collections/MutableCollection"), Intrinsics.stringPlus(f10kotlin, "/collections/List"), Intrinsics.stringPlus(f10kotlin, "/collections/MutableList"), Intrinsics.stringPlus(f10kotlin, "/collections/Set"), Intrinsics.stringPlus(f10kotlin, "/collections/MutableSet"), Intrinsics.stringPlus(f10kotlin, "/collections/Map"), Intrinsics.stringPlus(f10kotlin, "/collections/MutableMap"), Intrinsics.stringPlus(f10kotlin, "/collections/Map.Entry"), Intrinsics.stringPlus(f10kotlin, "/collections/MutableMap.MutableEntry"), Intrinsics.stringPlus(f10kotlin, "/collections/Iterator"), Intrinsics.stringPlus(f10kotlin, "/collections/MutableIterator"), Intrinsics.stringPlus(f10kotlin, "/collections/ListIterator"), Intrinsics.stringPlus(f10kotlin, "/collections/MutableListIterator")});

    /* compiled from: JvmNameResolver.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmNameResolver$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JvmProtoBuf.StringTableTypes.Record.Operation.values().length];
            iArr[JvmProtoBuf.StringTableTypes.Record.Operation.NONE.ordinal()] = 1;
            iArr[JvmProtoBuf.StringTableTypes.Record.Operation.INTERNAL_TO_CLASS_ID.ordinal()] = 2;
            iArr[JvmProtoBuf.StringTableTypes.Record.Operation.DESC_TO_CLASS_ID.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public JvmNameResolver(@NotNull JvmProtoBuf.StringTableTypes types, @NotNull String[] strings) {
        Set<Integer> set;
        Intrinsics.checkNotNullParameter(types, "types");
        Intrinsics.checkNotNullParameter(strings, "strings");
        this.types = types;
        this.strings = strings;
        List $this$localNameIndices_u24lambda_u2d0 = this.types.getLocalNameList();
        if ($this$localNameIndices_u24lambda_u2d0.isEmpty()) {
            set = SetsKt.emptySet();
        } else {
            Intrinsics.checkNotNullExpressionValue($this$localNameIndices_u24lambda_u2d0, "");
            set = CollectionsKt.toSet($this$localNameIndices_u24lambda_u2d0);
        }
        this.localNameIndices = set;
        ArrayList $this$records_u24lambda_u2d2 = new ArrayList();
        List records = getTypes().getRecordList();
        $this$records_u24lambda_u2d2.ensureCapacity(records.size());
        for (JvmProtoBuf.StringTableTypes.Record record : records) {
            int range = record.getRange();
            for (int i = 0; i < range; i++) {
                $this$records_u24lambda_u2d2.add(record);
            }
        }
        $this$records_u24lambda_u2d2.trimToSize();
        Unit unit = Unit.INSTANCE;
        this.records = $this$records_u24lambda_u2d2;
    }

    @NotNull
    public final JvmProtoBuf.StringTableTypes getTypes() {
        return this.types;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0061  */
    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getString(int r8) {
        /*
            Method dump skipped, instructions count: 529
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver.getString(int):java.lang.String");
    }

    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    @NotNull
    public String getQualifiedClassName(int index) {
        return getString(index);
    }

    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    public boolean isLocalClassName(int index) {
        return this.localNameIndices.contains(Integer.valueOf(index));
    }

    /* compiled from: JvmNameResolver.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmNameResolver$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        Iterable $this$associateBy$iv = CollectionsKt.withIndex(PREDEFINED_STRINGS);
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
        Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv : $this$associateBy$iv) {
            IndexedValue it = (IndexedValue) element$iv$iv;
            IndexedValue it2 = (IndexedValue) element$iv$iv;
            destination$iv$iv.put((String) it.getValue(), Integer.valueOf(it2.getIndex()));
        }
        PREDEFINED_STRINGS_MAP = destination$iv$iv;
    }
}
