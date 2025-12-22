package okhttp3.logging;

import io.legado.app.data.entities.Book;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.internal.platform.Platform;
import org.jetbrains.annotations.NotNull;

/* compiled from: HttpLoggingInterceptor.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0006\u0018��2\u00020\u0001:\u0002\u001e\u001fB\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\r\u0010\u000b\u001a\u00020\tH\u0007¢\u0006\u0002\b\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0007J\u0010\u0010\u001d\u001a\u00020��2\u0006\u0010\n\u001a\u00020\tH\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n��R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@GX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\f\"\u0004\b\n\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001¨\u0006 "}, d2 = {"Lokhttp3/logging/HttpLoggingInterceptor;", "Lokhttp3/Interceptor;", "logger", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "(Lokhttp3/logging/HttpLoggingInterceptor$Logger;)V", "headersToRedact", "", "", "<set-?>", "Lokhttp3/logging/HttpLoggingInterceptor$Level;", "level", "getLevel", "()Lokhttp3/logging/HttpLoggingInterceptor$Level;", "(Lokhttp3/logging/HttpLoggingInterceptor$Level;)V", "bodyHasUnknownEncoding", "", "headers", "Lokhttp3/Headers;", "-deprecated_level", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "logHeader", "", "i", "", "redactHeader", "name", "setLevel", org.apache.logging.log4j.Level.CATEGORY, "Logger", "okhttp-logging-interceptor"})
/* loaded from: reader.jar:BOOT-INF/lib/logging-interceptor-4.1.0.jar:okhttp3/logging/HttpLoggingInterceptor.class */
public final class HttpLoggingInterceptor implements Interceptor {
    private volatile Set<String> headersToRedact;

    @NotNull
    private volatile Level level;
    private final Logger logger;

    /* compiled from: HttpLoggingInterceptor.kt */
    @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lokhttp3/logging/HttpLoggingInterceptor$Level;", "", "(Ljava/lang/String;I)V", "NONE", "BASIC", "HEADERS", "BODY", "okhttp-logging-interceptor"})
    /* loaded from: reader.jar:BOOT-INF/lib/logging-interceptor-4.1.0.jar:okhttp3/logging/HttpLoggingInterceptor$Level.class */
    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    @JvmOverloads
    public HttpLoggingInterceptor() {
        this(null, 1, null);
    }

    @JvmOverloads
    public HttpLoggingInterceptor(@NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.logger = logger;
        this.headersToRedact = SetsKt.emptySet();
        this.level = Level.NONE;
    }

    public /* synthetic */ HttpLoggingInterceptor(Logger logger, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Logger.DEFAULT : logger);
    }

    @NotNull
    public final Level getLevel() {
        return this.level;
    }

    @JvmName(name = "level")
    public final void level(@NotNull Level level) {
        Intrinsics.checkParameterIsNotNull(level, "<set-?>");
        this.level = level;
    }

    /* compiled from: HttpLoggingInterceptor.kt */
    @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018�� \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001¨\u0006\u0007"}, d2 = {"Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "", "log", "", "message", "", "Companion", "okhttp-logging-interceptor"})
    /* loaded from: reader.jar:BOOT-INF/lib/logging-interceptor-4.1.0.jar:okhttp3/logging/HttpLoggingInterceptor$Logger.class */
    public interface Logger {
        public static final Companion Companion = new Companion(null);

        @JvmField
        @NotNull
        public static final Logger DEFAULT = new Logger() { // from class: okhttp3.logging.HttpLoggingInterceptor$Logger$Companion$DEFAULT$1
            @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
            public void log(@NotNull String message) {
                Intrinsics.checkParameterIsNotNull(message, "message");
                Platform.Companion.get().log(4, message, (Throwable) null);
            }
        };

        void log(@NotNull String str);

        /* compiled from: HttpLoggingInterceptor.kt */
        @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001��¢\u0006\u0002\n��¨\u0006\u0001\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001¨\u0006\u0005"}, d2 = {"Lokhttp3/logging/HttpLoggingInterceptor$Logger$Companion;", "", "()V", Book.imgStyleDefault, "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "okhttp-logging-interceptor"})
        /* loaded from: reader.jar:BOOT-INF/lib/logging-interceptor-4.1.0.jar:okhttp3/logging/HttpLoggingInterceptor$Logger$Companion.class */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = null;

            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    public final void redactHeader(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        TreeSet newHeadersToRedact = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        CollectionsKt.addAll(newHeadersToRedact, this.headersToRedact);
        newHeadersToRedact.add(name);
        this.headersToRedact = newHeadersToRedact;
    }

    @Deprecated(message = "Moved to var. Replace setLevel(...) with level(...) to fix Java", replaceWith = @ReplaceWith(imports = {}, expression = "apply { this.level = level }"), level = DeprecationLevel.WARNING)
    @NotNull
    public final HttpLoggingInterceptor setLevel(@NotNull Level level) {
        Intrinsics.checkParameterIsNotNull(level, "level");
        HttpLoggingInterceptor $this$apply = this;
        $this$apply.level = level;
        return this;
    }

    @Deprecated(message = "moved to var", replaceWith = @ReplaceWith(imports = {}, expression = "level"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_level")
    @NotNull
    /* renamed from: -deprecated_level, reason: not valid java name */
    public final Level m4639deprecated_level() {
        return this.level;
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x0546  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0241  */
    @Override // okhttp3.Interceptor
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public okhttp3.Response intercept(@org.jetbrains.annotations.NotNull okhttp3.Interceptor.Chain r7) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1560
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.logging.HttpLoggingInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    private final void logHeader(Headers headers, int i) {
        String value = this.headersToRedact.contains(headers.name(i)) ? "██" : headers.value(i);
        this.logger.log(headers.name(i) + ": " + value);
    }

    private final boolean bodyHasUnknownEncoding(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return (contentEncoding == null || StringsKt.equals(contentEncoding, "identity", true) || StringsKt.equals(contentEncoding, "gzip", true)) ? false : true;
    }
}
