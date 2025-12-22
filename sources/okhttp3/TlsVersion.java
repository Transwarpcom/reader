package okhttp3;

import cn.hutool.core.net.SSLProtocols;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: TlsVersion.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0001\u0018�� \f2\b\u0012\u0004\u0012\u00020��0\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0006R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0005j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lokhttp3/TlsVersion;", "", "javaName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "()Ljava/lang/String;", "-deprecated_javaName", "TLS_1_3", "TLS_1_2", "TLS_1_1", "TLS_1_0", "SSL_3_0", "Companion", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/TlsVersion.class */
public enum TlsVersion {
    TLS_1_3("TLSv1.3"),
    TLS_1_2(SSLProtocols.TLSv12),
    TLS_1_1(SSLProtocols.TLSv11),
    TLS_1_0(SSLProtocols.TLSv1),
    SSL_3_0(SSLProtocols.SSLv3);


    @NotNull
    private final String javaName;
    public static final Companion Companion = new Companion(null);

    @JvmStatic
    @NotNull
    public static final TlsVersion forJavaName(@NotNull String javaName) {
        return Companion.forJavaName(javaName);
    }

    TlsVersion(String javaName) {
        this.javaName = javaName;
    }

    @JvmName(name = "javaName")
    @NotNull
    public final String javaName() {
        return this.javaName;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "javaName"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_javaName")
    @NotNull
    /* renamed from: -deprecated_javaName, reason: not valid java name */
    public final String m4574deprecated_javaName() {
        return this.javaName;
    }

    /* compiled from: TlsVersion.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lokhttp3/TlsVersion$Companion;", "", "()V", "forJavaName", "Lokhttp3/TlsVersion;", "javaName", "", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/TlsVersion$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @JvmStatic
        @NotNull
        public final TlsVersion forJavaName(@NotNull String javaName) {
            Intrinsics.checkNotNullParameter(javaName, "javaName");
            switch (javaName.hashCode()) {
                case -503070503:
                    if (javaName.equals(SSLProtocols.TLSv11)) {
                        return TlsVersion.TLS_1_1;
                    }
                    break;
                case -503070502:
                    if (javaName.equals(SSLProtocols.TLSv12)) {
                        return TlsVersion.TLS_1_2;
                    }
                    break;
                case -503070501:
                    if (javaName.equals("TLSv1.3")) {
                        return TlsVersion.TLS_1_3;
                    }
                    break;
                case 79201641:
                    if (javaName.equals(SSLProtocols.SSLv3)) {
                        return TlsVersion.SSL_3_0;
                    }
                    break;
                case 79923350:
                    if (javaName.equals(SSLProtocols.TLSv1)) {
                        return TlsVersion.TLS_1_0;
                    }
                    break;
            }
            throw new IllegalArgumentException("Unexpected TLS version: " + javaName);
        }
    }
}
