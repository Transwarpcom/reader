package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.LinkedList;
import java.util.List;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import org.jetbrains.annotations.NotNull;

/* compiled from: NameResolverImpl.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/NameResolverImpl.class */
public final class NameResolverImpl implements NameResolver {

    @NotNull
    private final ProtoBuf.StringTable strings;

    @NotNull
    private final ProtoBuf.QualifiedNameTable qualifiedNames;

    /* compiled from: NameResolverImpl.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/NameResolverImpl$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProtoBuf.QualifiedNameTable.QualifiedName.Kind.values().length];
            iArr[ProtoBuf.QualifiedNameTable.QualifiedName.Kind.CLASS.ordinal()] = 1;
            iArr[ProtoBuf.QualifiedNameTable.QualifiedName.Kind.PACKAGE.ordinal()] = 2;
            iArr[ProtoBuf.QualifiedNameTable.QualifiedName.Kind.LOCAL.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public NameResolverImpl(@NotNull ProtoBuf.StringTable strings, @NotNull ProtoBuf.QualifiedNameTable qualifiedNames) {
        Intrinsics.checkNotNullParameter(strings, "strings");
        Intrinsics.checkNotNullParameter(qualifiedNames, "qualifiedNames");
        this.strings = strings;
        this.qualifiedNames = qualifiedNames;
    }

    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    @NotNull
    public String getString(int index) {
        String string = this.strings.getString(index);
        Intrinsics.checkNotNullExpressionValue(string, "strings.getString(index)");
        return string;
    }

    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    @NotNull
    public String getQualifiedClassName(int index) {
        Triple<List<String>, List<String>, Boolean> tripleTraverseIds = traverseIds(index);
        List packageFqNameSegments = tripleTraverseIds.component1();
        List relativeClassNameSegments = tripleTraverseIds.component2();
        String className = CollectionsKt.joinToString$default(relativeClassNameSegments, ".", null, null, 0, null, null, 62, null);
        return packageFqNameSegments.isEmpty() ? className : CollectionsKt.joinToString$default(packageFqNameSegments, "/", null, null, 0, null, null, 62, null) + '/' + className;
    }

    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    public boolean isLocalClassName(int index) {
        return traverseIds(index).getThird().booleanValue();
    }

    private final Triple<List<String>, List<String>, Boolean> traverseIds(int startingIndex) {
        int index = startingIndex;
        LinkedList packageNameSegments = new LinkedList();
        LinkedList relativeClassNameSegments = new LinkedList();
        boolean local = false;
        while (index != -1) {
            ProtoBuf.QualifiedNameTable.QualifiedName proto = this.qualifiedNames.getQualifiedName(index);
            String shortName = this.strings.getString(proto.getShortName());
            ProtoBuf.QualifiedNameTable.QualifiedName.Kind kind = proto.getKind();
            Intrinsics.checkNotNull(kind);
            switch (WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
                case 1:
                    relativeClassNameSegments.addFirst(shortName);
                    break;
                case 2:
                    packageNameSegments.addFirst(shortName);
                    break;
                case 3:
                    relativeClassNameSegments.addFirst(shortName);
                    local = true;
                    break;
            }
            index = proto.getParentQualifiedName();
        }
        return new Triple<>(packageNameSegments, relativeClassNameSegments, Boolean.valueOf(local));
    }
}
