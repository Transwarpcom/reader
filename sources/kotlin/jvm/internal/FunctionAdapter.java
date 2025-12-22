package kotlin.jvm.internal;

import kotlin.Function;
import kotlin.SinceKotlin;

@SinceKotlin(version = "1.4")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/jvm/internal/FunctionAdapter.class */
public interface FunctionAdapter {
    Function<?> getFunctionDelegate();
}
