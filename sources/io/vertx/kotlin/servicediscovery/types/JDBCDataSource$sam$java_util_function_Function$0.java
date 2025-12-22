package io.vertx.kotlin.servicediscovery.types;

import java.util.function.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* compiled from: JDBCDataSource.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/JDBCDataSource$sam$java_util_function_Function$0.class */
final class JDBCDataSource$sam$java_util_function_Function$0 implements Function {
    private final /* synthetic */ Function1 function;

    JDBCDataSource$sam$java_util_function_Function$0(Function1 function1) {
        this.function = function1;
    }

    @Override // java.util.function.Function
    public final /* synthetic */ Object apply(Object p0) {
        return this.function.invoke(p0);
    }
}
