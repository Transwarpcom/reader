package kotlinx.coroutines.flow;

import kotlin.Metadata;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* compiled from: StateFlow.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J\u001d\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028��2\u0006\u0010\f\u001a\u00028��H&¢\u0006\u0002\u0010\rR\u0018\u0010\u0004\u001a\u00028��X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/flow/MutableStateFlow;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/flow/StateFlow;", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "value", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "compareAndSet", "", "expect", "update", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/MutableStateFlow.class */
public interface MutableStateFlow<T> extends StateFlow<T>, MutableSharedFlow<T> {
    @Override // kotlinx.coroutines.flow.StateFlow
    T getValue();

    void setValue(T t);

    boolean compareAndSet(T t, T t2);
}
