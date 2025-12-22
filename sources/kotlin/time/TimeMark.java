package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

/* compiled from: TimeSource.kt */
@SinceKotlin(version = "1.3")
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b'\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u0004H&ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u001b\u0010\n\u001a\u00020��2\u0006\u0010\u000b\u001a\u00020\u0004H\u0096\u0002ø\u0001��¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000e\u001a\u00020��2\u0006\u0010\u000b\u001a\u00020\u0004H\u0096\u0002ø\u0001��¢\u0006\u0004\b\u000f\u0010\r\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0010"}, d2 = {"Lkotlin/time/TimeMark;", "", "()V", "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "()J", "hasNotPassedNow", "", "hasPassedNow", "minus", "duration", "minus-LRDsOJo", "(J)Lkotlin/time/TimeMark;", "plus", "plus-LRDsOJo", "kotlin-stdlib"})
@ExperimentalTime
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/time/TimeMark.class */
public abstract class TimeMark {
    /* renamed from: elapsedNow-UwyO8pc */
    public abstract long mo4048elapsedNowUwyO8pc();

    @NotNull
    /* renamed from: plus-LRDsOJo */
    public TimeMark mo4049plusLRDsOJo(long duration) {
        return new AdjustedTimeMark(this, duration, null);
    }

    @NotNull
    /* renamed from: minus-LRDsOJo, reason: not valid java name */
    public TimeMark m4145minusLRDsOJo(long duration) {
        return mo4049plusLRDsOJo(Duration.m4057unaryMinusUwyO8pc(duration));
    }

    public final boolean hasPassedNow() {
        return !Duration.m4066isNegativeimpl(mo4048elapsedNowUwyO8pc());
    }

    public final boolean hasNotPassedNow() {
        return Duration.m4066isNegativeimpl(mo4048elapsedNowUwyO8pc());
    }
}
