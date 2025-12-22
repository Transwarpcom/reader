package io.vertx.kotlin.pgclient.data;

import io.vertx.pgclient.data.Interval;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Interval.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\t\u001aa\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\n\u001a_\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"Interval", "Lio/vertx/pgclient/data/Interval;", "days", "", "hours", "microseconds", "minutes", "months", "seconds", "years", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lio/vertx/pgclient/data/Interval;", "intervalOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/pgclient/data/IntervalKt.class */
public final class IntervalKt {
    @NotNull
    public static /* synthetic */ Interval intervalOf$default(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 4) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 8) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 16) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 32) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 64) != 0) {
            num7 = (Integer) null;
        }
        return intervalOf(num, num2, num3, num4, num5, num6, num7);
    }

    @NotNull
    public static final Interval intervalOf(@Nullable Integer days, @Nullable Integer hours, @Nullable Integer microseconds, @Nullable Integer minutes, @Nullable Integer months, @Nullable Integer seconds, @Nullable Integer years) {
        Interval $this$apply = new Interval();
        if (days != null) {
            $this$apply.setDays(days.intValue());
        }
        if (hours != null) {
            $this$apply.setHours(hours.intValue());
        }
        if (microseconds != null) {
            $this$apply.setMicroseconds(microseconds.intValue());
        }
        if (minutes != null) {
            $this$apply.setMinutes(minutes.intValue());
        }
        if (months != null) {
            $this$apply.setMonths(months.intValue());
        }
        if (seconds != null) {
            $this$apply.setSeconds(seconds.intValue());
        }
        if (years != null) {
            $this$apply.setYears(years.intValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "intervalOf(days, hours, microseconds, minutes, months, seconds, years)"))
    @NotNull
    public static /* synthetic */ Interval Interval$default(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 4) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 8) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 16) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 32) != 0) {
            num6 = (Integer) null;
        }
        if ((i & 64) != 0) {
            num7 = (Integer) null;
        }
        return Interval(num, num2, num3, num4, num5, num6, num7);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "intervalOf(days, hours, microseconds, minutes, months, seconds, years)"))
    @NotNull
    public static final Interval Interval(@Nullable Integer days, @Nullable Integer hours, @Nullable Integer microseconds, @Nullable Integer minutes, @Nullable Integer months, @Nullable Integer seconds, @Nullable Integer years) {
        Interval $this$apply = new Interval();
        if (days != null) {
            $this$apply.setDays(days.intValue());
        }
        if (hours != null) {
            $this$apply.setHours(hours.intValue());
        }
        if (microseconds != null) {
            $this$apply.setMicroseconds(microseconds.intValue());
        }
        if (minutes != null) {
            $this$apply.setMinutes(minutes.intValue());
        }
        if (months != null) {
            $this$apply.setMonths(months.intValue());
        }
        if (seconds != null) {
            $this$apply.setSeconds(seconds.intValue());
        }
        if (years != null) {
            $this$apply.setYears(years.intValue());
        }
        return $this$apply;
    }
}
