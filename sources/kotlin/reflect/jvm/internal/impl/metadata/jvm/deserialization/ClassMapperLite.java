package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDListAttributeObject;
import org.jetbrains.annotations.NotNull;

/* compiled from: ClassMapperLite.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/ClassMapperLite.class */
public final class ClassMapperLite {

    @NotNull
    public static final ClassMapperLite INSTANCE = new ClassMapperLite();

    /* renamed from: kotlin, reason: collision with root package name */
    @NotNull
    private static final String f9kotlin = CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new Character[]{'k', 'o', 't', 'l', 'i', 'n'}), "", null, null, 0, null, null, 62, null);

    @NotNull
    private static final Map<String, String> map;

    private ClassMapperLite() {
    }

    static {
        int i;
        Map $this$map_u24lambda_u2d0 = new LinkedHashMap();
        List primitives = CollectionsKt.listOf((Object[]) new String[]{"Boolean", "Z", "Char", "C", "Byte", "B", "Short", "S", "Int", "I", "Float", "F", "Long", OperatorName.SET_LINE_CAPSTYLE, PDLayoutAttributeObject.BORDER_STYLE_DOUBLE, "D"});
        int i2 = 0;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, primitives.size() - 1, 2);
        if (0 <= progressionLastElement) {
            do {
                i = i2;
                i2 += 2;
                $this$map_u24lambda_u2d0.put(f9kotlin + '/' + ((String) primitives.get(i)), primitives.get(i + 1));
                $this$map_u24lambda_u2d0.put(f9kotlin + '/' + ((String) primitives.get(i)) + "Array", Intrinsics.stringPlus("[", primitives.get(i + 1)));
            } while (i != progressionLastElement);
        }
        $this$map_u24lambda_u2d0.put(Intrinsics.stringPlus(f9kotlin, "/Unit"), "V");
        m3755map$lambda0$add($this$map_u24lambda_u2d0, "Any", "java/lang/Object");
        m3755map$lambda0$add($this$map_u24lambda_u2d0, "Nothing", "java/lang/Void");
        m3755map$lambda0$add($this$map_u24lambda_u2d0, "Annotation", "java/lang/annotation/Annotation");
        for (String klass : CollectionsKt.listOf((Object[]) new String[]{"String", "CharSequence", "Throwable", "Cloneable", "Number", "Comparable", "Enum"})) {
            m3755map$lambda0$add($this$map_u24lambda_u2d0, klass, Intrinsics.stringPlus("java/lang/", klass));
        }
        for (String klass2 : CollectionsKt.listOf((Object[]) new String[]{"Iterator", "Collection", PDListAttributeObject.OWNER_LIST, "Set", "Map", "ListIterator"})) {
            m3755map$lambda0$add($this$map_u24lambda_u2d0, Intrinsics.stringPlus("collections/", klass2), Intrinsics.stringPlus("java/util/", klass2));
            m3755map$lambda0$add($this$map_u24lambda_u2d0, Intrinsics.stringPlus("collections/Mutable", klass2), Intrinsics.stringPlus("java/util/", klass2));
        }
        m3755map$lambda0$add($this$map_u24lambda_u2d0, "collections/Iterable", "java/lang/Iterable");
        m3755map$lambda0$add($this$map_u24lambda_u2d0, "collections/MutableIterable", "java/lang/Iterable");
        m3755map$lambda0$add($this$map_u24lambda_u2d0, "collections/Map.Entry", "java/util/Map$Entry");
        m3755map$lambda0$add($this$map_u24lambda_u2d0, "collections/MutableMap.MutableEntry", "java/util/Map$Entry");
        int i3 = 0;
        do {
            int i4 = i3;
            i3++;
            m3755map$lambda0$add($this$map_u24lambda_u2d0, Intrinsics.stringPlus("Function", Integer.valueOf(i4)), f9kotlin + "/jvm/functions/Function" + i4);
            m3755map$lambda0$add($this$map_u24lambda_u2d0, Intrinsics.stringPlus("reflect/KFunction", Integer.valueOf(i4)), Intrinsics.stringPlus(f9kotlin, "/reflect/KFunction"));
        } while (i3 <= 22);
        for (String klass3 : CollectionsKt.listOf((Object[]) new String[]{"Char", "Byte", "Short", "Int", "Float", "Long", PDLayoutAttributeObject.BORDER_STYLE_DOUBLE, "String", "Enum"})) {
            m3755map$lambda0$add($this$map_u24lambda_u2d0, Intrinsics.stringPlus(klass3, ".Companion"), f9kotlin + "/jvm/internal/" + klass3 + "CompanionObject");
        }
        map = $this$map_u24lambda_u2d0;
    }

    /* renamed from: map$lambda-0$add, reason: not valid java name */
    private static final void m3755map$lambda0$add(Map<String, String> map2, String kotlinSimpleName, String javaInternalName) {
        map2.put(f9kotlin + '/' + kotlinSimpleName, 'L' + javaInternalName + ';');
    }

    @JvmStatic
    @NotNull
    public static final String mapClass(@NotNull String classId) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        String str = map.get(classId);
        return str == null ? 'L' + StringsKt.replace$default(classId, '.', '$', false, 4, (Object) null) + ';' : str;
    }
}
