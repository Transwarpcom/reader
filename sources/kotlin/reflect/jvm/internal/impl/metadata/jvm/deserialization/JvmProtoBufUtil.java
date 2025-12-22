package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* compiled from: JvmProtoBufUtil.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmProtoBufUtil.class */
public final class JvmProtoBufUtil {

    @NotNull
    public static final JvmProtoBufUtil INSTANCE = new JvmProtoBufUtil();

    @NotNull
    private static final ExtensionRegistryLite EXTENSION_REGISTRY;

    private JvmProtoBufUtil() {
    }

    @NotNull
    public final ExtensionRegistryLite getEXTENSION_REGISTRY() {
        return EXTENSION_REGISTRY;
    }

    static {
        ExtensionRegistryLite p0 = ExtensionRegistryLite.newInstance();
        JvmProtoBuf.registerAllExtensions(p0);
        Intrinsics.checkNotNullExpressionValue(p0, "newInstance().apply(JvmProtoBuf::registerAllExtensions)");
        EXTENSION_REGISTRY = p0;
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, ProtoBuf.Class> readClassDataFrom(@NotNull String[] data, @NotNull String[] strings) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(strings, "strings");
        JvmProtoBufUtil jvmProtoBufUtil = INSTANCE;
        byte[] bArrDecodeBytes = BitEncoding.decodeBytes(data);
        Intrinsics.checkNotNullExpressionValue(bArrDecodeBytes, "decodeBytes(data)");
        return readClassDataFrom(bArrDecodeBytes, strings);
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, ProtoBuf.Class> readClassDataFrom(@NotNull byte[] bytes, @NotNull String[] strings) throws IOException {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Intrinsics.checkNotNullParameter(strings, "strings");
        ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        JvmNameResolver nameResolver = INSTANCE.readNameResolver(input, strings);
        JvmProtoBufUtil jvmProtoBufUtil = INSTANCE;
        return new Pair<>(nameResolver, ProtoBuf.Class.parseFrom(input, EXTENSION_REGISTRY));
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, ProtoBuf.Package> readPackageDataFrom(@NotNull String[] data, @NotNull String[] strings) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(strings, "strings");
        JvmProtoBufUtil jvmProtoBufUtil = INSTANCE;
        byte[] bArrDecodeBytes = BitEncoding.decodeBytes(data);
        Intrinsics.checkNotNullExpressionValue(bArrDecodeBytes, "decodeBytes(data)");
        return readPackageDataFrom(bArrDecodeBytes, strings);
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, ProtoBuf.Package> readPackageDataFrom(@NotNull byte[] bytes, @NotNull String[] strings) throws IOException {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Intrinsics.checkNotNullParameter(strings, "strings");
        ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        JvmNameResolver nameResolver = INSTANCE.readNameResolver(input, strings);
        JvmProtoBufUtil jvmProtoBufUtil = INSTANCE;
        return new Pair<>(nameResolver, ProtoBuf.Package.parseFrom(input, EXTENSION_REGISTRY));
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, ProtoBuf.Function> readFunctionDataFrom(@NotNull String[] data, @NotNull String[] strings) throws IOException {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(strings, "strings");
        ByteArrayInputStream input = new ByteArrayInputStream(BitEncoding.decodeBytes(data));
        JvmNameResolver nameResolver = INSTANCE.readNameResolver(input, strings);
        JvmProtoBufUtil jvmProtoBufUtil = INSTANCE;
        return new Pair<>(nameResolver, ProtoBuf.Function.parseFrom(input, EXTENSION_REGISTRY));
    }

    private final JvmNameResolver readNameResolver(InputStream $this$readNameResolver, String[] strings) throws IOException {
        JvmProtoBuf.StringTableTypes delimitedFrom = JvmProtoBuf.StringTableTypes.parseDelimitedFrom($this$readNameResolver, EXTENSION_REGISTRY);
        Intrinsics.checkNotNullExpressionValue(delimitedFrom, "parseDelimitedFrom(this, EXTENSION_REGISTRY)");
        return new JvmNameResolver(delimitedFrom, strings);
    }

    @Nullable
    public final JvmMemberSignature.Method getJvmMethodSignature(@NotNull ProtoBuf.Function proto, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable) {
        String strStringPlus;
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Function, JvmProtoBuf.JvmMethodSignature> methodSignature = JvmProtoBuf.methodSignature;
        Intrinsics.checkNotNullExpressionValue(methodSignature, "methodSignature");
        JvmProtoBuf.JvmMethodSignature signature = (JvmProtoBuf.JvmMethodSignature) ProtoBufUtilKt.getExtensionOrNull(proto, methodSignature);
        int name = (signature == null || !signature.hasName()) ? proto.getName() : signature.getName();
        if (signature != null && signature.hasDesc()) {
            strStringPlus = nameResolver.getString(signature.getDesc());
        } else {
            List listListOfNotNull = CollectionsKt.listOfNotNull(ProtoTypeTableUtilKt.receiverType(proto, typeTable));
            Iterable valueParameterList = proto.getValueParameterList();
            Intrinsics.checkNotNullExpressionValue(valueParameterList, "proto.valueParameterList");
            Iterable $this$map$iv = valueParameterList;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                ProtoBuf.ValueParameter it = (ProtoBuf.ValueParameter) item$iv$iv;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                destination$iv$iv.add(ProtoTypeTableUtilKt.type(it, typeTable));
            }
            Iterable parameterTypes = CollectionsKt.plus((Collection) listListOfNotNull, destination$iv$iv);
            Iterable $this$map$iv2 = parameterTypes;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            for (Object item$iv$iv2 : $this$map$iv2) {
                String strMapTypeDefault = INSTANCE.mapTypeDefault((ProtoBuf.Type) item$iv$iv2, nameResolver);
                if (strMapTypeDefault == null) {
                    return null;
                }
                destination$iv$iv2.add(strMapTypeDefault);
            }
            List parametersDesc = (List) destination$iv$iv2;
            String returnTypeDesc = mapTypeDefault(ProtoTypeTableUtilKt.returnType(proto, typeTable), nameResolver);
            if (returnTypeDesc == null) {
                return null;
            }
            strStringPlus = Intrinsics.stringPlus(CollectionsKt.joinToString$default(parametersDesc, "", "(", ")", 0, null, null, 56, null), returnTypeDesc);
        }
        String desc = strStringPlus;
        return new JvmMemberSignature.Method(nameResolver.getString(name), desc);
    }

    @Nullable
    public final JvmMemberSignature.Method getJvmConstructorSignature(@NotNull ProtoBuf.Constructor proto, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable) {
        String string;
        String strJoinToString$default;
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Constructor, JvmProtoBuf.JvmMethodSignature> constructorSignature = JvmProtoBuf.constructorSignature;
        Intrinsics.checkNotNullExpressionValue(constructorSignature, "constructorSignature");
        JvmProtoBuf.JvmMethodSignature signature = (JvmProtoBuf.JvmMethodSignature) ProtoBufUtilKt.getExtensionOrNull(proto, constructorSignature);
        if (signature != null && signature.hasName()) {
            string = nameResolver.getString(signature.getName());
        } else {
            string = Constants.CONSTRUCTOR_NAME;
        }
        String name = string;
        if (signature != null && signature.hasDesc()) {
            strJoinToString$default = nameResolver.getString(signature.getDesc());
        } else {
            Iterable valueParameterList = proto.getValueParameterList();
            Intrinsics.checkNotNullExpressionValue(valueParameterList, "proto.valueParameterList");
            Iterable $this$map$iv = valueParameterList;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                ProtoBuf.ValueParameter it = (ProtoBuf.ValueParameter) item$iv$iv;
                JvmProtoBufUtil jvmProtoBufUtil = INSTANCE;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                String strMapTypeDefault = jvmProtoBufUtil.mapTypeDefault(ProtoTypeTableUtilKt.type(it, typeTable), nameResolver);
                if (strMapTypeDefault == null) {
                    return null;
                }
                destination$iv$iv.add(strMapTypeDefault);
            }
            strJoinToString$default = CollectionsKt.joinToString$default((List) destination$iv$iv, "", "(", ")V", 0, null, null, 56, null);
        }
        String desc = strJoinToString$default;
        return new JvmMemberSignature.Method(name, desc);
    }

    public static /* synthetic */ JvmMemberSignature.Field getJvmFieldSignature$default(JvmProtoBufUtil jvmProtoBufUtil, ProtoBuf.Property property, NameResolver nameResolver, TypeTable typeTable, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        return jvmProtoBufUtil.getJvmFieldSignature(property, nameResolver, typeTable, z);
    }

    @Nullable
    public final JvmMemberSignature.Field getJvmFieldSignature(@NotNull ProtoBuf.Property proto, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable, boolean requireHasFieldFlag) {
        String string;
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, JvmProtoBuf.JvmPropertySignature> propertySignature = JvmProtoBuf.propertySignature;
        Intrinsics.checkNotNullExpressionValue(propertySignature, "propertySignature");
        JvmProtoBuf.JvmPropertySignature signature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(proto, propertySignature);
        if (signature == null) {
            return null;
        }
        JvmProtoBuf.JvmFieldSignature field = signature.hasField() ? signature.getField() : null;
        if (field == null && requireHasFieldFlag) {
            return null;
        }
        int name = (field == null || !field.hasName()) ? proto.getName() : field.getName();
        if (field == null || !field.hasDesc()) {
            String strMapTypeDefault = mapTypeDefault(ProtoTypeTableUtilKt.returnType(proto, typeTable), nameResolver);
            if (strMapTypeDefault == null) {
                return null;
            }
            string = strMapTypeDefault;
        } else {
            string = nameResolver.getString(field.getDesc());
        }
        String desc = string;
        return new JvmMemberSignature.Field(nameResolver.getString(name), desc);
    }

    private final String mapTypeDefault(ProtoBuf.Type type, NameResolver nameResolver) {
        if (!type.hasClassName()) {
            return null;
        }
        ClassMapperLite classMapperLite = ClassMapperLite.INSTANCE;
        return ClassMapperLite.mapClass(nameResolver.getQualifiedClassName(type.getClassName()));
    }

    @JvmStatic
    public static final boolean isMovedFromInterfaceCompanion(@NotNull ProtoBuf.Property proto) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Flags.BooleanFlagField is_moved_from_interface_companion = JvmFlags.INSTANCE.getIS_MOVED_FROM_INTERFACE_COMPANION();
        Object extension = proto.getExtension(JvmProtoBuf.flags);
        Intrinsics.checkNotNullExpressionValue(extension, "proto.getExtension(JvmProtoBuf.flags)");
        Boolean bool = is_moved_from_interface_companion.get(((Number) extension).intValue());
        Intrinsics.checkNotNullExpressionValue(bool, "JvmFlags.IS_MOVED_FROM_INTERFACE_COMPANION.get(proto.getExtension(JvmProtoBuf.flags))");
        return bool.booleanValue();
    }
}
