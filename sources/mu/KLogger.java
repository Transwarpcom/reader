package mu;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.Marker;

/* compiled from: KLogger.kt */
@Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0003\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018��2\u00020\u0001J\u0018\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\"\u0010\u0005\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J(\u0010\u0005\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J2\u0010\u0005\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\u0018\u0010\u000f\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\"\u0010\u000f\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J(\u0010\u000f\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J2\u0010\u000f\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\u0018\u0010\u0010\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\"\u0010\u0010\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J(\u0010\u0010\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J2\u0010\u0010\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\u0018\u0010\u0011\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\"\u0010\u0011\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J(\u0010\u0011\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J2\u0010\u0011\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\u0018\u0010\u0012\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\"\u0010\u0012\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J(\u0010\u0012\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J2\u0010\u0012\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&R\u0012\u0010\u0002\u001a\u00020\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0013"}, d2 = {"Lmu/KLogger;", "Lorg/slf4j/Logger;", "underlyingLogger", "getUnderlyingLogger", "()Lorg/slf4j/Logger;", "debug", "", "msg", "Lkotlin/Function0;", "", "t", "", "marker", "Lorg/slf4j/Marker;", "Lmu/Marker;", "error", "info", "trace", "warn", "kotlin-logging"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-logging-1.6.24.jar:mu/KLogger.class */
public interface KLogger extends Logger {
    @NotNull
    Logger getUnderlyingLogger();

    void trace(@NotNull Function0<? extends Object> function0);

    void debug(@NotNull Function0<? extends Object> function0);

    void info(@NotNull Function0<? extends Object> function0);

    void warn(@NotNull Function0<? extends Object> function0);

    void error(@NotNull Function0<? extends Object> function0);

    void trace(@Nullable Throwable th, @NotNull Function0<? extends Object> function0);

    void debug(@Nullable Throwable th, @NotNull Function0<? extends Object> function0);

    void info(@Nullable Throwable th, @NotNull Function0<? extends Object> function0);

    void warn(@Nullable Throwable th, @NotNull Function0<? extends Object> function0);

    void error(@Nullable Throwable th, @NotNull Function0<? extends Object> function0);

    void trace(@Nullable Marker marker, @NotNull Function0<? extends Object> function0);

    void debug(@Nullable Marker marker, @NotNull Function0<? extends Object> function0);

    void info(@Nullable Marker marker, @NotNull Function0<? extends Object> function0);

    void warn(@Nullable Marker marker, @NotNull Function0<? extends Object> function0);

    void error(@Nullable Marker marker, @NotNull Function0<? extends Object> function0);

    void trace(@Nullable Marker marker, @Nullable Throwable th, @NotNull Function0<? extends Object> function0);

    void debug(@Nullable Marker marker, @Nullable Throwable th, @NotNull Function0<? extends Object> function0);

    void info(@Nullable Marker marker, @Nullable Throwable th, @NotNull Function0<? extends Object> function0);

    void warn(@Nullable Marker marker, @Nullable Throwable th, @NotNull Function0<? extends Object> function0);

    void error(@Nullable Marker marker, @Nullable Throwable th, @NotNull Function0<? extends Object> function0);
}
