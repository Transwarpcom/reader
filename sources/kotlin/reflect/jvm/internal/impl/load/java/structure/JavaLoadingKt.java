package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;

/* compiled from: javaLoading.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/structure/JavaLoadingKt.class */
public final class JavaLoadingKt {
    public static final boolean isObjectMethodInInterface(@NotNull JavaMember $this$isObjectMethodInInterface) {
        Intrinsics.checkNotNullParameter($this$isObjectMethodInInterface, "<this>");
        return $this$isObjectMethodInInterface.getContainingClass().isInterface() && ($this$isObjectMethodInInterface instanceof JavaMethod) && isObjectMethod((JavaMethod) $this$isObjectMethodInInterface);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x004e, code lost:
    
        if (r0.equals("toString") == false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:?, code lost:
    
        return r3.getValueParameters().isEmpty();
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0036, code lost:
    
        if (r0.equals(org.springframework.jmx.export.naming.IdentityNamingStrategy.HASH_CODE_KEY) == false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final boolean isObjectMethod(kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod r3) {
        /*
            r0 = r3
            kotlin.reflect.jvm.internal.impl.name.Name r0 = r0.getName()
            java.lang.String r0 = r0.asString()
            r4 = r0
            r0 = r4
            int r0 = r0.hashCode()
            switch(r0) {
                case -1776922004: goto L48;
                case -1295482945: goto L3c;
                case 147696667: goto L30;
                default: goto L69;
            }
        L30:
            r0 = r4
            java.lang.String r1 = "hashCode"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L54
            goto L69
        L3c:
            r0 = r4
            java.lang.String r1 = "equals"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L62
            goto L69
        L48:
            r0 = r4
            java.lang.String r1 = "toString"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L54
            goto L69
        L54:
            r0 = r3
            java.util.List r0 = r0.getValueParameters()
            boolean r0 = r0.isEmpty()
            goto L6a
        L62:
            r0 = r3
            boolean r0 = isMethodWithOneObjectParameter(r0)
            goto L6a
        L69:
            r0 = 0
        L6a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.structure.JavaLoadingKt.isObjectMethod(kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod):boolean");
    }

    private static final boolean isMethodWithOneObjectParameter(JavaMethod method) {
        FqName classFqName;
        List parameters = method.getValueParameters();
        JavaValueParameter javaValueParameter = (JavaValueParameter) CollectionsKt.singleOrNull(parameters);
        JavaType type = javaValueParameter == null ? null : javaValueParameter.getType();
        JavaClassifierType type2 = type instanceof JavaClassifierType ? (JavaClassifierType) type : null;
        if (type2 == null) {
            return false;
        }
        JavaClassifier classifier = type2.getClassifier();
        return (classifier instanceof JavaClass) && (classFqName = ((JavaClass) classifier).getFqName()) != null && Intrinsics.areEqual(classFqName.asString(), "java.lang.Object");
    }
}
