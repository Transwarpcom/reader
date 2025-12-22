package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: SignatureBuildingComponents.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/SignatureBuildingComponents.class */
public final class SignatureBuildingComponents {

    @NotNull
    public static final SignatureBuildingComponents INSTANCE = new SignatureBuildingComponents();

    private SignatureBuildingComponents() {
    }

    @NotNull
    public final String javaLang(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return Intrinsics.stringPlus("java/lang/", name);
    }

    @NotNull
    public final String javaUtil(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return Intrinsics.stringPlus("java/util/", name);
    }

    @NotNull
    public final String javaFunction(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return Intrinsics.stringPlus("java/util/function/", name);
    }

    @NotNull
    public final String[] constructors(@NotNull String... signatures) {
        Intrinsics.checkNotNullParameter(signatures, "signatures");
        Collection destination$iv$iv = new ArrayList(signatures.length);
        for (String str : signatures) {
            destination$iv$iv.add("<init>(" + str + ")V");
        }
        Collection $this$toTypedArray$iv = (List) destination$iv$iv;
        Object[] array = $this$toTypedArray$iv.toArray(new String[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return (String[]) array;
    }

    @NotNull
    public final Set<String> inJavaLang(@NotNull String name, @NotNull String... signatures) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signatures, "signatures");
        String strJavaLang = javaLang(name);
        String[] strArr = new String[signatures.length];
        System.arraycopy(signatures, 0, strArr, 0, signatures.length);
        return inClass(strJavaLang, strArr);
    }

    @NotNull
    public final Set<String> inJavaUtil(@NotNull String name, @NotNull String... signatures) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signatures, "signatures");
        String strJavaUtil = javaUtil(name);
        String[] strArr = new String[signatures.length];
        System.arraycopy(signatures, 0, strArr, 0, signatures.length);
        return inClass(strJavaUtil, strArr);
    }

    @NotNull
    public final Set<String> inClass(@NotNull String internalName, @NotNull String... signatures) {
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        Intrinsics.checkNotNullParameter(signatures, "signatures");
        Collection destination$iv = new LinkedHashSet();
        for (String str : signatures) {
            destination$iv.add(internalName + '.' + str);
        }
        return (Set) destination$iv;
    }

    @NotNull
    public final String signature(@NotNull String internalName, @NotNull String jvmDescriptor) {
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        Intrinsics.checkNotNullParameter(jvmDescriptor, "jvmDescriptor");
        return internalName + '.' + jvmDescriptor;
    }

    @NotNull
    public final String jvmDescriptor(@NotNull String name, @NotNull List<String> parameters, @NotNull String ret) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(ret, "ret");
        return name + '(' + CollectionsKt.joinToString$default(parameters, "", null, null, 0, null, new Function1<String, CharSequence>() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents.jvmDescriptor.1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final CharSequence invoke(@NotNull String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return SignatureBuildingComponents.INSTANCE.escapeClassName(it);
            }
        }, 30, null) + ')' + escapeClassName(ret);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String escapeClassName(String internalName) {
        return internalName.length() > 1 ? 'L' + internalName + ';' : internalName;
    }
}
