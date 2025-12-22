package mu;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import mu.KLoggable;
import org.jetbrains.annotations.NotNull;

/* compiled from: KLogging.kt */
@Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lmu/NamedKLogging;", "Lmu/KLoggable;", "name", "", "(Ljava/lang/String;)V", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "kotlin-logging"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-logging-1.6.24.jar:mu/NamedKLogging.class */
public class NamedKLogging implements KLoggable {

    @NotNull
    private final KLogger logger;

    public NamedKLogging(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.logger = logger(name);
    }

    @Override // mu.KLoggable
    @NotNull
    public KLogger logger() {
        return KLoggable.DefaultImpls.logger(this);
    }

    @Override // mu.KLoggable
    @NotNull
    public KLogger logger(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return KLoggable.DefaultImpls.logger(this, name);
    }

    @Override // mu.KLoggable
    @NotNull
    public KLogger getLogger() {
        return this.logger;
    }
}
