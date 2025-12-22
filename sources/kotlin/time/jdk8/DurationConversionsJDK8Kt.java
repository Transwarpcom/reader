package kotlin.time.jdk8;

import java.time.Duration;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ExperimentalTime;

/* compiled from: DurationConversions.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001a\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0015\u0010\u0005\u001a\u00020\u0002*\u00020\u0001H\u0087\bø\u0001��¢\u0006\u0002\u0010\u0006\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0007"}, d2 = {"toJavaDuration", "Ljava/time/Duration;", "Lkotlin/time/Duration;", "toJavaDuration-LRDsOJo", "(J)Ljava/time/Duration;", "toKotlinDuration", "(Ljava/time/Duration;)J", "kotlin-stdlib-jdk8"}, pn = "kotlin.time")
@JvmName(name = "DurationConversionsJDK8Kt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-jdk8-1.5.21.jar:kotlin/time/jdk8/DurationConversionsJDK8Kt.class */
public final class DurationConversionsJDK8Kt {
    @SinceKotlin(version = "1.3")
    @ExperimentalTime
    @InlineOnly
    private static final long toKotlinDuration(Duration $this$toKotlinDuration) {
        return kotlin.time.Duration.m4058plusLRDsOJo(kotlin.time.Duration.Companion.m4127secondsUwyO8pc($this$toKotlinDuration.getSeconds()), kotlin.time.Duration.Companion.m4117nanosecondsUwyO8pc($this$toKotlinDuration.getNano()));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalTime
    @InlineOnly
    /* renamed from: toJavaDuration-LRDsOJo, reason: not valid java name */
    private static final Duration m4153toJavaDurationLRDsOJo(long $this$toJavaDuration) {
        long seconds = kotlin.time.Duration.m4093getInWholeSecondsimpl($this$toJavaDuration);
        int nanoseconds = kotlin.time.Duration.m4079getNanosecondsComponentimpl($this$toJavaDuration);
        Duration durationOfSeconds = Duration.ofSeconds(seconds, nanoseconds);
        Intrinsics.checkNotNullExpressionValue(durationOfSeconds, "toComponents { seconds, …, nanoseconds.toLong()) }");
        return durationOfSeconds;
    }
}
