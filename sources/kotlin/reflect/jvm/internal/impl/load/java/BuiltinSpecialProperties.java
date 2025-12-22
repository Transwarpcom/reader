package kotlin.reflect.jvm.internal.impl.load.java;

import com.jayway.jsonpath.internal.function.text.Length;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: BuiltinSpecialProperties.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/BuiltinSpecialProperties.class */
public final class BuiltinSpecialProperties {

    @NotNull
    public static final BuiltinSpecialProperties INSTANCE = new BuiltinSpecialProperties();

    @NotNull
    private static final Map<FqName, Name> PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP = MapsKt.mapOf(TuplesKt.to(BuiltinSpecialPropertiesKt.childSafe(StandardNames.FqNames._enum, "name"), Name.identifier("name")), TuplesKt.to(BuiltinSpecialPropertiesKt.childSafe(StandardNames.FqNames._enum, "ordinal"), Name.identifier("ordinal")), TuplesKt.to(BuiltinSpecialPropertiesKt.child(StandardNames.FqNames.collection, "size"), Name.identifier("size")), TuplesKt.to(BuiltinSpecialPropertiesKt.child(StandardNames.FqNames.map, "size"), Name.identifier("size")), TuplesKt.to(BuiltinSpecialPropertiesKt.childSafe(StandardNames.FqNames.charSequence, Length.TOKEN_NAME), Name.identifier(Length.TOKEN_NAME)), TuplesKt.to(BuiltinSpecialPropertiesKt.child(StandardNames.FqNames.map, "keys"), Name.identifier("keySet")), TuplesKt.to(BuiltinSpecialPropertiesKt.child(StandardNames.FqNames.map, "values"), Name.identifier("values")), TuplesKt.to(BuiltinSpecialPropertiesKt.child(StandardNames.FqNames.map, "entries"), Name.identifier("entrySet")));

    @NotNull
    private static final Map<Name, List<Name>> GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP;

    @NotNull
    private static final Set<FqName> SPECIAL_FQ_NAMES;

    @NotNull
    private static final Set<Name> SPECIAL_SHORT_NAMES;

    private BuiltinSpecialProperties() {
    }

    @NotNull
    public final Map<FqName, Name> getPROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP() {
        return PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP;
    }

    static {
        Object obj;
        BuiltinSpecialProperties builtinSpecialProperties = INSTANCE;
        Iterable $this$map$iv = PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP.entrySet();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Map.Entry it = (Map.Entry) item$iv$iv;
            destination$iv$iv.add(new Pair(((FqName) it.getKey()).shortName(), it.getValue()));
        }
        Iterable $this$groupBy$iv = (List) destination$iv$iv;
        Map destination$iv$iv2 = new LinkedHashMap();
        for (Object element$iv$iv : $this$groupBy$iv) {
            Pair it2 = (Pair) element$iv$iv;
            Name name = (Name) it2.getSecond();
            Object value$iv$iv$iv = destination$iv$iv2.get(name);
            if (value$iv$iv$iv == null) {
                ArrayList arrayList = new ArrayList();
                destination$iv$iv2.put(name, arrayList);
                obj = arrayList;
            } else {
                obj = value$iv$iv$iv;
            }
            List list$iv$iv = (List) obj;
            Pair it3 = (Pair) element$iv$iv;
            list$iv$iv.add((Name) it3.getFirst());
        }
        Map destination$iv$iv3 = new LinkedHashMap(MapsKt.mapCapacity(destination$iv$iv2.size()));
        Iterable $this$associateByTo$iv$iv$iv = destination$iv$iv2.entrySet();
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
            Map.Entry it4 = (Map.Entry) element$iv$iv$iv;
            destination$iv$iv3.put(it$iv$iv.getKey(), CollectionsKt.distinct((Iterable) it4.getValue()));
        }
        GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP = destination$iv$iv3;
        BuiltinSpecialProperties builtinSpecialProperties2 = INSTANCE;
        SPECIAL_FQ_NAMES = PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP.keySet();
        BuiltinSpecialProperties builtinSpecialProperties3 = INSTANCE;
        Iterable $this$map$iv2 = SPECIAL_FQ_NAMES;
        Collection destination$iv$iv4 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            FqName p0 = (FqName) item$iv$iv2;
            destination$iv$iv4.add(p0.shortName());
        }
        SPECIAL_SHORT_NAMES = CollectionsKt.toSet((List) destination$iv$iv4);
    }

    @NotNull
    public final Set<FqName> getSPECIAL_FQ_NAMES() {
        return SPECIAL_FQ_NAMES;
    }

    @NotNull
    public final Set<Name> getSPECIAL_SHORT_NAMES() {
        return SPECIAL_SHORT_NAMES;
    }

    @NotNull
    public final List<Name> getPropertyNameCandidatesBySpecialGetterName(@NotNull Name name1) {
        Intrinsics.checkNotNullParameter(name1, "name1");
        List<Name> list = GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP.get(name1);
        return list == null ? CollectionsKt.emptyList() : list;
    }
}
