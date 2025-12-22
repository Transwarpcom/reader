package kotlin.reflect;

import java.lang.reflect.Type;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypesJVM.kt */
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\bc\u0018��2\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lkotlin/reflect/TypeImpl;", "Ljava/lang/reflect/Type;", "getTypeName", "", "kotlin-stdlib"})
@ExperimentalStdlibApi
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/reflect/TypeImpl.class */
interface TypeImpl extends Type {
    @NotNull
    String getTypeName();
}
