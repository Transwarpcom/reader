package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

/* compiled from: MonoTimeSource.kt */
@SinceKotlin(version = "1.3")
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n��\bÁ\u0002\u0018��2\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lkotlin/time/MonotonicTimeSource;", "Lkotlin/time/AbstractLongTimeSource;", "Lkotlin/time/TimeSource;", "()V", "read", "", "toString", "", "kotlin-stdlib"})
@ExperimentalTime
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/time/MonotonicTimeSource.class */
public final class MonotonicTimeSource extends AbstractLongTimeSource implements TimeSource {

    @NotNull
    public static final MonotonicTimeSource INSTANCE = new MonotonicTimeSource();

    private MonotonicTimeSource() {
        super(TimeUnit.NANOSECONDS);
    }

    @Override // kotlin.time.AbstractLongTimeSource
    protected long read() {
        return System.nanoTime();
    }

    @NotNull
    public String toString() {
        return "TimeSource(System.nanoTime())";
    }
}
