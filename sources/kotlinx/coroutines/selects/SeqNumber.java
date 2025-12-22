package kotlinx.coroutines.selects;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Constants;

/* compiled from: Select.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010��\b��\u0018��2\u00020\u0007B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\r\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/selects/SeqNumber;", Constants.CONSTRUCTOR_NAME, "()V", "", "next", "()J", "kotlinx-coroutines-core", ""})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/selects/SeqNumber.class */
public final class SeqNumber {

    @NotNull
    private volatile /* synthetic */ long number = 1;
    private static final /* synthetic */ AtomicLongFieldUpdater number$FU = AtomicLongFieldUpdater.newUpdater(SeqNumber.class, "number");

    public final long next() {
        return number$FU.incrementAndGet(this);
    }
}
