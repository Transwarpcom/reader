package kotlinx.coroutines.debug.internal;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* compiled from: Comparisons.kt */
@Metadata(mv = {1, 5, 1}, k = 3, d1 = {"��\n\n��\n\u0002\u0010\b\n\u0002\b\u0007\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "a", "kotlin.jvm.PlatformType", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"})
/* renamed from: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesInfoImpl$lambda-14$$inlined$sortedBy$1, reason: invalid class name */
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/internal/DebugProbesImpl$dumpCoroutinesInfoImpl$lambda-14$$inlined$sortedBy$1.class */
public final class DebugProbesImpl$dumpCoroutinesInfoImpl$lambda14$$inlined$sortedBy$1<T> implements Comparator {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T t, T t2) {
        DebugProbesImpl.CoroutineOwner it = (DebugProbesImpl.CoroutineOwner) t;
        DebugProbesImpl.CoroutineOwner it2 = (DebugProbesImpl.CoroutineOwner) t2;
        return ComparisonsKt.compareValues(Long.valueOf(it.info.sequenceNumber), Long.valueOf(it2.info.sequenceNumber));
    }
}
