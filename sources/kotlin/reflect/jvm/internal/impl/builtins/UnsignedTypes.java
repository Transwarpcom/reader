package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: UnsignedType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/UnsignedTypes.class */
public final class UnsignedTypes {

    @NotNull
    public static final UnsignedTypes INSTANCE = new UnsignedTypes();

    @NotNull
    private static final Set<Name> unsignedTypeNames;

    @NotNull
    private static final Set<Name> unsignedArrayTypeNames;

    @NotNull
    private static final HashMap<ClassId, ClassId> arrayClassIdToUnsignedClassId;

    @NotNull
    private static final HashMap<ClassId, ClassId> unsignedClassIdToArrayClassId;

    @NotNull
    private static final HashMap<UnsignedArrayType, Name> unsignedArrayTypeToArrayCall;

    @NotNull
    private static final Set<Name> arrayClassesShortNames;

    private UnsignedTypes() {
    }

    static {
        UnsignedType[] unsignedTypeArrValues = UnsignedType.values();
        Collection destination$iv$iv = new ArrayList(unsignedTypeArrValues.length);
        for (UnsignedType unsignedType : unsignedTypeArrValues) {
            destination$iv$iv.add(unsignedType.getTypeName());
        }
        unsignedTypeNames = CollectionsKt.toSet((List) destination$iv$iv);
        UnsignedArrayType[] unsignedArrayTypeArrValues = UnsignedArrayType.values();
        Collection destination$iv$iv2 = new ArrayList(unsignedArrayTypeArrValues.length);
        for (UnsignedArrayType unsignedArrayType : unsignedArrayTypeArrValues) {
            destination$iv$iv2.add(unsignedArrayType.getTypeName());
        }
        unsignedArrayTypeNames = CollectionsKt.toSet((List) destination$iv$iv2);
        arrayClassIdToUnsignedClassId = new HashMap<>();
        unsignedClassIdToArrayClassId = new HashMap<>();
        unsignedArrayTypeToArrayCall = MapsKt.hashMapOf(TuplesKt.to(UnsignedArrayType.UBYTEARRAY, Name.identifier("ubyteArrayOf")), TuplesKt.to(UnsignedArrayType.USHORTARRAY, Name.identifier("ushortArrayOf")), TuplesKt.to(UnsignedArrayType.UINTARRAY, Name.identifier("uintArrayOf")), TuplesKt.to(UnsignedArrayType.ULONGARRAY, Name.identifier("ulongArrayOf")));
        UnsignedType[] unsignedTypeArrValues2 = UnsignedType.values();
        Collection destination$iv = (Set) new LinkedHashSet();
        for (UnsignedType unsignedType2 : unsignedTypeArrValues2) {
            destination$iv.add(unsignedType2.getArrayClassId().getShortClassName());
        }
        arrayClassesShortNames = (Set) destination$iv;
        UnsignedType[] unsignedTypeArrValues3 = UnsignedType.values();
        int i = 0;
        int length = unsignedTypeArrValues3.length;
        while (i < length) {
            UnsignedType unsignedType3 = unsignedTypeArrValues3[i];
            i++;
            arrayClassIdToUnsignedClassId.put(unsignedType3.getArrayClassId(), unsignedType3.getClassId());
            unsignedClassIdToArrayClassId.put(unsignedType3.getClassId(), unsignedType3.getArrayClassId());
        }
    }

    public final boolean isShortNameOfUnsignedArray(@NotNull Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return arrayClassesShortNames.contains(name);
    }

    @Nullable
    public final ClassId getUnsignedClassIdByArrayClassId(@NotNull ClassId arrayClassId) {
        Intrinsics.checkNotNullParameter(arrayClassId, "arrayClassId");
        return arrayClassIdToUnsignedClassId.get(arrayClassId);
    }

    @JvmStatic
    public static final boolean isUnsignedType(@NotNull KotlinType type) {
        ClassifierDescriptor descriptor;
        Intrinsics.checkNotNullParameter(type, "type");
        if (TypeUtils.noExpectedType(type) || (descriptor = type.getConstructor().mo3831getDeclarationDescriptor()) == null) {
            return false;
        }
        return INSTANCE.isUnsignedClass(descriptor);
    }

    public final boolean isUnsignedClass(@NotNull DeclarationDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        DeclarationDescriptor container = descriptor.getContainingDeclaration();
        return (container instanceof PackageFragmentDescriptor) && Intrinsics.areEqual(((PackageFragmentDescriptor) container).getFqName(), StandardNames.BUILT_INS_PACKAGE_FQ_NAME) && unsignedTypeNames.contains(descriptor.getName());
    }
}
